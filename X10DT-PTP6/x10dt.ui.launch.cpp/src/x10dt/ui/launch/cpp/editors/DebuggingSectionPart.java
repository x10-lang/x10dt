/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.editors;

import org.eclipse.imp.utils.Pair;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.SharedHeaderFormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import x10dt.ui.launch.core.platform_conf.EValidationStatus;
import x10dt.ui.launch.core.utils.KeyboardUtils;
import x10dt.ui.launch.core.utils.SWTFormUtils;
import x10dt.ui.launch.cpp.LaunchMessages;
import x10dt.ui.launch.cpp.platform_conf.IDebuggingInfoConf;


final class DebuggingSectionPart extends AbstractCommonSectionFormPart implements IConnectionTypeListener, IFormPart {

  DebuggingSectionPart(final Composite parent, final X10FormPage formPage) {
    super(parent, formPage);
    
    getSection().setFont(parent.getFont());
    getSection().setText(LaunchMessages.DSP_DebuggingSectionName);
    getSection().setDescription(LaunchMessages.DSP_DebuggingSectionDescr);
    getSection().setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    
    createClient(formPage.getManagedForm(), formPage.getManagedForm().getToolkit());
    addCompletePartListener(getFormPage());
  }
  
  // --- IConnectionTypeListener's interface methods implementation
  
  public void connectionChanged(final boolean isLocal, final String remoteConnectionName,
                                final EValidationStatus validationStatus, final boolean shouldDeriveInfo) {
    boolean enable= shouldEnableDebuggerFolderWidgets(isLocal, validationStatus);
    this.fBrowseBt.setEnabled(enable);
    this.fDebuggerFolderText.setEnabled(enable);
  }

  private boolean shouldEnableDebuggerFolderWidgets(final boolean isLocal, final EValidationStatus validationStatus) {
    return isLocal || validationStatus == EValidationStatus.VALID;
  }
  
  // --- IFormPart's interface methods implementation

  public void dispose() {
    removeCompletePartListener(getFormPage());
  }
  
  // --- Abstract methods implementation
  
  protected void initializeControls() {
    final IDebuggingInfoConf debuggingInfoConf = getPlatformConf().getDebuggingInfoConf();
    this.fDebuggerFolderText.setText(debuggingInfoConf.getDebuggerFolder());
    this.fPortSpinner.setSelection(debuggingInfoConf.getPort());
    boolean enable = shouldEnableDebuggerFolderWidgets(getPlatformConf().getConnectionConf().isLocal(), EValidationStatus.VALID);
    this.fBrowseBt.setEnabled(enable);
    this.fDebuggerFolderText.setEnabled(enable);
    
    handleFolderAndChildValidation(this.fDebuggerFolderText, GDIA_FILE, LaunchMessages.DSP_DebuggerFolder, 
                                   LaunchMessages.DSP_GDIAFileMsg, false /* isEmptyPathAnError */);
  }
  
  protected void postPagesCreation() {
    setPartCompleteFlag(hasCompleteInfo());
  }
  
  // --- Overridden methods
  
  public boolean setFormInput(final Object input) {
    return (input == this.fDebuggerFolderText);
  }
  
  // --- Private code
  
  private void addListeners(final IManagedForm managedForm, final Text debuggerFolderText, final Spinner portSpinner) {
    debuggerFolderText.addModifyListener(new ModifyListener() {
      
      public void modifyText(final ModifyEvent event) {
        getPlatformConf().setDebuggerFolder(debuggerFolderText.getText().trim());
        
        updateDirtyState(managedForm);
        setPartCompleteFlag(hasCompleteInfo());
      }
      
    });
    portSpinner.addModifyListener(new ModifyListener() {
      
      public void modifyText(final ModifyEvent event) {
        getPlatformConf().setDebuggingPort(portSpinner.getSelection());
        
        updateDirtyState(managedForm);
        setPartCompleteFlag(hasCompleteInfo());
      }
      
    });
  }
  
