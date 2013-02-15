/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.java.launching;

import static x10dt.ui.launch.core.utils.PTPConstants.REMOTE_CONN_SERVICE_ID;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_IS_LOCAL;
import static x10dt.ui.launch.cpp.launching.LaunchConfigConstants.ATTR_HOSTFILE;
import static x10dt.ui.launch.cpp.launching.LaunchConfigConstants.ATTR_HOSTLIST;
import static x10dt.ui.launch.cpp.launching.LaunchConfigConstants.ATTR_NUM_PLACES;
import static x10dt.ui.launch.cpp.launching.LaunchConfigConstants.ATTR_USE_HOSTFILE;
import static x10dt.ui.launch.cpp.launching.LaunchConfigConstants.DEFAULT_NUM_PLACES;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
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
import org.eclipse.ptp.launch.ui.LaunchConfigurationTab;
import org.eclipse.ptp.launch.ui.LaunchImages;
import org.eclipse.ptp.remote.core.IRemoteConnection;
import org.eclipse.ptp.remote.core.IRemoteServices;
import org.eclipse.ptp.remote.core.PTPRemoteCorePlugin;
import org.eclipse.ptp.remote.ui.IRemoteUIConstants;
import org.eclipse.ptp.remote.ui.IRemoteUIFileManager;
import org.eclipse.ptp.remote.ui.IRemoteUIServices;
import org.eclipse.ptp.remote.ui.PTPRemoteUIPlugin;
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
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.utils.PTPConstants;
import x10dt.ui.launch.cpp.CppLaunchCore;
import x10dt.ui.launch.cpp.LaunchMessages;
import x10dt.ui.launch.cpp.launching.ConfUtils;
import x10dt.ui.launch.rms.core.Messages;


