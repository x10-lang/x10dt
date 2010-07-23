/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package org.eclipse.imp.x10dt.ui.launch.cpp;

import static org.eclipse.imp.x10dt.tests.services.swbot.constants.WizardConstants.NEW_CPP_PROJECT_NAME;
import static org.eclipse.imp.x10dt.tests.services.swbot.constants.WizardConstants.X10_PROJECT_CPP_BACKEND;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import junit.framework.Assert;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.imp.x10dt.tests.services.swbot.conditions.X10DTConditions;
import org.eclipse.imp.x10dt.tests.services.swbot.constants.LaunchConstants;
import org.eclipse.imp.x10dt.tests.services.swbot.constants.PlatformConfConstants;
import org.eclipse.imp.x10dt.tests.services.swbot.constants.ViewConstants;
import org.eclipse.imp.x10dt.tests.services.swbot.constants.WizardConstants;
import org.eclipse.imp.x10dt.tests.services.swbot.utils.PerspectiveUtils;
import org.eclipse.imp.x10dt.tests.services.swbot.utils.ProblemsViewUtils;
import org.eclipse.imp.x10dt.tests.services.swbot.utils.ProjectUtils;
import org.eclipse.imp.x10dt.tests.services.swbot.utils.SWTBotUtils;
import org.eclipse.imp.x10dt.ui.launch.core.Constants;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableItem;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.osgi.framework.Bundle;

