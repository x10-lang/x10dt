/*******************************************************************************
* Copyright (c) 2008 IBM Corporation.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    Robert Fuhrer (rfuhrer@watson.ibm.com) - initial API and implementation
*******************************************************************************/

package x10dt.ui.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import lpg.runtime.ILexStream;
import lpg.runtime.IPrsStream;
import lpg.runtime.IToken;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.parser.IMessageHandler;
import org.eclipse.imp.parser.ISourcePositionLocator;
import org.eclipse.imp.services.IAnnotationTypeInfo;
import org.eclipse.imp.services.ILanguageSyntaxProperties;
import org.eclipse.jface.text.IRegion;

import polyglot.ast.Node;
import polyglot.frontend.FileSource;
import polyglot.frontend.Job;
import polyglot.frontend.Source;
import polyglot.frontend.ZipResource;
import polyglot.util.ErrorInfo;
import x10.parser.antlr.ASTBuilder;
import x10.parser.antlr.generated.X10Parser;
import x10.visit.InstanceInvariantChecker;
import x10.visit.PositionInvariantChecker;
import x10dt.core.X10DTCorePlugin;
import x10dt.ui.X10DTUIPlugin;

public class ParseController extends org.eclipse.imp.parser.ParseControllerBase {
	/**
	 * A trivial extension of the class ZipResource that permits the user to provide the
	 * source text as an explicit parameter, rather than reading it from the .zip file
	 * itself. Useful for representing the contents of editor buffers that reside in
	 * zip files, even when the editor buffers are read-only.<br>
	 * Along with StringSource and StringResource, probably belongs in Polyglot, not here.
	 * @author rfuhrer
	 */
	public static final class ZipStringResource extends ZipResource {
		private final String contents;

		private ZipStringResource(File source, ZipFile zip, String entryName, String contents) {
			super(source, zip, entryName);
			this.contents = contents;
		}

		@Override
		public InputStream getInputStream() throws IOException {
			return new StringBufferInputStream(contents);
		}
	}

	public interface InvariantViolationHandler {
		public void clear();
		public void handleViolation(ErrorInfo error);
		public void consumeAST(Node root);
	}

	private static final Pattern JAR_IDENTIFIER_PATTERN = Pattern.compile(".*\\.jar:.*");

	private CompilerDelegate fCompiler;
    private IProgressMonitor fMonitor;
    private InvariantViolationHandler fViolationHandler;
    private ISourcePositionLocator fSourcePositionLocator;
    private ISourceProject fProject;
    private IPath fFilePath;
    private IMessageHandler fHandler;
    private CommonTokenStream fTokens;
   
    public ParseController() {
    	super(X10DTCorePlugin.kLanguageName);
    }

    public CommonTokenStream getTokens(){
    	return fTokens;
    }
    
    public CompilerDelegate getCompiler() {
        return fCompiler;
    }

    
    public ISourcePositionLocator getSourcePositionLocator() {
        if (fSourcePositionLocator == null) {
            fSourcePositionLocator= new PolyglotNodeLocator(fProject);
        }
        return fSourcePositionLocator;
    }

    public ILanguageSyntaxProperties getSyntaxProperties() {
        return new X10SyntaxProperties();
    }


    public void initialize(IPath filePath, ISourceProject project, IMessageHandler handler) {
    	fFilePath = filePath;
    	fProject = project;
        fHandler = handler;
        fMonitor= new NullProgressMonitor();
    }

    public void setViolationHandler(InvariantViolationHandler handler) {
    	fViolationHandler= handler;
    }
    
