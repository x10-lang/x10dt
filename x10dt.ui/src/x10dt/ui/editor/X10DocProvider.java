/*******************************************************************************
 * Copyright (c) 2008 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Robert Fuhrer (rfuhrer@watson.ibm.com) -initial API and implementation
 *******************************************************************************/

package x10dt.ui.editor;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.jar.JarFile;

import org.eclipse.imp.language.ILanguageService;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.services.IDocumentationProvider;

import polyglot.ast.ClassDecl;
import polyglot.ast.ConstructorDecl;
import polyglot.ast.Field;
import polyglot.ast.FieldDecl;
import polyglot.ast.Formal;
import polyglot.ast.Id;
import polyglot.ast.Local;
import polyglot.ast.LocalDecl;
import polyglot.ast.MethodDecl;
import polyglot.ast.NamedVariable;
import polyglot.ast.New;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;
import polyglot.types.ClassDef;
import polyglot.types.ClassType;
import polyglot.types.ConstructorDef;
import polyglot.types.ConstructorInstance;
import polyglot.types.ContainerType;
import polyglot.types.FieldDef;
import polyglot.types.FieldInstance;
import polyglot.types.LocalDef;
import polyglot.types.LocalInstance;
import polyglot.types.MemberDef;
import polyglot.types.MethodDef;
import polyglot.types.Named;
import polyglot.types.ProcedureDef;
import polyglot.types.ProcedureInstance;
import polyglot.types.Type;
import polyglot.types.TypeObject;
import polyglot.types.VarDef;
import polyglot.types.VarInstance;
import polyglot.util.Position;
import x10.ast.AtExpr;
import x10.ast.AtStmt;
import x10.ast.Closure;
import x10.ast.DepParameterExpr;
import x10.types.ConstrainedType;
import x10dt.ui.parser.PolyglotNodeLocator;

/**
 * Provide  info for Hover Help and context help
 * (Dynamic Help / F1) as well.
 * 
 * This aims to provide some sort of information for a variety of objects. Need
 * to document whether it provides info with HTML or not.
 * Currently, queries by ContextHelper and HoverHelper process any html provided differently.
 */
public class X10DocProvider implements IDocumentationProvider, ILanguageService {
    private static final boolean traceOn = false;

    /**
     * Get text documentation for an entity in the x10 source code
     * @param target the object for which we want text info
     * @param parseController
     * @returns a text string of javadoc-style info, or other, if available
     */
    public String getDocumentation(Object target, IParseController parseController) {
        if (traceOn) System.out.println("\nX10DocProvider.getDocumentation(), target is :"+ target.toString());
        String doc = getHelpForEntity(target, parseController);
        if (traceOn) System.out.println("   " + doc);
        return doc;
    }

	/**
	 * Provides javadoc-like info (if available) and more for a variety of entities
	 */
	public String getHelpForEntity(Object target, IParseController parseController) {
		try {
			Node root = (Node) parseController.getCurrentAst();
			target = getTarget(target, parseController, root);

			// 4 kinds of declared entities: types, members, variables and functions
			// (functions not yet handled)
			if (target instanceof FieldDecl) {
				return getHelpForFieldDef(((FieldDecl) target).fieldDef());
			} else if (target instanceof FieldDef) {
			    return getHelpForFieldDef((FieldDef) target);

			} else if (target instanceof NamedVariable) {
				return getHelpForEntity((NamedVariable) target, parseController, root);
			} else if (target instanceof LocalDef) {
			    return getHelpForEntity(((LocalDef) target).asInstance(), parseController, root);

            } else if (target instanceof MethodDecl) {
                return getHelpForMethodDef(((MethodDecl) target).methodDef());
            } else if (target instanceof MethodDef) {
                return getHelpForMethodDef((MethodDef) target);

            } else if (target instanceof ConstructorDecl) {
                return getHelpForCtorDef(((ConstructorDecl) target).constructorDef());
            } else if (target instanceof ConstructorDef) {
                return getHelpForCtorDef((ConstructorDef) target);

            } else if (target instanceof ClassType) {  
				return getHelpForEntity((ClassType) target, parseController, root);	
			} else if (target instanceof ClassDecl) {
				return getHelpForEntity((ClassDecl) target, parseController, root);
			
			} else if (target instanceof AtStmt) {
				return getHelpForEntity((AtStmt) target, parseController, root);
			} else if (target instanceof Closure) {
				return getHelpForEntity((Closure) target, parseController, root);
			
			} else if (target instanceof TypeNode) {
				return getHelpForEntity((TypeNode) target, parseController, root);
			} else if (target instanceof ConstrainedType) {
				return getHelpForEntity((ConstrainedType) target, parseController, root);
			}
		} catch (NullPointerException e) {
			// If this exception is thrown, it means that there was a compilation error in the file, silently ignore
			return "";
		}
		return "";
	}

