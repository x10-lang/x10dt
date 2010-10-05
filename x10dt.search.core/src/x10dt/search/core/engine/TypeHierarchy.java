/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.engine;

import static x10dt.search.core.pdb.X10FactTypeNames.APPLICATION;
import static x10dt.search.core.pdb.X10FactTypeNames.LIBRARY;
import static x10dt.search.core.pdb.X10FactTypeNames.RUNTIME;
import static x10dt.search.core.pdb.X10FactTypeNames.X10_AllTypes;
import static x10dt.search.core.pdb.X10FactTypeNames.X10_TypeHierarchy;
import static x10dt.search.core.pdb.X10FactTypeNames.X10_TypeName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.imp.model.ModelFactory;
import org.eclipse.imp.model.ModelFactory.ModelException;
import org.eclipse.imp.pdb.facts.IInteger;
import org.eclipse.imp.pdb.facts.ISet;
import org.eclipse.imp.pdb.facts.IString;
import org.eclipse.imp.pdb.facts.ITuple;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.db.FactBase;
import org.eclipse.imp.pdb.facts.db.IFactContext;
import org.eclipse.imp.pdb.facts.db.context.ProjectContext;
import org.eclipse.imp.pdb.facts.db.context.WorkspaceContext;
import org.eclipse.imp.pdb.facts.impl.fast.ValueFactory;
import org.eclipse.imp.pdb.facts.type.Type;
import org.eclipse.osgi.util.NLS;

import x10dt.search.core.Messages;
import x10dt.search.core.SearchCoreActivator;
import x10dt.search.core.pdb.SearchDBTypes;
import x10dt.search.core.pdb.X10FlagsEncoder.X10;
import x10dt.search.core.utils.FactBaseUtils;


final class TypeHierarchy implements ITypeHierarchy {
  
  TypeHierarchy(final IProject project, final String typeName, 
                final IProgressMonitor monitor) throws ModelException, InterruptedException {
    final IFactContext context = new ProjectContext(ModelFactory.open(project));
    final FactBase factBase = FactBase.getInstance();
    
    if (monitor.isCanceled()) {
      throw new InterruptedException();
    }
    
    this.fSuperTypes = new HashSet<ITuple>();
    this.fSubTypes = new HashSet<ITuple>();
    this.fDirectSuperTypes = new HashSet<ITuple>();

    final Job buildJob = new Job(Messages.TH_BuildTHJobName) {
      
      // --- Abstract methods implementation
      
      protected IStatus run(final IProgressMonitor jobMonitor) {
        try {
          jobMonitor.beginTask(null, 20);
          jobMonitor.subTask(Messages.TH_CollectingData);
          final ISet allTypesAppValue = FactBaseUtils.getFactBaseSetValue(factBase, context, X10_AllTypes, APPLICATION,
                                                                          monitor);
          final ISet allTypesLibValue = FactBaseUtils.getFactBaseSetValue(factBase, context, X10_AllTypes, LIBRARY,
                                                                          monitor);
          final ISet allTypesRuntimeValue = FactBaseUtils.getFactBaseSetValue(factBase, WorkspaceContext.getInstance(), 
                                                                              X10_AllTypes, RUNTIME, monitor);
          
          final Type typeNameType = SearchDBTypes.getInstance().getType(X10_TypeName);
          final IValue typeNameValue = typeNameType.make(ValueFactory.getInstance(), typeName);
          
          final ISet typeHierarchyAppValue = FactBaseUtils.getFactBaseSetValue(factBase, context, X10_TypeHierarchy, 
                                                                               APPLICATION, monitor);
          final ISet typeHierarchyLibValue = FactBaseUtils.getFactBaseSetValue(factBase, context, X10_TypeHierarchy, LIBRARY,
                                                                               monitor);
          final ISet typeHierarchyRuntimeValue = FactBaseUtils.getFactBaseSetValue(factBase, WorkspaceContext.getInstance(),
                                                                                   X10_TypeHierarchy, RUNTIME, monitor);
          jobMonitor.worked(1);
          
          buildHierarchy(union(allTypesAppValue, allTypesLibValue, allTypesRuntimeValue),
                         union(typeHierarchyAppValue, typeHierarchyLibValue, typeHierarchyRuntimeValue),
                         typeNameValue, new SubProgressMonitor(monitor, 19));
          return Status.OK_STATUS;
        } catch (InterruptedException except) {
          return new Status(IStatus.CANCEL, SearchCoreActivator.PLUGIN_ID, Messages.TH_SearchCanceled);
        } catch (ExecutionException except) {
          return new Status(IStatus.ERROR, SearchCoreActivator.PLUGIN_ID, Messages.TH_FactAccessError, except);
        }
      }
      
    };
    buildJob.schedule();
    buildJob.join();
  }
  
