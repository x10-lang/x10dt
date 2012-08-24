/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.core;

import org.eclipse.core.runtime.QualifiedName;

/**
 * Miscellaneous constants used within this plug-in.
 * 
 * @author egeay
 */
@SuppressWarnings("all")
public final class Constants {
  
  public static final String EMPTY_STR = ""; //$NON-NLS-1$
  
  public static final String JAVA_EXT = ".java"; //$NON-NLS-1$
  
  public static final String CLASS_EXT = ".class"; //$NON-NLS-1$
  
  public static final String JAR_EXT = ".jar"; //$NON-NLS-1$
  
  public static final String CC_EXT = ".cc"; //$NON-NLS-1$
  
  public static final String CPP_EXT = ".cpp"; //$NON-NLS-1$
  
  public static final String CXX_EXT = ".cxx"; //$NON-NLS-1$
  
  public static final String H_EXT = ".h"; //$NON-NLS-1$
  
  public static final String HPP_EXT = ".hpp"; //$NON-NLS-1$
  
  public static final String INC_EXT = ".inc"; //$NON-NLS-1$
  
  public static final String O_EXT = ".o"; //$NON-NLS-1$
  
  public static final String A_EXT = ".a"; //$NON-NLS-1$
  
  public static final String X10_EXT = ".x10"; //$NON-NLS-1$
  
  // --- Constant for X10 builder
  
  public static final QualifiedName EXEC_PATH = new QualifiedName(LaunchCore.PLUGIN_ID, "exec.path"); //$NON-NLS-1$
  
  // --- Private code
  
  private Constants() {}
  
}
