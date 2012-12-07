package x10dt.ui.launch.cpp.launching;

import static org.eclipse.ptp.core.IPTPLaunchConfigurationConstants.ATTR_PROJECT_NAME;
import static x10dt.ui.launch.cpp.launching.ConnectionTab.IS_VALID;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationListener;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.imp.utils.Pair;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import x10dt.core.X10DTCorePlugin;
import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.platform_conf.EArchitecture;
import x10dt.ui.launch.core.platform_conf.EBitsArchitecture;
import x10dt.ui.launch.core.platform_conf.ETargetOS;
import x10dt.ui.launch.cpp.CppLaunchCore;
import x10dt.ui.launch.cpp.CppLaunchImages;
import x10dt.ui.launch.cpp.LaunchMessages;
import x10dt.ui.launch.cpp.builder.target_op.ITargetOpHelper;
import x10dt.ui.launch.cpp.builder.target_op.TargetOpHelperFactory;
import x10dt.ui.launch.cpp.utils.PlatformConfUtils;

public class CompilationTab extends AbstractLaunchConfigurationTab implements ILaunchConfigurationTab, ILaunchConfigurationListener {
  
  public static final String ATTR_DEFAULTS = CppLaunchCore.PLUGIN_ID + ".defaults"; //$NON-NLS-1$
  
  public static final String ATTR_ARCHITECTURE= CppLaunchCore.PLUGIN_ID + ".comp.architecture"; //$NON-NLS-1$
  
  public static final String ATTR_ARCHIVER = CppLaunchCore.PLUGIN_ID + ".comp.archiver"; //$NON-NLS-1$
  
  public static final String ATTR_ARCHIVER_OPTS = CppLaunchCore.PLUGIN_ID + ".comp.archiver.opts"; //$NON-NLS-1$
  
  public static final String ATTR_BITS_ARCH = CppLaunchCore.PLUGIN_ID + ".comp.bits.arch"; //$NON-NLS-1$
  
  public static final String ATTR_COMPILER = CppLaunchCore.PLUGIN_ID + ".comp.compiler"; //$NON-NLS-1$
  
  public static final String ATTR_COMPILER_OPTS = CppLaunchCore.PLUGIN_ID + ".comp.compiler.opts"; //$NON-NLS-1$
  
  public static final String ATTR_LINKER = CppLaunchCore.PLUGIN_ID + ".comp.linker"; //$NON-NLS-1$
  
  public static final String ATTR_LINKER_OPTS = CppLaunchCore.PLUGIN_ID + ".comp.linker.opts"; //$NON-NLS-1$
  
  public static final String ATTR_LINKER_LIBS = CppLaunchCore.PLUGIN_ID + ".comp.linker.libs"; //$NON-NLS-1$
  
  public static final String ATTR_OS = CppLaunchCore.PLUGIN_ID + ".comp.os"; //$NON-NLS-1$
  
  
  public CompilationTab(String projectName){
    CppLaunchImages.findOrCreateManaged(CppLaunchImages.PLACES_HOSTS);
    this.fProjectName = projectName;
  }
  
  public void launchConfigurationAdded(ILaunchConfiguration configuration) {
    // Nothing to do
  }

  public void launchConfigurationChanged(ILaunchConfiguration configuration) {
   // Nothing to do
  }

  public void launchConfigurationRemoved(ILaunchConfiguration configuration) {
    // Nothing to do
  }

 
  public void createControl(Composite parent) {
    final ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.V_SCROLL);
    
    setControl(scrolledComposite);
    
    final Composite composite = new Composite(scrolledComposite, SWT.NONE);
    composite.setFont(parent.getFont());
    composite.setLayout(new TableWrapLayout());
    composite.setLayoutData(new TableWrapData(TableWrapData.LEFT));
    
