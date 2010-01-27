/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package org.eclipse.imp.x10dt.ui.launch.core.wizards;

/**
 * Common base for wizard pages related to X10 Platform Configuration.
 * 
 * @author egeay
 */
interface IPlaftormConfWizardPage {
  
  boolean performFinish(final X10PlatformConfiguration platformConfiguration);

}