  // --- Interface methods implementation

  public boolean contains(final String typeName) {
    for (final ITuple tuple : this.fSuperTypes) {
      if (typeName.equals(((IString) tuple.get(0)).getValue())) {
        return true;
      }
    }
    for (final ITuple tuple : this.fSubTypes) {
      if (typeName.equals(((IString) tuple.get(0)).getValue())) {
        return true;
      }
    }
    return false;
  }

  public String[] getAllInterfaces() {
    final Collection<String> interfaces = new ArrayList<String>();
    for (final ITuple tuple : this.fSuperTypes) {
      final int flags = ((IInteger) tuple.get(2)).intValue();
      if ((X10.INTERFACE.getCode() & flags) != 0) {
        interfaces.add(((IString) tuple.get(0)).getValue());
      }
    }
    for (final ITuple tuple : this.fSubTypes) {
      final int flags = ((IInteger) tuple.get(2)).intValue();
      if ((X10.INTERFACE.getCode() & flags) != 0) {
        interfaces.add(((IString) tuple.get(0)).getValue());
      }
    }
    return interfaces.toArray(new String[interfaces.size()]);
  }
  
  public String[] getAllSubClasses() {
    final Collection<String> interfaces = new ArrayList<String>();
    for (final ITuple tuple : this.fSubTypes) {
      final int flags = ((IInteger) tuple.get(2)).intValue();
      if ((X10.INTERFACE.getCode() & flags) == 0) {
        interfaces.add(((IString) tuple.get(0)).getValue());
      }
    }
    return interfaces.toArray(new String[interfaces.size()]);
  }
  
  public String[] getAllSubTypes() {
    final Collection<String> interfaces = new ArrayList<String>();
    for (final ITuple tuple : this.fSubTypes) {
      interfaces.add(((IString) tuple.get(0)).getValue());
    }
    return interfaces.toArray(new String[interfaces.size()]);
  }
  
  public String[] getAllSuperClasses() {
    final Collection<String> interfaces = new ArrayList<String>();
    for (final ITuple tuple : this.fSuperTypes) {
      final int flags = ((IInteger) tuple.get(2)).intValue();
      if ((X10.INTERFACE.getCode() & flags) == 0) {
        interfaces.add(((IString) tuple.get(0)).getValue());
      }
    }
    return interfaces.toArray(new String[interfaces.size()]);
  }
  
  public String[] getAllSuperInterfaces() {
    final Collection<String> interfaces = new ArrayList<String>();
    for (final ITuple tuple : this.fSuperTypes) {
      final int flags = ((IInteger) tuple.get(2)).intValue();
      if ((X10.INTERFACE.getCode() & flags) != 0) {
        interfaces.add(((IString) tuple.get(0)).getValue());
      }
    }
    return interfaces.toArray(new String[interfaces.size()]);
  }
  
  public String[] getAllSuperTypes() {
    final Collection<String> interfaces = new ArrayList<String>();
    for (final ITuple tuple : this.fSuperTypes) {
      interfaces.add(((IString) tuple.get(0)).getValue());
    }
    return interfaces.toArray(new String[interfaces.size()]);
  }
  
