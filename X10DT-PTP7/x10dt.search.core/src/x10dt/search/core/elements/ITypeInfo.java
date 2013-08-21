/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.elements;

/**
 * Provides access to a general X10 type definition.
 * 
 * @author egeay
 */
public interface ITypeInfo extends ITypeBaseInfo, IMemberInfo {
  
  /**
   * Returns the potential generic type parameters for the given type.
   * 
   * @return A non-null but possibly empty list of type parameters.
   */
  public ITypeBaseInfo[] getTypeParameters();

}
