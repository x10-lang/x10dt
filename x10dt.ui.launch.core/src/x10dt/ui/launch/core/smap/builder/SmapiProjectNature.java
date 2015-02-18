/*******************************************************************************
* Copyright (c) 2007 IBM Corporation.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    Robert Fuhrer (rfuhrer@watson.ibm.com) - initial API and implementation
*******************************************************************************/

package x10dt.ui.launch.core.smap.builder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.imp.builder.ProjectNatureBase;
import org.eclipse.imp.runtime.IPluginLog;

import x10dt.core.X10DTCorePlugin;
import x10dt.ui.launch.core.LaunchCore;



/**
 * A nature implementation that supports rewriting generated Java .class files
 * with SMAP line-mapping attributes. Its only purpose is to enable the corresponding
 * builder that does the class-file rewriting.
 */
public class SmapiProjectNature extends ProjectNatureBase {
	public static final String k_natureID= LaunchCore.PLUGIN_ID + ".smapinature";

	/**
	 * The set of source file-name extensions for the original source from which the
	 * Java source files are generated
	 */
	private Set<String> fExtensions= new HashSet<String>();

	/**
	 * The ID of the "upstream" builder, i.e., the builder that produces the
	 * class files that will get rewritten/augmented by this builder
	 */
	private String fUpstreamBuilderID;
	
	public SmapiProjectNature() { }

	/**
	 * This constructor flavor assumes the JDT builder is the "upstream" builder,
	 * i.e., the builder that generates the .class files to be rewritten.
	 */
	public SmapiProjectNature(String exten) {
	    this(exten, "org.eclipse.jdt.core.javabuilder");
	}

	public SmapiProjectNature(String exten, String upstreamBuilderID) {
		fExtensions.add(exten);
		fUpstreamBuilderID = upstreamBuilderID;
	}

	public SmapiProjectNature(List<String> extens) {
	    fExtensions.addAll(extens);
	}

	public void addExtension(String extension) {
	    fExtensions.add(extension);
	}

	public String getNatureID() {
		return k_natureID;
	}

	public String getBuilderID() {
		return SmapieBuilder.BUILDER_ID;
	}

	public IPluginLog getLog() {
		return X10DTCorePlugin.getInstance();
	}

	protected void refreshPrefs() { }

	/**
	 * @return the ID of the "upstream" builder, i.e., the builder that produces the
     * class files that will get rewritten/augmented by this builder
	 */
	protected String getUpstreamBuilderID() {
		return fUpstreamBuilderID;
	}

	protected @SuppressWarnings("unchecked") Map getBuilderArguments() {
	    Map<String,String> m= new HashMap<String, String>();

	    if (fExtensions.size() > 0)
		m.put("exten", fExtensions.iterator().next());

	    return m;
	}
}
