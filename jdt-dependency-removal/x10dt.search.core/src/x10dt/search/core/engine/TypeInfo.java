/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.engine;

import java.net.URI;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.imp.pdb.facts.IInteger;
import org.eclipse.imp.pdb.facts.ISourceLocation;
import org.eclipse.imp.pdb.facts.IString;
import org.eclipse.imp.pdb.facts.ITuple;


final class TypeInfo implements ITypeInfo {
  
  TypeInfo(final ITuple tuple) {
    this(((IString) tuple.get(0)).getValue(), (ISourceLocation) tuple.get(1), ((IInteger) tuple.get(2)).intValue());
  }
  
  TypeInfo(final String typeName, final ISourceLocation location, final int x10FlagsCode) {
    this.fLocation = location;
    this.fX10FlagsCode = x10FlagsCode;
    this.fTypeName = typeName;
  }
  
  // --- IBasicTypeInfo's interface methods implementation
  
  public String getName() {
    return this.fTypeName;
  }

  // --- ITypeInfo's interface methods implementation
  
  public boolean exists(final IProgressMonitor monitor) {
    final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
    final URI uri = this.fLocation.getURI();
    if ("jar".equals(uri.getScheme())) { //$NON-NLS-1$
      try {
        return X10SearchEngine.getX10RuntimeTypeInfo(this.fTypeName, monitor) != null;
      } catch (Exception except) {
        // Let's forget about it, since probably it is not good news about the type existence.
        return false;
      }
    } else {
      final IFile[] files = root.findFilesForLocationURI(this.fLocation.getURI());
      if (files.length == 0) {
        return false;
      } else {
        monitor.beginTask(null, files.length);
        for (final IFile file : files) {
          try {
            file.refreshLocal(IResource.DEPTH_ZERO, new NullProgressMonitor());
          } catch (CoreException except) {
            // Let's simply forget in such case.
          }
          if (file.exists()) {
            try {
              if (X10SearchEngine.getTypeInfo(file.getProject(), this.fTypeName, new SubProgressMonitor(monitor, 1)) != null) {
                return true;
              }
            } catch (Exception except) {
              // Let's forget about it, since probably it is not good news about the type existence.
            }
          }
        }
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

}
