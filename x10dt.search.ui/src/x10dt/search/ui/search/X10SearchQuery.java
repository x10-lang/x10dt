package x10dt.search.ui.search;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.ISearchResult;

import x10dt.search.core.elements.IFieldInfo;
import x10dt.search.core.elements.IMemberInfo;
import x10dt.search.core.elements.IMethodInfo;
import x10dt.search.core.elements.ITypeInfo;
import x10dt.search.core.engine.X10SearchEngine;
import x10dt.search.ui.Messages;
import x10dt.search.ui.UISearchPlugin;

public class X10SearchQuery implements ISearchQuery {

	private ISearchResult fResult;
	private final SearchPatternData fPatternData;

	public X10SearchQuery(SearchPatternData data) {
		if (data == null) {
			throw new IllegalArgumentException("data must not be null"); //$NON-NLS-1$
		}
		fPatternData= data;
	}

	public IStatus run(IProgressMonitor monitor) {
		final X10SearchResult textResult= (X10SearchResult) getSearchResult();
		textResult.removeAll();
		int totalTicks = 1000;
		String stringPattern = fPatternData.getPattern();
		monitor.beginTask(NLS.bind(Messages.X10SearchQuery_task_label, stringPattern), totalTicks); 
		IProgressMonitor mainSearchPM= new SubProgressMonitor(monitor, totalTicks);
		try {
			
			if (fPatternData.getSearchFor() == SearchPatternData.TYPE){
				ITypeInfo[] results = X10SearchEngine.getAllMatchingTypeInfo(fPatternData.getX10Scope(), stringPattern, fPatternData.isCaseSensitive(), mainSearchPM);
				for(ITypeInfo info: results){
					if (fPatternData.getLimitTo() == SearchPatternData.ALL_OCCURRENCES ||
						fPatternData.getLimitTo() == SearchPatternData.DECLARATIONS) {
							acceptSearchResult(info, textResult);
					}
					if (fPatternData.getLimitTo() == SearchPatternData.ALL_OCCURRENCES ||
						fPatternData.getLimitTo() == SearchPatternData.REFERENCES){
							X10SearchEngine.collectTypeUses(fPatternData.getX10Scope(), info, textResult, mainSearchPM);	
					}
				}
			} else if (fPatternData.getSearchFor() == SearchPatternData.METHOD){
				IMethodInfo[] results = X10SearchEngine.getAllMatchingMethodInfo(fPatternData.getX10Scope(), stringPattern, fPatternData.isCaseSensitive(), mainSearchPM);
				for (IMethodInfo r: results){
					if (fPatternData.getLimitTo() == SearchPatternData.ALL_OCCURRENCES ||
							fPatternData.getLimitTo() == SearchPatternData.DECLARATIONS) {
						acceptSearchResult(r, textResult);
					}
					if (fPatternData.getLimitTo() == SearchPatternData.ALL_OCCURRENCES ||
						fPatternData.getLimitTo() == SearchPatternData.REFERENCES){
						X10SearchEngine.collectMethodUses(fPatternData.getX10Scope(), r, textResult, mainSearchPM);
					}
				}
			} else if (fPatternData.getSearchFor() == SearchPatternData.FIELD){
					IFieldInfo[] results = X10SearchEngine.getAllMatchingFieldInfo(fPatternData.getX10Scope(), stringPattern, fPatternData.isCaseSensitive(), mainSearchPM);
					for (IFieldInfo r: results){
						if (fPatternData.getLimitTo() == SearchPatternData.ALL_OCCURRENCES ||
							fPatternData.getLimitTo() == SearchPatternData.DECLARATIONS) {
								acceptSearchResult(r, textResult);
						}
						if (fPatternData.getLimitTo() == SearchPatternData.ALL_OCCURRENCES ||
							fPatternData.getLimitTo() == SearchPatternData.REFERENCES){
								X10SearchEngine.collectFieldUses(fPatternData.getX10Scope(), r, textResult, mainSearchPM);
						}
					}
			}
		} catch(Exception e){
			//TODO: FIXME
			System.err.println(e);
		}
		String message= NLS.bind(Messages.X10SearchQuery_status_ok_message, fPatternData.getPatternLabel(), String.valueOf(textResult.getMatchCount())); 
		return new Status(IStatus.OK, UISearchPlugin.PLUGIN_ID, 0, message, null); 
	}

	public void acceptSearchResult(IMemberInfo result, X10SearchResult search){
		search.addMatch(new X10ElementMatch(result, result.getLocation().getOffset(), result.getLocation().getLength(), false, false));
		
	}

	public String getLabel() {
		return Messages.X10SearchQuery_label;
	}

	public String getResultLabel(int nMatches) {
		return NLS.bind(Messages.X10SearchQuery_status_ok_message, fPatternData.getPatternLabel(), nMatches);
	}

	public boolean canRerun() {
		return true;
	}

	public boolean canRunInBackground() {
		return true;
	}

	public ISearchResult getSearchResult() {
		if (fResult == null) {
			X10SearchResult result= new X10SearchResult(this);
			// new SearchResultUpdater(result);
			fResult= result;
		}
		return fResult;
	}



}

