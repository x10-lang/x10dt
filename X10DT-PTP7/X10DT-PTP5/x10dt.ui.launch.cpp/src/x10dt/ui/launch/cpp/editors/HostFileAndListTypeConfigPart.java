/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.platform_conf.EValidationStatus;
import x10dt.ui.launch.core.utils.PTPConstants;
import x10dt.ui.launch.cpp.LaunchMessages;
import x10dt.ui.launch.cpp.editors.form_validation.AbstractFormControlChecker;
import x10dt.ui.launch.cpp.editors.form_validation.IFormControlChecker;
import x10dt.ui.launch.cpp.platform_conf.IHostsBasedConf;
import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConfWorkCopy;
import x10dt.ui.launch.rms.core.Messages;

class HostFileAndListTypeConfigPart extends AbstractCITypeConfigurationPart  implements ICITypeConfigurationPart {

  // --- Interface methods implementation
  
  public void connectionChanged(final boolean isLocal, final String remoteConnectionName, 
                                      final EValidationStatus validationStatus) {
    // Nothing to do.
  }

  public void create(final IManagedForm managedForm, final FormToolkit toolkit, final Composite parent, 
                           final AbstractCommonSectionFormPart formPart) {
    final Composite composite = toolkit.createComposite(parent);
    composite.setFont(parent.getFont());
    composite.setLayout(new TableWrapLayout());
    composite.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB));
    setMainComposite(composite);
    
    final Composite placesCompo = toolkit.createComposite(composite);
    placesCompo.setFont(parent.getFont());
    final TableWrapLayout placesLayout = new TableWrapLayout();
    placesLayout.numColumns = 2;
    placesCompo.setLayout(placesLayout);
    placesCompo.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    
    final Label placesLabel = toolkit.createLabel(placesCompo, Messages.SRMLCDT_PlacesNumber);
    placesLabel.setLayoutData(new TableWrapData(TableWrapData.LEFT, TableWrapData.MIDDLE));
    this.fNumPlacesSpinner = new Spinner(placesCompo, SWT.BORDER);
    this.fNumPlacesSpinner.setMinimum(1);
    addControl(this.fNumPlacesSpinner);
    
    final Group hostsGroup = new Group(composite, SWT.NONE);
    hostsGroup.setFont(composite.getFont());
    hostsGroup.setLayout(new TableWrapLayout());
    hostsGroup.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB));
    hostsGroup.setText(Messages.SRMLCDT_HostsGroupName);
    
    this.fHostFileBt = toolkit.createButton(hostsGroup, Messages.SRMLCDT_HostFileBt, SWT.RADIO);
    this.fHostFileBt.setLayoutData(new TableWrapData(TableWrapData.LEFT, TableWrapData.MIDDLE));
    
    final Composite hostFileCompo = toolkit.createComposite(hostsGroup);
    hostFileCompo.setFont(hostsGroup.getFont());
    final TableWrapLayout hostFileLayout = new TableWrapLayout();
    hostFileLayout.numColumns = 2;
    hostFileLayout.leftMargin = 30;
    hostFileCompo.setLayout(hostFileLayout);
    hostFileCompo.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    
    this.fHostFileText = toolkit.createText(hostFileCompo, Constants.EMPTY_STR, SWT.BORDER);
    this.fHostFileText.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.MIDDLE));
    addControl(this.fHostFileText);
    
    this.fHostFileBrowseBt = toolkit.createButton(hostFileCompo, Messages.SRMLCDT_BrowseBt, SWT.PUSH);
    this.fHostFileBrowseBt.setLayoutData(new TableWrapData(TableWrapData.RIGHT, TableWrapData.MIDDLE));

    this.fHostListBt = toolkit.createButton(hostsGroup, Messages.SRMLCDT_HostListBt, SWT.RADIO);
    this.fHostListBt.setLayoutData(new TableWrapData(TableWrapData.LEFT, TableWrapData.MIDDLE));
    
    final Composite mainCompo = toolkit.createComposite(hostsGroup);
    mainCompo.setFont(hostsGroup.getFont());
    final TableWrapLayout mainCompoLayout = new TableWrapLayout();
    mainCompoLayout.leftMargin = 30;
    mainCompoLayout.numColumns = 2;
    mainCompo.setLayout(mainCompoLayout);
    mainCompo.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB));
    
    this.fHostListViewer = new TableViewer(mainCompo, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL |
                                           SWT.FULL_SELECTION | SWT.HIDE_SELECTION | SWT.BORDER);
    this.fHostListViewer.getTable().setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB));
    this.fHostListViewer.getTable().setLinesVisible(true);
    addControl(this.fHostListViewer.getTable());
    
    final TableLayout tableLayout = new TableLayout();
    tableLayout.addColumnData(new ColumnWeightData(100, 30));
    this.fHostListViewer.getTable().setLayout(tableLayout);
    final TableWrapData twData = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB);
    twData.rowspan = 2;
    this.fHostListViewer.getTable().setLayoutData(twData);
    toolkit.adapt(this.fHostListViewer.getTable(), false, false);
    
    final TableViewerColumn column = new TableViewerColumn(this.fHostListViewer, SWT.NONE);
    
    final TextCellEditor editor = new TextCellEditor(this.fHostListViewer.getTable());
    
    final Button addButton = toolkit.createButton(mainCompo, Messages.SRMLCDT_AddBt, SWT.PUSH);
    addButton.setLayoutData(new TableWrapData(TableWrapData.FILL, TableWrapData.TOP));
    
    final Button removeButton = toolkit.createButton(mainCompo, Messages.SRMLCDT_RemoveBt, SWT.PUSH);
    removeButton.setLayoutData(new TableWrapData(TableWrapData.FILL, TableWrapData.TOP));
    
    addViewerProviders(formPart, managedForm, this.fHostListViewer, column, editor, this.fHosts, formPart.getPlatformConf());
    
    initializeControls(formPart, (IHostsBasedConf) formPart.getPlatformConf().getCommunicationInterfaceConf(), addButton,
                       removeButton);
    
    addListeners(formPart, managedForm, formPart.getPlatformConf(), this.fNumPlacesSpinner, this.fHostFileBt, 
                 this.fHostFileText, this.fHostFileBrowseBt, this.fHostListBt, this.fHostListViewer, this.fHosts, addButton, 
                 removeButton);
    
    this.fHostListViewer.setInput(this.fHosts);
  }
  
  public String getServiceProviderId() {
    return PTPConstants.SOCKETS_SERVICE_PROVIDER_ID;
  }

  public boolean hasCompleteInfo() {
    if (this.fHostFileBt.getSelection()) {
      return this.fHostFileText.getText().length() > 0;
    } else {
      return this.fHosts.size() > 0;
    }
  }
  
  public boolean setFormInput(final Object input) {
    return (input == this.fHostFileText) || ((this.fHostListViewer != null) && (input == this.fHostListViewer.getTable())) || 
           (input == this.fNumPlacesSpinner);
  }
  
  // --- Overridden methods
  
  public void dispose(final IMessageManager ... messageManagers) {
    super.dispose(messageManagers);
    this.fHosts.clear();
  }
  
  // --- Private code
  
  private void addListeners(final AbstractCommonSectionFormPart formPart, final IManagedForm managedForm,
                            final IX10PlatformConfWorkCopy x10PlatformConf, final Spinner numPlacesSpinner, 
                            final Button hostFileBt, final Text hostFileText, final Button hostFileBrowseBt,
                            final Button hostListBt, final TableViewer hostListViewer, final List<String> hosts, 
                            final Button addButton, final Button removeButton) {
    numPlacesSpinner.addSelectionListener(new SelectionListener() {
      
      public void widgetSelected(final SelectionEvent event) {
        x10PlatformConf.setNumOfPlaces(numPlacesSpinner.getSelection());
        formPart.updateDirtyState(managedForm);
        formPart.setPartCompleteFlag(hasCompleteInfo());
      }
      
      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }
      
    });
    
    hostFileText.addModifyListener(new ModifyListener() {
      
      public void modifyText(final ModifyEvent event) {
        x10PlatformConf.setHostFile(hostFileText.getText().trim());
        formPart.handleEmptyTextValidation(hostFileText, LaunchMessages.STCP_HostFileText);
        
        formPart.updateDirtyState(managedForm);
        formPart.setPartCompleteFlag(hasCompleteInfo());
      }
      
    });
    
    hostFileBt.addSelectionListener(new SelectionListener() {
      
      public void widgetSelected(final SelectionEvent event) {
        if (hostFileBt.getSelection()) {
          x10PlatformConf.setShouldUseHostFile(true);

          hostFileText.setEnabled(true);
          hostFileBrowseBt.setEnabled(true);
          hostListViewer.getTable().setEnabled(false);
          addButton.setEnabled(false);
          removeButton.setEnabled(false);

          formPart.handleEmptyTextValidation(hostFileText, LaunchMessages.STCP_HostFileText);
          new HostsControlChecker(formPart.getFormPage(), hostListViewer.getTable(), 
                                  HostFileAndListTypeConfigPart.this.fHosts).validate(null);

          formPart.updateDirtyState(managedForm);
          formPart.setPartCompleteFlag(hasCompleteInfo());
        }
      }
      
      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }
      
    });
    hostFileBrowseBt.addSelectionListener(formPart.new FileDialogSelectionListener(hostFileText));
    
    hostListBt.addSelectionListener(new SelectionListener() {
      
      public void widgetSelected(final SelectionEvent event) {
        if (hostListBt.getSelection()) {
          x10PlatformConf.setShouldUseHostFile(false);

          hostFileText.setEnabled(false);
          hostFileBrowseBt.setEnabled(false);
          hostListViewer.getTable().setEnabled(true);
          addButton.setEnabled(true);
          removeButton.setEnabled(true);

          formPart.handleEmptyTextValidation(hostFileText, LaunchMessages.STCP_HostFileText);
          new HostsControlChecker(formPart.getFormPage(), hostListViewer.getTable(), 
                                  HostFileAndListTypeConfigPart.this.fHosts).validate(null);

          formPart.updateDirtyState(managedForm);
          formPart.setPartCompleteFlag(hasCompleteInfo());
        }
      }
      
      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }
      
    });
    
    addButton.addSelectionListener(new SelectionListener() {
      
      public void widgetSelected(final SelectionEvent event) {
        hosts.add(Constants.EMPTY_STR);
        hostListViewer.getTable().select(hostListViewer.getTable().getItemCount() - 1);
        hostListViewer.add(Constants.EMPTY_STR);
        hostListViewer.editElement(Constants.EMPTY_STR, 0);
        
        x10PlatformConf.setHostList(hosts);
        
        new HostsControlChecker(formPart.getFormPage(), hostListViewer.getTable(), 
                                HostFileAndListTypeConfigPart.this.fHosts).validate(null);
        
        formPart.updateDirtyState(managedForm);
        formPart.setPartCompleteFlag(hasCompleteInfo());
      }
      
      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }
      
    });
    
    removeButton.addSelectionListener(new SelectionListener() {
      
      public void widgetSelected(final SelectionEvent event) {
        final Object hostName = hostListViewer.getElementAt(hostListViewer.getTable().getSelectionIndex());
        if (hostName != null) {
          hosts.remove(hostName);
          hostListViewer.remove(hostName);
          
          x10PlatformConf.setHostList(hosts);
          
          new HostsControlChecker(formPart.getFormPage(), hostListViewer.getTable(), 
                                  HostFileAndListTypeConfigPart.this.fHosts).validate(null);
          
          formPart.updateDirtyState(managedForm);
          formPart.setPartCompleteFlag(hasCompleteInfo());
        }
      }
      
      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }
      
    });
  }
  
  private void addViewerProviders(final AbstractCommonSectionFormPart formPart, final IManagedForm managedForm, 
                                  final TableViewer hostListViewer, final TableViewerColumn column,
                                  final TextCellEditor editor, final List<String> hosts,
                                  final IX10PlatformConfWorkCopy platformConf) {
    hostListViewer.setContentProvider(new IStructuredContentProvider() {
      
      public void inputChanged(final Viewer curViewer, final Object oldInput, final Object newInput) {
      }
      
      public void dispose() {
      }
      
      public Object[] getElements(final Object inputElement) {
        return hosts.toArray(new String[hosts.size()]);
      }
      
    });
    
    column.setLabelProvider(new ColumnLabelProvider() {

      public String getText(final Object element) {
        return (String) element;
      }
      
    });
    column.setEditingSupport(new EditingSupport(this.fHostListViewer) {

      protected CellEditor getCellEditor(final Object element) {
        return editor;
      }

      protected boolean canEdit(final Object element) {
        return hostListViewer.getTable().getSelectionIndex() < hosts.size();
      }

      protected Object getValue(final Object element) {
        return element;
      }

      protected void setValue(final Object element, final Object value) {
        final int index = hosts.indexOf(element);
        hosts.remove(element);
        hosts.add(index, (String) value);
        hostListViewer.refresh();
        
        new HostsControlChecker(formPart.getFormPage(), hostListViewer.getTable(), 
                                HostFileAndListTypeConfigPart.this.fHosts).validate((String) value);
        
        platformConf.setHostList(hosts);
        
        formPart.updateDirtyState(managedForm);
        formPart.setPartCompleteFlag(hasCompleteInfo());
      }

    });
  }
  
  protected void initializeControls(final AbstractCommonSectionFormPart formPart, final IHostsBasedConf socketsConf,
                                  final Button addButton, final Button removeButton) {
    final boolean shouldUseHostFile = socketsConf.shouldUseHostFile();
    final boolean shouldUseHostSection = socketsConf.shouldUseHostSection();
    doInitializeControls(formPart, socketsConf, shouldUseHostSection, shouldUseHostFile, addButton, removeButton);
  }

  protected void doInitializeControls(final AbstractCommonSectionFormPart formPart, final IHostsBasedConf socketsConf, final boolean shouldUseHostSection,
      final boolean shouldUseHostFile, final Button addButton, final Button removeButton) {
    this.fNumPlacesSpinner.setSelection(socketsConf.getNumberOfPlaces());
    
    if (shouldUseHostSection){
      if (shouldUseHostFile) {
        this.fHostFileText.setText(socketsConf.getHostFile());

        formPart.handleEmptyTextValidation(this.fHostFileText, LaunchMessages.STCP_HostFileText);
      } else {
        this.fHosts.addAll(socketsConf.getHostsAsList());
        this.fHostListViewer.setInput(this.fHosts);

        new HostsControlChecker(formPart.getFormPage(), this.fHostListViewer.getTable(), this.fHosts).validate(null);
      }
      this.fHostFileBt.setSelection(shouldUseHostFile);
      this.fHostListBt.setSelection(! shouldUseHostFile);

      this.fHostFileText.setEnabled(shouldUseHostFile);
      this.fHostFileBrowseBt.setEnabled(shouldUseHostFile);
      this.fHostListViewer.getTable().setEnabled(! shouldUseHostFile);
      addButton.setEnabled(! shouldUseHostFile);
      removeButton.setEnabled(! shouldUseHostFile);
    
    } else {
      this.fHostFileBt.setEnabled(false);
      this.fHostListBt.setEnabled(false);

      this.fHostFileText.setEnabled(false);
      this.fHostFileBrowseBt.setEnabled(false);
      this.fHostListViewer.getTable().setEnabled(false);
      addButton.setEnabled(false);
      removeButton.setEnabled(false);
    }
  }
  
  // --- Private classes
  
  protected static class HostsControlChecker extends AbstractFormControlChecker implements IFormControlChecker {

    HostsControlChecker(final IFormPage formPage, final Control control, final List<String> hosts) {
      super(formPage, control);
      this.fHosts = hosts;
    }
    
    // --- Interface methods implementation
    
    public boolean validate(final String text) {
      removeMessages();
      if (getControl().isEnabled()) {
        if (this.fHosts.isEmpty()) {
          addMessages(NLS.bind(LaunchMessages.ETIC_NoEmptyContent, LaunchMessages.STCP_HostList), IMessageProvider.ERROR);
          return false;
        } else if ((text != null) && (text.trim().length() == 0)) {
          addMessages(LaunchMessages.STCP_NoHostNameError, IMessageProvider.ERROR);
          return false;
        }
      }
      return true;
    }
    
    // --- Fields
    
    private final List<String> fHosts;
    
  }
  
  // --- Fields
  
  protected Spinner fNumPlacesSpinner;
  
  protected Button fHostFileBt;
  
  protected Text fHostFileText;
  
  protected Button fHostFileBrowseBt;
  
  protected Button fHostListBt;
  
  protected TableViewer fHostListViewer;
  
  protected List<String> fHosts = new ArrayList<String>();

}
