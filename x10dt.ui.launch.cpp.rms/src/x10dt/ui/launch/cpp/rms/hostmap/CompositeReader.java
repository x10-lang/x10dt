/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.rms.hostmap;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ptp.remote.core.IRemoteConnection;
import org.eclipse.ptp.remote.core.IRemoteServices;

import x10dt.ui.launch.cpp.rms.provider.IX10RuntimeSystem;


final class CompositeReader implements IHostMapReader {

  CompositeReader(final IHostMapReader ... readers) {
    this.fReaders = readers;
  }
  
  // --- Interface methods implementation
  
  public Collection<String> loadMap(final IX10RuntimeSystem runtimeSystem, final IRemoteConnection connection, 
                                    final IRemoteServices remoteServices, final String machineId, 
                                    final IProgressMonitor monitor) {
    for (final IHostMapReader reader : this.fReaders) {
      final Collection<String> hostNames = reader.loadMap(runtimeSystem, connection, remoteServices, machineId, monitor);
      if (! hostNames.isEmpty()) {
        return hostNames;
      }
    }
    return Collections.emptyList();
  }
  
  // --- Fields
  
  private final IHostMapReader[] fReaders;

}
