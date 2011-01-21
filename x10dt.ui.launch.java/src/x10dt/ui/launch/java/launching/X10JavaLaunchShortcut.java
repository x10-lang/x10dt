/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.java.launching;

import static org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME;
import static org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME;

import java.net.URL;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.imp.model.ISourceEntity;
import org.eclipse.imp.utils.Pair;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;

import polyglot.types.ClassType;
import x10dt.core.utils.X10BundleUtils;
import x10dt.ui.X10DTUIPlugin;
import x10dt.ui.launch.core.LaunchCore;
import x10dt.ui.launch.java.Messages;
import x10dt.ui.launching.AbstractX10LaunchShortcut;

/**
 * Implements the launch shortcut for Java back-end.
 * 
 * @author egeay
 */
public final class X10JavaLaunchShortcut extends AbstractX10LaunchShortcut implements ILaunchShortcut {

  // --- Interface methods implementation

  protected ILaunchConfigurationType getConfigurationType() {
    return DebugPlugin.getDefault().getLaunchManager().getLaunchConfigurationType(LAUNCH_CONF_TYPE);
  }
  
  protected String getProjectNatureId() {
    return LaunchCore.X10_PRJ_JAVA_NATURE_ID;
  }
  
  protected void setLaunchConfigurationAttributes(final ILaunchConfigurationWorkingCopy workingCopy,
                                                  final Pair<ClassType, ISourceEntity> type) {
    workingCopy.setAttribute(ATTR_PROJECT_NAME, type.second.getName());
    workingCopy.setAttribute(ATTR_MAIN_TYPE_NAME, type.first.fullName().toString());
    
    try {
      final URL x10RuntimeURL = X10BundleUtils.getX10RuntimeURL();
      if (x10RuntimeURL != null) {
        workingCopy.setAttribute(X10LaunchConfigAttributes.X10RuntimeAttributeID, x10RuntimeURL.getPath());
      }
    } catch (CoreException except) {
      X10DTUIPlugin.getInstance().getLog().log(new Status(IStatus.WARNING, X10DTUIPlugin.PLUGIN_ID, 
                                                          Messages.XJS_X10RuntimeAccessError, except));
    }
  }
  
  @Override
  protected boolean launchConfigMatches(final ILaunchConfiguration config, final String typeName,
			final String projectName) throws CoreException {
	// This is a fairly loose match, based only on the project and main type names.
	// This is based on the notion that the most common scenario is to have a single
	// launch configuration for a given project and main type.
	// If we want to support multiple launch types (e.g. with different comm interfaces)
	// for a given project/main type better, we should tighten this match.
	return config.getAttribute(IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME, "").equals(typeName) && //$NON-NLS-1$
	config.getAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, "").equals(projectName);
  }

  
  // --- Fields

private static final String LAUNCH_CONF_TYPE = "x10dt.ui.launch.java.launching.X10LaunchConfigurationType"; //$NON-NLS-1$
  
}
