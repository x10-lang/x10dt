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

package x10dt.ui.editor;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.imp.editor.UniversalEditor;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.utils.Position;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.IEditorPart;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import x10dt.tests.services.swbot.constants.WizardConstants;
import x10dt.tests.services.swbot.utils.ProjectUtils;
import x10dt.ui.tests.utils.EditorMatcher;

/**
 * @author rfuhrer@watson.ibm.com
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class QuickOutlineTests extends X10DTEditorTestBase {
  private static final String PROJECT_NAME = "TestOutline";

  private static final String CLASS_NAME = "Hello";

  private static final String SRC_FILE_NAME = CLASS_NAME + ".x10";

  @BeforeClass
  public static void beforeClass() throws Exception {
    X10DTEditorTestBase.BeforeClass();
    createJavaBackEndProject(PROJECT_NAME, false);
    topLevelBot.shells()[0].activate();
  }

  @After
  public void after() throws Exception {
      afterTest();
  }

  @AfterClass
  public static void afterClass() throws Exception {
      X10DTEditorTestBase.AfterClass();
  }

  @Test
  public void test1() throws Exception {
    ProjectUtils.createClass(topLevelBot, CLASS_NAME);
    topLevelBot.waitUntil(Conditions.waitForEditor(new EditorMatcher(SRC_FILE_NAME)));

    fSrcEditor = topLevelBot.activeEditor().toTextEditor();
    junit.framework.Assert.assertEquals(SRC_FILE_NAME, fSrcEditor.getTitle());

    final IEditorPart editorPart = fSrcEditor.getReference().getEditor(false);
    final UniversalEditor univEditor = (UniversalEditor) editorPart;

    fUpdateListener= new UpdateListener();
    univEditor.addModelListener(fUpdateListener);
    part1();
    part2();
  }

  private void part1() throws Exception {
    fSrcEditor.setFocus();
    fSrcEditor.insertText(1, 0, "public static def main(Array[String]) {}\n");
    fSrcEditor.insertText(2, 0, "public static def foo() { }\n");

    waitForParser();
    fSrcEditor.save();
    waitForBuildToFinish(); // WHY???

    SWTBot outlineBot = invokeQuickOutline(topLevelBot);// TODO

    SWTBotTreeItem classItem = outlineBot.tree().getTreeItem(CLASS_NAME);
    SWTBotTreeItem mainItem = classItem.getNode("main(x10.array.Array[x10.lang.String])");

    classItem.getNode("foo()");
    mainItem.doubleClick();

    Position cursorPos = fSrcEditor.cursorPosition();

    junit.framework.Assert.assertEquals("Cursor positioned at incorrect line after quick outline item selection", 1,
                                        cursorPos.line);
    junit.framework.Assert.assertEquals("Cursor positioned at incorrect column after quick outline item selection", 1,
                                        cursorPos.column);
  }

  private void part2() throws Exception {
    fUpdateListener.reset();

    fSrcEditor.setFocus();
    // topLevelBot.activeShell().setFocus();
    fSrcEditor.insertText(3, 0, "public static def bar() { }\n");
    fSrcEditor.insertText(4, 0, "public static def bletch() { }\n");

    // a better way of determining when the AST has been updated
    waitForParser();
    fSrcEditor.save();
    waitForBuildToFinish(); // WHY???

    SWTBot outlineBot = invokeQuickOutline(topLevelBot);

    SWTBotTreeItem classItem = outlineBot.tree().getTreeItem(CLASS_NAME);

    classItem.getNode("bar()");

    SWTBotTreeItem bletchItem = classItem.getNode("bletch()");

    bletchItem.doubleClick();

    Position cursorPos = fSrcEditor.cursorPosition();

    junit.framework.Assert.assertEquals("Cursor positioned at incorrect line after quick outline item selection", 4,
                                        cursorPos.line);
    junit.framework.Assert.assertEquals("Cursor positioned at incorrect column after quick outline item selection", 1,
                                        cursorPos.column);
  }

  /**
   * This method waits for a build to finish before continuing
   * 
   * @throws Exception
   */
  private void waitForBuildToFinish() throws Exception {
    Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, null);
  }

  private SWTBot invokeQuickOutline(SWTBot bot) throws Exception {

    fSrcEditor.pressShortcut(Keystrokes.CTRL, KeyStroke.getInstance("o"));

    SWTBotShell outlineShell = topLevelBot.shell(WizardConstants.QUICK_OUTLINE_SHELL); // TODO Fix this

    outlineShell.activate();

    return outlineShell.bot();
  }
}
