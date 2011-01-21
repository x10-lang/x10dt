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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.imp.language.LanguageRegistry;
import org.eclipse.imp.model.IPathContainer;
import org.eclipse.imp.model.IPathEntry;
import org.eclipse.imp.model.IPathEntry.PathEntryType;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.model.ISourceRoot;
import org.eclipse.imp.model.ModelFactory;
import org.eclipse.imp.model.PathContainerRegistry;
import org.eclipse.imp.utils.BuildPathUtils;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.search.internal.ui.text.BasicElementLabels;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.model.IWorkbenchAdapter;

import x10dt.search.ui.typeHierarchy.X10PluginImages;
import x10dt.ui.navigator.UINavigatorPlugin;


/**
 * Representation of class path containers in Java UI.
 */
public class ClassPathContainer extends PackageFragmentRootContainer {

	private IPathEntry fClassPathEntry;
	private IPathContainer fContainer;

	public static class RequiredProjectWrapper implements IAdaptable, IWorkbenchAdapter {

		private final ClassPathContainer fParent;
		private final ISourceProject fProject;

		public RequiredProjectWrapper(ClassPathContainer parent, ISourceProject project) {
			fParent= parent;
			fProject= project;
		}

		public ISourceProject getProject() {
			return fProject;
		}

		public ClassPathContainer getParentClassPathContainer() {
			return fParent;
		}

		public Object getAdapter(Class adapter) {
			if (adapter == IWorkbenchAdapter.class)
				return this;
			return null;
		}

		public Object[] getChildren(Object o) {
			return new Object[0];
		}

		public ImageDescriptor getImageDescriptor(Object object) {
			return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(IDE.SharedImages.IMG_OBJ_PROJECT);
		}

		public String getLabel(Object o) {
			return fProject.getName();
		}

		public Object getParent(Object o) {
			return fParent;
		}
	}

	public ClassPathContainer(ISourceProject parent, IPathEntry entry) {
		super(parent);
		fClassPathEntry= entry;
		fContainer= PathContainerRegistry.getInstance().getClasspathContainer(entry.getRawPath(), parent);
	}

	public boolean equals(Object obj) {
		if (obj instanceof ClassPathContainer) {
			ClassPathContainer other = (ClassPathContainer)obj;
			if (getJavaProject().equals(other.getJavaProject()) &&
				fClassPathEntry.equals(other.fClassPathEntry)) {
				return true;
			}

		}
		return false;
	}

	public int hashCode() {
		return getJavaProject().hashCode()*17+fClassPathEntry.hashCode();
	}

	public List<ISourceRoot> getPackageFragmentRoots() {
		return BuildPathUtils.getSourceRoots(getJavaProject(), LanguageRegistry.findLanguage("X10"), fClassPathEntry);
	}

	public Object[] getChildren() {
		List<Object> list = new ArrayList<Object>();
		list.addAll(getPackageFragmentRoots());
		
		if (fContainer != null) {
			List<IPathEntry> classpathEntries= fContainer.getPathEntries();
			if (classpathEntries.isEmpty()) {
				// invalid implementation of a classpath container
				UINavigatorPlugin.log(new IllegalArgumentException("Invalid classpath container implementation: getClasspathEntries() returns null. " + fContainer.getPath())); //$NON-NLS-1$
			} else {
				IWorkspaceRoot root= ResourcesPlugin.getWorkspace().getRoot();
				for (IPathEntry entry : classpathEntries) {
					if (entry.getEntryType() == PathEntryType.PROJECT) {
						IResource resource= root.findMember(entry.getRawPath());
						if (resource instanceof IProject)
							list.add(new RequiredProjectWrapper(this, ModelFactory.getProject((IProject) resource)));
					}
				}
			}
		}
		return (Object[]) list.toArray(new Object[list.size()]);
	}

	public ImageDescriptor getImageDescriptor() {
		return X10PluginImages.DESC_OBJS_LIBRARY;
	}

	public String getLabel() {
		if (fContainer != null)
			return fContainer.getDescription();

		IPath path= fClassPathEntry.getRawPath();
		String containerId= path.segment(0);
		IPathContainer container = PathContainerRegistry.getInstance().getClasspathContainer(new Path(containerId), getJavaProject());
		if (container != null) {
			String description= container.getDescription();
			return MessageFormat.format(PackagesMessages.ClassPathContainer_unbound_label, description);
		}
		return MessageFormat.format(PackagesMessages.ClassPathContainer_unknown_label, BasicElementLabels.getPathLabel(path, false));
	}

	public IPathEntry getClasspathEntry() {
		return fClassPathEntry;
	}
}
