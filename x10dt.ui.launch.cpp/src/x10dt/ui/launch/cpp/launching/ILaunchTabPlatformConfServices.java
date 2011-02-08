/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching;

import org.eclipse.debug.core.ILaunchConfiguration;

import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConf;


interface ILaunchTabPlatformConfServices {
  
  void platformConfSelected(final IX10PlatformConf platformConf);
  
  void setLaunchConfiguration(final ILaunchConfiguration configuration);

}
