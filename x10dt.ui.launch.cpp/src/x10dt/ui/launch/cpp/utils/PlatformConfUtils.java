/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.utils;

import java.util.Arrays;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.imp.utils.Pair;

import x10dt.ui.launch.core.platform_conf.EArchitecture;
import x10dt.ui.launch.core.platform_conf.EBitsArchitecture;
import x10dt.ui.launch.core.platform_conf.ETargetOS;
import x10dt.ui.launch.core.platform_conf.ETransport;
import x10dt.ui.launch.core.utils.IProcessOuputListener;
import x10dt.ui.launch.core.utils.PTPConstants;
import x10dt.ui.launch.core.utils.ProjectUtils;
import x10dt.ui.launch.cpp.builder.target_op.ITargetOpHelper;
import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConf;

/**
 * Utility methods for X10 Platform Configuration usage.
 * 
 * @author egeay
 */
public final class PlatformConfUtils {
  
  /**
   * Detects the architecture of the machine by executing "uname" commands.
   * 
   * @param targetOpHelper The target operation helper for the command execution.
   * @return A possibly null value for the pair or <i>pair.first</i> if we could not successfully execute "uname" or if the
   * output do not match expected values.
   */
  public static Pair<EArchitecture, EBitsArchitecture> detectArchitecture(final ITargetOpHelper targetOpHelper) {
    final LastLineOutputListener processorListener = new LastLineOutputListener();
    try {
      targetOpHelper.run(Arrays.asList(UNAME, UNAME_P_OPT), processorListener, new NullProgressMonitor());
    } catch (Exception except) {
      // Do nothing.
    }

    String output = processorListener.getOutput();
    if (output == null) {
      // We fail to get an output with "uname -p". Last option is to try with "uname -m".
      final LastLineOutputListener machineListener = new LastLineOutputListener();
      try {
        targetOpHelper.run(Arrays.asList(UNAME, UNAME_M_OPT), machineListener, new NullProgressMonitor());
        
        output = machineListener.getOutput();
      } catch (Exception except) {
        // Do nothing.
      }
    }
    
    if (output == null) {
      return null;
    }
    EArchitecture architecture = null;
    if (output.matches(I86_REGEX) || output.startsWith(X86_PROC)) {
      architecture = EArchitecture.x86;
    } else if (output.contains(POWER) || output.contains(PPC)) {
      architecture = EArchitecture.Power;
    }
    final EBitsArchitecture bitsArch = output.contains(A64BITS) ? EBitsArchitecture.E64Arch : EBitsArchitecture.E32Arch;
    return new Pair<EArchitecture, EBitsArchitecture>(architecture, bitsArch);
  }
  
  /**
   * Detects the OS of the target machine by executing "uname" commands.
   * 
   * @param targetOpHelper The target operation helper for the command execution.
   * @return A possibly null value if we could not execute properly the uname command.
   */
  public static ETargetOS detectOS(final ITargetOpHelper targetOpHelper) {
    final OSDetectionListener osDetectionListener = new OSDetectionListener();
    try {
      targetOpHelper.run(Arrays.asList(UNAME, UNAME_S_OPT), osDetectionListener, new NullProgressMonitor());
      return osDetectionListener.getDetectedOS();
    } catch (Exception except) {
      return null;
    }
  }
  
  /**
   * Returns the appropriate according to the PTP resource manager service id and target operating system.
   * 
   * @param serviceTypeId The service id to consider.
   * @param targetOS The target operating system.
   * @return A non-null instance of {@link ETransport}.
   */
  public static ETransport getTransport(final String serviceTypeId, final ETargetOS targetOS) {
    if (PTPConstants.STANDALONE_SERVICE_PROVIDER_ID.equals(serviceTypeId)) {
      return ETransport.STANDALONE;
    } else if (PTPConstants.SOCKETS_SERVICE_PROVIDER_ID.equals(serviceTypeId)) {
      return ETransport.SOCKETS;
    } else if (PTPConstants.PAMI_SERVICE_PROVIDER_ID.equals(serviceTypeId)) {
      return ETransport.PAMI;
    } else {
      return (targetOS == ETargetOS.AIX) ? ETransport.LAPI : ETransport.MPI;
    }
  }
  
  /**
   * Returns the workspace directory (where generated files will be created and compiled) for a given project independently 
   * if the connection type, either local or remote.
   * 
   * @param platformConf The X10 platform configuration to consider.
   * @param project The project to consider.
   * @return A non-null non-empty string identifying a directory.
   * @throws CoreException Occurs if the project output folder does not exist or we can't obtain its path appropriately.
   */
  public static String getWorkspaceDir(final IX10PlatformConf platformConf, final IProject project) throws CoreException {
    if (platformConf.getConnectionConf().isLocal()) {
      return ProjectUtils.getProjectOutputDirPath(project);
    } else {
      return platformConf.getCppCompilationConf().getRemoteOutputFolder();
    }
  }
  
  // --- Private code
  
  private PlatformConfUtils() {}
  
  // --- Private classes
  
  private static final class LastLineOutputListener implements IProcessOuputListener {

    // --- Interface methods implementation
    
    public void read(final String line) {
      this.fOutput = line;
    }

    public void readError(final String line) {
    }
    
    // --- Internal services
    
    String getOutput() {
      return this.fOutput;
    }
    
    // --- Fields
    
    private String fOutput;
    
  }
  
  private static final class OSDetectionListener implements IProcessOuputListener {

    // --- Interface methods implementation
    
    public void read(final String line) {
      this.fOutput = line;
    }

    public void readError(final String line) {
    }
    
    // --- Internal services
    
    ETargetOS getDetectedOS() {
      final String output = this.fOutput.toLowerCase();
      if (LINUX.equals(output)) {
        return ETargetOS.LINUX;
      } else if (AIX.equals(output)) {
        return ETargetOS.AIX;
      } else if (DARWIN.equals(output)) {
        return ETargetOS.MAC;
      } else if (output.startsWith(CYGWIN)) {
        return ETargetOS.WINDOWS;
      } else {
        return ETargetOS.UNIX;
      }
    }
    
    // --- Fields
    
    private String fOutput;
    
    
    private static final String LINUX = "linux"; //$NON-NLS-1$
    
    private static final String CYGWIN = "cygwin"; //$NON-NLS-1$
    
    private static final String AIX = "aix"; //$NON-NLS-1$
    
    private static final String DARWIN = "darwin"; //$NON-NLS-1$
    
  }
  
  // --- Fields
  
  private static final String UNAME = "uname"; //$NON-NLS-1$
  
  private static final String UNAME_S_OPT = "-s"; //$NON-NLS-1$
  
  private static final String UNAME_P_OPT = "-p"; //$NON-NLS-1$
  
  private static final String UNAME_M_OPT = "-m"; //$NON-NLS-1$
  
  private static final String X86_PROC = "x86"; //$NON-NLS-1$
  
  private static final String I86_REGEX = "i.86"; //$NON-NLS-1$
  
  private static final String POWER = "power"; //$NON-NLS-1$
  
  private static final String PPC = "ppc"; //$NON-NLS-1$
  
  private static final String A64BITS = "64"; //$NON-NLS-1$

}
