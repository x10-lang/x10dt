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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;
import java.util.ListIterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import x10dt.core.utils.Timeout;
import x10dt.tests.services.swbot.constants.LaunchConstants;
import x10dt.tests.services.swbot.constants.ViewConstants;
import x10dt.tests.services.swbot.constants.WizardConstants;
import x10dt.tests.services.swbot.utils.SWTBotUtils;
import x10dt.ui.tests.utils.EditorMatcher;
import x10dt.ui.tests.waits.X10DT_Conditions;

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
//    SWTBotPreferences.KEYBOARD_STRATEGY = "org.eclipse.swtbot.swt.finder.keyboard.SWTKeyboardStrategy"; //$NON-NLS-1$
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
	  String archivePath = null;
	  
	  // Locate the archive file in the file system - get the full file path
	  ClassLoader cl = getClass().getClassLoader();		//archive file must be on the build path
	  URL archiveURL = cl.getResource(ARCHIVE_NAME);	//find the file
	  if (archiveURL != null) {
		  archivePath = FileLocator.toFileURL(archiveURL).getPath();	//turn the net URL into a file path
		  System.out.println("full path to archive = " + archivePath);
	  }
	  else {
		  Assert.fail("archive file '" + ARCHIVE_NAME + "' not found"); 		  
	  }


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

	  //import the archive
	  importArchiveToX10Project(archivePath, projectName + "/src", true);

	  //open the imported file in an editor view
	  openX10FileInEditor(projectName, CLASS_SRCFILE_NAME);

	  //wait for the editor to open
	  topLevelBot.waitUntil(Conditions.waitForEditor(new EditorMatcher(CLASS_SRCFILE_NAME)), 60000, 500);

	  //
	  // Check that the type indexer is working
	  //
	  // first, look for a few types by using the 'Open X10 Type...' dialog 
//	  for (Iterator<TypeSearchInfo> it = declarationCheckList.iterator(); it.hasNext();)
//	  {
//		  TypeSearchInfo searchSet = it.next();
//		  //look for a specific source class
//		  openX10Type(searchSet.typeName, searchSet.searchString, searchSet.expectToFind);
//		  verifySourceLine(searchSet.fileName, searchSet.typeDeclaration);	  
//	  }
//	  // now, do it all over again by looking for a few types using the X10 Search dialog 
//	  for (Iterator<TypeSearchInfo> it = declarationCheckList.iterator(); it.hasNext();)
//	  {
//		  TypeSearchInfo searchSet = it.next();
//		  //look for a specific source class
//		  searchForX10Type(searchSet.typeName, searchSet.searchString, searchSet.expectToFind);
//		  verifySourceLine(searchSet.fileName, searchSet.typeDeclaration);	  
//	  }
	  
	  //
	  // run the program
	  //
	  if (backEnd == BackEndType.javaBackEnd) {
		  createAndRunJavaBackEndLaunchConfig(launchName, projectName, CLASS_NAME);

		  //Well, let's see if it worked
		  // verify that the actual output matches the expected output
		  boolean match = verifyConsoleOutput(EXPECTED_OUTPUT); //$NON-NLS-1$
		  Assert.assertTrue("ImportArchiveTest: Console output does not match", match); //$NON-NLS-1$
	  }
	  else {
		  ConfigureAndRunCpp(projectName,"CppPlatformConfigs.xml");
		  //ConfigureAndRunCpp already calls verifyConsoleOutput, so we don't have to do it here
	  }


  }

