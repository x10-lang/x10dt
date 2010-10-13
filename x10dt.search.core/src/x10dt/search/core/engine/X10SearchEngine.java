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
import static x10dt.search.core.pdb.X10FactTypeNames.X10_AllFields;
import static x10dt.search.core.pdb.X10FactTypeNames.X10_AllMethods;
import static x10dt.search.core.pdb.X10FactTypeNames.X10_AllTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.imp.model.ModelFactory;
import org.eclipse.imp.model.ModelFactory.ModelException;
import org.eclipse.imp.pdb.facts.IInteger;
import org.eclipse.imp.pdb.facts.IList;
import org.eclipse.imp.pdb.facts.ISet;
import org.eclipse.imp.pdb.facts.ISourceLocation;
import org.eclipse.imp.pdb.facts.IString;
import org.eclipse.imp.pdb.facts.ITuple;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.db.FactBase;
import org.eclipse.imp.pdb.facts.db.IFactContext;
import org.eclipse.imp.pdb.facts.db.context.ProjectContext;
import org.eclipse.imp.pdb.facts.db.context.WorkspaceContext;

import x10dt.search.core.utils.FactBaseUtils;
import x10dt.ui.launch.core.utils.IFilter;


/**
 * Searches for X10 elements following a search pattern. The search can also be limited to a search scope.
 * 
 * @author egeay
 */
public final class X10SearchEngine {
  
  // --- Public services
  
  /**
   * Creates a type hierarchy for the given accessible from the project transmitted.
   * 
   * <p>Note that there is no refresh action at this point. If one wants an updated version of it, a new one needs to be
   * created.
   * 
   * @param project The project to consider.
   * @param typeName The type name to use for the type hierarchy.
   * @param monitor The monitor to use to report progress or cancel the operation.
   * @return A non-null instance of {@link ITypeHierarchy}.
   * @throws ModelException Occurs if the project does not exist.
   * @throws InterruptedException Occurs if the operation gets canceled.
   */
  public static ITypeHierarchy createTypeHierarchy(final IProject project, final String typeName,
                                                   final IProgressMonitor monitor) throws ModelException, 
                                                                                          InterruptedException {
    return new TypeHierarchy(project, typeName, monitor);
  }
  
  /**
   * Returns all the field information that matches the Java regular expression identifying a potential set of field names
   * for a given type.
   * 
   * @param project The project in which to start the search for field names.
   * @param typeName The type name that is needed to be located first.
   * @param fieldNameRegEx The regular expression identifying the field names to search for.
   * @param isCaseSensitive Indicates if the pattern matching is case sensitive or not.
   * @param monitor The monitor to use to report progress or cancel the operation.
   * @return A non-null, but possibly empty (if no match was found), array of field information.
   * @throws ModelException Occurs if the project does not exist.
   * @throws InterruptedException Occurs if the operation gets canceled.
   * @throws ExecutionException Occurs if the search threw an exception.
   */
  public static IFieldInfo[] getAllMatchingFieldInfo(final IProject project, final String typeName, 
                                                     final String fieldNameRegEx, final boolean isCaseSensitive,
                                                     final IProgressMonitor monitor) throws ModelException, 
                                                                                            InterruptedException, 
                                                                                            ExecutionException {
    return getFieldInfo(project, typeName, new PatternFilter(fieldNameRegEx, isCaseSensitive), true, monitor);
  }
  
  /**
   * Returns all the method information that matches the Java regular expression identifying a potential set of method names
   * for a given type.
   * 
   * @param project The project in which to start the search for method names.
   * @param typeName The type name that is needed to be located first.
   * @param methodNameRegEx The regular expression identifying the method names to search for.
   * @param isCaseSensitive Indicates if the pattern matching is case sensitive or not.
   * @param monitor The monitor to use to report progress or cancel the operation.
   * @return A non-null, but possibly empty (if no match was found), array of method information.
   * @throws ModelException Occurs if the project does not exist.
   * @throws InterruptedException Occurs if the operation gets canceled.
   * @throws ExecutionException Occurs if the search threw an exception.
   */
  public static IMethodInfo[] getAllMatchingMethodInfo(final IProject project, final String typeName, 
                                                       final String methodNameRegEx, final boolean isCaseSensitive,
                                                       final IProgressMonitor monitor) throws ModelException, 
                                                                                              InterruptedException, 
                                                                                              ExecutionException {
    return getMethodInfos(project, typeName, new PatternFilter(methodNameRegEx, isCaseSensitive), monitor);
  }
  
