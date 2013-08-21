package x10dt.ui.launch.core.builder;
/*******************************************************************************
* Copyright (c) 2008,2009 IBM Corporation.
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

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.imp.builder.DependencyInfo;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

import polyglot.types.ClassType;
import polyglot.types.Type;
import polyglot.types.Types;
import polyglot.util.Position;
import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.LaunchCore;
import x10dt.ui.launch.core.utils.ProjectUtils;

/**
 * A compilation unit-level dependency tracking manager that speaks in terms of Polyglot Type objects.
 * @author rfuhrer
 */
public class PolyglotDependencyInfo extends DependencyInfo {
	private IJavaProject fJavaProject ;
    public PolyglotDependencyInfo(IJavaProject project) {
        super(project.getProject());
        this.fJavaProject = project;
    }
    
    protected ClassType getClassType(Type type){
    	ClassType classType = null;
    	if (type.isClass()){
    		classType= (ClassType) Types.baseType(type);
    	} else {
    		return null;
    	}
    	return classType;
  
    }
    
    private Collection<String> getPossibleFileNames(String name){
    	Collection<String> result = new ArrayList<String>();
    	name = name.replace('.', File.separatorChar);
    	result.add(name + Constants.X10_EXT);
    	result.add(name + Constants.CLASS_EXT);
    	if (!name.contains(File.separator)){
    		return result;
    	}
    	int i = name.lastIndexOf(File.separatorChar);
    	while(i != -1){
    		name = name.substring(0, i);
    		result.add(name + Constants.X10_EXT);
    		result.add(name + Constants.CLASS_EXT);
    		i = name.lastIndexOf(File.separatorChar);
    	}
 
    	return result;
    }
    
    private Collection<String> getPossiblePaths(ClassType type, String context){
    	Collection<String> result = new ArrayList<String>();
    	String name = type.fullName().toString();
    	Collection<String> srcFolders = getSrcFolders();
    	String pac = getPackageFragment(context, srcFolders);
    	for(String path: srcFolders){
    		Collection<String> names = getPossibleFileNames(name);
    		for (String n: names){
    			String middle = !pac.equals("") ? pac + File.separator : "";
    			if (path.endsWith("src-java")) {
    			  if (n.endsWith(Constants.CLASS_EXT)){
    			    result.add(path.replace("src-java", "bin-java") + File.separator + middle + n);
    			  }
    			} else {
    			  if (!n.endsWith(Constants.CLASS_EXT)){
    			    result.add(path + File.separator + middle + n);
    			  }
    			}
    		}
    	}
    	
    	name = name + Constants.X10_EXT; 
    	Collection<String> possiblePaths = fDependsUpon.get(context); // --- The dependencies will contain paths from the imports.
    	for (String path: possiblePaths){
    		if (path.endsWith(name)) {
    			return new ArrayList<String>(); // --- return empty set. Already have the needed dependence.
    		}
    		if (!path.endsWith(Constants.X10_EXT) && !path.endsWith(Constants.CLASS_EXT)){
    			result.add(path + File.separator + name);
    		}
    	}
 
    	return result;
    }
    
    private String getPackageFragment(String context, Collection<String> srcFolders){
    	for(String path: srcFolders){
    		if (context.startsWith(path)){
    			String rest = normalize(context.substring(path.length()));
    			if (!rest.contains(File.separator)) { // --- Default package.
    				return "";
    			}
    			int i = rest.lastIndexOf(File.separator);
    			return rest.substring(0,i);
    		}
    	}
    	return null;
    }
    
    
    private String normalize(String path){
    	if (path.startsWith(File.separator))
    		return path.substring(1);
    	return path;
    }
    
    private Collection<String> getSrcFolders(){
    	Collection<String> result = new ArrayList<String>();
    	try {
    		result.addAll(ProjectUtils.collectSourceFolders(fJavaProject));
    		for(String project: fJavaProject.getRequiredProjectNames()){
    			IJavaProject javaProject = JavaCore.create(ResourcesPlugin.getWorkspace().getRoot().getProject(project));
    			result.addAll(ProjectUtils.collectSourceFolders(javaProject));
    		}
    	} catch(JavaModelException e){
    		LaunchCore.log(e.getStatus());
    	}
    	return result;
    }
    
    protected String getPath(Position pos){
    	String filePath= pos.file();
    	if (filePath.contains("x10.jar"))
    		return null;
    	String result=null;
        String wsPath= fProject.getProject().getWorkspace().getRoot().getLocation().toOSString();
        if(filePath.startsWith(wsPath)){
        	result = filePath.substring(fWorkspacePath.length());
        }else{
        	result=filePath;
        }
    	return result;
    }

    public void addDependency(Type fromType, Type uponType) {
        String fromPath= getPath(getClassType(fromType).position()); 
        if (fromPath == null) return;
        ClassType uponClassType = getClassType(uponType);
        Position uponPosition = uponClassType.position();
        if (!uponPosition.isCompilerGenerated()){
        	String uponPath= getPath(uponPosition);
        	if (uponPath == null) return;
        	if(!(uponPath.contains(".zip") || uponPath.contains(".jar")) && !(uponPath.equals(fromPath))) {
        		addDependency(fromPath, uponPath);
        	}
        } else {
        	Collection<String> paths =  getPossiblePaths(uponClassType, fromPath);
        	for(String path: paths){
        		addDependency(fromPath, path);
        	}
        }
    }

    public void clearDependenciesOf(Type type) {
    	String path = getPath(getClassType(type).position()); 
    	if (path == null) return;
        clearDependenciesOf(path);
    }

    public Set<String> getDependentsOf(Type type) {
    	String path = getPath(getClassType(type).position()); 
    	if (path == null) return new HashSet<String>();
        return getDependentsOf(path);
    }
}

