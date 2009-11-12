/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package org.eclipse.imp.x10dt.ui.launch.core.utils;

/**
 * 
 * 
 * @author egeay
 */
public interface IInputListener {
  
  /**
   * Gets notified once we finished reading the input stream.
   */
  public void after();
  
  /**
   * Gets notified before we start reading the input stream.
   */
  public void before();
  
  /**
   * Reads a line on the input stream.
   * 
   * @param line The line read.
   */
  public void read(final String line);

}