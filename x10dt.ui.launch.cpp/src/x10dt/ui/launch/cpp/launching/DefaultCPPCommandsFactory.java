/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching;

import org.eclipse.core.runtime.CoreException;


/**
 * Factory methods to create different implementations of {@link IDefaultCPPCommands}.
 * 
 * @author egeay
 */
public final class DefaultCPPCommandsFactory {
  
  /**
   * Format to get location of X10RT properties file required for building c++ compilation/linking commands.
   */
  public static final String X10RT_PROPERTIES_FILE_FORMAT = "etc/x10rt_%s.properties"; //$NON-NLS-1$
  
  /**
   * Contains path of libx10.properties relative to an X10 distribution.
   */
  public static final String LIBX10_PROPERTIES_FILE = "stdlib/libx10.properties"; //$NON-NLS-1$

  /**
   * Contains path of sharedlib.properties relative to an X10 distribution.
   */
  public static final String SHARED_LIB_PROPERTIES_FILE = "etc/sharedlib.properties"; //$NON-NLS-1$
  
  /**
   * Creates the implementation of {@link IDefaultCPPCommands} for AIX.
   * 
   * @param platformConf The platform configuration to consider for building the commands.
   * @return A non-null implementation of {@link IDefaultCPPCommands}.
   * @throws CoreException Occurs if we could not the X10 properties file required to build the commands.
   */
  public static IDefaultCPPCommands create(final String project) throws CoreException {
    switch (ConfUtils.getTargetOS(ConfUtils.getConfiguration(project))) {
      case AIX:
        return new AixDefaultCommands(project);
      case LINUX:
        return new LinuxDefaultCommands(project);
      case MAC:
        return new MacDefaultCommands(project);
      case WINDOWS:
        return new CygwinDefaultCommands(project);
      default:
        return new UnknownUnixDefaultCommands(project);
    }
  }
  
  // --- Private code
  
  private DefaultCPPCommandsFactory() {}

}
