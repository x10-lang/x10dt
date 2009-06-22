package com.ibm.watson.safari.x10.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import com.ibm.watson.safari.x10.X10ProjectNature;

/**
 * A simple action to enable the X10 builder for the currently-selected project.
 * @see IWorkbenchWindowActionDelegate
 */
public class BuilderEnable implements IWorkbenchWindowActionDelegate {
    private IProject fProject;

    public BuilderEnable() {}

    /**
     * The action has been activated. The argument of the method represents the 'real' action sitting in the workbench UI.
     * @see IWorkbenchWindowActionDelegate#run
     */
    public void run(IAction action) {
	new X10ProjectNature().addToProject(fProject);
    }

    /**
     * Selection in the workbench has been changed. We can change the state of the 'real' action here if we want, but this can only happen after the
     * delegate has been created.
     * @see IWorkbenchWindowActionDelegate#selectionChanged
     */
    public void selectionChanged(IAction action, ISelection selection) {
	if (selection instanceof IStructuredSelection) {
	    IStructuredSelection ss= (IStructuredSelection) selection;
	    Object first= ss.getFirstElement();
	    if (first instanceof IProject) {
		fProject= (IProject) first;
	    } else if (first instanceof IJavaProject) {
		fProject= ((IJavaProject) first).getProject();
	    }
	}
    }

    public void dispose() {}

    public void init(IWorkbenchWindow window) {}
}