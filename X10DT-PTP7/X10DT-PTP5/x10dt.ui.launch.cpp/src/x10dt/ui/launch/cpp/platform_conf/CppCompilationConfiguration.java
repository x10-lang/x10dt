/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.platform_conf;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import x10dt.core.utils.X10BundleUtils;
import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.core.platform_conf.EArchitecture;
import x10dt.ui.launch.core.platform_conf.EBitsArchitecture;
import x10dt.ui.launch.core.platform_conf.ETargetOS;
import x10dt.ui.launch.core.utils.CodingUtils;



final class CppCompilationConfiguration extends StatusConfProvider implements ICppCompilationConf {
  
  // --- Interface methods implementation
  
  public EArchitecture getArchitecture() {
    return this.fArchitecture;
  }

  public String getArchiver() {
    return (this.fArchiver == null) ? Constants.EMPTY_STR : this.fArchiver;
  }

  public String getArchivingOpts() {
    return (this.fArchivingOpts == null) ? Constants.EMPTY_STR : this.fArchivingOpts;
  }
  
  public EBitsArchitecture getBitsArchitecture() {
    return this.fBitsArchitecture;
  }

  public String getCompiler() {
    return (this.fCompiler == null) ? Constants.EMPTY_STR : this.fCompiler;
  }

  public String getCompilingOpts() {
    return (this.fCompilingOpts == null) ? Constants.EMPTY_STR : this.fCompilingOpts;
  }

  public String getLinker() {
    return (this.fLinker == null) ? Constants.EMPTY_STR : this.fLinker;
  }

  public String getLinkingLibs() {
    return (this.fLinkingLibs == null) ? Constants.EMPTY_STR : this.fLinkingLibs;
  }

  public String getLinkingOpts() {
    return (this.fLinkingOpts == null) ? Constants.EMPTY_STR : this.fLinkingOpts;
  }
  
  public String getRemoteOutputFolder() {
    return (this.fRemoteOutputFolder == null) ? Constants.EMPTY_STR : this.fRemoteOutputFolder;
  }

  public ETargetOS getTargetOS() {
    return this.fTargetOS;
  }

  public String getX10DistribLocation(final boolean isLocal) {
    if (this.fX10DistLoc == null) {
      if (isLocal) {
        try {
          return new File(X10BundleUtils.getX10DistHostResource("include").getFile()).getParent(); //$NON-NLS-1$
        } catch (IOException except) {
          // Simply forgets
          return Constants.EMPTY_STR;
        }
      } else {
        return Constants.EMPTY_STR;
      }
    } else {
      return this.fX10DistLoc;
    }
  }

  public String[] getX10HeadersLocations(final boolean isLocal) {
    final String x10DistLoc = getX10DistribLocation(isLocal);
    return new String[] { String.format(INCLUDE_FORMAT, x10DistLoc), String.format(STDLIB_INCLUDE_FORMAT, x10DistLoc) };
  }

  public String[] getX10LibsLocations(final boolean isLocal) {
    final String x10DistLoc = getX10DistribLocation(isLocal);
    return new String[] { String.format(LIB_FORMAT, x10DistLoc), String.format(STDLIB_FORMAT, x10DistLoc),
                          String.format(STDLIB_LIB_FORMAT, x10DistLoc) };
  }
  
  // --- Overridden methods
  
  public boolean equals(final Object rhs) {
    final CppCompilationConfiguration rhsObj = (CppCompilationConfiguration) rhs;
    if (! super.equals(rhs)) {
      return false;
    }
    return ((this.fTargetOS == rhsObj.fTargetOS) && (this.fBitsArchitecture == rhsObj.fBitsArchitecture) &&
            (this.fArchitecture == rhsObj.fArchitecture) &&
            Arrays.equals(new Object[] { this.fCompiler, this.fCompilingOpts, this.fArchiver, this.fArchivingOpts,
                                         this.fLinker, this.fLinkingOpts, this.fLinkingLibs, this.fX10DistLoc, 
                                         this.fRemoteOutputFolder },
                          new Object[] { rhsObj.fCompiler, rhsObj.fCompilingOpts, rhsObj.fArchiver, rhsObj.fArchivingOpts,
                                         rhsObj.fLinker, rhsObj.fLinkingOpts, rhsObj.fLinkingLibs, rhsObj.fX10DistLoc, 
                                         rhsObj.fRemoteOutputFolder }));
  }
  
