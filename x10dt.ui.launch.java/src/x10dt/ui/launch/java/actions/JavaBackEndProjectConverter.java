/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.java.actions;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
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
  
  public void postProjectSetup(final IShellProvider shellProvider, final IProject project) throws CoreException {
	    // -- create src-java and bin-java
	    String srcJava = "src-java";
	    String binJava = "bin-java";
	    String x10GenSrc = "x10-gen-src";
	    IFolder srcFolder = project.getFolder(srcJava);
	    IFolder binFolder = project.getFolder(binJava);
	    IFolder x10genFolder = project.getFolder(x10GenSrc);
	    if (!srcFolder.exists() || !binFolder.exists() || !x10genFolder.exists()){
	    	srcFolder.create(IResource.FORCE, true, new NullProgressMonitor());
	    	binFolder.create(IResource.FORCE, true,  new NullProgressMonitor());
	    	x10genFolder.create(IResource.FORCE, true,  new NullProgressMonitor());
	    
	    	// -- add src-java to classpath
	    	IClasspathEntry javasrc = JavaCore.newSourceEntry(project.getFolder(srcJava).getFullPath(), new IPath[0], project.getFolder(binJava).getFullPath());
	    	IClasspathEntry x10gen = JavaCore.newSourceEntry(project.getFolder(x10GenSrc).getFullPath());
	    	IJavaProject javaProject = JavaCore.create(project);
	    	IClasspathEntry[] entries = javaProject.getRawClasspath();
	    	IClasspathEntry[] newEntries = new IClasspathEntry[entries.length + 2];
	    	System.arraycopy(entries, 0, newEntries, 0, entries.length);
	    	newEntries[newEntries.length - 1] = javasrc;
	    	newEntries[newEntries.length - 2] = x10gen;
	    	javaProject.setRawClasspath(newEntries, new NullProgressMonitor());
	    	}
  	}

  public void preProjectSetup(final IShellProvider shellProvider, final IProject project) {
    // Nothing to do.
  }

}
