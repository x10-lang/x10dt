/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.rms.provider;

import org.eclipse.ptp.rm.core.rmsystem.AbstractRemoteResourceManagerConfiguration;
import org.eclipse.ptp.services.core.IServiceProvider;

import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.cpp.rms.Messages;
import x10dt.ui.launch.rms.core.provider.IX10RMConfiguration;

/**
 * Service provider implements for the PAMI resource manager control.
 * 
 * @author egeay
 */
public final class PAMIResourceManagerConfiguration extends AbstractRemoteResourceManagerConfiguration 
                                          implements /*IServiceProvider,*/ IX10RMConfiguration {
 
  /**
   * Creates the service provider with a default description.
   */
  public PAMIResourceManagerConfiguration(String namespace, IServiceProvider provider) {
	  super(namespace, provider);
	  setDescription(Messages.PSP_ProviderDescr);
  }

  // --- Abstract methods definition
  
  public void setDefaultNameAndDesc() {
    final StringBuilder sb = new StringBuilder();
    sb.append(Messages.PSP_ProviderName);
    final String connection = getConnectionName();
    if (connection != null && ! Constants.EMPTY_STR.equals(connection)) {
      sb.append('@').append(connection);
    }
    setName(sb.toString());
    setDescription(Messages.PSP_ProviderDescr);
  }


 /* public IResourceManagerControl createResourceManager() {
    final IPUniverse universe = (IPUniverse) PTPCorePlugin.getDefault().getModelManager().getUniverse();
    return new PAMIResourceManager(String.valueOf(universe.getNextResourceManagerId()), universe, this);
  }
  
  // --- Overridden methods
  
  public IServiceProviderWorkingCopy copy() {
    return new PAMIServiceProvider(this);
  }
  
  // --- Private code
  
  private PAMIServiceProvider(final IServiceProvider provider) {
    super(provider);
  } */

}

