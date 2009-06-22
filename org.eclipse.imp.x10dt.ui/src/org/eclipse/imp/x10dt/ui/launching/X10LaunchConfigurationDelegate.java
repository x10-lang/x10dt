package x10.uide.launching;

import java.io.File;
import java.text.MessageFormat;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.internal.ui.DebugUIPlugin;
import org.eclipse.jdt.launching.AbstractJavaLaunchConfigurationDelegate;
import org.eclipse.jdt.launching.ExecutionArguments;
import org.eclipse.jdt.launching.IVMRunner;
import org.eclipse.jdt.launching.VMRunnerConfiguration;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import x10.uide.X10UIPlugin;

public class X10LaunchConfigurationDelegate extends AbstractJavaLaunchConfigurationDelegate {
    private final static String x10RuntimeType= "x10.lang.Runtime";

    public void launch(final ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor) throws CoreException {
	// boolean debug= mode.equals(ILaunchManager.DEBUG_MODE);

	if (monitor == null) {
	    monitor= new NullProgressMonitor();
	}

	monitor.beginTask(MessageFormat.format("{0}...", new String[] { configuration.getName() }), 3); //$NON-NLS-1$
	// check for cancellation
	if (monitor.isCanceled()) {
	    return;
	}

	monitor.subTask("Verifying launch attributes");

	String mainTypeName= verifyMainTypeName(configuration);
	IVMRunner runner= getVMRunner(configuration, mode);

	File workingDir= verifyWorkingDirectory(configuration);
	String workingDirName= null;
	if (workingDir != null) {
	    workingDirName= workingDir.getAbsolutePath();
	}

	// Environment variables
	String[] envp= getEnvironment(configuration);

	// Program & VM args
	String pgmArgs= getProgramArguments(configuration);
	String vmArgs= getVMArguments(configuration);
	ExecutionArguments execArgs= new ExecutionArguments(vmArgs, pgmArgs);

	// VM-specific attributes
	Map vmAttributesMap= getVMSpecificAttributesMap(configuration);

	// Classpath
	String[] classpath= getClasspath(configuration);

	// Create VM config
	VMRunnerConfiguration runConfig= new VMRunnerConfiguration(x10RuntimeType, classpath);
	String[] explicitArgsArray= execArgs.getProgramArgumentsArray();
	String[] realArgsArray= new String[explicitArgsArray.length + 1];

	realArgsArray[0]= mainTypeName;
	System.arraycopy(explicitArgsArray, 0, realArgsArray, 1, explicitArgsArray.length);

	String x10RuntimeLoc= configuration.getAttribute(X10LaunchConfigAttributes.X10RuntimeAttributeID, "");

	if (x10RuntimeLoc.length() == 0) {
	    Display.getDefault().asyncExec(new Runnable() {
		public void run() {
		    Shell shell= X10UIPlugin.getInstance().getWorkbench().getActiveWorkbenchWindow().getShell();
		    MessageDialog.openError(shell, "Please specify the X10 Runtime location", "The location of the X10 Runtime is unset in the launch configuration '" + configuration.getName() + "'.");
		}
	    });
	    return;
	}

	//	String runtimeLoc= x10RuntimeLoc + File.separator + "x10.runtime";
	String commonLoc= x10RuntimeLoc.substring(0, x10RuntimeLoc.lastIndexOf(File.separator)) + File.separator + "x10.common";

	String[] x10ExtraVMArgs= {
		"-Djava.library.path=" + commonLoc + "\\lib",
		"-ea",
		"-classpath=" + x10RuntimeLoc + "\\classes"
	};

	String[] explicitVMArgsArray= execArgs.getVMArgumentsArray();
	String[] realVMArgsArray= new String[explicitVMArgsArray.length + x10ExtraVMArgs.length];

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

	// Launch the configuration - 1 unit of work
	runner.run(runConfig, launch, monitor);

	// check for cancellation
	if (monitor.isCanceled()) {
	    return;
	}

	monitor.done();
    }

}
