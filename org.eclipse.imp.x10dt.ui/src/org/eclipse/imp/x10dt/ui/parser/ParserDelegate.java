package x10.uide.parser;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import lpg.runtime.Monitor;
import lpg.runtime.PrsStream;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.uide.parser.IParser;

import polyglot.ext.x10.ExtensionInfo;
import polyglot.frontend.Compiler;
import polyglot.frontend.FileSource;
import polyglot.main.Options;
import polyglot.main.UsageError;
import polyglot.util.StdErrorQueue;
import x10.parser.X10Parser;
import x10.uide.X10UIPlugin;

public class ParserDelegate implements IParser {
    X10Parser myParser;

    public PrsStream getParseStream() {
    	// SMS 25 Jun 200y:
    	//This doesn't work; can still get NPE due to
    	// interference of concurrent threads	
//      assert(myParser != null);
//		return myParser.getParseStream();
    	// Replacement text:
   		try {
   			assert(myParser != null);
			return myParser.getParseStream();
		} catch (NullPointerException e) {
			System.err.println("ParserDelegate.getParseStream(..):  NullPointerException; discarding, returning null");
			throw e;
		}
    }

    public int getEOFTokenKind() {
	return myParser.getEOFTokenKind();
    }

    public Object parser(Monitor monitor, int error_repair_count) {
	return myParser.parser(monitor);
    }

    String[] orderedTerminalSymbols() {
	return myParser.orderedTerminalSymbols();
    }

    ParserDelegate(X10Parser myParser) {
        this.myParser = myParser;
    }
}