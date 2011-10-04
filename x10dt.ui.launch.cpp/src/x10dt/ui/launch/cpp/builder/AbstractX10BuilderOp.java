/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.builder;

import static x10dt.ui.launch.core.Constants.A_EXT;
import static x10dt.ui.launch.core.Constants.CC_EXT;
import static x10dt.ui.launch.core.Constants.CPP_EXT;
import static x10dt.ui.launch.core.Constants.CXX_EXT;
import static x10dt.ui.launch.core.Constants.EXEC_PATH;
import static x10dt.ui.launch.core.Constants.O_EXT;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.imp.java.hosted.BuildPathUtils;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import x10dt.core.utils.ICountableIterable;
import x10dt.core.utils.URIUtils;
import x10dt.ui.launch.core.LaunchCore;
import x10dt.ui.launch.core.Messages;
import x10dt.ui.launch.core.builder.AbstractX10Builder;
import x10dt.ui.launch.core.builder.target_op.IX10BuilderFileOp;
import x10dt.ui.launch.core.platform_conf.ETargetOS;
import x10dt.ui.launch.core.platform_conf.EValidationStatus;
import x10dt.ui.launch.core.utils.CoreResourceUtils;
import x10dt.ui.launch.core.utils.IProcessOuputListener;
import x10dt.ui.launch.core.utils.UIUtils;
import x10dt.ui.launch.core.utils.X10BuilderUtils;
import x10dt.ui.launch.cpp.CppLaunchCore;
import x10dt.ui.launch.cpp.LaunchMessages;
import x10dt.ui.launch.cpp.builder.target_op.ITargetOpHelper;
import x10dt.ui.launch.cpp.builder.target_op.TargetOpHelperFactory;
import x10dt.ui.launch.cpp.platform_conf.IConnectionConf;
import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConf;


abstract class AbstractX10BuilderOp implements IX10BuilderFileOp {
  
