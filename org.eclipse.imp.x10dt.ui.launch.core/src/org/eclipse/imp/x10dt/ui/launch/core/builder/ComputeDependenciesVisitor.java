
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

package org.eclipse.imp.x10dt.ui.launch.core.builder;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.imp.x10dt.ui.launch.core.Constants;
import org.eclipse.imp.x10dt.ui.launch.core.LaunchCore;
import org.eclipse.imp.x10dt.ui.launch.core.utils.ProjectUtils;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

import polyglot.ast.Call;
import polyglot.ast.ClassDecl;
import polyglot.ast.Field;
import polyglot.ast.FieldDecl;
import polyglot.ast.Formal;
import polyglot.ast.Import;
import polyglot.ast.MethodDecl;
import polyglot.ast.New;
import polyglot.ast.Node;
import polyglot.ast.ProcedureDecl;
import polyglot.ast.Receiver;
import polyglot.ast.SourceFile;
import polyglot.ast.TypeNode;
import polyglot.frontend.Job;
import polyglot.types.ArrayType;
import polyglot.types.ClassDef;
import polyglot.types.ClassType;
import polyglot.types.ConstructorInstance;
import polyglot.types.MethodInstance;
import polyglot.types.ParsedClassType;
import polyglot.types.QName;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.util.Position;
import polyglot.visit.ContextVisitor;
import polyglot.visit.NodeVisitor;
import x10.ast.AnnotationNode;
import x10.types.ClosureType_c;
import x10.types.X10ClassType;
import x10.types.X10TypeMixin;

/**
 * A Polyglot visitor that scans for type reference information in order to
 * build a map of compilation unit-level dependencies to be used in compilation.
 * @author rfuhrer
 */
public class ComputeDependenciesVisitor extends ContextVisitor {
	private final IJavaProject fProject;
    private final Job fJob;
    private Type fFromType;
    private SourceFile fFromFile;
    private final PolyglotDependencyInfo fDependencyInfo;

    public ComputeDependenciesVisitor(IJavaProject project, Job job, TypeSystem ts, PolyglotDependencyInfo di) {
    	super(job, ts, ts.extensionInfo().nodeFactory());
        fJob= job;
        fDependencyInfo= di;
        fProject = project;
    }

    private void recordTypeDependency(Type type) {
        if (type.isArray()) {
            ArrayType arrayType= (ArrayType) type;
            type= arrayType.base();
        }
        if (type.isClass()) {
            ClassType classType= (ClassType) X10TypeMixin.baseType(type);
            
            if (!isBinary(classType) && !fFromType.typeEquals(classType,this.context)) { 
                fDependencyInfo.addDependency(fFromType, type);
            }
        }
    }
    
    /**
     * This method is used to record dependences for import statements.
     * 
     * @param path Must be a workspace-relative path
     */
    private void recordPathDependency(String path){
    	fDependencyInfo.addDependency(fDependencyInfo.getPath(fFromFile.position()), path);
    }
    
    private Collection<String> getPossiblePaths(QName name){
    	if (name == null)
    		return null;
    	Collection<String> results = new ArrayList<String>();
    	for (String path: getSrcFolders()){
    		results.add(path + File.separator + name.toString().replace('.', File.separatorChar));
    	}
    	Collection<String> rest = getPossiblePaths(name.qualifier());
    	if (rest != null)
    		results.addAll(rest);
    	return results;
    }
    
    /**
     * @return This method returns the collection of src folders visible to the current project,
     * including the project's own src folders and the ones from all projects that this depends on.
     * 
     */
    private Collection<String> getSrcFolders(){
    	Collection<String> result = new ArrayList<String>();
    	try {
    		result.addAll(ProjectUtils.collectSourceFolders(fProject));
    		for(String project: fProject.getRequiredProjectNames()){
    			IJavaProject javaProject = JavaCore.create(ResourcesPlugin.getWorkspace().getRoot().getProject(project));
    			result.addAll(ProjectUtils.collectSourceFolders(javaProject));
    		}
    	} catch(JavaModelException e){
    		LaunchCore.log(e.getStatus());
    	}
    	return result;
    }

    private boolean isBinary(ClassType classType) {
        // HACK for some reason ParsedClassType's show up even for class files(???)...
        Position pos= classType.position();
		return !(classType instanceof ParsedClassType) || (pos != null && pos.file().endsWith(".class"));
    }

