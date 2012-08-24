/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching.services;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;

/**
 * Responsible for converting some launch configuration attributes into an appropriate structure of attributes 
 * categorization. Such data structure can then be used to present a synopsis of the configuration and perform comparisons 
 * in a case of multiple configuration choice when launching.
 * 
 * @author egeay
 */
public interface ILaunchConfAttrDataBuilder {
  
  /**
   * Fills up the attribute data map transmitted for the given launch configuration. 
   * 
   * @param config The configuration to consider.
   * @param attrDataMap The attribute data map to fill up.
   * @throws CoreException Occurs if we could not retrieve some data in the underlying storage of the launch configuration, 
   * or if some value type differs for the name searched for.
   */
  public void buildAttrDataMap(final ILaunchConfiguration config,
                               final Map<IAttrServices, List<IAttrServices>> attrDataMap) throws CoreException;

}
