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

/*
 * (C) Copyright IBM Corporation 2007
 * 
 * This file is part of the Eclipse IMP.
 */
/*
 * Created on Feb 9, 2006
 */
package org.eclipse.imp.x10dt.ui.parser;

import lpg.runtime.IPrsStream;
import lpg.runtime.IToken;
import lpg.runtime.LexStream;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.parser.ISourcePositionLocator;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

import polyglot.ast.Node;
import polyglot.types.Declaration;
import polyglot.util.Position;
import polyglot.visit.NodeVisitor;

// TODO This should really derive from the Java ASTNodeLocator impl in org.eclipse.safari.java.core...
// Or better yet, this implementation shouldn't be necessary at all, since Polyglot nodes all behave
// the same wrt position access and the visitor interface.
public class PolyglotNodeLocator implements ISourcePositionLocator {
    private final Node[] fNode= new Node[1];

    private int fOffset;

    private int fEndOffset;

    private final ISourceProject fSrcProject;

    private final LexStream fLS;

    private boolean DEBUG= false;

    private NodeVisitor fVisitor= new NodeVisitor() {
	public NodeVisitor enter(Node n) {
	    if (DEBUG)
		System.out.println("Entering node type = " + n.getClass().getName() + ", first token '" + fLS.getPrsStream().getTokenAtCharacter(n.position().offset()) + "'");
            // N.B.: Polyglot's notion of line # is 1 off from that of Eclipse's.
	    Position pos= n.position();

	    if (pos == null || pos.line() < 0) {
		if (DEBUG)
		    System.out.println("PolyglotNodeLocator.NodeVisitor.enter(Node):  node positions < 0 for node type = " + n.getClass().getName());
	    	return this;
	    }

	    if (DEBUG)
		System.out.println("Node extent: " + pos.offset() + " => " + pos.endOffset() + " [" + pos.line() + ":" + pos.column() + " => [" + pos.endLine() + ":" + pos.endColumn() + "]");
	    int nodeStartOffset= pos.offset();
	    int nodeEndOffset= pos.endOffset();
	    //System.out.println("Examining " + n.getClass().getName() + " node @ [" + nodeStartOffset + "->" + nodeEndOffset + ']');

	    if (nodeStartOffset <= fOffset && nodeEndOffset >= fEndOffset) {	
		if (DEBUG)
		    System.out.println(" --> " + n + " (" + n.getClass().getName() + ") node @ [" + nodeStartOffset + "->" + nodeEndOffset + "] selected.");
    		fNode[0]= n;
	    }
	    return this;
	}
	
	// Note:  Returning null is interpreted as a signal to *not* override
	// the given node
	public Node override(Node n) {
	    // Prune traversal to avoid examining nodes outside the given text range.
            // N.B.: Polyglot's notion of line # is 1 off from that of Eclipse's.

	    if (true) return null; // RMF 7/3/2007 - Disabled short-circuiting b/c it seemed to
	    // break node location (probably the result of "rogue offsets" on various node types).

	    Position pos= n.position();

	    if (pos == null || pos.line() < 0) {
	    	//System.out.println("PolyglotNodeLocator.NodeVisitor.override(Node):  node positions < 0 for node type = " + n.getClass().getName());
	    	return null;
	    }

	    int nodeStartOffset= pos.offset();
	    int nodeEndOffset= pos.endOffset();

//	    if (nodeStartOffset == fOffset) System.out.println("NodeStartOffset = fOffset");
//	    if (nodeEndOffset == fEndOffset) System.out.println("NodeEndOffset = fEndOffset");

	    //if (nodeStartOffset > fEndOffset || nodeEndOffset < fOffset)
	    if (nodeStartOffset > fOffset)
	    	return n;
	    return null;
	}
    };

    // Almost identical to the above visitor, but overrides enter(Node,Node) rather
    // than enter(Node), so it can save the parent instead of the node itself.
    private NodeVisitor fParentVisitor= new NodeVisitor() {
	public NodeVisitor enter(Node parent, Node n) {
	    if (DEBUG)
		System.out.println("Entering node type = " + n.getClass().getName());
            // N.B.: Polyglot's notion of line # is 1 off from that of Eclipse's.
	    Position pos= n.position();

	    if (pos == null || pos.line() < 0) {
		if (DEBUG)
		    System.out.println("PolyglotNodeLocator.ParentNodeVisitor.enter(Node,Node):  node positions < 0 for node type = " + n.getClass().getName());
	    	return this;
	    }

	    if (DEBUG)
		System.out.println("Node extent: " + pos.offset() + " => " + pos.endOffset() + " [" + pos.line() + ":" + pos.column() + " => [" + pos.endLine() + ":" + pos.endColumn() + "]");
	    int nodeStartOffset= pos.offset();
	    int nodeEndOffset= pos.endOffset();
	    //System.out.println("Examining " + n.getClass().getName() + " node @ [" + nodeStartOffset + "->" + nodeEndOffset + ']');

	    if (nodeStartOffset <= fOffset && nodeEndOffset >= fEndOffset) {	
		if (DEBUG)
		    System.out.println(" --> " + n + " (" + n.getClass().getName() + ") node @ [" + nodeStartOffset + "->" + nodeEndOffset + "] selected.");
    		fNode[0]= parent;
	    }
	    return this;
	}
	
	// Note:  Returning null is interpreted as a signal to *not* override
	// the given node
	public Node override(Node n) {
	    // Prune traversal to avoid examining nodes outside the given text range.
            // N.B.: Polyglot's notion of line # is 1 off from that of Eclipse's.
	    Position pos= n.position();

	    if (pos == null || pos.line() < 0) {
	    	//System.out.println("PolyglotNodeLocator.NodeVisitor.override(Node):  node positions < 0 for node type = " + n.getClass().getName());
	    	return null;
	    }

	    int nodeStartOffset= pos.offset();
	    int nodeEndOffset= pos.endOffset();

//	    if (nodeStartOffset == fOffset) System.out.println("NodeStartOffset = fOffset");
//	    if (nodeEndOffset == fEndOffset) System.out.println("NodeEndOffset = fEndOffset");

	    //if (nodeStartOffset > fEndOffset || nodeEndOffset < fOffset)
	    if (nodeStartOffset > fOffset)
	    	return n;
	    return null;
	}
    };

