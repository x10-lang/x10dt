/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.builder;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;

import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.builder.target_op.IX10BuilderFileOp;
import x10dt.ui.launch.cpp.builder.target_op.ITargetOpHelper;
import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConf;


final class LocalX10BuilderFileOp extends AbstractX10BuilderOp implements IX10BuilderFileOp {

  LocalX10BuilderFileOp(final IJavaProject javaProject, final String workspaceDir, 
                        final IX10PlatformConf platformConf, Map<String, Collection<String>> generatedFiles) throws CoreException {
    super(platformConf, javaProject, workspaceDir, generatedFiles);
  }
  
  LocalX10BuilderFileOp(final IJavaProject javaProject, final String workspaceDir, final IX10PlatformConf platformConf,
                        final ITargetOpHelper targetOpHelper, Map<String, Collection<String>> generatedFiles) {
    super(javaProject, workspaceDir, platformConf, targetOpHelper, generatedFiles);
  }
  
  // --- Interface methods implementation

  public void transfer(final Map<IPath, Collection<File>> files, final IProgressMonitor monitor) throws CoreException {
    for (final Collection<File> collection : files.values()) {
      for (final File file : collection) {
        if (file.getAbsolutePath().endsWith(Constants.CC_EXT)) {
          addCppFile(file.getAbsolutePath(), file.getAbsolutePath());
        }
      }
    }
  }
  
}
