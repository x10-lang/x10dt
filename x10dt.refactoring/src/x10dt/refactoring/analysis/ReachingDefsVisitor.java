package x10dt.refactoring.analysis;

import java.io.PrintStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import polyglot.ast.FieldAssign;
import polyglot.ast.LocalAssign;
import polyglot.ast.LocalDecl;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.Term;
import x10.ast.ForLoop;
import x10.ast.SettableAssign;
import x10dt.refactoring.X10DTRefactoringPlugin;
import polyglot.frontend.Job;
import polyglot.types.FieldDef;
import polyglot.types.LocalDef;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import polyglot.types.VarDef;
import polyglot.visit.DataFlow;
import polyglot.visit.FlowGraph;

/**
 * Computes the set of reaching definitions for each node in the AST passed in.
 * The AST node passed in is presumably some form of code body.
 */
public class ReachingDefsVisitor extends DataFlow {
    /**
     * An "item" for the data-flow engine that records a set of variables
     * (represented as VarDefs) and, for each one, a set of code entities
     * (e.g. assignments) that define values for that variable.
     * The intent is that one of these maps will be associated with each
     * AST node in a method, to represent the value definitions that flow
     * into that node.
     */
    public static class ValueMap extends Item {
        private final Map<VarDef,Set<Term>> fMap= new HashMap<VarDef, Set<Term>>();

        public ValueMap() { }

        public ValueMap(ValueMap in) {
            fMap.putAll(in.fMap);
        }

        public void kill(VarDef vd) {
            fMap.remove(vd);
        }

        public void defineValue(VarDef vd, Term t) {
            Set<Term> terms = fMap.get(vd);
            if (terms == null) {
                terms = new HashSet<Term>();
            }
            terms.add(t);
            fMap.put(vd, terms);
        }

        public void addValues(ValueMap vm) {
            fMap.putAll(vm.fMap);
        }

        public Set<VarDef> getKeys() {
            return fMap.keySet();
        }

        public Set<Term> getValues(VarDef vd) {
            Set<Term> result = fMap.get(vd);
            if (result == null) {
                result = Collections.emptySet();
            }
            return result;
        }

        @Override
        public boolean equals(Object i) {
            if (i instanceof ValueMap) {
                return fMap.equals(((ValueMap) i).fMap);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return 8191 + 171 * fMap.hashCode();
        }
        @Override
        public String toString() {
            StringBuilder sb= new StringBuilder();
            sb.append('{');
            for(VarDef vd: fMap.keySet()) {
                sb.append(vd.name());
                sb.append(" : ");
                sb.append(fMap.get(vd));
                sb.append(", ");
            }
            sb.append('}');
            return sb.toString();
        }
    }

    /**
     * The top-most node handed to this visitor (presumably a method decl).
     */
    private final Node fTopNode;

    /**
     * The ValueMap for the top-most node handed to this visitor (presumably a method decl)
     */
    private ValueMap fTopValueMap;

    private boolean fVerbose;

    private final PrintStream fDiagStream= X10DTRefactoringPlugin.getInstance().getConsoleStream();

    public ReachingDefsVisitor(Node topNode, Job job, TypeSystem ts, NodeFactory nf) {
        super(job, ts, nf, true, true);
        fTopNode= topNode;
    }

    public void setVerbose(boolean verbose) {
        fVerbose = verbose;
    }

    /**
     * @return the ValueMap for the top-most node passed in to this visitor
     */
    public ValueMap getReachingDefs() {
        return fTopValueMap;
    }

    @Override
    protected Item createInitialItem(FlowGraph graph, Term node, boolean entry) {
        return new ValueMap();
    }

    @Override
    protected Map flow(Item in, FlowGraph graph, Term n, boolean entry, Set edgeKeys) {
        return itemToMap(flow(in, graph, n, entry), edgeKeys);
    }

    protected ValueMap flow(Item in, FlowGraph g, Term t, boolean entry) {
        ValueMap result = new ValueMap((ValueMap) in);

        if (entry) {
            return result;
        }

        if (t instanceof LocalAssign) {
            LocalAssign localAssign = (LocalAssign) t;
            LocalDef def = localAssign.local().localInstance().def();
            result.kill(def);
            result.defineValue(def, localAssign.right());
        } else if (t instanceof FieldAssign) {
            FieldAssign fieldAssign = (FieldAssign) t;
            FieldDef def = fieldAssign.fieldInstance().def();
            result.kill(def);
            result.defineValue(def, fieldAssign); // object-insensitive
        } else if (t instanceof LocalDecl) {
            LocalDecl localDecl = (LocalDecl) t;
            if (localDecl.init() != null) {
                result.kill(localDecl.varDef());
                result.defineValue(localDecl.varDef(), localDecl.init());
            }
        } else if (t instanceof ForLoop) {
            ForLoop forLoop= (ForLoop) t;
            LocalDef def = forLoop.formal().localDef();
            result.kill(def);
            result.defineValue(def, forLoop.domain());
        } else if (t instanceof SettableAssign) {
            SettableAssign settableAssign = (SettableAssign) t;
//          result.defineValue(settableAssign.array(), settableAssign);
        }
        return result;
    }

    @Override
    protected void check(FlowGraph graph, Term n, boolean entry, Item inItem, Map outItems) {
        if (n == fTopNode && !entry) {
            fTopValueMap= (ValueMap) inItem;
        }
        if (fVerbose) {
            fDiagStream.println("Term " + n + " => " + inItem);
        }
    }

    @Override
    protected Item confluence(List items, Term node, boolean entry, FlowGraph graph) {
        ValueMap result = new ValueMap();
        for(Iterator iter= items.iterator(); iter.hasNext(); ) {
            ValueMap vm = (ValueMap) iter.next();
            result.addValues(vm);
        }
        return result;
    }
}
