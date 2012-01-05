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
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.imp.model.ICompilationUnit;
import org.eclipse.imp.model.ISourceEntity;
import org.eclipse.imp.model.ModelFactory;
import org.eclipse.imp.model.ModelFactory.ModelException;
import org.eclipse.imp.pdb.facts.ISourceLocation;

import x10dt.search.core.elements.IX10Element;


abstract class AbstractX10Element implements IX10Element {
  
  AbstractX10Element(final String name, final ISourceLocation location) {
    this.fName = name;
    this.fLocation = location;
    
    final IFile[] files = ResourcesPlugin.getWorkspace().getRoot().findFilesForLocationURI(location.getURI());
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
  
  // --- Interface methods implementation
  
  public final ICompilationUnit getCompilationUnit() {
    return this.fCompilationUnit;
  }

  public final ISourceLocation getLocation() {
    return this.fLocation;
  }
  
  public final String getName() {
    return this.fName;
  }
  
  public final ISourceEntity getSourceEntity() {
    return this.fCompilationUnit;
  }
  
  // --- Overridden methods
  
  public boolean equals(final Object rhs) {
    if ((rhs == null) || ! (rhs instanceof IX10Element)) {
      return false;
    }
    final IX10Element rhsObj = (IX10Element) rhs;
    return this.fName.equals(rhsObj.getName()) && 
           (this.fLocation == null) ? (rhsObj.getLocation() == null) : this.fLocation.equals(rhsObj.getLocation()); 
  }
  
  public int hashCode() {
    return this.fName.hashCode() + this.fLocation.hashCode();
  }
  
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Name: ").append(this.fName).append("\nLocation: ").append(this.fLocation); //$NON-NLS-1$ //$NON-NLS-2$
    return sb.toString();
  }
  
  // --- Fields
  
  private final String fName;
  
  private final ISourceLocation fLocation;
  
  private ICompilationUnit fCompilationUnit;

}