    public Object parse(final String contents, IProgressMonitor monitor) {
    	Source source= null;

    	try {
            
            int jarPathComponentIdx = findJarIdentifierComponent(fFilePath);

            if (jarPathComponentIdx >= 0) {
            	source = buildJarFileEntrySource(contents, jarPathComponentIdx);
            } else {
            	String path= fProject != null ? fProject.getRawProject().getLocation().append(fFilePath).toOSString() : fFilePath.toOSString();
            	File file= new File(path);

            	source= new FileSource(new StringResource(contents, file, path));
            }

            List<Source> streams= Arrays.asList(source);
            IProject proj= (fProject != null) ? fProject.getRawProject() : null;
            IPath sourcePath = (fProject != null) ? Platform.getLocation().append(fProject.getName()).append(fFilePath) : fFilePath;

            fCompiler= new CompilerDelegate(fMonitor, fHandler, proj, sourcePath, fViolationHandler); // Create the compiler
            fCompiler.compile(streams);
    	} catch (FileNotFoundException e) {
    	    // do nothing - presumably the file just got deleted...
        } catch (IOException e) {
            X10DTUIPlugin.log(e);
        } catch (CoreException e) {
            X10DTUIPlugin.log(e);
        } finally {
        
            // RMF 8/2/2006 - retrieve the AST, token stream and lex stream, if they exist; front-end semantic
            // checks may fail, even though the AST/token-stream are well-formed enough to support various IDE
        	// services, like syntax highlighting and the outline view's contents.

            if (source != null && fCompiler != null) {
            	final ASTBuilder parser= (ASTBuilder) fCompiler.getParserFor(source);
            	fTokens = parser.getTokens();
            	
            	//final X10Lexer lexer= fCompiler.getLexerFor(source);
            	//fParseStream = parser.getIPrsStream();
            	//fLexStream = fParseStream.getILexStream();
//            	fLexStream = lexer.getILexStream();
            	//fParser = new ParserDelegate(parser); // HACK - SimpleLPGParseController.cacheKeywordsOnce() needs an IParser and an ILexer, so create them here. Luckily, they're just lightweight wrappers...
            	//fLexer = new LexerDelegate(lexer);
            	fCurrentAst= fCompiler.getASTFor(source); // getASTFor(fileSource); // TODO use commandLineJobs() instead?

            	if (fViolationHandler != null && fCurrentAst != null) {
                    // TODO Tweak appropriate option in Configuration/Options object to include the invariant checking goals
	            	Job job= fCompiler.getJobFor(source);
	            	PositionInvariantChecker pic= new PositionInvariantChecker(job, "", true);
	            	InstanceInvariantChecker iic= new InstanceInvariantChecker(job);

	            	((Node) fCurrentAst).visit(pic);
	            	((Node) fCurrentAst).visit(iic);

	            	fViolationHandler.consumeAST((Node) fCurrentAst);
	            }
        	}
            // RMF 8/2/2006 - cacheKeywordsOnce() must have been run for syntax highlighting to work.
            // Must do this after attempting parsing (even though that might fail), since it depends
            // on the parser/lexer being set in the ExtensionInfo, which only happens as a result of
            // ExtensionInfo.parser(). Ugghh.
            
            //if (fParser != null) {
            //	cacheKeywordsOnce();
            //}
            
            fCompiler = null;
            //fParser = null;
            //fLexer = null;
        }
        return fCurrentAst;
    }

    /**
     * Build a Polyglot Source object that refers to the jar file entry given by fFilePath,
     * but using the given String as the source text contents.
     * @param jarPathComponentIdx the index of the path component that contains the "jar:" indicator
     */
	private Source buildJarFileEntrySource(final String contents, int jarPathComponentIdx) throws ZipException, IOException {
		Source source;
		String jarPathComponent= fFilePath.segment(jarPathComponentIdx);
		StringBuilder jarPath= new StringBuilder();

		for(int i=0; i < jarPathComponentIdx; i++) {
			jarPath.append(File.separatorChar);
			jarPath.append(fFilePath.segment(i));
		}

		String jarName= jarPathComponent.substring(0, jarPathComponent.indexOf(':'));
		String trailer= jarPathComponent.substring(jarPathComponent.indexOf(':') + 1);

		jarPath.append(File.separatorChar);
		jarPath.append(jarName);

		StringBuilder entryPath= new StringBuilder();

		entryPath.append(trailer);
		for(int i=jarPathComponentIdx+1; i < fFilePath.segmentCount(); i++) {
			entryPath.append('/');
			entryPath.append(fFilePath.segment(i));
		}

		File jarFile= new File(jarPath.toString());
		ZipFile zipFile= new ZipFile(jarFile);
		ZipResource zipRsrc= new ZipStringResource(jarFile, zipFile, entryPath.toString(), contents);

		source= new FileSource(zipRsrc);
		return source;
	}

