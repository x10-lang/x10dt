package x10dt.search.ui.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.internal.ui.IJavaHelpContextIds;
import org.eclipse.jdt.internal.ui.dialogs.TextFieldNavigationHandler;
import org.eclipse.jdt.internal.ui.util.SWTUtil;
import org.eclipse.jdt.ui.search.ElementQuerySpecification;
import org.eclipse.jdt.ui.search.PatternQuerySpecification;
import org.eclipse.jdt.ui.search.QuerySpecification;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.search.ui.ISearchPage;
import org.eclipse.search.ui.ISearchPageContainer;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.IWorkingSetManager;
import org.eclipse.ui.PlatformUI;
import static x10dt.search.ui.search.SearchPatternData.*;

/**
 * The X10SearchPage presents the search UI page to the user.
 * @author mvaziri
 *
 */
public class X10SearchPage extends DialogPage implements ISearchPage {

	// --- The main pattern field.
	private Combo fPattern;
	
	// --- The Case Sensitive button.
	private Button fCaseSensitive;
	
	// --- Field indicating if search is case sensitive.
	private boolean fIsCaseSensitive;
	
	// --- Previously stored search patterns.
	private final List<SearchPatternData> fPreviousSearchPatterns;
	
	// --- Initial data for the search page.
	private SearchPatternData fInitialData;
	
	// --- SearchFor buttons.
	private Button[] fSearchFor;
	
	// --- Limit to buttons.
	private Button[] fLimitTo;
	
	// --- Limit to group.
	private Group fLimitToGroup;
	
	// --- Search page container
	private ISearchPageContainer fContainer;
	
	// --- First time search page is opened
	private boolean fFirstTime= true;
	
	public X10SearchPage(){
		fPreviousSearchPatterns = new ArrayList<SearchPatternData>();
	}
	
