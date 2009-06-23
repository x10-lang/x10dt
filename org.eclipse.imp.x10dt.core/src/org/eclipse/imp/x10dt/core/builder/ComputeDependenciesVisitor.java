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

package org.eclipse.imp.x10dt.core.builder;

import java.util.Iterator;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.imp.x10dt.core.X10DTCorePlugin;

import polyglot.ast.Call;
import polyglot.ast.ClassDecl;
import polyglot.ast.Field;
import polyglot.ast.FieldDecl;
import polyglot.ast.Formal;
import polyglot.ast.New;
import polyglot.ast.Node;
import polyglot.ast.ProcedureDecl;
import polyglot.ast.Receiver;
import polyglot.ast.SourceFile;
import polyglot.ast.TypeNode;
import polyglot.ext.x10.types.NullableType;
import polyglot.ext.x10.types.X10TypeMixin;
import polyglot.frontend.Job;
import polyglot.types.ArrayType;
import polyglot.types.ClassType;
import polyglot.types.ConstructorInstance;
import polyglot.types.MethodInstance;
import polyglot.types.ParsedClassType;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.visit.ContextVisitor;
import polyglot.visit.NodeVisitor;

/**
 * A Polyglot visitor that scans for type reference information in order to
 * build a map of compilation unit-level dependencies to be used in compilation.
 * @author rfuhrer
 */
class ComputeDependenciesVisitor extends ContextVisitor {
    private final Job fJob;
    //private final TypeSystem fTypeSystem;
    private SourceFile fFromFile;
    private Type fFromType;
    private final PolyglotDependencyInfo fDependencyInfo;

    private static boolean DEBUG= false;

    public ComputeDependenciesVisitor(Job job, TypeSystem ts, PolyglotDependencyInfo di) {
    	super(job, ts, ts.extensionInfo().nodeFactory());
        fJob= job;
        fDependencyInfo= di;
        
    }
    private void recordTypeDependency(Type type) {
        if (type.isArray()) {
            ArrayType arrayType= (ArrayType) type;
            type= arrayType.base();
        }
        if (type.isClass()) {
            if (type instanceof NullableType)
                type = ((NullableType) type).base();
            ClassType classType= (ClassType) X10TypeMixin.baseType(type);
            if (!isBinary(classType) && !fFromType.typeEquals(type,this.context)) { 
                fDependencyInfo.addDependency(fFromType, type);
            }
        }
    }
    private boolean isBinary(ClassType classType) {
        // HACK for some reason ParsedClassType's show up even for class files(???)...
        return !(classType instanceof ParsedClassType) || classType.position().file().endsWith(".class");
    }
    @Override
    public NodeVisitor begin() {
        String wsPath= ResourcesPlugin.getWorkspace().getRoot().getLocation().toPortableString();

        String path= fJob.source().path();
        //PORT1.7 don't bother looking for dependencies if we're in jar/zip
        if(path.endsWith(".jar")|| path.endsWith(".zip")) {
//        	if(printOnce) {
//        		// print this out only once, so we don't forget about it.
//        		printOnce=false;   		
//        		String msg = "ComputeDependenciesVisitor - looking in zip/jar file.";
//        		//X10Plugin.getInstance().writeErrorMsg(msg);
//        		String id=X10Plugin.getInstance().getID();
//        		//X10Plugin.getInstance().getLog().log(new Status(IStatus.WARNING,id,msg+" logStatus"));
//        		X10Plugin.getInstance().logException(msg, new Exception("zip/jar file in dependencies"));
//        	}
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
        if (n instanceof SourceFile) {
            fFromFile= (SourceFile) n;
            if (DEBUG)
        	System.out.println("Scanning file " + fFromFile.position() + " for dependencies.");
        } else if (n instanceof TypeNode) {
            TypeNode typeNode= (TypeNode) n;
            Type type= typeNode.type();

            recordTypeDependency(type);
        } else if (n instanceof Field) {
            Field field= (Field) n;
            Receiver rcvr= field.target();
            Type type= rcvr.type();

            recordTypeDependency(type);
        } else if (n instanceof Call) {
            Call call= (Call) n;
            MethodInstance mi= call.methodInstance();

            recordTypeDependency(mi.container());
        } else if (n instanceof New) {
            New nw= (New) n;
            ConstructorInstance ci= nw.constructorInstance();

            recordTypeDependency(ci.container());
        } else if (n instanceof ClassDecl) {
            ClassDecl classDecl = (ClassDecl) n;
			fFromType = classDecl.classDef().asType(); // PORT1.7 classDecl.type()->classDecl.classDef().asType()
			if (classDecl.superClass() != null) // interfaces have no superclass
				recordTypeDependency(classDecl.superClass().type());
			for (Iterator intfs = classDecl.interfaces().iterator(); intfs
					.hasNext();) {
				TypeNode typeNode = (TypeNode) intfs.next();

				recordTypeDependency(typeNode.type());
            }
        } else if (n instanceof FieldDecl) {
            FieldDecl fieldDecl= (FieldDecl) n;

            recordTypeDependency(fieldDecl.type().type());
        } else if (n instanceof ProcedureDecl) {
            ProcedureDecl procedureDecl= (ProcedureDecl) n;

            for(Iterator iter= procedureDecl.formals().iterator(); iter.hasNext();) {
        	Formal formal= (Formal) iter.next();

        	recordTypeDependency(formal.type().type());
            }
        }
        return super.enterCall(n);
    }
}
