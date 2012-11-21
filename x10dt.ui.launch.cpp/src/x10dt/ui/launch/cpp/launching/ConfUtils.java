package x10dt.ui.launch.cpp.launching;

import static org.eclipse.ptp.core.IPTPLaunchConfigurationConstants.ATTR_PROJECT_NAME;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.IS_VALID;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.ptp.remote.core.IRemoteConnectionManager;

import x10dt.core.utils.X10BundleUtils;
import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.platform_conf.ETargetOS;
import x10dt.ui.launch.cpp.CppLaunchCore;
import x10dt.ui.launch.cpp.LaunchMessages;
import x10dt.ui.launch.cpp.builder.target_op.TargetOpHelperFactory;

import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_IS_LOCAL;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_X10_DISTRIBUTION;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_REMOTE_OUTPUT_FOLDER;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_CITYPE;
import static x10dt.ui.launch.core.utils.PTPConstants.STANDALONE_SERVICE_PROVIDER_ID;

import x10dt.ui.launch.cpp.builder.target_op.ITargetOpHelper;
import x10dt.ui.launch.cpp.utils.PlatformConfUtils;

public class ConfUtils {


    public static ILaunchConfiguration getConfiguration(String project) throws CoreException {
      if (project == null || project.equals("")) {
        return null;
      }
      try {
        for(ILaunchConfiguration conf: DebugPlugin.getDefault().getLaunchManager().getLaunchConfigurations()){
          if (CppLaunchCore.REMOTE_COMPILATION_LAUNCH_CONF_TYPE.equals(conf.getType().getIdentifier()) &&
              project.equals(conf.getAttribute(ATTR_PROJECT_NAME, Constants.EMPTY_STR))){
            return conf;
          }
        }
      } catch(CoreException e){
        CppLaunchCore.log(IStatus.ERROR, LaunchMessages.PCU_GetConf, e);
      }
      return null;
    }

  
    public static boolean isLocalConnection(ILaunchConfiguration conf){
      try {
        if (conf == null) return true;
        return conf.getAttribute(ATTR_IS_LOCAL, true);
      } catch(CoreException e){
        CppLaunchCore.log(IStatus.ERROR, LaunchMessages.PCU_GetConf, e);
      }
      return true;
    }
   
    public static String getServiceTypeId(ILaunchConfiguration conf){
      try {
        if (conf == null) return STANDALONE_SERVICE_PROVIDER_ID;
        return conf.getAttribute(ATTR_CITYPE, STANDALONE_SERVICE_PROVIDER_ID);
      } catch(CoreException e){
        CppLaunchCore.log(IStatus.ERROR, LaunchMessages.PCU_GetConf, e);
      }
      return null;
    }
    
    
    public static String getConnectionName(ILaunchConfiguration conf){
    if (conf == null)
      return IRemoteConnectionManager.DEFAULT_CONNECTION_NAME;
    return conf.getName();
    }
    
    public static ETargetOS getTargetOS(ILaunchConfiguration conf){
      if (isLocalConnection(conf)){
        return getLocalOS();
      } else {
        ITargetOpHelper targetOp = TargetOpHelperFactory.create(false /*not local*/, false /*not cygwin -- we do not support remote windows*/,
                                                                ConfUtils.getConnectionName(conf));
        return PlatformConfUtils.detectOS(targetOp);
      }
    }
    
    public static String getRemoteOutputFolder(ILaunchConfiguration conf){
      if (conf == null) return null; // Should not happen
      try {
        return conf.getAttribute(ATTR_REMOTE_OUTPUT_FOLDER, Constants.EMPTY_STR);
      } catch(CoreException e){
        CppLaunchCore.getInstance().getLog().log(e.getStatus());
      }
      return null;
    }
    
    public static String getX10DistribLocation(ILaunchConfiguration conf) {
        if (isLocalConnection(conf)) {
          try {
            return new File(X10BundleUtils.getX10DistHostResource("include").getFile()).getParent(); //$NON-NLS-1$
          } catch (IOException except) {
            // Simply forgets
            return Constants.EMPTY_STR;
          }
        } else { //not local
          try {
            return conf.getAttribute(ATTR_X10_DISTRIBUTION, Constants.EMPTY_STR);
          } catch (CoreException e){
            return Constants.EMPTY_STR;
          }
        }
    }
    
    public static boolean isValid(ILaunchConfiguration conf){
      if (conf == null) return true;
      try {
        return conf.getAttribute(IS_VALID, true);
      } catch (CoreException e){
        CppLaunchCore.getInstance().getLog().log(e.getStatus());
      }
      return true;
    }
    
    private static ETargetOS getLocalOS() {
      final String osName = System.getProperty(OS_NAME_VAR);
      if (osName.startsWith("AIX")) { //$NON-NLS-1$
        return ETargetOS.AIX;
      } else if (osName.startsWith("Linux")) { //$NON-NLS-1$
        return ETargetOS.LINUX;
      } else if (osName.startsWith("Mac")) { //$NON-NLS-1$
        return ETargetOS.MAC;
      } else if (osName.startsWith("Windows")) { //$NON-NLS-1$
        return ETargetOS.WINDOWS;
      } else {
        return ETargetOS.UNIX;
      }
    }
    
    

    private static final String OS_NAME_VAR = "os.name"; //$NON-NLS-1$

}