  /**
   * Returns all the type information that matches the Java regular expression identifying a potential set of type names.
   * 
   * @param project The project in which to start the search for type names.
   * @param typeNameRegEx The regular expression identifying the type names to search for.
   * @param isCaseSensitive Indicates if the pattern matching is case sensitive or not.
   * @param monitor The monitor to use to report progress or cancel the operation.
   * @return A non-null, but possibly empty (if no match was found), array of type information.
   * @throws ModelException Occurs if the project does not exist.
   * @throws InterruptedException Occurs if the operation gets canceled.
   * @throws ExecutionException Occurs if the search threw an exception.
   */
  public static ITypeInfo[] getAllMatchingTypeInfo(final IProject project, final String typeNameRegEx,
                                                   final boolean isCaseSensitive,
                                                   final IProgressMonitor monitor) throws ModelException, 
                                                                                          InterruptedException, 
                                                                                          ExecutionException {
    final IFactContext context = new ProjectContext(ModelFactory.open(project));
    final FactBase factBase = FactBase.getInstance();
    
    final SubMonitor subMonitor = SubMonitor.convert(monitor);
    final Collection<ITypeInfo> typeInfos = new ArrayList<ITypeInfo>();
    try {
      subMonitor.beginTask(null, 3);
      final IFilter<String> filter = new TypePatternFilter(typeNameRegEx, isCaseSensitive);
      collectTypeInfo(typeInfos, factBase, context, filter, APPLICATION, subMonitor.newChild(1));
      collectTypeInfo(typeInfos, factBase, context, filter, LIBRARY, subMonitor.newChild(1));
      collectTypeInfo(typeInfos, factBase, WorkspaceContext.getInstance(), filter, RUNTIME, subMonitor.newChild(1));
    } finally {
      subMonitor.done();
    }
    return typeInfos.toArray(new ITypeInfo[typeInfos.size()]);
  }
  
  /**
   * Returns the field info wrapper for a given type and field name.
   * 
   * @param project The project in which the type of interest resides.
   * @param typeName The type name that is supposed to contain the field name.
   * @param fieldName The field name to look for.
   * @param monitor The progress monitor to use to report progress or cancel the operation. Can be <b>null</b>.
   * @return A non-null {@link IFieldInfo} if it is found, otherwise <b>null</b>.
   * @throws ModelException Occurs if the project does not exist.
   * @throws InterruptedException Occurs if the operation get canceled.
   * @throws ExecutionException Occurs if the search threw an exception.
   */
  public static IFieldInfo getFieldInfo(final IProject project, final String typeName, final String fieldName,
                                        final IProgressMonitor monitor) throws ModelException, InterruptedException, 
                                                                               ExecutionException {
    final IFieldInfo[] fieldInfos = getFieldInfo(project, typeName, new EqualsFilter(fieldName), false, monitor);
    return (fieldInfos.length == 0) ? null : fieldInfos[0];
  }
  
  /**
   * Returns the method info wrappers for a given type and method name.
   * 
   * @param project The project in which the type of interest resides.
   * @param typeName The type name that is supposed to contain the method name(s).
   * @param methodName The method name to look for.
   * @param monitor The progress monitor to use to report progress or cancel the operation. Can be <b>null</b>.
   * @return A non-null, but possibly empty (if no match was found), array of method information.
   * @throws ModelException Occurs if the project does not exist.
   * @throws InterruptedException Occurs if the operation get canceled.
   * @throws ExecutionException Occurs if the search threw an exception.
   */
  public static IMethodInfo[] getMethodInfos(final IProject project, final String typeName, final String methodName,
                                             final IProgressMonitor monitor) throws ModelException, InterruptedException, 
                                                                                    ExecutionException {
    return getMethodInfos(project, typeName, new EqualsFilter(methodName), monitor);
  }
  
