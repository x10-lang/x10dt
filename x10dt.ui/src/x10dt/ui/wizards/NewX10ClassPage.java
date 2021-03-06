/*******************************************************************************
 * Copyright (c) 2008 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Robert Fuhrer (rfuhrer@watson.ibm.com) - initial API and implementation
 *******************************************************************************/

package x10dt.ui.wizards;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.dialogs.StatusInfo;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.LayoutUtil;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.SelectionButtonDialogFieldGroup;
import org.eclipse.jdt.ui.wizards.NewTypeWizardPage;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import x10dt.core.X10DTCorePlugin;
import x10dt.search.core.elements.ITypeInfo;
import x10dt.search.core.engine.X10SearchEngine;
import x10dt.search.core.engine.scope.IX10SearchScope;
import x10dt.search.core.engine.scope.SearchScopeFactory;
import x10dt.search.core.engine.scope.X10SearchScope;

/**
 * The "New X10 Class" wizard page allows setting the package for the new class as well as the class name.
 */
public class NewX10ClassPage extends NewTypeWizardPage {
  private final static String SETTINGS_CREATEMAIN = "create_main"; //$NON-NLS-1$

  private final static String SETTINGS_CREATECONSTR = "create_constructor"; //$NON-NLS-1$

  private final static String SETTINGS_CREATEUNIMPLEMENTED = "create_unimplemented"; //$NON-NLS-1$

  private static final String PAGE_NAME = "wizardPage";

  private SelectionButtonDialogFieldGroup fMethodStubsButtons;

  public NewX10ClassPage(ISelection selection) {
    super(true, PAGE_NAME);
    setTitle("X10 Class");
    setDescription("This wizard creates a new X10 class in a user-specified package.");
    String[] buttonNames = new String[] { NewWizardMessages.NewClassWizardPage_methods_main,
                                         NewWizardMessages.NewClassWizardPage_methods_constructors
    // NewWizardMessages.NewClassWizardPage_methods_inherited
    };
    fMethodStubsButtons = new SelectionButtonDialogFieldGroup(SWT.CHECK, buttonNames, 1);
    fMethodStubsButtons.setLabelText(NewWizardMessages.NewClassWizardPage_methods_label);
  }

  /**
   * The wizard owning this page is responsible for calling this method with the current selection. The selection is used to
   * initialize the fields of the wizard page.
   * 
   * @param selection
   *          used to initialize the fields
   */
  public void init(IStructuredSelection selection) {
    IJavaElement jelem = getInitialJavaElement(selection);
    initContainerPage(jelem);
    initTypePage(jelem);
    setSuperClass("x10.lang.Object", true);
    doStatusUpdate();

    // TODO RMF 2/7/2005 - re-enable the following to remember dialog
    // settings across invocations
    IDialogSettings dialogSettings = getDialogSettings();
    if (dialogSettings != null) {
      IDialogSettings section = dialogSettings.getSection(PAGE_NAME);

      boolean createMain = false;
      boolean createConstructors = false;
      boolean createUnimplemented = true;

      if (section != null) {
        createMain = section.getBoolean(SETTINGS_CREATEMAIN);
        createConstructors = section.getBoolean(SETTINGS_CREATECONSTR);
        createUnimplemented = section.getBoolean(SETTINGS_CREATEUNIMPLEMENTED);
      }
      setMethodStubSelection(createMain, createConstructors, false, true);
    }
  }

  // ------ validation --------
  private void doStatusUpdate() {
    // status of all used components
    IStatus[] status = new IStatus[] { fContainerStatus, isEnclosingTypeSelected() ? fEnclosingTypeStatus : fPackageStatus,
                                      fTypeNameStatus, fModifierStatus, fSuperClassStatus, fSuperInterfacesStatus };

    // the mode severe status will be displayed and the OK button
    // enabled/disabled.
    updateStatus(status);
  }

  public void createControl(Composite parent) {
    initializeDialogUnits(parent);

    Composite composite = new Composite(parent, SWT.NONE);
    composite.setFont(parent.getFont());

    int nColumns = 4;

    GridLayout layout = new GridLayout();
    layout.numColumns = nColumns;
    composite.setLayout(layout);

    // pick & choose the wanted UI components

    createContainerControls(composite, nColumns);
    createPackageControls(composite, nColumns);
    createEnclosingTypeControls(composite, nColumns);

    createSeparator(composite, nColumns);

    createTypeNameControls(composite, nColumns);
    createModifierControls(composite, nColumns);

    createSuperClassControls(composite, nColumns);
    createSuperInterfacesControls(composite, nColumns);

    createMethodStubSelectionControls(composite, nColumns);

    createCommentControls(composite, nColumns);
    enableCommentControl(true);

    setControl(composite);

    Dialog.applyDialogFont(composite);
    // PlatformUI.getWorkbench().getHelpSystem().setHelp(composite,
    // IJavaHelpContextIds.NEW_CLASS_WIZARD_PAGE);
    setFocus(); // For some reason, the type name doesn't get focus by the
                // time the dialog is fully up... force it
  }

