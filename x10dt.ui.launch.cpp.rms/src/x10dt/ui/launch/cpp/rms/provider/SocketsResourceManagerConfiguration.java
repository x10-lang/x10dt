/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.rms.provider;

import org.eclipse.ptp.core.PTPCorePlugin;
import org.eclipse.ptp.core.elements.IPUniverse;
import org.eclipse.ptp.rmsystem.IResourceManagerControl;
import org.eclipse.ptp.rm.core.rmsystem.AbstractRemoteResourceManagerConfiguration;
import org.eclipse.ptp.services.core.IServiceProvider;
import org.eclipse.ptp.services.core.IServiceProviderWorkingCopy;

import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.cpp.rms.Messages;
import x10dt.ui.launch.rms.core.provider.IX10RMConfiguration;

/**
 * Service provider implements for the X10 Sockets resource manager control.
 * 
 * @author egeay
 */
public final class SocketsResourceManagerConfiguration extends AbstractRemoteResourceManagerConfiguration
                                          implements /*IServiceProvider, */IX10RMConfiguration {
 
  /**
   * Creates the service provider with a default description.
   */
  public SocketsResourceManagerConfiguration(String namespace, IServiceProvider provider) {
	super(namespace, provider);
    setDescription(Messages.SSS_SocketsRMDescr);
  }

  // --- Abstract methods definition
  
  public void setDefaultNameAndDesc() {
    final StringBuilder sb = new StringBuilder();
    sb.append(Messages.SSS_Sockets);
    final String connection = getConnectionName();
    if (connection != null && ! Constants.EMPTY_STR.equals(connection)) {
      sb.append('@').append(connection);
    }
    setName(sb.toString());
    setDescription(Messages.SSS_SocketsRMDescr);
  }

  /*
  public IResourceManagerControl createResourceManager() {
    final IPUniverse universe = (IPUniverse) PTPCorePlugin.getDefault().getModelManager().getUniverse();
    return new SocketsResourceManager(String.valueOf(universe.getNextResourceManagerId()), universe, this);
  }
  
  // --- Overridden methods
  
  public IServiceProviderWorkingCopy copy() {
    return new SocketsServiceProvider(this);
  }
  
  // --- Private code
  
  private SocketsServiceProvider(final IServiceProvider provider) {
    super(provider);
  }*/

}
