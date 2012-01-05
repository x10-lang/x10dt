/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching;

import static org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME;
import static org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationListener;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.Bullet;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.GlyphMetrics;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.AbstractElementListSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

import x10dt.ui.Messages;
import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.utils.StringUtils;
import x10dt.ui.launch.cpp.CppLaunchCore;
import x10dt.ui.launch.cpp.LaunchMessages;
import x10dt.ui.launch.cpp.launching.services.AttrServicesFactory;
import x10dt.ui.launch.cpp.launching.services.IAttrServices;
import x10dt.ui.launch.cpp.launching.services.ILaunchConfAttrDataBuilder;
import x10dt.ui.launch.cpp.platform_conf.ICommunicationInterfaceConf;
import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConf;


final class SelectExistingLaunchConfigsDialog extends AbstractElementListSelectionDialog implements ISelectionStatusValidator {

  SelectExistingLaunchConfigsDialog(final Shell parent, final List<ILaunchConfiguration> configList, final String mode) {
    super(parent, new LabelProvider());
    this.fConfigList = configList;
    this.fGroupIdentifier = "org.eclipse.debug.ui.launchGroup." + mode; //$NON-NLS-1$
    
    final ILaunchConfiguration firstConfig = configList.iterator().next();
    setMultipleSelection(true);
    setTitle(Messages.AXLS_MultipleConfDialogTitle);
    setStatusLineAboveButtons(true);
    setValidator(this);
    try {
      final String projectName = firstConfig.getAttribute(ATTR_PROJECT_NAME, Constants.EMPTY_STR);
      final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
      this.fX10PlatformConf = CppLaunchCore.getInstance().getPlatformConfiguration(project);
      
      setMessage(NLS.bind(Messages.AXLS_MultipleConfDialogMsg, 
                          firstConfig.getAttribute(ATTR_MAIN_TYPE_NAME, Constants.EMPTY_STR), projectName));
    } catch (CoreException except) {
      // Can't really occur since it would have been thrown earlier.
      CppLaunchCore.log(except.getStatus());
    }
  }
  
  // --- ISelectionStatusValidator's interface methods implementation
  
  public IStatus validate(final Object[] selection) {
    if (selection.length == 1) {
      return new Status(IStatus.OK, CppLaunchCore.PLUGIN_ID, Constants.EMPTY_STR);
    } else {
      return new Status(IStatus.WARNING, CppLaunchCore.PLUGIN_ID, Messages.AXLS_OneLaunchConfAllowed);
    }
  }
  
  // --- Abstract methods implementation
  
  protected void computeResult() {
    setResult(Arrays.asList(getSelectedElements()));
  }
  
  // --- Overridden methods
  
  protected void buttonPressed(final int buttonId) {
    if (buttonId == CREATE_NEW_ID) {
      setResult(null);
      setReturnCode(OK);
      close();
    } else if (buttonId == EDIT_ID) {
      final ILaunchConfiguration configuration = (ILaunchConfiguration) getSelectedElements()[0];
      
      final LaunchConfListener launchConfListener = new LaunchConfListener();
      try {
        DebugPlugin.getDefault().getLaunchManager().addLaunchConfigurationListener(launchConfListener);
                                                                                 
        final int returnCode = DebugUITools.openLaunchConfigurationDialog(getShell(), configuration, 
                                                                          this.fGroupIdentifier, null);
        if (returnCode == OK) {
          okPressed();
        } else {
          if (launchConfListener.hasChanged()) {
            setListElements(this.fConfigList.toArray());
          }
          showLaunchConfigResume(configuration);
        }
      } finally {
        DebugPlugin.getDefault().getLaunchManager().removeLaunchConfigurationListener(launchConfListener);
      }
    } else {
      super.buttonPressed(buttonId);
    }
  }
  
  protected void createButtonsForButtonBar(final Composite parent) {
    final GridLayout parentLayout = (GridLayout) parent.getLayout();
    parentLayout.makeColumnsEqualWidth = false;
    
    createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
    createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    createButton(parent, CREATE_NEW_ID, Messages.AXLS_CreateNewConfBt, false);
    createButton(parent, EDIT_ID, Messages.AXLS_EditBt, false);
  }
  
  protected Control createDialogArea(Composite parent) {
    final Composite contents = (Composite) super.createDialogArea(parent);

    createMessageArea(contents);
    
    final Composite composite = new Composite(contents, SWT.NONE);
    composite.setFont(contents.getFont());
    composite.setLayout(new GridLayout(2, true));
    composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    
    createFilterText(composite);
    createResumeArea(composite);
    createFilteredList(composite);

    setListElements(this.fConfigList.toArray());

    setSelection(getInitialElementSelections().toArray());

    return contents;
  }
  
