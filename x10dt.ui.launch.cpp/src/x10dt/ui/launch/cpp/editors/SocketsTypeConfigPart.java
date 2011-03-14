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

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.platform_conf.EValidationStatus;
import x10dt.ui.launch.core.utils.PTPConstants;
import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConfWorkCopy;
import x10dt.ui.launch.rms.core.Messages;



final class SocketsTypeConfigPart extends AbstractCITypeConfigurationPart  implements ICITypeConfigurationPart {

  // --- Interface methods implementation
  
  public final void connectionChanged(final boolean isLocal, final String remoteConnectionName, 
                                      final EValidationStatus validationStatus) {
    // Nothing to do.
  }

  public final void create(final IManagedForm managedForm, final FormToolkit toolkit, final Composite parent, 
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
    addControl(placesLabel);
    placesLabel.setLayoutData(new TableWrapData(TableWrapData.LEFT, TableWrapData.MIDDLE));
    this.fNumPlacesSpinner = new Spinner(placesCompo, SWT.BORDER);
    addControl(this.fNumPlacesSpinner);
    
    final Group hostsGroup = new Group(composite, SWT.NONE);
    hostsGroup.setFont(composite.getFont());
    final TableWrapLayout hostsLayout = new TableWrapLayout();
    hostsLayout.numColumns = 2;
    hostsGroup.setLayout(hostsLayout);
    hostsGroup.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB));
    hostsGroup.setText(Messages.SRMLCDT_HostsGroupName);
    
    this.fHostFileBt = toolkit.createButton(hostsGroup, Messages.SRMLCDT_HostFileBt, SWT.RADIO);
    this.fHostFileBt.setLayoutData(new TableWrapData(TableWrapData.LEFT, TableWrapData.MIDDLE, 1, 2));
    
    this.fHostFileText = toolkit.createText(hostsGroup, Constants.EMPTY_STR, SWT.BORDER);
    final TableWrapData hostFileTwData = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.MIDDLE);
    hostFileTwData.indent = 30;
    this.fHostFileText.setLayoutData(hostFileTwData);
    addControl(this.fHostFileText);
    
    this.fHostFileBrowseBt = toolkit.createButton(hostsGroup, Messages.SRMLCDT_BrowseBt, SWT.PUSH);

    this.fHostListBt = toolkit.createButton(hostsGroup, Messages.SRMLCDT_HostListBt, SWT.RADIO);
    this.fHostListBt.setLayoutData(new TableWrapData(TableWrapData.LEFT, TableWrapData.MIDDLE, 1, 2));
    
    final Composite tableComposite = toolkit.createComposite(hostsGroup, SWT.BORDER);
    tableComposite.setFont(hostsGroup.getFont());
    final TableWrapData tableTwData = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB);
    tableTwData.rowspan = 2;
    tableTwData.indent = 30;
    tableComposite.setLayoutData(tableTwData);
    
    final TableColumnLayout tableLayout = new TableColumnLayout();
    tableComposite.setLayout(tableLayout);
    
    this.fHostListViewer = new TableViewer(tableComposite, SWT.BORDER | SWT.FULL_SELECTION);
    this.fHostListViewer.getTable().setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB));
    this.fHostListViewer.getTable().setLinesVisible(true);
    
    final TableViewerColumn column = new TableViewerColumn(this.fHostListViewer, SWT.NONE);
    tableLayout.setColumnData(column.getColumn(), new ColumnWeightData(100));
    
    final TextCellEditor editor = new TextCellEditor(this.fHostListViewer.getTable());
    
    final Button addButton = toolkit.createButton(hostsGroup, Messages.SRMLCDT_AddBt, SWT.PUSH);
    addButton.setLayoutData(new TableWrapData(TableWrapData.FILL, TableWrapData.TOP));
    
    final Button removeButton = toolkit.createButton(hostsGroup, Messages.SRMLCDT_RemoveBt, SWT.PUSH);
    removeButton.setLayoutData(new TableWrapData(TableWrapData.FILL, TableWrapData.TOP));
    
    addListeners(formPart, managedForm, formPart.getPlatformConf(), this.fNumPlacesSpinner, this.fHostFileBt, 
                 this.fHostFileText, this.fHostFileBrowseBt, this.fHostFileBt, this.fHostListViewer, column, editor,
                 this.fHosts, addButton, removeButton);
    
    this.fHostListViewer.setInput(this.fHosts);
  }
  
  public String getServiceProviderId() {
    return PTPConstants.SOCKETS_SERVICE_PROVIDER_ID;
  }

  public final boolean hasCompleteInfo() {
    if (this.fHostFileBt.getSelection()) {
      return this.fHostFileText.getText().length() > 0;
    } else {
      return this.fHosts.size() > 0;
    }
  }
  
  public boolean setFormInput(final Object input) {
    return false;
  }
  
  // --- Private code
  
  private void addListeners(final AbstractCommonSectionFormPart formPart, final IManagedForm managedForm,
                            final IX10PlatformConfWorkCopy x10PlatformConf, final Spinner numPlacesSpinner, 
                            final Button hostFileBt, final Text hostFileText, final Button hostFileBrowseBt,
                            final Button hostListBt, final TableViewer hostListViewer, final TableViewerColumn column,
                            final TextCellEditor editor, final List<String> hosts, final Button addButton,
                            final Button removeButton) {
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
    
    hostFileBt.addSelectionListener(new SelectionListener() {
      
      public void widgetSelected(final SelectionEvent event) {
        x10PlatformConf.setShouldUseHostFile(true);
        
        formPart.updateDirtyState(managedForm);
        formPart.setPartCompleteFlag(hasCompleteInfo());
      }
      
      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }
      
    });
    hostFileBrowseBt.addSelectionListener(formPart.new FileDialogSelectionListener(hostFileText));
    
    hostListBt.addSelectionListener(new SelectionListener() {
      
      public void widgetSelected(final SelectionEvent event) {
        x10PlatformConf.setShouldUseHostFile(false);
        
        formPart.updateDirtyState(managedForm);
        formPart.setPartCompleteFlag(hasCompleteInfo());
      }
      
      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }
      
    });
    
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
        
//        fireContentChange();
      }

    });
    
    addButton.addSelectionListener(new SelectionListener() {
      
      public void widgetSelected(final SelectionEvent event) {
        hosts.add(Constants.EMPTY_STR);
        hostListViewer.getTable().select(hostListViewer.getTable().getItemCount() - 1);
        hostListViewer.add(Constants.EMPTY_STR);
        hostListViewer.editElement(Constants.EMPTY_STR, 0);
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
          
//          fireContentChange();
        }
      }
      
      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }
      
    });
  }
  
  private void initializeControls(final IX10PlatformConfWorkCopy x10PlatformConf) {
  }
  
  // --- Fields
  
  private Spinner fNumPlacesSpinner;
  
  private Button fHostFileBt;
  
  private Text fHostFileText;
  
  private Button fHostFileBrowseBt;
  
  private Button fHostListBt;
  
  private TableViewer fHostListViewer;
  
  private List<String> fHosts = new ArrayList<String>();

}
