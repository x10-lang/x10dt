package x10dt.ui.launch.java.builder;

import java.io.File;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.imp.java.hosted.BuildPathUtils;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

import polyglot.main.Options;
import x10.ExtensionInfo;
import x10dt.core.utils.IFilter;
import x10dt.core.utils.URIUtils;
import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.builder.AbstractX10Builder;
import x10dt.ui.launch.core.builder.target_op.IX10BuilderFileOp;
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

	  public File getMainGeneratedFile(final IJavaProject project, final IFile x10File) throws CoreException {
		    final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		    IPath rootPath = root.getLocation();
		    for (final IClasspathEntry cpEntry : project.getRawClasspath()) {
		      if (cpEntry.getEntryKind() == IClasspathEntry.CPE_SOURCE && cpEntry.getPath().isPrefixOf(x10File.getFullPath())
		    		  && !BuildPathUtils.isExcluded(x10File.getFullPath(), cpEntry)){
		        IPath outputLocation = new Path(getX10GenSrc());
		        outputLocation = outputLocation.makeRelativeTo(rootPath);
		        final StringBuilder sb = new StringBuilder();
		        sb.append(File.separatorChar).append(x10File.getProjectRelativePath().removeFileExtension().toString())
		          .append(getFileExtension());
		        final IPath projectRelativeFilePath = new Path(sb.toString());
		        final int srcPathCount = cpEntry.getPath().removeFirstSegments(1).segmentCount();
		        final IPath generatedFilePath = outputLocation.append(projectRelativeFilePath.removeFirstSegments(srcPathCount));
		        final IFileStore fileStore = EFS.getLocalFileSystem().getStore(root.getFile(generatedFilePath).getLocation());
		        if (fileStore.fetchInfo().exists()) {
		          return fileStore.toLocalFile(EFS.NONE, null);
		        }
		      }
		    }
		    return null;
		  }
	
	public String getFileExtension() {
		return Constants.JAVA_EXT;
	}
	
	public Options getOptions(){
		return fOptions;
	}
	
	protected boolean checkPostCompilationCondition(final String srcPath)throws CoreException {
	  return true;
	}
	
	private String getX10GenSrc() throws CoreException {
	    final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
	    final URI outputFolderURI = URIUtils.getExpectedURI(root.getFolder(fProjectWrapper.getPath().append(new Path("x10-gen-src"))).getLocationURI());
	    return EFS.getStore(outputFolderURI).toLocalFile(EFS.NONE, new NullProgressMonitor()).getAbsolutePath();
	}

	@Override
	protected void deleteX10GeneratedFiles() throws CoreException {
		final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
	    final URI outputFolderURI = URIUtils.getExpectedURI(root.getFolder(fProjectWrapper.getPath().append(new Path("x10-gen-src"))).getLocationURI());
	    File folder = EFS.getStore(outputFolderURI).toLocalFile(EFS.NONE, new NullProgressMonitor());
	    if (folder != null && folder.exists()){
	    	for (File file: folder.listFiles()){
	    		if (file.exists()){
	    			file.delete();
	    		}
	    	}
	    }
	}


}
