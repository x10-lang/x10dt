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
	restorePlatformConf(project);  
    final IFile platformConfFile = X10PlatformConfFactory.getFile(project);
    if (! EFS.getLocalFileSystem().getStore(URIUtils.getExpectedURI(platformConfFile.getLocationURI())).fetchInfo().exists()) {
      final IX10PlatformConf platformConf = X10PlatformConfFactory.loadOrCreate(platformConfFile);
      final IX10PlatformConfWorkCopy platformConfWorkCopy = platformConf.createWorkingCopy();
      platformConfWorkCopy.initializeToDefaultValues(project);
      platformConfWorkCopy.applyChanges();

      try {
        X10PlatformConfFactory.save(platformConfFile, platformConfWorkCopy);
      } catch (CoreException except) {
        DialogsFactory.createErrorBuilder().setDetailedMessage(except.getStatus())
                      .createAndOpen(shellProvider, LaunchMessages.CBEPC_PlatformConfSavingErrTitle, 
                                     LaunchMessages.CBEPC_PlatformConfSavingErrMsg);
      }
    }
    
    final IJavaProject javaProject = JavaCore.create(project);
    final Collection<IClasspathEntry> cpEntries = new ArrayList<IClasspathEntry>();
    try {
      boolean foundEntry = false;
      for (final IClasspathEntry cpEntry : javaProject.getRawClasspath()) {
        if (JavaRuntime.JRE_CONTAINER.equals(cpEntry.getPath().toString())) {
          foundEntry = true;
        } else {
          cpEntries.add(cpEntry);
        }
      }
      if (foundEntry) {
        javaProject.setRawClasspath(cpEntries.toArray(new IClasspathEntry[cpEntries.size()]), new NullProgressMonitor());
      }
    } catch (JavaModelException except) {
      CppLaunchCore.log(except.getStatus());
    }
  }

  public void preProjectSetup(final IShellProvider shellProvider, final IProject project) {
  }
  
  /**
   * If the Java project had a platform conf as a dot file, restore it back.
   */
  private void restorePlatformConf(IProject project){
	  IFile hiddenFile = project.getFile("." + X10PlatformConfFactory.X10_PLATFORM_CONF_FILE);
	  if (EFS.getLocalFileSystem().getStore(URIUtils.getExpectedURI(hiddenFile.getLocationURI())).fetchInfo().exists()) {
		  IFile platformConfFile = ResourcesPlugin.getWorkspace().getRoot().getFile(hiddenFile.getFullPath().removeLastSegments(1).append(new Path(X10PlatformConfFactory.X10_PLATFORM_CONF_FILE)));
		  IFileStore platformConfStore = EFS.getLocalFileSystem().getStore(URIUtils.getExpectedURI(platformConfFile.getLocationURI()));
			try {
				EFS.getLocalFileSystem().getStore(URIUtils.getExpectedURI(hiddenFile.getLocationURI())).move(platformConfStore, EFS.OVERWRITE, new NullProgressMonitor());
			} catch (CoreException e) {
				//TODO
			}
	  }
	  
  }

}
