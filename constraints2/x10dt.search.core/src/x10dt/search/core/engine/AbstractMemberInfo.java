/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.engine;

import org.eclipse.imp.pdb.facts.ISourceLocation;

import x10dt.search.core.elements.IMemberInfo;
import x10dt.search.core.elements.ITypeInfo;


abstract class AbstractMemberInfo extends AbstractX10Element implements IMemberInfo {
  
  AbstractMemberInfo(final ISourceLocation location, final String name, final int x10FlagsCode, 
                     final ITypeInfo declaringType) {
    super(name, location);
    this.fDeclaringType = declaringType;
    this.fX10FlagsCode = x10FlagsCode;
  }
  
  // --- IMemberInfo's interface methods implementation

  public final ITypeInfo getDeclaringType() {
    return this.fDeclaringType;
  }

  public final int getX10FlagsCode() {
    return this.fX10FlagsCode;
  }
  
  // --- Overridden methods
  
  public boolean equals(final Object rhs) {
    if ((rhs == null) || ! (rhs instanceof IMemberInfo)) {
      return false;
    }
    final IMemberInfo rhsObj = (IMemberInfo) rhs;
    if (! super.equals(rhsObj) || (this.fX10FlagsCode != rhsObj.getX10FlagsCode())) {
      return false;
    }
    if (this.fDeclaringType == null) {
      return (rhsObj.getDeclaringType() == null); 
    } else {
      return this.fDeclaringType.equals(rhsObj.getDeclaringType());
    }
  }
  
  public int hashCode() {
    return super.hashCode() + this.fX10FlagsCode + ((this.fDeclaringType == null) ? 34543 : this.fDeclaringType.hashCode());
  }
  
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append(super.toString()) 
      .append("\nDeclaring Type: ").append((this.fDeclaringType == null) ? "null" : this.fDeclaringType.getName()) //$NON-NLS-1$ //$NON-NLS-2$
      .append("\nFlags code: ").append(this.fX10FlagsCode); //$NON-NLS-1$
    return sb.toString();
  }
  
  // --- Fields
  
  private final ITypeInfo fDeclaringType;
  
  private final int fX10FlagsCode;

}
