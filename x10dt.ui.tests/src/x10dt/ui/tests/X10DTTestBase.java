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

package x10dt.ui.tests;

//import java.util.regex.Matcher;
import org.hamcrest.Matcher;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotRadio;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;

import x10dt.tests.services.swbot.constants.WizardConstants;

/**
 * @author rfuhrer@watson.ibm.com
 */
public class X10DTTestBase {
  /**
   * The top-level "bot" for the entire workbench.
   */
  public static SWTWorkbenchBot topLevelBot;

  /**
   * This method waits for a build to finish before continuing
   * 
   * @throws Exception
   */
  public static void waitForBuildToFinish() throws Exception {
    Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, null);
  }

  public static void createJavaBackEndProject(String projName, boolean withHello) throws X10DT_Test_Exception
  {
	  try
	  {
		  //Open the New Project dialog
		  try
		  {
			  topLevelBot.menu(WizardConstants.FILE_MENU)
			  		.menu(WizardConstants.NEW_MENU_ITEM)
			  			.menu(WizardConstants.PROJECTS_SUB_MENU_ITEM)
			  				.click();
		  }
		  catch (WidgetNotFoundException e)
		  {
			  throw new X10DT_Test_Exception("Cannot access New Project menu " +
					  WizardConstants.FILE_MENU +":"+
					  		WizardConstants.NEW_MENU_ITEM +":"+
					  			WizardConstants.PROJECTS_SUB_MENU_ITEM + 
					  				".\n        Reason: " + e.getMessage());
		  }

		  //Select the New Java Backend project wizard in the X10 folder
		  try
		  {
			  SWTBotShell newProjShell = topLevelBot.shell(WizardConstants.NEW_PROJECT_DIALOG_TITLE);

			  newProjShell.activate();

			  SWTBot newProjBot = newProjShell.bot();
			  SWTBotTreeItem x10ProjItems = newProjBot.tree().expandNode(WizardConstants.X10_FOLDER);

			  x10ProjItems.select(WizardConstants.X10_PROJECT_JAVA_BACKEND);

			  SWTBotButton nextBut = newProjBot.button(WizardConstants.NEXT_BUTTON);

			  nextBut.click();
		  }
		  catch (WidgetNotFoundException e)
		  {
			  throw new X10DT_Test_Exception("Cannot select Java backend X10 project wizard.\n        Reason: " + e.getMessage());
		  }

		  //Set up the new X10 project - specify project name and select whether or not to create Hello World sample code
		  try
		  {
			  SWTBotShell newX10ProjShell = topLevelBot.shell(WizardConstants.X10_PROJECT_SHELL_JAVA_BACKEND);
			  newX10ProjShell.activate();
			  SWTBot newX10ProjBot = newX10ProjShell.bot();

			  SWTBotRadio radioBtn; 

			  newX10ProjBot.textWithLabel(WizardConstants.NEW_JAVA_PROJECT_NAME_FIELD).setFocus();
			  newX10ProjBot.textWithLabel(WizardConstants.NEW_JAVA_PROJECT_NAME_FIELD).setText(projName);

			  if (withHello) {
				  radioBtn = 
					  newX10ProjBot.radioInGroup(WizardConstants.NEW_X10_PROJECT_HELLO_SOURCE_RADIO,
							  WizardConstants.NEW_X10_PROJECT_SAMPLE_SOURCE_GROUP);
			  } else {
				  radioBtn = 
					  newX10ProjBot.radioInGroup(WizardConstants.NEW_X10_PROJECT_NO_SAMPLE_SOURCE_RADIO,
							  WizardConstants.NEW_X10_PROJECT_SAMPLE_SOURCE_GROUP);
			  }
			  radioBtn.setFocus();
			  radioBtn.click();

			  newX10ProjBot.button(WizardConstants.FINISH_BUTTON).click();

			  topLevelBot.waitUntil(Conditions.shellCloses(newX10ProjShell));
		  }
		  catch (WidgetNotFoundException e)
		  {
			  throw new X10DT_Test_Exception("Wizard cannot generate new Java project.\n        Reason: " + e.getMessage());
		  }
	  }
	  
	  catch (Exception e)	//some other error
	  {
		  throw new X10DT_Test_Exception("Cannot create new Java project.\n        Reason: " + e.getMessage());
	  }
  }
  
  public static void importJavaBackEndArchiveProject(String archiveName, String folderName, boolean doOverwrite) throws Exception {
	  topLevelBot.menu(WizardConstants.FILE_MENU).menu(WizardConstants.IMPORT_MENU_ITEM).click();

	  SWTBotShell selectArchiveShell = topLevelBot.shell(WizardConstants.IMPORT_ARCHIVE_DIALOG_TITLE);

	  selectArchiveShell.activate();

	  SWTBot selectArchiveBot = selectArchiveShell.bot();
	  SWTBotTreeItem x10ProjItems = selectArchiveBot.tree().expandNode(WizardConstants.GENERAL_FOLDER);

	  x10ProjItems.select(WizardConstants.IMPORT_ARCHIVE);

	  SWTBotButton nextBut = selectArchiveBot.button(WizardConstants.NEXT_BUTTON);

	  nextBut.click();

	  SWTBotShell importArchiveShell = topLevelBot.shell(WizardConstants.IMPORT_ARCHIVE_DIALOG_TITLE);
	  importArchiveShell.activate();

	  SWTBot importArchiveBot = importArchiveShell.bot();

	  importArchiveBot.comboBoxWithLabel(WizardConstants.FROM_ARCHIVE_FILE_FIELD).setText(archiveName);
	  importArchiveBot.comboBoxWithLabel(WizardConstants.FROM_ARCHIVE_FILE_FIELD).setSelection(0);
	  importArchiveBot.textWithLabel(WizardConstants.INTO_FOLDER_FIELD).setText(folderName);
	  
	  if (doOverwrite) {
		  importArchiveBot.checkBox(WizardConstants.OVERWRITE_EXISTING_CHECKBOX).select();
		  importArchiveBot.button(WizardConstants.FINISH_BUTTON).click();
	  } else {
//		  importArchiveBot.checkBox(WizardConstants.OVERWRITE_EXISTING_CHECKBOX).deselect();
//		  importArchiveBot.button(WizardConstants.FINISH_BUTTON).click();
//		  Matcher areYouSureAlert = allOf (widgetOfType(Label.class), withRegex("Overwrite .*"));
//		  try {
//			  SWTBotShell areYouSureShell = importArchiveBot.shell(areYouSureAlert.toString());
//			  SWTBotButton yesToAllBut = areYouSureShell.bot().button(WizardConstants.YES_TO_ALL_BUTTON);
//			  yesToAllBut.click();
//		  }
//		  catch (WidgetNotFoundException e)
//		  {
//			  //this is ok - there's nothing to overwrite
//		  }
//		  catch (TimeoutException e)
//		  {
//			  //this is ok - there's nothing to overwrite
//		  }
		  
	  } //else doOverwrite == false

	  topLevelBot.waitUntil(Conditions.shellCloses(importArchiveShell));
  }

  public static void openX10FileInEditor(String projName, String fileName) throws Exception {
	    SWTBotView packageExplorerView = topLevelBot.viewByTitle(WizardConstants.PACKAGE_EXPLORER_VIEW_TITLE);
	    SWTBot packageExplorerBot = packageExplorerView.bot();
	    SWTBotTree packageTree = packageExplorerBot.tree();
	    SWTBotTreeItem projectFolder = packageTree.expandNode(projName);
	    SWTBotTreeItem srcFolder = projectFolder.expandNode("src");
	    SWTBotTreeItem srcFile = srcFolder.expandNode(fileName);
	    srcFile.select();

//	    SWTBotMenu navMenu = topLevelBot.menu(WizardConstants.NAVIGATE_MENU);
//	    navMenu.click();
//	    SWTBotMenu openMenu = navMenu.menu(WizardConstants.OPEN_ITEM);
//	    openMenu.click();
	    topLevelBot.menu(WizardConstants.NAVIGATE_MENU).menu(WizardConstants.OPEN_ITEM).click();
  }


  
  public static void createCPPBackEndProject(String projName, boolean withHello) throws Exception {
	    topLevelBot.menu(WizardConstants.FILE_MENU).menu(WizardConstants.NEW_MENU_ITEM).menu(WizardConstants.PROJECTS_SUB_MENU_ITEM).click();

	    SWTBotShell newProjShell = topLevelBot.shell(WizardConstants.NEW_PROJECT_DIALOG_TITLE);

	    newProjShell.activate();

	    SWTBot newProjBot = newProjShell.bot();
	    SWTBotTreeItem x10ProjItems = newProjBot.tree().expandNode(WizardConstants.X10_FOLDER);

	    x10ProjItems.select(WizardConstants.X10_PROJECT_CPP_BACKEND);

	    SWTBotButton nextBut = newProjBot.button(WizardConstants.NEXT_BUTTON);

	    nextBut.click();

	    SWTBotShell newX10ProjShell = topLevelBot.shell(WizardConstants.X10_PROJECT_SHELL_CPP_BACKEND);
	    newX10ProjShell.activate();
	    SWTBot newX10ProjBot = newX10ProjShell.bot();

	    newX10ProjBot.textWithLabel(WizardConstants.NEW_CPP_PROJECT_NAME_FIELD).setText(projName);
	    if (withHello) {
	      newX10ProjBot.checkBox(WizardConstants.NEW_X10_PROJECT_HELLO_SOURCE_CHECKBOX).select();
	    } else {
		      newX10ProjBot.checkBox(WizardConstants.NEW_X10_PROJECT_HELLO_SOURCE_CHECKBOX).deselect();
	    }
	    newX10ProjBot.button(WizardConstants.FINISH_BUTTON).click();

	    topLevelBot.waitUntil(Conditions.shellCloses(newX10ProjShell));
	  }

}
