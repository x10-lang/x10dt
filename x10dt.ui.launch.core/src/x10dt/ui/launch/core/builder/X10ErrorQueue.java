/*****************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                       *
 * All rights reserved. This program and the accompanying materials          *
 * are made available under the terms of the Eclipse Public License v1.0     *
 * which accompanies this distribution, and is available at                  *
 * http://www.eclipse.org/legal/epl-v10.html                                 *
 *****************************************************************************/
package x10dt.ui.launch.core.builder;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;

import polyglot.util.AbstractErrorQueue;
import polyglot.util.CodedErrorInfo;
import polyglot.util.ErrorInfo;
import polyglot.util.ErrorQueue;
import polyglot.util.Position;
import x10dt.ui.launch.core.Messages;
import x10dt.ui.launch.core.utils.CoreResourceUtils;

final class X10ErrorQueue extends AbstractErrorQueue implements ErrorQueue {
	IJavaProject fProject;
	Collection<IFile> fSourcesToCompile;

	X10ErrorQueue(IJavaProject project, final int errorsLimit,
			final String compilerName, final Collection<IFile> sourcesToCompile) {
		super(errorsLimit, compilerName);
		fProject = project;
		fSourcesToCompile = sourcesToCompile;
	}

	// --- Abstract methods implementation
	protected void displayError(final ErrorInfo error) {
		if (error.getErrorKind() == ErrorInfo.INTERNAL_ERROR) {
			CoreResourceUtils.addBuildMarkerTo(this.fProject.getResource(),
					Messages.XEQ_InternalCompilerErrorMsg,
					IMarker.SEVERITY_ERROR, IMarker.PRIORITY_NORMAL);
		} else {
			if (error.getPosition() == null) {
				final int severity = (error.getErrorKind() == ErrorInfo.WARNING) ? IMarker.SEVERITY_WARNING
						: IMarker.SEVERITY_ERROR;
				CoreResourceUtils.addBuildMarkerTo(fProject.getResource(),
						error.getMessage(), severity, IMarker.PRIORITY_NORMAL);
			} else {
				final int severity = (error.getErrorKind() == ErrorInfo.WARNING) ? IMarker.SEVERITY_WARNING
						: IMarker.SEVERITY_ERROR;
				final Position position = error.getPosition();
				final IPath rootPath = ResourcesPlugin.getWorkspace().getRoot()
						.getLocation();
				final IPath filePath = new Path(position.file());
				final String workspaceRelatedPath = filePath
						.removeFirstSegments(rootPath.segmentCount()).toString();
				final IFile file = ResourcesPlugin.getWorkspace().getRoot()
						.getFile(new Path(workspaceRelatedPath));
//				final IFile file = ResourcesPlugin.getWorkspace().getRoot()
//                   .getFile(filePath);

				Map<String, Object> atts = null;
				if (error instanceof CodedErrorInfo) {
					atts = ((CodedErrorInfo) error).getAttributes();
				}
				if (fSourcesToCompile.contains(file)){ // --- Only create the marker if the error pertains to a file that was submitted for compilation. This is to avoid duplicate markers on other files.
					CoreResourceUtils.addBuildMarkerTo(file, error.getMessage(),
						severity, IMarker.PRIORITY_NORMAL, workspaceRelatedPath,
						position.line(), position.offset(), position
								.endOffset(), atts);
				}
			}
		}
	}

}
