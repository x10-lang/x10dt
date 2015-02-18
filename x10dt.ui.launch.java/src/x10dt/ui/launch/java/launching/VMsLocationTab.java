/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.java.launching;

import static x10dt.ui.launch.core.utils.PTPConstants.REMOTE_CONN_SERVICE_ID;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_HOST;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_IS_LOCAL;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_IS_PASSWORD_BASED;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_LOCAL_ADDRESS;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_PASSPHRASE;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_PASSWORD;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_PORT;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_PRIVATE_KEY_FILE;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_REMOTE_OUTPUT_FOLDER;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_TIMEOUT;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_USERNAME;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_USE_PORT_FORWARDING;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_X10_DISTRIBUTION;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationListener;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.imp.utils.Pair;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.ptp.remote.core.IRemoteConnection;
import org.eclipse.ptp.remote.core.IRemoteConnectionManager;
import org.eclipse.ptp.remote.core.IRemoteServices;
import org.eclipse.ptp.remote.core.RemoteServices;
import org.eclipse.ptp.remote.ui.IRemoteUIConstants;
import org.eclipse.ptp.remote.ui.IRemoteUIFileManager;
import org.eclipse.ptp.remote.ui.IRemoteUIServices;
import org.eclipse.ptp.remote.ui.RemoteUIServices;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.utils.PTPConstants;
import x10dt.ui.launch.cpp.launching.ConfUtils;
import x10dt.ui.launch.java.Activator;
import x10dt.ui.launch.java.LaunchJavaImages;
import x10dt.ui.launch.java.Messages;


