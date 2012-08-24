/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.java.launching;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.variables.VariablesPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ptp.core.IPTPLaunchConfigurationConstants;
import org.eclipse.ptp.core.PTPCorePlugin;
import org.eclipse.ptp.core.attributes.AttributeManager;
import org.eclipse.ptp.core.attributes.StringAttribute;
import org.eclipse.ptp.core.elements.IPQueue;
import org.eclipse.ptp.core.elements.IPResourceManager;
import org.eclipse.ptp.core.elements.attributes.JobAttributes;
import org.eclipse.ptp.launch.PTPLaunchPlugin;
import org.eclipse.ptp.launch.ParallelLaunchConfigurationDelegate;
import org.eclipse.ptp.remote.core.IRemoteConnection;
import org.eclipse.ptp.remote.core.IRemoteConnectionManager;
import org.eclipse.ptp.remote.core.IRemoteFileManager;
import org.eclipse.ptp.remote.core.IRemoteServices;
import org.eclipse.ptp.remote.core.PTPRemoteCorePlugin;
import org.eclipse.ptp.rmsystem.IResourceManager;
import org.eclipse.ptp.rmsystem.IResourceManagerConfiguration;
import org.eclipse.ptp.utils.core.ArgumentParser;

import x10dt.core.utils.URIUtils;
import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.utils.CoreResourceUtils;
import x10dt.ui.launch.core.utils.LaunchUtils;
import x10dt.ui.launch.core.utils.PTPConstants;
import x10dt.ui.launch.cpp.CppLaunchCore;
import x10dt.ui.launch.cpp.LaunchMessages;
import x10dt.ui.launch.cpp.launching.AbstractX10ResourceManager;
import x10dt.ui.launch.java.Activator;
import x10dt.ui.launch.java.Messages;
import x10dt.ui.launch.rms.core.launch_configuration.X10PlacesAndHostsDynamicTab;
import x10dt.ui.launch.rms.core.provider.AbstractX10RuntimeSystem;

/**
 * Performs launching of Java application generated by X10 back-end.
 * 
 * @author egeay
 */
public final class X10LaunchConfigurationDelegate extends ParallelLaunchConfigurationDelegate {
  
  // --- Overridden methods

