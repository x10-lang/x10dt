package org.eclipse.imp.x10dt.core.tests.compiler;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;
import polyglot.ast.Node;
import polyglot.frontend.Compiler;
import polyglot.frontend.ExtensionInfo;
import polyglot.frontend.Globals;
import polyglot.frontend.Job;
import polyglot.main.Options;
import polyglot.main.UsageError;
import polyglot.util.AbstractErrorQueue;
import polyglot.util.ErrorInfo;

public class CompilerTestsBase {
	
	protected static String[] STATIC_CALLS = {"-STATIC_CALLS=true", "-CHECK_INVARIANTS"};
	protected static String[] NOT_STATIC_CALLS = {"-STATIC_CALLS=false", "-CHECK_INVARIANTS"};
	

	private static String OUTPUT_DIR = "output";
	
	public boolean compile(File[] files, String[] options,
			final Collection<ErrorInfo> errors, String sourcepath) throws Exception {
		return compile(files, options, errors, sourcepath, new ArrayList<Job>());	
	}
	
	
	/**
	 * 
	 * @param files
	 * @param options
	 * @param sourcepath
	 * @param jobs: non-null collection of jobs to return to caller (from which ASTs can be extracted and visited)
	 * @return true if compilation succeeds without errors
	 * @throws Exception 
	 */
	public boolean compile(File[] files, String[] options,
			final Collection<ErrorInfo> errors, String sourcepath, Collection<Job> jobs) throws Exception {
		
		try {
			Collection<String> sources = new ArrayList<String>();
			for (File f : files) {
				sources.add(f.getPath());
			}
			ExtensionInfo extInfo = new x10.ExtensionInfo();
			buildOptions(extInfo, options, sourcepath);
			final Compiler compiler = new Compiler(extInfo,
					new AbstractErrorQueue(1000000, extInfo.compilerName()) {
						protected void displayError(ErrorInfo error) {
							errors.add(error);
						}
					});
			Globals.initialize(compiler);
			compiler.compileFiles(sources);
			
			//get ASTs
			jobs.addAll(extInfo.scheduler().commandLineJobs());
			
			for (String s : sources) {
				System.err.print(s + " - ");
			}
			for (String s : options) {
				System.err.print(s + " - ");
			}
			System.err.println(sourcepath);
			for (ErrorInfo e : errors) {
				System.err.println(e + ":" + e.getPosition());
			}
			for(ErrorInfo error: errors){
				Assert.assertFalse(getTestId(files, options), internalError(error));
				Assert.assertFalse(getTestId(files, options), notWellFormed(error));
			}
			Assert.assertFalse(getTestId(files, options), duplicateErrors(errors));
			return errors.isEmpty();
		} catch (Throwable e) {
			throw new Exception(getTestId(files, options), e);
		}
	}
	

	
	private void buildOptions(ExtensionInfo extinfo, String[] options, String sourcepath) {
		Options opts = extinfo.getOptions();
		List<String> optsList = new ArrayList<String>();
		String[] stdOptsArray = new String[] { 
				"-noserial", 
				"-d",
				OUTPUT_DIR,
				"-sourcepath",
				sourcepath,
				"-commandlineonly", 
				"-c",
				};
		for (String s : stdOptsArray) {
			optsList.add(s);
		}
		for (String s: options){
			optsList.add(0, s);
		}
        
		String[] optsArray = optsList.toArray(new String[optsList.size()]);
		try {
			opts.parseCommandLine(optsArray, new HashSet());
		} catch (UsageError e) {
			
		}
	}
	
	protected String getTestId(File[] files, String[] options){
		String testId = "";
		for (File f : files) {
			testId += f.getPath() + " - ";
		}
		for (String s : options) {
			testId += s + " - ";
		}
		return testId;
	}
	
	protected static Collection<File> getSources(File dir) {
		Collection<File> results = new ArrayList<File>();
		if (dir.isDirectory()) {
			for (File f : dir.listFiles()) {
				if (f.isFile() && f.getName().endsWith(".x10")) {
					results.add(f);
				} else if (f.isDirectory()) {
					results.addAll(getSources(f));
				}
			}
		}
		return results;
	}
	
	protected static String getRuntimeJar(){
		return ".." + File.separator + "x10.dist" + File.separator + "lib" + File.separator + "x10.jar";
	}
	
	private String getErrorString(ErrorInfo e){
		return e.toString() + e.getPosition()==null?"":":" + e.getPosition();
	}
	
	
	private boolean duplicateErrors(Collection<ErrorInfo> errors){
		Map<String,Integer> count = new HashMap<String,Integer>(); //Map of message string to count
		for(ErrorInfo e1: errors){
			if (e1.getErrorKind() == ErrorInfo.INTERNAL_ERROR) continue;
			String m1 = getErrorString(e1);
			for(ErrorInfo e2: errors){
				String m2 = getErrorString(e2);
				if(m1.equals(m2)){
					if (count.get(m1) == null){
						count.put(m1, 1);
					} else {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean notWellFormed(ErrorInfo e) {
		// TODO: add other well-formedness conditions here.
		if (e.getMessage() == null)
			return true;
		if (e.getMessage().contains("{amb}"))
			return true;
		if (e.getMessage().contains("<unknown>"))
			return true;
		if (e.getMessage().contains("place_"))
			return true;
		if (e.getMessage().contains("self_"))
			return true;
		return false;
	}

	
	private boolean internalError(ErrorInfo e){
		if (e.getErrorKind() == ErrorInfo.INTERNAL_ERROR)
			return true;
		return false;
	}
	
	
}