/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.editors;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

import x10dt.ui.launch.core.utils.PTPConstants;
import x10dt.ui.launch.cpp.platform_conf.IHostsBasedConf;



final class StandaloneTypeConfigPart extends HostFileAndListTypeConfigPart implements ICITypeConfigurationPart {

  // --- Interface methods implementation

  public boolean hasCompleteInfo() {
    return true;
  }

  @Override
  protected void initializeControls(AbstractCommonSectionFormPart formPart, IHostsBasedConf socketsConf, Button addButton, Button removeButton) {
    doInitializeControls(formPart, socketsConf, false, false, false, false, addButton, removeButton);
  }
  public String getServiceProviderId() {
    return PTPConstants.STANDALONE_SERVICE_PROVIDER_ID;
  }

}
