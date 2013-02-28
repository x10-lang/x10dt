/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.tests.services.pde.utils;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;

import x10dt.core.X10DTCorePlugin;
import x10dt.core.utils.X10DTCoreConstants;
import x10dt.tests.services.TestsServicesActivator;

/**
 * Utility methods to create X10 project(s) for PDE test cases.
 * 
 * @author egeay
 */
public final class ProjectUtils {
  
  /**
   * Copies some file content into the project source folder root and adds it to the Eclipse project model. 
   * 
   * @param project The project name to use.
   * @param bundle The bundle to consider for accessing the resources.
   * @param bundleEntryPath The path to use for collecting the resources within the bundle.
   * @throws CoreException Occurs if we could not create the project or some its structures.
   * @throws URISyntaxException May occur when accessing the data folder location.
   * @throws IOException Occurs if we could not copy the sources to the target destination.
   */
  public static void addFileToProject(final IProject project, final Bundle bundle, 
                                      final String bundleEntryPath) throws URISyntaxException, IOException, CoreException {
   final URL url = bundle.getEntry(bundleEntryPath);
   assertNotNull(NLS.bind("Could not find ''{0}'' for bundle ''{1}''", bundleEntryPath, bundle.getSymbolicName()), url); //$NON-NLS-1$
   final URL finalURL;
   if (TestsServicesActivator.getContext() == null) {
     finalURL = url;
   } else {
     finalURL = FileLocator.resolve(url);
   }
   final File dataFile = new File(finalURL.toURI());
   final Collection<File> files = new ArrayList<File>();
   if (dataFile.isDirectory()) {
     for (final File file : dataFile.listFiles()) {
       files.add(file);
     }
   } else {
     files.add(dataFile);
   }
   final File srcFolder = new File(project.getLocation().toFile(), "src"); //$NON-NLS-1$
   for (final File file : files) {
     if (file.isFile()) {
       final InputStream in = new FileInputStream(file);
       final OutputStream out = new FileOutputStream(new File(srcFolder, file.getName()));
       try {
         byte[] buf = new byte[1024];
         int len;
         while ((len = in.read(buf)) > 0) {
           out.write(buf, 0, len);
         }
       } finally {
         in.close();
         out.close();
       }
     }
   }
 }
  
  /**
   * Creates an X10 project with C++ back-end with the name provided.
   * 
   * @param name The project name to use.
   * @return A non-null {@link IProject} instance once it has been created.
   * @throws CoreException Occurs if we could not create the project or some its structures.
   */
  public static IProject createX10ProjectCppBackEnd(final String name) throws CoreException {
    return createX10Project(name, X10DTCorePlugin.X10_CPP_PRJ_NATURE_ID);
  }
  
  /**
   * Creates an X10 project with C++ back-end with the name provided and with the sources collected from a given data
   * directory.
   * 
   * @param name The project name to use.
   * @param bundle The bundle to consider for accessing the resources.
   * @param bundleEntryPath The path to use for collecting the resources within the bundle.
   * @return A non-null {@link IProject} instance once it has been created and populated.
   * @throws CoreException Occurs if we could not create the project or some its structures.
   * @throws URISyntaxException May occur when accessing the data folder location.
   * @throws IOException Occurs if we could not copy the sources to the target destination.
   */
  public static IProject createX10ProjectCppBackEnd(final String name, final Bundle bundle,
                                                    final String bundleEntryPath) throws CoreException, URISyntaxException, 
                                                                                         IOException {
    final IProject project = createX10Project(name, X10DTCorePlugin.X10_CPP_PRJ_NATURE_ID);
    addFileToProject(project, bundle, bundleEntryPath);
    return project;
  }
  
  /**
   * Creates an X10 project with Java back-end with the name provided.
   * 
   * @param name The project name to use.
   * @return A non-null {@link IProject} instance once it has been created.
   * @throws CoreException
   */
  public static IProject createX10ProjectJavaBackEnd(final String name) throws CoreException {
    return createX10Project(name, X10DTCorePlugin.X10_PRJ_JAVA_NATURE_ID);
  }
  
  /**
   * Creates an X10 project with Java back-end with the name provided and with the sources collected from a given data
   * directory.
   * 
   * @param name The project name to use.
   * @param bundle The bundle to consider for accessing the resources.
   * @param bundleEntryPath The path to use for collecting the resources within the bundle.
   * @return A non-null {@link IProject} instance once it has been created and populated.
   * @throws CoreException Occurs if we could not create the project or some its structures.
   * @throws URISyntaxException May occur when accessing the data folder location.
   * @throws IOException Occurs if we could not copy the sources to the target destination.
   */
  public static IProject createX10ProjectJavaBackEnd(final String name, final Bundle bundle,
                                                     final String bundleEntryPath) throws CoreException, URISyntaxException, 
                                                                                          IOException {
    final IProject project = createX10Project(name, X10DTCorePlugin.X10_PRJ_JAVA_NATURE_ID);
    addFileToProject(project, bundle, bundleEntryPath);
    return project;
  }
  
  // --- Private code
  
  private static IProject createX10Project(final String name, final String backEndNature) throws CoreException {
    final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(name);
    if (! project.exists()) {
      project.create(null);
      project.open(null);
      
      IProjectDescription description = project.getDescription();
      description.setNatureIds(new String[] {
        backEndNature, JavaCore.NATURE_ID                        
      });
      project.setDescription(description, null);
      
      final IPath srcPath = new Path('/' + name + "/src"); //$NON-NLS-1$
      final IPath binPath = new Path('/' + name + "/bin"); //$NON-NLS-1$
      final IFolder srcFolder = project.getFolder(new Path("src")); //$NON-NLS-1$
      srcFolder.create(false /* force */, true /* local */, null);
      final IFolder binFolder = project.getFolder(new Path("bin")); //$NON-NLS-1$
      binFolder.create(false /* force */, true /* local */, null);
      
      // We need to remove the Java builder.
      if (X10DTCorePlugin.X10_CPP_PRJ_NATURE_ID.equals(backEndNature)) {
        description = project.getDescription();
        final ICommand[] commands = description.getBuildSpec();
        final ICommand[] newCommands = new ICommand[commands.length - 1];
        for (int i = 0; i < commands.length; ++i) {
          if (JavaCore.BUILDER_ID.equals(commands[i].getBuilderName())) {
            System.arraycopy(commands, 0, newCommands, 0, i);
            System.arraycopy(commands, i + 1, newCommands, i, commands.length - i - 1);
            break;
          }
        }
        description.setBuildSpec(newCommands);
        
        project.setDescription(description, null);
      }
      
      final IJavaProject javaProject = JavaCore.create(project);
      
      final List<IClasspathEntry> entries = new ArrayList<IClasspathEntry>();
      entries.add(JavaCore.newSourceEntry(srcPath));
      entries.add(JavaCore.newContainerEntry(new Path(X10DTCoreConstants.X10_CONTAINER_ENTRY_ID)));
      javaProject.setRawClasspath(entries.toArray(new IClasspathEntry[entries.size()]), binPath, null);
    }
    return project;
  }

}
