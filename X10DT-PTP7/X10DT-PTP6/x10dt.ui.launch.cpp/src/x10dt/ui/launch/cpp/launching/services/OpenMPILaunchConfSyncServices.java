/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching.services;

import static org.eclipse.ptp.rm.mpi.openmpi.core.launch.OpenMPILaunchConfiguration.ATTR_ARGUMENTS;
import static org.eclipse.ptp.rm.mpi.openmpi.core.launch.OpenMPILaunchConfiguration.ATTR_BYNODE;
import static org.eclipse.ptp.rm.mpi.openmpi.core.launch.OpenMPILaunchConfiguration.ATTR_BYSLOT;
import static org.eclipse.ptp.rm.mpi.openmpi.core.launch.OpenMPILaunchConfiguration.ATTR_HOSTFILE;
import static org.eclipse.ptp.rm.mpi.openmpi.core.launch.OpenMPILaunchConfiguration.ATTR_HOSTLIST;
import static org.eclipse.ptp.rm.mpi.openmpi.core.launch.OpenMPILaunchConfiguration.ATTR_NOLOCAL;
import static org.eclipse.ptp.rm.mpi.openmpi.core.launch.OpenMPILaunchConfiguration.ATTR_NOOVERSUBSCRIBE;
import static org.eclipse.ptp.rm.mpi.openmpi.core.launch.OpenMPILaunchConfiguration.ATTR_NUMPROCS;
import static org.eclipse.ptp.rm.mpi.openmpi.core.launch.OpenMPILaunchConfiguration.ATTR_PREFIX;
import static org.eclipse.ptp.rm.mpi.openmpi.core.launch.OpenMPILaunchConfiguration.ATTR_USEDEFAULTARGUMENTS;
import static org.eclipse.ptp.rm.mpi.openmpi.core.launch.OpenMPILaunchConfiguration.ATTR_USEDEFAULTPARAMETERS;
import static org.eclipse.ptp.rm.mpi.openmpi.core.launch.OpenMPILaunchConfiguration.ATTR_USEHOSTFILE;
import static org.eclipse.ptp.rm.mpi.openmpi.core.launch.OpenMPILaunchConfiguration.ATTR_USEHOSTLIST;
import static org.eclipse.ptp.rm.mpi.openmpi.core.launch.OpenMPILaunchConfiguration.ATTR_USEPREFIX;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.ptp.rm.mpi.openmpi.core.launch.OpenMPILaunchConfigurationDefaults;

import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConf;


final class OpenMPILaunchConfSyncServices implements IPlatformConfLaunchConfSyncServices {
  
  // --- Interface methods implementation
  
  public boolean equals(final ILaunchConfiguration launchConfig, 
                        final IX10PlatformConf platformConf) throws CoreException {
    return true;
  }

  public void initOrUpdate(final ILaunchConfigurationWorkingCopy launchConfig, final IX10PlatformConf platformConf,
                           final boolean shouldInitialize) throws CoreException {
    if (shouldInitialize) {
      OpenMPILaunchConfigurationDefaults.loadDefaults();
      launchConfig.setAttribute(ATTR_NUMPROCS, OpenMPILaunchConfigurationDefaults.ATTR_NUMPROCS);
      launchConfig.setAttribute(ATTR_USEHOSTLIST, OpenMPILaunchConfigurationDefaults.ATTR_USEHOSTLIST);
      launchConfig.setAttribute(ATTR_HOSTLIST, OpenMPILaunchConfigurationDefaults.ATTR_HOSTLIST);
      launchConfig.setAttribute(ATTR_HOSTFILE, OpenMPILaunchConfigurationDefaults.ATTR_HOSTFILE);
      launchConfig.setAttribute(ATTR_USEHOSTFILE, OpenMPILaunchConfigurationDefaults.ATTR_USEHOSTFILE);

      launchConfig.setAttribute(ATTR_BYNODE, OpenMPILaunchConfigurationDefaults.ATTR_BYNODE);
      launchConfig.setAttribute(ATTR_BYSLOT, OpenMPILaunchConfigurationDefaults.ATTR_BYSLOT);
      launchConfig.setAttribute(ATTR_NOOVERSUBSCRIBE, OpenMPILaunchConfigurationDefaults.ATTR_NOOVERSUBSCRIBE);
      launchConfig.setAttribute(ATTR_NOLOCAL, OpenMPILaunchConfigurationDefaults.ATTR_NOLOCAL);
      launchConfig.setAttribute(ATTR_PREFIX, OpenMPILaunchConfigurationDefaults.ATTR_PREFIX);
      launchConfig.setAttribute(ATTR_USEPREFIX, OpenMPILaunchConfigurationDefaults.ATTR_USEPREFIX);
      launchConfig.setAttribute(ATTR_ARGUMENTS, OpenMPILaunchConfigurationDefaults.ATTR_ARGUMENTS);
      launchConfig.setAttribute(ATTR_USEDEFAULTARGUMENTS, OpenMPILaunchConfigurationDefaults.ATTR_USEDEFAULTARGUMENTS);
      launchConfig.setAttribute(ATTR_USEDEFAULTPARAMETERS, OpenMPILaunchConfigurationDefaults.ATTR_USEDEFAULTPARAMETERS);
    }
  }

}
