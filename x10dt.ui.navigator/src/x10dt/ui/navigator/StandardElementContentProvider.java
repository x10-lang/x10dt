/*******************************************************************************
 * Copyright (c) 2000, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package x10dt.ui.navigator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.imp.language.LanguageRegistry;
import org.eclipse.imp.model.ISourceFolder;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.model.ISourceRoot;
import org.eclipse.imp.model.ModelFactory.ModelException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;


/**
 * A base content provider for Java elements. It provides access to the
 * Java element hierarchy without listening to changes in the Java model.
 * If updating the presentation on Java model change is required than
 * clients have to subclass, listen to Java model changes and have to update
 * the UI using corresponding methods provided by the JFace viewers or their
 * own UI presentation.
 * <p>
 * The following Java element hierarchy is surfaced by this content provider:
 * <p>
 * <pre>
Java model (<code>IJavaModel</code>)
   Java project (<code>ISourceProject</code>)
      package fragment root (<code>ISourceFolderRoot</code>)
         package fragment (<code>ISourceFolder</code>)
            compilation unit (<code>ICompilationUnit</code>)
            binary class file (<code>IClassFile</code>)
 * </pre>
 * </p>
 * <p>
 * Note that when the entire Java project is declared to be package fragment root,
 * the corresponding package fragment root element that normally appears between the
 * Java project and the package fragments is automatically filtered out.
 * </p>
 *
 * @since 2.0
 */
public class StandardElementContentProvider implements ITreeContentProvider/*, IWorkingCopyProvider*/ {

	protected static final Object[] NO_CHILDREN= new Object[0];
	protected boolean fProvideMembers;
	protected boolean fProvideWorkingCopy;

	/**
	 * Creates a new content provider. The content provider does not
	 * provide members of compilation units or class files.
	 */
	public StandardElementContentProvider() {
		this(false);
	}

	/**
	 * @param provideMembers if <code>true</code> members below compilation units
	 * @param provideWorkingCopy if <code>true</code> working copies are provided
	 * @deprecated Use {@link #StandardElementContentProvider(boolean)} instead.
	 * Since 3.0 compilation unit children are always provided as working copies. The Java Model
	 * does not support the 'original' mode anymore.
	 */
	public StandardElementContentProvider(boolean provideMembers, boolean provideWorkingCopy) {
		this(provideMembers);
	}


	/**
	 * Creates a new <code>StandardElementContentProvider</code>.
	 *
	 * @param provideMembers if <code>true</code> members below compilation units
	 * and class files are provided.
	 */
	public StandardElementContentProvider(boolean provideMembers) {
		fProvideMembers= provideMembers;
		fProvideWorkingCopy= provideMembers;
	}

	/**
	 * Returns whether members are provided when asking
	 * for a compilation units or class file for its children.
	 *
	 * @return <code>true</code> if the content provider provides members;
	 * otherwise <code>false</code> is returned
	 */
	public boolean getProvideMembers() {
		return fProvideMembers;
	}

	/**
	 * Sets whether the content provider is supposed to return members
	 * when asking a compilation unit or class file for its children.
	 *
	 * @param b if <code>true</code> then members are provided.
	 * If <code>false</code> compilation units and class files are the
	 * leaves provided by this content provider.
	 */
	public void setProvideMembers(boolean b) {
		//hello
		fProvideMembers= b;
	}

	/**
	 * @return returns <code>true</code> if working copies are provided
	 * @deprecated Since 3.0 compilation unit children are always provided as working copies. The Java model
	 * does not support the 'original' mode anymore.
	 */
	public boolean getProvideWorkingCopy() {
		return fProvideWorkingCopy;
	}

	/**
	 * @param b specifies if working copies should be provided
	 * @deprecated Since 3.0 compilation unit children are always provided from the working copy. The Java model
	 * offers a unified world and does not support the 'original' mode anymore.
	 */
	public void setProvideWorkingCopy(boolean b) {
		fProvideWorkingCopy= b;
	}

