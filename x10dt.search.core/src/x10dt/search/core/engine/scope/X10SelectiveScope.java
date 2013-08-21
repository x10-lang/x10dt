/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.engine.scope;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.imp.model.ModelFactory;
import org.eclipse.imp.model.ModelFactory.ModelException;
import org.eclipse.imp.pdb.facts.db.IFactContext;
import org.eclipse.imp.pdb.facts.db.context.ProjectContext;
import org.eclipse.osgi.util.NLS;

import x10dt.core.utils.CountableIterableFactory;
import x10dt.core.utils.ICountableIterable;
import x10dt.core.utils.URIUtils;
import x10dt.search.core.Messages;
import x10dt.search.core.SearchCoreActivator;

final class X10SelectiveScope extends AbstractX10SearchScope implements IX10SearchScope {
  
  protected X10SelectiveScope(final int searchMask, final IResource ... resources) {
    super(searchMask);
    this.fResources = resources;
  }
  
  // --- Interface methods implementation
  
  private String normalize(String path){
	  if (!path.startsWith(Path.SEPARATOR + "")){
		  path  = Path.SEPARATOR + path;
	  }
	  if (!path.endsWith(Path.SEPARATOR + "")){
		  path += Path.SEPARATOR;
	  }
	  return path;
  }
  
  public boolean contains(final URI resourceURI) {
	    for (final IResource resource : this.fResources) {
	    	String path = normalize(URIUtils.getExpectedURI(resourceURI).getPath());
	    	String resourcePath = normalize(resource.getLocation().toString());
	    	
	      if (path.startsWith(resourcePath)){
	        return true;
	      }
	    }
	    return false;
	  }

  public ICountableIterable<IFactContext> createSearchContexts() {
    final Collection<IFactContext> searchContexts = new ArrayList<IFactContext>();
    for (final IResource resource : this.fResources) {
      final IProject project = resource.getProject();
      if (project.isAccessible()) {
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
  
  // --- Fields
  
  private final IResource[] fResources;

}
