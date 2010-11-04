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
package org.eclipse.imp.x10dt.ui.editor;

import lpg.runtime.IToken;

import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.parser.SimpleLPGParseController;
import org.eclipse.imp.services.ITokenColorer;
import org.eclipse.imp.services.base.TokenColorerBase;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import x10.parser.X10Parsersym;

public class MyTokenColorer extends TokenColorerBase implements X10Parsersym, ITokenColorer {

	TextAttribute commentAttribute, characterAttribute, numberAttribute, identifierAttribute;
	
	public TextAttribute getColoring(IParseController controller, Object o) {
            IToken token= (IToken) o;
	    switch (token.getKind()) {
	    case TK_DocComment: case TK_SlComment: case TK_MlComment:
                 return commentAttribute;
            case TK_IDENTIFIER:
                 return identifierAttribute;
            case TK_DoubleLiteral:
            case TK_FloatingPointLiteral:
            case TK_IntegerLiteral:
            case TK_LongLiteral:
                 return numberAttribute;
            case TK_CharacterLiteral:
            case TK_StringLiteral:
                 return characterAttribute;
            default: {
                SimpleLPGParseController lpgPC= (SimpleLPGParseController) controller;
        	// TODO Can the following either be removed altogether or folded into the base class impl?
        	// RMF 10/26/2006 - Avoid AIOOB that happens if we pass error tokens to isKeyword()
                if (token.getKind() < TK_ERROR_TOKEN && lpgPC.isKeyword(token.getKind()))
                     return keywordAttribute;
                else return null;
            }
	    }
	}

	public MyTokenColorer() {
		super();
		Display display = Display.getDefault();
		commentAttribute = new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_RED), null, SWT.ITALIC); 		
		characterAttribute = new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_BLUE), null, SWT.BOLD); 		
		identifierAttribute = new TextAttribute(display.getSystemColor(SWT.COLOR_BLACK), null, SWT.NORMAL); 		
		numberAttribute = new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_YELLOW), null, SWT.BOLD); 		
	}

	public void setLanguage(String language) { }

    public IRegion calculateDamageExtent(IRegion seed) {
        return seed;
    }
}