final class CommunicationInterfaceTab extends LaunchConfigurationTab 
                                      implements ILaunchConfigurationTab {
  public static int HOST_FILE = 0;
  public static int HOST_LIST = 1;
  public static int LOAD_LEVELER = 2;
  
  public static String LOCALHOST = "localhost";
  
  CommunicationInterfaceTab() {
    this.fHosts = new ArrayList<String>();
    this.fHosts.add(LOCALHOST);
  }
  
  // --- ILaunchConfigurationTab's interface methods implementation
  
  public void createControl(final Composite parent) { 
    final Composite mainComposite = new Composite(parent, SWT.NONE);
    mainComposite.setFont(parent.getFont());
    mainComposite.setLayout(new GridLayout(1, false));
    mainComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    
    final Composite placesCompo = new Composite(mainComposite, SWT.NONE);
    placesCompo.setFont(mainComposite.getFont());
    placesCompo.setLayout(new GridLayout(2, false));
    placesCompo.setLayoutData(new GridData(SWT.LEFT, SWT.NONE, true, false));
    
    new Label(placesCompo, SWT.NONE).setText(Messages.SRMLCDT_PlacesNumber);
    this.fNumPlacesSpinner = new Spinner(placesCompo, SWT.BORDER);
    this.fNumPlacesSpinner.setSelection(DEFAULT_NUM_PLACES);
    this.fNumPlacesSpinner.addSelectionListener(new SelectionListener() {
      
      public void widgetSelected(final SelectionEvent event) {
        updateLaunchConfigurationDialog();
      }
      
      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }
      
    });
    
    final Group hostsGroup = new Group(mainComposite, SWT.NONE);
    hostsGroup.setFont(mainComposite.getFont());
    hostsGroup.setLayout(new GridLayout(2, false));
    hostsGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    hostsGroup.setText(Messages.SRMLCDT_HostsGroupName);
    
    this.fHostFileBt = new Button(hostsGroup, SWT.RADIO);
    this.fHostFileBt.setText(Messages.SRMLCDT_HostFileBt);
    this.fHostFileBt.setLayoutData(new GridData(SWT.LEFT, SWT.NONE, false, false, 2, 1));
    this.fHostFileBt.addSelectionListener(new SelectionListener() {
      
      public void widgetSelected(final SelectionEvent event) {
        updateLaunchConfigurationDialog();
      }
      
      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }
      
    });
    
    final Text hostFileText = new Text(hostsGroup, SWT.BORDER);
    this.fHostFileText = hostFileText;
    final GridData hostFileGData = new GridData(SWT.FILL, SWT.CENTER, true, false);
    hostFileGData.horizontalIndent = 30;
    this.fHostFileText.setLayoutData(hostFileGData);
    this.fHostFileText.addModifyListener(new ModifyListener() {
      
      public void modifyText(final ModifyEvent event) {
        updateLaunchConfigurationDialog();
      }
      
    });
    
    this.fHostFileBrowseBt = new Button(hostsGroup, SWT.PUSH);
    this.fHostFileBrowseBt.setText(Messages.SRMLCDT_BrowseBt);
    this.fHostFileBrowseBt.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
    this.fHostFileBrowseBt.addSelectionListener(new SelectionListener() {

			public void widgetSelected(final SelectionEvent event) {

				final String path;
				if (CommunicationInterfaceTab.this.fIsLocal) {
					final FileDialog dialog = new FileDialog(parent.getShell());
					dialog.setText(Messages.SRMLCDT_SelectHostFileDialogTitle);
					path = dialog.open();
				} else {
					final IRemoteServices remoteServices = PTPRemoteCorePlugin
							.getDefault().getRemoteServices(
									REMOTE_CONN_SERVICE_ID);
					final IRemoteConnection connection = remoteServices.getConnectionManager().getConnection(ConfUtils.getConnectionName(CommunicationInterfaceTab.this.fConfiguration));
					path = remoteBrowse(parent.getShell(), connection,
							Messages.SRMLCDT_SelectHostFileDialogTitle,
							Constants.EMPTY_STR);
				}
				if (path != null) {
					hostFileText.setText(path);
				}

			}

      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }

    });

    this.fHostListBt = new Button(hostsGroup, SWT.RADIO);
    this.fHostListBt.setText(Messages.SRMLCDT_HostListBt);
    this.fHostListBt.setLayoutData(new GridData(SWT.LEFT, SWT.NONE, false, false, 2, 1));
    this.fHostListBt.addSelectionListener(new SelectionListener() {
      
      public void widgetSelected(final SelectionEvent event) {
        updateLaunchConfigurationDialog();
      }
      
      public void widgetDefaultSelected(final SelectionEvent event) {
       widgetSelected(event);
      }
      
    });
    
    final Composite tableComposite = new Composite(hostsGroup, SWT.NONE);
    tableComposite.setFont(hostsGroup.getFont());
    final GridData tableGData = new GridData(SWT.FILL, SWT.FILL, true, true);
    tableGData.verticalSpan = 2;
    tableGData.horizontalIndent = 30;
    tableComposite.setLayoutData(tableGData);
    
    final TableViewer viewer = new TableViewer(tableComposite, SWT.BORDER | SWT.FULL_SELECTION);
    this.fHostListViewer = viewer;
    this.fHostListViewer.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
    final TableViewerColumn column = new TableViewerColumn(this.fHostListViewer, SWT.NONE);
    column.setLabelProvider(new ColumnLabelProvider() {

      public String getText(final Object element) {
        return (String) element;
      }
      
    });
    final TextCellEditor editor = new TextCellEditor(viewer.getTable());;
    column.setEditingSupport(new EditingSupport(this.fHostListViewer) {

      protected CellEditor getCellEditor(final Object element) {
        return editor;
      }

      @SuppressWarnings("unqualified-field-access")
      protected boolean canEdit(final Object element) {
        return viewer.getTable().getSelectionIndex() < fHosts.size();
      }

      protected Object getValue(final Object element) {
        return element;
      }

      @SuppressWarnings("unqualified-field-access")
      protected void setValue(final Object element, final Object value) {
        final int index = fHosts.indexOf(element);
        fHosts.remove(element);
        fHosts.add(index, (String) value);
        viewer.refresh();
        
        updateLaunchConfigurationDialog();
      }

    });
    
    final TableColumnLayout tableColumnLayout = new TableColumnLayout();
    tableColumnLayout.setColumnData(column.getColumn(), new ColumnWeightData(100));
    tableComposite.setLayout(tableColumnLayout);
    
    this.fHostListViewer.setContentProvider(new IStructuredContentProvider() {
      
      public void inputChanged(final Viewer curViewer, final Object oldInput, final Object newInput) {
      }
      
      public void dispose() {
      }
      
      @SuppressWarnings("unqualified-field-access")
      public Object[] getElements(final Object inputElement) {
        return fHosts.toArray(new String[fHosts.size()]);
      }
      
    });
    
    this.fHostListViewer.setInput(this.fHosts);
    this.fHostListViewer.getTable().setLinesVisible(true);
    
    final Button addButton = new Button(hostsGroup, SWT.PUSH);
    this.fAddButton = addButton;
    addButton.setText(Messages.SRMLCDT_AddBt);
    addButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
    addButton.addSelectionListener(new SelectionListener() {
      
      @SuppressWarnings("unqualified-field-access")
      public void widgetSelected(final SelectionEvent event) {
        fHosts.add(Constants.EMPTY_STR);
        viewer.getTable().select(viewer.getTable().getItemCount() - 1);
        viewer.add(Constants.EMPTY_STR);
        viewer.editElement(Constants.EMPTY_STR, 0);
      }
      
      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }
      
    });
    
    final Button removeButton = new Button(hostsGroup, SWT.PUSH);
    this.fRemoveButton = removeButton;
    removeButton.setText(Messages.SRMLCDT_RemoveBt);
    removeButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
    removeButton.addSelectionListener(new SelectionListener() {
      
      @SuppressWarnings("unqualified-field-access")
      public void widgetSelected(final SelectionEvent event) {
        final Object hostName = viewer.getElementAt(viewer.getTable().getSelectionIndex());
        if (hostName != null) {
          fHosts.remove(hostName);
          viewer.remove(hostName);
          
          updateLaunchConfigurationDialog();
        }
      }
      
      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }
      
    });    

    setControl(mainComposite);
    
    initState();
  }
  
  public String getName() {
    return LaunchMessages.CIT_CommunicationInterface;
  }
  
  private void initState() {
	  this.fHostFileBt.setEnabled(true);
      this.fHostListBt.setEnabled(true);  

      this.fHostFileBt.setSelection(false);
      this.fHostFileText.setEnabled(false);
      this.fHostFileBrowseBt.setEnabled(false);
      this.fHostListBt.setSelection(true);
      this.fHostListViewer.getTable().setEnabled(true);
      this.fAddButton.setEnabled(true);
      this.fRemoveButton.setEnabled(true);
      this.fNumPlacesSpinner.setEnabled(true);
  }
  
  
  public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
    if (this.fHostFileBt != null && this.fHostFileBt.getSelection())
      configuration.setAttribute(ATTR_USE_HOSTFILE, HOST_FILE);
    if (this.fHostListBt != null && this.fHostListBt.getSelection())
      configuration.setAttribute(ATTR_USE_HOSTFILE, HOST_LIST);
    if (this.fNumPlacesSpinner != null)
      configuration.setAttribute(ATTR_NUM_PLACES, this.fNumPlacesSpinner.getSelection());
    if (this.fHostFileText != null)
      configuration.setAttribute(ATTR_HOSTFILE, this.fHostFileText.getText().trim());
    configuration.setAttribute(ATTR_HOSTLIST, getHostList());
  }
  
  private String getHostList(){
    if (this.fHosts.isEmpty()){
      return Constants.EMPTY_STR;
    }
    String result = Constants.EMPTY_STR;
    boolean first = true;
    for(String s: this.fHosts){
      if (first){
        result += s;
        first = false;
      } else {
        result += "," + s;
      }
    }
    return result;
  }
  
  private List<String> parseHostList(String hosts){
    List<String> result = new ArrayList<String>();
    for(String s: hosts.split(",")){
      result.add(s);
    }
    return result;
  }
  
  public void setDefaults(final ILaunchConfigurationWorkingCopy configuration) {
    try {
	  this.fConfiguration = configuration.getOriginal();
	  this.fIsLocal = configuration.getAttribute(ATTR_IS_LOCAL, true);
    } catch (CoreException e){
    	CppLaunchCore.getInstance().getLog().log(e.getStatus());
    }
  }
 
  
  // --- Overridden methods
  
  public Image getImage() {
    return LaunchImages.getImage(LaunchImages.IMG_PARALLEL_TAB);
  }
  
  public void initializeFrom(final ILaunchConfiguration configuration) {
    try {
      this.fConfiguration = configuration;	
      this.fIsLocal = configuration.getAttribute(ATTR_IS_LOCAL, true);
      final String hosts= LOCALHOST; 
      this.fNumPlacesSpinner.setSelection(configuration.getAttribute(ATTR_NUM_PLACES, DEFAULT_NUM_PLACES));
      this.fHosts= parseHostList(configuration.getAttribute(ATTR_HOSTLIST, hosts));
      this.fHostListViewer.setInput(this.fHosts);
      this.fHostFileText.setText(configuration.getAttribute(ATTR_HOSTFILE, Constants.EMPTY_STR));
    } catch (CoreException e) {
      CppLaunchCore.getInstance().getLog().log(e.getStatus());
    }
  }
  
  public boolean isValid(final ILaunchConfiguration configuration) {
    setErrorMessage(null);
    if (this.fNumPlacesSpinner.getSelection() < 1) {
      setErrorMessage(Messages.SRMLCDT_AtLeastOnePlaceMsg);
      return false;
    }
    if (this.fHostFileBt.getSelection()) {
      if (this.fHostFileText.getText().trim().length() == 0) {
        setErrorMessage(Messages.SRMLCDT_HostFileRequiredMsg);
        return false;
      }
    } else {
      if (this.fHosts.size() == 0) {
        setErrorMessage(Messages.SRMLCDT_AtLeastOneHostNameMsg);
        return false;
      }
      for (final String host : this.fHosts) {
        if (host.trim().length() == 0) {
          setErrorMessage(Messages.SRMLCDT_NoEmptyHostNameMsg);
          return false;
        }
      }
    }
    return true;
  }
  
  public void setLaunchConfigurationDialog(final ILaunchConfigurationDialog dialog) {
    super.setLaunchConfigurationDialog(dialog);
  }
  
  // --- Private code
  
  




  private String remoteBrowse(final Shell shell, final IRemoteConnection rmConnection, final String dialogTitle, final String initialPath) {
    final IRemoteServices rmServices= PTPRemoteCorePlugin.getDefault().getRemoteServices(PTPConstants.REMOTE_CONN_SERVICE_ID);
    final IRemoteUIServices rmUIServices= PTPRemoteUIPlugin.getDefault().getRemoteUIServices(rmServices);

    final IRemoteUIFileManager fileManager= rmUIServices.getUIFileManager();
    if (fileManager != null) {
      fileManager.setConnection(rmConnection);
      fileManager.showConnections(false);
      final String path= fileManager.browseFile(shell, dialogTitle, initialPath, IRemoteUIConstants.NONE);
      if (path != null) {
        return path.replace('\\', '/');
      }
    }
    return null;
  }
  
  // --- Fields
  
  private ILaunchConfiguration fConfiguration;
  
  private boolean fIsLocal;
    
  private Spinner fNumPlacesSpinner;
  
  private Button fHostFileBt;
  
  private Text fHostFileText;
  
  private Button fHostFileBrowseBt;
  
  private Button fHostListBt;
  
  private TableViewer fHostListViewer;
  
  private Button fAddButton;
  
  private Button fRemoveButton;
  
  private List<String> fHosts;

}