	/* (non-Javadoc)
	 * @see IWorkingCopyProvider#providesWorkingCopies()
	 */
	public boolean providesWorkingCopies() {
		return getProvideWorkingCopy();
	}

	/* (non-Javadoc)
	 * Method declared on IStructuredContentProvider.
	 */
	public Object[] getElements(Object parent) {
		return getChildren(parent);
	}

	/* (non-Javadoc)
	 * Method declared on IContentProvider.
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	/* (non-Javadoc)
	 * Method declared on IContentProvider.
	 */
	public void dispose() {
	}

	/* (non-Javadoc)
	 * Method declared on ITreeContentProvider.
	 */
	public Object[] getChildren(Object element) {
		if (!exists(element))
			return NO_CHILDREN;

		try {
			
//			if (element instanceof IJavaModel)
//				return getJavaProjects((IJavaModel)element);

			if (element instanceof ISourceProject)
				return getPackageFragmentRoots((ISourceProject)element);

			if (element instanceof ISourceRoot)
				return getPackageFragmentRootContent((ISourceRoot)element);

			if (element instanceof ISourceFolder)
				return getPackageContent((ISourceFolder)element);

			if (element instanceof IFolder)
				return getFolderContent((IFolder)element);

//			if (element instanceof IJarEntryResource) {
//				return ((IJarEntryResource) element).getChildren();
//			}

//			if (getProvideMembers() && element instanceof ISourceReference && element instanceof IParent) {
//				return ((IParent)element).getChildren();
//			}
			
		} catch (Exception e) {
			return NO_CHILDREN;
		}
		return NO_CHILDREN;
	}

	/* (non-Javadoc)
	 * @see ITreeContentProvider
	 */
	public boolean hasChildren(Object element) {
		if (getProvideMembers()) {
			// assume CUs and class files are never empty
//			if (element instanceof ICompilationUnit ||
//				element instanceof IClassFile) {
//				return true;
//			}
		} else {
			// don't allow to drill down into a compilation unit or class file
			if (
//					element instanceof ICompilationUnit ||
//				element instanceof IClassFile ||
				element instanceof IFile)
			return false;
		}

		if (element instanceof ISourceProject) {
			ISourceProject jp= (ISourceProject)element;
			if (!jp.getProject().getRawProject().isOpen()) {
				return false;
			}
		}

//		if (element instanceof IParent) {
//			try {
//				// when we have Java children return true, else we fetch all the children
//				if (((IParent)element).hasChildren())
//					return true;
//			} catch(JavaModelException e) {
//				return true;
//			}
//		}
		Object[] children= getChildren(element);
		return (children != null) && children.length > 0;
	}

	/* (non-Javadoc)
	 * Method declared on ITreeContentProvider.
	 */
	public Object getParent(Object element) {
		if (!exists(element))
			return null;
		return internalGetParent(element);
	}

	/**
	 * Evaluates all children of a given {@link ISourceFolderRoot}. Clients can override this method.
	 * @param root The root to evaluate the children for.
	 * @return The children of the root
	 * @exception JavaModelException if the package fragment root does not exist or if an
	 *      exception occurs while accessing its corresponding resource
	 *
	 * @since 3.3
	 */
	protected Object[] getPackageFragmentRootContent(ISourceRoot root) throws ModelException {
		ISourceFolder[] fragments= root.getChildren();
		if (isProjectPackageFragmentRoot(root)) {
			return fragments;
		}
//		Object[] nonJavaResources= root.getNonJavaResources();
//		if (nonJavaResources == null)
			return fragments;
//		return concatenate(fragments, nonJavaResources);
	}

