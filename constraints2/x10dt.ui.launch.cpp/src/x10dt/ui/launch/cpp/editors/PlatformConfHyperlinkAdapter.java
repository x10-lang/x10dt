/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.editors;

import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.IMessage;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;


final class PlatformConfHyperlinkAdapter implements IHyperlinkListener {
  
  PlatformConfHyperlinkAdapter(final X10PlatformConfFormEditor formEditor) {
    this.fFormEditor = formEditor;
  }
  
  // --- Interface methods implementation

  public void linkEntered(final HyperlinkEvent event) {
  }

  public void linkExited(final HyperlinkEvent event) {
  }

  public void linkActivated(final HyperlinkEvent event) {
    final IMessage[] messages = (IMessage[]) event.data;
    if ((messages.length == 1)  && (messages[0].getKey() instanceof Control)) {
      final Control control = (Control) messages[0].getKey();
      final IFormPage formPage = this.fFormEditor.selectReveal(control);
      if (formPage != null) {
        this.fFormEditor.setActivePage(formPage.getId());
        control.setFocus();
      }
    } else {
      final MessagesFormDialog dialog = new MessagesFormDialog(this.fFormEditor.getSite().getShell(), 
                                                               messages, this.fFormEditor);
      dialog.create();
      dialog.getShell().setSize(500, 500);
      dialog.open();
    }
  }
  
  // --- Fields
  
  private final X10PlatformConfFormEditor fFormEditor;

}
