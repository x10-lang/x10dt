/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching;

import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;

import x10dt.ui.launch.cpp.platform_conf.ICommunicationInterfaceConf;
import x10dt.ui.launch.cpp.platform_conf.ISocketsConf;
import x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants;


final class SocketsDefaults implements ICommunicationInterfaceDefaults {
  
  // --- Interface methods implementation

  public void setDefaults(final ILaunchConfigurationWorkingCopy launchConfig, final ICommunicationInterfaceConf ciConf) {
    final ISocketsConf socketsConf = (ISocketsConf) ciConf;
    
    launchConfig.setAttribute(LaunchConfigConstants.ATTR_NUM_PLACES, socketsConf.getNumberOfPlaces());
    launchConfig.setAttribute(LaunchConfigConstants.ATTR_USE_HOSTFILE, socketsConf.shouldUseHostFile());
    if (socketsConf.shouldUseHostFile()) {
      launchConfig.setAttribute(LaunchConfigConstants.ATTR_HOSTFILE, socketsConf.getHostFile());
    } else {
      launchConfig.setAttribute(LaunchConfigConstants.ATTR_HOSTLIST, socketsConf.getHostsAsList());
    }
  }

}
