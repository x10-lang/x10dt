package x10dt.ui.quickfix;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.imp.editor.quickfix.CUCorrectionProposal;
import org.eclipse.imp.language.LanguageRegistry;
import org.eclipse.imp.model.ICompilationUnit;
import org.eclipse.imp.runtime.PluginImages;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.TypeNameMatch;
import org.eclipse.jdt.internal.core.search.JavaWorkspaceScope;
import org.eclipse.jdt.internal.corext.util.TypeNameMatchCollector;
import org.eclipse.jdt.internal.ui.JavaPluginImages;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.contentassist.ICompletionProposal;

import org.eclipse.imp.services.IQuickFixInvocationContext;
import org.eclipse.imp.utils.FormatUtils;
import org.eclipse.imp.utils.NullMessageHandler;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextUtilities;

import org.eclipse.text.edits.InsertEdit;
import org.eclipse.text.edits.MultiTextEdit;
import org.eclipse.text.edits.TextEdit;

import polyglot.ast.Import;
import polyglot.ast.PackageNode;
import polyglot.ast.SourceFile_c;
import x10dt.search.core.elements.ITypeInfo;
import x10dt.search.core.engine.X10SearchEngine;
import x10dt.search.core.engine.scope.SearchScopeFactory;
import x10dt.search.core.engine.scope.X10SearchScope;
import x10dt.ui.typeHierarchy.SearchUtils;

public class AddImportProposal extends CUCorrectionProposal {
	IQuickFixInvocationContext context;
	String typeName;
	String packageName;

	public static void addImportProposals(
			Collection<ICompletionProposal> proposals,
			IQuickFixInvocationContext context,
			IProgressMonitor monitor, 
			String typeName) {
		// Find all imports from the classpath that would make typeName a valid type
		// For each one, add an appropriate AddImportProposal for it
		ICompilationUnit comp = context.getModel(); 
		Object root = comp.getAST(new NullMessageHandler(),
				new NullProgressMonitor());
		if(! (root instanceof SourceFile_c)) {
			return;
		}
		SourceFile_c source = (SourceFile_c)root;
		PackageNode sourcePackage = source.package_();
		String sourcePackageName = sourcePackage == null ? "" : sourcePackage.toString();
		// The set of packages already added (to avoid duplicates)
		Set<String> packageNames = new HashSet<String>();
		// We already "found" the default and the current packages
		packageNames.add("");
		packageNames.add("default");
		packageNames.add("x10.lang");
		packageNames.add(sourcePackageName);
		
		// proposals.add(new AddImportProposal(context, typeName, "java.lang"));
		
		try {
			// We should probably pass in an ICompilationUnit[]
//			SearchEngine search = new SearchEngine();
//			search.searchAllTypeNames(null,  SearchPattern.R_PATTERN_MATCH, typeName, SearchPattern.R_PATTERN_MATCH, IJavaSearchConstants.ALL_OCCURRENCES, scope, nameRequestor, waitingPolicy, progressMonitor)
//			ITypeInfo[] types = X10SearchEngine.getAllMatchingTypeInfo(SearchScopeFactory.createWorkspaceScope(X10SearchScope.ALL), typeNameRegEx, true, new NullProgressMonitor());
			SubProgressMonitor searchMonitor = new SubProgressMonitor(monitor, 1);
			ITypeInfo[] types = X10SearchEngine.getAllMatchingTypeInfo(SearchScopeFactory.createWorkspaceScope(X10SearchScope.ALL), SearchUtils.getTypeRegex(typeName), false, searchMonitor);
			for(ITypeInfo typeInfo : types) {
				ITypeInfo type = typeInfo;
				ITypeInfo parentType = typeInfo;
				while(parentType != null) {
					type = parentType;
					parentType = SearchUtils.getOuterTypeInfo(type);
					if(parentType == type) {
						break;
					}
				}
				String packageName = SearchUtils.getPackageName(type);
				// don't suggest imports from the default package or the current package, or an already added package
				if (! packageNames.contains(packageName)) {
					String fullName = typeInfo.getName();				
					String elemName = fullName.substring(packageName.length()+1); 
					packageNames.add(packageName);
					proposals.add(new AddImportProposal(true, context, elemName, packageName, 8));
				}
			}

			try {
				if(context.getModel().getProject().getRawProject().hasNature("x10dt.ui.launch.java.x10nature")) {
					// If we are in a java backend project, also find the java types
					ArrayList<TypeNameMatch> typeInfos= new ArrayList<TypeNameMatch>();
					TypeNameMatchCollector requestor= new TypeNameMatchCollector(typeInfos);
					IJavaSearchScope searchScope= new JavaWorkspaceScope();
					new SearchEngine().searchAllTypeNames(null, 0, typeName.toCharArray(), SearchPattern.R_EXACT_MATCH | SearchPattern.R_CASE_SENSITIVE, IJavaSearchConstants.TYPE, searchScope, requestor, IJavaSearchConstants.FORCE_IMMEDIATE_SEARCH, searchMonitor);
					for (TypeNameMatch curr : typeInfos) {
						String packageName = curr.getPackageName();
							IType type = curr.getType();
							IType parentType = type;
							while(parentType != null) {
								type = parentType;
								parentType = type.getDeclaringType();

							}
							// don't suggest imports from the default package or the current package
							if (! packageNames.contains(packageName)) {
								String fullName = curr.getFullyQualifiedName();
								String elemName = fullName.substring(packageName.length()+1); 
								packageNames.add(packageName);
								proposals.add(new AddImportProposal(false, context, elemName, packageName, 8));
							}
					}
				}
			} catch (CoreException e) {
			}
		} catch (InterruptedException e) {
		}
	}
		
