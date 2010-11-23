/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.engine;

import org.eclipse.imp.pdb.facts.ISourceLocation;


final class FieldInfo implements IFieldInfo {
  
  FieldInfo(final String fieldName, final IBasicTypeInfo fieldType, final ISourceLocation location, final int x10FlagsCode) {
    this.fLocation = location;
    this.fX10FlagsCode = x10FlagsCode;
    this.fFieldName = fieldName;
    this.fFieldType = fieldType;
  }

  // --- Interface methods implementation
  
  public IBasicTypeInfo getFieldTypeInfo() {
    return this.fFieldType;
  }
  
  public ISourceLocation getLocation() {
    return this.fLocation;
  }
  
  public String getName() {
    return this.fFieldName;
  }

  public int getX10FlagsCode() {
    return this.fX10FlagsCode;
  }
  
  // --- Overridden methods
  
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Name: ").append(this.fFieldName).append("\nType: ").append(this.fFieldType.getName()) //$NON-NLS-1$ //$NON-NLS-2$
      .append("\nLocation: ").append(this.fLocation).append("\nFlags code: ").append(this.fX10FlagsCode); //$NON-NLS-1$ //$NON-NLS-2$
    return sb.toString();
  }
  
  // --- Private code
  
  private final ISourceLocation fLocation;
  
  private final int fX10FlagsCode;
  
  private final String fFieldName;
  
  private final IBasicTypeInfo fFieldType;

}
