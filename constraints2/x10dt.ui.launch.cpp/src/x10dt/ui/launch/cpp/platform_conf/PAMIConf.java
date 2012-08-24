/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.platform_conf;

import org.eclipse.ptp.rmsystem.IResourceManagerConfiguration;

import x10dt.ui.launch.core.utils.PTPConstants;


final class PAMIConf extends AbstractHostsBasedConf implements IPAMIConf {

  // --- ICommunicationInterfaceConf's interface methods implementation
  
  public boolean hasSameCommunicationInterfaceInfo(final IResourceManagerConfiguration rmConfiguration) {
    return PTPConstants.PAMI_SERVICE_PROVIDER_ID.equals(rmConfiguration.getResourceManagerId());
  }

  public void visitInterfaceOptions(final ICIConfOptionsVisitor visitor) {
    visitor.visit(this);
  }

  // --- Abstract methods implementation
  
  AbstractCommunicationInterfaceConfiguration copy() {
    return new PAMIConf(this);
  }

  // --- Private code
  
  PAMIConf() {}
  
  PAMIConf(final PAMIConf original) {
    super(original);
  }
  
  PAMIConf(final IResourceManagerConfiguration rmConf) {
    super(rmConf);
  }

  public boolean shouldUseHostSection() {
    return true;
  }

}
