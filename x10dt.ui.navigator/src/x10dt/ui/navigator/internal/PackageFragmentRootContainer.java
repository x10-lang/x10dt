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
package x10dt.ui.navigator.internal;

import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.model.ISourceRoot;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;

public abstract class PackageFragmentRootContainer implements IAdaptable {

	private static WorkbenchAdapterImpl fgAdapterInstance= new WorkbenchAdapterImpl();

	private static class WorkbenchAdapterImpl implements IWorkbenchAdapter {

		/* (non-Javadoc)
		 * @see org.eclipse.ui.model.IWorkbenchAdapter#getChildren(java.lang.Object)
		 */
		public Object[] getChildren(Object o) {
			if (o instanceof PackageFragmentRootContainer)
				return ((PackageFragmentRootContainer) o).getChildren();
			return new Object[0];
		}

		/* (non-Javadoc)
		 * @see org.eclipse.ui.model.IWorkbenchAdapter#getImageDescriptor(java.lang.Object)
		 */
		public ImageDescriptor getImageDescriptor(Object o) {
			if (o instanceof PackageFragmentRootContainer)
				return ((PackageFragmentRootContainer) o).getImageDescriptor();
			return null;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.ui.model.IWorkbenchAdapter#getLabel(java.lang.Object)
		 */
		public String getLabel(Object o) {
			if (o instanceof PackageFragmentRootContainer)
				return ((PackageFragmentRootContainer) o).getLabel();
			return new String();
		}

		/* (non-Javadoc)
		 * @see org.eclipse.ui.model.IWorkbenchAdapter#getParent(java.lang.Object)
		 */
		public Object getParent(Object o) {
			if (o instanceof PackageFragmentRootContainer)
				return ((PackageFragmentRootContainer) o).getJavaProject();
			return null;
		}
	}

	private ISourceProject fProject;

	public PackageFragmentRootContainer(ISourceProject project) {
		Assert.isNotNull(project);
		fProject= project;
	}

	public Object getAdapter(Class adapter) {
		if (adapter == IWorkbenchAdapter.class)
			return fgAdapterInstance;
		return null;
	}

	public abstract Object[] getChildren();

	public abstract List<ISourceRoot> getPackageFragmentRoots();

	public abstract String getLabel();

	public abstract ImageDescriptor getImageDescriptor();

	public ISourceProject getJavaProject() {
		return fProject;
	}
}
