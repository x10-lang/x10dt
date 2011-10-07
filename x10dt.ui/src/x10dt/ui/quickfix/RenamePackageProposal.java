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

import org.eclipse.jface.text.contentassist.ICompletionProposal;

import org.eclipse.imp.services.IQuickFixInvocationContext;
import org.eclipse.imp.utils.FormatUtils;
import org.eclipse.imp.utils.NullMessageHandler;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextUtilities;

import org.eclipse.text.edits.DeleteEdit;
import org.eclipse.text.edits.InsertEdit;
import org.eclipse.text.edits.MultiTextEdit;
import org.eclipse.text.edits.ReplaceEdit;
import org.eclipse.text.edits.TextEdit;

import polyglot.ast.Import;
import polyglot.ast.PackageNode;
import polyglot.ast.SourceFile_c;
import polyglot.util.Position;
import x10dt.search.core.elements.ITypeInfo;
import x10dt.search.core.engine.X10SearchEngine;
import x10dt.search.core.engine.scope.SearchScopeFactory;
import x10dt.search.core.engine.scope.X10SearchScope;
import x10dt.ui.typeHierarchy.SearchUtils;

public class RenamePackageProposal extends CUCorrectionProposal {
	IQuickFixInvocationContext context;
	String packageName;

	
	public RenamePackageProposal(IQuickFixInvocationContext context, String packageName) {
		super("Change package declaration to " + (packageName.equals("") ? "use default package" : ("'" + packageName + "'")), context.getModel(), 8, null);
		this.context = context;
		setImage(JavaPluginImages.get(JavaPluginImages.IMG_CORRECTION_MOVE));
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
		
		String lineDelim = TextUtilities.getDefaultLineDelimiter(document);
		int initOffset;

		MultiTextEdit edit = new MultiTextEdit();
		if(packageName == "") {
			if(sourcePackage != null) {
				Position packPos = sourcePackage.position();
				initOffset = packPos.offset();
				edit.addChild(new DeleteEdit(initOffset, packPos.endOffset() - initOffset + 1));
			} else {
				return;
			}
		} else {
			StringBuffer buf = new StringBuffer();
			buf.append("package ");
			buf.append(packageName);
			buf.append(";");
			buf.append(lineDelim);

			if(sourcePackage != null) {
				Position packPos = sourcePackage.position();
				initOffset = packPos.offset();
				edit.addChild(new ReplaceEdit(initOffset, packPos.endOffset() - initOffset + 1, buf.toString()));
			} else {
				initOffset = source.position().offset();
				edit.addChild(new InsertEdit(initOffset, buf.toString()));
				
			}
			//if(lastOffset == packageOffset) {
			//	edit.addChild(new InsertEdit(lastOffset, lineDelim));
			//}

		}


		editRoot.addChild(edit);
		FormatUtils.format(LanguageRegistry.findLanguage("X10"), document, new Region(initOffset, edit.getLength()));

	}	

	
}
