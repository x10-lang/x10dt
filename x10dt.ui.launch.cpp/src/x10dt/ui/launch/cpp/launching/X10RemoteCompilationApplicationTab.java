/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching;


import static org.eclipse.ptp.core.IPTPLaunchConfigurationConstants.ATTR_PROJECT_NAME;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.IS_VALID;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ptp.launch.ui.tabs.LaunchConfigurationTab;
import org.eclipse.ptp.launch.ui.LaunchImages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import x10dt.core.X10DTCorePlugin;
import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.cpp.CppLaunchCore;
import x10dt.ui.launch.cpp.LaunchMessages;

final class X10RemoteCompilationApplicationTab extends LaunchConfigurationTab implements ILaunchConfigurationTab {


  // --- Interface methods implementation

  public void createControl(final Composite parent) {
    final Composite composite = new Composite(parent, SWT.NULL);
    composite.setFont(parent.getFont());
    composite.setLayout(new GridLayout(1, false));
    composite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false));

    createProjectEditor(composite);

    setControl(composite);
  }
  
  public String getName() {
    return LaunchMessages.CAT_ProjectName;
  }
  

  public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
    final String projectName = this.fProjectText.getText().trim();
    if (projectName.length() > 0) {
      configuration.setAttribute(ATTR_PROJECT_NAME, projectName);
      configuration.setAttribute(org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, projectName);
    }
  }

  public void setDefaults(final ILaunchConfigurationWorkingCopy configuration) {
    final IResource context = getCurrentSelectionContext();
    if (context == null) {
      configuration.setAttribute(ATTR_PROJECT_NAME, (String) null);
    } else {
      configuration.setAttribute(ATTR_PROJECT_NAME, context.getProject().getName());
    }
  }

  // --- Overridden methods

  public Image getImage() {
    return LaunchImages.getImage(LaunchImages.IMG_MAIN_TAB);
  }

  public void initializeFrom(final ILaunchConfiguration configuration) {
    super.initializeFrom(configuration);
    try {
      setTextWithoutNotification(this.fProjectText, configuration, ATTR_PROJECT_NAME);
    } catch (CoreException except) {
      setErrorMessage(LaunchMessages.CAT_ReadConfigError);
      CppLaunchCore.log(except.getStatus());
    }
  }

  public boolean isValid(final ILaunchConfiguration configuration) {
    setErrorMessage(null);
    setMessage(null);
    try {
      ILaunchConfigurationWorkingCopy wc = configuration.getWorkingCopy();
      wc.setAttribute(ConnectionTab.IS_VALID, false);
      wc.doSave();
    } catch (CoreException e){
      CppLaunchCore.getInstance().getLog().log(e.getStatus());
    }

    final String projectName = this.fProjectText.getText().trim();
    if (projectName.length() == 0) {
      setErrorMessage(LaunchMessages.CAT_RequiredPrjName);
      return false;
    } else {
      final IWorkspace workspace = ResourcesPlugin.getWorkspace();
      final IStatus status = workspace.validateName(projectName, IResource.PROJECT);
      if (status.isOK()) {
        final IProject project = workspace.getRoot().getProject(projectName);
        if (!project.exists()) {
          setErrorMessage(NLS.bind(LaunchMessages.CAT_NoExistingProject, projectName));
          return false;
        }
        try {
          if (!project.hasNature(X10DTCorePlugin.X10_CPP_PRJ_NATURE_ID)) {
            setErrorMessage(NLS.bind(LaunchMessages.CAT_NoCPPProjectNature, projectName));
            return false;
          }
        } catch (CoreException except) {
          setErrorMessage(NLS.bind(LaunchMessages.CAT_ProjectNatureAccessError, projectName));
          return false;
        }
        if (!project.isOpen()) {
          setErrorMessage(NLS.bind(LaunchMessages.CAT_ClosedProject, projectName));
          return false;
        }
        try {
          if (!uniqueConf(projectName)){
            setErrorMessage(NLS.bind(LaunchMessages.CAT_MultipleCompilationConfs, projectName));
          }
        } catch (CoreException except){
          setErrorMessage(NLS.bind(LaunchMessages.CAT_MultipleCompilationConfs, projectName));
        }
        
      } else {
        setErrorMessage(NLS.bind(LaunchMessages.CAT_IllegalPrjName, projectName));
        return false;
      }
    }
    
    try {
      ILaunchConfigurationWorkingCopy wc = configuration.getWorkingCopy();
      wc.setAttribute(IS_VALID, true);
      wc.doSave();
    } catch (CoreException e){
      CppLaunchCore.getInstance().getLog().log(e.getStatus());
    }
    return true;
  }
  
 
  // --- Private code
 
  private boolean uniqueConf(String projectName) throws CoreException {
    ILaunchConfiguration[] confs = DebugPlugin.getDefault().getLaunchManager().getLaunchConfigurations();
    int count = 0;
    for (ILaunchConfiguration conf: confs){
      if (conf.getType().getIdentifier().equals(CppLaunchCore.REMOTE_COMPILATION_LAUNCH_CONF_TYPE) && projectName.equals(conf.getAttribute(ATTR_PROJECT_NAME, Constants.EMPTY_STR))){
        count++;
        if (count == 2) return false;
      }
    }
    return true;
  }

  private void createProjectEditor(final Composite parent) {
    final Group group = new Group(parent, SWT.NONE);
    group.setFont(parent.getFont());
    group.setLayout(new GridLayout(2, false));
    group.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
    group.setText(LaunchMessages.CAT_ProjectGroupName);

    this.fProjectText = new Text(group, SWT.SINGLE | SWT.BORDER);
    this.fProjectText.setFont(group.getFont());
    this.fProjectText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    this.fProjectText.addModifyListener(new ProjectTextModificationListener());

    this.fProjectBt = createPushButton(group, LaunchMessages.CAT_BrowseButton, null /* image */);
    this.fProjectBt.addSelectionListener(new ProjectBtSelectionListener());
  }
  
  private IResource getCurrentSelectionContext() {
    final IWorkbenchWindow workbenchWindow = CppLaunchCore.getInstance().getWorkbench().getActiveWorkbenchWindow();
    if (workbenchWindow != null) {
      final IWorkbenchPage activePage = workbenchWindow.getActivePage();
      if (activePage != null) {
        final ISelection selection = activePage.getSelection();
        if (selection instanceof IStructuredSelection) {
          return getCurrentSelectionContext((IStructuredSelection) selection);
        }
      }
    }
    return null;
  }
  
  private IResource getCurrentSelectionContext(final IStructuredSelection selection) {
    final Object selectedObject;
    if (selection.size() > 1) {
      // Unless they have a common project, it doesn't really makes sense here.
      IProject commonProject = null;
      for (final Iterator<?> it = selection.iterator(); it.hasNext();) {
        final Object element = it.next();
        final IProject curPrj;
        if (element instanceof IJavaElement) {
          curPrj = ((IJavaElement) element).getJavaProject().getProject();
        } else if (element instanceof IResource) {
          curPrj = ((IResource) element).getProject();
        } else {
          curPrj = null;
        }
        if (commonProject == null) {
          commonProject = curPrj;
        } else if ((curPrj != null) && ! commonProject.equals(curPrj)) {
          commonProject = null;
          break;
        }
      }
      selectedObject = commonProject;
    } else {
      selectedObject = selection.getFirstElement();
    }
    final IResource resource;
    if (selectedObject instanceof IJavaElement) {
      resource = ((IJavaElement) selectedObject).getResource();
    } else if (selectedObject instanceof IResource) {
      resource = (IResource) selectedObject;
    } else {
      resource = null;
    }
    if ((resource != null) && resource.exists()) {
      final IProject project = resource.getProject();
      try {
        if (project.hasNature(X10DTCorePlugin.X10_CPP_PRJ_NATURE_ID)) {
          return resource;
        }
      } catch (CoreException except) {
        // Simply forgets.
      }
    }
    return null;
  }

  private void setTextWithoutNotification(final Text text, final ILaunchConfiguration configuration, 
                                          final String name) throws CoreException {
    final Listener[] listeners = text.getListeners(SWT.Modify);
    for (final Listener listener : listeners) {
      text.removeListener(SWT.Modify, listener);
    }
    text.setText(configuration.getAttribute(name, Constants.EMPTY_STR));
    for (final Listener listener : listeners) {
      text.addListener(SWT.Modify, listener);
    }
  }

  // --- Private classes

  private final class ProjectBtSelectionListener implements SelectionListener {

    // --- Interface methods implementation

    public void widgetDefaultSelected(final SelectionEvent event) {
    }

    public void widgetSelected(final SelectionEvent event) {
      final ILabelProvider labelProvider = new JavaElementLabelProvider(JavaElementLabelProvider.SHOW_DEFAULT);
      final ElementListSelectionDialog dialog = new ElementListSelectionDialog(getShell(), labelProvider);
      dialog.setTitle(LaunchMessages.CAT_PrjSelectionDialogTitle);
      dialog.setMessage(LaunchMessages.CAT_PrjSelectionDialogMsg);
      try {
        dialog.setElements(getProjectList());
      } catch (CoreException except) {
        setErrorMessage(except.getMessage());
        CppLaunchCore.log(except.getStatus());
      }
      if (dialog.open() == Window.OK) {
        final IProject project = (IProject) dialog.getFirstResult();
        X10RemoteCompilationApplicationTab.this.fProjectText.setText(project.getName());
      }
    }

    // --- Private code

    private IProject[] getProjectList() throws CoreException {
      final IProject[] allProjects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
      final Collection<IProject> projects = new ArrayList<IProject>(allProjects.length);
      for (final IProject project : allProjects) {
        if (project.isOpen() && project.hasNature(X10DTCorePlugin.X10_CPP_PRJ_NATURE_ID)) {
          projects.add(project);
        }
      }
      return projects.toArray(new IProject[projects.size()]);
    }

  }

  private final class ProjectTextModificationListener implements ModifyListener {

    // --- Interface methods implementation

    public void modifyText(final ModifyEvent event) {
      setDirty(true);
      updateLaunchConfigurationDialog();
    }

  }


  public String getProjectName(){
    return (this.fProjectText != null) ? this.fProjectText.getText() : Constants.EMPTY_STR;
  }


  private Text fProjectText;

  private Button fProjectBt;

 

 

}
