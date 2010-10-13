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
import org.eclipse.imp.pdb.facts.db.FactKey;
import org.eclipse.imp.pdb.facts.db.IFactContext;
import org.eclipse.imp.pdb.facts.db.context.ProjectContext;
import org.eclipse.imp.pdb.facts.db.context.WorkspaceContext;
import org.eclipse.imp.pdb.facts.impl.fast.ValueFactory;
import org.eclipse.imp.pdb.facts.type.Type;
import org.eclipse.imp.pdb.indexing.IndexManager;
import org.eclipse.osgi.util.NLS;

import x10dt.search.core.Messages;
import x10dt.search.core.SearchCoreActivator;
import x10dt.search.core.pdb.SearchDBTypes;
import x10dt.search.core.pdb.X10FlagsEncoder.X10;


final class TypeHierarchy implements ITypeHierarchy {
  
  TypeHierarchy(final String typeName, final IProgressMonitor monitor) throws InterruptedException {
    this(WorkspaceContext.getInstance(), typeName, monitor);
  }
  
  TypeHierarchy(final IProject project, final String typeName, 
                final IProgressMonitor monitor) throws ModelException, InterruptedException {
    this(new ProjectContext(ModelFactory.open(project)), typeName, monitor);
  }
  
  TypeHierarchy(final IFactContext context, final String typeName, 
                final IProgressMonitor monitor) throws InterruptedException {
    if (monitor.isCanceled()) {
      throw new InterruptedException();
    }
    
    this.fProject = (context instanceof ProjectContext) ? ((ProjectContext) context).getProject().getRawProject() : null;
    this.fClassToSuperClass = new HashMap<String, ITuple>();
    this.fTypeToSuperInterfaces = new HashMap<String, Set<ITuple>>();
    this.fTypeToSubInterfaces = new HashMap<String, Set<ITuple>>();
    this.fTypeToSubClasses = new HashMap<String, Set<ITuple>>();
    this.fAllTypes = new HashSet<ITuple>();

    final FactBase factBase = FactBase.getInstance();
    final Job buildJob = new Job(Messages.TH_BuildTHJobName) {
      
      // --- Abstract methods implementation
      
      protected IStatus run(final IProgressMonitor jobMonitor) {
        while (! IndexManager.isAvailable() && ! monitor.isCanceled())
          ;
        if (monitor.isCanceled()) {
          return new Status(IStatus.CANCEL, SearchCoreActivator.PLUGIN_ID, Messages.TH_SearchCanceled);
        }
        try {
          jobMonitor.beginTask(null, 20);
          jobMonitor.subTask(Messages.TH_CollectingData);
          final ISet allTypesAppValue = getFactBaseSetValue(factBase, context, X10_AllTypes, APPLICATION);
          final ISet allTypesLibValue = getFactBaseSetValue(factBase, context, X10_AllTypes, LIBRARY);
          final ISet allTypesRuntimeValue = getFactBaseSetValue(factBase, WorkspaceContext.getInstance(), 
                                                                X10_AllTypes, RUNTIME);
          
          final Type typeNameType = SearchDBTypes.getInstance().getType(X10_TypeName);
          final IValue typeNameValue = typeNameType.make(ValueFactory.getInstance(), typeName);
          
          final ISet typeHierarchyAppValue = getFactBaseSetValue(factBase, context, X10_TypeHierarchy, APPLICATION);
          final ISet typeHierarchyLibValue = getFactBaseSetValue(factBase, context, X10_TypeHierarchy, LIBRARY);
          final ISet typeHierarchyRuntimeValue = getFactBaseSetValue(factBase, WorkspaceContext.getInstance(),
                                                                     X10_TypeHierarchy, RUNTIME);
          jobMonitor.worked(1);
          
          buildHierarchy(union(allTypesAppValue, allTypesLibValue, allTypesRuntimeValue),
                         union(typeHierarchyAppValue, typeHierarchyLibValue, typeHierarchyRuntimeValue),
                         typeNameValue, new SubProgressMonitor(monitor, 19));
          return Status.OK_STATUS;
        } catch (InterruptedException except) {
          return new Status(IStatus.CANCEL, SearchCoreActivator.PLUGIN_ID, Messages.TH_SearchCanceled);
        } catch (TypeHierarchyException except) {
          return new Status(IStatus.ERROR, SearchCoreActivator.PLUGIN_ID, except.getMessage());
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
  
  public ITypeInfo[] getAllClasses() {
    final Set<ITypeInfo> classes = new HashSet<ITypeInfo>();
    for (final ITuple tuple : this.fAllTypes) {
      final int typeFlags = ((IInteger) tuple.get(2)).intValue();
      if ((X10.INTERFACE.getCode() & typeFlags) == 0) {
        classes.add(new TypeInfo(tuple));
      }
    }
    return classes.toArray(new ITypeInfo[classes.size()]);
  }

  public ITypeInfo[] getAllInterfaces() {
    final Set<ITypeInfo> interfaces = new HashSet<ITypeInfo>();
    for (final ITuple tuple : this.fAllTypes) {
      final int typeFlags = ((IInteger) tuple.get(2)).intValue();
      if ((X10.INTERFACE.getCode() & typeFlags) != 0) {
        interfaces.add(new TypeInfo(tuple));
      }
    }
    return interfaces.toArray(new ITypeInfo[interfaces.size()]);
  }
  
  public ITypeInfo[] getAllSubClasses(final String typeName) {
    final Set<ITypeInfo> subClasses = new HashSet<ITypeInfo>();
    collectMapElements(this.fTypeToSubClasses, subClasses, typeName);
    return subClasses.toArray(new ITypeInfo[subClasses.size()]);
  }
  
  public ITypeInfo[] getAllSubTypes(final String typeName) {
    final Set<ITypeInfo> subTypes = new HashSet<ITypeInfo>();
    collectMapElements(this.fTypeToSubClasses, subTypes, typeName);
    collectMapElements(this.fTypeToSubInterfaces, subTypes, typeName);
    return subTypes.toArray(new ITypeInfo[subTypes.size()]);
  }
  
  public ITypeInfo[] getAllSuperClasses(final String typeName) {
    final Collection<ITypeInfo> superClasses = new ArrayList<ITypeInfo>();
    collectAllSuperClasses(superClasses, typeName);
    return superClasses.toArray(new ITypeInfo[superClasses.size()]);
  }
  
  public ITypeInfo[] getAllSuperInterfaces(final String typeName) {
    final Set<ITypeInfo> superTypes = new HashSet<ITypeInfo>();
    collectMapElements(this.fTypeToSuperInterfaces, superTypes, typeName);
    String name = typeName;    
    while (name != null) {
      final ITuple superClass = this.fClassToSuperClass.get(name);      
      if (superClass != null) {
        name = ((IString) superClass.get(0)).getValue();
        collectMapElements(this.fTypeToSuperInterfaces, superTypes, name);
      } else {
        name = null;
      }
    }
    return superTypes.toArray(new ITypeInfo[superTypes.size()]);
  }
  
  public ITypeInfo[] getAllSuperTypes(final String typeName) {
    final Set<ITypeInfo> superTypes = new HashSet<ITypeInfo>();
    collectMapElements(this.fTypeToSuperInterfaces, superTypes, typeName);
    String name = typeName;    
    while (name != null) {
      final ITuple superClass = this.fClassToSuperClass.get(name);      
      if (superClass != null) {
        superTypes.add(new TypeInfo(superClass));
        name = ((IString) superClass.get(0)).getValue();
        collectMapElements(this.fTypeToSuperInterfaces, superTypes, name);
      } else {
        name = null;
      }
    }
    return superTypes.toArray(new ITypeInfo[superTypes.size()]);
  }
  
  public ITypeInfo[] getInterfaces(final String typeName) {
    final Set<ITuple> interfaces = this.fTypeToSuperInterfaces.get(typeName);
    if (interfaces == null) {
      return new ITypeInfo[0];
    } else {
      final ITypeInfo[] typeInfos = new ITypeInfo[interfaces.size()];
      int i = -1;
      for (final ITuple interf : interfaces) {
        typeInfos[++i] = new TypeInfo(interf);
      }
      return typeInfos;
    }
  }
  
  public IProject getProject() {
    return this.fProject;
  }
  
  public ITypeInfo[] getSubTypes(final String typeName) {
    final Set<ITypeInfo> subTypes = new HashSet<ITypeInfo>();
    final Set<ITuple> subClasses = this.fTypeToSubClasses.get(typeName);
    if (subClasses != null) {
      for (final ITuple subClass : subClasses) {
        subTypes.add(new TypeInfo(subClass));
      }
    }
    final Set<ITuple> subInterfaces = this.fTypeToSubInterfaces.get(typeName);
    if (subInterfaces != null) {
      for (final ITuple subInterface : subInterfaces) {
        subTypes.add(new TypeInfo(subInterface));
      }
    }
    return subTypes.toArray(new ITypeInfo[subTypes.size()]);
  }
  
  public ITypeInfo getSuperClass(final String typeName) {
    final ITuple tuple = this.fClassToSuperClass.get(typeName);
    return (tuple == null) ? null : new TypeInfo(tuple);
  }
  
  public ITypeInfo[] getSuperTypes(final String typeName) {
    final Set<ITypeInfo> superTypes = new HashSet<ITypeInfo>();
    final ITuple superClass = this.fClassToSuperClass.get(typeName);
    if (superClass != null) {
      superTypes.add(new TypeInfo(superClass));
    }
    final Set<ITuple> superInterfaces = this.fTypeToSuperInterfaces.get(typeName);
    if (superInterfaces != null) {
      for (final ITuple superInterface : superInterfaces) {
        superTypes.add(new TypeInfo(superInterface));
      }
    }
    return superTypes.toArray(new ITypeInfo[superTypes.size()]);
  }
  
  public ITypeInfo getType() {
    return this.fMainType;
  }
    
  // --- Private code
  
  private void addToMap(final Map<String, Set<ITuple>> container, final String key, final ITuple value) {
    final Set<ITuple> set = container.get(key);
    if (set == null) {
      final Set<ITuple> newSet = new HashSet<ITuple>();
      newSet.add(value);
      container.put(key, newSet);
    } else {
      set.add(value);
    }
  }
  
  private void buildHierarchy(final ISet allTypes, final ISet globalTypeHierarchy, final IValue typeNameValue,
                              final IProgressMonitor monitor) throws InterruptedException, TypeHierarchyException {
    final ITuple mainTypeTuple = getTypeInfo(allTypes, ((IString) typeNameValue).getValue());
    if (mainTypeTuple == null) {
      throw new TypeHierarchyException(NLS.bind(Messages.TH_MainTypeInfoError, typeNameValue));
    }
    this.fMainType = new TypeInfo(mainTypeTuple);
    
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
    if (type != null) {
      this.fAllTypes.add(type);
      final int typeFlags = ((IInteger) type.get(2)).intValue();
      final ITuple parentType = getTypeInfo(allTypes, parentTypeName);
      if (parentType != null) {
        this.fAllTypes.add(parentType);
        final int parentTypeFlags = ((IInteger) parentType.get(2)).intValue();

        if ((X10.INTERFACE.getCode() & typeFlags) != 0) {
          addToMap(this.fTypeToSuperInterfaces, typeName, parentType);
          addToMap(this.fTypeToSubInterfaces, parentTypeName, type);
        } else {
          if ((X10.INTERFACE.getCode() & parentTypeFlags) != 0) {
            addToMap(this.fTypeToSuperInterfaces, typeName, parentType);
            addToMap(this.fTypeToSubClasses, parentTypeName, type);
          } else {
            this.fClassToSuperClass.put(typeName, parentType);
            addToMap(this.fTypeToSubClasses, parentTypeName, type);
          }
        }
      }
    }
  }
  
  private void collectAllSuperClasses(final Collection<ITypeInfo> container, final String typeName) {
    final ITuple superClass = this.fClassToSuperClass.get(typeName);
    if (superClass != null) {
      container.add(new TypeInfo(superClass));
      collectAllSuperClasses(container, ((IString) superClass.get(0)).getValue());
    }
  }
  
  private void collectMapElements(final Map<String, Set<ITuple>> map, final Set<ITypeInfo> container, final String typeName) {
    final Set<ITuple> set = map.get(typeName);
    if (set != null) {
      for (final ITuple element : set) {
        if (! container.contains(element)) {
          container.add(new TypeInfo(element));
          collectMapElements(map, container, ((IString) element.get(0)).getValue());
        }
      }
    }
  }
  
  private ISet getFactBaseSetValue(final FactBase factBase, final IFactContext context, final String parametricTypeName,
                                   final String scopeTypeName) {
    return (ISet) factBase.queryFact(new FactKey(SearchDBTypes.getInstance().getType(parametricTypeName, scopeTypeName), 
                                                 context));
  }
  
  private ITuple getTypeInfo(final ISet allTypes, final String typeName) {
    for (final IValue value : allTypes) {
      final ITuple tuple = (ITuple) value;
      if (((IString) tuple.get(0)).getValue().equals(typeName)) {
        return tuple;
      }
    }
    return null;
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
  
  private ITypeInfo fMainType;
  
  private final Map<String, ITuple> fClassToSuperClass;
  
  private final Map<String, Set<ITuple>> fTypeToSuperInterfaces;
  
  private final Map<String, Set<ITuple>> fTypeToSubClasses;
  
  private final Map<String, Set<ITuple>> fTypeToSubInterfaces;
  
  private final Set<ITuple> fAllTypes;
  
  private final IProject fProject;
  
}
