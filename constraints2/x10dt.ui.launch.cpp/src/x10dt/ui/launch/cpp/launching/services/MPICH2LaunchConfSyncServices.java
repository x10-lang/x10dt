/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching.services;

import static org.eclipse.ptp.rm.mpi.mpich2.core.launch.MPICH2LaunchConfiguration.ATTR_ARGUMENTS;
import static org.eclipse.ptp.rm.mpi.mpich2.core.launch.MPICH2LaunchConfiguration.ATTR_HOSTFILE;
import static org.eclipse.ptp.rm.mpi.mpich2.core.launch.MPICH2LaunchConfiguration.ATTR_HOSTLIST;
import static org.eclipse.ptp.rm.mpi.mpich2.core.launch.MPICH2LaunchConfiguration.ATTR_NOLOCAL;
import static org.eclipse.ptp.rm.mpi.mpich2.core.launch.MPICH2LaunchConfiguration.ATTR_NUMPROCS;
import static org.eclipse.ptp.rm.mpi.mpich2.core.launch.MPICH2LaunchConfiguration.ATTR_PREFIX;
import static org.eclipse.ptp.rm.mpi.mpich2.core.launch.MPICH2LaunchConfiguration.ATTR_USEDEFAULTARGUMENTS;
import static org.eclipse.ptp.rm.mpi.mpich2.core.launch.MPICH2LaunchConfiguration.ATTR_USEDEFAULTPARAMETERS;
import static org.eclipse.ptp.rm.mpi.mpich2.core.launch.MPICH2LaunchConfiguration.ATTR_USEHOSTFILE;
import static org.eclipse.ptp.rm.mpi.mpich2.core.launch.MPICH2LaunchConfiguration.ATTR_USEHOSTLIST;
import static org.eclipse.ptp.rm.mpi.mpich2.core.launch.MPICH2LaunchConfiguration.ATTR_USEPREFIX;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.ptp.rm.mpi.mpich2.core.launch.MPICH2LaunchConfigurationDefaults;

import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConf;

final class MPICH2LaunchConfSyncServices implements IPlatformConfLaunchConfSyncServices {

  // --- Interface methods implementation

  public boolean equals(final ILaunchConfiguration launchConfig, final IX10PlatformConf platformConf) throws CoreException {
    return true;
  }

  public void initOrUpdate(final ILaunchConfigurationWorkingCopy launchConfig, final IX10PlatformConf platformConf,
                           final boolean shouldInitialize) throws CoreException {
    if (shouldInitialize) {
      MPICH2LaunchConfigurationDefaults.loadDefaults();
      launchConfig.setAttribute(ATTR_NUMPROCS, MPICH2LaunchConfigurationDefaults.ATTR_NUMPROCS);
      launchConfig.setAttribute(ATTR_NOLOCAL, MPICH2LaunchConfigurationDefaults.ATTR_NOLOCAL);
      launchConfig.setAttribute(ATTR_PREFIX, MPICH2LaunchConfigurationDefaults.ATTR_PREFIX);
      launchConfig.setAttribute(ATTR_USEPREFIX, MPICH2LaunchConfigurationDefaults.ATTR_USEPREFIX);
      launchConfig.setAttribute(ATTR_HOSTFILE, MPICH2LaunchConfigurationDefaults.ATTR_HOSTFILE);
      launchConfig.setAttribute(ATTR_USEHOSTFILE, MPICH2LaunchConfigurationDefaults.ATTR_USEHOSTFILE);
      launchConfig.setAttribute(ATTR_HOSTLIST, MPICH2LaunchConfigurationDefaults.ATTR_HOSTLIST);
      launchConfig.setAttribute(ATTR_USEHOSTLIST, MPICH2LaunchConfigurationDefaults.ATTR_USEHOSTLIST);
      launchConfig.setAttribute(ATTR_ARGUMENTS, MPICH2LaunchConfigurationDefaults.ATTR_ARGUMENTS);
      launchConfig.setAttribute(ATTR_USEDEFAULTARGUMENTS, MPICH2LaunchConfigurationDefaults.ATTR_USEDEFAULTARGUMENTS);
      launchConfig.setAttribute(ATTR_USEDEFAULTPARAMETERS, MPICH2LaunchConfigurationDefaults.ATTR_USEDEFAULTPARAMETERS);
    }
  }

}
