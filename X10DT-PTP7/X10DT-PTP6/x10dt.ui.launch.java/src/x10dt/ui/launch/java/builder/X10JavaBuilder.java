package x10dt.ui.launch.java.builder;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.JavaModelException;

import polyglot.main.Options;

import x10.ExtensionInfo;
import x10dt.core.utils.AlwaysTrueFilter;
import x10dt.core.utils.IFilter;
import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.builder.AbstractX10Builder;
import x10dt.ui.launch.core.builder.CpEntryAsStringFunc;
import x10dt.ui.launch.core.builder.IPathToFileFunc;
import x10dt.ui.launch.core.builder.target_op.IX10BuilderFileOp;
import x10dt.ui.launch.core.utils.CollectionUtils;
import x10dt.ui.launch.core.utils.IdentityFunctor;
import x10dt.ui.launch.core.utils.ProjectUtils;

public class X10JavaBuilder extends AbstractX10Builder {
	private Options fOptions;
	
	public ExtensionInfo createExtensionInfo(String classPath,
			List<File> sourcePath, String localOutputDir,
			boolean withMainMethod, IProgressMonitor monitor) {
		final ExtensionInfo extInfo = new JavaBuilderExtensionInfo(monitor, this);
		IPath binJava = fProjectWrapper.getProject().getLocation().append("bin-java");
		classPath = binJava.toOSString() + File.pathSeparatorChar + classPath;
		sourcePath.add(0, binJava.toFile());
		
	    buildOptions(classPath, sourcePath, localOutputDir, extInfo.getOptions(), withMainMethod);
	    fOptions = extInfo.getOptions();
	    fOptions.post_compiler = null;
	    
	    return extInfo;
	}

	public IFilter<IFile> createNativeFilesFilter() {
		return new IFilter<IFile>(){

			public boolean accepts(IFile element) {
				// -- We do not need a filter for the Java backend.
				return false;
			}
			
		};
	}

	public IX10BuilderFileOp createX10BuilderFileOp() throws CoreException {
		return new X10JavaBuilderOp(getJavaProject(), this, this.fGeneratedFiles);
	}

	public String getFileExtension() {
		return Constants.JAVA_EXT;
	}
	
	public Options getOptions(){
		return fOptions;
	}
	
	private void removeSrcJava(Collection<IPath> cps){
	    IPath srcjava = null;
	    for(IPath entry: cps){
	      if (entry.toOSString().endsWith("src-java")){
	        srcjava = entry;
	        break;
	      }
	    }
	    if (srcjava != null){
	      cps.remove(srcjava);
	    }
	  }
	  
//	protected String getSrcClassPath(List<File> sourcePath) throws CoreException{
//		StringBuffer bufferPath = new StringBuffer();
//		final Set<IPath> srcPaths = ProjectUtils.getFilteredCpEntries(this.fProjectWrapper, new IdentityFunctor<IPath>(),
//		        new AlwaysTrueFilter<IPath>(), bufferPath);
//		    
//		// removeSrcJava(srcPaths);
//		// TODO: I should have threaded a list around	
//		sourcePath.addAll(CollectionUtils.transform(srcPaths, new IPathToFileFunc()));
//		    
//		final List<String> cps = CollectionUtils.transform(srcPaths, new CpEntryAsStringFunc());
//		    
//		return bufferPath.toString();
//
//	}
}
