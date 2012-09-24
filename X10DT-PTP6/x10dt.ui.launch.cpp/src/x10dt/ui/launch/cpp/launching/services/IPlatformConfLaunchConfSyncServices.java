/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
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

/**
 * Provides services for launch configurations that are in sync with the platform configuration and that require some 
 * attributes to be tested for equality or initialized/updated. 
 * 
 * <p>See {@link LaunchConfServicesFactory} for creating implementations of this interface.
 * 
 * @author egeay
 */
public interface IPlatformConfLaunchConfSyncServices {
 
  /**
   * Tests the portion of the launch configuration attributes that are equals with the relevant part of the platform
   * configuration that it is in sync with. 
   * 
   * @param launchConfig The launch configuration to test for equality.
   * @param platformConf The platform configuration instance to use for the test.
   * @return True if the relevant portion in sync is equals, false otherwise.
   * @throws CoreException Occurs if we could not read some attributes from the launch configuration underlying storage.
   */
  public boolean equals(final ILaunchConfiguration launchConfig, final IX10PlatformConf platformConf) throws CoreException;
  
  /**
   * Initializes or updates (depending of <i>shouldInitialize</i> flag value) the relevant set of attributes of the launch
   * configuration that is in sync with the platform configuration.
   * 
   * @param launchConfig The launch configuration working copy to initialize or update.
   * @param platformConf The platform configuration to consider.
   * @param shouldInitialize True if we should initialize it, false if we want to update.
   * @throws CoreException Occurs if we could not read some attributes from the launch configuration underlying storage.
   */
  public void initOrUpdate(final ILaunchConfigurationWorkingCopy launchConfig, final IX10PlatformConf platformConf, 
                           final boolean shouldInitialize) throws CoreException;

}
