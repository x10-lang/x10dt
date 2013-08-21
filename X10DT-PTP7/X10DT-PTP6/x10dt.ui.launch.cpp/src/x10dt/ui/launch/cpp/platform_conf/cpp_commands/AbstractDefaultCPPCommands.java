/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.platform_conf.cpp_commands;

import static x10dt.ui.launch.cpp.platform_conf.cpp_commands.DefaultCPPCommandsFactory.LIBX10_PROPERTIES_FILE;
import static x10dt.ui.launch.cpp.platform_conf.cpp_commands.DefaultCPPCommandsFactory.SHARED_LIB_PROPERTIES_FILE;
import static x10dt.ui.launch.cpp.platform_conf.cpp_commands.DefaultCPPCommandsFactory.X10RT_PROPERTIES_FILE_FORMAT;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;

import x10.X10CompilerOptions;
import x10cpp.postcompiler.CXXCommandBuilder;
import x10cpp.postcompiler.PostCompileProperties;
import x10cpp.postcompiler.PrecompiledLibrary;
import x10cpp.postcompiler.SharedLibProperties;
import x10dt.core.utils.CompilerOptionsFactory;
import x10dt.core.utils.ShallowErrorQueue;
import x10dt.core.utils.X10BundleUtils;
import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.platform_conf.ETargetOS;
import x10dt.ui.launch.core.platform_conf.ETransport;
import x10dt.ui.launch.cpp.CppLaunchCore;
import x10dt.ui.launch.cpp.LaunchMessages;
import x10dt.ui.launch.cpp.builder.target_op.ITargetOpHelper;
import x10dt.ui.launch.cpp.builder.target_op.TargetOpHelperFactory;
import x10dt.ui.launch.cpp.platform_conf.ICommunicationInterfaceConf;
import x10dt.ui.launch.cpp.platform_conf.ICppCompilationConf;
import x10dt.ui.launch.cpp.platform_conf.IX10PlatformConf;
import x10dt.ui.launch.cpp.utils.PlatformConfUtils;

abstract class AbstractDefaultCPPCommands implements IDefaultCPPCommands {

