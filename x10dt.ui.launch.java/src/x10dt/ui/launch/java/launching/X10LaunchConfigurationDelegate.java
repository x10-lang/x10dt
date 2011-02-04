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

package x10dt.ui.launch.java.launching;

import java.io.File;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.variables.VariablesPlugin;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.IStatusHandler;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.imp.language.Language;
import org.eclipse.imp.language.LanguageRegistry;
import org.eclipse.imp.model.IPathEntry;
import org.eclipse.imp.model.IPathEntry.PathEntryType;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.model.ModelFactory.ModelException;
import org.eclipse.imp.preferences.IPreferencesService;
import org.eclipse.imp.utils.BuildPathUtils;
import org.eclipse.jdt.internal.launching.LaunchingMessages;
import org.eclipse.jdt.internal.launching.LaunchingPlugin;
import org.eclipse.jdt.launching.AbstractJavaLaunchConfigurationDelegate;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.IVMRunner;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.VMRunnerConfiguration;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;

import x10dt.core.X10DTCorePlugin;
import x10dt.core.preferences.generated.X10Constants;
import x10dt.core.utils.X10BundleUtils;
import x10dt.ui.X10DTUIPlugin;

/**
 * TODO need to reconcile changes made to X10LaunchConfigurationDelegate in x10dt.ui for X10 1.7
 * 
 * @author beth
 *
 */
public class X10LaunchConfigurationDelegate extends AbstractJavaLaunchConfigurationDelegate {
	/** Suffix that is appended to user class to get main x10 executable class - e.g. to make Hello$Main */
	static final String USER_MAIN_SUFFIX="$$Main";

	private IProject[] fOrderedProjects;
	
	public IVMInstall verifyVMInstall(ILaunchConfiguration configuration) {
		return JavaRuntime.getDefaultVMInstall();
	}
	
	protected File getDefaultWorkingDirectory(ILaunchConfiguration configuration) throws CoreException {
		// default working directory is the project if this config has a project
		ISourceProject jp = getJavaProjectFromConfig(configuration);
		if (jp != null) {
			IProject p = jp.getRawProject();
			return p.getLocation().toFile();
		}
		return null;
	}
	
	public Map getVMSpecificAttributesMap(ILaunchConfiguration configuration)
	throws CoreException {
		return null;
	}
	
