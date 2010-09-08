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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import lpg.runtime.IPrsStream;
import lpg.runtime.IToken;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.imp.editor.UniversalEditor;
import org.eclipse.imp.utils.StreamUtils;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEclipseEditor;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.osgi.framework.Bundle;

import x10.parser.X10Parsersym;
import x10dt.tests.services.swbot.utils.ProjectUtils;
import x10dt.tests.services.swbot.utils.SWTBotUtils;
import x10dt.ui.editor.X10TokenColorer;
import x10dt.ui.parser.ParseController;
import x10dt.ui.tests.X10DTTestBase;
import x10dt.ui.tests.utils.EditorMatcher;

/**
 * @author rfuhrer@watson.ibm.com
 */
// @RunWith(ColoringTestRunner.class)
@RunWith(SWTBotJunit4ClassRunner.class)
public class SyntaxColoringTests extends X10DTTestBase {
    private static final String PROJECT_NAME= "ColoringProject";

    private static final String CLASS_NAME= "MyClass";

    private static final String CLASS_SRCFILE_NAME= CLASS_NAME + ".x10";

    private static final String lineSep = System.getProperty("line.separator");

    private static final boolean verbose= false;

    /**
     * The bot for the editor used to exercise syntax coloring
     */
    protected static SWTBotEclipseEditor fSrcEditor;

    /**
     * The text attribute used for any text that isn't given a special styling by
     * the syntax colorer.
     */
    private static TextAttribute fDefaultTextAttribute;

    /**
     * The list of text regions for the current editor buffer that aren't contained
     * by any string, comment, keyword, or anything else that requires special coloring.
     * These regions should all be styled with the fDefaultTextAttribute.
     */
    private List<Region> fOtherRegions;

    @BeforeClass
    public static void beforeClass() throws Exception {
        SWTBotPreferences.KEYBOARD_STRATEGY= "org.eclipse.swtbot.swt.finder.keyboard.SWTKeyboardStrategy";
        topLevelBot= new SWTWorkbenchBot();
        SWTBotPreferences.TIMEOUT= 15000; // Long timeout needed for first project creation
        SWTBotUtils.closeWelcomeViewIfNeeded(topLevelBot);

        createJavaBackEndProject(PROJECT_NAME, false);

        fDefaultTextAttribute= UIThreadRunnable.syncExec(new Result<TextAttribute>() {
            public TextAttribute run() {
                return new TextAttribute(Display.getDefault().getSystemColor(SWT.COLOR_BLACK), null, SWT.NORMAL);
            }
        });
        topLevelBot.shells()[0].activate(); // HACK - make sure the main window's shell is active, in case we ran after other tests
        ProjectUtils.createClass(topLevelBot, CLASS_NAME);

        topLevelBot.waitUntil(Conditions.waitForEditor(new EditorMatcher(CLASS_SRCFILE_NAME)));

        fSrcEditor= topLevelBot.editorByTitle(CLASS_SRCFILE_NAME).toTextEditor();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        SWTBotUtils.saveAllDirtyEditors(topLevelBot);
    }

//    @ColoringTests(files="data/*.x10")
    @Test
    public void testHello() {
        runTest("data/Hello.x10");
    }

//    @Test
//    public void testKMeansSPMD() {
//        runTest("data/KMeansSPMD.x10");
//    }

    @Test
    public void test2() {
        runTest("data/FRASimpleDist.x10");
    }

    @Test
    public void test3() {
        runTest("data/FSSimpleDist.x10");
    }

    @Test
    public void test4() {
        runTest("data/GCSpheres.x10");
    }

    @Test
    public void test5() {
        runTest("data/Histogram.x10");
    }

    @Test
    public void test6() {
        runTest("data/MontyPi.x10");
    }

    @Test
    public void test7() {
        runTest("data/NQueensDist.x10");
    }

    @Test
    public void test8() {
        runTest("data/NQueensPar.x10");
    }

    @Test
    public void test9() {
        runTest("data/StructSpheres.x10");
    }

    private void runTest(String srcPath) {
        getTestSource(fSrcEditor, srcPath);
        verifyColoring(fSrcEditor);
    }