    @Override
    public NodeVisitor begin() {
        String wsPath= ResourcesPlugin.getWorkspace().getRoot().getLocation().toPortableString();

        String path= fJob.source().path();
        //PORT1.7 don't bother looking for dependencies if we're in jar/zip
        if(path.endsWith(".jar")|| path.endsWith(".zip")) {
        	return null;
        }
        if (path.startsWith(wsPath)) {
            path= path.substring(wsPath.length());
        }
        fDependencyInfo.clearDependenciesOf(path);
        return super.begin();
    }
    @Override
    public NodeVisitor enterCall(Node n) throws SemanticException {
		try {
		    if (n instanceof SourceFile) {
		    	
		    	fFromFile= (SourceFile) n;
			
		    } else if (n instanceof TypeNode) {
				
				TypeNode typeNode = (TypeNode) n;
				Type type = typeNode.type();
				List<Type> types = new ArrayList<Type>();
				extractAllClassTypes(type, types);
				for (Type t : types) {
					recordTypeDependency(t);
				}

			} else if (n instanceof Field) {
				
				Field field = (Field) n;
				Receiver rcvr = field.target();
				Type type = rcvr.type();
				Type fieldType = field.type();
				recordTypeDependency(type);
				recordTypeDependency(fieldType);

			} else if (n instanceof Call) {
				
				Call call = (Call) n;
				MethodInstance mi = call.methodInstance();
				recordTypeDependency(mi.container());
				recordTypeDependency(mi.returnType());

			} else if (n instanceof New) {
				
				New nw = (New) n;
				ConstructorInstance ci = nw.constructorInstance();
				recordTypeDependency(ci.container());

			} else if (n instanceof ClassDecl) {
				
				ClassDecl classDecl = (ClassDecl) n;
				ClassDef classDef = classDecl.classDef();
				if (classDef != null) {
					fFromType = classDef.asType(); // PORT1.7
												// classDecl.type()->classDecl.classDef().asType()
					List<ClassType> supers = new ArrayList<ClassType>();
					superTypes(classDef.asType(), supers);
					for (ClassType supert : supers) {
						recordTypeDependency(supert);
					}
				}

			} else if (n instanceof FieldDecl) {
				
				FieldDecl fieldDecl = (FieldDecl) n;
				recordTypeDependency(fieldDecl.type().type());

			} else if (n instanceof ProcedureDecl) {
				
				ProcedureDecl procedureDecl = (ProcedureDecl) n;
				for(Formal formal: procedureDecl.formals()){
					recordTypeDependency(formal.type().type());
				}
				if (procedureDecl instanceof MethodDecl) {
					MethodDecl method = (MethodDecl) procedureDecl;
					recordTypeDependency(method.returnType().type());
				}
				for (TypeNode thrown : procedureDecl.throwTypes()) {
					recordTypeDependency(thrown.type());
				}

			} else if (n instanceof AnnotationNode) {
				
				Type type = ((AnnotationNode) n).annotationType().type();
				recordTypeDependency(type);
			
			} else if (n instanceof Import) {
				
				Import node  = (Import) n;
				QName name = node.name();
				for (String path: getPossiblePaths(name)){
					if (node.kind() == Import.CLASS){
						recordPathDependency(path + Constants.X10_EXT);
					} else { 
						// --- The node kind is package (e.g. the import is of the form import foo.*).
						// --- In this case, foo may be a class or package.
						// --- We record a dependence to both conservatively.
						recordPathDependency(path + Constants.X10_EXT);
						recordPathDependency(path);
						
					}
				}
				
			}
		
		} catch (Exception e) {
			// --- Exceptions may be thrown here if the AST is not well-formed.
			// --- In this case the dependence information will be incomplete, 
			// --- but this happens only if there are errors in the file.
			// --- This is not a problem with regards to compilation if
			// --- conservative build is turned on, because we recompile every file
			// --- that has no generated file (which all files with errors).
		}
		
		return super.enterCall(n);
    }
    

	private void extractAllClassTypes(Type type, List<Type> types) {
		if (!type.isClass())
			return;
		if (type instanceof ClosureType_c){
         	ClosureType_c closure = (ClosureType_c) type;
         	for(Type formal: closure.argumentTypes()){
         		extractAllClassTypes(formal, types);
         	}
         	extractAllClassTypes(closure.returnType(), types);
         	for(Type thrown:closure.throwTypes()){
         		extractAllClassTypes(thrown, types);
         	}	
         	return;
		}
		X10ClassType classType = (X10ClassType) X10TypeMixin.baseType(type);
		if (!types.contains(classType))
			types.add(classType);

		for (Type param : classType.typeArguments())
			extractAllClassTypes(param, types);
	}
	
	private void superTypes(ClassType type, List<ClassType> types){
		Type parentClass = type.superClass();
		if (parentClass != null){
			X10ClassType classType = (X10ClassType) X10TypeMixin.baseType(parentClass);
			if (!types.contains(classType)){
				types.add(classType);
				superTypes(classType, types);
			}
		}
		for(Type inter: type.interfaces()){
			X10ClassType interc = (X10ClassType) X10TypeMixin.baseType(inter);
			if (!types.contains(interc)){
				types.add(interc);
				superTypes(interc, types);
			}
		}
	}
}
