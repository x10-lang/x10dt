package org.eclipse.imp.x10dt.debug.model;

import org.eclipse.debug.core.model.IVariable;

/**
 * X10DT Debugger Model Interface
 * 
 * @author mmk
 * @since 10/10/08
 */
public interface IX10Variable extends IVariable{
	String getName();
	IX10Type getType();
}
