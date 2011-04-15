/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching.services;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;

import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConf;


final class CompositePlatformConfLaunchConfSyncServices implements IPlatformConfLaunchConfSyncServices {
  
  CompositePlatformConfLaunchConfSyncServices(final Iterable<IPlatformConfLaunchConfSyncServices> servicesIterable) {
    this.fServicesIteable = servicesIterable;
  }
  
  // --- Interface methods implementation

  public boolean equals(final ILaunchConfiguration launchConfig, final IX10PlatformConf platformConf) throws CoreException {
    for (final IPlatformConfLaunchConfSyncServices launchConfServices : this.fServicesIteable) {
      if (! launchConfServices.equals(launchConfig, platformConf)) {
        return false;
      }
    }
    return true;
  }

  public void initOrUpdate(ILaunchConfigurationWorkingCopy launchConfig, IX10PlatformConf platformConf,
                           boolean shouldInitialize) throws CoreException {
    for (final IPlatformConfLaunchConfSyncServices launchConfServices : this.fServicesIteable) {
      launchConfServices.initOrUpdate(launchConfig, platformConf, shouldInitialize);
    }
  }
  
  // --- Fields
  
  private final Iterable<IPlatformConfLaunchConfSyncServices> fServicesIteable;

}
