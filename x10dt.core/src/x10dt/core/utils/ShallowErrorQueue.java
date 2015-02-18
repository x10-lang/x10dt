/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.core.utils;

import polyglot.util.ErrorInfo;
import polyglot.util.ErrorQueue;
import polyglot.util.Position;

/**
 * Implementation of Polyglot {@link ErrorQueue} that records and does nothing.
 * 
 * @author egeay
 */
public final class ShallowErrorQueue implements ErrorQueue {

  // --- Interface methods implementation
  
  public void enqueue(final int type, final String message) {
  }

  public void enqueue(final int type, final String message, final Position position) {
  }

  public void enqueue(final ErrorInfo errorInfo) {
  }

  public int errorCount() {
    return 0;
  }

  public void flush() {
  }

  public boolean hasErrors() {
    return false;
  }
  
}
