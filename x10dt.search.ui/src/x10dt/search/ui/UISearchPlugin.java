package x10dt.search.ui;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import x10dt.search.ui.typeHierarchy.TypeFilter;

/**
 * The activator class controls the plug-in life cycle
 */
public class UISearchPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "x10dt.search.ui";

	// The shared instance
	private static UISearchPlugin plugin;

	private static URL fgIconBaseURL = null;

	public static final String OPENTYPE_NAME = "opentype.gif";
	public static ImageDescriptor OPENTYPE_DESC;

	private TypeFilter fTypeFilter;

	/**
	 * The constructor
	 */
	public UISearchPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		if (fTypeFilter != null) {
			fTypeFilter.dispose();
			fTypeFilter = null;
		}
		
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static UISearchPlugin getDefault() {
		return plugin;
	}

	public static void log(Exception e) {
		getDefault().getLog().log(
				new Status(IStatus.ERROR, PLUGIN_ID, 0, e.getMessage(), e));
	}

	private void initImageRegistry() {
		fgIconBaseURL = getBundle().getEntry("/icons/"); //$NON-NLS-1$

		OPENTYPE_DESC = create(OPENTYPE_NAME);
		getDefault().getImageRegistry().put(OPENTYPE_NAME, OPENTYPE_DESC);
	}

	private static URL makeIconFileURL(String name)
			throws MalformedURLException {
		if (fgIconBaseURL == null)
			plugin.initImageRegistry();

		return new URL(fgIconBaseURL, name);
	}

	public static ImageDescriptor create(String name) {
		try {
			return ImageDescriptor.createFromURL(makeIconFileURL(name));
		} catch (MalformedURLException e) {
			return ImageDescriptor.getMissingImageDescriptor();
		}
	}

	public synchronized TypeFilter getTypeFilter() {
		if (fTypeFilter == null)
			fTypeFilter = new TypeFilter();
		return fTypeFilter;
	}

}