/**
 * Tests a local and remote (to localhost) launch of Hello World program with C++ back-end.
 * 
 * @author egeay
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public final class LocalAndRemoteLaunchTest {
  
  // --- Before and After
  
  @BeforeClass @SuppressWarnings("all") public static void beforeClass() throws Exception {
    bot = new SWTWorkbenchBot();
    bot.viewByTitle("Welcome").close();
  }
  
  @AfterClass @SuppressWarnings("all") public static void sleep() {
    bot.sleep(2000);
  }
  
  // --- Test cases
  
  /**
   * Tests compilation and run of an X10 program without any intermediate actions.
   */
  @Test public void shouldCompileAndRunHelloWorldLocally() {
    final String projectName = "demo"; //$NON-NLS-1$
    ProjectUtils.createX10ProjectWithCppBackEndFromTopMenu(bot, projectName);
    
    bot.menu(LaunchConstants.RUN_MENU).menu(LaunchConstants.RUN_CONFS_MENU_ITEM).click();
    final SWTBotShell runShell = bot.shell(LaunchConstants.RUN_CONF_DIALOG_TITLE);
    runShell.activate();
    bot.tree().select(LaunchConstants.NEW_CPP_LAUNCH_CONFIG).contextMenu(LaunchConstants.NEW_CONF_CONTEXT_MENU).click();
    
    bot.cTabItem(LaunchConstants.CPP_LAUNCH_CONFIG_APPLICATION_TAB).activate();
    bot.textInGroup(LaunchConstants.CPP_LAUNCH_CONFIG_X10_PROJECT, 0).setText(projectName);
    bot.textInGroup(LaunchConstants.CPP_LAUNCH_CONFIG_MAIN_CLASS, 0).setText("Hello"); //$NON-NLS-1$
    
    runShell.bot().button(LaunchConstants.RUN_BUTTON).click();
    
    final SWTBotView consoleView = bot.viewByTitle(ViewConstants.CONSOLE_VIEW_NAME);
    bot.waitUntil(X10DTConditions.workbenchPartIsActive(consoleView), 20000);
    consoleView.bot().styledText().getText().contains("Hello X10 world"); //$NON-NLS-1$
  }
  
  /**
   * Tests creation of new project with the same name than the previous one. This should disable the next button.
   * Then, we cancel it and test that the project is still here in the Package Explorer.
   */
  @Test public void shouldNotCreateProjectWithExistingName() {
    final String projectName = "demo"; //$NON-NLS-1$    
    PerspectiveUtils.switchToX10Perspective(bot);
    bot.viewByTitle(ViewConstants.PACKAGE_EXPLORER_VIEW_NAME).setFocus();
    bot.sleep(1000);
    SWTBotUtils.findSubMenu(bot.tree().contextMenu(ViewConstants.NEW_MENU), X10_PROJECT_CPP_BACKEND).click();
    
    bot.textWithLabel(NEW_CPP_PROJECT_NAME).setText(projectName);
    
    Assert.assertFalse(bot.button(WizardConstants.NEXT_BUTTON).isEnabled());
    bot.button(WizardConstants.CANCEL_BUTTON).click();
    
    bot.viewByTitle(ViewConstants.PACKAGE_EXPLORER_VIEW_NAME).setFocus();
    Assert.assertTrue(bot.tree().hasItems());
  }
  
  /**
   * Tests compilation and running of the original demo project remotely.
   * @throws IOException Occurs if we could not resolve the URL for the X10 distribution bundle.
   */
  @Test public void shouldCompileAndLaunchRemotely() throws IOException {
    bot.viewByTitle(ViewConstants.PACKAGE_EXPLORER_VIEW_NAME).bot().tree().expandNode("demo") //$NON-NLS-1$
       .expandNode(PlatformConfConstants.PLATFORM_CONF_FILE).doubleClick();
    
    final SWTBot editorBot = bot.editorByTitle(PlatformConfConstants.PLATFORM_CONF_FILE).bot();
    
    editorBot.radio(PlatformConfConstants.REMOTE_CONNECTION).click();
    editorBot.button(PlatformConfConstants.ADD_BUTTON).click();
    
    editorBot.sleep(1000);
    
    final SWTBotTableItem botTabItem = editorBot.tableInGroup(PlatformConfConstants.WORKSPACE_PERSISTED_GROUP).getTableItem(0);
    botTabItem.pressShortcut(SWT.NONE, 'l');
    botTabItem.pressShortcut(SWT.NONE, 'o');
    botTabItem.pressShortcut(SWT.NONE, 'c');
    botTabItem.pressShortcut(SWT.NONE, 'a');
    botTabItem.pressShortcut(SWT.NONE, 'l');
    botTabItem.pressShortcut(SWT.CR, SWT.LF);
    editorBot.sleep(100);
    
    editorBot.textWithLabel(PlatformConfConstants.HOST_TEXT_LABEL).setText("localhost"); //$NON-NLS-1$
    editorBot.textWithLabel(PlatformConfConstants.USER_TEXT_LABEL).setText(System.getProperty("user.name")); //$NON-NLS-1$
    editorBot.radio(PlatformConfConstants.PUBLIC_KEY_AUTH_RADIO_BUTTON).click();
    final String privateKeyFile = String.format("%s/.ssh/id_rsa", System.getProperty("user.home")); //$NON-NLS-1$ //$NON-NLS-2$
    editorBot.textWithLabel(PlatformConfConstants.PRIVATE_KEY_FILE_LABEL).setText(privateKeyFile);
    editorBot.button(PlatformConfConstants.VALIDATE_BUTTON).click();
    editorBot.sleep(4000); // Leave the time to make the connection.
    
    editorBot.cTabItem(PlatformConfConstants.CPP_COMPILATION_LINKING_TAB).activate();
    
    final String tempDir = System.getProperty("java.io.tmpdir"); //$NON-NLS-1$
    final String outputFolder = String.format("%s/test", tempDir); //$NON-NLS-1$
    editorBot.textWithLabel(PlatformConfConstants.OUTPUT_FOLDER_TEXT_LABEL).setText(outputFolder);
    final Bundle x10DistBundle = Platform.getBundle(Constants.X10_DIST_PLUGIN_ID);
    final URL url = x10DistBundle.getResource("include"); //$NON-NLS-1$
    final String x10DistLoc = new File(FileLocator.resolve(url).getFile()).getParent();
    editorBot.textWithLabel(PlatformConfConstants.X10_DIST_TEXT_LABEL).setText(x10DistLoc);
    
    editorBot.toolbarButtonWithTooltip(PlatformConfConstants.SAVE_PLATFORM_TOOLTIP_BT).click();
    
    editorBot.sleep(6000);
    
    Assert.assertEquals(0, ProblemsViewUtils.getErrorMessages(bot).length);
    
    SWTBotUtils.findSubMenu(bot.menu(LaunchConstants.RUN_MENU).menu(LaunchConstants.RUN_HISTORY_ITEM), "&1 New_configuration") //$NON-NLS-1$
               .click();
    
    final SWTBotView consoleView = bot.viewByTitle(ViewConstants.CONSOLE_VIEW_NAME);
    bot.waitUntil(X10DTConditions.workbenchPartIsActive(consoleView), 10000);
    consoleView.bot().styledText().getText().contains("Hello X10 world"); //$NON-NLS-1$
  }
  
  // --- Fields
  
  private static SWTWorkbenchBot bot;

}