package x10dt.ui.launch.core.builder;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

import polyglot.ast.Node;
import polyglot.ast.PackageNode;
import polyglot.frontend.Job;
import polyglot.frontend.Source;
import polyglot.util.Position;
import polyglot.visit.NodeVisitor;
import x10dt.core.X10DTCorePlugin;
import x10dt.ui.launch.core.Messages;
import x10dt.ui.launch.core.utils.CoreResourceUtils;

public class CheckPackageDeclVisitor extends NodeVisitor {
    private final Job fJob;
    private final IProject fProject;
    private final AbstractX10Builder fBuilder;
    private boolean fSeenPkg= false;

    public CheckPackageDeclVisitor(Job job, IProject project, AbstractX10Builder builder) {
        fJob= job;
        fProject= project;
        fBuilder = builder;
    }
    @Override
    public NodeVisitor begin() {
        String path= fJob.source().path(); 
        //PORT1.7 don't bother looking for dependencies if we're in jar/zip
        if(path.endsWith(".jar")|| path.endsWith(".zip")) {
        	return null;
        }
        return super.begin();
    }
    private void checkPackage(String declaredPkg, String actualPkg, Position pos) {
        if (!actualPkg.equals(declaredPkg)) {
        	IFile file= fBuilder.getProject().getFile(fJob.source().path().substring(fBuilder.getProject().getLocation().toOSString().length()));
        	CoreResourceUtils.addBuildMarkerTo(file, Messages.CPD_PackageDeclError, IMarker.SEVERITY_ERROR, IMarker.PRIORITY_NORMAL, 
        	                                   file.getLocation().toString(), pos.line(), pos.offset(), pos.endOffset());
        }
    }

    @Override
    public NodeVisitor enter(Node n) {
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
        String srcPath= src.path();
        String srcFileName= src.name();
        String wsPath= fProject.getLocation().removeLastSegments(1).toOSString();
        String pkgPath; // rel to ws loc

        if (srcPath.startsWith(wsPath)) {
            pkgPath= srcPath.substring(wsPath.length());
        } else {
            pkgPath= srcPath;
        }

        IJavaProject javaProject = JavaCore.create(fProject);
        IClasspathEntry[] cpEntries;
		try {
			cpEntries = javaProject.getResolvedClasspath(true);
		} catch (JavaModelException e) {
			X10DTCorePlugin.getInstance().logException(e.getMessage(), e);
			// remember to test if java nature is still on cpp-backend projects etc.
			return null;
		}

        for (int i = 0; i < cpEntries.length; i++) {
			IClasspathEntry classpathEntry = cpEntries[i];
			if (classpathEntry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
				IPath cpPath= classpathEntry.getPath(); // ws rel
				String cpPathStr = cpPath.toOSString();

				if (pkgPath.startsWith(cpPathStr)) {
					int cpPathLen = cpPathStr.length();
					String srcFolderRelPath= pkgPath.substring(cpPathLen + 1, pkgPath.length() - srcFileName.length() );
					String result= srcFolderRelPath.replace(File.separatorChar, '.');
					if(result.endsWith(".")) {
						result=result.substring(0, result.length()-1);
					}
					return result;
				}
			}
		}
        return null;
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
        if (!fSeenPkg) { // No package decl -> implicitly in the default package
            Source src= fJob.source();
            checkPackage("", determineActualPackage(src), new Position(src.path(), src.name(), 1, 1));
        }
    }
}
