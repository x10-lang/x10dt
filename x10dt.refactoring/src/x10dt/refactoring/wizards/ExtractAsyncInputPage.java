package x10dt.refactoring.wizards;

import org.eclipse.ltk.ui.refactoring.UserInputWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import x10dt.refactoring.X10RefactoringBase;

public class ExtractAsyncInputPage extends UserInputWizardPage {
    public ExtractAsyncInputPage(String name) {
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

        final Button verboseCB= new Button(result, SWT.CHECK);
        verboseCB.setText("Verbose");
        verboseCB.addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(SelectionEvent e) { }
            public void widgetSelected(SelectionEvent e) {
                boolean state= verboseCB.getSelection();
                ExtractAsyncInputPage.this.getExtractAsyncRefactoring().setVerbose(state);
            }
        });

        setControl(result);
    }

    private X10RefactoringBase getExtractAsyncRefactoring() {
        return (X10RefactoringBase) getRefactoring();
    }
}
