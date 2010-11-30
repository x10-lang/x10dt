/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Robert Fuhrer (rfuhrer@watson.ibm.com) - initial API and implementation
 *******************************************************************************/

package x10dt.builders.tests.utils;

import org.eclipse.ui.IViewReference;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class ViewMatcher extends BaseMatcher<IViewReference> {
    private final String fViewName;

    public ViewMatcher(String viewName) {
        fViewName = viewName;
    }

    public void describeTo(Description description) {
        description.appendText("ViewMatcher(name == " + fViewName + ")");
    }

    public boolean matches(Object item) {
        if (item instanceof IViewReference) {
        	IViewReference er= (IViewReference) item;
            return er.getContentDescription().equals(fViewName);
        }
        return false;
    }
}