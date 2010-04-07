/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package org.eclipse.imp.x10dt.ui.launch.cpp.platform_conf.cpp_commands;


final class CygwinDefaultCommands extends AbstractDefaultCPPCommands implements IDefaultCPPCommands {
  
  CygwinDefaultCommands(final boolean is64Arch) {
    super(is64Arch);
  }
  
  // --- Interface methods implementation
  
  public String getArchiver() {
    return "ar"; //$NON-NLS-1$
  }

  public String getArchivingOpts() {
    return "r"; //$NON-NLS-1$
  }

  public String getCompiler() {
    return "g++-4"; //$NON-NLS-1$
  }

  public String getCompilerOptions() {
    final String cmpOpts = "-g -DTRANSPORT=sockets -Wno-long-long -Wno-unused-parameter -msse2 -mfpmath=sse -DX10_USE_BDWGC"; //$NON-NLS-1$
    if (is64Arch()) {
      return cmpOpts + " -m64"; //$NON-NLS-1$
    } else {
      return cmpOpts;
    }
  }

  public String getLinker() {
    return getCompiler();
  }

  public String getLinkingLibraries() {
    return "-lx10 -lx10rt_pgas_sockets -ldl -lm -lpthread -lgc"; //$NON-NLS-1$
  }

  public String getLinkingOptions() {
    final String linkOpts = "-g -DTRANSPORT=sockets -Wno-long-long -Wno-unused-parameter -msse2 -mfpmath=sse -DX10_USE_BDWGC"; //$NON-NLS-1$
    if (is64Arch()) {
      return linkOpts + " -m64"; //$NON-NLS-1$
    } else {
      return linkOpts;
    }
  }

}
