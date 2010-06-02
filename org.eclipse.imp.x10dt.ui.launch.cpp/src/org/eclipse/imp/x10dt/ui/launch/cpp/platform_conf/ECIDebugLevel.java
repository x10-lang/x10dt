/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package org.eclipse.imp.x10dt.ui.launch.cpp.platform_conf;

/**
 * Debugging level of the proxy for IBM Parallel Environment.
 * 
 * @author egeay
 */
public enum ECIDebugLevel {
  
 /**
  * No debugging information for proxy.
  */
  NONE,
  
  /**
   * Function debugging level for proxy.
   */
  FUNCTION,
  
  /**
   * Detailed debugging level for proxy.
   */
  DETAILED;

}
