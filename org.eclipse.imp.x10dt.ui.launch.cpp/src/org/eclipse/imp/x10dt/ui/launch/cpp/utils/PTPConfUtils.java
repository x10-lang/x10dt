/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package org.eclipse.imp.x10dt.ui.launch.cpp.utils;

import static org.eclipse.imp.x10dt.ui.launch.core.utils.PTPConstants.LOCAL_CONN_SERVICE_ID;
import static org.eclipse.imp.x10dt.ui.launch.core.utils.PTPConstants.REMOTE_CONN_SERVICE_ID;

import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.imp.utils.Pair;
import org.eclipse.imp.x10dt.ui.launch.core.utils.PTPConstants;
import org.eclipse.imp.x10dt.ui.launch.cpp.platform_conf.ICommunicationInterfaceConf;
import org.eclipse.imp.x10dt.ui.launch.cpp.platform_conf.IConnectionConf;
import org.eclipse.imp.x10dt.ui.launch.cpp.platform_conf.IX10PlatformConf;
import org.eclipse.ptp.core.PTPCorePlugin;
import org.eclipse.ptp.core.elementcontrols.IPUniverseControl;
import org.eclipse.ptp.core.elementcontrols.IResourceManagerControl;
import org.eclipse.ptp.core.elements.IResourceManager;
import org.eclipse.ptp.core.events.IChangedResourceManagerEvent;
import org.eclipse.ptp.core.events.INewResourceManagerEvent;
import org.eclipse.ptp.core.events.IRemoveResourceManagerEvent;
import org.eclipse.ptp.core.listeners.IModelManagerChildListener;
import org.eclipse.ptp.remote.core.IRemoteConnection;
import org.eclipse.ptp.remote.core.IRemoteConnectionManager;
import org.eclipse.ptp.remote.core.IRemoteServices;
import org.eclipse.ptp.remote.core.PTPRemoteCorePlugin;
import org.eclipse.ptp.remote.core.exception.RemoteConnectionException;
import org.eclipse.ptp.remote.remotetools.core.RemoteToolsServices;
import org.eclipse.ptp.remotetools.environment.EnvironmentPlugin;
import org.eclipse.ptp.remotetools.environment.core.ITargetElement;
import org.eclipse.ptp.remotetools.environment.core.ITargetEnvironmentEventListener;
import org.eclipse.ptp.remotetools.environment.core.TargetElement;
import org.eclipse.ptp.remotetools.environment.core.TargetEnvironmentManager;
import org.eclipse.ptp.rm.core.rmsystem.IToolRMConfiguration;
import org.eclipse.ptp.rmsystem.IResourceManagerConfiguration;
import org.eclipse.ptp.services.core.IService;
import org.eclipse.ptp.services.core.IServiceConfiguration;
import org.eclipse.ptp.services.core.IServiceProvider;
import org.eclipse.ptp.services.core.IServiceProviderDescriptor;
import org.eclipse.ptp.services.core.ServiceModelManager;
import org.eclipse.ptp.services.core.ServicesCorePlugin;

/**
 * Utility methods for identifying PTP constructs from equivalent X10 Platform Configuration information.
 * 
 * @author egeay
 */
public final class PTPConfUtils {
  
  /**
   * Creates a PTP target element and remote connection given some PTP connection parameters.
   * 
   * @param connectionName The connection name to create.
   * @param attributes The connection attributes as expected by PTP.
   * @return A non-null target element associated to the connection configuration transmitted.
   * @throws RemoteConnectionException Occurs if we failed in creating the remote connection.
   */
  public static ITargetElement createRemoteConnection(final String connectionName, 
                                                      final Map<String, String> attributes) throws RemoteConnectionException {
    final PTPRemoteCorePlugin plugin = PTPRemoteCorePlugin.getDefault();
    final IRemoteConnectionManager rmConnManager = plugin.getRemoteServices(REMOTE_CONN_SERVICE_ID).getConnectionManager();
    final TargetEnvListener listener = new TargetEnvListener();
    fTargetEnvManager.addModelChangedListener(listener);
    try {
      rmConnManager.newConnection(connectionName, attributes);
    } catch (RemoteConnectionException except) {
      if (listener.fTargetElement != null) {
        RemoteToolsServices.getTargetTypeElement().removeElement(listener.fTargetElement);
      }
      throw except;
    } finally {
      fTargetEnvManager.removeModelChangedListener(listener);
    }
    return listener.fTargetElement;
  }
  
