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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;
import java.util.ListIterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.waits.WaitForView;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEclipseEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotWorkbenchPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCTabItem;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewReference;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import x10dt.core.utils.Timeout;
import x10dt.tests.services.swbot.constants.LaunchConstants;
import x10dt.tests.services.swbot.constants.ViewConstants;
import x10dt.tests.services.swbot.constants.WizardConstants;
import x10dt.tests.services.swbot.utils.SWTBotUtils;
import x10dt.ui.tests.XMLPlatformConfigurations.PlatformConfig;
import x10dt.ui.tests.SmokeTestSetup.TypeSearchInfo;
import x10dt.ui.tests.utils.EditorMatcher;
import x10dt.ui.tests.waits.X10DT_Conditions;

/**
 * @author lesniakr@us.ibm.com
 */

public class ImportX10Archive extends X10DTTestBase {

/*
	 * Support methods for testing x10 projects imported from archives
	 */

//TODO: pass in constants as arguments, so we can use this method in other test modules
	public void importArchive(BackEndType backEnd, String projectName, String archiveName, String classSrcfileName) throws Exception {

		Boolean createHello = false;
		String archivePath = null;

		System.out.println("Import: find archive file '" + archiveName + "'");
		// Locate the archive file in the file system - get the full file path
		ClassLoader cl = getClass().getClassLoader();		//archive file must be on the build path
		URL archiveURL = cl.getResource(archiveName);	//find the file
		if (archiveURL != null) {
			archivePath = FileLocator.toFileURL(archiveURL).getPath();	//turn the net URL into a file path
			System.out.println("full path to archive = " + archivePath);
		}
		else {
			Assert.fail("archive file '" + archiveName + "' not found"); 		  
		}


		System.out.println("creating project '" + projectName + "'");
		if (backEnd == BackEndType.javaBackEnd) {
			// create a java back end project to import the archive into
			createJavaBackEndProject(projectName, createHello);
		}
		else { // create a C++ back end project to import the archive into
			createCPPBackEndProject(projectName, createHello);
		}

		if (createHello) {
			topLevelBot.waitUntil(Conditions.waitForEditor(new EditorMatcher("Hello.x10")));
		}

		System.out.println("Import archive file");
		//import the archive
		importArchiveToX10Project(archivePath, projectName + "/src", true);

		System.out.println("wait for import build to finish");

		waitForFamilyJobToFinish(ResourcesPlugin.FAMILY_AUTO_BUILD);	//wait for build to finish

		System.out.println("wait for indexer to finish");

//		waitForFamilyJobToFinish(SearchCoreActivator.FAMILY_X10_INDEXER);	//3/11/11 currently blocks forever - indexer is always rescheduled, so it never 'finishes

		System.out.println("import build is finished");

		//open the imported file in an editor view
		openX10FileInEditor(projectName, classSrcfileName);

		//wait for the editor to open
		topLevelBot.waitUntil(Conditions.waitForEditor(new EditorMatcher(classSrcfileName)), 60000, 500);

	}

  //
  // Check that the type indexer is working
  //
  public boolean validateOpenType(List<TypeSearchInfo> checkList) throws Exception {

	  boolean okSoFar = true;

	  if (checkList != null) {
		  // look for a few types by using the 'Open X10 Type...' dialog 
		  for (Iterator<TypeSearchInfo> it = checkList.iterator(); (it.hasNext() && okSoFar);)
		  {
			  TypeSearchInfo searchSet = it.next();
			  //look for a specific source class
			  okSoFar &= openX10Type(searchSet.typeName, searchSet.searchString, searchSet.expectToFind);
			  if (okSoFar)
				  okSoFar &= verifySourceLine(searchSet.fileName, searchSet.typeDeclaration);	  
		  }
	  }
	  return okSoFar;
  }

  public boolean validateTypeSearch(List<TypeSearchInfo> checkList) throws Exception {

	  boolean okSoFar = true;

	  if (checkList != null) {
		  // now, do it all over again by looking for a few types using the X10 Search dialog 
		  for (Iterator<TypeSearchInfo> it = checkList.iterator(); (it.hasNext() && okSoFar);)
		  {
			  TypeSearchInfo searchSet = it.next();
			  //look for a specific source class
			  okSoFar &= searchForX10Type(searchSet.typeName, searchSet.searchString, searchSet.expectToFind);
			  if (okSoFar)
				  okSoFar &= verifySourceLine(searchSet.fileName, searchSet.typeDeclaration);	  
		  }
	  }
	  return okSoFar;
  }

/*
 * Verification routines
 */

