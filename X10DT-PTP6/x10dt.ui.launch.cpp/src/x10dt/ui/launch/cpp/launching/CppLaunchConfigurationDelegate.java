/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching;

import static x10dt.ui.launch.cpp.launching.CppBackEndLaunchConfAttrs.ATTR_X10_MAIN_CLASS;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_HOSTFILE;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_HOSTLIST;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_NUM_PLACES;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_USE_HOSTFILE;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.DEFAULT_NUM_PLACES;


import java.io.ByteArrayInputStream;
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
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.imp.preferences.PreferencesService;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ptp.core.IPTPLaunchConfigurationConstants;
import org.eclipse.ptp.core.ModelManager;
import org.eclipse.ptp.core.PTPCorePlugin;
import org.eclipse.ptp.core.attributes.ArrayAttribute;
import org.eclipse.ptp.core.attributes.AttributeManager;
import org.eclipse.ptp.core.attributes.IllegalValueException;
import org.eclipse.ptp.rmsystem.IResourceManagerControl;
import org.eclipse.ptp.core.elements.IPJob;
import org.eclipse.ptp.core.elements.IPMachine;
import org.eclipse.ptp.core.elements.IPNode;
import org.eclipse.ptp.core.elements.IPQueue;
import org.eclipse.ptp.core.elements.IPResourceManager;
import org.eclipse.ptp.core.elements.IPUniverse;
import org.eclipse.ptp.rmsystem.IResourceManager;
import org.eclipse.ptp.core.elements.attributes.JobAttributes;
import org.eclipse.ptp.core.elements.attributes.ResourceManagerAttributes.State;
import org.eclipse.ptp.debug.core.IPDebugger;
import org.eclipse.ptp.debug.core.launch.IPLaunch;
import org.eclipse.ptp.launch.ParallelLaunchConfigurationDelegate;
import org.eclipse.ptp.remote.core.IRemoteConnection;
import org.eclipse.ptp.remote.core.IRemoteConnectionManager;
import org.eclipse.ptp.remote.core.IRemoteFileManager;
import org.eclipse.ptp.remote.core.IRemoteServices;
import org.eclipse.ptp.remote.core.PTPRemoteCorePlugin;
import org.eclipse.ptp.rmsystem.IResourceManagerConfiguration;
import org.eclipse.ptp.rtsystem.AbstractRuntimeResourceManager;
import org.eclipse.ptp.rtsystem.IRuntimeSystem;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.ptp.core.elements.IPResourceManager;

import x10cpp.X10CPPCompilerOptions;
import x10cpp.visit.MessagePassingCodeGenerator;
import x10dt.core.preferences.generated.X10Constants;
import x10dt.core.utils.CompilerOptionsFactory;
import x10dt.core.utils.X10DTCoreConstants;
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
import x10dt.ui.launch.cpp.platform_conf.IConnectionConf;
import x10dt.ui.launch.cpp.platform_conf.ICppCompilationConf;
import x10dt.ui.launch.cpp.platform_conf.IDebuggingInfoConf;
import x10dt.ui.launch.cpp.platform_conf.IHostsBasedConf;
import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConf;
import x10dt.ui.launch.cpp.platform_conf.X10PlatformConfFactory;
import x10dt.ui.launch.cpp.utils.PlatformConfUtils;
import x10dt.ui.launch.rms.core.RMSCoreActivator;
import x10dt.ui.launch.rms.core.launch_configuration.LaunchAttributes;
import x10dt.ui.launch.rms.core.provider.AbstractX10RuntimeSystem;

/**
 * Performs linking and launching of C++ application generated by X10 back-end.
 * 
 * @author egeay
 */
public class CppLaunchConfigurationDelegate extends ParallelLaunchConfigurationDelegate {

  // --- Overridden methods
  
