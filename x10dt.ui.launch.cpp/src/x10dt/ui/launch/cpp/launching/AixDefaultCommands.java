/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching;

import org.eclipse.core.runtime.CoreException;



final class AixDefaultCommands extends AbstractDefaultCPPCommands implements IDefaultCPPCommands {
  
  AixDefaultCommands(final String project) throws CoreException {
    super(project);
  }
  
  // --- Interface methods implemen=tation
  
  public String getArchiver() {
    final String archiver = "ar"; //$NON-NLS-1$
    if (is64Arch()) {
      return archiver + " -X64"; //$NON-NLS-1$
    } else {
      return archiver;
    }
  }

  public String getArchivingOpts() {
    return "-r"; //$NON-NLS-1$
  }
  
}
