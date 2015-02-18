package x10dt.ui.builder.java;

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
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import x10dt.core.X10DTCorePlugin;


/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin implements ILaunchConfigurationListener, IPreferenceChangeListener {

	/**
	 * The unique plugin id for <b>x10dt.ui.launch.java</b> project.
	 */
	public static final String PLUGIN_ID = "x10dt.ui.builder.java"; //$NON-NLS-1$

	/**
	 * Unique id for the Java Builder.
	 */
	public static final String BUILDER_ID = PLUGIN_ID + ".X10JavaBuilder"; //$NON-NLS-1$
	
	
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

