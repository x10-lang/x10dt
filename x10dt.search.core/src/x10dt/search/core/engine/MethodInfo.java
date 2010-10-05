/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.engine;

import org.eclipse.imp.pdb.facts.ISourceLocation;


final class MethodInfo implements IMethodInfo {
  
  MethodInfo(final String name, final IBasicTypeInfo returnType, final IBasicTypeInfo[] parameterTypes, 
             final ISourceLocation location, final int x10FlagsCode) {
    this.fName = name;
    this.fReturnType = returnType;
    this.fParameterTypes = parameterTypes;
    this.fLocation = location;
    this.fX10FlagsCode = x10FlagsCode;
  }
  
  // --- Interface methods implementation

  public ISourceLocation getLocation() {
    return this.fLocation;
  }

  public String getName() {
    return this.fName;
  }

  public IBasicTypeInfo[] getParameters() {
    return this.fParameterTypes;
  }

  public IBasicTypeInfo getReturnType() {
    return this.fReturnType;
  }

  public int getX10FlagsCode() {
    return this.fX10FlagsCode;
  }
  
  // --- Overridden methods
  
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Name: ").append(this.fName).append("\nReturn type: ").append(this.fReturnType.getName()); //$NON-NLS-1$ //$NON-NLS-2$
    int i = 0;
    for (final IBasicTypeInfo paramType : this.fParameterTypes) {
      sb.append("\nParam ").append(i).append(": ").append(paramType.getName()); //$NON-NLS-1$ //$NON-NLS-2$
    }
    sb.append("\nLocation: ").append(this.fLocation).append("\nFlags code: ").append(this.fX10FlagsCode); //$NON-NLS-1$ //$NON-NLS-2$
    return sb.toString();
  }
  
  // --- Fields
  
  private final String fName;
  
  private final IBasicTypeInfo fReturnType;
  
  private final IBasicTypeInfo[] fParameterTypes;
  
  private final ISourceLocation fLocation;
  
  private final int fX10FlagsCode;

}
