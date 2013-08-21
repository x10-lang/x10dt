package x10dt.ui.launch.cpp.launching;

import static org.eclipse.ptp.core.IPTPLaunchConfigurationConstants.ATTR_PROJECT_NAME;

import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_HOST;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_USERNAME;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_PORT;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_IS_PASSWORD_BASED;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_PASSWORD;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_PRIVATE_KEY_FILE;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_PASSPHRASE;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_TIMEOUT;


import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;

import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.cpp.CppLaunchCore;

public class X10RemoteCompilationConfigurationDelegate implements ILaunchConfigurationDelegate {

  public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor) throws CoreException {
    String projectName  = configuration.getAttribute(ATTR_PROJECT_NAME, Constants.EMPTY_STR);
    IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
    
    try {
      String hostname = configuration.getAttribute(ATTR_HOST, Constants.EMPTY_STR);
      String username = configuration.getAttribute(ATTR_USERNAME, Constants.EMPTY_STR);
      int port = configuration.getAttribute(ATTR_PORT, 0);
      boolean isPasswordBased = configuration.getAttribute(ATTR_IS_PASSWORD_BASED, true);
      String password = configuration.getAttribute(ATTR_PASSWORD, Constants.EMPTY_STR);
      String privateKeyFile = configuration.getAttribute(ATTR_PRIVATE_KEY_FILE, Constants.EMPTY_STR);
      String passphrase = configuration.getAttribute(ATTR_PASSPHRASE, Constants.EMPTY_STR);
      int timeout = configuration.getAttribute(ATTR_TIMEOUT, 0);
      ConfUtils.createOrUpdateRemoteConnection(ConfUtils.getConnectionName(configuration), hostname, username, port, isPasswordBased, password, privateKeyFile, passphrase, timeout);
    } catch (CoreException e){
      CppLaunchCore.getInstance().getLog().log(e.getStatus());
    }
    
    project.build(IncrementalProjectBuilder.FULL_BUILD, new NullProgressMonitor());
  }

}