  protected void handleSelectionChanged() {
    final Object[] selectedElements = getSelectedElements();
    if (selectedElements.length > 0) {
      if (selectedElements.length == 1) {
        showLaunchConfigResume((ILaunchConfiguration) selectedElements[0]);
      } else if (selectedElements.length == 2) {
        showLaunchConfigDiff((ILaunchConfiguration) selectedElements[0], (ILaunchConfiguration) selectedElements[1]);
      } else {
        this.fResumeText.setText(Constants.EMPTY_STR);
        this.fScrolledCompo.setMinSize(this.fResumeText.computeSize(SWT.DEFAULT, SWT.DEFAULT, true));
      }
    } else {
      this.fResumeText.setText(Constants.EMPTY_STR);
      this.fScrolledCompo.setMinSize(this.fResumeText.computeSize(SWT.DEFAULT, SWT.DEFAULT, true));
    }
    super.handleSelectionChanged();
  }
  
  public boolean close() {
    if ((this.fGradientImage != null) && ! this.fGradientImage.isDisposed()) {
      this.fGradientImage.dispose();
    }
    return super.close();
  }
  
  protected void updateButtonsEnableState(final IStatus status) {
    final Button okButton = getOkButton();
    if (okButton != null && ! okButton.isDisposed()) {
      okButton.setEnabled(! status.matches(IStatus.WARNING | IStatus.ERROR));
    }
    final Button newConfButton = getButton(CREATE_NEW_ID);
    if ((newConfButton != null) && ! newConfButton.isDisposed()) {
      newConfButton.setEnabled(! status.matches(IStatus.WARNING | IStatus.ERROR));
    }
    final Button editButton = getButton(EDIT_ID);
    if ((editButton != null) && ! editButton.isDisposed()) {
      editButton.setEnabled(! status.matches(IStatus.WARNING | IStatus.ERROR));
    }
  }
  
  // --- Private code
  
  private void createResumeArea(final Composite parent) {
    final ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.H_SCROLL);
    this.fScrolledCompo = scrolledComposite;
    scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
    scrolledComposite.setFont(parent.getFont());
    scrolledComposite.setExpandHorizontal(true);
    scrolledComposite.setExpandVertical(true);
    scrolledComposite.setShowFocusedControl(true);
    
    final StyledText styledText = new StyledText(scrolledComposite, SWT.READ_ONLY | SWT.BORDER);
    this.fResumeText = styledText;
    styledText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    final Display display = getShell().getDisplay();
    styledText.addListener(SWT.Resize, new Listener() {
      
      public void handleEvent(final Event event) {
        final Rectangle rect = styledText.getClientArea();
        final Image newImage = new Image(display, 1, Math.max(1, rect.height));
        final GC gc = new GC(newImage);
        gc.setForeground(display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
        gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
        gc.fillGradientRectangle(rect.x, rect.y, 1, rect.height, true);
        gc.dispose();
        styledText.setBackgroundImage(newImage);
        if (SelectExistingLaunchConfigsDialog.this.fGradientImage != null) {
          SelectExistingLaunchConfigsDialog.this.fGradientImage.dispose();
        }
        SelectExistingLaunchConfigsDialog.this.fGradientImage = newImage;
      }
      
    });
    
    scrolledComposite.setContent(styledText);
  }
  
  private void postProcess() {
    for (int lineIndex = 0, size = this.fResumeText.getLineCount(); lineIndex < size; ++lineIndex) {
      final String line = this.fResumeText.getLine(lineIndex);
      final StyleRange[] lineStyleRanges = this.fResumeText.getStyleRanges(this.fResumeText.getOffsetAtLine(lineIndex), 
                                                                           line.length());
      boolean shouldIndent = lineStyleRanges.length > 0;
      if (shouldIndent) {
        for (final StyleRange styleRange : lineStyleRanges) {
          if (styleRange.underline) {
            shouldIndent = false;
            break;
          }
        }
      }
      if (shouldIndent) {
        lineStyleRanges[0].metrics = new GlyphMetrics(0, 0, 30);
        this.fResumeText.setLineBullet(lineIndex, 1, new Bullet(lineStyleRanges[0]));
      }
    
    }
    this.fResumeText.setLeftMargin(5);
    this.fResumeText.setRightMargin(5);
    this.fResumeText.setBottomMargin(5);
  }
  
  private void printConfigurationResume(final ILaunchConfiguration launchConfig, 
                                        final Map<IAttrServices, List<IAttrServices>> attrMap) {
    final StringBuilder sb = new StringBuilder();
    sb.append(launchConfig.getName()).append('\n');
    final List<StyleRange> styleRanges = new ArrayList<StyleRange>();
    for (final Map.Entry<IAttrServices, List<IAttrServices>> entry : attrMap.entrySet()) {
      entry.getKey().buildFormattedString(sb, getShell().getDisplay(), styleRanges);
      for (final IAttrServices attr : entry.getValue()) {
        attr.buildFormattedString(sb, getShell().getDisplay(), styleRanges);
      }
    }
    this.fResumeText.setText(sb.toString());
    
    this.fResumeText.setStyleRanges(styleRanges.toArray(new StyleRange[styleRanges.size()]));
    postProcess();
  }
  
