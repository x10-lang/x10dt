/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.rms.launch_configuration;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.ptp.core.elements.IResourceManager;
import org.eclipse.ptp.launch.ui.extensions.AbstractRMLaunchConfigurationFactory;
import org.eclipse.ptp.launch.ui.extensions.IRMLaunchConfigurationDynamicTab;

import x10dt.ui.launch.cpp.rms.provider.StandaloneResourceManager;

/**
 * Provides the implementation for dynamic configuration tabs for the X10 Standalone Resource Manager.
 * 
 * @author egeay
 */
public final class StandaloneRMLaunchConfigurationFactory extends AbstractRMLaunchConfigurationFactory {

  // --- Abstract methods implementation
  
  public Class<? extends IResourceManager> getResourceManagerClass() {
    return StandaloneResourceManager.class;
  }

  protected IRMLaunchConfigurationDynamicTab doCreate(final IResourceManager resourceManager,
                                                      final ILaunchConfigurationDialog launchDialog) throws CoreException {
    return new StandaloneRMLaunchConfigurationDynamicTab();
  }

}
