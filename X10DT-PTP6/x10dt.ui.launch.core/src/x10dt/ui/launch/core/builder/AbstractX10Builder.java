/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.core.builder;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.MultiRule;
import org.eclipse.imp.java.hosted.BuildPathUtils;
import org.eclipse.imp.preferences.IPreferencesService;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import polyglot.frontend.Compiler;
import polyglot.frontend.Globals;
import polyglot.frontend.Job;
import polyglot.main.Options;
import polyglot.util.InternalCompilerError;
import x10.ExtensionInfo;
import x10.X10CompilerOptions;
import x10dt.core.X10DTCorePlugin;
import x10dt.core.preferences.generated.X10Constants;
import x10dt.core.utils.AlwaysTrueFilter;
import x10dt.core.utils.CompilerOptionsFactory;
import x10dt.core.utils.CountableIterableFactory;
import x10dt.core.utils.IFilter;
import x10dt.core.utils.URIUtils;
import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.LaunchCore;
import x10dt.ui.launch.core.Messages;
import x10dt.ui.launch.core.builder.target_op.IX10BuilderFileOp;
import x10dt.ui.launch.core.utils.CollectionUtils;
import x10dt.ui.launch.core.utils.CoreResourceUtils;
import x10dt.ui.launch.core.utils.IdentityFunctor;
import x10dt.ui.launch.core.utils.ProjectUtils;
import x10dt.ui.launch.core.utils.UIUtils;
import x10dt.ui.launch.core.utils.X10BuilderUtils;

/**
 * X10 builder base class for all the different back-ends.
 * 
 * @author egeay
 */
public abstract class AbstractX10Builder extends IncrementalProjectBuilder {

  // --- Abstract methods definition

  /**
   * Creates the Polyglot extension information that controls the compiler options for the particular back-end.
   * 
   * @param classPath The class path to consider for compilation.
   * @param sourcePath The source path to use.
   * @param localOutputDir The directory where the generated files will be created.
   * @param withMainMethod True if the main method should be generated, false otherwise.
   * @param monitor The monitor to use for reporting progress and/or cancel the operation.
   * @return A non-null object.
   */
  public abstract ExtensionInfo createExtensionInfo(final String classPath, final List<File> sourcePath,
                                                    final String localOutputDir, final boolean withMainMethod,
                                                    final IProgressMonitor monitor);

  /**
   * Creates a filter for all the native files that may be present in the projects for the current back-end.
   * 
   * @return A non-null filter instance.
   */
  public abstract IFilter<IFile> createNativeFilesFilter();

  /**
   * Creates the instance of {@link IX10BuilderFileOp} depending of the connection type, local or remote.
   * 
   * @return A non-null object.
   * @throws CoreException Occurs if we could not load the X10 platform configuration associated with the project.
   */
  public abstract IX10BuilderFileOp createX10BuilderFileOp() throws CoreException;

  /**
   * Returns the file extension corresponding to each back-end (e.g., ".java" for Java back-end).
   * 
   * @return A non-null string in all cases.
   */
  public abstract String getFileExtension();

  
  // --- Abstract methods implementation

  static Map<Integer, String> BUILD_TYPE_NAME= new HashMap<Integer, String>();
  static {
    BUILD_TYPE_NAME.put(IncrementalProjectBuilder.AUTO_BUILD, "auto");
    BUILD_TYPE_NAME.put(IncrementalProjectBuilder.CLEAN_BUILD, "clean");
    BUILD_TYPE_NAME.put(IncrementalProjectBuilder.FULL_BUILD, "full");
    BUILD_TYPE_NAME.put(IncrementalProjectBuilder.INCREMENTAL_BUILD, "incremental");
  }

  private void logBuildTypeToConsole(final int kind, PrintStream ps) {
    IResourceDelta delta= this.getDelta(getProject());

    ps.println("Build initiated: type = " + BUILD_TYPE_NAME.get(kind) + "; delta = " + (delta != null ? delta.toString() : "<none>"));
  }
  

