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

final class PAMITypeConfigPart extends HostFileAndListTypeConfigPart  implements ICITypeConfigurationPart {

  // --- Interface methods implementation
  
  public final void create(final IManagedForm managedForm, final FormToolkit toolkit, final Composite parent, 
                           final AbstractCommonSectionFormPart formPart) {
    super.create(managedForm, toolkit, parent, formPart);
    // RMF Force to use a hostfile for now (not sure host-lists are supported)
    //this.fHostFileBt.setSelection(true);
    //this.fLoadLevelerBt.setSelection(false);
//    this.fHostListBt.setSelection(false);
   // this.fHostListBt.setEnabled(false);
   // this.fHostFileText.setEnabled(true);
   // this.fHostFileBrowseBt.setEnabled(true);
   // this.fLoadLevelerText.setEnabled(true);
   // this.fLoadLevelerBrowseBt.setEnabled(true);
//    this.fHostListBt.notify();
  }

  @Override
  protected void initializeControls(AbstractCommonSectionFormPart formPart, IHostsBasedConf socketsConf, Button addButton, Button removeButton) {
    doInitializeControls(formPart, socketsConf, true, true, true, addButton, removeButton);
  }

  public String getServiceProviderId() {
    return PTPConstants.PAMI_SERVICE_PROVIDER_ID;
  }
  
  public boolean hasCompleteInfo() {
   if (this.fLoadLevelerBt.getSelection()){
     return this.fLoadLevelerText.getText().length() > 0;
   } else {
    if (this.fHostFileBt.getSelection()) {
      return this.fHostFileText.getText().length() > 0;
    } else {
      return this.fHosts.size() > 0;
    }
   }
  }
  
  // --- Fields
  
}
