/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.editors;

import static x10dt.ui.launch.core.utils.PTPConstants.LOAD_LEVELER_SERVICE_PROVIDER_ID;
import static x10dt.ui.launch.core.utils.PTPConstants.MPICH2_SERVICE_PROVIDER_ID;
import static x10dt.ui.launch.core.utils.PTPConstants.OPEN_MPI_SERVICE_PROVIDER_ID;
import static x10dt.ui.launch.core.utils.PTPConstants.PAMI_SERVICE_PROVIDER_ID;
import static x10dt.ui.launch.core.utils.PTPConstants.PARALLEL_ENVIRONMENT_SERVICE_PROVIDER_ID;
import static x10dt.ui.launch.core.utils.PTPConstants.SOCKETS_SERVICE_PROVIDER_ID;
import static x10dt.ui.launch.core.utils.PTPConstants.STANDALONE_SERVICE_PROVIDER_ID;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.ptp.core.ModelManager;
import org.eclipse.ptp.core.PTPCorePlugin;
import org.eclipse.ptp.rm.core.rmsystem.IRemoteResourceManagerConfiguration;
import org.eclipse.ptp.rm.core.rmsystem.IToolRMConfiguration;
import org.eclipse.ptp.rm.ibm.ll.core.rmsystem.IIBMLLResourceManagerConfiguration;
import org.eclipse.ptp.rm.ibm.pe.core.rmsystem.PEResourceManagerConfiguration;
import org.eclipse.ptp.rm.mpi.openmpi.core.rmsystem.IOpenMPIResourceManagerConfiguration;
import org.eclipse.ptp.rmsystem.IResourceManager;
import org.eclipse.ptp.rmsystem.IResourceManagerConfiguration;
import org.eclipse.ptp.rmsystem.ResourceManagerServiceProvider;
import org.eclipse.ptp.services.core.IService;
import org.eclipse.ptp.services.core.IServiceProvider;
import org.eclipse.ptp.services.core.IServiceProviderDescriptor;
import org.eclipse.ptp.services.core.ServiceModelManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.forms.editor.SharedHeaderFormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import x10dt.ui.launch.core.platform_conf.EValidationStatus;
import x10dt.ui.launch.core.utils.PTPConstants;
import x10dt.ui.launch.core.utils.SWTFormUtils;
import x10dt.ui.launch.cpp.LaunchMessages;
import x10dt.ui.launch.cpp.platform_conf.ICommunicationInterfaceConf;


