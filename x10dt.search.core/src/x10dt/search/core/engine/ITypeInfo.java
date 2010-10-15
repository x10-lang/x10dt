/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.engine;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.pdb.facts.ISourceLocation;

import x10dt.search.core.pdb.X10FlagsEncoder.X10;

/**
 * Provides type information for a type name coming from the search index database.
 * 
 * @author egeay
 */
public interface ITypeInfo extends IBasicTypeInfo {
  
  /**
   * Indicates if the underlying type still exists in the indexing database.
   * 
   * @param monitor Monitor to report progress and/or cancel the operation.
   * @return True if it exists, false otherwise.
   */
  public boolean exists(final IProgressMonitor monitor);
  
  /**
   * Returns the location of the type declaration.
   * 
   * @return A non-null location.
   */
  public ISourceLocation getLocation();
  
  /**
   * Returns a code identifying uniquely the list of flags relevant for the type.
   * 
   * <p>Look at {@link X10} to test the existence of some particular flags.
   * 
   * @return A natural number.
   */
  public int getX10FlagsCode();

}