	public String[] getClasspath(ILaunchConfiguration configuration)
	throws CoreException {
		Language lang = LanguageRegistry.findLanguage("X10");
		ISourceProject jp = getJavaProjectFromConfig(configuration);
		List<String> userEntries = new ArrayList<String>(10);
		try {
			for(IPathEntry entry :  jp.getResolvedBuildPath(lang, true))
			{
				if(entry.getEntryType() != PathEntryType.SOURCE_FOLDER)
				{
					userEntries.add(entry.getRawPath().toString());
				}
			}
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IPath path = jp.getOutputLocation(lang);
		userEntries.add(jp.getRawProject().getLocation().append(path.removeFirstSegments(1)).toString());
		return (String[]) userEntries.toArray(new String[userEntries.size()]);
	}
	
	public String[] getBootpath(ILaunchConfiguration configuration)
	throws CoreException {
		return null;
	}

    /**
	 * Returns the X10 runtime arguments specified by the given launch
	 * configuration, as a string. The returned string is empty if no program
	 * arguments are specified.
	 * 
	 * @param configuration
	 *            launch configuration
	 * @return the runtime arguments specified by the given launch
	 *         configuration, possibly an empty string
	 * @exception CoreException
	 *                if unable to retrieve the attribute
	 */
    public String getRuntimeArguments(ILaunchConfiguration configuration) throws CoreException {
		String arguments = configuration.getAttribute(X10LaunchConfigAttributes.X10RuntimeArgumentsID, ""); //$NON-NLS-1$
//		IPreferenceStore prefStore = RuntimePlugin.getInstance().getPreferenceStore();
//		if (prefStore.contains(X10Constants.P_NUMBEROFPLACES)) {
//			String numPlacesArg = " -NUMBER_OF_LOCAL_PLACES=" + prefStore.getInt(X10Constants.P_NUMBEROFPLACES);
//			arguments += numPlacesArg;
//		}
		

	return VariablesPlugin.getDefault().getStringVariableManager().performStringSubstitution(arguments);
    }
    
    @Override
    public String getVMArguments(ILaunchConfiguration configuration) throws CoreException {
    	String arguments = super.getVMArguments(configuration);
    	IPreferencesService prefService = X10DTCorePlugin.getInstance().getPreferencesService();
    	if (prefService.isDefined(X10Constants.P_NUMBEROFPLACES)){
    		String numPlacesArg = "-Dx10.NUMBER_OF_LOCAL_PLACES="+ prefService.getIntPreference(X10Constants.P_NUMBEROFPLACES);
			arguments += numPlacesArg;
    	}
    	return arguments;
	}

    public void launch(final ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor) throws CoreException {
        final boolean[] projProbs= { false };
    	final IWorkbench workbench = X10DTUIPlugin.getInstance().getWorkbench();
    	workbench.getDisplay().syncExec(new Runnable() {
			public void run() {
				try {
					final Shell parent = workbench.getActiveWorkbenchWindow().getShell();
					String projectName;
					projectName = configuration.getAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME,"");
					IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
					IProject project = wsRoot.getProject(projectName);

					if (project.findMaxProblemSeverity(X10DTCorePlugin.kPluginID + ".problemMarker", true,
							IResource.DEPTH_INFINITE) == IMarker.SEVERITY_ERROR) {
						// stop launch because there are errors in the project.
						MessageDialog.openError(parent, "Launch aborted",
										"Cannot launch application because there are errors in the project.");
						projProbs[0]= true;
					}
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
    	});
    	if (projProbs[0])
    	    return;
   
    	boolean debug= mode.equals(ILaunchManager.DEBUG_MODE);

		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}

		monitor.beginTask(MessageFormat.format("{0}...", new Object[] { configuration.getName() }), 3); //$NON-NLS-1$
		// check for cancellation
		if (monitor.isCanceled()) {
			return;
		}

		monitor.subTask("Verifying launch attributes");

		String mainTypeName = verifyMainTypeName(configuration);
//		if (debug) System.out.println("mainTypeName: "+mainTypeName);
		IVMRunner runner = getVMRunner(configuration, mode);

		File workingDir = verifyWorkingDirectory(configuration);
		String workingDirName = null;
		if (workingDir != null) {
			workingDirName = workingDir.getAbsolutePath();
		}

		// Environment variables
		String[] envp = getEnvironment(configuration);

		// Program & VM args
		String pgmArgs = getProgramArguments(configuration);
		String vmArgs = getVMArguments(configuration);
		String rtArgs = getRuntimeArguments(configuration);
		X10ExecutionArguments execArgs = new X10ExecutionArguments(vmArgs, rtArgs, pgmArgs);

		// VM-specific attributes
		Map vmAttributesMap = getVMSpecificAttributesMap(configuration);

		// Classpath
		String[] classpath = getClasspath(configuration);

		// Place the user-specified X10 runtime location in front of whatever we
		// obtain from the project's classpath.
		String x10RuntimeLoc = configuration.getAttribute(X10LaunchConfigAttributes.X10RuntimeAttributeID, "");

		// BRT assure we have a runtime
		// validators for launch config page shd ensure that this field is not
		// empty. can't dismiss dialog if it's empty
		if (x10RuntimeLoc.length() == 0) {
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					Shell shell = X10DTUIPlugin.getInstance().getWorkbench().getActiveWorkbenchWindow().getShell();
					MessageDialog.openError(shell, "Please specify the X10 Runtime location",
							"The location of the X10 Runtime is unset in the launch configuration '" + configuration.getName() + "'.");
				}
			});
			return;
		}

		// ======== start new classpath calculation
		List<String> classpathList = new ArrayList<String>();
		for (int i = 0; i < classpath.length; i++) {
			classpathList.add(classpath[i]);
		}

		// PORT1.7 -- these are not used but when we correctly calculate where
		// all the jars should be, we probably will
		// String locPrefix=x10RuntimeLoc.substring(0,
		// x10RuntimeLoc.lastIndexOf(File.separator)) + File.separator ;
		// String commonLoc= locPrefix+ X10Plugin.X10_COMMON_BUNDLE_ID;
		// String constraintsLoc=locPrefix +
		// X10Plugin.X10_CONSTRAINTS_BUNDLE_ID;

		// PORT1.7 -- common and constraints jars also need to be added to classpath

		if (X10BundleUtils.isDeployedX10Runtime(x10RuntimeLoc)) {
		  try {
		    final URL commonJarURL = X10BundleUtils.getX10CommonURL();
		    final URL constraintsJarURL = X10BundleUtils.getX10ConstraintsURL();
		
		    classpathList.add(commonJarURL.getPath());
		    classpathList.add(constraintsJarURL.getPath());
		  } catch (CoreException except) {
		    X10DTUIPlugin.getInstance().getLog().log(new Status(IStatus.ERROR, X10DTUIPlugin.PLUGIN_ID,
		                                                        "Unable to access X10 bundles", except));
		  }
		}
		// PORT1.7 -- we might also want a third jar, the pre-built X10 classes

		// test that each exists (be paranoid)
		for (int i = 0; i < classpathList.size(); i++) {
			String path = classpathList.get(i);
			File file = new File(path);
			if (!file.exists()) {
				X10DTCorePlugin.getInstance().writeErrorMsg("X10LaunchConfigurationDelegate, cannot find expected part of runtime path: " + path);
			}

		}
		String[] classPathExpanded = classpathList.toArray(new String[] {});
		// ======== end new classpath calculation

		// x10RuntimeType is the class name that the runtime actually launches.
		// in 1.5 this was always "x10.lang.Runtime'
		// in 1.7 this is "Hello$Main" where "Hello" is main user class
		//
		// We accept the launch config value of "Main class" with or without  trailing "$Main"
		// that is, append it only if necessary
		// We accept it with trailing "$Main" because this is what the
		// "Search..." action in launch config dialog returns. ("Hello$Main")
		final String x10RuntimeType;

		if (!mainTypeName.endsWith(USER_MAIN_SUFFIX)) {
			x10RuntimeType = mainTypeName + USER_MAIN_SUFFIX;
		} else {
			x10RuntimeType = mainTypeName;
		}
		// Create VM config
		VMRunnerConfiguration runConfig = new VMRunnerConfiguration(x10RuntimeType, classPathExpanded);
		String[] explicitRuntimeArgsArray = execArgs.getRuntimeArgumentsArray();
		String[] explicitProgArgsArray = execArgs.getProgramArgumentsArray();
		String[] realArgsArray = new String[explicitProgArgsArray.length + explicitRuntimeArgsArray.length /* PORT1.7  + 1*/];

		System.arraycopy(explicitRuntimeArgsArray, 0, realArgsArray, 0, explicitRuntimeArgsArray.length);
		//realArgsArray[explicitRuntimeArgsArray.length] = mainTypeName;// PORT1.7  1.5 required main as first arg to launch
		System.arraycopy(explicitProgArgsArray, 0, realArgsArray, explicitRuntimeArgsArray.length /* PORT1.7  + 1*/, explicitProgArgsArray.length);

		// DLLs were for testcases??? in 1.5 needed some native code. but not
		// any more.
		String[] x10ExtraVMArgs = {
		// "-Djava.library.path=" + commonLoc + "\\lib",
		"-ea" // BRT ea= enable assertions, do this only if set in prefs
		};
		// 1.7: would have to get to c++ backend code
		// eventually want some UI in launch config for this

		String[] explicitVMArgsArray = execArgs.getVMArgumentsArray();
		String[] realVMArgsArray = new String[explicitVMArgsArray.length + x10ExtraVMArgs.length];

		System.arraycopy(x10ExtraVMArgs, 0, realVMArgsArray, 0, x10ExtraVMArgs.length);
		System.arraycopy(explicitVMArgsArray, 0, realVMArgsArray, x10ExtraVMArgs.length, explicitVMArgsArray.length);

		runConfig.setProgramArguments(realArgsArray);
		runConfig.setEnvironment(envp);
		runConfig.setVMArguments(explicitVMArgsArray);
		runConfig.setWorkingDirectory(workingDirName);
		runConfig.setVMSpecificAttributesMap(vmAttributesMap);

		// Bootpath
		runConfig.setBootClassPath(getBootpath(configuration));

		// check for cancellation
		if (monitor.isCanceled()) {
			return;
		}

		// stop in main
		prepareStopInMain(configuration);

		// done the verification phase
		monitor.worked(1);

		monitor.subTask("Creating source locator");
		// set the default source locator if required
		setDefaultSourceLocator(launch, configuration);
		monitor.worked(1);

