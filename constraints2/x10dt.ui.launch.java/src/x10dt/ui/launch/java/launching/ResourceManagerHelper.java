/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.java.launching;

import static x10dt.ui.launch.core.utils.PTPConstants.LOCAL_CONN_SERVICE_ID;
import static x10dt.ui.launch.core.utils.PTPConstants.REMOTE_CONN_SERVICE_ID;
import static x10dt.ui.launch.java.launching.MultiVMAttrConstants.ATTR_HOST;
import static x10dt.ui.launch.java.launching.MultiVMAttrConstants.ATTR_IS_LOCAL;
import static x10dt.ui.launch.java.launching.MultiVMAttrConstants.ATTR_IS_PASSWORD_BASED;
import static x10dt.ui.launch.java.launching.MultiVMAttrConstants.ATTR_LOCAL_ADDRESS;
import static x10dt.ui.launch.java.launching.MultiVMAttrConstants.ATTR_PASSPHRASE;
import static x10dt.ui.launch.java.launching.MultiVMAttrConstants.ATTR_PASSWORD;
import static x10dt.ui.launch.java.launching.MultiVMAttrConstants.ATTR_PORT;
import static x10dt.ui.launch.java.launching.MultiVMAttrConstants.ATTR_PRIVATE_KEY_FILE;
import static x10dt.ui.launch.java.launching.MultiVMAttrConstants.ATTR_TIMEOUT;
import static x10dt.ui.launch.java.launching.MultiVMAttrConstants.ATTR_USERNAME;
import static x10dt.ui.launch.java.launching.MultiVMAttrConstants.ATTR_USE_PORT_FORWARDING;

import java.util.HashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.ptp.core.IServiceConstants;
import org.eclipse.ptp.core.ModelManager;
import org.eclipse.ptp.core.events.IResourceManagerAddedEvent;
import org.eclipse.ptp.core.events.IResourceManagerChangedEvent;
import org.eclipse.ptp.core.events.IResourceManagerErrorEvent;
import org.eclipse.ptp.core.events.IResourceManagerRemovedEvent;
import org.eclipse.ptp.core.listeners.IResourceManagerListener;
import org.eclipse.ptp.remote.core.IRemoteConnectionManager;
import org.eclipse.ptp.remote.core.IRemoteProxyOptions;
import org.eclipse.ptp.remote.core.PTPRemoteCorePlugin;
import org.eclipse.ptp.remote.core.exception.RemoteConnectionException;
import org.eclipse.ptp.remote.remotetools.core.RemoteToolsServices;
import org.eclipse.ptp.remotetools.environment.EnvironmentPlugin;
import org.eclipse.ptp.remotetools.environment.control.ITargetConfig;
import org.eclipse.ptp.remotetools.environment.control.ITargetStatus;
import org.eclipse.ptp.remotetools.environment.core.ITargetElement;
import org.eclipse.ptp.remotetools.environment.core.TargetElement;
import org.eclipse.ptp.remotetools.environment.core.TargetTypeElement;
import org.eclipse.ptp.rm.core.rmsystem.IRemoteResourceManagerConfiguration;
import org.eclipse.ptp.rmsystem.IResourceManager;
import org.eclipse.ptp.rmsystem.IResourceManagerConfiguration;
import org.eclipse.ptp.rmsystem.ResourceManagerServiceProvider;
import org.eclipse.ptp.services.core.IService;
import org.eclipse.ptp.services.core.IServiceConfiguration;
import org.eclipse.ptp.services.core.IServiceProvider;
import org.eclipse.ptp.services.core.IServiceProviderDescriptor;
import org.eclipse.ptp.services.core.ServiceModelManager;

import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.utils.PTPConstants;


final class ResourceManagerHelper {
  
  ResourceManagerHelper(final String name) {
    this.fIsLocal = true;
    this.fName = name;
    this.fHostName = null;
    this.fPort = 0;
    this.fUserName = null;
    this.fIsPasswordBased = false;
    this.fPassword = null;
    this.fPrivateKeyFile = null;
    this.fPassphrase = null;
    this.fTimeout = 0;
    this.fLocalAddress = null;
    this.fUsePortForwarding = false;
  }
  
  ResourceManagerHelper(final String name, final String hostName, final int port, final String userName, 
                         final boolean isPasswordBased, final String password, final String privateKeyFile, 
                         final String passphrase, final String localAddress, final int timeout, 
                         final boolean usePortForwarding) {
    this.fIsLocal = false;
    this.fName = name;
    this.fHostName = hostName;
    this.fPort = port;
    this.fUserName = userName;
    this.fIsPasswordBased = isPasswordBased;
    this.fPassword = isPasswordBased ? password : null;
    this.fPrivateKeyFile = isPasswordBased ? null : privateKeyFile;
    this.fPassphrase = isPasswordBased ? null : passphrase;
    this.fLocalAddress = localAddress;
    this.fTimeout = timeout;
    this.fUsePortForwarding = usePortForwarding;
  }
  
