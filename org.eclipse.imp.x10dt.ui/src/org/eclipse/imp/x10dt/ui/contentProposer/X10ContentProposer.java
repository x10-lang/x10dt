package x10.safari.contentProposer;

import java.util.ArrayList;
import java.util.List;

import lpg.lpgjavaruntime.IToken;
import lpg.lpgjavaruntime.PrsStream;

import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.uide.editor.IContentProposer;
import org.eclipse.uide.editor.SourceProposal;
import org.eclipse.uide.parser.Ast;
import org.eclipse.uide.parser.IParseController;

import polyglot.ast.*;
import polyglot.ext.jl.ast.Field_c;
import polyglot.ext.x10.ast.X10CanonicalTypeNode_c;
import polyglot.types.ClassType;
import polyglot.types.ConstructorInstance;
import polyglot.types.FieldInstance;
import polyglot.types.MethodInstance;
import polyglot.types.Qualifier;
import polyglot.types.ReferenceType;
import polyglot.types.Type;

import x10.parser.X10Parsersym;
import x10.uide.parser.PolyglotNodeLocator;

public class X10ContentProposer implements IContentProposer, X10Parsersym
{
    private void filterFields(ArrayList fields, List in_fields, String prefix)
    {
        for (int i = 0; i < in_fields.size(); i++)
        {
            FieldInstance f = (FieldInstance) in_fields.get(i);
            String name = f.name();
            if (name.length() >= prefix.length() && prefix.equals(name.substring(0, prefix.length())))
                fields.add(f);
        }
    }
    
    private void filterMethods(ArrayList methods, List in_methods, String prefix)
    {
        for (int i = 0; i < in_methods.size(); i++)
        {
            MethodInstance m = (MethodInstance) in_methods.get(i);
            String name = m.name();
            if (name.length() >= prefix.length() && prefix.equals(name.substring(0, prefix.length())))
                methods.add(m);
        }
    }

    private void filterClasses(ArrayList classes, List in_classes, String prefix)
    {
        for (int i = 0; i < in_classes.size(); i++)
        {
            ClassType c = ((ReferenceType) in_classes.get(i)).toClass();
            String name = c.name();
            if (name.length() >= prefix.length() && prefix.equals(name.substring(0, prefix.length())))
            {
                //if (name.length() == prefix.length())
                //    ; // TODO: highlight class name in YELLOW in editor
                //else 
                classes.add(c);
            }
        }
    }
    
    private void getFieldCandidates(ReferenceType container_type, ArrayList fields, String prefix)
    {
        if (container_type == null)
            return;
        
        if (prefix.length() == 0)
             fields.addAll(container_type.fields());
        else filterFields(fields, container_type.fields(), prefix);

        for (int i = 0; i < container_type.interfaces().size(); i++)
        {
            ReferenceType interf = (ReferenceType) container_type.interfaces().get(i);
            if (prefix.length() == 0)
                 fields.addAll(interf.fields());
            else filterFields(fields, interf.fields(), prefix);
        }
        
        getFieldCandidates((ReferenceType) container_type.superType(), fields, prefix);
    }

    private void getMethodCandidates(ReferenceType container_type, ArrayList methods, String prefix)
    {
        if (container_type == null)
            return;
        
        if (prefix.length() == 0)
             methods.addAll(container_type.methods());
        else filterMethods(methods, container_type.methods(), prefix);

        getMethodCandidates((ReferenceType) container_type.superType(), methods, prefix);
    }

    private void getClassCandidates(Type type, ArrayList classes, String prefix)
    {
        if (type == null)
            return;
        ClassType container_type = type.toClass();
        if (container_type == null)
            return;
        
        if (prefix.length() == 0)
             classes.addAll(container_type.memberClasses());
        else filterClasses(classes, container_type.memberClasses(), prefix);

        for (int i = 0; i < container_type.interfaces().size(); i++)
        {
            ClassType interf = ((ReferenceType) container_type.interfaces().get(i)).toClass();
            if (prefix.length() == 0)
                 classes.addAll(interf.memberClasses());
            else filterClasses(classes, interf.memberClasses(), prefix);
        }
        
        getClassCandidates(container_type.superType(), classes, prefix);
    }

