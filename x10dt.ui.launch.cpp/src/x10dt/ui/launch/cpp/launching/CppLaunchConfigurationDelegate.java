/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
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
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ptp.core.PTPCorePlugin;
import org.eclipse.ptp.core.attributes.AttributeManager;
import org.eclipse.ptp.core.attributes.IAttribute;
import org.eclipse.ptp.core.elementcontrols.IResourceManagerControl;
import org.eclipse.ptp.core.elements.IPJob;
import org.eclipse.ptp.core.elements.IPQueue;
import org.eclipse.ptp.core.elements.IPUniverse;
import org.eclipse.ptp.core.elements.IResourceManager;
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
import org.eclipse.ptp.rm.mpi.mpich2.core.MPICH2LaunchAttributes;
import org.eclipse.ptp.rmsystem.IResourceManagerConfiguration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import x10cpp.visit.MessagePassingCodeGenerator;
import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.LaunchCore;
import x10dt.ui.launch.core.Messages;
import x10dt.ui.launch.core.platform_conf.ETargetOS;
import x10dt.ui.launch.core.utils.CoreResourceUtils;
import x10dt.ui.launch.core.utils.IProcessOuputListener;
import x10dt.ui.launch.core.utils.UIUtils;
import x10dt.ui.launch.core.utils.X10BuilderUtils;
import x10dt.ui.launch.cpp.CppLaunchCore;
import x10dt.ui.launch.cpp.LaunchMessages;
import x10dt.ui.launch.cpp.builder.target_op.ITargetOpHelper;
import x10dt.ui.launch.cpp.builder.target_op.TargetOpHelperFactory;
import x10dt.ui.launch.cpp.platform_conf.IConnectionConf;
import x10dt.ui.launch.cpp.platform_conf.ICppCompilationConf;
import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConf;
import x10dt.ui.launch.cpp.platform_conf.X10PlatformConfFactory;
import x10dt.ui.launch.cpp.utils.PlatformConfUtils;

/**
 * Performs linking and launching of C++ application generated by X10 back-end.
 * 
 * @author egeay
 */
public final class CppLaunchConfigurationDelegate extends ParallelLaunchConfigurationDelegate {

  // --- Overridden methods
  
  protected AttributeManager getAttributeManager(final ILaunchConfiguration configuration, 
                                                 final String mode, final IProgressMonitor monitor) throws CoreException {
    try {
      final AttributeManager attrMgr = new AttributeManager();

      // Collects attributes from Resource tab
      final IAttribute<?, ?, ?>[] resourceAttributes = getResourceAttributes(configuration, mode);
      if (this.fIsCygwin) {
        final StringBuilder pathBuilder = new StringBuilder();
        final String ldLibPathValue = this.fTargetOpHelper.getEnvVarValue(PATH_ENV);
        if (ldLibPathValue != null) {
          pathBuilder.append(ldLibPathValue);
        }
        for (final String x10Lib : this.fX10PlatformConf.getCppCompilationConf().getX10LibsLocations()) {
          if (pathBuilder.length() > 0) {
            pathBuilder.append(File.pathSeparatorChar);
          }
          pathBuilder.append(x10Lib);
        }
        
        // In the case of MPICH-2 we need to add it via 'gpath' option.
        for (int i = 0; i < resourceAttributes.length; ++i) {
          if (MPICH2LaunchAttributes.getLaunchArgumentsAttributeDefinition().equals(resourceAttributes[i].getDefinition())) {
            final String curValue = resourceAttributes[i].getValueAsString();
            final StringBuilder newArgs = new StringBuilder();
            newArgs.append("-gpath '").append(pathBuilder.toString()).append("' ").append(curValue); //$NON-NLS-1$//$NON-NLS-2$
            resourceAttributes[i] = MPICH2LaunchAttributes.getLaunchArgumentsAttributeDefinition().create(newArgs.toString());
          }
        }
      }
      attrMgr.addAttributes(resourceAttributes);

      // Collects attributes from Environment tab
      final String[] envArr = getEnvironmentToAppend(configuration);
      if (envArr != null) {
        attrMgr.addAttribute(JobAttributes.getEnvironmentAttributeDefinition().create(envArr));
      }
      
      // Makes sure there is a queue, even if the resources tab doesn't require one to be specified.
      if (attrMgr.getAttribute(JobAttributes.getQueueIdAttributeDefinition()) == null) {
        final IPQueue queue = getQueueDefault(this.fResourceManager);
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
        attrMgr.addAttribute(JobAttributes.getExecutablePathAttributeDefinition().create(protectPath(path)));
      }

      // Collects attributes from Arguments tab
      attrMgr.addAttribute(JobAttributes.getWorkingDirectoryAttributeDefinition().create(this.fWorkspaceDir));

      final String[] argArr = getProgramArguments(configuration);
      if (argArr != null) {
        attrMgr.addAttribute(JobAttributes.getProgramArgumentsAttributeDefinition().create(argArr));
      }

      // PTP launched this job
      attrMgr.addAttribute(JobAttributes.getLaunchedByPTPFlagAttributeDefinition().create(true));

      return attrMgr;
    } finally {
      monitor.done();
    }
  }
  
