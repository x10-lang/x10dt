/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching.services;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;


abstract class AbstractAttrServices implements IAttrServices {
  
  AbstractAttrServices(final String name, final boolean withIndent) {
    this.fName = name;
    this.fWithIndent = withIndent;
  }
  
  // --- Abstract methods definition
  
  protected abstract String getStringValue();
  
  // --- Interface methods implementation
  
  public final void buildFormattedString(final StringBuilder strBuilder, final Display display, 
                                         final List<StyleRange> styleRanges) {
    int startOffset = strBuilder.length();

    if (! this.fWithIndent) {
      strBuilder.append('\t');
    }
    strBuilder.append(this.fName);
    final Color foregroundColor = (this.fSWTNameColor == SWT.NONE) ? null : display.getSystemColor(this.fSWTNameColor);
    final StyleRange styleRange = new StyleRange(startOffset, strBuilder.length() - startOffset, foregroundColor, null);
    strBuilder.append(':');
    if (! this.fWithIndent) {
      styleRange.underline = true;
    }
    styleRanges.add(styleRange);
    
    int sepIndex = strBuilder.length();
    strBuilder.append(' ').append(getStringValue());
    styleRanges.add(new StyleRange(sepIndex, strBuilder.length() - sepIndex, 
                                   (this.fSWTValueColor == SWT.NONE) ? null : display.getSystemColor(this.fSWTValueColor), 
                                   null, SWT.BOLD));
    
    strBuilder.append('\n');
  }
  
  public String getName() {
    return this.fName;
  }
  
  public final void setSWTColor(final int nameColor, final int valueColor) {
    this.fSWTNameColor = nameColor;
    this.fSWTValueColor = valueColor;
  }
  
  // --- Overridden methods
  
  public boolean equals(final Object rhs) {
    if ((rhs == null) || ! (rhs instanceof IAttrServices)) {
      return false;
    }
    final IAttrServices rhsObj = (IAttrServices) rhs;
    return this.fName.equals(rhsObj.getName());
  }
  
  public int hashCode() {
    return this.fName.hashCode();
  }
  
  public String toString() {
    return String.format("%s=%s", this.fName, getStringValue()); //$NON-NLS-1$
  }
  
  // --- Fields
  
  private final String fName;
  
  private final boolean fWithIndent;
  
  private int fSWTNameColor;
  
  private int fSWTValueColor;

}
