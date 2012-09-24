/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.platform_conf;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ptp.remotetools.environment.core.ITargetElement;

import x10dt.ui.launch.core.platform_conf.EArchitecture;
import x10dt.ui.launch.core.platform_conf.EBitsArchitecture;
import x10dt.ui.launch.core.platform_conf.ETargetOS;
import x10dt.ui.launch.core.platform_conf.EValidationStatus;
import x10dt.ui.launch.core.utils.PTPConstants;
import x10dt.ui.launch.cpp.CppLaunchCore;
import x10dt.ui.launch.cpp.editors.EOpenMPIVersion;
import x10dt.ui.launch.cpp.platform_conf.cpp_commands.DefaultCPPCommandsFactory;
import x10dt.ui.launch.cpp.platform_conf.cpp_commands.IDefaultCPPCommands;


final class X10PlatformConfWorkCopy extends X10PlatformConf implements IX10PlatformConfWorkCopy {
  
  X10PlatformConfWorkCopy(final X10PlatformConf source) {
    super(source);
    this.fSource = source;
    this.fId = source.fId;
    this.fName = source.fName;
    this.fDescription = source.fDescription;
  }
  
  public void applyChanges() {
    this.fSource.fId = super.fId;
    this.fSource.fName = super.fName;
    this.fSource.fDescription = super.fDescription;
    this.fSource.fConnectionConf.applyChanges(this.fConnectionConf);
    this.fSource.fCommInterfaceFact.applyChanges(super.fCommInterfaceFact);
    this.fSource.fCppCompilationConf.applyChanges(this.fCppCompilationConf, this.fSource.fConnectionConf.fIsLocal);
    this.fSource.fDebuggingInfoConf.applyChanges(this.fDebuggingInfoConf);
    this.fIsDirty = false;
  }
  
  public boolean isDirty() {
    return this.fIsDirty;
  }
  
  public boolean isStale() {
    return this.fIsStale;
  }
  
  public void refresh() {
    this.fIsStale = false;
  }
  
  // --- IX10PlatformConf's setter methods
  
  public void setDescription(final String description) {
    super.fDescription = description;
    updateDirtyFlag();
  }
  
  public void setName(final String name) {
    super.fName = name;
    updateDirtyFlag();
  }
  
  // --- IStatusConfProvider's setter methods
  
  public void setCompilationCommandsErrorMessage(final String errorMessage) {
    this.fCppCompilationConf.fValidationErrorMsg = errorMessage;
  }
  
  public void setCompilationCommandsStatus(final EValidationStatus validationStatus) {
    this.fCppCompilationConf.fValidationStatus = validationStatus;
  }
  
  // --- ICppCompilationConf's setter methods
  
  public void setArchitecture(final EArchitecture architecture) {
    super.fCppCompilationConf.fArchitecture = architecture;
    updateDirtyFlag();
  }
  
  public void setArchiver(final String archiver) {
    super.fCppCompilationConf.fArchiver = archiver;
    updateDirtyFlag();
  }
  
  public void setArchivingOpts(final String archivingOpts) {
    super.fCppCompilationConf.fArchivingOpts = archivingOpts;
    updateDirtyFlag();
  }
  
  public void setBitsArchitecture(final EBitsArchitecture bitsArch) {
    super.fCppCompilationConf.fBitsArchitecture = bitsArch;
    updateDirtyFlag();
  }
  
  public void setCompiler(final String compiler) {
    super.fCppCompilationConf.fCompiler = compiler;
    updateDirtyFlag();
  }
  
  public void setCompilingOpts(final String compilerOpts) {
    super.fCppCompilationConf.fCompilingOpts = compilerOpts;
    updateDirtyFlag();
  }
  
  public void setCppConfValidationErrorMessage(final String validationErrorMessage) {
    super.fCppCompilationConf.fValidationErrorMsg = validationErrorMessage;
    updateDirtyFlag();
  }
  
  public void setCppConfValidationStatus(final EValidationStatus validationStatus) {
    super.fCppCompilationConf.fValidationStatus = validationStatus;
    updateDirtyFlag();
  }
  
