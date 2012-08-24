/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.editors;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.imp.utils.Pair;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.SharedHeaderFormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.platform_conf.EArchitecture;
import x10dt.ui.launch.core.platform_conf.EBitsArchitecture;
import x10dt.ui.launch.core.platform_conf.ETargetOS;
import x10dt.ui.launch.core.platform_conf.EValidationStatus;
import x10dt.ui.launch.core.utils.KeyboardUtils;
import x10dt.ui.launch.core.utils.SWTFormUtils;
import x10dt.ui.launch.cpp.LaunchMessages;
import x10dt.ui.launch.cpp.builder.target_op.ITargetOpHelper;
import x10dt.ui.launch.cpp.builder.target_op.TargetOpHelperFactory;
import x10dt.ui.launch.cpp.editors.form_validation.FormCheckerFactory;
import x10dt.ui.launch.cpp.editors.form_validation.IFormControlChecker;
import x10dt.ui.launch.cpp.platform_conf.IConnectionConf;
import x10dt.ui.launch.cpp.platform_conf.ICppCompilationConf;
import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConf;
import x10dt.ui.launch.cpp.platform_conf.cpp_commands.DefaultCPPCommandsFactory;
import x10dt.ui.launch.cpp.platform_conf.cpp_commands.IDefaultCPPCommands;
import x10dt.ui.launch.cpp.utils.PlatformConfUtils;