  /**
   * Creates a PTP resource manager from the X10 platform configuration transmitted.
   * 
   * @param platformConf The X10 platform configuration to use.
   * @return A non-null resource manager if the creation went smoothly.
   * @throws RemoteConnectionException Occurs if we could not create a new valid remote connection for the resource manager.
   * @throws CoreException Occurs if we could not create the service provider from the plugin extension mechanism.
   */
  public static IResourceManager createResourceManager(final IX10PlatformConf platformConf) throws RemoteConnectionException,
  																																																 CoreException {
    final ICommunicationInterfaceConf commConf = platformConf.getCommunicationInterfaceConf();
    final ServiceModelManager serviceModelManager = ServiceModelManager.getInstance();
    final Pair<IService, IServiceProvider> pair = createProvider(serviceModelManager, commConf);
    if ((pair == null) || (pair.second == null)) {
      return null;
    }
    final IToolRMConfiguration rmConf = (IToolRMConfiguration) pair.second;
    setCommunicationInterfaceParameters(rmConf, commConf);
    final IConnectionConf connConf = platformConf.getConnectionConf();
    if (! connConf.isLocal() && findTargetElement(connConf.getConnectionName()) == null) {
      createRemoteConnection(connConf.getConnectionName(), connConf.getAttributes());
    }
    rmConf.setConnectionName(connConf.getConnectionName());
    rmConf.setName(platformConf.getName());
    rmConf.setRemoteServicesId(connConf.isLocal() ? PTPConstants.LOCAL_CONN_SERVICE_ID : PTPConstants.REMOTE_CONN_SERVICE_ID);
    
    final IServiceConfiguration serviceConf = serviceModelManager.newServiceConfiguration(platformConf.getName());
    serviceConf.setServiceProvider(pair.first, pair.second);
    
    final ResourceManagerListener listener = new ResourceManagerListener();
    PTPCorePlugin.getDefault().getModelManager().addListener(listener);
    
    serviceModelManager.addConfiguration(serviceConf);
    
    while (! listener.hasResourceManager()) ;
    
    PTPCorePlugin.getDefault().getModelManager().removeListener(listener);
    
    return listener.getResourceManager();
  }
  
  /**
   * Returns the communication interface type name from the id encapsulated in the configuration.
   * 
   * @param commInterfaceConf The communication interface configuration to consider.
   * @return A non-null non-empty string.
   */
  public static String getCommunicationInterfaceTypeName(final ICommunicationInterfaceConf commInterfaceConf) {
    final ServiceModelManager serviceModelManager = ServiceModelManager.getInstance();
    for (final IService service : serviceModelManager.getServices()) {
      if (PTPConstants.RUNTIME_SERVICE_CATEGORY_ID.equals(service.getCategory().getId()) &&
          service.getId().equals(commInterfaceConf.getServiceModeId())) {
        for (final IServiceProviderDescriptor providerDescriptor : service.getProviders()) {
          if (providerDescriptor.getId().equals(commInterfaceConf.getServiceTypeId())) {
            return providerDescriptor.getName();
          }
        }
      }
    }
    throw new AssertionError();
  }
  
  /**
   * Finds the resource manager associated with the platform configuration name.
   * 
   * @param platformConfName The platform configuration name to consider.
   * @return A non-null resource manager if we found a match, <b>null</b> otherwise.
   */
  public static IResourceManager findResourceManager(final String platformConfName) {
    final IPUniverseControl universe = (IPUniverseControl) PTPCorePlugin.getDefault().getUniverse();
    
    for (final IResourceManagerControl resourceManager : universe.getResourceManagerControls()) {
      final IResourceManagerConfiguration rmConf = resourceManager.getConfiguration();
      
      if (rmConf.getName().equals(platformConfName)) {
        return resourceManager;
      }
    }
    
    return null;
  }
  
