package org.eclipse.imp.x10dt.core.preferences;


import org.eclipse.imp.preferences.ISafariPreferencesService;
import org.eclipse.imp.preferences.SafariPreferencesTab;
import org.eclipse.imp.preferences.SafariTabbedPreferencesPage;
import org.eclipse.imp.x10dt.core.X10Plugin;
import org.eclipse.swt.widgets.TabFolder;

/**
 * The Safari-based tabbed preferences page for language X10.
 * 
 * Naming conventions:  This template uses the language name as a prefix
 * for naming the language plugin class and the preference-tab classes.
 * 	
 */
public class X10TabbedPreferencesPage extends SafariTabbedPreferencesPage {
	
	public X10TabbedPreferencesPage() {
		super();
		// Get the language-specific preferences service
		prefService = X10Plugin.getPreferencesService();
	}
	
	
	protected SafariPreferencesTab[] createTabs(
			ISafariPreferencesService prefService, SafariTabbedPreferencesPage page, TabFolder tabFolder) 
	{
		SafariPreferencesTab[] tabs = new SafariPreferencesTab[4];
		
		X10ProjectPreferencesTab projectTab = new X10ProjectPreferencesTab(prefService);
		projectTab.createProjectPreferencesTab(page, tabFolder);
		tabs[0] = projectTab;

		X10InstancePreferencesTab instanceTab = new X10InstancePreferencesTab(prefService);
		instanceTab.createInstancePreferencesTab(page, tabFolder);
		tabs[1] = instanceTab;
		
		X10ConfigurationPreferencesTab configurationTab = new X10ConfigurationPreferencesTab(prefService);
		configurationTab.createConfigurationPreferencesTab(page, tabFolder);
		tabs[2] = configurationTab;

		X10DefaultPreferencesTab defaultTab = new X10DefaultPreferencesTab(prefService);
		defaultTab.createDefaultPreferencesTab(page, tabFolder);
		tabs[3] = defaultTab;
		
		return tabs;
	}

}