  public String[] getInterfaces() {
    final Collection<String> interfaces = new ArrayList<String>();
    for (final ITuple tuple : this.fDirectSuperTypes) {
      final int flags = ((IInteger) tuple.get(2)).intValue();
      if ((X10.INTERFACE.getCode() & flags) != 0) {
        interfaces.add(((IString) tuple.get(0)).getValue());
      }
    }
    return interfaces.toArray(new String[interfaces.size()]);
  }
  
  public String getSuperClass() {
    String superClass = null;
    for (final ITuple tuple : this.fDirectSuperTypes) {
      final int flags = ((IInteger) tuple.get(2)).intValue();
      if ((X10.INTERFACE.getCode() & flags) == 0) {
        superClass = ((IString) tuple.get(0)).getValue();
        break;
      }
    }
    return superClass;
  }
  
  // --- Overridden methods
  
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Super Types:"); //$NON-NLS-1$
    for (final ITuple tuple : this.fSuperTypes) {
      sb.append('\n').append(tuple);
    }
    sb.append("\nSub Types:"); //$NON-NLS-1$
    for (final ITuple tuple : this.fSubTypes) {
      sb.append('\n').append(tuple);
    }
    return sb.toString();
  }
  
  // --- Private code
  
  private void buildHierarchy(final ISet allTypes, final ISet globalTypeHierarchy, final IValue typeNameValue,
                              final IProgressMonitor monitor) throws InterruptedException {
    final Queue<IValue> subTypesWork = new LinkedList<IValue>();
    final Queue<IValue> superTypesWork = new LinkedList<IValue>();
    
    subTypesWork.add(typeNameValue);
    superTypesWork.add(typeNameValue);
    
    final SubMonitor progress = SubMonitor.convert(monitor);
    progress.subTask(Messages.TH_BuildTHTaskName);
    
    int counter = 0;
    
    while (subTypesWork.size() > 0 || superTypesWork.size() > 0) {
      if (progress.isCanceled()) {
        throw new InterruptedException();
      }
      progress.setWorkRemaining(subTypesWork.size() + superTypesWork.size());
      
      final IValue superWorkItem = superTypesWork.isEmpty() ? null : superTypesWork.poll();
      final IValue subWorkItem = subTypesWork.isEmpty() ? null : subTypesWork.poll();
      
      for (final IValue value : globalTypeHierarchy) {
        final ITuple tuple = (ITuple) value;
        if ((superWorkItem != null) && superWorkItem.equals(tuple.get(0))) {
          final ITuple element = getTypeInfo(allTypes, tuple.get(1));
          this.fSuperTypes.add(element);
          if (counter == 0) {
            this.fDirectSuperTypes.add(element);
          }
          superTypesWork.offer(tuple.get(1));
          progress.worked(1);
        }
        if ((subWorkItem != null) && subWorkItem.equals(tuple.get(1))) {
          final ITuple element = getTypeInfo(allTypes, tuple.get(0));
          this.fSubTypes.add(element);
          subTypesWork.offer(tuple.get(0));
          progress.worked(1);
        }
      }
      
      counter = 1;
    }
    
    progress.done();
  }
  
  private ITuple getTypeInfo(final ISet allTypes, final IValue typeName) {
    for (final IValue value : allTypes) {
      final ITuple tuple = (ITuple) value;
      if (tuple.get(0).equals(typeName)) {
        return tuple;
      }
    }
    throw new AssertionError(NLS.bind(Messages.TH_NoTypeFoundError, typeName));
  }
  
  private <T extends ISet> T union(final T ... sets) {
    T finalSet = null;
    for (final T set : sets) {
      if (finalSet == null) {
        if (set != null) {
          finalSet = set;
        }
      } else if (set != null) {
        finalSet = finalSet.union(set);
      }
    }
    return finalSet;
  }
  
  // --- Fields
  
  private final Set<ITuple> fSubTypes;
  
  private final Set<ITuple> fSuperTypes;
  
  private Set<ITuple> fDirectSuperTypes;

}
