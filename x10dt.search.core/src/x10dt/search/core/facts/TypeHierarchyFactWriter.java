/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.facts;

import static x10dt.search.core.pdb.X10FactTypeNames.X10_TypeHierarchy;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.osgi.util.NLS;

import polyglot.ast.ClassDecl;
import polyglot.ast.TypeNode;
import polyglot.types.ClassDef;
import polyglot.types.ClassType;
import x10dt.search.core.Messages;
import x10dt.search.core.SearchCoreActivator;


final class TypeHierarchyFactWriter extends AbstractFactWriter implements IFactWriter {
  
  TypeHierarchyFactWriter(final String scopeTypeName) {
    super(scopeTypeName);
    this.fAllMembersFactWriter = new AllMembersFactWriter(scopeTypeName);
  }

  // --- Interface methods implementation

  public void writeFacts(final ClassDecl classDecl) {
    this.fAllMembersFactWriter.writeFacts(classDecl);
    
    final ClassDef classDef = classDecl.classDef();
    final ClassType classType = classDef.asType();
    final IValue typeNameValue = createTypeName(classType.fullName().toString());

    final TypeNode superTypeNode = classDecl.superClass();
    final ClassType superClass;
    if (superTypeNode == null) {
      superClass = null;
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
      insertValue(X10_TypeHierarchy, getValueFactory().tuple(typeNameValue, createTypeName(superClass.fullName().toString())));
    }

    for (final TypeNode interfaceTypeNode : classDecl.interfaces()) {
      final polyglot.types.Type interfaceType = interfaceTypeNode.type();
      if ((interfaceType != null) && (interfaceType instanceof ClassType)) {
        insertValue(X10_TypeHierarchy,
                    getValueFactory().tuple(typeNameValue, createTypeName(((ClassType) interfaceType).fullName().toString())));
      }
    }
  }
  
  // --- Fields
  
  private final IFactWriter fAllMembersFactWriter;

}
