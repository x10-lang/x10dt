/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.platform_conf.cpp_commands;

import org.eclipse.core.runtime.CoreException;

import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConf;


final class UnknownUnixDefaultCommands extends AbstractDefaultCPPCommands implements IDefaultCPPCommands {
  
  UnknownUnixDefaultCommands(final IX10PlatformConf platformConf) throws CoreException {
    super(platformConf);
  }
  
  // --- Interface methods implementation
  
  public String getArchiver() {
    return "ar"; //$NON-NLS-1$
  }

  public String getArchivingOpts() {
    return "r"; //$NON-NLS-1$
  }

}
