/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching.services;


final class BooleanAttrServices extends AbstractAttrServices implements IAttrServices {
  
  BooleanAttrServices(final String name, final boolean withIndent, final boolean value) {
    super(name, withIndent);
    this.fValue = value;
  }
  
  // --- Interface methods implementation
  
  public boolean hasSameValue(final IAttrServices attrData) {
    if (attrData instanceof BooleanAttrServices) {
      return this.fValue == ((BooleanAttrServices) attrData).fValue;
    } else {
      return false;
    }
  }
  
  // --- Abstract methods implementation
  
  protected String getStringValue() {
    return String.valueOf(this.fValue);
  }
  
  // --- Fields
  
  private final boolean fValue;

}
