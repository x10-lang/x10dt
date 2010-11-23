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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.imp.editor.UniversalEditor;
import org.eclipse.imp.parser.IModelListener;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.utils.StreamUtils;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEclipseEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.IEditorPart;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.osgi.framework.Bundle;

import polyglot.ast.ClassDecl;
import polyglot.ast.FieldDecl;
import polyglot.ast.MethodDecl;
import polyglot.ast.Node;
import polyglot.ast.SourceFile;
import polyglot.visit.NodeVisitor;
import x10dt.tests.services.swbot.utils.ProjectUtils;
import x10dt.tests.services.swbot.utils.SWTBotUtils;
import x10dt.ui.parser.ParseController;
import x10dt.ui.tests.X10DTTestBase;
import x10dt.ui.tests.utils.EditorMatcher;

/**
 * @author rfuhrer@watson.ibm.com
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class OutlineTests extends X10DTTestBase{
    private static final String PROJECT_NAME= "OutlineProject"; //$NON-NLS-1$

//    private static final String CLASS_NAME= "MyClass"; //$NON-NLS-1$

  //  private static final String CLASS_SRCFILE_NAME= CLASS_NAME + ".x10"; //$NON-NLS-1$
    
    private static final String CLASS_NAME_1 = "Hello";
    private static final String CLASS_NAME_2 = "KMeansSPMD";
    private static final String CLASS_NAME_3 = "FRASimpleDist";
    private static final String CLASS_NAME_4 = "GCSpheres";
    private static final String CLASS_NAME_5 = "Histogram";
    private static final String CLASS_NAME_6 = "MontyPi";
    private static final String CLASS_NAME_7 = "NQueensDist";
    private static final String CLASS_NAME_8 = "NQueensPar";
    private static final String CLASS_NAME_9 = "StructSpheres";
    
    private static final String CLASS_SRCFILE_NAME_1 = CLASS_NAME_1 + ".x10";
    private static final String CLASS_SRCFILE_NAME_2 = CLASS_NAME_2 + ".x10";
    private static final String CLASS_SRCFILE_NAME_3 = CLASS_NAME_3 + ".x10";
    private static final String CLASS_SRCFILE_NAME_4 = CLASS_NAME_4 + ".x10";
    private static final String CLASS_SRCFILE_NAME_5 = CLASS_NAME_5 + ".x10";
    private static final String CLASS_SRCFILE_NAME_6 = CLASS_NAME_6 + ".x10";
    private static final String CLASS_SRCFILE_NAME_7 = CLASS_NAME_7 + ".x10";
    private static final String CLASS_SRCFILE_NAME_8 = CLASS_NAME_8 + ".x10";
    private static final String CLASS_SRCFILE_NAME_9 = CLASS_NAME_9 + ".x10";
    
    private static final boolean verbose= true;
    private static boolean updated = false;
    private static Logger log;

   public class MyListener implements IModelListener {
    	
        public AnalysisRequired getAnalysisRequired() {
            return AnalysisRequired.NONE; // Even if it doesn't scan, it's ok - this posts the error annotations!
        }
        
        public void update(IParseController parseController, IProgressMonitor monitor) {
        	System.out.println("We're inside the listener");
        	updated = true;
        }
    }
    
/**
     * The bot for the editor used to exercise the outline view
     */
    protected static SWTBotEclipseEditor fSrcEditor;

    @BeforeClass
    public static void beforeClass() throws Exception {
    	DOMConfigurator.configure("log4j.xml");
    	log = Logger.getLogger("x10dt.ui.tests");
    	// Logger log = Logger.getLogger("x10dt.ui.tests");

    	// Set up a simple configuration that logs on the console.
    	   BasicConfigurator.configure();

        SWTBotPreferences.KEYBOARD_STRATEGY= "org.eclipse.swtbot.swt.finder.keyboard.SWTKeyboardStrategy"; //$NON-NLS-1$
        topLevelBot= new SWTWorkbenchBot();
        SWTBotPreferences.TIMEOUT= 15000; // Long timeout needed for first project creation
        
        if(verbose){
        	SWTBotPreferences.PLAYBACK_DELAY = 0;
        }
        log.info("Closing welcome view");
        SWTBotUtils.closeWelcomeViewIfNeeded(topLevelBot);
        topLevelBot.perspectiveByLabel("X10").activate();
        createJavaBackEndProject(PROJECT_NAME, false);
        topLevelBot.shells()[0].activate();
    }
   private static void createProject(final String className, final String classFileName) throws Exception{
	      
   //     createJavaBackEndProject(PROJECT_NAME, false);
	     topLevelBot.shells()[0].activate();
//       SWTBotUtils.SelectOpenAssociatedPerspectiveIfNeeded(topLevelBot);
     //  topLevelBot.shells()[0].activate(); // HACK - make sure the main window's shell is active, in case we ran after other tests
        ProjectUtils.createClass(topLevelBot, className);

        topLevelBot.waitUntil(Conditions.waitForEditor(new EditorMatcher(classFileName)));

        fSrcEditor= topLevelBot.editorByTitle(classFileName).toTextEditor();
    }
