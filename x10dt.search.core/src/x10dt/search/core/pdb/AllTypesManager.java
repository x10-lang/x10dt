/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.pdb;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.imp.pdb.analysis.AnalysisException;
import org.eclipse.imp.pdb.facts.ISet;
import org.eclipse.imp.pdb.facts.ISourceLocation;
import org.eclipse.imp.pdb.facts.IString;
import org.eclipse.imp.pdb.facts.ITuple;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.db.FactBase;
import org.eclipse.imp.pdb.facts.db.FactKey;
import org.eclipse.imp.pdb.facts.db.IFactContext;
import org.eclipse.imp.pdb.facts.db.IFactKey;
import org.eclipse.imp.pdb.facts.impl.fast.ValueFactory;
import org.eclipse.imp.pdb.facts.type.Type;
import org.eclipse.osgi.util.NLS;

import x10dt.search.core.Messages;
import x10dt.ui.launch.core.utils.IFilter;



final class AllTypesManager extends AbstractTypeManager implements ITypeManager {
  
  AllTypesManager(final Type type) {
    super(type);
  }
  
  // --- Interface methods implementation
  
  public void clearWriter() {
    super.fWriter = null;
  }
  
  public FactWriterVisitor createNodeVisitor() {
    return new AllTypesFactWriterVisitor();
  }
  
  public void writeDataInFactBase(final FactBase factBase, final IFactContext factContext) {
    factBase.defineFact(new FactKey(getType(), factContext), getWriter().done());
  }
  
  public final void initWriter() {
    super.fWriter = getType().writer(ValueFactory.getInstance());
  }

  public void initWriter(final FactBase factBase, final IFactContext factContext, 
                         final IResource resource) throws AnalysisException {
    initWriter(factBase, factContext, resource, new HashSet<IString>());
  }
  
  // --- Internal services
  
  void initWriter(final FactBase factBase, final IFactContext factContext, final IResource resource, 
                  final Set<IString> typesToRemove) throws AnalysisException {
    initWriter();
    
    final IFactKey key = new FactKey(getType(), factContext);
    if (factBase.getAllKeys().contains(key)) {
      final URI resourceURI = resource.getLocationURI();
      final IFilter<ITuple> tupleFilter;
      if (resource.getType() == IResource.FILE) {
        tupleFilter = new FileTupleFilter(resourceURI);
      } else {
        tupleFilter = new ContainerTupleFilter(resourceURI.toString());
      }
      
      final ISet currentSet = (ISet) factBase.getFact(key);
      if (currentSet == null) {
        throw new AnalysisException(NLS.bind(Messages.ATM_NoDataBaseValue, key));
      }
      for (final IValue value : currentSet) {
        final ITuple tuple = (ITuple) value;
        if (tupleFilter.accepts(tuple)) {
          typesToRemove.add((IString) tuple.get(0));
        } else {
          getWriter().insert(value);
        }
      }
    }
  }
  
  // --- Private classes
  
  private static final class ContainerTupleFilter implements IFilter<ITuple> {
    
    ContainerTupleFilter(final String locationURI) {
      this.fLocationURI = locationURI;
    }

    // --- Interface methods implementation
    
    public boolean accepts(final ITuple tuple) {
      final ISourceLocation sourceLoc = (ISourceLocation) tuple.get(1);
      return sourceLoc.getURI().getPath().startsWith(this.fLocationURI);
    }
    
    // --- Fields
    
    private final String fLocationURI;
    
  }
  
  private static final class FileTupleFilter implements IFilter<ITuple> {
    
    FileTupleFilter(final URI locationURI) {
      this.fLocationURI = locationURI;
    }

    // --- Interface methods implementation
    
    public boolean accepts(final ITuple tuple) {
      final ISourceLocation sourceLoc = (ISourceLocation) tuple.get(1);
      return sourceLoc.getURI().equals(this.fLocationURI);
    }
    
    // --- Fields
    
    private final URI fLocationURI;
    
  }

}
