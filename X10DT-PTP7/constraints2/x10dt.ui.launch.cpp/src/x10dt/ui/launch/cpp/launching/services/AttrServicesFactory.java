/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp.launching.services;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;

import x10dt.ui.launch.core.utils.PTPConstants;
import x10dt.ui.launch.cpp.CppLaunchCore;
import x10dt.ui.launch.cpp.platform_conf.ICommunicationInterfaceConf;

/**
 * Factory methods to create implementation(s) of {@link ILaunchConfAttrDataBuilder} and {@link IAttrServices}.
 * 
 * @author egeay
 */
public final class AttrServicesFactory {
  
  /**
   * Creates a wrapper for a string attribute.
   * 
   * @param name The attribute name.
   * @param withIndent Indicates if yes or no the attribute needs to be indented when displayed in a dialog.
   * @param value The attribute value.
   * @return A non-null implementation of {@link IAttrServices}.
   */
  public static IAttrServices createAttr(final String name, final boolean withIndent, final String value) {
    return new StringAttrServices(name, withIndent, value);
  }
  
  /**
   * Creates a wrapper for an integer attribute.
   * 
   * @param name The attribute name.
   * @param withIndent Indicates if yes or no the attribute needs to be indented when displayed in a dialog.
   * @param value The attribute value.
   * @return A non-null implementation of {@link IAttrServices}.
   */
  public static IAttrServices createAttr(final String name, final boolean withIndent, final int value) {
    return new IntegerAttrServices(name, withIndent, value);
  }
  
  /**
   * Creates a wrapper for a boolean attribute.
   * 
   * @param name The attribute name.
   * @param withIndent Indicates if yes or no the attribute needs to be indented when displayed in a dialog.
   * @param value The attribute value.
   * @return A non-null implementation of {@link IAttrServices}.
   */
  public static IAttrServices createAttr(final String name, final boolean withIndent, final boolean value) {
    return new BooleanAttrServices(name, withIndent, value);
  }
  
  
  /**
   * Creates a wrapper for a list attribute.
   * 
   * @param name The attribute name.
   * @param withIndent Indicates if yes or no the attribute needs to be indented when displayed in a dialog.
   * @param value The attribute value.
   * @return A non-null implementation of {@link IAttrServices}.
   */
  public static IAttrServices createAttr(final String name, final boolean withIndent, final List<String> value) {
    return new ListAttrServices(name, withIndent, value);
  }
  
  /**
   * Creates an implementation of {@link ILaunchConfAttrDataBuilder} that will process the Application, 
   * Communication Interface and Environment tabs of an X10 launch configuration with C++ back-end, and whatever extra tabs 
   * are added to the extension <b>attrDataBuilder</b> of the extension point <b>x10dt.ui.launch.cpp.launchConfServices</b>.
   * 
   * @param commIntfConf The Communication Interface Configuration to consider for the launch configuration that the
   * builder will have to process.
   * @return A non-null implementation of {@link ILaunchConfAttrDataBuilder}.
   */
  public static ILaunchConfAttrDataBuilder createBuilder(final ICommunicationInterfaceConf commIntfConf) {
    final String serviceTypeId = commIntfConf.getServiceTypeId();
    final ILaunchConfAttrDataBuilder baseBuilder;
    if (PTPConstants.SOCKETS_SERVICE_PROVIDER_ID.equals(serviceTypeId)) {
      baseBuilder = new SocketsLaunchConfAttrDataBuilder();
    } else {
      baseBuilder = new DefaultsLaunchConfAttrDataBuilder();
    }
    
    final IExtensionPoint extPt = Platform.getExtensionRegistry().getExtensionPoint(CppLaunchCore.PLUGIN_ID, 
                                                                                    LAUNCH_CONF_SERVICES_EXT_POINT_ID);
    final LinkedList<ILaunchConfAttrDataBuilder> extBuilders = new LinkedList<ILaunchConfAttrDataBuilder>();
    if (extPt != null) {
      for (final IConfigurationElement configElement : extPt.getConfigurationElements()) {
        try {
          extBuilders.add((ILaunchConfAttrDataBuilder) configElement.createExecutableExtension(ATTR_BUILDER));
        } catch (CoreException except) {
          CppLaunchCore.log(except.getStatus());
        }
      }
    }
    if (extBuilders.isEmpty()) {
      return baseBuilder;
    } else {
      extBuilders.addFirst(baseBuilder);
      return new CompositeLaunchConfAttrDataBuilder(extBuilders);
    }
  }
  
  // --- Private code
  
  private AttrServicesFactory() {}
  
  // --- Fields
  
  private static final String LAUNCH_CONF_SERVICES_EXT_POINT_ID = "launchConfServices"; //$NON-NLS-1$
  
  private static final String ATTR_BUILDER = "attrDataBuilder"; //$NON-NLS-1$

}
