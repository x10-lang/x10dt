/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.actions;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.window.IShellProvider;

import x10dt.core.X10DTCorePlugin;
import x10dt.ui.launch.core.actions.IBackEndX10ProjectConverter;

/**
 * Implementation of {@link IBackEndX10ProjectConverter} when converting an X10 project to use the C++ back-end.
 * 
 * @author egeay
 */
public final class CppBackEndProjectConverter implements IBackEndX10ProjectConverter {

  // --- Interface methods implementation
  
  public String getProjectNatureId() {
    return X10DTCorePlugin.X10_CPP_PRJ_NATURE_ID;
  }
  
  public void postProjectSetup(final IShellProvider shellProvider, final IProject project) throws CoreException {
    final IProjectDescription description = project.getDescription();
    final ICommand[] commands = description.getBuildSpec();
    final ICommand[] newCommands = new ICommand[commands.length -2];
    System.arraycopy(commands, 0, newCommands, 0, commands.length-2);
    description.setBuildSpec(newCommands);
    project.setDescription(description, new NullProgressMonitor());
  }

  public void preProjectSetup(final IShellProvider shellProvider, final IProject project) {
  }

}