  //	Verify console output from application
  //
  public static boolean verifyConsoleOutput(List<String> expectedLines) { //throws X10DT_Test_Exception {

	  boolean matches = true;				//verification result. Assume the best!

	  //TODO: detect and handle "Linking Prerequisite Check" dialog here.
	  //		indicates compile errors in project.  Dismiss dialog and fail test
	  
	  System.out.println("verifying X10 program's console output");
	  //used to synchronize expected text block with actual output
	  int startAtLine = 0;					//First line number in actual output to begin compare operation
	  String firstExpectedLine = null;		//Text of first expected output line

	  //used in matching operation
	  int currentLine = 0;					//Current line number in matching operation
	  String currentActualLine = null;		//Text of current actual output line
	  String currentExpectedLine = null;	//Text of current expected output line

	  // look for the Console view, and check the output
	  final Matcher<IViewReference> withPartName = WidgetMatcherFactory.withPartName(ViewConstants.CONSOLE_VIEW_NAME);
	  final WaitForView waitForConsole = Conditions.waitForView(withPartName);

	  topLevelBot.waitUntil(waitForConsole);

	  //find the console view
	  SWTBotView consoleView = new SWTBotView(waitForConsole.get(0), topLevelBot);
	  //wait for some output to show up
	  consoleView.bot().waitUntil(X10DT_Conditions.viewHasText(consoleView), 90000, 500);

	  dumpConsole(topLevelBot);		// echo program's console output to the development console

	  //load the text of the actual output
	  List<String> actualLines = consoleView.bot().styledText().getLines();
	  Iterator<String> actualLine = actualLines.listIterator(0);
	  Iterator<String> expectedLine = expectedLines.listIterator(0);

	  if (expectedLine.hasNext())  { //don't bother looking if there's nothing to find.

		  //Before we start comparing actual output with expected output, there may be some
		  // trace output from the runtime. We can skip over this by scanning for a line
		  // which matches the first line we're expecting

		  startAtLine = -1;	//we pre-increment this, so we'll start at one less
		  matches = false;	// init starting condition
		  firstExpectedLine = expectedLine.next();
		  while (!matches && actualLine.hasNext()) {
			  startAtLine++;
			  matches = firstExpectedLine.equals(actualLine.next());
		  }

		  Assert.assertTrue("Could not find an output line to match'" + firstExpectedLine + "'", matches);

		  //OK, so we found a match to the expected starting line
		  //loop and compare actual and expected one line at a time
		  currentLine = startAtLine;	// track for possible use in error message
		  currentActualLine = "";		// track for possible use in error message
		  currentExpectedLine = "";		// track for possible use in error message
		  while (matches && expectedLine.hasNext() && actualLine.hasNext()) {
			  currentLine++;
			  currentActualLine = actualLine.next();
			  currentExpectedLine = expectedLine.next();
			  matches = currentActualLine.equals(currentExpectedLine);
		  }

		  //assert if everything didn't match properly
		  Assert.assertTrue("Mismatch at line " + currentLine + 
				  "\n    actual: '" + currentActualLine + 
				  "\n    expected: '" + currentExpectedLine + "'", matches);

	  }

	  return matches;
  }


  //	Verify source line in editor
  //
  private Boolean verifySourceLine(String fileName, String expectedText)
  {
	  Boolean matches = false;
	  
	  SWTBotEclipseEditor fArchiveFileEditor = topLevelBot.editorByTitle(fileName).toTextEditor();

	  int timeout = 20;	//wait for up to 10 sec (20 * 500ms) for editor window to become active 
	  while (( ! fArchiveFileEditor.isActive()) && (--timeout != 0))
	  	topLevelBot.sleep(500);

		  matches = expectedText.equals(fArchiveFileEditor.getTextOnCurrentLine().trim());

	  Assert.assertTrue("Expected source line does not match actual source line in file '" + fileName +  "':" +
			  "\n    actual: '" + fArchiveFileEditor.getTextOnCurrentLine() + 
			  "'\n    expected: '" + expectedText + "'", matches);

	  return matches;
  }
  
  /* 
   * C++ back end platform configuration
   * 
   */
  
  //  one or more C++ backend platform configurations are loaded from an xml file. A run is made for each one
  public Boolean ConfigureAndRunCppProject(String projectName, String className, PlatformConfig platformConfig, List<String> expectedOutput) throws IOException, Exception {

	  Boolean result = false;

	  if (platformConfig != null) {
		  setCppPlatformConnectionConfig(projectName, platformConfig);
		  result = RunCppProject(projectName, className, platformConfig, expectedOutput);
	  }

	  return result;
  }


  //  one or more C++ backend platform configurations are loaded from an xml file. A run is made for each one
  public Boolean RunCppProject(String projectName, String className, PlatformConfig platformConfig, List<String> expectedOutput) throws IOException {

	  System.out.println("open Parallel Runtime perspective");
	  //the program output will be in the Console view of the Parallel Runtime perspective, so let's get into that
	  openPerspective(topLevelBot, "Parallel Runtime");	  //switch to Parallel Runtime perspective

	  clearConsoleView();		//get rid of any stale output in the console view

	  System.out.println("create and run '" + platformConfig.configName + "'");
	  //run the program
	  createAndRunCPPBackEndLaunchConfig(platformConfig.configName, projectName, className, platformConfig.numPlaces, platformConfig.programArgs);			  

	  System.out.println("verify console output");
	  //Well, let's see if it worked
	  // verify that the actual output matches the expected output
	  boolean match = verifyConsoleOutput(expectedOutput); //$NON-NLS-1$
	  Assert.assertTrue("ImportArchiveTest: Console output does not match", match); //$NON-NLS-1$

	  System.out.println("Go back to the X10 perspective");
	  //Go back to the X10 perspective
	  topLevelBot.perspectiveByLabel("X10").activate();

	  return match;
  }
 
}