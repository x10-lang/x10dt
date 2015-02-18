/*******************************************************************************
 * Copyright (c) 2008 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Robert Fuhrer (rfuhrer@watson.ibm.com) - initial API and implementation

 *******************************************************************************/

/*
 * (C) Copyright IBM Corporation 2007
 * 
 * This file is part of the Eclipse IMP.
 */
/*
 * Created on Feb 6, 2006
 */
package x10dt.ui.launch.java.wizards;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.imp.builder.ProjectNatureBase;
import org.eclipse.imp.java.hosted.wizards.NewProjectWizardSecondPage;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;

import x10dt.core.utils.X10DTCoreConstants;
import x10dt.ui.builder.java.Activator;
import x10dt.ui.builder.java.Messages;
import x10dt.ui.launch.java.nature.X10ProjectNature;
import x10dt.ui.utils.WizardUtils;

public class X10ProjectWizardSecondPage extends NewProjectWizardSecondPage {

  public X10ProjectWizardSecondPage(X10ProjectWizardFirstPage firstPage) {
    super(firstPage);
  }

  protected ProjectNatureBase getProjectNature() {
    return new X10ProjectNature();
  }

  @Override
  protected List<IClasspathEntry> createLanguageRuntimeEntries() {
    return Arrays.asList(JavaCore.newContainerEntry(new Path(X10DTCoreConstants.X10_CONTAINER_ENTRY_ID)));
  }

  /**
   * The purpose of this override is to add an extra check on the classpath,
   * to make sure that it has an explicit source folder entry. Unfortunately,
   * there does not appear to be enough API exposed on the base class to implement
   * this check in the normal manner.
   */
  protected void updateStatus(IStatus status) {
    if (status.isOK()) {
      if (!checkClasspath()) {
        super.updateStatus(new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.X10ProjectWizardSecondPage_noSourceEntry));
        return;
      }
    }
    super.updateStatus(status);
  }

  private boolean checkClasspath() {
    IClasspathEntry[] cpEntries= getRawClassPath();
    for(IClasspathEntry entry: cpEntries) {
        if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
            return true;
        }
    }
    return false;
  }

  @Override
  public void performFinish(IProgressMonitor monitor) throws CoreException, InterruptedException {
    final X10ProjectWizardFirstPage firstPage = (X10ProjectWizardFirstPage) this.getPreviousPage();
    final IProject project = firstPage.getProjectHandle();

    super.performFinish(monitor);

    // -- create src-java and bin-java
    String srcJava = "src-java";
    String binJava = "bin-java";
    String x10GenSrc = "x10-gen-src";
    IFolder srcFolder = project.getFolder(srcJava);
    IFolder binFolder = project.getFolder(binJava);
    IFolder x10genFolder = project.getFolder(x10GenSrc);
    srcFolder.create(IResource.FORCE, true, monitor);
    binFolder.create(IResource.FORCE, true,  monitor);
    x10genFolder.create(IResource.FORCE, true,  monitor);
    
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
    
    
    // generate sample "Hello World" X10 application
    if (firstPage.isGenHello()) {
      ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {
        public void run(IProgressMonitor monitor) throws CoreException {
          IFile newFile = project.getFile("src/Hello.x10"); //$NON-NLS-1$
          IFolder srcFolder = project.getFolder("src"); //$NON-NLS-1$
          IJavaProject javaProject = JavaCore.create(project);
          IPackageFragmentRoot pkgFragRoot = javaProject.getPackageFragmentRoot(srcFolder);
          IPackageFragment pkgFrag = pkgFragRoot.getPackageFragment(""); //$NON-NLS-1$

          InputStream sourceInputStream = WizardUtils.createSampleContentStream(pkgFrag.getElementName(), "Hello"); //$NON-NLS-1$
          newFile.create(sourceInputStream, IResource.FORCE | IResource.KEEP_HISTORY, monitor);

          ((X10ProjectWizard) X10ProjectWizardSecondPage.this.getWizard()).selectAndReveal(newFile);
          openResource(newFile);
        }
      }, monitor);
    }
  }

}