  @Override
  public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor) throws CoreException {
    IJavaProject javaProject = fLocalConfDelegate.verifyJavaProject(configuration);
    IProject project= javaProject.getProject();
    boolean shouldLaunch= true;
    int errorCount = CoreResourceUtils.getNumberOfBuildErrorMarkers(project);

    if (errorCount > 0) {
      // Oops, there are errors on the project... ask the user whether to proceed with the launch
      String message = (errorCount == 0) ? null : NLS.bind(x10dt.ui.launch.core.Messages.LCD_LaunchWithErrorsCheck, errorCount, project.getName());
      shouldLaunch= LaunchUtils.queryUserToLaunchWithErrors(message);
    }
    updateAttributes(configuration, mode, monitor);
    if (shouldLaunch) {
      super.launch(configuration, mode, launch, monitor);
    }
  }
  


  private void updateAttributes(final ILaunchConfiguration configuration, final String mode,
                                                 final IProgressMonitor monitor) throws CoreException {
    final SubMonitor progress = SubMonitor.convert(monitor, 30);
    try {
      final String rmUniqueName = getResourceManagerUniqueName(configuration);
      IResourceManager resourceManager = null;
      for (final IPResourceManager prm : PTPCorePlugin.getDefault().getModelManager().getUniverse().getResourceManagers()) {
        IResourceManager rm= (IResourceManager) prm.getAdapter(IResourceManager.class);
    	if (rm.getUniqueName().equals(rmUniqueName)) {
          resourceManager = rm;
          break;
        }
      }
      if (resourceManager == null) {
        throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.XLCD_NoResourceManager));
      }
      resourceManager.stop();
      resourceManager.start(new NullProgressMonitor());

      this.fResourceManager = resourceManager;
      
      final AttributeManager attrMgr = new AttributeManager();
      
      attrMgr.addAttributes(new X10PlacesAndHostsDynamicTab().getAttributes(resourceManager, null, configuration, mode));

      // Make sure there is a queue
      if (attrMgr.getAttribute(JobAttributes.getQueueIdAttributeDefinition()) == null) {
        IPQueue queue = getQueueDefault((IPResourceManager)resourceManager.getAdapter(IPResourceManager.class));
        if (queue == null) {
          throw new CoreException(new Status(IStatus.ERROR, PTPLaunchPlugin.getUniqueIdentifier(), 
                                             Messages.XLCD_NoDefaultQueue));
        }
        attrMgr.addAttribute(JobAttributes.getQueueIdAttributeDefinition().create(queue.getID()));
      }
      progress.worked(10);

      final IJavaProject javaProject = this.fLocalConfDelegate.verifyJavaProject(configuration);
      final String mainTypeName = this.fLocalConfDelegate.getMainTypeName(configuration);

      final File workingDir = getWorkingDir(configuration, javaProject, mainTypeName);
      
      final String rmServicesId = resourceManager.getControlConfiguration().getRemoteServicesId();
      final boolean isRemote = PTPConstants.REMOTE_CONN_SERVICE_ID.equals(rmServicesId);
      final IPath jarPath;
      if (isRemote) {
        jarPath = transferJar(createJarFile(workingDir, javaProject.getElementName(), mainTypeName),
                              rmServicesId, configuration.getName(), getOutputFolder(configuration));
      } else {
        jarPath = null;
      }
      progress.worked(10);

      final String x10DistFolder = getX10DistributionFolder(configuration, isRemote);
      final IPath execPath = getExecutablePath(x10DistFolder, rmServicesId, configuration.getName());
      
      this.fExecPath = execPath;
      
      attrMgr.addAttribute(JobAttributes.getExecutableNameAttributeDefinition().create(execPath.lastSegment().toString()));
      attrMgr.addAttribute(JobAttributes.getExecutablePathAttributeDefinition().create(execPath.removeLastSegments(1).toString()));
      final String dir = isRemote ? execPath.removeLastSegments(1).toString() : workingDir.getAbsolutePath();
      attrMgr.addAttribute(JobAttributes.getWorkingDirectoryAttributeDefinition().create(dir));

 
      final String[] argArr = getProgArguments(configuration, mainTypeName, jarPath, isRemote);
      if (argArr != null) {
        attrMgr.addAttribute(JobAttributes.getProgramArgumentsAttributeDefinition().create(argArr));
      }

      final String[] envArr = this.fLocalConfDelegate.getEnvironment(configuration);
      if (envArr != null) {
        attrMgr.addAttribute(JobAttributes.getEnvironmentAttributeDefinition().create(envArr));
      }

      attrMgr.addAttribute(JobAttributes.getLaunchedByPTPFlagAttributeDefinition().create(true));
      progress.worked(10);

      AbstractX10RuntimeSystem runtimeSystem = ((AbstractX10ResourceManager) resourceManager).getRuntimeSystem();
      
      runtimeSystem.setAttributes(attrMgr.getAttributes());
    } finally {
      if (monitor != null) {
        monitor.done();
      }
    }
  }
  
  protected IPath verifyExecutablePath(final ILaunchConfiguration configuration, 
		  final IProgressMonitor monitor) throws CoreException {
	  try {
		  return verifyResource(this.fExecPath.toOSString(), configuration, monitor);
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
  
  private void addWinWMArgs(final StringBuilder stringBuilder) {
    if (Platform.OS_WIN32.equals(Platform.getOS())) {
      stringBuilder.append(' ').append(X10RT_IMPL_VMARG);
    }
  }
  
  private String buildClassPath(final ILaunchConfiguration configuration) throws CoreException {
    final StringBuilder sb = new StringBuilder();
    for (final String element : this.fLocalConfDelegate.getClasspath(configuration)) {
      if (sb.length() > 0) {
        sb.append(File.pathSeparatorChar);
      }
      sb.append(element);
    }
    return sb.toString();
  }

  private boolean containsMainType(final File folder, final String mainTypeFileName) {
    for (final File file : folder.listFiles()) {
      if (file.isDirectory()) {
        if (containsMainType(file, mainTypeFileName)) {
          return true;
        }
      } else {
        if (file.getPath().endsWith(mainTypeFileName)) {
          return true;
        }
      }
    }
    return false;
  }
  
  private void createJarFile(int pathStartIndex, final File folder, final JarOutputStream outStream, final byte[] buffer) throws IOException {
    for (final File file : folder.listFiles()) {
      if (file.isDirectory()) {
    	String name = file.getPath().substring(pathStartIndex).replace("\\", "/");
    	if (!name.endsWith("/")) {
    		name = name + "/";
    	}
    	final JarEntry jarEntry = new JarEntry(name);
    	jarEntry.setTime(file.lastModified());
        outStream.putNextEntry(jarEntry);
        outStream.closeEntry();
        createJarFile(pathStartIndex,file, outStream, buffer);
      } else {
        if (file.getName().endsWith(Constants.CLASS_EXT)) {
          final JarEntry jarEntry = new JarEntry(file.getPath().substring(pathStartIndex).replace("\\", "/"));
          jarEntry.setTime(file.lastModified());
          outStream.putNextEntry(jarEntry);
        
          final FileInputStream inputStream = new FileInputStream(file);
          while (true) {
            final int nRead = inputStream.read(buffer, 0, buffer.length);
            if (nRead <= 0) {
              break;
            } else {
              outStream.write(buffer, 0, nRead);
            }
          }
          inputStream.close();
          outStream.closeEntry();
        }
      }
    }
  }
  
  private IPath createJarFile(final File folder, final String projectName, final String mainTypeName) throws CoreException {
    try {
      final File archiveFile = new File(folder, projectName + ".jar"); //$NON-NLS-1$
    
      final FileOutputStream stream = new FileOutputStream(archiveFile);
      final Manifest manifest = new Manifest();
      manifest.getMainAttributes().putValue("Manifest-Version", "1.0"); //$NON-NLS-1$ //$NON-NLS-2$
      manifest.getMainAttributes().putValue("Main-Class", mainTypeName); //$NON-NLS-1$
      final JarOutputStream outStream = new JarOutputStream(stream, manifest);

      String path = folder.getPath().replace("\\", "/");
      int pathStartIndex = path.endsWith("/") ? path.length()  : path.length() +1 ;
      createJarFile(pathStartIndex, folder, outStream, new byte[1024]);

      outStream.close();
      stream.close();

      return new Path(archiveFile.getAbsolutePath());
    } catch (IOException except) {
      throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.XLCD_JarCreationError, except));
    }
  }
  
  private IPath getExecutablePath(final String x10DistFolder, final String rmServicesId, 
                                  final String confName) throws CoreException {
    final IRemoteServices remoteServices = PTPRemoteCorePlugin.getDefault().getRemoteServices(rmServicesId);
    final IRemoteConnectionManager rmConnManager = remoteServices.getConnectionManager();
    
    
    
    if (PTPConstants.LOCAL_CONN_SERVICE_ID.equals(rmServicesId)) {
      final String x10Launcher = this.fLocalConfDelegate.getX10DistHostLauncherDir("bin/X10Launcher").getAbsolutePath(); //$NON-NLS-1$
      final IRemoteConnection connection = rmConnManager.getConnection(IRemoteConnectionManager.DEFAULT_CONNECTION_NAME);
      final IRemoteFileManager rmFileManager = remoteServices.getFileManager(connection);
      final IFileStore x10LauncherFStore = rmFileManager.getResource(x10Launcher);
      final IFileInfo x10LauncherFInfo = x10LauncherFStore.fetchInfo();
      if (! x10LauncherFInfo.getAttribute(EFS.ATTRIBUTE_EXECUTABLE)) {
        x10LauncherFInfo.setAttribute(EFS.ATTRIBUTE_EXECUTABLE, true);
        x10LauncherFStore.putInfo(x10LauncherFInfo, EFS.SET_ATTRIBUTES, null /* monitor */);
      }
      return new Path(x10Launcher); 
    }
    final IRemoteConnection connection = rmConnManager.getConnection(confName);
    final IRemoteFileManager rmFileManager = remoteServices.getFileManager(connection);
    final IPath x10 = new Path(x10DistFolder).append("bin").append("x10"); //$NON-NLS-1$ //$NON-NLS-2$
    final IFileStore x10FileStore = rmFileManager.getResource(x10.toString());
    if (x10FileStore.fetchInfo().exists()) {
      return x10;
    } else {
      throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.XLCD_NoX10Script));
    }
  }
   
  private File getLocalFile(final IPath path) throws CoreException {
    final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
    final IResource resource = root.findMember(path);
    if (resource == null) {
      throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, NLS.bind(Messages.XLCD_ResourceNotFound, path)));
    }
    final IFileStore fileStore = EFS.getLocalFileSystem().getStore(URIUtils.getExpectedURI(resource.getLocationURI()));
    if (fileStore.fetchInfo().exists()) {
      return fileStore.toLocalFile(EFS.NONE, new NullProgressMonitor());
    } else {
      return null;
    }
  }
  
  private String getOutputFolder(final ILaunchConfiguration configuration) throws CoreException {
    final String folder = configuration.getAttribute(MultiVMAttrConstants.ATTR_REMOTE_OUTPUT_FOLDER, Constants.EMPTY_STR);
    if (folder.length() == 0) {
      throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.XLCD_OutputFolderNotDefined));
    }
    return folder;
  }
  
  private String[] getProgArguments(final ILaunchConfiguration configuration, final String mainTypeName, 
                                    final IPath jarPath, final boolean isRemote) throws CoreException {
    final StringBuilder sb = new StringBuilder();
    
    if (isRemote) {
      sb.append(String.format("%s -cp \"%s\" %s", getVMArguments(configuration, true), jarPath.toString(), mainTypeName)); //$NON-NLS-1$
    } else {
      sb.append(String.format("java %s -cp \"%s\" %s", getVMArguments(configuration, false), buildClassPath(configuration), //$NON-NLS-1$ 
                              mainTypeName + "$$Main")); //$NON-NLS-1$
    }
    
    final String attrProgArgs = configuration.getAttribute(IJavaLaunchConfigurationConstants.ATTR_PROGRAM_ARGUMENTS, ""); //$NON-NLS-1$
    final String progArgs = VariablesPlugin.getDefault().getStringVariableManager().performStringSubstitution(attrProgArgs);
   
    if (progArgs.length() > 0) {
      sb.append(' ').append(progArgs);
    }
    return new ArgumentParser(sb.toString()).getTokenArray();
  }
  
  private String getVMArguments(final ILaunchConfiguration configuration, final boolean isRemote) throws CoreException {
    final String vmArgs = this.fLocalConfDelegate.getVMArguments(configuration);
    final String[] vmArgsArray = new ArgumentParser(vmArgs).getTokenArray();
    if (isRemote) {
      final StringBuilder sb = new StringBuilder();
      for (final String vmArg : vmArgsArray) {
        if (sb.length() > 0) {
          sb.append(' ');
        }
        if (vmArg.startsWith(JAVA_LIB_PATH_OPT)) {
          sb.append("-libpath=\"").append(vmArg.substring(JAVA_LIB_PATH_OPT.length())).append('"'); //$NON-NLS-1$
        } else {
          if (sb.length() > 0) {
            sb.append(' ');
          }
          sb.append("-J").append(vmArg); //$NON-NLS-1$
        }
      }
      return sb.toString();
    } else {
      final String x10LibDir = this.fLocalConfDelegate.getX10DistHostLibDir().getAbsolutePath();
      boolean found = false;
      for (int i = 0; i < vmArgsArray.length; ++i) {
        if (vmArgsArray[i].startsWith(JAVA_LIB_PATH_OPT)) {
          found = true;
          final StringBuilder sb = new StringBuilder();
          final String end = vmArgsArray[i].substring(JAVA_LIB_PATH_OPT.length());
          sb.append(JAVA_LIB_PATH_OPT);
          if (end.charAt(0) == '"') {
            sb.append('"').append(x10LibDir).append(File.pathSeparatorChar).append(end.substring(1, end.length() - 1))
              .append('"');
          } else if (end.charAt(0) == '\'') {
            sb.append('\'').append(x10LibDir).append(File.pathSeparatorChar).append(end.substring(1, end.length() - 1))
              .append('\'');
          } else {
            sb.append('"').append(x10LibDir).append(File.pathSeparatorChar).append(end).append('"');
          }
          vmArgsArray[i] = sb.toString();
          break;
        }
      }
      if (found) {
        final StringBuilder sb = new StringBuilder();
        for (final String vmArg : vmArgsArray) {
          if (sb.length() > 0) {
            sb.append(' ');
          }
          sb.append(vmArg);
        }
        addWinWMArgs(sb);
        return sb.toString();
      } else {
        final StringBuilder sb = new StringBuilder(vmArgs);
        sb.append(' ').append(JAVA_LIB_PATH_OPT).append('"').append(x10LibDir).append('"');
        addWinWMArgs(sb);
        return sb.toString();
      }
    }
  }

  private File getWorkingDir(final ILaunchConfiguration configuration,
                             final IJavaProject javaProject, final String mainTypeName) throws CoreException {
    final IPath workDirPath = this.fLocalConfDelegate.getWorkingDirectoryPath(configuration);
    final String mainTypeFileName = mainTypeName.replace('.', File.separatorChar).concat(Constants.CLASS_EXT);
    if (workDirPath == null) {
      for (final IClasspathEntry cpEntry : javaProject.getRawClasspath()) {
        if ((cpEntry.getEntryKind() == IClasspathEntry.CPE_SOURCE) && (cpEntry.getOutputLocation() != null)) {
          final File dir = getWorkindDir(mainTypeFileName, cpEntry.getOutputLocation(), false);
          if (dir != null) {
            return dir;
          }
        }
      }
      return getWorkindDir(mainTypeFileName, javaProject.getOutputLocation(), true);
    } else {
      return getWorkindDir(mainTypeFileName, workDirPath, true);
    }
  }

  private File getWorkindDir(final String mainTypeFileName, final IPath outputPath, 
                             final boolean checkOutputFolder) throws CoreException {
    final File outputFolder = getLocalFile(outputPath);
    if ((outputFolder == null) && checkOutputFolder) {
      throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, 
                                         NLS.bind(Messages.XLCD_WorkDirDoNotExist, outputPath)));
    }
    if (containsMainType(outputFolder, mainTypeFileName)) {
      return outputFolder;
    }
    if (checkOutputFolder) {
      throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, 
                                         NLS.bind(Messages.XLCD_NoMainTypeName, outputPath)));
    }
    return null;
  }
  
  private String getX10DistributionFolder(final ILaunchConfiguration configuration, 
                                          final boolean isRemote) throws CoreException {
    if (isRemote) {
      final String folder = configuration.getAttribute(MultiVMAttrConstants.ATTR_X10_DISTRIBUTION, Constants.EMPTY_STR);
      if (folder.length() == 0) {
        throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.XLCD_NoX10Distrib));
      }
      return folder;
    } else {
      return this.fLocalConfDelegate.getX10DistHostLibDir().getParent();
    }
  }
   
  private IPath transferJar(final IPath jarPath, final String rmServicesId,
                            final String confName, final String outputFolder) throws CoreException {
    final IRemoteServices remoteServices = PTPRemoteCorePlugin.getDefault().getRemoteServices(rmServicesId);
    final IRemoteConnectionManager rmConnManager = remoteServices.getConnectionManager();
    final IRemoteConnection connection = rmConnManager.getConnection(confName);
    final IRemoteFileManager rmFileManager = remoteServices.getFileManager(connection);
    
    final IPath outputFolderPath = new Path(outputFolder).append(jarPath.lastSegment());
    final IFileStore localJarFS = EFS.getLocalFileSystem().getStore(jarPath);
    final IFileStore remoteJarFS = rmFileManager.getResource(outputFolderPath.toString());
    localJarFS.copy(remoteJarFS, EFS.OVERWRITE, null);
    
    return outputFolderPath;
  }
  
  // --- Fields
  
  private final X10LocalLaunchConfigDelegate fLocalConfDelegate = new X10LocalLaunchConfigDelegate();
  
  private IPath fExecPath;
  
  private IResourceManager fResourceManager;
  
  private static final String JAVA_LIB_PATH_OPT = "-Djava.library.path="; //$NON-NLS-1$
  
  private static final String X10RT_IMPL_VMARG = "-DX10RT_IMPL=disabled"; //$NON-NLS-1$

}