	public AddImportProposal(boolean isX10, IQuickFixInvocationContext context,
			String typeName, String packageName, int priority) {
		super("Import '" + typeName + "' (" + packageName + ")" + (isX10 ? "" : " [java]"), context.getModel(), priority, null);
		this.context = context;
		setImage(JavaPluginImages.get(JavaPluginImages.IMG_OBJS_IMPDECL));
		// setImage(PluginImages.get(PluginImages.IMG_CORRECTION_ADD));
		this.typeName = typeName;
		this.packageName = packageName;
	}

	@Override
	protected void addEdits(IDocument document, TextEdit editRoot)
			throws CoreException {
		
		Object root = getCompilationUnit().getAST(new NullMessageHandler(),
				new NullProgressMonitor());
		if(! (root instanceof SourceFile_c)) {
			return;
		}
		Boolean before = true;
		SourceFile_c source = (SourceFile_c)root;
		PackageNode sourcePackage = source.package_();
		int packageOffset;
		if(sourcePackage == null) {
			packageOffset = source.position().offset();
		} else {
			 packageOffset = sourcePackage.position().endOffset() + 1;
			 before = false;
		}
		int lastOffset = packageOffset;
		List<Import> imports = source.imports();
		if(! imports.isEmpty()) {
			before = false;
			for(Import imp : imports) { 
				int endOffset = imp.position().endOffset();
				if(endOffset > lastOffset) {
					lastOffset = endOffset + 1;
				}
			}
		}
		
		String lineDelim = TextUtilities.getDefaultLineDelimiter(document);

		MultiTextEdit edit = new MultiTextEdit();
		//if(lastOffset == packageOffset) {
		//	edit.addChild(new InsertEdit(lastOffset, lineDelim));
		//}
		
		StringBuffer buf = new StringBuffer();
		if(! before) buf.append(lineDelim);
		buf.append("import ");
		buf.append(packageName);
		buf.append(".");
		buf.append(typeName);
		buf.append(";");
		if(before) buf.append(lineDelim);
		edit.addChild(new InsertEdit(lastOffset, buf.toString()));

		editRoot.addChild(edit);
		FormatUtils.format(LanguageRegistry.findLanguage("X10"), document, new Region(lastOffset, edit.getLength()));
	}	

	
}
