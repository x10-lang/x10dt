/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.elements;


/**
 * Provides minimal type information for a type name coming from the search index database.
 * 
 * @author egeay
 */
public interface ITypeBaseInfo extends IX10Element {
  
  /**
   * Indicates if this type definition is a type parameter or not.
   * If it returns true it can safely be casted into an {@link ITypeParameterInfo}. Otherwise it can safely be casted into
   * an {@link ITypeInfo}.
   * 
   * @return True if it is a type parameter, false otherwise.
   */
  public boolean isTypeParameter();
  
}
