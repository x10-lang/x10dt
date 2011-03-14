/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.platform_conf.validation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.imp.utils.Pair;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ptp.remote.core.IRemoteConnection;
import org.eclipse.ptp.remote.core.IRemoteFileManager;
import org.eclipse.ptp.remote.core.IRemoteProcess;
import org.eclipse.ptp.remote.core.IRemoteProcessBuilder;
import org.eclipse.ptp.remote.core.IRemoteServices;
import org.eclipse.ptp.remote.core.PTPRemoteCorePlugin;
import org.osgi.framework.Bundle;

import polyglot.frontend.Compiler;
import polyglot.frontend.Globals;
import polyglot.frontend.Source;
import polyglot.util.ErrorInfo;
import polyglot.util.ErrorQueue;
import polyglot.util.Position;
import x10.ExtensionInfo;
import x10cpp.X10CPPCompilerOptions;
import x10cpp.visit.MessagePassingCodeGenerator;
import x10dt.core.builder.StreamSource;
import x10dt.core.utils.CompilerOptionsFactory;
import x10dt.core.utils.X10BundleUtils;
import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.utils.FileUtils;
import x10dt.ui.launch.core.utils.IFilter;
import x10dt.ui.launch.core.utils.IProcessOuputListener;
import x10dt.ui.launch.core.utils.X10BuilderUtils;
import x10dt.ui.launch.cpp.CppLaunchCore;
import x10dt.ui.launch.cpp.LaunchMessages;
import x10dt.ui.launch.cpp.builder.X10CppBuilder;
import x10dt.ui.launch.cpp.platform_conf.ICppCompilationConf;
import x10dt.ui.launch.cpp.utils.PTPUtils;
import x10dt.ui.utils.WizardUtils;


final class CppCompilationChecker implements ICppCompilationChecker {
  
CppCompilationChecker(final String rmServicesId, final IRemoteConnection rmConnection, ICppCompilationConf compilationConf, IProject project) {
    this.fRemoteServices = PTPRemoteCorePlugin.getDefault().getRemoteServices(rmServicesId);
    this.fRemoteConnection = rmConnection;
    this.fCompilationConf = compilationConf;
    this.fProject = project;
  }
  
  // --- IPlatformConfChecker's interface methods implementation

  public Pair<String,String> validateArchiving(final SubMonitor monitor) throws Exception {
    final String archiver = this.fCompilationConf.getArchiver();
    final String archivingOptions = this.fCompilationConf.getArchivingOpts();

    monitor.beginTask(null, 10);
    monitor.subTask(LaunchMessages.APCC_ArchivingTaskMsg);
    
    final IRemoteFileManager fileManager = this.fRemoteServices.getFileManager(this.fRemoteConnection);
    try {
      return execute(getArchivingCommand(archiver, archivingOptions), fileManager, monitor);
    } catch (Exception except) {
      fileManager.getResource(this.fWorkDir).delete(EFS.NONE, new NullProgressMonitor());
      throw except;
    }
  }
  
  public Pair<String,String> validateCompilation(final SubMonitor monitor) throws Exception {
    final boolean isLocal = PTPRemoteCorePlugin.getDefault().getDefaultServices().equals(this.fRemoteServices);
    final String compiler = this.fCompilationConf.getCompiler();
    final String compilingOptions = this.fCompilationConf.getCompilingOpts();
    final String[] x10HeadersLocs = this.fCompilationConf.getX10HeadersLocations(isLocal);
    final String[] x10LibsLocs = this.fCompilationConf.getX10LibsLocations(isLocal);

    monitor.beginTask(null, 20);
    monitor.subTask(LaunchMessages.APCC_CompilationTaskMsg);
    
    final IRemoteFileManager fileManager = this.fRemoteServices.getFileManager(this.fRemoteConnection);

    try {
      final String uniqueDirName = createUniqueDirName();
      this.fWorkDir = String.format(PATH_SEP_STRFORMAT, getTempDirectory(), uniqueDirName);
      fileManager.getResource(this.fWorkDir).mkdir(EFS.NONE, new NullProgressMonitor());
      final File testFilePath = createTestFile(uniqueDirName, monitor.newChild(1));

      // X10 compilation
      final Pair<Pair<String,String>, String> compilationResults = compileX10File(testFilePath, getContentSampleStream(),
                                                                                  this.fWorkDir, x10LibsLocs, fileManager, 
                                                                                  monitor.newChild(5));
      if (compilationResults.first != null) {
        fileManager.getResource(this.fWorkDir).delete(EFS.NONE, new NullProgressMonitor());
        return compilationResults.first;
      }
    
      // Compilation of generated file.
      final List<String> command = getCompilationCommand(compiler, compilingOptions, x10HeadersLocs, this.fWorkDir, 
                                                         compilationResults.second);
      return execute(command, fileManager, monitor.newChild(14));
    } catch (Exception except) {
      if (this.fWorkDir != null) {
      	fileManager.getResource(this.fWorkDir).delete(EFS.NONE, new NullProgressMonitor());
      }
      throw except;
    }
  }

