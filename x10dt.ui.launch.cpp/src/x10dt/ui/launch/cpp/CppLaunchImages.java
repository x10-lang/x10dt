/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.cpp;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;

/**
 * Provides access to all images of this plugin.
 * 
 * @author egeay
 */
@SuppressWarnings("all")
public class CppLaunchImages {
  
  private static final IPath ICONS_PATH = new Path("$nl$/icons"); //$NON-NLS-1$
  
  // --- Icons images
  
  public static final String VALID_CONNECTION = "obj/ok-icon.png"; //$NON-NLS-1$
  
  public static final String INVALID_CONNECTION = "obj/error-icon.png"; //$NON-NLS-1$
  
  public static final String UNKOWN_CONNECTION = "obj/question-mark.png"; //$NON-NLS-1$
  
  public static final String CUR_CONNECTION = "obj/right-arrow.png"; //$NON-NLS-1$
  
  public static final String INVALID_PLATFORM_CONF = "obj/invalid-conf.png"; //$NON-NLS-1$
  
  public static final String PLATFORM_CONF_VALIDATION_ERROR = "obj/conf-valid-error.png"; //$NON-NLS-1$
  
  public static final String VALID_PLATFORM_CONF = "obj/valid-conf.png"; //$NON-NLS-1$
  
  public static final String INVALIDATED_PLATFORM_CONF = "obj/go-icon.png"; //$NON-NLS-1$
  
  public static final String FILE = "obj/x10-platform-conf.png"; //$NON-NLS-1$
  
  public static final String PLACES_HOSTS = "obj/places_hosts.gif";
  
  // --- Wizards images
  
  public static final String NEW_X10_PRJ_WIZBAN = "wizban/newx10prj_cpp.png"; //$NON-NLS-1$
  
  // --- Public services
  
  /**
   * Finds (from the registry) or creates the descriptor for a given image file name. This version caches the image 
   * in the {@link ImageRegistry}.
   * 
   * @param imagePath The relative path to the image for which one wants the descriptor.
   * @return A non-null instance of {@link ImageDescriptor}.
   */
  public static ImageDescriptor findOrCreateManaged(final String imagePath) {
    ImageDescriptor descriptor = fImageRegistry.getDescriptor(imagePath);
    if (descriptor == null) {
      descriptor = createUnmanaged(imagePath);
      fImageRegistry.put(imagePath, descriptor);
    }
    return descriptor;
  }
  
  /**
   * Creates the descriptor for a given image file name. This version does not cache the image in the {@link ImageRegistry}.
   * 
   * @param imagePath The relative path to the image for which one wants the descriptor.
   * @return A non-null instance of {@link ImageDescriptor}.
   */
  public static ImageDescriptor createUnmanaged(final String imagePath) {
    return createImageDescriptor(CppLaunchCore.getInstance().getBundle(), ICONS_PATH.append(imagePath), true);
  }
  
  /**
   * Creates an image descriptor for the given path in a bundle.
   * 
   * <p>If no image could be found, <code>useMissingImageDescriptor</code> decides if either the 'missing image descriptor' 
   * is returned or <code>null</code>.
   * 
   * @param bundle The bundle of interest.
   * @param path The path to the image.
   * @param useMissingImgDescriptor True if we should the "missing image descriptor" in case we could create one for the
   * path transmitted, false otherwise.
   * @return A descriptor for the image path, or <b>null</b> if we could not create one and <i>useMissingImgDescriptor</i>
   * is set to false.
   */
  public static ImageDescriptor createImageDescriptor(final Bundle bundle, final IPath path, boolean useMissingImgDescriptor) {
    final URL url= FileLocator.find(bundle, path, null);
    if (url != null) {
      return ImageDescriptor.createFromURL(url);
    }
    if (useMissingImgDescriptor) {
      return ImageDescriptor.getMissingImageDescriptor();
    }
    return null;
  }
  
  /**
   * Returns the image for the path transmitted if any exists with this key.
   * 
   * @param imagePath The path representing the key to access the image.
   * @return A non-null image if the path is within the image registry, <b>null</b> otherwise.
   */
  public static Image getImage(final String imagePath) {
    return fImageRegistry.get(imagePath);
  }
  
  /**
   * Removes the image from the image registry. It will have no effect if the image path is not part of the image registry.
   * 
   * @param imagePath The path representing the key to access the image.
   */
  public static void removeImage(final String imagePath) {
    fImageRegistry.remove(imagePath);
  }
  
  // --- Fields
  
  private static final ImageRegistry fImageRegistry = new ImageRegistry();

}
