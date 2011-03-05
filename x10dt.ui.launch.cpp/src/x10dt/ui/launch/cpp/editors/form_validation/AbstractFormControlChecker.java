/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.editors.form_validation;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.forms.editor.SharedHeaderFormEditor;


abstract class AbstractFormControlChecker {
  
  // --- Code for descendants
  
  protected final void addMessages(final String messageText, final int type) {
	  Display.getDefault().asyncExec(new Runnable() {
		  public void run() {
			  fHeaderMMgr.addMessage(fControl, messageText, null /* data */, type);
			  fPageMMgr.addMessage(fControl, messageText, null /* data */, type, fControl);
		  }
	  });
  }
  
  protected final Control getControl() {
    return this.fControl;
  }
  
  protected final void removeMessages() {
    this.fHeaderMMgr.removeMessage(this.fControl);
    this.fPageMMgr.removeMessage(this.fControl, this.fControl);
  }
  
  // --- Private code
  
  protected AbstractFormControlChecker(final IFormPage formPage, final Control control) {
    this.fHeaderMMgr = ((SharedHeaderFormEditor) formPage.getEditor()).getHeaderForm().getMessageManager();
    this.fPageMMgr = formPage.getManagedForm().getMessageManager();
    this.fControl = control;
  }
  
  // --- Fields
  
  private final IMessageManager fHeaderMMgr;
  
  private final IMessageManager fPageMMgr;
  
  private final Control fControl;
}
