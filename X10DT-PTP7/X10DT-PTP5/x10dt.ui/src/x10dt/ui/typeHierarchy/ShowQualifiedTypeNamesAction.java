package x10dt.ui.typeHierarchy;
/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/


import org.eclipse.jface.action.Action;
import org.eclipse.swt.custom.BusyIndicator;

/**
 * Action enable / disable showing qualified type names
 */
public class ShowQualifiedTypeNamesAction extends Action {

	private ITypeHierarchyViewPart fView;

	public ShowQualifiedTypeNamesAction(ITypeHierarchyViewPart v, boolean initValue) {
		super(TypeHierarchyMessages.ShowQualifiedTypeNamesAction_label);
		setDescription(TypeHierarchyMessages.ShowQualifiedTypeNamesAction_description);
		setToolTipText(TypeHierarchyMessages.ShowQualifiedTypeNamesAction_tooltip);

		X10PluginImages.setLocalImageDescriptors(this, "th_showqualified.gif"); //$NON-NLS-1$

		fView= v;
		setChecked(initValue);

//		PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IJavaHelpContextIds.SHOW_QUALIFIED_NAMES_ACTION);
	}

	/*
	 * @see Action#actionPerformed
	 */
	public void run() {
		BusyIndicator.showWhile(fView.getSite().getShell().getDisplay(), new Runnable() {
			public void run() {
				fView.showQualifiedTypeNames(isChecked());
			}
		});
	}
}
