package x10.uide.parser;

import java.io.IOException;

import lpg.lpgjavaruntime.LexStream;
import lpg.lpgjavaruntime.Monitor;
import lpg.lpgjavaruntime.PrsStream;

import org.eclipse.uide.parser.ILexer;
import x10.parser.X10Lexer;

public class LexerDelegate implements ILexer
{
	X10Lexer myLexer;

        public LexerDelegate(String absFilePath) throws IOException {
            this.myLexer = new X10Lexer(absFilePath);
        }
        public LexerDelegate(X10Lexer myLexer) {
            this.myLexer = myLexer;
        }
	public int [] getKeywordKinds() { return myLexer.getKeywordKinds(); }
	public LexStream getLexStream() { return myLexer.getLexStream(); }
	public void initialize(char [] content, String filename) { myLexer.initialize(content, filename); }
	public void lexer(Monitor monitor, PrsStream prsStream) { myLexer.lexer(monitor, prsStream); }
}