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

import polyglot.ast.Formal;
import polyglot.ast.MethodDecl;

/**
 *
 */
public class InsertFormalArgumentChange extends Change {
    /**
     * @param name
     */
    public InsertFormalArgumentChange(String name, MethodDecl md, Formal formal, int idx) {
        super(name);
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
