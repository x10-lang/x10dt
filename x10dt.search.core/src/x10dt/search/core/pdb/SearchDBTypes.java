/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.pdb;

import static x10dt.search.core.pdb.X10FactTypeNames.APPLICATION;
import static x10dt.search.core.pdb.X10FactTypeNames.LIBRARY;
import static x10dt.search.core.pdb.X10FactTypeNames.RUNTIME;
import static x10dt.search.core.pdb.X10FactTypeNames.X10_AllTypes;
import static x10dt.search.core.pdb.X10FactTypeNames.X10_Type;
import static x10dt.search.core.pdb.X10FactTypeNames.X10_TypeHierarchy;
import static x10dt.search.core.pdb.X10FactTypeNames.X10_TypeName;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.imp.pdb.facts.type.Type;
import org.eclipse.imp.pdb.facts.type.TypeFactory;
import org.eclipse.imp.pdb.facts.type.TypeStore;

/**
 * Manages and provides services for the X10 Search PDB types.
 * 
 * @author egeay
 */
public final class SearchDBTypes {
  
  /**
   * Initializes all the relevant types for X10 Search facilities.
   * 
   * @see X10FactTypeNames
   */
  public SearchDBTypes() {
    final TypeFactory typeFactory = TypeFactory.getInstance();
    this.fTypeStore = new TypeStore();
    
    final Type typeName = typeFactory.aliasType(this.fTypeStore, X10_TypeName, typeFactory.stringType());
    final Type x10Type = typeFactory.aliasType(this.fTypeStore, X10_Type, 
                                               typeFactory.tupleType(typeName, typeFactory.sourceLocationType()));
    
    typeFactory.aliasType(this.fTypeStore, APPLICATION, typeFactory.stringType());
    typeFactory.aliasType(this.fTypeStore, LIBRARY, typeFactory.stringType());
    typeFactory.aliasType(this.fTypeStore, RUNTIME, typeFactory.stringType());
    
    this.fScopeType = typeFactory.parameterType("scope"); //$NON-NLS-1$
    
    typeFactory.aliasType(this.fTypeStore, X10_AllTypes, typeFactory.setType(x10Type), this.fScopeType);
    typeFactory.aliasType(this.fTypeStore, X10_TypeHierarchy, typeFactory.relType(typeName, typeName), this.fScopeType);
  }

  // --- Public services
  
  /**
   * Returns the fact type for the name provided, if any is present.
   * 
   * @param name The type name to look for.
   * @return A non-null type instance if we have found it, otherwise <b>null</b>.
   */
  public Type getType(final String name) {
    return this.fTypeStore.lookupAlias(name);
  }
  
  /**
   * Instantiates a parametric type with the bound type value provided.
   * 
   * @param parametricType The parametric type to consider.
   * @param boundType The bound type value to consider.
   * @return The new type instance after instantiation.
   */
  public Type instantiate(final Type parametricType, final Type boundType) {
    final Map<Type, Type> bindings = new HashMap<Type, Type>(1);
    bindings.put(this.fScopeType, boundType);
    return parametricType.instantiate(bindings);
  }
  
  // --- Internal services
  
  TypeStore getTypeStore() {
    return this.fTypeStore;
  }
  
  // --- Fields
  
  private final TypeStore fTypeStore;
  
  private final Type fScopeType;

}
