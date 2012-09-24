/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.editors;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.imp.utils.Pair;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ptp.core.IPTPLaunchConfigurationConstants;
import org.eclipse.ptp.remotetools.environment.core.ITargetElement;
import org.eclipse.ptp.services.core.IServiceProvider;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.forms.editor.SharedHeaderFormEditor;
import org.eclipse.ui.forms.widgets.Form;

import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.dialogs.DialogsFactory;
import x10dt.ui.launch.core.platform_conf.EValidationStatus;
import x10dt.ui.launch.core.utils.CoreResourceUtils;
import x10dt.ui.launch.cpp.CppLaunchCore;
import x10dt.ui.launch.cpp.CppLaunchImages;
import x10dt.ui.launch.cpp.LaunchMessages;
import x10dt.ui.launch.cpp.launching.CppBackEndLaunchConfAttrs;
import x10dt.ui.launch.cpp.launching.services.IPlatformConfLaunchConfSyncServices;
import x10dt.ui.launch.cpp.launching.services.LaunchConfServicesFactory;
import x10dt.ui.launch.cpp.platform_conf.ICommunicationInterfaceConf;
import x10dt.ui.launch.cpp.platform_conf.ICppCompilationConf;
import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConf;
import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConfWorkCopy;
import x10dt.ui.launch.cpp.platform_conf.X10PlatformConfFactory;
import x10dt.ui.launch.cpp.platform_conf.validation.IX10PlatformChecker;
import x10dt.ui.launch.cpp.platform_conf.validation.IX10PlatformValidationListener;
import x10dt.ui.launch.cpp.platform_conf.validation.PlatformCheckerFactory;

/**
 * Main class activating the editor for editing the X10 Platform Configuration file.
 * 
 * @author egeay
 */
