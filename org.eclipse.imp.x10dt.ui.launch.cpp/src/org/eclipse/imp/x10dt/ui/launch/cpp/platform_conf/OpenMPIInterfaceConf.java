/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package org.eclipse.imp.x10dt.ui.launch.cpp.platform_conf;

import org.eclipse.imp.x10dt.ui.launch.core.utils.CodingUtils;
import org.eclipse.imp.x10dt.ui.launch.cpp.editors.EOpenMPIVersion;
import org.eclipse.ptp.rm.mpi.openmpi.core.rmsystem.IOpenMPIResourceManagerConfiguration;
import org.eclipse.ptp.rmsystem.IResourceManagerConfiguration;


final class OpenMPIInterfaceConf extends MessagePassingInterfaceConf implements IOpenMPIInterfaceConf {

  // --- ICommunicationInterfaceConf's interface methods implementation
  
  public void visitInterfaceOptions(final ICIConfOptionsVisitor visitor) {
    visitor.visit(this);
  }
  
  // --- IOpenMPIInterfaceConf's interface methods implementation
  
  public EOpenMPIVersion getOpenMPIVersion() {
    return this.fOpenMPIVersion;
  }
  
  // --- Abstract methods implementation
  
  AbstractCommunicationInterfaceConfiguration copy() {
    return new OpenMPIInterfaceConf(this);
  }
  
  // --- Overridden methods
  
  public boolean equals(final Object rhs) {
    final boolean isEquals = super.equals(rhs);
    if (isEquals && (rhs instanceof OpenMPIInterfaceConf)) {
      final OpenMPIInterfaceConf rhsObj = (OpenMPIInterfaceConf) rhs;
      return (this.fOpenMPIVersion == rhsObj.fOpenMPIVersion);
    } else {
      return false;
    }
  }
  
  public int hashCode() {
    return super.hashCode() + CodingUtils.generateHashCode(542, this.fOpenMPIVersion);
  }
  
  public boolean hasSameCommunicationInterfaceInfo(final IResourceManagerConfiguration rmConf) {
    final boolean hasSameInfo = super.hasSameCommunicationInterfaceInfo(rmConf);
    if (hasSameInfo && (rmConf instanceof IOpenMPIResourceManagerConfiguration)) {
      final IOpenMPIResourceManagerConfiguration openMPIRMConf = (IOpenMPIResourceManagerConfiguration) rmConf;
      switch (this.fOpenMPIVersion) {
      case EVersion_1_2:
        return IOpenMPIResourceManagerConfiguration.VERSION_12.equals(openMPIRMConf.getVersionId());
      case EVersion_1_3:
        return IOpenMPIResourceManagerConfiguration.VERSION_13.equals(openMPIRMConf.getVersionId());
      case EVersion_1_4:
        return IOpenMPIResourceManagerConfiguration.VERSION_14.equals(openMPIRMConf.getVersionId());
      default:
        return IOpenMPIResourceManagerConfiguration.VERSION_AUTO.equals(openMPIRMConf.getVersionId());
      }
    }
    return false;
  }
  
  public boolean isComplete() {
    if (super.isComplete()) {
      if (this.fOpenMPIVersion == null) {
        return false;
      }
      if ((this.fOpenMPIVersion != EOpenMPIVersion.EAutoDetect) && (! hasData(this.fLaunchCmd) || ! hasData(this.fDebugCmd))) {
        return false;
      }
      return true;
    } else {
      return false;
    }
  }
  
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append(super.toString()).append("\nOpenMPI version: ").append(this.fOpenMPIVersion); //$NON-NLS-1$
    return sb.toString();
  }
  
  // --- Internal code
  
  OpenMPIInterfaceConf() {}
  
  OpenMPIInterfaceConf(final OpenMPIInterfaceConf original) {
    super(original);
    this.fOpenMPIVersion = original.fOpenMPIVersion;
  }
  
  void applyChanges(final IOpenMPIInterfaceConf source) {
    super.applyChanges(source);
    this.fOpenMPIVersion = source.getOpenMPIVersion();
  }
  
  // --- Fields
  
  EOpenMPIVersion fOpenMPIVersion;

}
