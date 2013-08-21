package x10dt.ui.launch.cpp.launching;

import static org.eclipse.ptp.core.IPTPLaunchConfigurationConstants.ATTR_PROJECT_NAME;
import static x10dt.ui.launch.core.utils.PTPConstants.REMOTE_CONN_SERVICE_ID;
import static x10dt.ui.launch.cpp.launching.CommunicationInterfaceTab.HOST_LIST;
import static x10dt.ui.launch.cpp.launching.CompilationTab.ATTR_ARCHIVER;
import static x10dt.ui.launch.cpp.launching.CompilationTab.ATTR_ARCHIVER_OPTS;
import static x10dt.ui.launch.cpp.launching.CompilationTab.ATTR_COMPILER;
import static x10dt.ui.launch.cpp.launching.CompilationTab.ATTR_COMPILER_OPTS;
import static x10dt.ui.launch.cpp.launching.CompilationTab.ATTR_DEFAULTS;
import static x10dt.ui.launch.cpp.launching.CompilationTab.ATTR_LINKER;
import static x10dt.ui.launch.cpp.launching.CompilationTab.ATTR_LINKER_LIBS;
import static x10dt.ui.launch.cpp.launching.CompilationTab.ATTR_LINKER_OPTS;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_CITYPE;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_HOST;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_IS_LOCAL;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_IS_PASSWORD_BASED;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_PASSPHRASE;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_PASSWORD;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_PORT;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_PRIVATE_KEY_FILE;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_REMOTE_OUTPUT_FOLDER;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_TIMEOUT;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_USERNAME;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.ATTR_X10_DISTRIBUTION;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.IS_VALID;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.STANDALONE;
import static x10dt.ui.launch.cpp.launching.LaunchConfigConstants.ATTR_HOSTFILE;
import static x10dt.ui.launch.cpp.launching.LaunchConfigConstants.ATTR_HOSTLIST;
import static x10dt.ui.launch.cpp.launching.LaunchConfigConstants.ATTR_LOADLEVELER_SCRIPT;
import static x10dt.ui.launch.cpp.launching.LaunchConfigConstants.ATTR_NUM_PLACES;
import static x10dt.ui.launch.cpp.launching.LaunchConfigConstants.ATTR_USE_HOSTFILE;
import static x10dt.ui.launch.cpp.launching.LaunchConfigConstants.DEFAULT_NUM_PLACES;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.ptp.internal.remote.remotetools.core.RemoteToolsServices;
import org.eclipse.ptp.remote.core.IRemoteConnectionManager;
import org.eclipse.ptp.remote.core.RemoteServices;
import org.eclipse.ptp.remotetools.environment.EnvironmentPlugin;
import org.eclipse.ptp.remotetools.environment.control.ITargetConfig;
import org.eclipse.ptp.remotetools.environment.control.ITargetStatus;
import org.eclipse.ptp.remotetools.environment.core.ITargetElement;
import org.eclipse.ptp.remotetools.environment.core.TargetElement;
import org.eclipse.ptp.remotetools.environment.core.TargetTypeElement;

import x10dt.core.utils.X10BundleUtils;
import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.platform_conf.ETargetOS;
import x10dt.ui.launch.cpp.CppLaunchCore;
import x10dt.ui.launch.cpp.LaunchMessages;
import x10dt.ui.launch.cpp.builder.target_op.ITargetOpHelper;
import x10dt.ui.launch.cpp.builder.target_op.TargetOpHelperFactory;
import x10dt.ui.launch.cpp.utils.PlatformConfUtils;

public class ConfUtils {
  
