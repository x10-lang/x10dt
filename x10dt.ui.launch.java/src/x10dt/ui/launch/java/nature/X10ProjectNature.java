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

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.imp.builder.ProjectNatureBase;
import org.eclipse.imp.runtime.IPluginLog;
import org.eclipse.imp.smapifier.builder.SmapiProjectNature;
import org.eclipse.jdt.core.JavaCore;

import x10dt.core.X10DTCorePlugin;
import x10dt.ui.launch.java.Activator;

public class X10ProjectNature extends ProjectNatureBase {
    
    public String getNatureID() {
        return Activator.X10_PRJ_JAVA_NATURE_ID;
    }

    public String getBuilderID() {
        return Activator.BUILDER_ID;
    }

    public void addToProject(IProject project) {
        super.addToProject(project);
        new SmapiProjectNature("x10").addToProject(project);
    }

    protected void refreshPrefs() {}

    protected String getDownstreamBuilderID() {
        return "org.eclipse.jdt.core.javabuilder";
    }

    public IPluginLog getLog() {
        return X10DTCorePlugin.getInstance();
    }
    
    @Override
    public void configure() throws CoreException {
      super.configure();
      final IProject project = getProject();
      final IProjectDescription description = project.getDescription();
      final ICommand[] commands = description.getBuildSpec();
      if (hasJavaBuilder(commands)) {
        final ICommand[] newCommands = new ICommand[commands.length - 1];
        for(int i = 0; i < commands.length; i++){
          if (!commands[i].getBuilderName().equals(JavaCore.BUILDER_ID)){
            newCommands[i] = commands[i];
          }
        }
        description.setBuildSpec(newCommands);
        project.setDescription(description, new NullProgressMonitor());
      }
    }
    
    private boolean hasJavaBuilder(final ICommand[] commands) {
      for (final ICommand command : commands) {
        if (JavaCore.BUILDER_ID.equals(command.getBuilderName())) {
          return true;
        }
      }
      return false;
    }
    
}
