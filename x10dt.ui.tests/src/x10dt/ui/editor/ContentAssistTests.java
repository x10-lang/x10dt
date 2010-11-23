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
import java.util.LinkedList;
import java.util.List;
import org.hamcrest.Matcher;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.imp.editor.UniversalEditor;
import org.eclipse.imp.parser.IModelListener;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.parser.IModelListener.AnalysisRequired;
import org.eclipse.imp.utils.StreamUtils;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEclipseEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotWorkbenchPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotStyledText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.osgi.framework.Bundle;

import polyglot.ast.ClassMember;
import polyglot.ast.Node;
import polyglot.ast.SourceFile;
import polyglot.ast.Stmt;
import polyglot.util.Position;
import polyglot.visit.NodeVisitor;
import x10dt.tests.services.swbot.utils.ProjectUtils;
import x10dt.tests.services.swbot.utils.SWTBotUtils;
import x10dt.ui.parser.ParseController;
import x10dt.ui.tests.X10DTTestBase;
import x10dt.ui.tests.utils.EditorMatcher;




/**
 * @author rfuhrer@watson.ibm.com
 *
 */
public class ContentAssistTests extends X10DTTestBase {
    private static final String PROJECT_NAME = "ContentAssistProject"; //$NON-NLS-1$
    
    private static final String CLASS_NAME_1 = "HelloWorld"; //$NON-NLS-1$
    private static final String CLASS_SRCFILE_NAME_1 = CLASS_NAME_1 + ".x10"; //$NON-NLS-1$ 
    private static final String CLASS_NAME_2 = "MontyPi";
    private static final String CLASS_SRCFILE_NAME_2 = CLASS_NAME_2 + ".x10";
         
    private static final String[] STATEMENT1 = {
    	"var - mutable variable/field declaration",
        "val - immutable variable/field declaration",
        "static val - immutable static field declaration",
        "property - property declaration",
        "main method - main method",
        "def - method declaration",
        "this - constructor declaration",
        "class - class declaration",
        "struct - struct declaration",
        "dependent type - class declaration with property"
    };
    
    private static final String[] STATEMENT2 = {
    	"array new - array instantiation",
        "dist array new - dist array instantiation",
        "dist array access - remote array access",
        "async - async statement",
        "at - at statement",
        "ateach - ateach statement",
        "atomic - atomic statement",
        "clocked - clocked statement",
        "constraint - constrained type definition",
        "finish - finish statement",
        "function - function definition",
        "instanceof - instanceof in a conditional",
        "printing - printing to console",
        "type - type definition",
        "val - immutable variable/field declaration",
        "var - mutable variable/field declaration",
        "when - when statement"
    };
    
    private static final String[] STATEMENT3 = { "result"};
    
    private static final String[] STATEMENT4 = {
    	"at - at expression",
    	"as - coercion", 
    	"region 1-D - 1-dimensional region creation",
    	"region 2-D - 2-dimensional region creation" };
    
    private static final String[][] TEST1 = { STATEMENT1, STATEMENT1 };
    private static final String[][] TEST2 = { 
    	STATEMENT1 , STATEMENT2, STATEMENT2, STATEMENT2,
    	STATEMENT2, STATEMENT2, STATEMENT2, STATEMENT2,
    	STATEMENT2, STATEMENT2, STATEMENT2, STATEMENT2,
    	STATEMENT2, STATEMENT2, STATEMENT2, STATEMENT2, 
    	STATEMENT2, STATEMENT2, STATEMENT2, STATEMENT3,
    	STATEMENT2, STATEMENT2, STATEMENT2 };
    private static boolean verbose = true;
    
    
    private boolean updated = false;


    private class MyListener implements IModelListener {
     	
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
        SWTBotPreferences.KEYBOARD_STRATEGY= "org.eclipse.swtbot.swt.finder.keyboard.SWTKeyboardStrategy"; //$NON-NLS-1$
        topLevelBot= new SWTWorkbenchBot();
        SWTBotPreferences.TIMEOUT= 15000; // Long timeout needed for first project creation
        if(verbose){
        	SWTBotPreferences.PLAYBACK_DELAY = 0;
        }
        SWTBotUtils.closeWelcomeViewIfNeeded(topLevelBot);
        topLevelBot.perspectiveByLabel("X10").activate();
    }

