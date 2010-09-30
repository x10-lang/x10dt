/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.pdb;

import org.eclipse.imp.pdb.facts.IWriter;
import org.eclipse.imp.pdb.facts.type.Type;


abstract class AbstractTypeManager implements ITypeManager {
  
  AbstractTypeManager(final Type type) {
    this.fType = type;
  }
  
  // --- Abstract methods implementation

  public final Type getType() {
    return this.fType;
  }
  
  public final IWriter getWriter() {
    return this.fWriter;
  }
  
  // --- Fields
  
  private final Type fType;
  
  protected IWriter fWriter;

}