    private void getCandidates(ReferenceType container_type, ArrayList list, String prefix, int offset)
    {        
        ArrayList fields = new ArrayList(),
                  methods = new ArrayList(),
                  classes = new ArrayList();

        getFieldCandidates(container_type, fields, prefix);
        getMethodCandidates(container_type, methods, prefix);
        getClassCandidates((Type) container_type, classes, prefix);

        for (int i = 0; i < fields.size(); i++)
        {
            assert(fields.get(i) instanceof FieldInstance);
            FieldInstance field = (FieldInstance) fields.get(i);
            list.add(new SourceProposal(field.name(), "", offset));
        }

        for (int i = 0; i < methods.size(); i++)
        {
            assert(methods.get(i) instanceof MethodInstance);
            MethodInstance method = (MethodInstance) methods.get(i);
            list. add(new SourceProposal(method.name() + "()", "", offset));
        }
        
        for (int i = 0; i < classes.size(); i++)
        {
            assert(classes.get(i) instanceof ClassType);
            //ClassType type = ((ReferenceType) classes.get(i)).toClass();
            Node type = (Node) classes.get(i);
            list. add(new SourceProposal(type.toString() + "()", "", offset));
        }
    }

    public ICompletionProposal[] getContentProposals(IParseController controller, int offset)
    {
        ArrayList list = new ArrayList();
        //
        // When the offset is in between two tokens (forexample, on a white space or comment)
        // the getTokenIndexAtCharacter in parse stream returns the negative index
        // of the token preceding the offset. Here, we adjust this index to point instead
        // to the token following the offset.
        //
        // Note that the controller also has a getTokenIndexAtCharacter and 
        // getTokenAtCharacter. However, these methods won't work here because
        // controller.getTokenAtCharacter(offset) returns null when the offset
        // is not the offset of a valid token; and controller.getTokenAtCharacter(offset) returns
        // the index of the token preceding the offset when the offset is not
        // the offset of a valid token.
        //
        PrsStream prs_stream = controller.getParser().getParseStream(); 
        int index = prs_stream.getTokenIndexAtCharacter(offset),
            token_index = (index < 0 ? -(index - 1) : index);
        IToken token = prs_stream.getIToken(token_index),
               candidate = prs_stream.getIToken(prs_stream.getPrevious(token_index));
        //
        // If we are at an offset position immediately following an "identifier"
        // candidate, then consider the candidate to be the token for which we need
        // assistance and chose its predecessor as the candidate for the lookup.
        //
        if ((candidate.getKind() == TK_IDENTIFIER ||
             controller.isKeyword(candidate.getKind()))
            && offset == candidate.getEndOffset() + 1)
        {
            token = candidate;
            candidate = prs_stream.getIToken(prs_stream.getPrevious(candidate.getTokenIndex()));
        }

        String prefix = "";
        if (token.getKind() == TK_IDENTIFIER ||
            token.getKind() == TK_ErrorId ||
            controller.isKeyword(token.getKind()))
        {
            if (offset >= token.getStartOffset() && offset <= token.getEndOffset() + 1)
            {
                prefix = token.toString().substring(0, offset - token.getStartOffset());
            }
        }
/*
list.add(new SourceProposal("Offset: " + offset, "", offset));
list.add(new SourceProposal("Token start offset: " + token.getStartOffset(), "", offset));
list.add(new SourceProposal("Token end offset: " + token.getEndOffset(), "", offset));
list.add(new SourceProposal("Prefix: \"" + (prefix == null ? "" : prefix) + "\"", "", offset));
list.add(new SourceProposal("Candidate start offset: " + candidate.getStartOffset(), "", offset));
list.add(new SourceProposal("Candidate end offset: " + candidate.getEndOffset(), "", offset));
list.add(new SourceProposal("Token: " + token, "", offset));
list.add(new SourceProposal("Candidate: " + candidate, "", offset));
*/
        PolyglotNodeLocator locator = new PolyglotNodeLocator(controller.getLexer().getLexStream());
        Node node = (Node) locator.findNode(controller.getCurrentAst(), candidate.getStartOffset(), candidate.getEndOffset()); // offset);
        //
        // We execute this code when we encounter a qualified name x.foo,
        // where the left-hand side x of the qualified name may be either
        // an object reference (declared field or local declaration) or a
        // type. Note that x itself may be a qualified name.
        // 
        if (node instanceof Field)
        {
//            list.add(new SourceProposal("Field: " + node.getClass().toString(), " source proposal ", 0));
            Field field = (Field) node;
            if (candidate.getKind() == TK_DOT && field.target().type().isReference() /* instanceof ReferenceType */)
                 getCandidates((ReferenceType) field.target().type(), list, prefix, offset);
            else list.add(new SourceProposal("no info available", " source proposal ", 0));
        }
        //
        // We execute this code when we encounter a qualified name x.foo,
        // where the left-hand side x is a package name.
        //
        else if (node instanceof CanonicalTypeNode)
        {
            if (candidate.getKind() == TK_DOT)
            {
                candidate = prs_stream.getIToken(prs_stream.getPrevious(candidate.getTokenIndex()));
                node = (Node) locator.findNode(controller.getCurrentAst(), candidate.getStartOffset() + 1, candidate.getEndOffset() - 1);
                Qualifier qualifier = ((CanonicalTypeNode) node).qualifier();
                if (qualifier.isType())
                {
                    list.add(new SourceProposal("CanonicalTypeNode: " + candidate.toString() + " " + qualifier.toType().toString() + " => " + node.getClass().toString(), " source proposal ", 0));
                }
            }
        }
        else if (node instanceof Assign)
        {
            list.add(new SourceProposal("Assign: " + node.getClass().toString(), " source proposal ", 0));
            list.add(new SourceProposal("complete prefix " + prefix, " source proposal ", 0));
            if (prefix != null && prefix.length() > 0)
            {
                // TODO: Any package, type, local variable and accessible class members
            }
            else list.add(new SourceProposal("no info available", " source proposal ", 0));
        }
        else if (node instanceof FieldDecl)
        {
            list.add(new SourceProposal("FieldDecl: " + node.getClass().toString(), " source proposal ", 0));
            list.add(new SourceProposal("complete prefix " + prefix, " source proposal ", 0));
            if (prefix != null && prefix.length() > 0)
            {
                // TODO: Any package, type, local variable and accessible class members
                list.add(new SourceProposal("complete prefix " + prefix, " source proposal ", 0));
            }
            else if (candidate.getKind() == TK_EQUAL)
            {
                // TODO: Any package, type, local variable and accessible class members
            }
            else list.add(new SourceProposal("no info available", " source proposal ", 0));
        }
        else 
        {
            // TODO: Any package, type, local variable and accessible class members
            if (node instanceof Binary)
            {
                 list.add(new SourceProposal("BINARY: " + node.getClass().toString(), " source proposal ", 0));
            }
            else if (node instanceof Unary)
            {
                 list.add(new SourceProposal("UNARY: " + node.getClass().toString(), " source proposal ", 0));
            }
            else list.add(new SourceProposal("Other: " + node.getClass().toString(), " source proposal ", 0));
            
            if (prefix != null && prefix.length() > 0)
            {
                // complete this prefix...
                list.add(new SourceProposal("complete prefix " + prefix, " source proposal ", 0));
            }
        }

        return (ICompletionProposal[]) list.toArray(new ICompletionProposal[list.size()]);
    }
}
