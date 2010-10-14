package x10dt.search.ui.typeHierarchy;
/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/


import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jdt.internal.ui.viewsupport.JavaElementImageProvider;
import org.eclipse.jdt.ui.JavaElementImageDescriptor;
import org.eclipse.jface.preference.JFacePreferences;
import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;

import x10dt.search.core.engine.ITypeHierarchy;
import x10dt.search.core.engine.ITypeInfo;
import x10dt.search.core.pdb.X10FlagsEncoder.X10;
import x10dt.search.ui.UISearchPlugin;

/**
 * Label provider for the hierarchy viewers. Types in the hierarchy that are not belonging to the
 * input scope are rendered differently.
  */
public class HierarchyLabelProvider extends AppearanceAwareLabelProvider {

	private static class FocusDescriptor extends CompositeImageDescriptor {
		private ImageDescriptor fBase;
		public FocusDescriptor(ImageDescriptor base) {
			fBase= base;
		}
		protected void drawCompositeImage(int width, int height) {
			drawImage(getImageData(fBase), 0, 0);
			drawImage(getImageData(X10LabelProvider.DESC_OVR_FOCUS), 0, 0);
		}

		private ImageData getImageData(ImageDescriptor descriptor) {
			ImageData data= descriptor.getImageData(); // see bug 51965: getImageData can return null
			if (data == null) {
				data= DEFAULT_IMAGE_DATA;
				UISearchPlugin.log(new Exception("Image data not available: " + descriptor.toString())); //$NON-NLS-1$
			}
			return data;
		}

		protected Point getSize() {
			return JavaElementImageProvider.BIG_SIZE;
		}
		public int hashCode() {
			return fBase.hashCode();
		}
		public boolean equals(Object object) {
			return object != null && FocusDescriptor.class.equals(object.getClass()) && ((FocusDescriptor)object).fBase.equals(fBase);
		}
	}

	private Color fSpecialColor;

	private ViewerFilter fFilter;

	private TypeHierarchyLifeCycle fHierarchy;

	public HierarchyLabelProvider(TypeHierarchyLifeCycle lifeCycle) {
		super(DEFAULT_TEXTFLAGS,  DEFAULT_IMAGEFLAGS);

		fHierarchy= lifeCycle;
		fFilter= null;
	}

	/**
	 * @return Returns the filter.
	 */
	public ViewerFilter getFilter() {
		return fFilter;
	}

	/**
	 * @param filter The filter to set.
	 */
	public void setFilter(ViewerFilter filter) {
		fFilter= filter;
	}

	protected boolean isDifferentScope(ITypeInfo type) {
		if (fFilter != null && !fFilter.select(null, null, type)) {
			return true;
		}

		ITypeInfo input= fHierarchy.getInputElement();
		return false;
//		if (input == null || input.getElementType() == ISourceEntity.TYPE) {
//			return false;
//		}

//		ISourceEntity parent= type.g
//		if (input.getElementType() == ISourceEntity.PACKAGE_FRAGMENT) {
//			if (parent == null || parent.getElementName().equals(input.getElementName())) {
//				return false;
//			}
//		} else
//		if (input.equals(parent)) {
//			return false;
//		}
//		return true;
	}

	/* (non-Javadoc)
	 * @see ILabelProvider#getImage
	 */
	public Image getImage(Object element) {
		Image result= null;
		if (element instanceof ITypeInfo) {
			ImageDescriptor desc= getTypeImageDescriptor((ITypeInfo) element);
			if (desc != null) {
				if (element.equals(fHierarchy.getInputElement())) {
					desc= new FocusDescriptor(desc);
				}
				result= desc.createImage();
			}
		} else {
			result= fImageLabelProvider.getImageLabel(element, evaluateImageFlags(element));
		}
		return decorateImage(result, element);
	}
	
	

	private ImageDescriptor getTypeImageDescriptor(ITypeInfo type) {
		ITypeHierarchy hierarchy= fHierarchy.getHierarchy();
		if (hierarchy == null) {
			return new JavaElementImageDescriptor(JavaPluginImages.DESC_OBJS_CLASS, 0, JavaElementImageProvider.BIG_SIZE);
		}

		int flags= type.getX10FlagsCode();
		if (flags == -1) {
			return new JavaElementImageDescriptor(JavaPluginImages.DESC_OBJS_CLASS, 0, JavaElementImageProvider.BIG_SIZE);
		}

		boolean isInterface= SearchUtils.hasFlag(X10.INTERFACE, flags);
		ITypeInfo declaringType= null;/*.getDeclaringType();*/
		boolean isInner= declaringType != null;
		boolean isInInterfaceOrAnnotation= false;
		if (isInner) {
			int declaringTypeFlags= declaringType.getX10FlagsCode();
			if (declaringTypeFlags != -1) {
				isInInterfaceOrAnnotation= SearchUtils.hasFlag(X10.INTERFACE, declaringTypeFlags);
			} else {
				// declaring type is not in hierarchy, so we have to pay the price for resolving here
				try {
					isInInterfaceOrAnnotation= SearchUtils.hasFlag(X10.INTERFACE, declaringTypeFlags);
				} catch (Exception e) {
				}
			}
		}

		ImageDescriptor desc= JavaElementImageProvider.getTypeImageDescriptor(isInner, isInInterfaceOrAnnotation, SearchUtils.getJavaFlags(flags), isDifferentScope(type));

		int adornmentFlags= 0;
		if (SearchUtils.hasFlag(X10.FINAL, flags)) {
			adornmentFlags |= JavaElementImageDescriptor.FINAL;
		}
		if (SearchUtils.hasFlag(X10.ABSTRACT, flags) && !isInterface) {
			adornmentFlags |= JavaElementImageDescriptor.ABSTRACT;
		}
		if (SearchUtils.hasFlag(X10.STATIC, flags)) {
			adornmentFlags |= JavaElementImageDescriptor.STATIC;
		}
//		if (Flags.isDeprecated(flags)) {
//			adornmentFlags |= JavaElementImageDescriptor.DEPRECATED;
//		}

		return new JavaElementImageDescriptor(desc, adornmentFlags, JavaElementImageProvider.BIG_SIZE);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
	 */
	public Color getForeground(Object element) {
//		if (element instanceof IMethod) {
//			if (fSpecialColor == null) {
//				fSpecialColor= Display.getCurrent().getSystemColor(SWT.COLOR_DARK_BLUE);
//			}
//			return fSpecialColor;
//		} else 
		if (element instanceof ITypeInfo && isDifferentScope((ITypeInfo) element)) {
			return JFaceResources.getColorRegistry().get(JFacePreferences.QUALIFIER_COLOR);
		}
		return null;
	}



}
