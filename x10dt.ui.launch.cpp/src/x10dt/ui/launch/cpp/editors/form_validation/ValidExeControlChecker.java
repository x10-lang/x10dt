/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.editors.form_validation;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.editor.IFormPage;

import x10dt.ui.launch.cpp.LaunchMessages;

final class ValidExeControlChecker extends AbstractFormControlChecker implements
		IFormControlChecker {

	protected ValidExeControlChecker(final IFormPage formPage,
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

			else if (!validateExe(text)) {
				addMessages(NLS.bind(LaunchMessages.CLCD_NotFound, text),
						IMessageProvider.ERROR);
				return false;
			}
		}

		removeMessages();
		return true;
	}

	private boolean validateExe(final String text) {
		try {
			Process p = Runtime.getRuntime().exec(text);
			p.waitFor();
			return true;

		} catch (Exception e) {
			// fall through
		}

		return false;
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