final class CompilationAndLinkingSectionPart extends AbstractCommonSectionFormPart 
                                             implements IConnectionTypeListener, IFormPart, IX10DistribChangeListener {

  CompilationAndLinkingSectionPart(final Composite parent, final X10FormPage formPage) {
    super(parent, formPage);
    
    getSection().setFont(parent.getFont());
    getSection().setText(LaunchMessages.XPCP_CompilationLinkingSection);
    getSection().setDescription(LaunchMessages.XPCP_CompilationLinkingSectionDescr);
    final TableWrapData twData = new TableWrapData(TableWrapData.FILL_GRAB);
    twData.rowspan = 3;
    getSection().setLayoutData(twData);
    
    createClient(formPage.getManagedForm(), formPage.getManagedForm().getToolkit());
    addCompletePartListener(formPage);
  }

  
  // --- IConnectionTypeListener's interface methods implementation
  
  public void connectionChanged(final boolean isLocal, final String remoteConnectionName, 
                                final EValidationStatus validationStatus, final boolean shouldDeriveInfo) {
    final boolean shouldEnable = isLocal || validationStatus == EValidationStatus.VALID;
    this.fCompilerBrowseBt.setEnabled(shouldEnable);
    this.fArchiverBrowseBt.setEnabled(shouldEnable);
    this.fLinkerBrowseBt.setEnabled(shouldEnable);
    if (isLocal && shouldDeriveInfo) {
      if (! this.fOSCombo.isEnabled()) {
        for (final Control control : this.fControlsAffectedByCIType) {
          control.setEnabled(true);
        }
      }
      final SharedHeaderFormEditor formEditor = (SharedHeaderFormEditor) getFormPage().getEditor();
      formEditor.getHeaderForm().getMessageManager().removeMessage(this.fOSCombo);
      try {
        selectOsAndArchitecture();
        checkCompilerVersion(this.fCompilerText, this.fOSCombo, this.fArchCombo);
        getPlatformConf().setCompilationCommandsStatus(EValidationStatus.UNKNOWN);
        getPlatformConf().setCompilationCommandsErrorMessage(Constants.EMPTY_STR);
      } catch (Exception except) {
        getPlatformConf().setCompilationCommandsStatus(EValidationStatus.FAILURE);
        getPlatformConf().setCompilationCommandsErrorMessage(except.getMessage());
        formEditor.getHeaderForm().getMessageManager().addMessage(this.fOSCombo, except.getMessage(), null /* data */, 
                                                                  IMessageProvider.ERROR);
        for (final Control control : this.fControlsAffectedByCIType) {
          control.setEnabled(false);
        }
      }
    }
  }
  
  // --- IFormPart's interface methods implementation

  public void dispose() {
    removeCompletePartListener(getFormPage());
  }
  
  // --- IX10DistribChangeListener's interface methods implementation
  
  public void x10DistributionChange(final boolean isValid, final boolean shouldDeriveInfo) {
    final boolean enable = isValid;
    if (this.fOSCombo.isEnabled() != enable) {
      for (final Control control : this.fControlsAffectedByCIType) {
        control.setEnabled(enable);
      }
    }
    final SharedHeaderFormEditor formEditor = (SharedHeaderFormEditor) getFormPage().getEditor();
    formEditor.getHeaderForm().getMessageManager().removeMessage(this.fOSCombo);
    
    if (isValid) {
      try {
        if (shouldDeriveInfo) {
          selectOsAndArchitecture();
        }
        checkCompilerVersion(this.fCompilerText, this.fOSCombo, this.fArchCombo);
        getPlatformConf().setCompilationCommandsStatus(EValidationStatus.UNKNOWN);
        getPlatformConf().setCompilationCommandsErrorMessage(Constants.EMPTY_STR);
      } catch (Exception except) {
        getPlatformConf().setCompilationCommandsStatus(EValidationStatus.FAILURE);
        getPlatformConf().setCompilationCommandsErrorMessage(except.getMessage());
        formEditor.getHeaderForm().getMessageManager().addMessage(this.fOSCombo, except.getMessage(), null /* data */, 
                                                                  IMessageProvider.ERROR);
        
        for (final Control control : this.fControlsAffectedByCIType) {
          control.setEnabled(false);
        }
      }
    } else {
      handleEmptyTextValidation(this.fCompilerText, LaunchMessages.XPCP_CompilerLabel);
      handleEmptyTextValidation(this.fCompilingOptsText, LaunchMessages.XPCP_CompilingOptsLabel);
      handleEmptyTextValidation(this.fArchiverText, LaunchMessages.XPCP_ArchiverLabel);
      handleEmptyTextValidation(this.fArchivingOptsText, LaunchMessages.XPCP_ArchivingOptsLabel);
      handleEmptyTextValidation(this.fLinkerText, LaunchMessages.XPCP_LinkerLabel);
      handleEmptyTextValidation(this.fLinkingOptsText, LaunchMessages.XPCP_LinkingOptsLabel);
      handleEmptyTextValidation(this.fLinkingLibsText, LaunchMessages.XPCP_LinkingLibsLabel);
    }
  }
  
  // --- Abstract methods implementation
  
  protected void initializeControls() {
    final ICppCompilationConf cppCompConf = getPlatformConf().getCppCompilationConf();
    int index = -1;
    for (final ETargetOS targetOS : ETargetOS.values()) {
      ++index;
      if (targetOS == cppCompConf.getTargetOS()) {
        this.fOSCombo.select(index);
      }
    }
    index = -1;
    for (final EArchitecture arch : EArchitecture.values()) {
      ++index;
      if (arch == cppCompConf.getArchitecture()) {
        this.fArchCombo.select(index);
      }
    }
    
    this.fCompilerText.setText(cppCompConf.getCompiler());
    handleEmptyTextValidation(this.fCompilerText, LaunchMessages.XPCP_CompilerLabel);
    
    this.fCompilingOptsText.setText(cppCompConf.getCompilingOpts());
    handleEmptyTextValidation(this.fCompilingOptsText, LaunchMessages.XPCP_CompilingOptsLabel);
    this.fArchiverText.setText(cppCompConf.getArchiver());
    handleEmptyTextValidation(this.fArchiverText, LaunchMessages.XPCP_ArchiverLabel);
    this.fArchivingOptsText.setText(cppCompConf.getArchivingOpts());
    handleEmptyTextValidation(this.fArchivingOptsText, LaunchMessages.XPCP_ArchivingOptsLabel);
    this.fLinkerText.setText(cppCompConf.getLinker());
    handleEmptyTextValidation(this.fLinkerText, LaunchMessages.XPCP_LinkerLabel);
    this.fLinkingOptsText.setText(cppCompConf.getLinkingOpts());
    handleEmptyTextValidation(this.fLinkingOptsText, LaunchMessages.XPCP_LinkingOptsLabel);
    this.fLinkingLibsText.setText(cppCompConf.getLinkingLibs());
    handleEmptyTextValidation(this.fLinkingLibsText, LaunchMessages.XPCP_LinkingLibsLabel);

    this.fBitsArchBt.setSelection(cppCompConf.getBitsArchitecture() == EBitsArchitecture.E64Arch);
  }
  
  protected void postPagesCreation() {
    checkCompilerVersion(this.fCompilerText, this.fOSCombo, this.fArchCombo);
    setPartCompleteFlag(hasCompleteInfo());
  }
  
  // --- Overridden methods
  
  public boolean setFormInput(final Object input) {
    return (input == this.fCompilerText) || (input == this.fCompilingOptsText) || (input == this.fArchiverText) ||
           (input == this.fArchivingOptsText) || (input == this.fLinkerText) || (input == this.fLinkingOptsText) ||
           (input == this.fLinkingLibsText);
  }
  
  // --- Private code
  
  private void addListeners(final IManagedForm managedForm, final Text compilerText, final Text compilingOptsText,
                            final Text archiverText, final Text archivingOptsText, final Text linkerText, 
                            final Text linkingOptsText, final Text linkingLibsText, final Button bitsArchBt,
                            final Combo osCombo, final Combo archCombo) {
		compilerText.addModifyListener(new ModifyListener() {
			public void modifyText(final ModifyEvent event) {
			  handleEmptyTextValidation(compilerText, LaunchMessages.XPCP_CompilerLabel);

				getPlatformConf().setCompiler(compilerText.getText());
				setPartCompleteFlag(hasCompleteInfo());
				updateDirtyState(managedForm);
			}
		});
    compilingOptsText.addModifyListener(new ModifyListener() {
      
      public void modifyText(final ModifyEvent event) {
        handleEmptyTextValidation(compilingOptsText, LaunchMessages.XPCP_CompilingOptsLabel);
        getPlatformConf().setCompilingOpts(compilingOptsText.getText());
        setPartCompleteFlag(hasCompleteInfo());
        updateDirtyState(managedForm);
      }
      
    });
    archiverText.addModifyListener(new ModifyListener() {
      
      public void modifyText(final ModifyEvent event) {
        handleEmptyTextValidation(archiverText, LaunchMessages.XPCP_ArchiverLabel);
        getPlatformConf().setArchiver(archiverText.getText());
        setPartCompleteFlag(hasCompleteInfo());
        updateDirtyState(managedForm);
      }
      
    });
    archivingOptsText.addModifyListener(new ModifyListener() {
      
      public void modifyText(final ModifyEvent event) {
        handleEmptyTextValidation(archivingOptsText, LaunchMessages.XPCP_ArchivingOptsLabel);
        getPlatformConf().setArchivingOpts(archivingOptsText.getText());
        setPartCompleteFlag(hasCompleteInfo());
        updateDirtyState(managedForm);
      }
      
    });
    linkerText.addModifyListener(new ModifyListener() {
      
      public void modifyText(final ModifyEvent event) {
        handleEmptyTextValidation(linkerText, LaunchMessages.XPCP_LinkerLabel);
        getPlatformConf().setLinker(linkerText.getText());
        setPartCompleteFlag(hasCompleteInfo());
        updateDirtyState(managedForm);
      }
      
    });
    linkingOptsText.addModifyListener(new ModifyListener() {
      
      public void modifyText(final ModifyEvent event) {
        handleEmptyTextValidation(linkingOptsText, LaunchMessages.XPCP_LinkingOptsLabel);
        getPlatformConf().setLinkingOpts(linkingOptsText.getText());
        setPartCompleteFlag(hasCompleteInfo());
        updateDirtyState(managedForm);
      }
      
    });
    linkingLibsText.addModifyListener(new ModifyListener() {
      
      public void modifyText(final ModifyEvent event) {
        handleEmptyTextValidation(linkingLibsText, LaunchMessages.XPCP_LinkingLibsLabel);
        getPlatformConf().setLinkingLibs(linkingLibsText.getText());
        setPartCompleteFlag(hasCompleteInfo());
        updateDirtyState(managedForm);
      }
      
    });
    bitsArchBt.addSelectionListener(new SelectionListener() {
      
      public void widgetSelected(final SelectionEvent event) {
        getPlatformConf().setBitsArchitecture(bitsArchBt.getSelection() ? EBitsArchitecture.E64Arch : 
                                                                          EBitsArchitecture.E32Arch);        
        try {
          updateCompilationCommands(compilerText, compilingOptsText, archiverText, archivingOptsText, linkerText,
                                    linkingOptsText, linkingLibsText);
        } catch (Exception except) {
          
        }
        
        updateDirtyState(managedForm);
      }
      
      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }
      
    });
    osCombo.addSelectionListener(new SelectionListener() {
      
      public void widgetSelected(final SelectionEvent event) {
        final String osName = osCombo.getItem(osCombo.getSelectionIndex());
        final ETargetOS targetOs = (ETargetOS) osCombo.getData(osName);
        getPlatformConf().setTargetOS(targetOs);
        
        try {
          updateCompilationCommands(compilerText, compilingOptsText, archiverText, archivingOptsText, linkerText,
                                    linkingOptsText, linkingLibsText);
        } catch (Exception except) {
          
        }
        updateDirtyState(managedForm);
      }
      
      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }
      
    });
    archCombo.addSelectionListener(new SelectionListener() {
      
      public void widgetSelected(final SelectionEvent event) {
        final String archName = archCombo.getItem(archCombo.getSelectionIndex());
        final EArchitecture architecture = (EArchitecture) archCombo.getData(archName);
        getPlatformConf().setArchitecture(architecture);
        
        try {
          updateCompilationCommands(compilerText, compilingOptsText, archiverText, archivingOptsText, linkerText,
                                    linkingOptsText, linkingLibsText);
        } catch (Exception except) {
          
        }
        updateDirtyState(managedForm);
      }
      
      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }
      
    });
  }
  
  private void checkCompilerVersion(final Text compilerText, final Combo osCombo, final Combo archCombo) {
    final IX10PlatformConf platformConf = getPlatformConf();
    final ICppCompilationConf cppCompConf = platformConf.getCppCompilationConf();
    final IConnectionConf connConf = platformConf.getConnectionConf();
    final ITargetOpHelper targetOpHelper = TargetOpHelperFactory.create(connConf.isLocal(), 
                                                                        cppCompConf.getTargetOS() ==  ETargetOS.WINDOWS, 
                                                                        connConf.getConnectionName());
    if (targetOpHelper != null && osCombo.getSelectionIndex() != -1) {
      final String osName = osCombo.getItem(osCombo.getSelectionIndex());
      final ETargetOS targetOs = (ETargetOS) osCombo.getData(osName);
      final String archName = archCombo.getItem(archCombo.getSelectionIndex());
      final EArchitecture architecture = (EArchitecture) archCombo.getData(archName);
    
      final IFormControlChecker versionChecker = FormCheckerFactory.createCPPCompilerVersionChecker(targetOpHelper, targetOs, 
                                                                                                    architecture, 
                                                                                                    getFormPage(),
                                                                                                    compilerText);
      setPartCompleteFlag(versionChecker.validate(compilerText.getText().trim()) && hasCompleteInfo());
    }
  }

  
  private void createClient(final IManagedForm managedForm, final FormToolkit toolkit) {
    final Composite sectionClient = toolkit.createComposite(getSection());
    sectionClient.setLayout(new TableWrapLayout());
    sectionClient.setFont(getSection().getFont());
    sectionClient.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    
    final Composite osComposite = toolkit.createComposite(sectionClient);
    osComposite.setFont(getSection().getFont());
    final TableWrapLayout osLayout = new TableWrapLayout();
    osLayout.numColumns = 2;
    osComposite.setLayout(osLayout);
    osComposite.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    
    this.fOSCombo = SWTFormUtils.createLabelAndCombo(osComposite, LaunchMessages.XPCP_OSLabel, toolkit);
    this.fOSCombo.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    for (final ETargetOS targetOs : ETargetOS.values()) {
      this.fOSCombo.add(targetOs.name());
      this.fOSCombo.setData(targetOs.name(), targetOs);
    }
    this.fControlsAffectedByCIType.add(this.fOSCombo);
    
    final Composite archComposite = toolkit.createComposite(sectionClient);
    archComposite.setFont(getSection().getFont());
    final TableWrapLayout archLayout = new TableWrapLayout();
    archLayout.numColumns = 3;
    archComposite.setLayout(archLayout);
    archComposite.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    
    this.fArchCombo = SWTFormUtils.createLabelAndCombo(archComposite, LaunchMessages.XPCP_Architecture, toolkit);
    this.fArchCombo.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    for (final EArchitecture arch : EArchitecture.values()) {
      this.fArchCombo.add(arch.name());
      this.fArchCombo.setData(arch.name(), arch);
    }
    this.fControlsAffectedByCIType.add(this.fArchCombo);
    
    this.fBitsArchBt = toolkit.createButton(archComposite, LaunchMessages.XPCP_64BitsArchitectureBt, SWT.CHECK);
    this.fBitsArchBt.setLayoutData(new TableWrapData(TableWrapData.FILL, TableWrapData.MIDDLE));
    this.fControlsAffectedByCIType.add(this.fBitsArchBt);
    
    final Group compilingGroup = new Group(sectionClient, SWT.NONE);
    compilingGroup.setFont(sectionClient.getFont());
    compilingGroup.setLayout(new TableWrapLayout());
    compilingGroup.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    compilingGroup.setText(LaunchMessages.XPCP_CompilingGroup);
        
    final Pair<Text, Button> compPair = createLabelTextBrowseBt(compilingGroup, LaunchMessages.XPCP_CompilerLabel, 
                                                                LaunchMessages.XPCP_BrowseBt, toolkit);
    this.fCompilerText = compPair.first;
    this.fCompilerBrowseBt = compPair.second;
    this.fControlsAffectedByCIType.add(this.fCompilerText);
    this.fControlsAffectedByCIType.add(this.fCompilerBrowseBt);
    
    this.fCompilingOptsText = SWTFormUtils.createLabelAndText(compilingGroup, LaunchMessages.XPCP_CompilingOptsLabel,
                                                              toolkit, 3);
    this.fControlsAffectedByCIType.add(this.fCompilingOptsText);
    
    final Group archivingGroup = new Group(sectionClient, SWT.NONE);
    archivingGroup.setFont(sectionClient.getFont());
    archivingGroup.setLayout(new TableWrapLayout());
    archivingGroup.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    archivingGroup.setText(LaunchMessages.XPCP_ArchivingGroup);
    
    final Pair<Text, Button> archPair = createLabelTextBrowseBt(archivingGroup, LaunchMessages.XPCP_ArchiverLabel, 
                                                                LaunchMessages.XPCP_BrowseBt, toolkit);
    this.fArchiverText = archPair.first;
    this.fArchiverBrowseBt = archPair.second;
    this.fControlsAffectedByCIType.add(this.fArchiverText);
    this.fControlsAffectedByCIType.add(this.fArchiverBrowseBt);
    
    this.fArchivingOptsText = SWTFormUtils.createLabelAndText(archivingGroup, LaunchMessages.XPCP_ArchivingOptsLabel,
                                                              toolkit, 3);
    this.fControlsAffectedByCIType.add(this.fArchivingOptsText);
    
    final Group linkingGroup = new Group(sectionClient, SWT.NONE);
    linkingGroup.setFont(sectionClient.getFont());
    linkingGroup.setLayout(new TableWrapLayout());
    linkingGroup.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    linkingGroup.setText(LaunchMessages.XPCP_LinkingGroup);
    
    final Pair<Text, Button> linkPair = createLabelTextBrowseBt(linkingGroup, LaunchMessages.XPCP_LinkerLabel, 
                                                                LaunchMessages.XPCP_BrowseBt, toolkit);
    this.fLinkerText = linkPair.first;
    this.fLinkerBrowseBt = linkPair.second;
    this.fControlsAffectedByCIType.add(this.fLinkerText);
    this.fControlsAffectedByCIType.add(this.fLinkerBrowseBt);
    
    this.fLinkingOptsText = SWTFormUtils.createLabelAndText(linkingGroup, LaunchMessages.XPCP_LinkingOptsLabel, toolkit, 3);
    this.fControlsAffectedByCIType.add(this.fLinkingOptsText);

    this.fLinkingLibsText = SWTFormUtils.createLabelAndText(linkingGroup, LaunchMessages.XPCP_LinkingLibsLabel, toolkit, 3);
    this.fControlsAffectedByCIType.add(this.fLinkingLibsText);
    
    initializeControls();
    
    KeyboardUtils.addDelayedActionOnControl(this.fCompilerText, new Runnable() {
      
      public void run() {
        getFormPage().getEditorSite().getShell().getDisplay().asyncExec(new Runnable() {
          
          public void run() {
            checkCompilerVersion(CompilationAndLinkingSectionPart.this.fCompilerText, 
                                 CompilationAndLinkingSectionPart.this.fOSCombo,
                                 CompilationAndLinkingSectionPart.this.fArchCombo);
          }
          
        });
      }
      
    });
    
    addListeners(managedForm, this.fCompilerText, this.fCompilingOptsText, this.fArchiverText, this.fArchivingOptsText, 
                 this.fLinkerText, this.fLinkingOptsText, this.fLinkingLibsText, this.fBitsArchBt, this.fOSCombo,
                 this.fArchCombo);

    getSection().setClient(sectionClient);
  }
  
  private boolean hasCompleteInfo() {
    return this.fOSCombo.isEnabled() && (this.fOSCombo.getSelectionIndex() != -1) && 
           (this.fArchCombo.getSelectionIndex() != -1) && (this.fCompilerText.getText().trim().length() > 0) && 
           (this.fCompilingOptsText.getText().trim().length() > 0) && (this.fArchiverText.getText().trim().length() > 0) && 
           (this.fArchivingOptsText.getText().trim().length() > 0) && (this.fLinkerText.getText().trim().length() > 0) && 
           (this.fLinkingOptsText.getText().trim().length() > 0) && (this.fLinkingLibsText.getText().trim().length() > 0);
  }
  
  private void selectArchitecture(final ITargetOpHelper targetOpHelper) {
    final Pair<EArchitecture, EBitsArchitecture> pair = PlatformConfUtils.detectArchitecture(targetOpHelper);
    if (pair != null) {
      this.fBitsArchBt.setSelection(pair.second == EBitsArchitecture.E64Arch);
      getPlatformConf().setBitsArchitecture(pair.second);
      
      if (pair.first != null) {
        int index = -1;
        for (final String archName : this.fArchCombo.getItems()) {
          ++index;
          final EArchitecture curArch = (EArchitecture) this.fArchCombo.getData(archName);
          if (curArch == pair.first) {
            getPlatformConf().setArchitecture(curArch);
            this.fArchCombo.select(index);
            break;
          }
        }
      }
    }
  }
  
  private void selectOS(final ITargetOpHelper targetOpHelper) {
    final ETargetOS detectedOS = PlatformConfUtils.detectOS(targetOpHelper);
    if (detectedOS != null) {
      int index = -1;
      for (final String osName : this.fOSCombo.getItems()) {
        ++index;
        final ETargetOS targetOS = (ETargetOS) this.fOSCombo.getData(osName);
        if (detectedOS == targetOS) {
          this.fOSCombo.select(index);
          getPlatformConf().setTargetOS(targetOS);
          break;
        }
      }
    }
  }
  
  private void selectOsAndArchitecture() throws CoreException {
    final IX10PlatformConf platformConf = getPlatformConf();
    final ICppCompilationConf cppCompConf = platformConf.getCppCompilationConf();
    final IConnectionConf connConf = platformConf.getConnectionConf();
    final ITargetOpHelper targetOpHelper = TargetOpHelperFactory.create(connConf.isLocal(), 
                                                                        cppCompConf.getTargetOS() ==  ETargetOS.WINDOWS, 
                                                                        connConf.getConnectionName());
    if (targetOpHelper != null) {
      selectOS(targetOpHelper);
      selectArchitecture(targetOpHelper);
      updateCompilationCommands(this.fCompilerText, this.fCompilingOptsText, this.fArchiverText, this.fArchivingOptsText, 
                                this.fLinkerText, this.fLinkingOptsText, this.fLinkingLibsText);
    }
  }
  
  private void updateCompilationCommands(final Text compilerText, final Text compilingOptsText, final Text archiverText,
                                         final Text archivingOptsText, final Text linkerText, final Text linkingOptsText,
                                         final Text linkingLibsText) throws CoreException {
    final IDefaultCPPCommands defaultCPPCommands = DefaultCPPCommandsFactory.create(getPlatformConf());
    compilerText.setText(defaultCPPCommands.getCompiler());
    compilingOptsText.setText(defaultCPPCommands.getCompilerOptions());
    archiverText.setText(defaultCPPCommands.getArchiver());
    archivingOptsText.setText(defaultCPPCommands.getArchivingOpts());
    linkerText.setText(defaultCPPCommands.getLinker());
    linkingOptsText.setText(defaultCPPCommands.getLinkingOptions());
    linkingLibsText.setText(defaultCPPCommands.getLinkingLibraries());
  }
  
  // --- Fields
  
  private Combo fOSCombo;
  
  private Combo fArchCombo;
  
  private Text fCompilerText;
  
  private Text fCompilingOptsText;
  
  private Text fArchiverText;
  
  private Text fArchivingOptsText;
  
  private Text fLinkerText;
  
  private Text fLinkingOptsText;
  
  private Text fLinkingLibsText;
  
  private Button fBitsArchBt;
  
  private Button fCompilerBrowseBt;
  
  private Button fArchiverBrowseBt;
  
  private Button fLinkerBrowseBt;

  private final Collection<Control> fControlsAffectedByCIType = new ArrayList<Control>();
    
}