  protected AbstractDefaultCPPCommands(final IX10PlatformConf platformConf) throws CoreException {
    final ICppCompilationConf cppCompConf = platformConf.getCppCompilationConf();
    final ICommunicationInterfaceConf ciConf = platformConf.getCommunicationInterfaceConf();
    this.fIs64Arch = false;
    
    final boolean isLocal = platformConf.getConnectionConf().isLocal();
    final ETransport transport = PlatformConfUtils.getTransport(ciConf.getServiceTypeId(), cppCompConf.getTargetOS());
    final ITargetOpHelper targetOpHelper = TargetOpHelperFactory.create(isLocal, cppCompConf.getTargetOS() == ETargetOS.WINDOWS,
                                                                        platformConf.getConnectionConf().getConnectionName());
    final String x10DistribLoc;
    try {
      if (isLocal) {
        x10DistribLoc = new File(X10BundleUtils.getX10DistHostResource("etc").getFile()).getParentFile().getAbsolutePath(); //$NON-NLS-1$
      } else {
        x10DistribLoc = cppCompConf.getX10DistribLocation(isLocal);
      }
    } catch (IOException except) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, LaunchMessages.ADCC_NoEtcFolder, except));
    }
    final IFileStore x10DistFileStore = targetOpHelper.getStore(x10DistribLoc);

    final PostCompileProperties prop = getTransportProperties(transport, x10DistFileStore);
    final X10CompilerOptions options = CompilerOptionsFactory.createOptions(platformConf.getConfFile().getProject());
    options.output_directory = new File(x10DistribLoc);
    definePrecompiledLibrary(options, isLocal, x10DistribLoc, x10DistFileStore, targetOpHelper);
    final SharedLibProperties sharedLibProp = getSharedLibProperties(x10DistFileStore);
    final CXXCommandBuilder builder = CXXCommandBuilder.getCXXCommandBuilder(options, prop, sharedLibProp, 
                                                                             new ShallowErrorQueue());

    this.fPostCompiler = builder.getPostCompiler();
    this.fPostFileArgs = concatenate(builder.getPostFileArgs(), targetOpHelper);
    this.fPreFileArgs = concatenate(builder.getPreFileArgs(), targetOpHelper);
  }
  
  // --- Interface methods implementation
  
  public final String getCompiler() {
    return this.fPostCompiler;
  }
  
  public final String getCompilerOptions() {
    return this.fPreFileArgs;
  }

  public final String getLinker() {
    return this.fPostCompiler;
  }

  public final String getLinkingLibraries() {
    return this.fPostFileArgs;
  }

  public final String getLinkingOptions() {
    return this.fPreFileArgs;
  }

  // --- Code for descendants

  protected final boolean is64Arch() {
    return this.fIs64Arch;
  }
  
  // --- Private code
  
  private String concatenate(final List<String> items, final ITargetOpHelper targetOpHelper) {
    final StringBuilder sb = new StringBuilder();
    for (final String item : items) {
      if (sb.length() > 0) {
        sb.append(' ');
      }
      final String element;
      if (item.startsWith("-I") || item.startsWith("-L")) { //$NON-NLS-1$ //$NON-NLS-2$
        element = item.substring(0, 2) + targetOpHelper.getTargetSystemPath(item.substring(2));
      } else {
        element = item.replace('\\', '/');
      }
      if (element.contains(Constants.EMPTY_STR)) {
        sb.append(element.replaceAll("\\s", "\\\\ ")); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
        sb.append(element);
      }
    }
    return sb.toString();
  }

  private void definePrecompiledLibrary(final X10CompilerOptions options, final boolean isLocal, final String x10DistPath,
                                        final IFileStore x10DistFileStore, 
                                        final ITargetOpHelper targetOpHelper) throws CoreException {
    final IFileStore libX10FileStore = x10DistFileStore.getChild(LIBX10_PROPERTIES_FILE);
    options.setDistPath(targetOpHelper.getTargetSystemPath(x10DistPath));

    final Properties properties = new Properties();
    try {
      properties.load(libX10FileStore.openInputStream(EFS.NONE, null /* monitor */));
    } catch (IOException except) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, 
                                         NLS.bind(LaunchMessages.ADCC_PropFileReadingError, LIBX10_PROPERTIES_FILE)));
    } catch (CoreException except) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, 
                                         NLS.bind(LaunchMessages.XPC_PropertiesFileLoadingError, LIBX10_PROPERTIES_FILE)));
    }
    final PrecompiledLibrary lib = new PrecompiledLibrary(String.format("%s/%s", x10DistPath, "stdlib"), properties); //$NON-NLS-1$ //$NON-NLS-2$
    if (isLocal) {
      options.addLocalPrecompiledLibrary(lib);
    } else {
      options.addRemotePrecompiledLibrary(lib);
    }
  }
  
  private SharedLibProperties getSharedLibProperties(final IFileStore x10DistFileStore) throws CoreException {
    final IFileStore propertiesFileStore = x10DistFileStore.getChild(SHARED_LIB_PROPERTIES_FILE);
    final Properties properties = new Properties();
    try {
      properties.load(propertiesFileStore.openInputStream(EFS.NONE, null /* monitor */));
    } catch (IOException except) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, 
                                         NLS.bind(LaunchMessages.ADCC_PropFileReadingError, SHARED_LIB_PROPERTIES_FILE)));
    } catch (CoreException except) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, 
                                         NLS.bind(LaunchMessages.XPC_PropertiesFileLoadingError, SHARED_LIB_PROPERTIES_FILE)));
    }
    return new SharedLibProperties(properties);
  }
  
  private PostCompileProperties getTransportProperties(final ETransport transport, 
                                                       final IFileStore x10DistFileStore) throws CoreException {
    final String propertiesFileName = String.format(X10RT_PROPERTIES_FILE_FORMAT, transport.name().toLowerCase());
    final IFileStore propertiesFileStore = x10DistFileStore.getChild(propertiesFileName);
    final Properties properties = new Properties();
    try {
      properties.load(propertiesFileStore.openInputStream(EFS.NONE, null /* monitor */));
    } catch (IOException except) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, 
                                         NLS.bind(LaunchMessages.ADCC_PropFileReadingError, propertiesFileName)));
    } catch (CoreException except) {
      throw new CoreException(new Status(IStatus.ERROR, CppLaunchCore.PLUGIN_ID, 
                                         NLS.bind(LaunchMessages.XPC_PropertiesFileLoadingError, propertiesFileName)));
    }
    return new PostCompileProperties(properties);
  }

  // --- Fields
  
  private final boolean fIs64Arch;

  protected final String fPostCompiler;

  protected final String fPreFileArgs;

  protected final String fPostFileArgs;
  
}
