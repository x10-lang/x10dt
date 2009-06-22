/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package org.eclipse.imp.x10dt.ui.cpp.debug.core;

import static org.eclipse.ptp.core.IPTPLaunchConfigurationConstants.ATTR_WORK_DIRECTORY;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.imp.runtime.RuntimePlugin;
import org.eclipse.imp.x10dt.ui.cpp.debug.utils.PDTUtils;
import org.eclipse.imp.x10dt.ui.cpp.launch.builder.CpEntryAsStringFunc;
import org.eclipse.imp.x10dt.ui.cpp.launch.builder.IPathToFileFunc;
import org.eclipse.imp.x10dt.ui.cpp.launch.builder.RuntimeFilter;
import org.eclipse.imp.x10dt.ui.cpp.launch.utils.collections.AlwaysTrueFilter;
import org.eclipse.imp.x10dt.ui.cpp.launch.utils.collections.IdentityFunctor;
import org.eclipse.imp.x10dt.ui.cpp.launch.utils.collections.JavaProjectUtils;
import org.eclipse.imp.x10dt.ui.cpp.launch.utils.collections.ListUtils;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.ptp.core.util.BitList;
import org.eclipse.ptp.debug.core.pdi.PDIException;

import polyglot.ast.ClassDecl;
import polyglot.ast.LocalClassDecl;
import polyglot.ast.Node;
import polyglot.ast.SourceFile;
import polyglot.ext.x10.types.X10ParsedClassType;
import polyglot.ext.x10cpp.debug.LineNumberMap;
import polyglot.ext.x10cpp.types.X10CPPTypeSystem_c;
import polyglot.ext.x10cpp.visit.Emitter;
import polyglot.frontend.Compiler;
import polyglot.frontend.ExtensionInfo;
import polyglot.frontend.FileSource;
import polyglot.frontend.Globals;
import polyglot.frontend.Goal;
import polyglot.frontend.Job;
import polyglot.frontend.Scheduler;
import polyglot.main.Options;
import polyglot.types.FieldInstance;
import polyglot.types.QName;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.util.ErrorInfo;
import polyglot.util.ErrorQueue;
import polyglot.util.Position;
import polyglot.visit.NodeVisitor;

import com.ibm.debug.internal.epdc.IEPDCConstants;
import com.ibm.debug.internal.pdt.PICLDebugTarget;
import com.ibm.debug.internal.pdt.model.DebuggeeProcess;
import com.ibm.debug.internal.pdt.model.DebuggeeThread;
import com.ibm.debug.internal.pdt.model.EngineRequestException;
import com.ibm.debug.internal.pdt.model.ExprNodeBase;
import com.ibm.debug.internal.pdt.model.ExpressionBase;
import com.ibm.debug.internal.pdt.model.GlobalVariable;
import com.ibm.debug.internal.pdt.model.Location;
import com.ibm.debug.internal.pdt.model.ViewFile;

public final class X10DebuggerTranslator implements IDebuggerTranslator {

  public void init(IProject project) {
//    System.err.println("Reading mapping information");
//    GlobalVariable[] globals = p.getDebugEngine().getGlobalVariables();
//    for (GlobalVariable v : globals) {
//      //System.out.println("Got var: "+v.getName()+" "+v.getExpression());
//      if (v.getName().contains("LNMAP_")) {
//        readLineNumberMap(p, v);
//      }
//    }
//    System.out.println("m="+fCppToX10Map);
//    System.out.println("im="+fX10ToCppMap);
//    System.err.println("Done reading mapping information");
    fCompiler = Globals.Compiler();
    if (fCompiler == null) {
      ExtensionInfo extInfo = new polyglot.ext.x10cpp.ExtensionInfo() {
        public Scheduler createScheduler() {
          return new X10CPPScheduler(this) {
            public List<Goal> goals(Job job) {
              ArrayList<Goal> goals = new ArrayList<Goal>();
              goals.add(Parsed(job));
              goals.add(TypesInitialized(job));
              goals.add(ImportTableInitialized(job));
              goals.add(CastRewritten(job));
              goals.add(PreTypeCheck(job));
              goals.add(TypeChecked(job));
              goals.add(End(job));
              return goals;
//              return super.goals(job);
            }
          };
        }
      };
      buildOptions(JavaCore.create(project), extInfo.getOptions());
      fCompiler = new Compiler(extInfo, new ErrorQueue(){
        public boolean hasErrors() { return false; }
        public void flush() { }
        public int errorCount() { return 0; }
        public void enqueue(ErrorInfo e) { System.out.println(e.getErrorString()); }
        public void enqueue(int type, String message, Position position) { System.out.println(message); }
        public void enqueue(int type, String message) { System.out.println(message); }
      });
      Globals.initialize(fCompiler);
    }
    this.fTypeSystem = (X10CPPTypeSystem_c) fCompiler.sourceExtension().typeSystem();
  }

