/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching.services;

import java.util.LinkedList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;

import x10dt.ui.launch.core.utils.PTPConstants;
import x10dt.ui.launch.cpp.CppLaunchCore;
import x10dt.ui.launch.cpp.platform_conf.ICommunicationInterfaceConf;

/**
 * Factory methods to create implementations of {@link IPlatformConfLaunchConfSyncServices}.
 * 
 * @author egeay
 */
public final class LaunchConfServicesFactory {
  
  /**
   * Creates the appropriate launch configuration services for the given communication interface type.
   * 
   * @param commIntfConf The communication interface configuration identifying the type to consider.
   * @return A possibly <b>null</b> value if this factory method does not support the particular communication interface type.
   */
  public static IPlatformConfLaunchConfSyncServices create(final ICommunicationInterfaceConf commIntfConf) {
    final String serviceTypeId = commIntfConf.getServiceTypeId();
    
    final IPlatformConfLaunchConfSyncServices basedLaunchConfServices;
    if (PTPConstants.OPEN_MPI_SERVICE_PROVIDER_ID.equals(serviceTypeId)) {
      basedLaunchConfServices = new OpenMPILaunchConfSyncServices();
    } else if (PTPConstants.MPICH2_SERVICE_PROVIDER_ID.equals(serviceTypeId)) {
      basedLaunchConfServices =  new MPICH2LaunchConfSyncServices();
    } else if (PTPConstants.SOCKETS_SERVICE_PROVIDER_ID.equals(serviceTypeId) ||
               PTPConstants.PAMI_SERVICE_PROVIDER_ID.equals(serviceTypeId)) {
      basedLaunchConfServices = new SocketsLaunchConfSyncServices();
    } else {
      basedLaunchConfServices = null;
    }
    
    final LinkedList<IPlatformConfLaunchConfSyncServices> extConfServices = new LinkedList<IPlatformConfLaunchConfSyncServices>();
    final IExtensionPoint extPt = Platform.getExtensionRegistry().getExtensionPoint(CppLaunchCore.PLUGIN_ID, 
                                                                                    LAUNCH_CONF_SERVICES_EXT_POINT_ID);
    if (extPt != null) {
      for (final IConfigurationElement configElement : extPt.getConfigurationElements()) {
        try {
          extConfServices.add((IPlatformConfLaunchConfSyncServices) configElement.createExecutableExtension(ATTR_BUILDER));
        } catch (CoreException except) {
          CppLaunchCore.log(except.getStatus());
        }
      }
    }
    if (extConfServices.isEmpty()) {
      return basedLaunchConfServices;
    } else {
      if (basedLaunchConfServices != null) {
        extConfServices.addFirst(basedLaunchConfServices);
      }
      return new CompositePlatformConfLaunchConfSyncServices(extConfServices);
    }
  }
  
  // --- Private code
  
  private LaunchConfServicesFactory() {}
  
  // --- Fields
  
  private static final String LAUNCH_CONF_SERVICES_EXT_POINT_ID = "launchConfServices"; //$NON-NLS-1$
  
  private static final String ATTR_BUILDER = "platformConfLaunchSyncBuilder"; //$NON-NLS-1$

}
