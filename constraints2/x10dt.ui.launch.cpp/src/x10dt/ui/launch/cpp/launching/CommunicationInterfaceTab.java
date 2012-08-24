/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching;

import static x10dt.ui.launch.cpp.launching.CppBackEndLaunchConfAttrs.ATTR_USE_PLATFORM_CONF_DATA;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_HOSTFILE;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_HOSTLIST;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_NUM_PLACES;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_USE_HOSTFILE;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ptp.core.IModelManager;
import org.eclipse.ptp.core.IPTPLaunchConfigurationConstants;
import org.eclipse.ptp.core.PTPCorePlugin;
import org.eclipse.ptp.core.elements.IPResourceManager;
import org.eclipse.ptp.core.elements.IPUniverse;
import org.eclipse.ptp.rmsystem.IResourceManager;
import org.eclipse.ptp.core.elements.attributes.ResourceManagerAttributes.State;
import org.eclipse.ptp.launch.PTPLaunchPlugin;
import org.eclipse.ptp.launch.ui.LaunchConfigurationTab;
import org.eclipse.ptp.launch.ui.LaunchImages;
import org.eclipse.ptp.launch.ui.extensions.AbstractRMLaunchConfigurationFactory;
import org.eclipse.ptp.launch.ui.extensions.IRMLaunchConfigurationContentsChangedListener;
import org.eclipse.ptp.launch.ui.extensions.IRMLaunchConfigurationDynamicTab;
import org.eclipse.ptp.launch.ui.extensions.RMLaunchValidation;
import org.eclipse.ptp.remote.core.exception.RemoteConnectionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.utils.PTPConstants;
import x10dt.ui.launch.cpp.CppLaunchCore;
import x10dt.ui.launch.cpp.LaunchMessages;
import x10dt.ui.launch.cpp.platform_conf.ICommunicationInterfaceConf;
import x10dt.ui.launch.cpp.platform_conf.IHostsBasedConf;
import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConf;
import x10dt.ui.launch.cpp.utils.PTPConfUtils;
import x10dt.ui.launch.rms.core.launch_configuration.X10PlacesAndHostsDynamicTab;


