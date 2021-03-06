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

package x10dt.ui.parser;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;

import lpg.runtime.IMessageHandler;
import lpg.runtime.Monitor;
import polyglot.ast.Node;
import polyglot.frontend.FileSource;
import polyglot.frontend.ForgivingVisitorGoal;
import polyglot.frontend.Goal;
import polyglot.frontend.Job;
import polyglot.frontend.Parser;
import polyglot.frontend.Scheduler;
import polyglot.frontend.Source;
import polyglot.util.ErrorQueue;

/**
 * Information about our extension of the polyglot compiler. This derives from
 * the ExtensionInfo class used by the X10 compiler, and specializes it to create
 * a parser/scanner that can read from an arbitrary Reader, and to save the parser
 * and lexer for "interesting" source files for later reference. It also creates a
 * dummy goal to record the AST of "interesting" source files.
 * @author beth
 * @author rfuhrer@watson.ibm.com
 */
public class ExtensionInfo extends x10.ExtensionInfo {
    //private X10Lexer x10_lexer; 
    private Parser x10_parser;
    private final IProgressMonitor monitor;
    private final IMessageHandler handler;
    protected final Set<Source> fInterestingSources = new HashSet<Source>();
    private final Map<Source,Node> fInterestingASTs = new HashMap<Source,Node>();
    protected final Map<Source,Job> fInterestingJobs = new HashMap<Source,Job>();
    private final Map<Source,Parser> fInterestingParsers = new HashMap<Source,Parser>();
//    private final Map<Source,X10Lexer> fInterestingLexers = new HashMap<Source,X10Lexer>();
    private final IProject project;
    
    public ExtensionInfo(IProgressMonitor monitor, IMessageHandler handler, IProject project) {
        this.monitor = monitor;
        this.handler = handler;
        this.project = project;
    }

    public void setInterestingSources(Collection<Source> sources) {
        fInterestingSources.clear();
        fInterestingJobs.clear();
        fInterestingASTs.clear();
//        fInterestingLexers.clear();
        fInterestingParsers.clear();
        fInterestingSources.addAll(sources);
    }

    //public X10Lexer getLexerFor(Source src) { return fInterestingLexers.get(src); }
    public Parser getParserFor(Source src) { return fInterestingParsers.get(src); }
    public Node getASTFor(Source src) { Job job= fInterestingJobs.get(src); return (job != null) ? job.ast() : null; /* return fInterestingASTs.get(src); */ }
    public Job getJobFor(Source src) { return fInterestingJobs.get(src); }

    @Override
    protected Scheduler createScheduler() {
        return new X10Scheduler(this) {
            @Override
            public List<Goal> goals(Job job) {
 
                if (fInterestingSources.contains(job.source())) {
                	fInterestingJobs.put(job.source(), job);
                }
                List<Goal> goals =  super.semanticCheckSourceGoals(job);
                List<Goal> newGoals = new ArrayList<Goal>();
                if (project != null){
                	for(Goal goal: goals){
                		if (goal.name().equals("CheckASTForErrors")){ // --- WARNING: FRAGILE CODE HERE!
                			newGoals.add(PackageDeclGoal(job, project));
                		}
                		newGoals.add(goal);
                	}
                	return newGoals;
                }
                return goals;
            }
            
            
            protected Goal PackageDeclGoal(Job job, IProject project){
            	return new ForgivingVisitorGoal("PackageDeclarationCheck", job, new CheckPackageDeclVisitor(job, project)).intern(this);
            }
 
        };
    }

    public Parser parser(Reader reader, FileSource source, ErrorQueue eq) {
        x10_parser = super.parser(reader, source, eq);
 //       x10_lexer = x10_parser.getX10Lexer();
 //       x10_parser.getIPrsStream().setMessageHandler(handler);
        if (fInterestingSources.contains(source)) {
//            fInterestingLexers.put(source, x10_lexer);
            fInterestingParsers.put(source, x10_parser);
        }
        return x10_parser;
    }
}