//		if (debug) {
//			System.out.println("runConfig classToLaunch: "+runConfig.getClassToLaunch());
//			System.out.println("runConfig program args: "+printArray(runConfig.getProgramArguments()));
//			System.out.println("runConfig VM args: "+printArray(runConfig.getVMArguments()));
//		}

		// Launch the configuration - 1 unit of work
		runner.run(runConfig, launch, monitor);

		// check for cancellation
		if (monitor.isCanceled()) {
			return;
		}

		monitor.done();
	}
    
    
    public boolean superPreLaunchCheck(ILaunchConfiguration configuration, String mode, IProgressMonitor monitor) throws CoreException {
		if (!saveBeforeLaunch(configuration, mode, monitor)) {
			return false;
		}
		if (mode.equals(ILaunchManager.RUN_MODE) && configuration.supportsMode(ILaunchManager.DEBUG_MODE)) {
			IBreakpoint[] breakpoints= getBreakpoints(configuration);
            if (breakpoints == null) {
                return true;
            }
			for (int i = 0; i < breakpoints.length; i++) {
				if (breakpoints[i].isEnabled()) {
					IStatusHandler prompter = DebugPlugin.getDefault().getStatusHandler(promptStatus);
					if (prompter != null) {
						boolean launchInDebugModeInstead = ((Boolean)prompter.handleStatus(switchToDebugPromptStatus, configuration)).booleanValue();
						if (launchInDebugModeInstead) { 
							return false; //kill this launch
						} 
					}
					// if no user prompt, or user says to continue (no need to check other breakpoints)
					return true;
				}
			}
		}	
		// no enabled breakpoints... continue launch
		return true;
	}
    
    /*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.LaunchConfigurationDelegate#getBuildOrder(org.eclipse.debug.core.ILaunchConfiguration,
	 *      java.lang.String)
	 */
	protected IProject[] getBuildOrder(ILaunchConfiguration configuration,
			String mode) throws CoreException {
		return fOrderedProjects;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.LaunchConfigurationDelegate#getProjectsForProblemSearch(org.eclipse.debug.core.ILaunchConfiguration,
	 *      java.lang.String)
	 */
	protected IProject[] getProjectsForProblemSearch(
			ILaunchConfiguration configuration, String mode)
			throws CoreException {
		return fOrderedProjects;
	}
	
    @Override
	public boolean preLaunchCheck(ILaunchConfiguration configuration,
			String mode, IProgressMonitor monitor) throws CoreException {
    	// build project list
		if (monitor != null) {
			monitor.subTask(LaunchingMessages.AbstractJavaLaunchConfigurationDelegate_20); 
		}
		fOrderedProjects = null;
		ISourceProject javaProject = getJavaProjectFromConfig(configuration);
		if (javaProject != null) {
			fOrderedProjects = computeReferencedBuildOrder(new IProject[]{javaProject
					.getRawProject()});
		}
		// do generic launch checks
		return superPreLaunchCheck(configuration, mode, monitor);
	}
    
    
    
    private static ISourceProject getJavaProjectFromConfig(ILaunchConfiguration configuration) throws CoreException {
		String projectName = configuration.getAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, (String)null);
		if ((projectName == null) || (projectName.trim().length() < 1)) {
			return null;
		}
		ISourceProject javaProject = BuildPathUtils.getProject(projectName);
		if (javaProject != null && javaProject.getRawProject().exists() && !javaProject.getRawProject().isOpen()) {
			abort(MessageFormat.format(LaunchingMessages.JavaRuntime_28, new String[] {configuration.getName(), projectName}), IJavaLaunchConfigurationConstants.ERR_PROJECT_CLOSED, null);
		}
		if ((javaProject == null) || !javaProject.getRawProject().exists()) {
			abort(MessageFormat.format(LaunchingMessages.JavaRuntime_Launch_configuration__0__references_non_existing_project__1___1, new String[] {configuration.getName(), projectName}), IJavaLaunchConfigurationConstants.ERR_NOT_A_JAVA_PROJECT, null);
		}
		return javaProject;
	}
    
    private static void abort(String message, int code, Throwable exception) throws CoreException {
		throw new CoreException(new Status(IStatus.ERROR, LaunchingPlugin.getUniqueIdentifier(), code, message, exception));
	}
	private String printArray(String[] arg) {
    	StringBuffer buf = new StringBuffer();
    	buf.append("[");
    	for (String a : arg) {
			buf.append(a);
			buf.append(", ");
		}
    	buf.append("]");
    	return buf.toString();
    }

}
