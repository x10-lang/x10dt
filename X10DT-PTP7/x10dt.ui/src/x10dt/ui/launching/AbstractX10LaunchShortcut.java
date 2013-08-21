/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launching;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.imp.editor.UniversalEditor;
import org.eclipse.imp.preferences.PreferencesService;
import org.eclipse.imp.utils.Pair;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import x10dt.core.X10DTCorePlugin;
import x10dt.core.preferences.generated.X10Constants;
import x10dt.search.core.elements.ITypeInfo;
import x10dt.ui.Messages;
import x10dt.ui.X10DTUIPlugin;
import x10dt.ui.utils.LaunchUtils;

/**
 * Provides a common base for launch shortcuts for X10 project with the different back-ends.
 * 
 * @author egeay
 */
public abstract class AbstractX10LaunchShortcut implements ILaunchShortcut {
  
  // --- Abstract methods definition
  
  /**
   * Returns the type of configuration this shortcut is applicable to.
   * 
   * @return A non-null instance of {@link ILaunchConfigurationType}.
   */
  protected abstract ILaunchConfigurationType getConfigurationType();
  
  /**
   * Returns the project nature id of interest in case we search for all the relevant projects automatically under the
   * workspace root.
   * 
   * @return A non-null string identifying the nature.
   */
  protected abstract String getProjectNatureId();
  
  /**
   * Initializes the launch configuration attributes for the particular main type provided.
   * 
   * @param workingCopy The launch configuration working copy to consider.
   * @param type The X10 main type of interest.
   * @throws CoreException Occurs if we could not read some attributes from the working copy.
   */
  protected abstract void setLaunchConfigurationAttributes(final ILaunchConfigurationWorkingCopy workingCopy,
                                                           final ITypeInfo type) throws CoreException;
  
  // --- Interface methods implementation

  public final void launch(final ISelection selection, final String mode) {
    if (selection instanceof IStructuredSelection) {
      final IStructuredSelection structuredSelection = (IStructuredSelection) selection;
      final Collection<IResource> resources = new ArrayList<IResource>();
      final Iterator<?> it = structuredSelection.iterator();
      for (int i = 0, size = structuredSelection.size(); i < size; ++i) {
        final Object obj = it.next();
        if (obj instanceof IResource) {
          resources.add((IResource) obj);
        } else if (obj instanceof IJavaElement) {
          resources.add(((IJavaElement) obj).getResource());
        }
      }
      searchAndLaunch(resources.toArray(new IResource[resources.size()]), mode);
    }
  }

  public final void launch(final IEditorPart editor, final String mode) {
    if (editor instanceof UniversalEditor) {
      final IFile file = ((IFileEditorInput) editor.getEditorInput()).getFile();
      searchAndLaunch(new IResource[] { file }, mode);
    }
  }
  
  // --- Code for sub-classes
  
  protected Pair<Integer, ILaunchConfiguration> chooseConfiguration(final List<ILaunchConfiguration> configList,
                                                                    @SuppressWarnings("unused") final String mode) {
    final IDebugModelPresentation labelProvider = DebugUITools.newDebugModelPresentation();
    final ElementListSelectionDialog dialog = new ElementListSelectionDialog(getShell(), labelProvider);
    dialog.setElements(configList.toArray());
    dialog.setTitle(Messages.AXLS_MultipleConfDialogTitle);
    dialog.setMessage(Messages.AXLS_MultipleConfDialogMsg);
    dialog.setMultipleSelection(false);
    final int result = dialog.open();
    labelProvider.dispose();
    return new Pair<Integer, ILaunchConfiguration>(result, (ILaunchConfiguration) dialog.getFirstResult());
  }
  
  protected final Shell getShell() {
    final IWorkbenchWindow window = X10DTUIPlugin.getInstance().getWorkbench().getActiveWorkbenchWindow();
    return (window == null) ? null : window.getShell();
  }
  
