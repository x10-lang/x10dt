package x10dt.builders.tests;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEclipseEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import x10dt.builders.data.Data;
import x10dt.builders.data.timeout;
import x10dt.tests.services.swbot.constants.WizardConstants;
import x10dt.tests.services.swbot.utils.PerspectiveUtils;
import x10dt.tests.services.swbot.utils.ProblemsViewUtils;
import x10dt.tests.services.swbot.utils.ProjectUtils;

@RunWith(SWTBotJunit4ClassRunner.class)
public class MiscTests {

		private static SWTWorkbenchBot bot;

		@BeforeClass
		public static void beforeClass() throws Exception {
			bot = new SWTWorkbenchBot();
			bot.viewByTitle("Welcome").close();
			SWTBotPreferences.TIMEOUT= timeout.ONE_MINUTE; // Long timeout needed for first project creation
		}  
		
		@Before
		public void before() throws Exception {
			SWTBotPreferences.TIMEOUT = timeout.ONE_MINUTE;
			setUp(Data.MiscTestsProject);
		}
		
		/**
		 * The next test checks a situation in which we have a file that refers to a missing type.
		 * First it checks that we initially get the right number of compilation errors.
		 * Second it checks that when we add the missing type, all compilation errors go away.
		 * 
		 */
		@Test
		public void createAndCompileHi() throws Exception {
			ProjectUtils.createClass(bot, "Hi");
			SWTBotEclipseEditor srcEditor = bot.editorByTitle("Hi.x10").toTextEditor();
			srcEditor.setText(Data.Hi);
			srcEditor.save();
			SWTBotPreferences.TIMEOUT = timeout.ONE_MINUTE;
			Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, null);
			String[] errors = ProblemsViewUtils.getErrorMessages(bot);
			Assert.assertTrue("Compiling Hi should yield 3 errors: " + errors.length, errors.length == 3);
			ProjectUtils.createPackage(bot, Data.MiscTestsProject, "src", Data.pac);
			ProjectUtils.createClass(bot, "Howdy");
			Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, null);
			errors = ProblemsViewUtils.getErrorMessages(bot);
			Assert.assertTrue("All compilation errors should have been cleared: " + errors.length, errors.length == 0);
		}
		
		/**
		 * This method creates the test project.
		 * @throws Exception
		 */
		private void setUp(String project) throws Exception {
			PerspectiveUtils.switchToX10Perspective(bot);
			ProjectUtils.createX10ProjectWithJavaBackEndFromTopMenu(bot, project);
			SWTBotView pkgView = bot.viewByTitle("Package Explorer");
			SWTBotTree pkgTree = pkgView.bot().tree();
			SWTBotTreeItem projItem = pkgTree.getTreeItem(project);
			projItem.expand();
			SWTBotTreeItem srcItem = projItem.getNode("src");
			srcItem.expand();
			waitForBuildToFinish();
		}
		
	
		private void waitForBuildToFinish() throws Exception {
			Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, null);
		}
		
		/**
		 * This method cleans up the workspace: it deletes the project created.
		 * @throws Exception
		 */
		private void cleanUp(String project) throws Exception {
			SWTBotView pkgView = bot.viewByTitle("Package Explorer");
			SWTBotTree pkgTree = pkgView.bot().tree();
			SWTBotTreeItem projItem = pkgTree.getTreeItem(project);
			projItem.select();
			bot.menu(WizardConstants.EDIT_MENU).menu(WizardConstants.DELETE_MENU_ITEM).click();
			SWTBotShell deleteShell= bot.shell(WizardConstants.DELETE_RESOURCES_SHELL);
		    deleteShell.activate();
		    bot.checkBox(WizardConstants.DELETE_PROJECT_CONTENTS).select();
		    bot.button(WizardConstants.OK_BUTTON).click();
	    }
		
		
		@After
		public void after() throws Exception {
			cleanUp(Data.MiscTestsProject);
			bot.closeAllEditors();
			Assert.assertTrue("X10 Eclipse editors should all be closed.", bot.editors().isEmpty());
			bot.closeAllShells();
			}
	 
		@AfterClass
		public static void sleep() {
		}
	 
}