    /**
     * @return the index of the given IPath that indicates that the path refers
     * to a jar file entry, if any, or -1 if this is not a jar file entry path
     */
	private int findJarIdentifierComponent(IPath filePath) {
		int jarPathComponentIdx= -1;
		for(int i=0; i < filePath.segmentCount(); i++) {
			String seg= filePath.segment(i);
			if (JAR_IDENTIFIER_PATTERN.matcher(seg).matches()) {
				jarPathComponentIdx= i;
				break;
			}
		}
		return jarPathComponentIdx;
	}

    // TODO Use this rather than fCompiler.getASTFor() ? (more reliable?)
    private Object getASTFor(Source source) {
    	Collection<Job> cmdJobs = fCompiler.getExtInfo().scheduler().commandLineJobs();
    	for(Job job: cmdJobs) {
    		if (job.source().equals(source)) {
    			return job.ast();
    		}
    	}
    	return null;
    }

    
//    public Iterator<IToken> getTokenIterator(IRegion region) {
//      final int regionOffset= region.getOffset();
//      final int regionLength= region.getLength();
//      final int regionEnd= regionOffset + regionLength - 1;
//
//      if (fParseStream == null) {
//    	  return new Iterator<IToken>() {
//			public boolean hasNext() {
//				return false;
//			}
//
//			public IToken next() {
//				return null;
//			}
//
//			public void remove() {
//			} };
//      }
//      return new Iterator<IToken>() {
//          final IPrsStream stream= fParseStream;
//          final int firstTokIdx= getTokenIndexAtCharacter(regionOffset);
//          final int lastTokIdx;
//          {
//              int endIdx= getTokenIndexAtCharacter(regionEnd);
//              char[] streamChars= stream.getInputChars();
//              int streamLen= streamChars.length;
//              try {
//                  if (regionEnd >= 1 && regionEnd < streamLen
//                          && streamChars[regionEnd] == IToken.EOF) {
//                      // skip EOF token (assume LPG puts one at end of input
//                      // character stream, since it does)
//                      endIdx--;
//                  }
//              } catch (ArrayIndexOutOfBoundsException e) {
//                  ErrorHandler.logError("ParseController.getTokenIterator(IRegion): error initializing lastTokIdx",
//                          e);
//                  // System.err.println("getTokenIterator: new Iterator(..)<init>: ArrayIndexOutOfBoundsException");
//                  // System.err.println(" regionEnd = " + regionEnd + ", endIdx = " + endIdx + ", streamLen = " + streamLen + ",
//                  // inputChars.length = " + streamChars.length);
//              }
//              lastTokIdx= endIdx;
//          }
//          int curTokIdx= Math.max(1, firstTokIdx); // skip bogus initial token
//
//          private int getTokenIndexAtCharacter(int offset) {
//              int result= stream.getTokenIndexAtCharacter(offset);
//              // getTokenIndexAtCharacter() answers the negative of the index of the
//              // preceding token if the given offset is not actually within a token.
//              if (result < 0) {
//                  result= -result + 1;
//              }
//
//              // The above may leave result set to a value that is one more than the
//              // last token index, so return the last token index if that's the case
//              // (This can happen if the end of the file contains some text that
//              // does not correspond to a token--e.g., if the text represents an adjunct
//              // or something unrecognized)
//              if (result >= stream.getTokens().size())
//                  result= stream.getTokens().size() - 1;
//
//              return result;
//          }
//
//          // The following declarations cover the whole input stream, which
//          // may be a proper superset of the range of the given region.
//          // For now, that's a simple way to collect the information, and
//          // most often the given region corresponds to the whole input anyway.
//          // In any case, iteration is based on the range of the given region.
//
//          // The preceding adjuncts for each token
//          IToken[][] precedingAdjuncts= new IToken[lastTokIdx + 1][];
//          {
//              stream.setStreamLength();
//              for(int i= 0; i < precedingAdjuncts.length; i++) {
//                  precedingAdjuncts[i]= stream.getPrecedingAdjuncts(i);
//              }
//          }
//
//          // The current indices for each array of preceding adjuncts
//          int[] nextPrecedingAdjunct= new int[lastTokIdx + 1];
//          {
//              for(int i= 0; i < nextPrecedingAdjunct.length; i++) {
//                  if (precedingAdjuncts[i].length == 0)
//                      nextPrecedingAdjunct[i]= -1;
//                  else
//                      nextPrecedingAdjunct[i]= 0;
//              }
//          }
//
//          // The following adjuncts (for the last token only)
//          IToken[] followingAdjuncts;
//          {
//              if (lastTokIdx <= 0)
//                  followingAdjuncts= new IToken[0];
//              else
//                  followingAdjuncts= stream.getFollowingAdjuncts(lastTokIdx);
//          }
//
//          // The current index for the array of following adjuncts
//          int nextFollowingAdjunct;
//          {
//              if (followingAdjuncts.length == 0)
//                  nextFollowingAdjunct= -1;
//              else
//                  nextFollowingAdjunct= 0;
//          }
//
//          // To support hasNext(); initial values may be reset if appropriate
//          private boolean finalTokenReturned= regionEnd < 1 || lastTokIdx <= 0;
//          private boolean finalAdjunctsReturned= !(followingAdjuncts.length > 0);
//
//          /**
//           * Tests whether the iterator has any unreturned tokens. These may
//           * include "regular" tokens and "adjunct" tokens (e.g., representing
//           * comments).
//           * 
//           * @return True if there is another token available, false otherwise
//           */
//          public boolean hasNext() {
//              return !(finalTokenReturned && finalAdjunctsReturned);
//          }
//
//          /**
//           * Returns the next available token in the iterator (or null if
//           * there is none)
//           * 
//           * Will return a valid token under conditions that would cause
//           * hasNext() to to return true; conversely, will return null under
//           * conditions that would cause hasNext() to return false.
//           * 
//           * As a side effect, updates the flags that are used to compute the
//           * value returned by hasNext().
//           * 
//           * The returned token may be a "regular" token (which will have a
//           * corresponding AST node) or an "adjunct" token (which will
//           * represent a comment). The tokens are returned in the order in
//           * which they occur in the text, regardless of their kind.
//           * 
//           */
//          public IToken next() {
//              int next= -1; // for convenience
//
//              // If we're not all the way through the tokens
//              if (curTokIdx <= lastTokIdx) {
//
//                  // First check for any remaining preceding adjuncts
//                  // of the current token
//                  next= nextPrecedingAdjunct[curTokIdx];
//                  // If the current token has any unreturned preceding
//                  // adjuncts
//                  if (next >= 0 && next < precedingAdjuncts[curTokIdx].length) {
//                      // Return the next preceding adjunct, incrementing the
//                      // adjunct index afterwards
//                      return precedingAdjuncts[curTokIdx][nextPrecedingAdjunct[curTokIdx]++];
//                  }
//
//                  // Flag whether the current token is the last one
//                  finalTokenReturned= curTokIdx >= lastTokIdx;
//
//                  // Return the current token, incrementing the token index
//                  // afterwards
//                  return stream.getIToken(curTokIdx++);
//              }
//
//              // If there are any adjuncts following the last token
//              if (nextFollowingAdjunct >= 0 && nextFollowingAdjunct < followingAdjuncts.length) {
//                  // Flag whether the current adjunct is the last one
//                  finalAdjunctsReturned= (nextFollowingAdjunct + 1) >= followingAdjuncts.length;
//
//                  // Return the current adjunct, incrementing the adjunct
//                  // index afterwards
//                  return followingAdjuncts[nextFollowingAdjunct++];
//              }
//
//              return null;
//          }
//
//          public void remove() {
//              throw new UnsupportedOperationException("Unimplemented");
//          }
//      };
//    }

    
    public boolean isKeyword(int type){
      switch (type) {
	    case X10Parser.ABSTRACT:
	    case X10Parser.AS:
	    case X10Parser.ASSERT:
	    case X10Parser.ASYNC:
	    case X10Parser.AT:
	    case X10Parser.ATEACH:
	    case X10Parser.ATHOME:
	    case X10Parser.ATOMIC:
	    case X10Parser.BREAK:
	    case X10Parser.CASE:
	    case X10Parser.CATCH:
	    case X10Parser.CLASS:
	    case X10Parser.CLOCKED:
	    case X10Parser.CONTINUE:
	    case X10Parser.DEF:
	    case X10Parser.DEFAULT:
	    case X10Parser.DO:
	    case X10Parser.ELSE:
	    case X10Parser.EXTENDS:
	    case X10Parser.FALSE:
	    case X10Parser.FINAL:
	    case X10Parser.FINALLY:
	    case X10Parser.FINISH:
	    case X10Parser.FOR:
	    case X10Parser.GOTO:
	    case X10Parser.HASZERO:
	    case X10Parser.HERE:
	    case X10Parser.IF:
	    case X10Parser.IMPLEMENTS:
	    case X10Parser.IMPORT:
	    case X10Parser.IN:
	    case X10Parser.INSTANCEOF:
	    case X10Parser.INTERFACE:
	    case X10Parser.ISREF:
	    case X10Parser.NATIVE:
	    case X10Parser.NEW:
	    case X10Parser.NULL:
	    case X10Parser.OFFER:
	    case X10Parser.OFFERS:
	    case X10Parser.OPERATOR:
	    case X10Parser.PACKAGE:
	    case X10Parser.PRIVATE:
	    case X10Parser.PROPERTY:
	    case X10Parser.PROTECTED:
	    case X10Parser.PUBLIC:
	    case X10Parser.RETURN:
	    case X10Parser.SELF:
	    case X10Parser.STATIC:
	    case X10Parser.STRUCT:
	    case X10Parser.SUPER:
	    case X10Parser.SWITCH:
	    case X10Parser.THIS:
	    case X10Parser.THROW:
	    case X10Parser.THROWS:
	    case X10Parser.TRANSIENT:
	    case X10Parser.TRUE:
	    case X10Parser.TRY:
	    case X10Parser.TYPE:
	    case X10Parser.VAL:
	    case X10Parser.VAR:
	    case X10Parser.VOID:
	    case X10Parser.WHEN:
	    case X10Parser.WHILE:
	    	return true;
	    default:
	    	return false;
      }
    }

