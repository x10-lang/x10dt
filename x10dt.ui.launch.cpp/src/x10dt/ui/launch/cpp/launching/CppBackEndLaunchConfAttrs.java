/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching;

import x10dt.ui.launch.core.LaunchCore;

/**
 * Contains launch configuration attribute constants for the C++ back-end.
 * 
 * @author egeay
 */
public final class CppBackEndLaunchConfAttrs {
  
  /**
   * Attribute name for storing the X10 Main Class to run.
   */
  public static final String ATTR_X10_MAIN_CLASS = LaunchCore.PLUGIN_ID + ".X10MainClass"; //$NON-NLS-1$
  
  /**
   * Attribute name for storing if yes or no we should use some the platform configuration data for the launch.
   */
  public static final String ATTR_USE_PLATFORM_CONF_DATA = LaunchCore.PLUGIN_ID + ".usePlatformConfData"; //$NON-NLS-1$
  
  // --- Private code
  
  private CppBackEndLaunchConfAttrs() {}

}