    private Object getTarget(Object target, IParseController parseController, Node root) {
        if (target instanceof Id) {
            Id id = (Id) target;
            PolyglotNodeLocator locator = (PolyglotNodeLocator) parseController.getSourcePositionLocator();
            Node parent = (Node) locator.getParentNodeOf(id, root);
            target = parent;
        }

        if (target instanceof Field) { // field reference
            Field field = (Field) target;
            FieldInstance fi = field.fieldInstance();

            target = fi.def();
        } else if (target instanceof Local) { // local var reference
            Local local = (Local) target;
            LocalInstance li = local.localInstance();

            target = li.def();
        } else if (target instanceof LocalDecl) {
            LocalDecl localDecl = (LocalDecl) target;
            LocalDef ld = localDecl.localDef();

            target = ld;
        }

        return target;
    }

    private String getHelpForFieldDef(FieldDef fd) {
        String sig = getSignature(fd);

        if (sig != null) {
            return getX10DocFor(sig, fd);
        }
        return "Field '" + fd.name() + "' of type " + fd.type().toString();
    }

    private String getSignature(FieldDef fd) {
        ContainerType ownerType = fd.container().get();

        if (ownerType.isClass()) {
            ClassType ownerClass = (ClassType) ownerType;
            String ownerName = ownerClass.fullName().toString();
            String fieldType = fd.type().toString(); // int or pkg.TypeName; want TypeName only
            String fieldName= fd.name().toString();

            return fieldType + " " + ownerName + "." + fieldName;
        }
        return null;
    }

	private String getHelpForEntity(NamedVariable target, IParseController parseController, Node root) {
    	Type type = target.type();
		return "Variable '" + target + "' of type " + type.toString();
    }
	
	private String getHelpForEntity(LocalInstance li, IParseController parseController, Node root) {
	    LocalDef ldef = li.def();
		String s = ldef.toString();

		return addNameToDoc(s, null);
	}

	private String getHelpForEntity(ClassType ct, IParseController parseController, Node root) {
	    ClassDef cdef = ct.def();
		String qualifiedName = cdef.fullName().toString();
		qualifiedName = stripArraySuffixes(qualifiedName);			
		return getX10DocFor(qualifiedName, cdef);
	}

	private String getHelpForEntity(ClassDecl cdecl, IParseController parseController, Node root) {
		ClassDef cdef= cdecl.classDef();
		if (cdef == null)
		    return null;
		String fullName = cdef.fullName().toString();
		String doc = getX10DocFor(fullName, cdef);
		return doc;
	}

    private String getHelpForMethodDef(MethodDef mdef) {
        if (mdef == null)
            return null;
        String sig = getSignature(mdef);	
		String doc = getX10DocFor(sig, mdef);
		return doc;
    }

    private String getSignature(MethodDef mdef) {
        String containerName = containerName(mdef);

        return getSignature(mdef, qualify(containerName, mdef.name().toString()), mdef.returnType().get());
    }

    private String getHelpForCtorDef(ConstructorDef cdef) {
        if (cdef == null)
            return null;
        String sig = getSignature(cdef); 
        String doc = getX10DocFor(sig, cdef);
        return doc;
    }

    private String getSignature(ConstructorDef cdef) {
        return getSignature(cdef, qualify(containerName(cdef), "this"), null);
    }

    private String getHelpForEntity(AtStmt atStmt, IParseController parseController, Node root) {
		
    	String fullName = "at (" + atStmt.place().toString() + ") captures ";
    	List<VarInstance<? extends VarDef>> env = atStmt.atDef().capturedEnvironment();
    	String captures = formatEnvList(env);

    	return getX10DocFor(fullName + captures, atStmt.atDef().asInstance());

    }

