/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.pdb;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.imp.pdb.facts.ISourceLocation;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.eclipse.imp.pdb.facts.impl.fast.ValueFactory;
import org.eclipse.imp.pdb.facts.type.Type;
import org.eclipse.osgi.util.NLS;

import polyglot.types.ClassType;
import polyglot.util.Position;
import polyglot.visit.NodeVisitor;
import x10dt.search.core.Messages;

/**
 * Provides services for facts writing and defines a base for all fact writers that need to browse final AST nodes in order
 * to create the appropriate indexing information.
 * 
 * @author egeay
 */
public class FactWriterVisitor extends NodeVisitor {
  
  protected FactWriterVisitor() {
    this.fValueFactory = ValueFactory.getInstance();
    this.fTypeName = SearchDBTypes.getInstance().getType(X10FactTypeNames.X10_TypeName);
  }
  
  // --- Public services
  
  /**
   * Defines the current scope type.
   * 
   * @param scopeTypeName The name defining the scope type.
   */
  public final void setScopeType(final String scopeTypeName) {
    this.fScopeTypeName = scopeTypeName;
  }
  
  // --- Code for implementers
  
  /**
   * Creates a string value encapsulating the class type qualified name.
   * 
   * @param classType The class type to consider.
   * @return A non-null string value.
   */
  protected IValue createTypeName(final ClassType classType) {
    return this.fTypeName.make(this.fValueFactory, classType.fullName().toString());
  }

  /**
   * Transforms a polyglot position into an IMP PDB source location.
   * 
   * @param position The position to consider.
   * @return The source location equivalent to the position given.
   */
  protected final ISourceLocation getSourceLocation(final Position position) {
    final StringBuilder scheme = new StringBuilder();
    if (position.file().contains(".jar:")) { //$NON-NLS-1$
      scheme.append("jar:"); //$NON-NLS-1$
    }
    scheme.append("file"); //$NON-NLS-1$
    try {
      final URI uri = new URI(scheme.toString(), null /* host */, position.file(), null /* fragment */);
      return this.fValueFactory.sourceLocation(uri, position.offset(), 
                                               position.endOffset() - position.offset(), position.line(), position.endLine(),
                                               position.column(), position.endColumn());
    } catch (URISyntaxException except) {
      throw new RuntimeException(NLS.bind(Messages.FWV_URICreationError, position.file()), except);
    }
  }
  
  protected final ITypeManager getTypeManager(final String typeName) {
    return SearchDBTypes.getInstance().getTypeManager(typeName, this.fScopeTypeName);
  }
  
  /**
   * Returns the current value factory.
   * 
   * @return A non-null implementation of {@link IValueFactory}.
   */
  protected final IValueFactory getValueFactory() {
    return this.fValueFactory;
  }
  
  protected final void insertValue(final String typeName, final IValue ... values) {
    getTypeManager(typeName).getWriter().insert(values);
  }
  
  // --- Fields
  
  private final IValueFactory fValueFactory;
  
  private final Type fTypeName;
  
  
  private String fScopeTypeName;

}
