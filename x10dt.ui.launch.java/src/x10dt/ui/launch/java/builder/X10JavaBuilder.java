package x10dt.ui.launch.java.builder;

import java.io.File;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import polyglot.main.Options;

import x10.ExtensionInfo;
import x10dt.core.utils.IFilter;
import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.builder.AbstractX10Builder;
import x10dt.ui.launch.core.builder.target_op.IX10BuilderFileOp;

public class X10JavaBuilder extends AbstractX10Builder {
	private Options fOptions;
	
	public ExtensionInfo createExtensionInfo(String classPath,
			List<File> sourcePath, String localOutputDir,
			boolean withMainMethod, IProgressMonitor monitor) {
		final ExtensionInfo extInfo = new JavaBuilderExtensionInfo(monitor, this);
		classPath = classPath + ":" + fProjectWrapper.getProject().getLocation().append("bin-java");
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
	 
	  

}