public final class X10PlatformConfFormEditor extends SharedHeaderFormEditor 
                                             implements ICompletePageChangedListener, 
                                                        IX10PlatformValidationListener, IConnectionTypeListener {
  
  // --- ICompletePageChangedListener's interface methods implementation
  
  public final void completePageChanged(final X10FormPage formPage, final boolean isComplete) {
    if (formPage.isPageComplete()) {
      boolean allPagesComplete = true;
      for (final Object page : super.pages) {
        if ((page != null) && (page != formPage) && ! ((X10FormPage) page).isPageComplete()) {
          allPagesComplete = false;
          break;
        }
      }
      this.fValidateAction.setEnabled(allPagesComplete);
      if (allPagesComplete) {
        this.fValidateAction.setImageDescriptor(this.fUncheckedPlatformImg);
      }
    } else {
      this.fValidateAction.setEnabled(false);
    }
  }
  
  // --- IX10PlatformValidationListener's interface methods implementation
  
  public void platformCommunicationInterfaceValidated() {
    getSite().getShell().getDisplay().asyncExec(new Runnable() {
      
      public void run() {
        getHeaderForm().getMessageManager().removeMessage(X10PlatformConfFormEditor.this);
      }
      
    });
    this.fValidateAction.setImageDescriptor(this.fValidPlatformImg);  	
  }

	public void platformCommunicationInterfaceValidationFailure(final String message) {
		this.fValidateAction.setImageDescriptor(this.fInvalidPlatformImg);
    getSite().getShell().getDisplay().asyncExec(new Runnable() {
      
      public void run() {
        getHeaderForm().getMessageManager().removeMessage(X10PlatformConfFormEditor.this);
        getHeaderForm().getMessageManager().addMessage(X10PlatformConfFormEditor.this, message, null, IMessageProvider.ERROR);
      }
      
    });
	}
  
  public void platformCppCompilationValidated() {
    this.fCppValidationError = null;
    if (! isDirty()) {
      final IFile file = ((IFileEditorInput) getEditorInput()).getFile();
      CoreResourceUtils.deletePlatformConfValidationMarkers(file);
    }
  }
  
  public void platformCppCompilationValidationFailure(final String topMessage, final String command, 
                                                      final String errorMessage) {
    this.fValidateAction.setImageDescriptor(this.fInvalidPlatformImg);
    this.fCppValidationError = NLS.bind(LaunchMessages.XPCFE_ValidationFailureDlgMsg, errorMessage);
    if (! isDirty()) {
      updateMarkers();
    }
    
    getSite().getShell().getDisplay().asyncExec(new Runnable() {
      
      public void run() {
        getHeaderForm().getMessageManager().removeMessage(X10PlatformConfFormEditor.this);
        getHeaderForm().getMessageManager().addMessage(X10PlatformConfFormEditor.this, topMessage, 
                                                       new Pair<String,String>(command, errorMessage), 
                                                       IMessageProvider.ERROR);
      }
      
    });
  }
  
  public void platformCppCompilationValidationError(final Exception exception) {
    this.fValidateAction.setImageDescriptor(this.fErrorPlatformImg);
    this.fCppValidationError = NLS.bind(LaunchMessages.XPCFE_ValidationErrorDlgMsg, exception.getMessage());
    if (! isDirty()) {
      updateMarkers();
    }
    
    getSite().getShell().getDisplay().asyncExec(new Runnable() {
      
      public void run() {
        getHeaderForm().getMessageManager().removeMessage(X10PlatformConfFormEditor.this);
        getHeaderForm().getMessageManager().addMessage(X10PlatformConfFormEditor.this, exception.getMessage(), exception, 
                                                       IMessageProvider.ERROR);
      }
      
    });
  }
  
  public void remoteConnectionFailure(final Exception exception) {
    final IAction validationAction = this.fValidateAction;    
    getSite().getShell().getDisplay().syncExec(new Runnable() {
      
      public void run() {
        if (validationAction.isEnabled()) {
          validationAction.setImageDescriptor(X10PlatformConfFormEditor.this.fInvalidPlatformImg);
        }
        getHeaderForm().getMessageManager().addMessage(X10PlatformConfFormEditor.this, 
                                                       LaunchMessages.XPCFE_RemoteConnValidationError, exception, 
                                                       IMessageProvider.ERROR);
      }
      
    });
  }
  
  public void remoteConnectionUnknownStatus() {
    final IAction validationAction = this.fValidateAction;
    getSite().getShell().getDisplay().syncExec(new Runnable() {
      
      public void run() {
        getHeaderForm().getMessageManager().removeMessage(X10PlatformConfFormEditor.this);
        if (validationAction.isEnabled()) {
          validationAction.setImageDescriptor(X10PlatformConfFormEditor.this.fUncheckedPlatformImg);
        }   
      }
      
    });
  }
  
  public void remoteConnectionValidated(final ITargetElement targetElement) {
    final IAction validationAction = this.fValidateAction;
    getSite().getShell().getDisplay().syncExec(new Runnable() {
      
      public void run() {
        getHeaderForm().getMessageManager().removeMessage(X10PlatformConfFormEditor.this);
        if (validationAction.isEnabled()) {
          validationAction.setImageDescriptor(X10PlatformConfFormEditor.this.fUncheckedPlatformImg);
        }
      }
      
    });
  }
  
  public void serviceProviderFailure(final CoreException exception) {
  	// Should never occur.
  	CppLaunchCore.log(exception.getStatus());
  }
  
  // --- IConnectionTypeListener's interface methods implementation
  
  public void connectionChanged(final boolean isLocal, final String remoteConnectionName,
                                final EValidationStatus validationStatus, final boolean shouldDeriveInfo) {
    getHeaderForm().getMessageManager().removeMessage(this);
    if (this.fValidateAction.isEnabled()) {
      this.fValidateAction.setImageDescriptor(this.fUncheckedPlatformImg);
    }
  }
  
  // --- Abstract methods implementation

  protected void addPages() {
    setActiveEditor(this);
    setPartName(getEditorInput().getName());
    try {
      addPage(new ConnectionAndCommunicationConfPage(this));
      addPage(new X10CompilationConfigurationPage(this));
    } catch (PartInitException except) {
      CppLaunchCore.log(except.getStatus());
    }
  }

  public void doSave(final IProgressMonitor monitor) {
    final IFile file = ((IFileEditorInput) getEditorInput()).getFile();
    synchronized (file) {
      {
        final IProject project = file.getProject();
        final IWorkspace workspace = project.getWorkspace();
        final IWorkspaceDescription description = workspace.getDescription();
        boolean isAutoBuilding = description.isAutoBuilding();
        if (isAutoBuilding == true) {
          description.setAutoBuilding(false);
          try {
            workspace.setDescription(description);
          } catch (CoreException except) {
            CppLaunchCore.log(except.getStatus());
          }
        }

        monitor.beginTask(null, 3);
        final BuildChangeListener buildListener = new BuildChangeListener();
        try {
          project.getWorkspace().addResourceChangeListener(buildListener);
          project.build(IncrementalProjectBuilder.CLEAN_BUILD, new SubProgressMonitor(monitor, 1));
          while (buildListener.hasFinished())
            ;
        } catch (CoreException except) {
          DialogsFactory.createErrorBuilder()
                        .setDetailedMessage(except.getStatus())
                        .createAndOpen(getEditorSite(), LaunchMessages.XPCFE_ConfSavingErrorDlgTitle,
                                       LaunchMessages.XPCFE_CouldNotCleanOutputDir);
        } finally {
          if (isAutoBuilding) {
            description.setAutoBuilding(true);
            try {
              workspace.setDescription(description);
            } catch (CoreException except) {
              CppLaunchCore.log(except.getStatus());
            }
          }
          project.getWorkspace().removeResourceChangeListener(buildListener);
        }
        if (monitor.isCanceled()) {
          return;
        }
      }

      try {
        getCurrentPlatformConf().applyChanges();
        
        X10PlatformConfFactory.save(file, getCurrentPlatformConf());
        monitor.worked(1);
        
        commitPages(true);
        updateMarkers();
        updateExistingLaunchConfigurations(file.getProject().getName());
        monitor.worked(1);
      } catch (CoreException except) {
        DialogsFactory.createErrorBuilder().setDetailedMessage(except.getStatus())
                      .createAndOpen(getEditorSite(), LaunchMessages.XPCFE_ConfSavingErrorDlgTitle,
                                     LaunchMessages.XPCFE_ContentCopyError);
      } finally {
        monitor.done();
      }
    }
  }

  public void doSaveAs() {
  }

  public boolean isSaveAsAllowed() {
    return false;
  }
  
  // --- Overridden methods
  
  protected void createHeaderContents(final IManagedForm headerForm) {
    final Form form = headerForm.getForm().getForm();
    form.setText(LaunchMessages.XPCFE_FormTitle);
    headerForm.getToolkit().decorateFormHeading(form);
    
    this.fValidateAction = new ValidateAction();
    form.getToolBarManager().add(this.fValidateAction);
    form.getToolBarManager().update(true);
    form.addMessageHyperlinkListener(new PlatformConfHyperlinkAdapter(this));
  }
  
  protected void createPages() {
    addPages();
    
    int index = -1;
    for (final Object page : super.pages) {
      final IFormPage formPage = (IFormPage) page;
      if (formPage != null) {
        formPage.createPartControl(getContainer());
        setControl(++index, formPage.getPartControl());
        formPage.getPartControl().setMenu(getContainer().getMenu());
      }
    }
    for (final Object page : super.pages) {
      if (page != null) {
        ((X10FormPage) page).postPagesCreation();
      }
    }
    ((IFormPage) super.pages.get(0)).getManagedForm().reflow(true);
    
    if (getActivePage() == -1) {
      super.setActivePage(0);
    }
    this.fValidateAction.setImageDescriptor(this.fUncheckedPlatformImg);
    this.fValidateAction.setEnabled(getCurrentPlatformConf().isComplete(false));
  }
  
  public void dispose() {
    super.dispose();
  }
  
  public void editorDirtyStateChanged() {
    super.editorDirtyStateChanged();
    getContributor().updateActions();
  }
  
  public void init(final IEditorSite site, final IEditorInput input) throws PartInitException {
    super.init(site, input);
    
    final IProject project = ((IFileEditorInput) input).getFile().getProject();
    final IX10PlatformConf platformConf = CppLaunchCore.getInstance().getPlatformConfiguration(project);
    final IX10PlatformConfWorkCopy workCopy = platformConf.createWorkingCopy();
    
    this.fX10PlatformConfs.put(workCopy.getName(), workCopy);
    this.fCurConfiguration = workCopy;
  }
  
  // --- Internal services
  
  IX10PlatformConfWorkCopy getCurrentPlatformConf() {
    return this.fCurConfiguration;
  }
  
  void performRevert() {
    final IFile file = ((IFileEditorInput) getEditorInput()).getFile();
    if (MessageDialog.openQuestion(getSite().getShell(), LaunchMessages.XPCFE_RevertPlatformActionMsg,
                                   LaunchMessages.XPCFE_RevertPlatformActionDialogMsg)) {
      final IX10PlatformConf platformConf = X10PlatformConfFactory.loadOrCreate(file);
      final IX10PlatformConfWorkCopy platformConfWorkCopy = platformConf.createWorkingCopy();

      this.fX10PlatformConfs.put(platformConfWorkCopy.getName(), platformConfWorkCopy);
      this.fCurConfiguration = platformConfWorkCopy;

      
      for (final Object page : this.pages) {
        if (page != null) {
          final IManagedForm managedForm = ((IFormPage) page).getManagedForm();
          for (final IFormPart part : managedForm.getParts()) {
            ((AbstractCommonSectionFormPart) part).initializeControls();
          }
          managedForm.commit(true);
        }
      }
    }

    editorDirtyStateChanged();
  }
  
  void setNewPlatformConfState(final String name, final IServiceProvider serviceProvider) {
    final IX10PlatformConfWorkCopy configuration = this.fX10PlatformConfs.get(name);
    final IFile confFile = ((IFileEditorInput) getEditorInput()).getFile();

    if (configuration == null) {
      final IX10PlatformConf platformConf = X10PlatformConfFactory.createFromProvider(serviceProvider, confFile);
      final IX10PlatformConfWorkCopy workCopy = platformConf.createWorkingCopy();
      
      workCopy.setName(name);
      
      this.fX10PlatformConfs.put(name, workCopy);
      this.fCurConfiguration = workCopy;
    } else {
      this.fCurConfiguration = configuration;
    }
  }
  
  // --- Private code
  
  private FormEditorActionBarContributor getContributor() {
    return (FormEditorActionBarContributor) getEditorSite().getActionBarContributor();
  }
  
  private void updateExistingLaunchConfigurations(final String projectName) {
    final ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
    final ILaunchConfigurationType launchConfType = launchManager.getLaunchConfigurationType(CppLaunchCore.LAUNCH_CONF_TYPE);
    try {
      for (final ILaunchConfiguration launchConf : launchManager.getLaunchConfigurations(launchConfType)) {
        final String curPrjName = launchConf.getAttribute(IPTPLaunchConfigurationConstants.ATTR_PROJECT_NAME, 
                                                          Constants.EMPTY_STR);
        if (projectName.equals(curPrjName) && 
            launchConf.getAttribute(CppBackEndLaunchConfAttrs.ATTR_USE_PLATFORM_CONF_DATA, true)) {
          final ICommunicationInterfaceConf commIntfConf = getCurrentPlatformConf().getCommunicationInterfaceConf();
          final IPlatformConfLaunchConfSyncServices launchConfServices = LaunchConfServicesFactory.create(commIntfConf);
          if (launchConfServices != null) {
            final ILaunchConfigurationWorkingCopy launchConfWC = launchConf.getWorkingCopy();
            launchConfServices.initOrUpdate(launchConfWC, getCurrentPlatformConf(), false);
            launchConfWC.doSave();
          }
        }
      }
    } catch (CoreException except) {
      CppLaunchCore.log(except.getStatus());
    }
  }
  
  private void updateMarkers() {
    final IFile file = ((IFileEditorInput) getEditorInput()).getFile();
    CoreResourceUtils.deletePlatformConfValidationMarkers(file);
    if (this.fCppValidationError != null) {
      CoreResourceUtils.addPlatformConfValidationMarker(((IFileEditorInput) getEditorInput()).getFile(), 
                                                        this.fCppValidationError, IMarker.SEVERITY_ERROR, 
                                                        IMarker.PRIORITY_HIGH);
    }
    if (! getCurrentPlatformConf().isComplete(false)) {
      CoreResourceUtils.addPlatformConfValidationMarker(file, LaunchMessages.XPCFE_PlatformConfNotComplete, 
                                                        IMarker.SEVERITY_ERROR, IMarker.PRIORITY_HIGH);
    }
    final EValidationStatus compValidationStatus = getCurrentPlatformConf().getCppCompilationConf().getValidationStatus();
    if ((compValidationStatus == EValidationStatus.ERROR) || (compValidationStatus == EValidationStatus.FAILURE)) {
      final String message = getCurrentPlatformConf().getCppCompilationConf().getValidationErrorMessage();
      CoreResourceUtils.addPlatformConfValidationMarker(file, message, IMarker.SEVERITY_ERROR, IMarker.PRIORITY_HIGH);
    }
  }
  
  private synchronized void validate() {
    final IX10PlatformChecker checker = PlatformCheckerFactory.create();
    checker.addValidationListener(this);
    try {
      final IRunnableWithProgress runnable = new IRunnableWithProgress() {
      
        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        	final SubMonitor subMonitor = SubMonitor.convert(monitor, 10);
          checker.validateCppCompilationConf(getCurrentPlatformConf(), subMonitor.newChild(5));
          final ICppCompilationConf compConf = getCurrentPlatformConf().getCppCompilationConf();
          if (compConf.getValidationStatus() == EValidationStatus.VALID) {
          	checker.validateCommunicationInterface(getCurrentPlatformConf(), subMonitor.newChild(5));
          } else {
          	subMonitor.done();
          }
        }
      
      };

      try {
        new ProgressMonitorDialog(getEditorSite().getShell()).run(true, true, runnable);
      } catch (InterruptedException except) {
      
      } catch (InvocationTargetException except) {
      
      }
    } finally {
      checker.removeValidationListener(this);
    }
  }
  
  // --- Private classes
  
  private final class ValidateAction extends Action {
    
    ValidateAction() {
      super(LaunchMessages.XPCFE_ValidationPlatformActionMsg);
    }
    
    // --- Overridden methods
    
    public void run() {
      validate();
    }
    
  }
  
  private static final class BuildChangeListener implements IResourceChangeListener {

    // --- Interface methods implementation
    
    public void resourceChanged(final IResourceChangeEvent event) {
      if (event.getType() == IResourceChangeEvent.POST_BUILD) {
        this.fHasFinished = true;
      }
    }
    
    // --- Internal services
    
    boolean hasFinished() {
      return this.fHasFinished;
    }
    
    // --- Fields
    
    private boolean fHasFinished;
    
  }
  
  // --- Fields
  
  private final Map<String, IX10PlatformConfWorkCopy> fX10PlatformConfs = new HashMap<String, IX10PlatformConfWorkCopy>();
  
  private IX10PlatformConfWorkCopy fCurConfiguration; 
  
  private IAction fValidateAction;
  
  private String fCppValidationError;
  
  
  private final ImageDescriptor fValidPlatformImg = CppLaunchImages.createUnmanaged(CppLaunchImages.VALID_PLATFORM_CONF);
  
  private final ImageDescriptor fInvalidPlatformImg = CppLaunchImages.createUnmanaged(CppLaunchImages.INVALID_PLATFORM_CONF);
  
  private final ImageDescriptor fErrorPlatformImg = CppLaunchImages.createUnmanaged(CppLaunchImages.PLATFORM_CONF_VALIDATION_ERROR);
  
  private final ImageDescriptor fUncheckedPlatformImg = CppLaunchImages.createUnmanaged(CppLaunchImages.INVALIDATED_PLATFORM_CONF);

}
