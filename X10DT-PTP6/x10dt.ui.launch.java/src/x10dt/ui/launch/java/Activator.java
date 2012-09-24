package x10dt.ui.launch.java;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationListener;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.imp.preferences.IPreferencesService;
import org.eclipse.ptp.core.PTPCorePlugin;
import org.eclipse.ptp.core.elements.IPResourceManager;
import org.eclipse.ptp.core.elements.IPUniverse;
import org.eclipse.ptp.rmsystem.IResourceManager;
import org.eclipse.ptp.rmsystem.IResourceManagerControl;
import org.eclipse.ptp.launch.PTPLaunchPlugin;
import org.eclipse.ptp.remote.core.IRemoteConnection;
import org.eclipse.ptp.remote.core.IRemoteConnectionManager;
import org.eclipse.ptp.remote.core.IRemoteServices;
import org.eclipse.ptp.remote.core.PTPRemoteCorePlugin;
import org.eclipse.ptp.remote.core.exception.RemoteConnectionException;
import org.eclipse.ptp.remote.remotetools.core.RemoteToolsServices;
import org.eclipse.ptp.remotetools.environment.core.ITargetElement;
import org.eclipse.ptp.remotetools.environment.core.TargetTypeElement;
import org.eclipse.ptp.rmsystem.IResourceManagerConfiguration;
import org.eclipse.ptp.services.core.IService;
import org.eclipse.ptp.services.core.IServiceConfiguration;
import org.eclipse.ptp.services.core.IServiceProvider;
import org.eclipse.ptp.services.core.ServiceModelManager;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import x10dt.core.X10DTCorePlugin;
import x10dt.ui.launch.core.utils.PTPConstants;
import x10dt.ui.launch.java.launching.rms.MultiVMResourceManagerConfiguration;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin implements ILaunchConfigurationListener, IPreferenceChangeListener {

	/**
	 * The unique plugin id for <b>x10dt.ui.launch.java</b> project.
	 */
	public static final String PLUGIN_ID = "x10dt.ui.launch.java"; //$NON-NLS-1$

	/**
	 * Unique id for the Java Builder.
	 */
	public static final String BUILDER_ID = PLUGIN_ID + ".X10JavaBuilder"; //$NON-NLS-1$
	
	/**
	 * Launch configuration type for Java back-end.
	 */
	public static final String LAUNCH_CONF_TYPE = "x10dt.ui.launch.java.launching.X10LaunchConfigurationType"; //$NON-NLS-1$
	
	// --- Public services
	
  /**
   * Returns the context associated with the bundle for <b>x10dt.ui.launch.java</b> plugin.
   * 
   * @return A non-null value if the plugin is started, otherwise <b>null</b>.
   */
  public static Activator getDefault() {
    return plugin;
  }
  
  // --- ILaunchConfigurationListener's interface methods implementation
  
  public void launchConfigurationAdded(final ILaunchConfiguration configuration) {
  }

  public void launchConfigurationChanged(final ILaunchConfiguration configuration) {
  }

  public void launchConfigurationRemoved(final ILaunchConfiguration configuration) {
    try {
      final ServiceModelManager serviceModelManager = ServiceModelManager.getInstance();
      final IService service = serviceModelManager.getService(PTPConstants.LAUNCH_SERVICE_ID);
      for (final IServiceConfiguration serviceConfiguration : serviceModelManager.getConfigurations()) {
        if (configuration.getName().equals(serviceConfiguration.getName())) {
          final IServiceProvider serviceProvider = serviceConfiguration.getServiceProvider(service);
          //if (serviceProvider instanceof MultiVMResourceManagerConfiguration) {  //MV - Check this
            final IPUniverse universe = (IPUniverse) PTPCorePlugin.getDefault().getModelManager().getUniverse();
            for (final IPResourceManager pResourceManager : universe.getResourceManagers()) {
              final IResourceManager resourceManager = (IResourceManager) pResourceManager.getAdapter(IResourceManager.class);	
              final IResourceManagerConfiguration rmConf = resourceManager.getConfiguration();
              if (rmConf.getUniqueName().equals(serviceProvider.getProperties().get("uniqName"))) { //$NON-NLS-1$
                resourceManager.stop();
                final PTPRemoteCorePlugin rmPlugin = PTPRemoteCorePlugin.getDefault();
                final IRemoteServices rmServices = rmPlugin.getRemoteServices(resourceManager.getControlConfiguration().getRemoteServicesId()); 
                final IRemoteConnectionManager rmConnManager = rmServices.getConnectionManager();
                final IRemoteConnection rmConnection = rmConnManager.getConnection(resourceManager.getControlConfiguration().getConnectionName()); 
                rmConnManager.removeConnection(rmConnection);
                final TargetTypeElement targetTypeElement = RemoteToolsServices.getTargetTypeElement();
                for (final ITargetElement targetElement : targetTypeElement.getElements()) {
                  if (targetElement.getName().equals(resourceManager.getControlConfiguration().getConnectionName())) { 
                    targetTypeElement.removeElement(targetElement);
                    break;
                  }
                }
              
                serviceModelManager.remove(serviceConfiguration);
                return;
              }
            }
          //}
        }
      }
    } catch (CoreException except) {
      getLog().log(except.getStatus());
    } catch (RemoteConnectionException except) {
        //TODO
    	System.err.println(except);
      }
  }
	
  /**
   * The keys for the set of preferences that can impact compilation results
   * (semantic errors or generated artifacts) such that a full rebuild would be
   * required on a preference value change.
   */
  private final static Set<String> FULL_BUILD_PREF_KEYS = new HashSet<String>();

  static {
    // At present, there are no additional preference keys that affect Java back-end
    // code gen, so just listen for changes to the base set of keys.
    FULL_BUILD_PREF_KEYS.addAll(X10DTCorePlugin.getFullBuildPreferenceKeys());
  }

  public void preferenceChange(final PreferenceChangeEvent event) {
    if (FULL_BUILD_PREF_KEYS.contains(event.getKey())) {
      X10DTCorePlugin.submitProjectsForFullBuild(X10DTCorePlugin.X10_PRJ_JAVA_NATURE_ID);
    }
  }

	// --- Overridden methods

	public void start(final BundleContext context) throws Exception {
		super.start(context);
		final ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
		manager.addLaunchConfigurationListener(this);
		// Let's activate magically the PTP core plugin to avoid some late ClassCircularityError !?
		PTPLaunchPlugin.getDefault();
		plugin = this;
	    X10DTCorePlugin.getInstance().getPreferencesService().getPreferences(IPreferencesService.INSTANCE_LEVEL)
                                                                              .addPreferenceChangeListener(this);
	}

	public void stop(final BundleContext context) throws Exception {
	    X10DTCorePlugin.getInstance().getPreferencesService().getPreferences(IPreferencesService.INSTANCE_LEVEL)
                                                                           .removePreferenceChangeListener(this);
		plugin = null;
		super.stop(context);
	}
	
	// --- Fields
	
  private static Activator plugin;

}
