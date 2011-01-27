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


import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.imp.language.LanguageRegistry;
import org.eclipse.imp.model.ModelFactory.ModelException;
import org.eclipse.imp.utils.SelectionUtil;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IWorkbenchPartSite;

import x10dt.search.core.elements.IMethodInfo;
import x10dt.search.core.elements.ITypeInfo;
import x10dt.search.ui.UISearchPlugin;


/**
 * Method viewer shows a list of methods of a input type.
 * Offers filter actions.
 * No dependency to the type hierarchy view
 */
public class MethodsViewer extends ProblemTableViewer {

	private static final String TAG_SHOWINHERITED= "showinherited";		 //$NON-NLS-1$
	private static final String TAG_SORTBYDEFININGTYPE= "sortbydefiningtype";		 //$NON-NLS-1$
	private static final String TAG_VERTICAL_SCROLL= "mv_vertical_scroll";		 //$NON-NLS-1$

	private MethodsLabelProvider fLabelProvider;

	private MemberFilterActionGroup fMemberFilterActionGroup;

	private ShowInheritedMembersAction fShowInheritedMembersAction;
	private SortByDefiningTypeAction fSortByDefiningTypeAction;

	public MethodsViewer(Composite parent, final TypeHierarchyLifeCycle lifeCycle) {
		super(new Table(parent, SWT.MULTI));

		addFilter(new SyntheticMembersFilter());

		fLabelProvider= new MethodsLabelProvider(lifeCycle, this);

		setLabelProvider(new DecoratingLabelProvider(fLabelProvider, true, LanguageRegistry.findLanguage("X10")));
		setContentProvider(new MethodsContentProvider(lifeCycle));

		HierarchyViewerSorter sorter= new HierarchyViewerSorter(lifeCycle);
		sorter.setSortByDefiningType(false);
		setComparator(sorter);

		fMemberFilterActionGroup= new MemberFilterActionGroup(this, "HierarchyMethodView", false, MemberFilterActionGroup.ALL_FILTERS & ~MemberFilterActionGroup.FILTER_LOCALTYPES); //$NON-NLS-1$

		fShowInheritedMembersAction= new ShowInheritedMembersAction(this, false);
		fSortByDefiningTypeAction= new SortByDefiningTypeAction(this, false);

		showInheritedMethodsNoRedraw(false);
		sortByDefiningTypeNoRedraw(false);

//		JavaUIHelp.setHelp(this, IJavaHelpContextIds.TYPE_HIERARCHY_VIEW);
	}

	private void showInheritedMethodsNoRedraw(boolean on) {
		MethodsContentProvider cprovider= (MethodsContentProvider) getContentProvider();
		cprovider.showInheritedMethods(on);
		fShowInheritedMembersAction.setChecked(on);
		if (on) {
			fLabelProvider.setTextFlags(fLabelProvider.getTextFlags() | X10ElementLabels.ALL_POST_QUALIFIED);
		} else {
			fLabelProvider.setTextFlags(fLabelProvider.getTextFlags() & ~X10ElementLabels.ALL_POST_QUALIFIED);
		}
		if (on) {
			sortByDefiningTypeNoRedraw(false);
		}
		fSortByDefiningTypeAction.setEnabled(!on);

	}

	/**
	 * Show inherited methods
	 * @param on the new state
	 */
	public void showInheritedMethods(boolean on) {
		if (on == isShowInheritedMethods()) {
			return;
		}
		try {
			getTable().setRedraw(false);
			showInheritedMethodsNoRedraw(on);
			refresh();
		} finally {
			getTable().setRedraw(true);
		}
	}

	private void sortByDefiningTypeNoRedraw(boolean on) {
		fSortByDefiningTypeAction.setChecked(on);
		fLabelProvider.setShowDefiningType(on);
		((HierarchyViewerSorter) getComparator()).setSortByDefiningType(on);
	}

	/**
	 * Show the name of the defining type
	 * @param on the new state
	 */
	public void sortByDefiningType(boolean on) {
		if (on == isShowDefiningTypes()) {
			return;
		}
		try {
			getTable().setRedraw(false);
			sortByDefiningTypeNoRedraw(on);
			refresh();
		} finally {
			getTable().setRedraw(true);
		}
	}

	/*
	 * @see Viewer#inputChanged(Object, Object)
	 */
	protected void inputChanged(Object input, Object oldInput) {
		super.inputChanged(input, oldInput);
	}

	/**
	 * Returns <code>true</code> if inherited methods are shown.
	 * @return <code>true</code> if inherited methods are shown.
	 */
	public boolean isShowInheritedMethods() {
		return ((MethodsContentProvider) getContentProvider()).isShowInheritedMethods();
	}

	/**
	 * Returns <code>true</code> if defining types are shown.
	 * @return <code>true</code> if defining types are shown.
	 */
	public boolean isShowDefiningTypes() {
		return fLabelProvider.isShowDefiningType();
	}

