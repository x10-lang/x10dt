/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.editors;

import org.eclipse.ptp.services.core.IServiceProvider;


interface IServiceConfigurationListener {
  
  void serviceConfigurationModified(final String textContent);
  
  void serviceConfigurationSelected(final IServiceProvider serviceProvider);

}
