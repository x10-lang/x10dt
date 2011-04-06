package x10dt.ui.launch.java.builder;

import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.imp.java.hosted.BuildPathUtils;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.compiler.batch.BatchCompiler;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import x10dt.core.utils.ICountableIterable;
import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.builder.target_op.IX10BuilderFileOp;
import x10dt.ui.launch.core.utils.CoreResourceUtils;
import x10dt.ui.launch.core.utils.UIUtils;
import x10dt.ui.launch.java.Messages;

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
    		  final String javaFilePath =  gen.getAbsolutePath();
    	      final File classFile = new File(javaFilePath.substring(0, javaFilePath.length() - 5).concat(Constants.CLASS_EXT));
    	      if (classFile.exists()) {
    	        classFile.delete();
    	      }
    	      // We need to take care of possible anonymous classes now.
    	      final String typeName = gen.getName().substring(0, gen.getName().length() - 5);
    	      for (final File f : gen.getParentFile().listFiles(new AnonymousClassFilter(typeName))) {
    	        f.delete();
    	      }
    	  }
      }
    }
  }

  public boolean compile(final IProgressMonitor monitor) throws CoreException {
	    if (fJavaFiles.isEmpty()){
	    	return true;
	    }
	    Collection<String> commandline = new ArrayList<String>();
        commandline.add("-1.5");
		commandline.add("-nowarn");
		commandline.add("-classpath");
		commandline.add(fBuilder.getOptions().constructPostCompilerClasspath());
		for (String file: fJavaFiles) {
			commandline.add(file);
		}
		final MessageConsole console = UIUtils.findOrCreateX10Console();
		final MessageConsoleStream consoleStream = console.newMessageStream();
		boolean success = BatchCompiler.compile(commandline.toArray(new String[0]), new PrintWriter(System.out), new PrintWriter(consoleStream), null);
		if (!success){
			CoreResourceUtils.addBuildMarkerTo(fBuilder.getProject(), Messages.XJB_CompileErrors , 
                    IMarker.SEVERITY_ERROR, IMarker.PRIORITY_HIGH);
		}
		return success;
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

  public void transfer(final Collection<File> files, final IProgressMonitor monitor) throws CoreException {
    for(File file: files){
    	fJavaFiles.add(file.getAbsolutePath());
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