final class CommunicationInterfaceSectionPart extends AbstractCommonSectionFormPart 
                                              implements IServiceConfigurationListener, IConnectionTypeListener, IFormPart,
                                                         IConnectionSwitchListener {

  CommunicationInterfaceSectionPart(final Composite parent, final ConnectionAndCommunicationConfPage formPage) {
    super(parent, formPage);

    this.fProviderListeners = new ArrayList<IServiceProviderChangeListener>();

    getSection().setFont(parent.getFont());
    getSection().setText(LaunchMessages.RMCP_CommInterfaceSectionTitle);
    getSection().setDescription(LaunchMessages.RMCP_CommInterfaceSectionDescr);
    getSection().setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB));

    createClient(formPage.getManagedForm(), formPage.getManagedForm().getToolkit());
    addCompletePartListener(formPage);
  }

  // --- IServiceConfigurationListener's interface methods implementation

  public void serviceConfigurationModified(final String textContent) {
  }

  public void serviceConfigurationSelected(final IServiceProvider serviceProvider) {
    int index = -1;
    final ICommunicationInterfaceConf ciConf = getPlatformConf().getCommunicationInterfaceConf();
    for (final String name : this.fCITypeCombo.getItems()) {
      ++index;
      final ICITypeConfigurationPart comboConfPart = (ICITypeConfigurationPart) this.fCITypeCombo.getData(name);
      if (ciConf.getServiceTypeId().equals(comboConfPart.getServiceProviderId())) {
        this.fCITypeCombo.select(index);
        this.fCITypeCombo.setData(name, createCITypeConfigurationPart(serviceProvider));

        int modeIndex = -1;
        for (final String modeName : this.fCIModeCombo.getItems()) {
          ++modeIndex;
          final String modeId = (String) this.fCIModeCombo.getData(modeName);
          if (modeId.equals(ciConf.getServiceModeId())) {
            this.fCIModeCombo.select(modeIndex);
          }
        }
        
        updateCommunicationTypeInfo(this.fCITypeCombo, getFormPage().getManagedForm(), 
                                    getFormPage().getManagedForm().getToolkit(),
                                    (Composite) getSection().getClient());
        break;
      }
    }
  }

  // --- IConnectionTypeListener's interface methods implementation

  public void connectionChanged(final boolean isLocal, final String remoteConnectionName,
                                final EValidationStatus validationStatus, final boolean shouldDeriveInfo) {
    for (final String itemName : this.fCITypeCombo.getItems()) {
      final ICITypeConfigurationPart typeConfPart = (ICITypeConfigurationPart) this.fCITypeCombo.getData(itemName);
      typeConfPart.connectionChanged(isLocal, remoteConnectionName, validationStatus);
    }
  }

  // --- IConnectionSwitchListener's interface methods implementation

  public void connectionSwitched(final boolean isLocal) {
    final String currentCITypeName = this.fCITypeCombo.getItem(this.fCITypeCombo.getSelectionIndex());
    
    this.fCITypeCombo.removeAll();
    final Set<IServiceProviderDescriptor> serviceProviders = new HashSet<IServiceProviderDescriptor>();

    final ServiceModelManager serviceModelManager = ServiceModelManager.getInstance();
    for (final IService service : serviceModelManager.getServices()) {
      if (service.getCategory() != null && PTPConstants.RUNTIME_SERVICE_CATEGORY_ID.equals(service.getCategory().getId())) {
        serviceProviders.addAll(service.getProviders());
      }
    }
    initTypeCombo(serviceProviders, serviceModelManager, isLocal);
    
    // Tries to select the same type than before.
    boolean found = false;
    int index = -1;
    for (final String itemName : this.fCITypeCombo.getItems()) {
      ++index;
      if (itemName.equals(currentCITypeName)) {
        this.fCITypeCombo.select(index);
        found = true;
        break;
      }
    }
    if (! found) {
      // If it is no longer there, selects Sockets by default.
      index = -1;
      for (final String itemName : this.fCITypeCombo.getItems()) {
        ++index;
        final ICITypeConfigurationPart typeConfPart = (ICITypeConfigurationPart) this.fCITypeCombo.getData(itemName);
        if (SOCKETS_SERVICE_PROVIDER_ID.equals(typeConfPart)) {
          this.fCITypeCombo.select(index);
          break;
        }
      }
      this.fCITypeCombo.notifyListeners(SWT.Selection, new Event());
    }
  }

  // --- IFormPart's methods implementation

  public void dispose() {
    removeCompletePartListener(getFormPage());
    this.fProviderListeners.clear();
  }
  
  // --- Abstract methods implementation
  
  protected void initializeControls() {
    final ICommunicationInterfaceConf ciConf = getPlatformConf().getCommunicationInterfaceConf();

    if (ciConf != null) {
      int index = -1;
      for (final String name : this.fCITypeCombo.getItems()) {
        ++index;
        final ICITypeConfigurationPart typeConfPart = (ICITypeConfigurationPart) this.fCITypeCombo.getData(name);
        if (typeConfPart.getServiceProviderId().equals(ciConf.getServiceTypeId())) {
          this.fCITypeCombo.select(index);
          break;
        }
      }
      index = -1;
      for (final String name : this.fCIModeCombo.getItems()) {
        ++index;
        final String id = (String) this.fCIModeCombo.getData(name);
        if (id.equals(ciConf.getServiceModeId())) {
          this.fCIModeCombo.select(index);
          break;
        }
      }
    }
    checkComboValues();

    this.fCITypeCombo.notifyListeners(SWT.Selection, new Event());
  }
  
  protected void postPagesCreation() {
    final IFormPage page = getFormPage().getEditor().findPage(X10CompilationConfigurationPage.X10_COMPILATION_CONF_PAGE_ID);
    if ((page != null) && (page.getManagedForm() != null)) {
      for (final IFormPart formPart : page.getManagedForm().getParts()) {
        if (formPart instanceof IServiceProviderChangeListener) {
          this.fProviderListeners.add((IServiceProviderChangeListener) formPart);
        }
      }
    }
    setPartCompleteFlag(hasCompleteInfo());
  }

  // --- Overridden methods

  public boolean setFormInput(final Object input) {
    if ((input == this.fCITypeCombo) || (input == this.fCIModeCombo)) {
      return true;
    } else {
      final String itemName = this.fCITypeCombo.getItem(this.fCITypeCombo.getSelectionIndex());
      final ICITypeConfigurationPart typeConfPart = (ICITypeConfigurationPart) this.fCITypeCombo.getData(itemName);
      return typeConfPart.setFormInput(input);
    }
  }

  // --- Internal services

  Combo getCommunicationModeCombo() {
    return this.fCIModeCombo;
  }

  Combo getCommunicationTypeCombo() {
    return this.fCITypeCombo;
  }

  // --- Private code

  private void addListeners(final IManagedForm managedForm, final FormToolkit toolkit, final Composite parent,
                            final Combo ciTypeCombo, final Combo ciModeCombo) {
    ciTypeCombo.addSelectionListener(new SelectionListener() {

      public void widgetSelected(final SelectionEvent event) {
        updateCommunicationTypeInfo(ciTypeCombo, managedForm, toolkit, parent);

        final String serviceName = ciModeCombo.getItem(ciModeCombo.getSelectionIndex());
        final String serviceModeId = (String) ciModeCombo.getData(serviceName);
        getPlatformConf().setServiceModeId(getPlatformConf().getCommunicationInterfaceConf().getServiceTypeId(), 
                                           serviceModeId);
        
        final String itemName = ciTypeCombo.getItem(ciTypeCombo.getSelectionIndex());
        final ICITypeConfigurationPart typeConfPart = (ICITypeConfigurationPart) ciTypeCombo.getData(itemName);

        for (final IServiceProviderChangeListener listener : CommunicationInterfaceSectionPart.this.fProviderListeners) {
          listener.serviceTypeChange(typeConfPart.getServiceProviderId());
        }
        
        setPartCompleteFlag(hasCompleteInfo());
        updateDirtyState(managedForm);
      }

      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }

    });
    ciModeCombo.addSelectionListener(new SelectionListener() {

      public void widgetSelected(final SelectionEvent event) {
        final String serviceName = ciModeCombo.getItem(ciModeCombo.getSelectionIndex());
        final String serviceModeId = (String) ciModeCombo.getData(serviceName);
        
        if (ciTypeCombo.getSelectionIndex() != -1) {
          final String serviceTypeName = ciTypeCombo.getItem(ciTypeCombo.getSelectionIndex());
          final ICITypeConfigurationPart typeConfPart = (ICITypeConfigurationPart) ciTypeCombo.getData(serviceTypeName);
          getPlatformConf().setServiceTypeId(typeConfPart.getServiceProviderId());
          getPlatformConf().setServiceModeId(typeConfPart.getServiceProviderId(), serviceModeId);
        }

        for (final IServiceProviderChangeListener listener : CommunicationInterfaceSectionPart.this.fProviderListeners) {
          listener.serviceModeChange(serviceModeId);
        }

        setPartCompleteFlag(hasCompleteInfo());
        updateDirtyState(managedForm);
      }

      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }

    });
  }
  
  private void checkComboValues() {
    boolean updatedType = false;
    boolean updatedMode = false;
    
    int index = -1;
    if (this.fCITypeCombo.getSelectionIndex() == -1) {
      for (final String name : this.fCITypeCombo.getItems()) {
        ++index;
        final ICITypeConfigurationPart typeConfPart = (ICITypeConfigurationPart) this.fCITypeCombo.getData(name);
        if (PTPConstants.SOCKETS_SERVICE_PROVIDER_ID.equals(typeConfPart.getServiceProviderId())) {
          this.fCITypeCombo.select(index);
          getPlatformConf().setServiceTypeId(PTPConstants.SOCKETS_SERVICE_PROVIDER_ID);
          updatedType = true;
          break;
        }
      }
    }
    if (this.fCIModeCombo.getSelectionIndex() == -1) {
      index = -1;
      for (final String name : this.fCIModeCombo.getItems()) {
        ++index;
        final String id = (String) this.fCIModeCombo.getData(name);
        if (PTPConstants.LAUNCH_SERVICE_ID.equals(id)) {
          this.fCIModeCombo.select(index);
          getPlatformConf().setServiceModeId(PTPConstants.SOCKETS_SERVICE_PROVIDER_ID, PTPConstants.LAUNCH_SERVICE_ID);
          updatedMode = true;
          break;
        }
      }
    }
    
    if (updatedType || updatedMode) {
      final SharedHeaderFormEditor formEditor = (SharedHeaderFormEditor) getFormPage().getEditor();
      if (updatedType && updatedMode) {
        formEditor.getHeaderForm().getMessageManager().addMessage(formEditor, LaunchMessages.CISP_TypeAndModeUpdatedWarning, 
                                                                  null, IMessageProvider.WARNING);
      } else if (updatedType) {
        formEditor.getHeaderForm().getMessageManager().addMessage(formEditor, LaunchMessages.CISP_TypeUpdatedWarning, 
                                                                  null, IMessageProvider.WARNING);
      } else {
        formEditor.getHeaderForm().getMessageManager().addMessage(formEditor, LaunchMessages.CISP_ModeUpdatedWarning, 
                                                                  null, IMessageProvider.WARNING);
      }
    
      updateDirtyState(getFormPage().getManagedForm());
    }
  }

  private void createClient(final IManagedForm managedForm, final FormToolkit toolkit) {
    final Composite sectionClient = toolkit.createComposite(getSection());
    sectionClient.setLayout(new TableWrapLayout());
    sectionClient.setFont(getSection().getFont());
    sectionClient.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB));

    final Composite comboComposite = toolkit.createComposite(sectionClient);
    comboComposite.setFont(getSection().getFont());
    final TableWrapLayout comboLayout = new TableWrapLayout();
    comboLayout.numColumns = 2;
    comboComposite.setLayout(comboLayout);
    comboComposite.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));

    this.fCITypeCombo = SWTFormUtils.createLabelAndCombo(comboComposite, LaunchMessages.RMCP_CITypeLabel, toolkit);

    final ServiceModelManager serviceModelManager = ServiceModelManager.getInstance();

    this.fCIModeCombo = SWTFormUtils.createLabelAndCombo(comboComposite, LaunchMessages.RMCP_CIModeLabel, toolkit);

    final Set<IServiceProviderDescriptor> serviceProviders = new HashSet<IServiceProviderDescriptor>();

    for (final IService service : serviceModelManager.getServices()) {
      if (service.getCategory() == null) continue;
      if (PTPConstants.RUNTIME_SERVICE_CATEGORY_ID.equals(service.getCategory().getId()) && service.getName().equals(LaunchMessages.CISP_Launch)) {
    
        this.fCIModeCombo.add(service.getName());
        this.fCIModeCombo.setData(service.getName(), service.getId());

        serviceProviders.addAll(service.getProviders());
      }
    }
    initTypeCombo(serviceProviders, serviceModelManager, getPlatformConf().getConnectionConf().isLocal());

    new Label(sectionClient, SWT.SEPARATOR | SWT.HORIZONTAL).setLayoutData(new TableWrapData(TableWrapData.FILL));

    initializeControls();
    
    updateCommunicationTypeInfo(this.fCITypeCombo, managedForm, toolkit, sectionClient);

    addListeners(managedForm, toolkit, sectionClient, this.fCITypeCombo, this.fCIModeCombo);

    getSection().setClient(sectionClient);
    
    managedForm.reflow(true);
  }

  private ICITypeConfigurationPart createCITypeConfigurationPart(final IServiceProvider serviceProvider) {
    final String remoteServiceId = serviceProvider.getId();
//    IResourceManager rm = ModelManager.getInstance().getResourceManagerFromUniqueName(((ResourceManagerServiceProvider)serviceProvider).getUniqueName());
//    if (OPEN_MPI_SERVICE_PROVIDER_ID.equals(remoteServiceId)) {
//      return new OpenMPITypeConfigPart((IOpenMPIResourceManagerConfiguration) rm.getControlConfiguration());
//    } else if (MPICH2_SERVICE_PROVIDER_ID.equals(remoteServiceId)) {
//      return new MPICH2TypeConfigPart((IToolRMConfiguration) rm.getControlConfiguration());
//    } else if (PARALLEL_ENVIRONMENT_SERVICE_PROVIDER_ID.equals(remoteServiceId)) {
//      return new ParallelEnvironmentTypeConfigPart((PEResourceManagerConfiguration) rm.getControlConfiguration());
//    } else if (LOAD_LEVELER_SERVICE_PROVIDER_ID.equals(remoteServiceId)) {
//      //return new LoadLevelerTypeConfigPart((IIBMLLResourceManagerConfiguration) rm.getControlConfiguration());
//      return new LoadLevelerTypeConfigPart();
//    } else 
      
    if (SOCKETS_SERVICE_PROVIDER_ID.equals(remoteServiceId)) {
      return new HostFileAndListTypeConfigPart();
    } else if (PAMI_SERVICE_PROVIDER_ID.equals(remoteServiceId)) {
      return new PAMITypeConfigPart();
    } else if (STANDALONE_SERVICE_PROVIDER_ID.equals(remoteServiceId)) {
      return new StandaloneTypeConfigPart();
    }
    return null;
  }
  
  private boolean hasCompleteInfo() {
    if ((this.fCITypeCombo.getSelectionIndex() == -1) || (this.fCIModeCombo.getSelectionIndex() == -1)) {
      return false;
    } else {
      final String itemName = this.fCITypeCombo.getItem(this.fCITypeCombo.getSelectionIndex());
      final ICITypeConfigurationPart typeConfPart = (ICITypeConfigurationPart) this.fCITypeCombo.getData(itemName);
      return typeConfPart.hasCompleteInfo();
    }
  }

  private void initTypeCombo(final Set<IServiceProviderDescriptor> serviceProviders,
                             final ServiceModelManager serviceModelManager, final boolean isLocal) {
    for (final IServiceProviderDescriptor providerDescriptor : serviceProviders) {
      final IServiceProvider serviceProvider = serviceModelManager.getServiceProvider(providerDescriptor);
      if (serviceProvider == null) continue;
      String rmId = serviceProvider.getId();
      if (SOCKETS_SERVICE_PROVIDER_ID.equals(rmId) || STANDALONE_SERVICE_PROVIDER_ID.equals(rmId) ||
            PAMI_SERVICE_PROVIDER_ID.equals(rmId) /*|| LOAD_LEVELER_SERVICE_PROVIDER_ID.equals(rmId) ||
            (! isLocal && (MPICH2_SERVICE_PROVIDER_ID.equals(rmId) || OPEN_MPI_SERVICE_PROVIDER_ID.equals(rmId)))*/) { // --- MV - check if we still want to support these
          // We skip intentionally LoadLeveler and ParallelEnvironment for now.
          final ICITypeConfigurationPart typeConfPart = createCITypeConfigurationPart(serviceProvider);
          if (typeConfPart != null) {
            this.fCITypeCombo.add(providerDescriptor.getName());
            this.fCITypeCombo.setData(providerDescriptor.getName(), typeConfPart);
          }
        }
      
    }
  }

  private void updateCommunicationTypeInfo(final Combo ciTypeCombo, final IManagedForm managedForm,
                                           final FormToolkit toolkit, final Composite parent) {
    if (ciTypeCombo.getSelectionIndex() != -1) {
      final String itemName = ciTypeCombo.getItem(ciTypeCombo.getSelectionIndex());
      final ICITypeConfigurationPart typeConfPart = (ICITypeConfigurationPart) ciTypeCombo.getData(itemName);

      getPlatformConf().setServiceTypeId(typeConfPart.getServiceProviderId());

      if (this.fPreviousTypeConfPart != null) {
        final IManagedForm headerForm = ((SharedHeaderFormEditor) getFormPage().getEditor()).getHeaderForm();
        this.fPreviousTypeConfPart.dispose(headerForm.getMessageManager(), managedForm.getMessageManager());
      }
      
      typeConfPart.create(managedForm, toolkit, parent, this);

      parent.layout(true, true);

      this.fPreviousTypeConfPart = typeConfPart;

      setPartCompleteFlag(hasCompleteInfo());
    }
  }
  
  // --- Fields

  private Combo fCITypeCombo;

  private Combo fCIModeCombo;

  private ICITypeConfigurationPart fPreviousTypeConfPart;

  private final Collection<IServiceProviderChangeListener> fProviderListeners;
  
}
