/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.actions;

import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.imp.smapifier.builder.SmapiProjectNature;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Action to convert a C++ back-end nature to Java back-end nature.
 * 
 * @author egeay
 */
public final class ConvertToJavaBackEndAction implements IObjectActionDelegate {

  // --- Interface methods implementation
  
  public void run(final IAction action) {
    if (this.fCurSelection instanceof IStructuredSelection) {
      for (final Iterator<?> it = ((IStructuredSelection) this.fCurSelection).iterator(); it.hasNext();) {
        final IProject project = ((IJavaProject) it.next()).getProject();
        try {
          final IProjectDescription newDescr = project.getDescription();
        
          final String[] natureIds = new String[] { 
            "x10dt.ui.launch.java.x10nature", SmapiProjectNature.k_natureID, JavaCore.NATURE_ID
          };
          newDescr.setNatureIds(natureIds);

          project.setDescription(newDescr, new NullProgressMonitor());
        } catch (CoreException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public void selectionChanged(final IAction action, final ISelection selection) {
    this.fCurSelection = selection;
  }

  public void setActivePart(final IAction action, final IWorkbenchPart targetPart) {
  }
  
  // --- Fields
  
  private ISelection fCurSelection;

}