  private void buildOptions(IJavaProject project, Options options) {
    try {
      // Sets the class path
      final Set<String> cps = JavaProjectUtils.getFilteredCpEntries(project, new CpEntryAsStringFunc(), 
                                                                    new AlwaysTrueFilter<IPath>());
      final StringBuilder cpBuilder = new StringBuilder();
      int i = -1;
      for (final String cpEntry : cps) {
        if (++i > 0) {
          cpBuilder.append(File.pathSeparatorChar);
        }
        cpBuilder.append(cpEntry);
      }
      // Sets the source path.
      final Set<IPath> srcPaths = JavaProjectUtils.getFilteredCpEntries(project, new IdentityFunctor<IPath>(),
                                                                        new RuntimeFilter());
      // We can now set all the Polyglot options for our extension.
      options.assertions = true;
      options.classpath = cpBuilder.toString();
      options.output_classpath = options.classpath;
      options.serialize_type_info = false;
      options.post_compiler = null;
      options.source_path = ListUtils.transform(srcPaths, new IPathToFileFunc());
      options.compile_command_line_only = true;
    } catch (JavaModelException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private GlobalVariable[] findLineNumberMaps(DebuggeeProcess p, String cppFile) {
    GlobalVariable[] res = new GlobalVariable[1];
    // TODO: find the appropriate part and extract the variable directly
    System.err.println("Looking for mapping info for '"+cppFile+"'");
    if (cppFile.indexOf('/') == -1)
    	cppFile = "/" + cppFile;
    String name = "LNMAP_" + cppFile.replace('/', '_').replace('.', '_');
    GlobalVariable[] globals = p.getDebugEngine().getGlobalVariables();
    for (GlobalVariable v : globals) {
      if (v.getName().equals(name)) {
        System.err.println("\tFound mapping info for '"+cppFile+"'");
        res[0] = v;
        break;
      }
    }
    return res;
  }

  private String getClassName(String x10File, int x10Line) {
    if (x10File.startsWith("file:/"))
      x10File = x10File.substring("file:/".length());
    String[] classes = fX10ClassMap.get(x10File);
    if (classes == null) {
      FileSource source = null;
      try {
        source = fCompiler.sourceExtension().sourceLoader().fileSource(x10File, true);
      } catch (IOException e) { }
      Scheduler scheduler = fCompiler.sourceExtension().scheduler();
      ArrayList<Job> jobs = new ArrayList<Job>();
      Job job = scheduler.addJob(source);
      jobs.add(job);
      Globals.initialize(fCompiler);
      scheduler.setCommandLineJobs(jobs);
      scheduler.addDependenciesForJob(job, true);
      scheduler.runToCompletion(scheduler.TypeChecked(job));
      Node ast = job.ast();
      assert (ast instanceof SourceFile);
      final ArrayList<String> classnames = new ArrayList<String>();
      // FIXME: deal with local classes
      ast.visit(new NodeVisitor() {
        private ArrayList<String> path = new ArrayList<String>();
        public NodeVisitor enter(Node n) {
          if (n instanceof ClassDecl)
            path.add(((ClassDecl)n).name().toString());
          else if (n instanceof LocalClassDecl)
            path.add(((LocalClassDecl)n).position().toString());
          return this;
        }
        public Node leave(Node old, Node n, NodeVisitor v) {
          if (n instanceof ClassDecl || n instanceof LocalClassDecl) {
            int start = n.position().line();
            int end = n.position().endLine();
            String p = path.toString();
            p = p.substring(1, p.length()-1).replace(", ", "__");
            classnames.add(p+":"+start+":"+end);
            path.remove(path.size()-1);
          }
          return n;
        }
      });
      classes = classnames.toArray(new String[classnames.size()]);
      fX10ClassMap.put(x10File, classes);
    }
    for (int i = 0; i < classes.length; i++) {
      String cInfo = classes[i];
      int l = cInfo.indexOf(':');
      int m = cInfo.lastIndexOf(':');
      assert (l != -1 && m != -1);
      int s = Integer.parseInt(cInfo.substring(l+1, m));
      int e = Integer.parseInt(cInfo.substring(m+1));
      if (s <= x10Line && e >= x10Line)
        return cInfo.substring(0, l);
    }
    return null;
  }

  private GlobalVariable[] findX10LineNumberMaps(DebuggeeProcess p, String x10File, String className) {
    GlobalVariable[] res = new GlobalVariable[3];
    int i = 0;
    // TODO: find the appropriate part and extract the variable directly
    System.err.println("Looking for mapping info for '"+x10File+"'");
    x10File = x10File.replace('\\', '/'); // normalize directory separators
    String nameNoExt = x10File.substring(0, x10File.lastIndexOf('/')+1)+className;
    GlobalVariable[] globals = p.getDebugEngine().getGlobalVariables();
    for (GlobalVariable v : globals) {
      String name = v.getName();
	  if (!name.startsWith("LNMAP_"))
	    continue;
	  String ext = name.substring(name.lastIndexOf('_'));
	  int a = 5;
//	  assert (ext.equals("_cc") || ext.equals("_h") || ext.equals("_inc"));
	  String fName = name.substring("LNMAP_".length(), name.length()-ext.length());
	  if (fName.startsWith("_"))
	    fName = fName.substring(1);
	  int len = fName.length();
	  // Try to match the whole file name
	  String baseName = nameNoExt.substring(nameNoExt.length()-len).replace('/', '_');
	  if (!fName.equals(baseName))
	    continue;
      System.err.println("\tFound mapping info for '"+x10File+"'");
      res[i++] = v;
    }
    return res;
  }

  private void readLineNumberMaps(DebuggeeProcess p, GlobalVariable[] vars) {
    assert (vars != null);
    for (GlobalVariable v : vars) {
      if (v == null)
        continue;

      System.out.println("\tGOT MAP: "+v.getName()+" "+v.getExpression());
      System.err.println("Reading mapping info from "+v.getName());
      String val = null;
      try {
        DebuggeeThread t = p.getStoppingThread();
        // The code below doesn't create the right kind of monitor.
        //ExpressionBase b = t.evaluateExpression(t.getLocation(t.getViewInformation()), v.getExpression(), 1, 1000000);
        ExpressionBase b = p.monitorExpression(t.getLocation(t.getViewInformation()).getEStdView(), t.getId(), v.getExpression(), IEPDCConstants.MonEnable, IEPDCConstants.MonTypeProgram, null, null, null, null);
        // TODO
        //Address addr = p.convertToAddress(v.getExpression(), t.getLocation(t.getViewInformation()), t);
        if (b != null) {
          ExprNodeBase n = b.getRootNode();
          if (n != null)
            val = n.getValueString();
          b.remove();
        }
      } catch (EngineRequestException e) {
        RuntimePlugin.getInstance().logException(e.getMessage(), e);
      //} catch (MemoryException e) {
      //  RuntimePlugin.getInstance().logException(e.getMessage(), e);
      }
      System.out.println("\tValue = '"+val+"'");
      if (val == null)
        continue;
      if (val.endsWith("..."))
        val = val.substring(0, val.lastIndexOf(',')+1) + "}\""; // FIXME: damage control
      assert (val.startsWith("\"") && val.endsWith("\""));
      LineNumberMap c2xFileMap = LineNumberMap.importMap(val.substring(1, val.length()-1));
      HashMap<String, LineNumberMap> c2xMap = new HashMap<String, LineNumberMap>();
      c2xMap.put(c2xFileMap.file(), c2xFileMap);
      LineNumberMap.mergeMap(fCppToX10Map, c2xMap);
      HashMap<String, LineNumberMap> x2cMap = c2xFileMap.invert();
      LineNumberMap.mergeMap(fX10ToCppMap, x2cMap);
//      System.out.println("\tm="+c2xMap);
//      System.out.println("\tim="+x2cMap);
    }
  }

  public String getX10File(DebuggeeProcess process, Location cppLocation) {
    String cppFile = getCppFile(process, cppLocation);
    int cppLineNumber = getCppLine(process, cppLocation);
    LineNumberMap cppLineToX10LineMap = getCppToX10LineMap(process, cppFile);
    String x10File = cppLineToX10LineMap.getSourceFile(cppLineNumber);
    if (x10File != null && x10File.startsWith("file:/"))
      x10File = x10File.substring("file:/".length());
    return x10File;
  }

  public int getX10Line(DebuggeeProcess process, Location cppLocation) {
    String cppFile = getCppFile(process, cppLocation);
    int cppLineNumber = getCppLine(process, cppLocation);
    LineNumberMap cppLineToX10LineMap = getCppToX10LineMap(process, cppFile);
    int x10LineNumber = cppLineToX10LineMap.getSourceLine(cppLineNumber);
    return x10LineNumber;
  }

  public String getX10Function(DebuggeeProcess process, String cppFunction, Location cppLocation) {
    String cppFile = getCppFile(process, cppLocation);
    int cppLineNumber = getCppLine(process, cppLocation);
    LineNumberMap cppLineToX10LineMap = getCppToX10LineMap(process, cppFile);
    String x10Function = cppLineToX10LineMap.getMappedMethod(cppFunction);
    if (x10Function == null) { // now try alternate forms of primitives
      cppFunction = cppFunction.replaceAll("\\b(int|short|double|float)\\b", "x10_$1");
      x10Function = cppLineToX10LineMap.getMappedMethod(cppFunction);
      if (x10Function == null) { // now try adding spaces before closing type arg braces
        cppFunction = cppFunction.replaceAll("(?<!\\s)>", " >");
        x10Function = cppLineToX10LineMap.getMappedMethod(cppFunction);
      }
    }
    if (x10Function != null)
    	x10Function = x10Function.replace("::", ".");
    return x10Function;
  }
  
  private LineNumberMap getCppToX10LineMap(DebuggeeProcess process, String cppFile) {
    LineNumberMap map = fCppToX10Map.get(cppFile);
    if (map == null) {
      readLineNumberMaps(process, findLineNumberMaps(process, cppFile));
      map = fCppToX10Map.get(cppFile);
      if (map == null)
        fCppToX10Map.put(cppFile, map = new LineNumberMap(cppFile));
    }
    assert (map != null);
    return map;
  }
  
  private LineNumberMap getX10ToCppLineMap(DebuggeeProcess process, String x10File, int x10Line) {
    String className = getClassName(x10File, x10Line);
    LineNumberMap map = fX10ToCppMap.get(x10File+"|"+className);
    if (map == null) {
      readLineNumberMaps(process, findX10LineNumberMaps(process, x10File, className));
      map = fX10ToCppMap.get(x10File);
      if (map == null)
        fX10ToCppMap.put(x10File, map = new LineNumberMap(x10File));
      fX10ToCppMap.put(x10File+"|"+className, map);
    }
    assert (map != null);
    return map;
  }

  private int getCppLine(DebuggeeProcess process, Location cppLocation) {
    int cppLineNumber = cppLocation.getLineNumber();
    return cppLineNumber;
  }

  private String getCppFile(DebuggeeProcess process, Location cppLocation) {
    String cppFile = cppLocation.getViewFile().getName();
    try {
      String baseDir = process.getDebugTarget().getLaunch().getLaunchConfiguration().getAttribute(ATTR_WORK_DIRECTORY, (String) null);
      if (cppFile.startsWith(baseDir+"/"))
        cppFile = cppFile.substring(baseDir.length()+1);
    } catch (CoreException e) { }
    if (cppFile.startsWith("gen/"))
  	  cppFile = cppFile.substring("gen/".length());
    return cppFile;
  }

  public Location getCppLocation(DebuggeeProcess process, BitList tasks, String x10File, int x10LineNumber) {
    if (!x10File.startsWith("file:/"))
      x10File = "file:/"+x10File;
    LineNumberMap x10LineToCppLineMap = getX10ToCppLineMap(process, x10File, x10LineNumber);
    String cppFile = x10LineToCppLineMap.getSourceFile(x10LineNumber);
    int cppLineNumber = x10LineToCppLineMap.getSourceLine(x10LineNumber);
    if (cppFile == null || cppLineNumber == -1)
      return null;
    try {
      final ViewFile viewFile = PDTUtils.searchViewFile((PICLDebugTarget) process.getDebugTarget(),
                                                        tasks, process, cppFile);
      return new Location(viewFile, cppLineNumber);
    } catch (PDIException e) {
      return null;
    }
  }

  private Type loadType(String x10Type) {
    Globals.initialize(fCompiler);
    try {
      return fTypeSystem.typeForName(QName.make(x10Type));
    } catch (SemanticException e) {
    }
    return null;
  }

  private String getX10TypeFromCppType(String type) {
    if (type.endsWith(" "))
      type = type.trim();
    if (type.startsWith("x10aux__ref<") || type.startsWith("x10aux::ref")) {
      assert (type.endsWith(">"));
      type = type.substring("x10aux__ref<".length(), type.length()-1);
      if (type.endsWith(" "))
          type = type.trim();
      int paramStart = type.indexOf("<");
      String[] typeArgs = paramStart == -1 ? null : extractTypeArguments(type.substring(paramStart+1, type.lastIndexOf('>')));
      String cppType = paramStart == -1 ? type : type.substring(0, paramStart);
      String args = "";
      if (typeArgs != null) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < typeArgs.length; i++) {
          if (i > 0)
            sb.append(",");
          sb.append(typeArgs[i]);
        }
        sb.append("]");
        args = sb.toString();
      }
      return cppType.replace("__", ".").replace("::", ".")+args;
    } else if (type.equals("int")) {
      return "x10.lang.Int";
    } else if (type.equals("float")) {
        return "x10.lang.Float";
    } else if (type.equals("double")) {
        return "x10.lang.Double";
    } else {
        return "UNKNOWN";
    }
  }

