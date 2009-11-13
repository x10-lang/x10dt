/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package org.eclipse.imp.x10dt.ui.launch.cpp.launching;

import static org.eclipse.ptp.core.IPTPLaunchConfigurationConstants.ATTR_EXECUTABLE_PATH;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.imp.utils.ConsoleUtil;
import org.eclipse.imp.x10dt.ui.launch.core.Constants;
import org.eclipse.imp.x10dt.ui.launch.core.LaunchCore;
import org.eclipse.imp.x10dt.ui.launch.core.Messages;
import org.eclipse.imp.x10dt.ui.launch.core.platform_conf.IX10PlatformConfiguration;
import org.eclipse.imp.x10dt.ui.launch.core.platform_conf.X10PlatformsManager;
import org.eclipse.imp.x10dt.ui.launch.core.utils.IInputListener;
import org.eclipse.imp.x10dt.ui.launch.core.utils.IResourceUtils;
import org.eclipse.imp.x10dt.ui.launch.core.utils.UIUtils;
import org.eclipse.imp.x10dt.ui.launch.core.utils.X10BuilderUtils;
import org.eclipse.imp.x10dt.ui.launch.cpp.CppLaunchCore;
import org.eclipse.imp.x10dt.ui.launch.cpp.LaunchMessages;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ptp.core.attributes.AttributeManager;
import org.eclipse.ptp.core.elementcontrols.IResourceManagerControl;
import org.eclipse.ptp.core.elements.IPJob;
import org.eclipse.ptp.core.elements.IPQueue;
import org.eclipse.ptp.core.elements.attributes.JobAttributes;
import org.eclipse.ptp.debug.core.IPDebugger;
import org.eclipse.ptp.debug.core.launch.IPLaunch;
import org.eclipse.ptp.launch.ParallelLaunchConfigurationDelegate;
import org.eclipse.ptp.remote.core.IRemoteConnection;
import org.eclipse.ptp.remote.core.IRemoteFileManager;
import org.eclipse.ptp.remote.core.IRemoteProcess;
import org.eclipse.ptp.remote.core.IRemoteProcessBuilder;
import org.eclipse.ptp.remote.core.IRemoteServices;
import org.eclipse.ptp.remote.core.PTPRemoteCorePlugin;
import org.eclipse.ptp.rmsystem.IResourceManagerConfiguration;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import x10cpp.visit.MessagePassingCodeGenerator;

/**
 * Performs linking and launching of C++ application generated by X10 back-end.
 * 
 * @author egeay
 */
public final class CppLaunchConfigurationDelegate extends ParallelLaunchConfigurationDelegate {

  // --- Overridden methods
  
