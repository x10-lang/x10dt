/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Robert Fuhrer (rfuhrer@watson.ibm.com) - initial API and implementation
 *    Rick Lesniak (lesniakr@us.ibm.com) - 
 *    				added ImportJavaBackEndArchiveProject and findX10TypeDef.
 *    				added createCPPBackendProject
 *    				moved in createAndRunJavaBackEndLaunchConfig and openX10FileInEditor
 *    				added X10DT_TestException and exception reporting structure
 *******************************************************************************/

package x10dt.ui.tests;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.eclipse.finder.waits.WaitForView;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCTabItem;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotList;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotRadio;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableItem;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;
import org.eclipse.ui.IViewReference;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.osgi.framework.Bundle;

import x10dt.core.utils.Timeout;
import x10dt.tests.services.swbot.constants.LaunchConstants;
import x10dt.tests.services.swbot.constants.PlatformConfConstants;
import x10dt.tests.services.swbot.constants.ViewConstants;
import x10dt.tests.services.swbot.constants.WizardConstants;
import x10dt.tests.services.swbot.utils.ProblemsViewUtils;
import x10dt.tests.services.swbot.utils.SWTBotUtils;
import x10dt.ui.launch.core.Constants;
import x10dt.ui.tests.waits.X10DT_Conditions;

/**
 * @author rfuhrer@watson.ibm.com
 */
public class X10DTTestBase {
  /**
   * The top-level "bot" for the entire workbench.
   */
  public static SWTWorkbenchBot topLevelBot;
  
  public enum BackEndType {javaBackEnd, cppBackEnd};

  //maps Comminterface Mode and Type enums to/from strings
  public static class CommInterface {
	  //create the Mode enum and map each enum to a string
	  public enum Mode {Profile	("Profile"),
						Debug	("Debug"),
						Launch	("Launch");
		  Mode(String key) { this.key = key; }	 //constructor sets the string in
		  private final String key;				 // private field 'key'
		  public String getKey() { return key; } //use enum to get string
	  };
	  // now go the other way - map a string key to an enum value
	  private static Map<String, Mode> modeMap = new HashMap<String, Mode>();
	  public static Mode getMode(String s) { return modeMap.get(s); } //use string to get enum
	  static {
		  for (Mode t : Mode.values()) {
			  modeMap.put(t.getKey(), t);	//initialize map
		  }
	  }

	  //create the Mode enum and map each enum to a string
	  public enum Type {Sockets			("Sockets"),		
						IBM_LoadLeveler	("IBM LoadLeveler"),		
						Standalone		("Standalone"),		
						IBM_Parallel_Environment("IBM Parallel Environment"),		
						Open_MPI		("Open MPI"),		
						MPICH2			("MPICH2");
		  Type(String key) { this.key = key; }   //constructor sets the string in
		  private final String key;              // private field 'key'          
		  public String getKey() { return key; } //use enum to get string        
	  };
	  // now go the other way - map a string key to an enum value
	  private static Map<String, Type> typeMap = new HashMap<String, Type>();
	  public static Type getType(String s) { return typeMap.get(s); } //use string to get enum
	  static {
		  for (Type t : Type.values()) {
			  typeMap.put(t.getKey(), t);
		  }
	  }
  }
  
  //maps Architecture Type enum to/from strings
  public static class Architecture {
	  //create the Mode enum and map each enum to a string
	  public enum Type {x86("x86"), 
		  				Power("Power");
		  Type(String key) { this.key = key; }   //constructor sets the string in
		  private final String key;              // private field 'key'          
		  public String getKey() { return key; } //use enum to get string        
	  };
	  // now go the other way - map a string key to an enum value
	  private static Map<String, Type> typeMap = new HashMap<String, Type>();
	  public static Type get(String s) { return typeMap.get(s); } //use string to get enum
	  static {
		  for (Type t : Type.values()) {
			  typeMap.put(t.getKey(), t);
		  }
		  typeMap.put("i386",	Type.x86  ); //additional string reported by mac os x
	  }
  }
  
  //maps OS Type enum to/from strings
  public static class OS {
	  //create the Mode enum and map each enum to a string
	  public enum Type {WINDOWS("WINDOWS"	),
					    LINUX  ("LINUX"		),
					    AIX    ("AIX"		),
					    UNIX   ("UNIX"		),
					    MAC    ("MAC"		);
		  Type(String key) { this.key = key; }   //constructor sets the string in
		  private final String key;              // private field 'key'          
		  public String getKey() { return key; } //use enum to get string        
	  };
	  // now go the other way - map a string key to an enum value
	  private static Map<String, Type> typeMap = new HashMap<String, Type>();
	  public static Type get(String s) { return typeMap.get(s.toUpperCase()); } //use string to get enum
	  static {
		  for (Type t : Type.values()) {
			  typeMap.put(t.getKey(), t);
		  }
		  typeMap.put("MAC OS X",	Type.MAC ); //additional string reported by mac os x
	  }
  }
  
  /*
   * Use this class to provide the platform configuration settings
   */
//TODO: 	Use for a single call to setCppPlatformConnectionConfig, which in turn calls setCppPlatformCompilationConfig
//
  public class PlatformConfig {

	  public boolean useLocalConnection;
	  public String  projectName;
	  public String  configName;
	  public String  description;
	  
	  // target settings
	  public OS.Type  			os;
	  public Architecture.Type	arch;
	  public Boolean 			set64bit;

	  // x10 settings
	  public Integer			numPlaces;

	  // remote connection settings
	  public String  remoteHostName;
	  public String  remoteHostURL;
	  public Integer remoteHostPort;
	  public String  remoteHostUser;
	  public boolean usePassword;
	  public String  remoteHostPassword;

	  // Communications interface
	  public CommInterface.Type interfaceType;
	  public CommInterface.Mode interfaceMode;

	  // Port forwarding settings
	  public boolean usePortForwarding;
	  public String  portForwardingTimeout;
	  public String  portForwardingLocalAddress;

	  // remote compilation settings
	  public String  outputFolder;
	  public boolean useSelectedPGAS;
	  public String  remoteDistribution;
	  public String  remotePGASDist;
  }

  /**
   * Saves dirty editors and resets the workbench.
   * Call this from your derived test classes' @AfterClass-decorated method.
   */
  public static void AfterClass() {
    SWTBotUtils.saveAllDirtyEditors(topLevelBot);
    SWTBotUtils.resetWorkbench(topLevelBot);
  }

  /**
   * Closes the "Welcome" view and makes sure the X10 perspective is open.
   * Call this from your derived test classes' @BeforeClass-decorated method.
   */
  public static void BeforeClass() {
    SWTBotPreferences.KEYBOARD_STRATEGY = "org.eclipse.swtbot.swt.finder.keyboard.SWTKeyboardStrategy"; //$NON-NLS-1$
    topLevelBot = new SWTWorkbenchBot();
    SWTBotPreferences.TIMEOUT = Timeout.SIXTY_SECONDS; // TODO remove this ?

    SWTBotUtils.closeWelcomeViewIfNeeded(topLevelBot);
    topLevelBot.perspectiveByLabel("X10").activate();
  }

