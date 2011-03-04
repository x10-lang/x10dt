/***********************************s******************************************
 * Copyright (c) 2010 IBM Corporation.eLocal
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

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.w3c.dom.Document;

import x10dt.core.utils.Timeout;
import x10dt.tests.services.swbot.utils.SWTBotUtils;

/**
 * @author lesniakr@us.ibm.com
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class ImportCppArchiveTest extends ImportX10Archive {

	private static SmokeTestSetup testSetup;
	private static Document xmlConfigurations;		//loaded from XML - this document contains the specific settings of one or more x10 platform configurations
	private static final String PLATFORM_CONFIGS_FILE = "CppSmokeTestConfigs.xml";  //$NON-NLS-1$


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
	}

	/*
	 * Import archive into CPP back end project and check out type indexing
	 */

	//
	@Test
	public void importCPPArchiveTest() throws Exception {
		xmlConfigurations = loadXML(PLATFORM_CONFIGS_FILE);
		testSetup = new SmokeTestSetup(xmlConfigurations);

		importArchive(BackEndType.cppBackEnd, testSetup.projectName, testSetup.archiveName, testSetup.classSourceFileName);
	}

//	//
//	@Test
//	public void test_CPPOpenType() throws Exception {
//		validateOpenType(testSetup.declarationCheckList);
//	}
//
//	@Test
//	public void test_CPPSearchType() throws Exception {
//		validateTypeSearch(testSetup.declarationCheckList);
//	}
}