final class CommunicationInterfaceTab extends LaunchConfigurationTab 
                                      implements ILaunchConfigurationTab, ILaunchTabPlatformConfServices {
  
  CommunicationInterfaceTab() {
    this.fConfToDynamicTabs = new HashMap<String, IRMLaunchConfigurationDynamicTab>();
    this.fConfToMemento = new HashMap<String, CommunicationInterfaceTab.LaunchConfigMemento>();
  }
  
  // --- ILaunchConfigurationTab's interface methods implementation
  
  public void createControl(final Composite parent) {
    this.fParent = (ScrolledComposite) parent;
    
    final Composite mainComposite = new Composite(parent, SWT.NONE);
    mainComposite.setFont(parent.getFont());
    mainComposite.setLayout(new GridLayout(1, false));
    mainComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    
    final Button synchronizationBt = new Button(mainComposite, SWT.CHECK);
    this.fSynchronizationBt = synchronizationBt;
    synchronizationBt.setText(LaunchMessages.CIT_UseDataFromPlatformConf);
    synchronizationBt.setSelection(true);
    synchronizationBt.addSelectionListener(new SelectionListener() {
      
      public void widgetSelected(final SelectionEvent event) {
        final ILaunchConfigurationWorkingCopy configuration = (ILaunchConfigurationWorkingCopy) getLaunchConfiguration();
        
        final IResourceManager resourceManager = CommunicationInterfaceTab.this.fResourceManager;
        if (resourceManager != null) {
          final IRMLaunchConfigurationDynamicTab rmDynamicTab = getRMLaunchConfigurationDynamicTab(resourceManager);
          if (rmDynamicTab != null) {
            updateConfiguration(configuration, rmDynamicTab);
            rmDynamicTab.initializeFrom(CommunicationInterfaceTab.this.fComposite, resourceManager, null /* queue */, 
                                        getLaunchConfiguration());
          }
        }
        
        handleSynchronizationButtonSelection(synchronizationBt.getSelection());
        setDirty(true);
        updateLaunchConfigurationDialog();
      }
      
      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }
      
    });
    
    this.fComposite = new Composite(mainComposite, SWT.NONE);
    this.fComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    
    setControl(mainComposite);
  }
  
  public String getName() {
    return LaunchMessages.CIT_CommunicationInterface;
  }
  
  public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
    if (this.fResourceManager != null) {
      if (! this.fSynchronizationBt.getSelection()) {
        try {
          final LaunchConfigMemento memento = this.fConfToMemento.get(configuration.getName());
          if (memento == null) {
            this.fConfToMemento.put(configuration.getName(), new LaunchConfigMemento(configuration));
          } else {
            memento.updateConfiguration(configuration);
          }
        } catch (CoreException except) {
          CppLaunchCore.log(IStatus.WARNING, NLS.bind(LaunchMessages.CIT_PreviousStateStorageError, configuration.getName()),
                            except);
        }
      }
      
      configuration.setAttribute(IPTPLaunchConfigurationConstants.ATTR_RESOURCE_MANAGER_UNIQUENAME, 
                                 this.fResourceManager.getUniqueName());
      configuration.setAttribute(ATTR_USE_PLATFORM_CONF_DATA, this.fSynchronizationBt.getSelection());
      final IRMLaunchConfigurationDynamicTab rmDynamicTab = getRMLaunchConfigurationDynamicTab(this.fResourceManager);
      if (rmDynamicTab != null) {
        final RMLaunchValidation validation = rmDynamicTab.performApply(configuration, this.fResourceManager, null);
        if (! validation.isSuccess()) {
          setErrorMessage(validation.getMessage());
        }
      }
    }
  }
  
  public void setDefaults(final ILaunchConfigurationWorkingCopy configuration) {
    final IModelManager modelManager = PTPCorePlugin.getDefault().getModelManager();
    final IPUniverse universe = modelManager.getUniverse();
    IResourceManager resourceManager = null;
    if (universe != null) {
      IPResourceManager[] prms = universe.getResourceManagers();
      final IResourceManager[] rms = new IResourceManager[prms.length];
      for(int i = 0; i < prms.length; i++){
        rms[i] = (IResourceManager) prms[i].getAdapter(IResourceManager.class);
      }
     
      if (rms.length == 1) {
        resourceManager = rms[0];
      }
    }
    if (resourceManager == null) {
      setErrorMessage(LaunchMessages.CIT_NoResourceManager);
      return;
    }
    final String resourceManagerUniqName = resourceManager.getUniqueName();
    configuration.setAttribute(IPTPLaunchConfigurationConstants.ATTR_RESOURCE_MANAGER_UNIQUENAME, resourceManagerUniqName);

    final IRMLaunchConfigurationDynamicTab rmDynamicTab = getRMLaunchConfigurationDynamicTab(resourceManager);
    if (rmDynamicTab == null) {
      setErrorMessage(NLS.bind(LaunchMessages.CIT_NoLaunchConfigForRM, 
                               new Object[] { resourceManager.getName() }));
    } else {
      rmDynamicTab.setDefaults(configuration, resourceManager, null);
    }
  }
  
  // --- ILaunchTabPlatformConfServices's interface methods implementation
  
  public void platformConfSelected(final IX10PlatformConf platformConf) {
    this.fX10PlatformConf = platformConf;
    try {
			this.fResourceManager = PTPConfUtils.getResourceManager(platformConf);
		} catch (RemoteConnectionException except) {
			// Let's forget. Handled by next statement.
		} catch (CoreException except) {
			// Let's forget. Handled by next statement.
		}
    if (this.fResourceManager == null){
    	setErrorMessage(LaunchMessages.CIT_CouldNotFindResManager);
    	return;
    }
    if (this.fResourceManager.getState() == IResourceManager.ERROR_STATE) {
    	try {
				this.fResourceManager.stop();
			} catch (CoreException except) {
				setErrorMessage(LaunchMessages.CIT_CouldNotStopResMgr);
				this.fResourceManager = null;
				return;
			}
    } 
    if (this.fResourceManager.getState() != IResourceManager.STARTED_STATE) { 
      try {
        this.fResourceManager.start(new NullProgressMonitor());
      } catch (CoreException except) {
        setErrorMessage(LaunchMessages.CIT_CouldNotStartResManager);
        this.fResourceManager = null;
      }
    }
    if ((getLaunchConfiguration() != null) && (this.fResourceManager != null)) {
      initializeFrom(getLaunchConfiguration());
    }
  }
  
  // --- Overridden methods
  
  public Image getImage() {
    return LaunchImages.getImage(LaunchImages.IMG_PARALLEL_TAB);
  }
  
  public void initializeFrom(final ILaunchConfiguration configuration) {
    super.initializeFrom(configuration);
    
    final IRMLaunchConfigurationDynamicTab rmDynamicTab = getRMLaunchConfigurationDynamicTab(this.fResourceManager);
    if (rmDynamicTab != null) {
      try {
        final boolean btEnabled;
        final boolean selected;
        if (this.fX10PlatformConf == null) {
          btEnabled = false;
          selected = false;
        } else {
          final ICommunicationInterfaceConf commIntfConf = this.fX10PlatformConf.getCommunicationInterfaceConf();
          if (PTPConstants.SOCKETS_SERVICE_PROVIDER_ID.equals(commIntfConf.getServiceTypeId()) ||
              PTPConstants.PAMI_SERVICE_PROVIDER_ID.equals(commIntfConf.getServiceTypeId())) {
            btEnabled = true;
            selected = configuration.getAttribute(ATTR_USE_PLATFORM_CONF_DATA, true);
          } else {
            btEnabled = false;
            selected = false;
          }
        }
        this.fSynchronizationBt.setEnabled(btEnabled);
        this.fSynchronizationBt.setSelection(selected);
        
        initializeDynamicTab(configuration, rmDynamicTab);
        
        if (btEnabled) {
          handleSynchronizationButtonSelection(this.fSynchronizationBt.getSelection());
        }
      } catch (CoreException except) {
        setErrorMessage(except.getMessage());
      }
    }
  }
  
  public boolean isValid(final ILaunchConfiguration configuration) {
    setErrorMessage(null);
    setMessage(null);
    if (this.fResourceManager == null) {
      setErrorMessage(LaunchMessages.CIT_NoX10ProjectIdentified);
      return false;
    }
    final IRMLaunchConfigurationDynamicTab rmDynamicTab = getRMLaunchConfigurationDynamicTab(this.fResourceManager);
    if (rmDynamicTab == null) {
      setErrorMessage(LaunchMessages.CIT_NoLaunchConfigAvailable);
      return false;
    }
    if (this.fSynchronizationBt.getSelection()) {
      return true;
    }
    try {
      if (rmDynamicTab.getControl() == null) {
        initializeDynamicTab(configuration, rmDynamicTab);
      }
      final RMLaunchValidation validation = rmDynamicTab.isValid(configuration, this.fResourceManager, null);
      if (! validation.isSuccess()) {
        setErrorMessage(validation.getMessage());
        return false;
      }
    } catch (CoreException except) {
      CppLaunchCore.log(except.getStatus());
    }
    
    return true;
  }
  
  public void setLaunchConfigurationDialog(final ILaunchConfigurationDialog dialog) {
    super.setLaunchConfigurationDialog(dialog);
  }
  
  // --- Private code
  
  private IRMLaunchConfigurationDynamicTab getRMLaunchConfigurationDynamicTab(final IResourceManager resourceManager) {
    if (getLaunchConfiguration() == null) {
      return null;
    }
    if (! this.fConfToDynamicTabs.containsKey(getLaunchConfiguration().getName())) {
      try {
        final PTPLaunchPlugin launchPlugin = PTPLaunchPlugin.getDefault();
        final AbstractRMLaunchConfigurationFactory rmFactory = launchPlugin.getRMLaunchConfigurationFactory(resourceManager);
        if (rmFactory == null) {
          return null;
        }
        final IRMLaunchConfigurationDynamicTab rmDynamicTab = rmFactory.create(resourceManager, 
                                                                               getLaunchConfigurationDialog());
        if (rmDynamicTab == null) {
          return null;
        }
        rmDynamicTab.addContentsChangedListener(new IRMLaunchConfigurationContentsChangedListener() {

          public void handleContentsChanged(final IRMLaunchConfigurationDynamicTab factory) {
            updateLaunchConfigurationDialog();
          }
          
        });
        this.fConfToDynamicTabs.put(getLaunchConfiguration().getName(), rmDynamicTab);
        return rmDynamicTab;
      } catch (CoreException except) {
        setErrorMessage(except.getMessage());
        return null;
      }
    }
    return this.fConfToDynamicTabs.get(getLaunchConfiguration().getName());
  }
  
  private void handleSynchronizationButtonSelection(final boolean isButtonSelected) {
    final IRMLaunchConfigurationDynamicTab rmDynamicTab = getRMLaunchConfigurationDynamicTab(this.fResourceManager);
    if (rmDynamicTab != null) {
      handleSynchronizationButtonSelection(rmDynamicTab.getControl(), ! isButtonSelected);
    }
  }
  
  private void handleSynchronizationButtonSelection(final Control control, final boolean enable) {
    control.setEnabled(enable);
    if (control instanceof Composite) {
      for (final Control childControl : ((Composite) control).getChildren()) {
        handleSynchronizationButtonSelection(childControl, enable);
      }
    }
  }
  
  private void initializeDynamicTab(final ILaunchConfiguration configuration,
                                    final IRMLaunchConfigurationDynamicTab rmDynamicTab) throws CoreException {
    for (final Control child : this.fComposite.getChildren()) {
      if (! child.isDisposed()) {
        child.dispose();
      }
    }
    
    this.fComposite.setLayout(new GridLayout(1, false));
    rmDynamicTab.createControl(this.fComposite, this.fResourceManager, null /* queue */);
    rmDynamicTab.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    if (configuration instanceof ILaunchConfigurationWorkingCopy) {
      updateConfiguration((ILaunchConfigurationWorkingCopy) configuration, rmDynamicTab);
    }
    rmDynamicTab.initializeFrom(this.fComposite, this.fResourceManager, null /* queue */, 
                                getLaunchConfiguration());
    
    this.fParent.setMinSize(this.fComposite.computeSize(this.fSynchronizationBt.getBounds().width, SWT.DEFAULT));
  }
  
  private void updateConfiguration(final ILaunchConfigurationWorkingCopy configuration,
                                   final IRMLaunchConfigurationDynamicTab rmDynamicTab) {
    if (this.fSynchronizationBt.getSelection()) {
      // We need to update the launch config data the ones coming from the platform configuration.
      // No clean mechanism for now :-/ I'm kind of forced of writing the next ugly code.
      if (rmDynamicTab instanceof X10PlacesAndHostsDynamicTab) {
        final IHostsBasedConf hostBasedConf = (IHostsBasedConf) this.fX10PlatformConf.getCommunicationInterfaceConf();
        configuration.setAttribute(ATTR_HOSTFILE, hostBasedConf.getHostFile());
        configuration.setAttribute(ATTR_USE_HOSTFILE, hostBasedConf.shouldUseHostFile());
        configuration.setAttribute(ATTR_HOSTLIST, hostBasedConf.getHostsAsList());
        configuration.setAttribute(ATTR_NUM_PLACES, hostBasedConf.getNumberOfPlaces());
      }
    } else {
      final LaunchConfigMemento memento = this.fConfToMemento.get(configuration.getName());
      if (memento == null) {
        try {
          this.fConfToMemento.put(configuration.getName(), new LaunchConfigMemento(configuration));
        } catch (CoreException except) {
          CppLaunchCore.log(IStatus.WARNING, NLS.bind(LaunchMessages.CIT_PreviousStateStorageError, configuration.getName()),
                            except);
        }
      }
      if (memento != null) {
        configuration.setAttribute(ATTR_USE_HOSTFILE, memento.shouldUseHostFile());
        configuration.setAttribute(ATTR_HOSTFILE, memento.getHostFile());
        configuration.setAttribute(ATTR_HOSTLIST, memento.getHostList());
        configuration.setAttribute(ATTR_NUM_PLACES, memento.getNumberOfPlaces());
      }
    }
  }

  // --- Private classes
  
  private static final class LaunchConfigMemento {
    
    LaunchConfigMemento(final ILaunchConfiguration launchConfig) throws CoreException {
      updateConfiguration(launchConfig);
    }
    
    // --- Internal services
    
    String getHostFile() {
      return this.fHostFile;
    }
    
    List<String> getHostList() {
      return this.fHostList;
    }
    
    int getNumberOfPlaces() {
      return this.fNumPlaces;
    }
    
    boolean shouldUseHostFile() {
      return this.fUseHostFile;
    }
    
    @SuppressWarnings("unchecked")
    void updateConfiguration(final ILaunchConfiguration launchConfig) throws CoreException {
      this.fUseHostFile = launchConfig.getAttribute(ATTR_USE_HOSTFILE, false);
      this.fHostFile = launchConfig.getAttribute(ATTR_HOSTFILE, Constants.EMPTY_STR);
      this.fHostList = launchConfig.getAttribute(ATTR_HOSTLIST, Collections.<String>emptyList());
      this.fNumPlaces = launchConfig.getAttribute(ATTR_NUM_PLACES, 1);
    }
    
    // --- Fields
    
    private boolean fUseHostFile;
    
    private String fHostFile;
    
    private List<String> fHostList;
    
    private int fNumPlaces;
    
  }
  
  // --- Fields
  
  private ScrolledComposite fParent;
  
  private Composite fComposite;
  
  private Button fSynchronizationBt;
  
  private IX10PlatformConf fX10PlatformConf;
  
  private IResourceManager fResourceManager;
  
  private final Map<String, LaunchConfigMemento> fConfToMemento;
  
  private final Map<String, IRMLaunchConfigurationDynamicTab> fConfToDynamicTabs;

}