  /**
   * Closes all editors and shells.
   * Call from your derived test classes' @After-decorated method.
   */
  public void afterTest() {
    SWTBotUtils.closeAllEditors(topLevelBot);
    SWTBotUtils.closeAllShells(topLevelBot);
  }

/**
   * This method waits for a build to finish before continuing
   * 
   * @throws Exception
   */
  public static void waitForBuildToFinish() throws Exception {
    Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, null);
  }
  
  //
  //	For Java Back End Projects
  //

  //Create a new X10 Java back-end project
  //
  public static void createJavaBackEndProject(String projName, boolean withHello) throws X10DT_Test_Exception {
	  String operationMsg = null;			//string describing the current operation, for use in constructing error messages
	  String dialogContextMsg = null;		//string identifying the current dialog context, for use in constructing error messages
	  try
	  {
		  //Open the New Project dialog
		  operationMsg = "access the New Project menu '" + WizardConstants.FILE_MENU +":"+
					  		WizardConstants.NEW_MENU_ITEM +":"+ WizardConstants.PROJECTS_SUB_MENU_ITEM + "'.";
		  
		  topLevelBot.menu(WizardConstants.FILE_MENU)
		  				.menu(WizardConstants.NEW_MENU_ITEM)
		  					.menu(WizardConstants.PROJECTS_SUB_MENU_ITEM)
		  						.click();

		  //
		  //Use the New Project wizard in the X10 folder
		  //
		  dialogContextMsg = " the '" + WizardConstants.NEW_PROJECT_DIALOG_TITLE + "' wizard";
		  
		  //find the new project wizard dialog
		  operationMsg = "find" + dialogContextMsg;
		  SWTBotShell newProjShell = topLevelBot.shell(WizardConstants.NEW_PROJECT_DIALOG_TITLE);
		  newProjShell.activate();
		  SWTBot newProjBot = newProjShell.bot();
		  SWTBotTree newProjTree = newProjBot.tree();
		  
		  //open the X10 wizard folder
		  operationMsg = "find the '" + WizardConstants.X10_FOLDER + "' wizard folder in" + dialogContextMsg;
		  newProjBot.waitUntil(X10DT_Conditions.treeHasNode(newProjTree, WizardConstants.X10_FOLDER), 10000);  
		  SWTBotTreeItem x10ProjItems = newProjBot.tree().expandNode(WizardConstants.X10_FOLDER);

		  //find the X10 wizard 
		  operationMsg = "find the '" + WizardConstants.X10_PROJECT_JAVA_BACKEND + "' wizard in" + dialogContextMsg;
		  newProjBot.waitUntil(X10DT_Conditions.treeNodeHasItem(x10ProjItems, WizardConstants.X10_PROJECT_JAVA_BACKEND), 10000);
		  x10ProjItems.select(WizardConstants.X10_PROJECT_JAVA_BACKEND);

		  //click 'Next'
		  operationMsg = "find the '" + WizardConstants.NEXT_BUTTON + "' button in" + dialogContextMsg;
		  newProjBot.button(WizardConstants.NEXT_BUTTON).click();

		  //
		  //Set up the new X10 project - specify project name and select whether or not to create Hello World sample code
		  //
		  dialogContextMsg = " the '" + WizardConstants.X10_PROJECT_SHELL_JAVA_BACKEND + "' dialog";
		  
		  //find the new X10 project wizard dialog 
		  operationMsg = "find" + dialogContextMsg;
		  SWTBotShell newX10ProjShell = topLevelBot.shell(WizardConstants.X10_PROJECT_SHELL_JAVA_BACKEND);
		  newX10ProjShell.activate();
		  SWTBot newX10ProjBot = newX10ProjShell.bot();

		  //set radio button to either create hello sample code or not
		  operationMsg = /*click the '<name>*/"' radio button in the '" 
			  				+ WizardConstants.NEW_X10_PROJECT_SAMPLE_SOURCE_GROUP + 
			  					"' dialog group, inside"  + dialogContextMsg;
		  if (withHello) 
		  {
			  operationMsg = "click the '" + WizardConstants.NEW_X10_PROJECT_HELLO_SOURCE_RADIO + operationMsg;
			  newX10ProjBot.radioInGroup(WizardConstants.NEW_X10_PROJECT_HELLO_SOURCE_RADIO,
						  WizardConstants.NEW_X10_PROJECT_SAMPLE_SOURCE_GROUP).click();
		  } 
		  else
		  {
			  operationMsg = "click the '" + WizardConstants.NEW_X10_PROJECT_HELLO_SOURCE_RADIO + operationMsg;
			  newX10ProjBot.radioInGroup(WizardConstants.NEW_X10_PROJECT_NO_SAMPLE_SOURCE_RADIO,
						  WizardConstants.NEW_X10_PROJECT_SAMPLE_SOURCE_GROUP).click();
		  }

		  // Fill in the project name field
		  operationMsg = "fill in the '" + WizardConstants.X10_PROJECT_JAVA_BACKEND_NAME_FIELD + "' field of" + dialogContextMsg;
		  newX10ProjBot.textWithLabel(WizardConstants.X10_PROJECT_JAVA_BACKEND_NAME_FIELD).setFocus();
		  newX10ProjBot.textWithLabel(WizardConstants.X10_PROJECT_JAVA_BACKEND_NAME_FIELD).setText(projName);

		  // click the 'Finish' button
		  operationMsg = "click the '" + WizardConstants.X10_PROJECT_JAVA_BACKEND_NAME_FIELD + "' button in" + dialogContextMsg;
		  newX10ProjBot.button(WizardConstants.FINISH_BUTTON).click();

		  topLevelBot.waitUntil(Conditions.shellCloses(newX10ProjShell));
	  }
	  
	  catch (Exception e)
	  {
		  throw new X10DT_Test_Exception("Could not create new X10 project '" + projName +
				  							"': Failed to " + operationMsg +
				  								"'.\n        Reason: " + e.getMessage());
	  }
  }

  //Set up Java back end run configuration and launch application
  //
  public static void createAndRunJavaBackEndLaunchConfig(String launchName, String projectName, String mainTypeName) throws X10DT_Test_Exception
  {
	  String operationMsg = null;			//string describing the current operation, for use in constructing error messages
	  String dialogContextMsg = null;		//string identifying the current dialog context, for use in constructing error messages

	  try {
		  // Open the X10 Run Configuration dialog
		  dialogContextMsg = " the '" + LaunchConstants.RUN_CONF_DIALOG_TITLE + "' dialog";

		  operationMsg = "access the '" + LaunchConstants.RUN_MENU +":"+ LaunchConstants.RUN_CONFS_MENU_ITEM +"' menu";
		  topLevelBot.menu(LaunchConstants.RUN_MENU).menu(LaunchConstants.RUN_CONFS_MENU_ITEM).click();

		  // Wait for the Run Configuration dialog to open
		  operationMsg = "find" + dialogContextMsg;
		  
		  topLevelBot.waitUntil(Conditions.shellIsActive(LaunchConstants.RUN_CONF_DIALOG_TITLE));
		  SWTBotShell configsShell = topLevelBot.shell(LaunchConstants.RUN_CONF_DIALOG_TITLE);
		  configsShell.activate();
		  SWTBot configsBot = configsShell.bot();

		  //Select Java Back-End application configuration
		  operationMsg = "select the '" + LaunchConstants.JAVA_BACK_END_LAUNCH_CONFIG_TYPE + "' item in" + dialogContextMsg;
		  SWTBotTreeItem x10AppItem = configsBot.tree().getTreeItem(LaunchConstants.JAVA_BACK_END_LAUNCH_CONFIG_TYPE);
		  x10AppItem.doubleClick();

		  //enter the project configuration name
		  operationMsg = "enter the configuration name '" + launchName + "' in field '" +
		  					LaunchConstants.JAVA_BACK_END_PROJECT_DIALOG_NAME_FIELD + "' of" + dialogContextMsg;
		  SWTBotText launchNameText = configsBot.textWithLabel(LaunchConstants.JAVA_BACK_END_PROJECT_DIALOG_NAME_FIELD);
		  launchNameText.setText(launchName);

		  //pick the the 'Main' dialog tab
		  operationMsg = "select the '" + LaunchConstants.JAVA_BACK_END_LAUNCH_CONFIG_MAIN_TAB + "' tab in"+ dialogContextMsg;
		  SWTBotCTabItem mainTab = configsBot.cTabItem(LaunchConstants.JAVA_BACK_END_LAUNCH_CONFIG_MAIN_TAB);
		  mainTab.activate();
		  
		  //set the project name
		  operationMsg = "enter  '" + projectName + "' in field " + LaunchConstants.JAVA_BACK_END_LAUNCH_CONFIG_PROJECT + "' of" + dialogContextMsg;
		  configsBot.textInGroup(LaunchConstants.JAVA_BACK_END_LAUNCH_CONFIG_PROJECT, 0).setText(projectName);

		  //set the main class name
		  operationMsg = "enter  '" + mainTypeName + "' in field " + LaunchConstants.JAVA_BACK_END_LAUNCH_CONFIG_MAIN_CLASS + "' of" + dialogContextMsg;
		  configsBot.textInGroup(LaunchConstants.JAVA_BACK_END_LAUNCH_CONFIG_MAIN_CLASS, 0).setText(mainTypeName);

		  //click the RUN button
		  operationMsg = "click the '" + LaunchConstants.RUN_BUTTON + "' button in" + dialogContextMsg;
		  configsBot.button(LaunchConstants.RUN_BUTTON).click();
	  }
	  catch (Exception e)
	  {
		  throw new X10DT_Test_Exception("Failed to " + operationMsg + "'.\n        Reason: " + e.getMessage());
	  }

  }

  //
  //	For C++ Back End Projects
  //
  
  //Create a new X10 C++ back-end project
  //
  public static void createCPPBackEndProject(String projName, boolean withHello) throws Exception {

	  //Open the New Project dialog
	  topLevelBot.menu(WizardConstants.FILE_MENU)
	  				.menu(WizardConstants.NEW_MENU_ITEM)
	  					.menu(WizardConstants.PROJECTS_SUB_MENU_ITEM).click();

	  //
	  //Use the New Project wizard in the X10 folder
	  //

	  //find the new project wizard dialog
	  SWTBotShell newProjShell = topLevelBot.shell(WizardConstants.NEW_PROJECT_DIALOG_TITLE);
	  newProjShell.activate();
	  SWTBot newProjBot = newProjShell.bot();
	  SWTBotTree newProjTree = newProjBot.tree();
	  
	  //open the X10 wizard folder
	  newProjBot.waitUntil(X10DT_Conditions.treeHasNode(newProjTree, WizardConstants.X10_FOLDER), 10000);  
	  SWTBotTreeItem x10ProjItems = newProjBot.tree().expandNode(WizardConstants.X10_FOLDER);

	  //find the X10 wizard 
	  newProjBot.waitUntil(X10DT_Conditions.treeNodeHasItem(x10ProjItems, WizardConstants.X10_PROJECT_CPP_BACKEND), 10000);
	  x10ProjItems.select(WizardConstants.X10_PROJECT_CPP_BACKEND);

	  //click 'Next'
	  newProjBot.button(WizardConstants.NEXT_BUTTON).click();

	  //
	  //Set up the new X10 project - specify project name and select whether or not to create Hello World sample code
	  //
	  SWTBotShell newX10ProjShell = topLevelBot.shell(WizardConstants.X10_PROJECT_SHELL_CPP_BACKEND);
	  newX10ProjShell.activate();
	  SWTBot newX10ProjBot = newX10ProjShell.bot();

	  // Fill in the project name field
	  newX10ProjBot.textWithLabel(WizardConstants.NEW_CPP_PROJECT_NAME_FIELD).setFocus();
	  newX10ProjBot.textWithLabel(WizardConstants.NEW_CPP_PROJECT_NAME_FIELD).setText(projName);

	  //set radio button to either create hello sample code or not
	  if (withHello) {
		  newX10ProjBot.checkBox(WizardConstants.NEW_X10_PROJECT_HELLO_SOURCE_CHECKBOX).select();
	  } else {
		  newX10ProjBot.checkBox(WizardConstants.NEW_X10_PROJECT_HELLO_SOURCE_CHECKBOX).deselect();
	  }

	  // click the 'Finish' button
	  newX10ProjBot.button(WizardConstants.FINISH_BUTTON).click();

	  topLevelBot.waitUntil(Conditions.shellCloses(newX10ProjShell));
  }

  //Set up CPP backend run configuration and launch application
  //
  public static void createAndRunCPPBackEndLaunchConfig(String launchName, String projectName, String mainTypeName) //throws X10DT_Test_Exception
  {
	  String operationMsg = null;			//string describing the current operation, for use in constructing error messages
	  String dialogContextMsg = null;		//string identifying the current dialog context, for use in constructing error messages

//	  try {
		  // Open the X10 Run Configuration dialog
		  dialogContextMsg = " the '" + LaunchConstants.RUN_CONF_DIALOG_TITLE + "' dialog";

		  operationMsg = "access the '" + LaunchConstants.RUN_MENU +":"+ LaunchConstants.RUN_CONFS_MENU_ITEM +"' menu";
		  SWTBotMenu runMenu = topLevelBot.menu(LaunchConstants.RUN_MENU);
		  SWTBotMenu runConfsMenu = runMenu.menu(LaunchConstants.RUN_CONFS_MENU_ITEM);
		  runConfsMenu.click();
		  
		  // Wait for the Run Configuration dialog to open
		  operationMsg = "find" + dialogContextMsg;
		  topLevelBot.waitUntil(Conditions.shellIsActive(LaunchConstants.RUN_CONF_DIALOG_TITLE));
		  SWTBotShell configsShell = topLevelBot.shell(LaunchConstants.RUN_CONF_DIALOG_TITLE);
		  configsShell.activate();
		  SWTBot configsBot = configsShell.bot();

		  //Select C++ Back-End application configuration
		  operationMsg = "select the '" + LaunchConstants.NEW_CPP_LAUNCH_CONFIG + "' item in" + dialogContextMsg;
		  SWTBotTreeItem x10AppItem = configsBot.tree().getTreeItem(LaunchConstants.NEW_CPP_LAUNCH_CONFIG);
		  x10AppItem.doubleClick();

		  //enter the project configuration name
		  operationMsg = "enter the configuration name '" + launchName + "' in field '" +
		  					LaunchConstants.CPP_BACK_END_PROJECT_DIALOG_NAME_FIELD + "' of" + dialogContextMsg;
		  SWTBotText launchNameText = configsBot.textWithLabel(LaunchConstants.CPP_BACK_END_PROJECT_DIALOG_NAME_FIELD);
		  launchNameText.setText(launchName);

		  //pick the the 'Application' dialog tab
		  operationMsg = "select the '" + LaunchConstants.CPP_LAUNCH_CONFIG_APPLICATION_TAB + "' tab in"+ dialogContextMsg;
		  SWTBotCTabItem mainTab = configsBot.cTabItem(LaunchConstants.CPP_LAUNCH_CONFIG_APPLICATION_TAB);
		  mainTab.activate();
		  
		  //set the project name
		  operationMsg = "enter  '" + projectName + "' in field " + LaunchConstants.CPP_LAUNCH_CONFIG_X10_PROJECT + "' of" + dialogContextMsg;
		  configsBot.textInGroup(LaunchConstants.CPP_LAUNCH_CONFIG_X10_PROJECT, 0).setText(projectName);

		  //set the main class name
		  operationMsg = "enter  '" + mainTypeName + "' in field " + LaunchConstants.CPP_LAUNCH_CONFIG_MAIN_CLASS + "' of" + dialogContextMsg;
		  configsBot.textInGroup(LaunchConstants.CPP_LAUNCH_CONFIG_MAIN_CLASS, 0).setText(mainTypeName);

		  //click the RUN button
		  operationMsg = "click the '" + LaunchConstants.RUN_BUTTON + "' button in" + dialogContextMsg;
		  configsBot.button(LaunchConstants.RUN_BUTTON).click();
//	  }
//	  catch (Exception e)
//	  {
//		  throw new X10DT_Test_Exception("Failed to " + operationMsg + "'.\n        Reason: " + e.getMessage());
//	  }
  }

  //
  //	For all X10 projects
  //
  //Import an archive into an existing X10 project
  //
  public static void importArchiveToX10Project(String archiveName, String folderName, boolean doOverwrite) throws X10DT_Test_Exception {
	  String operationMsg = null;			//string describing the current operation, for use in constructing error messages
	  
	  try
	  {
		  //Open the New Import dialog
		  operationMsg = " access menu '" + WizardConstants.FILE_MENU +":"+ WizardConstants.IMPORT_MENU_ITEM + "'";
		  topLevelBot.menu(WizardConstants.FILE_MENU).menu(WizardConstants.IMPORT_MENU_ITEM).click();

		  //Find the Import dialog
		  operationMsg = "Cannot select the '" + WizardConstants.IMPORT_DIALOG_TITLE + "' wizard.";
		  SWTBotShell selectArchiveShell = topLevelBot.shell(WizardConstants.IMPORT_DIALOG_TITLE);

		  selectArchiveShell.activate();

		  SWTBot selectArchiveBot = selectArchiveShell.bot();
		  SWTBotTree importSourceTree = selectArchiveBot.tree();

		  //Select the General item in the import sources list
		  operationMsg = "select the '" + WizardConstants.GENERAL_FOLDER + "' item.";
		  selectArchiveBot.waitUntil(X10DT_Conditions.treeHasNode(importSourceTree, WizardConstants.GENERAL_FOLDER), 10000);  
		  SWTBotTreeItem x10ProjItems = importSourceTree.expandNode(WizardConstants.GENERAL_FOLDER);
		  
		  //Select the Import Archive item in the import sources list
		  operationMsg = "select the '" + WizardConstants.IMPORT_ARCHIVE + "' item.";
		  selectArchiveBot.waitUntil(X10DT_Conditions.treeNodeHasItem(x10ProjItems, WizardConstants.IMPORT_ARCHIVE), 10000);
		  x10ProjItems.select(WizardConstants.IMPORT_ARCHIVE);

		  //click Next to go to the Import Archive dialog
		  operationMsg = "click the '" + WizardConstants.NEXT_BUTTON +"' to the Import Archive dialog";
		  selectArchiveBot.button(WizardConstants.NEXT_BUTTON).click();

		  // Set up the Archive Import dialog
		  operationMsg = "locate the '" + WizardConstants.IMPORT_ARCHIVE_DIALOG_TITLE + "' dialog ";
		  SWTBotShell importArchiveShell = topLevelBot.shell(WizardConstants.IMPORT_ARCHIVE_DIALOG_TITLE);
		  importArchiveShell.activate();

		  SWTBot importArchiveBot = importArchiveShell.bot();

		  // Fill in the archive file name field
		  operationMsg = "fill in the '" + 
		  					WizardConstants.IMPORT_ARCHIVE_DIALOG_TITLE + " " +
		  							WizardConstants.FROM_ARCHIVE_FILE_FIELD + "' field";
		  importArchiveBot.comboBoxWithLabel(WizardConstants.FROM_ARCHIVE_FILE_FIELD).setText(archiveName);
		  importArchiveBot.comboBoxWithLabel(WizardConstants.FROM_ARCHIVE_FILE_FIELD).setSelection(0);

		  // Fill in the archive destination folder field
		  operationMsg = "fill in the '" + WizardConstants.IMPORT_ARCHIVE_DIALOG_TITLE + " " +
		  							WizardConstants.INTO_FOLDER_FIELD + "' field";
		  importArchiveBot.textWithLabel(WizardConstants.INTO_FOLDER_FIELD).setText(folderName);

		  if (doOverwrite) {
			  // check the 'overwrite existing' box and click 'Finish'
			  operationMsg = "check the '" + WizardConstants.OVERWRITE_EXISTING_CHECKBOX + "' checkbox";
			  importArchiveBot.checkBox(WizardConstants.OVERWRITE_EXISTING_CHECKBOX).select();
			  
			  // click 'Finish'
			  operationMsg = "click the " + WizardConstants.FINISH_BUTTON + 
			  					"' button and import archive file '" + archiveName + "'.";
			  importArchiveBot.button(WizardConstants.FINISH_BUTTON).click();
		  } else {
//TODO: We might never want to do this, so I never finished it.  Maybe someday...
//
//			  // uncheck the 'overwrite existing' box
//			  operationMsg = "uncheck the '" + WizardConstants.OVERWRITE_EXISTING_CHECKBOX + "' checkbox";
//	  		  importArchiveBot.checkBox(WizardConstants.OVERWRITE_EXISTING_CHECKBOX).deselect();
//
//			  // click 'Finish'
//			  operationMsg = "click the " + WizardConstants.FINISH_BUTTON + 
//			  					"' button and import archive file '" + archiveName + "'.";
//	  		  importArchiveBot.button(WizardConstants.FINISH_BUTTON).click();
//	  		  
//	  		  //the wizard may incredulously question our judgment, nay, even our sanity!
//			  operationMsg += "find the 'overwrite file, are-you-sure?' dialog";
//	  		  Matcher areYouSureAlert = allOf (widgetOfType(Label.class), withRegex("Overwrite .*"));
//	  		  
//	  		  //tell the wizard that we are quite serious, and in full possession of our faculties, 
//	  		  // and that it should go forth and multiply itself
//			  operationMsg += ", and then click the '" + WizardConstants.YES_TO_ALL_BUTTON + "' button ";
//	  		  try {
//	  			  SWTBotShell areYouSureShell = importArchiveBot.shell(areYouSureAlert.toString());
//	  			  SWTBotButton yesToAllBut = areYouSureShell.bot().button(WizardConstants.YES_TO_ALL_BUTTON);
//	  			  yesToAllBut.click();
//	  		  }
//	  		  catch (WidgetNotFoundException e)
//	  		  {
//	  			  //this is ok - there's nothing to overwrite
//	  		  }
//	  		  catch (TimeoutException e)
//	  		  {
//	  			  //this is ok - there's nothing to overwrite
//	  		  }

		  } //else doOverwrite == false

		  topLevelBot.waitUntil(Conditions.shellCloses(importArchiveShell));
	  }
	  catch (Exception e)
	  {
		  throw new X10DT_Test_Exception("Could not import  '" + archiveName +
				  	"' to X10 project '" + folderName +
				  		"' : Failed to " + operationMsg +
				  			"'.\n        Reason: " + e.getMessage());
	  }
  }

  //Open the x10 file by pawing through the project in the Package Explorer view
  //
  public static void openX10FileInEditor(String projName, String fileName) throws X10DT_Test_Exception {
	  String operationMsg = null;			//string describing the current operation, for use in constructing error messages
	  try
	  {
		  //Open the Package Explorer
		  operationMsg = "open the '" + WizardConstants.PACKAGE_EXPLORER_VIEW_TITLE+"' View";
		  SWTBotView packageExplorerView = topLevelBot.viewByTitle(WizardConstants.PACKAGE_EXPLORER_VIEW_TITLE);
		  SWTBot packageExplorerBot = packageExplorerView.bot();
		  
		  //Navigate down the package tree to find the project
		  operationMsg = "find the project'" + projName + "' in the Package View";
		  SWTBotTree packageTree = packageExplorerBot.tree();
		  packageExplorerBot.waitUntil(X10DT_Conditions.treeHasNode(packageTree, projName), 10000);  
		  SWTBotTreeItem projectFolder = packageTree.expandNode(projName);
		  
		  //Find the src folder
		  operationMsg = "find the src folder for project '" + projName + "'";
		  packageExplorerBot.waitUntil(X10DT_Conditions.treeNodeHasItem(projectFolder, "src"), 10000);
		  SWTBotTreeItem srcFolder = projectFolder.expandNode("src");
		  
		  //find the file
		  operationMsg = "find the file '" + fileName + "' in project '" + projName + "'";
		  packageExplorerBot.waitUntil(X10DT_Conditions.treeNodeHasItem(srcFolder, fileName), 10000);
		  SWTBotTreeItem srcFile = srcFolder.expandNode(fileName);
		  srcFile.select();

		  //open the file
		  operationMsg = "open the file '" + fileName + "' in project '" + projName + "'";
		  topLevelBot.menu(WizardConstants.NAVIGATE_MENU).menu(WizardConstants.OPEN_ITEM).click();
	  }
	  catch (Exception e)
	  {
		  throw new X10DT_Test_Exception("Failed to " + operationMsg +
				  " when opening X10 file '" + fileName + "'.\n        Reason: " + e.getMessage());
	  }
  }

  //Open up the 'Open X10 Type...' dialog and look for a type declaration
  public static boolean openX10Type(String typeName, String searchString, Integer expectedRows)  throws X10DT_Test_Exception
  {
	  String operationMsg = null;			//string describing the current operation, for use in constructing error messages
	  String dialogContextMsg = null;		//string identifying the current dialog context, for use in constructing error messages

	  boolean found = false;	//assume the worst

	  try {
		  // Open the X10 Type dialog
		  operationMsg = "access the '" +
		  					WizardConstants.NAVIGATE_MENU +":"+ WizardConstants.OPEN_X10_TYPE_ITEM +"' menu";
		  topLevelBot.menu(WizardConstants.NAVIGATE_MENU).menu(WizardConstants.OPEN_X10_TYPE_ITEM).click();

		  // create a context message identifying the dialog
		  dialogContextMsg = " the '" + WizardConstants.OPEN_X10_TYPE_DIALOG_TITLE + "' dialog";
		  
		  //Set up a shell for the X10 Type Dialog
		  operationMsg = "find" + dialogContextMsg;
		  SWTBotShell x10TypeShell = topLevelBot.shell(WizardConstants.OPEN_X10_TYPE_DIALOG_TITLE);
		  x10TypeShell.activate();
		  SWTBot x10TypeBot = x10TypeShell.bot();

		  // find the text box for the search patterns, and set the search string
		  operationMsg = "set the '" + WizardConstants.X10_TYPE_SEARCH_PATTERN + "'text box in" + dialogContextMsg;
		  x10TypeBot.textWithLabel(WizardConstants.X10_TYPE_SEARCH_PATTERN).setText(searchString);

			  //find the list box for the types we found
		  operationMsg = "find the '" + WizardConstants.X10_TYPE_SEARCH_LISTBOX + "' list box in" + dialogContextMsg;
		  SWTBotTable typeList = x10TypeBot.tableWithLabel(WizardConstants.X10_TYPE_SEARCH_LISTBOX);

		  operationMsg = "waiting for the '" + WizardConstants.X10_TYPE_SEARCH_LISTBOX + "' list box to fill, in" + dialogContextMsg;
		  try {
			  x10TypeBot.waitUntil(Conditions.tableHasRows(typeList, expectedRows), 2000);	//give it a few seconds to find at least 
			  																		// as many things as we're looking for.
			  																		// benignly time-out if it doesn't find that many
		  }
		  catch (TimeoutException e) {
			  //not a big deal - it just means we didn't find a match, and we'll return false
			  System.out.println("timed out waiting for the type list to populate"); /*debug*/
		  }
		  
		  // look for the expected type in the list
		  
		  //Check that we found the expected number of matching items
		  int rowCount = typeList.rowCount();
		  if (rowCount < expectedRows) {
			  throw new Exception("expected to find " + expectedRows + ", found " + rowCount + " rows");			  
		  }
		  
		  //See if we found the type we're looking for
		  operationMsg = "find X10 type '" + typeName + "' by searching on '" + searchString + "' in" + dialogContextMsg;
		  int i=0;
		  while ((!found) && (i < rowCount))
		  {
			  String tableItemText = typeList.getTableItem(i).getText();
			  int firstSpace = tableItemText.indexOf(' ');
			  String classText = tableItemText.substring(0, (firstSpace != -1 ? firstSpace : tableItemText.length())); //get rid of various qualifiers, like " - default"
			  System.out.println("comparing classText'" + classText + "' to typename'" + typeName + "'");
			  found = classText.equals(typeName);
			  if (found) {
				  typeList.select(i);	//select the row
			  }
			  i++;
		  }

		  if (!found) {	// throw a regular exception if we don't find anything 
			  			// (the X10DT_Test_Exception error message gets properly assembled by doing it this way)
			  throw new Exception(((rowCount == 0) ?  "Type list is empty":"No match found in list"));
		  }

		  //that's enough for now.  find the OK button
		  operationMsg = "find the '" + WizardConstants.OK_BUTTON + "' button in" + dialogContextMsg;
		  SWTBotButton okButton = x10TypeBot.button(WizardConstants.OK_BUTTON);
		  okButton.click();
		  
		  topLevelBot.waitUntil(Conditions.shellCloses(x10TypeShell));
	  }
	  catch (Exception e)
	  {
		  throw new X10DT_Test_Exception("Failed to " + operationMsg + "'.\n        Reason: " + e.getMessage());
	  }

	  return found;
  }

  //Open up the 'Search' dialog and search for an X10 type declaration
  public static boolean searchForX10Type(String typeName, String searchString, Integer expectedRows)  throws X10DT_Test_Exception
  {
	  String operationMsg = null;			//string describing the current operation, for use in constructing error messages
	  String dialogContextMsg = null;		//string identifying the current dialog context, for use in constructing error messages

	  boolean found = false;	//assume the worst

	  try {
		  // Open the Search dialog
		  dialogContextMsg = " the '" + WizardConstants.SEARCH_DIALOG_TITLE + "' dialog";

		  operationMsg = "select the '" + WizardConstants.SEARCH_MENU +":"+ WizardConstants.SEARCH_MENU_ITEM +"' menu";
		  topLevelBot.menu(WizardConstants.SEARCH_MENU).menu(WizardConstants.SEARCH_MENU_ITEM).click();
		  
		  //Set up a shell for the Search Dialog
		  operationMsg = "create shell for" + dialogContextMsg;
		  SWTBotShell x10SearchShell = topLevelBot.shell(WizardConstants.SEARCH_DIALOG_TITLE);
		  x10SearchShell.activate();
		  SWTBot x10SearchBot = x10SearchShell.bot();

		  // activate out the X10 Search tab
		  operationMsg = "activate the '" + WizardConstants.X10_SEARCH_TAB + "' tab in " + dialogContextMsg;
		  x10SearchBot.tabItem(WizardConstants.X10_SEARCH_TAB).activate();
		  
		  // Fill in the search string field
		  operationMsg = "fill in the '" + WizardConstants.X10_SEARCH_FIELD + "' field in" + dialogContextMsg;  
		  x10SearchBot.comboBoxWithLabel(WizardConstants.X10_SEARCH_FIELD).setText(searchString);

		  // check the 'search for type' box
		  operationMsg = "check the '" + WizardConstants.X10_SEARCH_FOR_TYPE_RADIO + "' checkbox in group '" +
		  			WizardConstants.X10_SEARCH_FOR_GROUP + "' in" + dialogContextMsg;
		  x10SearchBot.radioInGroup(WizardConstants.X10_SEARCH_FOR_TYPE_RADIO, WizardConstants.X10_SEARCH_FOR_GROUP).click();

		  // click the 'Search' button
		  operationMsg = "click the '" + WizardConstants.SEARCH_BUTTON + "' button in" + dialogContextMsg;
		  x10SearchBot.button(WizardConstants.SEARCH_BUTTON).click();

		  //wait for the search dialog to go away
		  x10SearchBot.waitUntil(Conditions.shellCloses(x10SearchShell));

		  //find the Search View
		  dialogContextMsg = " the '" + WizardConstants.SEARCH_VIEW_TITLE + "' View";
		  operationMsg = "find" + dialogContextMsg;
		  SWTBotView searchView = topLevelBot.viewByTitle(WizardConstants.SEARCH_VIEW_TITLE);
		  SWTBot searchViewBot = searchView.bot();
		  searchView.setFocus();

		  //find the type list in the search view
		  operationMsg = "find the search list box in" + dialogContextMsg;
		  SWTBotTable typeList = null;
		  
		  //ok, this is annoying. We can't simply assume that the table is going to be at index 0,
		  // because I have seen it appear in both index 0 and at index 1. I don't know of a way 
		  // to search for the right one by name. If you do know how, you are welcome to replace
		  // the following.
		  //This scans through table indices from 0 until it throws an exception. The possibly
		  // foolish assumption is that the table with the highest index is the one we want.
		  found = false;
		  Integer tableIndex = 0;
		  while (!found)
		  {
			  try {
				  typeList = searchViewBot.table(tableIndex);
				  tableIndex++;
			  }
			  catch (Exception e) {
				  found = true;
			  }
		  }
		  
		  operationMsg = "see the search list become filled" + dialogContextMsg;
		  Integer waitLoop = 30;
		  System.out.println("\n    find X10 type '" + typeName + "' by searching on '" + searchString + "'");

		  do {
			  try {
				  searchViewBot.waitUntil(Conditions.tableHasRows(typeList, expectedRows), 2000);	//give it a few seconds to find something.
			  }
			  catch (TimeoutException e) {
				  //not a big deal - it just means we didn't find a match, and we'll return false
				  System.out.println("timed out waiting for the type list to populate.\n     have " + typeList.rowCount() + " rows so far"); /*debug*/
				  for (Integer i=0; (i < typeList.rowCount()); i++)
					  System.out.println("      line " + i + " is '" + typeList.getTableItem(i).getText() +"'");

			  }
		  } while ((--waitLoop > 0) && 
			  		((typeList.getTableItem(0).getText().equals("Searching...")) ||
			  		(typeList.getTableItem(0).getText().equals("")))
				  );
		  
		  // look for the expected type in the list
		  
		  //Check that we found the expected number of matching items
		  int rowCount = typeList.rowCount();
		  if (rowCount < expectedRows) {
			  throw new Exception("expected to find " + expectedRows + ", found " + rowCount + " rows");			  
		  }
		  
		  //See if we found the type we're looking for
		  operationMsg = "find X10 type '" + typeName + "' by searching on '" + searchString + "' in" + dialogContextMsg;
		  int i=0;
		  found = false;
		  while ((!found) && (i < rowCount))
		  {
			  String tableItemText = typeList.getTableItem(i).getText();
			  int firstSpace = tableItemText.indexOf(' ');
			  String classText = tableItemText.substring(0, (firstSpace != -1 ? firstSpace : tableItemText.length())); //get rid of various qualifiers, like " - default"
			  System.out.println("comparing classText'" + classText + "' to typename'" + typeName + "'");
			  found = classText.equals(typeName);
			  if (found) {
				  typeList.setFocus();
				  typeList.select(i);
				  typeList.pressShortcut(0, '\n');
			  }
			  i++;
		  }
		  
		  if (!found) {	// throw a regular exception if we don't find anything 
			  		// (the X10DT_Test_Exception error message gets properly assembled by doing it this way)
			  throw new Exception(((rowCount == 0) ?  "Type list is empty":"No match found in list"));
		  }

		  operationMsg = "clear search view by clicking toolbar button '" + WizardConstants.REMOVE_ALL_MATCHES_BUTTON + "' in" + dialogContextMsg;
		  searchView.setFocus();
		  searchView.toolbarPushButton(WizardConstants.REMOVE_ALL_MATCHES_BUTTON).click();	//reset search window for the next time around
	  }
	  catch (Exception e)
	  {
		  throw new X10DT_Test_Exception("Failed to " + operationMsg + "'.\n        Reason: " + e.getMessage());
	  }

	  return found;
  }
  
  /*
   * C++ back end X10 Platform Configuration
   * 
   */
  
  /**
   * Generic c++ platform configuration editor.
   * This method sets the C++ Compilation and Linking tab
   * Control specifics through method arguments
   */
  
  //NB: This must be called AFTER the local/remote selection is made in the Connection and Communication Interface tab
  //
  public void setCppPlatformCompilationConfig(String projectName, PlatformConfig platformSetup)
  {
//TODO: consolidate this with compilation tab by passing editorBot from caller
    topLevelBot.viewByTitle(ViewConstants.PACKAGE_EXPLORER_VIEW_NAME).bot().tree().expandNode(projectName)
       .expandNode(PlatformConfConstants.PLATFORM_CONF_FILE).doubleClick();
    
    final SWTBot editorBot = topLevelBot.editorByTitle(PlatformConfConstants.PLATFORM_CONF_FILE).bot();

    //open the C++ Compilation and Linking tab
	editorBot.cTabItem(PlatformConfConstants.CPP_COMPILATION_LINKING_TAB).activate();
    //Set target operating system
	editorBot.comboBoxWithLabel(PlatformConfConstants.CPP_OS_COMBO_BOX).setSelection(platformSetup.os.getKey());
	//set target cpu architecture
    editorBot.comboBoxWithLabel(PlatformConfConstants.CPP_ARCHITECTURE_COMBO_BOX).setSelection(platformSetup.arch.getKey());
    
    //set or reset 64-bit architecture option
    SWTBotCheckBox checkBox64bit = editorBot.checkBox(PlatformConfConstants.CPP_64_BIT_CHECKBOX);
    if (platformSetup.set64bit) {
    	checkBox64bit.select();
    } else {
    	checkBox64bit.deselect();
    }
    
    //
    // NB: Currently leaving "MPI compile/link..." checkbox, 
    // and the complier options, archiver options, and linker options groups
    // at default settings

    //
    //Set up paths for a remote host
    if ( ! platformSetup.useLocalConnection) {
    	editorBot.textWithLabel(PlatformConfConstants.REMOTE_OUTPUT_FOLDER_TEXTBOX).setText(platformSetup.outputFolder);
    	editorBot.textWithLabel(PlatformConfConstants.X10_DISTRIBUTION_PATH_TEXTBOX).setText(platformSetup.remoteDistribution);
    	
    	SWTBotCheckBox checkBoxUseX10PGAS = editorBot.checkBox(PlatformConfConstants.CPP_USE_X10_DIST_PGAS);
    	if (platformSetup.useSelectedPGAS) {	//set up to use specific PGAS distribution
    		checkBoxUseX10PGAS.deselect();
    		editorBot.textWithLabel(PlatformConfConstants.PGAS_DISTRIBUTION_PATH_TEXTBOX).setText(platformSetup.remotePGASDist);
    	}
    	else {		//use the PGAS distribution provided with the X10 distribution
    		checkBoxUseX10PGAS.select();
    	}
    }
    
	//Validate Configuration in setCppPlatformConnectionConfig
	//Save Configuration in setCppPlatformConnectionConfig

  }

  /**
   * Generic c++ platform configuration editor.
   * This method sets the C++ Compilation and Linking tab
   * Control specifics through method arguments
   * 
   */
  
  //NB: Call this BEFORE calling setCppPlatformCompilationConfig
  //
  public void setCppPlatformConnectionConfig(String projectName, PlatformConfig	 platformSetup)
  {
	  //MNake sure we're in the X10 perspective before getting started
	  topLevelBot.perspectiveByLabel("X10").activate();

	  //TODO: consolidate this with compilation tab by passing editorBot from caller
	  topLevelBot.viewByTitle(ViewConstants.PACKAGE_EXPLORER_VIEW_NAME).bot().tree()
	  		.expandNode(projectName)
	  			.expandNode(PlatformConfConstants.PLATFORM_CONF_FILE).doubleClick();

	  final SWTBot editorBot = topLevelBot.editorByTitle(PlatformConfConstants.PLATFORM_CONF_FILE).bot();

	  //open the C++ Connection and Communication tab
	  editorBot.cTabItem(PlatformConfConstants.CPP_CONNECTION_COMMUNICATION_TAB).activate();

	  //set configuration name
	  editorBot.comboBoxWithLabel(PlatformConfConstants.PLATFORM_CONFIGURATION_NAME).setText(platformSetup.configName);
	  //set configuration description
	  editorBot.textWithLabel(PlatformConfConstants.PLATFORM_CONFIGURATION_DESCRIPTION).setText(platformSetup.description);

	  //Set interprocess communication type
	  editorBot.comboBoxWithLabel(PlatformConfConstants.CPP_INTERPROCESS_TYPE).setSelection(platformSetup.interfaceType.getKey());
	  //Set interprocess communication mode
	  editorBot.comboBoxWithLabel(PlatformConfConstants.CPP_INTERPROCESS_MODE).setSelection(platformSetup.interfaceMode.getKey());

	  if (platformSetup.useLocalConnection)
	  {
		  editorBot.radio(PlatformConfConstants.LOCAL_CONNECTION).click();	//well, *that* was easy.
	  }
	  else { //set up connection to a remote host (a lot less easy)

		  editorBot.radio(PlatformConfConstants.REMOTE_CONNECTION).click();	//but *this* is going to be less easy.		  
		  editorBot.button(PlatformConfConstants.ADD_BUTTON).click();

		  editorBot.sleep(1000);

//		  SWTBotTable remoteHostTableBot = editorBot.tableInGroup(PlatformConfConstants.WORKSPACE_PERSISTED_GROUP);
//		  SWTBotTableItem remoteNameItem = remoteHostTableBot.getTableItem(1);
		  final SWTBotTableItem remoteNameItem = editorBot.tableInGroup(PlatformConfConstants.WORKSPACE_PERSISTED_GROUP).getTableItem(0);

//		  for (int c = 0; c < platformSetup.remoteHostName.length(); c++) {
//			  remoteNameItem.pressShortcut(SWT.NONE, platformSetup.remoteHostName.charAt(c));
//		  }



		  remoteNameItem.pressShortcut(SWT.CR, SWT.LF);
		  editorBot.sleep(100);

		  editorBot.textWithLabel(PlatformConfConstants.HOST_TEXT_LABEL).setText(platformSetup.remoteHostURL); //$NON-NLS-1$
		  editorBot.textWithLabel(PlatformConfConstants.USER_TEXT_LABEL).setText(platformSetup.remoteHostUser); //$NON-NLS-1$

		  if (platformSetup.remoteHostPort > 0) {	//if caller specified a port number.  otherwise, leave it as the default port number
			  editorBot.spinnerWithLabel(PlatformConfConstants.HOST_PORT_LABEL).setSelection(platformSetup.remoteHostPort); //$NON-NLS-1$
		  }

		  if (platformSetup.usePassword) {
			  editorBot.radio(PlatformConfConstants.PASSWORD_RADIO_BUTTON).click();
			  editorBot.textWithLabel(PlatformConfConstants.PASSWORD_TEXT_LABEL).setText(platformSetup.remoteHostPassword);
		  }
		  else {	//use public key authentication
			  editorBot.radio(PlatformConfConstants.PUBLIC_KEY_AUTH_RADIO_BUTTON).click();
			  editorBot.radio(PlatformConfConstants.PUBLIC_KEY_AUTH_RADIO_BUTTON).click();
			  editorBot.radio(PlatformConfConstants.PUBLIC_KEY_AUTH_RADIO_BUTTON).click();
			  final String privateKeyFile = String.format("%s/.ssh/id_rsa", System.getProperty("user.home")); //$NON-NLS-1$ //$NON-NLS-2$
			  editorBot.textWithLabel(PlatformConfConstants.PRIVATE_KEY_FILE_LABEL).setText(privateKeyFile);
		  }

		  SWTBotCheckBox checkBoxPortForwarding = editorBot.checkBox(PlatformConfConstants.PORT_FORWARDING_CHECKBOX);
		  if (platformSetup.usePortForwarding) {
			  checkBoxPortForwarding.select();
			  editorBot.textWithLabel(PlatformConfConstants.PORT_FORWARDING_TIMEOUT).setText(platformSetup.portForwardingTimeout.toString());
			  editorBot.textWithLabel(PlatformConfConstants.PORT_FORWARDING_LOCAL_ADDR).setText(platformSetup.portForwardingLocalAddress);
		  }
		  else {	//don't do port forwarding
			  checkBoxPortForwarding.deselect();
		  }
		  
		  editorBot.button(PlatformConfConstants.VALIDATE_BUTTON).click();
		  editorBot.sleep(8000); // Leave the time to make the connection.

	  }
	  editorBot.cTabItem(PlatformConfConstants.CPP_COMPILATION_LINKING_TAB).activate();
	  //
	  //    	final String tempDir = System.getProperty("java.io.tmpdir"); //$NON-NLS-1$
	  //    	final String outputFolder = String.format("%s/test", tempDir); //$NON-NLS-1$
	  //    	editorBot.textWithLabel(PlatformConfConstants.OUTPUT_FOLDER_TEXT_LABEL).setText(outputFolder);
	  //    	final Bundle x10DistBundle = Platform.getBundle(Constants.X10_DIST_PLUGIN_ID);
	  //    	final URL url = x10DistBundle.getResource("include"); //$NON-NLS-1$
	  //    	final String x10DistLoc = new File(FileLocator.resolve(url).getFile()).getParent();
	  //    	editorBot.textWithLabel(PlatformConfConstants.X10_DIST_TEXT_LABEL).setText(x10DistLoc);

	  setCppPlatformCompilationConfig(projectName, platformSetup);

	  editorBot.toolbarButton(PlatformConfConstants.VALIDATE_PLATFORM_TOOLTIP_BT).click();

	  // The Progress Configuration dialog will open.  Wait for it to go away
	  topLevelBot.waitUntil(Conditions.shellIsActive(PlatformConfConstants.CPP_VALIDATION_PROGESS_DLG));
	  SWTBotShell progressShell = topLevelBot.shell(PlatformConfConstants.CPP_VALIDATION_PROGESS_DLG);
	  progressShell.activate();
	  
	  int timeoutCount = 12;	//loops waits 5 sec, we'll loop 12 times = 1 min
	  boolean stillWaiting = true;
	  while ((stillWaiting) && (0 < timeoutCount--)) {
		  try {
			  topLevelBot.waitUntil(Conditions.shellCloses(progressShell), 5000, 500); //did it go away yet?  Huh?  Huh?
			  stillWaiting = false;		//At last!
		  }
		  catch (TimeoutException e) {	//Awww...!! The validation is *still* running!
			  try {	//well, let's just hope that the error dialog didn't open
				  SWTBotShell ValidationFailureShell = topLevelBot.shell(PlatformConfConstants.CPP_VALIDATION_FAILURE_DLG);
				  //rats!! it opened.
				  //it's modal. Unless we click 'OK', we'll have to wait for the overall junit timeout
				  ValidationFailureShell.bot().button(WizardConstants.OK_BUTTON);
				  Assert.fail("Errors occurred in X10 Platform configuration validation");
			  }
			  catch (WidgetNotFoundException eWNF) { //this is a good thing.  It means that the error dialog didn't pop up.
				  //Keep waiting for progress dialog to go away
			  }
		  }
	  }
	  
	  if (stillWaiting) { //yawnnnnn...
		  Assert.fail("X10 Platform configuration validation failed to complete");
	  }

	  editorBot.toolbarButton(PlatformConfConstants.SAVE_PLATFORM_TOOLTIP_BT).click();
	  editorBot.sleep(6000);

	  Assert.assertEquals(0, ProblemsViewUtils.getErrorMessages(topLevelBot).length);
  }
}
