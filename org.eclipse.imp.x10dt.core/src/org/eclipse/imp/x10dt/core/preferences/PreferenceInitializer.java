package com.ibm.watson.safari.x10.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import com.ibm.watson.safari.x10.X10Plugin;

/**
 * Initializes X10 preference default values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {
    /*
     * (non-Javadoc)
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
     */
    public void initializeDefaultPreferences() {
	IPreferenceStore store= X10Plugin.getInstance().getPreferenceStore();

	store.setDefault(PreferenceConstants.P_EMIT_MESSAGES, true);
	// Disabled "auto-add runtime", since that causes subsequent Java compiles to
	// fail, because then x10.runtime won't be in the Java build-time classpath.
	store.setDefault(PreferenceConstants.P_AUTO_ADD_RUNTIME, false);

	store.setDefault(PreferenceConstants.P_X10CONFIG_NAME, "standard");
	store.setDefault(PreferenceConstants.P_X10CONFIG_FILE, "etc/standard.cfg");

        store.setDefault(PreferenceConstants.P_SAMPLING_FREQ, 50);
        store.setDefault(PreferenceConstants.P_STATS_DISABLE, "none");
    }
}
