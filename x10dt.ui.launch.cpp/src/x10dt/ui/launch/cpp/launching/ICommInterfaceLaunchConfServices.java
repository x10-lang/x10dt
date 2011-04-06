/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;

import x10dt.ui.launch.cpp.platform_conf.ICommunicationInterfaceConf;


interface ICommInterfaceLaunchConfServices {
 
  boolean equals(final ILaunchConfiguration launchConfig, final ICommunicationInterfaceConf ciConf) throws CoreException;
  
  void initOrUpdate(final ILaunchConfigurationWorkingCopy launchConfig, final ICommunicationInterfaceConf ciConf, 
                    final boolean shouldInitialize) throws CoreException;

}