    private void getTestSource(final SWTBotEclipseEditor srcEditor, final String resPath) {
        final Bundle bundle= Platform.getBundle("x10dt.ui.tests");
        final URL resURL= bundle.getEntry(resPath);
        junit.framework.Assert.assertNotNull("Unable to find test source: " + resPath, resURL);
        try {
            final InputStream resStream= FileLocator.openStream(bundle, new Path(resURL.getPath()), false);
            final String contents= StreamUtils.readStreamContents(resStream);
            
            srcEditor.setText(contents);
            topLevelBot.sleep(7000); // give parser a chance to catch up...
        } catch (final IOException e) {
            junit.framework.Assert.fail(e.getMessage());
        }
    }

    @SuppressWarnings("unused")
    private void deleteSourceRange(final SWTBotEclipseEditor srcEditor, final int line, final int column, final int length) {
        srcEditor.selectRange(line, column, length);
        srcEditor.insertText("");
    }

    @SuppressWarnings("unused")
    private void mutateSource(final SWTBotEclipseEditor srcEditor) {
        srcEditor.selectRange(5, 0, 0);
        srcEditor.insertText("        // a comment" + lineSep);
    }

    private void verifyColoring(final SWTBotEclipseEditor editor) {
        final IEditorPart editorPart= editor.getReference().getEditor(false);
        final IEditorInput input= editorPart.getEditorInput();
        final IDocument doc= ((AbstractTextEditor) editorPart).getDocumentProvider().getDocument(input);
        final X10TokenColorer colorer= UIThreadRunnable.syncExec(new Result<X10TokenColorer>() {
            public X10TokenColorer run() {
                return new X10TokenColorer();
            }
        });
        final UniversalEditor univEditor= (UniversalEditor) editorPart;
        final ParseController pc= (ParseController) univEditor.getParseController();

        fOtherRegions= new LinkedList<Region>();
        fOtherRegions.add(new Region(0, doc.getLength()));

        if (verbose) {
            System.out.println("Doc: " + doc.get());
        }

        verifyColoringOf(findComments(pc), colorer.commentAttribute, doc, editor);
        verifyColoringOf(findDocComments(pc), colorer.docCommentAttribute, doc, editor);
        verifyColoringOf(findKeywords(pc), colorer.getKeywordAttribute(), doc, editor);
        verifyColoringOf(findTokensOfKind(pc, X10Parsersym.TK_StringLiteral), colorer.characterAttribute, doc, editor);
        verifyColoringOf(findTokensOfKind(pc, X10Parsersym.TK_CharacterLiteral), colorer.characterAttribute, doc, editor);
        verifyColoringOf(findTokensOfKind(pc, X10Parsersym.TK_IDENTIFIER), colorer.identifierAttribute, doc, editor);
        verifyColoringOf(findTokensOfKind(pc, X10Parsersym.TK_IntegerLiteral), colorer.numberAttribute, doc, editor);
        verifyColoringOf(findTokensOfKind(pc, X10Parsersym.TK_LongLiteral), colorer.numberAttribute, doc, editor);
        verifyColoringOf(findTokensOfKind(pc, X10Parsersym.TK_FloatingPointLiteral), colorer.numberAttribute, doc, editor);
        verifyColoringOf(findTokensOfKind(pc, X10Parsersym.TK_DoubleLiteral), colorer.numberAttribute, doc, editor);

        // check that other ranges are styled using the default font/color
        verifyOtherRegions(doc, editor);
    }

    private void verifyOtherRegions(final IDocument doc, final SWTBotEclipseEditor editor) {
        doVerifyColoring(fOtherRegions, fDefaultTextAttribute, doc, editor);
    }

    private void verifyColoringOf(final List<Region> regions, final TextAttribute attrib, final IDocument doc, final SWTBotEclipseEditor editor) {
        removeFromOtherRegions(regions);
        doVerifyColoring(regions, attrib, doc, editor);
    }