  //Attributes from org.eclipse.ptp.remotetools.environment.generichost.conf/default.properties
  public static String CONNECTION_TIMEOUT = "CONNECTION_TIMEOUT";
  public static String KEY_PATH = "KEY_PATH";
  public static String KEY_PASSPHRASE = "KEY_PASSPHRASE";
  public static String IS_PASSWORD_AUTH = "IS_PASSWORD_AUTH";


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
        if (compilationConf == null) return STANDALONE;
        return compilationConf.getAttribute(ATTR_CITYPE, STANDALONE);
      } catch(CoreException e){
        CppLaunchCore.log(IStatus.ERROR, LaunchMessages.PCU_GetConf, e);
      }
      return STANDALONE;
    }
    
    
    public static String getConnectionName(ILaunchConfiguration compilationConf){
    if (compilationConf == null)
      return IRemoteConnectionManager.LOCAL_CONNECTION_NAME;
    String connectionName = compilationConf.getName();
    if (!isLocalConnection(compilationConf)){
      final IRemoteConnectionManager rmConnManager = RemoteServices.getRemoteServices(REMOTE_CONN_SERVICE_ID).getConnectionManager();
      if (rmConnManager.getConnection(connectionName) == null){
        try {
          String hostname = compilationConf.getAttribute(ATTR_HOST, Constants.EMPTY_STR);
          String username = compilationConf.getAttribute(ATTR_USERNAME, Constants.EMPTY_STR);
          int port = compilationConf.getAttribute(ATTR_PORT, 0);
          boolean isPasswordBased = compilationConf.getAttribute(ATTR_IS_PASSWORD_BASED, true);
          String password = compilationConf.getAttribute(ATTR_PASSWORD, Constants.EMPTY_STR);
          String privateKeyFile = compilationConf.getAttribute(ATTR_PRIVATE_KEY_FILE, Constants.EMPTY_STR);
          String passphrase = compilationConf.getAttribute(ATTR_PASSPHRASE, Constants.EMPTY_STR);
          int timeout = compilationConf.getAttribute(ATTR_TIMEOUT, 0);
          createOrUpdateRemoteConnection(connectionName, hostname, username, port, isPasswordBased, password, privateKeyFile, passphrase, timeout);
        } catch(CoreException e){
          CppLaunchCore.getInstance().getLog().log(e.getStatus());
        }
      }
    }
    return connectionName;
    }
    
    
    public static void createOrUpdateRemoteConnection(String connectionName, String hostname, String username, int port, 
                                  boolean isPasswordBased, String password, String privateKeyFile, String passphrase, int timeout) throws CoreException {

      final IRemoteConnectionManager rmConnManager = RemoteServices.getRemoteServices(REMOTE_CONN_SERVICE_ID).getConnectionManager();

      // There is a problem in writing this code in the following way:  The attribute is_pass_auth is not recognized.
//      try {
//        IRemoteConnection conn  = rmConnManager.getConnection(connectionName);
//        if (conn != null) {
//          conn.close();
//        } else {
//          conn = rmConnManager.newConnection(connectionName);
//        }
//        conn.setAddress(hostname);
//        conn.setUsername(username);
//        conn.setPort(port);
//        conn.setPassword(password);
//        if (isPasswordBased) {
//           conn.setAttribute(IS_PASSWORD_AUTH, "true");
//        } else {
//           conn.setAttribute(IS_PASSWORD_AUTH, "false");
//        }
//        conn.setAttribute(KEY_PATH, privateKeyFile);
//        conn.setAttribute(KEY_PASSPHRASE, passphrase);
//        conn.setAttribute(CONNECTION_TIMEOUT, new Integer(timeout).toString());
//      } catch (RemoteConnectionException e){
//        CppLaunchCore.log(IStatus.ERROR, LaunchMessages.CU_ConnectionException, e);
//      }
//      
      
      
      final TargetTypeElement targetTypeElement = RemoteToolsServices.getTargetTypeElement();
      ITargetElement targetElement = getDefaultTargetElement(connectionName);
      if (targetElement == null) {
        final String id = EnvironmentPlugin.getDefault().getEnvironmentUniqueID();
        targetElement = new TargetElement(targetTypeElement, connectionName, new HashMap<String, String>(), id);
        targetTypeElement.addElement((TargetElement) targetElement);
      } else {
        if (targetElement.getControl().query() != ITargetStatus.STOPPED) {
          targetElement.getControl().kill();     
        }
      }
      
      final ITargetConfig targetConfig = targetElement.getControl().getConfig();
      targetConfig.setConnectionAddress(hostname);
      targetConfig.setLoginUsername(username);
      targetConfig.setConnectionPort(port);
      targetConfig.setPasswordAuth(isPasswordBased);
      targetConfig.setLoginPassword(password);
      targetConfig.setKeyPath(privateKeyFile);
      targetConfig.setKeyPassphrase(passphrase);
      targetConfig.setConnectionTimeout(timeout);
     
      rmConnManager.getConnections(); // Side effect of creating connection.

    }
    
    
    private static ITargetElement getDefaultTargetElement(final String connectionName) {
      final TargetTypeElement targetTypeElement = RemoteToolsServices.getTargetTypeElement();
      for (final ITargetElement targetElement : targetTypeElement.getElements()) {
        if (targetElement.getName().equals(connectionName)) {
          return targetElement;
        }
      }
      return null;
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
    
    public static Collection<String> getX10LibsLocation(ILaunchConfiguration compilationConf){
      Collection<String> result = new ArrayList<String>();
      try {
        File[] files = new File(X10BundleUtils.getX10DistHostResource("lib").getFile()).listFiles();
        for (File f: files){
          result.add(f.getAbsolutePath());
        }
        File[] morefiles = new File(X10BundleUtils.getX10DistHostResource("stdlib/lib").getFile()).listFiles();
        for (File f: morefiles){
          result.add(f.getAbsolutePath());
        }
      } catch (IOException e){
        //Simply forgets
      }
      return result;
    }
    
    
    public static boolean isCygwin(ILaunchConfiguration compilationConf){
      if (!ConfUtils.isLocalConnection(compilationConf)) return false; //We do not support remote windows.
      
      if (ConfUtils.getLocalOS() == ETargetOS.WINDOWS) return true;
      
      return false;
    }
    
    
    public static IDefaultCPPCommands getCPPCompilationCommands(String project, final ILaunchConfiguration compilationConf){
      try {
        if (compilationConf == null){
          return DefaultCPPCommandsFactory.create(project);
        }
        
        try {
          if(compilationConf.getAttribute(ATTR_DEFAULTS, true)){
            return DefaultCPPCommandsFactory.create(project);
          }
          
        } catch (CoreException e){
          CppLaunchCore.getInstance().getLog().log(e.getStatus());
        }
        
        return new IDefaultCPPCommands() {

          public String getArchiver() {
            try {
              return compilationConf.getAttribute(ATTR_ARCHIVER, Constants.EMPTY_STR);
            } catch (CoreException e){
              CppLaunchCore.getInstance().getLog().log(e.getStatus());
            }
            return Constants.EMPTY_STR;
          }

          public String getArchivingOpts() {
            try {
              return compilationConf.getAttribute(ATTR_ARCHIVER_OPTS, Constants.EMPTY_STR);
            } catch (CoreException e){
              CppLaunchCore.getInstance().getLog().log(e.getStatus());
            }
            return Constants.EMPTY_STR;
          }

          public String getCompiler() {
            try {
              return compilationConf.getAttribute(ATTR_COMPILER, Constants.EMPTY_STR);
            } catch (CoreException e){
              CppLaunchCore.getInstance().getLog().log(e.getStatus());
            }
            return Constants.EMPTY_STR;
          }

          public String getCompilerOptions() {
            try {
              return compilationConf.getAttribute(ATTR_COMPILER_OPTS, Constants.EMPTY_STR);
            } catch (CoreException e){
              CppLaunchCore.getInstance().getLog().log(e.getStatus());
            }
            return Constants.EMPTY_STR;
          }

          public String getLinker() {
            try {
              return compilationConf.getAttribute(ATTR_LINKER, Constants.EMPTY_STR);
            } catch (CoreException e){
              CppLaunchCore.getInstance().getLog().log(e.getStatus());
            }
            return Constants.EMPTY_STR;
          }

          public String getLinkingLibraries() {
            try {
              return compilationConf.getAttribute(ATTR_LINKER_LIBS, Constants.EMPTY_STR);
            } catch (CoreException e){
              CppLaunchCore.getInstance().getLog().log(e.getStatus());
            }
            return Constants.EMPTY_STR;
          }

          public String getLinkingOptions() {
            try {
              return compilationConf.getAttribute(ATTR_LINKER_OPTS, Constants.EMPTY_STR);
            } catch (CoreException e){
              CppLaunchCore.getInstance().getLog().log(e.getStatus());
            }
            return Constants.EMPTY_STR;
          }
          
        };
      } catch(CoreException e){
        CppLaunchCore.getInstance().getLog().log(e.getStatus());
      }
      return null;
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
