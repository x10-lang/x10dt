package x10dt.refactoring;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.services.IASTFindReplaceTarget;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.CompositeChange;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.TextFileChange;
import org.eclipse.text.edits.InsertEdit;
import org.eclipse.text.edits.MultiTextEdit;
import org.eclipse.ui.texteditor.ITextEditor;

import polyglot.ast.Block;
import polyglot.ast.Formal;
import polyglot.ast.MethodDecl;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.Stmt;
import polyglot.frontend.Job;
import polyglot.types.TypeSystem;
import x10.ast.Async;
import x10.ast.Finish;
import x10.ast.ForLoop;
import x10.ast.X10Formal;
import x10.ast.X10MethodDecl;
import x10.constraint.XLocal;
import x10.constraint.XTerms;
import x10.effects.constraints.Effect;
import x10dt.refactoring.analysis.ReachingDefsVisitor;
import x10dt.refactoring.effects.EffectsVisitor;
import x10dt.refactoring.utils.NodePathComputer;
import x10dt.ui.parser.CompilerDelegate;
import x10dt.ui.parser.ExtensionInfo;
import x10dt.ui.parser.ParseController;

public class LoopFlatParallelizationRefactoring extends X10RefactoringBase {
    private static final String LOOP_FLAT_REFACTORING_NAME= "Loop Flat Parallelization";

    /**
     * The user-selected ForLoop (often the same as fNode, if the latter is a ForLoop)
     */
    private ForLoop fLoop;

    public LoopFlatParallelizationRefactoring(ITextEditor editor) {
        super(editor);
    }

    @Override
    public String getName() {
        return LOOP_FLAT_REFACTORING_NAME;
    }

    @Override
    public RefactoringStatus checkInitialConditions(IProgressMonitor pm) throws CoreException,
            OperationCanceledException {
        if (fSourceAST == null) {
            return RefactoringStatus.createFatalErrorStatus("Unable to analyze source due to syntax errors");
        }
        if (fSelNodes.size() != 1) {
            return RefactoringStatus.createFatalErrorStatus("You must select a single loop statement.");
        }
        Node node= fSelNodes.get(0);
        if (!(node instanceof ForLoop)) {
            return RefactoringStatus.createFatalErrorStatus("Selected node is not a loop");
        }
        fLoop= (ForLoop) node;
        fPathComputer= new NodePathComputer(fSourceAST, fLoop);
        fContainingMethod= (X10MethodDecl) fPathComputer.findEnclosingNode(fLoop, MethodDecl.class);

        if (loopHasAsync(fLoop)) {
            return RefactoringStatus.createFatalErrorStatus("Body of selected loop is already wrapped in an async");
        }

        return RefactoringStatus.create(new Status(IStatus.OK, X10DTRefactoringPlugin.kPluginID, ""));
    }

    private boolean loopHasAsync(ForLoop loop) {
        Stmt loopBody= loop.body();

        if (loopBody instanceof Async) {
            return true;
        }
        if (loopBody instanceof Block) {
            Block block = (Block) loopBody;
            List<Stmt> blockStmts = block.statements();

            if (blockStmts.size() == 1 && blockStmts.get(0) instanceof Async) {
                return true;
            }
        }
        return false;
    }

    private boolean loopIsWrappedWithFinish() {
        Node loopParent= fPathComputer.getParent(fLoop);

        if (loopParent instanceof Finish) {
            return true;
        }

        Node loopGrandparent= fPathComputer.getParent(loopParent);

        if (loopGrandparent instanceof Finish && loopParent instanceof Block && ((Block) loopParent).statements().size() == 1) {
            return true;
        }
        return false;
    }

    @Override
    public RefactoringStatus checkFinalConditions(IProgressMonitor pm) throws CoreException, OperationCanceledException {
        try {
            Stmt loopBody = fLoop.body();
            X10Formal loopVar = (X10Formal) fLoop.formal();
            List<Formal> explodedVars= loopVar.vars();
            
            TypeSystem ts = getTypeSystem();
            polyglot.frontend.ExtensionInfo ext = ts.extensionInfo();
            NodeFactory nf= ext.nodeFactory();
            Job job = new Job(ext, null, fSourceAST.source(), fSourceAST);
            ReachingDefsVisitor rdVisitor = new ReachingDefsVisitor(fContainingMethod, job, ts, nf);
            fContainingMethod.visit(rdVisitor);

            EffectsVisitor effVisitor= new EffectsVisitor(rdVisitor.getReachingDefs(), fContainingMethod);
            effVisitor.setVerbose(X10DTRefactoringPlugin.getInstance().getConsoleStream());
            loopBody.visit(effVisitor);
            if (fVerbose) {
                effVisitor.dump();
            }
            Effect bodyEff= effVisitor.getEffectFor(loopBody);

            if (bodyEff == null) {
                return RefactoringStatus.createFatalErrorStatus("Unable to compute the effects of the loop body.");
            }
            fConsoleStream.println("***");
            fConsoleStream.println("Loop body effect = " + bodyEff);
            fConsoleStream.println("Loop induction variable = " + loopVar.name());
            if (explodedVars.size() > 0) fConsoleStream.println("  exploded vars: " + explodedVars);

            // If the loop formal has "exploded var syntax" (e.g. "for(p(i): Point in r) { ... }"),
            // then we need to do commutesWithForall() over the set of all the induction variables.
            boolean commutes;

            if (explodedVars.size() > 0) {
                List<XLocal> loopLocals= new ArrayList<XLocal>(explodedVars.size() + 1);
                loopLocals.add(new XLocal(loopVar.localDef()));
                for(Formal explodedVar: explodedVars) {
                    loopLocals.add(new XLocal(explodedVar.localDef()));
                }
                commutes= bodyEff.commutesWithForall(loopLocals);
            } else {
                XLocal loopLocal= new XLocal(loopVar.localDef());

                commutes= bodyEff.commutesWithForall(loopLocal);
            }
            if (!commutes) {
                return RefactoringStatus.createErrorStatus("The loop body contains effects that don't commute.");
            }
            return RefactoringStatus.create(new Status(IStatus.OK, X10DTRefactoringPlugin.kPluginID, ""));
        } catch (Exception e) {
            return RefactoringStatus.createFatalErrorStatus("Exception occurred while analyzing loop: " + e.getMessage());
        }
    }

    @Override
    public Change createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
        CompositeChange outerChange = new CompositeChange(LOOP_FLAT_REFACTORING_NAME);
        TextFileChange tfc = new TextFileChange("Add 'async' to loop body", fSourceFile);

        tfc.setEdit(new MultiTextEdit());

        createAddAsyncChange(tfc);

        if (!loopIsWrappedWithFinish()) {
            createAddFinishChange(tfc);
        }
        outerChange.add(tfc);

        fFinalChange= outerChange;
        return fFinalChange;
    }

    private void createAddAsyncChange(TextFileChange tfc) {
        int asyncOffset = fLoop.body().position().offset();
        tfc.addEdit(new InsertEdit(asyncOffset, "async "));
    }

    private void createAddFinishChange(TextFileChange tfc) {
        int forStart = fLoop.position().offset();
        tfc.addEdit(new InsertEdit(forStart, "finish "));
    }
}