  public void setLinker(final String linker) {
    super.fCppCompilationConf.fLinker = linker;
    updateDirtyFlag();
  }
  
  public void setLinkingLibs(final String linkingLibs) {
    super.fCppCompilationConf.fLinkingLibs = linkingLibs;
    updateDirtyFlag();
  }
  
  public void setLinkingOpts(final String linkingOpts) {
    super.fCppCompilationConf.fLinkingOpts = linkingOpts;
    updateDirtyFlag();
  }
  
  public void setRemoteOutputFolder(final String remoteOutputFolder) {
    super.fCppCompilationConf.fRemoteOutputFolder = remoteOutputFolder;
    updateDirtyFlag();
  }
  
  public void setTargetOS(final ETargetOS targetOS) {
    super.fCppCompilationConf.fTargetOS = targetOS;
    updateDirtyFlag();
  }
  
  public void setX10DistribLocation(final String x10DistribLoc) {
    super.fCppCompilationConf.fX10DistLoc = x10DistribLoc;
    updateDirtyFlag();
  }
  
  public void updateCompilationCommands() {
    try {
      final IDefaultCPPCommands defaultCPPCommands = DefaultCPPCommandsFactory.create(this);
      this.fCppCompilationConf.fCompiler = defaultCPPCommands.getCompiler();
      this.fCppCompilationConf.fCompilingOpts = defaultCPPCommands.getCompilerOptions();
      this.fCppCompilationConf.fArchiver = defaultCPPCommands.getArchiver();
      this.fCppCompilationConf.fArchivingOpts = defaultCPPCommands.getArchivingOpts();
      this.fCppCompilationConf.fLinker = defaultCPPCommands.getLinker();
      this.fCppCompilationConf.fLinkingOpts = defaultCPPCommands.getLinkingOptions();
      this.fCppCompilationConf.fLinkingLibs = defaultCPPCommands.getLinkingLibraries();
    } catch (CoreException except) {
      // We can't update the commands so we keep the previous state as is.
      CppLaunchCore.log(except.getStatus());
    }
    updateDirtyFlag();
  }
    
  // --- IConnectionConf's setter methods
  
  public void setConnectionName(final String connectionName) {
    super.fConnectionConf.fConnectionName = connectionName;
    updateDirtyFlag();
  }
  
  public void setConnectionTimeout(final int timeout) {
    super.fConnectionConf.fTimeout = timeout;
    updateDirtyFlag();
  }
  
  public void setHostName(final String hostName) {
    super.fConnectionConf.fHostName = hostName;
    updateDirtyFlag();
  }
  
  public void setIsLocalFlag(final boolean isLocal) {
    super.fConnectionConf.fIsLocal = isLocal;
    updateDirtyFlag();
  }
  
  public void setIsPasswordBasedAuthenticationFlag(final boolean isPasswordBasedAuth) {
    super.fConnectionConf.fIsPasswordBasedAuth = isPasswordBasedAuth;
    updateDirtyFlag();
  }
  
  public void setLocalAddress(final String localAddress) {
    super.fConnectionConf.fLocalAddress = localAddress;
    updateDirtyFlag();
  }
  
  public void setPassphrase(final String passphrase) {
    super.fConnectionConf.fPassphrase = passphrase;
    updateDirtyFlag();
  }
  
  public void setPassword(final String password) {
    super.fConnectionConf.fPassword = password;
    updateDirtyFlag();
  }
  
  public void setPort(final int port) {
    super.fConnectionConf.fPort = port;
    updateDirtyFlag();
  }
  
  public void setPrivateKeyFile(final String privateKeyFile) {
    super.fConnectionConf.fPrivateKeyFile = privateKeyFile;
    updateDirtyFlag();
  }
  
  public void setShouldUsePortForwarding(final boolean usePortForwarding) {
    super.fConnectionConf.fUsePortForwarding = usePortForwarding;
    updateDirtyFlag();
  }
  
  public void setTargetElement(final ITargetElement targetElement) {
    super.fConnectionConf.initValuesFromTargetElement(targetElement);
    updateDirtyFlag();
  }
  
  public void setUserName(final String userName) {
    super.fConnectionConf.fUserName = userName;
    updateDirtyFlag();
  }
  
