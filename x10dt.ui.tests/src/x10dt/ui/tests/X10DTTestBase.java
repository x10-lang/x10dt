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

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

import x10dt.tests.services.swbot.constants.WizardConstants;

/**
 * @author rfuhrer@watson.ibm.com
 */
public class X10DTTestBase {
  /**
   * The top-level "bot" for the entire workbench.
   */
  public static SWTWorkbenchBot topLevelBot;

  public static void createJavaBackEndProject(String projName, boolean withHello) throws Exception {
    topLevelBot.menu(WizardConstants.FILE_MENU).menu(WizardConstants.NEW_MENU_ITEM)
    .menu(WizardConstants.PROJECTS_SUB_MENU_ITEM).click();

    SWTBotShell newProjShell = topLevelBot.shell(WizardConstants.NEW_PROJECT_DIALOG_TITLE);

    newProjShell.activate();

    SWTBot newProjBot = newProjShell.bot();
    SWTBotTreeItem x10ProjItems = newProjBot.tree().expandNode(WizardConstants.X10_FOLDER);

    x10ProjItems.select(WizardConstants.X10_PROJECT_JAVA_BACKEND);

    SWTBotButton nextBut = newProjBot.button(WizardConstants.NEXT_BUTTON);

    nextBut.click();

    SWTBotShell newX10ProjShell = topLevelBot.shell(WizardConstants.X10_PROJECT_SHELL_JAVA_BACKEND);
    SWTBot newX10ProjBot = newX10ProjShell.bot();

    newX10ProjBot.textWithLabel(WizardConstants.NEW_JAVA_PROJECT_NAME_FIELD).setText(projName);
    if (withHello) {
      newX10ProjBot.radioInGroup(WizardConstants.NEW_X10_PROJECT_HELLO_SOURCE_RADIO,
                                 WizardConstants.NEW_X10_PROJECT_SAMPLE_SOURCE_GROUP).click();
    } else {
      newX10ProjBot.radioInGroup(WizardConstants.NEW_X10_PROJECT_NO_SAMPLE_SOURCE_RADIO,
                                 WizardConstants.NEW_X10_PROJECT_SAMPLE_SOURCE_GROUP).click();
    }
    newX10ProjBot.button(WizardConstants.FINISH_BUTTON).click();

    topLevelBot.waitUntil(Conditions.shellCloses(newX10ProjShell));

  }
}
