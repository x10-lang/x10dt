/*
 * Created on Jul 20, 2006
 */
package x10.uide.editor;

import java.util.HashSet;
import java.util.Set;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uide.core.ILanguageService;
import org.eclipse.uide.editor.ProblemsLabelDecorator;
import polyglot.ast.ClassDecl;
import polyglot.ast.FieldDecl;
import polyglot.ast.Node;
import polyglot.ast.ProcedureDecl;
import x10.uide.X10UIPlugin;
import x10.uide.views.Outliner;

public class X10LabelProvider implements ILabelProvider, ILanguageService {
    private Set<ILabelProviderListener> fListeners= new HashSet<ILabelProviderListener>();

//  private ProblemsLabelDecorator fLabelDecorator;

    public static final String DEFAULT_AST= "default_ast.gif";

    // TODO Shouldn't need these decorated images - use 1 CU image combined w/ 0+ decoration images
    public static final String COMPILATION_UNIT_NORMAL= "compilationUnitNormal.gif";
    public static final String COMPILATION_UNIT_WARNING= "compilationUnitWarning.gif";
    public static final String COMPILATION_UNIT_ERROR= "compilationUnitError.gif";

    private static ImageRegistry sImageRegistry= X10UIPlugin.getInstance().getImageRegistry();

    private static ImageDescriptor DEFAULT_AST_IMAGE_DESC;

    private static ImageDescriptor COMPILATION_UNIT_NORMAL_IMAGE_DESC;
    private static ImageDescriptor COMPILATION_UNIT_WARNING_IMAGE_DESC;
    private static ImageDescriptor COMPILATION_UNIT_ERROR_IMAGE_DESC;

    private static Image DEFAULT_AST_IMAGE= sImageRegistry.get(DEFAULT_AST);

    private static Image COMPILATION_UNIT_NORMAL_IMAGE= sImageRegistry.get(COMPILATION_UNIT_NORMAL);
    private static Image COMPILATION_UNIT_WARNING_IMAGE= sImageRegistry.get(COMPILATION_UNIT_WARNING);
    private static Image COMPILATION_UNIT_ERROR_IMAGE= sImageRegistry.get(COMPILATION_UNIT_ERROR);

    static {
	final ImageRegistry ir= X10UIPlugin.getInstance().getImageRegistry();

	DEFAULT_AST_IMAGE_DESC= X10UIPlugin.create(DEFAULT_AST);
	ir.put(DEFAULT_AST, DEFAULT_AST_IMAGE_DESC);
	DEFAULT_AST_IMAGE= ir.get(DEFAULT_AST);

	COMPILATION_UNIT_NORMAL_IMAGE_DESC= X10UIPlugin.create(COMPILATION_UNIT_NORMAL);
	ir.put(COMPILATION_UNIT_NORMAL, COMPILATION_UNIT_NORMAL_IMAGE_DESC);
	COMPILATION_UNIT_NORMAL_IMAGE= ir.get(COMPILATION_UNIT_NORMAL);

	COMPILATION_UNIT_WARNING_IMAGE_DESC= X10UIPlugin.create(COMPILATION_UNIT_WARNING);
	ir.put(COMPILATION_UNIT_WARNING, COMPILATION_UNIT_WARNING_IMAGE_DESC);
	COMPILATION_UNIT_WARNING_IMAGE= ir.get(COMPILATION_UNIT_WARNING);

	COMPILATION_UNIT_ERROR_IMAGE_DESC= X10UIPlugin.create(COMPILATION_UNIT_ERROR);
	ir.put(COMPILATION_UNIT_ERROR, COMPILATION_UNIT_ERROR_IMAGE_DESC);
	COMPILATION_UNIT_ERROR_IMAGE= ir.get(COMPILATION_UNIT_ERROR);
    }

    public Image getImage(Object node) {
	if (node instanceof ClassDecl) {
	    ClassDecl cd= (ClassDecl) node;

	    return cd.flags().isInterface() ? Outliner._DESC_OBJS_CFILEINT : Outliner._DESC_OBJS_CFILECLASS;
	} else if (node instanceof FieldDecl) {
	    FieldDecl fd= (FieldDecl) node;

	    if (fd.flags().isPublic()) return Outliner._DESC_FIELD_PUBLIC;
	    if (fd.flags().isPrivate()) return Outliner._DESC_FIELD_PRIVATE;
	    if (fd.flags().isProtected()) return Outliner._DESC_FIELD_PROTECTED;
	    return Outliner._DESC_FIELD_DEFAULT;
	} else if (node instanceof ProcedureDecl) {
	    ProcedureDecl pd= (ProcedureDecl) node;

	    if (pd.flags().isPublic()) return Outliner._DESC_MISC_PUBLIC;
	    if (pd.flags().isPrivate()) return Outliner._DESC_MISC_PRIVATE;
	    if (pd.flags().isProtected()) return Outliner._DESC_MISC_PROTECTED;
	    return Outliner._DESC_MISC_DEFAULT;
	}
	if (node instanceof IFile) {
	    IFile file= (IFile) node;

	    return getErrorTicksFromMarkers(file);
	}
	return DEFAULT_AST_IMAGE;
    }

    // TODO This is really generic, and belongs in the SAFARI runtime...
    private Image getErrorTicksFromMarkers(IResource res) {
	if (res == null || !res.isAccessible()) {
	    return COMPILATION_UNIT_NORMAL_IMAGE;
	}
	boolean hasWarnings= false; // if resource has errors, will return error image immediately
	IMarker[] markers= null;

	try {
	    markers= res.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_ONE);
	} catch (CoreException e) {
	    X10UIPlugin.log(e);
	    return COMPILATION_UNIT_NORMAL_IMAGE;
	}

	if (markers != null) {
	    for(int i= 0; i < markers.length; i++) {
		IMarker m= markers[i];
		int priority= m.getAttribute(IMarker.SEVERITY, -1);

		if (priority == IMarker.SEVERITY_WARNING) {
		    hasWarnings= true;
		} else if (priority == IMarker.SEVERITY_ERROR) {
		    return COMPILATION_UNIT_ERROR_IMAGE;
		}
	    }
	}
	return hasWarnings ? COMPILATION_UNIT_WARNING_IMAGE : COMPILATION_UNIT_NORMAL_IMAGE;
    }

    public String getText(Object element) {
	Node node= (Node) element;

	if (node instanceof ClassDecl) {
	    ClassDecl cd= (ClassDecl) node;
	    return cd.name();
	} else if (node instanceof FieldDecl) {
	    FieldDecl fd= (FieldDecl) node;
	    return fd.name();
	} else if (node instanceof ProcedureDecl) {
	    ProcedureDecl pd= (ProcedureDecl) node;
	    return pd.name() + "()";
	}
	return "???";
    }

    public void addListener(ILabelProviderListener listener) {
	fListeners.add(listener);
    }

    public void dispose() { }

    public boolean isLabelProperty(Object element, String property) {
	return false;
    }

    public void removeListener(ILabelProviderListener listener) {
	fListeners.remove(listener);
    }
}
