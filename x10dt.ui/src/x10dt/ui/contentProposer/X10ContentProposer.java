/*******************************************************************************
 * Copyright (c) 2008 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Robert Fuhrer (rfuhrer@watson.ibm.com) - initial API and implementation

 *******************************************************************************/

/*
 * (C) Copyright IBM Corporation 2007
 * 
 * This file is part of the Eclipse IMP.
 */
package x10dt.ui.contentProposer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.eclipse.help.internal.base.BaseHelpSystem;
import org.eclipse.imp.editor.SourceProposal;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.services.IContentProposer;
import org.eclipse.imp.utils.HTMLPrinter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.templates.DocumentTemplateContext;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateContextType;

import polyglot.ast.Assign;
import polyglot.ast.Block;
import polyglot.ast.Call;
import polyglot.ast.Call_c;
import polyglot.ast.CanonicalTypeNode;
import polyglot.ast.ClassBody;
import polyglot.ast.ClassDecl;
import polyglot.ast.ClassMember;
import polyglot.ast.Eval;
import polyglot.ast.Eval_c;
import polyglot.ast.Field;
import polyglot.ast.FieldDecl;
import polyglot.ast.Field_c;
import polyglot.ast.Formal;
import polyglot.ast.Id;
import polyglot.ast.LocalDecl;
import polyglot.ast.MethodDecl;
import polyglot.ast.Node;
import polyglot.ast.Stmt;
import polyglot.types.ClassType;
import polyglot.types.FieldInstance;
import polyglot.types.ObjectType;
import polyglot.types.Type;
import polyglot.visit.NodeVisitor;
import x10.parser.X10Parsersym;
import x10.parserGen.X10Parser;
import x10.types.MethodInstance;
import x10dt.ui.parser.ParseController;
import x10dt.ui.parser.PolyglotNodeLocator;

/**
 * ContentProposer Specification:
 * The content proposer is activated using control space, and currently supports the following features:
 * 1. When the cursor is after a "." following a reference type, it displays all members of that type, filtering using the prefix.
 * 2. When the cursor is in the middle or right after an identifier, it proposes completion for that identifier using names that are in the scope.
 * 3. When the cursor is in between tokens, it proposes X10 code templates. It distinguishes between a class body, and a method body.
 * 4. When the cursor is right before a "def" method declaration, it proposes method modifiers.
 * 5. When the cursor is right before a "val" for a field declaration, it proposes field modifiers (global).
 * 
 * Note that these features are not necessarily mutually exclusive. E.g., if the cursor is before a def, it will propose both templates for a class body,
 * as well as method modifiers.
 * 
 * Note that currently these features only work in the absence of compilation errors.
 * 
 * @author mvaziri
 *
 */
public class X10ContentProposer implements IContentProposer, X10Parsersym { 
    
	private final static boolean EMPTY_PREFIX_MATCHES = true;
	
    private static final String HELP_PLUGIN_ID = "x10dt.help";
    private static final String LANG_REF_PREFIX = "/html/langref/";

    private static String getHelpURL(String pluginDocPath) {
        return BaseHelpSystem.resolve("/" + HELP_PLUGIN_ID + "/" + pluginDocPath, true).toString();
    }

    private static String getLangRefURL(String section) {
        return getHelpURL(LANG_REF_PREFIX + section);
    }

	private boolean emptyPrefixTest(boolean emptyPrefixMatches, String prefix){
		if (!emptyPrefixMatches)
			return !prefix.equals("");
		return true;
	}
	
	private void filterFields(List<FieldInstance> fields, List<FieldInstance> in_fields, String prefix, boolean emptyPrefixMatches) {
        for(FieldInstance f: in_fields) {
            String name= f.name().toString(); // PORT1.7 was f.name()
            if (emptyPrefixTest(emptyPrefixMatches, prefix) && name.startsWith(prefix))
                fields.add(f);
            
        }
        
    }

