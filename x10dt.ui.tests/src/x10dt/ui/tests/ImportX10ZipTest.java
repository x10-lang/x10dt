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

import java.util.List;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.waits.WaitForView;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEclipseEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCTabItem;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
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
import x10dt.tests.services.swbot.utils.SWTBotUtils;
import x10dt.ui.tests.utils.EditorMatcher;

/**
 * @author lesniakr@us.ibm.com
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class ImportX10ZipTest extends X10DTTestBase {
  public static final String JAVA_BACK_END_PROJECT_DIALOG_NAME_FIELD = "Name:"; //$NON-NLS-1$

  private static final String CLASS_NAME = "ZipTest";//"Hello";  //$NON-NLS-1$

  private static final String CLASS_SRCFILE_NAME = CLASS_NAME + ".x10"; //$NON-NLS-1$

  private final static String LINE_MARKER = "public static def main("; //$NON-NLS-1$
  
  private static final String ARCHIVE_NAME = "/Users/lesniakr/x10dt/wksp/ZipTest.zip"; //"../ZipTest.zip"; //$NON-NLS-1$	// specify file at top level of this workspace

  private static final String PROJECT_NAME = "ZipTestUnzipped"; //$NON-NLS-1$	//will be created as a new empty project to accept the import


  /**
   * The bot for the editor created for the "ZipTest" archive class.
   */
  protected SWTBotEclipseEditor fZipTestEditor;

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
  public void basicImportZipTest() throws Exception {
	  
    Boolean createHello = false;
    try
    {
    	createJavaBackEndProject(PROJECT_NAME, createHello);
    }
    catch (X10DT_Test_Exception e)
    {
    	Assert.fail("Failed to create Java Backend Project.\n     Reason: " + e.getMessage()); //$NON-NLS-1$
    }

    try
    {
	    importJavaBackEndArchiveProject(ARCHIVE_NAME, PROJECT_NAME + "/src", true);
	    topLevelBot.waitUntil(Conditions.waitForEditor(new EditorMatcher("Hello.x10")));
	}
    catch (X10DT_Test_Exception e)
    {
    	Assert.fail("Failed to import Java Backend Project.\n     Reason: " + e.getMessage()); //$NON-NLS-1$
    }

    try
    {
    	openX10FileInEditor(PROJECT_NAME, CLASS_SRCFILE_NAME);
	    topLevelBot.waitUntil(Conditions.waitForEditor(new EditorMatcher(CLASS_SRCFILE_NAME)));
	}
    catch (X10DT_Test_Exception e)
    {
    	Assert.fail("Failed to open ." + CLASS_SRCFILE_NAME + "\n     Reason: " + e.getMessage()); //$NON-NLS-1$
    }

    String launchName = PROJECT_NAME;
//    try
//    {
    	fZipTestEditor = topLevelBot.editorByTitle(CLASS_SRCFILE_NAME).toTextEditor();
    	createAndRunJavaBackEndLaunchConfig(launchName, PROJECT_NAME, CLASS_NAME);
//    }
//    catch (X10DT_Test_Exception e)
//    {
//    	Assert.fail("Failed to execute Java Backend Project.\n     Reason: " + e.getMessage()); //$NON-NLS-1$
//    }

    //Well, let's see if it worked
    try
    {
    	boolean match = checkConsoleOutput("Hello, World!\n"); //$NON-NLS-1$
        Assert.assertTrue("ImportArchiveTest: Console output does not match", match); //$NON-NLS-1$
        
    	// let's change the source code to produce different console output, and run it again
    	modifySrcText();
    	launchRunModeLaunchConfig("1 " + launchName);
    	
    	match = checkConsoleOutput("Huh?\n" + "Hello, World!\n"); //$NON-NLS-1$ //$NON-NLS-2$
        Assert.assertTrue("ImportArchiveTest: Modified console output does not match", match); //$NON-NLS-1$
    }
    catch (Exception e)
    {
    	Assert.fail("An error of great mystery and ominous portent has occurred.\n  We must be cautious, for it is written:\n \"Behold! " + e.getMessage() + "\"");
    }
  }

  private void modifySrcText() {
    List<String> lines = fZipTestEditor.getLines();
    for (int i = 0; i < lines.size(); i++) {
      if (lines.get(i).contains(LINE_MARKER)) {
        fZipTestEditor.insertText(i + 1, 0, "    Console.OUT.println(\"Huh?\");\n"); //$NON-NLS-1$
        break;
      }
    }
    fZipTestEditor.save();
  }

  private void launchRunModeLaunchConfig(String launchMenuItemName) {
    topLevelBot.menu(LaunchConstants.RUN_MENU)
    			.menu(LaunchConstants.RUN_HISTORY_ITEM)
    			.menu(launchMenuItemName).click(); //$NON-NLS-1$
  }

  public static void createAndRunJavaBackEndLaunchConfig(String launchName, String projectName, String mainTypeName) {
    topLevelBot.menu(LaunchConstants.RUN_MENU).menu(LaunchConstants.RUN_CONFS_MENU_ITEM).click();
    topLevelBot.waitUntil(Conditions.shellIsActive(LaunchConstants.RUN_CONF_DIALOG_TITLE));

    SWTBotShell configsShell = topLevelBot.shell(LaunchConstants.RUN_CONF_DIALOG_TITLE);

    configsShell.activate();
    SWTBot configsBot = configsShell.bot();

    SWTBotTreeItem x10AppItem = configsBot.tree().getTreeItem(LaunchConstants.JAVA_BACK_END_LAUNCH_CONFIG_TYPE);
    x10AppItem.doubleClick();

    SWTBotText launchNameText = configsBot.textWithLabel(JAVA_BACK_END_PROJECT_DIALOG_NAME_FIELD);
    launchNameText.setText(launchName);

    SWTBotCTabItem mainTab = configsBot.cTabItem(LaunchConstants.JAVA_BACK_END_LAUNCH_CONFIG_MAIN_TAB);

    mainTab.activate();
    configsBot.textInGroup(LaunchConstants.JAVA_BACK_END_LAUNCH_CONFIG_PROJECT, 0).setText(projectName);
    configsBot.textInGroup(LaunchConstants.JAVA_BACK_END_LAUNCH_CONFIG_MAIN_CLASS, 0).setText(mainTypeName);

    configsBot.button(LaunchConstants.RUN_BUTTON).click();
  }

  public static boolean checkConsoleOutput(String contents) {
    // look for the Console view, and check the output
    final Matcher<IViewReference> withPartName = WidgetMatcherFactory.withPartName(ViewConstants.CONSOLE_VIEW_NAME);
    final WaitForView waitForConsole = Conditions.waitForView(withPartName);

    topLevelBot.waitUntil(waitForConsole);

    SWTBotView consoleView = new SWTBotView(waitForConsole.get(0), topLevelBot);

    return consoleView.bot().styledText().getText().equals(contents);
  }
}