/*
 * Verification routines
 */

  //	Verify console output from application
  //
  public static boolean verifyConsoleOutput(List<String> expectedLines) { //throws X10DT_Test_Exception {

	  boolean matches = true;				//verification result. Assume the best!

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
	  consoleView.bot().waitUntil(X10DT_Conditions.viewHasText(consoleView), 30000, 500);

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
		  
		  Assert.assertTrue("Could not find an output line to match'" + currentExpectedLine + "'", matches);

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

	  String actualText = fArchiveFileEditor.getTextOnCurrentLine();
	  matches = expectedText.equals(actualText.trim());

	  Assert.assertTrue("Expected source line does not match actual source line in file '" + fileName +  "':" +
			  "\n    actual: '" + actualText + 
			  "'\n    expected: '" + expectedText + "'", matches);

	  return matches;
  }
  
  /* 
   * C++ back end platform configuration
   * 
   */
  
  //  one or more C++ backend platform configurations are loaded from an xml file. A run is made for each one
  public Boolean ConfigureAndRunCpp(String projectName, String xmlFileName) {

	  Document xmlConfigurations = loadXML(xmlFileName);


	  NodeList xmlConfigList = xmlConfigurations.getElementsByTagName("config");

	  for (int xmlConfigNum = 0; xmlConfigNum < xmlConfigList.getLength(); xmlConfigNum++)
	  {
		  PlatformConfig platformConfig = new PlatformConfig();
		  Node xmlConfigNode = xmlConfigList.item(xmlConfigNum);
		  Element xmlConfigElement = (Element) xmlConfigNode;

		  platformConfig.projectName = projectName;
		  platformConfig.configName = getTagString("configName", xmlConfigElement);
		  platformConfig.description = getTagString("description", xmlConfigElement);

		  platformConfig.useLocalConnection = getTagBoolean("useLocalConnection", xmlConfigElement);

		  //target configuration
		  NodeList xmlTargetNode = xmlConfigElement.getElementsByTagName("target");			  
		  Element xmlTargetElement = (Element) xmlTargetNode.item(0);
		  platformConfig.os = OS.get(getTagString("osType", xmlTargetElement));
		  platformConfig.arch = Architecture.get(getTagString("architectureType", xmlTargetElement));	  
		  platformConfig.set64bit = getTagBoolean("set64BitSpace", xmlTargetElement);				  

		  //x10 configuration
		  NodeList xmlX10Node = xmlConfigElement.getElementsByTagName("x10");			  
		  Element xmlX10Element = (Element) xmlX10Node.item(0);
		  platformConfig.numPlaces = getTagInteger("numPlaces", xmlX10Element);

		  //communication interface configuration
		  NodeList xmlCommNode = xmlConfigElement.getElementsByTagName("commInterface");			  
		  Element xmlCommElement = (Element) xmlCommNode.item(0);
		  platformConfig.interfaceType = CommInterface.getType(getTagString("interfaceType", xmlCommElement));    
		  platformConfig.interfaceMode = CommInterface.getMode(getTagString("interfaceMode", xmlCommElement));


		  if (platformConfig.useLocalConnection) {			//running on local machine
			  // replace whatever os and arch we got from xml with the local report from Java
			  platformConfig.os = OS.get(System.getProperty("os.name"));
			  platformConfig.arch = Architecture.get(System.getProperty("os.arch"));
		  }
		  else	{ //use remote connection

			  //remote connection configuration
			  NodeList xmlRemoteNode = xmlConfigElement.getElementsByTagName("remoteConnection");			  
			  Element xmlRemoteElement = (Element) xmlRemoteNode.item(0);
			  platformConfig.connectionName 	= getTagString("connectionName", xmlRemoteElement);     
			  platformConfig.remoteHostName 	= getTagString("remoteHostName", xmlRemoteElement);    
			  platformConfig.remoteHostPort 	= getTagInteger("remoteHostPort", xmlRemoteElement);    
			  platformConfig.remoteHostUser 	= getTagString("remoteHostUser", xmlRemoteElement);    
			  platformConfig.usePassword 		= getTagBoolean("usePassword", xmlRemoteElement);      
			  platformConfig.remoteHostPassword = getTagString("remoteHostPassword", xmlRemoteElement);

			  //Port forwarding settings
			  NodeList xmlPortNode = xmlConfigElement.getElementsByTagName("portForwarding");			  
			  Element xmlPortElement = (Element) xmlPortNode.item(0);
			  platformConfig.usePortForwarding        	= getTagBoolean("usePortForwarding", xmlPortElement);    
			  platformConfig.portForwardingTimeout    	= getTagString("portForwardingTimeout", xmlPortElement);    
			  platformConfig.portForwardingLocalAddress	= getTagString("portForwardingLocalAddress", xmlPortElement);      

			  //Remote compilation settings
			  NodeList xmlCompileNode = xmlConfigElement.getElementsByTagName("remotePlatform");			  
			  Element xmlCompileElement = (Element) xmlCompileNode.item(0);
			  platformConfig.outputFolder       = getTagString("outputFolder", xmlCompileElement);
			  platformConfig.useSelectedPGAS    = getTagBoolean("useSelectedPGAS", xmlCompileElement);      
			  platformConfig.remoteDistribution = getTagString("remoteDistribution", xmlCompileElement);    
			  platformConfig.remotePGASDist     = getTagString("remotePGASDist", xmlCompileElement);    
		  } //if using remote connection


		  setCppPlatformConnectionConfig(projectName, platformConfig);

		  //run the program
		  createAndRunCPPBackEndLaunchConfig(platformConfig.configName, projectName, CLASS_NAME);			  


		  //Well, let's see if it worked
		  //It's going to pop up the Parallel Runtime perspective, so let's get a handle to that
		  topLevelBot.perspectiveByLabel("Parallel Runtime").activate();

		  // verify that the actual output matches the expected output
		  boolean match = verifyConsoleOutput(EXPECTED_OUTPUT); //$NON-NLS-1$
		  Assert.assertTrue("ImportArchiveTest: Console output does not match", match); //$NON-NLS-1$

		  //Go back to the X10 perspective
		  topLevelBot.perspectiveByLabel("X10").activate();

	  }
	  return true;
  }
  
//  Load XML file with target platform configurations
  public Document loadXML(String xmlFileName) { //throws ParserConfigurationException, IOException, Exception {

	  File fXmlFile;

	  DocumentBuilderFactory docFactory;
	  DocumentBuilder docBuilder;
	  Document doc = null;

	  try {
		  ClassLoader cl = getClass().getClassLoader();		//archive file must be on the build path
		  URL xmlFileURL = cl.getResource(xmlFileName);	//find the file
		  fXmlFile = new File(FileLocator.toFileURL(xmlFileURL).getFile());

		  docFactory = DocumentBuilderFactory.newInstance();
		  docBuilder = docFactory.newDocumentBuilder();
		  doc = docBuilder.parse(fXmlFile);
		  doc.getDocumentElement().normalize();
	  }
	  catch (Exception e) {
		  Assert.fail("exception in loadXML " + e.getMessage());		
	  }
	  return doc;
  }

  //well, isn't this annoying!
  //I could tag everything as a text node, but that would make the xml file pretty ugly
  private static String getTagString(String tag, Element element) {
	  NodeList nList= element.getElementsByTagName(tag).item(0).getChildNodes();
	  Node nValue = (Node) nList.item(0);
	  if (nValue != null)
		  return nValue.getNodeValue().trim();
	  else
		  return "";
  }

  private static Integer getTagInteger(String tag, Element element) {
	  String tagString = getTagString(tag, element);
	  return tagString.equals("") ? 0 : Integer.valueOf(tagString);
  }

  private static Boolean getTagBoolean(String tag, Element element) {
	  return (getTagString(tag, element).equals("yes") ? true : false);
  }

}