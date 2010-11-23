package x10dt.search.ui.search;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.search.ui.ISearchPageContainer;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.IWorkingSetManager;
import org.eclipse.ui.PlatformUI;

/**
 * This class encapsulates the data gathered from the search page UI.
 * @author mvaziri
 *
 */
public class SearchPatternData {
	// search for
	public final static int TYPE= 0;
	public final static int METHOD= 1;
	public final static int PACKAGE= 2;
	public final static int CONSTRUCTOR= 3;
	public final static int FIELD= 4;

	// limit to
	public final static int DECLARATIONS= 0;
	public final static int IMPLEMENTORS= 1;
	public final static int REFERENCES= 2;
	public final static int SPECIFIC_REFERENCES= 3;
	public final static int ALL_OCCURRENCES= 4;
	public final static int READ_ACCESSES= 5;
	public final static int WRITE_ACCESSES= 6;
	
	private final int searchFor;
	private final int limitTo;
	private final String pattern;
	private final IWorkingSet[] workingSets;
	private final boolean isCaseSensitive;
	private final int scope;

	public SearchPatternData(int searchFor, int limitTo, boolean isCaseSensitive, String pattern) {
		this(searchFor, limitTo, pattern, isCaseSensitive,  ISearchPageContainer.WORKSPACE_SCOPE, null);
	}

	public SearchPatternData(int searchFor, int limitTo, String pattern, boolean isCaseSensitive, int scope, IWorkingSet[] workingSets) {
		this.searchFor= searchFor;
		this.limitTo= limitTo;
		this.pattern = pattern.replace("?", ".?").replace("*", ".*");
		this.isCaseSensitive= isCaseSensitive;
		this.workingSets= workingSets;
		this.scope= scope;
	}

	public boolean isCaseSensitive() {
		return isCaseSensitive;
	}

	public int getLimitTo() {
		return limitTo;
	}

	public String getPattern() {
		return pattern;
	}

	public int getScope() {
		return scope;
	}

	public int getSearchFor() {
		return searchFor;
	}

	public IWorkingSet[] getWorkingSets() {
		return workingSets;
	}
	
	public void store(IDialogSettings settings) {
		settings.put("searchFor", searchFor); //$NON-NLS-1$
		settings.put("scope", scope); //$NON-NLS-1$
		settings.put("pattern", pattern); //$NON-NLS-1$
		settings.put("limitTo", limitTo); //$NON-NLS-1$
		settings.put("isCaseSensitive", isCaseSensitive); //$NON-NLS-1$
		if (workingSets != null) {
			String[] wsIds= new String[workingSets.length];
			for (int i= 0; i < workingSets.length; i++) {
				wsIds[i]= workingSets[i].getName();
			}
			settings.put("workingSets", wsIds); //$NON-NLS-1$
		} else {
			settings.put("workingSets", new String[0]); //$NON-NLS-1$
		}
	}

	public static SearchPatternData create(IDialogSettings settings) {
		String pattern= settings.get("pattern"); //$NON-NLS-1$
		if (pattern.length() == 0) {
			return null;
		}
		try {
			int searchFor= settings.getInt("searchFor"); //$NON-NLS-1$
			int scope= settings.getInt("scope"); //$NON-NLS-1$
			int limitTo= settings.getInt("limitTo"); //$NON-NLS-1$

			boolean isCaseSensitive= settings.getBoolean("isCaseSensitive"); //$NON-NLS-1$

			String[] wsIds= settings.getArray("workingSets"); //$NON-NLS-1$
			IWorkingSet[] workingSets= null;
			if (wsIds != null && wsIds.length > 0) {
				IWorkingSetManager workingSetManager= PlatformUI.getWorkbench().getWorkingSetManager();
				workingSets= new IWorkingSet[wsIds.length];
				for (int i= 0; workingSets != null && i < wsIds.length; i++) {
					workingSets[i]= workingSetManager.getWorkingSet(wsIds[i]);
					if (workingSets[i] == null) {
						workingSets= null;
					}
				}
			}
			
			return new SearchPatternData(searchFor, limitTo, pattern, isCaseSensitive, scope, workingSets);
		} catch (NumberFormatException e) {
			return null;
		}
	}

}
