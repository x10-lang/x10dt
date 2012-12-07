/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.java.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.window.IShellProvider;

import x10dt.core.X10DTCorePlugin;
import x10dt.ui.launch.core.actions.IBackEndX10ProjectConverter;

/**
 * Implementation of {@link IBackEndX10ProjectConverter} when converting an X10 project to use the Java back-end.
 * 
 * @author egeay
 */
public final class JavaBackEndProjectConverter implements IBackEndX10ProjectConverter {

  // --- Interface methods implementation
  
  public String getProjectNatureId() {
    return X10DTCorePlugin.X10_PRJ_JAVA_NATURE_ID;
  }
  
  public void postProjectSetup(final IShellProvider shellProvider, final IProject project) {
	  
  }

  public void preProjectSetup(final IShellProvider shellProvider, final IProject project) {
    // Nothing to do.
  }

}
