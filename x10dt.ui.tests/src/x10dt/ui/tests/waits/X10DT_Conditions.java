/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Rick Lesniak (lesniakr@us.ibm.com) - initial API and implementation
 *******************************************************************************/
package x10dt.ui.tests.waits;

import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * @author rick lesniak
 *
 */

public abstract class X10DT_Conditions {

	/**
	 * Gets the condition for checking if a tree has a specific item.
	 * 
	 * @param tree the tree
	 * @param itemName the item name the tree must have, in order for {@link ICondition#test()} to evaluate to
	 *            <code>true</code>.
	 * @return <code>true</code> if the tree has the item specified. Otherwise <code>false</code>.
	 * @throws IllegalArgumentException Thrown if the item name is null.
	 * @since 2.0
	 */
	public static ICondition treeNodeHasItem(SWTBotTreeItem tree, String itemName) {
		return new TreeNodeHasItem(tree, itemName);
	}

	public static ICondition treeHasNode(SWTBotTree tree,String nodeName) {
		return new TreeHasNode(tree, nodeName);
	}

}
