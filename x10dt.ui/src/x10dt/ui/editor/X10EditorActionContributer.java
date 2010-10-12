package x10dt.ui.editor;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.editors.text.TextEditorActionContributor;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.RetargetTextEditorAction;

import x10dt.search.ui.Messages;
import x10dt.search.ui.typeHierarchy.X10Constants;

public class X10EditorActionContributer extends TextEditorActionContributor {

	private RetargetTextEditorAction fOpenHierarchy;

	public X10EditorActionContributer() {
		fOpenHierarchy = new RetargetTextEditorAction(Messages
				.getBundleForConstructedKeys(), "OpenHierarchy."); //$NON-NLS-1$
		fOpenHierarchy.setActionDefinitionId(X10Constants.OPEN_HIERARCHY);
	}

	@Override
	public void contributeToMenu(IMenuManager menu) {
		super.contributeToMenu(menu);

		IMenuManager navigateMenu = menu
				.findMenuUsingPath(IWorkbenchActionConstants.M_NAVIGATE);
		if (navigateMenu != null) {
			navigateMenu.appendToGroup(IWorkbenchActionConstants.SHOW_EXT,
					fOpenHierarchy);
		}
	}

	@Override
	public void setActiveEditor(IEditorPart part) {
		super.setActiveEditor(part);

		ITextEditor textEditor = null;
		if (part instanceof ITextEditor)
			textEditor = (ITextEditor) part;

		fOpenHierarchy.setAction(getAction(textEditor,
				X10Constants.OPEN_HIERARCHY));
	}

}
