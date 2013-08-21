package x10dt.ui.quickfix;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.imp.editor.hover.ProblemLocation;
import org.eclipse.imp.editor.quickfix.IAnnotation;
import org.eclipse.imp.parser.IMessageHandler;
import org.eclipse.imp.services.IQuickFixInvocationContext;
import org.eclipse.imp.services.base.DefaultQuickFixAssistant;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.projection.AnnotationBag;
import org.eclipse.ui.texteditor.MarkerAnnotation;

import polyglot.util.CodedErrorInfo;

public class QuickFixAssistant extends DefaultQuickFixAssistant {

	public QuickFixAssistant() {

	}

	public boolean canAssist(IQuickFixInvocationContext invocationContext) {
		return false;
	}

	public boolean canFix(Annotation annotation) {
//		if(annotation instanceof AnnotationBag) {
//			// For some reason, AnnotationBag does not implement Iterable
//			Iterator<Annotation> annIt = ((AnnotationBag)annotation).iterator(); 
//			while(annIt.hasNext()) {
//				Annotation ann = annIt.next();
//				if(canFix(ann)) {
//					return true;
//				}
//			}
//			return false;
//		}

		int errorCode = -1;
		if (annotation instanceof IAnnotation) {
			errorCode = ((IAnnotation) annotation).getId();
		}
		

		if (annotation instanceof MarkerAnnotation) {
			errorCode = (((MarkerAnnotation) annotation).getMarker()
					.getAttribute(IMessageHandler.ERROR_CODE_KEY, -1));
		}

		switch (errorCode) {
		case CodedErrorInfo.ERROR_CODE_SURROUND_THROW:
		case CodedErrorInfo.ERROR_CODE_METHOD_NOT_FOUND:
		case CodedErrorInfo.ERROR_CODE_CONSTRUCTOR_NOT_FOUND:
		case CodedErrorInfo.ERROR_CODE_METHOD_NOT_IMPLEMENTED:
		case CodedErrorInfo.ERROR_CODE_TYPE_NOT_FOUND:
		case CodedErrorInfo.ERROR_CODE_WRONG_PACKAGE:
			return true;
		}

		return false;
	}

	public String[] getSupportedMarkerTypes() {
		return new String[]{"x10dt.core.problemMarker"};
	}
	
	public void addProposals(IQuickFixInvocationContext context,
			ProblemLocation problem, Collection<ICompletionProposal> proposals) {
		int id = problem.getProblemId();
		switch (id) {
		case CodedErrorInfo.ERROR_CODE_SURROUND_THROW:
			proposals.add(new SurroundThrowProposal(context));
			break;
		case CodedErrorInfo.ERROR_CODE_METHOD_NOT_FOUND:
			proposals.add(new CreateMethodProposal(context, problem.getAttribute("METHOD", ""), problem.getAttribute("ARGUMENTS", "")));
			break;
		case CodedErrorInfo.ERROR_CODE_CONSTRUCTOR_NOT_FOUND:
			proposals.add(new ConstructorFromSuperclassProposal(context,
					problem.getAttribute("CONSTRUCTOR", ""), problem.getAttribute("ARGUMENTS", "")));
			break;
		case CodedErrorInfo.ERROR_CODE_METHOD_NOT_IMPLEMENTED:
			proposals.add(new MakeAbstractProposal(context, problem.getAttribute("CLASS", "")));
			
			if(proposals.size() > 0)
			{
				for(ICompletionProposal proposal : proposals)
				{
					if(proposal instanceof UnimplementedMethodProposal)
					{
						return;
					}
				}
			}
			
			proposals.add(new UnimplementedMethodProposal(context));
			
			break;
		// Class not found Exception
		case CodedErrorInfo.ERROR_CODE_TYPE_NOT_FOUND: 
			AddImportProposal.addImportProposals(proposals, context, new NullProgressMonitor(), problem.getAttribute("TYPE", ""));
			break;
		case CodedErrorInfo.ERROR_CODE_WRONG_PACKAGE:
			String correctPackage = problem.getAttribute("ACTUAL_PACKAGE", "/");
			String declaredPackage = problem.getAttribute("DECLARED_PACKAGE", "/");
			if(correctPackage != null && ! correctPackage.equals("/")) {
				proposals.add(new RenamePackageProposal(context, declaredPackage, correctPackage));
			}
		default:
		}
	}
}
