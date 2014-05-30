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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.launching.AbstractJavaLaunchConfigurationDelegate;

import x10dt.core.utils.X10BundleUtils;
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
  
  File getX10DistHostLauncherDir(final String fileName) throws CoreException {
    try {
      return new File(X10BundleUtils.getX10DistHostResource(fileName).getFile());
    } catch (IOException except) {
      throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.XLCD_NoLaunchersDir, except));
    }
  }
  
  File getX10DistHostLibDir() throws CoreException {
    try {
      return new File(X10BundleUtils.getX10DistHostResource("lib").getFile()); //$NON-NLS-1$
    } catch (IOException except) {
      throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.XLCD_NoLibDir, except));
    }
  }
  
  public File getX10SourceLocation(final ILaunchConfiguration configuration) throws CoreException {
	  final File libDir = getX10DistHostLibDir().getParentFile();
	  File x10SrcFolder = null;
	    for (final File file : libDir.listFiles()) {
	      if (file.getName().startsWith("src-x10")) { //$NON-NLS-1$
	    	  x10SrcFolder = file;
	        break;
	      }
	    }
	  if (x10SrcFolder != null)
		  return x10SrcFolder;
	  return null;
  }
  
  // --- Overridden methods
  
  public String[] getClasspath(final ILaunchConfiguration configuration) throws CoreException {
    final List<String> cp = new ArrayList<String>();
        
    final File libDir = getX10DistHostLibDir();
    File commonMathFile = null;
    for (final File file : libDir.listFiles()) {
      if (file.getName().startsWith("commons-math") && file.getName().endsWith(Constants.JAR_EXT)) { //$NON-NLS-1$
        commonMathFile = file;
        break;
      }
    }
    if (commonMathFile == null) {
      throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.XLCD_NoCommonMath));
    }
    cp.add(commonMathFile.getAbsolutePath());
    
    File commonLoggingFile = null;
    for (final File file : libDir.listFiles()) {
      if (file.getName().startsWith("commons-logging") && file.getName().endsWith(Constants.JAR_EXT)) { //$NON-NLS-1$
        commonLoggingFile = file;
        break;
      }
    }
    if (commonLoggingFile == null) {
      throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.XLCD_NoCommonLogging));
    }
    cp.add(commonLoggingFile.getAbsolutePath());
    
    for (final String element : super.getClasspath(configuration)) {
      cp.add(element);
    }
    return cp.toArray(new String[cp.size()]);
  }

}
