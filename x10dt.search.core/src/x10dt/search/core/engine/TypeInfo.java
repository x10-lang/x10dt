/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.engine;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.imp.model.ICompilationUnit;
import org.eclipse.imp.model.ModelFactory;
import org.eclipse.imp.model.ModelFactory.ModelException;
import org.eclipse.imp.pdb.facts.IInteger;
import org.eclipse.imp.pdb.facts.ISourceLocation;
import org.eclipse.imp.pdb.facts.IString;
import org.eclipse.imp.pdb.facts.ITuple;

import x10dt.search.core.engine.scope.SearchScopeFactory;
import x10dt.search.core.engine.scope.X10SearchScope;


final class TypeInfo implements ITypeInfo {
  
  TypeInfo(final ITuple tuple) {
    this(((IString) tuple.get(0)).getValue(), (ISourceLocation) tuple.get(1), ((IInteger) tuple.get(2)).intValue());
  }
  
  TypeInfo(final String typeName, final ISourceLocation location, final int x10FlagsCode) {
    this.fLocation = location;
    this.fX10FlagsCode = x10FlagsCode;
    this.fTypeName = typeName;
    
    final IFile[] files = ResourcesPlugin.getWorkspace().getRoot().findFilesForLocationURI(this.fLocation.getURI());
    if (files.length == 0) {
      this.fCompilationUnit = null;
    } else {
      int counter = 0;
      for (int i = 0; i < files.length; ++i) {
        try {
          files[i].refreshLocal(IResource.DEPTH_ZERO, new NullProgressMonitor());
        } catch (CoreException except) {
          // Let's simply forget in such case.
        }
        if (files[i].exists()) {
          ++counter;
        } else {
          files[i] = null;
        }
      }
      if (counter == 1) {
        for (final IFile file : files) {
          if (file != null) {
            try {
              this.fCompilationUnit = ModelFactory.open(file, ModelFactory.open(file.getProject()));
            } catch (ModelException except) {
              // This should never occur since we already have tested the file existence.
            }
            break;
          }
        }
      } else {
        this.fCompilationUnit = null;
      }
    }
  }
  
  // --- IBasicTypeInfo's interface methods implementation
  
  public String getName() {
    return this.fTypeName;
  }

  // --- ITypeInfo's interface methods implementation
  
  public boolean exists(final IProgressMonitor monitor) {
    if (this.fCompilationUnit == null) {
      try {
        return X10SearchEngine.getTypeInfo(SearchScopeFactory.createWorkspaceScope(X10SearchScope.ALL), this.fTypeName, 
                                           monitor).length > 0;
      } catch (Exception except) {
        return false;
      }
    } else {
      final IFile file = this.fCompilationUnit.getFile();
      try {
        return X10SearchEngine.getTypeInfo(SearchScopeFactory.createSelectiveScope(X10SearchScope.ALL, file), this.fTypeName, 
                                           monitor).length > 0;
      } catch (Exception except) {
        return false;
      }
    }
  }
  
  public ISourceLocation getLocation() {
    return this.fLocation;
  }

  public int getX10FlagsCode() {
    return this.fX10FlagsCode;
  }
  
  public ICompilationUnit getCompilationUnit() {
    return this.fCompilationUnit;
  }
  
  // --- Overridden methods
  
  public boolean equals(final Object rhs) {
    if ((rhs == null) || ! (rhs instanceof ITypeInfo)) {
      return false;
    }
    final ITypeInfo rhsObj = (ITypeInfo) rhs;
    return this.fTypeName.equals(rhsObj.getName()) && this.fLocation.equals(rhsObj.getLocation()) &&
           (this.fX10FlagsCode == rhsObj.getX10FlagsCode()); 
  }
  
  public int hashCode() {
    return this.fTypeName.hashCode() + this.fLocation.hashCode() + this.fX10FlagsCode;
  }
  
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Name: ").append(this.fTypeName).append("\nLocation: ").append(this.fLocation) //$NON-NLS-1$ //$NON-NLS-2$
      .append("\nFlags Code: ").append(this.fX10FlagsCode); //$NON-NLS-1$
    return sb.toString();
  }
  
  // --- Private code
  
  private final ISourceLocation fLocation;
  
  private final int fX10FlagsCode;
  
  private final String fTypeName;
  
  private ICompilationUnit fCompilationUnit;

}
