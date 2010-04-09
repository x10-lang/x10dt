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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
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
import org.eclipse.imp.x10dt.core.builder.X10Builder;
import org.eclipse.imp.x10dt.ui.launch.core.Constants;
import org.eclipse.imp.x10dt.ui.launch.core.LaunchCore;
import org.eclipse.imp.x10dt.ui.launch.core.Messages;
import org.eclipse.imp.x10dt.ui.launch.core.builder.target_op.ITargetOpHelper;
import org.eclipse.imp.x10dt.ui.launch.core.builder.target_op.TargetOpHelperFactory;
import org.eclipse.imp.x10dt.ui.launch.core.platform_conf.ETargetOS;
import org.eclipse.imp.x10dt.ui.launch.core.utils.IProcessOuputListener;
import org.eclipse.imp.x10dt.ui.launch.core.utils.IResourceUtils;
import org.eclipse.imp.x10dt.ui.launch.core.utils.X10BuilderUtils;
import org.eclipse.imp.x10dt.ui.launch.cpp.CppLaunchCore;
import org.eclipse.imp.x10dt.ui.launch.cpp.LaunchMessages;
import org.eclipse.imp.x10dt.ui.launch.cpp.platform_conf.IConnectionConf;
import org.eclipse.imp.x10dt.ui.launch.cpp.platform_conf.ICppCompilationConf;
import org.eclipse.imp.x10dt.ui.launch.cpp.platform_conf.IX10PlatformConf;
import org.eclipse.imp.x10dt.ui.launch.cpp.utils.PlatformConfUtils;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ptp.core.attributes.AttributeManager;
import org.eclipse.ptp.core.attributes.IAttribute;
import org.eclipse.ptp.core.elementcontrols.IResourceManagerControl;
import org.eclipse.ptp.core.elements.IPJob;
import org.eclipse.ptp.core.elements.IPQueue;
import org.eclipse.ptp.core.elements.attributes.JobAttributes;
import org.eclipse.ptp.debug.core.IPDebugger;
import org.eclipse.ptp.debug.core.launch.IPLaunch;
import org.eclipse.ptp.launch.ParallelLaunchConfigurationDelegate;
import org.eclipse.ptp.rm.mpi.mpich2.core.MPICH2LaunchAttributes;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbench;
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
    final IAttribute<?, ?, ?>[] resourceAttributes = getResourceAttributes(configuration, mode);
    if (this.fIsCygwin) {
      final StringBuilder pathBuilder = new StringBuilder();
      pathBuilder.append(this.fTargetOpHelper.getEnvVarValue(PATH_ENV)); 
      for (final String x10Lib : this.fCppCompConf.getX10LibsLocations()) {
        pathBuilder.append(File.pathSeparatorChar).append(x10Lib);
      }
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
    try {
      // Performs linking first.
      monitor.beginTask(null, 10);
      monitor.subTask(LaunchMessages.CLCD_ExecCreationTaskName);
      final IProject project = verifyProject(configuration);
      if (! monitor.isCanceled() && shouldProcessToLinkStep(project) && 
      		createExecutable(configuration, project, new SubProgressMonitor(monitor, 5)) == 0) {
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

  // --- Private code
  
  private int createExecutable(final ILaunchConfiguration configuration, final IProject project, 
                               final IProgressMonitor monitor) throws CoreException {
    final SubMonitor subMonitor = SubMonitor.convert(monitor, 10);
    try {
      final IX10PlatformConf platformConf = CppLaunchCore.getInstance().getPlatformConfiguration(project);
      this.fCppCompConf = platformConf.getCppCompilationConf();
      final IConnectionConf connConf = platformConf.getConnectionConf();
      this.fIsCygwin = this.fCppCompConf.getTargetOS() == ETargetOS.WINDOWS;
      this.fTargetOpHelper = TargetOpHelperFactory.create(connConf.isLocal(), this.fIsCygwin, connConf.getConnectionName());
      final String workspaceDir = PlatformConfUtils.getWorkspaceDir(platformConf, project);
      final String execPath = configuration.getAttribute(ATTR_EXECUTABLE_PATH, (String) null);
      final boolean shouldLinkApp = configuration.getAttribute(Constants.ATTR_SHOULD_LINK_APP, true);
      
      final IFileStore mainClassFileStore = this.fTargetOpHelper.getStore(execPath);
      if (mainClassFileStore.fetchInfo().exists() && ! shouldLinkApp) {
        return 0;
      }
      
      final String className = configuration.getAttribute(Constants.ATTR_X10_MAIN_CLASS, Constants.EMPTY_STR)
                                            .replace(PACKAGE_SEP, NAMESPACE_SEP);
      final String mainX10FilePath = createX10MainFile(this.fTargetOpHelper, className, workspaceDir, subMonitor.newChild(1)); 
      
      final String mainCppFilePath = configuration.getAttribute(Constants.ATTR_MAIN_CPP_FILE_PATH, (String) null);
      final int slashIndex = mainCppFilePath.lastIndexOf('/');
      final String mainCppFileIncludePath = (slashIndex == -1) ? mainCppFilePath : mainCppFilePath.substring(0, slashIndex);
      
      final List<String> command = new ArrayList<String>();
      command.add(this.fTargetOpHelper.getTargetSystemPath(this.fCppCompConf.getLinker()));
      command.addAll(X10BuilderUtils.getAllTokens(this.fCppCompConf.getLinkingOpts(true)));
      command.add(INCLUDE_OPT + this.fTargetOpHelper.getTargetSystemPath(workspaceDir));
      command.add(INCLUDE_OPT + this.fTargetOpHelper.getTargetSystemPath(mainCppFileIncludePath));
      for (final String headerLoc : this.fCppCompConf.getX10HeadersLocations()) {
        command.add(INCLUDE_OPT + this.fTargetOpHelper.getTargetSystemPath(headerLoc));
      }
      command.add(this.fTargetOpHelper.getTargetSystemPath(mainX10FilePath));
      command.add("-o"); //$NON-NLS-1$
      command.add(this.fTargetOpHelper.getTargetSystemPath(execPath));
      command.add(LIB_OPT + this.fTargetOpHelper.getTargetSystemPath(workspaceDir));
      for (final String libLoc : this.fCppCompConf.getX10LibsLocations()) {
        command.add(LIB_OPT + this.fTargetOpHelper.getTargetSystemPath(libLoc));
      }
      command.add("-l" + project.getName()); //$NON-NLS-1$
      command.addAll(X10BuilderUtils.getAllTokens(this.fCppCompConf.getLinkingLibs(true)));
      
      final MessageConsole messageConsole = ConsoleUtil.findConsole(Messages.CPPB_ConsoleName);
      messageConsole.clearConsole();
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
        IResourceUtils.addMarkerTo(project, LaunchMessages.CLCD_LinkCmdError, IMarker.SEVERITY_ERROR, 
                                   project.getFullPath().toString(), IMarker.PRIORITY_HIGH);
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
  
  private boolean shouldProcessToLinkStep(final IProject project) {
  	String message = null;
  	try {
			final IMarker[] markers = project.findMarkers(X10Builder.PROBLEMMARKER_ID, true /* includeSubtypes */, 
			                                              IResource.DEPTH_INFINITE);
			int errorCount = 0;
			for (final IMarker marker : markers) {
				if (marker.getAttribute(IMarker.SEVERITY, -1) == IMarker.SEVERITY_ERROR) {
					++errorCount;
				}
			}
			message = (errorCount == 0) ? null : NLS.bind(LaunchMessages.CLCD_FoundErrorMarkers, errorCount, project.getName());
		} catch (CoreException except) {
			message = LaunchMessages.CLCD_CouldNotAccessErrorMarkers;
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
  
  private ICppCompilationConf fCppCompConf;
  
  
  private static final String MAIN_FILE_NAME = "/xxx_main_xxx.cc"; //$NON-NLS-1$
  
  private static final String INCLUDE_OPT = "-I"; //$NON-NLS-1$
  
  private static final String LIB_OPT = "-L"; //$NON-NLS-1$
  
  private static final String PATH_ENV = "PATH"; //$NON-NLS-1$
  
  private static final String PACKAGE_SEP = "."; //$NON-NLS-1$
  
  private static final String NAMESPACE_SEP = "::"; //$NON-NLS-1$
  
}