	public void createControl(Composite parent) {
		initializeDialogUnits(parent);
		
		Composite result= new Composite(parent, SWT.NONE);

		GridLayout layout= new GridLayout(2, true);
		layout.horizontalSpacing= 10;
		result.setLayout(layout);

		Control expressionComposite= createExpression(result);
		expressionComposite.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 2, 1));

		Label separator= new Label(result, SWT.NONE);
		separator.setVisible(false);
		GridData data= new GridData(GridData.FILL, GridData.FILL, false, false, 2, 1);
		data.heightHint= convertHeightInCharsToPixels(1) / 3;
		separator.setLayoutData(data);

		Control searchFor= createSearchFor(result);
		searchFor.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false, 1, 1));

		Control limitTo= createLimitTo(result);
		limitTo.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false, 1, 1));

		SelectionAdapter javaElementInitializer= new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				setLimitTo(getSearchFor(), getLimitTo());
				doPatternModified();
			}
		};

		for (int i= 0; i < fSearchFor.length; i++) {
			fSearchFor[i].addSelectionListener(javaElementInitializer);
		}
		
		setControl(result);

		Dialog.applyDialogFont(result);
	}

	public boolean performAction() {
		return performNewSearch();
	}


	
	public void setContainer(ISearchPageContainer container) {
		fContainer = container;
	}
	
	public void setVisible(boolean visible) {
		if (visible && fPattern != null) {
			if (fFirstTime) {
				fFirstTime= false;
				// Set item and text here to prevent page from resizing
				fPattern.setItems(getPreviousSearchPatterns());
			}
			fPattern.setFocus();
		}
		updateOKStatus();
		super.setVisible(visible);
	}
	
	// --- Private methods
	
	private boolean performNewSearch() {
		SearchPatternData data= getPatternData();
		X10SearchQuery query = new X10SearchQuery(data);
		NewSearchUI.runQueryInBackground(query);
		return true;
	}
	
	private SearchPatternData getPatternData() {
		String pattern= getPattern();
		SearchPatternData match= findInPrevious(pattern);
		if (match != null) {
			fPreviousSearchPatterns.remove(match);
		}
		match= new SearchPatternData(
				getSearchFor(),
				getLimitTo(),
				pattern,
				fCaseSensitive.getSelection(),
				getContainer().getSelectedScope(),
				getContainer().getSelectedWorkingSets()
		);

		fPreviousSearchPatterns.add(0, match); // insert on top
		return match;
	}
	
	private SearchPatternData findInPrevious(String pattern) {
		for (Iterator<SearchPatternData> iter= fPreviousSearchPatterns.iterator(); iter.hasNext();) {
			SearchPatternData element= iter.next();
			if (pattern.equals(element.getPattern())) {
				return element;
			}
		}
		return null;
	}
	
	private String[] getPreviousSearchPatterns() {
		// Search results are not persistent
		int patternCount= fPreviousSearchPatterns.size();
		String [] patterns= new String[patternCount];
		for (int i= 0; i < patternCount; i++)
			patterns[i]= fPreviousSearchPatterns.get(i).getPattern();
		return patterns;
	}
	
	private Control createSearchFor(Composite parent) {
		Group result= new Group(parent, SWT.NONE);
		result.setText(SearchMessages.SearchPage_searchFor_label);
		result.setLayout(new GridLayout(2, true));

		fSearchFor= new Button[] {
			createButton(result, SWT.RADIO, SearchMessages.SearchPage_searchFor_type, TYPE, true),
			createButton(result, SWT.RADIO, SearchMessages.SearchPage_searchFor_method, METHOD, false),
			createButton(result, SWT.RADIO, SearchMessages.SearchPage_searchFor_package, PACKAGE, false),
			createButton(result, SWT.RADIO, SearchMessages.SearchPage_searchFor_constructor, CONSTRUCTOR, false),
			createButton(result, SWT.RADIO, SearchMessages.SearchPage_searchFor_field, FIELD, false)
		};

		// Fill with dummy radio buttons
		Label filler= new Label(result, SWT.NONE);
		filler.setVisible(false);
		filler.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));

		return result;
	}

	private Control createLimitTo(Composite parent) {
		fLimitToGroup= new Group(parent, SWT.NONE);
		fLimitToGroup.setText(SearchMessages.SearchPage_limitTo_label);
		fLimitToGroup.setLayout(new GridLayout(2, false));

		fillLimitToGroup(TYPE, ALL_OCCURRENCES);

		return fLimitToGroup;
	}
	
	private Control createExpression(Composite parent) {
		Composite result= new Composite(parent, SWT.NONE);
		GridLayout layout= new GridLayout(2, false);
		layout.marginWidth= 0;
		layout.marginHeight= 0;
		result.setLayout(layout);

		// Pattern text + info
		Label label= new Label(result, SWT.LEFT);
		label.setText(SearchMessages.SearchPage_expression_label);
		label.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false, 2, 1));

		// Pattern combo
		fPattern= new Combo(result, SWT.SINGLE | SWT.BORDER);
		SWTUtil.setDefaultVisibleItemCount(fPattern);
		fPattern.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handlePatternSelected();
				updateOKStatus();
			}
		});
		fPattern.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				doPatternModified();
				updateOKStatus();

			}
		});
		TextFieldNavigationHandler.install(fPattern);
		GridData data= new GridData(GridData.FILL, GridData.FILL, true, false, 1, 1);
		data.widthHint= convertWidthInCharsToPixels(50);
		fPattern.setLayoutData(data);

		// Ignore case checkbox
		fCaseSensitive= new Button(result, SWT.CHECK);
		fCaseSensitive.setText(SearchMessages.SearchPage_expression_caseSensitive);
		fCaseSensitive.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				fIsCaseSensitive= fCaseSensitive.getSelection();
			}
		});
		fCaseSensitive.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false, 1, 1));

		return result;
	}
	
	private void doPatternModified() {
		if (fInitialData != null && getPattern().equals(fInitialData.getPattern()) && fInitialData.getSearchFor() == getSearchFor()) {
			fCaseSensitive.setEnabled(false);
			fCaseSensitive.setSelection(true);
		} else {
			fCaseSensitive.setEnabled(true);
			fCaseSensitive.setSelection(fIsCaseSensitive);
		}
	}
	
	final void updateOKStatus() {
		boolean isValidPattern= isValidSearchPattern();
		getContainer().setPerformActionEnabled(isValidPattern);
	}

	private ISearchPageContainer getContainer() {
		return fContainer;
	}

	private boolean isValidSearchPattern() {
		if (getPattern().length() == 0) {
			return false;
		}
		// --- TODO: Need a different check here for deciding if pattern is valid.
		return SearchPattern.createPattern(getPattern(), getSearchFor(), getLimitTo(), SearchPattern.R_EXACT_MATCH) != null;
	}
	
	private String getPattern() {
		return fPattern.getText();
	}
	
	private int getSearchFor() {
		for (int i= 0; i < fSearchFor.length; i++) {
			Button button= fSearchFor[i];
			if (button.getSelection()) {
				return getIntData(button);
			}
		}
		Assert.isTrue(false, "shouldNeverHappen"); //$NON-NLS-1$
		return -1;
	}
	
	private int getLimitTo() {
		for (int i= 0; i < fLimitTo.length; i++) {
			Button button= fLimitTo[i];
			if (button.getSelection()) {
				return getIntData(button);
			}
		}
		return -1;
	}
	
	private void handlePatternSelected() {
		int selectionIndex= fPattern.getSelectionIndex();
		if (selectionIndex < 0 || selectionIndex >= fPreviousSearchPatterns.size())
			return;

		SearchPatternData initialData= fPreviousSearchPatterns.get(selectionIndex);

		setSearchFor(initialData.getSearchFor());
		setLimitTo(initialData.getSearchFor(), initialData.getLimitTo());
		

		fPattern.setText(initialData.getPattern());
		fIsCaseSensitive= initialData.isCaseSensitive();
		fCaseSensitive.setSelection(initialData.isCaseSensitive());

		if (initialData.getWorkingSets() != null)
			getContainer().setSelectedWorkingSets(initialData.getWorkingSets());
		else
			getContainer().setSelectedScope(initialData.getScope());
		
		fInitialData= initialData;
	}
	
	private void setSearchFor(int searchFor) {
		for (int i= 0; i < fSearchFor.length; i++) {
			Button button= fSearchFor[i];
			button.setSelection(searchFor == getIntData(button));
		}
	}
	
	private int getIntData(Button button) {
		return ((Integer) button.getData()).intValue();
	}
	
	private int setLimitTo(int searchFor, int limitTo) {
		if (searchFor != TYPE && limitTo == IMPLEMENTORS) {
			limitTo= REFERENCES;
		}

		if (searchFor != FIELD && (limitTo == READ_ACCESSES || limitTo == WRITE_ACCESSES)) {
			limitTo= REFERENCES;
		}
		
		if (searchFor != TYPE /*&& searchFor != FIELD*/ && searchFor != METHOD && limitTo == SPECIFIC_REFERENCES) {
			limitTo= REFERENCES;
		}
		fillLimitToGroup(searchFor, limitTo);
		return limitTo;
	}
	
	private void fillLimitToGroup(int searchFor, int limitTo) {
		Control[] children= fLimitToGroup.getChildren();
		for (int i= 0; i < children.length; i++) {
			children[i].dispose();
		}

		ArrayList<Button> buttons= new ArrayList<Button>();
		buttons.add(createButton(fLimitToGroup, SWT.RADIO, SearchMessages.SearchPage_limitTo_allOccurrences, ALL_OCCURRENCES, limitTo == ALL_OCCURRENCES));
		buttons.add(createButton(fLimitToGroup, SWT.RADIO, SearchMessages.SearchPage_limitTo_declarations, DECLARATIONS, limitTo == DECLARATIONS));

		buttons.add(createButton(fLimitToGroup, SWT.RADIO, SearchMessages.SearchPage_limitTo_references, REFERENCES, limitTo == REFERENCES));
		if (searchFor == TYPE) {
			buttons.add(createButton(fLimitToGroup, SWT.RADIO, SearchMessages.SearchPage_limitTo_implementors, IMPLEMENTORS, limitTo == IMPLEMENTORS));
		}

		if (searchFor == FIELD) {
			buttons.add(createButton(fLimitToGroup, SWT.RADIO, SearchMessages.SearchPage_limitTo_readReferences, READ_ACCESSES, limitTo == READ_ACCESSES));
			buttons.add(createButton(fLimitToGroup, SWT.RADIO, SearchMessages.SearchPage_limitTo_writeReferences, WRITE_ACCESSES, limitTo == WRITE_ACCESSES));
		}

		fLimitTo= buttons.toArray(new Button[buttons.size()]);

		SelectionAdapter listener= new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				performLimitToSelectionChanged((Button) e.widget);
			}
		};
		for (int i= 0; i < fLimitTo.length; i++) {
			fLimitTo[i].addSelectionListener(listener);
		}
		Dialog.applyDialogFont(fLimitToGroup); // re-apply font as we disposed the previous widgets

		fLimitToGroup.layout();
	}
	
	private Button createButton(Composite parent, int style, String text, int data, boolean isSelected) {
		Button button= new Button(parent, style);
		button.setText(text);
		button.setData(new Integer(data));
		button.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		button.setSelection(isSelected);
		return button;
	}
	
	protected final void performLimitToSelectionChanged(Button button) {
		if (button.getSelection()) {
			for (int i= 0; i < fLimitTo.length; i++) {
				Button curr= fLimitTo[i];
				if (curr != button) {
					curr.setSelection(false);
				}
			}
		}
	}

	

}
