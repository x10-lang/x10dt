/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching;

import x10dt.ui.launch.cpp.CppLaunchCore;


/**
 * Provides some useful constants for some attributes present in the launch configuration.
 * 
 * @author egeay
 */
public final class LaunchConfigConstants {
  
  // --- Attribute Constants
  
  /**
   * The id of the attribute storing if we should use a host file or not.
   */
  public static final String ATTR_USE_HOSTFILE = CppLaunchCore.PLUGIN_ID + ".launchAttrs.useHostFile";  //$NON-NLS-1$
  
  /**
   * The id of the attribute storing the number of places.
   */
  public static final String ATTR_NUM_PLACES = CppLaunchCore.PLUGIN_ID + ".launchAttrs.numPlaces";  //$NON-NLS-1$
  
  /**
   * The id of the attribute storing the host file name.
   */
  public static final String ATTR_HOSTFILE = CppLaunchCore.PLUGIN_ID + ".launchAttrs.hostFile";  //$NON-NLS-1$
  
  /**
   * The id of the attribute storing the load leveler script file name.
   */
  public static final String ATTR_LOADLEVELER_SCRIPT = CppLaunchCore.PLUGIN_ID + ".launchAttrs.loadLevelerScript";  //$NON-NLS-1$
  /**
   * The id of the attribute storing a hosts list.
   */
  public static final String ATTR_HOSTLIST = CppLaunchCore.PLUGIN_ID + ".launchAttrs.hostList";  //$NON-NLS-1$
  
  // --- Default attribute values
  
  /**
   * The default number of places.
   */
  public static final int DEFAULT_NUM_PLACES = 1;
  
  // --- Private code
  
  private LaunchConfigConstants() {}

}
