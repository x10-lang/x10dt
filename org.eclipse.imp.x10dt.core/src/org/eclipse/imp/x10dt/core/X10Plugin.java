package com.ibm.watson.safari.x10;

import java.io.File;
import java.net.URL;

import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.uide.runtime.SAFARIPluginBase;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import com.ibm.watson.safari.x10.preferences.PreferenceConstants;
import com.ibm.watson.safari.x10.preferences.X10Preferences;

/**
 * The main plugin class to be used in the desktop.
 */
public class X10Plugin extends SAFARIPluginBase {
    public static final String kPluginID= "com.ibm.watson.safari.x10";

    /**
     * The unique instance of this plugin class
     */
    protected static X10Plugin sPlugin;

    public static String x10CompilerPath;

    public static X10Plugin getInstance() {
        return sPlugin;
    }

    public X10Plugin() {
	sPlugin= this;
    }

    public String getID() {
	return kPluginID;
    }

    /**
     * This method is called upon plug-in activation
     */
    public void start(BundleContext context) throws Exception {
	super.start(context);

	// Initialize the X10Preferences fields with the preference store data.
	IPreferenceStore prefStore= getPreferenceStore();

	Bundle x10CompilerBundle= Platform.getBundle("x10.compiler");
	URL x10CompilerURL= Platform.asLocalURL(Platform.find(x10CompilerBundle, new Path("")));

	x10CompilerPath= x10CompilerURL.getPath();

	X10Preferences.builderEmitMessages= prefStore.getBoolean(PreferenceConstants.P_EMIT_MESSAGES);
//	X10Preferences.autoAddRuntime= prefStore.getBoolean(PreferenceConstants.P_AUTO_ADD_RUNTIME);
	X10Preferences.x10ConfigName= prefStore.getString(PreferenceConstants.P_X10CONFIG_NAME);
	X10Preferences.x10ConfigFile= prefStore.getString(PreferenceConstants.P_X10CONFIG_FILE);
	fEmitInfoMessages= X10Preferences.builderEmitMessages;
    }

    /**
     * This method is called when the plug-in is stopped
     */
    public void stop(BundleContext context) throws Exception {
	super.stop(context);
	// For some reason, X10Builder.build() gets called with an AUTO build
	// after stop() gets called, and it tries to use the plugin instance
	// to get at the log... resulting in an NPE. So don't null it out.
//	sPlugin= null;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in relative path.
     * 
     * @param path the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path) {
	return AbstractUIPlugin.imageDescriptorFromPlugin(kPluginID, path);
    }

    public void refreshPrefs() {
        super.refreshPrefs();
        fEmitInfoMessages= X10Preferences.builderEmitMessages;
        // Set the "x10.configuration" System property from the corresponding preference
        // value, since polyglot.ext.x10.Configuration relies on its being set, and a
        // static initializer there throws an exception if it can't find the config file.
        final String configFile= (X10Preferences.x10ConfigFile!= null ? X10Preferences.x10ConfigFile : x10CompilerPath + File.separator + "etc" + File.separator + "standard.cfg");

        System.setProperty("x10.configuration", configFile);
    }
}