  private void printConfigurationsDiff(final ILaunchConfiguration firstConfig, final ILaunchConfiguration secondConfig,
                                       final Map<IAttrServices, List<IAttrServices>> firstAttrMap,
                                       final Map<IAttrServices, List<IAttrServices>> secondAttrMap) {
    final Map<IAttrServices, List<IAttrServices>> firstFinalAttrMap = new LinkedHashMap<IAttrServices, List<IAttrServices>>();
    final Map<IAttrServices, List<IAttrServices>> secondFinalAttrMap = new LinkedHashMap<IAttrServices, List<IAttrServices>>();
    
    for (final Map.Entry<IAttrServices, List<IAttrServices>> firstAttrEntry : firstAttrMap.entrySet()) {
      final List<IAttrServices> secondAttrList = secondAttrMap.get(firstAttrEntry.getKey());
      if (secondAttrList == null) {
        for (final IAttrServices firstAttr : firstAttrEntry.getValue()) {
          firstAttr.setSWTColor(SWT.COLOR_BLUE, SWT.COLOR_BLUE);
          updateDataMap(firstFinalAttrMap, firstAttrEntry.getKey(), firstAttr);
        }
      } else {
        updateColors(firstAttrEntry.getKey(), firstFinalAttrMap, firstAttrEntry.getValue(), secondFinalAttrMap, 
                     secondAttrList);
      }
    }
    for (final Map.Entry<IAttrServices, List<IAttrServices>> secondDataEntry : secondAttrMap.entrySet()) {
      if (! firstAttrMap.containsKey(secondDataEntry.getKey())) {
        for (final IAttrServices secondAttr : secondDataEntry.getValue()) {
          secondAttr.setSWTColor(SWT.COLOR_BLUE, SWT.COLOR_BLUE);
          updateDataMap(firstFinalAttrMap, secondDataEntry.getKey(), secondAttr);
        }
      }
    }
    
    final StringBuilder sb = new StringBuilder();

    final List<StyleRange> styleRanges = new ArrayList<StyleRange>();
    if (firstFinalAttrMap.isEmpty() && secondFinalAttrMap.isEmpty()) {
      sb.append(LaunchMessages.ACLCS_SameConfig);
    } else {
      if (! firstFinalAttrMap.isEmpty()) {
        sb.append(firstConfig.getName()).append('\n');
      }

      for (final Map.Entry<IAttrServices, List<IAttrServices>> entry : firstFinalAttrMap.entrySet()) {
        entry.getKey().buildFormattedString(sb, getShell().getDisplay(), styleRanges);
        for (final IAttrServices attr : entry.getValue()) {
          attr.buildFormattedString(sb, getShell().getDisplay(), styleRanges);
        }
      }

      if (! secondFinalAttrMap.isEmpty()) {
        sb.append('\n').append(secondConfig.getName()).append('\n');
      }
      for (final Map.Entry<IAttrServices, List<IAttrServices>> entry : secondFinalAttrMap.entrySet()) {
        entry.getKey().buildFormattedString(sb, getShell().getDisplay(), styleRanges);
        for (final IAttrServices attr : entry.getValue()) {
          attr.buildFormattedString(sb, getShell().getDisplay(), styleRanges);
        }
      }
    }
    
    this.fResumeText.setText(sb.toString());
    if (! styleRanges.isEmpty()) {
      this.fResumeText.setStyleRanges(styleRanges.toArray(new StyleRange[styleRanges.size()]));
    }
    postProcess();
  }
  
  private void showLaunchConfigDiff(final ILaunchConfiguration firstConfig, final ILaunchConfiguration secondConfig) {
    final ICommunicationInterfaceConf commIntfConf = this.fX10PlatformConf.getCommunicationInterfaceConf();
    try {
      final ILaunchConfAttrDataBuilder attrServicesBuilder = AttrServicesFactory.createBuilder(commIntfConf);
      
      final Map<IAttrServices, List<IAttrServices>> firstAttrMap = new LinkedHashMap<IAttrServices, List<IAttrServices>>();
      attrServicesBuilder.buildAttrDataMap(firstConfig, firstAttrMap);
      final Map<IAttrServices, List<IAttrServices>> secondAttrMap = new LinkedHashMap<IAttrServices, List<IAttrServices>>();
      attrServicesBuilder.buildAttrDataMap(secondConfig, secondAttrMap);
      
      printConfigurationsDiff(firstConfig, secondConfig, firstAttrMap, secondAttrMap);
    } catch (CoreException except) {
      this.fResumeText.setText(NLS.bind(Messages.AXLS_DiffError, StringUtils.getStackTrace(except)));
    }
    this.fScrolledCompo.setMinSize(this.fResumeText.computeSize(SWT.DEFAULT, SWT.DEFAULT, true));
  }
  
