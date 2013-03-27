package x10dt.ui.builder.java;

import org.eclipse.osgi.util.NLS;


@SuppressWarnings("all")
public class Messages extends NLS {

  public static String X10ProjectWizardSecondPage_noSourceEntry;
  public static String XJB_CompileErrors;
  public static String GenFileNotFound;
  
  private Messages() {
  }
  
  // --- Fields
  
  private static final String BUNDLE_NAME = "x10dt.ui.builder.java.messages"; //$NON-NLS-1$

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }
  
}
