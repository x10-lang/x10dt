/*****************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                       *
 * All rights reserved. This program and the accompanying materials          *
 * are made available under the terms of the Eclipse Public License v1.0     *
 * which accompanies this distribution, and is available at                  *
 * http://www.eclipse.org/legal/epl-v10.html                                 *
 *****************************************************************************/
package org.eclipse.imp.x10dt.ui.launch.core.utils;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.imp.x10dt.ui.launch.core.LaunchCore;
import org.eclipse.imp.x10dt.ui.launch.core.Messages;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.osgi.util.NLS;

/**
 * Utility methods for JDT {@link IJavaProject} interface.
 *
 * @author egeay
 */
public final class ProjectUtils {
  
  /**
   * Returns a filtered set of class path entries for a given Java project.
   * 
   * @param <T> The type of the class path entries once transformed via the functor provided.
   * @param jProject The Java project to consider.
   * @param cpEntryFunctor The functor to use to transform an {@link IPath} related to a class path
   * entry into another type of interest.
   * @param libFilter The filter to user in order to filter the library entries.
   * @return A non-null, possibly empty, set of class path entries.
   * @throws JavaModelException Occurs if we could not resolve the class path entries.
   * @throws IllegalArgumentException Occurs if a class path entry kind is not one of the expected
   * list. More precisely, CPE_VARIABLE and CPE_CONTAINER should not be encountered.
   */
  public static <T> Set<T> getFilteredCpEntries(final IJavaProject jProject, final IFunctor<IPath, T> cpEntryFunctor,
                                                final IFilter<IPath> libFilter) throws JavaModelException {
    final Set<T> container = new HashSet<T>();
    final IWorkspaceRoot root = jProject.getResource().getWorkspace().getRoot();
    for (final IClasspathEntry cpEntry : jProject.getResolvedClasspath(true)) {
      collectCpEntries(container, cpEntry, root, libFilter, cpEntryFunctor);
    }
    return container;
  }
  
  /**
   * Returns the path to the project main output directory.
   * 
   * @param project The project of interest.
   * @return A non-null string identifying the project output directory.
   * @throws CoreException Occurs if we could not access the output directory for the project transmitted.
   */
  public static String getProjectOutputDirPath(final IProject project) throws CoreException {
    final IJavaProject javaProject = JavaCore.create(project);
    final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
    final URI outputFolderURI = root.getFolder(javaProject.getOutputLocation()).getLocationURI();
    return EFS.getStore(outputFolderURI).toLocalFile(EFS.NONE, new NullProgressMonitor()).getAbsolutePath();
  }
  
  /**
   * Returns the collection of source folders for a given project.
   * 
   * @param project The project of interest.
   * @return A non-null collection of workspace-relative strings representing the src folders of the project.
   * @throws JavaModelException
   */
  public static Collection<String> collectSourceFolders(final IJavaProject project) throws JavaModelException {
      Collection<String> result = new ArrayList<String>();
      IClasspathEntry[] cpEntries= project.getResolvedClasspath(true);
      for(int i= 0; i < cpEntries.length; i++) {
          IClasspathEntry cpEntry= cpEntries[i];
          if (cpEntry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
              final IPath entryPath= cpEntry.getPath();
              if (!entryPath.segment(0).equals(project.getElementName())) {
                  continue;
              }
              result.add(entryPath.toOSString());
          }
      }
      return result;
  }
  
  // --- Private code
  
  private ProjectUtils() {}
  
  private static <T> void collectCpEntries(final Set<T> container, final IClasspathEntry cpEntry, final IWorkspaceRoot root, 
                                           final IFilter<IPath> libFilter, 
                                           final IFunctor<IPath, T> functor) throws JavaModelException {
    switch (cpEntry.getEntryKind()) {
      case IClasspathEntry.CPE_SOURCE:
        container.add(functor.apply(getAbsolutePath(root, cpEntry.getPath())));
        break;
        
      case IClasspathEntry.CPE_LIBRARY:
        if (libFilter.accepts(cpEntry.getPath())) {
          container.add(functor.apply(cpEntry.getPath()));
        }
        break;
      
      case IClasspathEntry.CPE_PROJECT:
        final IResource resource = root.findMember(cpEntry.getPath());
        if (resource == null) {
          LaunchCore.log(IStatus.WARNING, NLS.bind(Messages.JPU_ResourceErrorMsg, cpEntry.getPath()));
        } else {
          final IJavaProject refProject = JavaCore.create((IProject) resource);
          for (final IClasspathEntry newCPEntry : refProject.getResolvedClasspath(true)) {
            collectCpEntries(container, newCPEntry, root, libFilter, functor);
          }
        }
        break;
        
      default:
        throw new IllegalArgumentException(NLS.bind(Messages.JPU_UnexpectedEntryKindMsg, cpEntry.getEntryKind()));
    }
  }
  
  private static IPath getAbsolutePath(final IWorkspaceRoot root, final IPath path) {
    if (path.isRoot()) {
      return path;
    } else {
      return root.getLocation().append(path);
    }
  }
  
}
