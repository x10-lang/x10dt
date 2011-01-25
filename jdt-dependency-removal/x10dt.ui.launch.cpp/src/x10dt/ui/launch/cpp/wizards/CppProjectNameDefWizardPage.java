/*****************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                       *
 * All rights reserved. This program and the accompanying materials          *
 * are made available under the terms of the Eclipse Public License v1.0     *
 * which accompanies this distribution, and is available at                  *
 * http://www.eclipse.org/legal/epl-v10.html                                 *
 *****************************************************************************/
package x10dt.ui.launch.cpp.wizards;

import x10dt.ui.launch.core.wizards.X10ProjectNameDefWizardPage;
import x10dt.ui.launch.cpp.LaunchMessages;


final class CppProjectNameDefWizardPage extends X10ProjectNameDefWizardPage {
	CppProjectNameDefWizardPage() {
		super();
	    setTitle(LaunchMessages.PWFP_PageTitle);
	    setDescription(LaunchMessages.PWFP_PageDescription);
	  }
}
