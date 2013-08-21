package x10dt.ui.views;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import x10dt.core.X10DTCorePlugin;
import x10dt.ui.X10DTUIPlugin;
import x10dt.ui.typeHierarchy.X10PluginImages;


public class ProjectDecorator extends LabelProvider implements ILabelDecorator {
	public ProjectDecorator() {
		super();
	}

	public Image decorateImage(Image baseImage, Object object) {
		IResource objectResource = (IResource) object;
		if (objectResource == null) {
			return null;
		}
		if (objectResource.getType() != IResource.PROJECT) {
			// Only projects are decorated
			return null;
		}
		IProject project = (IProject) objectResource;
		boolean isJava = false;
		try {
			if (project.hasNature(X10DTCorePlugin.X10_CPP_PRJ_NATURE_ID)) {
				isJava = false;
			} else if (project.hasNature(X10DTCorePlugin.X10_PRJ_JAVA_NATURE_ID)){
				isJava = true;
			} else {
				return null;
			}
		} catch(CoreException e){
			X10DTUIPlugin.getInstance().getLog().log(e.getStatus());
		}
		// Overlay custom image over base image
		ImageDescriptor[] desc = new ImageDescriptor[5];
		desc[1] = X10PluginImages.DESC_OVR_X10; //This is the X10 project decoration.
		if (isJava) desc[0] = X10PluginImages.DESC_OVR_JAVA;
		DecorationOverlayIcon overlayIcon = new DecorationOverlayIcon(baseImage, desc);
		return overlayIcon.createImage();

	}

	public String decorateText(String label, Object object) {
		return null;
	}
}