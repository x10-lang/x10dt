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
import org.eclipse.ptp.rm.mpi.openmpi.ui.launch.OpenMPILaunchConfiguration;
import org.eclipse.ptp.rm.mpi.openmpi.ui.launch.OpenMPILaunchConfigurationDefaults;

import x10dt.ui.launch.cpp.platform_conf.ICommunicationInterfaceConf;


final class OpenMPIDefaults implements ICommunicationInterfaceDefaults {
  
  // --- Interface methods implementation

  public void setDefaults(final ILaunchConfigurationWorkingCopy launchConfig,
                          final ICommunicationInterfaceConf ciConf) throws CoreException {
    OpenMPILaunchConfigurationDefaults.loadDefaults();
    launchConfig.setAttribute(OpenMPILaunchConfiguration.ATTR_NUMPROCS, OpenMPILaunchConfigurationDefaults.ATTR_NUMPROCS);
    launchConfig.setAttribute(OpenMPILaunchConfiguration.ATTR_USEHOSTLIST, 
                              OpenMPILaunchConfigurationDefaults.ATTR_USEHOSTLIST);
    launchConfig.setAttribute(OpenMPILaunchConfiguration.ATTR_HOSTLIST, OpenMPILaunchConfigurationDefaults.ATTR_HOSTLIST);
    launchConfig.setAttribute(OpenMPILaunchConfiguration.ATTR_BYNODE, OpenMPILaunchConfigurationDefaults.ATTR_BYNODE);
    launchConfig.setAttribute(OpenMPILaunchConfiguration.ATTR_BYSLOT, OpenMPILaunchConfigurationDefaults.ATTR_BYSLOT);
    launchConfig.setAttribute(OpenMPILaunchConfiguration.ATTR_NOOVERSUBSCRIBE, 
                             OpenMPILaunchConfigurationDefaults.ATTR_NOOVERSUBSCRIBE);
    launchConfig.setAttribute(OpenMPILaunchConfiguration.ATTR_NOLOCAL, OpenMPILaunchConfigurationDefaults.ATTR_NOLOCAL);
    launchConfig.setAttribute(OpenMPILaunchConfiguration.ATTR_PREFIX, OpenMPILaunchConfigurationDefaults.ATTR_PREFIX);
    launchConfig.setAttribute(OpenMPILaunchConfiguration.ATTR_USEPREFIX, OpenMPILaunchConfigurationDefaults.ATTR_USEPREFIX);
    launchConfig.setAttribute(OpenMPILaunchConfiguration.ATTR_HOSTFILE, OpenMPILaunchConfigurationDefaults.ATTR_HOSTFILE);
    launchConfig.setAttribute(OpenMPILaunchConfiguration.ATTR_USEHOSTFILE, 
                              OpenMPILaunchConfigurationDefaults.ATTR_USEHOSTFILE);
    launchConfig.setAttribute(OpenMPILaunchConfiguration.ATTR_ARGUMENTS, OpenMPILaunchConfigurationDefaults.ATTR_ARGUMENTS);
    launchConfig.setAttribute(OpenMPILaunchConfiguration.ATTR_USEDEFAULTARGUMENTS, 
                             OpenMPILaunchConfigurationDefaults.ATTR_USEDEFAULTARGUMENTS);
    launchConfig.setAttribute(OpenMPILaunchConfiguration.ATTR_USEDEFAULTPARAMETERS, 
                             OpenMPILaunchConfigurationDefaults.ATTR_USEDEFAULTPARAMETERS);
  }

}