  public void launch(final ILaunchConfiguration configuration, final String mode, final ILaunch launch,
                     final IProgressMonitor monitor) throws CoreException {
    try {
      // Performs linking first.
      monitor.beginTask(null, 10);
      monitor.subTask(LaunchMessages.CLCD_ExecCreationTaskName);

      
      this.fProject = verifyProject(configuration);

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
        
        ILaunchConfigurationWorkingCopy workingCopy = configuration.getWorkingCopy();
        workingCopy.setAttribute(IPTPLaunchConfigurationConstants.ATTR_RESOURCE_MANAGER_UNIQUENAME, fResourceManager.getUniqueName());
        workingCopy.doSave();
        
        // Then, performs the launch.
        if (!monitor.isCanceled()) {
          // Hijack launch if it's PAMI with LoadLeveler
          if (PTPConstants.PAMI_SERVICE_PROVIDER_ID.equals(this.fX10PlatformConf.getCommunicationInterfaceConf().getServiceTypeId()) &&
              ((IHostsBasedConf)this.fX10PlatformConf.getCommunicationInterfaceConf()).loadLevelerSelected()){
            launchPAMIwLL(configuration, fProject, new SubProgressMonitor(monitor, 5));
            
          } else {
            monitor.subTask(LaunchMessages.CLCD_LaunchCreationTaskName);
            updateAttributes(configuration, mode, monitor);
            //super.launch(configuration, mode, launch, new SubProgressMonitor(monitor, 5));
            runMyCommand(monitor);
          }
        }
      }
    } finally {
      monitor.done();
    }
  }
  
  private void runMyCommand(final IProgressMonitor monitor)  {
    try {
      final MessageConsole messageConsole = UIUtils.findOrCreateX10Console();
      final MessageConsoleStream mcStream = messageConsole.newMessageStream();
      messageConsole.clearConsole();
      final String cmd = this.fExecPath;
      final List<String> command = new ArrayList<String>();
      command.add(cmd);
      final int returnCode = this.fTargetOpHelper.run(command, this.fWorkspaceDir, new IProcessOuputListener() {

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
      //TODO
    } catch (IOException e){
      //TODO
    }
  }
 
  private void launchPAMIwLL(final ILaunchConfiguration configuration, final IProject project,
      final IProgressMonitor monitor) throws CoreException{
    final SubMonitor subMonitor = SubMonitor.convert(monitor, 10);
    try {
      this.fX10PlatformConf = CppLaunchCore.getInstance().getPlatformConfiguration(project);
      
      final ICppCompilationConf cppCompConf = this.fX10PlatformConf.getCppCompilationConf();
      final IConnectionConf connConf = this.fX10PlatformConf.getConnectionConf();
      this.fIsCygwin = cppCompConf.getTargetOS() == ETargetOS.WINDOWS;
      this.fTargetOpHelper = TargetOpHelperFactory.create(connConf.isLocal(), this.fIsCygwin, connConf.getConnectionName());
      if (this.fTargetOpHelper == null) {
        throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, Messages.CPPB_NoPTPConnectionForName));
      }
      
      final MessageConsole messageConsole = UIUtils.findOrCreateX10Console();
      final MessageConsoleStream mcStream = messageConsole.newMessageStream();
      
      final List<String> command = new ArrayList<String>();
      command.add("llsubmit");
      command.add(((IHostsBasedConf)this.fX10PlatformConf.getCommunicationInterfaceConf()).getLoadLevelerScript());
      
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
      this.fX10PlatformConf = CppLaunchCore.getInstance().getPlatformConfiguration(project);
      
      final ICppCompilationConf cppCompConf = this.fX10PlatformConf.getCppCompilationConf();
      final IConnectionConf connConf = this.fX10PlatformConf.getConnectionConf();
      this.fIsCygwin = cppCompConf.getTargetOS() == ETargetOS.WINDOWS;
      this.fTargetOpHelper = TargetOpHelperFactory.create(connConf.isLocal(), this.fIsCygwin, connConf.getConnectionName());
      if (this.fTargetOpHelper == null) {
        throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, Messages.CPPB_NoPTPConnectionForName));
      }

      this.fResourceManager = getResourceManagerControl(configuration);
      if (this.fResourceManager == null) {
        throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, LaunchMessages.CLCD_NoResManagerError));
      }
      if (this.fResourceManager.getState() == IResourceManager.ERROR_STATE) {
        this.fResourceManager.stop();
      }
      if (this.fResourceManager.getState() != IResourceManager.STARTED_STATE) {
        this.fResourceManager.start(new NullProgressMonitor());
      }

      this.fWorkspaceDir = PlatformConfUtils.getWorkspaceDir(this.fX10PlatformConf, project);
      final IFileStore wDirStore = this.fTargetOpHelper.getStore(this.fWorkspaceDir);
      if (!wDirStore.fetchInfo().exists()) {
        throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, LaunchMessages.CLCD_NoOutputDir));
      }
      final String x10MainType = configuration.getAttribute(ATTR_X10_MAIN_CLASS, Constants.EMPTY_STR);
      final String mainX10FilePath = createX10MainFile(this.fTargetOpHelper, x10MainType.replace(PACKAGE_SEP, NAMESPACE_SEP),
                                                       this.fWorkspaceDir, project, subMonitor.newChild(1));

      for (final IMarker marker : project.findMarkers(X10DTCoreConstants.PROBLEMMARKER_ID, false, IResource.DEPTH_ZERO)) {
        final String confFilePath = this.fX10PlatformConf.getConfFile().getProjectRelativePath().toPortableString();
        if (marker.getAttribute(MAIN_FILE_KEY, Constants.EMPTY_STR).equals(confFilePath) &&
            marker.getAttribute(MAIN_CLASS_KEY, Constants.EMPTY_STR).equals(x10MainType)) {
          marker.delete();
          break;
        }
      }
      
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
      final String linker = this.fTargetOpHelper.getTargetSystemPath(cppCompConf.getLinker());

      command.add(linker);
      command.addAll(X10BuilderUtils.getAllTokens(cppCompConf.getLinkingOpts()));
      command.add(INCLUDE_OPT + this.fTargetOpHelper.getTargetSystemPath(this.fWorkspaceDir));
      if (fProject == null){
        this.fProject = verifyProject(configuration);
      }
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
      final List<String> linkingLibs = X10BuilderUtils.getAllTokens(cppCompConf.getLinkingLibs());
      command.addAll(linkingLibs);

      final MessageConsole messageConsole = UIUtils.findOrCreateX10Console();
      final MessageConsoleStream mcStream = messageConsole.newMessageStream();
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
        map.put(MAIN_FILE_KEY, this.fX10PlatformConf.getConfFile().getProjectRelativePath().toPortableString());
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

  
  protected void updateAttributes(final ILaunchConfiguration configuration, final String mode,
                                                 final IProgressMonitor monitor) throws CoreException {
    try {
      final AttributeManager attrMgr = new AttributeManager();
      final int numPlaces = this.fX10PlatformConf.getCommunicationInterfaceConf().getNumberOfPlaces();
      attrMgr.addAttribute(JobAttributes.getNumberOfProcessesAttributeDefinition().create(numPlaces));
      
      if (this.fX10PlatformConf.getCommunicationInterfaceConf() instanceof IHostsBasedConf){
    	  IHostsBasedConf conf = (IHostsBasedConf) this.fX10PlatformConf.getCommunicationInterfaceConf();
    	  attrMgr.addAttribute(LaunchAttributes.getHostFileAttr().create(conf.getHostFile()));
          attrMgr.addAttribute(LaunchAttributes.getUseHostFileAttr().create(conf.shouldUseHostFile()));
      } else {
          attrMgr.addAttribute(LaunchAttributes.getHostFileAttr().create(configuration.getAttribute(ATTR_HOSTFILE, Constants.EMPTY_STR)));
          attrMgr.addAttribute(LaunchAttributes.getUseHostFileAttr().create(configuration.getAttribute(ATTR_USE_HOSTFILE, false)));
      }
      final ArrayAttribute<String> attribute = LaunchAttributes.getHostListAttr().create();
      final List<String> defaultHostList = new ArrayList<String>();
      if (defaultHostList.isEmpty()) {
        // In case of launching via shortcut.
        IPResourceManager IPrm = (IPResourceManager) fResourceManager.getAdapter(IPResourceManager.class);
        for (final IPMachine machine : IPrm.getMachines()) {
          for (final IPNode node : machine.getNodes()) {
            defaultHostList.add(node.getName());
          }
        }
      }
      attribute.setValue(configuration.getAttribute(ATTR_HOSTLIST, defaultHostList));
      attrMgr.addAttribute(attribute);

      
      // Collects attributes from Resource tab
      //attrMgr.addAttributes(getResourceAttributes(configuration, mode));

      // Collects attributes from Environment tab
      String[] envArr = null; //org.eclipse.ptp.core.util.LaunchUtils.getEnvironmentToAppend(configuration);
      
      if (this.fIsCygwin) {
        final StringBuilder sb = new StringBuilder();
        sb.append(PATH_ENV).append('=');
        final String ldLibPathValue = this.fTargetOpHelper.getEnvVarValue(PATH_ENV);
        int k = 0;
        final boolean isLocal = this.fX10PlatformConf.getConnectionConf().isLocal();
        for (final String x10Lib : this.fX10PlatformConf.getCppCompilationConf().getX10LibsLocations(isLocal)) {
          if (k > 0) {
            sb.append(';');
          } else {
            k = 1;
          }
          sb.append(x10Lib.replace('/', '\\'));
        }
        if (ldLibPathValue != null) {
          sb.append(';').append(ldLibPathValue);
        }
        
        int pathIndex = -1;
        if (envArr != null) {
          final String pathEnvStart = PATH_ENV + '=';
          for (int i = 0; i < envArr.length; ++i) {
            if ((envArr[i] != null) && envArr[i].startsWith(pathEnvStart)) {
              pathIndex = i;
              break;
            }
          }
        }
        
        if (pathIndex == -1) {
          final String[] newArray = new String[(envArr == null) ? 1 : envArr.length + 1];
          newArray[0] = sb.toString();
          if (envArr != null) {
            System.arraycopy(envArr, 0, newArray, 1, envArr.length);
          }
          envArr = newArray;
        } else if (envArr != null) {
          sb.append(';').append(envArr[pathIndex]);
          envArr[pathIndex] = sb.toString();
        }
      }
      if (envArr != null) {
        attrMgr.addAttribute(JobAttributes.getEnvironmentAttributeDefinition().create(envArr));
      }

      // Makes sure there is a queue, even if the resources tab doesn't require one to be specified.
      if (attrMgr.getAttribute(JobAttributes.getQueueIdAttributeDefinition()) == null) {
        final IPQueue queue = org.eclipse.ptp.core.util.LaunchUtils.getQueueDefault((IPResourceManager) this.fResourceManager.getAdapter(IPResourceManager.class));
        if (queue == null) {
          throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, LaunchMessages.CLCD_NoRMQueueError));
        }
        attrMgr.addAttribute(JobAttributes.getQueueIdAttributeDefinition().create(queue.getID()));
      }

      // Collects attributes from Application tab
      final IPath programPath = verifyExecutablePath(configuration, monitor);
      attrMgr.addAttribute(JobAttributes.getExecutableNameAttributeDefinition().create(programPath.lastSegment()));

      final String path = programPath.removeLastSegments(1).toString();
      if (path != null) {
        attrMgr.addAttribute(JobAttributes.getExecutablePathAttributeDefinition().create(path));
      }

      // Collects attributes from Arguments tab
      attrMgr.addAttribute(JobAttributes.getWorkingDirectoryAttributeDefinition().create(this.fWorkspaceDir));

      final String[] argArr = org.eclipse.ptp.core.util.LaunchUtils.getProgramArguments(configuration);
      if (argArr != null) {
        attrMgr.addAttribute(JobAttributes.getProgramArgumentsAttributeDefinition().create(argArr));
      }

      // PTP launched this job
      attrMgr.addAttribute(JobAttributes.getLaunchedByPTPFlagAttributeDefinition().create(true));

      AbstractX10RuntimeSystem runtimeSystem = ((AbstractX10ResourceManager) fResourceManager).getRuntimeSystem();
      
      runtimeSystem.setAttributes(attrMgr.getAttributes());
      
  } catch (IllegalValueException except) {
    throw new CoreException(new Status(IStatus.ERROR, RMSCoreActivator.PLUGIN_ID, "Invalid Places Number", except)); //TODO
  

    } finally {
      monitor.done();
    }
  }
  
  
  
  protected final IDebuggingInfoConf getDebuggingInfoConf() {
    return this.fX10PlatformConf.getDebuggingInfoConf();
  }
  
  protected final String getExecutablePath() {
    return this.fExecPath;
  }

  protected void doCompleteJobLaunch(final ILaunchConfiguration configuration, final String mode, final IPLaunch launch,
                                     final AttributeManager mgr, final IPDebugger debugger, final IPJob job) {
    if (mode.equals(ILaunchManager.DEBUG_MODE)) {
      job.setDebug();
    }
    //super.doCompleteJobLaunch(configuration, mode, launch, mgr, debugger, job);
    super.doCompleteJobLaunch(launch,debugger);
  }

  protected IResourceManager getResourceManager(final ILaunchConfiguration configuration) {
    return this.fResourceManager;
  }
  
  protected final boolean shouldProcessToLinkStep(final IProject project) {
    int errorCount = CoreResourceUtils.getNumberOfBuildErrorMarkers(project);
    String message = (errorCount == 0) ? null : NLS.bind(LaunchMessages.CLCD_FoundErrorMarkers, errorCount, project.getName());
    if (message == null) {
      errorCount = CoreResourceUtils.getNumberOfPlatformConfErrorMarkers(X10PlatformConfFactory.getFile(project));
      message = (errorCount == 0) ? null
                                 : NLS.bind(LaunchMessages.CLCD_FoundPlatformConfErrors, errorCount, project.getName());
    }
    if (message == null) {
      return true;
    } else {
      return LaunchUtils.queryUserToLaunchWithErrors(message);
    }
  }

  protected IPath verifyExecutablePath(final ILaunchConfiguration configuration, 
                                       final IProgressMonitor monitor) throws CoreException {
    try {
      return verifyResource(this.fExecPath, configuration, monitor);
    } catch (CoreException except) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, NLS.bind(LaunchMessages.CLCD_NoCppExecutable,
                                                                                          this.fExecPath), except));
    }
  }

  protected IPath verifyResource(final String path, final ILaunchConfiguration configuration, 
                                 final IProgressMonitor monitor) throws CoreException {
    final IResourceManagerConfiguration conf = this.fResourceManager.getConfiguration();
    final IRemoteServices remoteServices = PTPRemoteCorePlugin.getDefault().getRemoteServices(this.fResourceManager.getControlConfiguration().getRemoteServicesId()); 
    if (remoteServices == null) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, LaunchMessages.CLCD_NoRemoteServices));
    }
    final IRemoteConnectionManager connMgr = remoteServices.getConnectionManager();
    if (connMgr == null) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, LaunchMessages.CLCD_NoConnectionMgr));
    }
    final IRemoteConnection conn = connMgr.getConnection(this.fResourceManager.getControlConfiguration().getConnectionName()); 
    if (conn == null) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, LaunchMessages.CLCD_NoConnection));
    }
    final IRemoteFileManager fileManager = remoteServices.getFileManager(conn);
    if (fileManager == null) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, LaunchMessages.CLCD_NoFileManager));
    }
    if (!fileManager.getResource(path).fetchInfo().exists()) {
      throw new CoreException(new Status(IStatus.INFO, CppLaunchCore.PLUGIN_ID, NLS.bind(LaunchMessages.CLCD_PathNotFound,
                                                                                         path)));
    }
    return new Path(path);
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

    if (this.fX10PlatformConf.getCppCompilationConf().getTargetOS() == ETargetOS.WINDOWS) {
      execPathBuilder.append(EXE_EXT);
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

  private IResourceManager getResourceManagerControl(final ILaunchConfiguration configuration) throws CoreException {
   final IPUniverse universe = ModelManager.getInstance().getUniverse();
    final String rmUniqueName = org.eclipse.ptp.core.util.LaunchUtils.getResourceManagerUniqueName(configuration);
    for (final IPResourceManager pResourceManager : universe.getResourceManagers()) {
      IResourceManager resourceManager = (IResourceManager) pResourceManager.getAdapter(IResourceManager.class);
      if (resourceManager.getUniqueName().equals(rmUniqueName)) {
        return resourceManager;
      }
    }
    // In case of switching between local and remote for instance, a new resource manager will be created in the background
    // with a different unique id. And so the previous code will fail. We are forced then to attempt finding one by matching
    // the name.
    final Collection<IResourceManager> resourceManagers = new ArrayList<IResourceManager>();
    for (final IPResourceManager pResourceManager : universe.getResourceManagers()) {
      IResourceManager resourceManager = (IResourceManager) pResourceManager.getAdapter(IResourceManager.class);
      if (resourceManager.getName().equals(this.fX10PlatformConf.getName())) {
        resourceManagers.add(resourceManager);
      }
    }
    if (resourceManagers.isEmpty()) {
      return null;
    } else if (resourceManagers.size() == 1) {
      return resourceManagers.iterator().next();
    } else {
      // With multiple matches the best solution would be a dialog box that would show all the characteristics of each
      // resource manager so that the end-user could select it.
      // For now, we give up by asking the end-user to settle the name conflict situation.
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID,
                                         NLS.bind(LaunchMessages.CLCD_MultipleRMWithSameName, resourceManagers.size(),
                                                  this.fX10PlatformConf.getName())));
    }
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

  private IX10PlatformConf fX10PlatformConf;

  private String fExecPath;

  private IResourceManager fResourceManager;

  private String fWorkspaceDir;
  
  private IProject fProject;

  private static final String MAIN_FILE_NAME = "/xxx_main_xxx.cc"; //$NON-NLS-1$

  private static final String INCLUDE_OPT = "-I"; //$NON-NLS-1$

  private static final String LIB_OPT = "-L"; //$NON-NLS-1$

  private static final String PATH_ENV = "Path"; //$NON-NLS-1$

  private static final String PACKAGE_SEP = "."; //$NON-NLS-1$

  private static final String NAMESPACE_SEP = "::"; //$NON-NLS-1$

  private static final String EXE_EXT = ".exe"; //$NON-NLS-1$

  private static final String CC_EXT = "cc"; //$NON-NLS-1$
  

  private static final String MAIN_FILE_KEY = "file"; //$NON-NLS-1$

  private static final String MAIN_CLASS_KEY = "class"; //$NON-NLS-1$

}
