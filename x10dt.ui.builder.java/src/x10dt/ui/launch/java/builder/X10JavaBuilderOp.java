package x10dt.ui.launch.java.builder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.imp.java.hosted.BuildPathUtils;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.compiler.batch.BatchCompiler;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import x10dt.core.utils.ICountableIterable;
import x10dt.core.utils.URIUtils;
import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.LaunchCore;
import x10dt.ui.launch.core.builder.AbstractX10Builder;
import x10dt.ui.launch.core.builder.target_op.IX10BuilderFileOp;
import x10dt.ui.launch.core.utils.CoreResourceUtils;
import x10dt.ui.launch.core.utils.ProjectUtils;
import x10dt.ui.launch.core.utils.UIUtils;
import x10dt.ui.builder.java.Messages;

public class X10JavaBuilderOp implements IX10BuilderFileOp {

  private IJavaProject fJavaProject;

  private X10JavaBuilder fBuilder;
  
  private Map<String, Collection<String>> fGeneratedFiles;
  
  private Collection<String> fJavaFiles = new ArrayList<String>();

  public X10JavaBuilderOp(IJavaProject project, X10JavaBuilder builder, Map<String, Collection<String>> generatedFiles) {
    this.fJavaProject = project;
    this.fBuilder = builder;
    this.fGeneratedFiles = generatedFiles;
  }

  // --- Interface methods implementation

  public void archive(final IProgressMonitor monitor) throws CoreException {
    // NoOp for Java Backend
  }

  public void cleanFiles(final ICountableIterable<IFile> files, final SubMonitor monitor) throws CoreException {
    for (final IFile file : files) {
      String name = BuildPathUtils.getBareName(file, fJavaProject);
      Collection<String> gens  = fGeneratedFiles.get(name);
      if (gens != null){
    	  Collection<File> genFiles = fBuilder.getLocalFiles(gens);
    	  for (File gen: genFiles){
    		  if (gen.exists()){
    			  gen.delete();
    		  }
    		  
    		  // Delete generated Java in x10-gen-src, on disk...
    		  final String javaFilePath =  gen.getAbsolutePath();
    		  IPath srcPath = getJavaFileInX10JavaSrc(javaFilePath);
    		  File srcFile = srcPath.toFile();
    		  if (srcFile.exists()){
    			  srcFile.delete();
    		  }
    		  
    		  //... and in the workspace
    		  srcPath = srcPath.makeRelativeTo(ResourcesPlugin.getWorkspace().getRoot().getLocation().append(fJavaProject.getProject().getLocation()));
    		  IFile wsGen = fJavaProject.getProject().getFile(srcPath);
    		  wsGen.delete(true, monitor);
    		  
    	      final File classFile = new File(javaFilePath.substring(0, javaFilePath.length() - 5).concat(Constants.CLASS_EXT));
    	      if (classFile.exists()) {
    	        classFile.delete();
    	      }
    	      // We need to take care of possible anonymous classes now.
    	      final String typeName = gen.getName().substring(0, gen.getName().length() - 5);
    	      File[] filess = gen.getParentFile().listFiles(new AnonymousClassFilter(typeName));
    	      if (filess != null){
    	    	  for (int i = 0; i < filess.length; i++) {
    	    		  filess[i].delete();
    	    	  }
    	      }
    	  }
      }
    }
    
    // --- Clear any x10 files that the JDT builder might have copied here.
    removeX10FilesFromOutputFolders();
    
  }
  
  private void removeX10FilesFromOutputFolders() throws JavaModelException {
	  IPath workspacePath = ResourcesPlugin.getWorkspace().getRoot().getLocation();
	  File mainOutputFolder = new File(workspacePath.append(fJavaProject.getOutputLocation()).toOSString());
	  removeX10Files(mainOutputFolder);
	  for(final IClasspathEntry cpEntry : fJavaProject.getRawClasspath()) {
          if (cpEntry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
        	  if (cpEntry.getOutputLocation() == null)
        		  continue;
              File binFolder = new File(workspacePath.append(cpEntry.getOutputLocation()).toOSString());
              removeX10Files(binFolder);
          }
      }
  }
  
  private void removeX10Files(File file){
	  if (file.isDirectory()){
		  File[] fs = file.listFiles();
		  for(int i = 0; i < fs.length; i++){
			  removeX10Files(fs[i]);
		  }
	  }
	  if (file.getName().endsWith(Constants.X10_EXT)){
		  file.delete();
	  }
  }