    private String getHelpForEntity(Closure c, IParseController parseController, Node root) {
		if (c instanceof AtExpr) {
			AtExpr ae = (AtExpr) c;
			String fullName = "at[" + ae.returnType().toString() + "] (" + ae.place().toString() + ") captures ";
			String captures = formatEnvList(ae.closureDef().capturedEnvironment());
			
			return getX10DocFor(fullName + captures, c.closureDef().asInstance());
		} else {
			// String fullName = getClosure
			String formals = formatFormalArgs(c.formals());
			DepParameterExpr guard_ = c.guard();
			String guard = guard_ == null ? "" : "{ " + guard_.toString() + " }";
			String ret = c.returnType().toString();
			String fullName = formals + guard + " => " + ret + " captures ";
			String captures = formatEnvList(c.closureDef().capturedEnvironment());
			
			return getX10DocFor(fullName + captures, c.closureDef().asInstance());

		}
    }

	private String getHelpForEntity(TypeNode tn, IParseController parseController, Node root) {
		PolyglotNodeLocator locator = (PolyglotNodeLocator) parseController.getSourcePositionLocator();
		Node parent = (Node) locator.getParentNodeOf(tn, root);

		if (parent instanceof ConstructorDecl) {
			ConstructorDecl cd = (ConstructorDecl) parent;
			String fullName = tn.toString();// FIXME better way of getting fully qualified name??
			
			// get Constructor args, if any
			String sig= fullName + formatArgs(cd.constructorDef().formalNames()); 
			ConstructorInstance ci= cd.constructorDef().asInstance();

			return getX10DocFor(sig, ci);
		} else if (parent instanceof New) {
			New n = (New) parent;		
			return getX10DocFor(n.constructorInstance());
		} else {
			Type type = tn.type();
			if (type == null)
			    return null;
			String qualifiedName = tn.qualifierRef().get().toString();
			qualifiedName = stripArraySuffixes(qualifiedName);
			return getX10DocFor(qualifiedName, type); 
		}
	}

	private String getHelpForEntity(ConstrainedType target, IParseController parseController, Node root) {
	    String doc = getX10DocFor(target);
		return addNameToDoc(target.toString(), doc);
	}

	private String qualify(String container, String member) {
	    return (container != null && container.length() > 0) ? container + "." + member : member;
	}

	private String containerName(MemberDef mdef) {
	    String result= "(unspecified)";
        ContainerType type = mdef.container().get();

        if (type instanceof Named) {
            Named ct = (Named) type;
            result = ct.fullName().toString();
        }
        return result;
	}

	/**
	 * Get the method signature, including argument types and argument names
	 * @param mi the method instance
	 * @param md the method decl - can be null, but if available, can get better info
	 * @return
	 */
	private String getSignature(ProcedureDef pdef, String name, Type retType) {
		String sig= (retType != null) ? retType + " " + name : name;
		List<LocalDef> argList= pdef.formalNames();
		String argString= formatArgs(argList);

		sig = sig + argString;
		return sig;
	}

	/**
	 * Get the javadoc-like comment string for an X10 declaration
	 * <p>Does not return signature(qualifiedName) info if the javadoc is empty.
	 * <p>That is, if the javadoc is empty, don't return anything
	 */
	// Maybe rename this getDocFor(...)
	private String getX10DocFor(String qualifiedName, TypeObject decl) {
		String doc = getX10DocFor(decl);
		if (doc != null) return addNameToDoc(qualifiedName, doc);
		else return qualifiedName;
	}

	private String getX10DocFor(TypeObject obj) {
		if (obj == null) return "";

		Position pos= obj.position();
		boolean skipBackwardOverQualifiers = false;

		if (hasQualifiers(obj)) {
		    skipBackwardOverQualifiers = true;
		}
        String doc = getNewRawX10DocFor(pos, skipBackwardOverQualifiers);
		return doc;
	}

    private boolean hasQualifiers(TypeObject obj) {
        return (obj instanceof FieldDef || obj instanceof LocalDef || obj instanceof MethodDef);
    }