    public PolyglotNodeLocator(ISourceProject srcProject, LexStream ls) {
	fLS= ls;
	fSrcProject= srcProject;
    }

    public Object findNode(Object ast, int offset) {
	return findNode(ast, offset, offset);
    }

    public Object findNode(Object ast, int startOffset, int endOffset) {
	if (DEBUG) {
	    System.out.println("Looking for node spanning offsets " + startOffset + " => " + endOffset);
	}
	if (ast == null) return null;
//	if (endOffset == startOffset && Character.isWhitespace(fLS.getCharValue(startOffset)))
//	    return null;
	if (DEBUG) {
	    IPrsStream ps= fLS.getPrsStream();
	    if (endOffset == startOffset)
		System.out.println("Token at this offset: '" + ps.getTokenAtCharacter(startOffset) + "'");
	    else {
		System.out.println("Token span: '" + ps.getTokenAtCharacter(startOffset) + "' to '" + ps.getTokenAtCharacter(endOffset) + "'");
	    }
	}
	fOffset= startOffset;
	fEndOffset= endOffset;
	((Node) ast).visit(fVisitor);	// assigns to fNode[0], if a suitable node is found
	// SMS 14 Jun 2006:  Elaborated on println:
	if (fNode[0] == null) {
		//System.out.println("Selected node is null");
	} else {
	    if (DEBUG)
		System.out.println("Selected node (type): " + fNode[0] + " (" + fNode[0].getClass().getName() + ")");
	}
	return fNode[0];
    }

    public Object findParentNode(Object ast, int offset) {
	return findParentNode(ast, offset, offset);
    }

    public Object findParentNode(Object ast, int startOffset, int endOffset) {
	if (DEBUG)
	    System.out.println("Looking for parent of node spanning offsets " + startOffset + " => " + endOffset);
	if (ast == null) return null;
	fOffset= startOffset;
	fEndOffset= endOffset;
	((Node) ast).visit(fParentVisitor);	// assigns to fNode[0], if a suitable node is found
	// SMS 14 Jun 2006:  Elaborated on println:
	if (fNode[0] == null) {
		//System.out.println("Selected node is null");
	} else {
	    if (DEBUG)
		System.out.println("Selected node (type): " + fNode[0] + " (" + fNode[0].getClass().getName() + ")");
	}
	return fNode[0];
    }

    public int getStartOffset(Object entity) {
	Position pos;

	if (entity instanceof Declaration)
	    pos= ((Declaration) entity).position();
	else if (entity instanceof Node)
	    pos= ((Node)entity).position();
	else if (entity instanceof Position)
	    pos= (Position) entity;
        else if (entity instanceof IToken)
            return ((IToken) entity).getStartOffset();
	else
	    return -1;

	return pos.offset();
    }

    public int getEndOffset(Object entity) {
	Position pos;

	if (entity instanceof Declaration)
	    pos= ((Declaration) entity).position();
	else if (entity instanceof Node)
	    pos= ((Node)entity).position();
	else if (entity instanceof Position)
	    pos= (Position) entity;
        else if (entity instanceof IToken)
            return ((IToken) entity).getEndOffset();
	else
	    return -1;

	return pos.endOffset();
    }

    public int getLength(Object node) {
    	return getEndOffset(node) - getStartOffset(node);
    }

    public IPath getPath(Object node) {
	if (node instanceof Declaration) {
	    final String path= ((Declaration) node).position().file();
	    if (path.endsWith(".class")) {
		// TODO Fix totally bogus hardwired rt.jar path inserted for testing.
		// Probably need the IProject or ISourceProject to scan the classpath and
		// figure out where this class file is.
		try {
		    IClassFile classFile= resolveClassFile(path);
		    if (classFile.exists())
			// Eclipse doesn't seem to properly handle "jar:" URLs.
//			return new Path("jar:" + classFile.getPath().toPortableString() + "!" + path);
			return classFile.getPath(); // but this just describes the archive container, not the member within it...
		} catch (JavaModelException jme) {
		    System.err.println(jme.getMessage());
		}
	    }
	    return new Path(path);
	} else if (node instanceof Node)
	    return new Path(((Node)node).position().path());
	else if (node instanceof Position)
	    return new Path(((Position) node).file());
	else
	    return null;
    }

    private IClassFile resolveClassFile(final String path) throws JavaModelException {
	IJavaProject javaProject= JavaCore.create(fSrcProject.getRawProject());
	IClasspathEntry[] cpEntries= javaProject.getResolvedClasspath(true);

	for(int i= 0; i < cpEntries.length; i++) {
	    IClasspathEntry entry= cpEntries[i];

	    if (entry.getEntryKind() == IClasspathEntry.CPE_LIBRARY) {
		IPath entryPath= entry.getPath();
		IPackageFragmentRoot pkgRoot= javaProject.findPackageFragmentRoot(entryPath);
		final int pkgEnd= path.lastIndexOf('/');
		String pkgName= path.substring(0, pkgEnd).replace('/', '.');
		IPackageFragment pkgFrag= pkgRoot.getPackageFragment(pkgName);

		return pkgFrag.getClassFile(path.substring(pkgEnd + 1));
	    }
	}
	return null;
    }
}
