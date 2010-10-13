package x10dt.search.ui.typeHierarchy;
/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/


import java.net.URI;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.imp.editor.EditorUtility;
import org.eclipse.jdt.internal.core.search.StringOperation;
import org.eclipse.ui.dialogs.SearchPattern;

import x10dt.search.core.engine.ITypeInfo;
import x10dt.search.core.engine.X10SearchEngine;
import x10dt.search.core.pdb.X10FlagsEncoder.X10;
import x10dt.search.ui.UISearchPlugin;



public class SearchUtils {

//	/**
//	 * @param match the search match
//	 * @return the enclosing {@link IJavaElement}, or null iff none
//	 */
//	public static IJavaElement getEnclosingJavaElement(SearchMatch match) {
//		Object element = match.getElement();
//		if (element instanceof IJavaElement)
//			return (IJavaElement) element;
//		else
//			return null;
//	}
//
//	/**
//	 * @param match the search match
//	 * @return the enclosing {@link ICompilationUnit} of the given match, or null iff none
//	 */
//	public static ICompilationUnit getCompilationUnit(SearchMatch match) {
//		IJavaElement enclosingElement = getEnclosingJavaElement(match);
//		if (enclosingElement != null){
//			if (enclosingElement instanceof ICompilationUnit)
//				return (ICompilationUnit) enclosingElement;
//			ICompilationUnit cu= (ICompilationUnit) enclosingElement.getAncestor(IJavaElement.COMPILATION_UNIT);
//			if (cu != null)
//				return cu;
//		}
//
//		IJavaElement jElement= JavaCore.create(match.getResource());
//		if (jElement != null && jElement.exists() && jElement.getElementType() == IJavaElement.COMPILATION_UNIT)
//			return (ICompilationUnit) jElement;
//		return null;
//	}
//
//	public static SearchParticipant[] getDefaultSearchParticipants() {
//		return new SearchParticipant[] { SearchEngine.getDefaultSearchParticipant() };
//	}
//
//    /**
//     * Constant for use as matchRule in {@link SearchPattern#createPattern(IJavaElement, int, int)}
//     * to get search behavior as of 3.1M3 (all generic instantiations are found).
//     */
    public final static int GENERICS_AGNOSTIC_MATCH_RULE= SearchPattern.RULE_EXACT_MATCH | SearchPattern.RULE_CASE_SENSITIVE;

    /**
     * Returns whether the given pattern is a camel case pattern or not.
     * <em>Note: this method does not consider the
     * {@link SearchPattern#RULE_CAMELCASE_SAME_PART_COUNT_MATCH} variant.<em>
     *
     * @param pattern the pattern to inspect
     * @return whether it is a camel case pattern or not
     */
	public static boolean isCamelCasePattern(String pattern) {
		//return SearchPattern.getMatchRule() == SearchPattern.RULE_CAMELCASE_MATCH;
		return false;
	}
	
	public static final int[] getMatchingRegions(String pattern, String name, int matchRule) {
		if (name == null) return null;
		final int nameLength = name.length();
		if (pattern == null) {
			return new int[] { 0, nameLength };
		}
		final int patternLength = pattern.length();
		boolean countMatch = false;
		switch (matchRule) {
			case SearchPattern.RULE_EXACT_MATCH:
				if (patternLength == nameLength && pattern.equalsIgnoreCase(name)) {
					return new int[] { 0, patternLength };
				}
				break;
			case SearchPattern.RULE_EXACT_MATCH | SearchPattern.RULE_CASE_SENSITIVE:
				if (patternLength == nameLength && pattern.equals(name)) {
					return new int[] { 0, patternLength };
				}
				break;
			case SearchPattern.RULE_PREFIX_MATCH:
				if (patternLength <= nameLength && name.substring(0, patternLength).equalsIgnoreCase(pattern)) {
					return new int[] { 0, patternLength };
				}
				break;
			case SearchPattern.RULE_PREFIX_MATCH | SearchPattern.RULE_CASE_SENSITIVE:
				if (name.startsWith(pattern)) {
					return new int[] { 0, patternLength };
				}
				break;
//			case SearchPattern.RULE_CAMELCASE_SAME_PART_COUNT_MATCH:
//				countMatch = true;
				//$FALL-THROUGH$
			case SearchPattern.RULE_CAMELCASE_MATCH:
				if (patternLength <= nameLength) {
					int[] regions = StringOperation.getCamelCaseMatchingRegions(pattern, 0, patternLength, name, 0, nameLength, countMatch);
					if (regions != null) return regions;
					if (name.substring(0, patternLength).equalsIgnoreCase(pattern)) {
						return new int[] { 0, patternLength };
					}
				}
				break;
//			case SearchPattern.RULE_CAMELCASE_SAME_PART_COUNT_MATCH | SearchPattern.RULE_CASE_SENSITIVE:
//				countMatch = true;
				//$FALL-THROUGH$
			case SearchPattern.RULE_CAMELCASE_MATCH | SearchPattern.RULE_CASE_SENSITIVE:
				if (patternLength <= nameLength) {
					return StringOperation.getCamelCaseMatchingRegions(pattern, 0, patternLength, name, 0, nameLength, countMatch);
				}
				break;
			case SearchPattern.RULE_PATTERN_MATCH:
				return StringOperation.getPatternMatchingRegions(pattern, 0, patternLength, name, 0, nameLength, false);
			case SearchPattern.RULE_PATTERN_MATCH | SearchPattern.RULE_CASE_SENSITIVE:
				return StringOperation.getPatternMatchingRegions(pattern, 0, patternLength, name, 0, nameLength, true);
		}
		return null;
	}
	
