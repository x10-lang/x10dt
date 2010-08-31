/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package org.eclipse.imp.x10dt.ui.launch.cpp.platform_conf.cpp_commands;

import org.eclipse.imp.x10dt.ui.launch.core.platform_conf.EArchitecture;


final class MacDefaultCommands extends AbstractDefaultCPPCommands implements IDefaultCPPCommands {
  
  MacDefaultCommands(final boolean is64Arch, final EArchitecture architecture) {
    super(is64Arch, architecture);
  }
  
  // --- Interface methods implementation
  
  public String getArchiver() {
    return "ar"; //$NON-NLS-1$
  }

  public String getArchivingOpts() {
    return "rcs"; //$NON-NLS-1$
  }

  public String getCompiler() {
    return "g++"; //$NON-NLS-1$
  }

  public String getCompilerOptions() {
    String cmpOpts = "-g -DTRANSPORT=sockets -Wno-long-long -Wno-unused-parameter -pthread -DX10_USE_BDWGC"; //$NON-NLS-1$
    if (is64Arch()) {
      cmpOpts += M64BIT_OPTION;
    }
    if (supportsStreamingSIMDExtensions()) {
      cmpOpts += STREAMING_SIMD_EXTENSIONS;
    }
    return cmpOpts;
  }

  public String getLinker() {
    return getCompiler();
  }

  public String getLinkingLibraries() {
    return "-lx10 -lgc -lx10rt_pgas_sockets -ldl -lm -lpthread -Wl,-rpath -Wl,${X10-DIST}/lib -Wl,-rpath -Wl,${X10-DIST}"; //$NON-NLS-1$
  }

  public String getLinkingOptions() {
    String linkOpts = "-g -DTRANSPORT=sockets -Wno-long-long -Wno-unused-parameter -DX10_USE_BDWGC"; //$NON-NLS-1$
    if (is64Arch()) {
      linkOpts += M64BIT_OPTION;
    }
    if (supportsStreamingSIMDExtensions()) {
      linkOpts += STREAMING_SIMD_EXTENSIONS;
    }
    return linkOpts;
  }

}
