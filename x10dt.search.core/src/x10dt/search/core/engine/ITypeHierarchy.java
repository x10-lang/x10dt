/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.engine;

import org.eclipse.core.resources.IProject;

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
   * Returns all the classes present in the type hierarchy built.
   * 
   * @return A non-null, but possibly empty, array of type names.
   */
  public String[] getAllClasses();
  
  /**
   * Returns all the interfaces present in the type hierarchy built.
   * 
   * @return A non-null, but possibly empty, array of type names.
   */
  public String[] getAllInterfaces();
  
  /**
   * Returns all the sub classes present in the type hierarchy built for the given type name.
   * 
   * @param typeName The type name of interest.
   * @return A non-null, but possibly empty, array of type names.
   */
  public String[] getAllSubClasses(final String typeName);
  
  /**
   * Returns all the sub types present in the type hierarchy built for the given type name.
   * 
   * @param typeName The type name of interest.
   * @return A non-null, but possibly empty, array of type names.
   */
  public String[] getAllSubTypes(final String typeName);
  
  /**
   * Returns all the super classes present in the type hierarchy built for the given type name.
   * 
   * @param typeName The type name of interest.
   * @return A non-null, but possibly empty, array of type names.
   */
  public String[] getAllSuperClasses(final String typeName);
  
  /**
   * Returns all the super interfaces present in the type hierarchy built for the given type name.
   * 
   * @param typeName The type name of interest.
   * @return A non-null, but possibly empty, array of type names.
   */
  public String[] getAllSuperInterfaces(final String typeName);
  
  /**
   * Returns all the super types present in the type hierarchy built for the given type name.
   * 
   * @param typeName The type name of interest.
   * @return A non-null, but possibly empty, array of type names.
   */
  public String[] getAllSuperTypes(final String typeName);
  
  /**
   * Returns the direct interfaces in the type hierarchy built for the given type name.
   * 
   * @param typeName The type name of interest.
   * @return A non-null, but possibly empty, array of type names.
   */
  public String[] getInterfaces(final String typeName);
  
  /**
   * Returns the project in which the main type given to build hierarchy is located.
   * 
   * @return A non-null project instance.
   */
  public IProject getProject();
  
  /**
   * Returns the super class in the type hierarchy built for the given type name.
   * 
   * @param typeName The type name of interest.
   * @return A non-null value if such type name has a super class, otherwise <b>null</b>.
   */
  public String getSuperClass(final String typeName);
  
  /**
   * Returns the type for which the type hierarchy was built.
   * 
   * @return A non-null non-empty string.
   */
  public String getType();

}
