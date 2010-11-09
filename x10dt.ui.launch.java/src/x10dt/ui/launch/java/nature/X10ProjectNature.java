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
package x10dt.ui.launch.java.nature;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.imp.builder.ProjectNatureBase;
import org.eclipse.imp.runtime.IPluginLog;
import org.eclipse.imp.smapifier.builder.SmapiProjectNature;

import x10dt.core.X10DTCorePlugin;
import x10dt.ui.launch.core.LaunchCore;
import x10dt.ui.launch.java.Activator;

public class X10ProjectNature extends ProjectNatureBase {
    
    public String getNatureID() {
        return LaunchCore.X10_PRJ_JAVA_NATURE_ID;
    }

    public String getBuilderID() {
        return Activator.BUILDER_ID;
    }

    public void addToProject(IProject project) {
        super.addToProject(project);
        new SmapiProjectNature("x10").addToProject(project);
    }

    protected void refreshPrefs() {}

    public IPluginLog getLog() {
        return X10DTCorePlugin.getInstance();
    }
    
    @Override
    public void configure() throws CoreException {
      super.configure();
    }
    
}
