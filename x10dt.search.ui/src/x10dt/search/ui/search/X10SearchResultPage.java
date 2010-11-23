package x10dt.search.ui.search;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.search.ui.text.AbstractTextSearchViewPage;



public class X10SearchResultPage extends AbstractTextSearchViewPage {

	// --- The content provider can be configured to present results as a table or a tree.
	private X10SearchContentProvider fContentProvider;
	
	// --- Maximum number of elements that will be shown in the result page.
	private static final int DEFAULT_ELEMENT_LIMIT = 1000;
	
	public X10SearchResultPage(){
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
		//fSortingLabelProvider= new SortingLabelProvider(this);
		//viewer.setLabelProvider(new DecoratingJavaLabelProvider(fSortingLabelProvider, false));
		fContentProvider=new X10SearchTableContentProvider(this);
		viewer.setContentProvider(fContentProvider);
		//viewer.setComparator(new DecoratorIgnoringViewerSorter(fSortingLabelProvider));
		//setSortOrder(fCurrentSortO
	}
	
	protected StructuredViewer getViewer() {
		// override so that it's visible in the package.
		return super.getViewer();
	}
	
	public void setElementLimit(Integer elementLimit) {
		super.setElementLimit(elementLimit);
	}

}