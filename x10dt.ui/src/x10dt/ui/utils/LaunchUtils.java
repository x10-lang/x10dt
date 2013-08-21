/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.utils;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

import x10dt.search.core.elements.ITypeInfo;
import x10dt.search.core.engine.X10SearchEngine;
import x10dt.search.core.engine.scope.IX10SearchScope;
import x10dt.search.core.engine.scope.SearchScopeFactory;
import x10dt.search.core.engine.scope.X10SearchScope;
import x10dt.ui.Messages;
import x10dt.ui.launching.X10TypeSelectionDialog;

/**
 * Utility methods for launching activities.
 * 
 * @author egeay
 */
public final class LaunchUtils {
  
  /**
   * Finds and returns a unique main type, possibly selected by end-user among a list of possible choices, from a given
   * list of resources.
   * 
   * @param resources The resources representing the searching scope. Can be <b>null</b> or empty. In such case,
   * it will automatically search within the all workspace.
   * @param projectNatureId The nature id for the projects of interest in case we receive no resources.
   * @param shell The shell to use for the dialogs we may have to create.
   * @return A non-null pair identifying uniquely the X10 main type, or <b>null</b> if we could not find any.
   * @throws InvocationTargetException Occurs if something wrong happened during the search. Such wrapper will contain a 
   * {@link CoreException} as its target in this case.
   * @throws InterruptedException Occurs if the operation got canceled by end-user.
   */
  public static ITypeInfo findMainType(final IResource[] resources, final String projectNatureId,
                                       final Shell shell) throws InvocationTargetException, InterruptedException {
    final ITypeInfo[] mainTypes = findMainTypes(resources, projectNatureId, PlatformUI.getWorkbench().getProgressService());
    if (mainTypes.length == 0) {
      final MessageBox msgBox = new MessageBox(shell, SWT.OK | SWT.ICON_INFORMATION);
      msgBox.setText(Messages.LU_MainTypeSearchResult);
      msgBox.setMessage(Messages.LU_NoMainTypeFound);
      msgBox.open();
      return null;
    } else if (mainTypes.length == 1) {
      return mainTypes[0];
    } else {
      return chooseType(mainTypes, shell);
    }
  }
  
  /**
   * Finds all the the X10 main types present under the list of resources transmitted. If no resources are given, the search
   * will occur at the workspace level for all the projects that have the project nature id equals to the one given.
   * 
   * @param resources The resources representing the searching scope. Can be <b>null</b> or empty. In such case,
   * it will automatically search within the all workspace.
   * @param projectNatureId The nature id for the projects of interest in case we receive no resources.
   * @param progressService The progress service to use for running the search operation. Can be null,
   * in which case the search is executed synchronously without progress.
   * @return A non-null but possibly empty array.
   * @throws InterruptedException Occurs if the operation got canceled by end-user.
   * @throws InvocationTargetException Occurs if something wrong happened during the search. Such wrapper will contain a 
   * {@link CoreException} as its target in this case.
   */
  public static ITypeInfo[] findMainTypes(final IResource[] resources, final String projectNatureId,
                                          final IProgressService progressService) throws InterruptedException, 
                                                                                         InvocationTargetException {
    final ITypeInfo[][] mainTypes = new ITypeInfo[1][];
    final IRunnableWithProgress runnable = new IRunnableWithProgress() {
      public void run(final IProgressMonitor monitor) throws InterruptedException {
        final IX10SearchScope searchScope;
        if (resources == null) {
          searchScope = SearchScopeFactory.createWorkspaceScope(X10SearchScope.ALL, projectNatureId);
        } else {
          searchScope = SearchScopeFactory.createSelectiveScope(X10SearchScope.ALL, resources);
        }
        mainTypes[0] = X10SearchEngine.getAllTypesWithMainMethod(searchScope, monitor);
      }
    };
    if (progressService != null) {
        progressService.busyCursorWhile(runnable);
    } else {
        runnable.run(null);
    }
    return mainTypes[0];
  }
  
  // --- Private code
  
  private LaunchUtils() {}
  
  private static ITypeInfo chooseType(final ITypeInfo[] mainTypes, final Shell shell) throws InterruptedException {
    final X10TypeSelectionDialog dialog = new X10TypeSelectionDialog(shell, mainTypes);
    if (dialog.open() == Window.OK) {
      return (ITypeInfo) dialog.getResult()[0];
    } else {
      throw new InterruptedException();
    }
  }

}
