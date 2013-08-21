package x10dt.ui.launch.java;

import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationListener;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.ptp.launch.PTPLaunchPlugin;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin implements ILaunchConfigurationListener {

	/**
	 * The unique plugin id for <b>x10dt.ui.launch.java</b> project.
	 */
	public static final String PLUGIN_ID = "x10dt.ui.launch.java"; //$NON-NLS-1$

	
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

  }
	
 
	// --- Overridden methods

	public void start(final BundleContext context) throws Exception {
		super.start(context);
		final ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
		manager.addLaunchConfigurationListener(this);
		// Let's activate magically the PTP core plugin to avoid some late ClassCircularityError !?
		PTPLaunchPlugin.getDefault();
		plugin = this;
	}

	public void stop(final BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}
	
	// --- Fields
	
  private static Activator plugin;

}
