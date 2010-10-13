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
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
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
    
    this.fProject = project;
    this.fTypeName = typeName;
    this.fClassToSuperClass = new HashMap<String, String>();
    this.fTypeToSuperInterfaces = new HashMap<String, Set<String>>();
    this.fTypeToSubInterfaces = new HashMap<String, Set<String>>();
    this.fTypeToSubClasses = new HashMap<String, Set<String>>();
    this.fAllTypes = new HashSet<ITuple>();

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
    for (final ITuple tuple : this.fAllTypes) {
      if (typeName.equals(((IString) tuple.get(0)).getValue())) {
        return true;
      }
    }
    return false;
  }
  
  public String[] getAllClasses() {
    final Set<String> classes = new HashSet<String>();
    for (final ITuple tuple : this.fAllTypes) {
      final int typeFlags = ((IInteger) tuple.get(2)).intValue();
      if ((X10.INTERFACE.getCode() & typeFlags) == 0) {
        classes.add(((IString) tuple.get(0)).getValue());
      }
    }
    return classes.toArray(new String[classes.size()]);
  }

  public String[] getAllInterfaces() {
    final Set<String> interfaces = new HashSet<String>();
    for (final ITuple tuple : this.fAllTypes) {
      final int typeFlags = ((IInteger) tuple.get(2)).intValue();
      if ((X10.INTERFACE.getCode() & typeFlags) != 0) {
        interfaces.add(((IString) tuple.get(0)).getValue());
      }
    }
    return interfaces.toArray(new String[interfaces.size()]);
  }
  
  public String[] getAllSubClasses(final String typeName) {
    final Set<String> subClasses = new HashSet<String>();
    collectMapElements(this.fTypeToSubClasses, subClasses, typeName);
    return subClasses.toArray(new String[subClasses.size()]);
  }
  
  public String[] getAllSubTypes(final String typeName) {
    final Set<String> subTypes = new HashSet<String>();
    collectMapElements(this.fTypeToSubClasses, subTypes, typeName);
    collectMapElements(this.fTypeToSubInterfaces, subTypes, typeName);
    return subTypes.toArray(new String[subTypes.size()]);
  }
  
  public String[] getAllSuperClasses(final String typeName) {
    final Collection<String> superClasses = new ArrayList<String>();
    collectAllSuperClasses(superClasses, typeName);
    return superClasses.toArray(new String[superClasses.size()]);
  }
  
  public String[] getAllSuperInterfaces(final String typeName) {
    final Set<String> superTypes = new HashSet<String>();
    collectMapElements(this.fTypeToSuperInterfaces, superTypes, typeName);
    String name = typeName;    
    while (name != null) {
      final String superClass = this.fClassToSuperClass.get(name);      
      if (superClass != null) {
        collectMapElements(this.fTypeToSuperInterfaces, superTypes, superClass);
        name = superClass;
      } else {
        name = null;
      }
    }
    return superTypes.toArray(new String[superTypes.size()]);
  }
  
  public String[] getAllSuperTypes(final String typeName) {
    final Set<String> superTypes = new HashSet<String>();
    collectMapElements(this.fTypeToSuperInterfaces, superTypes, typeName);
    String name = typeName;    
    while (name != null) {
      final String superClass = this.fClassToSuperClass.get(name);      
      if (superClass != null) {
        superTypes.add(superClass);
        collectMapElements(this.fTypeToSuperInterfaces, superTypes, superClass);
        name = superClass;
      } else {
        name = null;
      }
    }
    return superTypes.toArray(new String[superTypes.size()]);
  }
  
  public String[] getInterfaces(final String typeName) {
    final Set<String> interfaces = this.fTypeToSuperInterfaces.get(typeName);
    return (interfaces == null) ? new String[0] : interfaces.toArray(new String[interfaces.size()]);
  }
  
  public IProject getProject() {
    return this.fProject;
  }
  
  public String getSuperClass(final String typeName) {
    return this.fClassToSuperClass.get(typeName);
  }
  
  public String getType() {
    return this.fTypeName;
  }
    
  // --- Private code
  
  private void addToMap(final Map<String, Set<String>> container, final String key, final String value) {
    final Set<String> set = container.get(key);
    if (set == null) {
      final Set<String> newSet = new HashSet<String>();
      newSet.add(value);
      container.put(key, newSet);
    } else {
      set.add(value);
    }
  }
  
  private void buildHierarchy(final ISet allTypes, final ISet globalTypeHierarchy, final IValue typeNameValue,
                              final IProgressMonitor monitor) throws InterruptedException {
    final Queue<IValue> subTypesWork = new LinkedList<IValue>();
    final Queue<IValue> superTypesWork = new LinkedList<IValue>();
    
    subTypesWork.add(typeNameValue);
    superTypesWork.add(typeNameValue);
    
    final SubMonitor progress = SubMonitor.convert(monitor);
    progress.subTask(Messages.TH_BuildTHTaskName);
    
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
          buildHierarchy(allTypes, tuple);
          superTypesWork.offer(tuple.get(1));
          progress.worked(1);
        }
        if ((subWorkItem != null) && subWorkItem.equals(tuple.get(1))) {
          buildHierarchy(allTypes, tuple);
          subTypesWork.offer(tuple.get(0));
          progress.worked(1);
        }
      }
    }
    
    progress.done();
  }

  private void buildHierarchy(final ISet allTypes, final ITuple tuple) {
    final String typeName = ((IString) tuple.get(0)).getValue();
    final String parentTypeName = ((IString) tuple.get(1)).getValue();

    final ITuple type = getTypeInfo(allTypes, typeName);
    this.fAllTypes.add(type);
    final int typeFlags = ((IInteger) type.get(2)).intValue();
    final ITuple parentType = getTypeInfo(allTypes, parentTypeName);
    this.fAllTypes.add(parentType);
    final int parentTypeFlags = ((IInteger) parentType.get(2)).intValue();

    if ((X10.INTERFACE.getCode() & typeFlags) != 0) {
      addToMap(this.fTypeToSuperInterfaces, typeName, parentTypeName);
      addToMap(this.fTypeToSubInterfaces, parentTypeName, typeName);
    } else {
      if ((X10.INTERFACE.getCode() & parentTypeFlags) != 0) {
        addToMap(this.fTypeToSuperInterfaces, typeName, parentTypeName);
        addToMap(this.fTypeToSubClasses, parentTypeName, typeName);
      } else {
        this.fClassToSuperClass.put(typeName, parentTypeName);
        addToMap(this.fTypeToSubClasses, parentTypeName, typeName);
      }
    }
  }
  
  private void collectAllSuperClasses(final Collection<String> container, final String typeName) {
    final String superClass = this.fClassToSuperClass.get(typeName);
    if (superClass != null) {
      container.add(superClass);
      collectAllSuperClasses(container, superClass);
    }
  }
  
  private void collectMapElements(final Map<String, Set<String>> map, final Set<String> container, final String typeName) {
    final Set<String> set = map.get(typeName);
    if (set != null) {
      for (final String element : set) {
        if (! container.contains(element)) {
          container.add(element);
          collectMapElements(map, container, element);
        }
      }
    }
  }
  
  private ITuple getTypeInfo(final ISet allTypes, final String typeName) {
    for (final IValue value : allTypes) {
      final ITuple tuple = (ITuple) value;
      if (((IString) tuple.get(0)).getValue().equals(typeName)) {
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
  
  private final String fTypeName;
  
  private final Map<String, String> fClassToSuperClass;
  
  private final Map<String, Set<String>> fTypeToSuperInterfaces;
  
  private final Map<String, Set<String>> fTypeToSubClasses;
  
  private final Map<String, Set<String>> fTypeToSubInterfaces;
  
  private final Set<ITuple> fAllTypes;
  
  private final IProject fProject;
  
}
