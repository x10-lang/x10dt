package x10dt.core.preferences.generated;

import org.eclipse.swt.widgets.TabFolder;import org.eclipse.imp.preferences.IPreferencesService;import org.eclipse.imp.preferences.PreferencesInitializer;import org.eclipse.imp.preferences.PreferencesTab;import org.eclipse.imp.preferences.TabbedPreferencesPage;import x10dt.core.X10DTCorePlugin;

/**
 * A preference page class.
 */


public class X10CompilerPreferencePage extends TabbedPreferencesPage {
	public X10CompilerPreferencePage() {
		super();
		prefService = X10DTCorePlugin.getInstance().getPreferencesService();
	}

	protected PreferencesTab[] createTabs(IPreferencesService prefService,
		TabbedPreferencesPage page, TabFolder tabFolder) {
		PreferencesTab[] tabs = new PreferencesTab[1];

		X10CompilerInstanceTab instanceTab = new X10CompilerInstanceTab(prefService);
		instanceTab.createTabContents(page, tabFolder);
		tabs[0] = instanceTab;

		return tabs;
	}

	public PreferencesInitializer getPreferenceInitializer() {
		return new X10Initializer();
	}
}