  private void createClient(final IManagedForm managedForm, final FormToolkit toolkit) {
    final Composite sectionClient = toolkit.createComposite(getSection());
    sectionClient.setLayout(new TableWrapLayout());
    sectionClient.setFont(getSection().getFont());
    sectionClient.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));

    final Pair<Text, Button> pair = SWTFormUtils.createLabelTextButton(sectionClient, LaunchMessages.DSP_DebuggerFolder, 
                                                                       LaunchMessages.XPCP_BrowseBt, toolkit, null, 3);
    this.fDebuggerFolderText = pair.first;
    this.fBrowseBt = pair.second;
    this.fBrowseBt.addSelectionListener(new DebuggingSectionBrowseSelectionListener(this.fDebuggerFolderText));
    
    final Composite twoColsCompo = toolkit.createComposite(sectionClient);
    final TableWrapLayout twoColsLayout = new TableWrapLayout();
    twoColsLayout.numColumns = 2;
    twoColsCompo.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    twoColsCompo.setLayout(twoColsLayout);
    twoColsCompo.setFont(sectionClient.getFont());
    
    final Label portLabel = toolkit.createLabel(twoColsCompo, LaunchMessages.DSP_DebuggingPort);
    portLabel.setLayoutData(new TableWrapData(TableWrapData.FILL, TableWrapData.MIDDLE));
    
    this.fPortSpinner = new Spinner(twoColsCompo, SWT.SINGLE | SWT.BORDER);
    this.fPortSpinner.setLayoutData(new TableWrapData(TableWrapData.FILL, TableWrapData.MIDDLE));
    this.fPortSpinner.setMinimum(1);
    this.fPortSpinner.setMaximum(65535);
    this.fPortSpinner.setTextLimit(5);
        
    initializeControls();
    
    final Text debuggerText = this.fDebuggerFolderText;
    KeyboardUtils.addDelayedActionOnControl(this.fDebuggerFolderText, new Runnable() {

      public void run() {
        getFormPage().getEditorSite().getShell().getDisplay().asyncExec(new Runnable() {

          public void run() {
            // If the field is non-empty, check that the specified folder doesn't exist.
            // We only do the check when the field is non-empty, so that users that don't
            // want to use the debugger aren't forced into supplying this info.
            if (debuggerText.getText().length() > 0) {
              handleFolderAndChildValidation(debuggerText, GDIA_FILE, LaunchMessages.DSP_DebuggerFolder, 
                                             LaunchMessages.DSP_GDIAFileMsg, false /* isEmptyPathAnError */);
            } else {
              final SharedHeaderFormEditor formEditor = (SharedHeaderFormEditor) getFormPage().getEditor();
              formEditor.getHeaderForm().getMessageManager().removeMessage(debuggerText);
              getFormPage().getManagedForm().getMessageManager().removeMessage(debuggerText, debuggerText);
            }
          }

        });
      }

    });
    
    addListeners(managedForm, this.fDebuggerFolderText, this.fPortSpinner);
     
    getSection().setClient(sectionClient);
  }
  
  private boolean hasCompleteInfo() {
    return true;
  }
  
  // --- Private classes
  
  private class DebuggingSectionBrowseSelectionListener extends DirectoryDialogSelectionListener {

    DebuggingSectionBrowseSelectionListener(final Text text) {
      super(text);
    }
    
    // --- Overridden methods
    
    protected void updateText(final String path) {
      super.updateText(path);
      handleFolderAndChildValidation(getText(), GDIA_FILE, LaunchMessages.DSP_DebuggerFolder, LaunchMessages.DSP_GDIAFileMsg,
                                     false /* isEmptyPathAnError */);
    }
    
  }
  
  // --- Fields
  
  private Text fDebuggerFolderText;
  
  private Button fBrowseBt;
  
  private Spinner fPortSpinner;

  
  private static final String GDIA_FILE = "gdia"; //$NON-NLS-1$

}
