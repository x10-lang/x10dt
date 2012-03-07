/*****************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                       *
 * All rights reserved. This program and the accompanying materials          *
 * are made available under the terms of the Eclipse Public License v1.0     *
 * which accompanies this distribution, and is available at                  *
 * http://www.eclipse.org/legal/epl-v10.html                                 *
 *****************************************************************************/
package x10dt.ui.launch.core.utils;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.compiler.env.AccessRuleSet;
import org.eclipse.jdt.internal.core.ClasspathEntry;
import org.eclipse.jdt.internal.core.JavaModel;
import org.eclipse.jdt.internal.core.builder.ClasspathJar;
import org.eclipse.jdt.internal.core.builder.ClasspathLocation;
import org.eclipse.osgi.util.NLS;

import x10dt.core.X10DTCorePlugin;
import x10dt.core.utils.IFilter;
import x10dt.core.utils.URIUtils;
import x10dt.ui.X10DTUIPlugin;
import x10dt.ui.launch.core.LaunchCore;
import x10dt.ui.launch.core.Messages;

/**
 * Utility methods for JDT {@link IJavaProject} interface.
 *
 * @author egeay
 */
public final class ProjectUtils {
  
  /**
   * Returns the set of projects on this project's classpath.
   */
  public static Collection<IProject> getDependentProjects(IJavaProject project) throws JavaModelException {
    Collection<IProject> result = new ArrayList<IProject>();
    final IWorkspaceRoot root = project.getResource().getWorkspace().getRoot();
    for (final IClasspathEntry cpEntry : project.getResolvedClasspath(true)) {
      if (cpEntry.getEntryKind() == IClasspathEntry.CPE_PROJECT) {
        final IResource resource = root.findMember(cpEntry.getPath());
        if (resource == null) {
          LaunchCore.log(IStatus.WARNING, NLS.bind(Messages.JPU_ResourceErrorMsg, cpEntry.getPath()));
        } else {
          result.add((IProject) resource);
        }
      }
    }
    return result;
  }
  
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
                                                final IFilter<IPath> libFilter, StringBuffer pathBuffer) throws JavaModelException {
    final Set<T> container = new HashSet<T>();
    final IWorkspaceRoot root = jProject.getResource().getWorkspace().getRoot();
    for (final IClasspathEntry cpEntry : jProject.getResolvedClasspath(true)) {
      collectCpEntries(container, cpEntry, root, libFilter, cpEntryFunctor, jProject.getProject(), pathBuffer);
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
    final URI outputFolderURI = URIUtils.getExpectedURI(root.getFolder(javaProject.getOutputLocation()).getLocationURI());
    return EFS.getStore(outputFolderURI).toLocalFile(EFS.NONE, new NullProgressMonitor()).getAbsolutePath();
  }
  
  /**
   * Returns the collection of source folders for a given project.
   * 
   * @param project The project of interest.
   * @return A non-null collection of workspace-relative strings representing the src folders of the project.
   * @throws JavaModelException Occurs if we could not resolve the project class path.
   */
  public static Collection<String> collectSourceFolders(final IJavaProject project) throws JavaModelException {
    final Collection<String> result = new ArrayList<String>();
    for (final IClasspathEntry cpEntry : project.getResolvedClasspath(true)) {
      if (cpEntry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
        final IPath entryPath = cpEntry.getPath();
        if (! entryPath.segment(0).equals(project.getElementName())) {
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
                                           final IFunctor<IPath, T> functor,
                                           final IProject project,
                                           StringBuffer pathBuffer) throws JavaModelException {
    final IJavaProject javaProject = JavaCore.create(project);
    switch (cpEntry.getEntryKind()) {
      case IClasspathEntry.CPE_SOURCE:
        if(isX10Project(javaProject)) {
          IPath outputPath = getOutputLocation(cpEntry, javaProject);
          if(outputPath.lastSegment().equals("bin-java")) {
            appendNew(functor, container, pathBuffer, makeAbsolutePath(root, outputPath));

          } else {
            appendNew(functor, container, pathBuffer, makeAbsolutePath(root, cpEntry.getPath()));
          }
        } else {
          appendNew(functor, container, pathBuffer, makeAbsolutePath(root, getOutputLocation(cpEntry, javaProject)));
        }
        break;

      case IClasspathEntry.CPE_LIBRARY:
        IPath path = cpEntry.getPath();
        if (libFilter.accepts(path)) {
          IPath absolutePath = makeAbsolutePath(root, path);
          appendNew(functor, container, pathBuffer, absolutePath);
        }
        break;

      case IClasspathEntry.CPE_PROJECT:
        final IResource resource = root.findMember(cpEntry.getPath());
        if (resource == null) {
          LaunchCore.log(IStatus.WARNING, NLS.bind(Messages.JPU_ResourceErrorMsg, cpEntry.getPath()));
        } else {
          final IJavaProject refProject = JavaCore.create((IProject) resource);
          for (final IClasspathEntry newCPEntry : refProject.getResolvedClasspath(true)) {
            collectCpEntries(container, newCPEntry, root, libFilter, functor, (IProject) resource, pathBuffer);
          }
        }
        break;
        
      default:
        throw new IllegalArgumentException(NLS.bind(Messages.JPU_UnexpectedEntryKindMsg, cpEntry.getEntryKind()));
    }
  }
  
  private static boolean isX10Project(IJavaProject project) {
    try {
        return project.getProject().hasNature(X10DTCorePlugin.X10_CPP_PRJ_NATURE_ID) ||
               project.getProject().hasNature(X10DTCorePlugin.X10_PRJ_JAVA_NATURE_ID);
    } catch (CoreException e) {
        X10DTUIPlugin.log(e);
        return false;
    }
}
  
  private static IPath getOutputLocation(IClasspathEntry cpEntry, IJavaProject project) throws JavaModelException {
    IPath specificPath = cpEntry.getOutputLocation();
    if(specificPath != null) {
        return specificPath;
    } else {
        return project.getOutputLocation();
    }
}
  
  private static <T> void appendNew(final IFunctor<IPath, T> functor, Set<T> container, StringBuffer pathBuffer, IPath path) {
    final T pathT = functor.apply(path);
    if(! container.contains(pathT)) {
      container.add(pathT);
      if(pathBuffer.length() > 0) {
        pathBuffer.append(File.pathSeparatorChar);
      }
      pathBuffer.append(path.toOSString());
    }
  }
  private static IPath makeAbsolutePath(final IWorkspaceRoot root, IPath path) {
    Object target = JavaModel.getTarget(path, true);

    if (target instanceof IResource) {
      return ((IResource) target).getLocation();
    } else if(path.isAbsolute()) {
      return path;
    } else {
      return root.getLocation().append(path);
    }
  }
  
}
