/*******************************************************************************
* Copyright (c) 2008 IBM Corporation.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    Robert Fuhrer (rfuhrer@watson.ibm.com) - initial API and implementation

*******************************************************************************/

package org.eclipse.imp.x10dt.core.preferences.generated;

import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.imp.preferences.IPreferencesService;
import org.eclipse.imp.preferences.PreferencesInitializer;
import org.eclipse.imp.preferences.PreferencesTab;
import org.eclipse.imp.preferences.TabbedPreferencesPage;
import org.eclipse.imp.x10dt.core.X10Plugin;

/**
 * The IMP-based tabbed preferences page for language X10Preferences.
 * 
 * Naming conventions:  This template uses the language name as a prefix
 * for naming the language plugin class and the preference-tab classes.
 * 	
 */
public class X10Preferences extends TabbedPreferencesPage {

	public X10Preferences() {
		super();
		// Get the language-specific preferences service
		// SMS 28 Mar 2007:  parameterized full name of plugin class
		prefService = X10Plugin.getPreferencesService();
	}

	protected PreferencesTab[] createTabs(IPreferencesService prefService,
			TabbedPreferencesPage page, TabFolder tabFolder) {
		PreferencesTab[] tabs = new PreferencesTab[1];

//		X10PreferencesProjectTab projectTab = new X10PreferencesProjectTab(
//				prefService);
//		projectTab.createProjectPreferencesTab(page, tabFolder);
//		tabs[0] = projectTab;

		X10PreferencesInstanceTab instanceTab = new X10PreferencesInstanceTab(
				prefService);
		instanceTab.createInstancePreferencesTab(page, tabFolder);
		tabs[0] = instanceTab;

//		X10PreferencesConfigurationTab configurationTab = new X10PreferencesConfigurationTab(
//				prefService);
//		configurationTab.createConfigurationPreferencesTab(page, tabFolder);
//		tabs[2] = configurationTab;
//
//		X10PreferencesDefaultTab defaultTab = new X10PreferencesDefaultTab(
//				prefService);
//		defaultTab.createDefaultPreferencesTab(page, tabFolder);
//		tabs[3] = defaultTab;

		return tabs;
	}

	public PreferencesInitializer getPreferenceInitializer() {
		return new X10PreferencesInitializer();
	}

}