    private void filterMethods(List<MethodInstance> methods, List<MethodInstance> in_methods, String prefix, boolean emptyPrefixMatches) {
        for(MethodInstance m: in_methods) {
            String name= m.name().toString();
            // RMF 18 Oct 2010 - prune methods w/ identical signatures, since signatures are
            // what's presented in the list of proposals.
            if (emptyPrefixTest(emptyPrefixMatches, prefix) && name.startsWith(prefix) && !containsMethodSig(methods, m)) {
                methods.add(m);
            }
        }
    }

    private boolean containsMethodSig(List<MethodInstance> methods, MethodInstance m) {
        for(MethodInstance mi: methods) {
            if (mi.signature().equals(m.signature())) {
                return true;
            }
        }
		return false;
	}

	private void filterClasses(List<ClassType> classes, List<ClassType> in_classes, String prefix, boolean emptyPrefixMatches) {
        for(ObjectType r: in_classes) {
            ClassType c= r.toClass();
            String name= c.name().toString();
            if (emptyPrefixTest(emptyPrefixMatches, prefix) && name.startsWith(prefix)) {
                classes.add(c);
            }
        }
    }

    private void getFieldCandidates(Type container_type, List<FieldInstance> fields, String prefix, boolean emptyPrefixMatches) {
		if (container_type == null)
			return;

		if (container_type instanceof ObjectType) {
			ObjectType oType = (ObjectType) container_type;

			filterFields(fields, oType.fields(), prefix, emptyPrefixMatches);

			for (int i = 0; i < oType.interfaces().size(); i++) {
				ObjectType interf = (ObjectType) oType.interfaces().get(i);
				filterFields(fields, interf.fields(), prefix, emptyPrefixMatches);
			}

			getFieldCandidates(oType.superClass(), fields, prefix, emptyPrefixMatches);
		}
	}

    private void getMethodCandidates(ObjectType container_type, List<MethodInstance> methods, String prefix, boolean emptyPrefixMatches) {
        if (container_type == null)
            return;

        filterMethods(methods, container_type.methods(), prefix, emptyPrefixMatches);

        getMethodCandidates((ObjectType)container_type.superClass(), methods, prefix, emptyPrefixMatches);//PORT1.7 superType()->superClass()
    }

    private void getClassCandidates(Type type, List<ClassType> classes, String prefix, boolean emptyPrefixMatches) {
        if (type == null)
            return;
        ClassType container_type= type.toClass();
        if (container_type == null)
            return;

        filterClasses(classes, container_type.memberClasses(), prefix, emptyPrefixMatches);
       
        for(int i= 0; i < container_type.interfaces().size(); i++) {
            ClassType interf= ((ObjectType) container_type.interfaces().get(i)).toClass();
            filterClasses(classes, interf.memberClasses(), prefix, emptyPrefixMatches);
        }
        getClassCandidates(container_type.superClass(), classes, prefix, emptyPrefixMatches);//PORT1.7 superType)_->superClass()
    }

    private void getCandidates(ObjectType container_type, List<ICompletionProposal> list, String prefix, int offset, boolean emptyPrefixMatches) {//PORT1.7 RefType->ObjectType
        List<FieldInstance> fields= new ArrayList<FieldInstance>();
        List<MethodInstance> methods= new ArrayList<MethodInstance>();
        List<ClassType> classes= new ArrayList<ClassType>();

        getFieldCandidates(container_type, fields, prefix, emptyPrefixMatches);
        getMethodCandidates(container_type, methods, prefix, emptyPrefixMatches);
        getClassCandidates((Type) container_type, classes, prefix, emptyPrefixMatches);

        for(FieldInstance field : fields) {
            list.add(new SourceProposal(field.name().toString(), prefix, offset)); //PORT1.7 name() replaced with name().toString()
           
        }

        for(MethodInstance method : methods) {
            list.add(new SourceProposal(removeTypeFromSignature(method.signature()), prefix, offset));
        }

        for(ClassType type : classes) {
            if (!type.isAnonymous())
                list.add(new SourceProposal(type.name().toString(), prefix, offset));//PORT1.7 name() replaced with name().toString()
        }
    }
    
