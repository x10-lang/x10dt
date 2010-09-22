/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.editors.form_validation;

import java.io.File;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.editor.IFormPage;

import x10dt.ui.launch.cpp.LaunchMessages;

final class ValidPathControlChecker extends AbstractFormControlChecker
		implements IFormControlChecker {

	protected ValidPathControlChecker(final IFormPage formPage,
			final Control control, final String controlInfo) {
		super(formPage, control);
		this.fControlInfo = controlInfo;
	}

	// --- Interface methods implementation

	public boolean validate(final String text) {
		if (getControl().isEnabled()) {
			if (text.length() == 0) {
				addMessages(NLS.bind(LaunchMessages.ETIC_NoEmptyContent,
						this.fControlInfo), IMessageProvider.ERROR);
				return false;
			}

			else if (!validatePath(text)) {
				addMessages(NLS.bind(LaunchMessages.CLCD_PathNotFound, text),
						IMessageProvider.ERROR);
				return false;
			}
		}

		removeMessages();
		return true;
	}

	private boolean validatePath(final String text) {
		// check absolute path
		File f = new File(text);
		if(f.exists())
		{
			return true;
		}
		
		// check current working dir
		return f.exists();
	}

	// --- Overridden methods

	public boolean equals(final Object rhs) {
		if (!(rhs instanceof ValidPathControlChecker)) {
			return false;
		}
		return getControl().equals(
				((AbstractFormControlChecker) rhs).getControl());
	}

	public int hashCode() {
		return getControl().hashCode();
	}

	// --- Fields

	private final String fControlInfo;

}