	/**
	 * Evaluates all children of a given {@link ISourceProject}. Clients can override this method.
	 * @param project The Java project to evaluate the children for.
	 * @return The children of the project. Typically these are package fragment roots but can also be other elements.
	 * @exception JavaModelException if the Java project does not exist or if an
	 *      exception occurs while accessing its corresponding resource
	 */
	protected Object[] getPackageFragmentRoots(ISourceProject project) throws ModelException {
		if (!project.getProject().getRawProject().isOpen())
			return NO_CHILDREN;

		List<ISourceRoot> roots= project.getSourceRoots(LanguageRegistry.findLanguage("X10"));
		List list= new ArrayList(roots.size());
		// filter out package fragments that correspond to projects and
		// replace them with the package fragments directly
		for (ISourceRoot root : roots) {
			if (isProjectPackageFragmentRoot(root)) {
				Object[] fragments= getPackageFragmentRootContent(root);
				for (int j= 0; j < fragments.length; j++) {
					list.add(fragments[j]);
				}
			} else {
				list.add(root);
			}
		}
//		Object[] resources= project.getNonJavaResources();
//		for (int i= 0; i < resources.length; i++) {
//			list.add(resources[i]);
//		}
		return list.toArray();
	}

	/**
	 * Evaluates all Java projects of a given {@link IJavaModel}. Clients can override this method.
	 *
	 * @param jm the Java model
	 * @return the projects
	 * @throws JavaModelException thrown if accessing the model failed
	 */
//	protected Object[] getJavaProjects(IJavaModel jm) throws JavaModelException {
//		return jm.getJavaProjects();
//	}

	/**
	 * Evaluates all children of a given {@link ISourceFolder}. Clients can override this method.
	 * @param fragment The fragment to evaluate the children for.
	 * @return The children of the given package fragment.
	 * @exception JavaModelException if the package fragment does not exist or if an
	 *      exception occurs while accessing its corresponding resource
	 *
	 * @since 3.3
	 */
	protected Object[] getPackageContent(ISourceFolder fragment) throws ModelException {
//		if (fragment.getKind() == ISourceRoot.K_SOURCE) {
//			return concatenate(fragment.getCompilationUnits(), fragment.getNonJavaResources());
//		}
//		return concatenate(fragment.getClassFiles(), fragment.getNonJavaResources());
		return fragment.getChildren();
	}

	/**
	 * Evaluates all children of a given {@link IFolder}. Clients can override this method.
	 * @param folder The folder to evaluate the children for.
	 * @return The children of the given folder.
	 * @exception CoreException if the folder does not exist.
	 *
	 * @since 3.3
	 */
	protected Object[] getFolderContent(IFolder folder) throws CoreException, ModelException {
		IResource[] members= folder.members();
//		ISourceProject javaProject= JavaCore.create(folder.getProject());
//		if (javaProject == null || !javaProject.exists())
//			return members;
//		boolean isFolderOnClasspath = javaProject.isOnClasspath(folder);
		List nonJavaResources= new ArrayList();
		// Can be on classpath but as a member of non-java resource folder
		for (int i= 0; i < members.length; i++) {
			IResource member= members[i];
			// A resource can also be a java element
			// in the case of exclusion and inclusion filters.
			// We therefore exclude Java elements from the list
			// of non-Java resources.
//			if (isFolderOnClasspath) {
//				if (javaProject.findPackageFragmentRoot(member.getFullPath()) == null) {
//					nonJavaResources.add(member);
//				}
//			} else if (!javaProject.isOnClasspath(member)) {
//				nonJavaResources.add(member);
//			} else {
//				IJavaElement element= JavaCore.create(member, javaProject);
//				if (element instanceof ISourceFolderRoot
//						&& javaProject.equals(element.getJavaProject())
//						&& ((ISourceFolderRoot)element).getKind() != ISourceFolderRoot.K_SOURCE) {
//					// don't skip libs and class folders on the classpath of their project
//					nonJavaResources.add(member);
//				}
//			}
		}
		return nonJavaResources.toArray();
	}
	
	/**
	 * Tests if the a Java element delta contains a class path change
	 *
	 * @param delta the Java element delta
	 * @return returns <code>true</code> if the delta contains a class path change
	 */
//	protected boolean isClassPathChange(IJavaElementDelta delta) {
//
//		// need to test the flags only for package fragment roots
//		if (delta.getElement().getElementType() != IJavaElement.PACKAGE_FRAGMENT_ROOT)
//			return false;
//
//		int flags= delta.getFlags();
//		return (delta.getKind() == IJavaElementDelta.CHANGED &&
//			((flags & IJavaElementDelta.F_ADDED_TO_CLASSPATH) != 0) ||
//			 ((flags & IJavaElementDelta.F_REMOVED_FROM_CLASSPATH) != 0) ||
//			 ((flags & IJavaElementDelta.F_REORDER) != 0));
//	}