  /**
   * Returns the type info wrapper for a given type name.
   * 
   * @param project The project in which the type of interest resides.
   * @param typeName The type name to look for.
   * @param monitor The progress monitor to use to report progress or cancel the operation. Can be <b>null</b>.
   * @return A non-null {@link ITypeInfo} if it is found, otherwise <b>null</b>.
   * @throws ModelException Occurs if the project does not exist.
   * @throws InterruptedException Occurs if the operation get canceled.
   * @throws ExecutionException Occurs if the search threw an exception.
   */
  public static ITypeInfo getTypeInfo(final IProject project, final String typeName,
                                      final IProgressMonitor monitor) throws ModelException, InterruptedException, 
                                                                             ExecutionException {
    final IFactContext context = new ProjectContext(ModelFactory.open(project));
    final FactBase factBase = FactBase.getInstance();
    return getTypeInfo(factBase, context, typeName, monitor);
  }
  
  // --- Private code
  
  private static IFieldInfo createFieldInfo(final ITuple tuple, final FactBase factBase, final IFactContext context,
                                            final String typeName, 
                                            final IProgressMonitor monitor) throws InterruptedException, ExecutionException {
    final String fieldTypeName = getBaseTypeName(((IString) tuple.get(2)).getValue());
    IBasicTypeInfo fieldTypeInfo = getTypeInfo(factBase, context, fieldTypeName, monitor);
    if (fieldTypeInfo == null) {
      fieldTypeInfo = new UnknownTypeInfo(fieldTypeName);
    }
    return new FieldInfo(((IString) tuple.get(1)).getValue(), fieldTypeInfo, (ISourceLocation) tuple.get(0), 
                         ((IInteger) tuple.get(3)).intValue());
  }
  
  private static IMethodInfo createMethodInfo(final ITuple tuple, final FactBase factBase, final IFactContext context,
                                              final String typeName,
                                              final IProgressMonitor monitor) throws InterruptedException, ExecutionException {
    final String returnTypeName = getBaseTypeName(((IString) tuple.get(2)).getValue());
    IBasicTypeInfo returnTypeInfo;
    if (VoidTypeInfo.VOID_TYPE_NAME.equals(returnTypeName)) {
      returnTypeInfo = new VoidTypeInfo();
    } else {
      returnTypeInfo = getTypeInfo(factBase, context, returnTypeName, monitor);;
    }
    if (returnTypeInfo == null) {
      returnTypeInfo = new UnknownTypeInfo(returnTypeName);
    }
    final IList parameters = (IList) tuple.get(3);
    final IBasicTypeInfo[] paramInfos = new IBasicTypeInfo[parameters.length()];
    int i = -1;
    for (final IValue paramValue : parameters) {
      final String paramTypeName = getBaseTypeName(((IString) paramValue).getValue());
      paramInfos[++i] = getTypeInfo(factBase, context, paramTypeName, monitor);
      if (paramInfos[i] == null) {
        paramInfos[i] = new UnknownTypeInfo(paramTypeName);
      }
    }
    return new MethodInfo(((IString) tuple.get(1)).getValue(), returnTypeInfo, paramInfos, (ISourceLocation) tuple.get(0), 
                          ((IInteger) tuple.get(4)).intValue());
  }
  
  private static boolean collectFieldInfo(final Collection<IFieldInfo> fieldInfos, final FactBase factBase, 
                                          final IFactContext context, final String typeName, final IFilter<String> filter, 
                                          final String scopeTypeName, final boolean pursueSearch,
                                          final IProgressMonitor monitor) throws InterruptedException, ExecutionException {
    final ISet allTypesValue = FactBaseUtils.getFactBaseSetValue(factBase, context, X10_AllFields, scopeTypeName,
                                                                 monitor);
    if (allTypesValue != null) {
      for (final IValue value : allTypesValue) {
        final ITuple tuple = (ITuple) value;
        if (((IString) tuple.get(0)).getValue().equals(typeName)) {
          final IList list = (IList) tuple.get(1);
          for (final IValue listElement : list) {
            final ITuple fieldTuple = (ITuple) listElement;
            if (filter.accepts(((IString) fieldTuple.get(1)).getValue())) {
              fieldInfos.add(createFieldInfo(fieldTuple, factBase, context, typeName, monitor));
              if (! pursueSearch) {
                return true;
              }
            }
          }
          return true;
        }
      }
    }
    return false;
  }
  