    private String removeTypeFromSignature(String signature){
    	String ret = signature;
    	int i = signature.indexOf(":");
    	if (i == -1)
    		return signature;
    	return signature.substring(0,i);
    }

    private static final String CONTEXT_ID= "x10Source";

    private TemplateContextType fContextType= new TemplateContextType(CONTEXT_ID, "Coding Templates");

    // Declarations
    private final Template fVariableDeclaration = new Template("var", "mutable variable/field declaration", CONTEXT_ID, "var ${name}:${typename};", false );
    private final Template fValueDeclaration = new Template("val", "immutable variable/field declaration", CONTEXT_ID, "val ${name} = ${expression};", false );
    /* CHANGED */ private final Template fMethodTemplate = new Template("def", "method declaration", CONTEXT_ID, "def ${name}(${x}:${typename1}){${constraint}}:${typename2} {  }", false );
    private final Template fMainMethod = new Template("main method", "main method", CONTEXT_ID, "public static def main(argv:Rail[String]) {  }", false);   
    private final Template fConstructorTemplate = new Template("this", "constructor declaration", CONTEXT_ID, "def this(${argname}:${argtype}) {  }", false);
    private final Template fStructTemplate = new Template("struct", "struct declaration", CONTEXT_ID, "struct ${name} {  }", false );
    private final Template fGenericParameter = new Template("[X]", "generic type parameter", CONTEXT_ID, "[${X}]", false);
//    private final Template fCovariantGeneric = new Template("[+X]", "generic type, covariant parameter", CONTEXT_ID, "[+${X}]", false);
//    private final Template fContravariantGeneric = new Template("[-X]", "generic type, contravariant parameter", CONTEXT_ID, "[-${X}]", false);
    private final Template fDependentTypeDeclaration = new Template("dependent type", "class declaration with property", CONTEXT_ID, "class ${name}(${x}:${typename}) { def this(${x}:${typename}){ property(${x}); } }", false);
    private final Template fTypeDefinition = new Template("type", "type definition", CONTEXT_ID, "type ${name1} = ${name2};", false);
    private final Template fFunctionType = new Template("function", "function definition", CONTEXT_ID, "var ${fname}:(${T1},${T2}) => ${T} = (${x1}:${T1}, ${x2}:${T2}) => ${expression};", false);
   
    //say static val instead.
    private final Template fConstDeclaration = new Template("static val", "immutable static field declaration", CONTEXT_ID, "static val ${name} = ${expression};", false );
    /*CHANGED*/ private final Template fPropertyDeclaration = new Template("property", "property declaration", CONTEXT_ID, "property ${name}() = ${expression};", false);
    private final Template fClassTemplate = new Template("class", "class declaration", CONTEXT_ID, "class ${name} {  }", false);
    
    // Constructs
    /*CHANGED*/ private final Template fAsyncTemplate= new Template("async", "async statement", CONTEXT_ID, "async { ${stmt} }", false);
    private final Template fAtStatementTemplate = new Template("at","at statement", CONTEXT_ID, "at (${place}) { ${stmt} }", false );
    private final Template fAtExpressionTemplate = new Template("at", "at expression", CONTEXT_ID, "at (${place}) ${expression}", false);
    private final Template fFinishTemplate = new Template("finish", "finish statement", CONTEXT_ID, "finish { ${stmt} }", false);
    private final Template fAtEachTemplate= new Template("ateach", "ateach statement", CONTEXT_ID, "ateach (${x}:Point in ${distribution}) { ${stmt} }", false);
    private final Template fAtomicTemplate= new Template("atomic", "atomic statement", CONTEXT_ID, "atomic { ${stmt} }", false);
    private final Template fWhenTemplate= new Template("when", "when statement", CONTEXT_ID, "when (${condition}) { ${stmt} }", false);
    private final Template fCoercionTemplate = new Template("as", "coercion", CONTEXT_ID, "${expression} as ${typename}" , false);
    
