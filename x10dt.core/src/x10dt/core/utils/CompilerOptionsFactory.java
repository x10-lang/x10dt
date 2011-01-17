package x10dt.core.utils;

import org.eclipse.core.resources.IProject;
import org.eclipse.imp.preferences.IPreferencesService;
import org.eclipse.imp.preferences.PreferencesService;
import org.eclipse.osgi.util.NLS;

import x10.X10CompilerOptions;
import x10.config.ConfigurationError;
import x10.config.OptionError;
import x10dt.core.X10DTCorePlugin;
import x10dt.core.preferences.generated.X10Constants;

public class CompilerOptionsFactory {
    public static X10CompilerOptions createOptions(IProject project) {
        // BUG Need a non-null extInfo if one wants to parse a "-version" arg... but this should probably be changed in the Options API
        final X10CompilerOptions options = new X10CompilerOptions(null);
        final IPreferencesService prefService = new PreferencesService(project, X10DTCorePlugin.kLanguageName);

        options.x10_config.DEBUG = true;

        final String additionalOptions = prefService.getStringPreference(X10Constants.P_ADDITIONALCOMPILEROPTIONS);

        if ((additionalOptions != null) && (additionalOptions.length() > 0)) {
            // First initialize to default values.
            options.x10_config.CHECK_INVARIANTS = false;
            options.x10_config.ONLY_TYPE_CHECKING = false;
            options.x10_config.NO_CHECKS = false;
            options.x10_config.FLATTEN_EXPRESSIONS = false;
            options.x10_config.WALA = false;
            options.x10_config.FINISH_ASYNCS = false;
            for (final String opt : additionalOptions.split("\\s")) { ////$NON-NLS-1$
                try {
                    options.x10_config.parseArgument(opt);
                } catch (OptionError except) {
                    X10DTCorePlugin.getInstance().logException(NLS.bind("Could not recognize or set option ''{0}''.", opt), except);
                } catch (ConfigurationError except) {
                    X10DTCorePlugin.getInstance().logException(NLS.bind("Could not initialize option ''{0}''.", opt), except);
                }
            }
        }
        // Optimization prefs update
        options.x10_config.STATIC_CALLS = prefService.getBooleanPreference(X10Constants.P_STATICCALLS);
        options.x10_config.VERBOSE_CALLS = prefService.getBooleanPreference(X10Constants.P_VERBOSECALLS);
        options.x10_config.OPTIMIZE = prefService.getBooleanPreference(X10Constants.P_OPTIMIZE);

        return options;
    }
}
