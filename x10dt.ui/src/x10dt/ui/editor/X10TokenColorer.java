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

package x10dt.ui.editor;

import lpg.runtime.IToken;

import org.eclipse.imp.parser.IParseController;
//import org.eclipse.imp.parser.SimpleLPGParseController;
import org.eclipse.imp.services.base.TokenColorerBase;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import x10.parser.X10Parsersym;
import x10.parserGen.X10Parser;

public class X10TokenColorer extends TokenColorerBase implements X10Parsersym {
	TextAttribute commentAttribute, docCommentAttribute, characterAttribute, numberAttribute, identifierAttribute;
	
    public X10TokenColorer() {
        super();
        Display display = Display.getDefault();
        commentAttribute = new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_GREEN), null, SWT.NORMAL);         
        characterAttribute = new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_BLUE), null, SWT.NORMAL);        
        docCommentAttribute = new TextAttribute(display.getSystemColor(SWT.COLOR_BLUE), null, SWT.NORMAL);      
        identifierAttribute = new TextAttribute(display.getSystemColor(SWT.COLOR_BLACK), null, SWT.NORMAL);         
        numberAttribute = new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_YELLOW), null, SWT.BOLD);         
    }

    public void setLanguage(String language) { }

    TextAttribute getKeywordAttribute() {
        return keywordAttribute;
    }

    @Override
	public TextAttribute getColoring(IParseController controller, Object o) {
	    IToken token= (IToken) o;
	    switch (token.getKind()) {
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
	    	return keywordAttribute;
	    case X10Parser.DOCCOMMENT: 
	    	return docCommentAttribute;
	    case X10Parser.COMMENT: case X10Parser.LINE_COMMENT:
	    		return commentAttribute;
        case X10Parser.IDENTIFIER:
                 return identifierAttribute;
        case X10Parser.DoubleLiteral:
            case X10Parser.FloatingPointLiteral:
            case X10Parser.ByteLiteral:
            case X10Parser.ShortLiteral:
            case X10Parser.IntLiteral:
            case X10Parser.LongLiteral:
            case X10Parser.UnsignedByteLiteral:
            case X10Parser.UnsignedShortLiteral:
            case X10Parser.UnsignedIntLiteral:
            case X10Parser.UnsignedLongLiteral:
                 return numberAttribute;
            case X10Parser.CharacterLiteral:
            case X10Parser.StringLiteral:
                 return characterAttribute;
            default: {
//                SimpleLPGParseController lpgPC= (SimpleLPGParseController) controller;
//                // TODO The following should be folded into an LPG-specific token colorer base class
//                if (lpgPC.isKeyword(token.getKind()))
//                     return keywordAttribute;
//                else 
                	return null;
            }
	    }
	}

	/**
	 * Some day this should probably expand the damage region if the damage was
	 * to the opening or closing fence of a comment.
	 */
	public IRegion calculateDamageExtent(IRegion seed, IParseController ctlr) {
		return seed;
	}
}
