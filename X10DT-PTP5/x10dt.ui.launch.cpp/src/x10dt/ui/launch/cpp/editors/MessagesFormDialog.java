/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.editors;

import static org.eclipse.ui.forms.widgets.ExpandableComposite.CLIENT_INDENT;
import static org.eclipse.ui.forms.widgets.ExpandableComposite.TREE_NODE;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.imp.utils.Pair;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.FormDialog;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.IMessage;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import x10dt.ui.launch.core.utils.StringUtils;
import x10dt.ui.launch.cpp.CppLaunchImages;
import x10dt.ui.launch.cpp.LaunchMessages;


final class MessagesFormDialog extends FormDialog {

  MessagesFormDialog(final Shell shell, final IMessage[] messages, final FormEditor formEditor) {
    super(shell);
    
    this.fValidationErrorMessages = new ArrayList<IMessage>();
    this.fInputErrorMessages = new ArrayList<IMessage>();
    for (final IMessage message : messages) {
      if (message.getKey() instanceof Control) {
        this.fInputErrorMessages.add(message);
      } else {
        this.fValidationErrorMessages.add(message);
      }
    }
    
    this.fFormEditor = formEditor;
  }
  
  // --- Overridden methods
  
  protected void configureShell(final Shell shell) {
    super.configureShell(shell);
    shell.setText(LaunchMessages.MFD_MessagesFormDialogTitle);
  }
  
  protected void createButtonsForButtonBar(final Composite parent) {
    createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
  }
  
  protected void createFormContent(final IManagedForm managedForm) {
    final Composite body = managedForm.getForm().getBody();
    final TableWrapLayout mainLayout = new TableWrapLayout();
    mainLayout.leftMargin = 15;
    mainLayout.rightMargin = 15;
    mainLayout.topMargin = 15;
    mainLayout.bottomMargin = 15;
    body.setLayout(mainLayout);

    managedForm.getForm().setBackground(getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
    managedForm.getToolkit().setBackground(getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
    
    if (! this.fValidationErrorMessages.isEmpty()) {
      createValidationErrorsContent(managedForm.getToolkit(), managedForm, body);
    }
    if (! this.fInputErrorMessages.isEmpty()) {
      createInputErrorsContent(managedForm.getToolkit(), managedForm, body);
    }
  }

  // --- Private code
  
  private void createInputErrorsContent(final FormToolkit toolkit, final IManagedForm managedForm, final Composite parent) {
    final Label label = toolkit.createLabel(parent, LaunchMessages.MFD_FormInputErrors);
    label.setFont(JFaceResources.getHeaderFont());
    label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
    label.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    
    final Composite composite = toolkit.createComposite(parent);
    composite.setFont(parent.getFont());
    final TableWrapLayout compoLayout = new TableWrapLayout();
    compoLayout.leftMargin = 15;
    composite.setLayout(compoLayout);
    composite.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    
    
    for (final IMessage message : this.fInputErrorMessages) {
      final ImageHyperlink imageLink = new ImageHyperlink(composite, SWT.NONE);
      imageLink.setBackground(managedForm.getForm().getBackground());
      imageLink.setText(message.getMessage());
      imageLink.setImage(CppLaunchImages.getImage(CppLaunchImages.INVALID_CONNECTION));
      imageLink.addHyperlinkListener(new HyperlinkAdapter() {
        public void linkActivated(final HyperlinkEvent event) {
          final Control control = (Control) message.getKey();
          final IFormPage formPage = MessagesFormDialog.this.fFormEditor.selectReveal(control);
          if (formPage != null) {
            okPressed();
            MessagesFormDialog.this.fFormEditor.setActivePage(formPage.getId());
            control.setFocus();
          }
        }
      });
      
      toolkit.getHyperlinkGroup().add(imageLink);
    }
  }
  
  private void createValidationErrorsContent(final FormToolkit toolkit, final IManagedForm managedForm, 
                                             final Composite parent) {
    final Label label = toolkit.createLabel(parent, LaunchMessages.MFD_ValidationErrors);
    label.setFont(JFaceResources.getHeaderFont());
    label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
    label.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    
    for (final IMessage message : this.fValidationErrorMessages) {
      final ExpandableComposite expandableCompo = toolkit.createExpandableComposite(parent, TREE_NODE | CLIENT_INDENT);
      expandableCompo.setText(message.getMessage());
      
      final Composite composite = toolkit.createComposite(expandableCompo);
      composite.setFont(parent.getFont());
      composite.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
      composite.setLayout(new TableWrapLayout());
      
      if (message.getData() instanceof Exception) {
        final Exception exception = (Exception) message.getData();
        toolkit.createText(composite, StringUtils.getStackTrace(exception), SWT.READ_ONLY);
      } else {
        @SuppressWarnings("unchecked")
        final Pair<String,String> error = (Pair<String,String>) message.getData();
        toolkit.createLabel(expandableCompo, NLS.bind(LaunchMessages.MFD_CommandPrefix, error.first), SWT.WRAP);
        toolkit.createText(composite, NLS.bind(LaunchMessages.MFD_ErrorPrefix, error.second), SWT.READ_ONLY);
      }
      
      expandableCompo.setClient(composite);

      final TableWrapData twData = new TableWrapData();
      twData.indent = 15;
      expandableCompo.setLayoutData(twData);
      expandableCompo.addExpansionListener(new ExpansionAdapter() {
        public void expansionStateChanged(final ExpansionEvent event) {
          managedForm.reflow(true);
        }
      });
    }
  }
  
  // --- Fields
  
  private final Collection<IMessage> fValidationErrorMessages;
  
  private final Collection<IMessage> fInputErrorMessages;
  
  private final FormEditor fFormEditor;

}
