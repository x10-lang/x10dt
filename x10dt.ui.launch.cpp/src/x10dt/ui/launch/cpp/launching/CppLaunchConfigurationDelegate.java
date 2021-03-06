/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching;

import static org.eclipse.ptp.core.IPTPLaunchConfigurationConstants.ATTR_ARGUMENTS;
import static org.eclipse.ptp.core.IPTPLaunchConfigurationConstants.ATTR_PROJECT_NAME;
import static x10dt.ui.launch.cpp.launching.CommunicationInterfaceTab.HOST_FILE;
import static x10dt.ui.launch.cpp.launching.CommunicationInterfaceTab.HOST_LIST;
import static x10dt.ui.launch.cpp.launching.CommunicationInterfaceTab.LOAD_LEVELER;
import static x10dt.ui.launch.cpp.launching.CppBackEndLaunchConfAttrs.ATTR_X10_MAIN_CLASS;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.PAMI;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.SOCKETS;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.STANDALONE;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.variables.VariablesPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.imp.preferences.PreferencesService;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import x10cpp.X10CPPCompilerOptions;
import x10cpp.visit.MessagePassingCodeGenerator;
import x10dt.core.preferences.generated.X10Constants;
import x10dt.core.utils.CompilerOptionsFactory;
import x10dt.core.utils.X10BundleUtils;
import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.LaunchCore;
import x10dt.ui.launch.core.Messages;
import x10dt.ui.launch.core.builder.AbstractX10Builder;
import x10dt.ui.launch.core.platform_conf.ETargetOS;
import x10dt.ui.launch.core.utils.CoreResourceUtils;
import x10dt.ui.launch.core.utils.IProcessOuputListener;
import x10dt.ui.launch.core.utils.LaunchUtils;
import x10dt.ui.launch.core.utils.PTPConstants;
import x10dt.ui.launch.core.utils.UIUtils;
import x10dt.ui.launch.core.utils.X10BuilderUtils;
import x10dt.ui.launch.cpp.CppLaunchCore;
import x10dt.ui.launch.cpp.LaunchMessages;
import x10dt.ui.launch.cpp.builder.target_op.ITargetOpHelper;
import x10dt.ui.launch.cpp.builder.target_op.TargetOpHelperFactory;
import x10dt.ui.launch.cpp.builder.target_op.CygwinTargetOpHelper;
import x10dt.ui.launch.cpp.utils.PlatformConfUtils;

/**
 * Performs linking and launching of C++ application generated by X10 back-end.
 * 
 * @author egeay
 */
public class CppLaunchConfigurationDelegate implements ILaunchConfigurationDelegate {
  private static String ATTR_ENV = "org.eclipse.debug.core.environmentVariables";
  private static String NPLACES_SOCKETS = "X10_NPLACES";
  private static String HOSTFILE_SOCKETS = "X10_HOSTFILE";
  private static String HOSTLIST_SOCKETS = "X10_HOSTLIST";
  private static String NPLACES_PAMI = "MP_PROCS";
  private static String HOSTLIST_PAMI = "MP_HOSTFILE";
  private static final String PATH_ENV = "PATH"; 
  
  // --- Overridden methods
  
