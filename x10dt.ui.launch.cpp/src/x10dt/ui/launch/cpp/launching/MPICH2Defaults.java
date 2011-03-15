/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.ptp.rm.mpi.mpich2.ui.launch.MPICH2LaunchConfiguration;
import org.eclipse.ptp.rm.mpi.mpich2.ui.launch.MPICH2LaunchConfigurationDefaults;

import x10dt.ui.launch.cpp.platform_conf.ICommunicationInterfaceConf;


final class MPICH2Defaults implements ICommunicationInterfaceDefaults {
  
  // --- Interface methods implementation

  public void setDefaults(final ILaunchConfigurationWorkingCopy launchConfig,
                          final ICommunicationInterfaceConf ciConf) throws CoreException {
    MPICH2LaunchConfigurationDefaults.loadDefaults();
    launchConfig.setAttribute(MPICH2LaunchConfiguration.ATTR_NUMPROCS, MPICH2LaunchConfigurationDefaults.ATTR_NUMPROCS);
    launchConfig.setAttribute(MPICH2LaunchConfiguration.ATTR_NOLOCAL, MPICH2LaunchConfigurationDefaults.ATTR_NOLOCAL);
    launchConfig.setAttribute(MPICH2LaunchConfiguration.ATTR_PREFIX, MPICH2LaunchConfigurationDefaults.ATTR_PREFIX);
    launchConfig.setAttribute(MPICH2LaunchConfiguration.ATTR_USEPREFIX, MPICH2LaunchConfigurationDefaults.ATTR_USEPREFIX);
    launchConfig.setAttribute(MPICH2LaunchConfiguration.ATTR_HOSTFILE, MPICH2LaunchConfigurationDefaults.ATTR_HOSTFILE);
    launchConfig.setAttribute(MPICH2LaunchConfiguration.ATTR_USEHOSTFILE, MPICH2LaunchConfigurationDefaults.ATTR_USEHOSTFILE);
    launchConfig.setAttribute(MPICH2LaunchConfiguration.ATTR_HOSTLIST, MPICH2LaunchConfigurationDefaults.ATTR_HOSTLIST);
    launchConfig.setAttribute(MPICH2LaunchConfiguration.ATTR_USEHOSTLIST, MPICH2LaunchConfigurationDefaults.ATTR_USEHOSTLIST);
    launchConfig.setAttribute(MPICH2LaunchConfiguration.ATTR_ARGUMENTS, MPICH2LaunchConfigurationDefaults.ATTR_ARGUMENTS);
    launchConfig.setAttribute(MPICH2LaunchConfiguration.ATTR_USEDEFAULTARGUMENTS, 
                             MPICH2LaunchConfigurationDefaults.ATTR_USEDEFAULTARGUMENTS);
    launchConfig.setAttribute(MPICH2LaunchConfiguration.ATTR_USEDEFAULTPARAMETERS, 
                             MPICH2LaunchConfigurationDefaults.ATTR_USEDEFAULTPARAMETERS);
  }

}