  /**
   * Returns an existing resource manager that is equivalent to information encapsulated in the platform configuration or
   * creates a new one.
   * 
   * <p>Note that in the case or creation, a previous manager that would happen to share the same name will be removed first.
   * 
   * @param platformConf The platform configuration to use.
   * @return A n
   * @throws RemoteConnectionException
   * @throws CoreException Occurs if we could not create the service provider from the plugin extension mechanism.
   */
  public static IResourceManager getResourceManager(final IX10PlatformConf platformConf) throws RemoteConnectionException, 
  																																													    CoreException {
    final IPUniverseControl universe = (IPUniverseControl) PTPCorePlugin.getDefault().getUniverse();
    
    final ICommunicationInterfaceConf communicationInterfaceConf = platformConf.getCommunicationInterfaceConf();
    
    IResourceManager finalRM = null;
    for (final IResourceManagerControl resourceManager : universe.getResourceManagerControls()) {
      final IResourceManagerConfiguration rmConf = resourceManager.getConfiguration();
      
			if (rmConf.getResourceManagerId().equals(communicationInterfaceConf.getServiceTypeId()) &&
					((IServiceProvider) rmConf).getServiceId().equals(communicationInterfaceConf.getServiceModeId()) &&
					(rmConf instanceof IToolRMConfiguration)) {
				final IToolRMConfiguration toolRMConf = (IToolRMConfiguration) rmConf;

				if (communicationInterfaceConf.hasSameCommunicationInterfaceInfo(toolRMConf)) {
					if (platformConf.getConnectionConf().isLocal()) {
						return PTPConstants.LOCAL_CONN_SERVICE_ID.equals(toolRMConf.getRemoteServicesId()) ? resourceManager : null;
					} else if (PTPConstants.REMOTE_CONN_SERVICE_ID.equals(toolRMConf.getRemoteServicesId())) {
						final PTPRemoteCorePlugin plugin = PTPRemoteCorePlugin.getDefault();
						final IRemoteServices remoteServices = plugin.getRemoteServices(PTPConstants.REMOTE_CONN_SERVICE_ID);

						final IRemoteConnection rmConn = remoteServices.getConnectionManager().getConnection(rmConf.getConnectionName());
						if ((rmConn != null) && platformConf.getConnectionConf().hasSameConnectionInfo(rmConn)) {
							finalRM = resourceManager;
							break;
						}
					}
				}
			}
    }
    
    if (finalRM == null) {
    	final IResourceManager sameRMName = findResourceManager(platformConf.getName());
    	if (sameRMName != null) {
    		sameRMName.shutdown();
    		final IResourceManagerConfiguration rmConf = ((IResourceManagerControl) sameRMName).getConfiguration();
    		final ServiceModelManager modelManager = ServiceModelManager.getInstance();
    		loop:
    		for (final IServiceConfiguration serviceConf : modelManager.getConfigurations()) {
    			for (final IService service : serviceConf.getServices()) {
    				if (PTPConstants.RUNTIME_SERVICE_CATEGORY_ID.equals(service.getCategory().getId()) &&
    	          service.getId().equals(platformConf.getCommunicationInterfaceConf().getServiceModeId())) {
    					final IServiceProvider provider = serviceConf.getServiceProvider(service);
    					if (rmConf.getUniqueName().equals(provider.getProperties().get("uniqName"))) { //$NON-NLS-1$
    						modelManager.remove(serviceConf);
    						break loop;
    					}
    				}
    			}
    		}
    	}
    	return createResourceManager(platformConf);
    } else {
    	return finalRM;
    }
  }
  
  /**
   * Finds a PTP remote connection equivalent to the connection configuration transmitted.
   * 
   * @param connectionConf The connection configuration to consider.
   * @return A non-null remote connection instance if one has been found, <b>null</b> otherwise.
   */
  public static IRemoteConnection findRemoteConnection(final IConnectionConf connectionConf) {
    final IPUniverseControl universe = (IPUniverseControl) PTPCorePlugin.getDefault().getUniverse();
    final PTPRemoteCorePlugin plugin = PTPRemoteCorePlugin.getDefault();
    
    final String rmServicesId = connectionConf.isLocal() ? LOCAL_CONN_SERVICE_ID : REMOTE_CONN_SERVICE_ID;
    final IRemoteConnectionManager rmConnManager = plugin.getRemoteServices(rmServicesId).getConnectionManager();
    
    final IRemoteConnection rmConnOnName = rmConnManager.getConnection(connectionConf.getConnectionName());
    if (rmConnOnName != null) {
      if (connectionConf.hasSameConnectionInfo(rmConnOnName)) {
        return rmConnOnName;
      }
    }
      
    for (final IResourceManagerControl resourceManager : universe.getResourceManagerControls()) {
      final IResourceManagerConfiguration rmConf = resourceManager.getConfiguration();
      
      final IRemoteConnection rmConn = rmConnManager.getConnection(rmConf.getConnectionName());
      if ((rmConn != null) && connectionConf.hasSameConnectionInfo(rmConn)) {
        return rmConn;
      }
    }
    
    return null;
  }
  
  /**
   * Looks for a PTP target element matching the name provided.
   * 
   * @param targetName The name to look for.
   * @return The target element if it has been found, otherwise <b>null</b>.
   */
  public static ITargetElement findTargetElement(final String targetName) {
    for (final ITargetElement targetElement : getTargetElements()) {
      if (targetElement.getName().equals(targetName)) {
        return targetElement;
      }
    }
    return null;
  }
  