  @SuppressWarnings("rawtypes")
  protected final IProject[] build(final int kind, final Map args, final IProgressMonitor monitor) throws CoreException {
	final MessageConsole messageConsole = UIUtils.findOrCreateX10Console();
    messageConsole.clearConsole();

    logBuildTypeToConsole(kind, new PrintStream(messageConsole.newMessageStream()));

    if (!getProject().isAccessible()) {
      return null;
    }
    try {
      CoreResourceUtils.deleteBuildMarkers(getProject(), IResource.DEPTH_ZERO);
      if (this.fProjectWrapper == null) {
        return new IProject[0];
      }
      final SubMonitor subMonitor = SubMonitor.convert(monitor, 100);
      final Collection<IFile> sourcesToCompile = new HashSet<IFile>();
      final Collection<IFile> deletedSources = new HashSet<IFile>();
      final Collection<IFile> nativeFiles = new HashSet<IFile>();

      IX10BuilderFileOp x10BuilderOp = null;
      try {
        x10BuilderOp = createX10BuilderFileOp();
      } catch (CoreException except) {
        CoreResourceUtils.addBuildMarkerTo(getProject(), except.getMessage(), IMarker.SEVERITY_ERROR, IMarker.PRIORITY_NORMAL);
      }
      if (x10BuilderOp == null) {
        return new IProject[0];
      }
      if (! x10BuilderOp.hasAllPrerequisites()) {
        CoreResourceUtils.addBuildMarkerTo(getProject(), NLS.bind(Messages.AXB_IncompleteConfMsg, getProject().getName()),
                                           IMarker.SEVERITY_ERROR, IMarker.PRIORITY_HIGH);
        UIUtils.showProblemsView();
        return null;
      }
      if (! x10BuilderOp.hasValidCompilationCommands()) {
        CoreResourceUtils.addBuildMarkerTo(getProject(), NLS.bind(Messages.AXB_InvalidCommands, getProject().getName()),
                                           IMarker.SEVERITY_ERROR, IMarker.PRIORITY_HIGH);
        UIUtils.showProblemsView();
        return null;
      }
      
      checkForCancelation(subMonitor);
      
      final Set<IProject> dependentProjects = cleanFiles(kind, subMonitor.newChild(10), sourcesToCompile, deletedSources,
                                                         nativeFiles, x10BuilderOp);
      if (dependentProjects == null) {
        return null;
      }

      final String localOutputDir = ProjectUtils.getProjectOutputDirPath(getProject());
      x10BuilderOp.copyToOutputDir(nativeFiles, subMonitor.newChild(5));

      checkForCancelation(subMonitor);
      
      compile(localOutputDir, sourcesToCompile, x10BuilderOp, subMonitor);
      
      

      this.fProjectWrapper.getProject().refreshLocal(IResource.DEPTH_INFINITE, subMonitor);
      dependentProjects.addAll(ProjectUtils.getDependentProjects(fProjectWrapper));
      return dependentProjects.toArray(new IProject[dependentProjects.size()]);
    } catch (Exception except) {
      if (!(except instanceof OperationCanceledException)) {
        LaunchCore.log(IStatus.ERROR, Messages.AXB_BuildException, except);
        CoreResourceUtils.addBuildMarkerTo(getProject(), NLS.bind(Messages.XEQ_InternalCompilerErrorMsg, getProject().getName()),
              IMarker.SEVERITY_ERROR, IMarker.PRIORITY_HIGH);
      
      } else { // --- cancelation
        super.forgetLastBuiltState();
        throw new OperationCanceledException();
      }
    } finally {
      info();	
      if (monitor.isCanceled()){
        super.forgetLastBuiltState();
        throw new OperationCanceledException();
      }
      monitor.done();
    }
    return new IProject[0];
  }

  private Set<IProject> cleanFiles(final int kind, final SubMonitor subMonitor, final Collection<IFile> sourcesToCompile,
                                   final Collection<IFile> deletedSources, final Collection<IFile> nativeFiles,
                                   final IX10BuilderFileOp x10BuilderFileOp) throws CoreException {
    subMonitor.beginTask(null, 10);
    try {
      final boolean shouldBuildAll;
      if (kind == FULL_BUILD) {
        this.fDependencyInfo.clearAllDependencies();
        shouldBuildAll = true;
      } else {
        shouldBuildAll = this.fDependencyInfo.getDependencies().isEmpty();
      }

      final Set<IProject> dependentProjects = new HashSet<IProject>();
      collectSourceFilesToCompile(sourcesToCompile, nativeFiles, deletedSources, this.fProjectWrapper, dependentProjects,
                                  createNativeFilesFilter(), shouldBuildAll, subMonitor.newChild(5));
      CoreResourceUtils.deleteBuildMarkers(getProject(), IResource.DEPTH_ZERO);
      clearDependencyInfoAsNeeded(sourcesToCompile, deletedSources);
      clearMarkers(sourcesToCompile); // --- This needs to happen before filter because we need to clear markers on files that have been recently excluded from the class path.
      filter(sourcesToCompile);
      if (subMonitor.isCanceled()) {
        return null;
      }
     
      final ISchedulingRule rule = MultiRule.combine(getSchedulingRule(sourcesToCompile), 
    		  										 MultiRule.combine(getSchedulingRule(nativeFiles), getSchedulingRule(deletedSources)));
      final IWorkspace workspace = ResourcesPlugin.getWorkspace();
      final IWorkspaceRunnable runnable = new IWorkspaceRunnable() {

        public void run(final IProgressMonitor monitor) throws CoreException {
        	x10BuilderFileOp.cleanFiles(CountableIterableFactory.create(sourcesToCompile, nativeFiles, deletedSources), 
        			subMonitor.newChild(5));
        }

      };
      
      checkForCancelation(subMonitor);
      
      workspace.run(runnable, rule, IWorkspace.AVOID_UPDATE, subMonitor);
      
      return dependentProjects;
    } finally {
      subMonitor.done();
    }
  }

  // --- Overridden methods

