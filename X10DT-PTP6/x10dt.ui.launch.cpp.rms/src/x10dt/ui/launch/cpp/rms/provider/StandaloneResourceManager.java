/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.rms.provider;

import org.eclipse.ptp.rmsystem.IResourceManager;

import x10dt.ui.launch.cpp.launching.AbstractX10ResourceManager;

/**
 * Defines the resource manager implementation for X10 Standalone transport.
 * 
 * @author egeay
 */
public final class StandaloneResourceManager extends AbstractX10ResourceManager implements IResourceManager {

  /**
   * Creates the resource manager instance with the parameters provided.
   * 
   * @param id The resource manager id.
   * @param universe The universe of controls.
   * @param rmConfig The resource manager configuration.
   */
  public StandaloneResourceManager(final StandaloneResourceManagerConfiguration rmConfig, StandaloneResourceManagerControl control, StandaloneResourceManagerMonitor monitor) {
	    super(rmConfig, control, monitor);
  }

 
  
  // --- Abstract methods implementation

  /*protected void doAfterCloseConnection() {
  }

  protected void doAfterOpenConnection() {
  }

  protected void doBeforeCloseConnection() {
  }

  protected void doBeforeOpenConnection() {
  }

  protected IPJob  doCreateJob(final IPQueue  queue, final String jobId, final AttributeManager attrs) {
    return newJob(queue, jobId, attrs);
  }

  protected IPMachine  doCreateMachine(final String machineId, final AttributeManager attrs) {
    return newMachine(machineId, attrs);
  }

  protected IPNode  doCreateNode(final IPMachine  machine, final String nodeId, final AttributeManager attrs) {
    return newNode(machine, nodeId, attrs);
  }

  protected IPQueue  doCreateQueue(final String queueId, final AttributeManager attrs) {
    return newQueue(queueId, attrs);
  }

  protected IRuntimeSystem doCreateRuntimeSystem() throws CoreException {
    final IX10RMConfiguration rmConfig = (IX10RMConfiguration) getConfiguration();
    return new StandaloneX10RuntimeSystem(Integer.parseInt(getID()), rmConfig);
  }

  protected boolean doUpdateJobs(final IPQueue  queue, final Collection<IPJob > jobs, 
                                 final AttributeManager attrs) {
    return updateJobs(queue, jobs, attrs);
  }

  protected boolean doUpdateMachines(final Collection<IPMachine > machines, final AttributeManager attrs) {
    return updateMachines(machines, attrs);
  }

  protected boolean doUpdateNodes(final IPMachine  machine, final Collection<IPNode > nodes, 
                                  final AttributeManager attrs) {
    return updateNodes(machine, nodes, attrs);
  }

  protected boolean doUpdateProcesses(final IPJob  job, final BitSet processJobRanks, final AttributeManager attrs) {
    return updateProcessesByJobRanks(job, processJobRanks, attrs);
  }

  protected boolean doUpdateQueues(final Collection<IPQueue > queues, final AttributeManager attrs) {
    return updateQueues(queues, attrs);
  }

  protected boolean doUpdateRM(final AttributeManager attrs) {
    return updateRM(attrs);
  }
*/
}
