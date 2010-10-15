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

/*
 * (C) Copyright IBM Corporation 2007
 * 
 * This file is part of the Eclipse IMP.
 */
package x10dt.ui;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.imp.editor.UniversalEditor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PerspectiveAdapter;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.contexts.IContextActivation;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import x10dt.search.ui.typeHierarchy.X10Constants;

public class X10DTUIPlugin extends AbstractUIPlugin {
    public static final String PLUGIN_ID= "x10dt.ui";
    public static final String PERSPECTIVE_SCOPE = PLUGIN_ID + ".x10PerspectiveScope";
    public static final String EDITOR_SCOPE = PLUGIN_ID + ".x10EditorScope";
    
    private static X10DTUIPlugin sInstance;

    private static ILog sLog;

    public X10DTUIPlugin() {
	super();
	sInstance= this;
    }

    public static X10DTUIPlugin getInstance() {
	return sInstance;
    }

	public void maybeWriteInfoMsg(String msg) {
    	// BRT fEmitInfoMessages not read here, always write info msg for now, until can determine how to read/write correctly
//        if (!fEmitInfoMessages) 
//            return;

        Status status= new Status(Status.INFO, PLUGIN_ID, 0, msg, null);
    
        if (sLog == null)
            sLog= getLog();
    
        sLog.log(status);
    }

    public void writeErrorMsg(String msg) {
        Status status= new Status(Status.ERROR, PLUGIN_ID, 0, msg, null);
    
        if (sLog == null)
            sLog= getLog();
    
        sLog.log(status);
    }

    private void initImageRegistry() {
	fgIconBaseURL= getBundle().getEntry("/icons/"); //$NON-NLS-1$
	RUNTIME_IMG_DESC= create(RUNTIME_IMG_NAME);
	getInstance().getImageRegistry().put(RUNTIME_IMG_NAME, RUNTIME_IMG_DESC);
	
	
    }

    public static void log(Exception e) {
	getInstance().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, 0, e.getMessage(), e));
    }

    public static final String RUNTIME_IMG_NAME= "runtime_obj.gif";
    public static ImageDescriptor RUNTIME_IMG_DESC;
    
   

    private static URL fgIconBaseURL= null;

    private static URL makeIconFileURL(String name) throws MalformedURLException {
	if (fgIconBaseURL == null)
	    sInstance.initImageRegistry();
//	    throw new MalformedURLException();

	return new URL(fgIconBaseURL, name);
    }

    public static ImageDescriptor create(String name) {
	try {
	    return ImageDescriptor.createFromURL(makeIconFileURL(name));
	} catch (MalformedURLException e) {
	    return ImageDescriptor.getMissingImageDescriptor();
	}
    }

    /**
     * Returns the image managed under the given key in this registry.
     * 
     * @param key the image's key
     * @return the image managed under the given key
     */
    public Image getImage(String key) {
	if (fgIconBaseURL == null)
	    initImageRegistry();
	return getInstance().getImageRegistry().get(key);
    }
}