  public void launch(final ILaunchConfiguration configuration, final String mode, final ILaunch launch,
                     final IProgressMonitor monitor) throws CoreException {
    try {
      // Performs linking first.
      monitor.beginTask(null, 10);
      monitor.subTask(LaunchMessages.CLCD_ExecCreationTaskName);

      
      this.fProject = ResourcesPlugin.getWorkspace().getRoot().getProject(configuration.getAttribute(ATTR_PROJECT_NAME, Constants.EMPTY_STR));
      this.fCompilationConfiguration = ConfUtils.getConfiguration(this.fProject.getName());
      
      // The following check shouldn't be necessary, since this ILaunchConfigurationDelegate implementation
      // isn't used for debug-mode launches. I.e., we should never get here in that case.
      if (ILaunchManager.DEBUG_MODE.equals(mode) && 
          !new PreferencesService(fProject).getBooleanPreference(X10Constants.P_DEBUG)) {
          ErrorDialog.openError(null, x10dt.ui.Messages.AXLS_NoDebugInfoErrorTitle, LaunchMessages.CLCD_AppBuiltWithoutDebug,
                                new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, Constants.EMPTY_STR));
          return;
      }

     
      if (!monitor.isCanceled() && shouldProcessToLinkStep(fProject) &&
          createExecutable(configuration, fProject, new SubProgressMonitor(monitor, 5)) == 0) {
  
        if (!monitor.isCanceled()) {
          // Hijack launch if it's PAMI with LoadLeveler
          if (PAMI.equals(ConfUtils.getServiceTypeId(this.fCompilationConfiguration)) &&
              ConfUtils.hostSelectionMode(configuration) == LOAD_LEVELER){
            launchPAMIwLL(configuration, fProject, new SubProgressMonitor(monitor, 5));
            
          } else {
            monitor.subTask(LaunchMessages.CLCD_LaunchCreationTaskName);
            runCommand(configuration, monitor);
          }
        }
      }
    } finally {
      monitor.done();
    }
  }
  
  private void runCommand(ILaunchConfiguration configuration, final IProgressMonitor monitor) throws CoreException {
    try {
      final MessageConsole messageConsole = UIUtils.findOrCreateX10Console();
      final MessageConsoleStream mcStream = messageConsole.newMessageStream();
      messageConsole.activate();
      messageConsole.clearConsole();
      
      final Map<String,String> env = new HashMap<String,String>();
      final int NPlaces = ConfUtils.getNPlaces(configuration);
      String transport = ConfUtils.getServiceTypeId(this.fCompilationConfiguration);
      
      if (transport.equals(STANDALONE)){
        env.put(NPLACES_SOCKETS, (new Integer(NPlaces)).toString());
      } else if (transport.equals(SOCKETS)) {
        env.put(NPLACES_SOCKETS, (new Integer(NPlaces)).toString());
        int sel = ConfUtils.hostSelectionMode(configuration);
        if (sel == HOST_FILE){
          env.put(HOSTFILE_SOCKETS, ConfUtils.getHostFile(configuration));
        } else if (sel == HOST_LIST){
          env.put(HOSTLIST_SOCKETS, ConfUtils.getHostList(configuration));
        }
      } else if (transport.equals(PAMI)) {
        env.put(NPLACES_PAMI,(new Integer(NPlaces)).toString());
        env.put(HOSTLIST_PAMI, ConfUtils.getHostFile(configuration));
      }
      
      Map<String,String> launchEnv = configuration.getAttribute(ATTR_ENV, (Map<String,String>)null);
      if (launchEnv != null){
        for(String key: launchEnv.keySet()){
          env.put(key, launchEnv.get(key));
        }
      }
      final List<String> command = new ArrayList<String>();
      
      final String cmd = this.fExecPath;
      if (this.fIsCygwin){
        command.add(((CygwinTargetOpHelper) this.fTargetOpHelper).getCygwinBash());
        String runx10 = new File(X10BundleUtils.getX10DistHostResource("bin/runx10").getFile()).getAbsolutePath();
        command.add(this.fTargetOpHelper.getTargetSystemPath(runx10));
        command.add(this.fTargetOpHelper.getTargetSystemPath(cmd));
      } else { 
        command.add(cmd);
      }
      
      
      String attrProgArgs = configuration.getAttribute(ATTR_ARGUMENTS, Constants.EMPTY_STR);
      final String progArgs = VariablesPlugin.getDefault().getStringVariableManager().performStringSubstitution(attrProgArgs);
      String[] argArr = parse(progArgs);
      for (String s: argArr){
        command.add(s);
      }
      
      this.fTargetOpHelper.run(command, this.fWorkspaceDir, env, new IProcessOuputListener() {

        public void read(final String line) {
          mcStream.println(line);
        }

        public void readError(final String line) {
          if (this.fCounter == 0) {
            mcStream.println(NLS.bind(LaunchMessages.CLCD_CmdUsedMsg, cmd));
            this.fCounter = 1;
          }
          mcStream.println(line);
        }

        // --- Fields

        int fCounter;

      }, monitor);
      mcStream.flush();
    } catch (InterruptedException e){
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, LaunchMessages.CLCD_Interrupted, e));
    } catch (IOException e){
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, LaunchMessages.CLCD_IOError, e));
    }
  }
  
  private String[] parse(String args){
    return args.split("\\s+");
  }
 
  private void launchPAMIwLL(final ILaunchConfiguration configuration, final IProject project,
      final IProgressMonitor monitor) throws CoreException{
    final SubMonitor subMonitor = SubMonitor.convert(monitor, 10);
    try {
      ILaunchConfiguration conf = ConfUtils.getConfiguration(this.fProject.getName());
      this.fTargetOpHelper = TargetOpHelperFactory.create(ConfUtils.isLocalConnection(conf), false /*not cygwin*/, ConfUtils.getConnectionName(conf));
      if (this.fTargetOpHelper == null) {
        throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, Messages.CPPB_NoPTPConnectionForName));
      }
      
      final MessageConsole messageConsole = UIUtils.findOrCreateX10Console();
      final MessageConsoleStream mcStream = messageConsole.newMessageStream();
      messageConsole.activate();
      messageConsole.clearConsole();
      
      final List<String> command = new ArrayList<String>();
      command.add("llsubmit");
      command.add(ConfUtils.getLoadLevelerScript(configuration));  
      this.fTargetOpHelper.run(command, this.fWorkspaceDir, new IProcessOuputListener() {

        public void read(final String line) {
        }

        public void readError(final String line) {
          if (this.fCounter == 0) {
            mcStream.println(NLS.bind(LaunchMessages.CLCD_CmdUsedMsg, command.toString()));
            this.fCounter = 1;
          }
          mcStream.println(line);
        }

        // --- Fields

        int fCounter;

      }, monitor);
      
    } catch (IOException except) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, LaunchMessages.CLCD_LoadLevelerIOError, except));
    } catch (InterruptedException except) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, LaunchMessages.CLCD_LoadLevelerInterrupted,
                                         except));
    } finally {
      subMonitor.done();
    }
  }
  
  protected final int createExecutable(final ILaunchConfiguration configuration, final IProject project,
                                       final IProgressMonitor monitor) throws CoreException {
    final SubMonitor subMonitor = SubMonitor.convert(monitor, 10);
    try {
      ILaunchConfiguration conf = ConfUtils.getConfiguration(this.fProject.getName());
      this.fIsCygwin = ConfUtils.getTargetOS(conf) == ETargetOS.WINDOWS;
      this.fTargetOpHelper = TargetOpHelperFactory.create(ConfUtils.isLocalConnection(conf), this.fIsCygwin, ConfUtils.getConnectionName(conf));
      if (this.fTargetOpHelper == null) {
        throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, Messages.CPPB_NoPTPConnectionForName));
      }

      this.fWorkspaceDir = PlatformConfUtils.getWorkspaceDir(project);
      final IFileStore wDirStore = this.fTargetOpHelper.getStore(this.fWorkspaceDir);
      if (!wDirStore.fetchInfo().exists()) {
        throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, LaunchMessages.CLCD_NoOutputDir));
      }
      final String x10MainType = configuration.getAttribute(ATTR_X10_MAIN_CLASS, Constants.EMPTY_STR);
      final String mainX10FilePath = createX10MainFile(this.fTargetOpHelper, x10MainType.replace(PACKAGE_SEP, NAMESPACE_SEP),
                                                       this.fWorkspaceDir, project, subMonitor.newChild(1));
      
      final IFileStore mainCppFileStore = getMainCppFileStore(x10MainType, this.fWorkspaceDir);
      if (mainCppFileStore == null) {
        throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID,
                                           NLS.bind(LaunchMessages.CLCD_NoMainCppFileFound, x10MainType)));
      }
      final String mainCppFilePath = this.fTargetOpHelper.toPath(mainCppFileStore.toURI());
      final int slashIndex = mainCppFilePath.lastIndexOf('/');
      final String mainCppFileIncludePath = (slashIndex == -1) ? mainCppFilePath : mainCppFilePath.substring(0, slashIndex);

      this.fExecPath = getExecutablePath(mainCppFilePath, this.fWorkspaceDir);
      project.setPersistentProperty(Constants.EXEC_PATH, this.fExecPath);

      final List<String> command = new ArrayList<String>();
      IDefaultCPPCommands cppCommands = DefaultCPPCommandsFactory.create(project.getName());
      final String linker = this.fTargetOpHelper.getTargetSystemPath(cppCommands.getLinker());

      command.add(linker);
      command.addAll(X10BuilderUtils.getAllTokens(cppCommands.getLinkingOptions()));
      command.add(INCLUDE_OPT + this.fTargetOpHelper.getTargetSystemPath(this.fWorkspaceDir));

      IJavaProject javaProject = JavaCore.create(fProject);
      Collection<IPath> projectDeps = AbstractX10Builder.getProjectDependencies(javaProject);
      for (final IPath projectDep: projectDeps){
        command.add(INCLUDE_OPT + this.fTargetOpHelper.getTargetSystemPath(projectDep.toOSString()));
      }
      command.add(INCLUDE_OPT + this.fTargetOpHelper.getTargetSystemPath(mainCppFileIncludePath));
      command.add(this.fTargetOpHelper.getTargetSystemPath(mainX10FilePath));
      command.add("-o"); //$NON-NLS-1$
      command.add(this.fTargetOpHelper.getTargetSystemPath(this.fExecPath));
      command.add(LIB_OPT + this.fTargetOpHelper.getTargetSystemPath(this.fWorkspaceDir));
      for (final IPath projectDep: projectDeps){
        command.add(LIB_OPT + this.fTargetOpHelper.getTargetSystemPath(projectDep.toOSString()));
      }
      command.add("-l" + project.getName()); //$NON-NLS-1$
      for (final String projectDep: AbstractX10Builder.getProjectDependenciesNames(javaProject)){
        command.add("-l" + projectDep);
      }
      final List<String> linkingLibs = X10BuilderUtils.getAllTokens(cppCommands.getLinkingLibraries());
      command.addAll(linkingLibs);

      final MessageConsole messageConsole = UIUtils.findOrCreateX10Console();
      final MessageConsoleStream mcStream = messageConsole.newMessageStream();
      messageConsole.clearConsole();
      final StringBuilder cmdBuilder = new StringBuilder();
      for (final String str : command) {
        cmdBuilder.append(str).append(' ');
      }
      final int returnCode = this.fTargetOpHelper.run(command, this.fWorkspaceDir, new IProcessOuputListener() {

        public void read(final String line) {
        }

        public void readError(final String line) {
          if (this.fCounter == 0) {
            mcStream.println(NLS.bind(LaunchMessages.CLCD_CmdUsedMsg, cmdBuilder.toString()));
            this.fCounter = 1;
          }
          mcStream.println(line);
        }

        // --- Fields

        int fCounter;

      }, monitor);

      if (returnCode != 0) {
        mcStream.println();
        UIUtils.showX10Console();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(MAIN_CLASS_KEY, x10MainType);
        
        CoreResourceUtils.addBuildMarkerTo(project, LaunchMessages.CLCD_LinkCmdError, IMarker.SEVERITY_ERROR,
                                           IMarker.PRIORITY_HIGH, null, -1, 0, 0, map);
      }

      return returnCode;
    } catch (IOException except) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, LaunchMessages.CLCD_LinkIOError, except));
    } catch (InterruptedException except) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, LaunchMessages.CLCD_LinkingInterrupted,
                                         except));
    } finally {
      subMonitor.done();
    }
  }

  


  
  protected final boolean shouldProcessToLinkStep(final IProject project) {
    int errorCount = CoreResourceUtils.getNumberOfBuildErrorMarkers(project);
    String message = (errorCount == 0) ? null : NLS.bind(LaunchMessages.CLCD_FoundErrorMarkers, errorCount, project.getName());
    if (message == null) {
      return true;
    } else {
      return LaunchUtils.queryUserToLaunchWithErrors(message);
    }
  }


  // --- Private code

  private String createX10MainFile(final ITargetOpHelper targetOpHelper, final String mainClassName,
                                   final String workspaceDir, IProject project, final IProgressMonitor monitor) throws CoreException {
    final X10CPPCompilerOptions options = (X10CPPCompilerOptions) CompilerOptionsFactory.createOptions(project);
    final StringBuilder sb = new StringBuilder();
    sb.append("#include \""); //$NON-NLS-1$
    sb.append(mainClassName.replace(NAMESPACE_SEP, "/")); //$NON-NLS-1$ 
    sb.append(".h\"\n"); //$NON-NLS-1$
    sb.append(MessagePassingCodeGenerator.createMainStub(mainClassName, options));
    final InputStream is = new ByteArrayInputStream(sb.toString().getBytes());

    final String mainFileName = workspaceDir + MAIN_FILE_NAME;
    final IFileStore fileStore = targetOpHelper.getStore(mainFileName);
    try {
      final OutputStream os = fileStore.openOutputStream(EFS.NONE, monitor);
      try {
        final byte[] b = new byte[4 * 1024];
        int read;
        while ((read = is.read(b)) != -1) {
          os.write(b, 0, read);
        }
        return mainFileName;
      } finally {
        is.close();
        os.close();
        monitor.done();
      }
    } catch (IOException except) {
      throw new CoreException(new Status(IStatus.ERROR, LaunchCore.PLUGIN_ID, LaunchMessages.CLCD_NoMainFileAccessIOError,
                                         except));
    }
  }

  private String getExecutablePath(final String mainCppFilePath, final String workspaceDir) {
    final StringBuilder execPathBuilder = new StringBuilder();
    execPathBuilder.append(workspaceDir).append('/');

    final String fileName = mainCppFilePath.substring(mainCppFilePath.lastIndexOf('/') + 1);

    final int dotIndex = fileName.lastIndexOf('.');
    final int lastIndex = (dotIndex == -1) ? fileName.length() : dotIndex;
    final String className = fileName.substring(0, lastIndex);

    execPathBuilder.append(className);

    try {
    if (ConfUtils.getTargetOS(ConfUtils.getConfiguration(this.fProject.getName())) == ETargetOS.WINDOWS) {
      execPathBuilder.append(EXE_EXT);
    }
    } catch (CoreException e){
      CppLaunchCore.getInstance().getLog().log(e.getStatus());
    }
    return execPathBuilder.toString();
  }

  private IFileStore getMainCppFileStore(final String x10MainType, final String workspaceDir) throws CoreException {
    final Collection<IFileStore> matches = new ArrayList<IFileStore>();
    final IFileStore dirStore = this.fTargetOpHelper.getStore(workspaceDir);
    final int dotIndex = x10MainType.lastIndexOf('.');
    final String pkgName = (dotIndex == -1) ? Constants.EMPTY_STR : x10MainType.substring(0, dotIndex);
    searchForMatchingGeneratedFile(matches, dirStore, Constants.EMPTY_STR, pkgName, x10MainType, new NullProgressMonitor());
    return (matches.isEmpty()) ? null : matches.iterator().next();
  }
  
  private void searchForMatchingGeneratedFile(final Collection<IFileStore> matches, final IFileStore dirStore,
                                              final String curDir, final String pkgName, final String typeName,
                                              final IProgressMonitor monitor) throws CoreException {
    for (final IFileStore fileStore : dirStore.childStores(EFS.NONE, monitor)) {
      final IFileInfo fileInfo = fileStore.fetchInfo();
      if (fileInfo.isDirectory()) {
        final StringBuilder newDir = new StringBuilder();
        if (!curDir.equals(Constants.EMPTY_STR)) {
          newDir.append(curDir).append('.');
        }
        newDir.append(fileInfo.getName());
        searchForMatchingGeneratedFile(matches, fileStore, newDir.toString(), pkgName, typeName, monitor);
      } else {
        final String name = fileInfo.getName();
        final int dotIndex = name.lastIndexOf('.');
        final String ext = name.substring(dotIndex + 1);
        if (CC_EXT.equals(ext)) {
          final String nameWithoutExt = name.substring(0, (dotIndex == -1) ? name.length() : dotIndex);
          final StringBuilder typeNameBuilder = new StringBuilder();
          if (!curDir.equals(Constants.EMPTY_STR)) {
            typeNameBuilder.append(curDir).append('.');
          }
          typeNameBuilder.append(nameWithoutExt);
          if (typeName.equals(typeNameBuilder.toString())) {
            matches.add(fileStore);
          }
        }
      }
    }
  }

  // --- Fields

  private boolean fIsCygwin;

  private ITargetOpHelper fTargetOpHelper;

  private String fExecPath;

  private String fWorkspaceDir;
  
  private IProject fProject;
  
  private ILaunchConfiguration fCompilationConfiguration;

  private static final String MAIN_FILE_NAME = "/xxx_main_xxx.cc"; //$NON-NLS-1$

  private static final String INCLUDE_OPT = "-I"; //$NON-NLS-1$

  private static final String LIB_OPT = "-L"; //$NON-NLS-1$

  private static final String PACKAGE_SEP = "."; //$NON-NLS-1$

  private static final String NAMESPACE_SEP = "::"; //$NON-NLS-1$

  private static final String EXE_EXT = ".exe"; //$NON-NLS-1$

  private static final String CC_EXT = "cc"; //$NON-NLS-1$
  
  private static final String MAIN_CLASS_KEY = "class"; //$NON-NLS-1$

}
