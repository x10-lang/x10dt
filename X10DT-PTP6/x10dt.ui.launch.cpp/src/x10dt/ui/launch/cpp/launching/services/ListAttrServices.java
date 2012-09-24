/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching.services;

import java.util.List;


final class ListAttrServices extends AbstractAttrServices implements IAttrServices {
  
  ListAttrServices(final String name, final boolean withIndent, final List<String> value) {
    super(name, withIndent);
    this.fValue = value;
  }
  
  // --- Interface methods implementation
  
  public boolean hasSameValue(final IAttrServices attrData) {
    if (attrData instanceof ListAttrServices) {
      return this.fValue.equals(((ListAttrServices) attrData).fValue);
    } else {
      return false;
    }
  }
  
  // --- Abstract methods implementation
  
  protected String getStringValue() {
    final StringBuilder sb = new StringBuilder();
    for (final String item : this.fValue) {
      if (sb.length() > 0) {
        sb.append(',');
      }
      sb.append(item);
    }
    return sb.toString();
  }
  
  // --- Fields
  
  private final List<String> fValue;

}
