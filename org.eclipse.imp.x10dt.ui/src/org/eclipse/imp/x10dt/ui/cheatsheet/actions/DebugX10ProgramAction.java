/*
 * (C) Copyright IBM Corporation 2007
 * 
 * This file is part of the Eclipse IMP.
 */
package org.eclipse.imp.x10dt.ui.cheatsheet.actions;

import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.imp.x10dt.ui.launching.X10LaunchShortcut;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;

public class DebugX10ProgramAction extends Action implements ICheatSheetAction {
    public DebugX10ProgramAction() {
	this("Debug an X10 program");
    }

    public DebugX10ProgramAction(String text) {
	super(text, null);
    }

    public void run(String[] params, ICheatSheetManager manager) {
	X10LaunchShortcut x10ls= new X10LaunchShortcut();
	IEditorPart editorPart= PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();

	x10ls.launch(editorPart, ILaunchManager.DEBUG_MODE);
    }
}