    /**
	 * Get the actual text within the x10doc(javadoc) text. Substitutes HTML for tags, etc
     * @param skipBackwardOverQualifiers 
	 */
	private String getNewRawX10DocFor(Position pos, boolean skipBackwardOverQualifiers) {
		if (pos.isCompilerGenerated()) {
			return "";
		}

		String path = pos.file();
		Reader reader = getReader(path);
		
		try {
			String fileSrc = readReader(reader);
			int idx = pos.offset();

			if (skipBackwardOverQualifiers) idx = skipBackwardOverQualifiers(fileSrc, idx);
			idx = skipBackwardWhite(fileSrc, idx);
			if (lookingPastEndOf(fileSrc, idx, "*/")) {
				String doc = collectBackwardTo(fileSrc, idx, "/**");
				doc = getCommentText(doc);// strip comment chars,stars, etc
				if (traceOn)System.out.println("X10DocProvider.getX10DocCharsFor: "+ doc);
				StringReader rdr = new StringReader(doc);
				X10Doc2HTMLTextReader xrdr=new X10Doc2HTMLTextReader(rdr);
				String result = readReader(xrdr);
				//String result = xrdr.getString();
				return result;
			}
		} catch (Exception e) {
			return "";
		} 
		return null;
	}
	
	private int skipBackwardOverQualifiers(String fileSrc, int idx) {
        // cheap hack - skip over a contiguous set of identifiers (which happen to be qualifiers)
	    do {
	        do {
	            idx--;
	        } while (idx > 0 && Character.isWhitespace(fileSrc.charAt(idx)));
	        if (idx <= 0 || !Character.isJavaIdentifierPart(fileSrc.charAt(idx))) {
	            return idx+1;
	        }
	        while (idx > 0 && Character.isJavaIdentifierPart(fileSrc.charAt(idx))) {
	            idx--;
	        }
	    } while (true);
    }

    private Reader getReader(String path) {
		Reader reader = null;
		try {
			if (path.contains(".jar")) {
				int index = path.lastIndexOf(":");
				JarFile jar = new JarFile(path.substring(0, index));
				return new InputStreamReader(jar.getInputStream(jar.getJarEntry(path.substring(index + 1))));
			}

			else {
				reader = new FileReader(new File(path));
			}
		} catch (IOException e) {
			// fall through
		}

		return reader;
	}
	
	
	private static final String BOLD="<b>";
	private static final String UNBOLD="</b>";
	private static final String NEWLINE="\n";
	private static final String PARA="<p>";
	
	/**
	 * Add name (probably a full signature) to the javadoc string, nicely formatted
	 * <p>Note: if we just use newlines, somebody takes them out later.
	 * This will be uglier in Context help till we better-format it, but prettier in hover help
	 * @param name the signature or properly qualified name of the thing
	 * @param doc the javadoc string
	 * @return
	 */
	private String addNameToDoc(String name, String doc) {
		if (doc == null) {
			doc = "";
		}
		doc = BOLD+name+UNBOLD + PARA+doc+PARA;
		return doc;
	}

	/**
	 * 
	 * Reads text from javadoc (x10doc) comment, ignoring leading/trailing
	 * slash-star-star and star-slash and * leading lines. <br>
	 * Assumes leading/trailing comment chars should also be removed
	 * 
	 * @param text
	 *            the text of the comment
	 * @returns text without the javadoc comment characters
	 * 
	 */
	private String getCommentText(String text) {
		return getCommentText(text, true);
	}

	/**
	 * 
	 * Reads text from javadoc (x10doc) comment, ignoring leading/trailing
	 * slash-star-star and star-slash and * leading lines. <br>
	 * adapted from JavadocCommentReader.read() <br>
	 * Also strips some html and other tags as well, for now
	 * 
	 * @param text
	 *            the text of the comment
	 * @param stripLeadingTrailingParts
	 *            whether or not to strip the leading/trailing comment chars
	 * @return text with the intervening (if any) leading star characters
	 * 
	 */
	private String getCommentText(String text, boolean stripLeadingTrailingParts) {
		StringBuilder result = new StringBuilder();

		// If it starts with the 3 chars /** and ends with the two chars */,
		// then start by ditching these
		if (stripLeadingTrailingParts) {
			text = text.substring(3, text.length() - 2);
		}
		int fCurrPos = 0;
		int fEndPos = text.length() - 1;

		boolean fWasNewLine = false;
		while (fCurrPos < fEndPos) {// was if
			char ch;
			if (fWasNewLine) {
				do {
					ch = text.charAt(fCurrPos++);
				} while (fCurrPos < fEndPos && Character.isWhitespace(ch));
				if (ch == '*') {
					if (fCurrPos < fEndPos) {
						do {
							ch = text.charAt(fCurrPos++);
						} while (ch == '*');
					}
				}
			} else {
				ch = text.charAt(fCurrPos++);
			}
			// determine if prev char was line delimiter; if so will affect how
			// we start next line
			fWasNewLine = ch == '\n' || ch == '\r';
			result.append(ch);
		}
		String resStr = result.toString();

		return resStr;
	}