final class VMsLocationTab extends AbstractLaunchConfigurationTab 
                           implements ILaunchConfigurationTab, ILaunchConfigurationListener {
  
  VMsLocationTab() {
    LaunchJavaImages.createManaged(LaunchJavaImages.VMS_LOCATION);
    
    DebugPlugin.getDefault().getLaunchManager().addLaunchConfigurationListener(this);
  }
  
  // --- ILaunchConfigurationTab's interface methods implementation

  public void createControl(final Composite parent) {
    final ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.V_SCROLL);
    
    setControl(scrolledComposite);
    
    final Composite composite = new Composite(scrolledComposite, SWT.NONE);
    composite.setFont(parent.getFont());
    composite.setLayout(new GridLayout(1, false));
    composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    
    createConnectionGroup(composite);
    
    scrolledComposite.setContent(composite);
    scrolledComposite.setExpandVertical(true);
    scrolledComposite.setExpandHorizontal(true);
  }

  public void setDefaults(final ILaunchConfigurationWorkingCopy configuration) {
  }

  public void initializeFrom(final ILaunchConfiguration configuration) {
    this.fLaunchConfigName = configuration.getName();
    try {
      this.fHostText.setText(configuration.getAttribute(ATTR_HOST, Constants.EMPTY_STR));
      this.fPortText.setSelection(configuration.getAttribute(ATTR_PORT, 22));
      this.fUserNameText.setText(configuration.getAttribute(ATTR_USERNAME, Constants.EMPTY_STR));
      this.fPasswordBasedAuthBt.setSelection(configuration.getAttribute(ATTR_IS_PASSWORD_BASED, true));
      this.fPrivateKeyFileAuthBt.setSelection(! this.fPasswordBasedAuthBt.getSelection());
      if (this.fPrivateKeyFileAuthBt.getSelection()) {
        this.fPrivateKeyFileText.setText(configuration.getAttribute(ATTR_PRIVATE_KEY_FILE, Constants.EMPTY_STR));
        this.fPassphraseText.setText(configuration.getAttribute(ATTR_PASSPHRASE, Constants.EMPTY_STR));
      } else {
        final String password = configuration.getAttribute(ATTR_PASSWORD, (String) null);
        if (password != null) {
          this.fPasswordText.setText(password);
        }
      }
      this.fUsePortForwardingBt.setSelection(configuration.getAttribute(ATTR_USE_PORT_FORWARDING, false));
      this.fConnectionTimeoutSpinner.setSelection(configuration.getAttribute(ATTR_TIMEOUT, 1000));
      this.fLocalAddressText.setText(configuration.getAttribute(ATTR_LOCAL_ADDRESS, Constants.EMPTY_STR));
      this.fRemoteOutputFolderText.setText(configuration.getAttribute(ATTR_REMOTE_OUTPUT_FOLDER, Constants.EMPTY_STR));
      this.fX10DistributionText.setText(configuration.getAttribute(ATTR_X10_DISTRIBUTION, Constants.EMPTY_STR));
      
      this.fLocalConnBt.setSelection(configuration.getAttribute(ATTR_IS_LOCAL, true));
      this.fLocalConnBt.notifyListeners(SWT.Selection, new Event());
      this.fRemoteConnBt.setSelection(! this.fLocalConnBt.getSelection());
      this.fRemoteConnBt.notifyListeners(SWT.Selection, new Event());
    } catch (CoreException except) {
      // Let's forget.
    }
    
    if (configuration instanceof ILaunchConfigurationWorkingCopy) {
      if (this.fRMUpdateRunning) {
        while (this.fRMUpdateRunning) {
          getShell().getDisplay().readAndDispatch();
        }
      }
      updateConnection();    
      while (this.fRMUpdateRunning) {
        getShell().getDisplay().readAndDispatch();
      }
    }
  }

  public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
    configuration.setAttribute(ATTR_IS_LOCAL, this.fLocalConnBt.getSelection());

    if (! this.fLocalConnBt.getSelection()) {
      configuration.setAttribute(ATTR_HOST, this.fHostText.getText());
      configuration.setAttribute(ATTR_PORT, this.fPortText.getSelection());
      configuration.setAttribute(ATTR_USERNAME, this.fUserNameText.getText());
      configuration.setAttribute(ATTR_IS_PASSWORD_BASED, ! this.fPrivateKeyFileAuthBt.getSelection());
      if (this.fPrivateKeyFileAuthBt.getSelection()) {
        configuration.setAttribute(ATTR_PRIVATE_KEY_FILE, this.fPrivateKeyFileText.getText());
        configuration.setAttribute(ATTR_PASSPHRASE, this.fPassphraseText.getText());
      } else {
        configuration.setAttribute(ATTR_PASSWORD, this.fPasswordText.getText());
      }
      configuration.setAttribute(ATTR_USE_PORT_FORWARDING, this.fUsePortForwardingBt.getSelection());
      configuration.setAttribute(ATTR_TIMEOUT, this.fConnectionTimeoutSpinner.getSelection());
      configuration.setAttribute(ATTR_LOCAL_ADDRESS, this.fLocalAddressText.getText());
      configuration.setAttribute(ATTR_REMOTE_OUTPUT_FOLDER, this.fRemoteOutputFolderText.getText());
      configuration.setAttribute(ATTR_X10_DISTRIBUTION, this.fX10DistributionText.getText());
    }
  }

  public String getName() {
    return Messages.VMLT_TabName;
  }
  
  // --- ILaunchConfigurationListener's interface methods implementation
  
  public void launchConfigurationAdded(final ILaunchConfiguration configuration) {
    try {
      final String projectName = configuration.getAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, 
                                                            Constants.EMPTY_STR);
      final ILaunchConfigurationWorkingCopy launchWC = this.fWCMap.get(configuration.getName());
      if ((projectName.length() > 0) && (launchWC != null)) {
        if (Activator.LAUNCH_CONF_TYPE.equals(configuration.getType().getIdentifier()) &&
            (! configuration.getName().equals(this.fLaunchConfigName))) {
          this.fLaunchConfigName = configuration.getName();
          updateConnection();
          while (this.fRMUpdateRunning) {
            getShell().getDisplay().readAndDispatch();
          }
        }
      }
    } catch (CoreException except) {
      Activator.getDefault().getLog().log(except.getStatus());
    }
  }

  public void launchConfigurationChanged(final ILaunchConfiguration configuration) {
    if (configuration instanceof ILaunchConfigurationWorkingCopy) {
      this.fWCMap.put(configuration.getName(), (ILaunchConfigurationWorkingCopy) configuration);
    }
  }

  public void launchConfigurationRemoved(final ILaunchConfiguration configuration) {
    this.fWCMap.remove(configuration.getName());
  }
  
  // --- Overridden methods
  
  public void dispose() {
    DebugPlugin.getDefault().getLaunchManager().removeLaunchConfigurationListener(this);
    LaunchJavaImages.removeImage(LaunchJavaImages.VMS_LOCATION);
    super.dispose();
  }
  
  public Image getImage() {
    return LaunchJavaImages.getImage(LaunchJavaImages.VMS_LOCATION);
  }
  
  private boolean errorMessageErasable(String message){
	    if (message == null) return true;
	    if (message.equals(Messages.VMLT_HostNameNotDefined) ||
	        message.equals(Messages.VMLT_UserNameNotDefined) ||
	        message.equals(Messages.VMLT_PrivateKeyFileNotDefined) ||
	        message.equals(Messages.VMLT_OutputFolderNotDefined) ||
	        message.equals(Messages.VMLT_X10DistNotDefined)) {
	      return true;
	    }
	    return false;
	  }
  public boolean isValid(final ILaunchConfiguration configuration) {
    setErrorMessage(null);
    if (this.fRemoteConnBt.getSelection()) {
      if (errorMessageErasable(getErrorMessage())){
            setErrorMessage(null);
      } else {
         return false;
      }	
      if (this.fHostText.getText().length() == 0) {
        setErrorMessage(Messages.VMLT_HostNameNotDefined);
        return false;
      }
      if (this.fUserNameText.getText().length() == 0) {
        setErrorMessage(Messages.VMLT_UserNameNotDefined);
        return false;
      }
      if (this.fPrivateKeyFileAuthBt.getSelection()) {
        if (this.fPrivateKeyFileText.getText().length() == 0) {
          setErrorMessage(Messages.VMLT_PrivateKeyFileNotDefined);
          return false;
        }
      }
      if (this.fRemoteOutputFolderText.getText().length() == 0) {
        setErrorMessage(Messages.VMLT_OutputFolderNotDefined);
        return false;
      }
      if (this.fX10DistributionText.getText().length() == 0) {
        setErrorMessage(Messages.VMLT_X10DistNotDefined);
        return false;
      }
    } 
    setErrorMessage(null);
    return true;
  }
  
  // --- Private code

  private void addConnectionWidgetsListeners(final Collection<Control> remoteControls, final Button browseFileButton,
                                             final Button outputFolderBrowseBt, final Button x10DistFolderBrowseBt,
                                             final Button checkButton) {
    this.fLocalConnBt.addSelectionListener(new SelectionListener() {
      
      public void widgetSelected(final SelectionEvent event) {
        if (VMsLocationTab.this.fLocalConnBt.getSelection()) {
          for (final Control control : remoteControls) {
            control.setEnabled(false);
          }
          browseFileButton.setEnabled(false);
          outputFolderBrowseBt.setEnabled(false);
          updateConnection();
          updateLaunchConfigurationDialog();
        }
      }
      
      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }
      
    });
    this.fRemoteConnBt.addSelectionListener(new SelectionListener() {
      
      public void widgetSelected(final SelectionEvent event) {
        if (VMsLocationTab.this.fRemoteConnBt.getSelection()) {
          for (final Control control : remoteControls) {
            control.setEnabled(true);
          }
          browseFileButton.setEnabled(true);
          outputFolderBrowseBt.setEnabled(false);
          updateConnection();
          updateLaunchConfigurationDialog();
        }
      }
      
      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }
      
    });
    checkButton.addSelectionListener(new SelectionListener() {
      
      public void widgetSelected(final SelectionEvent event) {
        updateConnection();
        updateLaunchConfigurationDialog();
      }
      
      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }
      
    });
    final ModifyListener modifyListener = new UpdateConfDialogModifyListener();
    this.fHostText.addModifyListener(modifyListener);
    this.fPortText.addModifyListener(modifyListener);
    this.fUserNameText.addModifyListener(modifyListener);
    this.fPasswordText.addModifyListener(modifyListener);
    this.fPrivateKeyFileText.addModifyListener(modifyListener);
    this.fPassphraseText.addModifyListener(modifyListener);
    this.fConnectionTimeoutSpinner.addModifyListener(modifyListener);
    this.fLocalAddressText.addModifyListener(modifyListener);
    this.fRemoteOutputFolderText.addModifyListener(modifyListener);
    this.fX10DistributionText.addModifyListener(modifyListener);
    
    browseFileButton.addSelectionListener(new SelectionListener() {
      
      public void widgetSelected(final SelectionEvent event) {
        final FileDialog dialog = new FileDialog(getShell());
        dialog.setText(Messages.VMLT_PrivateKeyFileDialogMsg);
        final String path = dialog.open();
        if (path != null) {
          VMsLocationTab.this.fPrivateKeyFileText.setText(path);
        }
      }
      
      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }
      
    });
    outputFolderBrowseBt.addSelectionListener(new SelectionListener() {
      
      public void widgetSelected(final SelectionEvent event) {
        final String path = getFolderFromDialog(Messages.VMLT_SelectRemoteOutputFolder);
        if (path != null) {
          VMsLocationTab.this.fRemoteOutputFolderText.setText(path);
        }
      }
      
      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }
      
    });
    x10DistFolderBrowseBt.addSelectionListener(new SelectionListener() {
      
      public void widgetSelected(final SelectionEvent event) {
        final String path = getFolderFromDialog(Messages.VMLT_SelectX10Dist);
        if (path != null) {
          VMsLocationTab.this.fX10DistributionText.setText(path);
        }
      }
      
      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }
      
    });
  }
  
  private void createConnectionGroup(final Composite parent) {
    this.fLocalConnBt = createRadioButton(parent, Messages.VMLT_LocalConnBt);
    
    this.fRemoteConnBt = createRadioButton(parent, Messages.VMLT_RemoteConnBt);
    
    final Composite marginCompo = new Composite(parent, SWT.NONE);
    marginCompo.setFont(parent.getFont());
    final GridLayout marginGLayout = new GridLayout(3, false);
    marginGLayout.marginLeft = 15;
    marginCompo.setLayout(marginGLayout);
    marginCompo.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
    
    final Collection<Control> remoteControls = new ArrayList<Control>();
    
    final Button checkButton = createPushButton(marginCompo, Messages.VMLT_CheckConnBt, null /* image */);
    remoteControls.add(checkButton);
    
    final Group remoteGroup = new Group(marginCompo, SWT.NONE);
    remoteGroup.setFont(parent.getFont());
    remoteGroup.setLayout(new GridLayout(4, false));
    remoteGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
    
    this.fHostText = createLabelAndText(remoteGroup, Messages.VMLT_HostLabel, new GridData(SWT.FILL, SWT.NONE, true, false),
                                        SWT.BORDER, remoteControls);
    
    final Label portLabel = new Label(remoteGroup, SWT.NONE);
    portLabel.setText(Messages.VMLT_PortLabel);
    portLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
    remoteControls.add(portLabel);
    this.fPortText = new Spinner(remoteGroup, SWT.SINGLE | SWT.BORDER);
    this.fPortText.setMinimum(0);
    this.fPortText.setSelection(22);
    this.fPortText.setTextLimit(10);
    this.fPortText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
    remoteControls.add(this.fPortText);
    
    this.fUserNameText = createLabelAndText(remoteGroup, Messages.VMLT_UserLabel, remoteControls);
    
    this.fPasswordBasedAuthBt = createRadioButton(remoteGroup, Messages.VMLT_PasswordAuthBt);
    this.fPasswordBasedAuthBt.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1));
    remoteControls.add(this.fPasswordBasedAuthBt);
    
    final Composite passwordCompo = new Composite(remoteGroup, SWT.NONE);
    passwordCompo.setFont(remoteGroup.getFont());
    final GridLayout passwordGLayout = new GridLayout(1, false);
    passwordGLayout.marginLeft = 15;
    passwordCompo.setLayout(passwordGLayout);
    passwordCompo.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1));
    
    this.fPasswordText = createLabelAndText(passwordCompo, Messages.VMLT_PasswordLabel,
                                            new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1), 
                                            SWT.BORDER | SWT.PASSWORD, remoteControls);

    this.fPrivateKeyFileAuthBt = createRadioButton(remoteGroup, Messages.VMLT_PublicKeyAutBt);
    this.fPrivateKeyFileAuthBt.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1));
    remoteControls.add(this.fPrivateKeyFileAuthBt);
    
    final Composite privateKeyCompo = new Composite(remoteGroup, SWT.NONE);
    privateKeyCompo.setFont(remoteGroup.getFont());
    final GridLayout privateKeyGLayout = new GridLayout(1, false);
    privateKeyGLayout.marginLeft = 15;
    privateKeyCompo.setLayout(privateKeyGLayout);
    privateKeyCompo.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1));
    final Pair<Text,Button> pair = createLabelTextAndPushButton(privateKeyCompo, Messages.VMLT_PrivateKeyFileLabel,
                                                                Messages.VMLT_BrowseBt, remoteControls);
    this.fPrivateKeyFileText = pair.first;
    
    this.fPassphraseText = createLabelAndText(privateKeyCompo, Messages.VMLT_PassphraseLabel,
                                              new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1), 
                                              SWT.BORDER | SWT.PASSWORD, remoteControls);
    
    final Label separator = new Label(remoteGroup, SWT.SEPARATOR | SWT.SHADOW_OUT | SWT.HORIZONTAL);
    separator.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1));
    
    this.fUsePortForwardingBt = createCheckButton(remoteGroup, Messages.VMLT_PortFrwdLabel);
    this.fUsePortForwardingBt.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));
    remoteControls.add(this.fUsePortForwardingBt);
    
    final Label timeoutLabel = new Label(remoteGroup, SWT.NONE);
    timeoutLabel.setText(Messages.VMLT_TimeoutLabel);
    
    this.fConnectionTimeoutSpinner = new Spinner(remoteGroup, SWT.SINGLE | SWT.BORDER);
    this.fConnectionTimeoutSpinner.setMinimum(0);
    this.fConnectionTimeoutSpinner.setTextLimit(5);
    this.fConnectionTimeoutSpinner.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false));
    remoteControls.add(this.fConnectionTimeoutSpinner);
    
    this.fLocalAddressText = createLabelAndText(remoteGroup, Messages.VMLT_LocalAddressLabel, null);
    remoteControls.add(this.fLocalAddressText);
    
    final Pair<Text,Button> pair2 = createLabelTextAndPushButton(marginCompo, Messages.VMLT_RemoteOutputFolder, 
                                                                 Messages.VMLT_BrowseBt,
                                                                 new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1),
                                                                 remoteControls);
    this.fRemoteOutputFolderText = pair2.first;
    this.fBrowseBts[0] = pair2.second;
    
    final Pair<Text,Button> pair3 = createLabelTextAndPushButton(marginCompo, Messages.VMLT_X10Dist, Messages.VMLT_BrowseBt,
                                                                 new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1),
                                                                 remoteControls);
    this.fX10DistributionText = pair3.first;
    this.fBrowseBts[1] = pair3.second;
    
    addConnectionWidgetsListeners(remoteControls, pair.second, pair2.second, pair3.second, checkButton);
    
    this.fLocalConnBt.setSelection(true);
    this.fLocalConnBt.notifyListeners(SWT.Selection, new Event());
    updateBrowseButtonsEnablement(false);
  }
  
  private Text createLabelAndText(final Composite parent, final String labelText, final Collection<Control> controlContainer) {
    return createLabelAndText(parent, labelText, new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1), SWT.BORDER,
                              controlContainer);
  }
  
  private Text createLabelAndText(final Composite parent, final String labelText, final GridData gridData,
                                  final int style, final Collection<Control> controlContainer) {
    final Composite composite = new Composite(parent, SWT.NONE);
    composite.setFont(parent.getFont());
    composite.setLayout(new GridLayout(2, false));
    composite.setLayoutData(gridData);
    if (controlContainer != null) {
      controlContainer.add(composite);
    }
    final Label label = new Label(composite, SWT.NONE);
    label.setText(labelText);
    label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
    if (controlContainer != null) {
      controlContainer.add(label);
    }
    final Text text = new Text(composite, style);
    text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
    if (controlContainer != null) {
      controlContainer.add(text);
    }
    return text;
  }
  
  private Pair<Text,Button> createLabelTextAndPushButton(final Composite parent, final String labelText, final String btText, 
                                                         final Collection<Control> controlContainer) {
    return createLabelTextAndPushButton(parent, labelText, btText, new GridData(SWT.FILL, SWT.NONE, true, false),
                                        controlContainer);
  }
  
  private Pair<Text,Button> createLabelTextAndPushButton(final Composite parent, final String labelText, final String btText, 
                                                         final GridData gridData, final Collection<Control> controlContainer) {
    final Composite composite = new Composite(parent, SWT.NONE);
    composite.setFont(parent.getFont());
    composite.setLayout(new GridLayout(3, false));
    composite.setLayoutData(gridData);
    if (controlContainer != null) {
      controlContainer.add(composite);
    }
    final Label label = new Label(composite, SWT.NONE);
    label.setText(labelText);
    label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
    if (controlContainer != null) {
      controlContainer.add(label);
    }
    final Text text = new Text(composite, SWT.BORDER);
    text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    if (controlContainer != null) {
      controlContainer.add(text);
    }
    final Button button = createPushButton(composite, btText, null /* image */);
    button.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
    if (controlContainer != null) {
      controlContainer.add(button);
    }
    return new Pair<Text, Button>(text, button);
  }
  
  private String getFolderFromDialog(final String dialogText) {
    if (this.fLocalConnBt.getSelection()) {
      final DirectoryDialog dialog = new DirectoryDialog(getShell());
      dialog.setText(dialogText);
      return dialog.open();
    } else {
      final IRemoteServices rmServices = RemoteServices.getRemoteServices(PTPConstants.REMOTE_CONN_SERVICE_ID);
      final IRemoteConnectionManager rmConnManager = rmServices.getConnectionManager();
      final IRemoteConnection rmConnection = rmConnManager.getConnection(this.fLaunchConfigName);
      final IRemoteUIServices rmUIServices = RemoteUIServices.getRemoteUIServices(rmServices);
      
      final IRemoteUIFileManager fileManager = rmUIServices.getUIFileManager();
      if ((fileManager != null) && (rmConnection != null)) {
        fileManager.setConnection(rmConnection);
        fileManager.showConnections(false);
        final String path = fileManager.browseDirectory(getShell(), dialogText, Constants.EMPTY_STR, IRemoteUIConstants.NONE); 
        if (path != null) {
          return path.replace('\\', '/');
        }
      }
      return null;
    }
  }
   
  private boolean hasAllConnectionInfo() {
    if (this.fLocalConnBt.getSelection()) {
      return true;
    } else {
      if ((this.fHostText.getText().length() > 0) && (this.fUserNameText.getText().length() > 0)) {
        if (this.fPrivateKeyFileAuthBt.getSelection()) {
          return (this.fPrivateKeyFileText.getText().length() > 0);
        } else {
          return true;
        }
      } else {
        return false;
      }
    }
  }
  
  private String processJSchMessage(final String message) {
    if ("Auth cancel".equals(message)) { //$NON-NLS-1$
      return Messages.VMLT_PasswordCheckCanceled;
    } else if ("Auth fail".equals(message)) { //$NON-NLS-1$
      return Messages.VMLT_PasswordCheckFailed;
    } else {
      return message;
    }
  }
  
  private void updateBrowseButtonsEnablement(final boolean enabled) {
    for (final Button button : this.fBrowseBts) {
      button.setEnabled(this.fLocalConnBt.getSelection() ? false : enabled);
    }
  }

  
	private void updateConnection() {

		if (!this.fRMUpdateRunning && (this.fLaunchConfigName != null)
				&& hasAllConnectionInfo()) {
			this.fRMUpdateRunning = true;
			try {
				if (VMsLocationTab.this.fRemoteConnBt.isDisposed()
						|| VMsLocationTab.this.fLocalAddressText.isDisposed()
						|| VMsLocationTab.this.fUsePortForwardingBt
								.isDisposed()) {
					return;
				}

				IRemoteConnectionManager rmConnManager = null;

				if (VMsLocationTab.this.fRemoteConnBt.getSelection()) {
					ConfUtils.createOrUpdateRemoteConnection(
							this.fLaunchConfigName, this.fHostText.getText(),
							this.fUserNameText.getText(),
							this.fPortText.getSelection(),
							!this.fPrivateKeyFileAuthBt.getSelection(),
							this.fPasswordText.getText(),
							this.fPrivateKeyFileText.getText(),
							this.fPassphraseText.getText(),
							this.fConnectionTimeoutSpinner.getSelection());
					rmConnManager = RemoteServices.getRemoteServices(
							REMOTE_CONN_SERVICE_ID).getConnectionManager();
					IRemoteConnection conn = rmConnManager
							.getConnection(VMsLocationTab.this.fLaunchConfigName);
					conn.open(new NullProgressMonitor());
					updateBrowseButtonsEnablement(true);
					setErrorMessage(null);
				}
			} catch (Exception e) {
				String error = processJSchMessage(e.getMessage());
				 if (error != null) {
				        setErrorMessage(error);
				 } else {
				        setErrorMessage(e.toString());
				 }
				updateBrowseButtonsEnablement(false);

			}

			VMsLocationTab.this.fRMUpdateRunning = false;

		}
	}
  
  // --- Private classes
  
  private final class UpdateConfDialogModifyListener implements ModifyListener {

    // --- Interface methods implementation
    
    public void modifyText(final ModifyEvent event) {
      updateLaunchConfigurationDialog();
    }
    
  }
  
  // --- Fields
  
  private final Button[] fBrowseBts = new Button[2];
  
  private final Map<String, ILaunchConfigurationWorkingCopy> fWCMap = new HashMap<String, ILaunchConfigurationWorkingCopy>();
  
  private Button fLocalConnBt;
  
  private Button fRemoteConnBt;
  
  private Text fHostText;
  
  private Spinner fPortText;
  
  private Text fUserNameText;
  
  private Button fPasswordBasedAuthBt;
  
  private Text fPasswordText;
  
  private Button fPrivateKeyFileAuthBt;
  
  private Text fPrivateKeyFileText;
  
  private Text fPassphraseText;
  
  private Spinner fConnectionTimeoutSpinner;
  
  private Button fUsePortForwardingBt;
  
  private Text fLocalAddressText;
  
  private String fLaunchConfigName;
  
  private Text fRemoteOutputFolderText;
  
  private Text fX10DistributionText;
  
  private boolean fRMUpdateRunning;
    
}
