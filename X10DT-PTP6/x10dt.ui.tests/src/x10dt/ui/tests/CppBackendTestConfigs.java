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
import x10dt.ui.tests.XMLPlatformConfigurations.PlatformConfig;

/**
 * @author lesniakr@us.ibm.com
 */

@RunWith(Parameterized.class)
public class CppBackendTestConfigs extends ImportX10Archive {

	private static String defaultConfigsFile = "CppSmokeTestConfigs.xml";  //$NON-NLS-1$	//default configs file name. test accepts alternate file name as a VM argument
																							//  e.g. "-DconfigsFile=<file name>"  NB: this is a VM argument, not a program argument!!

	private static XMLPlatformConfigurations xmlPlatformConfigs;
	private PlatformConfig platformConfig;

	//	@BeforeClass
	//	public static void beforeClass() throws Exception {
	//		topLevelBot = new SWTWorkbenchBot();
	//		SWTBotUtils.closeWelcomeViewIfNeeded(topLevelBot);
	//		topLevelBot.perspectiveByLabel("X10").activate();
	//		SWTBotPreferences.TIMEOUT = Timeout.SIXTY_SECONDS;
	//	}
	//	
		@After
		public void after() throws Exception {
	//		SWTBotUtils.closeAllEditors(topLevelBot);
			SWTBotUtils.closeAllShells(topLevelBot);
			if (platformConfig.useLocalConnection == false) {
				cleanRemoteOutputFiles(platformConfig);
			}
		}
	
		@AfterClass
		public static void afterClass() throws Exception {
	//		SWTBotUtils.saveAllDirtyEditors(topLevelBot);
			SWTBotUtils.closeAllShells(topLevelBot);
		}

	/*
	 * get the name of the xml configuration file. Optionally set as a system property in VM arguments
	 */
	public static String getConfigFileName() {
		String argConfigsFile = System.getProperty("configsFile");	//see if we have a command line argument
		if (argConfigsFile == null)
			return defaultConfigsFile;
		else 
			return argConfigsFile;			
	}


	/*
	 * This sets up a separate instance of this test class (i.e., CppBackendTestConfigs) for every instance
	 * of a <config> element in the xml file.  JUnit takes care of running each instance sequentially
	 */
	@Parameters
	public static Collection<Node[]> data() {
		List<Node[]> configElements = new ArrayList<Node[]>();
		xmlPlatformConfigs = new XMLPlatformConfigurations(getConfigFileName());
		NodeList xmlConfigList = xmlPlatformConfigs.getElementsByTagName("config");
		if (xmlConfigList != null) {
			for (int xmlConfigNum = 0; xmlConfigNum < xmlConfigList.getLength(); xmlConfigNum++) {
				Node[] n = (Node[])Array.newInstance(Node.class, 1);
				n[0] = xmlConfigList.item(xmlConfigNum);
				configElements.add(n);
			}
		}
		return configElements;
	}	

	/*
	 * Constructs a single instance of this test class, representing a single <config> element in the xml file.
	 * JUnit takes care of running each instance as an individual test class
	 */
	public CppBackendTestConfigs(Node configNode) throws IOException {
		platformConfig = xmlPlatformConfigs.getConfig((Element)configNode);	
	}

	//
	@Test
	//Test a platform configuration.
	public void test_Configuration() throws Exception {
		System.out.println("Now testing configuration " + platformConfig.configName);
		ConfigureAndRunCppProject(xmlPlatformConfigs.projectName, xmlPlatformConfigs.className, 
				platformConfig, xmlPlatformConfigs.expectedOutput); 
		System.out.println("Configuration test '" + platformConfig.configName + "' complete");
	}
}