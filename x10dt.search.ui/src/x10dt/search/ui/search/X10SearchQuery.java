package x10dt.search.ui.search;

import java.net.URI;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.ISearchResult;

import x10dt.search.core.engine.ITypeInfo;
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
			for (IProject project: ResourcesPlugin.getWorkspace().getRoot().getProjects()){
				if (fPatternData.getSearchFor() == SearchPatternData.TYPE && fPatternData.getLimitTo() == SearchPatternData.ALL_OCCURRENCES){
					ITypeInfo[] results = X10SearchEngine.getAllMatchingTypeInfo(project, stringPattern, fPatternData.isCaseSensitive(), mainSearchPM);
					for(ITypeInfo info: results){
						acceptSearchResult(info, textResult);
					}
				}
			}
		} catch(Exception e){
			//TODO: FIXME
			System.err.println(e);
		}
		String message= NLS.bind(Messages.X10SearchQuery_status_ok_message, String.valueOf(textResult.getMatchCount())); 
		return new Status(IStatus.OK, UISearchPlugin.PLUGIN_ID, 0, message, null); 
	}

	public void acceptSearchResult(ITypeInfo result, X10SearchResult search){
		URI loc = result.getLocation().getURI();
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(loc.getPath()).makeRelativeTo(ResourcesPlugin.getWorkspace().getRoot().getLocation()));
		search.addMatch(new X10ElementMatch(file, result.getLocation().getOffset(), result.getLocation().getLength(), false, false));
		
	}

	public String getLabel() {
		return Messages.X10SearchQuery_label;
	}

	public String getResultLabel(int nMatches) {
		return NLS.bind(Messages.X10SearchQuery_status_ok_message, nMatches);
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

