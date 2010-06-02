/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package org.eclipse.imp.x10dt.ui.launch.cpp.editors;

import org.eclipse.imp.x10dt.ui.launch.core.platform_conf.EValidationStatus;
import org.eclipse.imp.x10dt.ui.launch.cpp.platform_conf.IX10PlatformConfWorkCopy;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.forms.widgets.FormToolkit;


interface ICITypeConfigurationPart {
  
  public void connectionChanged(final boolean isLocal, final String remoteConnectionName, 
                                final EValidationStatus validationStatus);
  
  public void create(final IManagedForm managedForm, final FormToolkit toolkit, final Composite parent,
                     final IX10PlatformConfWorkCopy x10PlatformConf, final AbstractCommonSectionFormPart formPart);
  
  public void dispose(final IMessageManager ... messageManagers);
  
  public String getServiceProviderId();
  
  public boolean hasCompleteInfo();

}