	/**
	 * Adapted from jdt's ContextHelpPart.decodeContextBoldTags()
	 */
	private String decodeContextBoldTags(String styledText) {

		if (styledText == null) {
			return "No description available ";
		}
		String decodedString = styledText.replaceAll("<@#\\$b>", "<b>"); //$NON-NLS-1$ //$NON-NLS-2$
		decodedString = decodedString.replaceAll("</@#\\$b>", "</b>"); //$NON-NLS-1$ //$NON-NLS-2$
		// decodedString = escapeSpecialChars(decodedString, true);
		// decodedString = decodedString.replaceAll("\r\n|\n|\r", "<br/>");
		// //$NON-NLS-1$ //$NON-NLS-2$
		return decodedString;
	}
	/**
	 * handle the at-param, at-returns etc tags by translating into html defn lists
	 * @param styledText
	 * @return
	 */
	private String decodeAts(String styledText) {
		return "";
	}

	/**
	 * Adapted from jdt's ReusableHelpPart.escapeSpecialChars()
	 */
	String escapeSpecialChars(String value) {
		return escapeSpecialChars(value, false);
	}

	/**
	 * Also adapted from jdt's ReusableHelpPart.escapeSpecialChars()
	 */
	String escapeSpecialChars(String value, boolean leaveBold) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);

			switch (c) {
			case '&':
				buf.append("&amp;"); //$NON-NLS-1$
				break;
			case '<':
				if (leaveBold) {
					int length = value.length();
					if (i + 6 < length) {
						String tag = value.substring(i, i + 7);
						if (tag.equalsIgnoreCase("</code>")) { //$NON-NLS-1$
							buf.append("</span>"); //$NON-NLS-1$
							i += 6;
							continue;
						}
					}
					if (i + 5 < length) {
						String tag = value.substring(i, i + 6);
						if (tag.equalsIgnoreCase("<code>")) { //$NON-NLS-1$
							buf.append("<span font=\"code\">"); //$NON-NLS-1$
							i += 5;
							continue;
						}
					}
					if (i + 3 < length) {
						String tag = value.substring(i, i + 4);
						if (tag.equalsIgnoreCase("</b>")) { //$NON-NLS-1$
							buf.append(tag);
							i += 3;
							continue;
						}
						if (tag.equalsIgnoreCase("<br>")) { //$NON-NLS-1$
							buf.append("<br/>"); //$NON-NLS-1$
							i += 3;
							continue;
						}
					}
					if (i + 2 < length) {
						String tag = value.substring(i, i + 3);
						if (tag.equalsIgnoreCase("<b>")) { //$NON-NLS-1$
							buf.append(tag);
							i += 2;
							continue;
						}
					}
				}
				buf.append("&lt;"); //$NON-NLS-1$
				break;
			case '>':
				buf.append("&gt;"); //$NON-NLS-1$
				break;
			case '\'':
				buf.append("&apos;"); //$NON-NLS-1$
				break;
			case '\"':
				buf.append("&quot;"); //$NON-NLS-1$
				break;
			case 160:
				buf.append(" "); //$NON-NLS-1$
				break;

			default:
				buf.append(c);
				break;
			}
		}
		return buf.toString();
	}


	private String collectBackwardTo(String fileSrc, int idx, String string) {
		return fileSrc.substring(fileSrc.lastIndexOf(string, idx), idx);
	}

	private boolean lookingPastEndOf(String fileSrc, int endIdx, String string) {
		int idx = endIdx - string.length();
		int fnd=fileSrc.indexOf(string, idx);
		return fnd == idx;
	}

	private int skipBackwardWhite(String fileSrc, int idx) {
		while (idx > 0 && Character.isWhitespace(fileSrc.charAt(idx - 1)))
			idx--;
		return idx;
	}
	/**
	 * Get the next 'token' in given string, starting at given index, up to next whitespace or end of string.
	 * <p>e.g. looking for \@param foo the description of something  -- looking for 'foo'
	 * @param str
	 * @param idx position in the string to start looking
	 * @return the token found.  The length of this token will specify how far parsing ate into the original string
	 */
	private String getNextToken(String str, int idx) {
		String result="";
		int begin=idx;
		int len=str.length();
		try {
		    // skip leading whitespace
		    while(idx<len && Character.isWhitespace(str.charAt(idx))) {
		        idx++;
		    }
		    while(idx<len && !Character.isWhitespace(str.charAt(idx))) {
		        idx++;
		    }
		    result= str.substring(begin,idx);
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return result;
	}

	private String stripArraySuffixes(String qualifiedName) {
		while (qualifiedName.endsWith("[]")) {
			qualifiedName = qualifiedName.substring(0,
					qualifiedName.length() - 2);
		}
		return qualifiedName;
	}

	private String[] convertParamTypes(ProcedureInstance<?> pi) {
		String[] paramTypes = new String[pi.formalTypes().size()];

		int i = 0;
		for (Type formalType: pi.formalTypes()) {
			String typeName = formalType.toString();
			String typeSig = (typeName.indexOf('.') > 0) ? "L"
					+ formalType.toString() + ";" : typeName;
			paramTypes[i++] = typeSig;
		}
		return paramTypes;
	}
	
	private String readReader(Reader reader) {
		try {
			StringBuffer buffer = new StringBuffer();
			char[] part = new char[2048];
			int read = 0;

			while ((read = reader.read(part)) != -1)
				buffer.append(part, 0, read);

			return buffer.toString();
		} catch (IOException ex) {
			System.err.println("I/O Exception: " + ex.getMessage());
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ex) {
					// silently ignored
				}
			}
		}
		return "";
	}
	/**
	 * Given a list of string args, format them with commas between
	 * e.g. "int a, int b, String c"  etc.
	 * @param args
	 * @return the formatted string list of args
	 */
	private String formatArgs(List<LocalDef> args) {
		StringBuilder result = new StringBuilder();
		
		result.append("(");
		int idx = 0;
		for (LocalDef argDef: args) {
		    String typeAndName = argDef.type() + " " + argDef.name();
		    typeAndName = removeJavaLang(typeAndName);
            if (idx++ > 0) {
                result.append(", ");
            }
		    result.append(typeAndName);
		}
		result.append(")");
		return result.toString();
		
	}
	
	/**
	 * Given a list of string args, format them with commas between
	 * e.g. "int a, int b, String c"  etc.
	 * @param args
	 * @return the formatted string list of args
	 */
	private String formatFormalArgs(List<Formal> args) {
		StringBuilder result = new StringBuilder();
		
		result.append("(");
		int idx = 0;
		for (Formal arg: args) {
			LocalDef argDef = arg.localDef();
		    String typeAndName = argDef.type() + " " + argDef.name();
		    typeAndName = removeJavaLang(typeAndName);
            if (idx++ > 0) {
                result.append(", ");
            }
		    result.append(typeAndName);
		}
		result.append(")");
		return result.toString();
		
	}
	
	/**
	 * Given a list of environment variables, format them with commas between them.
	 * If they are constant, also prints out their constant value
	 * e.g. "{a : int, b : int, c : String = 3}"  etc.
	 * @param env
	 * @return the formatted string list of variables
	 */
	private String formatEnvList(List<VarInstance<? extends VarDef>> env) {
		StringBuilder result = new StringBuilder();
		
		result.append("{");
		int idx = 0;
		for (VarInstance<? extends VarDef> envInst: env) {
            if (idx++ > 0) {
                result.append(", ");
            }
            result.append(envInst.name());
            result.append(" : ");
            result.append(removeJavaLang(envInst.type().toString()));
            if(envInst.isConstant()) {
            	result.append(" = ");
            	result.append(envInst.constantValue().toString());
            }
		}
		result.append("}");
		return result.toString();
		
	}

	/**
	 * Remove the "java.lang." prefix on a type, if it exists
	 * @param name
	 * @return the truncated name
	 */
	private String removeJavaLang(String name) {
		final String JAVALANG="java.lang.";
		final int len=JAVALANG.length();
		String result=name;
		if(name.startsWith(JAVALANG)) {
			result=result.substring(len);
		}
		return result;
	}
}