  private static boolean collectMethodInfo(final Collection<IMethodInfo> methodInfos, final FactBase factBase, 
                                           final IFactContext context, final String typeName, 
                                           final String scopeTypeName, final IFilter<String> filter,
                                           final IProgressMonitor monitor) throws InterruptedException, ExecutionException {
    final ISet allTypesValue = FactBaseUtils.getFactBaseSetValue(factBase, context, X10_AllMethods, scopeTypeName,
                                                                 monitor);
    if (allTypesValue != null) {
      for (final IValue value : allTypesValue) {
        final ITuple tuple = (ITuple) value;
        if (((IString) tuple.get(0)).getValue().equals(typeName)) {
          final IList list = (IList) tuple.get(1);
          for (final IValue listElement : list) {
            final ITuple methodTuple = (ITuple) listElement;
            if (filter.accepts(((IString) methodTuple.get(1)).getValue())) {
              methodInfos.add(createMethodInfo(methodTuple, factBase, context, typeName, monitor));
            }
          }
          return true;
        }
      }
    }
    return false;
  }
  
  private static void collectTypeInfo(final Collection<ITypeInfo> typeInfos, final FactBase factBase, 
                                      final IFactContext context, final IFilter<String> filter, final String scopeTypeName, 
                                      final IProgressMonitor monitor) throws InterruptedException, ExecutionException {
    final ISet allTypesValue = FactBaseUtils.getFactBaseSetValue(factBase, context, X10_AllTypes, scopeTypeName,
                                                                 monitor);
    if (allTypesValue != null) {
      for (final IValue value : allTypesValue) {
        final ITuple tuple = (ITuple) value;
        if (filter.accepts(((IString) tuple.get(0)).getValue())) {
          typeInfos.add(new TypeInfo(tuple));
        }
      }
    }
  }
  
  private static String getBaseTypeName(final String typeName) {
    final int squareBracketIndex = typeName.indexOf('[');
    String baseTypeName = typeName;
    if (squareBracketIndex != -1) {
      baseTypeName = baseTypeName.substring(0, squareBracketIndex);
    }
    final int bracketIndex = baseTypeName.indexOf('{');
    if (bracketIndex != -1) {
      baseTypeName = baseTypeName.substring(0, bracketIndex);
    }
    return baseTypeName;
  }
  
  private static IFieldInfo[] getFieldInfo(final IProject project, final String typeName, final IFilter<String> filter,
                                           final boolean pursueSearch, 
                                           final IProgressMonitor monitor) throws ModelException, InterruptedException, 
                                                                                  ExecutionException {
    final IFactContext context = new ProjectContext(ModelFactory.open(project));
    final FactBase factBase = FactBase.getInstance();
    
    final SubMonitor subMonitor = SubMonitor.convert(monitor);
    final Collection<IFieldInfo> fieldInfos = new ArrayList<IFieldInfo>();
    try {
      subMonitor.beginTask(null, 3);
      if (! collectFieldInfo(fieldInfos, factBase, context, typeName, filter, APPLICATION, pursueSearch, 
                             subMonitor.newChild(1))) {
        if (! collectFieldInfo(fieldInfos, factBase, context, typeName, filter, LIBRARY, pursueSearch,
                               subMonitor.newChild(1))) {
          collectFieldInfo(fieldInfos, factBase, WorkspaceContext.getInstance(), typeName, filter, RUNTIME, 
                           pursueSearch, subMonitor.newChild(1));
        }
      }
    } finally {
      subMonitor.done();
    }
    return fieldInfos.toArray(new IFieldInfo[fieldInfos.size()]);
  }
  
