package x10dt.ui.launch.cpp.launching;

import static org.eclipse.ptp.core.IPTPLaunchConfigurationConstants.ATTR_PROJECT_NAME;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.IS_VALID;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_USE_HOSTFILE;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_LOADLEVELER_SCRIPT;
import static x10dt.ui.launch.cpp.launching.CommunicationInterfaceTab.HOST_LIST;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.DEFAULT_NUM_PLACES;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_NUM_PLACES;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_HOSTFILE;
import static x10dt.ui.launch.rms.core.launch_configuration.LaunchConfigConstants.ATTR_HOSTLIST;




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

  
    public static boolean isLocalConnection(ILaunchConfiguration compilationConf){
      try {
        if (compilationConf == null) return true;
        return compilationConf.getAttribute(ATTR_IS_LOCAL, true);
      } catch(CoreException e){
        CppLaunchCore.log(IStatus.ERROR, LaunchMessages.PCU_GetConf, e);
      }
      return true;
    }
   
    public static String getServiceTypeId(ILaunchConfiguration compilationConf){
      try {
        if (compilationConf == null) return STANDALONE_SERVICE_PROVIDER_ID;
        return compilationConf.getAttribute(ATTR_CITYPE, STANDALONE_SERVICE_PROVIDER_ID);
      } catch(CoreException e){
        CppLaunchCore.log(IStatus.ERROR, LaunchMessages.PCU_GetConf, e);
      }
      return STANDALONE_SERVICE_PROVIDER_ID;
    }
    
    
    public static String getConnectionName(ILaunchConfiguration compilationConf){
    if (compilationConf == null)
      return IRemoteConnectionManager.DEFAULT_CONNECTION_NAME;
    return compilationConf.getName();
    }
    
    public static ETargetOS getTargetOS(ILaunchConfiguration compilationConf){
      if (isLocalConnection(compilationConf)){
        return getLocalOS();
      } else {
        ITargetOpHelper targetOp = TargetOpHelperFactory.create(false /*not local*/, false /*not cygwin -- we do not support remote windows*/,
                                                                ConfUtils.getConnectionName(compilationConf));
        return PlatformConfUtils.detectOS(targetOp);
      }
    }
    
    public static ETargetOS getLocalOS() {
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
    
    public static String getRemoteOutputFolder(ILaunchConfiguration compilationConf){
      if (compilationConf == null) return null; // Should not happen
      try {
        return compilationConf.getAttribute(ATTR_REMOTE_OUTPUT_FOLDER, Constants.EMPTY_STR);
      } catch(CoreException e){
        CppLaunchCore.getInstance().getLog().log(e.getStatus());
      }
      return null;
    }
    
    public static String getX10DistribLocation(ILaunchConfiguration compilationConf) {
        if (isLocalConnection(compilationConf)) {
          try {
            return new File(X10BundleUtils.getX10DistHostResource("include").getFile()).getParent(); //$NON-NLS-1$
          } catch (IOException except) {
            // Simply forgets
            return Constants.EMPTY_STR;
          }
        } else { //not local
          try {
            return compilationConf.getAttribute(ATTR_X10_DISTRIBUTION, Constants.EMPTY_STR);
          } catch (CoreException e){
            return Constants.EMPTY_STR;
          }
        }
    }
    
    public static boolean isCygwin(ILaunchConfiguration compilationConf){
      if (!ConfUtils.isLocalConnection(compilationConf)) return false; //We do not support remote windows.
      
      if (ConfUtils.getLocalOS() == ETargetOS.WINDOWS) return true;
      
      return false;
    }
    
    public static boolean isValid(ILaunchConfiguration compilationConf){
      if (compilationConf == null) return true;
      try {
        return compilationConf.getAttribute(IS_VALID, true);
      } catch (CoreException e){
        CppLaunchCore.getInstance().getLog().log(e.getStatus());
      }
      return true;
    }
    
    public static int hostSelectionMode(ILaunchConfiguration conf){
      if (conf == null) return HOST_LIST;
      try {
        return conf.getAttribute(ATTR_USE_HOSTFILE, HOST_LIST);
      } catch (CoreException e){
        CppLaunchCore.getInstance().getLog().log(e.getStatus());
      }
      return HOST_LIST;
    }
    
    public static String getLoadLevelerScript(ILaunchConfiguration conf){
      if (conf == null) return Constants.EMPTY_STR;
      try {
        return conf.getAttribute(ATTR_LOADLEVELER_SCRIPT, Constants.EMPTY_STR);
      } catch (CoreException e){
        CppLaunchCore.getInstance().getLog().log(e.getStatus());
      }
      return Constants.EMPTY_STR;
    }
    

    
    
 
    
    public static int getNPlaces(ILaunchConfiguration conf){
      if (conf == null) return DEFAULT_NUM_PLACES;
      try {
        return conf.getAttribute(ATTR_NUM_PLACES, DEFAULT_NUM_PLACES);
      } catch (CoreException e){
        CppLaunchCore.getInstance().getLog().log(e.getStatus());
      }
      return DEFAULT_NUM_PLACES;
    }

    public static String getHostFile(ILaunchConfiguration conf){
      if (conf == null) return Constants.EMPTY_STR;
      try {
        return conf.getAttribute(ATTR_HOSTFILE, Constants.EMPTY_STR);
      } catch (CoreException e){
        CppLaunchCore.getInstance().getLog().log(e.getStatus());
      }
      return Constants.EMPTY_STR;
    }
    
    public static String getHostList(ILaunchConfiguration conf){
      if (conf == null) return Constants.EMPTY_STR;
      try {
        return conf.getAttribute(ATTR_HOSTLIST, Constants.EMPTY_STR);
      } catch (CoreException e){
        CppLaunchCore.getInstance().getLog().log(e.getStatus());
      }
      return Constants.EMPTY_STR;
    }
    
    private static final String OS_NAME_VAR = "os.name"; //$NON-NLS-1$

}
