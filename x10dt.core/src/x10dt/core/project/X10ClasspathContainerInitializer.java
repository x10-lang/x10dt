/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.core.project;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.ClasspathContainerInitializer;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.osgi.util.NLS;

import x10dt.core.Messages;
import x10dt.core.X10DTCorePlugin;
import x10dt.core.utils.X10BundleUtils;
import x10dt.core.utils.X10DTCoreConstants;

/**
 * Defines the initializer for the X10 Runtime Classpath Container.
 * 
 * @author egeay
 */
public final class X10ClasspathContainerInitializer extends ClasspathContainerInitializer {

  // --- Abstract methods implementation
  
  public void initialize(final IPath containerPath, final IJavaProject project) throws CoreException {
    if (X10DTCoreConstants.X10_CONTAINER_ENTRY_ID.equals(containerPath.toString())) {
      JavaCore.setClasspathContainer(containerPath, new IJavaProject[] { project }, new IClasspathContainer[] { 
                                     new X10Container(containerPath, newResolveClassPathEntries(project)) },
                                     null /* monitor */);
    } else {
      throw new CoreException(new Status(IStatus.ERROR, X10DTCorePlugin.kPluginID, 
                                         NLS.bind(Messages.XCCI_WrongClassPathContainer, containerPath.toString())));
    }
  }
  
  // --- Private code
  
  private IClasspathEntry[] newResolveClassPathEntries(final IJavaProject project) throws CoreException {
    final List<IClasspathEntry> cpEntries = new ArrayList<IClasspathEntry>();
    final URL x10RuntimeURL = X10BundleUtils.getX10RuntimeURL();
    if ((x10RuntimeURL == null) || !X10BundleUtils.isDeployedX10Runtime(x10RuntimeURL)) {
      if (x10RuntimeURL == null) {
        final IMarker marker = project.getProject().createMarker(X10DTCorePlugin.kPluginID + ".classpathMarker"); //$NON-NLS-1$
        marker.setAttribute(IMarker.MESSAGE, Messages.XCCI_NoX10JARFound);
        marker.setAttribute(IMarker.LOCATION, project.getProject().getLocation().toString());
        marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
        marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
      } else {
        addClasspathEntry(cpEntries, x10RuntimeURL);
      }
    } else {
      // We're in "deployed mode", so add the bundles for x10.runtime, x10.network, x10.common and x10.constraints
      addClasspathEntry(cpEntries, x10RuntimeURL);
      // addClasspathEntry(cpEntries, X10BundleUtils.getX10NetworkURL());
      addClasspathEntry(cpEntries, X10BundleUtils.getX10CommonURL());
      addClasspathEntry(cpEntries, X10BundleUtils.getX10ConstraintsURL());
    }
    return cpEntries.toArray(new IClasspathEntry[cpEntries.size()]);
  }

  private void addClasspathEntry(final List<IClasspathEntry> cpEntries, final URL wURL) throws CoreException {
    IPath path;
    try {
      final URL url = FileLocator.resolve(wURL);
      if (url.getProtocol().equals("jar")) { //$NON-NLS-1$
        final JarURLConnection jarConnection = (JarURLConnection) url.openConnection();
        path = new Path(jarConnection.getJarFileURL().getFile());
      } else {
        path = new Path(url.getFile());
      }
    } catch (IOException except) {
      throw new CoreException(new Status(IStatus.ERROR, X10DTCorePlugin.kPluginID, 
                                         Messages.XCCI_ClasspathResIOError, except));
    }
    cpEntries.add(JavaCore.newLibraryEntry(path, null /* sourceAttachmentPath */, null /* sourceAttachmentRootPath */));
  }

}
