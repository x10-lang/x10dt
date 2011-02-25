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
import org.eclipse.imp.pdb.facts.ITuple;
import org.eclipse.osgi.util.NLS;

import polyglot.ast.ClassDecl;
import polyglot.types.ClassType;
import polyglot.types.Type;
import x10dt.search.core.Messages;
import x10dt.search.core.SearchCoreActivator;


final class TypeHierarchyFactWriter extends AbstractFactWriter implements IFactWriter {
  
  TypeHierarchyFactWriter(final String scopeTypeName) {
    super(scopeTypeName);
    this.fAllMembersFactWriter = new AllMembersFactWriter(scopeTypeName);
  }

  // --- Interface methods implementation

  public void writeFacts(final ClassDecl classDecl) {
    final ClassType classType = classDecl.classDef().asType();
    if (! classType.position().isCompilerGenerated()) {
      this.fAllMembersFactWriter.writeFacts(classDecl);

      final ITuple classTypeTuple = createType(classType);

      final Type superType = classType.superClass();
      final ClassType superClass;
      if (superType == null) {
        superClass = null;
      } else {
        if (superType.isClass()) {
          superClass = superType.toClass();
        } else {
          SearchCoreActivator.log(IStatus.WARNING, NLS.bind(Messages.THFWV_UnknownSuperType, superType, classDecl.name()));
          superClass = null;
        }
      }
      if ((superClass != null) && ! superClass.position().isCompilerGenerated()) {
        insertValue(X10_TypeHierarchy, getValueFactory().tuple(classTypeTuple, createType(superClass)));
      }

      for (final Type interfaceType : classType.interfaces()) {
        if ((interfaceType != null) && (interfaceType instanceof ClassType) &&
            ! interfaceType.position().isCompilerGenerated()) {
          insertValue(X10_TypeHierarchy, getValueFactory().tuple(classTypeTuple, createType(interfaceType)));
        }
      }
    }
  }
  
  // --- Fields
  
  private final IFactWriter fAllMembersFactWriter;

}
