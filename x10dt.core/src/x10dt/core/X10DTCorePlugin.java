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

package x10dt.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.imp.runtime.PluginBase;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import x10dt.core.preferences.generated.X10Constants;

/**
 * The main plugin class to be used in the desktop.
 * 
 * <p>Logging methods, for reference, and what they look like in Error log
 * <br>writeErrorMessage(String) -  "red X" icon
 * <br>writeInfoMessage(String) -  "blue i" icon
 * <br>logException(String, Exception) - Red X over text icon (stack trace avail)
 * <p>Even more flexibility via:
 * <br>getLog().logStatus(new Status(...));
 * 
 */
public class X10DTCorePlugin extends PluginBase {
  
    public static final String kPluginID= "x10dt.core";
    public static final String kLanguageName = "X10";
   
    public static final String X10DT_CONSOLE_NAME = "X10DT info";

    /**
     * The unique instance of this plugin class
     */
    protected static X10DTCorePlugin sPlugin;

    protected static MessageConsole console=null;

    /**
     * Id for the X10DT C++ Project Nature.
     */
    public static final String X10_CPP_PRJ_NATURE_ID = "x10dt.ui.launch.cpp.x10nature"; //$NON-NLS-1$

    /**
     * Id for the X10DT Java Project Nature.
     */
    public static final String X10_PRJ_JAVA_NATURE_ID = "x10dt.ui.launch.java.x10nature"; //$NON-NLS-1$

    public X10DTCorePlugin() {
      super();
      sPlugin= this;
    }
    
    // --- Public services
    
    public static X10DTCorePlugin	 getInstance() {
    	// mmk: Creation if not auto-started adapted from generated preferences Activator
    	if (sPlugin == null)
			new X10DTCorePlugin();
    	return sPlugin;
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
    
    /**
     * Get the current time and date, useful for delineating console output.
     * @return
     */
    public String getTimeAndDate() {
      Calendar cal = Calendar.getInstance();
      String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
      SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
      String time = sdf.format(cal.getTime());
      return time;
    }
    
    /**
     * The keys for the set of preferences that can impact compilation results
     * (semantic errors or generated artifacts) such that a full rebuild would be
     * required on a preference value change.
     */
    private static final Set<String> FULL_BUILD_PREF_KEYS = new HashSet<String>();

    static {
        FULL_BUILD_PREF_KEYS.add(X10Constants.P_STATICCALLS);
        FULL_BUILD_PREF_KEYS.add(X10Constants.P_VERBOSECALLS);
        // The user could in theory put anything into the "additional options" field,
        // so it might affect compilation results.
        FULL_BUILD_PREF_KEYS.add(X10Constants.P_ADDITIONALCOMPILEROPTIONS);
    }

    /**
     * @return the set of preference keys corresponding to compiler options for which
     * a change in value should force a full build. Generally, this will include any
     * options that affect the set of semantic checks performed, and anything that
     * will affect code-generation. However, most code-generation preferences are
     * back-end-dependent; hence those are specified by the back-end plugins.
     */
    public static Set<String> getFullBuildPreferenceKeys() {
        return FULL_BUILD_PREF_KEYS;
    }

    // --- PluginBase's abstract methods implementation
    
    public String getLanguageID() {
      return kLanguageName;
    }

    public String getID() {
      return kPluginID;
    }
    
    // --- Overridden methods

    /**
     * This method is called upon plug-in activation
     */
    public void start(BundleContext context) throws Exception {
      super.start(context);
      
//      updateX10ConfigurationFromPreferences();

      // Set some system properties so that the "Help" -> "About Eclipse" -> "Configuration"
      // page shows what version of the X10 core projects was used to build the X10DT.
      // The values below are formatted so that the X10DT build system can perform a
      // substitution with the real tag/revision values. See the target x10dt-checkouts
      // in the Ant build file x10dt.build/x10dt-build.xml.
      System.setProperty("x10.core.tag", "%%x10.core.tag%%");
      System.setProperty("x10.core.revision", "%%x10.core.revision%%");
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
    
    @Override
    public void refreshPrefs() {
      this.getPreferencesService().getBooleanPreference("msgs?");
    }

    public static void submitProjectsForFullBuild(String natureID) {
      // Sadly, there seems to be no single API call to submit a set of projects for rebuild.
      // At the same time, we don't want to rebuild the entire workspace.
      IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
    
      for(final IProject project: projects) {
        // TODO The following should submit project build requests so as to respect inter-project dependencies.
        try {
          if (project.hasNature(natureID)) {
            final WorkspaceJob job = new WorkspaceJob(Messages.XCP_RebuildProjectJobName) {
              public IStatus runInWorkspace(final IProgressMonitor monitor) throws CoreException {
                project.build(IncrementalProjectBuilder.FULL_BUILD, monitor);
                return Status.OK_STATUS;
              }
            };
            job.setRule(ResourcesPlugin.getWorkspace().getRuleFactory().buildRule());
            job.setPriority(Job.BUILD);
            job.schedule();
          }
        } catch (CoreException e) {
          X10DTCorePlugin.getInstance().logException("Exception encountered while checking project " + project.getName() + " for the X10 C++ back-end nature", e);
        }
      }
    }
    
    // --- Private code
    
//    private void updateX10ConfigurationFromPreferences() {
//      final IPreferencesService prefService = getPreferencesService();
//      // Compiler prefs update
//      Configuration.DEBUG = true;
//      final String additionalOptions = prefService.getStringPreference(X10Constants.P_ADDITIONALCOMPILEROPTIONS);
//      if ((additionalOptions != null) && (additionalOptions.length() > 0)) {
//        // First initialize to default values.
//        Configuration.CHECK_INVARIANTS = false;
//        Configuration.ONLY_TYPE_CHECKING = false;
//        Configuration.NO_CHECKS = false;
//        Configuration.FLATTEN_EXPRESSIONS = false;
//        Configuration.WALA = false;
//        Configuration.FINISH_ASYNCS = false;
//        for (final String opt : additionalOptions.split("\\s")) { ////$NON-NLS-1$
//          try {
//            Configuration.parseArgument(opt);
//          } catch (OptionError except) {
//            logException(NLS.bind("Could not recognize or set option ''{0}''.", opt), except);
//          } catch (ConfigurationError except) {
//            logException(NLS.bind("Could not initialize option ''{0}''.", opt), except);
//          }
//        }
//      }
//      // Optimization prefs update
//      Configuration.STATIC_CALLS = prefService.getBooleanPreference(X10Constants.P_STATICCALLS);
//      Configuration.VERBOSE_CALLS = prefService.getBooleanPreference(X10Constants.P_VERBOSECALLS);
//      Configuration.OPTIMIZE = prefService.getBooleanPreference(X10Constants.P_OPTIMIZE);
//    }

}