  protected void clean(final IProgressMonitor monitor) throws CoreException {
    if (getProject().isAccessible()) {
      if (this.fProjectWrapper == null) {
        this.fProjectWrapper = JavaCore.create(getProject());
      }
      this.fDependencyInfo.clearAllDependencies();
      IX10BuilderFileOp x10BuilderOp = null;
      try {
        x10BuilderOp = createX10BuilderFileOp();
      } catch (CoreException except) {
        CoreResourceUtils.addBuildMarkerTo(getProject(), except.getMessage(), IMarker.SEVERITY_ERROR, IMarker.PRIORITY_NORMAL);
        return;
      }
      if (! x10BuilderOp.hasAllPrerequisites()) {
        CoreResourceUtils.addBuildMarkerTo(getProject(), NLS.bind(Messages.AXB_IncompleteConfMsg, getProject().getName()),
                                           IMarker.SEVERITY_ERROR, IMarker.PRIORITY_HIGH);
        UIUtils.showProblemsView();
      }
      cleanFiles(CLEAN_BUILD, SubMonitor.convert(monitor), new HashSet<IFile>(), new HashSet<IFile>(), new HashSet<IFile>(),
                 x10BuilderOp);
    }
  }

  protected void startupOnInitialize() {
    if (getProject().isAccessible()) {
      if (this.fProjectWrapper == null) {
        this.fProjectWrapper = JavaCore.create(getProject());
      }
      if (this.fDependencyInfo == null) {
        this.fDependencyInfo = new PolyglotDependencyInfo(fProjectWrapper);
      }
    }
  }
  
  public IJavaProject getJavaProject(){
	  return fProjectWrapper;
  }

