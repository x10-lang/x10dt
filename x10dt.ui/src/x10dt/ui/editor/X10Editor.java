package x10dt.ui.editor;

import org.eclipse.imp.editor.UniversalEditor;
import org.eclipse.imp.ui.DefaultPartListener;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.search.ui.IContextMenuConstants;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;

import x10dt.ui.X10DTUIPlugin;

public class X10Editor extends UniversalEditor {
	private DefaultPartListener fRefreshContributions;

	public static final String TYPE_ACTION_SET = X10DTUIPlugin.PLUGIN_ID
			+ ".typeActionSet";

	public X10Editor() {
		super();
		setSourceViewerConfiguration(new X10StructuredSourceViewerConfiguration(
				X10DTUIPlugin.getInstance().getPreferenceStore(), this));
	}

	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		registerEditorContributionsActivator();
	}

	@Override
	public void dispose() {
		unregisterEditorContributionsActivator();
		super.dispose();
	}

	/**
	 * Makes sure that menu items and status bar items disappear as the editor
	 * is out of focus, and reappear when it gets focus again. This does not
	 * work for toolbar items for unknown reasons, they stay visible.
	 * 
	 */
	private void registerEditorContributionsActivator() {
		fRefreshContributions = new DefaultPartListener() {
			@Override
			public void partActivated(IWorkbenchPart part) {
				if (part instanceof UniversalEditor) {
					part.getSite().getPage().showActionSet(TYPE_ACTION_SET);
				} else {
					part.getSite().getPage().hideActionSet(TYPE_ACTION_SET);
				}
			}
		};
		getSite().getPage().addPartListener(fRefreshContributions);
	}

	private void unregisterEditorContributionsActivator() {
		if (fRefreshContributions != null) {
			getSite().getPage().removePartListener(fRefreshContributions);
		}
		fRefreshContributions = null;
	}

	@Override
	protected void createActions() {
		super.createActions();

//		IAction action = new TextOperationAction(Messages
//				.getBundleForConstructedKeys(),
//				"OpenHierarchy.", this, JavaSourceViewer.SHOW_HIERARCHY, true); //$NON-NLS-1$
//		action.setActionDefinitionId(X10EditorActionContributer.OPEN_HIERARCHY);
//		setAction(X10EditorActionContributer.OPEN_HIERARCHY, action);
		// PlatformUI.getWorkbench().getHelpSystem().setHelp(action,
		// IJavaHelpContextIds.OPEN_HIERARCHY_ACTION);
	}

	@Override
	protected void editorContextMenuAboutToShow(IMenuManager menu) {
		super.editorContextMenuAboutToShow(menu);

		// Quick views
		IAction action = getAction(X10EditorActionContributer.OPEN_HIERARCHY);
		menu.appendToGroup(IContextMenuConstants.GROUP_OPEN, action);
	}

	/*
	 * @see org.eclipse.ui.texteditor.AbstractDecoratedTextEditor#initializeKeyBindingScopes()
	 */
	protected void initializeKeyBindingScopes() {
		setKeyBindingScopes(new String[] { "x10dt.ui.x10EditorScope" });  //$NON-NLS-1$
	}
}