  ResourceManagerHelper(final ILaunchConfiguration configuration) throws CoreException {
    this.fIsLocal = configuration.getAttribute(ATTR_IS_LOCAL, true);
    this.fName = configuration.getName();
    this.fHostName = configuration.getAttribute(ATTR_HOST, Constants.EMPTY_STR);
    this.fPort = configuration.getAttribute(ATTR_PORT, 22);
    this.fUserName = configuration.getAttribute(ATTR_USERNAME, Constants.EMPTY_STR);
    this.fIsPasswordBased = configuration.getAttribute(ATTR_IS_PASSWORD_BASED, true);
    this.fPassword = configuration.getAttribute(ATTR_PASSWORD, (String) null);
    this.fPrivateKeyFile = configuration.getAttribute(ATTR_PRIVATE_KEY_FILE, Constants.EMPTY_STR);
    this.fPassphrase = configuration.getAttribute(ATTR_PASSPHRASE, Constants.EMPTY_STR);
    this.fLocalAddress = configuration.getAttribute(ATTR_LOCAL_ADDRESS, Constants.EMPTY_STR);
    this.fTimeout = configuration.getAttribute(ATTR_TIMEOUT, 0);
    this.fUsePortForwarding = configuration.getAttribute(ATTR_USE_PORT_FORWARDING, false);
  }
  
  // --- Services
  
  public IResourceManager createResourceManager() throws CoreException {
    //final ServiceModelManager serviceModelManager = ServiceModelManager.getInstance();
    //final IService service = serviceModelManager.getService(PTPConstants.LAUNCH_SERVICE_ID);
       
	IService launchService = ServiceModelManager.getInstance().getService(IServiceConstants.LAUNCH_SERVICE);  
	IServiceProviderDescriptor descriptor = launchService.getProviderDescriptor(SERVICE_DESCRIPTOR_ID);
	IServiceProvider provider = ServiceModelManager.getInstance().getServiceProvider(descriptor);
	    
    for (final IServiceConfiguration serviceConfiguration : ServiceModelManager.getInstance().getConfigurations()) {
      if (this.fName.equals(serviceConfiguration.getName())) {
        final IServiceProvider serviceProvider = serviceConfiguration.getServiceProvider(launchService);
        String uniqueName = ((ResourceManagerServiceProvider) serviceProvider).getUniqueName();
        IResourceManager manager = ModelManager.getInstance().getResourceManagerFromUniqueName(uniqueName);
        return manager;
//        serviceProvider.
//        if (serviceProvider instanceof MultiVMResourceManagerConfiguration) {
//          return ((IX10RMConfiguration) serviceProvider).createResourceManager();
//        }
     
      }
    }
	  
	
    
    IResourceManagerConfiguration baseConfiguration = ModelManager.getInstance().createBaseConfiguration(provider); 
    String uniqueID = baseConfiguration.getUniqueName();
    
    final IServiceConfiguration config = ServiceModelManager.getInstance().newServiceConfiguration(this.fName);
    config.setServiceProvider(launchService, provider);
    ServiceModelManager.getInstance().addConfiguration(config);
    
    IResourceManager manager = ModelManager.getInstance().getResourceManagerFromUniqueName(uniqueID);
    manager.getConfiguration().setName(this.fName);
    IRemoteResourceManagerConfiguration rmConf = (IRemoteResourceManagerConfiguration) manager.getControlConfiguration();
    if (this.fIsLocal){
    	rmConf.setConnectionName(IRemoteConnectionManager.DEFAULT_CONNECTION_NAME);
    } else {
    	rmConf.setConnectionName(this.fName);
    }
    //provider.setDescriptor(descriptor);
    //final IX10RMConfiguration rmConf = (IX10RMConfiguration) provider;
    
    //rmConf.setName(this.fName); //MV  - check this
    final String remoteServicesId = this.fIsLocal ? PTPConstants.LOCAL_CONN_SERVICE_ID : PTPConstants.REMOTE_CONN_SERVICE_ID;

    
    if (this.fIsLocal) {
      final PTPRemoteCorePlugin plugin = PTPRemoteCorePlugin.getDefault();
      final IRemoteConnectionManager rmConnManager = plugin.getRemoteServices(LOCAL_CONN_SERVICE_ID).getConnectionManager();
      try {
        rmConnManager.newConnection(this.fName);
      } catch (RemoteConnectionException except) {
        // Can't occur with local connection.
      }
    } else {
      createOrUpdateRemoteConnection();
      
      rmConf.setLocalAddress(this.fLocalAddress);
      if (this.fUsePortForwarding) {
        rmConf.setOptions((rmConf.getOptions() & ~IRemoteProxyOptions.STDIO) | IRemoteProxyOptions.PORT_FORWARDING);
      }
    }
    rmConf.setRemoteServicesId(remoteServicesId);
    
    return manager;
    
//    final ResourceManagerListener listener = new ResourceManagerListener();
//    PTPCorePlugin.getDefault().getModelManager().addListener(listener);
//    
//    
//    
//    while (! listener.hasResourceManager()) ;
//    
//    PTPCorePlugin.getDefault().getModelManager().removeListener(listener);
//    
//    return listener.getResourceManager();
  }
  