  protected void doCompleteJobLaunch(final ILaunchConfiguration configuration, final String mode, final IPLaunch launch, 
                                     final AttributeManager mgr, final IPDebugger debugger, final IPJob job) {
    if (mode.equals(ILaunchManager.DEBUG_MODE)) {
      job.setDebug();
    }
    super.doCompleteJobLaunch(configuration, mode, launch, mgr, debugger, job);
  }
  
  protected IResourceManager getResourceManager(final ILaunchConfiguration configuration) {
    return this.fResourceManager;
  }
  
  public void launch(final ILaunchConfiguration configuration, final String mode, final ILaunch launch, 
                     final IProgressMonitor monitor) throws CoreException {
    try {
      // Performs linking first.
      monitor.beginTask(null, 10);
      monitor.subTask(LaunchMessages.CLCD_ExecCreationTaskName);
            
      final IProject project = verifyProject(configuration);
      if (! monitor.isCanceled() && shouldProcessToLinkStep(project) && 
      		createExecutable(configuration, project, mode, new SubProgressMonitor(monitor, 5)) == 0) {
        // Then, performs the launch.
      	if (! monitor.isCanceled()) {
      		monitor.subTask(LaunchMessages.CLCD_LaunchCreationTaskName);
      		super.launch(configuration, mode, launch, new SubProgressMonitor(monitor, 5));
      	}
      }
    } finally {
      monitor.done();
    }
  }
  
