/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.editors.form_validation;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.ui.forms.editor.IFormPage;


final class EmptyComboChecker extends AbstractFormControlChecker implements IFormControlChecker {

  protected EmptyComboChecker(final IFormPage formPage, final Combo combo, final String controlInfo) {
    super(formPage, combo);
    this.fControlInfo = controlInfo;
  }

  // --- Interface methods implementation
  
  public boolean validate(final String text) {
    if (getControl().isEnabled() && ((Combo) getControl()).getSelectionIndex() == -1) {
      addMessages(this.fControlInfo, IMessageProvider.ERROR);
      return false;
    } else {
      removeMessages();
      return true;
    }
  }
  
  // --- Overridden methods
  
  public boolean equals(final Object rhs) {
    if (! (rhs instanceof EmptyComboChecker)) {
      return false;
    }
    return getControl().equals(((AbstractFormControlChecker) rhs).getControl());
  }
  
  public int hashCode() {
    return getControl().hashCode();
  }
  
  // --- Fields
  
  private final String fControlInfo;

}
