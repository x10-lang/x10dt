package x10dt.search.ui.search;


import org.eclipse.imp.editor.EditorUtility;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.search.ui.text.AbstractTextSearchViewPage;
import org.eclipse.search.ui.text.Match;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;



public class X10SearchResultPage extends AbstractTextSearchViewPage {

	// --- The content provider can be configured to present results as a table or a tree.
	private X10SearchContentProvider fContentProvider;
	
	// --- Maximum number of elements that will be shown in the result page.
	private static final int DEFAULT_ELEMENT_LIMIT = 1000;
	
	
	public X10SearchResultPage(){
		// --- We support a flat layout for now, a tree configuration will be added when we have support for X10 elements.
		super(AbstractTextSearchViewPage.FLAG_LAYOUT_FLAT);
		setElementLimit(new Integer(DEFAULT_ELEMENT_LIMIT));
	}
	
	
	@Override
	protected void elementsChanged(Object[] objects) {
		if (fContentProvider != null)
			fContentProvider.elementsChanged(objects);
	}

	@Override
	protected void clear() {
		if (fContentProvider != null)
			fContentProvider.clear();
	}

	@Override
	protected void configureTreeViewer(TreeViewer viewer) {
		
	}

	@Override
	protected void configureTableViewer(TableViewer viewer) {
		viewer.setUseHashlookup(true);
		viewer.setLabelProvider(new X10SearchLabelProvider());
		fContentProvider=new X10SearchTableContentProvider(this);
		viewer.setContentProvider(fContentProvider);
	}
	
	protected StructuredViewer getViewer() {
		// override so that it's visible in the package.
		return super.getViewer();
	}
	
	public void showMatch(Match match, int offset, int length, boolean activate) throws PartInitException {
		if (activate){
			IEditorPart editor = EditorUtility.openInEditor(match.getElement());
			EditorUtility.revealInEditor(editor, match.getOffset(), match.getLength());
		}
	}
	

}