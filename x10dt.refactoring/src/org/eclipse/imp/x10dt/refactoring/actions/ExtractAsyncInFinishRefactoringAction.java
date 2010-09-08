package org.eclipse.imp.x10dt.refactoring.actions;

import org.eclipse.imp.refactoring.RefactoringStarter;
import org.eclipse.imp.x10dt.refactoring.ExtractAsyncInFinishRefactoring;
import org.eclipse.imp.x10dt.refactoring.wizards.ExtractAsyncInFinishWizard;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.TextEditorAction;


public class ExtractAsyncInFinishRefactoringAction extends TextEditorAction {
    public ExtractAsyncInFinishRefactoringAction(ITextEditor editor) {
        super(X10RefactoringMessages.ResBundle, "extractAsyncInFinish.", editor);
    }

    public void run() {
        final ExtractAsyncInFinishRefactoring refactoring = new ExtractAsyncInFinishRefactoring(getTextEditor());

        if (refactoring != null)
            new RefactoringStarter().activate(refactoring, new ExtractAsyncInFinishWizard(refactoring),
                    getTextEditor().getSite().getShell(), refactoring.getName(), false);
    }
}