  protected IPath verifyExecutablePath(final ILaunchConfiguration configuration, 
                                       final IProgressMonitor monitor)  throws CoreException {
    try {
      return verifyResource(this.fExecPath, configuration, monitor);
    } catch (CoreException except) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID,
                                         NLS.bind(LaunchMessages.CLCD_NoCppExecutable, this.fExecPath),
                                         except));
    }
  }
  
  protected IPath verifyResource(final String path, final ILaunchConfiguration configuration,
                                 final IProgressMonitor monitor) throws CoreException {
    final IResourceManagerConfiguration conf = this.fResourceManager.getConfiguration();
    final IRemoteServices remoteServices = PTPRemoteCorePlugin.getDefault().getRemoteServices(conf.getRemoteServicesId());
    if (remoteServices == null) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, LaunchMessages.CLCD_NoRemoteServices));
    }
    final IRemoteConnectionManager connMgr = remoteServices.getConnectionManager();
    if (connMgr == null) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, LaunchMessages.CLCD_NoConnectionMgr));
    }
    final IRemoteConnection conn = connMgr.getConnection(conf.getConnectionName());
    if (conn == null) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, LaunchMessages.CLCD_NoConnection));
    }
    final IRemoteFileManager fileManager = remoteServices.getFileManager(conn);
    if (fileManager == null) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, LaunchMessages.CLCD_NoFileManager));
    }
    if (! fileManager.getResource(path).fetchInfo().exists()) {
      throw new CoreException(new Status(IStatus.INFO, CppLaunchCore.PLUGIN_ID,
                                         NLS.bind(LaunchMessages.CLCD_PathNotFound, path)));
    }
    return new Path(path);
  }

  // --- Private code
  
  private int createExecutable(final ILaunchConfiguration configuration, final IProject project, final String mode,
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
      if (this.fResourceManager.getState() == State.ERROR) {
        this.fResourceManager.shutdown();
      }
      if (this.fResourceManager.getState() != State.STARTED) {
        this.fResourceManager.startUp(new NullProgressMonitor());
      }
      
      this.fWorkspaceDir = PlatformConfUtils.getWorkspaceDir(this.fX10PlatformConf, project);
      final IFileStore wDirStore = this.fTargetOpHelper.getStore(this.fWorkspaceDir);
      if (! wDirStore.fetchInfo().exists()) {
        throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, LaunchMessages.CLCD_NoOutputDir));
      }
      final String x10MainType = configuration.getAttribute(Constants.ATTR_X10_MAIN_CLASS, Constants.EMPTY_STR);
      final String mainX10FilePath = createX10MainFile(this.fTargetOpHelper, x10MainType.replace(PACKAGE_SEP, NAMESPACE_SEP), 
                                                       this.fWorkspaceDir, subMonitor.newChild(1));

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
      if ("debug".equals(mode)) {
    	  // !! Hack to switch into using the MPI library for the debugger rather than PGAS sockets.
    	  command.add(linker.replace("g++", "mpicxx")); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
    	  command.add(linker);
      }
      command.addAll(X10BuilderUtils.getAllTokens(cppCompConf.getLinkingOpts(true)));
      command.add(INCLUDE_OPT + this.fTargetOpHelper.getTargetSystemPath(this.fWorkspaceDir));
      command.add(INCLUDE_OPT + this.fTargetOpHelper.getTargetSystemPath(mainCppFileIncludePath));
      for (final String headerLoc : cppCompConf.getX10HeadersLocations()) {
        command.add(INCLUDE_OPT + this.fTargetOpHelper.getTargetSystemPath(headerLoc));
      }
      command.add(this.fTargetOpHelper.getTargetSystemPath(mainX10FilePath));
      command.add("-o"); //$NON-NLS-1$
      command.add(this.fTargetOpHelper.getTargetSystemPath(this.fExecPath));
      command.add(LIB_OPT + this.fTargetOpHelper.getTargetSystemPath(this.fWorkspaceDir));
      for (final String libLoc : cppCompConf.getX10LibsLocations()) {
        command.add(LIB_OPT + this.fTargetOpHelper.getTargetSystemPath(libLoc));
      }
      command.add("-l" + project.getName()); //$NON-NLS-1$
      final List<String> linkingLibs = X10BuilderUtils.getAllTokens(cppCompConf.getLinkingLibs(true));
      if ("debug".equals(mode)) {
    	  // !! Hack to switch into using the MPI library for the debugger rather than PGAS sockets.
    	  for (final String linkingLib : linkingLibs) {
    		  command.add(linkingLib.replace("-lx10rt_pgas_sockets", "-lx10rt_mpi")); //$NON-NLS-1$ //$NON-NLS-2$
    	  }
      } else {
    	  command.addAll(linkingLibs);
      }
      
      final MessageConsole messageConsole = UIUtils.findOrCreateX10Console();
      final MessageConsoleStream mcStream = messageConsole.newMessageStream();
      final StringBuilder cmdBuilder = new StringBuilder();
      for (final String str : command) {
        cmdBuilder.append(str).append(' ');
      }
      final int returnCode = this.fTargetOpHelper.run(command, new IProcessOuputListener() {
        
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
        
      });
      
      if (returnCode != 0) {
        mcStream.println();
        UIUtils.showX10Console();
        CoreResourceUtils.addBuildMarkerTo(project, LaunchMessages.CLCD_LinkCmdError, IMarker.SEVERITY_ERROR,
                                           IMarker.PRIORITY_HIGH);
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
  
  private String createX10MainFile(final ITargetOpHelper targetOpHelper, final String mainClassName, 
                                   final String workspaceDir, final IProgressMonitor monitor) throws CoreException {
    final StringBuilder sb = new StringBuilder();
    final int namespaceIndex = mainClassName.lastIndexOf(NAMESPACE_SEP);
    sb.append("#include \""); //$NON-NLS-1$
    if (namespaceIndex == -1) {
      sb.append(mainClassName);
    } else {
      sb.append(mainClassName.substring(namespaceIndex + 2));
    }
    sb.append(".h\"\n"); //$NON-NLS-1$
    sb.append(MessagePassingCodeGenerator.createMainStub(mainClassName));
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
  
  private IResourceManagerControl getResourceManagerControl(final ILaunchConfiguration configuration) throws CoreException {
    final IPUniverse universe = PTPCorePlugin.getDefault().getUniverse();
    final String rmUniqueName = getResourceManagerUniqueName(configuration);
    for (final IResourceManager resourceManager : universe.getResourceManagers()) {
      if (resourceManager.getUniqueName().equals(rmUniqueName)) {
        return (IResourceManagerControl) resourceManager;
      }
    }
    // In case of switching between local and remote for instance, a new resource manager will be created in the background 
    // with a different unique id. And so the previous code will fail. We are forced then to attempt finding one by matching
    // the name.
    final Collection<IResourceManager> resourceManagers = new ArrayList<IResourceManager>();
    for (final IResourceManager resourceManager : universe.getResourceManagers()) {
      if (resourceManager.getName().equals(this.fX10PlatformConf.getName())) {
        resourceManagers.add(resourceManager);
      }
    }
    if (resourceManagers.isEmpty()) {
      return null;
    } else if (resourceManagers.size() == 1) {
      return (IResourceManagerControl) resourceManagers.iterator().next();
    } else {
      // With multiple matches the best solution would be a dialog box that would show all the characteristics of each
      // resource manager so that the end-user could select it.
      // For now, we give up by asking the end-user to settle the name conflict situation.
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, 
                                         NLS.bind(LaunchMessages.CLCD_MultipleRMWithSameName, resourceManagers.size(),
                                                  this.fX10PlatformConf.getName())));
    }
  }
  
  private String protectPath(final String path) {
  	return path.replace(" ", "\\ "); //$NON-NLS-1$ //$NON-NLS-2$
  }
  
  private void searchForMatchingGeneratedFile(final Collection<IFileStore> matches, final IFileStore dirStore,
                                              final String curDir, final String pkgName, 
                                              final String typeName, final IProgressMonitor monitor) throws CoreException {
    for (final IFileStore fileStore : dirStore.childStores(EFS.NONE, monitor)) {
      final IFileInfo fileInfo = fileStore.fetchInfo();
      if (fileInfo.isDirectory()) {
        final StringBuilder newDir = new StringBuilder();
        if (! curDir.equals(Constants.EMPTY_STR)) {
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
          if (! curDir.equals(Constants.EMPTY_STR)) {
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
  
  private boolean shouldProcessToLinkStep(final IProject project) {
  	int errorCount = CoreResourceUtils.getNumberOfBuildErrorMarkers(project);
  	String message = (errorCount == 0) ? null : NLS.bind(LaunchMessages.CLCD_FoundErrorMarkers, errorCount,
  	                                                     project.getName());
  	if (message == null) {
  	  errorCount = CoreResourceUtils.getNumberOfPlatformConfErrorMarkers(X10PlatformConfFactory.getFile(project));
  	  message = (errorCount == 0) ? null : NLS.bind(LaunchMessages.CLCD_FoundPlatformConfErrors, errorCount, 
  	                                                project.getName());
  	}
		if (message == null) {
			return true;
		} else {
			final String boxMessage = message;
			final IWorkbench workbench = LaunchCore.getInstance().getWorkbench();
			final boolean[] result = new boolean[1];
			workbench.getDisplay().syncExec(new Runnable() {
				
				public void run() {
					final MessageBox msgBox = new MessageBox(workbench.getActiveWorkbenchWindow().getShell(), 
					                                         SWT.ICON_QUESTION | SWT.YES | SWT.NO);
					msgBox.setMessage(boxMessage);
					msgBox.setText(LaunchMessages.CLCD_LinkingCheck);
					result[0] = (msgBox.open() == SWT.YES);
				}
				
			});
			return result[0];
		}
  }
  
  // --- Fields
  
  private boolean fIsCygwin;
  
  private ITargetOpHelper fTargetOpHelper;
  
  private IX10PlatformConf fX10PlatformConf;
  
  private String fExecPath;
  
  private IResourceManagerControl fResourceManager;
  
  private String fWorkspaceDir;
  
  
  private static final String MAIN_FILE_NAME = "/xxx_main_xxx.cc"; //$NON-NLS-1$
  
  private static final String INCLUDE_OPT = "-I"; //$NON-NLS-1$
  
  private static final String LIB_OPT = "-L"; //$NON-NLS-1$
  
  private static final String PATH_ENV = "PATH"; //$NON-NLS-1$
  
  private static final String PACKAGE_SEP = "."; //$NON-NLS-1$
  
  private static final String NAMESPACE_SEP = "::"; //$NON-NLS-1$
  
  private static final String EXE_EXT = ".exe"; //$NON-NLS-1$
  
  private static final String CC_EXT = "cc"; //$NON-NLS-1$
  
}
