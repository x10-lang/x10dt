/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.editors;

import static x10dt.ui.launch.core.utils.PTPConstants.LOAD_LEVELER_SERVICE_PROVIDER_ID;
import static x10dt.ui.launch.core.utils.PTPConstants.MPICH2_SERVICE_PROVIDER_ID;
import static x10dt.ui.launch.core.utils.PTPConstants.OPEN_MPI_SERVICE_PROVIDER_ID;
import static x10dt.ui.launch.core.utils.PTPConstants.PAMI_SERVICE_PROVIDER_ID;
import static x10dt.ui.launch.core.utils.PTPConstants.PARALLEL_ENVIRONMENT_SERVICE_PROVIDER_ID;
import static x10dt.ui.launch.core.utils.PTPConstants.SOCKETS_SERVICE_PROVIDER_ID;
import static x10dt.ui.launch.core.utils.PTPConstants.STANDALONE_SERVICE_PROVIDER_ID;
import static x10dt.ui.launch.cpp.platform_conf.cpp_commands.DefaultCPPCommandsFactory.X10RT_PROPERTIES_FILE_FORMAT;
import static x10dt.ui.launch.cpp.platform_conf.cpp_commands.DefaultCPPCommandsFactory.SHARED_LIB_PROPERTIES_FILE;
import static x10dt.ui.launch.cpp.platform_conf.cpp_commands.DefaultCPPCommandsFactory.LIBX10_PROPERTIES_FILE;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.imp.utils.Pair;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.forms.editor.SharedHeaderFormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import x10dt.ui.launch.core.platform_conf.ETransport;
import x10dt.ui.launch.core.platform_conf.EValidationStatus;
import x10dt.ui.launch.core.utils.KeyboardUtils;
import x10dt.ui.launch.core.utils.SWTFormUtils;
import x10dt.ui.launch.cpp.LaunchMessages;
import x10dt.ui.launch.cpp.builder.target_op.ITargetOpHelper;
import x10dt.ui.launch.cpp.editors.form_validation.AbstractFormControlChecker;
import x10dt.ui.launch.cpp.editors.form_validation.IFormControlChecker;
import x10dt.ui.launch.cpp.platform_conf.ICppCompilationConf;