	public static String getElementName(ITypeInfo info) {
		return info.getName().substring(info.getName().lastIndexOf(".") + 1);
	}

	public static String getFullyQualifiedName(ITypeInfo info) {
		return info.getName();
	}

	public static String getFullyQualifiedName(ITypeInfo info, String qualifier) {
		return info.getName().replaceAll(".", qualifier);
	}

	public static String getFullyQualifiedName(ITypeInfo info, char qualifier) {
		return info.getName().replaceAll("\\.", new String(new char[]{qualifier}));
	}

	public static String getHandleIdentifier(ITypeInfo info) {
		return info.getName();
	}

	public static String getPackageName(ITypeInfo info) {
		return getTypeContainerName(info);
	}

	public static IResource getResource(ITypeInfo info) {
		try {
			String path = info.getLocation().getURI().getSchemeSpecificPart();
			IPath p = new Path(path);
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			return root.findMember(p.makeRelativeTo(root.getLocation()));
		} catch (Exception e) {
			//ignore
		}
		
		return null;
	}
	
	public static IResource getResource(URI uri) {
		IFile[] files = ResourcesPlugin.getWorkspace().getRoot().findFilesForLocationURI(uri);
		if(files.length > 0)
		{
			return files[0];
		}
		
		return null;
	}

	public static String getTypeContainerName(ITypeInfo info) {
		try {
			return info.getName().substring(0, info.getName().lastIndexOf("."));
		} catch (Exception e) {
			return "default";
		}
	}

	public static String getTypeQualifiedName(ITypeInfo info) {
		return info.getName();
	}

	public static String getTypeQualifiedName(ITypeInfo info, char qualifier) {
		return getFullyQualifiedName(info, qualifier);
	}
	
	public static ITypeInfo createType(String handle)
	{
		try {
			IResource res = getResource(new URI(handle));
			return X10SearchEngine.getTypeInfo(res.getProject(), getTypeRegex(res.getName()), new NullProgressMonitor());
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getTypeRegex(String typeName)
	{
		return typeName + ".*";
	}
	
	public static boolean hasFlag(X10 flag, int modifiers) {
		return (modifiers & flag.getCode()) != 0;
	}
	
	public static void openEditor(ITypeInfo ti) throws CoreException
	{
		IResource res = SearchUtils.getResource(ti);
		if(res != null)
		{
			EditorUtility.openInEditor(res, true);
		}
		
		else
		{
			URI uri = ti.getLocation().getURI();
			String scheme = uri.getSchemeSpecificPart();
			if(uri.getScheme().equals("jar"))
			{
//				scheme = scheme.substring(0, scheme.lastIndexOf(":"));
				scheme = scheme.replace("file:", "");
			}
			
			IPath path = new Path(scheme);
			EditorUtility.openInEditor(path);
		}
	}
	
	public static ITypeInfo getType(IProject project, String type)
	{
		try {
			if(project == null)
			{
				return X10SearchEngine.getX10RuntimeTypeInfo(type, new NullProgressMonitor());
			}
			return X10SearchEngine.getTypeInfo(project, type, new NullProgressMonitor());
		} catch (Exception e) {
			UISearchPlugin.log(e);
		}
		
		return null;
	}
}