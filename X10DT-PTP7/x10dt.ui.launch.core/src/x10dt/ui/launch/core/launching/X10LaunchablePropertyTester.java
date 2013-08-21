/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.core.launching;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.imp.preferences.PreferencesService;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PlatformUI;

import x10dt.core.preferences.generated.X10Constants;
import x10dt.search.core.elements.ITypeInfo;
import x10dt.ui.launch.core.LaunchCore;
import x10dt.ui.utils.LaunchUtils;

/**
 * Defines the "hasMain" property tester.
 * 
 * @author egeay
 */
public class X10LaunchablePropertyTester extends PropertyTester {

  // --- Interface methods implementation
  
  public boolean test(final Object receiver, final String property, final Object[] args, final Object expectedValue) {
    final IFile file = getFileFor(receiver);
    if (file == null) {
      return false;
    }

    if (! PROPERTY_HAS_MAIN.equals(property)) {
      // N.B. if we get here, the extension is wrong, since this class handles only this one property
      LaunchCore.log(IStatus.ERROR, 
                     String.format("Attempt to use X10LaunchablePropertyTester for a property it doesn't handle: %s", //$NON-NLS-1$ 
                                   property));
      return false;
    }
    
    final String launchMode = (args.length > 0) ? (String) args[0] : null;
    final String projectNatureId = (args.length > 1) ? (String) args[1] : null;
    
    if (! hasMain(file, projectNatureId)) {
      return false;
    }
    if (launchMode != null) {
      if (ILaunchManager.DEBUG_MODE.equals(launchMode)) {
        // Check that the -DEBUG preference is on; otherwise, debugging any X10 program in this
        // project is not possible, since the necessary debugging info won't be present.
        if (! new PreferencesService(file.getProject()).getBooleanPreference(X10Constants.P_DEBUG)) {
          return false;
        }
      }
    }
    return true;
  }

  // --- Private code
  
  private IFile getFileFor(final Object receiver) {
    if (receiver instanceof IAdaptable) {
      final IAdaptable adaptableReceiver = (IAdaptable) receiver;
      IFile file = (IFile) adaptableReceiver.getAdapter(IFile.class);

      if (file == null) {
        final IFileEditorInput fileEditorInput = (IFileEditorInput) adaptableReceiver.getAdapter(IFileEditorInput.class);

        if (fileEditorInput != null) {
          file = fileEditorInput.getFile();
        }
      }
      return file;
    }
    return null;
  }
    
  private boolean hasMain(final IFile file, final String projectNatureId) {
    if (X10_EXT.equals(file.getFileExtension())) {
      try {
        // Pass null for the IProgressService here - on Win32, if the property
        // tester uses a progress monitor, that could cause the owning context
        // menu to be dismissed. Ultimately, this causes an SWT invalid widget
        // exception, b/c the menu has already been dispose()'d.
        // 
        final ITypeInfo[] mainTypes = LaunchUtils.findMainTypes(new IResource[] { file }, projectNatureId, 
                                                                null /*PlatformUI.getWorkbench().getProgressService()*/);
        return mainTypes.length > 0;
      } catch (Exception except) {
        // Simply forget
      }
    }
    return false;
  }
  
  // --- Fields
  
  private static final String PROPERTY_HAS_MAIN = "hasMain"; //$NON-NLS-1$
  
  private static final String X10_EXT = "x10"; //$NON-NLS-1$

}
