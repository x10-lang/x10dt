/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package org.eclipse.imp.x10dt.ui.launch.core.builder.target_op;

import static org.eclipse.imp.x10dt.ui.launch.core.utils.PTPConstants.LOCAL_CONN_SERVICE_ID;
import static org.eclipse.imp.x10dt.ui.launch.core.utils.PTPConstants.REMOTE_CONN_SERVICE_ID;

import org.eclipse.ptp.remote.core.IRemoteConnection;
import org.eclipse.ptp.remote.core.IRemoteServices;
import org.eclipse.ptp.remote.core.PTPRemoteCorePlugin;

/**
 * Factory method(s) to create implementation(s) of {@link ITargetOpHelper}.
 * 
 * @author egeay
 */
public final class TargetOpHelperFactory {
  
  /**
   * Creates an implementation of {@link ITargetOpHelper} for a general Unix or Cygwin system depending of the <i>isCygwin</i>
   * flag.
   * 
   * @param isLocal Indicates if the target system is local or remote.
   * @param isCygwin Indicates if the target system is a Cygwin or a general Unix system.
   * @param connectionName The name of the connection to consider.
   * @return A non-null object.
   */
  public static ITargetOpHelper create(final boolean isLocal, final boolean isCygwin, final String connectionName) {
    final String rmServicesId = isLocal ? LOCAL_CONN_SERVICE_ID : REMOTE_CONN_SERVICE_ID;
    final IRemoteServices rmServices = PTPRemoteCorePlugin.getDefault().getRemoteServices(rmServicesId);
    final IRemoteConnection rmConnection = rmServices.getConnectionManager().getConnection(connectionName);
    if (isCygwin) {
      return new CygwinTargetOpHelper(rmServices, rmConnection);
    } else {
      return new DefaultTargetOpHelper(rmServices, rmConnection);
    }
  }

}
