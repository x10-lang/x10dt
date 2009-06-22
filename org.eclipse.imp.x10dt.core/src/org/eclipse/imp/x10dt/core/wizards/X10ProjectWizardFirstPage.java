/*
 * Created on Feb 6, 2006
 */
package com.ibm.watson.safari.x10.wizards;

import org.eclipse.uide.wizards.NewProjectWizardFirstPage;

public class X10ProjectWizardFirstPage extends NewProjectWizardFirstPage {
    public X10ProjectWizardFirstPage() {
	super("X10 Project");
	setPageComplete(false);
	setTitle("New X10 Project");
	setDescription("Creates a new X10 project");
	fInitialName= ""; //$NON-NLS-1$
    }
}