  /**
   * Returns the main generated file for the X10 file provided, i.e. for C++ back-end this should return the C++ file
   * corresponding to the X10 file in question.
   * 
   * @param project
   *          The project containing the X10 file in question.
   * @param x10File
   *          The x10 file to consider.
   * @return A non-null file if we found one, otherwise <b>null</b>.
   * @throws CoreException
   *           Occurs if we could not access some project information or get the local file for the location identified.
   */
  public File getMainGeneratedFile(final IJavaProject project, final IFile x10File) throws CoreException {
    final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
    for (final IClasspathEntry cpEntry : project.getRawClasspath()) {
      if (cpEntry.getEntryKind() == IClasspathEntry.CPE_SOURCE && cpEntry.getPath().isPrefixOf(x10File.getFullPath())
    		  && !BuildPathUtils.isExcluded(x10File.getFullPath(), cpEntry)){
        final IPath outputLocation;
        if (cpEntry.getOutputLocation() == null) {
          outputLocation = project.getOutputLocation();
        } else {
          outputLocation = cpEntry.getOutputLocation();
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(File.separatorChar).append(x10File.getProjectRelativePath().removeFileExtension().toString())
          .append(getFileExtension());
        final IPath projectRelativeFilePath = new Path(sb.toString());
        final int srcPathCount = cpEntry.getPath().removeFirstSegments(1).segmentCount();
        final IPath generatedFilePath = outputLocation.append(projectRelativeFilePath.removeFirstSegments(srcPathCount));
        final IFileStore fileStore = EFS.getLocalFileSystem().getStore(URIUtils.getExpectedURI(root.getFile(generatedFilePath).getLocationURI()));
        if (fileStore.fetchInfo().exists()) {
          return fileStore.toLocalFile(EFS.NONE, null);
        }
      }
    }
    return null;
  }
  
  // --- Code for descendants
  
  protected void buildOptions(final String classPath, final List<File> sourcePath, final String localOutputDir,
                              final Options options, final boolean withMainMethod) {
    options.assertions = true;
    options.classpath = classPath;
    options.output_classpath = options.classpath;
    options.output_directory = new File(localOutputDir);
    options.source_path = sourcePath;
    options.compile_command_line_only = true;

    final IPreferencesService prefService = X10DTCorePlugin.getInstance().getPreferencesService();
    options.assertions = prefService.getBooleanPreference(X10Constants.P_PERMITASSERT);
    CompilerOptionsFactory.setOptions(prefService, (X10CompilerOptions) options);
  }

  // --- Private code

  private void filter(Collection<IFile> files){
	Collection<IFile> filesToRemove = new ArrayList<IFile>();
	for(IFile file: files){
		if (BuildPathUtils.isExcluded(file.getFullPath(), this.fProjectWrapper)){
			filesToRemove.add(file);
		}
	}
	files.removeAll(filesToRemove);
  }
  
  
  
  private ISchedulingRule getSchedulingRule(Collection<IFile> files){
	  Collection<ISchedulingRule> rules = new ArrayList<ISchedulingRule>();
	  for(IFile file: files){
		  rules.add(file);
	  }
	  return MultiRule.combine(rules.toArray(new ISchedulingRule[0]));
  }
  
  private void clearMarkers(final Collection<IFile> sourcesToCompile) {
    for (final IFile file : sourcesToCompile) {
      CoreResourceUtils.deleteBuildMarkers(file);
    }
  }

  private void collectSourceFilesToCompile(final Collection<IFile> sourcesToCompile, final Collection<IFile> nativeFiles,
                                           final Collection<IFile> deletedSources, final IJavaProject javaProject,
                                           final Set<IProject> dependentProjects, final IFilter<IFile> nativeFilesFilter,
                                           final boolean fullBuild, final SubMonitor monitor) throws CoreException {
    try {
      monitor.beginTask(Messages.CPPB_CollectingSourcesTaskName, 1);

     

      final Set<Object> fullBuildSet  = new HashSet<Object>();
      Collection<IProject>projects = new ArrayList<IProject>();
      projects.add(javaProject.getProject());
      projects.addAll(ProjectUtils.getDependentProjects(javaProject));
      
      for (IProject project: projects) {
        final IResourceDelta resourceDelta = getDelta(project);
          if (resourceDelta != null) {
              resourceDelta.accept(new IResourceDeltaVisitor() {

          public boolean visit(final IResourceDelta delta) throws CoreException {
            if (delta.getResource().getType() == IResource.FOLDER) {
              final IFolder folder = (IFolder) delta.getResource();
              if (delta.getKind() == IResourceDelta.REMOVED || delta.getKind() == IResourceDelta.ADDED) {
                sourcesToCompile.addAll(getChangeDependents(folder));
              } 
            }
            if (delta.getResource().getType() == IResource.FILE) {
              final IFile file = (IFile) delta.getResource();
              if (isClassPathFile(file)){
            	  fullBuildSet.add(new Object()); // --- If the classpath has been changed, then we need to do a full build.
              }
              if (isX10File(file) || isClassFile(file)) {
                if (delta.getKind() == IResourceDelta.REMOVED) {
                  if (!isClassFile(file) && file.getProject().equals(fProjectWrapper.getProject())){
                    deletedSources.add(file);
                  }
                  sourcesToCompile.addAll(getChangeDependents(file));
                } else if (delta.getKind() == IResourceDelta.ADDED || delta.getKind() == IResourceDelta.CHANGED) {
                  if (!isClassFile(file) && file.getProject().equals(fProjectWrapper.getProject())){
                    sourcesToCompile.add(file);
                  }
                  sourcesToCompile.addAll(getChangeDependents(file));
                } 
              }
            }
            return true;
          }

           });
          }
      }
      final boolean buildAll = !fullBuildSet.isEmpty() || fullBuild; 
      final IPreferencesService prefService = X10DTCorePlugin.getInstance().getPreferencesService();
      final boolean conservativeBuild = prefService.getBooleanPreference(X10Constants.P_CONSERVATIVEBUILD);

      final IResourceVisitor visitor = new IResourceVisitor() {

        // --- Interface methods implementation

        public boolean visit(final IResource resource) throws CoreException {
          if ((resource.getType() == IResource.FILE) && !resource.isDerived()) {
            final IFile file = (IFile) resource;
            if (isX10File(file)) {
              final File generatedFile = getMainGeneratedFile(AbstractX10Builder.this.fProjectWrapper, file);
              boolean unprocessed = ((generatedFile == null) && ! CoreResourceUtils.hasBuildErrorMarkers(file));
              if (buildAll || (conservativeBuild && unprocessed)) {
                sourcesToCompile.add(file);

                /*if (!resource.getProject().equals(javaProject.getProject())) {
                  dependentProjects.add(resource.getProject());
                }*/
              }
            } else if (nativeFilesFilter.accepts(file)) {
              nativeFiles.add(file);
            }
          }
          return true;
        }

      };

      if (buildAll || conservativeBuild) {
        final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        for (final IClasspathEntry cpEntry : javaProject.getRawClasspath()) {
          if (cpEntry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
            if (cpEntry.getPath().segmentCount() > 1) {
              final IFolder folder = root.getFolder(cpEntry.getPath());
              if (folder.exists()){
                folder.accept(visitor);
              }
            }
          }
        }
      }
    } finally {
      monitor.done();
    }
  }
  
  private void clearDependencyInfoAsNeeded(Collection<IFile> sourcesToCompile, Collection<IFile> deletedSources){
	  for (IFile srcFile: sourcesToCompile){
		  fDependencyInfo.clearDependenciesOf(srcFile.getFullPath().toOSString());
	  }
	  
	  for (IFile srcFile: deletedSources){
		  fDependencyInfo.clearDependenciesOf(srcFile.getFullPath().toOSString());
	  }
  }
  
  private void info() throws CoreException {
	  final IResourceVisitor visitor = new IResourceVisitor() {
		   public boolean first = true;
	        // --- Interface methods implementation

	        public boolean visit(final IResource resource) throws CoreException {
	          if ((resource.getType() == IResource.FILE) && !resource.isDerived()) {
	            final IFile file = (IFile) resource;
	            if (isX10File(file)) {
	              final File generatedFile = getMainGeneratedFile(AbstractX10Builder.this.fProjectWrapper, file);
	              boolean unprocessed = ((generatedFile == null) && ! CoreResourceUtils.hasBuildErrorMarkers(file));
	              if (unprocessed  && ! BuildPathUtils.isExcluded(file.getFullPath(), AbstractX10Builder.this.fProjectWrapper)){
	            	  if (first) {
	            		  first = false;
	            		  CoreResourceUtils.addBuildMarkerTo(getProject(), Messages.AXB_Unprocessed, IMarker.SEVERITY_ERROR, IMarker.PRIORITY_NORMAL);
	            	  }
	            	  LaunchCore.log(IStatus.ERROR, NLS.bind(Messages.AXB_UnprocessedFile, file.getFullPath().toOSString()));
	              }
	            }
	          }
	          return true;
	        }

	  };
	  
	  final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
	  for (final IClasspathEntry cpEntry : fProjectWrapper.getRawClasspath()) {
	    if (cpEntry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
		 if (cpEntry.getPath().segmentCount() > 1) {
			 final IFolder folder = root.getFolder(cpEntry.getPath());
			 if (folder.exists()){
				 folder.accept(visitor);
			 }
		 }
	   }
	}  
  }
	      
  

  private void compile(final String localOutputDir, final Collection<IFile> sourcesToCompile,
                       final IX10BuilderFileOp builderOp, final SubMonitor subMonitor) throws CoreException {
    final IWorkspace workspace = ResourcesPlugin.getWorkspace();
    final ISchedulingRule rule = getSchedulingRule(sourcesToCompile);
    final IWorkspaceRunnable runnable = new IWorkspaceRunnable() {

      public void run(final IProgressMonitor monitor) throws CoreException {
    	Map<String, Collection<String>> generatedFiles = compileX10Files(localOutputDir, sourcesToCompile, subMonitor.newChild(20));
    	compileGeneratedFiles(builderOp, generatedFiles, sourcesToCompile, subMonitor.newChild(65));
      }

    };

    checkForCancelation(subMonitor);
    
    workspace.run(runnable, rule, IWorkspace.AVOID_UPDATE, subMonitor);
  }
  
  private void compileGeneratedFiles(final IX10BuilderFileOp builderOp, final Map<String, Collection<String>> generatedFiles,
                                     final Collection<IFile> sourcesToCompile, final SubMonitor monitor) throws CoreException {
    checkForCancelation(monitor);
    if (! generatedFiles.isEmpty()) {
      monitor.beginTask(null, 80);
      final Map<IPath, Collection<File>> sourcesToPostCompile = new HashMap<IPath, Collection<File>>();
      for(IFile srcFile: sourcesToCompile){ 
    	  final String name = BuildPathUtils.getBareName(srcFile, this.fProjectWrapper);
    	  final IPath packagePath = getPackagePath(srcFile);
    	  if (generatedFiles.containsKey(name)){ // --- Code was generated for srcFile during this build.
    		  if (checkPostCompilationCondition(srcFile.getFullPath().toString())) {
    			  addGeneratedFile(sourcesToPostCompile, packagePath, getLocalFiles(generatedFiles.get(name)));
    		  }
    		  addGeneratedFile(sourcesToPostCompile, packagePath, getFilesBlockedForPostCompilation(name));
    	  }
    	  
      }
      checkForCancelation(monitor);
      builderOp.transfer(sourcesToPostCompile, monitor.newChild(10));
      checkForCancelation(monitor);
      builderOp.compile(monitor.newChild(70));
    } 
    checkForCancelation(monitor);
    builderOp.archive(monitor);
  }
  
  private void checkForCancelation(IProgressMonitor monitor){
    if (monitor.isCanceled()) {
      throw new OperationCanceledException();
    }
  }
  
  private void addGeneratedFile(final Map<IPath, Collection<File>> map, final IPath pkgPath, final Collection<File> files) {
    final Collection<File> values = map.get(pkgPath);
    if (values == null) {
      map.put(pkgPath, files);
    } else {
      values.addAll(files);
    }
  }
  
  private IPath getPackagePath(final IFile file) {
    final IPath projectRelativePath = file.getProjectRelativePath().removeLastSegments(1);
    final String src = projectRelativePath.segment(0);
    if (src == null) {
      return projectRelativePath;
    } else {
      final IJavaProject javaProject = JavaCore.create(file.getProject());
      final IPath srcPath = file.getProject().getFolder(src).getFullPath();
      try {
        for (final IClasspathEntry cpEntry : javaProject.getRawClasspath()) {
          if (cpEntry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
            if (srcPath.equals(cpEntry.getPath())) {
              return projectRelativePath.removeFirstSegments(1);
            }
          }
        }
      } catch (JavaModelException except) {
        // Let's forget and try with the current relative path given from Core Resources.        
      }
      return projectRelativePath;
    }
  }
  
  private Collection<File> getFilesBlockedForPostCompilation(String name) throws CoreException {
	  Collection<File> result = new ArrayList<File>();
	  Collection<String> paths = fBlockingPostCompilation.get(name);
	  Collection<String> pathsToRemove = new ArrayList<String>();
	  if (paths != null) { 
		  for(String path: paths){
			  if (checkPostCompilationCondition(path)){
				  Collection<String> gens = fGeneratedFiles.get(BuildPathUtils.getBareName(path, fProjectWrapper));
				  result.addAll(getLocalFiles(gens));
				  pathsToRemove.add(path);
			  }
		  }
		  paths.removeAll(pathsToRemove);
	  }
	  return result;
  }
  
  private boolean checkPostCompilationCondition(final String srcPath)throws CoreException {
	  Set<String> dependencies = this.fDependencyInfo.getDependencies().get(srcPath);
	  final IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
	  if (dependencies != null){
		  for(String path: dependencies){
			  IFile dep = wsRoot.getFile(new Path(path));
			  if (!dep.exists()) { // --- Some dependencies are to folders
				  continue;
			  }
			  IJavaProject project = JavaCore.create(dep.getProject());
			  File f = getMainGeneratedFile(project, dep);
			  if (f == null){
				  addToBlockingPostCompilation(BuildPathUtils.getBareName(path, fProjectWrapper), srcPath);
				  return false;
			  }
		  }
	  }
	  return true;
  }

  
  private void addToBlockingPostCompilation(String name, String path){
	  Collection<String> blocked = fBlockingPostCompilation.get(name);
	  if (blocked == null){
		  blocked = new HashSet<String>();
		  fBlockingPostCompilation.put(name, blocked);
	  }
	  blocked.add(path);
  }
  
  public Collection<File> getLocalFiles(final Collection<String> generatedStrings) throws JavaModelException{
	  Collection<File> result = new ArrayList<File>();
	  final IPath root = ResourcesPlugin.getWorkspace().getRoot().getLocation();
	  final File wsRoot = root.toFile();
      final File outputLocation = new File(wsRoot, this.fProjectWrapper.getOutputLocation().toString().substring(1));
	  for (String name: generatedStrings){
		  if (name.startsWith(root.toOSString())){
			  result.add(new File(name));
		  }
		  else {
			  result.add(new File(outputLocation, name));
		  }
	  }
	  return result;
  }

  //protected abstract String getSrcClassPath(List<File> sourcePath) throws CoreException;
  
  protected String getSrcClassPath(List<File> sourcePath) throws CoreException{
    StringBuffer bufferPath = new StringBuffer();
    final Set<IPath> srcPaths = ProjectUtils.getFilteredCpEntries(this.fProjectWrapper, new IdentityFunctor<IPath>(),
            new AlwaysTrueFilter<IPath>(), bufferPath);
        
    // removeSrcJava(srcPaths);
    // TODO: I should have threaded a list around   
    sourcePath.addAll(CollectionUtils.transform(srcPaths, new IPathToFileFunc()));
        
    return bufferPath.toString();

}
  
  private Map<String, Collection<String>> compileX10Files(final String localOutputDir, final Collection<IFile> sourcesToCompile,
                                           final IProgressMonitor monitor) throws CoreException {
	checkSrcFolders();  
	
	List<File> sourcePath = new ArrayList<File>();
	String classPath = getSrcClassPath(sourcePath);
    

    ExtensionInfo extInfo = createExtensionInfo(classPath, sourcePath, localOutputDir,
                                                      false /* withMainMethod */, monitor);

    echoCommandLineToConsole(sourcesToCompile, extInfo);

    final Compiler compiler = new Compiler(extInfo, new X10ErrorQueue(this.fProjectWrapper, 1000000, extInfo.compilerName(), sourcesToCompile));
    Globals.initialize(compiler);
    try {
      final Collection<String> files = new ArrayList<String>();
      for (final IFile f : sourcesToCompile) {
        files.add(f.getLocation().toOSString());
      }
     
      checkForCancelation(monitor);
      compiler.compileFiles(files);
      // compiler.compile(toSources(sourcesToCompile)); // --- This way of calling the compiler causes a bad behavior
      // (duplicate class error),
      // --- when there is a file that imports another one file a bad syntactic error.
      
      
      final Map<String, Collection<String>> outputFiles = compiler.outputFiles();
      updateGeneratedFiles(outputFiles, sourcesToCompile);
      return outputFiles;
    } catch (InternalCompilerError except) {
      LaunchCore.log(IStatus.ERROR, Messages.AXB_CompilerInternalError, except);
      // The exception is also pushed on the error queue... A marker will be created accordingly for it.
      sourcesToCompile.clear(); // To prevent post-compilation step.
      return Collections.emptyMap();
    } finally {
      if (!monitor.isCanceled())
        analyze(extInfo.scheduler().commandLineJobs());	
      Globals.initialize(null);
    }
  }
  

  // Constants that describe the role of each slot in the 2D array that Configuration.options() returns.
  private static final int OPTION_NAME = 0;
  private static final int OPTION_TYPE = 1;
  private static final int OPTION_DESCRIPTION = 2;
  private static final int OPTION_VALUE = 3;

  private void echoCommandLineToConsole(final Collection<IFile> sourcesToCompile, final ExtensionInfo extInfo) {
    final IPreferencesService prefService = X10DTCorePlugin.getInstance().getPreferencesService();

    if (prefService.getBooleanPreference(X10Constants.P_ECHOCOMPILEARGUMENTSTOCONSOLE)) {
    	final MessageConsole console = UIUtils.findOrCreateX10Console();
    	final MessageConsoleStream consoleStream = console.newMessageStream();
    	try {
    		X10CompilerOptions options= extInfo.getOptions();
    		String[][] opts = options.x10_config.options(); // --- The shape of this data structure is an array of:
    		// --- (option,type,description,value) for each field in Configuration.

    		consoleStream.write(getOptions(options));

    		String result = "";
    		for (int j = 0; j < opts.length; j++) {
    			if (opts[j][OPTION_TYPE].equals("boolean")) {
    				if (opts[j][OPTION_VALUE].equals("true")) {
    					result += " -" + opts[j][OPTION_NAME] + " ";
    				}
    			}
    			if (opts[j][OPTION_TYPE].equals("String")) {
    				if (!opts[j][OPTION_VALUE].equals("null") && !opts[j][OPTION_VALUE].equals("")) {
    					result += " -" + opts[j][OPTION_NAME] + "=" + opts[j][OPTION_VALUE] + " ";
    				}
    			}
    		}
    		consoleStream.write(result);
    		for (IFile f : sourcesToCompile) {
    			consoleStream.write("\"");
    			consoleStream.write(X10BuilderUtils.getTargetSystemPath(f.getLocation().toPortableString()));
    			consoleStream.write("\" ");
    		}
    		consoleStream.write("\n");
    		console.activate();
    	} catch (IOException except) {
    		LaunchCore.log(IStatus.ERROR, Messages.AXB_EchoIOException, except);
    	}
    }
  }

  private void checkSrcFolders() throws CoreException {
	  try {
		final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		boolean hasNoSourceTypeEntries = true;
		IClasspathEntry[] entries = this.fProjectWrapper.getResolvedClasspath(true);
		for(int i = 0; i< entries.length; i++){
			if (entries[i].getEntryKind() == IClasspathEntry.CPE_SOURCE){
				hasNoSourceTypeEntries = false;
				final IPath path = entries[i].getPath();
				if (path.segmentCount() > 1) {
          final IFileStore fileStore = EFS.getLocalFileSystem().getStore(URIUtils.getExpectedURI(root.getFile(path).getLocationURI()));
          if (!fileStore.fetchInfo().exists()) { // --- Non existent source entry
            CoreResourceUtils.addBuildMarkerTo(getProject(), NLS.bind(Messages.AXB_NonExistentSRCFolder, path.lastSegment()),
                                               IMarker.SEVERITY_ERROR, IMarker.PRIORITY_HIGH);
          }
          if (fileStore.childNames(EFS.NONE, null).length == 0) { // --- Empty source directory
            CoreResourceUtils.addBuildMarkerTo(getProject(), NLS.bind(Messages.AXB_EmptySRCFolder, path.lastSegment()),
                                               IMarker.SEVERITY_WARNING, IMarker.PRIORITY_HIGH);
          }
				}
			}
		}
		if (hasNoSourceTypeEntries) { // --- Project has no source entry
			CoreResourceUtils.addBuildMarkerTo(getProject(), NLS.bind(Messages.AXB_NoSRCFolder, getProject().getName()),
                    IMarker.SEVERITY_ERROR, IMarker.PRIORITY_HIGH);
		}
	} catch (JavaModelException e) {
		LaunchCore.log(IStatus.ERROR, Messages.AXB_BuilderProblem, e);
	}
  }
  
  private void analyze(final Collection<Job> jobs) {
    computeDependencies(jobs);
    collectBookmarks(jobs);
  }


  private void collectBookmarks(final Collection<Job> jobs) {
    for (final Job job : jobs) {
      final CollectBookmarks cb = new CollectBookmarks(job, this);
      cb.perform();
    }
  }

  private void computeDependencies(final Collection<Job> jobs) {
    for (final Job job : jobs) {
      final ComputeDependenciesVisitor visitor = new ComputeDependenciesVisitor(this.fProjectWrapper, job, 
                                                                                job.extensionInfo().typeSystem(),
                                                                                this.fDependencyInfo);
      if (job.ast() != null) {
        job.ast().visit(visitor.begin());
      }
    }
  }

  private Collection<IFile> getChangeDependents(final IResource srcFile) {
    final Collection<IFile> result = new ArrayList<IFile>();
    final Set<String> fileDependents = this.fDependencyInfo.getDependentsOf(srcFile.getFullPath().toOSString());
    final IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
    if (fileDependents != null) {
      for (final String dependent : fileDependents) {
        result.add(wsRoot.getFile(new Path(dependent)));
      }
    }
    return result;
  }
  
  private void updateGeneratedFiles(Map<String, Collection<String>> generatedFiles, Collection<IFile> sourceFiles){
	  for(IFile srcFile: sourceFiles){
		  try { 
		  	String name = BuildPathUtils.getBareName(srcFile, fProjectWrapper);
		  	if (generatedFiles.containsKey(name)){
			  fGeneratedFiles.put(name, generatedFiles.get(name));
		  	} else {
		  	  fGeneratedFiles.remove(name);
		  	}
		  } catch(JavaModelException e){
			  LaunchCore.log(IStatus.ERROR, Messages.AXB_BuilderProblem, e);
		 }
	  }
  }

  private boolean isX10File(final IFile file) {
    return Constants.X10_EXT.equals('.' + file.getFileExtension());
  }
  
  private boolean isClassFile(final IFile file) {
    if (file.getFileExtension() != null){
      return file.getFileExtension().equals("class");
    }
    return false;
  }
  
  private boolean isClassPathFile(final IFile file) {
	    return file.getName().equals(".classpath");
  }
  
	private String getOptions(Options options) {
		String result = "";
		if (options.classpath != null && !options.classpath.equals("")) {
			result += "-classpath \"";
			for (String token : options.classpath.split(File.pathSeparator)) {
				result += X10BuilderUtils.getTargetSystemPath(token);
				result += File.pathSeparator;
			}
			result += "\"";
		}
		if (options.output_directory != null) {
			result += " -d \""
					+ X10BuilderUtils
							.getTargetSystemPath(options.output_directory
									.getAbsolutePath());
			result += "\"";
		}
		if (options.assertions) {
			result += " -assert";
		}
		if (options.source_path != null && !options.source_path.isEmpty()) {
			result += " -sourcepath \"";
			for (int i = 0; i < options.source_path.size(); i++) {
				result += ((i > 0) ? ":" : "")
						+ X10BuilderUtils
								.getTargetSystemPath(options.source_path.get(i)
										.getAbsolutePath());
			}
			result += "\"";
		}
		if (options.bootclasspath != null && !options.bootclasspath.equals("")) {
			result += " -bootclasspath " + options.bootclasspath;
		}
		if (options.compile_command_line_only) {
			result += " -commandlineonly";
		}
		if (options.fully_qualified_names) {
			result += " -fqcn";
		}
		if (options.source_ext != null) {
			for (int i = 0; i < options.source_ext.length; i++) {
				result += " -sx " + options.source_ext[i];
			}
		}
		if (options.output_ext != null && !options.output_ext.equals("")) {
			result += " -ox " + options.output_ext;
		}
		result += " -errors " + options.error_count;
		result += " -w " + options.output_width;
		for (String s : options.dump_ast) {
			result += " -dump " + s;
		}
		for (String s : options.print_ast) {
			result += " -print " + s;
		}
		for (String s : options.disable_passes) {
			result += " -disable " + s;
		}
		if (!options.serialize_type_info) {
			result += " -noserial";
		}
		if (!options.keep_output_files) {
			result += " -nooutput";
		}
		if (options.post_compiler != null && !options.post_compiler.equals("")) {
			result += " -post " + options.post_compiler;
		}
		if (options.precise_compiler_generated_positions) {
			result += " -debugpositions";
		}
		if (options.use_simple_code_writer) {
			result += " -simpleoutput";
		}
		return result;
	}
	
	
	public static Collection<IPath> getProjectDependencies(IJavaProject project) throws JavaModelException {
      Collection<IPath> ret = new ArrayList<IPath>();
      if (project == null) return ret;
      for (final IClasspathEntry cpEntry : project.getRawClasspath()) {
        if (cpEntry.getEntryKind() == IClasspathEntry.CPE_PROJECT){
            final IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
            IJavaProject javaProject = JavaCore.create(wsRoot.getProject(cpEntry.getPath().toOSString()));
            ret.add(wsRoot.getLocation().append(javaProject.getOutputLocation()));
            for (final IClasspathEntry pEntry: javaProject.getRawClasspath()){
                if (pEntry.getEntryKind() == IClasspathEntry.CPE_SOURCE){
                    if (pEntry.getOutputLocation() != null){
                        ret.add(wsRoot.getLocation().append(pEntry.getOutputLocation()));
                    }
                }
            }
        }
      }
      return ret;
	}
	
	public static Collection<String> getProjectDependenciesNames(IJavaProject project) throws JavaModelException {
      Collection<String> ret = new ArrayList<String>();
      for (final IClasspathEntry cpEntry : project.getRawClasspath()) {
        if (cpEntry.getEntryKind() == IClasspathEntry.CPE_PROJECT){
            final IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
            ret.add(wsRoot.getProject(cpEntry.getPath().toOSString()).getName());
        }
      }
      return ret;
    }

  // --- Fields

  
	
  private PolyglotDependencyInfo fDependencyInfo;

  protected IJavaProject fProjectWrapper;
  
  protected Map<String, Collection<String>> fGeneratedFiles = new HashMap<String, Collection<String>>(); 
  
  protected Map<String, Collection<String>> fBlockingPostCompilation = new HashMap<String, Collection<String>>(); // --- X10 sources.

}