    private void removeFromOtherRegions(final List<Region> regions) {
        if (regions.size() == 0) { return; }
        if (verbose) {
            System.out.print("removeFromOtherRegions(");
            dumpRegions(regions);
            System.out.println(")");
            System.out.print("  Current 'other' regions: ");
            dumpRegions(fOtherRegions);
            System.out.println();
        }
        for(final Region r: regions) {
            final List<Region> result= new LinkedList<Region>();
            final Iterator<Region> otherIter= fOtherRegions.iterator();
            Region otherRegion= otherIter.next();

            if (verbose) {
                System.out.println("Removing region " + toString(r));
            }

            // copy over old regions that are wholly BEFORE the region 'r'
            while (otherRegion.getOffset() + otherRegion.getLength() < r.getOffset()) {
                result.add(otherRegion);
                if (!otherIter.hasNext()) { otherRegion= null; break; }
                otherRegion= otherIter.next();
            }
            // >>> HERE: either otherIter ran dry, or otherRegion now overlaps region 'r'

            if (otherRegion != null) {
                int remainOff= r.getOffset();
                int remainEnd= remainOff + r.getLength();
                do {
                    int otherOff= otherRegion.getOffset();
                    int otherEnd= otherOff + otherRegion.getLength() - 1;
                    if (otherOff < remainOff) {
                        // Need to keep the beginning of 'otherRegion'
                        final Region newReg= new Region(otherOff, remainOff - otherOff);
                        result.add(newReg);
                    }
                    // >>> HERE: remainOff <= otherRegion.getOffset() <<<
                    // Determine whether there's anything left of the right-hand end of otherRegion
                    int rightOff= remainEnd;
                    int rightEnd= Math.max(remainEnd, otherEnd);
                    if (rightEnd >= rightOff) {
                        result.add(new Region(rightOff, rightEnd - rightOff + 1));
                    }
                    // we've taken care of this otherRegion, advance to the next
                    if (!otherIter.hasNext()) { otherRegion= null; break; }
                    otherRegion= otherIter.next();
                } while (otherRegion.getOffset() + otherRegion.getLength() < r.getOffset() + r.getLength());
            }
            // >>> HERE: either otherIter ran dry, or otherRegion DOESN'T overlap region 'r'

            if (otherRegion != null) {
                // copy over old regions that are wholly AFTER the region 'r'
                while (otherRegion.getOffset() > r.getOffset() + r.getLength()) {
                    result.add(otherRegion);
                    if (!otherIter.hasNext()) { break; }
                    otherRegion= otherIter.next();
                }
            }
            fOtherRegions= result;
            if (verbose) {
                System.out.print("  *** New 'other' regions: ");
                for(Region oth: fOtherRegions) {
                    System.out.print(toString(oth));
                }
                System.out.println();
            }
        }
        if (verbose) {
            System.out.println("done");
        }
    }

    private void dumpRegions(final List<Region> regions) {
        for(Region r: regions) {
            System.out.print(toString(r));
        }
    }

    private void doVerifyColoring(final List<Region> regions, final TextAttribute attrib, final IDocument doc, final SWTBotEclipseEditor editor) {
        for(final Region r: regions) {
            if (verbose) {
                try {
                    System.out.println("Verifying region " + toString(r) + ": '" + doc.get(r.getOffset(), r.getLength()) + "'");
                } catch (final BadLocationException e1) {
                }
            }
            final int offset= r.getOffset();
            final int length= r.getLength();

            for(int pos= offset; pos < offset + length; pos++) {
                if (pos < 0 || pos >= doc.getLength()) { continue; }

                try {
                    final int line= doc.getLineOfOffset(pos);
                    final int column= pos - doc.getLineOffset(line);
                    final StyleRange sr= editor.getStyle(line, column);

                    if (attrib != fDefaultTextAttribute) { // but it seems nothing actually explicitly uses fDefaultTextAttribute anyway...
                        junit.framework.Assert.assertNotNull("Default style found for text that needs a style at position " + getPosString(pos, doc) + ", character '" + doc.get(pos, 1) + "'", sr);
                        checkFontStyle(attrib.getStyle(), sr.fontStyle, pos, doc);
                        checkFontColor(attrib.getForeground().getRGB(), sr.foreground, pos, doc);
                    } else {
                        if (sr != null) {
                            junit.framework.Assert.assertNull("Default color expected at position " + getPosString(pos, doc) + ", character '" + doc.get(pos, 1) + "'", sr.foreground);
                        }
                    }
                } catch (final BadLocationException e) {
                    junit.framework.Assert.fail(e.getMessage());
                }
            }
        }
    }

    private void checkFontColor(final RGB expectedRGB, final Color actualColor, final int pos, final IDocument doc) {
        try {
            final char ch= doc.getChar(pos);
            final String posStr= getPosString(pos, doc);

            junit.framework.Assert.assertEquals("Font color mismatch at position " + posStr + ", character '" + ch + "'", expectedRGB, actualColor.getRGB());
        } catch (final BadLocationException e) {
            junit.framework.Assert.fail(e.getMessage());
        }
    }

    private String toString(Region r) {
        return "<" + r.getOffset() + ":" + (r.getOffset() + r.getLength() - 1) + "> ";
    }

