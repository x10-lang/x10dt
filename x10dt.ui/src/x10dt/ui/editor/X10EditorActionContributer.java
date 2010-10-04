package x10dt.ui.editor;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.editors.text.TextEditorActionContributor;

public class X10EditorActionContributer extends TextEditorActionContributor {
	public static final String OPEN_HIERARCHY= "x10dt.ui.navigate.OpenHierarchy"; //$NON-NLS-1$

//	private RetargetTextEditorAction fOpenHierarchy;
	
	public X10EditorActionContributer()
	{
//		fOpenHierarchy= new RetargetTextEditorAction(Messages.getBundleForConstructedKeys(), "OpenHierarchy."); //$NON-NLS-1$
//		fOpenHierarchy.setActionDefinitionId(OPEN_HIERARCHY);
	}
	
	@Override
	public void contributeToMenu(IMenuManager menu) {
		super.contributeToMenu(menu);
		
//		IMenuManager navigateMenu= menu.findMenuUsingPath(IWorkbenchActionConstants.M_NAVIGATE);
//		if (navigateMenu != null) {
//			navigateMenu.appendToGroup(IWorkbenchActionConstants.SHOW_EXT, fOpenHierarchy);
//		}
	}

	@Override
	public void setActiveEditor(IEditorPart part) {
		super.setActiveEditor(part);
		
//		ITextEditor textEditor= null;
//		if (part instanceof ITextEditor)
//			textEditor= (ITextEditor)part;
//		
//		fOpenHierarchy.setAction(getAction(textEditor, OPEN_HIERARCHY));
	}
	
}
