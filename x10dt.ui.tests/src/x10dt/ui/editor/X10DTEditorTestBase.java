/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/

package x10dt.ui.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.parser.IModelListener;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEclipseEditor;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

import x10dt.ui.tests.X10DTTestBase;

/**
 * @author rfuhrer
 */
public class X10DTEditorTestBase extends X10DTTestBase {
    protected class UpdateListener implements IModelListener {
        private boolean fUpdated = false;

        public void reset() {
            fUpdated = false;
        }

        public boolean updateFinished() {
            return fUpdated;
        }

        public AnalysisRequired getAnalysisRequired() {
            return AnalysisRequired.NONE; // this posts the error annotations!
        }

        public void update(IParseController parseController, IProgressMonitor monitor) {
            fUpdated = true;
        }
    }

    /**
       * The bot for the editor used to test the quick outline view.
       */
    protected SWTBotEclipseEditor fSrcEditor;
    protected UpdateListener fUpdateListener = new UpdateListener();

    protected void waitForParser() throws Exception {
        topLevelBot.waitUntil(new DefaultCondition() {
    
          public boolean test() throws Exception {
            return fUpdateListener.updateFinished();
          }
    
          public String getFailureMessage() {
            return "Some Failure Message";
          }
    
        });
    }
}
