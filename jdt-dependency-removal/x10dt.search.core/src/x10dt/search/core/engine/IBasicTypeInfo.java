/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.engine;

/**
 * Provides the most basic information for a type, which is its name. Needed when no other information is available for 
 * a type. In most other cases, {@link ITypeInfo} should be accessible.
 * 
 * @author egeay
 */
public interface IBasicTypeInfo {
  
  /**
   * Returns the type name represented.
   * 
   * @return A non-null string.
   */
  public String getName();

}