	/**
	 * Note: This method is for internal use only. Clients should not call this method.
	 *
	 * @param root the package fragment root
	 * @return returns the element representing the root.
	 *
	 * @noreference This method is not intended to be referenced by clients.
	 */
//	protected Object skipProjectPackageFragmentRoot(ISourceFolderRoot root) {
//		if (isProjectPackageFragmentRoot(root))
//			return root.getParent();
//		return root;
//	}

	/**
	 * Tests if the given element is a empty package fragment.
	 *
	 * @param element the element to test
	 * @return returns <code>true</code> if the package fragment is empty
	 * @throws JavaModelException thrown if accessing the element failed
	 */
//	protected boolean isPackageFragmentEmpty(IJavaElement element) throws JavaModelException {
//		if (element instanceof ISourceFolder) {
//			ISourceFolder fragment= (ISourceFolder)element;
//			if (fragment.exists() && !(fragment.hasChildren() || fragment.getNonJavaResources().length > 0) && fragment.hasSubpackages())
//				return true;
//		}
//		return false;
//	}

	/**
	 * Tests if the package fragment root is located on the project.
	 *
	 * @param root the package fragment root
	 * @return returns <code>true</code> if the package fragment root is the located on the project
	 */
	protected boolean isProjectPackageFragmentRoot(ISourceRoot root) {
		ISourceProject javaProject= root.getProject();
		return javaProject != null && javaProject.getPath().equals(root.getPath());
	}

	/**
	 * Note: This method is for internal use only. Clients should not call this method.
	 *
	 * @param element the element to test
	 * @return returns <code>true</code> if the element exists
	 *
	 * @noreference This method is not intended to be referenced by clients.
	 */
	protected boolean exists(Object element) {
		if (element == null) {
			return false;
		}
		if (element instanceof IResource) {
			return ((IResource)element).exists();
		}
//		if (element instanceof IJavaElement) {
//			return ((IJavaElement)element).exists();
//		}
		return true;
	}

	/**
	 * Note: This method is for internal use only. Clients should not call this method.
	 *
	 * @param element the element
	 * @return the parent of the element
	 *
	 * @noreference This method is not intended to be referenced by clients.
	 */
	protected Object internalGetParent(Object element) {

		// try to map resources to the containing package fragment
		if (element instanceof IResource) {
			IResource parent= ((IResource)element).getParent();
//			IJavaElement jParent= JavaCore.create(parent);
//			// http://bugs.eclipse.org/bugs/show_bug.cgi?id=31374
//			if (jParent != null && jParent.exists())
//				return jParent;
			return parent;
		} 
//		else if (element instanceof IJavaElement) {
//			IJavaElement parent= ((IJavaElement) element).getParent();
//			// for package fragments that are contained in a project package fragment
//			// we have to skip the package fragment root as the parent.
//			if (element instanceof ISourceFolder) {
//				return skipProjectPackageFragmentRoot((ISourceFolderRoot) parent);
//			}
//			return parent;
//		} else if (element instanceof IJarEntryResource) {
//			return ((IJarEntryResource) element).getParent();
//		}
		return null;
	}

	/**
	 * Utility method to concatenate two arrays.
	 *
	 * @param a1 the first array
	 * @param a2 the second array
	 * @return the concatenated array
	 */
	protected static Object[] concatenate(Object[] a1, Object[] a2) {
		int a1Len= a1.length;
		int a2Len= a2.length;
		if (a1Len == 0) return a2;
		if (a2Len == 0) return a1;
		Object[] res= new Object[a1Len + a2Len];
		System.arraycopy(a1, 0, res, 0, a1Len);
		System.arraycopy(a2, 0, res, a1Len, a2Len);
		return res;
	}


}
