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

package x10dt.ui.editor.formatting;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;

/**
 * This scanner recognizes the X10 comments (single-, multi-line, and X10doc), strings and
 * character constants.
 * @author rmfuhrer
 */
public class X10PartitionScanner extends RuleBasedPartitionScanner implements IX10Partitions {
	/**
	 * Detector for empty comments.
	 */
	static class EmptyCommentDetector implements IWordDetector {
		/*
		 * @see IWordDetector#isWordStart
		 */
		public boolean isWordStart(char c) {
			return (c == '/');
		}

		/*
		 * @see IWordDetector#isWordPart
		 */
		public boolean isWordPart(char c) {
			return (c == '*' || c == '/');
		}
	}


	/**
	 * Word rule for empty comments.
	 */
	static class EmptyCommentRule extends WordRule implements IPredicateRule {
		private IToken fSuccessToken;
		/**
		 * Constructor for EmptyCommentRule.
		 * @param successToken
		 */
		public EmptyCommentRule(IToken successToken) {
			super(new EmptyCommentDetector());
			fSuccessToken= successToken;
			addWord("/**/", fSuccessToken); //$NON-NLS-1$
		}

		/*
		 * @see IPredicateRule#evaluate(ICharacterScanner, boolean)
		 */
		public IToken evaluate(ICharacterScanner scanner, boolean resume) {
			return evaluate(scanner);
		}

		/*
		 * @see IPredicateRule#getSuccessToken()
		 */
		public IToken getSuccessToken() {
			return fSuccessToken;
		}
	}

	/**
	 * Creates the partitioner and sets up the appropriate rules.
	 */
	public X10PartitionScanner() {
		super();

		IToken string= new Token(X10_STRING);
		IToken character= new Token(X10_CHARACTER);
		IToken javaDoc= new Token(X10_DOC);
		IToken multiLineComment= new Token(X10_MULTI_LINE_COMMENT);
		IToken singleLineComment= new Token(X10_SINGLE_LINE_COMMENT);

		List<IPredicateRule> rules= new ArrayList<IPredicateRule>();

		// Add rule for single line comments.
		rules.add(new EndOfLineRule("//", singleLineComment)); //$NON-NLS-1$

		// Add rule for strings.
		rules.add(new SingleLineRule("\"", "\"", string, '\\')); //$NON-NLS-2$ //$NON-NLS-1$

		// Add rule for character constants.
		rules.add(new SingleLineRule("'", "'", character, '\\')); //$NON-NLS-2$ //$NON-NLS-1$

		// Add special case word rule.
		EmptyCommentRule wordRule= new EmptyCommentRule(multiLineComment);
		rules.add(wordRule);

		// Add rules for multi-line comments and javadoc.
		rules.add(new MultiLineRule("/**", "*/", javaDoc)); //$NON-NLS-1$ //$NON-NLS-2$
		rules.add(new MultiLineRule("/*", "*/", multiLineComment)); //$NON-NLS-1$ //$NON-NLS-2$

		IPredicateRule[] result= new IPredicateRule[rules.size()];
		rules.toArray(result);
		setPredicateRules(result);
	}
}
