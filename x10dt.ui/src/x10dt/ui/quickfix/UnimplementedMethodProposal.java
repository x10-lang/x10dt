package x10dt.ui.quickfix;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.imp.editor.quickfix.CUCorrectionProposal;
import org.eclipse.imp.language.LanguageRegistry;
import org.eclipse.imp.parser.ISourcePositionLocator;
import org.eclipse.imp.runtime.PluginImages;
import org.eclipse.imp.services.IQuickFixInvocationContext;
import org.eclipse.imp.utils.FormatUtils;
import org.eclipse.imp.utils.NullMessageHandler;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.text.edits.InsertEdit;
import org.eclipse.text.edits.MultiTextEdit;
import org.eclipse.text.edits.TextEdit;

import polyglot.ast.Block;
import polyglot.ast.ClassDecl;
import polyglot.ast.FlagsNode;
import polyglot.ast.Formal;
import polyglot.ast.MethodDecl;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;
import polyglot.types.ClassType;
import polyglot.types.Flags;
import polyglot.types.MethodInstance;
import polyglot.types.QName;
import polyglot.types.Type;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.util.SimpleCodeWriter;
import polyglot.visit.PrettyPrinter;
import x10.ExtensionInfo;
import x10.ast.X10NodeFactory_c;
import x10dt.core.utils.HierarchyUtils;
import x10dt.ui.X10DTUIPlugin;
import x10dt.ui.parser.PolyglotNodeLocator;

public class UnimplementedMethodProposal extends CUCorrectionProposal {
	IQuickFixInvocationContext context;
	
	ISourcePositionLocator nodeLocator;

	public UnimplementedMethodProposal(IQuickFixInvocationContext context) {
		super("Add unimplemented methods", context.getModel(), 8, null);
		this.context = context;
		setImage(PluginImages.get(PluginImages.IMG_CORRECTION_CHANGE));
	}

	@Override
	protected void addEdits(IDocument document, TextEdit editRoot)
			throws CoreException {
		Object root = getCompilationUnit().getAST(new NullMessageHandler(),
				new NullProgressMonitor());
		
		PolyglotNodeLocator pnl = new PolyglotNodeLocator(getCompilationUnit().getProject(), null);
		ClassDecl classDecl = null;
		Node coveringNode = (Node) pnl.findNode(root, context.getOffset());

		if (coveringNode instanceof ClassDecl) {
			classDecl = (ClassDecl) coveringNode;
		}

		else if (coveringNode instanceof FlagsNode) {
			classDecl = (ClassDecl) pnl.findParentNode(root, context
					.getOffset());
		}

		// TODO this will only look at 1 superclass, needs to look at all ancestors
		if (classDecl != null) {
			
			Set<ClassType> superClasses = HierarchyUtils.getSuperClasses(classDecl.classDef().asType());
			Set<ClassType> interfaces = HierarchyUtils.getInterfaces(superClasses);
			
			Set<MethodInstance> implementedMethods = HierarchyUtils.getImplementedMethods(superClasses);
			Set<MethodInstance> abstractMethods = HierarchyUtils.getMethods(superClasses, Flags.ABSTRACT);
			Set<MethodInstance> interfaceMethods = HierarchyUtils.getMethods(interfaces);
			
			MultiTextEdit edit = new MultiTextEdit();
			
			String lineDelim = TextUtilities.getDefaultLineDelimiter(document);
			List<MethodInstance> unimplemented = new ArrayList<MethodInstance>();

//			LanguageServiceManager man = new LanguageServiceManager(getCompilationUnit().getParseController().getLanguage());
//			man.getFormattingStrategy().format(parseController, content, isLineStart, indentation, positions);
//			
			
			for (MethodInstance mi : abstractMethods) {
				if(!implementedMethods.contains(mi))
				{
					unimplemented.add(mi);
				}
			}
			
			for (MethodInstance mi : interfaceMethods) {
				if(!implementedMethods.contains(mi))
				{
					unimplemented.add(mi);
				}
			}

			if (!unimplemented.isEmpty()) {
				int offset = classDecl.body().position().endOffset() - 1;
				X10NodeFactory_c factory = new X10NodeFactory_c(new ExtensionInfo());
				
				for (MethodInstance mi : unimplemented) {
					
					try {
						List<Formal> formals = new ArrayList<Formal>();
						
						FlagsNode flags = factory.FlagsNode(null, mi.flags().clearAbstract());
						TypeNode returnType = factory.TypeNodeFromQualifiedName(new Position("", ""), QName.make(mi.returnType().toString()));
						
						for(Type formal : mi.formalTypes())
						{
							ClassType ct = (ClassType)formal;
							formals.add(factory.Formal(null, factory.FlagsNode(null, Flags.NONE), factory.TypeNodeFromQualifiedName(new Position("", ""), ct.fullName()), factory.Id(null, ct.name())));
							System.out.print("");
							
						}
						
						Block body = factory.Block(null);
						MethodDecl newMethodDecl = factory.MethodDecl(null, flags, returnType, factory.Id(null, mi.name()), formals, body);
					
						StringWriter sw = new StringWriter();
						CodeWriter cw = new SimpleCodeWriter(sw, 1);
						newMethodDecl.prettyPrint(cw, new PrettyPrinter());
						cw.flush();
						edit.addChild(new InsertEdit(offset, sw.toString()));
						edit.addChild(new InsertEdit(offset, lineDelim));
						edit.addChild(new InsertEdit(offset, lineDelim));
					} catch (Exception e) {
						X10DTUIPlugin.log(e);
					}
				}
				editRoot.addChild(edit);
				FormatUtils.format(LanguageRegistry.findLanguage("X10"), document, new Region(offset, editRoot.getLength()));
			}
		}
		
		// TODO quickfix failed should we show a popup?
	}
}
