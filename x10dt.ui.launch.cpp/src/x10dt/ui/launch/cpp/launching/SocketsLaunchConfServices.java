/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching;

import static x10dt.ui.launch.cpp.launching.CppBackEndLaunchConfAttrs.ATTR_USE_PLATFORM_CONF_DATA;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_HOSTFILE;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_HOSTLIST;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_NUM_PLACES;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_USE_HOSTFILE;

import java.util.Collections;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;

import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.cpp.platform_conf.ICommunicationInterfaceConf;
import x10dt.ui.launch.cpp.platform_conf.IHostsBasedConf;


final class SocketsLaunchConfServices implements ICommInterfaceLaunchConfServices {
  
  // --- Interface methods implementation

  public boolean equals(final ILaunchConfiguration launchConfig, 
                        final ICommunicationInterfaceConf ciConf) throws CoreException {
    final IHostsBasedConf socketsConf = (IHostsBasedConf) ciConf;
    
    if (launchConfig.getAttribute(ATTR_USE_PLATFORM_CONF_DATA, true)) {
      return true;
    }
    
    if ((ciConf.getNumberOfPlaces() == launchConfig.getAttribute(ATTR_NUM_PLACES, 1)) &&
        (socketsConf.shouldUseHostFile() == launchConfig.getAttribute(ATTR_USE_HOSTFILE, false))) {
      if (socketsConf.shouldUseHostFile()) {
        return socketsConf.getHostFile().equals(launchConfig.getAttribute(ATTR_HOSTFILE, Constants.EMPTY_STR));
      } else {
        return socketsConf.getHostsAsList().equals(launchConfig.getAttribute(ATTR_HOSTLIST, Collections.<String>emptyList()));
      }
    } else {
      return false;
    }
  }
  
  public void initOrUpdate(final ILaunchConfigurationWorkingCopy launchConfig, final ICommunicationInterfaceConf ciConf,
                           final boolean shouldInitialize) throws CoreException {
    final IHostsBasedConf socketsConf = (IHostsBasedConf) ciConf;
    
    if (shouldInitialize || launchConfig.getAttribute(ATTR_USE_PLATFORM_CONF_DATA, true)) {
      launchConfig.setAttribute(ATTR_NUM_PLACES, socketsConf.getNumberOfPlaces());
      launchConfig.setAttribute(ATTR_USE_HOSTFILE, socketsConf.shouldUseHostFile());
      if (socketsConf.shouldUseHostFile()) {
        launchConfig.setAttribute(ATTR_HOSTFILE, socketsConf.getHostFile());
      } else {
        launchConfig.setAttribute(ATTR_HOSTLIST, socketsConf.getHostsAsList());
      }
    }
  }
  
}
