package x10dt.ui.launch.cpp.launching;

import static x10dt.ui.launch.core.utils.PTPConstants.REMOTE_CONN_SERVICE_ID;

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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.LaunchImages;
import x10dt.ui.launch.core.utils.PTPConstants;
import x10dt.ui.launch.core.utils.SWTFormUtils;
import x10dt.ui.launch.cpp.CppLaunchCore;
import x10dt.ui.launch.cpp.LaunchMessages;

public class ConnectionTab extends AbstractLaunchConfigurationTab 
implements ILaunchConfigurationTab, ILaunchConfigurationListener {

  public static final String ATTR_IS_LOCAL = CppLaunchCore.PLUGIN_ID + ".mvm.is_local"; //$NON-NLS-1$
  
  public static final String ATTR_HOST = CppLaunchCore.PLUGIN_ID + ".mvm.host"; //$NON-NLS-1$
  
  public static final String ATTR_PORT = CppLaunchCore.PLUGIN_ID + ".mvm.port"; //$NON-NLS-1$
  
  public static final String ATTR_CITYPE = CppLaunchCore.PLUGIN_ID + ".mvm.citype"; //$NON-NLS-1$
  
  public static final String ATTR_USERNAME = CppLaunchCore.PLUGIN_ID + ".mvm.user_name"; //$NON-NLS-1$
  
  public static final String ATTR_IS_PASSWORD_BASED = CppLaunchCore.PLUGIN_ID + ".mvm.is_password_based"; //$NON-NLS-1$
  
  public static final String ATTR_PASSWORD = CppLaunchCore.PLUGIN_ID + ".mvm.password"; //$NON-NLS-1$
  
  public static final String ATTR_PRIVATE_KEY_FILE = CppLaunchCore.PLUGIN_ID + ".mvm.private_key_file"; //$NON-NLS-1$
  
  public static final String ATTR_PASSPHRASE = CppLaunchCore.PLUGIN_ID + ".mvm.passphrase"; //$NON-NLS-1$
  
  public static final String ATTR_USE_PORT_FORWARDING = CppLaunchCore.PLUGIN_ID + ".mvm.use.port_forwarding"; //$NON-NLS-1$
  
  public static final String ATTR_TIMEOUT = CppLaunchCore.PLUGIN_ID + ".mvm.use.timeout"; //$NON-NLS-1$
  
  public static final String ATTR_LOCAL_ADDRESS = CppLaunchCore.PLUGIN_ID + ".mvm.use.local_address"; //$NON-NLS-1$
  
  public static final String ATTR_REMOTE_OUTPUT_FOLDER = CppLaunchCore.PLUGIN_ID + ".mvm.output.folder"; //$NON-NLS-1$
  
  public static final String ATTR_X10_DISTRIBUTION = CppLaunchCore.PLUGIN_ID + ".mvm.x10.distrib"; //$NON-NLS-1$
  
  public static final String IS_VALID = CppLaunchCore.PLUGIN_ID + ".mvm.is.valid"; //$NON-NLS-1$
  
  public static final String STANDALONE = "Standalone";
  
  public static final String SOCKETS = "Sockets";
  
  public static final String PAMI = "PAMI";
  
  public static final int STANDALONE_INDEX = 0;
  
  public static final int SOCKETS_INDEX = 1;
  
  public static final int PAMI_INDEX = 2;
  
  
  
  ConnectionTab() {
    LaunchImages.findOrCreateManaged(LaunchImages.VMS_LOCATION);
    LaunchImages.findOrCreateManaged(LaunchImages.RM_STOPPED);
    LaunchImages.findOrCreateManaged(LaunchImages.RM_STARTED);
    LaunchImages.findOrCreateManaged(LaunchImages.RM_STARTING);
    LaunchImages.findOrCreateManaged(LaunchImages.RM_ERROR);
    
    DebugPlugin.getDefault().getLaunchManager().addLaunchConfigurationListener(this);
  }
  
  public void launchConfigurationAdded(ILaunchConfiguration configuration) {
    try {
      final String projectName = configuration.getAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, 
                                                            Constants.EMPTY_STR);
      final ILaunchConfigurationWorkingCopy launchWC = this.fWCMap.get(configuration.getName());
      if ((projectName.length() > 0) && (launchWC != null)) {
        if (CppLaunchCore.REMOTE_COMPILATION_LAUNCH_CONF_TYPE.equals(configuration.getType().getIdentifier()) &&
            (! configuration.getName().equals(this.fLaunchConfigName))) {
          this.fLaunchConfigName = configuration.getName();
          updateConnection();
          while (this.fRMUpdateRunning) {
            getShell().getDisplay().readAndDispatch();
          }
        }
      }
    } catch (CoreException except) {
      CppLaunchCore.getInstance().getLog().log(except.getStatus());
    }
  }

  public void launchConfigurationChanged(ILaunchConfiguration configuration) {
    if (configuration instanceof ILaunchConfigurationWorkingCopy) {
      this.fWCMap.put(configuration.getName(), (ILaunchConfigurationWorkingCopy) configuration);
    }
    
  }

  public void launchConfigurationRemoved(ILaunchConfiguration configuration) {
    this.fWCMap.remove(configuration.getName());
  }

  public void createControl(Composite parent) {
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

  public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
    // nothing to do
    
  }
  
  private int getIndexForCIT(String cit){
    if (cit.equals(STANDALONE)){
      return STANDALONE_INDEX;
    }
    if (cit.equals(SOCKETS)){
      return SOCKETS_INDEX;
    }
    if (cit.equals(PAMI)){
      return PAMI_INDEX;
    }
    return -1;
  }

  public void initializeFrom(ILaunchConfiguration configuration) {
    LaunchImages.findOrCreateManaged(LaunchImages.VMS_LOCATION);
    LaunchImages.findOrCreateManaged(LaunchImages.RM_STOPPED);
    LaunchImages.findOrCreateManaged(LaunchImages.RM_STARTED);
    LaunchImages.findOrCreateManaged(LaunchImages.RM_STARTING);
    LaunchImages.findOrCreateManaged(LaunchImages.RM_ERROR);
    
    this.fLaunchConfigName = configuration.getName();
    try {
      String cit = configuration.getAttribute(ATTR_CITYPE, Constants.EMPTY_STR);
      if (cit == null){
        this.fCITypeCombo.select(STANDALONE_INDEX);
      } else {
        this.fCITypeCombo.select(getIndexForCIT(cit));
      }
      
      this.fHostText.setText(configuration.getAttribute(ATTR_HOST, Constants.EMPTY_STR));
      this.fPortText.setSelection(configuration.getAttribute(ATTR_PORT, 22));
      this.fUserNameText.setText(configuration.getAttribute(ATTR_USERNAME, Constants.EMPTY_STR));
      this.fPasswordBasedAuthBt.setSelection(configuration.getAttribute(ATTR_IS_PASSWORD_BASED, true));
      this.fPrivateKeyFileAuthBt.setSelection(! this.fPasswordBasedAuthBt.getSelection());
      if (this.fPrivateKeyFileAuthBt.getSelection()) {
        this.fPrivateKeyFileText.setText(configuration.getAttribute(ATTR_PRIVATE_KEY_FILE, Constants.EMPTY_STR));
        this.fPassphraseText.setText(configuration.getAttribute(ATTR_PASSPHRASE, Constants.EMPTY_STR));
      } else {
        final String password = configuration.getAttribute(ATTR_PASSWORD, Constants.EMPTY_STR);
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
      this.fRemoteConnBt.setSelection(! this.fLocalConnBt.getSelection());
      if (this.fRemoteConnBt.getSelection()){
        for (final Control control : this.fRemoteControls) {
          control.setEnabled(true);
        }
      } else {
        for (final Control control : this.fRemoteControls) {
          control.setEnabled(false);
        }
      }
    } catch (CoreException except) {
      CppLaunchCore.getInstance().getLog().log(except.getStatus());
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
    
  private String getCIT(int index){
    if (index == STANDALONE_INDEX){
      return STANDALONE;
    }
    if (index == SOCKETS_INDEX){
      return SOCKETS;
    }
    if (index == PAMI_INDEX){
      return PAMI;
    }
    return null;
  }

  public void performApply(ILaunchConfigurationWorkingCopy configuration) {
    configuration.setAttribute(ATTR_IS_LOCAL, this.fLocalConnBt.getSelection());
    configuration.setAttribute(ATTR_CITYPE, getCIT(this.fCITypeCombo.getSelectionIndex()));
    
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
    return LaunchMessages.VMLT_TabName;
  }
 
  
  public Image getImage() {
    return org.eclipse.ptp.launch.ui.LaunchImages.getImage(org.eclipse.ptp.launch.ui.LaunchImages.IMG_PARALLEL_TAB);
  }
  
  public void dispose() {
    DebugPlugin.getDefault().getLaunchManager().removeLaunchConfigurationListener(this);
    LaunchImages.removeImage(LaunchImages.VMS_LOCATION);
    LaunchImages.removeImage(LaunchImages.RM_STOPPED);
    LaunchImages.removeImage(LaunchImages.RM_STARTED);
    LaunchImages.removeImage(LaunchImages.RM_STARTING);
    LaunchImages.removeImage(LaunchImages.RM_ERROR);
    super.dispose();
  }
  
  private boolean errorMessageErasable(String message){
    if (message == null) return true;
    if (message.equals(LaunchMessages.VMLT_HostNameNotDefined) ||
        message.equals(LaunchMessages.VMLT_UserNameNotDefined) ||
        message.equals(LaunchMessages.VMLT_PrivateKeyFileNotDefined) ||
        message.equals(LaunchMessages.VMLT_OutputFolderNotDefined) ||
        message.equals(LaunchMessages.VMLT_X10DistNotDefined)) {
      return true;
    }
    return false;
  }
  
  public boolean isValid(final ILaunchConfiguration configuration) {
    
    try {
      ILaunchConfigurationWorkingCopy wc = configuration.getWorkingCopy();
      wc.setAttribute(IS_VALID, false);
      wc.doSave();
    } catch (CoreException e){
      CppLaunchCore.getInstance().getLog().log(e.getStatus());
    }
   
    if (this.fRemoteConnBt.getSelection()) {
      if (errorMessageErasable(getErrorMessage())){
        setErrorMessage(null);
      } else {
        return false;
      }
      if (this.fHostText.getText().length() == 0) {
        setErrorMessage(LaunchMessages.VMLT_HostNameNotDefined);
        return false;
      }
      if (this.fUserNameText.getText().length() == 0) {
        setErrorMessage(LaunchMessages.VMLT_UserNameNotDefined);
        return false;
      }
      if (this.fPrivateKeyFileAuthBt.getSelection()) {
        if (this.fPrivateKeyFileText.getText().length() == 0) {
          setErrorMessage(LaunchMessages.VMLT_PrivateKeyFileNotDefined);
          return false;
        }
      }
      if (this.fRemoteOutputFolderText.getText().length() == 0) {
        setErrorMessage(LaunchMessages.VMLT_OutputFolderNotDefined);
        return false;
      }
      if (this.fX10DistributionText.getText().length() == 0) {
        setErrorMessage(LaunchMessages.VMLT_X10DistNotDefined);
        return false;
      }

    } else { //local
      setErrorMessage(null);
    }
    
    try {
      ILaunchConfigurationWorkingCopy wc = configuration.getWorkingCopy();
      wc.setAttribute(IS_VALID, true);
      wc.doSave();
    } catch (CoreException e){
      CppLaunchCore.getInstance().getLog().log(e.getStatus());
    }
    setErrorMessage(null);
    return true;
  }
  
  
  // private code
  
  private void createConnectionGroup(final Composite parent) {
    this.fCITypeCombo = createLabelAndCombo(parent, LaunchMessages.RMCP_CITypeLabel);
    
    initTypeCombo();
    
    this.fLocalConnBt = createRadioButton(parent, LaunchMessages.VMLT_LocalConnBt);
    
    this.fRemoteConnBt = createRadioButton(parent, LaunchMessages.VMLT_RemoteConnBt);
    
    final Composite marginCompo = new Composite(parent, SWT.NONE);
    marginCompo.setFont(parent.getFont());
    final GridLayout marginGLayout = new GridLayout(3, false);
    marginGLayout.marginLeft = 15;
    marginCompo.setLayout(marginGLayout);
    marginCompo.setLayoutData(new GridData(SWT.LEFT, SWT.NONE, true, false));
    
    this.fRemoteControls = new ArrayList<Control>();
   
    final Group remoteGroup = new Group(marginCompo, SWT.NONE);
    remoteGroup.setFont(parent.getFont());
    remoteGroup.setLayout(new GridLayout(4, false));
    remoteGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
    
    final Button checkButton = createPushButton(remoteGroup, LaunchMessages.VMLT_CheckConnBt, null /* image */);
    this.fRemoteControls.add(checkButton);
    
    
    final Composite statusCompo = new Composite(remoteGroup, SWT.NONE);
    statusCompo.setFont(remoteGroup.getFont());
    statusCompo.setLayout(new GridLayout(2, false));
    statusCompo.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
      
    this.fHostText = SWTFormUtils.createLabelAndText(remoteGroup, LaunchMessages.VMLT_HostLabel, this.fRemoteControls);
    
    final Composite portCompo = new Composite(remoteGroup, SWT.NONE);
    portCompo.setFont(remoteGroup.getFont());
    portCompo.setLayout(new GridLayout(2, false));
    portCompo.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
    
    
    final Label portLabel = new Label(portCompo, SWT.NONE);
    portLabel.setText(LaunchMessages.VMLT_PortLabel);
    portLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
    this.fRemoteControls.add(portLabel);
    this.fPortText = new Spinner(portCompo, SWT.SINGLE | SWT.BORDER);
    this.fPortText.setMinimum(0);
    this.fPortText.setSelection(22);
    this.fPortText.setTextLimit(10);
    this.fPortText.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false));
    this.fRemoteControls.add(this.fPortText);
    
    this.fUserNameText = SWTFormUtils.createLabelAndText(remoteGroup, LaunchMessages.VMLT_UserLabel, this.fRemoteControls);
    
    this.fPasswordBasedAuthBt = createRadioButton(remoteGroup, LaunchMessages.VMLT_PasswordAuthBt);
    this.fPasswordBasedAuthBt.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1));
    this.fRemoteControls.add(this.fPasswordBasedAuthBt);
    
    final Composite passwordCompo = new Composite(remoteGroup, SWT.NONE);
    passwordCompo.setFont(remoteGroup.getFont());
    final GridLayout passwordGLayout = new GridLayout(1, false);
    passwordGLayout.marginLeft = 15;
    passwordCompo.setLayout(passwordGLayout);
    passwordCompo.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1));
    
    this.fPasswordText = SWTFormUtils.createLabelAndText(passwordCompo, LaunchMessages.VMLT_PasswordLabel,
                                            new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1), 
                                            SWT.BORDER | SWT.PASSWORD, this.fRemoteControls);

    this.fPrivateKeyFileAuthBt = createRadioButton(remoteGroup, LaunchMessages.VMLT_PublicKeyAutBt);
    this.fPrivateKeyFileAuthBt.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1));
    this.fRemoteControls.add(this.fPrivateKeyFileAuthBt);
    
    final Composite privateKeyCompo = new Composite(remoteGroup, SWT.NONE);
    privateKeyCompo.setFont(remoteGroup.getFont());
    final GridLayout privateKeyGLayout = new GridLayout(1, false);
    privateKeyGLayout.marginLeft = 15;
    privateKeyCompo.setLayout(privateKeyGLayout);
    privateKeyCompo.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1));
    final Pair<Text,Button> pair = SWTFormUtils.createLabelTextAndPushButton(privateKeyCompo, LaunchMessages.VMLT_PrivateKeyFileLabel,
                                                                LaunchMessages.VMLT_BrowseBt, this.fRemoteControls);
    this.fPrivateKeyFileText = pair.first;
    
    this.fPassphraseText = SWTFormUtils.createLabelAndText(privateKeyCompo, LaunchMessages.VMLT_PassphraseLabel,
                                              new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1), 
                                              SWT.BORDER | SWT.PASSWORD, this.fRemoteControls);
    
    final Label separator = new Label(remoteGroup, SWT.SEPARATOR | SWT.SHADOW_OUT | SWT.HORIZONTAL);
    separator.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1));
    
    this.fUsePortForwardingBt = createCheckButton(remoteGroup, LaunchMessages.VMLT_PortFrwdLabel);
    this.fUsePortForwardingBt.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));
    this.fRemoteControls.add(this.fUsePortForwardingBt);
    
    final Label timeoutLabel = new Label(remoteGroup, SWT.NONE);
    timeoutLabel.setText(LaunchMessages.VMLT_TimeoutLabel);
    
    this.fConnectionTimeoutSpinner = new Spinner(remoteGroup, SWT.SINGLE | SWT.BORDER);
    this.fConnectionTimeoutSpinner.setMinimum(0);
    this.fConnectionTimeoutSpinner.setTextLimit(5);
    this.fConnectionTimeoutSpinner.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false));
    this.fRemoteControls.add(this.fConnectionTimeoutSpinner);
    
    this.fLocalAddressText = SWTFormUtils.createLabelAndText(remoteGroup, LaunchMessages.VMLT_LocalAddressLabel, 1, SWT.NONE);
    this.fRemoteControls.add(this.fLocalAddressText);
    
    final Pair<Text,Button> pair2 = SWTFormUtils.createLabelTextAndPushButton(marginCompo, LaunchMessages.VMLT_RemoteOutputFolder, 
                                                                 LaunchMessages.VMLT_BrowseBt,
                                                                 new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1),
                                                                 this.fRemoteControls);
    this.fRemoteOutputFolderText = pair2.first;
    this.fBrowseBts[0] = pair2.second;
    
    final Pair<Text,Button> pair3 = SWTFormUtils.createLabelTextAndPushButton(marginCompo, LaunchMessages.VMLT_X10Dist, LaunchMessages.VMLT_BrowseBt,
                                                                 new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1),
                                                                 this.fRemoteControls);
    this.fX10DistributionText = pair3.first;
    this.fBrowseBts[1] = pair3.second;
    
    addConnectionWidgetsListeners(this.fRemoteControls, pair.second, pair2.second, pair3.second, checkButton);
    
    this.fLocalConnBt.setSelection(true);
    this.fLocalConnBt.notifyListeners(SWT.Selection, new Event());
    

    
  }
  
  
  private void initTypeCombo() {
    this.fCITypeCombo.add(STANDALONE);
    this.fCITypeCombo.add(SOCKETS);
    this.fCITypeCombo.add(PAMI);
    this.fCITypeCombo.select(STANDALONE_INDEX);
  }
  
  public static Combo createLabelAndCombo(final Composite parent, final String labelText) {
    final Composite composite= new Composite(parent, SWT.NONE);
    final boolean isTableWrapLayout= parent.getLayout() instanceof TableWrapLayout;
    composite.setFont(parent.getFont());
    if (isTableWrapLayout) {
      final TableWrapLayout tableWrapLayout= new TableWrapLayout();
      tableWrapLayout.numColumns= 2;
      composite.setLayout(tableWrapLayout);
      composite.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    } else {
      composite.setLayout(new GridLayout(2, false));
      composite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
    }
  

    final Label label= new Label(composite, SWT.NONE);
    label.setText(labelText);
    if (isTableWrapLayout) {
      label.setLayoutData(new TableWrapData(TableWrapData.FILL, TableWrapData.MIDDLE));
    } else {
      label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
    }
   

    final Combo combo= new Combo(composite, SWT.READ_ONLY);
    if (isTableWrapLayout) {
      final TableWrapData td= new TableWrapData(TableWrapData.FILL_GRAB);
      td.indent= 5;
      combo.setLayoutData(td);
    } else {
      final GridData gd= new GridData(SWT.LEFT, SWT.NONE, true, false);
      gd.horizontalIndent= 5;
      combo.setLayoutData(gd);
    }
    //toolkit.paintBordersFor(composite);

 
    return combo;

  }
  

  
  private void addConnectionWidgetsListeners(final Collection<Control> remoteControls, final Button browseFileButton, final Button outputFolderBrowseBt,
      final Button x10DistFolderBrowseBt, final Button checkButton) {
    this.fCITypeCombo.addSelectionListener(new SelectionListener() {

      public void widgetSelected(final SelectionEvent event) {
        updateLaunchConfigurationDialog();
      }

      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }

    });
    this.fLocalConnBt.addSelectionListener(new SelectionListener() {

      public void widgetSelected(final SelectionEvent event) {
        if (ConnectionTab.this.fLocalConnBt.getSelection()) {
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
        if (ConnectionTab.this.fRemoteConnBt.getSelection()) {
          for (final Control control : remoteControls) {
            control.setEnabled(true);
          }
          browseFileButton.setEnabled(true);
          outputFolderBrowseBt.setEnabled(false);
          x10DistFolderBrowseBt.setEnabled(false);
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
    final ModifyListener modifyListener= new UpdateConfDialogModifyListener();
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
        final FileDialog dialog= new FileDialog(getShell());
        dialog.setText(LaunchMessages.VMLT_PrivateKeyFileDialogMsg);
        final String path= dialog.open();
        if (path != null) {
          ConnectionTab.this.fPrivateKeyFileText.setText(path);
        }
        updateLaunchConfigurationDialog();
      }

      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }

    });
    outputFolderBrowseBt.addSelectionListener(new SelectionListener() {

      public void widgetSelected(final SelectionEvent event) {
        final String path= getFolderFromDialog(LaunchMessages.VMLT_SelectRemoteOutputFolder);
        if (path != null) {
          ConnectionTab.this.fRemoteOutputFolderText.setText(path);
        }
        updateLaunchConfigurationDialog();
      }

      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }

    });
    x10DistFolderBrowseBt.addSelectionListener(new SelectionListener() {

      public void widgetSelected(final SelectionEvent event) {
        final String path= getFolderFromDialog(LaunchMessages.VMLT_SelectX10Dist);
        if (path != null) {
          ConnectionTab.this.fX10DistributionText.setText(path);
        }
        updateLaunchConfigurationDialog();
      }

      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }

    });
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
  
  private final class UpdateConfDialogModifyListener implements ModifyListener {

    // --- Interface methods implementation
    
    public void modifyText(final ModifyEvent event) {
      updateLaunchConfigurationDialog();
    }
    
  }
  
  
  private String processJSchMessage(final String message) {
    if ("Auth cancel".equals(message)) { //$NON-NLS-1$
      return LaunchMessages.VMLT_PasswordCheckCanceled;
    } else if ("Auth fail".equals(message)) { //$NON-NLS-1$
      return LaunchMessages.VMLT_PasswordCheckFailed;
    } else if (message != null && message.contains("UnknownHostException")){ 
      return LaunchMessages.VMLT_UnknownHost; 
    } else if (message != null && message.contains("InterruptedIOException")){
      return LaunchMessages.VMLT_Interrupted;
    } else {
      return message;
    }
  }
  
  private void updateBrowseButtonsEnablement(final boolean enabled) {
    for (final Button button : this.fBrowseBts) {
      button.setEnabled(this.fLocalConnBt.getSelection() ? false : enabled);
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
  
  private void updateConnection() {
    
    if (! this.fRMUpdateRunning && (this.fLaunchConfigName != null) && hasAllConnectionInfo()) {
      this.fRMUpdateRunning = true;
      try {
        if (ConnectionTab.this.fRemoteConnBt.isDisposed() || ConnectionTab.this.fLocalAddressText.isDisposed() || ConnectionTab.this.fUsePortForwardingBt.isDisposed()) {
          return;
        }
       
        IRemoteConnectionManager rmConnManager = null;
        
        if (ConnectionTab.this.fRemoteConnBt.getSelection()) {
          ConfUtils.createOrUpdateRemoteConnection(this.fLaunchConfigName, this.fHostText.getText(), this.fUserNameText.getText(), 
              this.fPortText.getSelection(), !this.fPrivateKeyFileAuthBt.getSelection(), this.fPasswordText.getText(), 
              this.fPrivateKeyFileText.getText(), this.fPassphraseText.getText(), this.fConnectionTimeoutSpinner.getSelection());
          rmConnManager = RemoteServices.getRemoteServices(REMOTE_CONN_SERVICE_ID).getConnectionManager();
          IRemoteConnection conn = rmConnManager.getConnection(ConnectionTab.this.fLaunchConfigName);
          conn.open(new NullProgressMonitor());
          updateBrowseButtonsEnablement(true);
          setErrorMessage(null);
        } 
    } catch (Exception e){
      String error = processJSchMessage(e.getMessage());
      if (error != null) {
        setErrorMessage(error);
      } else {
        setErrorMessage(e.toString());
      }
      updateBrowseButtonsEnablement(false);
      
    }
      

    ConnectionTab.this.fRMUpdateRunning = false;
 
     
      
    }
  }
  
  
  private final Map<String, ILaunchConfigurationWorkingCopy> fWCMap = new HashMap<String, ILaunchConfigurationWorkingCopy>();
  
  private final Button[] fBrowseBts = new Button[2];
   
  private Button fLocalConnBt;
  
  private Button fRemoteConnBt;
  
  private Collection<Control> fRemoteControls;
  
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
  
  private Combo fCITypeCombo;
  
}
