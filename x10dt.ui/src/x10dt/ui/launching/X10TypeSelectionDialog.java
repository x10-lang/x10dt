/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launching;

import java.net.MalformedURLException;
import java.util.Comparator;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;

import x10dt.search.core.elements.ITypeInfo;
import x10dt.ui.Messages;
import x10dt.ui.X10DTUIPlugin;

/**
 * Selection dialog to select a list of X10 types among a list of types provided.
 * 
 * @author egeay
 */
public final class X10TypeSelectionDialog extends FilteredItemsSelectionDialog {
  
  /**
   * Creates the selection dialog with the required shell and the help of the X10 types transmitted.
   * 
   * @param shell The shell to consider for the selection dialog.
   * @param types The list of X10 types that represents the scope of selection.
   */
  public X10TypeSelectionDialog(final Shell shell, final ITypeInfo[] types) {
    super(shell, false);
    
    this.fTypes = types;
    
    setTitle(Messages.XTSD_SelectX10TypeDlgTitle);
    setMessage(Messages.XTSD_SelectX10TypeMsg);
    setInitialPattern("**"); //$NON-NLS-1$
    setListLabelProvider(new X10TypeLabelProvider());
    setDetailsLabelProvider(new X10DetailsLabelProvider());
  }
  
  // --- Abstract methods implementation

  protected Control createExtendedContentArea(final Composite parent) {
    // Nothing to do.
    return null;
  }

  protected ItemsFilter createFilter() {
    return new X10TypeItemsFilter();
  }

  protected void fillContentProvider(final AbstractContentProvider contentProvider, final ItemsFilter itemsFilter,
                                     final IProgressMonitor progressMonitor) throws CoreException {
    for (final ITypeInfo type : this.fTypes) {
      if (itemsFilter.isConsistentItem(type)) {
        contentProvider.add(type, itemsFilter);
      }
    }
  }

  protected IDialogSettings getDialogSettings() {
    final IDialogSettings dialogSettings = X10DTUIPlugin.getInstance().getDialogSettings();
    IDialogSettings section = dialogSettings.getSection(SETTINGS_ID);
    if (section == null) {
      section = dialogSettings.addNewSection(SETTINGS_ID);
    } 
    return section;
  }

  public String getElementName(final Object item) {
    return ((ITypeInfo) item).getName();
  }

  protected Comparator<?> getItemsComparator() {
    return new X10TypesComparator();
  }

  protected IStatus validateItem(final Object item) {
    return Status.OK_STATUS;
  }
  
  // --- Private code
  
  private static final class X10TypesComparator implements Comparator<Object> {

    public int compare(final Object lhs, final Object rhs) {
      return ((ITypeInfo) lhs).getName().compareTo(((ITypeInfo) rhs).getName());
    }
    
  }
  
  private final class X10TypeItemsFilter extends ItemsFilter {

    // --- Abstract methods implementation
    
    public boolean isConsistentItem(final Object item) {
      return item instanceof ITypeInfo;
    }

    public boolean matchItem(final Object item) {
      for (final ITypeInfo type : X10TypeSelectionDialog.this.fTypes) {
        if (type.equals(item)) {
          return true;
        }
      }
      return false;
    }
    
  }
  
  private static final class X10DetailsLabelProvider implements ILabelProvider {

    // --- IBaseLabelProvider's interface methods implementation
    
    public void addListener(final ILabelProviderListener listener) {
    }

    public void dispose() {
    }

    public boolean isLabelProperty(final Object element, final String property) {
      return false;
    }

    public void removeListener(final ILabelProviderListener listener) {
    }
    
    // --- ILabelProvider's interface methods implementation

    public Image getImage(final Object element) {
      return null;
    }

    public String getText(final Object element) {
      if (element == null) {
        return null;
      }
      final ITypeInfo type = (ITypeInfo) element;
      final StringBuilder sb = new StringBuilder();
      if (type.getCompilationUnit() != null) {
        final IContainer container = type.getCompilationUnit().getResource().getParent();
        sb.append(container.getFullPath().toString()).append(SEPARATOR);
      }
      try {
        sb.append(type.getLocation().getURI().toURL().getFile());
      } catch (MalformedURLException except) {
        sb.append(type.getLocation().getURI().toString());
      }
      return sb.toString();
    }
    
  }
  
  private static final class X10TypeLabelProvider implements ILabelProvider {

    // --- IBaseLabelProvider's interface methods implementation
    
    public void addListener(final ILabelProviderListener listener) {
    }

    public void dispose() {
    }

    public boolean isLabelProperty(final Object element, final String property) {
      return false;
    }

    public void removeListener(final ILabelProviderListener listener) {
    }
    
    // --- ILabelProvider's interface methods implementation
    
    public Image getImage(final Object element) {
      return null;
    }

    public String getText(final Object element) {
      if (element == null) {
        return null;
      }
      final ITypeInfo type = (ITypeInfo) element;
      final StringBuilder sb = new StringBuilder();
      sb.append(type.getName());
      if (type.getCompilationUnit() != null) {
        final IContainer container = type.getCompilationUnit().getResource().getParent();
        if (container.getName().length() > 0) {
          sb.append(SEPARATOR).append(container.getName());
        }
      }
      return sb.toString();
    }
    
  }
  
  // --- Fields
  
  private final ITypeInfo[] fTypes;
  
  private static final String SETTINGS_ID = X10DTUIPlugin.PLUGIN_ID + ".MAIN_METHOD_SELECTION_DIALOG"; //$NON-NLS-1$
  
  private static final String SEPARATOR = " - "; //$NON-NLS-1$

}
