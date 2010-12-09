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
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.imp.editor.UniversalEditor;
import org.eclipse.imp.parser.IModelListener;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEclipseEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.utils.Position;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
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
import x10dt.tests.services.swbot.utils.SWTBotUtils;
import x10dt.ui.tests.X10DTTestBase;
import x10dt.ui.tests.utils.EditorMatcher;
import x10dt.core.utils.Timeout;

/**
 * @author rfuhrer@watson.ibm.com
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class QuickOutlineTests extends X10DTTestBase {
  /**
   * The bot for the editor used to test the quick outline view.
   */
  protected SWTBotEclipseEditor fSrcEditor;

  private static final String PROJECT_NAME = "TestOutline";

  private static final String CLASS_NAME = "Hello";

  private static final String SRC_FILE_NAME = CLASS_NAME + ".x10";

  private static boolean updated = false;

  @BeforeClass
  public static void beforeClass() throws Exception {
    SWTBotPreferences.KEYBOARD_STRATEGY = "org.eclipse.swtbot.swt.finder.keyboard.SWTKeyboardStrategy";
    topLevelBot = new SWTWorkbenchBot();
    SWTBotPreferences.TIMEOUT = Timeout.SIXTY_SECONDS; // TODO remove this ?

    SWTBotUtils.closeWelcomeViewIfNeeded(topLevelBot);
    topLevelBot.perspectiveByLabel("X10").activate();
    createJavaBackEndProject(PROJECT_NAME, false);
    topLevelBot.shells()[0].activate();
  }

  @After
  public void after() throws Exception {
    SWTBotUtils.closeAllEditors(topLevelBot);
    SWTBotUtils.closeAllShells(topLevelBot);
  }

  @AfterClass
  public static void afterClass() throws Exception {
    SWTBotUtils.saveAllDirtyEditors(topLevelBot);
    SWTBotUtils.resetWorkbench(topLevelBot);
  }

  private class MyListener implements IModelListener {

    public AnalysisRequired getAnalysisRequired() {
      return AnalysisRequired.NONE; // this posts the error annotations!
    }

    public void update(IParseController parseController, IProgressMonitor monitor) {
      updated = true;
    }
  }

  @Test
  public void test1() throws Exception {
    ProjectUtils.createClass(topLevelBot, CLASS_NAME);
    topLevelBot.waitUntil(Conditions.waitForEditor(new EditorMatcher(SRC_FILE_NAME)));

    fSrcEditor = topLevelBot.activeEditor().toTextEditor();
    junit.framework.Assert.assertEquals(SRC_FILE_NAME, fSrcEditor.getTitle());

    final IEditorPart editorPart = fSrcEditor.getReference().getEditor(false);
    final UniversalEditor univEditor = (UniversalEditor) editorPart;
    univEditor.addModelListener(new MyListener());
    part1();
    part2();

  }

  private void part1() throws Exception {
    fSrcEditor.setFocus();
    fSrcEditor.insertText(1, 0, "public static def main(Array[String]) {}\n");
    fSrcEditor.insertText(2, 0, "public static def foo() { }\n");

    WaitForParser();
    fSrcEditor.save();
    waitForBuildToFinish();

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

  // WaitForParser
  public static void WaitForParser() throws Exception {
    topLevelBot.waitUntil(new DefaultCondition() {

      public boolean test() throws Exception {
        return updated == true;
      }

      public String getFailureMessage() {
        return "Some Failure Message";
      }

    }, Timeout.SIXTY_SECONDS);
  }

  private void part2() throws Exception {
    updated = false;
    fSrcEditor.setFocus();
    // topLevelBot.activeShell().setFocus();
    fSrcEditor.insertText(3, 0, "public static def bar() { }\n");
    fSrcEditor.insertText(4, 0, "public static def bletch() { }\n");

    // a better way of determining when the AST has been updated
    WaitForParser();
    fSrcEditor.save();
    waitForBuildToFinish();

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
