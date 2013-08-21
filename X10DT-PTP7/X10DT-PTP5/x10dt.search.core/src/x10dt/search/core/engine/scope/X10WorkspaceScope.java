/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.engine.scope;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.imp.model.ModelFactory;
import org.eclipse.imp.model.ModelFactory.ModelException;
import org.eclipse.imp.pdb.facts.db.IFactContext;
import org.eclipse.imp.pdb.facts.db.context.ProjectContext;
import org.eclipse.osgi.util.NLS;

import x10dt.core.utils.CountableIterableFactory;
import x10dt.core.utils.ICountableIterable;
import x10dt.search.core.Messages;
import x10dt.search.core.SearchCoreActivator;


final class X10WorkspaceScope extends AbstractX10SearchScope implements IX10SearchScope {
  
  X10WorkspaceScope(final int searchMask, final String projectNatureId) {
    super(searchMask);
    this.fProjectNatureId = projectNatureId;
  }

  // --- Interface methods implementation
  
  public boolean contains(final URI resourceURI) {
    return true;
  }
  
  public ICountableIterable<IFactContext> createSearchContexts() {
    final Collection<IFactContext> searchContexts = new ArrayList<IFactContext>();
    for (final IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
      if (project.isAccessible() && satisfiesProjectNature(project)) {
        try {
          searchContexts.add(new ProjectContext(ModelFactory.open(project)));
        } catch (ModelException except) {
          // Since we test for existence, this exception should never occur. Log it, in case the assertion is violated.
          SearchCoreActivator.log(IStatus.ERROR, NLS.bind(Messages.XS_ProjectExistenceError, project.getName()), except);
        }
      }
    }
    return CountableIterableFactory.create(searchContexts);
  }
  
  // --- Private code
  
  private boolean satisfiesProjectNature(final IProject project) {
    try {
      return (this.fProjectNatureId == null) ? true : project.hasNature(this.fProjectNatureId);
    } catch (CoreException except) {
      // We can't verify the nature. Let's be conservative and not consider it as a potential match.
      return false;
    }
  }
  
  // --- Fields
  
  private final String fProjectNatureId;

}