  public Pair<String,String> validateLinking(final SubMonitor monitor) throws Exception {
    final boolean isLocal = PTPRemoteCorePlugin.getDefault().getDefaultServices().equals(this.fRemoteServices);
    final String linker = this.fCompilationConf.getLinker();
    final String linkingOptions = this.fCompilationConf.getLinkingOpts();
    final String linkingLibs = this.fCompilationConf.getLinkingLibs();
    final String[] x10HeadersLocs = this.fCompilationConf.getX10HeadersLocations(isLocal);
    final String[] x10LibsLocs = this.fCompilationConf.getX10LibsLocations(isLocal);

    monitor.beginTask(null, 20);
    monitor.subTask(LaunchMessages.APCC_LinkingTaskMsg);
    
    final IRemoteFileManager fileManager = this.fRemoteServices.getFileManager(this.fRemoteConnection);
    try {
      return execute(getLinkingCommand(linker, linkingOptions, linkingLibs, x10HeadersLocs, x10LibsLocs), 
                     fileManager, monitor);
    } finally {
      fileManager.getResource(this.fWorkDir).delete(EFS.NONE, new NullProgressMonitor());
    }
  }
  
  // --- Private code
  
  private void addMainMethodStub(final File file) {
    try {
      final X10CPPCompilerOptions options = (X10CPPCompilerOptions) CompilerOptionsFactory.createOptions(this.fProject);
      final FileOutputStream fos = new FileOutputStream(file, true);
      final String stubSource = MessagePassingCodeGenerator.createMainStub("Hello", options); //$NON-NLS-1$

      fos.write(stubSource.getBytes());
      fos.flush();
      fos.close();
    } catch (IOException except) {
      CppLaunchCore.log(IStatus.ERROR, LaunchMessages.CppCompilationChecker_errorAppendingMainMethodStub, except);
    }
  }
  
  private Collection<String> collectCCFiles(final Map<String, Collection<String>> outputFiles) {
    final Collection<String> ccFiles = new LinkedList<String>();
    for (final Collection<String> generatedFiles : outputFiles.values()) {
      for (final String generatedFile : generatedFiles) {
        if (generatedFile.endsWith(Constants.CC_EXT)) {
          ccFiles.add(generatedFile);
        }
      }
    }
    return ccFiles;
  }
  
