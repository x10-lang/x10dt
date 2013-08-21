/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.engine;

import static org.junit.Assert.assertEquals;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Test;

import x10dt.search.core.elements.ITypeInfo;
import x10dt.search.core.engine.scope.IX10SearchScope;
import x10dt.search.core.engine.scope.SearchScopeFactory;
import x10dt.search.core.engine.scope.X10SearchScope;

/**
 * Test cases around {@link x10dt.search.core.elements.IMethodInfo} and {@link x10dt.search.core.elements.IFieldInfo}.
 * 
 * @author egeay
 */
@SuppressWarnings("nls")
public final class AllMainMethodsTests extends AbstractIndexerTestBase {
  
  // --- Test cases
  
  @Test public void testIntegrateTutorial() throws Exception {
    final IProject project = createProject(PROJECT_NAME, "data/engine/MainMethods.x10", EProjectBackEnd.JAVA);
    final IX10SearchScope scope = SearchScopeFactory.createSelectiveScope(X10SearchScope.APPLICATION, project);
    final IProgressMonitor nullMonitor = new NullProgressMonitor();
    
    final ITypeInfo[] typeInfo = X10SearchEngine.getAllTypesWithMainMethod(scope, nullMonitor);
    assertEquals(1, typeInfo.length);
  }
  
  // --- Fields
  
  private static final String PROJECT_NAME = "mainMethods";
  
}
