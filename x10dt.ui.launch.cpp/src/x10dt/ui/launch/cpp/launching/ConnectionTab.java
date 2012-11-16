package x10dt.ui.launch.cpp.launching;

import static x10dt.ui.launch.core.utils.PTPConstants.LOCAL_CONN_SERVICE_ID;
import static x10dt.ui.launch.core.utils.PTPConstants.PAMI_SERVICE_PROVIDER_ID;
import static x10dt.ui.launch.core.utils.PTPConstants.REMOTE_CONN_SERVICE_ID;
import static x10dt.ui.launch.core.utils.PTPConstants.SOCKETS_SERVICE_PROVIDER_ID;
import static x10dt.ui.launch.core.utils.PTPConstants.STANDALONE_SERVICE_PROVIDER_ID;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationListener;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.internal.ui.SWTFactory;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.imp.utils.Pair;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.ptp.remote.core.IRemoteConnection;
import org.eclipse.ptp.remote.core.IRemoteConnectionManager;
import org.eclipse.ptp.remote.core.IRemoteServices;
import org.eclipse.ptp.remote.core.PTPRemoteCorePlugin;
import org.eclipse.ptp.remote.remotetools.core.RemoteToolsServices;
import org.eclipse.ptp.remote.ui.IRemoteUIConstants;
import org.eclipse.ptp.remote.ui.IRemoteUIFileManager;
import org.eclipse.ptp.remote.ui.IRemoteUIServices;
import org.eclipse.ptp.remote.ui.PTPRemoteUIPlugin;
import org.eclipse.ptp.remotetools.environment.EnvironmentPlugin;
import org.eclipse.ptp.remotetools.environment.control.ITargetConfig;
import org.eclipse.ptp.remotetools.environment.control.ITargetStatus;
import org.eclipse.ptp.remotetools.environment.core.ITargetElement;
import org.eclipse.ptp.remotetools.environment.core.TargetElement;
import org.eclipse.ptp.remotetools.environment.core.TargetTypeElement;
import org.eclipse.ptp.services.core.IService;
import org.eclipse.ptp.services.core.IServiceProvider;
import org.eclipse.ptp.services.core.IServiceProviderDescriptor;
import org.eclipse.ptp.services.core.ServiceModelManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
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
import org.eclipse.swt.widgets.Display;
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
        this.fCITypeCombo.select(getIndexForCIT(STANDALONE_SERVICE_PROVIDER_ID));
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
      int i = 0;
      i++;
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
    
  

  public void performApply(ILaunchConfigurationWorkingCopy configuration) {
    configuration.setAttribute(ATTR_IS_LOCAL, this.fLocalConnBt.getSelection());
    configuration.setAttribute(ATTR_CITYPE, this.fCITypeComboIndexMap[this.fCITypeCombo.getSelectionIndex()]);
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
  
  public boolean isValid(final ILaunchConfiguration configuration) {
    setErrorMessage(null);
    if (this.fRemoteConnBt.getSelection()) {
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

    }
    // Do not enable launch because this configuration type is not launchable.
    return false;
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
    
    final Collection<Control> remoteControls = new ArrayList<Control>();
   
    final Group remoteGroup = new Group(marginCompo, SWT.NONE);
    remoteGroup.setFont(parent.getFont());
    remoteGroup.setLayout(new GridLayout(4, false));
    remoteGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
    
    final Button checkButton = createPushButton(remoteGroup, LaunchMessages.VMLT_CheckConnBt, null /* image */);
    remoteControls.add(checkButton);
    
    
    final Composite statusCompo = new Composite(remoteGroup, SWT.NONE);
    statusCompo.setFont(remoteGroup.getFont());
    statusCompo.setLayout(new GridLayout(2, false));
    statusCompo.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
    
   
    this.fConnLabel = new Label(statusCompo, SWT.NONE);
    this.fConnLabel.setText(LaunchMessages.VMLT_ConnStatus);
    this.fConnLabel.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false));
    this.fStatusLabel = new CLabel(statusCompo, SWT.NONE);
    this.fStatusLabel.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
    this.fConnLabel.setImage(null);
        
    this.fHostText = SWTFormUtils.createLabelAndText(remoteGroup, LaunchMessages.VMLT_HostLabel, remoteControls);
    
    final Composite portCompo = new Composite(remoteGroup, SWT.NONE);
    portCompo.setFont(remoteGroup.getFont());
    portCompo.setLayout(new GridLayout(2, false));
    portCompo.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
    
    
    final Label portLabel = new Label(portCompo, SWT.NONE);
    portLabel.setText(LaunchMessages.VMLT_PortLabel);
    portLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
    remoteControls.add(portLabel);
    this.fPortText = new Spinner(portCompo, SWT.SINGLE | SWT.BORDER);
    this.fPortText.setMinimum(0);
    this.fPortText.setSelection(22);
    this.fPortText.setTextLimit(10);
    this.fPortText.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false));
    remoteControls.add(this.fPortText);
    
    this.fUserNameText = SWTFormUtils.createLabelAndText(remoteGroup, LaunchMessages.VMLT_UserLabel, remoteControls);
    
    this.fPasswordBasedAuthBt = createRadioButton(remoteGroup, LaunchMessages.VMLT_PasswordAuthBt);
    this.fPasswordBasedAuthBt.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1));
    remoteControls.add(this.fPasswordBasedAuthBt);
    
    final Composite passwordCompo = new Composite(remoteGroup, SWT.NONE);
    passwordCompo.setFont(remoteGroup.getFont());
    final GridLayout passwordGLayout = new GridLayout(1, false);
    passwordGLayout.marginLeft = 15;
    passwordCompo.setLayout(passwordGLayout);
    passwordCompo.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1));
    
    this.fPasswordText = SWTFormUtils.createLabelAndText(passwordCompo, LaunchMessages.VMLT_PasswordLabel,
                                            new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1), 
                                            SWT.BORDER | SWT.PASSWORD, remoteControls);

    this.fPrivateKeyFileAuthBt = createRadioButton(remoteGroup, LaunchMessages.VMLT_PublicKeyAutBt);
    this.fPrivateKeyFileAuthBt.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1));
    remoteControls.add(this.fPrivateKeyFileAuthBt);
    
    final Composite privateKeyCompo = new Composite(remoteGroup, SWT.NONE);
    privateKeyCompo.setFont(remoteGroup.getFont());
    final GridLayout privateKeyGLayout = new GridLayout(1, false);
    privateKeyGLayout.marginLeft = 15;
    privateKeyCompo.setLayout(privateKeyGLayout);
    privateKeyCompo.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1));
    final Pair<Text,Button> pair = SWTFormUtils.createLabelTextAndPushButton(privateKeyCompo, LaunchMessages.VMLT_PrivateKeyFileLabel,
                                                                LaunchMessages.VMLT_BrowseBt, remoteControls);
    this.fPrivateKeyFileText = pair.first;
    
    this.fPassphraseText = SWTFormUtils.createLabelAndText(privateKeyCompo, LaunchMessages.VMLT_PassphraseLabel,
                                              new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1), 
                                              SWT.BORDER | SWT.PASSWORD, remoteControls);
    
    final Label separator = new Label(remoteGroup, SWT.SEPARATOR | SWT.SHADOW_OUT | SWT.HORIZONTAL);
    separator.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1));
    
    this.fUsePortForwardingBt = createCheckButton(remoteGroup, LaunchMessages.VMLT_PortFrwdLabel);
    this.fUsePortForwardingBt.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));
    remoteControls.add(this.fUsePortForwardingBt);
    
    final Label timeoutLabel = new Label(remoteGroup, SWT.NONE);
    timeoutLabel.setText(LaunchMessages.VMLT_TimeoutLabel);
    
    this.fConnectionTimeoutSpinner = new Spinner(remoteGroup, SWT.SINGLE | SWT.BORDER);
    this.fConnectionTimeoutSpinner.setMinimum(0);
    this.fConnectionTimeoutSpinner.setTextLimit(5);
    this.fConnectionTimeoutSpinner.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false));
    remoteControls.add(this.fConnectionTimeoutSpinner);
    
    this.fLocalAddressText = SWTFormUtils.createLabelAndText(remoteGroup, LaunchMessages.VMLT_LocalAddressLabel, 1, SWT.NONE);
    remoteControls.add(this.fLocalAddressText);
    
    final Pair<Text,Button> pair2 = SWTFormUtils.createLabelTextAndPushButton(marginCompo, LaunchMessages.VMLT_RemoteOutputFolder, 
                                                                 LaunchMessages.VMLT_BrowseBt,
                                                                 new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1),
                                                                 remoteControls);
    this.fRemoteOutputFolderText = pair2.first;
    this.fBrowseBts[0] = pair2.second;
    
    final Pair<Text,Button> pair3 = SWTFormUtils.createLabelTextAndPushButton(marginCompo, LaunchMessages.VMLT_X10Dist, LaunchMessages.VMLT_BrowseBt,
                                                                 new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1),
                                                                 remoteControls);
    this.fX10DistributionText = pair3.first;
    this.fBrowseBts[1] = pair3.second;
    
    addConnectionWidgetsListeners(remoteControls, pair.second, pair2.second, pair3.second, checkButton);
    
    this.fLocalConnBt.setSelection(true);
    this.fLocalConnBt.notifyListeners(SWT.Selection, new Event());
    

    
  }
  
  
  private void initTypeCombo() {
    final Set<IServiceProviderDescriptor> serviceProviders= new HashSet<IServiceProviderDescriptor>();
    final ServiceModelManager serviceModelManager = ServiceModelManager.getInstance();
    int index = 0;
    
    for (final IService service : serviceModelManager.getServices()) {
      if (service.getCategory() == null)
        continue;
      if (PTPConstants.RUNTIME_SERVICE_CATEGORY_ID.equals(service.getCategory().getId()) && service.getName().equals(LaunchMessages.CISP_Launch)) {
        serviceProviders.addAll(service.getProviders());
      }
    }
    for (final IServiceProviderDescriptor providerDescriptor : serviceProviders) {
      final IServiceProvider serviceProvider= serviceModelManager.getServiceProvider(providerDescriptor);
      if (serviceProvider == null)
        continue;
      String rmId= serviceProvider.getId();
      if (SOCKETS_SERVICE_PROVIDER_ID.equals(rmId) || STANDALONE_SERVICE_PROVIDER_ID.equals(rmId) || PAMI_SERVICE_PROVIDER_ID.equals(rmId)) {
        this.fCITypeCombo.add(providerDescriptor.getName());
        this.fCITypeComboIndexMap[index++] = rmId;
      }

    }
    this.fCITypeCombo.select(getIndexForCIT(STANDALONE_SERVICE_PROVIDER_ID));
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
      final PTPRemoteCorePlugin plugin = PTPRemoteCorePlugin.getDefault();
      final IRemoteServices rmServices = plugin.getRemoteServices(PTPConstants.REMOTE_CONN_SERVICE_ID);
      final IRemoteConnectionManager rmConnManager = rmServices.getConnectionManager();
      final IRemoteConnection rmConnection = rmConnManager.getConnection(this.fLaunchConfigName);
      final IRemoteUIServices rmUIServices = PTPRemoteUIPlugin.getDefault().getRemoteUIServices(rmServices);
      
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
      
      final Display display = getShell().getDisplay();
      new Thread(new Runnable() {

        public void run() {
          try {
            final Exception[] exception = new Exception[1];
            display.syncExec(new Runnable() {

              public void run() {
                try {
                  if (ConnectionTab.this.fRemoteConnBt.isDisposed() || ConnectionTab.this.fLocalAddressText.isDisposed() || ConnectionTab.this.fUsePortForwardingBt.isDisposed()) {
                    return;
                  }
                 
                  final PTPRemoteCorePlugin plugin = PTPRemoteCorePlugin.getDefault();
                  IRemoteConnectionManager rmConnManager = null;
                  
                  if (ConnectionTab.this.fRemoteConnBt.getSelection()) {
                    createOrUpdateRemoteConnection();
                    rmConnManager = plugin.getRemoteServices(REMOTE_CONN_SERVICE_ID).getConnectionManager();
                    IRemoteConnection conn = rmConnManager.getConnection(ConnectionTab.this.fLaunchConfigName);
                    conn.open(new NullProgressMonitor());
                  } else {
                    rmConnManager = plugin.getRemoteServices(LOCAL_CONN_SERVICE_ID).getConnectionManager();
                  }
                 
                  
                } catch (Exception except) {
                  exception[0] = except;
                }
              }

            });
            if (exception[0] != null) {
              throw exception[0];
            }

            if (ConnectionTab.this.fLocalConnBt.isDisposed() || ConnectionTab.this.fStatusLabel.isDisposed()) {
              return;
            }
            display.syncExec(new Runnable() {

              public void run() {
                if (ConnectionTab.this.fLocalConnBt.getSelection()) {
                  //ConnectionTab.this.fConnLabel.setImage(LaunchImages.getImage(LaunchImages.RM_STARTING));
                  ConnectionTab.this.fConnLabel.setImage(null);
                  ConnectionTab.this.fStatusLabel.setText(Constants.EMPTY_STR);
                } else {
                  ConnectionTab.this.fConnLabel.setImage(LaunchImages.getImage(LaunchImages.RM_STARTED));
                  ConnectionTab.this.fStatusLabel.setText(Constants.EMPTY_STR);
                }
                updateBrowseButtonsEnablement(true);
              }

            });

           } catch (final Exception except) {
            if (!ConnectionTab.this.fStatusLabel.isDisposed()) {
              display.syncExec(new Runnable() {

                public void run() {
                  String error = processJSchMessage(except.getMessage());
                  ConnectionTab.this.fStatusLabel.setText(error);
                  ConnectionTab.this.fConnLabel.setImage(LaunchImages.getImage(LaunchImages.RM_ERROR));
                  setErrorMessage(error);
                  updateBrowseButtonsEnablement(false);
                }

              });
            }
          }
          ConnectionTab.this.fRMUpdateRunning = false;
        }

      }).start();
     
      this.fRMUpdateRunning = true;
    }
  }
  
  private ITargetElement getDefaultTargetElement(final String connectionName) {
    final TargetTypeElement targetTypeElement = RemoteToolsServices.getTargetTypeElement();
    for (final ITargetElement targetElement : targetTypeElement.getElements()) {
      if (targetElement.getName().equals(connectionName)) {
        return targetElement;
      }
    }
    return null;
  }
  
  public void createOrUpdateRemoteConnection() throws CoreException {
    final PTPRemoteCorePlugin plugin = PTPRemoteCorePlugin.getDefault();
    final IRemoteConnectionManager rmConnManager = plugin.getRemoteServices(REMOTE_CONN_SERVICE_ID).getConnectionManager();
    final TargetTypeElement targetTypeElement = RemoteToolsServices.getTargetTypeElement();

    ITargetElement targetElement = getDefaultTargetElement(this.fLaunchConfigName);
    if (targetElement == null) {
      final String id = EnvironmentPlugin.getDefault().getEnvironmentUniqueID();
      targetElement = new TargetElement(targetTypeElement, this.fLaunchConfigName, new HashMap<String, String>(), id);
      targetTypeElement.addElement((TargetElement) targetElement);
    } else {
      if (targetElement.getControl().query() != ITargetStatus.STOPPED) {
        targetElement.getControl().kill();     
      }
    }
    
    updateControlAttributes(targetElement.getControl().getConfig());
    
    rmConnManager.getConnections(); // Side effect of creating connection.
    
  }
  
  
  private void updateControlAttributes(final ITargetConfig targetConfig) {
    targetConfig.setConnectionAddress(this.fHostText.getText());
    targetConfig.setLoginUsername(this.fUserNameText.getText());
    targetConfig.setConnectionPort(this.fPortText.getSelection());
    targetConfig.setPasswordAuth(!this.fPrivateKeyFileAuthBt.getSelection());
    targetConfig.setLoginPassword(this.fPasswordText.getText());
    targetConfig.setKeyPath(this.fPrivateKeyFileText.getText());
    targetConfig.setKeyPassphrase(this.fPassphraseText.getText());
    targetConfig.setConnectionTimeout(this.fConnectionTimeoutSpinner.getSelection());
  }
  
  private int getIndexForCIT(String cit){
    for(int i = 0; i < this.fCITypeComboIndexMap.length; i++){
      if (this.fCITypeComboIndexMap[i].equals(cit))
        return i;
    }
    return -1;
  }

  private String[] fCITypeComboIndexMap = new String[3];
  
  private final Map<String, ILaunchConfigurationWorkingCopy> fWCMap = new HashMap<String, ILaunchConfigurationWorkingCopy>();
  
  private final Button[] fBrowseBts = new Button[2];
   
  private Button fLocalConnBt;
  
  private Button fRemoteConnBt;
  
  private CLabel fStatusLabel;
  
  private Label fConnLabel;
  
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