	/**
	 * Saves the state of the filter actions
	 * @param memento the memento
	 */
	public void saveState(IMemento memento) {
		fMemberFilterActionGroup.saveState(memento);

		memento.putString(TAG_SHOWINHERITED, String.valueOf(isShowInheritedMethods()));
		memento.putString(TAG_SORTBYDEFININGTYPE, String.valueOf(isShowDefiningTypes()));

		ScrollBar bar= getTable().getVerticalBar();
		int position= bar != null ? bar.getSelection() : 0;
		memento.putString(TAG_VERTICAL_SCROLL, String.valueOf(position));
	}

	/**
	 * Restores the state of the filter actions
	 * @param memento the memento
	 */
	public void restoreState(IMemento memento) {
		fMemberFilterActionGroup.restoreState(memento);
		getControl().setRedraw(false);
		refresh();
		getControl().setRedraw(true);

		boolean showInherited= Boolean.valueOf(memento.getString(TAG_SHOWINHERITED)).booleanValue();
		showInheritedMethods(showInherited);

		boolean showDefiningTypes= Boolean.valueOf(memento.getString(TAG_SORTBYDEFININGTYPE)).booleanValue();
		sortByDefiningType(showDefiningTypes);

		ScrollBar bar= getTable().getVerticalBar();
		if (bar != null) {
			Integer vScroll= memento.getInteger(TAG_VERTICAL_SCROLL);
			if (vScroll != null) {
				bar.setSelection(vScroll.intValue());
			}
		}
	}

	/**
	 * Attaches a contextmenu listener to the table
	 * @param menuListener the menu listener
	 * @param popupId the popup id
	 * @param viewSite the view site
	 */
	public void initContextMenu(IMenuListener menuListener, String popupId, IWorkbenchPartSite viewSite) {
		MenuManager menuMgr= new MenuManager();
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(menuListener);
		Menu menu= menuMgr.createContextMenu(getTable());
		getTable().setMenu(menu);
		viewSite.registerContextMenu(popupId, menuMgr, this);
	}


	/**
	 * Fills up the context menu with items for the method viewer
	 * Should be called by the creator of the context menu
	 * @param menu teh menu manager
	 */
	public void contributeToContextMenu(IMenuManager menu) {
	}

	/**
	 * Fills up the tool bar with items for the method viewer
	 * Should be called by the creator of the tool bar
	 * @param tbm the tool bar manager
	 */
	public void contributeToToolBar(ToolBarManager tbm) {
		tbm.add(fShowInheritedMembersAction);
		tbm.add(fSortByDefiningTypeAction);
		tbm.add(new Separator());
		fMemberFilterActionGroup.contributeToToolBar(tbm);
	}

	public void dispose() {
		if (fMemberFilterActionGroup != null) {
			fMemberFilterActionGroup.dispose();
			fMemberFilterActionGroup= null;
		}
	}

	/*
	 * @see StructuredViewer#handleInvalidSelection(ISelection, ISelection)
	 */
	protected void handleInvalidSelection(ISelection invalidSelection, ISelection newSelection) {
		// on change of input, try to keep selected methods stable by selecting a method with the same
		// signature: See #5466
		List oldSelections= SelectionUtil.toList(invalidSelection);
		List newSelections= SelectionUtil.toList(newSelection);
		if (!oldSelections.isEmpty()) {
			ArrayList newSelectionElements= new ArrayList(newSelections);
			try {
				Object[] currElements= getFilteredChildren(getInput());
				for (int i= 0; i < oldSelections.size(); i++) {
					Object curr= oldSelections.get(i);
					if (curr instanceof IMethodInfo && !newSelections.contains(curr)) {
						IMethodInfo method= (IMethodInfo) curr;
						if (method.exists(new NullProgressMonitor())) {
							IMethodInfo similar= findSimilarMethod(method, currElements);
							if (similar != null) {
								newSelectionElements.add(similar);
							}
						}
					}
				}
				if (!newSelectionElements.isEmpty()) {
					newSelection= new StructuredSelection(newSelectionElements);
				} else if (currElements.length > 0) {
					newSelection= new StructuredSelection(currElements[0]);
				}
			} catch (Exception e) {
				UISearchPlugin.log(e);
			}
		}
		setSelection(newSelection);
		updateSelection(newSelection);
	}

	private IMethodInfo findSimilarMethod(IMethodInfo meth, Object[] elements) throws ModelException {
		String name= meth.getName();
		ITypeInfo[] paramTypes= meth.getParameters();
		boolean isConstructor= meth.isConstructor();

		for (int i= 0; i < elements.length; i++) {
			Object curr= elements[i];
			if (curr instanceof IMethodInfo && ModelUtil.isSameMethodSignature(name, paramTypes, isConstructor, (IMethodInfo) curr)) {
				return (IMethodInfo) curr;
			}
		}
		return null;
	}
}