    // Places, Regions, Distributions, Arrays
    /*CHANGED*/private final Template fRegion1DTemplate= new Template("region 1-D", "1-dimensional region creation", CONTEXT_ID, "${lower}..${upper}", false);
    /*CHNGED*/private final Template fRegion2DTemplate= new Template("region 2-D", "2-dimensional region creation", CONTEXT_ID, "(${lower1}..${upper1})*(${lower2}..${upper2})", false);
    /*CHANGED*/ private final Template fArrayNewTemplate= new Template("array new", "array instantiation", CONTEXT_ID, "var ${name}: Array[${typename}] = new Array[${typename}](${distribution} , (p: Point) => ${initialization_expression} );", false);
    /*CHANGED*/ private final Template fDistArrayNewTemplate= new Template("dist array new", "dist array instantiation", CONTEXT_ID, "var ${name}: DistArray[${typename}] = DistArray.make[${typename}](${distribution} , (p: Point) => ${initialization_expression} );", false);
    private final Template fArrayAccessTemplate = new Template("dist array access", "remote array access", CONTEXT_ID, "at(${array_name}.dist(${point})) ${array_name}(${point}) = ${expression};", false);
    
    // Modifiers
    private final Template fConstrainedType = new Template("constraint", "constrained type definition", CONTEXT_ID, "type ${typename1} = ${typename2}{${constraint}};", false);
    private final Template fPrivateTemplate = new Template("private", "private qualifier", CONTEXT_ID, "private ", false);
    private final Template fPropertyTemplate = new Template("property", "property qualifier", CONTEXT_ID, "property ", false);   
    private final Template fFinalTemplate = new Template("final", "final modifier", CONTEXT_ID, "final ", false);
    private final Template fAbstractTemplate = new Template("abstract", "abstract modifier", CONTEXT_ID, "abstract", false);
    
    // Clocks
    private final Template fClockedStatement = new Template("clocked", "clocked statement", CONTEXT_ID, "async clocked (${clock}) { ${stmt} }", false);
    
    // Misc
    /*CHANGED*/ private final Template fInstanceofTemplate = new Template("instanceof", "instanceof in a conditional", CONTEXT_ID, "if (${name1} instanceof ${typename}) { val ${name2} = ${name1} as ${typename}; }", false);
    private final Template fPrintTemplate = new Template("printing", "printing to console", CONTEXT_ID, "Console.OUT.println(\"${string}\");", false);
    
     
    private final Template[] fTemplates= new Template[] {  
            fArrayNewTemplate, fDistArrayNewTemplate, fArrayAccessTemplate, fAsyncTemplate, fAtStatementTemplate,
            fAtEachTemplate, fAtomicTemplate,  
            fClockedStatement, fConstrainedType, fFinishTemplate,  
            fFunctionType, fInstanceofTemplate, fPrintTemplate,
            fTypeDefinition, fValueDeclaration, fVariableDeclaration,   fWhenTemplate
    };
    
    
    private static final Map<Template,String> infos = new HashMap<Template,String>();
   
    {
    	infos.put(fArrayNewTemplate,    "New Array");
    	infos.put(fArrayAccessTemplate, "Array access");
    	infos.put(fCoercionTemplate,    "Coercion");
    	infos.put(fAsyncTemplate,       "Async statement");
    	infos.put(fAtStatementTemplate, "At statement");
    	infos.put(fAtEachTemplate,      "Ateach statement");
    	infos.put(fAtomicTemplate,      "Atomic statement");
    	infos.put(fClockedStatement,    "Clocked statement");
    	infos.put(fConstrainedType,     "Constrained type");
    	infos.put(fFinishTemplate,      "Finish statement");
    	infos.put(fFunctionType,        "Function Type");
    	infos.put(fRegion1DTemplate,    "1-D region");
    	infos.put(fRegion2DTemplate,    "2-D Region");
    	infos.put(fTypeDefinition,      "Type definition");
    	infos.put(fValueDeclaration,    "Value declaration");
    	infos.put(fVariableDeclaration, "Variable declaration");
    	infos.put(fWhenTemplate,        "When statement");
    	infos.put(fPropertyDeclaration, "Property declaration");
    	infos.put(fClassTemplate,       "Class declaration");
    	infos.put(fPrintTemplate,       "Print statement");
    }

