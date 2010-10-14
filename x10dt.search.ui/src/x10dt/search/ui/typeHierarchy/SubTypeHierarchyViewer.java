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


import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.imp.editor.UniversalEditor;
import org.eclipse.swt.widgets.Composite;

import x10dt.search.core.engine.ITypeHierarchy;
import x10dt.search.core.engine.ITypeInfo;
import x10dt.search.core.engine.X10SearchEngine;


/**
 * A viewer including the content provider for the subtype hierarchy.
 * Used by the TypeHierarchyViewPart which has to provide a TypeHierarchyLifeCycle
 * on construction (shared type hierarchy)
 */
public class SubTypeHierarchyViewer extends TypeHierarchyViewer {

	public SubTypeHierarchyViewer(Composite parent, TypeHierarchyLifeCycle lifeCycle) {
		super(parent, new SubTypeHierarchyContentProvider(lifeCycle), lifeCycle);
	}

	/*
	 * @see TypeHierarchyViewer#getTitle
	 */
	public String getTitle() {
		if (isMethodFiltering()) {
			return TypeHierarchyMessages.SubTypeHierarchyViewer_filtered_title;
		} else {
			return TypeHierarchyMessages.SubTypeHierarchyViewer_title;
		}
	}

	/*
	 * @see TypeHierarchyViewer#updateContent
	 */
	public void updateContent(boolean expand) {
		getTree().setRedraw(false);
		refresh();

		if (expand) {
			int expandLevel= 2;
			if (isMethodFiltering()) {
				expandLevel++;
			}
			expandToLevel(expandLevel);
		}
		getTree().setRedraw(true);
	}

	/**
	 * Content provider for the subtype hierarchy
	 */
	public static class SubTypeHierarchyContentProvider extends TypeHierarchyContentProvider {
		public SubTypeHierarchyContentProvider(TypeHierarchyLifeCycle lifeCycle) {
			super(lifeCycle);
		}

		protected final void getTypesInHierarchy(ITypeInfo type, List res) {
			ITypeHierarchy hierarchy= getHierarchy();
			if (hierarchy != null) {
				ITypeInfo[] types= hierarchy.getSubTypes(type.getName());
				if (isObject(type)) {
					for (int i= 0; i < types.length; i++) {
						ITypeInfo curr = null;
						try {
							curr = types[i];
						} catch (Exception e) {
							continue;
						}
						if (!isAnonymousFromInterface(curr)) {
							res.add(curr);
						}
					}
				} else {
					for (int i= 0; i < types.length; i++) {
						res.add(types[i]);
					}
				}
			}

		}

		protected ITypeInfo getParentType(ITypeInfo type) {
			try {
				ITypeHierarchy hierarchy= getHierarchy();
				if (hierarchy != null) {
					return hierarchy.getSuperClass(type.getName());
					// dont handle interfaces
				}
			} catch (Exception e) {
				// fall through
			}
			return null;
		}

}



}