  // --- ICommunicationInterface's setter methods
  
  public void setNumOfPlaces(final int numOfPlaces) {
    super.fCommInterfaceFact.getCurrentCommunicationInterface().fNumberOfPlaces = numOfPlaces;
    updateDirtyFlag();
  }
  
  public void setServiceModeId(final String ciType, final String serviceModeId) {
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final AbstractCommunicationInterfaceConfiguration configuration = super.fCommInterfaceFact.getOrCreate(ciType);
    if (configuration != null) {
      configuration.fServiceModeId = serviceModeId;
      updateDirtyFlag();
    }
  }
  
  public void setServiceTypeId(final String ciType) {
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final AbstractCommunicationInterfaceConfiguration configuration = super.fCommInterfaceFact.getOrCreate(ciType);
    if (configuration != null) {
      configuration.fServiceTypeId = ciType;
      updateDirtyFlag();
    }
  }
  
  public void setDebugCommand(final String ciType, final String debugCommand) {
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final MessagePassingInterfaceConf conf = (MessagePassingInterfaceConf) super.fCommInterfaceFact.getOrCreate(ciType);
    conf.fDebugCmd = debugCommand;
    updateDirtyFlag();
  }
  
  public void setDefaultInstallLocationFlag(final String ciType, final boolean shouldTakeInstallLocation) {
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final MessagePassingInterfaceConf conf = (MessagePassingInterfaceConf) super.fCommInterfaceFact.getOrCreate(ciType);
    conf.fDefaultIntallLocation = shouldTakeInstallLocation;
    updateDirtyFlag();
  }
  
  public void setDefaultToolCommands(final String ciType, final boolean shouldTakeDefaultToolCommands) {
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final MessagePassingInterfaceConf conf = (MessagePassingInterfaceConf) super.fCommInterfaceFact.getOrCreate(ciType);
    conf.fDefaultToolCmds = shouldTakeDefaultToolCommands;
    updateDirtyFlag();
  }
  
  public void setDiscoverCommand(final String ciType, final String discoverCommand) {
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final MessagePassingInterfaceConf conf = (MessagePassingInterfaceConf) super.fCommInterfaceFact.getOrCreate(ciType);
    conf.fDiscoverCmd = discoverCommand;
    updateDirtyFlag();
  }
  
  public void setInstallLocation(final String ciType, final String installLocation) {
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final MessagePassingInterfaceConf conf = (MessagePassingInterfaceConf) super.fCommInterfaceFact.getOrCreate(ciType);
    conf.fInstallLocation = installLocation;
    updateDirtyFlag();
  }
  
  public void setLaunchCommand(final String ciType, final String launchCommand) {
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final MessagePassingInterfaceConf conf = (MessagePassingInterfaceConf) super.fCommInterfaceFact.getOrCreate(ciType);
    conf.fLaunchCmd = launchCommand;
    updateDirtyFlag();
  }
  
  public void setMonitorCommand(final String ciType, final String monitorCommand) {
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final MessagePassingInterfaceConf conf = (MessagePassingInterfaceConf) super.fCommInterfaceFact.getOrCreate(ciType);
    conf.fMonitorCmd = monitorCommand;
    updateDirtyFlag();
  }
  
  public void setMonitorPeriod(final String ciType, final int monitorPeriod) {
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final MessagePassingInterfaceConf conf = (MessagePassingInterfaceConf) super.fCommInterfaceFact.getOrCreate(ciType);
    conf.fMonitoringPeriod = monitorPeriod;
    updateDirtyFlag();
  }
  
  public void setOpenMPIVersion(final EOpenMPIVersion openMPIVersion) {
    final String ciType = PTPConstants.OPEN_MPI_SERVICE_PROVIDER_ID;
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final OpenMPIInterfaceConf conf = (OpenMPIInterfaceConf) super.fCommInterfaceFact.getOrCreate(ciType);
    conf.fOpenMPIVersion = openMPIVersion;
    updateDirtyFlag();
  }
  
