/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.core.platform_conf;

/**
 * Defines the different type of transports supported by X10 runtime.
 * 
 * @author egeay
 */
public enum ETransport {
  
  /**
   * Sockets transport.
   */
  SOCKETS,
  
  /**
   * Standalone transport.
   */
  STANDALONE,
  
  /**
   * MPI transport.
   */
  MPI,
  
  /**
   * LAPI transport
   */
  LAPI;

}
