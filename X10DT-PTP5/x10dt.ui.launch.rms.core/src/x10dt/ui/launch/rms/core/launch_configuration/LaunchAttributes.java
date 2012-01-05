/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.rms.core.launch_configuration;

import org.eclipse.ptp.core.attributes.ArrayAttributeDefinition;
import org.eclipse.ptp.core.attributes.BooleanAttributeDefinition;
import org.eclipse.ptp.core.attributes.StringAttributeDefinition;

import x10dt.ui.launch.core.Constants;
import x10dt.ui.launch.rms.core.Messages;

/**
 * Launch attributes for X10 transports that need a hostfile or host list for proper execution.
 * 
 * @author egeay
 */
public final class LaunchAttributes {
  
  /**
   * Returns the host file attribute definition.
   * 
   * @return A non-null instance.
   */
  public static StringAttributeDefinition getHostFileAttr() {
    return fHostFileAttr;
  }
  
  /**
   * Returns the host list attribute definition.
   * 
   * @return A non-null instance.
   */
  public static ArrayAttributeDefinition<String> getHostListAttr() {
    return fHostListAttr;
  }
  
  /**
   * Returns the boolean attribute definition if we use a host file or not.
   * 
   * @return A non-null instance.
   */
  public static BooleanAttributeDefinition getUseHostFileAttr() {
    return fUseHostFileAttr;
  }
  
  // --- Private code
  
  private LaunchAttributes() {}
  
  // --- Fields
  
  private static final String HOST_FILE_ATTR_ID = "hostFileAttr"; //$NON-NLS-1$
  
  private static final String HOST_LIST_ATTR_ID = "hostListAttr"; //$NON-NLS-1$
  
  private static final String HOST_USE_HOSTFILE_ATTR_ID = "useHostFileAttr"; //$NON-NLS-1$
  
  private static final StringAttributeDefinition fHostFileAttr;
  
  private static final ArrayAttributeDefinition<String> fHostListAttr;
  
  private static final BooleanAttributeDefinition fUseHostFileAttr;
  
  static {
    fHostFileAttr = new StringAttributeDefinition(HOST_FILE_ATTR_ID, Messages.LA_HostFileName, 
                                                  Messages.LA_HostFileDescr, false, Constants.EMPTY_STR);
    
    fHostListAttr = new ArrayAttributeDefinition<String>(HOST_LIST_ATTR_ID, Messages.LA_HostListName, 
                                                         Messages.LA_HostListDescr, false, new String[0]);
    
    fUseHostFileAttr = new BooleanAttributeDefinition(HOST_USE_HOSTFILE_ATTR_ID, Messages.LA_UseHostFileName, 
                                                      Messages.LA_UseHostFileDescr, false, false);
  }

}
