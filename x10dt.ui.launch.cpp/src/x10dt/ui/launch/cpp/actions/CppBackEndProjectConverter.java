/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.actions;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jface.window.IShellProvider;

import x10dt.core.X10DTCorePlugin;
import x10dt.core.utils.URIUtils;
import x10dt.ui.launch.core.actions.IBackEndX10ProjectConverter;
import x10dt.ui.launch.core.dialogs.DialogsFactory;
import x10dt.ui.launch.cpp.CppLaunchCore;
import x10dt.ui.launch.cpp.LaunchMessages;
import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConf;
import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConfWorkCopy;
import x10dt.ui.launch.cpp.platform_conf.X10PlatformConfFactory;

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
  
  public void postProjectSetup(final IShellProvider shellProvider, final IProject project) {
  
  }

  public void preProjectSetup(final IShellProvider shellProvider, final IProject project) {
  }

}