final class X10DistributionSectionPart extends AbstractCommonSectionFormPart 
                                       implements IConnectionTypeListener, IFormPart, IServiceProviderChangeListener {

  X10DistributionSectionPart(final Composite parent, final X10FormPage formPage) {
    super(parent, formPage);
    
    getSection().setText(LaunchMessages.XPCP_X10DistribSection);
    getSection().setDescription(LaunchMessages.XPCP_X10DistribSectionDescr);
    getSection().setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    
    createClient(formPage.getManagedForm(), formPage.getManagedForm().getToolkit());
    addCompletePartListener(getFormPage());
  }
  
  // --- IConnectionTypeListener's interface methods implementation
  
  public void connectionChanged(final boolean isLocal, final String remoteConnectionName,
                                final EValidationStatus validationStatus, final boolean shouldDeriveInfo) {
    for (final Control control : this.fControlsAffectedByLocalRM) {
      control.setEnabled(! isLocal);
    }
    if (! isLocal) {
      final String x10DistLoc = this.fX10DistLocText.getText().trim();
      getPlatformConf().setX10DistribLocation(x10DistLoc);
    }
    this.fX10DistBrowseBt.setEnabled(! isLocal && validationStatus == EValidationStatus.VALID);

    if (isLocal) {
      handleEmptyTextValidation(this.fX10DistLocText, LaunchMessages.XPCP_X10DistLabel);
    } else if (validationStatus == EValidationStatus.VALID) {
      checkX10DistFolderContent(getPlatformConf().getCommunicationInterfaceConf().getServiceTypeId(), shouldDeriveInfo);
    } else {
      handleEmptyTextValidation(this.fX10DistLocText, LaunchMessages.XPCP_X10DistLabel);
    }
    
    setPartCompleteFlag(hasCompleteInfo());
  }
  
  // --- IFormPart's interface methods implementation

  public void dispose() {
    removeCompletePartListener(getFormPage());
  }
  
  // --- IServiceProviderChangeListener's interface methods implementation
  
  public void serviceTypeChange(final String serviceTypeId) {
    checkX10DistFolderContent(serviceTypeId, true);
  }
  
  public void serviceModeChange(final String serviceModeId) {
    // Nothing to do.
  }
  
  // --- Abstract methods implementation
  
  protected void initializeControls() {
    final boolean isLocal = getPlatformConf().getConnectionConf().isLocal();
    for (final Control control : this.fControlsAffectedByLocalRM) {
      control.setEnabled(! isLocal);
    }
    
    final ICppCompilationConf cppCompConf = getPlatformConf().getCppCompilationConf();
    if (isLocal) {
      handleEmptyTextValidation(this.fX10DistLocText, LaunchMessages.XPCP_X10DistLabel);
      handleEmptyTextValidation(this.fX10DistLocText, LaunchMessages.XPCP_X10DistLabel);
    } else {
      this.fX10DistLocText.setText(cppCompConf.getX10DistribLocation(isLocal));
      handleEmptyTextValidation(this.fX10DistLocText, LaunchMessages.XPCP_X10DistLabel);
    }
  }
  
  protected void postPagesCreation() {
    setPartCompleteFlag(hasCompleteInfo());
    for (final IFormPart formPart : getFormPage().getManagedForm().getParts()) {
      if (formPart instanceof IX10DistribChangeListener) {
        this.fListeners.add((IX10DistribChangeListener) formPart);
      }
    }
  }
  
  // --- Overridden methods
  
  public boolean setFormInput(final Object input) {
    return (this.fX10DistLocText == input);
  }
  
  // --- Private code
  
  private void addListeners(final IManagedForm managedForm, final Text x10DistLocText) {
    x10DistLocText.addModifyListener(new ModifyListener() {
      public void modifyText(final ModifyEvent event) {
        if (! getPlatformConf().getConnectionConf().isLocal()) {
          handleEmptyTextValidation(x10DistLocText, LaunchMessages.XPCP_X10DistLabel);

          String text= x10DistLocText.getText().trim(); // RTC 1034

          getPlatformConf().setX10DistribLocation(text);

          setPartCompleteFlag(hasCompleteInfo());
          updateDirtyState(managedForm);
        }
      }
    });
  }
  
  private void checkX10DistFolderContent(final String serviceTypeId, final boolean shouldDeriveInfo) {
    final boolean isLocal = getPlatformConf().getConnectionConf().isLocal();

    final SharedHeaderFormEditor formEditor = (SharedHeaderFormEditor) getFormPage().getEditor();
    formEditor.getHeaderForm().getMessageManager().removeMessage(this.fX10DistLocText);
    getFormPage().getManagedForm().getMessageManager().removeMessage(this.fX10DistLocText, this.fX10DistLocText);

    final IFormControlChecker controlChecker;
    if (!isLocal && !SOCKETS_SERVICE_PROVIDER_ID.equals(serviceTypeId) &&
        !STANDALONE_SERVICE_PROVIDER_ID.equals(serviceTypeId)) {
      final String transportName;
      if (OPEN_MPI_SERVICE_PROVIDER_ID.equals(serviceTypeId) || MPICH2_SERVICE_PROVIDER_ID.equals(serviceTypeId)) {
        transportName = ETransport.MPI.name().toLowerCase();
      } else if (PARALLEL_ENVIRONMENT_SERVICE_PROVIDER_ID.equals(serviceTypeId) ||
                 LOAD_LEVELER_SERVICE_PROVIDER_ID.equals(serviceTypeId)) {
        transportName = ETransport.LAPI.name().toLowerCase();
      } else if (PAMI_SERVICE_PROVIDER_ID.equals(serviceTypeId)) {
        transportName = ETransport.PAMI.name().toLowerCase();
      } else {
        transportName = null;
      }
      if (transportName != null) {
        final String propertiesFilePath = String.format(X10RT_PROPERTIES_FILE_FORMAT, transportName);
        controlChecker = new X10DistribControlChecker(createTargetOpHelper(), getFormPage(), this.fX10DistLocText,
                                                      SHARED_LIB_PROPERTIES_FILE, LIBX10_PROPERTIES_FILE, propertiesFilePath);
      } else {
        controlChecker = new X10DistribControlChecker(createTargetOpHelper(), getFormPage(), this.fX10DistLocText, 
                                                      SHARED_LIB_PROPERTIES_FILE, LIBX10_PROPERTIES_FILE);
      }
    } else if (!isLocal) {
      controlChecker = new X10DistribControlChecker(createTargetOpHelper(), getFormPage(), this.fX10DistLocText, 
                                                    SHARED_LIB_PROPERTIES_FILE, LIBX10_PROPERTIES_FILE);
    } else {
      controlChecker = null;
      this.fIsX10DistValid = true;
    }

    if (controlChecker != null) {
      this.fIsX10DistValid = controlChecker.validate(this.fX10DistLocText.getText().trim());
      getPlatformConf().setX10DistribLocation(this.fX10DistLocText.getText().trim());
      for (final IX10DistribChangeListener listener : this.fListeners) {
        listener.x10DistributionChange(this.fIsX10DistValid, shouldDeriveInfo);
      }

      setPartCompleteFlag(hasCompleteInfo());
    }
  }

  
  private void createClient(final IManagedForm managedForm, final FormToolkit toolkit) {
    this.fControlsAffectedByLocalRM = new ArrayList<Control>();
    this.fControlsAffectedByLocalRM.add(getSection());
    
    final Composite sectionClient = toolkit.createComposite(getSection());
    sectionClient.setLayout(new TableWrapLayout());
    sectionClient.setFont(getSection().getFont());
    sectionClient.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    
    final Pair<Text, Button> pair = SWTFormUtils.createLabelTextButton(sectionClient, LaunchMessages.XPCP_X10DistLabel, 
                                                                        LaunchMessages.XPCP_BrowseBt, toolkit, 
                                                                        this.fControlsAffectedByLocalRM, 3);
    this.fX10DistLocText = pair.first;
    this.fX10DistBrowseBt = pair.second;
    this.fX10DistBrowseBt.addSelectionListener(new X10DistribBrowseSelectionListener(this.fX10DistLocText));
    
    initializeControls();
    
    addListeners(managedForm, this.fX10DistLocText);
    
    KeyboardUtils.addDelayedActionOnControl(this.fX10DistLocText, new Runnable() {
      
      public void run() {
        getFormPage().getEditorSite().getShell().getDisplay().asyncExec(new Runnable() {
          
          public void run() {
            checkX10DistFolderContent(getPlatformConf().getCommunicationInterfaceConf().getServiceTypeId(), true);
          }
          
        });
      }
      
    });

    getSection().setClient(sectionClient);
  }
  
  private boolean hasCompleteInfo() {
    final boolean isLocal = getPlatformConf().getConnectionConf().isLocal();
    return isLocal || ((this.fX10DistLocText.getText().trim().length() > 0) && this.fIsX10DistValid);
  }
  
  // --- Private classes
  
  private final class X10DistribControlChecker extends AbstractFormControlChecker implements IFormControlChecker {
    
    X10DistribControlChecker(final ITargetOpHelper targetOpHelper, final IFormPage formPage, final Control control,
                             final String ... propertiesFiles) {
      super(formPage, control);
      this.fTargetOpHelper = targetOpHelper;
      this.fPropertiesFiles = propertiesFiles;
    }
    
    // --- Interface methods implementation
    
    public boolean validate(final String path) {
      removeMessages();
      if (path.length() == 0) {
        addMessages(NLS.bind(LaunchMessages.ETIC_NoEmptyContent, LaunchMessages.XPCP_X10DistLabel));
        return false;
      } else if (! checkPathExistence(path)) {
        addMessages(NLS.bind(LaunchMessages.LPCC_NonExistentPath, LaunchMessages.XPCP_X10DistLabel));
        return false;
      } else if (! checkPathExistence(String.format(PATH_FORMAT, path, INCLUDE_X10RT_PATH))) {
        addMessages(NLS.bind(LaunchMessages.LPCC_NonExistentPath, LaunchMessages.XPCP_X10DistRootIncludeLabel));
        return false;
      } else {
        for (final String propertiesFile : this.fPropertiesFiles) {
          if (! checkPathExistence(String.format(PATH_FORMAT, path, propertiesFile))) {
            addMessages(NLS.bind(LaunchMessages.LPCC_NonExistentPath, 
                                 NLS.bind(LaunchMessages.XDSP_X10DistPropFileMissing, propertiesFile)));
            return false;
          }
        }
      }
      return true;
    }
    
    // --- Private code
    
    private void addMessages(final String message) {
      getPlatformConf().setCompilationCommandsStatus(EValidationStatus.FAILURE);
      getPlatformConf().setCompilationCommandsErrorMessage(message);
      super.addMessages(message, IMessageProvider.ERROR);
    }
    
    private boolean checkPathExistence(final String path) {
      return (this.fTargetOpHelper == null) ? false : this.fTargetOpHelper.getStore(path).fetchInfo().exists();
    }
    
    // --- Fields

    private final ITargetOpHelper fTargetOpHelper;
    
    private final String[] fPropertiesFiles;
    
  }
  
  private final class X10DistribBrowseSelectionListener extends DirectoryDialogSelectionListener {

    X10DistribBrowseSelectionListener(final Text text) {
      super(text);
    }
    
    // --- Overridden methods
    
    protected void updateText(final String path) {
      super.updateText(path);
      checkX10DistFolderContent(getPlatformConf().getCommunicationInterfaceConf().getServiceTypeId(), true);
    }
    
  }

  // --- Fields

  private Text fX10DistLocText;
  
  private Button fX10DistBrowseBt;
  
  private Collection<Control> fControlsAffectedByLocalRM;
  
  private boolean fIsX10DistValid;
  
  private final Collection<IX10DistribChangeListener> fListeners = new ArrayList<IX10DistribChangeListener>();
  
  
  private static final String INCLUDE_X10RT_PATH = "stdlib/include/x10rt.h"; //$NON-NLS-1$
  
  private static final String PATH_FORMAT = "%s/%s"; //$NON-NLS-1$

}
