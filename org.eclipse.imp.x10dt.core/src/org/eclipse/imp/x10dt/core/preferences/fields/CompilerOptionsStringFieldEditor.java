package org.eclipse.imp.x10dt.core.preferences.fields;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.imp.preferences.IPreferencesService;
import org.eclipse.imp.preferences.PreferencesTab;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.widgets.Composite;

import polyglot.main.Main;
import polyglot.main.UsageError;
//import x10.runtime.util.OptionsError;
import x10.config.ConfigurationError;
import x10.config.OptionError;

public class CompilerOptionsStringFieldEditor extends StringFieldEditor {

	public CompilerOptionsStringFieldEditor(PreferencePage page,
			PreferencesTab tab, IPreferencesService service, String level,
			String name, String labelText, Composite parent) {
		super(page, tab, service, level, name, labelText, parent);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected boolean doCheckState() {
		String argString = "";
		argString += getStringValue();
		argString += " ."; // need to specify at
		// least one file
		Set sources = new HashSet();
		sources.add(".");

		String[] args = argString.split("\\s+");
		polyglot.ext.x10.X10CompilerOptions options = new polyglot.ext.x10.X10CompilerOptions(null); // Doesn't actually need an ExtensionInfo unless you ask about the version current directory
		try {
			for (int i=0; i<args.length; i++) {
				try {
					options.checkCommand(args, i, sources);
				} catch (Main.TerminationException e) {
					// Don't stop processing after -version
				}
			}
		} catch (OptionError e) {
			String msg = e.getMessage();
			// Inform the prefs UI component about the validation failure
			setErrorMessage(e.getMessage());
			return false;
		} catch (UsageError e) {
		} catch (ConfigurationError e) {
		} 
		return super.doCheckState();
	}
}
