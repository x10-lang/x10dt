/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.
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
import java.net.URL;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;

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


import x10dt.core.utils.Timeout;
import x10dt.tests.services.swbot.constants.LaunchConstants;
import x10dt.tests.services.swbot.constants.ViewConstants;
import x10dt.tests.services.swbot.constants.WizardConstants;
import x10dt.tests.services.swbot.utils.SWTBotUtils;
import x10dt.ui.tests.utils.EditorMatcher;

/**
 * @author lesniakr@us.ibm.com
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class ImportX10ArchiveTest extends X10DTTestBase {

	private static final String CLASS_NAME = "QSort";  //$NON-NLS-1$

	private static final String CLASS_SRCFILE_NAME = CLASS_NAME + ".x10"; //$NON-NLS-1$

	private static final String ARCHIVE_NAME = "ArchiveTestFile.zip"; //$NON-NLS-1$	// specify file at top level of this workspace
	
	private static final String PROJECT_NAME_JAVABACK = "ArchiveTest_JBack"; //$NON-NLS-1$	//will be created as a new empty project to accept the import
	private static final String PROJECT_NAME_CPPBACK = "ArchiveTest_CPPBack"; //$NON-NLS-1$	//will be created as a new empty project to accept the import

	public static final List<String> EXPECTED_OUTPUT = Arrays.asList(	"size of array: 1000",
																		"array is now sorted", 
																		"++++++ Test succeeded spectacularly!"
																	);
	
	private static class TypeSearchInfo {
		TypeSearchInfo(String searchString, String typeName, Integer expectToFind, String fileName, String typeDeclaration) {
			this.searchString = searchString;
			this.typeName = typeName;
			this.fileName = fileName;
			this.typeDeclaration = typeDeclaration;
			this.expectToFind = expectToFind;
			}
		String	searchString;
		String	typeName;
		String	typeDeclaration;
		String	fileName;
		Integer	expectToFind;
	}
	
	public static final List<TypeSearchInfo> declarationCheckList = Arrays.asList
	(
								/*search*/	/*type*/	 /*# to find*/	/*file*/			/*expected declaration*/
			new TypeSearchInfo("QS*",		"QSortable",	2,			"QSort.x10",		"public class QSortable(theArray: SortableArray)"),
			new TypeSearchInfo("Pos*",		"Position",		1,			"TriangleTest.x10",	"class Position(x: Int, y: Int)"),
			new TypeSearchInfo("Ar*",		"Array",		5,			"Array.x10",		"public final class Array[T] ("),
			new TypeSearchInfo("Ar*",		"ArrayList",	5,			"ArrayList.x10",	"public class ArrayList[T] extends AbstractCollection[T] implements List[T] {"),
			new TypeSearchInfo("QSort*",	"QSort",		2,			"QSort.x10",		"public class QSort")
	);


  @BeforeClass
  public static void beforeClass() throws Exception {
    SWTBotPreferences.KEYBOARD_STRATEGY = "org.eclipse.swtbot.swt.finder.keyboard.SWTKeyboardStrategy"; //$NON-NLS-1$
    topLevelBot = new SWTWorkbenchBot();
    SWTBotUtils.closeWelcomeViewIfNeeded(topLevelBot);
    topLevelBot.perspectiveByLabel("X10").activate();
    SWTBotPreferences.TIMEOUT = Timeout.SIXTY_SECONDS;
  }
  @After
  public void after() throws Exception {
    SWTBotUtils.closeAllEditors(topLevelBot);
    SWTBotUtils.closeAllShells(topLevelBot);
  }

  @AfterClass
  public static void afterClass() throws Exception {
    SWTBotUtils.saveAllDirtyEditors(topLevelBot);
  }
  
  @Test
  public void importCPPArchiveTest() throws Exception {
	  importArchiveTest(BackEndType.cppBackEnd);
  }

  @Test
  public void importJavaArchiveTest() throws Exception {
	  importArchiveTest(BackEndType.javaBackEnd);	  
  }


  public void importArchiveTest(BackEndType backEnd) throws Exception {

	  Boolean createHello = false;
	  String projectName = (backEnd == BackEndType.javaBackEnd)?PROJECT_NAME_JAVABACK:PROJECT_NAME_CPPBACK;
	  String launchName = projectName;
	  String archivePath;
	  
	  String operationMsg = null;			//string describing the current operation, for use in constructing error messages
		  		  	  
	  try
	  {

		  // Locate the archive file in the file system - get the full file path
		  operationMsg = "get archive file path";
		  ClassLoader cl = getClass().getClassLoader();		//archive file must be on the build path
		  URL archiveURL = cl.getResource(ARCHIVE_NAME);	//find the file
		  if (archiveURL != null) {
			  archivePath = FileLocator.toFileURL(archiveURL).getPath();	//turn the net URL into a file path
			  System.out.println("full path to archive = " + archivePath);
		  }
		  else {
			  throw new X10DT_Test_Exception("archive file '" + ARCHIVE_NAME + "' not found");  //turn it into an X10_Test_Exception so the exception structure works			  
		  }
		  
		  
		  if (backEnd == BackEndType.javaBackEnd) {
			  // create a java back end project to import the archive into
			  operationMsg = "create Java Backend Project '" + projectName + "'";
			  createJavaBackEndProject(projectName, createHello);
		  }
		  else { // create a C++ back end project to import the archive into
			  operationMsg = "create C++ Backend Project '" + projectName + "'";
			  createCPPBackEndProject(projectName, createHello);
		  }
		  
		  if (createHello) {
			  topLevelBot.waitUntil(Conditions.waitForEditor(new EditorMatcher("Hello.x10")));
		  }

		  //import the archive
		  operationMsg = "import archive to Project '" + projectName + "'";
		  importArchiveToX10Project(archivePath, projectName + "/src", true);

		  //open the imported file in an editor view
		  operationMsg = "open project '" + projectName + "', file '" + CLASS_SRCFILE_NAME + "'";
		  openX10FileInEditor(projectName, CLASS_SRCFILE_NAME);

		  try
		  {
			  //wait for the editor to open
			  operationMsg = "open '" + CLASS_SRCFILE_NAME + "' editor view";
			  topLevelBot.waitUntil(Conditions.waitForEditor(new EditorMatcher(CLASS_SRCFILE_NAME)));
		  }
		  catch (Exception e)
		  {
			  throw new X10DT_Test_Exception(e.getMessage());  //turn it into an X10_Test_Exception so the exception structure works
		  }

		  //run the program
		  operationMsg = "run configuration '" + launchName + "' of class '" + CLASS_SRCFILE_NAME + "' in project '" + projectName + "'";
		  if (backEnd == BackEndType.javaBackEnd) {
			  createAndRunJavaBackEndLaunchConfig(launchName, projectName, CLASS_NAME);
		  }
		  else {
			  createAndRunCPPBackEndLaunchConfig(launchName, projectName, CLASS_NAME);			  
		  }

		  //Well, let's see if it worked
		  // verify that the actual output matches the expected output
		  operationMsg = "match expected output of class '" + CLASS_NAME + 
		  "' in project '" + projectName + "', using Run Configuration '" + launchName + "'";
		  boolean match = verifyConsoleOutput(EXPECTED_OUTPUT, 1); //$NON-NLS-1$
		  Assert.assertTrue("ImportArchiveTest: Console output does not match", match); //$NON-NLS-1$

		  //OK - the program runs properly.
		  // So let's make sure the type indexer is working
		  
		  // first, look for a few types by using the 'Open X10 Type...' dialog 
		  for (Iterator<TypeSearchInfo> it = declarationCheckList.iterator(); it.hasNext();)
		  {
			  TypeSearchInfo searchSet = it.next();
			  //look for a specific source class
			  operationMsg = "find x10 type '" + searchSet.typeName +  "', using search pattern '" + searchSet.searchString + "'";
			  openX10Type(searchSet.typeName, searchSet.searchString, searchSet.expectToFind);
			  verifySourceLine(searchSet.fileName, searchSet.typeDeclaration);	  
		  }
		  
		  // now, do it all over again by looking for a few types using the X10 Search dialog 
		  for (Iterator<TypeSearchInfo> it = declarationCheckList.iterator(); it.hasNext();)
		  {
			  TypeSearchInfo searchSet = it.next();
			  //look for a specific source class
			  operationMsg = "find x10 type '" + searchSet.typeName +  "', using search pattern '" + searchSet.searchString + "'";
			  searchForX10Type(searchSet.typeName, searchSet.searchString, searchSet.expectToFind);
			  verifySourceLine(searchSet.fileName, searchSet.typeDeclaration);	  
		  }
	  }
	  catch (X10DT_Test_Exception e)
	  {
		  Assert.fail("Failed to " + operationMsg + ". \n  Reason: " + e.getMessage()); //$NON-NLS-1$
	  }
	  catch (Exception e)
	  {
		  Assert.fail("An error of great mystery and ominous portent has occurred.\n  We must be cautious! For it is written:\n \"Behold! " + e.getMessage() + "\"");
	  }
  }