  public void setAlternateLibraryPath(final String ciType, final String libPath) {
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final IBMCommunicationInterfaceConf conf = (IBMCommunicationInterfaceConf) super.fCommInterfaceFact.getOrCreate(ciType);
    conf.fAlternateLibPath = libPath;
    updateDirtyFlag();
  }
  
  public void setClusterMode(final String ciType, final EClusterMode mode) {
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final IBMCommunicationInterfaceConf conf = (IBMCommunicationInterfaceConf) super.fCommInterfaceFact.getOrCreate(ciType);
    conf.fClusterMode = mode;
    updateDirtyFlag();
  }
  
  public void setJobPolling(final String ciType, final int jobPolling) {
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final IBMCommunicationInterfaceConf conf = (IBMCommunicationInterfaceConf) super.fCommInterfaceFact.getOrCreate(ciType);
    conf.fJobPolling = jobPolling;
    updateDirtyFlag();
  }
  
  public void setNodeMinPolling(final String ciType, final int nodeMinPolling) {
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final IBMCommunicationInterfaceConf conf = (IBMCommunicationInterfaceConf) super.fCommInterfaceFact.getOrCreate(ciType);
    conf.fNodePollingMin = nodeMinPolling;
    updateDirtyFlag();
  }
  
  public void setNodeMaxPolling(final String ciType, final int nodeMaxPolling) {
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final IBMCommunicationInterfaceConf conf = (IBMCommunicationInterfaceConf) super.fCommInterfaceFact.getOrCreate(ciType);
    conf.fNodePollingMax = nodeMaxPolling;
    updateDirtyFlag();
  }
  
  public void setProxyServerPath(final String ciType, final String proxyServerPath) {
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final IBMCommunicationInterfaceConf conf = (IBMCommunicationInterfaceConf) super.fCommInterfaceFact.getOrCreate(ciType);
    conf.fProxyServerPath = proxyServerPath;
    updateDirtyFlag();
  }
  
  public void setLaunchProxyManuallyFlag(final String ciType, final boolean shouldLaunchProxyManually) {
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final IBMCommunicationInterfaceConf conf = (IBMCommunicationInterfaceConf) super.fCommInterfaceFact.getOrCreate(ciType);
    conf.fLaunchProxyManually = shouldLaunchProxyManually;
    updateDirtyFlag();
  }
  
  public void setSuspendProxyAtStartupFlag(final String ciType, final boolean shouldSuspendProxy) {
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final IBMCommunicationInterfaceConf conf = (IBMCommunicationInterfaceConf) super.fCommInterfaceFact.getOrCreate(ciType);
    conf.fSuspendProxyAtStartup = shouldSuspendProxy;
    updateDirtyFlag();
  }
  
  public void setDebuggingLevel(final ECIDebugLevel debugLevel) {
    final String ciType = PTPConstants.PARALLEL_ENVIRONMENT_SERVICE_PROVIDER_ID;
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final ParallelEnvironmentConf conf = (ParallelEnvironmentConf) super.fCommInterfaceFact.getOrCreate(ciType);
    conf.fCIDebugLevel = debugLevel;
    updateDirtyFlag();
  }
  
  public void setRunMiniProxyFlag(final boolean shouldRunMiniProxy) {
    final String ciType = PTPConstants.PARALLEL_ENVIRONMENT_SERVICE_PROVIDER_ID;
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final ParallelEnvironmentConf conf = (ParallelEnvironmentConf) super.fCommInterfaceFact.getOrCreate(ciType);
    conf.fRunMiniProxy = shouldRunMiniProxy;
    updateDirtyFlag();
  }
  
  public void setUseLoadLeveler(final boolean shouldUseLoadLeveler) {
    final String ciType = PTPConstants.PARALLEL_ENVIRONMENT_SERVICE_PROVIDER_ID;
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final ParallelEnvironmentConf conf = (ParallelEnvironmentConf) super.fCommInterfaceFact.getOrCreate(ciType);
    conf.fUseLoadLeveler = shouldUseLoadLeveler;
    updateDirtyFlag();
  }
  
  public void setProxyMessageOptions(final int options) {
    final String ciType = PTPConstants.LOAD_LEVELER_SERVICE_PROVIDER_ID;
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final LoadLevelerConf conf = (LoadLevelerConf) super.fCommInterfaceFact.getOrCreate(ciType);
    conf.fProxyMsgOpts = options;
    updateDirtyFlag();
  }
  