  private void showLaunchConfigResume(final ILaunchConfiguration launchConfig) {
    final ICommunicationInterfaceConf commIntfConf = this.fX10PlatformConf.getCommunicationInterfaceConf();
    try {
      final ILaunchConfAttrDataBuilder attrDataBuilder = AttrServicesFactory.createBuilder(commIntfConf);
      
      final Map<IAttrServices, List<IAttrServices>> attrMap = new LinkedHashMap<IAttrServices, List<IAttrServices>>();
      attrDataBuilder.buildAttrDataMap(launchConfig, attrMap);
      
      printConfigurationResume(launchConfig, attrMap);
    } catch (CoreException except) {
      this.fResumeText.setText(NLS.bind(Messages.AXLS_ResumeError, StringUtils.getStackTrace(except)));
    }
    this.fScrolledCompo.setMinSize(this.fResumeText.computeSize(SWT.DEFAULT, SWT.DEFAULT, true));
  }
  
  private void updateColors(final IAttrServices attrDataKey, final Map<IAttrServices, List<IAttrServices>> firstFinalAttrMap,
                            final List<IAttrServices> firstAttrDataList, 
                            final Map<IAttrServices, List<IAttrServices>> secondFinalAttrMap,
                            final List<IAttrServices> secondAttrDataList) {
    for (final IAttrServices firstAttrData : firstAttrDataList) {
      final int index = secondAttrDataList.indexOf(firstAttrData);
      if (index == -1) {
        firstAttrData.setSWTColor(SWT.COLOR_BLUE, SWT.COLOR_BLUE);
        updateDataMap(firstFinalAttrMap, attrDataKey, firstAttrData);
      } else {
        final IAttrServices secondAttrData = secondAttrDataList.get(index);
        if (! firstAttrData.hasSameValue(secondAttrData)) {
          firstAttrData.setSWTColor(SWT.NONE, SWT.COLOR_RED);
          secondAttrData.setSWTColor(SWT.NONE, SWT.COLOR_RED);
          
          updateDataMap(firstFinalAttrMap, attrDataKey, firstAttrData);
          updateDataMap(secondFinalAttrMap, attrDataKey, secondAttrData);
        }
      }
    }
    for (final IAttrServices secondAttrData : secondAttrDataList) {
      if (! firstAttrDataList.contains(secondAttrData)) {
        secondAttrData.setSWTColor(SWT.COLOR_BLUE, SWT.COLOR_BLUE);
        updateDataMap(secondFinalAttrMap, attrDataKey, secondAttrData);
      }
    }
  }
  
  private void updateDataMap(final Map<IAttrServices, List<IAttrServices>> attrDataMap, final IAttrServices key, 
                             final IAttrServices value) {
    final List<IAttrServices> list = attrDataMap.get(key);
    if (list == null) {
      final List<IAttrServices> newList = new ArrayList<IAttrServices>();
      newList.add(value);
      attrDataMap.put(key, newList);
    } else {
      list.add(value);
    }
  }

  // --- Private classes
  
  private static final class LabelProvider extends BaseLabelProvider implements ILabelProvider {

    // --- ILabelProvider's interface methods implementation
    
    public Image getImage(final Object element) {
      return null;
    }

    public String getText(final Object element) {
      return ((ILaunchConfiguration) element).getName();
    }
    
  }
  
  private final class LaunchConfListener implements ILaunchConfigurationListener {

    // --- Interface methods implementation
    
    public void launchConfigurationAdded(final ILaunchConfiguration configuration) {
      this.fHasChanged = true;
      SelectExistingLaunchConfigsDialog.this.fConfigList.add(configuration);
    }

    public void launchConfigurationChanged(final ILaunchConfiguration configuration) {
    }

    public void launchConfigurationRemoved(final ILaunchConfiguration configuration) {
      this.fHasChanged = true;
      SelectExistingLaunchConfigsDialog.this.fConfigList.remove(configuration);
    }
    
    // --- Internal services
    
    boolean hasChanged() {
      return this.fHasChanged;
    }
    
    // --- Fields
    
    private boolean fHasChanged;
    
  }
  
  // --- Fields
  
  private final List<ILaunchConfiguration> fConfigList;
  
  private final String fGroupIdentifier;
  
  private ScrolledComposite fScrolledCompo;
  
  private StyledText fResumeText;
  
  private Image fGradientImage;
  
  private IX10PlatformConf fX10PlatformConf;
  
  
  private static final int CREATE_NEW_ID = 100;
  
  private static final int EDIT_ID = 101;
  
}