  public int hashCode() {
    return super.hashCode() + CodingUtils.generateHashCode(134456, this.fTargetOS, this.fBitsArchitecture, this.fCompiler, 
                                                           this.fCompilingOpts, this.fArchiver, this.fArchivingOpts, 
                                                           this.fLinker,this.fLinkingOpts, this.fLinkingLibs,
                                                           this.fX10DistLoc, this.fRemoteOutputFolder, this.fArchitecture);
  }
  
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append(super.toString()).append("\nTarget OS: ").append(this.fTargetOS.name()) //$NON-NLS-1$
      .append("\nBits Arch: ").append(this.fBitsArchitecture.name()) //$NON-NLS-1$
      .append("\nArchitecture: ").append(this.fArchitecture.name()) //$NON-NLS-1$
      .append("\nCompiler: ").append(this.fCompiler) //$NON-NLS-1$
      .append("\nCompiling Options: ").append(this.fCompilingOpts) //$NON-NLS-1$
      .append("\nArchiver: ").append(this.fArchiver) //$NON-NLS-1$
      .append("\nArchiving Options: ").append(this.fArchivingOpts) //$NON-NLS-1$
      .append("\nLinker: ").append(this.fLinker) //$NON-NLS-1$
      .append("\nLinking Options: ").append(this.fLinkingOpts) //$NON-NLS-1$
      .append("\nLinking Libs: ").append(this.fLinkingLibs) //$NON-NLS-1$
      .append("\nX10 Dist Location: ").append(this.fX10DistLoc) //$NON-NLS-1$
      .append("\nRemote Output Folder: ").append(this.fRemoteOutputFolder); //$NON-NLS-1$
    return sb.toString();
  }
  
  // --- Internal code
  
  CppCompilationConfiguration() {}
  
  CppCompilationConfiguration(final CppCompilationConfiguration original) {
    this.fValidationStatus = original.fValidationStatus;
    this.fValidationErrorMsg = original.fValidationErrorMsg;
    this.fTargetOS = original.fTargetOS;
    this.fBitsArchitecture = original.fBitsArchitecture;
    this.fArchitecture = original.fArchitecture;
    this.fCompiler = original.fCompiler;
    this.fCompilingOpts = original.fCompilingOpts;
    this.fArchiver = original.fArchiver;
    this.fArchivingOpts = original.fArchivingOpts;
    this.fLinker = original.fLinker;
    this.fLinkingOpts = original.fLinkingOpts;
    this.fLinkingLibs = original.fLinkingLibs;
    this.fX10DistLoc = original.fX10DistLoc;
    this.fRemoteOutputFolder = original.fRemoteOutputFolder;
  }
  
  void applyChanges(final ICppCompilationConf source, final boolean isLocal) {
    this.fValidationStatus = source.getValidationStatus();
    this.fValidationErrorMsg = source.getValidationErrorMessage();
    this.fTargetOS = source.getTargetOS();
    this.fBitsArchitecture = source.getBitsArchitecture();
    this.fArchitecture = source.getArchitecture();
    this.fCompiler = source.getCompiler();
    this.fCompilingOpts = source.getCompilingOpts();
    this.fArchiver = source.getArchiver();
    this.fArchivingOpts = source.getArchivingOpts();
    this.fLinker = source.getLinker();
    this.fLinkingOpts = source.getLinkingOpts();
    this.fLinkingLibs = source.getLinkingLibs();
    this.fX10DistLoc = source.getX10DistribLocation(isLocal);
    this.fRemoteOutputFolder = source.getRemoteOutputFolder();
  }
  
  // --- Fields
  
  ETargetOS fTargetOS;
  
  EArchitecture fArchitecture;

  EBitsArchitecture fBitsArchitecture;
  
  String fCompiler;
  
  String fCompilingOpts;
  
  String fArchiver;
  
  String fArchivingOpts;
  
  String fLinker;
  
  String fLinkingOpts;
  
  String fLinkingLibs;
  
  String fX10DistLoc;

  String fRemoteOutputFolder;
  
  
  private static final String INCLUDE_FORMAT = "%s/include"; //$NON-NLS-1$
  
  private static final String STDLIB_INCLUDE_FORMAT = "%s/stdlib/include"; //$NON-NLS-1$
  
  private static final String LIB_FORMAT = "%s/lib"; //$NON-NLS-1$
  
  private static final String STDLIB_FORMAT = "%s/stdlib"; //$NON-NLS-1$
  
  private static final String STDLIB_LIB_FORMAT = "%s/stdlib/lib"; //$NON-NLS-1$

}
