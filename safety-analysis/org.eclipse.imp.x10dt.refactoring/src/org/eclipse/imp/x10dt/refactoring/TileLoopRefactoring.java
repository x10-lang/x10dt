/*******************************************************************************
* Copyright (c) 2009 IBM Corporation.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    Robert Fuhrer (rfuhrer@watson.ibm.com) - initial API and implementation
*******************************************************************************/

package org.eclipse.imp.x10dt.refactoring;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.services.IASTFindReplaceTarget;
import org.eclipse.imp.x10dt.refactoring.utils.NodePathComputer;
import org.eclipse.imp.x10dt.ui.parser.CompilerDelegate;
import org.eclipse.imp.x10dt.ui.parser.ExtensionInfo;
import org.eclipse.imp.x10dt.ui.parser.ParseController;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.CompositeChange;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.TextFileChange;
import org.eclipse.text.edits.InsertEdit;
import org.eclipse.text.edits.MultiTextEdit;
import org.eclipse.ui.texteditor.ITextEditor;

import polyglot.ast.Formal;
import polyglot.ast.MethodDecl;
import polyglot.ast.Node;
import polyglot.ast.Stmt;
import x10.ast.ForLoop;
import x10.ast.X10Formal;
import x10.ast.X10MethodDecl;


public class TileLoopRefactoring extends X10RefactoringBase {
    private static final String TILE_LOOP_REFACTORING_NAME= "Tile Loop";

    /**
     * The user-selected ForLoop (often the same as fNode, if the latter is a ForLoop)
     */
    private ForLoop fLoop;

    public TileLoopRefactoring(ITextEditor editor) {
        super(editor);
    }

    @Override
    public String getName() {
        return TILE_LOOP_REFACTORING_NAME;
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
            return RefactoringStatus.createFatalErrorStatus("Selected node is not a 'for' loop");
        }
        fLoop= (ForLoop) node;
        fPathComputer= new NodePathComputer(fSourceAST, fLoop);
        fContainingMethod= (X10MethodDecl) fPathComputer.findEnclosingNode(fLoop, MethodDecl.class);

        return RefactoringStatus.create(new Status(IStatus.OK, X10DTRefactoringPlugin.kPluginID, ""));
    }

    @Override
    public RefactoringStatus checkFinalConditions(IProgressMonitor pm) throws CoreException, OperationCanceledException {
        try {
            IParseController pc= ((IASTFindReplaceTarget) fEditor).getParseController();
            CompilerDelegate cd= ((ParseController) pc).getCompiler();
            ExtensionInfo extInfo = cd.getExtInfo();
            Stmt loopBody = fLoop.body();
            X10Formal loopVar = (X10Formal) fLoop.formal();
            List<Formal> explodedVars= loopVar.vars();

            return RefactoringStatus.create(new Status(IStatus.OK, X10DTRefactoringPlugin.kPluginID, ""));
        } catch (Exception e) {
            return RefactoringStatus.createFatalErrorStatus("Exception occurred while analyzing loop: " + e.getMessage());
        }
    }

    @Override
    public Change createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
        CompositeChange outerChange = new CompositeChange(TILE_LOOP_REFACTORING_NAME);
        TextFileChange tfc = new TextFileChange("Tile loop", fSourceFile);

        tfc.setEdit(new MultiTextEdit());

        createAddTileAnnotationChange(tfc);

        outerChange.add(tfc);

        fFinalChange= outerChange;
        return fFinalChange;
    }

    private void createAddTileAnnotationChange(TextFileChange tfc) {
        int loopOffset = fLoop.position().offset();

        tfc.addEdit(new InsertEdit(loopOffset, "@Tile(N)"));
    }
}
