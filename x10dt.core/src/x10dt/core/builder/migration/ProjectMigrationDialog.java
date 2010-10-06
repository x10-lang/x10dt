/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/

package x10dt.core.builder.migration;

import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

final class ProjectMigrationDialog extends Dialog {
	private final Set<IProject> fMigrateProjects;
	private final Set<IProject> fBrokenProjects;
	private boolean fDontAskAgain;

	ProjectMigrationDialog(Shell parentShell, Set<IProject> brokenProjects, Set<IProject> migrateProjects) {
		super(parentShell);
		this.fMigrateProjects = migrateProjects;
		this.fBrokenProjects = brokenProjects;
	}

	public boolean getDontAskAgain() {
		return fDontAskAgain;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setSize(350, 300);
		newShell.setText("Migrate X10 Projects");
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		// create OK and Cancel buttons by default
		createButton(parent, IDialogConstants.OK_ID, "Proceed", true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		GridLayout topLayout = new GridLayout(1, false);
		area.setLayout(topLayout);

		Text descriptionText = new Text(area, SWT.WRAP);
		descriptionText.setText("Some of the X10 projects in your workspace contain old meta-data that must be migrated in order to work with the current version of the X10DT.");
		descriptionText.setEditable(false);
		descriptionText.setBackground(parent.getBackground());
		descriptionText.setLayoutData(new GridData(350, 45));

		Label topLabel = new Label(area, SWT.NONE);
		topLabel.setText("Projects needing migration:");

		Composite tableButtons = new Composite(area, SWT.NONE);
		tableButtons.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));

		final CheckboxTableViewer cbTableViewer = CheckboxTableViewer.newCheckList(tableButtons, SWT.CHECK | SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION
				| SWT.V_SCROLL);

		cbTableViewer.setContentProvider(new IStructuredContentProvider() {
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}

			public void dispose() {}

			public Object[] getElements(Object inputElement) {
				return fBrokenProjects.toArray();
				// return new Object[] { "a", "b", "c", "d", "e", "f", "g", "h",
				// "i", "j", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j"
				// ,"a", "b", "c", "d", "e", "f", "g", "h", "i", "j" ,"a", "b",
				// "c", "d", "e", "f", "g", "h", "i", "j" ,"a", "b", "c", "d",
				// "e", "f", "g", "h", "i", "j" ,"a", "b", "c", "d", "e", "f",
				// "g", "h", "i", "j" };
			}
		});
		cbTableViewer.setLabelProvider(new ITableLabelProvider() {
			public void addListener(ILabelProviderListener listener) {}

			public void removeListener(ILabelProviderListener listener) {}

			public boolean isLabelProperty(Object element, String property) {
				return false;
			}

			public void dispose() {}

			public String getColumnText(Object element, int columnIndex) {
				return ((IProject) element).getName();
				// return (String) element;
			}

			public Image getColumnImage(Object element, int columnIndex) {
				return null;
			}
		});
		cbTableViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				IProject p = (IProject) event.getElement();
				if (event.getChecked()) {
					fMigrateProjects.add(p);
				} else {
					fMigrateProjects.remove(p);
				}
			}
		});
		cbTableViewer.setInput(new Object()); // just to trigger the initial
		// update

		Composite selectButtons = new Composite(tableButtons, SWT.NONE);
		RowLayout selectButtonsLayout = new RowLayout(SWT.VERTICAL);
		selectButtons.setLayout(selectButtonsLayout);

		Button selectAllButton = new Button(selectButtons, SWT.PUSH);
		selectAllButton.setText("Select All");
		selectAllButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				cbTableViewer.setAllChecked(true);
				// For some reason, setAllChecked() doesn't cause the
				// check-state listener
				// to be called, so do what it would do (argh).
				fMigrateProjects.addAll(fBrokenProjects);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		Button deselectAllButton = new Button(selectButtons, SWT.PUSH);
		deselectAllButton.setText("Deselect All");
		deselectAllButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				cbTableViewer.setAllChecked(false);
				// For some reason, setAllChecked() doesn't cause the check-state listener
				// to be called, so do what it would do (argh).
				fMigrateProjects.clear();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		TableLayout cbTableLayout = new TableLayout();
		cbTableLayout.addColumnData(new ColumnWeightData(100, false));
		cbTableViewer.getTable().setLayout(cbTableLayout);

		GridLayout tableButtonsLayout = new GridLayout(2, false);
		cbTableViewer.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		selectButtons.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false));
		tableButtons.setLayout(tableButtonsLayout);

		final Button dontAskAgainCB = new Button(area, SWT.CHECK);

		dontAskAgainCB.setText("Don't ask again about deselected projects");
		dontAskAgainCB.setToolTipText("If checked, you won't be asked again whether to migrate any of the projects you specified not to migrate this time. "
				+ "If you check this, you can still migrate the projects manually: select the project in the Package Explorer, right-click "
				+ "and select \"Configure\" and then \"Migrate Projects\".");
		dontAskAgainCB.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				fDontAskAgain = dontAskAgainCB.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		return area;
	}
}
