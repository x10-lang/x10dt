/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.engine;

import org.eclipse.imp.pdb.facts.ISourceLocation;

import x10dt.search.core.pdb.X10FlagsEncoder.X10;

/**
 * Provides field information for a given type coming from the search index database.
 * 
 * @author egeay
 */
public interface IFieldInfo {
  
  /**
   * Returns the field type information.
   * 
   * @return A non-null type information.
   */
  public IBasicTypeInfo getFieldTypeInfo();
  
  /**
   * Returns the location of the field declaration.
   * 
   * @return A non-null location.
   */
  public ISourceLocation getLocation();
  
  /**
   * Returns the field name represented.
   * 
   * @return A non-null string.
   */
  public String getName();
  
  /**
   * Returns a code identifying uniquely the list of flags relevant for the field.
   * 
   * <p>Look at {@link X10} to test the existence of some particular flags.
   * 
   * @return A natural number.
   */
  public int getX10FlagsCode();

}
