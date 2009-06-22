package x10.uide.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lpg.runtime.IMessageHandler;
import lpg.runtime.IToken;
import lpg.runtime.Monitor;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.uide.model.ISourceProject;
import org.eclipse.uide.parser.AstLocator;
import org.eclipse.uide.parser.IASTNodeLocator;
import org.eclipse.uide.parser.ILexer;
import org.eclipse.uide.parser.IParseController;
import org.eclipse.uide.parser.IParser;
import org.eclipse.uide.parser.ParseError;

import polyglot.ast.Node;
import polyglot.frontend.FileSource;

public class ParseController implements IParseController {
    private ISourceProject fProject;
    private IPath fFilePath;
    private CompilerDelegate fCompiler;
    private Node fCurrentAst;

    private char fKeywords[][];
    private boolean fIsKeyword[];

    public void initialize(IPath filePath, ISourceProject project, IMessageHandler handler) {
        this.fProject= project;
        this.fFilePath= filePath;
    }
    public ISourceProject getProject() {
	return fProject;
    }
    public IPath getPath() {
	return fFilePath;
    }
    public IParser getParser() {
        return new ParserDelegate(fCompiler.getParser());
    }
    public ILexer getLexer() {
        return new LexerDelegate(fCompiler.getLexer());
    }
    public Object getCurrentAst() { return fCurrentAst; }
    public char [][] getKeywords() { return fKeywords; }
    public boolean isKeyword(int kind) { return fIsKeyword[kind]; }
    public int getTokenIndexAtCharacter(int offset)
    {
        int index = getParser().getParseStream().getTokenIndexAtCharacter(offset);
        return (index < 0 ? -index : index);
    }
    public IToken getTokenAtCharacter(int offset) {
    	return getParser().getParseStream().getTokenAtCharacter(offset);
    }
    public IASTNodeLocator getNodeLocator() { return new PolyglotNodeLocator(getLexer().getLexStream()); }

    public boolean hasErrors() { return fCurrentAst == null; }
    public List getErrors() { return Collections.singletonList(new ParseError("parse error", null)); }
    
    public ParseController() {
        System.out.println("creating ParseController()");
    }

    class MyMonitor implements Monitor
    {
        IProgressMonitor monitor;
        boolean wasCancelled= false;
        MyMonitor(IProgressMonitor monitor)
        {
            this.monitor = monitor;
        }
        public boolean isCancelled() {
        	if (!wasCancelled)
        		wasCancelled = monitor.isCanceled();
        	return wasCancelled;
        }
    }
    
    /**
     * setFilePath() should be called before calling this method.
     */
    public Object parse(String contents, boolean scanOnly, IProgressMonitor monitor)
    {
        FileSource fileSource= null;
        try
        {
            MyMonitor my_monitor = new MyMonitor(monitor);
            fCompiler = new CompilerDelegate(my_monitor, fProject.getRawProject());  // Create the compiler
            fileSource= new SafariFileSource(contents,
                                             new File(fProject != null ? fProject.getRawProject().getLocation().append(fFilePath).toString() : fFilePath.toOSString()),
                                             fFilePath.toOSString());
            List/*<SourceStream>*/ streams= new ArrayList();
            streams.add(fileSource); //PC: just to test...
            fCompiler.getFrontEnd().compile(streams);
        }
        catch (IOException e)
        {
            throw new Error(e);
        } finally {
            // RMF 8/2/2006 - retrieve the AST if there is one; some later phase of compilation
            // may fail, even though the AST is well-formed enough to provide an outline.
            if (fileSource != null)
                fCurrentAst = (Node) fCompiler.getJob(fileSource).ast();
            // RMF 8/2/2006 - cacheKeywordsOnce() must have been run for syntax highlighting to work.
            // Must do this after attempting parsing (even though that might fail), since it depends
            // on the parser/lexer being set in the ExtensionInfo, which only happens as a result of
            // ExtensionInfo.parser(). Ugghh.
            cacheKeywordsOnce();
        }
        return fCurrentAst;
    }

    private void cacheKeywordsOnce() {
        if (fKeywords == null) {
            String tokenKindNames[] = getParser().getParseStream().orderedTerminalSymbols();
            this.fIsKeyword = new boolean[tokenKindNames.length];
            this.fKeywords = new char[tokenKindNames.length][];

            int [] keywordKinds = getLexer().getKeywordKinds();
            for (int i = 1; i < keywordKinds.length; i++)
            {
                int index = getParser().getParseStream().mapKind(keywordKinds[i]);

                fIsKeyword[index] = true;
                fKeywords[index] = getParser().getParseStream().orderedTerminalSymbols()[index].toCharArray();
            }
        }
    }
}
