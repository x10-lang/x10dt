/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.editors;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ptp.remotetools.environment.core.ITargetElement;
import org.eclipse.ptp.services.core.IServiceProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.IMessage;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.forms.editor.SharedHeaderFormEditor;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.internal.forms.MessageManager;

import x10dt.ui.X10DTUIPlugin;
import x10dt.ui.launch.core.platform_conf.EValidationStatus;
import x10dt.ui.launch.core.utils.CoreResourceUtils;
import x10dt.ui.launch.cpp.CppLaunchCore;
import x10dt.ui.launch.cpp.CppLaunchImages;
import x10dt.ui.launch.cpp.LaunchMessages;
import x10dt.ui.launch.cpp.platform_conf.ICppCompilationConf;
import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConf;
import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConfWorkCopy;
import x10dt.ui.launch.cpp.platform_conf.X10PlatformConfFactory;
import x10dt.ui.launch.cpp.platform_conf.validation.IX10PlatformChecker;
import x10dt.ui.launch.cpp.platform_conf.validation.IX10PlatformValidationListener;
import x10dt.ui.launch.cpp.platform_conf.validation.PlatformCheckerFactory;
import x10dt.ui.typeHierarchy.X10ElementImageDescriptor;
import x10dt.ui.typeHierarchy.X10ElementImageProvider;

/**
 * Main class activating the editor for editing the X10 Platform Configuration file.
 * 
 * @author egeay
 */
