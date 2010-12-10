package x10dt.search.ui.search;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.imp.editor.StorageLabelProvider;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ide.IDE;

import x10dt.search.core.elements.IMemberInfo;
import x10dt.search.ui.typeHierarchy.SearchUtils;

/**
 * This class provides labels for X10 search matches.
 * It extends StorageLabelProvider from imp. The getText() method provides path information for each element.
 * @author mvaziri
 *
 */
public class X10SearchLabelProvider extends StorageLabelProvider {
	
	public String getText(Object element) {
		if (element instanceof IMemberInfo){
			IPath base = ResourcesPlugin.getWorkspace().getRoot().getLocation();
			String path = SearchUtils.getPath((IMemberInfo)element).makeRelativeTo(base).toOSString();
			return ((IMemberInfo) element).getName() + "     (" + path + ")";
		}
		return super.getText(element);
	}
	
	public Image getImage(Object element) {
//		if (element instanceof IMemberInfo){
//			return super.getImage(Utils.getIFile((IMemberInfo) element));
//		}
		return JavaPlugin.getImageDescriptorRegistry().get(JavaPluginImages.DESC_FIELD_PRIVATE);
	}

	
}
