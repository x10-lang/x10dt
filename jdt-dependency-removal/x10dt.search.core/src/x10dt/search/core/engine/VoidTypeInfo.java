/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.engine;



final class VoidTypeInfo implements IBasicTypeInfo {
  
  // --- Interface methods

  public String getName() {
    return VOID_TYPE_NAME;
  }
  
  // --- Fields
  
  static final String VOID_TYPE_NAME = "x10.lang.Void"; //$NON-NLS-1$

}
