/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package org.eclipse.imp.x10dt.ui.cpp.launch.launching;

import static org.eclipse.ptp.core.IPTPLaunchConfigurationConstants.ATTR_EXECUTABLE_PATH;
import static org.eclipse.ptp.core.IPTPLaunchConfigurationConstants.ATTR_WORK_DIRECTORY;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.imp.utils.ConsoleUtil;
import org.eclipse.imp.x10dt.ui.cpp.launch.Constants;
import org.eclipse.imp.x10dt.ui.cpp.launch.LaunchCore;
import org.eclipse.imp.x10dt.ui.cpp.launch.LaunchMessages;
import org.eclipse.imp.x10dt.ui.cpp.launch.utils.IResourceUtils;
import org.eclipse.jface.preference.IPreferenceStore;
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
import org.eclipse.ptp.remote.core.IRemoteProxyOptions;
import org.eclipse.ptp.remote.core.IRemoteServices;
import org.eclipse.ptp.remote.core.PTPRemoteCorePlugin;
import org.eclipse.ptp.remote.remotetools.core.RemoteToolsConnection;
import org.eclipse.ptp.remote.ui.IRemoteUIServices;
import org.eclipse.ptp.remote.ui.PTPRemoteUIPlugin;
import org.eclipse.ptp.remotetools.core.IRemoteCopyTools;
import org.eclipse.ptp.remotetools.core.IRemoteExecutionManager;
import org.eclipse.ptp.remotetools.core.IRemoteExecutionTools;
import org.eclipse.ptp.remotetools.core.IRemoteScript;
import org.eclipse.ptp.remotetools.core.IRemoteScriptExecution;
import org.eclipse.ptp.remotetools.exception.CancelException;
import org.eclipse.ptp.remotetools.exception.RemoteConnectionException;
import org.eclipse.ptp.remotetools.exception.RemoteExecutionException;
import org.eclipse.ptp.remotetools.exception.RemoteOperationException;
import org.eclipse.ptp.rm.remote.core.AbstractRemoteResourceManagerConfiguration;
import org.eclipse.ptp.rmsystem.IResourceManagerConfiguration;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import polyglot.ext.x10cpp.ExtensionInfo;

/**
 * Performs linking and launching of C++ application generated by X10 back-end.
 * 
 * @author egeay
 */
public final class CppLaunchConfigurationDelegate extends ParallelLaunchConfigurationDelegate {

  // --- Overridden methods
  