  private static IMethodInfo[] getMethodInfos(final IProject project, final String typeName, final IFilter<String> filter,
                                              final IProgressMonitor monitor) throws ModelException, InterruptedException, 
                                                                                     ExecutionException {
    final IFactContext context = new ProjectContext(ModelFactory.open(project));
    final FactBase factBase = FactBase.getInstance();
    
    final SubMonitor subMonitor = SubMonitor.convert(monitor);
    final Collection<IMethodInfo> methodInfos = new ArrayList<IMethodInfo>();
    try {
      subMonitor.beginTask(null, 3);
      if (! collectMethodInfo(methodInfos, factBase, context, typeName, APPLICATION, filter, subMonitor.newChild(1))) {
        if (! collectMethodInfo(methodInfos, factBase, context, typeName, LIBRARY, filter, subMonitor.newChild(1))) {
          collectMethodInfo(methodInfos, factBase, WorkspaceContext.getInstance(), typeName, RUNTIME, filter,
                            subMonitor.newChild(1));
        }
      }
    } finally {
      subMonitor.done();
    }
    return methodInfos.toArray(new IMethodInfo[methodInfos.size()]);
  }
  
  private static ITypeInfo getTypeInfo(final FactBase factBase, final IFactContext context, final String typeName, 
                                       final IProgressMonitor monitor) throws InterruptedException, ExecutionException {
    final SubMonitor subMonitor = SubMonitor.convert(monitor);
    
    try {
      subMonitor.beginTask(null, 3);
      final ITypeInfo appTypeInfo = getTypeInfo(factBase, context, typeName, APPLICATION, subMonitor.newChild(1));
      if (appTypeInfo != null) {
        return appTypeInfo;
      }
      final ITypeInfo libTypeInfo = getTypeInfo(factBase, context, typeName, LIBRARY, subMonitor.newChild(1));
      if (libTypeInfo != null) {
        return libTypeInfo;
      }
      return getTypeInfo(factBase, WorkspaceContext.getInstance(), typeName, RUNTIME, subMonitor.newChild(1));
    } finally {
      subMonitor.done();
    }
  }
  
  private static ITypeInfo getTypeInfo(final FactBase factBase, final IFactContext context, final String typeName, 
                                       final String scopeTypeName, 
                                       final IProgressMonitor monitor) throws InterruptedException, ExecutionException {
    final ISet allTypesValue = FactBaseUtils.getFactBaseSetValue(factBase, context, X10_AllTypes, scopeTypeName,
                                                                 monitor);
    if (allTypesValue != null) {
      for (final IValue value : allTypesValue) {
        final ITuple tuple = (ITuple) value;
        if (((IString) tuple.get(0)).getValue().equals(typeName)) {
          return new TypeInfo(tuple);
        }
      }
    }
    return null;
  }
  
  // --- Private classes
  
  private static final class EqualsFilter implements IFilter<String> {
   
    EqualsFilter(final String base) {
      this.fBase = base;
    }

    // --- Interface methods implementation
    
    public boolean accepts(final String element) {
      return this.fBase.equals(element);
    }
    
    // --- Fields
    
    private final String fBase;
    
  }
  
  private static final class PatternFilter implements IFilter<String> {
    
    PatternFilter(final String regex, final boolean isCaseSensitive) {
      this.fPattern = isCaseSensitive ? Pattern.compile(regex) : Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    // --- Interface methods implementation
    
    public boolean accepts(final String element) {
      return this.fPattern.matcher(element).matches();
    }
    
    // --- Fields
    
    private final Pattern fPattern;
    
  }
  
  private static final class TypePatternFilter implements IFilter<String> {
    
    TypePatternFilter(final String regex, final boolean isCaseSensitive) {
      this.fPattern = isCaseSensitive ? Pattern.compile(regex) : Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    // --- Interface methods implementation
    
    public boolean accepts(final String element) {
      final String typeName = element.substring(element.lastIndexOf('.') + 1);
      return this.fPattern.matcher(typeName).matches();
    }
    
    // --- Fields
    
    private final Pattern fPattern;
    
  }

}
