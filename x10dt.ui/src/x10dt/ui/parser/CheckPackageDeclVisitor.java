package x10dt.ui.parser;

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.imp.java.hosted.BuildPathUtils;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

import polyglot.ast.Node;
import polyglot.ast.PackageNode;
import polyglot.frontend.Job;
import polyglot.frontend.Source;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.visit.NodeVisitor;
import x10.errors.Errors;
import x10dt.core.X10DTCorePlugin;
import x10dt.ui.Messages;

public class CheckPackageDeclVisitor extends NodeVisitor {
    private final Job fJob;
    private final IProject fProject;
    private boolean fSeenPkg= false;

    public CheckPackageDeclVisitor(Job job, IProject project) {
        fJob= job;
        fProject= project;
    }

    @Override
    public NodeVisitor begin() {
    	if (!fProject.exists())
    		return null;
        String path= fJob.source().path();
        // Don't bother looking for dependencies if the AST in question was from a jar/zip
        if (path.endsWith(".jar") || path.endsWith(".zip")) {
        	return null;
        }
        if (fJob.ast().position().isCompilerGenerated()) {
            // This is a "dummy AST" produced for a file that wouldn't parse, so ignore it.
            return null;
        }
        return super.begin();
    }

    private void checkPackage(String declaredPkg, String actualPkg, Position pos) {
        if (!actualPkg.equals(declaredPkg)) {
        	Errors.issue(fJob, new SemanticException(Messages.CPD_PackageDeclError, pos));
        }
    }

    @Override
    public NodeVisitor enter(Node n) {
    	if (!fProject.exists())
    		return super.enter(n);
        if (n instanceof PackageNode) {
            PackageNode pkg= (PackageNode) n;
            Source src= fJob.source();
            String declaredPkg= pkg.package_().get().fullName().toString();
            String actualPkg= determineActualPackage(src);
            if(actualPkg!=null) {
            	checkPackage(declaredPkg, actualPkg, pkg.position());    	
            }
            fSeenPkg= true;
        }
        return super.enter(n);
    }

    private String determineActualPackage(Source src) {
    	if (!fProject.exists())
    		return null;
        String srcPath= src.path();
        String srcFileName= src.name();
        String wsPath= fProject.getLocation().removeLastSegments(1).toOSString();
        String pkgPath; // rel to ws loc

        if (srcPath.startsWith(wsPath)) {
            pkgPath= srcPath.substring(wsPath.length());
        } else {
            pkgPath= srcPath;
        }

        if (srcFileName.indexOf("/") != -1) {
        	srcFileName = srcFileName.substring(srcFileName.lastIndexOf("/") + 1);
        } 
        
        IJavaProject javaProject = JavaCore.create(fProject);
        try {
        	String name = BuildPathUtils.getBareName(pkgPath, javaProject).replaceAll(File.separator, ".");
        	if (!name.contains("."))
        		return "";
        	int i = name.lastIndexOf(".");
        	return name.substring(0, i);
		} catch (JavaModelException e) {
		    if (!e.isDoesNotExist()) {
		        X10DTCorePlugin.getInstance().logException(e.getMessage(), e);
		        // remember to test if java nature is still on cpp-backend projects etc.
		    }
			return null;
		}
    }

    @Override
    public Node override(Node n) {
        if (fSeenPkg) {
            return n;
        }
        return null;
    }
    @Override
    public void finish() {
    	if (!fProject.exists())
    		return;
        if (!fSeenPkg) { // No package decl -> implicitly in the default package
            Source src= fJob.source();
            checkPackage("", determineActualPackage(src), new Position(src.path(), src.path(), 1, 1)); // --- WARNING: passing the path here for file !!!!!
        }
    }
    
}