  public void createOrUpdateRemoteConnection() throws CoreException {
    final PTPRemoteCorePlugin plugin = PTPRemoteCorePlugin.getDefault();
    final IRemoteConnectionManager rmConnManager = plugin.getRemoteServices(REMOTE_CONN_SERVICE_ID).getConnectionManager();
    final TargetTypeElement targetTypeElement = RemoteToolsServices.getTargetTypeElement();

    ITargetElement targetElement = getDefaultTargetElement(this.fName);
    if (targetElement == null) {
      final String id = EnvironmentPlugin.getDefault().getEnvironmentUniqueID();
      targetElement = new TargetElement(targetTypeElement, this.fName, new HashMap<String, String>(), id);
      targetTypeElement.addElement((TargetElement) targetElement);
    } else {
      if (targetElement.getControl().query() != ITargetStatus.STOPPED) {
        targetElement.getControl().kill();     
      }
    }
    
    updateControlAttributes(targetElement.getControl().getConfig());
    
    rmConnManager.getConnections(); // Side effect of creating connection.
  }
  
  public void deleteResourceManager(final IResourceManager resourceManager) throws CoreException {
    resourceManager.stop();
    final IResourceManagerConfiguration rmConf =  resourceManager.getConfiguration();
    final ServiceModelManager modelManager = ServiceModelManager.getInstance();
    loop:
    for (final IServiceConfiguration serviceConf : modelManager.getConfigurations()) {
      for (final IService service : serviceConf.getServices()) {
        if (PTPConstants.RUNTIME_SERVICE_CATEGORY_ID.equals(service.getCategory().getId())) {
          final IServiceProvider provider = serviceConf.getServiceProvider(service);
          if (rmConf.getUniqueName().equals(provider.getProperties().get("uniqName"))) { //$NON-NLS-1$
            modelManager.remove(serviceConf);
            break loop;
          }
        }
      }
    }
  }
  
  // --- Private code
  
  private ITargetElement getDefaultTargetElement(final String connectionName) {
    final TargetTypeElement targetTypeElement = RemoteToolsServices.getTargetTypeElement();
    for (final ITargetElement targetElement : targetTypeElement.getElements()) {
      if (targetElement.getName().equals(connectionName)) {
        return targetElement;
      }
    }
    return null;
  }
  
  private void updateControlAttributes(final ITargetConfig targetConfig) {
    targetConfig.setConnectionAddress(this.fHostName);
    targetConfig.setLoginUsername(this.fUserName);
    targetConfig.setConnectionPort(this.fPort);
    targetConfig.setPasswordAuth(this.fIsPasswordBased);
    targetConfig.setLoginPassword(this.fPassword);
    targetConfig.setKeyPath(this.fPrivateKeyFile);
    targetConfig.setKeyPassphrase(this.fPassphrase);
    targetConfig.setConnectionTimeout(this.fTimeout);
  }
  
  // --- Private classes
  
  private static final class ResourceManagerListener implements IResourceManagerListener {
    
    // --- Interface methods implementation

    public void handleEvent(final IResourceManagerChangedEvent event) {
    }

    public void handleEvent(final IResourceManagerAddedEvent event) {
      this.fResourceManager = event.getResourceManager();
    }

    public void handleEvent(final IResourceManagerRemovedEvent event) {
    }
    
    public void handleEvent(final IResourceManagerErrorEvent event) {
    }
    
    // --- Internal services
    
    IResourceManager getResourceManager() {
      return this.fResourceManager;
    }
    
    boolean hasResourceManager() {
      return this.fResourceManager != null;
    }
    
    // --- Fields
    
    private IResourceManager fResourceManager;
    
  }
  
  // --- Fields
  
  private final boolean fIsLocal;
  
  private final String fName;
  
  private final String fHostName;
  
  private final int fPort;
  
  private final String fUserName;
  
  private final boolean fIsPasswordBased;
  
  private final String fPassword;
  
  private final String fPrivateKeyFile;
  
  private final String fPassphrase;
  
  private final int fTimeout;
  
  private final String fLocalAddress;
  
  private final boolean fUsePortForwarding;
  
  
  private static final String SERVICE_DESCRIPTOR_ID = "x10dt.ui.launch.java.provider"; //$NON-NLS-1$

}
