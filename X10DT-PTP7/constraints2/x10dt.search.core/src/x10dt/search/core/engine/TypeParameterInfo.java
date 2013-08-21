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

import x10dt.search.core.elements.ITypeParameterInfo;


final class TypeParameterInfo extends AbstractX10Element implements ITypeParameterInfo {

  TypeParameterInfo(final String typeName, final ISourceLocation sourceLocation) {
    super(typeName, sourceLocation);
  }
  
  // --- IX10Element's interface methods implementation
  
  public boolean exists(final IProgressMonitor monitor) {
    return true;
  }
  
  // --- ITypeBaseInfo's interface methods implementation
  
  public boolean isTypeParameter() {
    return true;
  }

}
