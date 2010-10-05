/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.pdb.facts.ISet;
import org.eclipse.imp.pdb.facts.db.FactBase;
import org.eclipse.imp.pdb.facts.db.FactKey;
import org.eclipse.imp.pdb.facts.db.IFactContext;
import org.eclipse.imp.pdb.facts.type.Type;
import org.eclipse.imp.pdb.indexing.IndexManager;

import x10dt.search.core.pdb.SearchDBTypes;

/**
 * Utility methods around {@link FactBase} and {@link SearchDBTypes} classes.
 * 
 * @author egeay
 */
public final class FactBaseUtils {
  
  /**
   * Extracts and returns an {@link ISet} from the database for types of {@link SearchDBTypes}.
   * 
   * <p>The call may be long since we will assure that we are in sync with the database first.
   * 
   * @param factBase The fact database to consider.
   * @param context The context to use.
   * @param parametricTypeName The parametric type name to use for identifying the database type.
   * @param scopeTypeName The scope type name bound.
   * @param monitor The progress monitor that can be used for cancellation.
   * @return May returns <b>null</b> if there are no correspondence in the database.
   * @throws InterruptedException Occurs if the search thread got interrupted.
   * @throws ExecutionException Occurs if the search threw an exception.
   */
  public static ISet getFactBaseSetValue(final FactBase factBase, final IFactContext context, final String parametricTypeName,
                                         final String scopeTypeName, 
                                         final IProgressMonitor monitor) throws InterruptedException, ExecutionException {
    final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    final ScheduledFuture<ISet> future = executorService.schedule(new Callable<ISet>() {

      public ISet call() throws InterruptedException {
        while (! IndexManager.isAvailable() && ! monitor.isCanceled())
          ;
        if (monitor.isCanceled()) {
          throw new InterruptedException();
        }
        final Type type = SearchDBTypes.getInstance().getType(parametricTypeName, scopeTypeName);
        return (ISet) factBase.queryFact(new FactKey(type, context));
      }

    }, 0, TimeUnit.SECONDS);
    return future.get();
  }
  
  // --- Private code
  
  private FactBaseUtils() {}

}
