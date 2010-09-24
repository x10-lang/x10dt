/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.pdb;

import static x10dt.search.core.pdb.X10FactTypeNames.X10_AllTypes;

import polyglot.ast.ClassDecl;
import polyglot.ast.Node;
import polyglot.types.ParsedClassType;
import polyglot.visit.NodeVisitor;


final class AllTypesFactWriterVisitor extends FactWriterVisitor {
  
  AllTypesFactWriterVisitor(final SearchDBTypes sdbTypes) {
    super(sdbTypes);
  }

  // --- Overridden methods
  
  public NodeVisitor enter(final Node node) {
    if (node instanceof ClassDecl) {
      final ClassDecl classDecl = (ClassDecl) node;
      final ParsedClassType parsedClassType = (ParsedClassType) classDecl.classDef().asType();
      insertValue(X10_AllTypes, getValueFactory().tuple(createTypeName(parsedClassType), 
                                                        getSourceLocation(parsedClassType.position())));
    }
    return this;
  }
  
}
