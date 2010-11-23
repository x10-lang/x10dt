package x10dt.search.ui.search;

import org.eclipse.core.resources.IStorage;
import org.eclipse.imp.editor.StorageLabelProvider;

/**
 * This class provides labels for X10 search matches.
 * It extends StorageLabelProvider from imp. The getText() method provides path information for each element.
 * @author mvaziri
 *
 */
public class X10SearchLabelProvider extends StorageLabelProvider {
	
	public String getText(Object element) {
		if (element instanceof IStorage){
			String path = ((IStorage)element).getFullPath().toOSString();
			return super.getText(element) + " (" + path + ")";
		}
		return super.getText(element);
	}
	
}