    private static final Map<Template,String> templateLangRefPages = new HashMap<Template,String>();

    {
        templateLangRefPages.put(fArrayNewTemplate,    "arr-initializer.html");
        templateLangRefPages.put(fArrayAccessTemplate, "arr-ops.html");
        templateLangRefPages.put(fCoercionTemplate,    "exp-coercions.html");
        templateLangRefPages.put(fAsyncTemplate,       "act-spawn.html");
        templateLangRefPages.put(fAtStatementTemplate, "act-placechanging.html");
        templateLangRefPages.put(fAtEachTemplate,      "act-ateach.html");
        templateLangRefPages.put(fAtomicTemplate,      "act-atomicblocks.html");
        templateLangRefPages.put(fClockedStatement,    "clocks.html");
        templateLangRefPages.put(fConstrainedType,     "type-constrained.html");
        templateLangRefPages.put(fFinishTemplate,      "act-finish.html");
        templateLangRefPages.put(fFunctionType,        "functions.html");
        templateLangRefPages.put(fRegion1DTemplate,    "arr-regions.html");
        templateLangRefPages.put(fRegion2DTemplate,    "arr-regions.html");
        templateLangRefPages.put(fTypeDefinition,      "typ-typedefs.html");
        templateLangRefPages.put(fValueDeclaration,    "var-final.html");
        templateLangRefPages.put(fVariableDeclaration, "var-localvars.html");
        templateLangRefPages.put(fWhenTemplate,        "act-atomicblocks.html");
        templateLangRefPages.put(fPropertyDeclaration, "cla-properties.html");
        templateLangRefPages.put(fClassTemplate,       "cla-principles.html");
        templateLangRefPages.put(fPrintTemplate,       "");
    }

  
    public ICompletionProposal[] getContentProposals(IParseController controller, int offset, ITextViewer viewer) {
    	
        ArrayList<ICompletionProposal> list= new ArrayList<ICompletionProposal>();
 
        CommonTokenStream tokens = ((ParseController) controller).getTokens();
        
        Token tokenToComplete = null;
        Token previousToken = null;
        
        for(Token t: tokens.getTokens()){
    		if (t.getChannel() == Token.DEFAULT_CHANNEL){
    			if (t.getStartIndex() <= offset && t.getStopIndex() + 1 >= offset){
    				tokenToComplete = t;
    				break;
    			}
    			if (t.getStartIndex() > offset){
    				break;
    			}
    			previousToken = t;
    		}
    		
    	}
        
        String prefix= tokenToComplete==null?"":computePrefixOfToken(tokenToComplete, offset, (ParseController) controller);
       
        PolyglotNodeLocator locator= new PolyglotNodeLocator(controller.getProject()/*,((ParseController) controller).getLexStream()*/)  ;
        Node currentAst= (Node) controller.getCurrentAst();
        Node node= tokenToComplete!=null?(Node) locator.findNode(currentAst, tokenToComplete.getStartIndex(), tokenToComplete.getStopIndex()):null;
        Node previousNode = (previousToken != null)? (Node) locator.findNode(currentAst, previousToken.getStartIndex(), previousToken.getStopIndex()): null;
     
        if (node !=null && node instanceof Eval && tokenToComplete.getType() == X10Parser.DOT){
        	Type type = ((Eval_c) node).expr().type();
        	if (type != null && type.isReference()){
        		getCandidates((ObjectType) type, list, prefix, offset, true);
        	}
        } else if (node !=null && node instanceof Id && previousNode instanceof Field){
        	Type type = ((Field_c) previousNode).target().type();
        	if (type != null && type.isReference()){
        		getCandidates((ObjectType) type, list, prefix, offset, true);
        	}
        } else if (node != null && node instanceof Id && previousNode instanceof Call){
        	Type type = ((Call_c) previousNode).target().type();
        	if (type != null && type.isReference()){
        		getCandidates((ObjectType) type, list, prefix, offset, true);
        	}
        
        //The next case completes an Id with names in scope  
        } else if (node != null && node instanceof Id){ 
        	Node n = (node instanceof Id)? node : previousNode;
        	String pref = (node instanceof Id)? prefix : computePrefixOfToken(previousToken, offset, (ParseController) controller);
        	addNamesInScope(currentAst, n, pref, list, offset, !EMPTY_PREFIX_MATCHES);
        
        } else if (node == null){ //TODO: WIP 
        	//Display templates, names in scope -- index < 0 when we are at a white space or comment
            Node location = location(previousNode, node, locator, currentAst);
            if (location instanceof Block && (justAfter(TK_SEMICOLON, previousToken) || justAfter(TK_RBRACE, previousToken) || justAfter(TK_LBRACE, previousToken))){ //Statement context. 
        		addTemplateProposals(offset, viewer, list, prefix, fTemplates);
        		//addNamesInScope(currentAst, node, prefix, list, offset, EMPTY_PREFIX_MATCHES);
        	}
            else if (justAfter(TK_EQUAL, previousToken) && (location instanceof Assign || location instanceof LocalDecl)){
            	Template[] templates = new Template[]{fAtExpressionTemplate, fCoercionTemplate,  fRegion1DTemplate, fRegion2DTemplate};
            	addTemplateProposals(offset, viewer, list, prefix, templates );
            }
        	else if (location instanceof ClassBody){ //Class context
        		Template[] templates = new Template[]{fVariableDeclaration, fValueDeclaration, fConstDeclaration, fPropertyDeclaration, fMainMethod, fMethodTemplate, fConstructorTemplate, 
        			fClassTemplate, fStructTemplate, fDependentTypeDeclaration, };
        		addTemplateProposals(offset, viewer, list, prefix, templates);
            
        	} 
            
            //add method modifiers before a "def"
            if (justBefore(TK_def, tokenToComplete, previousToken, offset)){
            	Template[] templates = new Template[]{fAbstractTemplate, fFinalTemplate, fPrivateTemplate, fPropertyTemplate };
        		addTemplateProposals(offset, viewer, list, prefix, templates);
            }
            
            //add class modifiers before a "class"
            if (justBefore(TK_class, tokenToComplete, previousToken, offset)) {
            	Template[] templates = new Template[]{fAbstractTemplate, fFinalTemplate};
        		addTemplateProposals(offset, viewer, list, prefix, templates);
            }
        	
            //add field modifiers before a "val" field declaration
            if (location instanceof ClassBody && (justBefore(TK_val, tokenToComplete, previousToken, offset))){
        		Template[] templates = new Template[]{fPropertyTemplate};
        		addTemplateProposals(offset, viewer, list, prefix, templates);
        	}
            
            //add type parameter in a class declaration
            if (location instanceof ClassDecl && previousNode instanceof CanonicalTypeNode &&  
            		offset == previousToken.getStopIndex() + 1){
           		Template[] templates = new Template[]{fGenericParameter};
        		addTemplateProposals(offset, viewer, list, prefix, templates);
            }
        }
               
        return (ICompletionProposal[]) list.toArray(new ICompletionProposal[list.size()]);
    }