// WaitForParser
   public static void WaitForParser() throws Exception {
	   log.info("Waiting for parser");
	   topLevelBot.waitUntil(new DefaultCondition() {

		   public boolean test() throws Exception {
		   return updated == true;
		   }

		   public String getFailureMessage() {
			   log.debug("Waiting for parser failed");
		   return "Some Failure Message";
		   }

		   }, Timeout.SIXTY_SECONDS);
   }

    @AfterClass
    public static void afterClass() throws Exception {
        SWTBotUtils.saveAllDirtyEditors(topLevelBot);
    }
  
    @After
    public void after() throws Exception{
    	SWTBotUtils.closeAllEditors(topLevelBot);
    	SWTBotUtils.closeAllShells(topLevelBot);
     }
    
    @Test
    public void test1() throws Exception {
    	
	    	log.info("Currently Testing: " + CLASS_NAME_1 + " Class");
	    	createProject(CLASS_NAME_1,CLASS_SRCFILE_NAME_1);
	    	
	        runTest("data/"+CLASS_SRCFILE_NAME_1); //$NON-NLS-1$
    }

    @Test
    public void test2() throws Exception{
  
	    	log.info("Currently Testing: " + CLASS_NAME_2 + " Class");
	    	
	    	createProject(CLASS_NAME_2,CLASS_SRCFILE_NAME_2);
	    	
	    	log.info("Running "+ "data/" + CLASS_SRCFILE_NAME_2);
	        runTest("data/" + CLASS_SRCFILE_NAME_2); //$NON-NLS-1$
    }

    @Test
    public void test3() throws Exception{
    
	    	if (verbose) {
	    		System.out.println("Currently Testing: " + CLASS_NAME_3 + "Class");
	    	}
	    	createProject(CLASS_NAME_3,CLASS_SRCFILE_NAME_3);
	    	
	        runTest("data/"+CLASS_SRCFILE_NAME_3); //$NON-NLS-1$
    	
    }

    @Test
    public void test4() throws Exception{
 
	    	if (verbose) {
	    		System.out.println("Currently Testing: " + CLASS_NAME_4 + "Class");
	    	}
	    	createProject(CLASS_NAME_4,CLASS_SRCFILE_NAME_4);
	    	
	        runTest("data/"+CLASS_SRCFILE_NAME_4); //$NON-NLS-1$
    
    }

    @Test
    public void test5() throws Exception{
      	if (verbose) {
	    		System.out.println("Currently Testing: " + CLASS_NAME_5 + "Class");
	    	}
	    	createProject(CLASS_NAME_5,CLASS_SRCFILE_NAME_5);
	    	
	        runTest("data/"+CLASS_SRCFILE_NAME_1); //$NON-NLS-1$
    	  }

    @Test
    public void test6() throws Exception {
   
	    	if (verbose) {
	    		System.out.println("Currently Testing: " + CLASS_NAME_6 + "Class");
	    	}
	    	createProject(CLASS_NAME_6,CLASS_SRCFILE_NAME_6);
	    	
	        runTest("data/"+CLASS_SRCFILE_NAME_6); //$NON-NLS-1$
        }

    @Test
    public void test7() throws Exception{
      	if (verbose) {
	    		System.out.println("Currently Testing: " + CLASS_NAME_7 + "Class");
	    	}
	    	createProject(CLASS_NAME_7,CLASS_SRCFILE_NAME_7);
	    	
	        runTest("data/"+CLASS_SRCFILE_NAME_7); //$NON-NLS-1$
    	
    }

    @Test
    public void test8() throws Exception {
    	
	    	if (verbose) {
	    		System.out.println("Currently Testing: " + CLASS_NAME_8 + "Class");
	    	}
	    	createProject(CLASS_NAME_8,CLASS_SRCFILE_NAME_8);
	    	
	        runTest("data/"+CLASS_SRCFILE_NAME_8); //$NON-NLS-1$
    	
    }

   
    @Test
    public void test9()throws Exception {
      /*	if (verbose) {
	    		System.out.println("Currently Testing: " + CLASS_NAME_9 + "Class");
	    	}*/
      	log.debug("Currently Testing: " + CLASS_NAME_9 + "Class");
    	
	    	createProject(CLASS_NAME_9,CLASS_SRCFILE_NAME_9);
	    	
	        runTest("data/"+CLASS_SRCFILE_NAME_9); //$NON-NLS-1$
       }

    private void runTest(String srcPath) throws Exception {
    	
        getTestSource(fSrcEditor, srcPath);
        WaitForParser();
     // while( updated == false) {
  //  	  int x =2/2;
  //  	 }
     //  do nothing
        	
      verifyOutline(fSrcEditor);
    }

    private void getTestSource(final SWTBotEclipseEditor srcEditor, final String resPath) {
        final Bundle bundle= Platform.getBundle("x10dt.ui.tests"); //$NON-NLS-1$
        final URL resURL= bundle.getEntry(resPath);
        junit.framework.Assert.assertNotNull("Unable to find test source: " + resPath, resURL); //$NON-NLS-1$
        try {
            final InputStream resStream= FileLocator.openStream(bundle, new Path(resURL.getPath()), false);
            final String contents= StreamUtils.readStreamContents(resStream);
            
            final IEditorPart editorPart= srcEditor.getReference().getEditor(false);
            final UniversalEditor univEditor= (UniversalEditor) editorPart;
           univEditor.addModelListener(new MyListener());
           
            srcEditor.setText(contents);
            System.out.println("bah!");
        } catch (final IOException e) {
            junit.framework.Assert.fail(e.getMessage());
        }
    }

    /**
     * Verifies the contents of the "Outline" view against the X10 source in the given editor
     */
    private void verifyOutline(final SWTBotEclipseEditor editor) {
        SWTBotView outlineView= topLevelBot.viewByTitle("Outline"); //$NON-NLS-1$

        final IEditorPart editorPart= editor.getReference().getEditor(false);
        final UniversalEditor univEditor= (UniversalEditor) editorPart;
         final ParseController pc= (ParseController) univEditor.getParseController();
        SourceFile srcFile= (SourceFile) pc.getCurrentAst();

        verifyOutlineItems(outlineView, srcFile, ClassDecl.class);
        verifyOutlineItems(outlineView, srcFile, MethodDecl.class);
        verifyOutlineItems(outlineView, srcFile, FieldDecl.class);
    }

    /**
     * Cheap outline contents verification: check that all instances of the given Node sub-class
     * appear in the outline tree (excluding synthetic/compiler-generated instances).
     */
    private void verifyOutlineItems(SWTBotView outlineView, SourceFile srcFile, final Class<? extends Node> clazz) {
        final Set<Node> items= new HashSet<Node>();
        srcFile.visit(new NodeVisitor() { //
            @Override
            public NodeVisitor enter(Node n) {
                if (clazz.isInstance(n) && !n.position().isCompilerGenerated()) {
                    items.add(n);
                }
                return this;
            }
        });
        SWTBotTree outlineTree= outlineView.bot().tree();
        SWTBotTreeItem[] treeItems= outlineTree.getAllItems();

        X10LabelProvider labelProvider= new X10LabelProvider();
        if(verbose){
        	System.out.print("labelProvider: ");
        	System.out.print(labelProvider);
        	System.out.println();
        }
        for(Node n: items) {
            String label= labelProvider.getText(n);
            if(verbose){
            	System.out.print("Label: ");
            	System.out.print(label);
            	System.out.println();
              }
            junit.framework.Assert.assertTrue("Missing outline item: " + label, find(label, treeItems)); //$NON-NLS-1$
        }
    }

    // TODO "labels" aren't specific enough - there might be more than one item in a given outline view with the same label
    private boolean find(String label, SWTBotTreeItem[] treeItems) {
        for(SWTBotTreeItem item: treeItems) {
            if (item.getText().equals(label)) {
                return true;
            }
            item.expand();
            SWTBotTreeItem[] children= item.getItems();
            if (children.length > 0 && find(label, children)) {
                return true;
            }
        }
        return false;
    }
}
