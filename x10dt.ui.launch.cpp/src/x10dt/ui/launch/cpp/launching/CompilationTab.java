package x10dt.ui.launch.cpp.launching;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationListener;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.imp.utils.Pair;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
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
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import x10dt.ui.launch.core.platform_conf.EArchitecture;
import x10dt.ui.launch.core.platform_conf.EBitsArchitecture;
import x10dt.ui.launch.core.platform_conf.ETargetOS;
import x10dt.ui.launch.core.utils.KeyboardUtils;
import x10dt.ui.launch.core.utils.SWTFormUtils;
import x10dt.ui.launch.cpp.CppLaunchImages;
import x10dt.ui.launch.cpp.LaunchMessages;
import x10dt.ui.launch.cpp.builder.target_op.ITargetOpHelper;
import x10dt.ui.launch.cpp.builder.target_op.TargetOpHelperFactory;
import x10dt.ui.launch.cpp.platform_conf.IConnectionConf;
import x10dt.ui.launch.cpp.platform_conf.ICppCompilationConf;
import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConf;
import x10dt.ui.launch.cpp.utils.PlatformConfUtils;

public class CompilationTab extends AbstractLaunchConfigurationTab implements ILaunchConfigurationTab, ILaunchConfigurationListener {
  
  public CompilationTab(X10RemoteCompilationApplicationTab tab){
    CppLaunchImages.findOrCreateManaged(CppLaunchImages.PLACES_HOSTS);
    this.fApplicationTab = tab;
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
    
    final Composite osComposite = new Composite(sectionClient, SWT.NONE);
    osComposite.setFont(sectionClient.getFont());
    final TableWrapLayout osLayout = new TableWrapLayout();
    osLayout.numColumns = 2;
    osComposite.setLayout(osLayout);
    osComposite.setLayoutData(new TableWrapData(TableWrapData.LEFT));
    
    this.fOSCombo = createLabelAndCombo(osComposite, LaunchMessages.XPCP_OSLabel);
    this.fOSCombo.setLayoutData(new TableWrapData(TableWrapData.LEFT));
    for (final ETargetOS targetOs : ETargetOS.values()) {
      this.fOSCombo.add(targetOs.name());
      this.fOSCombo.setData(targetOs.name(), targetOs);
    }
    this.fControlsAffectedByCIType.add(this.fOSCombo);
    
    final Composite archComposite = new Composite(sectionClient, SWT.NONE);
    archComposite.setFont(sectionClient.getFont());
    final TableWrapLayout archLayout = new TableWrapLayout();
    archLayout.numColumns = 3;
    archComposite.setLayout(archLayout);
    archComposite.setLayoutData(new TableWrapData(TableWrapData.LEFT));
    
    this.fArchCombo = createLabelAndCombo(archComposite, LaunchMessages.XPCP_Architecture);
    this.fArchCombo.setLayoutData(new TableWrapData(TableWrapData.LEFT));
    for (final EArchitecture arch : EArchitecture.values()) {
      this.fArchCombo.add(arch.name());
      this.fArchCombo.setData(arch.name(), arch);
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
    try {
    selectOsAndArchitecture();
    } catch (CoreException e){
      //TODO
     
    }
    
    KeyboardUtils.addDelayedActionOnControl(this.fCompilerText, new Runnable() {
      
      public void run() {
        getShell().getDisplay().asyncExec(new Runnable() {
          
          public void run() {
            /*checkCompilerVersion(CompilationTab.this.fCompilerText, 
                                 CompilationTab.this.fOSCombo,
                                  CompilationTab.this.fArchCombo);*/
          }
          
        });
      }
      
    });
    
    /*addListeners(managedForm, this.fCompilerText, this.fCompilingOptsText, this.fArchiverText, this.fArchivingOptsText, 
                 this.fLinkerText, this.fLinkingOptsText, this.fLinkingLibsText, this.fBitsArchBt, this.fOSCombo,
                 this.fArchCombo);
*/
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
  
  public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
    // TODO Auto-generated method stub
    
  }

  public void initializeFrom(ILaunchConfiguration configuration) {
    // TODO Auto-generated method stub
    
  }

  public void performApply(ILaunchConfigurationWorkingCopy configuration) {
    // TODO Auto-generated method stub
    
  }

  public String getName() {
    return LaunchMessages.VMLT_CompilationTabName;
  }
  
  public Image getImage() {
    return CppLaunchImages.getImage(CppLaunchImages.PLACES_HOSTS);
  }

  // private code
  
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
  
  private void selectArchitecture(final ITargetOpHelper targetOpHelper) {
    final Pair<EArchitecture, EBitsArchitecture> pair = PlatformConfUtils.detectArchitecture(targetOpHelper);
    if (pair != null) {
      this.fBitsArchBt.setSelection(pair.second == EBitsArchitecture.E64Arch);
      
      if (pair.first != null) {
        int index = -1;
        for (final String archName : this.fArchCombo.getItems()) {
          ++index;
          final EArchitecture curArch = (EArchitecture) this.fArchCombo.getData(archName);
          if (curArch == pair.first) {
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
          break;
        }
      }
    }
  }
  
  private void selectOsAndArchitecture() throws CoreException {
    final ILaunchConfiguration conf = ConfUtils.getConfiguration(this.fApplicationTab.getProjectName());
    final ITargetOpHelper targetOpHelper = TargetOpHelperFactory.create(ConfUtils.isLocalConnection(conf), 
                                                                        ConfUtils.getTargetOS(conf) ==  ETargetOS.WINDOWS, 
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
    final IDefaultCPPCommands defaultCPPCommands= DefaultCPPCommandsFactory.create(this.fApplicationTab.getProjectName());
    compilerText.setText(defaultCPPCommands.getCompiler());
    compilingOptsText.setText(defaultCPPCommands.getCompilerOptions());
    archiverText.setText(defaultCPPCommands.getArchiver());
    archivingOptsText.setText(defaultCPPCommands.getArchivingOpts());
    linkerText.setText(defaultCPPCommands.getLinker());
    linkingOptsText.setText(defaultCPPCommands.getLinkingOptions());
    linkingLibsText.setText(defaultCPPCommands.getLinkingLibraries());
  }
 
  // Fields
  
  private X10RemoteCompilationApplicationTab fApplicationTab;
   
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
