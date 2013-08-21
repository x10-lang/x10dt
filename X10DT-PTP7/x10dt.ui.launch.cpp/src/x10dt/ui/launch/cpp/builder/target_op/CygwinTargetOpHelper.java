/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.builder.target_op;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.ptp.remote.core.IRemoteConnection;
import org.eclipse.ptp.remote.core.IRemoteServices;

import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.utils.IProcessOuputListener;

final public class CygwinTargetOpHelper extends AbstractTargetOpHelper implements ITargetOpHelper {

  CygwinTargetOpHelper(final IRemoteServices remoteServices, final IRemoteConnection remoteConnection) {
    super(remoteServices, remoteConnection);
  }
  
  // --- Interface methods implementation

  public String getTargetSystemPath(final String resourcePath) {
    if (resourcePath.startsWith("/cygdrive")) { //$NON-NLS-1$
      return resourcePath;
    }
    if (resourcePath.indexOf('\\') != -1 || resourcePath.indexOf('/') != -1) {
      final List<String> cygpathCmd = new ArrayList<String>();
      cygpathCmd.add("cygpath"); //$NON-NLS-1$
      cygpathCmd.add("-u"); //$NON-NLS-1$
      if (resourcePath.charAt(0) == '/') {
        cygpathCmd.add(resourcePath.substring(1)); 
      } else {
        cygpathCmd.add(resourcePath);
      }
      final StringBuilder output = new StringBuilder();
      try {
        final int returnCode = run(cygpathCmd, new IProcessOuputListener() {
        
          public void readError(final String line) {
          }
        
          public void read(final String line) {
            output.append(line);
          }
        
        }, new NullProgressMonitor());
      
        if ((returnCode == 0) && (output.length() > 0)) {
          return output.toString();
        }
      } catch (Exception except) {
        // Let's forget.
      }
      return resourcePath.replace('\\', '/');
    } else {
      return resourcePath;
    }
  }
    
    public String getCygwinBash(){
      final List<String> cmd = new ArrayList<String>();
      cmd.add("cygpath");
      cmd.add("-m");
      cmd.add("/");
      final StringBuilder output = new StringBuilder();
      try {
        final int returnCode = run(cmd, new IProcessOuputListener() {
        
          public void readError(final String line) {
          }
        
          public void read(final String line) {
            output.append(line);
          }
        
        }, new NullProgressMonitor());
      
        if ((returnCode == 0) && (output.length() > 0)) {
          return output.toString() + "/bin/bash";
        }
      } catch (Exception except) {
        // Let's forget.
      }
      return Constants.EMPTY_STR;
    }
    
  

}
