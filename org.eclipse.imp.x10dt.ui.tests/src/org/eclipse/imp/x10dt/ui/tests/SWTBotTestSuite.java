/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Robert Fuhrer (rfuhrer@watson.ibm.com) - initial API and implementation
 *******************************************************************************/

package org.eclipse.imp.x10dt.ui.tests;

import org.eclipse.imp.x10dt.ui.editor.QuickOutlineTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * JUnit suite containing all the SWTBot-based tests for x10dt.ui
 * @author rfuhrer@watson.ibm.com
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ JavaBackEndSmokeTest.class, QuickOutlineTests.class })
public final class SWTBotTestSuite {
}