	public IAnnotationTypeInfo getAnnotationTypeInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterator getTokenIterator(IRegion region) {
      final int regionOffset= region.getOffset();
      final int regionLength= region.getLength();
      final int regionEnd= regionOffset + regionLength - 1;
	
      List<Token> tokens = fTokens == null? new ArrayList<Token>(): fTokens.get(regionOffset, regionEnd);
      List<IToken> iTokens = new ArrayList<IToken>();
      if (tokens != null){
    	  for (Token t: tokens){
    		  iTokens.add(getIToken(t));
    	  }
      }
      return iTokens.listIterator();

	}
	
	public IPrsStream getParseStream(){
		return null;
	}
	
	public ILexStream getLexStream(){
		return null;
	}
	
	private IToken getIToken(final Token token){
		IToken ret = new IToken(){

			public int getAdjunctIndex() {
				// TODO Auto-generated method stub
				return 0;
			}

			public int getColumn() {
				return token.getCharPositionInLine();
			}

			public int getEndColumn() {
				return token.getCharPositionInLine() + token.getStopIndex() - token.getStartIndex();
			}

			public int getEndLine() {
				return token.getLine();
			}

			public int getEndOffset() {
				return token.getStopIndex();
			}

			public IToken[] getFollowingAdjuncts() {
				// TODO Auto-generated method stub
				return null;
			}

			public ILexStream getILexStream() {
				// TODO Auto-generated method stub
				return null;
			}

			public IPrsStream getIPrsStream() {
				// TODO Auto-generated method stub
				return null;
			}

			public int getKind() {
				return token.getType();
			}

			public ILexStream getLexStream() {
				// TODO Auto-generated method stub
				return null;
			}

			public int getLine() {
				return token.getLine();
			}

			public IToken[] getPrecedingAdjuncts() {
				// TODO Auto-generated method stub
				return null;
			}

			public IPrsStream getPrsStream() {
				// TODO Auto-generated method stub
				return null;
			}

			public int getStartOffset() {
				return token.getStartIndex();
			}

			public int getTokenIndex() {
				return token.getTokenIndex();
			}

			public String getValue(char[] arg0) {
				return token.getText();
			}

			public void setAdjunctIndex(int arg0) {
				// TODO Auto-generated method stub
				
			}

			public void setEndOffset(int arg0) {
			}

			public void setKind(int arg0) {
				// TODO Auto-generated method stub
				
			}

			public void setStartOffset(int arg0) {
				// TODO Auto-generated method stub
				
			}

			public void setTokenIndex(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
		};
		return ret;
	}
}
