/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.pdb;

import org.eclipse.imp.pdb.facts.ISourceLocation;

/**
 * The list of X10 fact type names that are supported by X10 Search Engine.
 * 
 * <p>Certain of the resulting facts are also considered outputs of the IMP PDB database. 
 * See the extension point implementation of <b>org.eclipse.imp.pdb.analyzerFactory</b>.
 * 
 * @author egeay
 */
public final class X10FactTypeNames {
  
  /**
   * Fact type name identifying X10 type as a string with a fully qualified name.
   */
  public static final String X10_TypeName = "x10.typeName"; //$NON-NLS-1$
  
  /**
   * Fact type name identifying X10 type as a pair of (String, {@link ISourceLocation}).
   */
  public static final String X10_Type = "x10.type"; //$NON-NLS-1$
  
  // --- Outputs
  
  /**
   * Fact type name identifying all X10 types as a set of X10 type name (see {@link #X10_TypeName}).
   */
  public static final String X10_AllTypes = "x10.all.types"; //$NON-NLS-1$
  
  /**
   * Fact type name identifying an X10 type hierarchy as a binary relation of a subset of 
   * {@value #X10_TypeName} X {@value #X10_TypeName}.
   */
  public static final String X10_TypeHierarchy = "x10.typeHierarchy"; //$NON-NLS-1$
  
  // --- Scope
  
  /**
   * Fact type name identifying the application scope.
   */
  public static final String APPLICATION = "application"; //$NON-NLS-1$
  
  /**
   * Fact type name identifying the library scope.
   */
  public static final String LIBRARY = "library"; //$NON-NLS-1$
  
  /**
   * Fact type name identifying the runtime scope.
   */
  public static final String RUNTIME = "runtime"; //$NON-NLS-1$

}
