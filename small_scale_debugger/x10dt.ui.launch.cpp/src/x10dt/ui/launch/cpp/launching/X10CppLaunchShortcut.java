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
import static x10dt.ui.launch.core.utils.PTPConstants.MPICH2_SERVICE_PROVIDER_ID;
import static x10dt.ui.launch.core.utils.PTPConstants.OPEN_MPI_SERVICE_PROVIDER_ID;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.imp.utils.Pair;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.ptp.core.elements.IPMachine;
import org.eclipse.ptp.core.elements.IPNode;
import org.eclipse.ptp.core.elements.IResourceManager;
import org.eclipse.ptp.rm.mpi.mpich2.ui.launch.MPICH2LaunchConfiguration;
import org.eclipse.ptp.rm.mpi.mpich2.ui.launch.MPICH2LaunchConfigurationDefaults;
import org.eclipse.ptp.rm.mpi.openmpi.ui.launch.OpenMPILaunchConfiguration;
import org.eclipse.ptp.rm.mpi.openmpi.ui.launch.OpenMPILaunchConfigurationDefaults;

import polyglot.types.ClassType;
import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.LaunchCore;
import x10dt.ui.launch.cpp.CppLaunchCore;
import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConf;
import x10dt.ui.launch.cpp.utils.PTPConfUtils;
import x10dt.ui.launching.AbstractX10LaunchShortcut;

/**
 * Implements the launch shortcut for C++ back-end.
 * 
 * @author egeay
 */
public final class X10CppLaunchShortcut extends AbstractX10LaunchShortcut implements ILaunchShortcut {

  // --- Abstract methods implementation

  protected ILaunchConfigurationType getConfigurationType() {
    return DebugPlugin.getDefault().getLaunchManager().getLaunchConfigurationType(LAUNCH_CONF_TYPE);
  }
  
  protected String getProjectNatureId() {
    return LaunchCore.X10_CPP_PRJ_NATURE_ID;
  }
  
  protected void setLaunchConfigurationAttributes(final ILaunchConfigurationWorkingCopy workingCopy,
                                                  final Pair<ClassType, IJavaElement> type) {
    workingCopy.setAttribute(ATTR_PROJECT_NAME, type.second.getJavaProject().getElementName());
    workingCopy.setAttribute(org.eclipse.ptp.core.IPTPLaunchConfigurationConstants.ATTR_PROJECT_NAME, 
                             type.second.getJavaProject().getElementName());
    workingCopy.setAttribute(ATTR_MAIN_TYPE_NAME, type.first.fullName().toString());
    workingCopy.setAttribute(ATTR_CONSOLE, true);
    workingCopy.setAttribute(Constants.ATTR_X10_MAIN_CLASS, type.first.fullName().toString());
    
    final IProject project = type.second.getResource().getProject();
    final IX10PlatformConf platformConf = CppLaunchCore.getInstance().getPlatformConfiguration(project);
    
    final String useHostListAttrKey;
    final String hostListAttrKey;
    if (OPEN_MPI_SERVICE_PROVIDER_ID.equals(platformConf.getCommunicationInterfaceConf().getServiceTypeId())) {
      try {
        OpenMPILaunchConfigurationDefaults.loadDefaults();
        useHostListAttrKey = OpenMPILaunchConfiguration.ATTR_USEHOSTLIST;
        hostListAttrKey = OpenMPILaunchConfiguration.ATTR_HOSTLIST;
      } catch (CoreException except) {
        CppLaunchCore.log(except.getStatus());
        return;
      }
    } else if (MPICH2_SERVICE_PROVIDER_ID.equals(platformConf.getCommunicationInterfaceConf().getServiceModeId())) {
      try {
        MPICH2LaunchConfigurationDefaults.loadDefaults();
        useHostListAttrKey = MPICH2LaunchConfiguration.ATTR_USEHOSTLIST;
        hostListAttrKey = MPICH2LaunchConfiguration.ATTR_HOSTLIST;
      } catch (CoreException except) {
        CppLaunchCore.log(except.getStatus());
        return;
      }
    } else {
      useHostListAttrKey = null;
      hostListAttrKey = null;
    }
    
    if ((useHostListAttrKey != null) && (hostListAttrKey != null)) {
      final IResourceManager resourceManager = PTPConfUtils.findResourceManagerById(platformConf);
      if (resourceManager != null) {
        final StringBuilder hostListBuilder = new StringBuilder();
        int i = 0;
        for (final IPMachine machine : resourceManager.getMachines()) {
          for (final IPNode node : machine.getNodes()) {
            if (i == 0) {
              i = 1;
            } else {
              hostListBuilder.append(',');
            }
            hostListBuilder.append(node.getName());
          }
        }
        workingCopy.setAttribute(useHostListAttrKey, true);
        workingCopy.setAttribute(hostListAttrKey, hostListBuilder.toString());
      }
    }
  }
  
  // --- Fields
  
  private static final String LAUNCH_CONF_TYPE = "x10dt.ui.cpp.launch.X10LaunchConfigurationType"; //$NON-NLS-1$

}