    // Tests if we are just before a token of kind tokenKind, with white space before the current cursor position
    private boolean justBefore(int tokenKind, Token tokenToComplete, Token previousToken, int offset){
    	return tokenToComplete.getType() == tokenKind && offset > previousToken.getStopIndex() + 1;
    }
    
    // Tests if we are just after a token of kind tokenKind
    private boolean justAfter(int tokenKind, Token previousToken){
    	return previousToken.getType() == tokenKind;
    }
    
    // Finds the least common parent of prev and next in the currentAst. The reason for using our own visitor is that polyglot parent finder changes the AST as it goes along.
    private Node location(final Node prev, final Node next, PolyglotNodeLocator locator, Node currentAst){    	if (prev == null)
    		return next;
    	
    	if (next == null)
    		return null;
    	
    	final Stack<Node> nextParents = new Stack<Node>();
        final Stack<Node> prevParents = new Stack<Node>();
       
        currentAst.visit(new NodeVisitor(){
        	boolean prevDone, nextDone = false;
        	
        	@Override
        	public NodeVisitor enter(Node parent, Node child){
        		if (!prevDone){
        			prevParents.push(child);
        			if (child == prev) prevDone = true;
        		} 
        		if (!nextDone){
        			nextParents.push(child);
        			if (child == next) nextDone = true;
        		}
        		return super.enter(parent, child);
        	}
        	
        	public Node leave(Node parent, Node old, Node n, NodeVisitor v){
        		if (!prevDone) prevParents.pop();
        		if (!nextDone) nextParents.pop();
        		return super.leave(parent, old, n, v);
        	}
        });
        
        
    	for(int i = prevParents.size()-1; i >= 0; i--){
    		if (nextParents.contains(prevParents.get(i))) return prevParents.get(i);
    	}
    	return null;
    }
  
   
    
