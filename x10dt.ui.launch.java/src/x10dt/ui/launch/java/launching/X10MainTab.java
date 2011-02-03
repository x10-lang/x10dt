package x10dt.ui.launch.java.launching;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.imp.model.ISourceEntity;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.model.ModelFactory;
import org.eclipse.imp.utils.BuildPathUtils;
import org.eclipse.imp.utils.Pair;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaMainTab;
import org.eclipse.jdt.internal.debug.ui.JDIDebugUIPlugin;
import org.eclipse.jdt.internal.debug.ui.launcher.LauncherMessages;
import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import polyglot.types.ClassType;
import x10dt.ui.Messages;
import x10dt.ui.X10DTUIPlugin;
import x10dt.ui.editor.X10LabelProvider;
import x10dt.ui.launch.core.LaunchCore;
import x10dt.ui.utils.LaunchUtils;

public class X10MainTab extends JavaMainTab {

  // --- Overridden methods
  
  protected IJavaElement getContext() {
    IWorkbenchPage page = JDIDebugUIPlugin.getActivePage();
    if (page != null) {
      ISelection selection = page.getSelection();
      if (selection instanceof IStructuredSelection) {
        IStructuredSelection ss = (IStructuredSelection) selection;
        if (!ss.isEmpty()) {
          Object obj = ss.getFirstElement();
          if (obj instanceof IJavaElement) {
            return (IJavaElement) obj;
          }
          if (obj instanceof IFolder) {
            IJavaElement je = JavaCore.create((IFolder) obj);
            if (je == null) {
              IProject pro = ((IResource) obj).getProject();
              je = JavaCore.create(pro);
            }
            if (je != null) {
              return je;
            }
          } else if (obj instanceof IFile) {
            IFile file = (IFile) obj;
            // Pretend as if the corresponding Java source file was selected
            if (file.getFileExtension().equals("x10")) {
              IProject project = file.getProject();
              IFile javaSrcFile = project.getFile(file.getProjectRelativePath().removeFileExtension().addFileExtension("java"));
              IJavaElement javaElem = JavaCore.create(javaSrcFile);
              if (javaElem == null) {
                IProject pro = ((IResource) obj).getProject();
                javaElem = JavaCore.create(pro);
              }
              if (javaElem != null) {
                return javaElem;
              }
            }
          }
        }
      }
      IEditorPart part = page.getActiveEditor();
      if (part != null) {
        IEditorInput input = part.getEditorInput();
        return (IJavaElement) input.getAdapter(IJavaElement.class);
      }
    }
    return null;
  }
  
  protected void handleSearchButtonSelected() {
    final ISourceProject project = getProject();
    final ISourceEntity[] scope;
    if ((project == null) || ! project.getRawProject().exists()) {
      scope = null;
    } else {
      boolean hasValidNature = false;
      try {
        hasValidNature = project.getRawProject().hasNature(LaunchCore.X10_PRJ_JAVA_NATURE_ID);
      } catch (CoreException except) {
        // Do nothing.
      }
      scope = hasValidNature ? new ISourceEntity[] { project } : null;
    }
    try {
      final Pair<ClassType, ISourceEntity> mainType = LaunchUtils.findMainType(scope, LaunchCore.X10_PRJ_JAVA_NATURE_ID,
                                                                              getShell());
      if (mainType != null) {
        super.fMainText.setText(mainType.first.fullName().toString());
        if ((project == null) || ! project.getRawProject().exists()) {
          super.fProjText.setText(mainType.second.getProject().getName());
        }
      }
    } catch (InvocationTargetException except) {
      if (except.getTargetException() instanceof CoreException) {
        ErrorDialog.openError(getShell(), Messages.AXLS_MainTypeSearchError, Messages.AXLS_MainTypeSearchErrorMsg, 
                              ((CoreException) except.getTargetException()).getStatus());
        X10DTUIPlugin.getInstance().getLog().log(((CoreException) except.getTargetException()).getStatus());
      } else {
        final IStatus status = new Status(IStatus.ERROR, X10DTUIPlugin.PLUGIN_ID, Messages.AXLS_MainTypeSearchInternalError,
                                          except.getTargetException());
        ErrorDialog.openError(getShell(), Messages.AXLS_MainTypeSearchError, Messages.AXLS_MainTypeSearchErrorMsg, status);
        X10DTUIPlugin.getInstance().getLog().log(status);
      }
    } catch (InterruptedException except) {
      // Do nothing.
    }
  }
  
  
  /**
	 * Return the IJavaProject corresponding to the project name in the project name
	 * text field, or null if the text does not match a project name.
	 */
	protected ISourceProject getProject() {
		String projectName = fProjText.getText().trim();
		if (projectName.length() < 1) {
			return null;
		}
		return BuildPathUtils.getProject(projectName);		
	}
	
  	protected void handleProjectButtonSelected() {
  		ISourceProject project = chooseJavaProject();
		if (project == null) {
			return;
		}
		String projectName = project.getName();
		fProjText.setText(projectName);		
	}
  
  /**
	 * chooses a project for the type of java launch config that it is
	 * @return
	 */
	private ISourceProject chooseJavaProject() {
		ILabelProvider labelProvider= new X10LabelProvider();
		ElementListSelectionDialog dialog= new ElementListSelectionDialog(getShell(), labelProvider);
		dialog.setTitle(LauncherMessages.AbstractJavaMainTab_4); 
		dialog.setMessage(LauncherMessages.AbstractJavaMainTab_3); 
		dialog.setElements(ModelFactory.getModelRoot().getProjects());
		
		ISourceProject javaProject= getProject();
		if (javaProject != null) {
			dialog.setInitialSelections(new Object[] { javaProject });
		}
		if (dialog.open() == Window.OK) {			
			return (ISourceProject) dialog.getFirstResult();
		}		
		return null;		
	}
}
