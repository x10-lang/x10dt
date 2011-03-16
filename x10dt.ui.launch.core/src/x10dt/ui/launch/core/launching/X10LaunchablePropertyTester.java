/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.core.launching;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.imp.preferences.PreferencesService;
import org.eclipse.ui.IFileEditorInput;

import polyglot.types.ClassType;
import x10dt.core.preferences.generated.X10Constants;
import x10dt.ui.X10DTUIPlugin;
import x10dt.ui.launching.ResourceToJavaElementAdapter;
import x10dt.ui.utils.X10Utils;

/**
 * Defines the "hasMain" property tester.
 * 
 * @author egeay
 */
public class X10LaunchablePropertyTester extends PropertyTester {

  // --- Interface methods implementation
  
  public boolean test(final Object receiver, final String property, final Object[] args, final Object expectedValue) {
    IFile file = getFileFor(receiver);

    if (file == null) {
      X10DTUIPlugin.getInstance().writeErrorMsg("Attempt to use X10LaunchablePropertyTester to test a non-file object: " + receiver.getClass());
      return false;
    }

    if (!PROPERTY_HAS_MAIN.equals(property)) {
      // N.B. if we get here, the extension is wrong, since this class handles only this one property
      X10DTUIPlugin.getInstance().writeErrorMsg("Attempt to use X10LaunchablePropertyTester for a property it doesn't handle: " + property);
      return false;
    }
    if (!hasMain(file)) {
      return false;
    }
    if (args.length > 0) {
      String launchMode = (String) args[0];
      if (ILaunchManager.DEBUG_MODE.equals(launchMode)) {
        // Check that the -DEBUG preference is on; otherwise, debugging any X10 program in this
        // project is not possible, since the necessary debugging info won't be present.
        if (!new PreferencesService(file.getProject()).getBooleanPreference(X10Constants.P_DEBUG)) {
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
    
  private boolean hasMain(final IFile file) {
    if (X10_EXT.equals(file.getFileExtension())) {
      try {
        final Collection<ClassType> x10Types = new ArrayList<ClassType>();
        X10Utils.collectX10MainTypes(x10Types, new ResourceToJavaElementAdapter(file), new NullProgressMonitor());
        if (x10Types.size() == 0) {
          return false;
        } else {
          return true;
        }
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