/*
 * Verification routines
 */

  //	Verify console output from application
  //
  public static boolean verifyConsoleOutput(List<String> expectedLines, int startAtLine) throws X10DT_Test_Exception {

	  String operationMsg = null;			//string describing the current operation, for use in constructing error messages
	  boolean matches = true;				//verification result. Assume the best!
	  
	  // look for the Console view, and check the output
	  try {
		  operationMsg = "create a matcher for the '" + ViewConstants.CONSOLE_VIEW_NAME + "' view";
		  final Matcher<IViewReference> withPartName = WidgetMatcherFactory.withPartName(ViewConstants.CONSOLE_VIEW_NAME);
		  final WaitForView waitForConsole = Conditions.waitForView(withPartName);

		  topLevelBot.waitUntil(waitForConsole);

		  SWTBotView consoleView = new SWTBotView(waitForConsole.get(0), topLevelBot);
		  
		  //load the text of the actual output
		  operationMsg = "load the actual console output";
		  List<String> actualLines = consoleView.bot().styledText().getLines();
		  Iterator<String> actualLine = actualLines.listIterator(startAtLine);
		  Iterator<String> expectedLine = expectedLines.listIterator(0);
		  
		  //loop and compare actual and expected one line at a time
		  operationMsg = "match the actual console output with the expected console output";
		  int currentLine = startAtLine - 1;	// track for possible use in error message
		  String currentActualLine = "";		// track for possible use in error message
		  String currentExpectedLine = "";		// track for possible use in error message
		  while (matches && expectedLine.hasNext() && actualLine.hasNext()) {
			  currentLine++;
			  currentActualLine = actualLine.next();
			  currentExpectedLine = expectedLine.next();
			  matches = currentActualLine.equals(currentExpectedLine);
		  }
		  
		  if (!matches) { //D'oh!
			  throw new X10DT_Test_Exception ("Mismatch at line " + currentLine + 
					  							"\n    actual: '" + currentActualLine + 
					  							"\n    expected: '" + currentExpectedLine + "'");
		  }
	  }
	  catch (X10DT_Test_Exception e) {
		  Assert.fail("Failed to " + operationMsg + ". \n  Reason: " + e.getMessage()); //$NON-NLS-1$
	  }
	  return matches;
  }


  //	Verify source line in editor
  //
  private Boolean verifySourceLine(String fileName, String expectedText) throws X10DT_Test_Exception 
  {
	  String operationMsg = null;
	  Boolean matches = false;

	  try {
		  operationMsg = "activate '" + fileName + "' editor widget";
		  SWTBotEclipseEditor fArchiveFileEditor = topLevelBot.editorByTitle(fileName).toTextEditor();
		  
		  operationMsg = "compare '" + expectedText + "' with actual text in editor '" + fileName + "'";
		  String actualText = fArchiveFileEditor.getTextOnCurrentLine();
		  matches = expectedText.equals(actualText.trim());
		  
		  if (!matches) { //D'oh!
			  throw new X10DT_Test_Exception ("Expected source line does not match actual source line in file '" + fileName +  "':" +
					  							"\n    actual: '" + actualText + 
					  							"'\n    expected: '" + expectedText + "'");
		  }
		  
		  return matches;
	  }
	  catch (Exception e) {  //turn it into an X10_Test_Exception so that our exception structure works
			  throw new X10DT_Test_Exception("Failed to " + operationMsg + ".\n        Reason: " + e.getMessage());
	  }

  }
}