/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package org.eclipse.imp.x10dt.ui.cpp.debug.pdi;

import static org.eclipse.ptp.core.IPTPLaunchConfigurationConstants.ATTR_ARGUMENTS;
import static org.eclipse.ptp.core.IPTPLaunchConfigurationConstants.ATTR_EXECUTABLE_PATH;
import static org.eclipse.ptp.core.IPTPLaunchConfigurationConstants.ATTR_PROJECT_NAME;
import static org.eclipse.ptp.core.IPTPLaunchConfigurationConstants.ATTR_STOP_IN_MAIN;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.UnexpectedException;
import java.util.List;
import java.util.Observer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.imp.x10dt.ui.cpp.debug.DebugCore;
import org.eclipse.imp.x10dt.ui.cpp.debug.DebugMessages;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ptp.core.util.BitList;
import org.eclipse.ptp.debug.core.launch.IPLaunch;
import org.eclipse.ptp.debug.core.pdi.IPDIDebugger;
import org.eclipse.ptp.debug.core.pdi.IPDILocation;
import org.eclipse.ptp.debug.core.pdi.PDIException;
import org.eclipse.ptp.debug.core.pdi.model.IPDIAddressBreakpoint;
import org.eclipse.ptp.debug.core.pdi.model.IPDIExceptionpoint;
import org.eclipse.ptp.debug.core.pdi.model.IPDIFunctionBreakpoint;
import org.eclipse.ptp.debug.core.pdi.model.IPDILineBreakpoint;
import org.eclipse.ptp.debug.core.pdi.model.IPDISignal;
import org.eclipse.ptp.debug.core.pdi.model.IPDIWatchpoint;
import org.eclipse.ptp.debug.core.pdi.model.aif.IAIF;

import com.ibm.debug.daemon.CoreDaemon;
import com.ibm.debug.daemon.DaemonConnectionInfo;
import com.ibm.debug.daemon.DaemonSocketConnection;
import com.ibm.debug.internal.pdt.PICLDebugPlugin;
import com.ibm.debug.internal.pdt.PICLDebugTarget;
import com.ibm.debug.internal.pdt.PICLUtils;
import com.ibm.debug.internal.pdt.model.DebuggeeProcess;
import com.ibm.debug.internal.pdt.model.EngineRequestException;
import com.ibm.debug.internal.pdt.model.Function;
import com.ibm.debug.internal.pdt.model.Location;
import com.ibm.debug.internal.pdt.model.Module;
import com.ibm.debug.internal.pdt.model.Part;
import com.ibm.debug.internal.pdt.model.View;
import com.ibm.debug.internal.pdt.model.ViewFile;
import com.ibm.debug.pdt.launch.PICLLoadInfo;

/**
 * Implementation of {@link IPDIDebugger} for X10.
 * 
 * <p>This is the main class that handles the connection and requests with PDT debugger.
 * 
 * @author egeay
 */
@SuppressWarnings("all")
public final class X10PDIDebugger implements IPDIDebugger, IDebugEventSetListener {
  
  public X10PDIDebugger(final int port) {
    this.fPort = port;
  }
  
  // --- IPDIDebugger's interface methods implementation
  
  public void commandRequest(final BitList tasks, final String command) throws PDIException {
    throw new IllegalStateException();
  }

  public void disconnect(final Observer observer) throws PDIException {
    stopDebugger();
  }

  public int getErrorAction(final int errorCode) {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
    throw new IllegalStateException();
  }

  public void initialize(final ILaunchConfiguration configuration, final List<String> args, 
                         final IProgressMonitor monitor) throws PDIException {
    try {
      this.fServerSocket = new ServerSocket(this.fPort);
      this.fState = ESessionState.CONNECTED;

      this.fAcceptingThread = new Thread(new ListenerRunnable(), "Listening thread to Remote Debugger"); //$NON-NLS-1$
      this.fAcceptingThread.setDaemon(true);
      this.fAcceptingThread.start();
    } catch (IOException except) {
      throw new PDIException(null, NLS.bind(DebugMessages.PDID_ServerSocketInitError, except.getMessage()));
    }
  }
  
  public boolean isConnected(final IProgressMonitor monitor) throws PDIException {
    if (hasConnected(monitor)) {
      return true;
    } else {
      disconnect(null);
      return false;
    }
  }

  public void register(final Observer observer) {
    // Nothing to do...
  }

  public void startDebugger(final String app, final String path, final String dir, final String[] args) throws PDIException {
    // Nothing to do...
  }

