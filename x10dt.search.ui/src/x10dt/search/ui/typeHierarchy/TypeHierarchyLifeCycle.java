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


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.imp.editor.EditorUtility;
import org.eclipse.imp.editor.UniversalEditor;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;

import x10dt.search.core.engine.ITypeHierarchy;
import x10dt.search.core.engine.ITypeInfo;
import x10dt.search.core.engine.X10SearchEngine;


/**
 * Manages a type hierarchy, to keep it refreshed, and to allow it to be shared.
 */
public class TypeHierarchyLifeCycle implements ITypeHierarchyChangedListener { //, IElementChangedListener {

	private boolean fHierarchyRefreshNeeded;
	private ITypeHierarchy fHierarchy;
	private ITypeInfo fInputElement;
	private boolean fIsSuperTypesOnly;
	private UniversalEditor editor;

	private List fChangeListeners;

	public TypeHierarchyLifeCycle(UniversalEditor editor) {
		this(editor, false);
	}

	public TypeHierarchyLifeCycle(UniversalEditor editor, boolean isSuperTypesOnly) {
		this.editor = editor;
		fHierarchy= null;
		fInputElement= null;
		fIsSuperTypesOnly= isSuperTypesOnly;
		fChangeListeners= new ArrayList(2);
	}

	public ITypeHierarchy getHierarchy() {
		return fHierarchy;
	}

	public ITypeInfo getInputElement() {
		return fInputElement;
	}


	public void freeHierarchy() {
		if (fHierarchy != null) {
//			fHierarchy.removeTypeHierarchyChangedListener(this);
			//JavaCore.removeElementChangedListener(this);
			fHierarchy= null;
			fInputElement= null;
		}
	}

	public void removeChangedListener(ITypeHierarchyLifeCycleListener listener) {
		fChangeListeners.remove(listener);
	}

	public void addChangedListener(ITypeHierarchyLifeCycleListener listener) {
		if (!fChangeListeners.contains(listener)) {
			fChangeListeners.add(listener);
		}
	}

	private void fireChange(ITypeInfo[] changedTypes) {
		for (int i= fChangeListeners.size()-1; i>=0; i--) {
			ITypeHierarchyLifeCycleListener curr= (ITypeHierarchyLifeCycleListener) fChangeListeners.get(i);
			curr.typeHierarchyChanged(this, changedTypes);
		}
	}

	public void ensureRefreshedTypeHierarchy(final ITypeInfo element, IRunnableContext context) throws InvocationTargetException, InterruptedException {
		if (element == null) {
			freeHierarchy();
			return;
		}
		boolean hierachyCreationNeeded= (fHierarchy == null || !element.equals(fInputElement));

		if (hierachyCreationNeeded || fHierarchyRefreshNeeded) {

			IRunnableWithProgress op= new IRunnableWithProgress() {
				public void run(IProgressMonitor pm) throws InvocationTargetException, InterruptedException {
					try {
						doHierarchyRefresh(element, pm);
					} catch (OperationCanceledException e) {
						throw new InterruptedException();
					} catch (Exception e) {
						throw new InvocationTargetException(e);
					}
				}
			};
			fHierarchyRefreshNeeded= true;
			context.run(true, true, op);
			fHierarchyRefreshNeeded= false;
		}
	}

	private ITypeHierarchy createTypeHierarchy(ITypeInfo element, IProgressMonitor pm) throws Exception {
//		if (element.getElementType() == ITypeInfo.TYPE) {
			IProject project = editor.getParseController().getProject().getRawProject();
			if (fIsSuperTypesOnly) {
				ITypeHierarchy h = X10SearchEngine.createTypeHierarchy(project, element.getName(), pm);
				return X10SearchEngine.createTypeHierarchy(project, h.getSuperClass(element.getName()), pm);
			} else {
				return X10SearchEngine.createTypeHierarchy(project, element.getName(), pm);
			}
//		} else {
//			IRegion region= new Region();
//			if (element instanceof ISourceProject) {
//				// for projects only add the contained source folders
//				IPackageFragmentRoot[] roots= ((ISourceProject) element).getPackageFragmentRoots();
//				for (int i= 0; i < roots.length; i++) {
//					if (!roots[i].isExternal()) {
//						region.add(roots[i]);
//					}
//				}
//			} else if (element.getElementType() == ITypeInfo.PACKAGE_FRAGMENT) {
//				IPackageFragmentRoot[] roots= element.getJavaProject().getPackageFragmentRoots();
//				String name= element.getElementName();
//				for (int i= 0; i < roots.length; i++) {
//					IPackageFragment pack= roots[i].getPackageFragment(name);
//					if (pack.exists()) {
//						region.add(pack);
//					}
//				}
//			} else {
//				region.add(element);
//			}
//			ISourceProject jproject= element.getJavaProject();
//			return jproject.newTypeHierarchy(region, pm);
//		}
	}