  protected boolean launchConfigMatches(final ILaunchConfiguration config, final String typeName,
                                        final String projectName) throws CoreException {
    // By default, we consider (like for JDT) only the project and main type to find a possible match.
    // One should override this method if he/she wants a more restrictive matching policy.
    return config.getAttribute(IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME, "").equals(typeName) && //$NON-NLS-1$
           config.getAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, "").equals(projectName); //$NON-NLS-1$
  }

  // --- Private code
  
  /**
   * Makes sure that the compiler's debug preference is turned on, so that the X10 application
   * will have the necessary debug information.
   */
  private void checkCompileDebugPreference(final IProject project) {
    if (!new PreferencesService(project, X10DTCorePlugin.kLanguageName).getBooleanPreference(X10Constants.P_DEBUG)) {
      ErrorDialog.openError(getShell(), Messages.AXLS_NoDebugInfoErrorTitle, Messages.AXLS_NoDebugInfoErrorMsg,
                            new Status(IStatus.ERROR, X10DTUIPlugin.PLUGIN_ID, "")); //$NON-NLS-1$
    }
  }
  
  
  // We will need to use the new method "generateLaunchConfigurationName" once Galileo won't be supported anymore by X10DT.
  private ILaunchConfiguration createConfiguration(final ITypeInfo typeInfo) {
    final ILaunchConfigurationType launchConfType = getConfigurationType();
    final String namePrefix = typeInfo.getName();
    final String confName = DebugPlugin.getDefault().getLaunchManager().generateLaunchConfigurationName(namePrefix);
    try {
      final ILaunchConfigurationWorkingCopy workingCopy = launchConfType.newInstance(null, confName);
      setLaunchConfigurationAttributes(workingCopy, typeInfo);
      workingCopy.setMappedResources(new IResource[] { typeInfo.getCompilationUnit().getProject().getRawProject() });
      return workingCopy.doSave();
    } catch (CoreException except) {
      ErrorDialog.openError(getShell(), Messages.AXLS_ConfCreationError, 
                            NLS.bind(Messages.AXLS_ConfCreationSavingErrorMsg, launchConfType.getName()), except.getStatus());
    }
    return null;
  }
  
  private Pair<Integer, ILaunchConfiguration> findLaunchConfiguration(final ILaunchConfigurationType configType, 
                                                                      final String typeName, final String projectName,
                                                                      final String mode) {
    final List<ILaunchConfiguration> candidateConfigs = new ArrayList<ILaunchConfiguration>();
    try {
      final ILaunchConfiguration[] configs = DebugPlugin.getDefault().getLaunchManager().getLaunchConfigurations(configType);
      for (final ILaunchConfiguration config : configs) {
        if (launchConfigMatches(config, typeName, projectName)) {
          final ILaunchConfigurationWorkingCopy launchConfigWC = config.getWorkingCopy();
          candidateConfigs.add(launchConfigWC);
        }
      }
    } catch (CoreException except) {
      X10DTUIPlugin.getInstance().getLog().log(except.getStatus());
      return new Pair<Integer, ILaunchConfiguration>(Window.CANCEL, null);
    }
    int candidateCount = candidateConfigs.size();
    switch (candidateCount) {
      case 0:
        return new Pair<Integer, ILaunchConfiguration>(Window.OK, null);
      case 1:
        return new Pair<Integer, ILaunchConfiguration>(Window.OK, candidateConfigs.get(0));
      default:
        return chooseConfiguration(candidateConfigs, mode);
    }
  }

  private void searchAndLaunch(final IResource[] selection, final String mode) {
    try {
      final ITypeInfo mainType = LaunchUtils.findMainType(selection, getProjectNatureId(), getShell());
      if (mainType != null) {
        // The following check shouldn't be necessary, since this ILaunchShortcut implementation
        // isn't used for debug-mode launches. I.e., we should never get here in that case.
        if (ILaunchManager.DEBUG_MODE.equals(mode)) {
          checkCompileDebugPreference(mainType.getCompilationUnit().getProject().getRawProject());
        }

        final String projectName = mainType.getCompilationUnit().getProject().getName();
        final Pair<Integer, ILaunchConfiguration> pair = findLaunchConfiguration(getConfigurationType(), mainType.getName(),
                                                                                 projectName, mode);
        if (pair.first == Window.OK) {
          ILaunchConfiguration config = pair.second;
          if (config == null) {
            config = createConfiguration(mainType);
          }
          if (config != null) {
            DebugUITools.launch(config, mode);
          }          
        }
      }
    } catch (InterruptedException except) {
      // Nothing to do.
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
    }
  }

}