    private void addTemplateProposals(int offset, ITextViewer viewer, ArrayList<ICompletionProposal> list, String prefix, Template[] templates) {
        IDocument doc= viewer.getDocument();
        Region r= new Region(offset, prefix.length());
        TemplateContext tc= new DocumentTemplateContext(fContextType, doc, offset - prefix.length(), prefix.length());

        for(int i= 0; i < templates.length; i++) {
        	addTemplateProposalIfMatch(list, templates[i], tc, r, prefix);
        }
    }


    private void addTemplateProposalIfMatch(ArrayList<ICompletionProposal> proposals, Template template, TemplateContext tc, Region r, String prefix) {
    	if (template.getName().startsWith(prefix)) {
    		String html = createHtml(template);
    		X10BrowserInformationControlInput info = new X10BrowserInformationControlInput(null, html, template.getName());
    		ICompletionProposal proposal = new X10TemplateProposal(template, tc, r, null, info);
    		proposals.add(proposal);
        }
    }
    
    private String createHtml(Template template) {
    	StringBuffer buffer= new StringBuffer();
    	HTMLPrinter.insertPageProlog(buffer, 0);
    	buffer.append(X10BrowserInformationControlInput.PATTERN); //Ugly HACK!!! need a placeholder for pattern (it will be evaluated later).
    	//String base = "PLUGINS_ROOT/x10dt.help/html/langref/variables.html";

    	String langRefURL = getHelpURL(LANG_REF_PREFIX + "toc.html");
    	String templateInfo = infos.get(template);
        String templateLangRefPage = templateLangRefPages.get(template);

    	if (templateInfo != null)
    		buffer.append("\n\n\n<h4>"+ templateInfo + "</h4>\n"); 
    	else buffer.append("\n<h4></h4>\n");

        if (templateLangRefPage != null && templateLangRefPage.length() > 0) {
    	    buffer.append("\n<a href=\"" + getLangRefURL(templateLangRefPage) + "\">" + " relevant section in the X10 Language Reference</a>\n");
    	} else {
    	    buffer.append("<a href=\"" + langRefURL + "\">X10 Language Reference</a>\n");
    	}
		HTMLPrinter.addPageEpilog(buffer);
		return buffer.toString();
    }
  

