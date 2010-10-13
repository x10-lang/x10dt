package x10dt.search.ui.typeHierarchy;

import org.eclipse.jface.preference.IPreferenceStore;


public class X10Constants {
	public static final String TYPEFILTER_ENABLED = "";
	
	public static final String ID_TYPE_HIERARCHY= "x10dt.search.ui.TypeHierarchy"; //$NON-NLS-1$
    public static final String ID_HIERARCHYPERSPECTIVE= "x10dt.ui.perspective";
    
    public static final String OPEN_HIERARCHY= "x10dt.ui.navigate.OpenHierarchy"; //$NON-NLS-1$


    /**
	 * A named preference that controls return type rendering of methods in the UI.
	 * <p>
	 * Value is of type <code>Boolean</code>: if <code>true</code> return types
	 * are rendered
	 * </p>
	 */
	public static final String APPEARANCE_METHOD_RETURNTYPE= "x10dt.search.ui.methodreturntype";//$NON-NLS-1$

	/**
	 * A named preference that controls type parameter rendering of methods in the UI.
	 * <p>
	 * Value is of type <code>Boolean</code>: if <code>true</code> return types
	 * are rendered
	 * </p>
	 * @since 3.1
	 */
	public static final String APPEARANCE_METHOD_TYPEPARAMETERS= "x10dt.search.ui.methodtypeparametesr";//$NON-NLS-1$

	/**
	 * A named preference that controls if package name compression is turned on or off.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 *
	 * @see #APPEARANCE_PKG_NAME_PATTERN_FOR_PKG_VIEW
	 */
	public static final String APPEARANCE_COMPRESS_PACKAGE_NAMES= "x10dt.search.ui.compresspackagenames";//$NON-NLS-1$

	/**
	 * A named preference that controls category rendering of Java elements in the UI.
	 * <p>
	 * Value is of type <code>Boolean</code>: if <code>true</code> category is rendered
	 * </p>
	 * @since 3.2
	 */
	public static final String APPEARANCE_CATEGORY= "x10dt.search.ui.category";//$NON-NLS-1$

	/**
	 * A named preference that defines the pattern used for package name compression.
	 * <p>
	 * Value is of type <code>String</code>. For example for the given package name 'org.eclipse.jdt' pattern
	 * '.' will compress it to '..jdt', '1~' to 'o~.e~.jdt'.
	 * </p>
	 */
	public static final String APPEARANCE_PKG_NAME_PATTERN_FOR_PKG_VIEW= "PackagesView.pkgNamePatternForPackagesView";//$NON-NLS-1$

	public static void initializeDefaultValues(IPreferenceStore store) {
		store.setDefault(APPEARANCE_COMPRESS_PACKAGE_NAMES, false);
		store.setDefault(APPEARANCE_METHOD_RETURNTYPE, true);
		store.setDefault(APPEARANCE_METHOD_TYPEPARAMETERS, true);
		store.setDefault(APPEARANCE_CATEGORY, true);
		store.setDefault(APPEARANCE_PKG_NAME_PATTERN_FOR_PKG_VIEW, ""); //$NON-NLS-1$
	}
}
