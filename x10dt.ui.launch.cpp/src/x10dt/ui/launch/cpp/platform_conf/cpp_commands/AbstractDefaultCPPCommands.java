/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.platform_conf.cpp_commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IProject;

import x10.X10CompilerOptions;
import x10cpp.postcompiler.CXXCommandBuilder;
import x10cpp.postcompiler.PostCompileProperties;
import x10cpp.postcompiler.PrecompiledLibrary;
import x10dt.core.utils.CompilerOptionsFactory;
import x10dt.core.utils.X10BundleUtils;
import x10dt.ui.launch.core.platform_conf.EArchitecture;
import x10dt.ui.launch.core.platform_conf.ETransport;
import x10dt.ui.utils.X10Utils;

abstract class AbstractDefaultCPPCommands implements IDefaultCPPCommands {

  protected AbstractDefaultCPPCommands(IProject project, final boolean is64Arch, final EArchitecture architecture, final ETransport transport, boolean isLocal) {
    this.fIs64Arch = is64Arch;
    this.fArchitecture = architecture;
    this.fTransport = transport;
    this.fCompilerOptions = CompilerOptionsFactory.createOptions(project);
    
    
    PostCompileProperties prop = getTransportProperties(transport);
    X10CompilerOptions options = CompilerOptionsFactory.createOptions(project);
    getPrecompiledLibrary(options, isLocal);
    CXXCommandBuilder builder = CXXCommandBuilder.getCXXCommandBuilder(options, prop, new X10Utils.ShallowErrorQueue());
    this.fPostCompiler = builder.getPostCompiler();
    this.fPostFileArgs = concatenate(builder.getPostFileArgs());
    this.fPreFileArgs = concatenate(builder.getPreFileArgs());
  }
  
  private String concatenate(List<String> items){
	  String result = "";
	  for(String item: items){
		  result += item + " ";
	  }
	  return result;
  }
  
  private String normalize(String path){
	if (path.endsWith("/")){
		return path.substring(0, path.length() - 1);
	}
	return path;
  }
  
  private String removeLastSegment(String path){
	int i = normalize(path).lastIndexOf("/");
	return path.substring(0, i);
  }
  
  private PostCompileProperties getTransportProperties(ETransport transport){
	try {
		String etcPath  = normalize(X10BundleUtils.getX10DistHostResource("etc").getPath());
		String propName = null;
		if (transport == ETransport.LAPI){
			propName = "x10rt_lapi.properties";
		} else if (transport == ETransport.MPI) {
			propName = "x10rt_mpi.properties";
		} else if (transport == ETransport.SOCKETS) {
			propName = "x10rt_sockets.properties";
		} else if (transport == ETransport.STANDALONE) {
			propName = "x10rt_standalone.properties";
		}
		Properties prop = new Properties();
		File f = new File(etcPath + File.separator + propName);
        prop.load(new FileInputStream(f));
		return new PostCompileProperties(prop);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
  }
  
  private void getPrecompiledLibrary(X10CompilerOptions options, boolean local){
	  try {
		String stdlibPath = normalize(X10BundleUtils.getX10DistHostResource("stdlib").getPath());  
		String distHostPath = removeLastSegment(stdlibPath);
		options.setDistPath(distHostPath);
		
		Properties prop = new Properties();
		File f = new File(stdlibPath + File.separator +  "libx10.properties");
		prop.load(new FileInputStream(f));
		PrecompiledLibrary lib = new PrecompiledLibrary(stdlibPath, prop);
		if (local){
			options.addLocalPrecompiledLibrary(lib);
		} else {
			options.addRemotePrecompiledLibrary(lib);
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  // --- Code for descendants
  
  protected final String addNoChecksOptions(final String command) {
    if (this.fCompilerOptions.x10_config.NO_CHECKS) {
      return command + " -DNO_CHECKS"; //$NON-NLS-1$
    } else {
      return command;
    }
  }
  
  protected final String addOptimizeOptions(final String command) {
    if (this.fCompilerOptions.x10_config.OPTIMIZE) {
      return command + " -O2 -DNDEBUG -DNO_PLACE_CHECKS -finline-functions"; //$NON-NLS-1$
    } else {
      return command;
    }
  }
  
  protected final EArchitecture getArchitecture() {
    return this.fArchitecture;
  }
  
  protected final ETransport getTransport() {
    return this.fTransport;
  }
  
  protected String getTransportCompilerOption() {
    switch (this.fTransport) {
      case LAPI:
        return "-DTRANSPORT=lapi"; //$NON-NLS-1$
      case MPI:
      case SOCKETS:
      case STANDALONE:
        return ""; //$NON-NLS-1$
      default:
        throw new AssertionError();
    }
  }
  
  protected String getTransportLibrary() {
    switch (this.fTransport) {
      case LAPI:
        return "-lupcrts_lapi"; //$NON-NLS-1$
      case MPI:
    	return "-lx10rt_mpi"; //$NON-NLS-1$
      case SOCKETS:
        return "-lx10rt_sockets"; //$NON-NLS-1$
      case STANDALONE:
        return "-lx10rt_standalone"; //$NON-NLS-1$
      default:
        throw new AssertionError();
    }
  }
  
  protected final boolean is64Arch() {
    return this.fIs64Arch;
  }
  
  protected final boolean supportsStreamingSIMDExtensions() {
    return this.fArchitecture == EArchitecture.x86;
  }
  
  // --- Fields
  
  private final X10CompilerOptions fCompilerOptions;
  
  private final boolean fIs64Arch;
  
  private final EArchitecture fArchitecture;
  
  private final ETransport fTransport;
  
  
  protected static final String M64BIT_OPTION = " -m64"; //$NON-NLS-1$
  
  protected static final String STREAMING_SIMD_EXTENSIONS = " -msse2 -mfpmath=sse"; //$NON-NLS-1$
  
  protected final String fPostCompiler;
  
  protected final String fPreFileArgs;
  
  protected final String fPostFileArgs;

}