  /*
   * @see NewContainerWizardPage#handleFieldChanged
   */
  protected void handleFieldChanged(String fieldName) {
    super.handleFieldChanged(fieldName);

    doStatusUpdate();
  }

  /**
   * Sets the selection state of the method stub checkboxes.
   * 
   * @param createMain
   *          initial selection state of the 'Create Main' checkbox.
   * @param createConstructors
   *          initial selection state of the 'Create Constructors' checkbox.
   * @param createInherited
   *          initial selection state of the 'Create inherited abstract methods' checkbox.
   * @param canBeModified
   *          if <code>true</code> the method stub checkboxes can be changed by the user. If <code>false</code> the buttons are
   *          "read-only"
   */
  public void setMethodStubSelection(boolean createMain, boolean createConstructors, boolean createInherited,
                                     boolean canBeModified) {
    fMethodStubsButtons.setSelection(0, createMain);
    fMethodStubsButtons.setSelection(1, createConstructors);
    fMethodStubsButtons.setSelection(2, createInherited);

    fMethodStubsButtons.setEnabled(canBeModified);
  }

  /*
   * @see WizardPage#becomesVisible
   */
  public void setVisible(boolean visible) {
    super.setVisible(visible);
    if (visible) {
      setFocus();
    } else {
      IDialogSettings dialogSettings = getDialogSettings();
      if (dialogSettings != null) {
        IDialogSettings section = dialogSettings.getSection(PAGE_NAME);
        if (section == null) {
          section = dialogSettings.addNewSection(PAGE_NAME);
        }
        section.put(SETTINGS_CREATEMAIN, isCreateMain());
        section.put(SETTINGS_CREATECONSTR, isCreateConstructors());
        section.put(SETTINGS_CREATEUNIMPLEMENTED, isCreateInherited());
      }
    }
  }

  private void createMethodStubSelectionControls(Composite composite, int nColumns) {
    Control labelControl = fMethodStubsButtons.getLabelControl(composite);
    LayoutUtil.setHorizontalSpan(labelControl, nColumns);

    DialogField.createEmptySpace(composite);

    Control buttonGroup = fMethodStubsButtons.getSelectionButtonsGroup(composite);
    LayoutUtil.setHorizontalSpan(buttonGroup, nColumns - 1);
  }

  /**
   * Returns the current selection state of the 'Create Main' checkbox.
   * 
   * @return the selection state of the 'Create Main' checkbox
   */
  public boolean isCreateMain() {
    return fMethodStubsButtons.isSelected(0);
  }

  /**
   * Returns the current selection state of the 'Create Constructors' checkbox.
   * 
   * @return the selection state of the 'Create Constructors' checkbox
   */
  public boolean isCreateConstructors() {
    return fMethodStubsButtons.isSelected(1);
  }

  /**
   * Returns the current selection state of the 'Create inherited abstract methods' checkbox.
   * 
   * @return the selection state of the 'Create inherited abstract methods' checkbox
   */
  public boolean isCreateInherited() {
    return fMethodStubsButtons.isSelected(2);
  }

  public void createType(IProgressMonitor monitor) throws CoreException, InterruptedException {
    IPackageFragment pkgFrag = this.getPackageFragment();
    String superClass = this.getSuperClass();
    @SuppressWarnings("unchecked")
    List<String> superIntfs = this.getSuperInterfaces();
    String typeName = this.getTypeName();

    doCreateType(typeName, pkgFrag, superClass, superIntfs, monitor);
  }

