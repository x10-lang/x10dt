/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.actions;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.imp.editor.EditorUtility;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import x10dt.core.utils.URIUtils;
import x10dt.ui.launch.core.actions.AbstractConvertX10ProjectAction;
import x10dt.ui.launch.core.actions.IPlatformConfHider;
import x10dt.ui.launch.cpp.platform_conf.X10PlatformConfFactory;

/**
 * Action to convert a C++ back-end nature to Java back-end nature.
 * 
 * @author egeay
 */
public final class ConvertToJavaBackEndAction extends AbstractConvertX10ProjectAction implements IObjectActionDelegate {
  
  // --- Abstract methods implementation
  
  protected String getTargetBackEndType() {
    return "java"; //$NON-NLS-1$
  }
  
  protected IPlatformConfHider createPlatformConfHider(final IProject project){
	  return new IPlatformConfHider(){
		public void hide() {
			
			final IFile platformConfFile = X10PlatformConfFactory.getFile(project);
			
			// --- Close the editor if it's open.
			final IWorkbenchPage activePage= PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IEditorPart editor= EditorUtility.isOpenInEditor(platformConfFile.getFullPath());
			if (editor != null) {
				activePage.closeEditor(editor, false);
			}
			
			// --- Rename the platformConf file to start with "."
			IFile hiddenFile = ResourcesPlugin.getWorkspace().getRoot().getFile(platformConfFile.getFullPath().removeLastSegments(1).append(new Path("." + platformConfFile.getName())));
			IFileStore hiddenStore = EFS.getLocalFileSystem().getStore(URIUtils.getExpectedURI(hiddenFile.getLocationURI()));
			try {
				EFS.getLocalFileSystem().getStore(URIUtils.getExpectedURI(platformConfFile.getLocationURI())).move(hiddenStore, EFS.OVERWRITE, new NullProgressMonitor());
			} catch (CoreException e) {
				//TODO
			}
			
			
			 
		}
	  };
  }
  
}