  /**
   * Returns all the PTP target elements present in memory.
   * 
   * @return A non-null but possibly empty array.
   */
  public static ITargetElement[] getTargetElements() {
    final ITargetElement[] targetElements = (fTargetEnvManager == null) ? null : fTargetEnvManager.getConfigElements();
    return (targetElements == null) ? new ITargetElement[0] : targetElements;
  }
  
  /**
   * Tries to remove the target element matching the given name provided. If there is no matching, then this method is a no-op.
   * 
   * @param targetName The name of the target element to remove.
   */
  public static void removeTargetElement(final String targetName) {
    final ITargetElement targetElement = findTargetElement(targetName);
    if (targetElement != null) {
      RemoteToolsServices.getTargetTypeElement().removeElement(targetElement);
    }
  }
  
  // --- Private classes
  
  private static final class TargetEnvListener implements ITargetEnvironmentEventListener {

    // --- Interface methods implementation
    
    public void elementAdded(final TargetElement element) {
      this.fTargetElement = element;
    }

    public void elementRemoved(final ITargetElement element) {
    }
    
    // --- Fields
    
    ITargetElement fTargetElement;
    
  }
  
  // --- Private code
  
  private PTPConfUtils() {}
  
  private static Pair<IService, IServiceProvider> createProvider(final ServiceModelManager serviceModelManager,
                                                                 final ICommunicationInterfaceConf conf) throws CoreException {
    final IExtensionRegistry extRegistry = Platform.getExtensionRegistry();
    
    for (final IService service : serviceModelManager.getServices()) {
      if (PTPConstants.RUNTIME_SERVICE_CATEGORY_ID.equals(service.getCategory().getId()) &&
          service.getId().equals(conf.getServiceModeId())) {
        final IServiceProviderDescriptor descriptor = service.getProviderDescriptor(conf.getServiceTypeId());
        if (descriptor != null) {
          final IExtensionPoint extensionPoint = extRegistry.getExtensionPoint(ServicesCorePlugin.PLUGIN_ID, 
                                                                               PROVIDER_EXTENSION_ID);
          if (extensionPoint != null) {
            for (IExtension extension : extensionPoint.getExtensions()) {
              for (IConfigurationElement element : extension.getConfigurationElements()) {
                if (element.getName().equals(PROVIDER_ELEMENT_NAME)) {
                  if (element.getAttribute(ATTR_ID).equals(descriptor.getId())) {
                  	final IServiceProvider provider = (IServiceProvider) element.createExecutableExtension(ATTR_CLASS);
                  	provider.setDescriptor(descriptor);                    
                  	return new Pair<IService, IServiceProvider>(service, provider);
                  }
                }
              }
            }
          }
        }
      }
    }
    return null;
  }
  
  private static void setCommunicationInterfaceParameters(final IToolRMConfiguration rmConfiguration, 
                                                          final ICommunicationInterfaceConf commConf) {
    rmConfiguration.setUseToolDefaults(commConf.shouldTakeDefaultToolCommands());
    if (! commConf.shouldTakeDefaultToolCommands()) {
      rmConfiguration.setLaunchCmd(commConf.getLaunchCommand());
      rmConfiguration.setDebugCmd(commConf.getDebugCommand());
      rmConfiguration.setDiscoverCmd(commConf.getDiscoverCommand());
      rmConfiguration.setPeriodicMonitorCmd(commConf.getMonitorCommand());
      rmConfiguration.setPeriodicMonitorTime(commConf.getMonitorPeriod());
    }
    rmConfiguration.setUseInstallDefaults(commConf.shouldTakeDefaultInstallLocation());
    if (! commConf.shouldTakeDefaultInstallLocation()) {
      rmConfiguration.setRemoteInstallPath(commConf.getInstallLocation());
    }
  }
  
  // --- Private classes
  
  private static final class ResourceManagerListener implements IModelManagerChildListener {
  	
  	// --- Interface methods implementation

		public void handleEvent(final IChangedResourceManagerEvent event) {
		}

		public void handleEvent(final INewResourceManagerEvent event) {
			this.fResourceManager = event.getResourceManager();
		}

		public void handleEvent(final IRemoveResourceManagerEvent event) {
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
  
  private static TargetEnvironmentManager fTargetEnvManager = EnvironmentPlugin.getDefault().getTargetsManager();
  
  
  private static final String PROVIDER_EXTENSION_ID = "providers"; //$NON-NLS-1$
  
  private static final String PROVIDER_ELEMENT_NAME = "provider"; //$NON-NLS-1$
  
  private final static String ATTR_ID = "id"; //$NON-NLS-1$
  
  private final static String ATTR_CLASS = "class"; //$NON-NLS-1$

}
