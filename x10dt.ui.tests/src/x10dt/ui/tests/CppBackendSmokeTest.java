/***********************************s******************************************
 * Copyright (c) 2011 IBM Corporation.eLocal
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Rick Lesniak (lesniakr@us.ibm.com) - initial API and implementation,
 *    									   adapted from JavaBackEndSmokeTest.java 
*******************************************************************************/

package x10dt.ui.tests;


import java.util.List;
import java.util.Arrays;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;

import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import x10dt.core.utils.Timeout;
import x10dt.tests.services.swbot.utils.SWTBotUtils;

/**
 * @author lesniakr@us.ibm.com
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
						ImportCppArchiveTest.class,
						CppBackendTestConfigs.class
        			})
        
public class CppBackendSmokeTest extends ImportX10Archive {

	@BeforeClass
	public static void beforeClass() throws Exception {
		topLevelBot = new SWTWorkbenchBot();
		SWTBotUtils.closeWelcomeViewIfNeeded(topLevelBot);
		topLevelBot.perspectiveByLabel("X10").activate();
		SWTBotPreferences.TIMEOUT = Timeout.SIXTY_SECONDS;
	}
	
	@AfterClass
	public static void afterClass() throws Exception {
		SWTBotUtils.saveAllDirtyEditors(topLevelBot);
		SWTBotUtils.closeAllEditors(topLevelBot);
		SWTBotUtils.closeAllShells(topLevelBot);
	}

}