  public void stopDebugger() throws PDIException {
    try {
      if (this.fAcceptingThread.isAlive()) {
        this.fAcceptingThread.interrupt();
      }
      this.fServerSocket.close();
      this.fServerSocket = null;
      this.fState = ESessionState.DISCONNECTED;
      
      if (this.fPDTTarget != null) {
        this.fPDTTarget.disconnect();
        this.fPDTTarget = null;
      }
    } catch (IOException except) {
      throw new PDIException(null, NLS.bind(DebugMessages.PDID_SocketClosingError, except.getMessage()));
    } catch (DebugException except) {
      throw new PDIException(null, NLS.bind(DebugMessages.PDID_PDTDisconnectError, except.getMessage()));
    } finally {
      DebugPlugin.getDefault().removeDebugEventListener(this);
    }
  }
  
  // --- IPDIBreakpointManagement's interface methods implementation

  public void deleteBreakpoint(final BitList tasks, final int bpid) throws PDIException {

  }

  public void setAddressBreakpoint(final BitList tasks, final IPDIAddressBreakpoint bpt) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void setConditionBreakpoint(final BitList tasks, final int bpid, final String condition) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void setEnabledBreakpoint(final BitList tasks, final int bpid, final boolean enabled) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void setExceptionpoint(final BitList tasks, final IPDIExceptionpoint breakPoint) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void setFunctionBreakpoint(final BitList tasks, final IPDIFunctionBreakpoint breakPoint) throws PDIException {
    try {
      final Function[] fns = getDebuggeeProcess().getFunctions(breakPoint.getLocator().getFunction(), true /* caseSensitive */);
      assert fns.length == 1;
      this.fPDTTarget.createEntryBreakpoint(true, fns[0], null, null, 0, 1, 1, 1, null);
    } catch (Exception except) {
      throw new PDIException(tasks, except.getMessage());
    }
  }

  public void setLineBreakpoint(final BitList tasks, final IPDILineBreakpoint breakPoint) throws PDIException {
    final ViewFile viewFile = searchViewFile("PETest.c");
    if (viewFile == null) {
      throw new PDIException(tasks, NLS.bind("Could not find PDT View file for breakpoint {0}", breakPoint.getLocator()));
    }
    final Location location = new Location(viewFile, breakPoint.getLocator().getLineNumber());
    try {
      this.fPDTTarget.createLineBreakpoint(true /* enabled */, location, null /* conditionalExpression */, 
                                           null /* brkAction */,  0 /* threadNumber */, 1 /* everyValue */, 1 /* fromValue */, 
                                           0 /* toValue */, null /* property */, null /* stmtNumber */, null /* engineData */);
    } catch (EngineRequestException except) {
      throw new PDIException(tasks, "PDT engine exception during setLinebreakpoint: " + except.getMessage());
    }
  }

