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


final class CompositeLaunchConfAttrDataBuilder implements ILaunchConfAttrDataBuilder {
  
  CompositeLaunchConfAttrDataBuilder(final Iterable<ILaunchConfAttrDataBuilder> builders) {
    this.fBuilders = builders;
  }
  
  // --- Interface methods implementation

  public void buildAttrDataMap(final ILaunchConfiguration config, 
                               final Map<IAttrServices, List<IAttrServices>> attrDataMap) throws CoreException {
    for (final ILaunchConfAttrDataBuilder builder : this.fBuilders) {
      builder.buildAttrDataMap(config, attrDataMap);
    }
  }
  
  // --- Fields
  
  private final Iterable<ILaunchConfAttrDataBuilder> fBuilders;

}