  /**
   * The worker method. It will find the container, create the file if missing or just replace its contents, and open the
   * editor on the newly created file.
   */
  private void doCreateType(String typeName, IPackageFragment pkgFrag, String superClass, List<String> superIntfs,
                            IProgressMonitor monitor) throws CoreException {
    monitor.beginTask("Creating " + typeName, 2);
    if (!pkgFrag.exists()) {
      String pkgName = pkgFrag.getElementName();
      IPackageFragmentRoot root = this.getPackageFragmentRoot();

      if (!root.exists()) {
          IJavaProject javaProject= getJavaProject();
          IClasspathEntry newEntry= JavaCore.newSourceEntry(new Path(getPackageFragmentRootText()).makeAbsolute());
          IClasspathEntry[] entries= javaProject.getRawClasspath();
          IClasspathEntry[] newEntries= new IClasspathEntry[entries.length + 1];

          System.arraycopy(entries, 0, newEntries, 0, entries.length);
          newEntries[entries.length]= newEntry;
          javaProject.setRawClasspath(newEntries, new NullProgressMonitor());

          root= (IPackageFragmentRoot) JavaCore.create(javaProject.getProject().getWorkspace().getRoot().getFolder(newEntry.getPath()));
      }
      pkgFrag = root.createPackageFragment(pkgName, true, null);
    }
    IResource resource = pkgFrag.getCorrespondingResource();
    IContainer container = (IContainer) resource;

    final IFile file = container.getFile(new Path(typeName + ".x10"));
    try {
      boolean doComments = isAddComments();
      InputStream stream = createContentStream(file, typeName, pkgFrag, superClass, superIntfs, isCreateMain(),
                                               isCreateConstructors(), doComments);

      if (file.exists()) {
        file.setContents(stream, true, true, monitor);
      } else {
        file.create(stream, IResource.FORCE | IResource.KEEP_HISTORY, monitor);
      }
      stream.close();
    } catch (IOException e) {
    }
    monitor.worked(1);
  }

  /**
   * Initialize the given file's contents with stub source code.
   * 
   * @param sourceFile
   */
  protected static InputStream createContentStream(IFile sourceFile, String typeName, IPackageFragment pkgFrag,
                                                   String superClass, List<String> superIntfs, boolean createMain,
                                                   boolean createConstructors, boolean createComments) {
    StringBuffer buff = new StringBuffer();

    if (!pkgFrag.isDefaultPackage())
      buff.append("package " + pkgFrag.getElementName() + ";\n\n");
    if (createComments) {
      buff.append("/**\n");
      buff.append(" * Class " + typeName + "\n");
      buff.append(" */\n");
    }
    buff.append("public class " + typeName);
    if (superClass != null && superClass.length() > 0 && !superClass.equals("x10.lang.Object"))
      buff.append(" extends " + superClass);
    if (superIntfs.size() > 0) {
      buff.append(" implements ");
      for (Iterator iter = superIntfs.iterator(); iter.hasNext();) {
        String superIntf = (String) iter.next();
        buff.append(superIntf);
        if (iter.hasNext())
          buff.append(", ");
      }
    }
    buff.append(" {\n");

    if (createMain) {
      if (createComments) {
        buff.append("    /**\n");
        buff.append("     * Main method " + "\n");
        buff.append("     */\n");
      }
      buff.append("    public static def main(args: Rail[String]) {\n");
      buff.append("        // TODO auto-generated stub\n");
      buff.append("    }\n");
    }
    if (createConstructors) {
      if (createComments) {
        buff.append("    /**\n");
        buff.append("     * Default constructor \n");
        buff.append("     */\n");
      }
      buff.append("    public def this() {\n");
      buff.append("        // TODO auto-generated stub\n");
      buff.append("    }\n");
    }
    buff.append("}");

    return new ByteArrayInputStream(buff.toString().getBytes());
  }

  /**
   * Returns the resource handle that corresponds to the compilation unit to was or will be created or modified.
   * 
   * @return A resource or null if the page contains illegal values.
   * @since 3.0
   */
  public IResource getModifiedResource() {
    IType enclosing = getEnclosingType();

    if (enclosing != null) {
      return enclosing.getResource();
    }

    IPackageFragment pack = getPackageFragment();

    if (pack != null) {
      IContainer packCont;
      try {
        packCont = (IContainer) pack.getCorrespondingResource();
      } catch (JavaModelException e) {
        X10DTCorePlugin.getInstance().writeErrorMsg(e.getMessage());
        return null;
      }
      return packCont.getFile(new Path(getTypeName() + ".x10"));
    }
    return null;
  }
  
	/**
	 * Hook method that gets called when the type name has changed. The method validates the
	 * type name and returns the status of the validation.
	 * <p>
	 * Subclasses may extend this method to perform their own validation.
	 * </p>
	 *
	 * @return the status of the validation
	 */
	protected IStatus typeNameChanged() {	
		final StatusInfo status = new StatusInfo();
		try {
			final String type = getTypeName();
			final IPackageFragment pack = getPackageFragment();
			final String fullyQualifiedType = pack.getElementName() == "" ? type : pack.getElementName() + "." + type;
			final IResource project = pack.getResource().getProject();
			final IX10SearchScope scope = SearchScopeFactory.createSelectiveScope(X10SearchScope.ALL, project);
			final ITypeInfo[] results = X10SearchEngine.getTypeInfo(scope, fullyQualifiedType, new NullProgressMonitor());
			if (results.length > 0) { 
				status.setError(NewWizardMessages.NewTypeWizardPage_error_TypeNameExists);
				return status;
			}
		} catch (InterruptedException except) {
		  status.setError(except.getMessage());
		}
		return super.typeNameChanged();
	}

}
