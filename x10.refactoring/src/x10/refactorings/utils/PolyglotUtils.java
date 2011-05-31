package x10.refactorings.utils;

import java.util.Collection;
import java.util.List;

import polyglot.ast.Assign;
import polyglot.ast.NamedVariable;
import polyglot.ast.Node;
import polyglot.ast.Stmt;
import polyglot.ast.Variable;
import polyglot.types.VarInstance;
import polyglot.visit.NodeVisitor;

public abstract class PolyglotUtils {

	public static Node findNodeOfType(List<Node> path, Class clazz) {
		for (Node node : path) {
			if (clazz.isInstance(node))
				return node;
		}
		return null;
	}

	public static Node findInnermostNodeOfType(List<Node> path, Class clazz) {
		Node result = null;
		for (Node node : path) {
			if (clazz.isInstance(node))
				result = node;
		}
		return result;
	}

	public static Node findInnermostNodeOfTypes(List<Node> path, Class[] classes) {
		Node result = null;
		for (Node node : path) {
			for (Class clazz : classes) {
				if (clazz.isInstance(node))
					result = node;
			}
		}
		return result;
	}

	/**
	 * Determines whether the given statement updates one of the variables in
	 * the given collection.
	 * 
	 * @param s
	 *            the statement to test
	 * @param vars
	 *            the set of variables that might updated
	 * @return true, if the statement updates one of the variables
	 */
	public static boolean updatesVarFromCollection(Stmt s,
			final Collection<Variable> vars) {
		NodeVisitor nv = new NodeVisitor() {
			boolean updates;

			public NodeVisitor enter(Node par, Node n) {
				if (n instanceof Assign) {
					Assign a = (Assign) n;
					for (Variable v : vars) {
						VarInstance vi, ali;
						if (v instanceof NamedVariable) {
							vi = ((NamedVariable) v).varInstance();
						} else {
							vi = ExtractAsyncStaticTools.extractArrayName(v)
									.varInstance();
						}

						if (a.left() instanceof NamedVariable) {
							ali = ((NamedVariable) a.left()).varInstance();
						} else {
							ali = ExtractAsyncStaticTools.extractArrayName(
									(Variable) a.left()).varInstance();
						}
						if ((vi == null && ali == null)
								|| (vi != null && vi.equals(ali)))
							updates = true;
					}
				}
				return super.enter(n);
			}

			public String toString() {
				return "" + updates;
			}
		};
		s.visit(nv);
		return Boolean.parseBoolean(nv.toString());
	}

    private static int getNodeLocation(List<Stmt> stmtList, final Node n) {
        int nodeLocation = stmtList.indexOf(n);
        if (nodeLocation == -1) {
            final ContainsNodeObserver contains_n = new ContainsNodeObserver();
            for (int i = 0; i < stmtList.size(); i++) {
                stmtList.get(i).visit(new NodeVisitor() {

                    @Override
                    public NodeVisitor enter(Node n3) {
                        if (n.equals(n3))
                            contains_n.contains();

                        return super.enter(n);
                    }

                });
                if (contains_n.containsNode) {
                    nodeLocation = i;
                    break;
                }
            }
        }
        return nodeLocation;
    }

    public static List<Stmt> splitBlockBeforeNode(Block b, Node n) {
        List<Stmt> stmtList = (List<Stmt>) b.statements();
        int nodeLocation = getNodeLocation(stmtList, n);
        return (nodeLocation != 0) ? stmtList.subList(0, nodeLocation) : new ArrayList<Stmt>();
    }

    public static List<Stmt> splitBlockAfterNode(Block b, Node n) {
        List<Stmt> stmtList = (List<Stmt>) b.statements();
        int nodeLocation = getNodeLocation(stmtList, n);
        return (nodeLocation != stmtList.size()) ? stmtList.subList(nodeLocation + 1, stmtList.size())
                : new ArrayList<Stmt>();

    }
}
