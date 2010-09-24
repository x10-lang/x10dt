/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.pdb;

import static x10dt.search.core.pdb.X10FactTypeNames.X10_AllTypes;
import static x10dt.search.core.pdb.X10FactTypeNames.X10_TypeHierarchy;

import org.eclipse.imp.pdb.analysis.IFactGenerator;
import org.eclipse.imp.pdb.analysis.IFactGeneratorFactory;
import org.eclipse.imp.pdb.facts.type.Type;
import org.eclipse.imp.pdb.facts.type.TypeStore;

/**
 * Factory class to create the {@link TypeStore} and {@link IFactGenerator} implementations for X10 searching activities.
 * 
 * @author egeay
 */
public final class X10FactGeneratorFactory implements IFactGeneratorFactory {

  // --- Interface methods implementation
  
  public String getName() {
    return "X10 Fact Generator Factory"; //$NON-NLS-1$
  }

  public IFactGenerator create(final Type type) {
    if (X10_AllTypes.equals(type.getName())) {
      return new X10FactGenerator(new AllTypesFactWriterVisitor(this.fSearchDBTypes));
    } else if (X10_TypeHierarchy.equals(type.getName())) {
      return new X10FactGenerator(new TypeHierarchyFactWriterVisitor(this.fSearchDBTypes));
    } else {
      return null;
    }
  }

  public TypeStore declareTypes() {
    this.fSearchDBTypes = new SearchDBTypes();
    return this.fSearchDBTypes.getTypeStore();
  }
  
  // --- Fields
  
  private SearchDBTypes fSearchDBTypes;

}
