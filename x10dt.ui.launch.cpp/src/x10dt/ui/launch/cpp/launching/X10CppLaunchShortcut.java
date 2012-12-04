/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching;

import static org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME;
import static org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME;
import static org.eclipse.ptp.core.IPTPLaunchConfigurationConstants.ATTR_CONSOLE;
import static x10dt.ui.launch.cpp.launching.CommunicationInterfaceTab.HOST_LIST;
import static x10dt.ui.launch.cpp.launching.CommunicationInterfaceTab.LOCALHOST;
import static x10dt.ui.launch.cpp.launching.CppBackEndLaunchConfAttrs.ATTR_X10_MAIN_CLASS;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_HOSTLIST;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_NUM_PLACES;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_USE_HOSTFILE;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.DEFAULT_NUM_PLACES;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.imp.utils.Pair;
import org.eclipse.ui.dialogs.AbstractElementListSelectionDialog;

import x10dt.core.X10DTCorePlugin;
import x10dt.search.core.elements.ITypeInfo;
import x10dt.ui.launching.AbstractX10LaunchShortcut;

/**
 * Implements the launch shortcut for C++ back-end.
 * 
 * @author egeay
 */
public class X10CppLaunchShortcut extends AbstractX10LaunchShortcut implements ILaunchShortcut {

  // --- Abstract methods implementation

  protected ILaunchConfigurationType getConfigurationType() {
    return DebugPlugin.getDefault().getLaunchManager().getLaunchConfigurationType(LAUNCH_CONF_TYPE);
  }
  
  protected final String getProjectNatureId() {
    return X10DTCorePlugin.X10_CPP_PRJ_NATURE_ID;
  }

  protected void setLaunchConfigurationAttributes(final ILaunchConfigurationWorkingCopy workingCopy,
                                                  final ITypeInfo typeInfo) throws CoreException {
    workingCopy.setAttribute(ATTR_PROJECT_NAME, typeInfo.getCompilationUnit().getProject().getName());
    workingCopy.setAttribute(org.eclipse.ptp.core.IPTPLaunchConfigurationConstants.ATTR_PROJECT_NAME, 
                             typeInfo.getCompilationUnit().getProject().getName());
    workingCopy.setAttribute(ATTR_MAIN_TYPE_NAME, typeInfo.getName());
    workingCopy.setAttribute(ATTR_CONSOLE, true);
    workingCopy.setAttribute(ATTR_X10_MAIN_CLASS, typeInfo.getName());
    workingCopy.setAttribute(ATTR_HOSTLIST, LOCALHOST);
    workingCopy.setAttribute(ATTR_NUM_PLACES, DEFAULT_NUM_PLACES);
    workingCopy.setAttribute(ATTR_USE_HOSTFILE, HOST_LIST);
  }
  
  // --- Overridden methods
  
  protected Pair<Integer, ILaunchConfiguration> chooseConfiguration(final List<ILaunchConfiguration> configList,
                                                                    final String mode) {
    final AbstractElementListSelectionDialog dialog = new SelectExistingLaunchConfigsDialog(getShell(), configList, mode);
    final int result = dialog.open();
    return new Pair<Integer, ILaunchConfiguration>(result, (ILaunchConfiguration) dialog.getFirstResult());
  }
  
  
  // --- Fields
  
  private static final String LAUNCH_CONF_TYPE = "x10dt.ui.cpp.launch.X10LaunchConfigurationType"; //$NON-NLS-1$

}
