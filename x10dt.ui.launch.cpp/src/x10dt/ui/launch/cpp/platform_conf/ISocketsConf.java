/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.platform_conf;

import java.util.List;

/**
 * Contains the communication interface options for Sockets.
 * 
 * @author egeay
 */
public interface ISocketsConf extends ICommunicationInterfaceConf {

  /**
   * Returns the host file to use for running.
   * 
   * @return A non-null, but possibly empty, string.
   */
  public String getHostFile();
  
  /**
   * Returns the list of hosts to use for running.
   * 
   * @return A non-null, but possibly empty, list.
   */
  public List<String> getHostList();
  
  /**
   * Returns if we should use the host file or host list for running with sockets transport.
   * 
   * @return True if we should use the host file, false otherwise.
   */
  public boolean shouldUseHostFile();
  
}
