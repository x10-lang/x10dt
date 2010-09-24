/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.imp.pdb.facts.ISourceLocation;
import org.eclipse.imp.pdb.facts.IString;
import org.eclipse.imp.pdb.facts.IValue;

/**
 * Utility methods for testing purposes.
 * 
 * @author egeay
 */
@SuppressWarnings("nls")
public final class TestUtils {
  
  /**
   * Checks some key source location parameters.
   * 
   * @param location The source location to test.
   * @param endPath The end path to test against the source location URI path.
   * @param beginLine The beginning of the line to verify.
   * @param endLine The end of the line to verify.
   */
  public static void assertLocation(final IValue location, final String endPath, final int beginLine, final int endLine) {
    assertTrue(location instanceof ISourceLocation);
    final ISourceLocation sourceLocation = (ISourceLocation) location;
    assertTrue("Condition failed for path: " + sourceLocation.getURI().getPath(), 
               sourceLocation.getURI().getPath().endsWith(endPath));
    assertEquals(beginLine, sourceLocation.getBeginLine());
    assertEquals(endLine, sourceLocation.getEndLine());
  }
  
  /**
   * Returns a fact string value without the surrounding quotes.
   * 
   * @param value The string value to consider.
   * @return The string value without the quotes.
   */
  public static String getString(final IValue value) {
    assertTrue(value instanceof IString);
    final String str = value.toString();
    return str.substring(1, str.length() - 1);
  }
  
  // --- Private code
  
  private TestUtils() {}

}
