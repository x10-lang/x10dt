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

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.imp.builder.ProjectNatureBase;
import org.eclipse.imp.runtime.IPluginLog;
import org.eclipse.jdt.core.JavaCore;


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
    
    @Override
    public void configure() throws CoreException {
      super.configure();
      final IProject project = getProject();
      final IProjectDescription description = project.getDescription();
      final ICommand[] commands = description.getBuildSpec();
      ICommand javaBuilder = getJavaBuilder(commands);
      if (javaBuilder == null) {
    	    javaBuilder = description.newCommand();
    	    javaBuilder.setBuilderName(JavaCore.BUILDER_ID);
    	    final ICommand[] newCommands = new ICommand[commands.length + 2]; 
    	    System.arraycopy(commands, 0, newCommands, 1, commands.length);
    	    newCommands[0] = javaBuilder;
    	    newCommands[commands.length+1] = javaBuilder;
    	    description.setBuildSpec(newCommands);
    	    project.setDescription(description, new NullProgressMonitor());  
    	    return;
    	    
      }
      
      final ICommand[] newCommands = new ICommand[commands.length + 1]; 
      System.arraycopy(commands, 0, newCommands, 0, commands.length);
  	  newCommands[commands.length] = javaBuilder;
      description.setBuildSpec(newCommands);
      project.setDescription(description, new NullProgressMonitor());
      
    }
    
    private ICommand getJavaBuilder(final ICommand[] commands) {
      for (final ICommand command : commands) {
        if (JavaCore.BUILDER_ID.equals(command.getBuilderName())) {
          return command;
        }
      }
      return null;
    }

   
}