  String[] extractTypeArguments(String typeArgsList) {
    ArrayList<String> res = new ArrayList<String>();
    StringTokenizer st = new StringTokenizer(typeArgsList, "<>, ", true);
    StringBuilder sb = new StringBuilder();
    int d = 0;
    while (st.hasMoreTokens()) {
      String t = st.nextToken();
      if (t.equals(",") && d == 0) {
        res.add(getX10TypeFromCppType(sb.toString()));
        sb = new StringBuilder();
      } else {
        if (!t.equals(" "))
          sb.append(t);
        if (t.equals("<")) {
          d++;
        } else if (t.equals(">")) {
          d--;
        }
      }
    }
    assert (d == 0);
    res.add(getX10TypeFromCppType(sb.toString()));
    return res.toArray(new String[res.size()]);
  }

  /**
   * Return a descriptor for a struct type.
   * TBFI = to be filled in.
   * The descriptor for a Rail is { X(type), elemType, length(TBFI) }.
   * The descriptor for a String is { X(type), content(TBFI), length(TBFI) }.
   * The descriptor for any other class is { X(type), num_interfaces, [fname, ftype]... }.
   */
  public String[] getStructDescriptor(String type) {
    if (type.startsWith("class ") && type.endsWith("*"))
      type = "x10aux::ref<"+type.substring("class ".length(), type.length()-1)+">";
    if (type.endsWith(" "))
      type = type.trim();
    if (type.endsWith("&"))
      type = type.substring(0, type.length()-1);
    type = stripPrefixes(type, new String[] { "class ref<", "x10aux__ref<", "class x10aux::ref<", "x10aux::ref<" });
    if (type == null)
    	return null;
    if (type.endsWith(" "))
      type = type.trim();
    // So now we have the contents of the ref
    String x10Type = getX10TypeFromCppType("x10aux__ref<"+type+" >");
    // Arrays are special
    if (type.startsWith("Rail<") || type.startsWith("ValRail<") ||
        type.startsWith("x10__lang__Rail<") || type.startsWith("x10__lang__ValRail<") ||
        type.startsWith("x10::lang::Rail<") || type.startsWith("x10::lang::ValRail<"))
    {
      int paramStart = type.indexOf("<");
      assert (type.endsWith(">"));
      String cppElementType = type.substring(paramStart+1, type.length()-1);
//      String x10ElementType = getX10TypeFromCppType(cppElementType, null);
      return new String[] { x10Type, cppElementType, null };
    }
    // So are strings
    if (type.equals("String") || type.equals("x10__lang__String") || type.equals("x10::lang::String"))
    {
      return new String[] { x10Type, null, null };
    }
    // Must be a class
    X10ParsedClassType t = (X10ParsedClassType) loadType(x10Type);
    if (t == null)
      return null;
    List<FieldInstance> fields = t.fields();
    String[] desc = new String[2 + 2*fields.size()];
    desc[0] = x10Type;
    desc[1] = Integer.toString(t.interfaces().size());
    int i = 2;
    for (FieldInstance f : fields) {
      desc[i++] = f.name().toString();
      desc[i++] = Emitter.translateType(f.type(), true);
    }
    return desc;
  }

  private String stripPrefixes(String type, String[] prefixes) {
    for (int i = 0; i < prefixes.length; i++) {
      if (type.startsWith(prefixes[i]))
        return stripPrefix(type, prefixes[i]);
	}
    return null;
  }

  private String stripPrefix(String type, String prefix) {
    assert (prefix.endsWith("<"));
    assert (type.startsWith(prefix));
    assert (type.endsWith(">"));
    return type.substring(prefix.length(), type.length()-1);
  }

  // --- Fields

  private X10CPPTypeSystem_c fTypeSystem;
  private final HashMap<String, String> fCppTypeToX10TypeMap = new HashMap<String, String>();
  private final HashMap<String, LineNumberMap> fX10ToCppMap = LineNumberMap.initMap();
  private final HashMap<String, LineNumberMap> fCppToX10Map = LineNumberMap.initMap();
  private final HashMap<String, String[]> fX10ClassMap = new HashMap<String, String[]>();
  private Compiler fCompiler;
}
