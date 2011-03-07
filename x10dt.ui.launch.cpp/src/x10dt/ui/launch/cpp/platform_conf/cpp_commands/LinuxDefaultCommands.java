/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.platform_conf.cpp_commands;

import org.eclipse.core.resources.IProject;

import x10dt.ui.launch.core.platform_conf.EArchitecture;
import x10dt.ui.launch.core.platform_conf.ETransport;


final class LinuxDefaultCommands extends AbstractDefaultCPPCommands implements IDefaultCPPCommands {
  
  LinuxDefaultCommands(IProject project, final boolean is64Arch, final EArchitecture architecture, final ETransport transport, boolean isLocal) {
    super(project, is64Arch, architecture, transport, isLocal);
  }
  
  // --- Interface methods implementation
  
  public String getArchiver() {
    return "ar"; //$NON-NLS-1$
  }

  public String getArchivingOpts() {
    return "cq"; //$NON-NLS-1$
  }
  
  public String getCompiler() {
    return fPostCompiler;
  }

  public String getCompilerOptions() {
    return fPreFileArgs;
  }

  public String getLinker() {
	  return fPostCompiler;
  }

  public String getLinkingLibraries() {
	  return fPostFileArgs;
  }

  public String getLinkingOptions() {
	  return fPreFileArgs;
  }

}
