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

package org.eclipse.imp.x10dt.refactoring.changes;

import java.util.List;


import polyglot.ast.Node;

/**
 *
 */
public class AddAnnotationChange extends Change {
    private final Node fNode;
    private final String fAnnotation;

    /**
     * @param name
     */
    public AddAnnotationChange(String name, Node n, String annotation) {
        super(name);
        fNode= n;
        fAnnotation= annotation;
    }

    public Node getNode() {
        return fNode;
    }

    public String getAnnotation() {
        return fAnnotation;
    }

    /* (non-Javadoc)
     * @see org.eclipse.imp.x10dt.refactoring.changes.Change#getDescriptor()
     */
    @Override
    public ChangeDescriptor getDescriptor() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.imp.x10dt.refactoring.changes.Change#getModifiedElements()
     */
    @Override
    public List getModifiedElements() {
        // TODO Auto-generated method stub
        return null;
    }
}