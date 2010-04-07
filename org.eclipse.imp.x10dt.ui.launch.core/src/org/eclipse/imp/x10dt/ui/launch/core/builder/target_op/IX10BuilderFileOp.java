/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package org.eclipse.imp.x10dt.ui.launch.core.builder.target_op;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Provides file operations useful for X10 builder regarding the X10 generated files.
 * 
 * <p>One should call {@link #hasAllPrerequisites()} prior to using the other operations/services provided with this interface.
 * 
 * @author egeay
 */
public interface IX10BuilderFileOp {
 
  /**
   * Archives the compiled generated files into a library.
   * 
   * @param monitor The monitor to consider for reporting progress or canceling the operation.
   * @throws CoreException Occurs if we could not transfer remotely some generated files.
   */
  public void archive(final IProgressMonitor monitor) throws CoreException;
  
  /**
   * Compiles the generated files on the target platform.
   * 
   * @param monitor The monitor to consider for reporting progress or canceling the operation.
   * @throws CoreException Occurs if we could not add some X10 markers to Problems View.
   * @return True if the compilation succeeded, false otherwise. In the latter, marker will be created and error will be 
   * logged.
   */
  public boolean compile(final IProgressMonitor monitor) throws CoreException;
  
  /**
   * Returns the helper class allowing file and process management independently of the connection type.
   * 
   * @return A non-null object.
   */
  public ITargetOpHelper getTargetOpHelper();
  
  /**
   * Returns the directory for the target machine where files can be generated and compiled.
   * 
   * @return A non-null directory path.
   */
  public String getWorkspaceDir();
  
  /**
   * Returns if yes or no we have all prerequisites in order to satisfy the operations provided with this interface.
   * Such method should be called before starting to use the other services.
   * 
   * @return True if we have all prerequisites. If we don't, calling the other operations is unsafe and unsupported.
   */
  public boolean hasAllPrerequisites();
  
  /**
   * Transfers (if necessary) the generated files from the local output directory to the workspace directory of 
   * the target platform.
   * 
   * @param localOutputDir The local directory containing the generated files.
   * @param monitor The monitor to consider for reporting progress or canceling the operation.
   * @throws CoreException Occurs if we could not add some X10 markers to Problems View.
   */
  public void transfer(final String localOutputDir, final IProgressMonitor monitor) throws CoreException;

}
