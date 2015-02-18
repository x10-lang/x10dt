package x10dt.ui.launch.java.builder;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;

import polyglot.frontend.CyclicDependencyException;
import polyglot.frontend.ForgivingVisitorGoal;
import polyglot.frontend.Goal;
import polyglot.frontend.Job;
import polyglot.frontend.Scheduler;
import x10dt.ui.parser.CheckPackageDeclVisitor;

public class JavaBuilderExtensionInfo extends x10c.ExtensionInfo {
    private final X10JavaBuilder fBuilder;
    private final IProgressMonitor fMonitor;
   
    public JavaBuilderExtensionInfo(final IProgressMonitor monitor, X10JavaBuilder builder) {
        this.fMonitor = monitor;
    	this.fBuilder= builder;
    }
    

    @Override
    protected Scheduler createScheduler() {
        return new X10CScheduler(this) {
            @Override
            public List<Goal> goals(Job job) {
                List<Goal> goals = super.goals(job);
                Goal endGoal = goals.get(goals.size() - 1);
                if (!(endGoal.name().equals("End"))) {
                    throw new IllegalStateException("Not an End Goal?");
                }
                List<Goal> newGoals = new ArrayList<Goal>();
                for(Goal goal: goals){
                	if (goal.name().equals("CheckASTForErrors")){ // --- WARNING: FRAGILE CODE HERE!
                		newGoals.add(PackageDeclGoal(job, fBuilder.getProject()));
                	}
                	newGoals.add(goal);
                }
                return newGoals;
            }
            
            protected Goal PackageDeclGoal(Job job, IProject project){
            	return new ForgivingVisitorGoal("PackageDeclarationCheck", job, new CheckPackageDeclVisitor(job, project)).intern(this);
            }
            
            @Override
            protected boolean runPass(Goal goal) throws CyclicDependencyException {
              if (fMonitor.isCanceled()) {
                this.cancel();
              }
              return super.runPass(goal);
            }
           
        };
    }

   
   
}