  public void setTemplateFilePath(final String templateFilePath) {
    final String ciType = PTPConstants.LOAD_LEVELER_SERVICE_PROVIDER_ID;
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final LoadLevelerConf conf = (LoadLevelerConf) super.fCommInterfaceFact.getOrCreate(ciType);
    conf.fTemplateFilePath = templateFilePath;
    updateDirtyFlag();
  }
  
  public void setTemplateOption(final ELLTemplateOpt templateOpt) {
    final String ciType = PTPConstants.LOAD_LEVELER_SERVICE_PROVIDER_ID;
    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
    final LoadLevelerConf conf = (LoadLevelerConf) super.fCommInterfaceFact.getOrCreate(ciType);
    conf.fTemplateOpt = templateOpt;
    updateDirtyFlag();
  }
  
  public void setHostFile(final String hostFile) {
//    final String ciType = PTPConstants.SOCKETS_SERVICE_PROVIDER_ID;
//    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
//    final AbstractHostsBasedConf conf = (AbstractHostsBasedConf) super.fCommInterfaceFact.getOrCreate(ciType);
    final AbstractHostsBasedConf conf = (AbstractHostsBasedConf) super.fCommInterfaceFact.getCurrentCommunicationInterface();
    conf.fHostFile = hostFile;
    updateDirtyFlag();
  }
  
  public void setLoadLevelerScript(final String file) {
  final AbstractHostsBasedConf conf = (AbstractHostsBasedConf) super.fCommInterfaceFact.getCurrentCommunicationInterface();
  conf.fLoadLevelerScript = file;
  updateDirtyFlag();
}
  
  public void setHostList(final List<String> hostList) {
//    final String ciType = PTPConstants.SOCKETS_SERVICE_PROVIDER_ID;
//    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
//    final AbstractHostsBasedConf conf = (AbstractHostsBasedConf) super.fCommInterfaceFact.getOrCreate(ciType);
    final AbstractHostsBasedConf conf = (AbstractHostsBasedConf) super.fCommInterfaceFact.getCurrentCommunicationInterface();
    final StringBuilder sb = new StringBuilder();
    for (final String host : hostList) {
      if (sb.length() > 0) {
        sb.append(',');
      }
      sb.append(host);
    }
    conf.fHosts = sb.toString();
    updateDirtyFlag();
  }
  
  public void setShouldUseHostFile(final boolean shouldUseHostFile) {
//    final String ciType = PTPConstants.SOCKETS_SERVICE_PROVIDER_ID;
//    super.fCommInterfaceFact.defineCurrentCommInterfaceType(ciType);
//    final AbstractHostsBasedConf conf = (AbstractHostsBasedConf) super.fCommInterfaceFact.getOrCreate(ciType);
    final AbstractHostsBasedConf conf = (AbstractHostsBasedConf) super.fCommInterfaceFact.getCurrentCommunicationInterface();
    conf.fShouldUseHostFile = shouldUseHostFile;
    updateDirtyFlag();
  }
  
  public void setLoadLevelerSelected(final boolean shouldUseLL) {
    final AbstractHostsBasedConf conf = (AbstractHostsBasedConf) super.fCommInterfaceFact.getCurrentCommunicationInterface();
    conf.fLoadLevelerSelected = shouldUseLL;
    updateDirtyFlag();
  }
  
  // --- IDebuggingInfoConf's setter methods
  
  public void setDebuggerFolder(final String folder) {
    super.fDebuggingInfoConf.fDebuggerFolder = folder;
    updateDirtyFlag();
  }
  
  public void setDebuggingPort(final int port) {
    super.fDebuggingInfoConf.fPort = port;
    updateDirtyFlag();
  }
  
  // --- Private code
  
  private void updateDirtyFlag() {
    this.fIsDirty = (this.fIsDirty) ? ! this.equals(this.fSource) : true;
  }
  
  // --- Fields
  
  private final X10PlatformConf fSource;
  
  private boolean fIsStale;
  
  private boolean fIsDirty;

}
