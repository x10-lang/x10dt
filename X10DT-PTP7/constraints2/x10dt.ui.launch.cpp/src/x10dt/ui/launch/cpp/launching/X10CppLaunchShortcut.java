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
import static x10dt.ui.launch.cpp.launching.CppBackEndLaunchConfAttrs.ATTR_X10_MAIN_CLASS;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.imp.preferences.IPreferencesService;
import org.eclipse.imp.preferences.PreferencesService;
import org.eclipse.imp.utils.Pair;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.ptp.core.elements.IPResourceManager;
import org.eclipse.ptp.core.elements.IPMachine;
import org.eclipse.ptp.core.elements.IPNode;
import org.eclipse.ptp.rmsystem.IResourceManager;
import org.eclipse.ptp.rm.mpi.mpich2.core.launch.MPICH2LaunchConfiguration;
import org.eclipse.ptp.rm.mpi.openmpi.core.launch.OpenMPILaunchConfiguration;
import org.eclipse.ui.dialogs.AbstractElementListSelectionDialog;

import x10dt.core.X10DTCorePlugin;
import x10dt.core.preferences.generated.X10Constants;
import x10dt.search.core.elements.ITypeInfo;
import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.utils.PTPConstants;
import x10dt.ui.launch.cpp.CppLaunchCore;
import x10dt.ui.launch.cpp.launching.services.IPlatformConfLaunchConfSyncServices;
import x10dt.ui.launch.cpp.launching.services.LaunchConfServicesFactory;
import x10dt.ui.launch.cpp.platform_conf.ICommunicationInterfaceConf;
import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConf;
import x10dt.ui.launch.cpp.utils.PTPConfUtils;
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
    
    final IProject project = typeInfo.getCompilationUnit().getProject().getRawProject();
    final IX10PlatformConf platformConf = CppLaunchCore.getInstance().getPlatformConfiguration(project);
    
    final String useHostListAttrKey;
    final String hostListAttrKey;
    final ICommunicationInterfaceConf commIntfConf = platformConf.getCommunicationInterfaceConf();
    final IPlatformConfLaunchConfSyncServices launchConfServices = LaunchConfServicesFactory.create(commIntfConf);
    if (launchConfServices != null) {
      launchConfServices.initOrUpdate(workingCopy, platformConf, true);
    }
    if (OPEN_MPI_SERVICE_PROVIDER_ID.equals(commIntfConf.getServiceTypeId())) {
      useHostListAttrKey = OpenMPILaunchConfiguration.ATTR_USEHOSTLIST;
      hostListAttrKey = OpenMPILaunchConfiguration.ATTR_HOSTLIST;
    } else if (MPICH2_SERVICE_PROVIDER_ID.equals(commIntfConf.getServiceTypeId())) {
      useHostListAttrKey = MPICH2LaunchConfiguration.ATTR_USEHOSTLIST;
      hostListAttrKey = MPICH2LaunchConfiguration.ATTR_HOSTLIST;
    } else if (PTPConstants.SOCKETS_SERVICE_PROVIDER_ID.equals(commIntfConf.getServiceTypeId())) {
      useHostListAttrKey = null;
      hostListAttrKey = null;
    } else {
      useHostListAttrKey = null;
      hostListAttrKey = null;
    }
    
    if ((useHostListAttrKey != null) && (hostListAttrKey != null)) {
      final IResourceManager resourceManager = PTPConfUtils.findResourceManagerById(platformConf);
      if (resourceManager != null) {
        final StringBuilder hostListBuilder = new StringBuilder();
        int i = 0;
        for (final IPMachine machine : ((IPResourceManager)resourceManager.getAdapter(IPResourceManager.class)).getMachines()) {
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
  
  protected void updateLaunchConfig(final ILaunchConfigurationWorkingCopy config) throws CoreException {
    final String projectName = config.getAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, Constants.EMPTY_STR);
    final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
    final IX10PlatformConf platformConf = CppLaunchCore.getInstance().getPlatformConfiguration(project);
    final ICommunicationInterfaceConf commIntfConf = platformConf.getCommunicationInterfaceConf();
    
    final IPlatformConfLaunchConfSyncServices launchConfServices = LaunchConfServicesFactory.create(commIntfConf);
    if (launchConfServices != null){
      launchConfServices.initOrUpdate(config, platformConf, false);
    }
  }
  
  // --- Overridden methods
  
  protected Pair<Integer, ILaunchConfiguration> chooseConfiguration(final List<ILaunchConfiguration> configList,
                                                                    final String mode) {
    final AbstractElementListSelectionDialog dialog = new SelectExistingLaunchConfigsDialog(getShell(), configList, mode);
    final int result = dialog.open();
    return new Pair<Integer, ILaunchConfiguration>(result, (ILaunchConfiguration) dialog.getFirstResult());
  }
  
  protected boolean launchConfigMatches(final ILaunchConfiguration config, final String typeName,
                                        final String projectName) throws CoreException {
    if (super.launchConfigMatches(config, typeName, projectName)) {
      final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
      final IPreferencesService prefsService = new PreferencesService(project, X10DTCorePlugin.kLanguageName);
      if (prefsService.getBooleanPreference(X10Constants.P_LAUNCHCONFIGRESTRICTIVEMATCHINGPOLICY)) {
        final IX10PlatformConf platformConf = CppLaunchCore.getInstance().getPlatformConfiguration(project);
        final ICommunicationInterfaceConf commIntfConf = platformConf.getCommunicationInterfaceConf();
        
        final IPlatformConfLaunchConfSyncServices launchConfServices = LaunchConfServicesFactory.create(commIntfConf);
        return (launchConfServices == null) ? true : launchConfServices.equals(config, platformConf);
      } else {
        return true;
      }
    } else {
      return false;
    }
  }
  
  // --- Fields
  
  private static final String LAUNCH_CONF_TYPE = "x10dt.ui.cpp.launch.X10LaunchConfigurationType"; //$NON-NLS-1$

}
