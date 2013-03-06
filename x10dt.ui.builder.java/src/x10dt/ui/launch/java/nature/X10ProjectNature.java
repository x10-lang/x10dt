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

package x10dt.ui.launch.java.nature;

import org.eclipse.core.resources.IProject;
import org.eclipse.imp.builder.ProjectNatureBase;
import org.eclipse.imp.runtime.IPluginLog;

import x10dt.core.X10DTCorePlugin;
import x10dt.ui.builder.java.Activator;

public class X10ProjectNature extends ProjectNatureBase {

    public String getNatureID() {
        return X10DTCorePlugin.X10_PRJ_JAVA_NATURE_ID;
    }

    public String getBuilderID() {
        return Activator.BUILDER_ID;
    }

    public void addToProject(IProject project) {
        super.addToProject(project);
    }

    protected void refreshPrefs() {}

    public IPluginLog getLog() {
        return X10DTCorePlugin.getInstance();
    }
   
}
