/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.core.platform_conf;

/**
 * Represents the different OS managed by X10 for compilation and running.
 * 
 * @author egeay
 */
public enum ETargetOS {
  
  /**
   * Windows OS.
   */
  WINDOWS,
  
  /**
   * Linux OS.
   */
  LINUX,
  
  /**
   * Diverse UNIX systems.
   */
  UNIX,
  
  /**
   * MAC OS.
   */
  MAC;
  
}