/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.engine;

/**
 * Provides a type hierarchy for a given type.
 * 
 * <p>Creates instance from {@link X10SearchEngine}.
 * 
 * @author egeay
 */
public interface ITypeHierarchy {
  
  /**
   * Returns true if the given type name is present in the type hierarchy built.
   * 
   * @param typeName The type name to compare it to.
   * @return True if the type name is present in the type hierarchy, false otherwise.
   */
  public boolean contains(final String typeName);
  
  /**
   * Returns all the interfaces present in the type hierarchy built.
   * 
   * @return A non-null, but possibly empty, array of type names.
   */
  public String[] getAllInterfaces();
  
  /**
   * Returns all the sub classes present in the type hierarchy built.
   * 
   * @return A non-null, but possibly empty, array of type names.
   */
  public String[] getAllSubClasses();
  
  /**
   * Returns all the sub types present in the type hierarchy built.
   * 
   * @return A non-null, but possibly empty, array of type names.
   */
  public String[] getAllSubTypes();
  
  /**
   * Returns all the super classes present in the type hierarchy built.
   * 
   * @return A non-null, but possibly empty, array of type names.
   */
  public String[] getAllSuperClasses();
  
  /**
   * Returns all the super interfaces present in the type hierarchy built.
   * 
   * @return A non-null, but possibly empty, array of type names.
   */
  public String[] getAllSuperInterfaces();
  
  /**
   * Returns all the super types present in the type hierarchy built.
   * 
   * @return A non-null, but possibly empty, array of type names.
   */
  public String[] getAllSuperTypes();
  
  /**
   * Returns the direct interfaces for the type for which the type hierarchy built.
   * 
   * @return A non-null, but possibly empty, array of type names.
   */
  public String[] getInterfaces();
  
  /**
   * Returns the super class for the type for which the type hierarchy was built.
   * 
   * @return A non-null, but possibly empty, array of type names.
   */
  public String getSuperClass();

}