    private String getPosString(final int pos, final IDocument doc) {
        try {
            final int line= doc.getLineOfOffset(pos);
            final int col= pos - doc.getLineOffset(line);

            return "<offset = " + pos + ", line = " + line + ", col = " + col + ">";
        } catch (final BadLocationException e) {
            junit.framework.Assert.fail(e.getMessage());
            return ""; // not reached
        }
    }

    private void checkFontStyle(final int expected, final int actual, final int pos, final IDocument doc) {
        if (expected == actual) return;

        try {
            final String expectedStr= buildStyleDescriptor(expected);
            final String actualStr= buildStyleDescriptor(actual);
            final char ch= doc.getChar(pos);
            final String posStr= getPosString(pos, doc);

            junit.framework.Assert.fail("Font style at position " + posStr + ", character '" + ch + "' doesn't match: expected " + expectedStr + ", got " + actualStr);
        } catch (final BadLocationException e) {
            junit.framework.Assert.fail(e.getMessage());
        }
    }

    private String buildStyleDescriptor(int style) {
        if (style == SWT.NORMAL) return "normal";
        final int mask= SWT.BOLD | SWT.ITALIC;
        final StringBuilder sb= new StringBuilder();

        while ((style & mask) != 0) {
            if (sb.length() > 0) {
                sb.append("+");
            }
            if ((style & SWT.BOLD) != 0) {
                style= style ^ SWT.BOLD;
                sb.append("bold");
            } else if ((style & SWT.ITALIC) != 0) {
                style= style ^ SWT.ITALIC;
                sb.append("italic");
            }
        }
        return sb.toString();
    }

    private List<Region> findDocComments(final ParseController pc) {
        // get all the adjuncts from the token stream and record their extents
        final List<Region> result= new LinkedList<Region>();
        final IPrsStream ps= pc.getParseStream();
        final List<IToken> adjuncts= ps.getAdjuncts();

        for(final IToken adjunct: adjuncts) {
            final String adjStr= adjunct.toString();
            if (adjStr.startsWith("/**")) {
                final int offset= adjunct.getStartOffset();
                final int length= adjunct.getEndOffset() - offset + 1;
                final Region r= new Region(offset, length);

                result.add(r);
            }
        }
        return result;
    }

    private List<Region> findComments(final ParseController pc) {
        // get all the adjuncts from the token stream and record their extents
        final List<Region> result= new LinkedList<Region>();
        final IPrsStream ps= pc.getParseStream();
        final List<IToken> adjuncts= ps.getAdjuncts();

        for(final IToken adjunct: adjuncts) {
            final String adjStr= adjunct.toString();
            if (adjStr.startsWith("//") || adjStr.startsWith("/*") && !adjStr.startsWith("/**")) {
                final int offset= adjunct.getStartOffset();
                final int length= adjunct.getEndOffset() - offset + 1;
                final Region r= new Region(offset, length);

                result.add(r);
            }
        }
        return result;
    }

    private List<Region> findKeywords(final ParseController pc) {
        final List<Region> result= new LinkedList<Region>();
        // get all the keywords from the token stream and record their extents
        final IPrsStream ps= pc.getParseStream();
        final List<IToken> tokens= ps.getTokens();

        for(final IToken token: tokens) {
            if (pc.isKeyword(token.getKind())) {
                final int offset= token.getStartOffset();
                final int length= token.getEndOffset() - offset + 1;
                final Region r= new Region(offset, length);

                result.add(r);
            }
        }
        return result;
    }

    private List<Region> findTokensOfKind(final ParseController pc, final int kind) {
        final List<Region> result= new LinkedList<Region>();
        // get all the string literals from the token stream and record their extents
        final IPrsStream ps= pc.getParseStream();
        final List<IToken> tokens= ps.getTokens();

        for(final IToken token: tokens) {
            if (token.getKind() == kind) {
                final int offset= token.getStartOffset();
                final int length= token.getEndOffset() - offset + 1;
                final Region r= new Region(offset, length);

                result.add(r);
            }
        }
        return result;
    }

    @AfterClass
    public static void cleanup() {
        // Among other things, the following makes sure that the workbench (and therefore the test)
        // shuts down cleanly, even if there are "dirty" open editors. Without it, the test might
        // hang waiting for someone to dismiss the "Foo has been modified. Save changes?" dialog.
        SWTBotUtils.resetWorkbench(topLevelBot);
    }

    @AfterClass
    public static void sleep() {
        topLevelBot.sleep(2000);
    }
}
