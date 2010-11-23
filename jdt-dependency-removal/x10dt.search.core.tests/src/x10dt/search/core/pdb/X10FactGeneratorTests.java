/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.pdb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static x10dt.search.core.pdb.X10FactTypeNames.APPLICATION;
import static x10dt.search.core.pdb.X10FactTypeNames.RUNTIME;
import static x10dt.search.core.pdb.X10FactTypeNames.X10_AllTypes;
import static x10dt.search.core.pdb.X10FactTypeNames.X10_TypeHierarchy;
import static x10dt.search.core.utils.TestUtils.assertLocation;
import static x10dt.search.core.utils.TestUtils.getString;

import org.eclipse.imp.pdb.facts.ISet;
import org.eclipse.imp.pdb.facts.IValue;
import org.junit.Test;

import x10dt.search.core.utils.ContextUtils;
import x10dt.search.core.utils.ValueUtils;

/**
 * Test cases for {@link X10FactGenerator} class.
 * 
 * @author egeay
 */
@SuppressWarnings("nls")
public final class X10FactGeneratorTests extends AbstractSearchTestBase {
    
  // --- Test cases
    
  @Test public void testHelloWorldWithProjectContext() throws Exception {
    final long time = generateFactForProject(X10_TypeHierarchy, "test", "factGenerator/case1", true);
    assertTrue("Time in seconds to perform the indexing: " + (time / 1000), time < 60000);
    
    final IValue[][] appAllTypes = ValueUtils.toArray((ISet) getValue(X10_AllTypes, APPLICATION));
    assertEquals(1, appAllTypes.length);
    assertEquals("HelloWorld", getString(appAllTypes[0][0]));
    assertLocation(appAllTypes[0][1], "src/HelloWorld.x10", 17, 21);
    
    final ISet runtimeAllTypes = (ISet) getValue(X10_AllTypes, RUNTIME);
    final int runtimeAllTypesSize = runtimeAllTypes.size();
    assertTrue(runtimeAllTypesSize > 200);
    
    final ISet runtimeTypeHierarchy = (ISet) getValue(X10_TypeHierarchy, RUNTIME);
    assertTrue(runtimeTypeHierarchy.size() > runtimeAllTypesSize);
    
    final IValue[][] appTypeHierarchy = ValueUtils.toArray((ISet) getValue(X10_TypeHierarchy, APPLICATION));
    assertEquals(1, appTypeHierarchy.length);
    assertEquals("HelloWorld", getString(appTypeHierarchy[0][0]));
    assertEquals("x10.lang.Object", getString(appTypeHierarchy[0][1]));
  }
  
  @Test public void testHelloWorldWithResourceContext() throws Exception {
    final long time = generateFact(X10_TypeHierarchy, "factGenerator/case1", 
                                   ContextUtils.createFileContext("/test/src/HelloWorld.x10"));
    assertTrue("Time in seconds to perform the indexing: " + (time / 1000), time < 60000);
    
    final IValue[][] appAllTypes = ValueUtils.toArray((ISet) getValue(X10_AllTypes, APPLICATION));
    assertEquals(1, appAllTypes.length);
    assertEquals("HelloWorld", getString(appAllTypes[0][0]));
    assertLocation(appAllTypes[0][1], "src/HelloWorld.x10", 17, 21);
    
    final ISet runtimeAllTypes = (ISet) getValue(X10_AllTypes, RUNTIME);
    assertNull(runtimeAllTypes);
    
    final ISet runtimeTypeHierarchy = (ISet) getValue(X10_TypeHierarchy, RUNTIME);
    assertNull(runtimeTypeHierarchy);
    
    final IValue[][] appTypeHierarchy = ValueUtils.toArray((ISet) getValue(X10_TypeHierarchy, APPLICATION));
    assertEquals(1, appTypeHierarchy.length);
    assertEquals("HelloWorld", getString(appTypeHierarchy[0][0]));
    assertEquals("x10.lang.Object", getString(appTypeHierarchy[0][1]));
  }

}