  private Pair<Pair<String, String>, String> compileX10File(final File testFilePath, final InputStream sourceInputStream, 
                                                            final String workspaceDir, final String[] x10LibsLocs, 
                                                            final IRemoteFileManager fileManager, 
                                                            final SubMonitor monitor) throws Exception {
    monitor.beginTask(LaunchMessages.APCC_GeneratingFilesMsg, 10);
    if (monitor.isCanceled()) {
      throw new InterruptedException();
    }
    
    final Bundle x10RuntimeBundle = Platform.getBundle(X10BundleUtils.X10_RUNTIME_BUNDLE_ID);
    final File x10RuntimeDir = getDirectory(x10RuntimeBundle);
    
    final File localTestDir = testFilePath.getParentFile();
    final StringBuilder classPathBuider = new StringBuilder();
    classPathBuider.append(localTestDir.getAbsolutePath());
    final IFilter<File> libFilter = new LibFilter();
    for (final String x10LibsLoc : x10LibsLocs) {
      for (final File libFile : FileUtils.collect(new File(x10LibsLoc), libFilter, false /* recurse */)) {
        classPathBuider.append(File.pathSeparatorChar).append(libFile.getAbsolutePath());
      }
    }
    classPathBuider.append(File.pathSeparatorChar).append(x10RuntimeDir.getAbsolutePath());
    
    final List<File> srcPath = new ArrayList<File>();
    srcPath.add(localTestDir);
    srcPath.add(x10RuntimeDir);
    
    final X10CppBuilder cppBuilder = new X10CppBuilder();
    final ExtensionInfo extInfo = cppBuilder.createExtensionInfo(classPathBuider.toString(), srcPath, 
                                                                 localTestDir.getAbsolutePath(), 
                                                                 true /* withMainMethod */, monitor);
    final CompilationErrorQueue errorQueue = new CompilationErrorQueue();
    final Compiler compiler = new Compiler(extInfo, errorQueue);
    Globals.initialize(compiler);
    
    try {
      compiler.compile(Arrays.<Source> asList(new StreamSource(sourceInputStream, testFilePath.getAbsolutePath())));

      final Collection<String> ccFiles = collectCCFiles(compiler.outputFiles());
      if (! ccFiles.isEmpty()) {
        // Need to add the main method stub to the generated source file
        final String ccFileName = ccFiles.iterator().next();
        addMainMethodStub(new File(testFilePath.getParentFile().getAbsolutePath(), ccFileName));
      }

      final boolean isLocal = PTPRemoteCorePlugin.getDefault().getDefaultServices().equals(this.fRemoteServices);
      if (! isLocal) {
        monitor.subTask(LaunchMessages.APCC_TransferringFiles);
        final IFileSystem fileSystem = EFS.getLocalFileSystem();
        final IFileStore destDir = fileManager.getResource(workspaceDir);
        try {
          for (final Collection<String> generatedFiles : compiler.outputFiles().values()) {
            for (final String generatedFile : generatedFiles) {
              for (final File file : localTestDir.listFiles(new CppFileNameFilter(generatedFile))) {
                if (monitor.isCanceled()) {
                  throw new InterruptedException();
                }
                final IFileStore curFileStore = fileSystem.getStore(new Path(file.getAbsolutePath()));
                curFileStore.copy(destDir.getChild(curFileStore.getName()), EFS.OVERWRITE, null);
              }
            }
          }
        } finally {
          FileUtils.deleteDirectory(localTestDir);
        }
      }

      monitor.done();

      if (errorQueue.hasErrors()) {
        return new Pair<Pair<String,String>, String>(new Pair<String,String>(NLS.bind(LaunchMessages.CCC_X10CompilerFailedCmd,
                                                                                      classPathBuider.toString(), srcPath),
                                                                             errorQueue.getAllErrors()), 
                                                     null);
      } else {
        if (ccFiles.isEmpty()) {
          return new Pair<Pair<String,String>,String>(new Pair<String,String>(NLS.bind(LaunchMessages.CCC_X10CompilerFailedCmd,
                                                                                       classPathBuider.toString(), srcPath),
                                                                              LaunchMessages.APCC_NoGeneratedFilesError), 
                                                      null);
        } else {
          return new Pair<Pair<String,String>, String>(null, ccFiles.iterator().next());
        }
      }
    } finally {
      Globals.initialize(null);
    }
  }

  private File createTestFile(final String uniqueDirName, final SubMonitor monitor) throws Exception {
    final String dirPath = String.format(PATH_SEP_STRFORMAT, System.getProperty(TMPDIR_JAVA_ENV_VAR), uniqueDirName);
    final String testFilePath = String.format(PATH_SEP_STRFORMAT, dirPath, TEST_FILENAME);
    final File localTestFileDir = new File(dirPath);
    localTestFileDir.mkdirs();
    
    final OutputStream os = new FileOutputStream(new File(testFilePath));
    final InputStream is = getContentSampleStream();
    try {
      final byte[] b = new byte[1024];  
      int read;  
      while ((read = is.read(b)) != -1) {  
        os.write(b, 0, read);
      }
    } finally {
      is.close();
      os.close();
      monitor.done();
    }
    return new File(testFilePath);
  }
  
