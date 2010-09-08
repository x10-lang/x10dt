/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package org.eclipse.imp.x10dt.ui.launch.core;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * Controls the plug-in life cycle for this project and provides logging services.
 * 
 * @author egeay
 */
public class LaunchCore extends AbstractUIPlugin {
  
  /**
   * Unique id for this plugin.
   */
  public static final String PLUGIN_ID = "org.eclipse.imp.x10dt.ui.launch.core"; //$NON-NLS-1$
   

  
  // --- Public services
  
  /**
   * Returns the current plugin instance.
   * 
   * @return A non-null value if the plugin is activated, or <b>null</b> if it is stopped.
   */
  public static AbstractUIPlugin getInstance() {
    return fPlugin;
  }
  
  /**
   * Logs (if the plugin is active) the operation outcome provided through the status instance.
   * 
   * @param status The status to log.
   */
  public static final void log(final IStatus status) {
    if (fPlugin != null) {
      fPlugin.getLog().log(status);
    }
  }
  
  /**
   * Logs (if the plugin is active) the outcome of an operation with the parameters provided.
   * 
   * @param severity The severity of the message. The code is one of {@link IStatus#OK}, {@link IStatus#ERROR}, 
   * {@link IStatus#INFO}, {@link IStatus#WARNING} or {@link IStatus#CANCEL}.
   * @param code The plug-in-specific status code, or {@link IStatus#OK}.
   * @param message The human-readable message, localized to the current locale.
   * @param exception The exception to log, or <b>null</b> if not applicable.
   */
  public static final void log(final int severity, final int code, final String message, final Throwable exception) {
    if (fPlugin != null) {
      fPlugin.getLog().log(new Status(severity, fPlugin.getBundle().getSymbolicName(), code, message, exception));
    }
  }
  
  /**
   * Logs (if the plugin is active) the outcome of an operation with the parameters provided.
   * 
   * <p>Similar to {@link #log(int, int, String, Throwable)} with <code>code = IStatus.OK</code>.
   * 
   * @param severity The severity of the message. The code is one of {@link IStatus#OK}, {@link IStatus#ERROR}, 
   * {@link IStatus#INFO}, {@link IStatus#WARNING} or {@link IStatus#CANCEL}.
   * @param message The human-readable message, localized to the current locale.
   * @param exception The exception to log, or <b>null</b> if not applicable.
   */
  public static final void log(final int severity, final String message, final Throwable exception) {
    if (fPlugin != null) {
      fPlugin.getLog().log(new Status(severity, fPlugin.getBundle().getSymbolicName(), message, exception));
    }
  }
  
  /**
   * Logs (if the plugin is active) the outcome of an operation with the parameters provided.
   * 
   * <p>Similar to {@link #log(int, int, String, Throwable)} with <code>code = IStatus.OK</code> and 
   * <code>exception = null</code>.
   * 
   * @param severity The severity of the message. The code is one of {@link IStatus#OK}, {@link IStatus#ERROR}, 
   * {@link IStatus#INFO}, {@link IStatus#WARNING} or {@link IStatus#CANCEL}.
   * @param message The human-readable message, localized to the current locale.
   */
  public static final void log(final int severity, final String message) {
    if (fPlugin != null) {
      fPlugin.getLog().log(new Status(severity, fPlugin.getBundle().getSymbolicName(), message));
    }
  }
  
  //--- Overridden methods

  public void start(final BundleContext context) throws Exception {
    super.start(context);
    fPlugin = this;
  }

  public void stop(final BundleContext context) throws Exception {
    fPlugin = null;
    super.stop(context);
  }
  
  // --- Fields
  
  private static AbstractUIPlugin fPlugin;

}