  public boolean compile(final IProgressMonitor monitor) throws CoreException {
	    if (fJavaFiles.isEmpty()){
	    	return true;
	    }
//	    Collection<String> commandline = new ArrayList<String>();
//        commandline.add("-1.5");
//		commandline.add("-nowarn");
//		commandline.add("-d");
//		commandline.add(ProjectUtils.getProjectOutputDirPath(fJavaProject.getProject()));
//		commandline.add("-classpath");
//		String classpath = fBuilder.getOptions().constructPostCompilerClasspath();
//		for (IPath entry: AbstractX10Builder.getProjectDependencies(fBuilder.getJavaProject())){
//			classpath += ":" + entry.toOSString();
//		}
//		commandline.add(classpath);
//		for (String file: fJavaFiles) {
//			commandline.add(file);
//		}
//		final MessageConsole console = UIUtils.findOrCreateX10Console();
//		final MessageConsoleStream consoleStream = console.newMessageStream();
//		boolean success = BatchCompiler.compile(commandline.toArray(new String[0]), new PrintWriter(System.out), new PrintWriter(consoleStream), null);
//		if (!success){
//			CoreResourceUtils.addBuildMarkerTo(fBuilder.getProject(), Messages.XJB_CompileErrors , 
//                    IMarker.SEVERITY_ERROR, IMarker.PRIORITY_HIGH);
//		}
		
		//Now create the generated Java files for Eclipse.
		
	    IFolder srcFolder = fJavaProject.getProject().getFolder("x10-gen-src");
	    if (!srcFolder.exists()){
	    	srcFolder.create(true, true, monitor);
	    	
	    	//Turn it into a source folder
	    	IClasspathEntry x10gen = JavaCore.newSourceEntry(srcFolder.getFullPath());
	        IClasspathEntry[] entries = this.fJavaProject.getRawClasspath();
	        IClasspathEntry[] newEntries = new IClasspathEntry[entries.length + 1];
	        System.arraycopy(entries, 0, newEntries, 0, entries.length);
	        newEntries[newEntries.length - 1] = x10gen;
	        this.fJavaProject.setRawClasspath(newEntries, new NullProgressMonitor());
	    }
		
		for (String javaFile: fJavaFiles){
			try {
				IPath javaPath = new Path(javaFile);
				javaPath = javaPath.makeRelativeTo(ResourcesPlugin.getWorkspace().getRoot().getLocation().append(fJavaProject.getOutputLocation()));
				createDirsInWorkspace(srcFolder, javaPath.removeLastSegments(1), monitor);
				IFile gen = srcFolder.getFile(javaPath);
				if (gen.exists()){
					gen.delete(true, monitor);
				}
				gen.create(new FileInputStream(javaFile), true, monitor);
				gen.refreshLocal(IResource.DEPTH_INFINITE, monitor);
			} catch(FileNotFoundException e){
				LaunchCore.log(IStatus.ERROR, Messages.GenFileNotFound, e);
			}
		}
		return true;
		//return success;
  }
  
  private void createDirsInWorkspace(IFolder parent, IPath relativePath, IProgressMonitor monitor) throws CoreException{
	 if (relativePath.isEmpty()) return;
	 IPath dir = new Path(relativePath.segment(0));
	 IFolder nextDir = parent.getFolder(dir);
	 if (! nextDir.exists()) {
		 nextDir.create(true, true, monitor);
	 }
	 relativePath = relativePath.removeFirstSegments(1);
	 createDirsInWorkspace(nextDir, relativePath, monitor);	 
  }
  
  
/*  public static void copyFile(File sourceFile, File destFile) throws IOException {
	  	File parent = destFile.getParentFile(); 
	    if (!parent.exists()){
	    	parent.mkdirs();
	    }
	    if(!destFile.exists()) {
	        destFile.createNewFile();
	    }

	    FileChannel source = null;
	    FileChannel destination = null;
	    try {
	        source = new FileInputStream(sourceFile).getChannel();
	        destination = new FileOutputStream(destFile).getChannel();
	        long count = 0;
	        long size = source.size();              
	        while((count += destination.transferFrom(source, count, size-count))<size);
	    }
	    finally {
	        if(source != null) {
	            source.close();
	        }
	        if(destination != null) {
	            destination.close();
	        }
	    }
	}
  */
  
  private IPath getJavaFileInX10JavaSrc(String javaFile) throws CoreException {
	  IPath workspacePath = ResourcesPlugin.getWorkspace().getRoot().getLocation();
	  IPath srcjavaPath = workspacePath.append(fJavaProject.getPath()).append(new Path("x10-gen-src"));
	  IPath javaPath = new Path(javaFile);
	  IPath outputPath = workspacePath.append(fJavaProject.getOutputLocation());
	  IPath javaEndPath = javaPath.removeFirstSegments(outputPath.segmentCount());
	  return srcjavaPath.append(javaEndPath);
  }
  

  public void copyToOutputDir(final Collection<IFile> files, final SubMonitor monitor) throws CoreException {
    // NoOp for Java Backend
  }

  public boolean hasAllPrerequisites() {
    return true;
  }
  
  public boolean hasValidCompilationCommands() {
    return true;
  }

  public void transfer(final Map<IPath, Collection<File>> files, final IProgressMonitor monitor) throws CoreException {
    for(Collection<File> collection: files.values()) {
      for (final File file : collection) {
        fJavaFiles.add(file.getAbsolutePath());
      }
    }
  }
  
  // --- Private classes
  
  private static final class AnonymousClassFilter implements FilenameFilter {
    
    AnonymousClassFilter(final String typeName) {
      this.fTypeNamePrefix = typeName + '$';
    }

    // --- Interface methods implementation
    
    public boolean accept(final File dir, final String name) {
      return name.startsWith(this.fTypeNamePrefix) && name.endsWith(Constants.CLASS_EXT);
    }
    
    // --- Private code
    
    private final String fTypeNamePrefix;
    
  }
  
}