  private String createUniqueDirName() {
    final SimpleDateFormat format = new SimpleDateFormat("hh-mm-ss"); //$NON-NLS-1$
    final String userName = System.getProperty("user.name").replace(" ", "_"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return String.format("x10-validation-%s-%s", userName, format.format(new Date())); //$NON-NLS-1$
  }
  
  private Pair<String,String> execute(final List<String> command, final IRemoteFileManager fileManager, 
                                      final SubMonitor monitor) throws Exception {
    monitor.beginTask(null, 1);
    try {
      final IRemoteProcess process = getProcessBuilder(command).directory(fileManager.getResource(this.fWorkDir)).start();

      final StringBuilder errMsgBuilder = new StringBuilder();
      final int exitValue = PTPUtils.run(process, new IProcessOuputListener() {

        public void read(final String line) {
        }
        
        public void readError(final String line) {
          errMsgBuilder.append(line).append('\n');
        }
        
      });
      
      monitor.worked(1);
      return (exitValue == 0) ? null : new Pair<String, String>(getCommandAsString(command), errMsgBuilder.toString());
    } finally {
      monitor.done();
    }
  }
  
  private List<String> getArchivingCommand(final String archiver, final String archivingOptions) {
    final List<String> command = new ArrayList<String>();
    command.add(archiver);
    command.addAll(X10BuilderUtils.getAllTokens(archivingOptions));
    // We only support Cygwin on Windows, so next line is safe at this time.
    command.add(this.fWorkDir + TEST_LIB);
    command.add(this.fCompiledFile);
    return command;
  }
  
  private String getCommandAsString(final List<String> command) {
    final StringBuilder sb = new StringBuilder();
    for (final String str : command) {
      if (sb.length() > 0) {
        sb.append(' ');
      }
      sb.append(str);
    }
    return sb.toString();
  }
  
  private List<String> getCompilationCommand(final String compiler, final String compilingOptions,
                                             final String[] x10HeaderLocs, final String workDirectory,
                                             final String generatedFilePath) {
    final List<String> command = new ArrayList<String>();
    command.add(compiler);
    command.addAll(X10BuilderUtils.getAllTokens(compilingOptions));
    command.add(INCLUDE_OPT + workDirectory);
    for (final String headerLoc : x10HeaderLocs) {
      command.add(INCLUDE_OPT + headerLoc);
    }
    command.add("-c"); //$NON-NLS-1$
    command.add(generatedFilePath);
    command.add("-o"); //$NON-NLS-1$
    this.fCompiledFile = generatedFilePath.replace(Constants.CC_EXT, Constants.O_EXT);
    command.add(this.fCompiledFile);
    return command;
  }
  
  private InputStream getContentSampleStream() {
    final String typeName = TEST_FILENAME.substring(0, TEST_FILENAME.lastIndexOf('.'));
    return WizardUtils.createSampleContentStream(null /* packageName */, typeName);
  }
  
  private File getDirectory(final Bundle bundle) throws IOException {
    URL wURL = bundle.getResource(SRC_X10_DIR);
    if (wURL == null) {
      // We access the root of the jar where the resources should be located.
      wURL = bundle.getResource(""); //$NON-NLS-1$
    }
    final URL url = FileLocator.resolve(wURL);
    if (url.getProtocol().equals("jar")) { //$NON-NLS-1$
      final JarURLConnection jarConnection = (JarURLConnection) url.openConnection();
      return new File(jarConnection.getJarFileURL().getFile());
    } else {
      return new File(url.getFile());
    }
  }
  
  private List<String> getLinkingCommand(final String linker, final String linkingOptions, final String linkingLibs,
                                         final String[] x10HeadersLocs, final String[] x10LibsLocs) {
    final List<String> command = new ArrayList<String>();
    command.add(linker);
    command.addAll(X10BuilderUtils.getAllTokens(linkingOptions));
    command.add(INCLUDE_OPT + this.fWorkDir);
    for (final String headerLoc : x10HeadersLocs) {
      command.add(INCLUDE_OPT + headerLoc);
    }
    command.add("-o"); //$NON-NLS-1$
    command.add(TEST_PROG_NAME);
    command.add(LIB_OPT + this.fWorkDir);
    for (final String libLoc : x10LibsLocs) {
      command.add(LIB_OPT + libLoc);
    }
    command.add(TEST_LIB_LINK);
    command.addAll(X10BuilderUtils.getAllTokens(linkingLibs));
    return command;
  }
  
  private IRemoteProcessBuilder getProcessBuilder(final List<String> command) {
    return this.fRemoteServices.getProcessBuilder(this.fRemoteConnection, command);
  }
  
  private String getTempDirectory() {
    final String tmp = PTPUtils.getTempDirectory(this.fRemoteConnection, 
                                                 this.fRemoteServices.getFileManager(this.fRemoteConnection));
    if (tmp == null) {
      throw new RuntimeException(LaunchMessages.APCC_NoTempDirError);
    } else {
      return tmp;
    }
  }
  
  // --- Private classes
  
  private static final class CompilationErrorQueue implements ErrorQueue {
    
    // --- Interface methods implementation

    public void enqueue(final int type, final String message) {
      enqueue(new ErrorInfo(type, message, null));
    }

    public void enqueue(final int type, final String message, final Position position) {
      enqueue(new ErrorInfo(type, message, position));
    }

    public void enqueue(final ErrorInfo errorInfo) {
      ++this.fCounter;
      if (errorInfo.getErrorKind() >= 1 && errorInfo.getErrorKind() <= 6) {
        if (errorInfo.getPosition() == null) {
          this.fErrorBuilder.append(NLS.bind(LaunchMessages.APCC_CompilErrorWithoutPos, errorInfo.getErrorString(), 
                                             errorInfo.getMessage()));
        } else {
          this.fErrorBuilder.append(NLS.bind(LaunchMessages.APCC_CompilErrorWithPos, 
                                             new String[] { errorInfo.getErrorString(), errorInfo.getPosition().toString(), 
                                                            errorInfo.getMessage() }));
        }
      }
    }

    public int errorCount() {
      return this.fCounter;
    }

    public void flush() {
      // Do nothing. We take all the errors.
    }

    public boolean hasErrors() {
      return this.fCounter > 0;
    }
    
    // --- Internal services
    
    String getAllErrors() {
      return this.fErrorBuilder.toString();
    }
    
    // --- Fields
    
    private int fCounter;
    
    private final StringBuilder fErrorBuilder = new StringBuilder();
    
  }
  
  private static final class LibFilter implements IFilter<File> {
    
    // --- Interface methods implementation

    public boolean accepts(final File file) {
      return file.getName().endsWith(".a"); //$NON-NLS-1$
    }
    
  }
  
  private static final class CppFileNameFilter implements FilenameFilter {

    CppFileNameFilter(final String fileName) {
      this.fFileNameStart = fileName.substring(0, fileName.lastIndexOf('.'));
    }
    
    // --- Interface methods implementation
    
    public boolean accept(final File dir, final String name) {
      return name.startsWith(this.fFileNameStart) && (name.endsWith(Constants.CC_EXT) || name.endsWith(Constants.H_EXT) || 
                             name.endsWith(Constants.INC_EXT));
    }
    
    // --- Fields
    
    private final String fFileNameStart;
    
  }
  
  // --- Fields
  private final ICppCompilationConf fCompilationConf;

  private IProject fProject;

  private final IRemoteServices fRemoteServices;
  
  private final IRemoteConnection fRemoteConnection;
  
  private String fWorkDir;
  
  private String fCompiledFile;
  

  private static final String INCLUDE_OPT = "-I"; //$NON-NLS-1$
  
  private static final String LIB_OPT = "-L"; //$NON-NLS-1$
  
  private static final String TEST_FILENAME = "Hello.x10"; //$NON-NLS-1$
  
  private static final String TEST_PROG_NAME = "a.out"; //$NON-NLS-1$
  
  private static final String TEST_LIB = "/libHello.a"; //$NON-NLS-1$
  
  private static final String TEST_LIB_LINK = "-lHello"; //$NON-NLS-1$
  
  private static final String SRC_X10_DIR = "src-x10"; //$NON-NLS-1$
  
  private static final String TMPDIR_JAVA_ENV_VAR = "java.io.tmpdir"; //$NON-NLS-1$

  private static final String PATH_SEP_STRFORMAT = "%s/%s"; //$NON-NLS-1$

}
