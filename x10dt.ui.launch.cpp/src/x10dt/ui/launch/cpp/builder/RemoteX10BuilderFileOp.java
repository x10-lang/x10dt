/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.builder;

import static x10dt.ui.launch.core.Constants.CC_EXT;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jdt.core.IJavaProject;

import x10dt.core.utils.ICountableIterable;
import x10dt.ui.launch.core.Messages;
import x10dt.ui.launch.core.builder.target_op.IX10BuilderFileOp;
import x10dt.ui.launch.core.platform_conf.ETargetOS;
import x10dt.ui.launch.core.utils.CoreResourceUtils;
import x10dt.ui.launch.core.utils.ProjectUtils;
import x10dt.ui.launch.cpp.LaunchMessages;
import x10dt.ui.launch.cpp.builder.target_op.ITargetOpHelper;
import x10dt.ui.launch.cpp.builder.target_op.TargetOpHelperFactory;
import x10dt.ui.launch.cpp.launching.ConfUtils;


final class RemoteX10BuilderFileOp extends AbstractX10BuilderOp implements IX10BuilderFileOp {
  
  RemoteX10BuilderFileOp(final IJavaProject javaProject, 
                         final Map<String, Collection<String>> generatedFiles) throws CoreException {
    super(javaProject, ConfUtils.getRemoteOutputFolder(ConfUtils.getConfiguration(javaProject.getProject().getName())), generatedFiles);
    this.fTargetOS = ConfUtils.getTargetOS(ConfUtils.getConfiguration(javaProject.getProject().getName()));
    
    final ITargetOpHelper localTargetOpHelper = TargetOpHelperFactory.create(true, false, null);
    this.fLocalX10BuilderOp = new LocalX10BuilderFileOp(javaProject, 
                                                        ProjectUtils.getProjectOutputDirPath(javaProject.getProject()), 
                                                        localTargetOpHelper, generatedFiles);
  }

  // --- Interface methods implementation

  public void transfer(final Map<IPath,Collection<File>> files, final IProgressMonitor monitor) throws CoreException {
    try {
      final IFileStore fileStore = getTargetOpHelper().getStore(getWorkspaceDir());
      monitor.subTask(Messages.CPPB_TransferTaskName);
      copyGeneratedFiles(fileStore, files, monitor);
    } catch (CoreException except) {
      CoreResourceUtils.addBuildMarkerTo(getProject(), LaunchMessages.RXBFO_TransferFailureMsg, IMarker.SEVERITY_ERROR, 
                                         IMarker.PRIORITY_HIGH);
      throw except;
    }
  }
  
  // --- Overridden methods
  
  public void cleanFiles(final ICountableIterable<IFile> files, final SubMonitor monitor) throws CoreException {
    monitor.beginTask(null, 2);
    this.fLocalX10BuilderOp.cleanFiles(files, monitor.newChild(1));
    super.cleanFiles(files, monitor.newChild(1));
  }
  
  // --- Private code
  
  private void copyGeneratedFiles(final IFileStore destDir, final Map<IPath,Collection<File>> files, 
                                  final IProgressMonitor monitor) throws CoreException {
    if (! destDir.fetchInfo().exists()) {
      destDir.mkdir(EFS.NONE, null);
    }
    final IFileSystem localFileSystem = EFS.getLocalFileSystem();
    monitor.beginTask(null, files.size());
    for (final Map.Entry<IPath, Collection<File>> entry : files.entrySet()) {
      for (final File file : entry.getValue()) {
        final String filePath = file.getAbsolutePath();
        final String destPath = copyGeneratedFile(destDir, localFileSystem, entry.getKey(), filePath);
        if (filePath.endsWith(CC_EXT)) {
          addCppFile(filePath, destPath);
        }
        monitor.worked(1);
      }
    }
  }

  private String copyGeneratedFile(final IFileStore destDir, final IFileSystem localFileSystem, 
                                   final IPath pkgPath, final String filePath) throws CoreException {
    final IFileStore fileStore = localFileSystem.getStore(new Path(filePath));
    final String name = fileStore.getName();
    final IFileStore parentDir = destDir.getFileStore(pkgPath);
    if (! parentDir.fetchInfo().exists()) {
      parentDir.mkdir(EFS.NONE, null);
    }
    final IFileStore destFile = parentDir.getChild(name);
    fileStore.copy(destFile, EFS.OVERWRITE, null);
    final String destPath = destFile.toURI().getPath();
    if (this.fTargetOS == ETargetOS.WINDOWS && destPath.startsWith("/")) { //$NON-NLS-1$
      return destPath.substring(1);
    } else {
      return destPath;
    }
  }
  
  // --- Fields
  
  private final ETargetOS fTargetOS;
  
  private final LocalX10BuilderFileOp fLocalX10BuilderOp;
  
}
