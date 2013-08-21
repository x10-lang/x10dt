/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching.services;

import static org.eclipse.debug.core.ILaunchManager.ATTR_ENVIRONMENT_VARIABLES;
import static org.eclipse.ptp.core.IPTPLaunchConfigurationConstants.ATTR_ARGUMENTS;
import static org.eclipse.ptp.core.IPTPLaunchConfigurationConstants.ATTR_CONSOLE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;

import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.cpp.LaunchMessages;


final class DefaultsLaunchConfAttrDataBuilder implements ILaunchConfAttrDataBuilder {
  
  // --- Interface methods implementation

  public void buildAttrDataMap(final ILaunchConfiguration launchConfig, 
                               final Map<IAttrServices, List<IAttrServices>> attrDataMap) throws CoreException {
    final String args = launchConfig.getAttribute(ATTR_ARGUMENTS, Constants.EMPTY_STR);
    final boolean printToConsole = launchConfig.getAttribute(ATTR_CONSOLE, true);
    final List<IAttrServices> runParamDataList = new ArrayList<IAttrServices>();
    if (args.length() > 0) {
      runParamDataList.add(new StringAttrServices(LaunchMessages.ACLCS_ProgArgsSection, true, args));
    }
    runParamDataList.add(new BooleanAttrServices(LaunchMessages.CAT_OutputToConsoleMsg, true, printToConsole));
    attrDataMap.put(new StringAttrServices(LaunchMessages.ACLCS_RunParamSection, false, Constants.EMPTY_STR), 
                    runParamDataList);
    
    @SuppressWarnings("unchecked")
    final Map<String, String> envVars = launchConfig.getAttribute(ATTR_ENVIRONMENT_VARIABLES, Collections.emptyMap());
    if (! envVars.isEmpty()) {
      final List<IAttrServices> envDataList = new ArrayList<IAttrServices>();
      for (final Map.Entry<String, String> entry : envVars.entrySet()) {
        envDataList.add(new StringAttrServices(entry.getKey(), true, entry.getValue()));
      }
      attrDataMap.put(new StringAttrServices(LaunchMessages.ACLCS_EnvVarSection, false, Constants.EMPTY_STR), envDataList);
    }
  }

}
