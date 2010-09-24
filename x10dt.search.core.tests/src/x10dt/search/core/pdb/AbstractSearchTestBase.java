/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.pdb;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.imp.model.ModelFactory;
import org.eclipse.imp.pdb.analysis.AnalysisManager;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.db.FactBase;
import org.eclipse.imp.pdb.facts.db.FactKey;
import org.eclipse.imp.pdb.facts.db.IFactContext;
import org.eclipse.imp.pdb.facts.db.IFactKey;
import org.eclipse.imp.pdb.facts.db.context.ProjectContext;
import org.eclipse.imp.pdb.facts.type.Type;

import x10dt.tests.services.pde.utils.ProjectUtils;

/**
 * Base class for test cases testing the X10DT indexing engine.
 * 
 * @author egeay
 */
public abstract class AbstractSearchTestBase {
  
  // --- Code for descendants
  
  /**
   * Produces facts for the given PDB type name for the set of data identified.
   * 
   * @param factTypeName The PDB fact type to consider.
   * @param data The path to either an X10 file or a directory. If it is a directory then all X10 files under it will be 
   * collected automatically and be considered as the input.
   * @param context The analysis context.
   * @return The time in milliseconds it took for the operation to perform.
   */
  protected final long generateFact(final String factTypeName, final String data,
                                    final IFactContext context) throws Exception {
    final long start = System.currentTimeMillis();
    final SearchDBTypes sdbTypes = new SearchDBTypes();
    final Type factType = sdbTypes.getType(factTypeName);
    assertNotNull(factType);
    
    this.fContext = context;
    
    AnalysisManager.getInstance().generateFact(new FactKey(factType, context));
    final long end = System.currentTimeMillis();
    return end - start;
  }
  
  /**
   * Produces facts for the given PDB type name for the set of data identified.
   * 
   * @param factTypeName The PDB fact type to consider.
   * @param projectName The project name in which the data will be populated.
   * @param data The path to either an X10 file or a directory. If it is a directory then all X10 files under it will be 
   * collected automatically and be considered as the input.
   * @param shouldCreateProject Indicates if we should create the project or not.
   * @return The time in milliseconds it took for the operation to perform.
   */
  protected final long generateFactForProject(final String factTypeName, final String projectName, final String data,
                                              final boolean shouldCreateProject) throws Exception {
    ResourcesPlugin.getWorkspace().getDescription().setAutoBuilding(false);
    final IProject project;
    if (shouldCreateProject) {
      project = ProjectUtils.createX10ProjectJavaBackEnd(projectName, getClass().getClassLoader(), data);
    } else {
      project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
    }
    assertTrue(project.exists());
    
    return generateFact(factTypeName, data, new ProjectContext(ModelFactory.open(project)));
  }
  
  /**
   * Returns the fact value in the database for the given type.
   * 
   * @param parametricTypeName The parametric type name to use.
   * @param scopeType The scope type value to bound with the parametric type.
   * @return The value for the type identified or <b>null</b> if there is no value in the database.
   */
  protected final IValue getValue(final String parametricTypeName, final String scopeType) {
    final SearchDBTypes sdbTypes = new SearchDBTypes();
    final IFactKey key = new FactKey(sdbTypes.instantiate(sdbTypes.getType(parametricTypeName), sdbTypes.getType(scopeType)), 
                                     this.fContext);
    return FactBase.getInstance().getFact(key);
  }
  
  // --- Fields
  
  private IFactContext fContext;

}