	public synchronized void doHierarchyRefresh(ITypeInfo element, IProgressMonitor pm) throws Exception {
		boolean hierachyCreationNeeded= (fHierarchy == null || !element.equals(fInputElement));
		// to ensure the order of the two listeners always remove / add listeners on operations
		// on type hierarchies
		if (fHierarchy != null) {
//			fHierarchy.removeTypeHierarchyChangedListener(this);
			//JavaCore.removeElementChangedListener(this);
		}
		if (hierachyCreationNeeded) {
			fHierarchy= createTypeHierarchy(element, pm);
			if (pm != null && pm.isCanceled()) {
				throw new OperationCanceledException();
			}
			fInputElement= element;
		} else {
			fHierarchy = createTypeHierarchy(element, pm);
		}
//		fHierarchy.addTypeHierarchyChangedListener(this);
		//JavaCore.addElementChangedListener(this);
		fHierarchyRefreshNeeded= false;
	}

	/*
	 * @see ITypeHierarchyChangedListener#typeHierarchyChanged
	 */
	public void typeHierarchyChanged(ITypeHierarchy typeHierarchy) {
	 	fHierarchyRefreshNeeded= true;
 		fireChange(null);
	}

	/*
	 * @see IElementChangedListener#elementChanged(ElementChangedEvent)
	 */
//	public void elementChanged(ElementChangedEvent event) {
//		if (fChangeListeners.isEmpty()) {
//			return;
//		}
//
//		if (fHierarchyRefreshNeeded) {
//			return;
//		} else {
//			ArrayList changedTypes= new ArrayList();
//			processDelta(event.getDelta(), changedTypes);
//			if (changedTypes.size() > 0) {
//				fireChange((IType[]) changedTypes.toArray(new IType[changedTypes.size()]));
//			}
//		}
//	}

	/*
	 * Assume that the hierarchy is intact (no refresh needed)
	 */
//	private void processDelta(IJavaElementDelta delta, ArrayList changedTypes) {
//		ITypeInfo element= delta.getElement();
//		switch (element.getElementType()) {
//			case ITypeInfo.TYPE:
//				processTypeDelta((IType) element, changedTypes);
//				processChildrenDelta(delta, changedTypes); // (inner types)
//				break;
//			case ITypeInfo.JAVA_MODEL:
//			case ITypeInfo.JAVA_PROJECT:
//			case ITypeInfo.PACKAGE_FRAGMENT_ROOT:
//			case ITypeInfo.PACKAGE_FRAGMENT:
//				processChildrenDelta(delta, changedTypes);
//				break;
//			case ITypeInfo.COMPILATION_UNIT:
//				ICompilationUnit cu= (ICompilationUnit)element;
//				if (!JavaModelUtil.isPrimary(cu)) {
//					return;
//				}
//
//				if (delta.getKind() == IJavaElementDelta.CHANGED && isPossibleStructuralChange(delta.getFlags())) {
//					try {
//						if (cu.exists()) {
//							IType[] types= cu.getAllTypes();
//							for (int i= 0; i < types.length; i++) {
//								processTypeDelta(types[i], changedTypes);
//							}
//						}
//					} catch (Exception e) {
//						UISearchPlugin.log(e);
//					}
//				} else {
//					processChildrenDelta(delta, changedTypes);
//				}
//				break;
//			case ITypeInfo.CLASS_FILE:
//				if (delta.getKind() == IJavaElementDelta.CHANGED) {
//					IType type= ((IClassFile) element).getType();
//					processTypeDelta(type, changedTypes);
//				} else {
//					processChildrenDelta(delta, changedTypes);
//				}
//				break;
//		}
//	}
//
//	private boolean isPossibleStructuralChange(int flags) {
//		return (flags & (IJavaElementDelta.F_CONTENT | IJavaElementDelta.F_FINE_GRAINED)) == IJavaElementDelta.F_CONTENT;
//	}

	private void processTypeDelta(ITypeInfo type, ArrayList changedTypes) {
		if (getHierarchy().contains(type.getName())) {
			changedTypes.add(type);
		}
	}

//	private void processChildrenDelta(IJavaElementDelta delta, ArrayList changedTypes) {
//		IJavaElementDelta[] children= delta.getAffectedChildren();
//		for (int i= 0; i < children.length; i++) {
//			processDelta(children[i], changedTypes); // recursive
//		}
//	}


}