  protected AttributeManager getAttributeManager(final ILaunchConfiguration configuration, 
                                                 final String mode) throws CoreException {
    final IResourceManagerControl resourceManager = (IResourceManagerControl) getResourceManager(configuration);
    if (resourceManager == null) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, LaunchMessages.CLCD_NoResManagerError));
    }

    final AttributeManager attrMgr = new AttributeManager();

    // Collects attributes from Resource tab
    attrMgr.addAttributes(getResourceAttributes(configuration, mode));

    // Makes sure there is a queue, even if the resources tab doesn't require one to be specified.
    if (attrMgr.getAttribute(JobAttributes.getQueueIdAttributeDefinition()) == null) {
      final IPQueue queue = getQueueDefault(resourceManager);
      attrMgr.addAttribute(JobAttributes.getQueueIdAttributeDefinition().create(queue.getID()));
    }

    // Collects attributes from Application tab
    final IPath programPath = verifyExecutablePath(configuration);
    attrMgr.addAttribute(JobAttributes.getExecutableNameAttributeDefinition().create(programPath.lastSegment()));

    final String path = programPath.removeLastSegments(1).toString();
    if (path != null) {
      attrMgr.addAttribute(JobAttributes.getExecutablePathAttributeDefinition().create(path));
    }

    // Collects attributes from Arguments tab
    final String wd = verifyWorkDirectory(configuration);
    if (wd != null) {
      attrMgr.addAttribute(JobAttributes.getWorkingDirectoryAttributeDefinition().create(wd));
    }

    final String[] argArr = getProgramArguments(configuration);
    if (argArr != null) {
      attrMgr.addAttribute(JobAttributes.getProgramArgumentsAttributeDefinition().create(argArr));
    }

    // Collects attributes from Environment tab
    final String[] envArr = getEnvironmentToAppend(configuration);
    if (envArr != null) {
      attrMgr.addAttribute(JobAttributes.getEnvironmentAttributeDefinition().create(envArr));
    }

    // PTP launched this job
    attrMgr.addAttribute(JobAttributes.getLaunchedByPTPFlagAttributeDefinition().create(true));
    
    return attrMgr;
  }
  
  protected void doCompleteJobLaunch(final ILaunchConfiguration configuration, final String mode, final IPLaunch launch, 
                                     final AttributeManager mgr, final IPDebugger debugger, final IPJob job) {
    if (mode.equals(ILaunchManager.DEBUG_MODE)) {
      job.setDebug();
    }
    super.doCompleteJobLaunch(configuration, mode, launch, mgr, debugger, job);
  }
  
  public void launch(final ILaunchConfiguration configuration, final String mode, final ILaunch launch, 
                     final IProgressMonitor monitor) throws CoreException {
    final IResourceManagerControl rmControl = (IResourceManagerControl) getResourceManager(configuration);
    final IResourceManagerConfiguration rmc = rmControl.getConfiguration();
    final IRemoteServices remoteServices = PTPRemoteCorePlugin.getDefault().getRemoteServices(rmc.getRemoteServicesId());
    final IRemoteConnection connection = remoteServices.getConnectionManager().getConnection(rmc.getConnectionName());
    try {
      if (connection != null) {
        // Performs linking first.
        monitor.beginTask(null, 10);
        monitor.subTask(LaunchMessages.CLCD_ExecCreationTaskName);
        if (createExecutable(connection, remoteServices, configuration, verifyProject(configuration), 
                             new SubProgressMonitor(monitor, 8)) == 0) {
          // Then, performs the launch.
          monitor.subTask(LaunchMessages.CLCD_LaunchCreationTaskName);
          super.launch(configuration, mode, launch, new SubProgressMonitor(monitor, 2));
        }
      }
    } finally {
      monitor.done();
    }
  }

  // --- Private code
  
  private int createExecutable(final IRemoteConnection connection, final IRemoteServices remoteServices,
                               final ILaunchConfiguration configuration, final IProject project, 
                               final IProgressMonitor monitor) throws CoreException {
    final SubMonitor subMonitor = SubMonitor.convert(monitor, 10);
    final IRemoteFileManager fileManager = remoteServices.getFileManager(connection);
    try {
      final String workspaceDir = project.getPersistentProperty(Constants.WORKSPACE_DIR);
      final String platformConfName = project.getPersistentProperty(Constants.X10_PLATFORM_CONF);
      final Map<String, IX10PlatformConfiguration> platforms = X10PlatformsManager.loadPlatformsConfiguration();
      final IX10PlatformConfiguration platform = platforms.get(platformConfName);
      final String execPath = configuration.getAttribute(ATTR_EXECUTABLE_PATH, (String) null);
      final boolean shouldLinkApp = configuration.getAttribute(Constants.ATTR_SHOULD_LINK_APP, true);
      
      final IFileStore mainClassFileStore = fileManager.getResource(execPath);
      if (mainClassFileStore.fetchInfo().exists() && ! shouldLinkApp) {
        return 0;
      }
      
      final int dotIndex = execPath.lastIndexOf('.');
      final int lastIndex = (dotIndex == -1) ? execPath.length() : dotIndex;
      final String className = execPath.substring(execPath.lastIndexOf('/') + 1, lastIndex);
      final String mainFilePath = createMainFile(fileManager, className, workspaceDir, subMonitor.newChild(1)); 
      
      final List<String> command = new ArrayList<String>();
      command.add(platform.getLinker());
      command.addAll(X10BuilderUtils.getAllTokens(platform.getLinkingOpts()));
      command.add(INCLUDE_OPT + workspaceDir);
      for (final String headerLoc : platform.getX10HeadersLocations()) {
        command.add(INCLUDE_OPT + headerLoc);
      }
      command.add(mainFilePath);
      command.add("-o"); //$NON-NLS-1$
      command.add(execPath);
      command.add(LIB_OPT + workspaceDir);
      for (final String libLoc : platform.getX10LibsLocations()) {
        command.add(LIB_OPT + libLoc);
      }
      command.add("-l" + project.getName()); //$NON-NLS-1$
      command.addAll(X10BuilderUtils.getAllTokens(platform.getLinkingLibs()));

      final IRemoteProcessBuilder processBuilder = remoteServices.getProcessBuilder(connection, command);
      final IRemoteProcess process = processBuilder.start();
      
      final MessageConsole messageConsole = ConsoleUtil.findConsole(Messages.CPPB_ConsoleName);
      final MessageConsoleStream mcStream = messageConsole.newMessageStream();
      final StringBuilder cmdBuilder = new StringBuilder();
      for (final String str : command) {
        cmdBuilder.append(str).append(' ');
      }
      UIUtils.printStream(process.getInputStream(), process.getErrorStream(), new IInputListener() {
        
        public void after() {
        }

        public void before() {
          this.fCounter = 0;
        }
        
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
      
      process.waitFor();
        
      final int returnCode = process.exitValue();
      if (returnCode != 0) {
        IResourceUtils.addMarkerTo(project, LaunchMessages.CLCD_LinkCmdError, IMarker.SEVERITY_ERROR, 
                                   project.getFullPath().toString(), IMarker.PRIORITY_HIGH);
      }
      
      return returnCode;
    } catch (WorkbenchException except) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, Messages.XPCPP_LoadingErrorMsg, except));
    } catch (IOException except) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, Messages.XPCPP_LoadingErrorMsg, except));
    } catch (InterruptedException except) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, LaunchMessages.CLCD_LinkingInterrupted, 
                                         except));
    } finally {
      subMonitor.done();
    }
  }
  
  private String createMainFile(final IRemoteFileManager fileManager, final String mainClassName, 
                                final String workspaceDir, final IProgressMonitor monitor) throws CoreException {
    final StringBuilder sb = new StringBuilder();
    sb.append("#include \"").append(mainClassName).append(".h\"\n"); //$NON-NLS-1$ //$NON-NLS-2$
    sb.append(MessagePassingCodeGenerator.createMainStub(mainClassName));
    final InputStream is = new ByteArrayInputStream(sb.toString().getBytes());
      
    final String mainFileName = workspaceDir + MAIN_FILE_NAME;
    final IFileStore fileStore = fileManager.getResource(mainFileName);
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
  
  // --- Fields
  
  private static final String MAIN_FILE_NAME = "/xxx_main_xxx.cc"; //$NON-NLS-1$
  
  private static final String INCLUDE_OPT = "-I"; //$NON-NLS-1$
  
  private static final String LIB_OPT = "-L"; //$NON-NLS-1$
  
}
