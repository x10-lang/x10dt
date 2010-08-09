/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package org.eclipse.imp.x10dt.ui.launch.cpp.builder;

import static org.eclipse.imp.x10dt.ui.launch.core.Constants.CC_EXT;

import java.io.File;
import java.util.List;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.imp.preferences.IPreferencesService;
import org.eclipse.imp.x10dt.core.X10DTCorePlugin;
import org.eclipse.imp.x10dt.core.preferences.generated.X10Constants;
import org.eclipse.imp.x10dt.ui.launch.core.Constants;
import org.eclipse.imp.x10dt.ui.launch.core.builder.AbstractX10Builder;
import org.eclipse.imp.x10dt.ui.launch.core.builder.target_op.IX10BuilderFileOp;
import org.eclipse.imp.x10dt.ui.launch.core.utils.IFilter;
import org.eclipse.imp.x10dt.ui.launch.core.utils.ProjectUtils;
import org.eclipse.imp.x10dt.ui.launch.cpp.CppLaunchCore;
import org.eclipse.imp.x10dt.ui.launch.cpp.LaunchMessages;
import org.eclipse.imp.x10dt.ui.launch.cpp.platform_conf.IX10PlatformConf;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.osgi.util.NLS;

import x10.Configuration;
import x10.ExtensionInfo;
import x10.config.ConfigurationError;
import x10.config.OptionError;
import x10cpp.X10CPPCompilerOptions;

/**
 * X10 builder for C++ back-end.
 * 
 * @author egeay
 */
public final class X10CppBuilder extends AbstractX10Builder {
  
  // --- Abstract methods implementation
  
  public ExtensionInfo createExtensionInfo(final String classPath, final List<File> sourcePath, final String localOutputDir,
                                           final boolean withMainMethod, final IProgressMonitor monitor) {
    final ExtensionInfo extInfo = new CppBuilderExtensionInfo(monitor);
    buildOptions(classPath, sourcePath, localOutputDir, (X10CPPCompilerOptions) extInfo.getOptions(), withMainMethod);
    return extInfo;
  }
  
  public IFilter<IFile> createNativeFilesFilter() {
    return new NativeFilesFilter();
  }
  
  public IX10BuilderFileOp createX10BuilderFileOp() throws CoreException {
    final IX10PlatformConf platformConf = CppLaunchCore.getInstance().getPlatformConfiguration(getProject());
    if (platformConf.getConnectionConf().isLocal()) {
      return new LocalX10BuilderFileOp(getProject(), ProjectUtils.getProjectOutputDirPath(getProject()), platformConf);
    } else {
      return new RemoteX10BuilderFileOp(getProject(), platformConf);
    }
  }
  
  public File getMainGeneratedFile(final IJavaProject project, final IFile x10File) throws CoreException {
    final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
    for (final IClasspathEntry cpEntry : project.getRawClasspath()) {
      if (cpEntry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
        final IPath outputLocation;
        if (cpEntry.getOutputLocation() == null) {
          outputLocation = project.getOutputLocation();
        } else {
          outputLocation = cpEntry.getOutputLocation();
        }
        final StringBuilder sb = new StringBuilder();
        sb.append('/').append(x10File.getProjectRelativePath().removeFileExtension().toString()).append(CC_EXT);
        final IPath projectRelativeFilePath = new Path(sb.toString());
        final int srcPathCount = cpEntry.getPath().removeFirstSegments(1).segmentCount();
        final IPath generatedFilePath = outputLocation.append(projectRelativeFilePath.removeFirstSegments(srcPathCount));
        final IFileStore fileStore = EFS.getLocalFileSystem().getStore(root.getFile(generatedFilePath).getLocationURI());
        if (fileStore.fetchInfo().exists()) {
          return fileStore.toLocalFile(EFS.NONE, null);
        }
      }
    }
    return null;
  }
  
  // --- Private code
  
  private void buildOptions(final String classPath, final List<File> sourcePath, final String localOutputDir,
                            final X10CPPCompilerOptions options, final boolean withMainMethod) {
    options.assertions = true;
    options.classpath = classPath;
    options.output_classpath = options.classpath;
    options.serialize_type_info = false;
    options.output_directory = new File(localOutputDir);
    options.source_path = sourcePath;
    options.compile_command_line_only = true;
    options.post_compiler = null;
    x10cpp.Configuration.MAIN_CLASS = (withMainMethod) ? null : Constants.EMPTY_STR;
    
    final IPreferencesService prefService = X10DTCorePlugin.getInstance().getPreferencesService();
    // Compiler prefs
    Configuration.STATIC_CALLS = prefService.getBooleanPreference(X10Constants.P_STATICCALLS);
    Configuration.VERBOSE_CALLS = prefService.getBooleanPreference(X10Constants.P_VERBOSECALLS);
    options.assertions = prefService.getBooleanPreference(X10Constants.P_PERMITASSERT);
    final String additionalOptions = prefService.getStringPreference(X10Constants.P_ADDITIONALCOMPILEROPTIONS);
    if ((additionalOptions != null) && (additionalOptions.length() > 0)) {
      // First initialize to default values.
      Configuration.DEBUG = false;
      Configuration.CHECK_INVARIANTS = false;
      Configuration.ONLY_TYPE_CHECKING = false;
      Configuration.NO_CHECKS = false;
      Configuration.FLATTEN_EXPRESSIONS = false;
      for (final String opt : additionalOptions.split("\\s")) { ////$NON-NLS-1$
        try {
          Configuration.parseArgument(opt);
        } catch (OptionError except) {
          CppLaunchCore.log(IStatus.ERROR,  NLS.bind(LaunchMessages.XCB_OptionError, opt), except);
        } catch (ConfigurationError except) {
          CppLaunchCore.log(IStatus.ERROR,  NLS.bind(LaunchMessages.XCB_ConfigurationError, opt), except);
        }
      }
    }
    // Optimization prefs
    Configuration.OPTIMIZE = prefService.getBooleanPreference(X10Constants.P_OPTIMIZE);
    Configuration.LOOP_OPTIMIZATIONS = prefService.getBooleanPreference(X10Constants.P_LOOPOPTIMIZATIONS);
    Configuration.INLINE_OPTIMIZATIONS = prefService.getBooleanPreference(X10Constants.P_INLINEOPTIMIZATIONS);
    Configuration.CLOSURE_INLINING = prefService.getBooleanPreference(X10Constants.P_CLOSUREINLINING);
    Configuration.WORK_STEALING = prefService.getBooleanPreference(X10Constants.P_WORKSTEALING);
  }
  
  // --- Private classes
  
  private static final class NativeFilesFilter implements IFilter<IFile> {

    // --- Interface methods implementation
    
    public boolean accepts(final IFile element) {
      final String extension = '.' + element.getFileExtension();
      for (final String possibleExtension : POSSIBLE_EXTENSIONS) {
        if (possibleExtension.equals(extension)) {
          return true;
        }
      }
      return false;
    }
    
    // --- Fields
    
    private static final String[] POSSIBLE_EXTENSIONS = { Constants.CC_EXT, Constants.CPP_EXT, Constants.CXX_EXT,
                                                          Constants.H_EXT, Constants.HPP_EXT,
                                                          Constants.INC_EXT };
    
  }
  
}