    createCompilationGroup(composite);
    scrolledComposite.setContent(composite);
    scrolledComposite.setExpandVertical(true);
    scrolledComposite.setExpandHorizontal(true);
  }
  
  private void createCompilationGroup(Composite parent){    
    final Composite sectionClient = new Composite(parent, SWT.NONE);
    sectionClient.setLayout(new TableWrapLayout());
    sectionClient.setFont(parent.getFont());
    sectionClient.setLayoutData(new TableWrapData(TableWrapData.LEFT));
    
    this.fDefaultsBt = new Button(sectionClient, SWT.CHECK);
    this.fDefaultsBt.setFont(parent.getFont());
    this.fDefaultsBt.setText(LaunchMessages.VMLT_DefaultsBt);
    this.fDefaultsBt.setLayoutData(new TableWrapData(TableWrapData.LEFT));
   
    
    final Composite osComposite = new Composite(sectionClient, SWT.NONE);
    osComposite.setFont(sectionClient.getFont());
    final TableWrapLayout osLayout = new TableWrapLayout();
    osLayout.numColumns = 2;
    osComposite.setLayout(osLayout);
    osComposite.setLayoutData(new TableWrapData(TableWrapData.LEFT));
    
    this.fOSCombo = createLabelAndCombo(osComposite, LaunchMessages.XPCP_OSLabel);
    this.fOSCombo.setLayoutData(new TableWrapData(TableWrapData.LEFT));
    int osIndex = 0;
    for (final ETargetOS targetOs : ETargetOS.values()) {
      this.fOSCombo.add(targetOs.name());
      this.fOSCombo.setData(targetOs.name(), targetOs);
      this.fOSIndexMap[osIndex++] = targetOs.name();
    }
    this.fControlsAffectedByCIType.add(this.fOSCombo);
    
    final Composite archComposite = new Composite(sectionClient, SWT.NONE);
    archComposite.setFont(sectionClient.getFont());
    final TableWrapLayout archLayout = new TableWrapLayout();
    archLayout.numColumns = 3;
    archComposite.setLayout(archLayout);
    archComposite.setLayoutData(new TableWrapData(TableWrapData.LEFT));
    
    int archIndex = 0;
    this.fArchCombo = createLabelAndCombo(archComposite, LaunchMessages.XPCP_Architecture);
    this.fArchCombo.setLayoutData(new TableWrapData(TableWrapData.LEFT));
    for (final EArchitecture arch : EArchitecture.values()) {
      this.fArchCombo.add(arch.name());
      this.fArchCombo.setData(arch.name(), arch);
      this.fArchIndexMap[archIndex++] = arch.name();
    }
    this.fControlsAffectedByCIType.add(this.fArchCombo);
    
    this.fBitsArchBt = new Button(archComposite,  SWT.CHECK);
    this.fBitsArchBt.setText(LaunchMessages.XPCP_64BitsArchitectureBt);
    this.fBitsArchBt.setLayoutData(new TableWrapData(TableWrapData.LEFT, TableWrapData.MIDDLE));
    this.fControlsAffectedByCIType.add(this.fBitsArchBt);
    
    final Group compilingGroup = new Group(sectionClient, SWT.NONE);
    compilingGroup.setFont(sectionClient.getFont());
    compilingGroup.setLayout(new TableWrapLayout());
    compilingGroup.setLayoutData(new TableWrapData(TableWrapData.LEFT));
    compilingGroup.setText(LaunchMessages.XPCP_CompilingGroup);
        
    final Pair<Text, Button> compPair = createLabelTextButton(compilingGroup, LaunchMessages.XPCP_CompilerLabel, 
      LaunchMessages.XPCP_BrowseBt, null, 1);
    this.fCompilerText = compPair.first;
    this.fCompilerBrowseBt = compPair.second;
    this.fControlsAffectedByCIType.add(this.fCompilerText);
    this.fControlsAffectedByCIType.add(this.fCompilerBrowseBt);
    
    this.fCompilingOptsText = createLabelAndText(compilingGroup, LaunchMessages.XPCP_CompilingOptsLabel,
                                                              3, SWT.NONE);
    this.fControlsAffectedByCIType.add(this.fCompilingOptsText);
    
    final Group archivingGroup = new Group(sectionClient, SWT.NONE);
    archivingGroup.setFont(sectionClient.getFont());
    archivingGroup.setLayout(new TableWrapLayout());
    archivingGroup.setLayoutData(new TableWrapData(TableWrapData.LEFT));
    archivingGroup.setText(LaunchMessages.XPCP_ArchivingGroup);
    
    final Pair<Text, Button> archPair = createLabelTextButton(archivingGroup, LaunchMessages.XPCP_ArchiverLabel, 
     LaunchMessages.XPCP_BrowseBt, null, 1);
    this.fArchiverText = archPair.first;
    this.fArchiverBrowseBt = archPair.second;
    this.fControlsAffectedByCIType.add(this.fArchiverText);
    this.fControlsAffectedByCIType.add(this.fArchiverBrowseBt);
    
    this.fArchivingOptsText = createLabelAndText(archivingGroup, LaunchMessages.XPCP_ArchivingOptsLabel,
      3, SWT.NONE);
    this.fControlsAffectedByCIType.add(this.fArchivingOptsText);
    
    final Group linkingGroup = new Group(sectionClient, SWT.NONE);
    linkingGroup.setFont(sectionClient.getFont());
    linkingGroup.setLayout(new TableWrapLayout());
    linkingGroup.setLayoutData(new TableWrapData(TableWrapData.LEFT));
    linkingGroup.setText(LaunchMessages.XPCP_LinkingGroup);
    
    final Pair<Text, Button> linkPair = createLabelTextButton(linkingGroup, LaunchMessages.XPCP_LinkerLabel, 
      LaunchMessages.XPCP_BrowseBt, null, 1);
    this.fLinkerText = linkPair.first;
    this.fLinkerBrowseBt = linkPair.second;
    this.fControlsAffectedByCIType.add(this.fLinkerText);
    this.fControlsAffectedByCIType.add(this.fLinkerBrowseBt);
    
    this.fLinkingOptsText = createLabelAndText(linkingGroup, LaunchMessages.XPCP_LinkingOptsLabel, 3, SWT.NONE);
    this.fControlsAffectedByCIType.add(this.fLinkingOptsText);

    this.fLinkingLibsText = createLabelAndText(linkingGroup, LaunchMessages.XPCP_LinkingLibsLabel, 3, SWT.NONE);
    this.fControlsAffectedByCIType.add(this.fLinkingLibsText);
    
    //initialization
    try {
      this.fDefaultsBt.setSelection(true);
      enableCompilationControls(false);
      selectOsAndArchitecture();
    } catch (CoreException e){
      CppLaunchCore.getInstance().getLog().log(e.getStatus());
    }
    
    addListeners(this.fDefaultsBt, this.fCompilerText, this.fCompilingOptsText, this.fArchiverText, this.fArchivingOptsText, 
                 this.fLinkerText, this.fLinkingOptsText, this.fLinkingLibsText, this.fBitsArchBt, this.fOSCombo,
                 this.fArchCombo);

  }
  

  public boolean isValid(ILaunchConfiguration conf){
    if (getErrorMessage() == null || getErrorMessage().equals(Constants.EMPTY_STR)) {
      try {
        ILaunchConfigurationWorkingCopy wc = conf.getWorkingCopy();
        wc.setAttribute(IS_VALID, true);
        wc.doSave();
      } catch (CoreException e){
        CppLaunchCore.getInstance().getLog().log(e.getStatus());
      }
      return true;
    }
    try {
      ILaunchConfigurationWorkingCopy wc = conf.getWorkingCopy();
      wc.setAttribute(IS_VALID, false);
      wc.doSave();
    } catch (CoreException e){
      CppLaunchCore.getInstance().getLog().log(e.getStatus());
    }
    return false;
  }
  
  public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
    //nothing to do
    
  }

  public void initializeFrom(ILaunchConfiguration configuration) {
    CppLaunchImages.findOrCreateManaged(CppLaunchImages.PLACES_HOSTS);
    try {
      this.fProjectName = configuration.getAttribute(ATTR_PROJECT_NAME, Constants.EMPTY_STR);
      
      this.fDefaultsBt.setSelection(configuration.getAttribute(ATTR_DEFAULTS, true));
      if (this.fDefaultsBt.getSelection()){
        selectOsAndArchitecture();
        enableCompilationControls(false);
        return;
      }
      
      int archIndex = 0;
      for (final EArchitecture arch : EArchitecture.values()) {
        this.fArchCombo.add(arch.name());
        this.fArchCombo.setData(arch.name(), arch);
        this.fArchIndexMap[archIndex++] = arch.name();
      }
      setArchitecture(getArch(configuration.getAttribute(ATTR_ARCHITECTURE, Constants.EMPTY_STR)), 
                      getBits(configuration.getAttribute(ATTR_BITS_ARCH, true)));
      
      for (final ETargetOS targetOs : ETargetOS.values()) {
        this.fOSCombo.add(targetOs.name());
        this.fOSCombo.setData(targetOs.name(), targetOs);
      }
      setOS(getOS(configuration.getAttribute(ATTR_OS, Constants.EMPTY_STR)));
      
      this.fArchiverText.setText(configuration.getAttribute(ATTR_ARCHIVER, Constants.EMPTY_STR));
      this.fArchivingOptsText.setText(configuration.getAttribute(ATTR_ARCHIVER_OPTS, Constants.EMPTY_STR));
      this.fCompilerText.setText(configuration.getAttribute(ATTR_COMPILER, Constants.EMPTY_STR));
      this.fCompilingOptsText.setText(configuration.getAttribute(ATTR_COMPILER_OPTS, Constants.EMPTY_STR));
      this.fLinkerText.setText(configuration.getAttribute(ATTR_LINKER, Constants.EMPTY_STR));
      this.fLinkingOptsText.setText(configuration.getAttribute(ATTR_LINKER_OPTS, Constants.EMPTY_STR));
      this.fLinkingLibsText.setText(configuration.getAttribute(ATTR_LINKER_LIBS, Constants.EMPTY_STR));
      enableCompilationControls(true);
    } catch (CoreException e){
      CppLaunchCore.getInstance().getLog().log(e.getStatus());
    }
  }

  public void performApply(ILaunchConfigurationWorkingCopy configuration) {
    configuration.setAttribute(ATTR_DEFAULTS, this.fDefaultsBt.getSelection());
    configuration.setAttribute(ATTR_ARCHITECTURE, this.fArchIndexMap[this.fArchCombo.getSelectionIndex()]);
    configuration.setAttribute(ATTR_ARCHIVER, this.fArchiverText.getText());
    configuration.setAttribute(ATTR_ARCHIVER_OPTS, this.fArchivingOptsText.getText());
    configuration.setAttribute(ATTR_BITS_ARCH, this.fBitsArchBt.getSelection());
    configuration.setAttribute(ATTR_COMPILER, this.fCompilerText.getText());
    configuration.setAttribute(ATTR_COMPILER_OPTS, this.fCompilingOptsText.getText());
    configuration.setAttribute(ATTR_LINKER, this.fLinkerText.getText());
    configuration.setAttribute(ATTR_LINKER_OPTS, this.fLinkingOptsText.getText());
    configuration.setAttribute(ATTR_LINKER_LIBS, this.fLinkingLibsText.getText());
    configuration.setAttribute(ATTR_OS, this.fOSIndexMap[this.fOSCombo.getSelectionIndex()]);
  }

  public String getName() {
    return LaunchMessages.VMLT_CompilationTabName;
  }
  
  public Image getImage() {
    return CppLaunchImages.getImage(CppLaunchImages.PLACES_HOSTS);
  }

  // private code
  
  private void handleEmptyTextValidation(Text text, String msg){
    if (text.getText().trim().length() == 0){
      setErrorMessage(msg + " field cannot be empty");
    }
  }
  
  private void enableCompilationControls(boolean enable){
    for (Control control: this.fControlsAffectedByCIType) {
      control.setEnabled(enable);
    }
  }
  
  private void addListeners(final Button defaultsBt, final Text compilerText, final Text compilingOptsText, final Text archiverText,
      final Text archivingOptsText, final Text linkerText, final Text linkingOptsText, final Text linkingLibsText, final Button bitsArchBt,
      final Combo osCombo, final Combo archCombo) {
    
    defaultsBt.addSelectionListener(new SelectionListener() {

      public void widgetSelected(final SelectionEvent event) {
        try {
          if (CompilationTab.this.fDefaultsBt.getSelection()){
            selectOsAndArchitecture();
            enableCompilationControls(false);
          } else {
            enableCompilationControls(true);
          }
        } catch (CoreException e){
          CppLaunchCore.getInstance().getLog().log(e.getStatus());
        }
        updateLaunchConfigurationDialog();
       
      }

      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }

    });
    
    compilerText.addModifyListener(new ModifyListener() {
      public void modifyText(final ModifyEvent event) {
        updateLaunchConfigurationDialog();
        handleEmptyTextValidation(compilerText, LaunchMessages.XPCP_CompilerLabel);
      }
    });
    compilingOptsText.addModifyListener(new ModifyListener() {

      public void modifyText(final ModifyEvent event) {
        updateLaunchConfigurationDialog();
        handleEmptyTextValidation(compilingOptsText, LaunchMessages.XPCP_CompilingOptsLabel);
      }

    });
    archiverText.addModifyListener(new ModifyListener() {

      public void modifyText(final ModifyEvent event) {
        updateLaunchConfigurationDialog();
        handleEmptyTextValidation(archiverText, LaunchMessages.XPCP_ArchiverLabel);
      }

    });
    archivingOptsText.addModifyListener(new ModifyListener() {

      public void modifyText(final ModifyEvent event) {
        updateLaunchConfigurationDialog();
        handleEmptyTextValidation(archivingOptsText, LaunchMessages.XPCP_ArchivingOptsLabel);
      }

    });
    linkerText.addModifyListener(new ModifyListener() {

      public void modifyText(final ModifyEvent event) {
        updateLaunchConfigurationDialog();
        handleEmptyTextValidation(linkerText, LaunchMessages.XPCP_LinkerLabel);
      }

    });
    linkingOptsText.addModifyListener(new ModifyListener() {

      public void modifyText(final ModifyEvent event) {
        updateLaunchConfigurationDialog();
        handleEmptyTextValidation(linkingOptsText, LaunchMessages.XPCP_LinkingOptsLabel);
      }

    });
    linkingLibsText.addModifyListener(new ModifyListener() {

      public void modifyText(final ModifyEvent event) {
        updateLaunchConfigurationDialog();
        handleEmptyTextValidation(linkingLibsText, LaunchMessages.XPCP_LinkingLibsLabel);
      }

    });
    bitsArchBt.addSelectionListener(new SelectionListener() {

      public void widgetSelected(final SelectionEvent event) {
        try {
          updateCompilationCommands(compilerText, compilingOptsText, archiverText, archivingOptsText, linkerText, linkingOptsText, linkingLibsText);
        } catch (CoreException except) {
          CppLaunchCore.getInstance().getLog().log(except.getStatus());
        }
      }

      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }

    });
    osCombo.addSelectionListener(new SelectionListener() {

      public void widgetSelected(final SelectionEvent event) {   
        try {
          updateCompilationCommands(compilerText, compilingOptsText, archiverText, archivingOptsText, linkerText, linkingOptsText, linkingLibsText);
        } catch (CoreException except) {
          CppLaunchCore.getInstance().getLog().log(except.getStatus());
        }
      }

      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }

    });
    archCombo.addSelectionListener(new SelectionListener() {

      public void widgetSelected(final SelectionEvent event) {
        try {
          updateCompilationCommands(compilerText, compilingOptsText, archiverText, archivingOptsText, linkerText, linkingOptsText, linkingLibsText);
        } catch (CoreException except) {
          CppLaunchCore.getInstance().getLog().log(except.getStatus());
        }
      }

      public void widgetDefaultSelected(final SelectionEvent event) {
        widgetSelected(event);
      }

    });
  }
  
  
  private Combo createLabelAndCombo(final Composite parent, final String labelText) {
    final Composite composite= new Composite(parent, SWT.NONE);
    final boolean isTableWrapLayout= parent.getLayout() instanceof TableWrapLayout;
    composite.setFont(parent.getFont());
    if (isTableWrapLayout) {
      final TableWrapLayout tableWrapLayout= new TableWrapLayout();
      tableWrapLayout.numColumns= 2;
      composite.setLayout(tableWrapLayout);
      composite.setLayoutData(new TableWrapData(TableWrapData.LEFT));
    } else {
      composite.setLayout(new GridLayout(2, false));
      composite.setLayoutData(new GridData(SWT.LEFT, SWT.NONE, true, false));
    }

    final Label label= new Label(composite, SWT.NONE);
    label.setText(labelText);
    if (isTableWrapLayout) {
      label.setLayoutData(new TableWrapData(TableWrapData.LEFT, TableWrapData.MIDDLE));
    } else {
      label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
    }

    final Combo combo= new Combo(composite, SWT.READ_ONLY);
    if (isTableWrapLayout) {
      final TableWrapData td= new TableWrapData(TableWrapData.LEFT);
      td.indent= 5;
      combo.setLayoutData(td);
    } else {
      final GridData gd= new GridData(SWT.LEFT, SWT.NONE, true, false);
      gd.horizontalIndent= 5;
      combo.setLayoutData(gd);
    }
    return combo;
  }
  
  private EBitsArchitecture getBits(boolean is64){
    if (is64) return EBitsArchitecture.E64Arch;
    return EBitsArchitecture.E32Arch;
  }
  
  
  private EArchitecture getArch(String archString){
    for (final EArchitecture arch : EArchitecture.values()) {
      if (arch.name().equals(archString)){
        return arch;
      }
    }
    return null;
  }
  
  private void setArchitecture(EArchitecture arch, EBitsArchitecture bitsArch){
    this.fBitsArchBt.setSelection(bitsArch == EBitsArchitecture.E64Arch);
    
    if (arch != null) {
      int index = -1;
      for (final String archName : this.fArchCombo.getItems()) {
        ++index;
        final EArchitecture curArch = (EArchitecture) this.fArchCombo.getData(archName);
        if (curArch == arch) {
          this.fArchCombo.select(index);
          break;
        }
      }
    }
  }
  
  private void selectArchitecture(final ITargetOpHelper targetOpHelper) {
    final Pair<EArchitecture, EBitsArchitecture> pair = PlatformConfUtils.detectArchitecture(targetOpHelper);
    if (pair != null) {
      setArchitecture(pair.first, pair.second);
    }
  }
  
  private void setOS(ETargetOS os){
    int index = -1;
    for (final String osName : this.fOSCombo.getItems()) {
      ++index;
      final ETargetOS targetOS = (ETargetOS) this.fOSCombo.getData(osName);
      if (os == targetOS) {
        this.fOSCombo.select(index);
        break;
      }
    }
  }
  
  private ETargetOS getOS(String osString){
    for (final ETargetOS os : ETargetOS.values()) {
      if (os.name().equals(osString)){
        return os;
      }
    }
    return null;
  }
  
  private void selectOS(final ITargetOpHelper targetOpHelper) {
    final ETargetOS detectedOS = PlatformConfUtils.detectOS(targetOpHelper);
    if (detectedOS != null) {
      setOS(detectedOS);
    }
  }
  
  private void selectOsAndArchitecture() throws CoreException {
    final ILaunchConfiguration conf = ConfUtils.getConfiguration(this.fProjectName);
    final ITargetOpHelper targetOpHelper = TargetOpHelperFactory.create(ConfUtils.isLocalConnection(conf), 
                                                                        ConfUtils.isCygwin(conf), 
                                                                        ConfUtils.getConnectionName(conf));
    if (targetOpHelper != null) {
      selectOS(targetOpHelper);
      selectArchitecture(targetOpHelper);
      updateCompilationCommands(this.fCompilerText, this.fCompilingOptsText, this.fArchiverText, this.fArchivingOptsText, 
                                this.fLinkerText, this.fLinkingOptsText, this.fLinkingLibsText);
    }
  }
  
  private void updateCompilationCommands(final Text compilerText, final Text compilingOptsText, final Text archiverText, final Text archivingOptsText,
      final Text linkerText, final Text linkingOptsText, final Text linkingLibsText) throws CoreException {
    if (this.fProjectName == null || this.fProjectName.equals(Constants.EMPTY_STR)){
      return;
    }
    IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(this.fProjectName);
    if (project.hasNature(X10DTCorePlugin.X10_CPP_PRJ_NATURE_ID)){
      final IDefaultCPPCommands defaultCPPCommands= DefaultCPPCommandsFactory.create(this.fProjectName);
      compilerText.setText(defaultCPPCommands.getCompiler());
      compilingOptsText.setText(defaultCPPCommands.getCompilerOptions());
      archiverText.setText(defaultCPPCommands.getArchiver());
      archivingOptsText.setText(defaultCPPCommands.getArchivingOpts());
      linkerText.setText(defaultCPPCommands.getLinker());
      linkingOptsText.setText(defaultCPPCommands.getLinkingOptions());
      linkingLibsText.setText(defaultCPPCommands.getLinkingLibraries());
    } 
  }
  
  private Text createLabelAndText(final Composite parent, final String labelText,
      final int textHeightFactor, final int textStyle) {
    final Composite composite= new Composite(parent, SWT.NONE);
    final boolean isTableWrapLayout= parent.getLayout() instanceof TableWrapLayout;
    composite.setFont(parent.getFont());
    if (isTableWrapLayout) {
      final TableWrapLayout tableWrapLayout= new TableWrapLayout();
      tableWrapLayout.numColumns= 2;
      composite.setLayout(tableWrapLayout);
      composite.setLayoutData(new TableWrapData(TableWrapData.LEFT));
    } else {
      composite.setLayout(new GridLayout(2, false));
      composite.setLayoutData(new GridData(SWT.LEFT, SWT.NONE, false, false));
    }
   

    final Label label= new Label(composite, SWT.NONE);
    label.setText(labelText);
    if (isTableWrapLayout) {
      label.setLayoutData(new TableWrapData(TableWrapData.LEFT, TableWrapData.MIDDLE));
    } else {
      label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
    }
   
    final int style= (textHeightFactor == 1) ? SWT.NONE : SWT.WRAP;
    final Text text= new Text(composite, style | textStyle);
    if (isTableWrapLayout) {
      final TableWrapData gd= new TableWrapData(TableWrapData.LEFT);
      if (textHeightFactor > 1) {
        gd.heightHint= text.getLineHeight() * textHeightFactor;
      }
      gd.indent= 5;
      text.setLayoutData(gd);
    } else {
      final GridData gd= new GridData(SWT.LEFT, SWT.NONE, false, false);
      if (textHeightFactor > 1) {
        gd.heightHint= text.getLineHeight() * textHeightFactor;
      }
      gd.horizontalIndent= 5;
      text.setLayoutData(gd);
    }
    return text;
  }

  private Pair<Text, Button> createLabelTextButton(final Composite parent, final String labelText, final String buttonText,
      final Collection<Control> controlContainer, final int textHeightFactor) {
    final Composite composite= new Composite(parent, SWT.NONE);
    composite.setFont(parent.getFont());
    final boolean isTableWrapLayout= parent.getLayout() instanceof TableWrapLayout;
    if (isTableWrapLayout) {
      final TableWrapLayout tableWrapLayout= new TableWrapLayout();
      tableWrapLayout.numColumns= 3;
      composite.setLayout(tableWrapLayout);
      composite.setLayoutData(new TableWrapData(TableWrapData.LEFT));
    } else {
      composite.setLayout(new GridLayout(3, false));
      composite.setLayoutData(new GridData(SWT.LEFT, SWT.NONE, false, false));
    }
    if (controlContainer != null) {
      controlContainer.add(composite);
    }

    final Label label= new Label(composite, SWT.NONE);
    label.setText(labelText);
    if (isTableWrapLayout) {
      label.setLayoutData(new TableWrapData(TableWrapData.LEFT, TableWrapData.MIDDLE));
    } else {
      label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
    }
    if (controlContainer != null) {
      controlContainer.add(label);
    }
    final int style= (textHeightFactor == 1) ? SWT.NONE : SWT.WRAP;
    final Text text= new Text(composite, style);
    if (isTableWrapLayout) {
      final TableWrapData twData= new TableWrapData(TableWrapData.LEFT, TableWrapData.MIDDLE);
      if (textHeightFactor > 1) {
        twData.heightHint= text.getLineHeight() * textHeightFactor;
      }
      twData.indent= 5;
      text.setLayoutData(twData);
    } else {
      final GridData gd= new GridData(SWT.LEFT, SWT.CENTER, false, false);
      if (textHeightFactor > 1) {
        gd.heightHint= text.getLineHeight() * textHeightFactor;
      }
      gd.horizontalIndent= 5;
      text.setLayoutData(gd);
    }
    if (controlContainer != null) {
      controlContainer.add(text);
    }
    final Button button= new Button(composite,  SWT.PUSH);
    button.setText(buttonText);
    if (isTableWrapLayout) {
      final TableWrapData twData= new TableWrapData(TableWrapData.LEFT, TableWrapData.MIDDLE);
      twData.heightHint= text.getLineHeight() + 4;
      button.setLayoutData(twData);
    } else {
      GridData gd= new GridData(SWT.LEFT, SWT.CENTER, false, false);
      gd.heightHint= text.getLineHeight() + 4;
      button.setLayoutData(gd);
    }
    button.setEnabled(false);
    if (controlContainer != null) {
      controlContainer.add(button);
    }
    return new Pair<Text, Button>(text, button);
  }
  
 
  // Fields
  
  private String fProjectName;
  
  private Button fDefaultsBt;
   
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
  
  private String[] fOSIndexMap = new String[5];
  
  private String[] fArchIndexMap = new String[2];
 
}
