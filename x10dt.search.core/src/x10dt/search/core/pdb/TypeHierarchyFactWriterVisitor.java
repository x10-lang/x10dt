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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.osgi.util.NLS;

import polyglot.ast.ClassDecl;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;
import polyglot.types.ClassType;
import polyglot.types.ParsedClassType;
import polyglot.visit.NodeVisitor;
import x10dt.search.core.Messages;
import x10dt.search.core.SearchCoreActivator;


final class TypeHierarchyFactWriterVisitor extends FactWriterVisitor {
  
  TypeHierarchyFactWriterVisitor(final SearchDBTypes sdbTypes) {
    super(sdbTypes);
  }
  
  // --- Overridden methods
  
  public NodeVisitor enter(final Node node) {
    if (node instanceof ClassDecl) {
      final ClassDecl classDecl = (ClassDecl) node;
      final ParsedClassType parsedClassType = (ParsedClassType) classDecl.classDef().asType();
      insertValue(X10_AllTypes, getValueFactory().tuple(createTypeName(parsedClassType), 
                                                        getSourceLocation(parsedClassType.position())));
      
      final TypeNode superTypeNode = classDecl.superClass();
      
      final ClassType superClass;
      if (superTypeNode == null) {
        // If none is declared, by default x10.lang.Object is the parent.
        superClass = (ClassType) parsedClassType.typeSystem().Object();
      } else {
        final polyglot.types.Type superType = superTypeNode.type();
        if (superType.isClass()) {
          superClass = superType.toClass();
        } else {
          SearchCoreActivator.log(IStatus.WARNING, NLS.bind(Messages.THFWV_UnknownSuperType, superType, classDecl.name()));
          superClass = null;
        }
      }
      if (superClass != null) {
        final IValue classTypeValue = createTypeName(parsedClassType);
        insertValue(X10_TypeHierarchy, getValueFactory().tuple(classTypeValue, createTypeName(superClass)));
        for (final TypeNode interfaceTypeNode : classDecl.interfaces()) {
          final polyglot.types.Type interfaceType = interfaceTypeNode.type();
          if ((interfaceType != null) && (interfaceType instanceof ClassType)) {
            insertValue(X10_TypeHierarchy, getValueFactory().tuple(classTypeValue, createTypeName((ClassType) interfaceType)));
          }
        }
      }
    }
    return this;
  }

}
