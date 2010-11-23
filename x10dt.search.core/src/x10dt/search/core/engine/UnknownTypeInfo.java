/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.engine;


final class UnknownTypeInfo implements IBasicTypeInfo {

  UnknownTypeInfo(final String typeName) {
    this.fTypeName = typeName;
  }
  
  // --- Interface methods implementation
  
  public String getName() {
    return this.fTypeName;
  }
  
  // --- Fields
  
  private final String fTypeName;

}
