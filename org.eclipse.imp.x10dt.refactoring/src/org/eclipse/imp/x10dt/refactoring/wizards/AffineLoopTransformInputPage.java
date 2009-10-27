/*******************************************************************************
* Copyright (c) 2009 IBM Corporation.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    Robert Fuhrer (rfuhrer@watson.ibm.com) - initial API and implementation
*******************************************************************************/

package org.eclipse.imp.x10dt.refactoring.wizards;

import org.eclipse.imp.x10dt.refactoring.AffineLoopTransformRefactoring;
import org.eclipse.ltk.ui.refactoring.UserInputWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

public class AffineLoopTransformInputPage extends UserInputWizardPage implements Listener {
    private Text fText;

    public AffineLoopTransformInputPage(String name) {
        super(name);
    }

    /**
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        initializeDialogUnits(parent);
        Composite result = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();

        layout.numColumns = 4;
        result.setLayout(layout);

        final Label numTimesLabel= new Label(result, SWT.NONE);
        numTimesLabel.setText("Transform function:");
        final Text numTimesText= new Text(result, SWT.SINGLE);

        numTimesText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                String newText= numTimesText.getText();
                if (newText.length() > 0) {
                    try {
                        Integer.parseInt(newText);
                    } catch (NumberFormatException ex) {
                        AffineLoopTransformInputPage.this.setErrorMessage("Transform function must be an affine function");
                        AffineLoopTransformInputPage.this.setPageComplete(false);
                    }
                }
            }
        });
        setControl(result);
    }

    public void handleEvent(Event e) {
        Widget source = e.widget;
        String fTextResult = "";
        if (source == fText) {
            fTextResult = fText.getText();
            if (fTextResult == null)
                fTextResult = "";
        }
    }

    private AffineLoopTransformRefactoring getAffineLoopTransformRefactoring() {
        return (AffineLoopTransformRefactoring) getRefactoring();
    }
}