@SuppressWarnings({"restriction"})
public final class X10PlatformConfFormEditor extends SharedHeaderFormEditor 
                                             implements IPropertyListener, ICompletePageChangedListener, 
                                                        IX10PlatformValidationListener, IConnectionTypeListener {
  
  // --- IPropertyListener's interface methods implementation
  
	public void propertyChanged(final Object source, final int propId) {
		
	}
  
	public ImageDescriptor getCUResourceImageDescriptor() {
		ImageDescriptor desc = fFileImg;

		try {
			int severity = getFile().findMaxProblemSeverity(IMarker.PROBLEM,
					true, IResource.DEPTH_ZERO);
			if (severity == IMarker.SEVERITY_ERROR) {
				severity = X10ElementImageDescriptor.ERROR;
			} else if (severity == IMarker.SEVERITY_WARNING) {
				severity = X10ElementImageDescriptor.WARNING;
			}

			if (severity > 0) {
				desc = new X10ElementImageDescriptor(desc, severity,
						X10ElementImageProvider.SMALL_SIZE);
			}
		} catch (CoreException e) {
			X10DTUIPlugin.log(e);
		}

		return desc;
	}
	
	public ImageDescriptor getCUResourceImageDescriptor(List<IMessage> messages) {
		ImageDescriptor desc = fFileImg;

		int severity = 0;
		if(messages.size() > 0)
		{
			for(IMessage msg : messages)
			{
				if(msg.getMessageType() == IMessage.ERROR)
				{
					severity = X10ElementImageDescriptor.ERROR;
				}
				
				else if (severity == IMessage.WARNING) {
					severity = X10ElementImageDescriptor.WARNING;
				}
				
				if(severity == X10ElementImageDescriptor.ERROR)
				{
					break;
				}
			}
		}
		
		if (severity > 0) {
			desc = new X10ElementImageDescriptor(desc, severity,
					X10ElementImageProvider.SMALL_SIZE);
		}
		
		return desc;
	}
  
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
    } else {
      this.fValidateAction.setEnabled(false);
    }
  }
  
  // --- IX10PlatformValidationListener's interface methods implementation
  
  public void platformCommunicationInterfaceValidated() {
	
  }

	private void addHeaderErrorMessage(final String msg) {
		getSite().getShell().getDisplay().syncExec(new Runnable() {
			public void run() {
				getHeaderForm().getMessageManager().addMessage(
						X10PlatformConfFormEditor.this.fConnKey, msg, null,
						IMessageProvider.ERROR);
			}

		});
	}
	
	private void removeHeaderMessage(final Object key) {
		getSite().getShell().getDisplay().syncExec(new Runnable() {
			public void run() {
				getHeaderForm().getMessageManager().removeMessage(key);
			}
		});
	}
  
	public void platformCommunicationInterfaceValidationFailure(final String message) {
		addHeaderErrorMessage(MessageFormat.format(LaunchMessages.XPCFE_DiscoveryCmdFailedMarkerMsg, message));
	}
  
  public void platformCppCompilationValidated() {
    // Nothing to do. We still have one step to go.
  }
  
  public void platformCppCompilationValidationFailure(final String message) {
    addHeaderErrorMessage(getCurrentPlatformConf().getCppCompilationConf().getValidationErrorMessage());
  }
  
  public void platformCppCompilationValidationError(final Exception exception) {
    addHeaderErrorMessage(MessageFormat.format(LaunchMessages.XPCFE_ValidationErrorDlgMsg, exception.getMessage()));
  }
  
  public void remoteConnectionFailure(final Exception exception) {
    addHeaderErrorMessage(LaunchMessages.XPCFE_RemoteConnValidationError);
  }
  
  public void remoteConnectionUnknownStatus() {
	  removeHeaderMessage(X10PlatformConfFormEditor.this.fConnKey);
  }
  
  public void remoteConnectionValidated(final ITargetElement targetElement) {
    
  }
  
  public void serviceProviderFailure(final CoreException exception) {
  	// Should never occur.
  	CppLaunchCore.log(exception.getStatus());
  }
  
  // --- IConnectionTypeListener's interface methods implementation
  
  public void connectionChanged(final boolean isLocal, final String remoteConnectionName,
                                final EValidationStatus validationStatus, final boolean shouldDeriveInfo) {
	  removeHeaderMessage(X10PlatformConfFormEditor.this.fConnKey);
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

  @SuppressWarnings({"unchecked"})
  public void doSave(final IProgressMonitor monitor) {
    final IFile file = getFile();
    synchronized (file) {
      CoreResourceUtils.deletePlatformConfMarkers(file);
      
      try {
        getCurrentPlatformConf().applyChanges();
        
        Field f = MessageManager.class.getDeclaredField("messages");
        f.setAccessible(true);
        final List<IMessage> msgs = (List<IMessage>)f.get((MessageManager)getHeaderForm().getMessageManager());
        f.setAccessible(false);
        
        for(IMessage msg : msgs) {
        	CoreResourceUtils.addPlatformConfMarker(file, msg.getMessage(), (msg.getMessageType() == IMessage.ERROR) ? IMarker.SEVERITY_ERROR : IMarker.SEVERITY_WARNING, 
                    IMarker.PRIORITY_HIGH);
        }
        
        X10PlatformConfFactory.save(file, getCurrentPlatformConf());
        monitor.worked(1);
        commitPages(true);
        
        Display.getDefault().syncExec(new Runnable() {
			public void run() {
				setTitleImage(getCUResourceImageDescriptor(msgs)
						.createImage());
			}
		});
        monitor.worked(1);
      } catch(Exception e) {
    	  CppLaunchCore.log(e);
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
    
    this.fRefreshAction = new RefreshAction();
    this.fValidateAction = new ValidateAction();
    form.getToolBarManager().add(this.fRefreshAction);
    form.getToolBarManager().add(this.fValidateAction);
    form.getToolBarManager().update(true);
    form.addMessageHyperlinkListener(new HyperlinkAdapter());
    this.fRefreshAction.setEnabled(true);
  }
  
  protected void createPages() {
    addPages();
    
    addPropertyListener(this);
    
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
        ((IFormPage) page).getManagedForm().setInput(getCurrentPlatformConf());
        ((IFormPage) page).getManagedForm().getMessageManager().setDecorationPosition(SWT.LEFT);
      }
    }
    ((IFormPage) super.pages.get(0)).getManagedForm().reflow(true);
    
    if (getActivePage() == -1) {
      super.setActivePage(0);
    }
    this.fValidateAction.setImageDescriptor(this.fUncheckedPlatformImg);
    this.fValidateAction.setEnabled(getCurrentPlatformConf().isComplete(false));
    this.fRefreshAction.setImageDescriptor(CppLaunchImages.createUnmanaged(CppLaunchImages.REFRESH_PLATFORM_CONF));
    
//    updateHeaderFromMarkers();
    Display.getDefault().syncExec(new Runnable() {
		public void run() {
			setTitleImage(getCUResourceImageDescriptor()
					.createImage());
		}
	});
  }
  
	public void updateHeaderFromMarkers() {
		getSite().getShell().getDisplay().syncExec(new Runnable() {
			public void run() {
				try {
					IMarker[] markers = ((IFileEditorInput) getEditorInput())
							.getFile().findMarkers(IMarker.PROBLEM, true,
									IResource.DEPTH_ZERO);
					for (IMarker marker : markers) {
						getHeaderForm().getMessageManager().addMessage(
								X10PlatformConfFormEditor.this.fConnKey,
								marker.getAttribute(IMarker.MESSAGE,
										"Missing message"), null,
								IMessageProvider.ERROR);
					}
				} catch (CoreException e) {
					CppLaunchCore.log(e);
				}
			}
		});
	}
  
  public void dispose() {
    removePropertyListener(this);
    super.dispose();
  }
  
  public void init(final IEditorSite site, final IEditorInput input) throws PartInitException {
    super.init(site, input);
    
    final IProject project = getProject();
    final IX10PlatformConf platformConf = CppLaunchCore.getInstance().getPlatformConfiguration(project);
    final IX10PlatformConfWorkCopy workCopy = platformConf.createWorkingCopy();
    //workCopy.initializeToDefaultValues(project);
    
    this.fX10PlatformConfs.put(workCopy.getName(), workCopy);
    this.fCurConfiguration = workCopy;
  }
  
  // --- Internal services
  
  IX10PlatformConfWorkCopy getCurrentPlatformConf() {
    return this.fCurConfiguration;
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
  
  private synchronized void validate() {
    final IX10PlatformChecker checker = PlatformCheckerFactory.create();
//  	CoreResourceUtils.deletePlatformConfMarkers(((IFileEditorInput) getEditorInput()).getFile());
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
  
	private final class RefreshAction extends Action {
		RefreshAction() {
			super(LaunchMessages.XPCFE_ResetPlatformActionMsg);
		}

		// --- Overridden methods
		public void run() {
			if(MessageDialog.openQuestion(null, 
					LaunchMessages.XPCFE_ResetPlatformActionMsg, 
					LaunchMessages.XPCFE_ResetPlatformActionDialogMsg))
			{
				final IX10PlatformConf platformConf = X10PlatformConfFactory.load(getFile(), false);
			    final IX10PlatformConfWorkCopy platformConfWorkCopy = platformConf.createWorkingCopy();
			    platformConfWorkCopy.initializeToDefaultValues(getProject());
			    
			    fX10PlatformConfs.put(platformConfWorkCopy.getName(), platformConfWorkCopy);
			    fCurConfiguration = platformConfWorkCopy;
			    
				
				for (final Object page : X10PlatformConfFormEditor.this.pages) {
					if (page != null) {
						IManagedForm form = ((IFormPage) page).getManagedForm();
						
						for (IFormPart part : form.getParts()) {
							((AbstractCommonSectionFormPart)part).initializeControls();
						}	
					}
				}
			}
		}
	}
	
	private IFile getFile()
	{
		return ((IFileEditorInput) getEditorInput()).getFile();
	}
	
	private IProject getProject()
	{
		return getFile().getProject();
	}
  
  // --- Fields
  
  private final Map<String, IX10PlatformConfWorkCopy> fX10PlatformConfs = new HashMap<String, IX10PlatformConfWorkCopy>();
  
  private IX10PlatformConfWorkCopy fCurConfiguration; 
  
  private IAction fRefreshAction;
  
  private IAction fValidateAction;
  
  private final Object fConnKey = new Object();
  
  private final ImageDescriptor fUncheckedPlatformImg = CppLaunchImages.createUnmanaged(CppLaunchImages.UNCHEKED_PLATFORM_CONF);
  
  private final ImageDescriptor fFileImg= CppLaunchImages.createUnmanaged(CppLaunchImages.FILE);
}