    private static void createProject(final String projName, final String className, final String classFilename) throws Exception{
        createJavaBackEndProject(projName, false);
        topLevelBot.shells()[0].activate(); // HACK - make sure the main window's shell is active, in case we ran after other tests
        ProjectUtils.createClass(topLevelBot, className);

        topLevelBot.waitUntil(Conditions.waitForEditor(new EditorMatcher(classFilename)));

        fSrcEditor= topLevelBot.editorByTitle(classFilename).toTextEditor();
        
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

    private static class LinePos {
        int line, col;
        public LinePos(int line, int col) {
            this.line= line;
            this.col= col;
        }
    }

    @Test
    public void test1() throws Exception {
    	if(verbose){
    		System.out.println("TESTING " + CLASS_NAME_1 + " CLASS");
    	}
    	createProject(PROJECT_NAME + "1", CLASS_NAME_1, CLASS_SRCFILE_NAME_1);
        getTestSource(fSrcEditor, "data/" + CLASS_SRCFILE_NAME_1, CLASS_NAME_1); //$NON-NLS-1$

        waitForParser();
        runStatementContextTest("statement", TEST1);
    }
 
    @Test
    public void test2() throws Exception{
    	if(verbose){
    		System.out.println("TESTING " + CLASS_NAME_2 + " CLASS");
    	}
    	createProject(PROJECT_NAME + "2", CLASS_NAME_2, CLASS_SRCFILE_NAME_2);
        getTestSource(fSrcEditor, "data/" + CLASS_SRCFILE_NAME_2, CLASS_NAME_2); //$NON-NLS-1$

        waitForParser();
        runStatementContextTest("member", TEST2);
        
    }
    
    public void waitForParser() throws Exception{
    	topLevelBot.waitUntil(new DefaultCondition() {

			public boolean test() throws Exception {
				return updated == true;
			}

			public String getFailureMessage() {
				return "Some Failure Message";
			}

		}, Timeout.TWENTY_SECONDS);
    }
    
	private void runStatementContextTest(String type, String[][] expectedProposals) {
        IDocument doc= getEditorDocument(fSrcEditor);
        SourceFile srcFileRoot= getASTRoot(fSrcEditor);

        List<LinePos> allContextPositions= findAllStatementContextLineEnds(srcFileRoot, doc);
        printProposals(allContextPositions);
        fSrcEditor.setFocus();
        
       runContextContextAssistTest(allContextPositions, expectedProposals,type);
    }
     
    private void printProposals(List<LinePos> allContextPositions){
    	if(verbose){
    		for(LinePos lp: allContextPositions){
    			System.out.println("Context position at: <line: " + lp.line + ", col: " + lp.col + ">");
    			fSrcEditor.selectRange(lp.line, lp.col, 0); // go to that position
    			
    			//fSrcEditor.insertText("\n");
    	            
    	        List<String> proposals= fSrcEditor.getAutoCompleteProposals("");   
    	        System.out.println(proposals.toString());
    		}
    	}
    }
    
    private void runContextContextAssistTest(List<LinePos> allContextPositions, String[][] expectedProposals, String contextName){
    
        int index = 0;
        for(LinePos lp: allContextPositions) {
        	if(verbose){
        		System.out.println("Verifying Content Assist at: <line: " + lp.line + ", col: " + lp.col + ">");
        	}
            fSrcEditor.selectRange(lp.line, lp.col, 0); // go to that position
            //fSrcEditor.insertText("\n");
            
            if(verbose){
            	System.out.println("CURRENT CURSOR POSITION: <" + fSrcEditor.cursorPosition().line + "," + fSrcEditor.cursorPosition().column + ">");
            }

            List<String> proposals= fSrcEditor.getAutoCompleteProposals("");   

            System.out.println(proposals.toString());

            junit.framework.Assert.assertNotNull("The proposals result is null", proposals);
            junit.framework.Assert.assertTrue("There are no proposals", proposals.size() > 0);

            	for(String proposal: expectedProposals[index]) {
            		junit.framework.Assert.assertTrue("Expected " + contextName + " proposal is missing: " + proposal + " at location " + toString(lp), containsMatchingProposal(proposals, proposal));
            		//if(verbose){
            			System.out.println(proposal + " Proposal - PASS");
            		//}
            	}
            	
           index++;
        }
        if(verbose){
        	System.out.println("END OF TESTCASE");
    	}
    }    

    private String toString(LinePos lp) {
        return "<line: " + lp.line + ", col: " + lp.col + ">";
    }


    private List<LinePos> findAllStatementContextLineEnds(SourceFile srcFileRoot, final IDocument doc) {
        final List<LinePos> result= new LinkedList<LinePos>();
        srcFileRoot.visit(new NodeVisitor() {
            @Override
            public NodeVisitor enter(Node n) {
                if (n instanceof Stmt && !n.position().isCompilerGenerated()) {
                    try {
                        Position pos= n.position();
                        int endOffset= pos.endOffset();
                        if(verbose){
                        	System.out.println("Reading line: <" + pos.endLine() + "," + pos.endColumn() + ">");                      
                        	System.out.println("DOC OFFSET: " + doc.get(endOffset, 1));
                        	System.out.println("DOC OFFSET 2: " + doc.get(endOffset,2) +"");
                        }
                        // TODO fix front-end so that Stmt extent covers ';' as well
                        if (doc.get(endOffset, 1).equals(";") || doc.get(endOffset, 2).equals(");") || doc.get(endOffset, 2).equals("D;")) {
                            endOffset++;
                        }
                        
                        int line= doc.getLineOfOffset(endOffset);
                        int col= endOffset - doc.getLineOffset(line) + 1;
                        LinePos lp= new LinePos(line, col);
                        
                        

                        if(!doc.get(endOffset, 2).equals("+,")){
                        	if(!doc.get(endOffset, 2).equals("),")){
                        	result.add(lp);
                        		if(verbose){
                        			System.out.println("ADDED: <" + lp.line + "," + lp.col +">");
                        		}
                        	}
                        }
                    } catch (BadLocationException e) {
                    }
                }
                return this;
            }
        });
        return result;
    }

    private static SourceFile getASTRoot(SWTBotEclipseEditor fSrcEditor2) {
        final IEditorPart editorPart= fSrcEditor2.getReference().getEditor(false);
        final UniversalEditor univEditor= (UniversalEditor) editorPart;
        final ParseController pc= (ParseController) univEditor.getParseController();

        return (SourceFile) pc.getCurrentAst();
    }

    private static IDocument getEditorDocument(SWTBotEclipseEditor fSrcEditor2) {
        final IEditorPart editorPart= fSrcEditor2.getReference().getEditor(false);
        final IEditorInput input= editorPart.getEditorInput();
        final IDocument doc= ((AbstractTextEditor) editorPart).getDocumentProvider().getDocument(input);

        return doc;
    }

    private boolean containsMatchingProposal(List<String> actualProposals, String proposalPrefix) {
        for(String actualProp: actualProposals) {
            if (actualProp.startsWith(proposalPrefix)) {
                return true;
            }
        }
        return false;
    }

    private void getTestSource(final SWTBotEclipseEditor srcEditor, final String resPath, final String className) {
        final Bundle bundle= Platform.getBundle("x10dt.ui.tests"); //$NON-NLS-1$
        final URL resURL= bundle.getEntry(resPath);
        junit.framework.Assert.assertNotNull("Unable to find test source: " + resPath, resURL); //$NON-NLS-1$
        try {
            final InputStream resStream= FileLocator.openStream(bundle, new Path(resURL.getPath()), false);
            final String contents= StreamUtils.readStreamContents(resStream);
      
      
           /* SWTBot outlineBot = new SWTBot();
            outlineBot = topLevelBot.viewByTitle("Outline").bot();
            SWTBotTreeItem[] outlineItems;   
          
            //set contents from the source member into the editor
            srcEditor.setText(contents);
              
            outlineItems = outlineBot.tree().getTreeItem(className).getItems();
            if(verbose){
            	System.out.println("Number of nodes before While Loop is: " + outlineItems.length);
            }

            
            while(outlineItems.length < 1){
            	if(verbose){
            		System.out.println("WAITING FOR OUTLINE VIEW TO GENERATE");
            	}
          	    topLevelBot.sleep(10);        	   
          	    outlineItems = outlineBot.tree().getTreeItem(className).getItems();
             } 
            
            outlineItems = outlineBot.tree().getTreeItem(className).getItems();
            if(verbose){
            	System.out.println("Number of nodes in Outline View: " + outlineItems.length);
            	System.out.println("FINISHED WAITING FOR OUTLINE VIEW TO GENERATE");
            } */
            
            final IEditorPart editorPart= srcEditor.getReference().getEditor(false);
            final UniversalEditor univEditor= (UniversalEditor) editorPart;
           univEditor.addModelListener(new MyListener());

            srcEditor.setText(contents);
            System.out.println("Waiting for parser...");
            
        } catch (final IOException e) {
            junit.framework.Assert.fail(e.getMessage());
        }
    }
}