    private String computePrefixOfToken(Token tokenToComplete, int offset, ParseController pc) {
        String prefix= "";
        if (tokenToComplete.getType() == X10Parser.IDENTIFIER || /*tokenToComplete.getType() == X10Parser.ErrorId ||*/ pc.isKeyword(tokenToComplete.getType())) {
            if (offset >= tokenToComplete.getStartIndex() && offset <= tokenToComplete.getStopIndex() + 1) {
                prefix= tokenToComplete.getText().toString().substring(0, offset - tokenToComplete.getStartIndex());
            }
        }
        return prefix;
    }
    
   

    private void addNamesInScope(Node currentAst, final Node in_node, String prefix, List<ICompletionProposal> proposals, int offset, boolean emptyPrefixMatches) {
        // Polyglot can't supply the pkg/class names, so we'll have to appeal to the search index
        if (emptyPrefixTest(emptyPrefixMatches, prefix) && "this".startsWith(prefix)) // Should check that we're not in a static method or initializer
            proposals.add(new SourceProposal("this", prefix, offset));
        if (emptyPrefixTest(emptyPrefixMatches, prefix) && "here".startsWith(prefix)) // Should check that we're not in a static method or initializer
            proposals.add(new SourceProposal("here", prefix, offset));
//        if (emptyPrefixTest(emptyPrefixMatches, prefix) && "self".startsWith(prefix)) // Should check that we're not in a static method or initializer
//            proposals.add(new SourceProposal("self", prefix, offset));
        final Stack<Node> path= new Stack<Node>();
        currentAst.visit(new NodeVisitor() {
        	boolean done = false;
        	@Override
        	public NodeVisitor enter(Node parent, Node child){
        		if (!done) {
        			if (parent != null) path.push(parent);
        			if (child == in_node) {
        				path.push(child);
        				done = true;
        			}
        		}
        		return super.enter(parent, child);
        	}
        	
            @Override
            public Node leave(Node parent, Node old, Node n, NodeVisitor v) {
            	if (!done) path.pop();
                return super.leave(parent, old, n, v);
            }
        });
        
        for(Node node : path) {
        	if (node instanceof ClassBody){
        		ClassBody cd = (ClassBody) node;
        		List<ClassMember> members = cd.members();
        		for(ClassMember m: members){
        			if (m instanceof FieldDecl){
        				String fname = ((FieldDecl)m).name().id().toString();
        				if (emptyPrefixTest(emptyPrefixMatches, prefix) && fname.startsWith(prefix)) { //PORT1.7 name() changed to name().id().toString()
                            proposals.add(new SourceProposal(fname, fname, prefix, offset)); //PORT1.7 use cached value
                        }
        			}
        		}
        	}
            if (node instanceof MethodDecl) {
                MethodDecl md= (MethodDecl) node;
                List<Formal> formals= md.formals();
                for(Formal formal : formals) {
                	String fname = formal.name().id().toString(); //PORT1.7 name() changed to name().id().toString(); cached here for reuse
                    if (emptyPrefixTest(emptyPrefixMatches, prefix) && fname.startsWith(prefix)) { //PORT1.7 name() changed to name().id().toString()
                        proposals.add(new SourceProposal(fname, fname, prefix, offset)); //PORT1.7 use cached value
                    }
                } 
            }
            
            if (node instanceof Block) {
                Block b= (Block) node;
                List<Stmt> stmts= b.statements();
                for(Stmt s : stmts) {
                	if (s.position().offset() > offset) {
                        // Don't include declarations that follow the current cursor pos
                        continue;
                    }
                    if (s instanceof LocalDecl) {
                        LocalDecl decl= (LocalDecl) s;
                        String ldname = decl.name().id().toString();// PORT1.7 changed name() to name().id().toString(); cached
                        if (emptyPrefixTest(emptyPrefixMatches, prefix) && ldname.startsWith(prefix)) { // PORT1.7 changed name() to name().id().toString(); cached
                            proposals.add(new SourceProposal(ldname, ldname, prefix, offset)); //PORT1.7 use cached value
                        }
                    }
                }
            }
           
            
        }
    }

}