  public void setWatchpoint(final BitList tasks, final IPDIWatchpoint breakPoint) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }
  
  // --- IPDIExecuteManagement's interface methods implementation

  public void restart(final BitList tasks) throws PDIException {
    throw new IllegalStateException();
  }

  public void resume(final BitList tasks, final boolean passSignal) throws PDIException {
    try {
      this.fPDTTarget.resume();
    } catch (DebugException except) {
      throw new PDIException(tasks, except.getMessage());
    }
  }

  public void resume(final BitList tasks, final IPDILocation location) throws PDIException {
    try {
      this.fPDTTarget.resume();
    } catch (DebugException except) {
      throw new PDIException(tasks, NLS.bind("Resume action error: ", except.getMessage()));
    }
  }

  public void resume(final BitList tasks, final IPDISignal signal) throws PDIException {
    try {
      this.fPDTTarget.resume();
    } catch (DebugException except) {
      throw new PDIException(tasks, except.getMessage());
    }
  }

  public void start(final BitList tasks) throws PDIException {
    resume(tasks, false);
  }

  public void stepInto(final BitList tasks, final int count) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void stepIntoInstruction(final BitList tasks, final int count) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void stepOver(final BitList tasks, final int count) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void stepOverInstruction(final BitList tasks, final int count) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void stepReturn(final BitList tasks, final int count) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void stepReturn(final BitList tasks, final IAIF aif) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void stepUntil(final BitList tasks, final IPDILocation location) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void suspend(final BitList tasks) throws PDIException {
    if (this.fPDTTarget != null) {
      try {
        this.fPDTTarget.suspend();
      } catch (DebugException except) {
        throw new PDIException(tasks, NLS.bind("Suspend action error: ", except.getMessage()));
      }
    }
  }

  public void terminate(final BitList tasks) throws PDIException {
    try {
      this.fPDTTarget.terminate();
    } catch (DebugException except) {
      throw new PDIException(tasks, "Terminate action error: " + except.getMessage());
    }
  }
  
  // --- IPDIVariableManagement's interface methods implementation

  public void dataEvaluateExpression(final BitList tasks, final String expression) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void deleteVariable(final BitList tasks, final String variable) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void evaluateExpression(final BitList tasks, final String expression) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void listArguments(final BitList tasks, final int low, final int high) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void listGlobalVariables(final BitList tasks) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void listLocalVariables(final BitList tasks) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void retrieveAIF(final BitList tasks, final String expr) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void retrievePartialAIF(final BitList tasks, final String expr, final String key, final boolean listChildren, 
                                 final boolean express) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void retrieveVariableType(final BitList tasks, final String variable) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }
  
  // --- IPDISignalManagement's interface methods implementation

  public void listSignals(final BitList tasks, final String name) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void retrieveSignalInfo(final BitList tasks, final String arg) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }
  
  // --- IPDIStackframeManagement's interface methods implementation

  public void listStackFrames(final BitList tasks, final int low, final int depth) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void setCurrentStackFrame(final BitList tasks, final int level) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }
  
  // --- IPDIThreadManagement's interface methods implementation

  public void listInfoThreads(final BitList tasks) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void retrieveStackInfoDepth(final BitList tasks) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void selectThread(final BitList tasks, final int tid) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }
  
  // --- IPDIMemoryBlockManagement's interface methods implementation

  public void createDataReadMemory(final BitList tasks, final long offset, final String address, final int wordFormat, 
                                   final int wordSize, final int rows, final int cols, 
                                   final Character asChar) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }

  public void createDataWriteMemory(final BitList tasks, final long offset, final String address, final int wordFormat, 
                                    final int wordSize, final String value) throws PDIException {
    System.err.println("Passed in " + new Exception().getStackTrace()[0]);
  }
  
  // --- IDebugEventSetListener
  
  public void handleDebugEvents(final DebugEvent[] events) {
    for (final DebugEvent debugEvent : events) {
      switch (debugEvent.getKind()) {
        case DebugEvent.RESUME:
          break;
        case DebugEvent.SUSPEND:
          break;
        case DebugEvent.CREATE:
          if (debugEvent.getSource() instanceof IProcess) {
            
          }
          break;
        case DebugEvent.TERMINATE:
          if (debugEvent.getSource() instanceof IDebugTarget) {
            if (this.fPDTTarget.equals(debugEvent.getSource())) {
              
            }
          }
          break;
        case DebugEvent.CHANGE:
          break;
        case DebugEvent.MODEL_SPECIFIC:
          // Do nothing
        default:
          throw new IllegalStateException("Unexpected debug event: " + debugEvent);
      }
    }
  }
  
  // --- Public services
  
  public void setLaunch(final IPLaunch launch) {
    this.fWaitLock.lock();
    try {
      this.fLaunch = launch;
      this.fLaunchCondition.signal();
    } finally {
      this.fWaitLock.unlock();
    }
  }
  
  // --- Private code
  
  private PICLLoadInfo createLoadInfo(final ILaunchConfiguration configuration) throws CoreException {
    final PICLLoadInfo loadInfo = new PICLLoadInfo();
    loadInfo.setLaunchConfig(configuration);
    loadInfo.setProgramName(configuration.getAttribute(ATTR_EXECUTABLE_PATH, EMPTY_STRING));
    loadInfo.setProgramParms(configuration.getAttribute(ATTR_ARGUMENTS, EMPTY_STRING));
    loadInfo.setProject(getProjectResource(configuration.getAttribute(ATTR_PROJECT_NAME, EMPTY_STRING)));
   
//    if (configuration.getAttribute(ATTR_STOP_IN_MAIN, false)) {
      loadInfo.setStartupBehaviour(PICLLoadInfo.RUN_TO_MAIN);
//    } else {
//      loadInfo.setStartupBehaviour(PICLLoadInfo.RUN_TO_BREAKPOINT);
//    }
    return loadInfo;
  }
  
  private DebuggeeProcess getDebuggeeProcess() {
    return (DebuggeeProcess) this.fPDTTarget.getProcess();
  }
  
  private IProject getProjectResource(final String projectName) {
    final IProject projects[] = ResourcesPlugin.getWorkspace().getRoot().getProjects();
    for (final IProject project : projects) {
      if (project.getName().equals(projectName)) {
        return project;
      }
    }
    return null;
  }
  
  private ViewFile searchViewFile(final String fileName) {
    for (final Module module : getDebuggeeProcess().getModules(false)) {
      if (module != null) {
        final Part[] parts = module.getParts();
        if (parts == null || parts.length == 0) {
          continue;
        }
        for (final Part part : parts) {
          if (part != null) {
            final View view = part.getView(this.fPDTTarget.getDebugEngine().getSourceViewInformation());
            if (view != null) {
              for (final ViewFile vf : view.getViewFiles()) {
                if (vf != null) {
                  if (vf.getBaseFileName().equals(fileName)) {
                    return vf;
                  }
                }
              }
            }
          }
        }
      }
    }
    return null;
  }
  
  private boolean hasConnected(final IProgressMonitor monitor) {
    this.fWaitLock.lock();
    try {
      if (this.fState == ESessionState.CONNECTED) {
        while (this.fState != ESessionState.RUNNING && ! monitor.isCanceled()) {
          this.fWaiting = true;
          try {
            this.fRunningCondition.await(1000, TimeUnit.MILLISECONDS);
          } catch (InterruptedException except) {
            // Expect to be interrupted if monitor is canceled
            monitor.setCanceled(true);
          }
        }
        if (monitor.isCanceled()) {
          return false;
        }
        if (this.fState != ESessionState.CONNECTED) {
          return false;
        }
      }
      return true;
    } finally {
      this.fWaitLock.unlock();
      monitor.done();
    }
  }
  
  private void process(final Socket socket) {
    this.fWaitLock.lock();
    try {
      while (this.fLaunch == null) {
        this.fLaunchCondition.await();
      }
      final int version = new DataInputStream(socket.getInputStream()).readInt();
      final String[] input = CoreDaemon.readOldStyleStrings(socket.getInputStream(), version);
      
      final PICLLoadInfo loadInfo = createLoadInfo(this.fLaunch.getLaunchConfiguration());
      final DaemonConnectionInfo connectionInfo = new DaemonConnectionInfo(input[0], input[1]);
      connectionInfo.setConnection(new DaemonSocketConnection(socket));

      DebugPlugin.getDefault().addDebugEventListener(this);

      this.fPDTTarget = new PICLDebugTarget(this.fLaunch, loadInfo, connectionInfo);
      this.fPDTTarget.engineIsWaiting(connectionInfo, true /* socketReuse */);
      
      this.fState = ESessionState.RUNNING;
      if (this.fWaiting) {
        this.fRunningCondition.signal();
        this.fWaiting = false;
      }
    } catch (InterruptedException except) {
      DebugCore.log(IStatus.WARNING, "Launch Thread Condition Interrupted.");
    } catch (IOException except) {
      DebugCore.log(IStatus.ERROR, "Unable to access socket input stream", except);
    } catch (CoreException except) {
      DebugCore.log(except.getStatus());
    } finally {
      this.fWaitLock.unlock();
    }
  }
  
  // --- Private classes
  
  private class ListenerRunnable implements Runnable {

    // --- Interface methods implementation
    
    public void run() {
      while (X10PDIDebugger.this.fState == ESessionState.CONNECTED) {
        try {
          if (! X10PDIDebugger.this.fServerSocket.isClosed()) {
            final Socket socket = X10PDIDebugger.this.fServerSocket.accept();
            if (socket != null) {
              new Thread(new ProcessRunnable(socket)).start();
            }
          }
        } catch (IOException except) {
          DebugCore.log(IStatus.ERROR, DebugMessages.PDID_SocketListeningError, except);
        }
      }
      
    }
    
  }
  
  private class ProcessRunnable implements Runnable {
    
    ProcessRunnable(final Socket socket) {
      this.fSocket = socket;
    }
    
    // --- Interface methods implementation

    public void run() {
      process(this.fSocket);
    }
    
    // --- Fields
    
    private final Socket fSocket;
    
  }
  
  private enum ESessionState {
    CONNECTED, RUNNING, DISCONNECTED
  }
  
  // --- Fields
  
  private ESessionState fState = ESessionState.DISCONNECTED;
  
  private ServerSocket fServerSocket;
  
  private IPLaunch fLaunch;  
  
  private boolean fWaiting;
  
  private PICLDebugTarget fPDTTarget;
  
  private Thread fAcceptingThread;
  
  private final int fPort;
  
  private final ReentrantLock fWaitLock = new ReentrantLock();
  
  private final Condition fRunningCondition = this.fWaitLock.newCondition();
  
  private final Condition fLaunchCondition = this.fWaitLock.newCondition();
  
  private static final String EMPTY_STRING = ""; //$NON-NLS-1$
  
}
