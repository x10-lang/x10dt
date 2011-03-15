/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.platform_conf;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.ptp.rmsystem.IResourceManagerConfiguration;

import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.utils.CodingUtils;
import x10dt.ui.launch.core.utils.PTPConstants;


final class SocketsConf extends AbstractCommunicationInterfaceConfiguration implements ISocketsConf {

  // --- ICommunicationInterfaceConf's interface methods implementation
  
  public boolean hasSameCommunicationInterfaceInfo(final IResourceManagerConfiguration rmConfiguration) {
    return PTPConstants.SOCKETS_SERVICE_PROVIDER_ID.equals(rmConfiguration.getResourceManagerId());
  }

  public void visitInterfaceOptions(final ICIConfOptionsVisitor visitor) {
    visitor.visit(this);
  }
  
  // --- ISocketConf's interface methods implementation
  
  public String getHostFile() {
    return (this.fHostFile == null) ? Constants.EMPTY_STR : this.fHostFile;
  }
  
  public String getHosts() {
    return this.fHosts;
  }
  
  public List<String> getHostsAsList() {
    if (this.fHosts == null) {
      return Collections.<String>emptyList();
    } else {
      return Arrays.asList(this.fHosts.split(",")); //$NON-NLS-1$
    }
  }
  
  public boolean shouldUseHostFile() {
    return this.fShouldUseHostFile;
  }

  // --- Abstract methods implementation
  
  AbstractCommunicationInterfaceConfiguration copy() {
    return new SocketsConf(this);
  }
  
  // --- Overridden methods
 
  public boolean equals(final Object rhs) {
    if ((rhs == null) || (! (rhs instanceof SocketsConf)) || ! super.equals(rhs)) {
      return false;
    }
    final SocketsConf rhsObj = (SocketsConf) rhs;
    if (this.fShouldUseHostFile == rhsObj.fShouldUseHostFile) {
      if (this.fShouldUseHostFile) {
        return CodingUtils.equals(this.fHostFile, rhsObj.fHostFile);
      } else {
        return CodingUtils.equals(this.fHosts, rhsObj.fHosts);
      }
    } else {
      return false;
    }
  }
  
  public int hashCode() {
    return 65747 + CodingUtils.generateHashCode(3454665, this.fHostFile, this.fHosts);
  }
  
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append(super.toString()).append("\nShould Use HostFile: ").append(this.fShouldUseHostFile) //$NON-NLS-1$
      .append("\nHost file: ").append(this.fHostFile).append("\nHosts: ").append(this.fHosts); //$NON-NLS-1$ //$NON-NLS-2$
    return sb.toString();
  }

  // --- Private code
  
  SocketsConf() {}
  
  SocketsConf(final SocketsConf original) {
    super(original);
    this.fHostFile = original.fHostFile;
    this.fHosts = original.fHosts;
    this.fShouldUseHostFile = original.fShouldUseHostFile;
  }
  
  SocketsConf(final IResourceManagerConfiguration rmConf) {
    super(rmConf);
  }
  
  // --- Fields
  
  String fHostFile;
  
  String fHosts;
  
  boolean fShouldUseHostFile;

}