  protected AttributeManager getAttributeManager(ILaunchConfiguration configuration, String mode) throws CoreException {
    final IResourceManagerControl resourceManager = (IResourceManagerControl) getResourceManager(configuration);
    if (resourceManager == null) {
      throw new CoreException(new Status(IStatus.ERROR, LaunchCore.PLUGIN_ID, LaunchMessages.CLCD_NoResManagerError));
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

    if (mode.equals(ILaunchManager.DEBUG_MODE)) {
      
      attrMgr.addAttribute(X10DebugAttributes.getDebuggerHostAddressAttributeDefinition().create(getDebugHostAddress(resourceManager)));
    }

    return attrMgr;
  }

  private String getDebugHostAddress(final IResourceManagerControl rm) {
    final AbstractRemoteResourceManagerConfiguration rmc = (AbstractRemoteResourceManagerConfiguration) rm.getConfiguration();
    if (rmc.testOption(IRemoteProxyOptions.PORT_FORWARDING)) {
      final IRemoteServices remServices = PTPRemoteCorePlugin.getDefault().getRemoteServices(rmc.getRemoteServicesId());
      final IRemoteUIServices remUIServices = PTPRemoteUIPlugin.getDefault().getRemoteUIServices(remServices);
	      if (remServices != null && remUIServices != null) {
        final IRemoteConnection rmConn = remServices.getConnectionManager().getConnection(rmc.getConnectionName());
        return rmConn.getAddress();
      }
    } else {
      String localAddress = rmc.getLocalAddress();
      if (localAddress != null)
        return localAddress;
      try {
        final InetAddress ip = InetAddress.getLocalHost();
        return ip.getHostAddress();
      } catch (UnknownHostException except) {
        // Simply forgets
      }
    }
    return null;
  }

  protected void doCompleteJobLaunch(ILaunchConfiguration configuration,
                                     String mode, IPLaunch launch, AttributeManager mgr,
                                     IPDebugger debugger, IPJob job)
  {
    if (mode.equals(ILaunchManager.DEBUG_MODE))
      job.setDebug();
    super.doCompleteJobLaunch(configuration, mode, launch, mgr, debugger, job);
  }

  public void launch(final ILaunchConfiguration configuration, final String mode, final ILaunch launch, 
                     final IProgressMonitor monitor) throws CoreException {
    final IRemoteConnection rmConnection = getRemoteConnection(configuration);
    try {
      if (rmConnection != null) {
        // Performs linking first.
        monitor.beginTask(null, 10);
        monitor.subTask(LaunchMessages.CLCD_ExecCreationTaskName);
        createExecutable(rmConnection, configuration, verifyProject(configuration), new SubProgressMonitor(monitor, 3));
        
        // Then, performs the launch.
        monitor.subTask(LaunchMessages.CLCD_LaunchCreationTaskName);
        super.launch(configuration, mode, launch, new SubProgressMonitor(monitor, 7));
      }
    } finally {
      monitor.done();
    }
  }
  
  // --- Private code
  
  private void createExecutable(final IRemoteConnection rmConnection, final ILaunchConfiguration configuration,
                                final IProject project, final IProgressMonitor monitor) throws CoreException {
    if (rmConnection instanceof RemoteToolsConnection) {
      try {
        final IRemoteExecutionManager rmExecManager = ((RemoteToolsConnection) rmConnection).createExecutionManager();
        final IRemoteExecutionTools rmExecTools = rmExecManager.getExecutionTools();
                
        final String workspaceDir = configuration.getAttribute(ATTR_WORK_DIRECTORY, (String) null);
        final String appProgName = configuration.getAttribute(ATTR_EXECUTABLE_PATH, (String) null);
        final boolean shouldLinkApp = configuration.getAttribute(Constants.ATTR_SHOULD_LINK_APP, true);
        
        try {
          if (rmExecManager.getRemoteFileTools().hasFile(appProgName) && ! shouldLinkApp) {
            return;
          }
        } catch (RemoteOperationException except) {
          // We failed on checking if we should link... but let's try to perform the link step.
        }
        
        createMainFile(rmExecManager.getRemoteCopyTools(), appProgName, workspaceDir);
        
        final IPreferenceStore store = LaunchCore.getInstance().getPreferenceStore();
        final String x10DistLoc = store.getString(Constants.P_CPP_BUILDER_X10_DIST_LOC);
        final String pgasLoc = store.getString(Constants.P_CPP_BUILDER_PGAS_LOC);
        final String linkCmdStart = store.getString(Constants.P_CPP_BUILDER_LINK_CMD);

        final IRemoteScript script = rmExecTools.createScript();
        script.addEnvironment("X10_DIST_LOC=" + x10DistLoc); //$NON-NLS-1$
        script.addEnvironment("PGAS_LOC=" + pgasLoc); //$NON-NLS-1$
          
        final StringBuilder commandBuilder = new StringBuilder();
        commandBuilder.append(linkCmdStart).append(" -L").append(workspaceDir).append(" -I").append(workspaceDir) //$NON-NLS-1$ //$NON-NLS-2$
                      .append(SPACE).append(workspaceDir).append('/').append(MAIN_FILE_NAME)
                      .append(" -o ").append(appProgName).append(SPACE).append("-l").append(project.getName()); //$NON-NLS-1$ //$NON-NLS-2$

        script.setScript(commandBuilder.toString());
            
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        script.setProcessOutputStream(outputStream);
        final ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
        script.setProcessErrorStream(errorStream);
        
        final IRemoteScriptExecution rmExec = rmExecTools.executeScript(script);
        rmExec.waitForEndOfExecution();
        
        final int returnCode = rmExec.getReturnCode();
        if (returnCode != 0) {
          IResourceUtils.addMarkerTo(project, NLS.bind(LaunchMessages.CLCD_LinkCmdError, commandBuilder.toString()), 
                                     IMarker.SEVERITY_ERROR, project.getFullPath().toString(), IMarker.PRIORITY_HIGH);
          final MessageConsole messageConsole = ConsoleUtil.findConsole(LaunchMessages.CPPB_ConsoleName);
          final MessageConsoleStream mcStream = messageConsole.newMessageStream();
          mcStream.println(NLS.bind(LaunchMessages.CLCD_CmdUsedMsg, commandBuilder.toString()));
          mcStream.println(errorStream.toString());
        }
        rmExec.close();
      } catch (RemoteConnectionException except) {
        throw new CoreException(new Status(IStatus.ERROR, LaunchCore.PLUGIN_ID, LaunchMessages.CLCD_LinkConnError, except));
      } catch (RemoteExecutionException except) {
        throw new CoreException(new Status(IStatus.ERROR, LaunchCore.PLUGIN_ID, LaunchMessages.CLCD_LinkExecError, except));
      } catch (CancelException except) {
        throw new CoreException(new Status(IStatus.CANCEL, LaunchCore.PLUGIN_ID, LaunchMessages.CLCD_LinkCancellation));
      }
    }
  }
  
  private void createMainFile(final IRemoteCopyTools rmCopyTools, final String appProgName, 
                              final String workspaceDir) throws CoreException, RemoteConnectionException, CancelException {
    final URL url = ExtensionInfo.class.getClassLoader().getResource(MAIN_TEMPLATE_FILE);
    try {
      if (url == null) {
        throw new FileNotFoundException(NLS.bind(LaunchMessages.CLCD_NoMainCPFileError, MAIN_TEMPLATE_FILE));
      } else {
        try {
          // Firstly, reads the content of the file and instantiates it with the main class name,
          final BufferedReader reader = new BufferedReader(new FileReader(new File(FileLocator.toFileURL(url).toURI())));
          
          final File tmpMainFile = new File(System.getProperty("java.io.tmpdir"), MAIN_FILE_NAME); //$NON-NLS-1$
          final BufferedWriter writer = new BufferedWriter(new FileWriter(tmpMainFile));          
          try {
            final StringBuilder sb = new StringBuilder();
            sb.append("#include \"").append(appProgName).append(".h\"\n"); //$NON-NLS-1$ //$NON-NLS-2$
            writer.write(sb.toString());
            final String progName = appProgName.substring(appProgName.lastIndexOf('/') + 1);
            
            String line = null;
            while ((line = reader.readLine()) != null) {
              writer.write(line.replace(PATTERN, progName).replace("##", "#") + '\n'); //$NON-NLS-1$ //$NON-NLS-2$
            }
          } finally {
            reader.close();
            writer.close();
          }
          // Secondly, transfers the file in the remote directory.
          rmCopyTools.uploadFileToDir(tmpMainFile, workspaceDir);
          // Thirdly and finally, deletes the local temporary file.
          tmpMainFile.delete();
        } catch (URISyntaxException except) {
          // Should really never occur.
        } 
      }
    } catch (IOException except) {
      throw new CoreException(new Status(IStatus.ERROR, LaunchCore.PLUGIN_ID, LaunchMessages.CLCD_NoMainFileAccessIOError, 
                                         except));
    } catch (RemoteOperationException except) {
      throw new CoreException(new Status(IStatus.ERROR, LaunchCore.PLUGIN_ID, LaunchMessages.CLCD_NoMainFileAccessOpError, 
                                         except));
    }
  }
  
  private IRemoteConnection getRemoteConnection(final ILaunchConfiguration configuration) throws CoreException {
    final IResourceManagerControl resourceManager = (IResourceManagerControl) getResourceManager(configuration);
    if (resourceManager != null) {
      final IResourceManagerConfiguration rmConf = resourceManager.getConfiguration();
      if (rmConf instanceof AbstractRemoteResourceManagerConfiguration) {
        final AbstractRemoteResourceManagerConfiguration remConf = (AbstractRemoteResourceManagerConfiguration) rmConf;
        final IRemoteServices rmServices = PTPRemoteCorePlugin.getDefault().getRemoteServices(remConf.getRemoteServicesId());
        
        return rmServices.getConnectionManager().getConnection(remConf.getConnectionName());
      }
    }
    return null;
  }
  
  // --- Fields
  
  private static final String SPACE = " "; //$NON-NLS-1$
  
  private static final String PATTERN = "#0"; //$NON-NLS-1$
  
  private static final String MAIN_FILE_NAME = "xxx_main_xxx.cc"; //$NON-NLS-1$
  
  private static final String MAIN_TEMPLATE_FILE = "data/MainMP.xcd"; //$NON-NLS-1$

}
