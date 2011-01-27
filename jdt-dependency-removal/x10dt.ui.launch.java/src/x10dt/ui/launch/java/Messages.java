package x10dt.ui.launch.java;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
    private static final String BUNDLE_NAME= "x10dt.ui.launch.java.messages"; //$NON-NLS-1$
    
    public static String XJS_X10RuntimeAccessError;

    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {}
}