/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.java.launching;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.launching.AbstractJavaLaunchConfigurationDelegate;
import org.osgi.framework.Bundle;

import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.java.Activator;
import x10dt.ui.launch.java.Messages;


final class X10LocalLaunchConfigDelegate extends AbstractJavaLaunchConfigurationDelegate {

  // --- Interface methods implementation
  
  public void launch(final ILaunchConfiguration configuration, final String mode, final ILaunch launch, 
                     final IProgressMonitor monitor) throws CoreException {
    // Do nothing.
  }
  
  // --- Internal services
  
  File getX10DistHostLauncherDir() throws CoreException {
    final Bundle x10DistBundle = Platform.getBundle(Constants.X10_DIST_PLUGIN_ID);
    final URL url = x10DistBundle.getResource("launchers"); //$NON-NLS-1$
    try {
      return new File(FileLocator.resolve(url).getFile());
    } catch (IOException except) {
      throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.XLCD_NoLaunchersDir, except));
    }
  }
  
  File getX10DistHostLibDir() throws CoreException {
    final Bundle x10DistBundle = Platform.getBundle(Constants.X10_DIST_PLUGIN_ID);
    final URL url = x10DistBundle.getResource("lib"); //$NON-NLS-1$
    try {
      return new File(FileLocator.resolve(url).getFile());
    } catch (IOException except) {
      throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.XLCD_NoLibDir, except));
    }
  }
  
  // --- Overridden methods
  
  public String[] getClasspath(final ILaunchConfiguration configuration) throws CoreException {
    final List<String> cp = new ArrayList<String>();
        
    final File libDir = getX10DistHostLibDir();
    File commonMathFile = null;
    for (final File file : libDir.listFiles()) {
      if (file.getName().startsWith("commons-math-") && file.getName().endsWith(Constants.JAR_EXT)) { //$NON-NLS-1$
        commonMathFile = file;
        break;
      }
    }
    if (commonMathFile == null) {
      throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.XLCD_NoCommonMath));
    }
    cp.add(commonMathFile.getAbsolutePath());
    
    for (final String element : super.getClasspath(configuration)) {
      cp.add(element);
    }
    return cp.toArray(new String[cp.size()]);
  }

}
