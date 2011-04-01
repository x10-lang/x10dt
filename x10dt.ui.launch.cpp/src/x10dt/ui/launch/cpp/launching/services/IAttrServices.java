/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching.services;

import java.util.List;

import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.widgets.Display;

/**
 * Encapsulates a launch configuration attribute and provide services for formatting and coloring.
 * 
 * @author egeay
 */
public interface IAttrServices {
  
  /**
   * Adds the attribute with the configured formatting into the string builder and adds the list of necessary style ranges
   * for the coloring if needed.
   * 
   * @param strBuilder The string builder that will contain the formatted attribute.
   * @param display The display to consider for accessing the SWT colors.
   * @param styleRanges The container for the style ranges that will be created.
   */
  public void buildFormattedString(final StringBuilder strBuilder, final Display display, final List<StyleRange> styleRanges);
  
  /**
   * Returns the name of the attribute.
   * 
   * @return A non-null non-empty string.
   */
  public String getName();
  
  /**
   * Returns whether the two attributes encapsulated have the same values. Equality of the names is defined via the 
   * {@link Object#equals(Object)} method.
   * 
   * @param attrData The attribute data to consider for comparison.
   * @return True if they are equals, false otherwise.
   */
  public boolean hasSameValue(final IAttrServices attrData);
  
  /**
   * Defines the SWT colors for the attribute name and value. Such values will be used for creating the appropriate
   * style ranges when calling {@link #buildFormattedString(StringBuilder, Display, List)}.
   * 
   * @param nameColor The color for the name.
   * @param valueColor The color for the value.
   */
  public void setSWTColor(final int nameColor, final int valueColor);

}
