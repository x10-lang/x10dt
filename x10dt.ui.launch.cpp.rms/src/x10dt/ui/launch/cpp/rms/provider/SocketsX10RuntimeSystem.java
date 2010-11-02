/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.rms.provider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ptp.core.attributes.AttributeDefinitionManager;
import org.eclipse.ptp.core.attributes.AttributeManager;
import org.eclipse.ptp.core.elements.attributes.JobAttributes;
import org.eclipse.ptp.remote.core.IRemoteProcess;
import org.eclipse.ptp.remote.core.IRemoteProcessBuilder;

import x10dt.ui.launch.cpp.rms.Messages;
import x10dt.ui.launch.cpp.rms.RMSActivator;
import x10dt.ui.launch.cpp.rms.launch_configuration.LaunchAttributes;


final class SocketsX10RuntimeSystem extends AbstractX10RuntimeSystem implements IX10RuntimeSystem {

  SocketsX10RuntimeSystem(final int id, final IX10RMConfiguration rmConfig, final AttributeDefinitionManager attrDefManager) {
    super(id, rmConfig, attrDefManager);
  }

  // --- Abstract methods implementation
  
  protected Job createCheckRequirementsJob(final IProgressMonitor monitor, final Collection<String> hostNames) {
    return new SSHValidationJob(monitor, hostNames);
  }
  
  protected Job createRuntimeSystemJob(final String jobID, final String queueID, 
                                       final AttributeManager attrMgr) throws CoreException {
    return new SocketsX10RuntimeSystemJob(jobID, queueID, "Sockets Run Job", this, attrMgr); //$NON-NLS-1$
  }
  
  // --- Private classes
  
  private static final class SocketsX10RuntimeSystemJob extends AbstractX10RuntimeSystemJob {

    protected SocketsX10RuntimeSystemJob(final String jobId, final String queueId, final String name, 
                                         final IX10RuntimeSystem runtimeSystem, final AttributeManager attrManager) {
      super(jobId, queueId, name, runtimeSystem, attrManager);
    }
    
    // --- Abstract methods implementation 
    
    protected void completeEnvironmentVariables(final Map<String, String> envMap) {
      final Integer procs = getAttrManager().getAttribute(JobAttributes.getNumberOfProcessesAttributeDefinition()).getValue();
      envMap.put("X10LAUNCHER_NPROCS", String.valueOf(procs)); //$NON-NLS-1$
      final String hostFile = getAttrManager().getAttribute(LaunchAttributes.getHostFileAttr()).getValue();
      if (hostFile.length() == 0) {
        final List<String> hostList = getAttrManager().getAttribute(LaunchAttributes.getHostListAttr()).getValue();
        final StringBuilder sb = new StringBuilder();
        int i = 0;
        for (final String hostName : hostList) {
          if (i == 1) {
            sb.append(',');
          } else {
            i = 1;
          }
          sb.append(hostName);
        }
        envMap.put("X10LAUNCHER_HOSTLIST", sb.toString()); //$NON-NLS-1$
      } else {
        envMap.put("X10LAUNCHER_HOSTFILE", hostFile); //$NON-NLS-1$
      }
    }

  }
  
  private final class SSHValidationJob extends Job {
    
    SSHValidationJob(final IProgressMonitor mainMonitor, final Collection<String> hostNames) {
      super(Messages.SXRS_SSHValidationTaskName);
      this.fMainMonitor = mainMonitor;
      this.fHostNames = hostNames;
    }

    // --- Abstract methods implementation
    
    protected IStatus run(final IProgressMonitor monitor) {
      for (final String hostName : this.fHostNames) {
        final List<String> command = new ArrayList<String>();
        command.add("ssh"); //$NON-NLS-1$
        command.add(hostName);
        command.add("hostname"); //$NON-NLS-1$
        
        final IRemoteProcessBuilder processBuilder = createProcessBuilder(command, null /* workingDirectory */);
        IRemoteProcess process = null;
        try {
          process = processBuilder.start();
        } catch (IOException except) {
          return new Status(IStatus.ERROR, RMSActivator.PLUGIN_ID, 
                            NLS.bind(Messages.SXRS_ProcessStartError, getCommandString(command)), except);
        }
          
        try {
          while (! process.isCompleted() && ! this.fMainMonitor.isCanceled()) {
            try {
              synchronized (this) {
                wait(100);
              }
            } catch (InterruptedException except) {
              // Simply forgets.
            }
          }
          
          if (process.exitValue() != 0) {
            return new Status(IStatus.ERROR, RMSActivator.PLUGIN_ID, 
                              NLS.bind(Messages.SXRS_ValidationError, getCommandString(command)));
          }
        } finally {
          process.destroy();
        }
      }
      return Status.OK_STATUS;
    }
    
    // --- Private code
    
    private String getCommandString(final List<String> command) {
      final StringBuilder sb = new StringBuilder();
      int i = 0;
      for (final String cmd : command) {
        if (i == 1) {
          sb.append(' ');
        } else {
          i = 1;
        }
        sb.append(cmd);
      }
      return sb.toString();
    }
    
    // --- Fields
    
    private final IProgressMonitor fMainMonitor;
    
    private final Collection<String> fHostNames;
    
  }

}
