/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching.services;

import static x10dt.ui.launch.cpp.launching.CppBackEndLaunchConfAttrs.ATTR_USE_PLATFORM_CONF_DATA;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_HOSTFILE;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_HOSTLIST;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_NUM_PLACES;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_USE_HOSTFILE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;

import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.cpp.LaunchMessages;


final class SocketsLaunchConfAttrDataBuilder implements ILaunchConfAttrDataBuilder {
  
  // --- Interface methods implementation

  @SuppressWarnings("unchecked")
  public void buildAttrDataMap(final ILaunchConfiguration launchConfig, 
                               final Map<IAttrServices, List<IAttrServices>> attrDataMap) throws CoreException {
    final List<IAttrServices> attrDataList = new ArrayList<IAttrServices>();
    attrDataList.add(new BooleanAttrServices(LaunchMessages.SLCS_X10PlatformConfSync, true, 
                                             launchConfig.getAttribute(ATTR_USE_PLATFORM_CONF_DATA, true)));
    attrDataList.add(new IntegerAttrServices(LaunchMessages.SLCS_NumOfPlaces, true, 
                                             launchConfig.getAttribute(ATTR_NUM_PLACES, 1)));    
    if (launchConfig.getAttribute(ATTR_USE_HOSTFILE, false)) {
      attrDataList.add(new StringAttrServices(LaunchMessages.SLCS_HostFile, true, 
                                              launchConfig.getAttribute(ATTR_HOSTFILE, Constants.EMPTY_STR)));
    } else {
      attrDataList.add(new ListAttrServices(LaunchMessages.SLCS_HostList, true, 
                                            launchConfig.getAttribute(ATTR_HOSTLIST, Collections.<String>emptyList())));
    }
    attrDataMap.put(new StringAttrServices(LaunchMessages.SLCS_CIType, false, LaunchMessages.SLCS_Sockets), attrDataList);
    
    new DefaultsLaunchConfAttrDataBuilder().buildAttrDataMap(launchConfig, attrDataMap);
  }

}