  protected AbstractX10BuilderOp(final IX10PlatformConf platformConf, final IJavaProject javaProject, 
                                 final String workspaceDir, 
                                 final Map<String, Collection<String>> generatedFiles) throws CoreException {
    if (workspaceDir == null) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, Messages.AXBO_NoRemoteOutputFolder));
    }

    this.fConfName = platformConf.getName();
    this.fJavaProject = javaProject;
    this.fWorkspaceDir = workspaceDir;
    this.fPlatformConf = platformConf;
    final IConnectionConf connConf = platformConf.getConnectionConf();
    this.fIsLocal = connConf.isLocal();
    final boolean isCygwin = this.fPlatformConf.getCppCompilationConf().getTargetOS() == ETargetOS.WINDOWS;
    this.fTargetOpHelper = TargetOpHelperFactory.create(connConf.isLocal(), isCygwin, connConf.getConnectionName());
    if (this.fTargetOpHelper == null) {
      String msg= NLS.bind(Messages.CPPB_NoValidConnectionError, 
                           new Object[] { connConf.getHostName(), this.fJavaProject.getProject().getName() } );
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, msg));
    }
    this.fGeneratedFiles = generatedFiles;
  }
  
  protected AbstractX10BuilderOp(final IJavaProject javaProject, final String workspaceDir, final IX10PlatformConf platformConf,
                                 final ITargetOpHelper targetOpHelper, final Map<String, Collection<String>> generatedFiles) {
    this.fConfName = platformConf.getName();
    this.fJavaProject = javaProject;
    this.fWorkspaceDir = workspaceDir;
    this.fIsLocal = platformConf.getConnectionConf().isLocal();
    this.fPlatformConf = platformConf;
    this.fTargetOpHelper = targetOpHelper;
    this.fGeneratedFiles = generatedFiles;
  }
  
  // --- Interface methods implementation
  
  public final void archive(final IProgressMonitor monitor) throws CoreException {
    Collection<String> objectFiles= collectAllObjectFiles();

    if (objectFiles.isEmpty()) {
      return;
    }

    monitor.subTask(Messages.CPPB_ArchivingTaskName);
    final List<String> archiveCmd = new ArrayList<String>();
    archiveCmd.add(this.fPlatformConf.getCppCompilationConf().getArchiver());
    archiveCmd.addAll(X10BuilderUtils.getAllTokens(this.fPlatformConf.getCppCompilationConf().getArchivingOpts()));
    final StringBuilder libName = new StringBuilder();
    libName.append("lib").append(this.fJavaProject.getProject().getName()).append(A_EXT); //$NON-NLS-1$
    archiveCmd.add(libName.toString());


    try {
      for (final String objectFile : objectFiles) {
        archiveCmd.add(objectFile);
      }

      final MessageConsole messageConsole = UIUtils.findOrCreateX10Console();
      final MessageConsoleStream mcStream = messageConsole.newMessageStream();
      final int returnCode = this.fTargetOpHelper.run(archiveCmd, this.fWorkspaceDir, new IProcessOuputListener() {
        
        public void read(final String line) {
        }
        
        public void readError(final String line) {
          mcStream.println(line);
        }
        
      }, monitor);
    
      if (returnCode != 0) {
        CoreResourceUtils.addBuildMarkerTo(getProject(), NLS.bind(Messages.CPPB_LibCreationError, archiveCmd), 
                                           IMarker.SEVERITY_ERROR, IMarker.PRIORITY_HIGH);
      }
    } catch (IOException except) {
      CoreResourceUtils.addBuildMarkerTo(getProject(), NLS.bind(Messages.CPPB_TargetOpError, this.fConfName), 
                                         IMarker.SEVERITY_ERROR, IMarker.PRIORITY_HIGH);
      LaunchCore.log(IStatus.ERROR, NLS.bind(Messages.CPPB_TargetOpError, this.fConfName), except);
    } catch (InterruptedException except) {
      CoreResourceUtils.addBuildMarkerTo(getProject(), NLS.bind(Messages.CPPB_CancelOpMsg, this.fConfName), 
                                         IMarker.SEVERITY_WARNING, IMarker.PRIORITY_LOW);
    } finally {
      monitor.done();
    }
  }
  
  public void cleanFiles(final ICountableIterable<IFile> files, final SubMonitor monitor) throws CoreException {
    final NullProgressMonitor nullMonitor = new NullProgressMonitor();
    try {
      monitor.beginTask(null, files.getSize() + 1);
      
      for (final IFile sourceFile : files) {
        if (monitor.isCanceled()) {
          return;
        }
        final String path = BuildPathUtils.getBareName(sourceFile, this.fJavaProject);
        final Collection<String> genStrings = this.fGeneratedFiles.get(path);
        if (genStrings != null){
        	for(String file: genStrings){
        		if (file.endsWith(CC_EXT)){
        			final String dotO = this.fWorkspaceDir + File.separator + removeExtension(file) + O_EXT;
        			deleteFile(this.fTargetOpHelper.getStore(dotO), nullMonitor);
        		}
        		deleteFile(this.fTargetOpHelper.getStore(this.fWorkspaceDir + File.separator +file), nullMonitor);
        	}
        }
      
        monitor.worked(1);
      }
      
      if (files.getSize() > 0) {
        final IFileStore parentStore = this.fTargetOpHelper.getStore(this.fWorkspaceDir);
        deleteFile(parentStore.getChild("xxx_main_xxx.cc"), nullMonitor); //$NON-NLS-1$
        deleteFile(parentStore.getChild("lib" + getProject().getName() + A_EXT), nullMonitor); //$NON-NLS-1$
      
        final String execPath = getProject().getPersistentProperty(EXEC_PATH);
        if (execPath != null) {
          deleteFile(this.fTargetOpHelper.getStore(execPath), nullMonitor);
        }
      }
      monitor.worked(1);
    } finally {
      monitor.done();
    }
  }
  
  public final boolean compile(final IProgressMonitor monitor) throws CoreException {
    monitor.beginTask(null, this.fCppFiles.size());
    if (fPlatformConf.getConnectionConf().isLocal()){
      monitor.subTask(Messages.CPPB_LocalCompilTaskName);
    } else {
      monitor.subTask(Messages.CPPB_RemoteCompilTaskName);
    }   
    boolean succeeded = true;
    try {
      final MessageConsole messageConsole = UIUtils.findOrCreateX10Console();
      final MessageConsoleStream mcStream = messageConsole.newMessageStream();

      for (final Map.Entry<String, String> entry : this.fCppFiles.entrySet()) {
        if (monitor.isCanceled()) {
          throw new OperationCanceledException();
        }
        final String file = entry.getValue();
        final String extension = file.substring(file.lastIndexOf('.'));
        final String objectFile = this.fTargetOpHelper.getTargetSystemPath(entry.getValue().replace(extension, O_EXT));

        final List<String> command = new ArrayList<String>();
        command.add(this.fTargetOpHelper.getTargetSystemPath(this.fPlatformConf.getCppCompilationConf().getCompiler()));
        command.addAll(X10BuilderUtils.getAllTokens(this.fPlatformConf.getCppCompilationConf().getCompilingOpts()));
        command.add(INCLUDE_OPT + this.fTargetOpHelper.getTargetSystemPath(this.fWorkspaceDir));
        for (final IPath projectDep: AbstractX10Builder.getProjectDependencies(fJavaProject)){
          command.add(INCLUDE_OPT + this.fTargetOpHelper.getTargetSystemPath(projectDep.toOSString()));
        }
        for (final String headerLoc : this.fPlatformConf.getCppCompilationConf().getX10HeadersLocations(this.fIsLocal)) {
          command.add(INCLUDE_OPT + this.fTargetOpHelper.getTargetSystemPath(headerLoc));
        }
        command.add("-c"); //$NON-NLS-1$
        command.add(this.fTargetOpHelper.getTargetSystemPath(entry.getValue()));
        command.add("-o"); //$NON-NLS-1$
        command.add(objectFile);
          
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

        
        monitor.worked(1);
        
        if (returnCode != 0) {
          succeeded = false;
          mcStream.println();
          CoreResourceUtils.addBuildMarkerTo(getProject(), NLS.bind(Messages.CPPB_CompilErrorMsg, getFileName(entry.getKey())), 
                                             IMarker.SEVERITY_ERROR, IMarker.PRIORITY_HIGH, entry.getKey());
        }
      }
    } catch (IOException except) {
      CoreResourceUtils.addBuildMarkerTo(getProject(), NLS.bind(Messages.CPPB_TargetOpError, this.fConfName), 
                                         IMarker.SEVERITY_ERROR, IMarker.PRIORITY_HIGH);
      LaunchCore.log(IStatus.ERROR, NLS.bind(Messages.CPPB_TargetOpError, this.fConfName), except);
      return false;
    } catch (InterruptedException except) {
      CoreResourceUtils.addBuildMarkerTo(getProject(), NLS.bind(Messages.CPPB_CancelOpMsg, this.fConfName), 
                                         IMarker.SEVERITY_WARNING, IMarker.PRIORITY_LOW);
      return false;
    } finally {
      monitor.done();
    }
    if (! succeeded) {
      UIUtils.showX10Console();
    }
    
    return succeeded;
  }
  
  public final void copyToOutputDir(final Collection<IFile> files, final SubMonitor monitor) throws CoreException {
    monitor.beginTask(null, files.size());
    final IFileStore workspaceDirStore = this.fTargetOpHelper.getStore(this.fWorkspaceDir);
    if (! workspaceDirStore.fetchInfo().exists()) {
      workspaceDirStore.mkdir(EFS.NONE, null);
    }
    for (final IFile file : files) {
      final IFileStore fileStore = EFS.getLocalFileSystem().getStore(URIUtils.getExpectedURI(file.getLocationURI()));
      final IFileStore destFile = workspaceDirStore.getChild(fileStore.getName());
      fileStore.copy(destFile, EFS.OVERWRITE, null);
      final String fileName = fileStore.getName();
      if (fileName.endsWith(CC_EXT) || fileName.endsWith(CPP_EXT) || fileName.endsWith(CXX_EXT)) {
        this.fCppFiles.put(fileStore.toLocalFile(EFS.NONE, null).getAbsolutePath(), destFile.toURI().getPath());
      }
    }
  }
  
  public final boolean hasAllPrerequisites() {
    return (this.fTargetOpHelper != null) && this.fPlatformConf.isComplete(true);
  }
  
  public final boolean hasValidCompilationCommands() {
    EValidationStatus validationStatus = this.fPlatformConf.getCppCompilationConf().getValidationStatus();
    return (validationStatus == EValidationStatus.VALID) ||
           (validationStatus == EValidationStatus.UNKNOWN) ||
           (validationStatus == null); // RMF 5/18 -- if "unknown" is ok, so should null
                                       // If we don't allow this, it causes problems during the build
                                       // that gets triggered when the user hits "Next" in the
                                       // "New X10 Project (C++ back-end)" wizard, and this basically
                                       // cripples the platform conf and subsequent builds.
  }

  // --- Code for descendants
  
  protected final void addCppFile(final String srcFile, final String destFilePath) {
    this.fCppFiles.put(srcFile, destFilePath);
  }

  protected final IProject getProject() {
    return this.fJavaProject.getProject();
  }
  
  protected final ITargetOpHelper getTargetOpHelper() {
    return this.fTargetOpHelper;
  }
  
  protected final String getWorkspaceDir() {
    return this.fWorkspaceDir;
  }
  
  // --- Private code
  
  private Collection<String> collectAllObjectFiles() {
    final Collection<String> result = new ArrayList<String>();
    for (final Collection<String> strings : this.fGeneratedFiles.values()) {
      for (final String file : strings) {
        if (file.endsWith(CC_EXT)) {
          final String oPath = removeExtension(file) + O_EXT;
          if (this.fTargetOpHelper.getStore(this.fWorkspaceDir + File.separator + oPath).fetchInfo().exists()) {
            result.add(oPath);
          }
        }
      }
    }
    return result;
  }
    
  private String removeExtension(String path){
	  int i = path.lastIndexOf('.');
	  return path.substring(0, i);
  }
  
  private void deleteFile(final IFileStore fileStore, final IProgressMonitor monitor) throws CoreException {
    if (fileStore.fetchInfo().exists()) {
      fileStore.delete(EFS.NONE, monitor);
    }
  }
  
  private String getFileName(final String filePath) {
    final int index = filePath.lastIndexOf(File.separatorChar);
    return filePath.substring(index + 1);
  }
  
  
  // --- Fields
  
  private final String fConfName;
  
  private final IJavaProject fJavaProject;
  
  private final String fWorkspaceDir;
  
  private final boolean fIsLocal;
  
  private final IX10PlatformConf fPlatformConf;
  
  private final ITargetOpHelper fTargetOpHelper;
  
  private final Map<String, String> fCppFiles = new HashMap<String, String>();
  
  private final Map<String, Collection<String>> fGeneratedFiles;
  
  private static final String INCLUDE_OPT = "-I"; //$NON-NLS-1$



}
