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


import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Arrays;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;

import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import x10dt.core.utils.Timeout;
import x10dt.tests.services.swbot.utils.SWTBotUtils;
import x10dt.ui.tests.SmokeTestSetup;

/**
 * @author lesniakr@us.ibm.com
 */

@RunWith(Parameterized.class)
public class CppBackendTestConfigs extends ImportX10Archive {

	private static final String PLATFORM_CONFIGS_FILE = "CppSmokeTestConfigs.xml";  //$NON-NLS-1$

	private static Document xmlConfigurations;		//loaded from XML - this document contains the specific settings of one or more x10 platform configurations
	private Element xmlConfigElement;		//loaded from XML - a Node containing the settings for a single x10 platform configurations
	private PlatformConfig platformConfig;
	private SmokeTestSetup testSetup;

	//	@BeforeClass
	//	public static void beforeClass() throws Exception {
	//		topLevelBot = new SWTWorkbenchBot();
	//		SWTBotUtils.closeWelcomeViewIfNeeded(topLevelBot);
	//		topLevelBot.perspectiveByLabel("X10").activate();
	//		SWTBotPreferences.TIMEOUT = Timeout.SIXTY_SECONDS;
	//	}
	//	
	//	@After
	//	public void after() throws Exception {
	//		SWTBotUtils.closeAllEditors(topLevelBot);
	//		SWTBotUtils.closeAllShells(topLevelBot);
	//	}
	//
	//	@AfterClass
	//	public static void afterClass() throws Exception {
	//		SWTBotUtils.saveAllDirtyEditors(topLevelBot);
	//	}



	@Parameters
	public static Collection<Node[]> data() {
		List<Node[]> configElements = new ArrayList<Node[]>();
		xmlConfigurations = loadXML(PLATFORM_CONFIGS_FILE);
		NodeList xmlConfigList = xmlConfigurations.getElementsByTagName("config");
		if (xmlConfigList != null) {
			for (int xmlConfigNum = 0; xmlConfigNum < xmlConfigList.getLength(); xmlConfigNum++) {
				Node[] n = (Node[])Array.newInstance(Node.class, 1);
				n[0] = xmlConfigList.item(xmlConfigNum);
				configElements.add(n);
			}
		}
		return configElements;
	}	

	public CppBackendTestConfigs(Node configNode) throws IOException {
		testSetup = new SmokeTestSetup(xmlConfigurations);
		platformConfig = ExtractConfigFromXML((Element)configNode);	
	}

	//
	@Test
	//use local platform config w/ sockets runtime
	public void test_Configuration() throws Exception {
		System.out.println("Now testing configuration " + platformConfig.configName);
		ConfigureAndRunCppProject(testSetup.projectName, testSetup.className, 
				platformConfig, testSetup.expectedOutput); 
		System.out.println("Configuration test '" + platformConfig.configName + "' complete");
	}
}