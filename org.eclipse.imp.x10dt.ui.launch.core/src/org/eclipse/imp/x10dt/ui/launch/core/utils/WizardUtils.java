/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package org.eclipse.imp.x10dt.ui.launch.core.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Utility methods for the various X10 wizards.
 * 
 * @author egeay
 */
public final class WizardUtils {
  
  /**
   * Creates a label with a push button on the right.
   * 
   * @param parent The composite parent to use.
   * @param labelText The text for the label.
   * @param buttonText The text for the push button.
   * @param selectionListener The selection listener to use if necessary. A null value is allowed if one does not want to
   * listen to selection event.
   * @return The button created. A non-null value in all cases.
   */
  public static Button createLabelAndPushButton(final Composite parent, final String labelText, final String buttonText, 
                                                final SelectionListener selectionListener) {
    final Label label = new Label(parent, SWT.NONE);
    label.setText(labelText);
    label.setFont(parent.getFont());
    label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    
    final Button button = new Button(parent, SWT.PUSH);
    button.setFont(parent.getFont());
    button.setText(buttonText);
    button.setLayoutData(new GridData(SWT.RIGHT, SWT.NONE, false, false));
    if (selectionListener != null) {
      button.addSelectionListener(selectionListener);
    }
    return button;
  }
  
  /**
   * Creates a label with a text widget on the right.
   * 
   * @param parent The composite parent to use.
   * @param labelText The text for the label.
   * @param modifyListener The modification listener to use if necessary. A null value is allowed if one does not want to
   * listen to modification event.
   * @return The text widget created. A non-null value in all cases.
   */
  public static Text createLabelAndText(final Composite parent, final String labelText, final ModifyListener modifyListener) {
    final Label label = new Label(parent, SWT.NONE);
    label.setText(labelText);
    label.setFont(parent.getFont());
    label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
    
    final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
    text.setFont(parent.getFont());
    text.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
    if (modifyListener != null) {
      text.addModifyListener(modifyListener);
    }
    return text;
  }
  
  /**
   * Initializes the given file's contents with "Hello World" source code in X10.
   * 
   * @param sourceFile
   *          The file to fill with the "Hello World" source code.
   * @param typeName
   *          The type that will contain the code.
   * @param pkgFrag
   *          The package fragment for the type.
   * @param superClass
   *          The super class of the type to define, if any. May be <b>nul</b>.
   * @param superIntfs
   *          The interfaces that this type may implement. The list can be empty or <b>null</b>.
   * @param createMain
   *          Indicates if we want to create the main method for the sample or not.
   * @param createConstructors
   *          Indicates if we want to create a constructor for the sample or not.
   * @return A non-null input stream encapsulating the sample code.
   */
  public static InputStream createSampleContentStream(final IFile sourceFile, final String typeName,
                                                      final IPackageFragment pkgFrag, final String superClass,
                                                      final List<String> superIntfs, final boolean createMain,
                                                      final boolean createConstructors) {
    final StringBuilder sb = new StringBuilder();

    if (!pkgFrag.isDefaultPackage()) {
      sb.append("package " + pkgFrag.getElementName() + ";\n\n"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    sb.append("public class " + typeName); //$NON-NLS-1$
    if (superClass != null && superClass.length() > 0 && !superClass.equals("x10.lang.Object")) { //$NON-NLS-1$
      sb.append(" extends " + superClass); //$NON-NLS-1$
    }
    if ((superIntfs != null) && superIntfs.size() > 0) {
      sb.append(" implements "); //$NON-NLS-1$
      for (final Iterator<String> iter = superIntfs.iterator(); iter.hasNext();) {
        sb.append(iter.next());
        if (iter.hasNext()) {
          sb.append(", "); //$NON-NLS-1$
        }
      }
    }
    sb.append(" {\n"); //$NON-NLS-1$
    if (createMain) {
      sb.append("    public static def main(var args: Rail[String]): void = {\n"); //$NON-NLS-1$
      sb.append("         Console.OUT.println(\"Hello X10 world\");\n"); //$NON-NLS-1$
      sb.append("         var h :Hello = new Hello();\n"); //$NON-NLS-1$
      sb.append("         var myBool:boolean = h.myMethod();\n"); //$NON-NLS-1$
      sb.append("         Console.OUT.println(\"The answer is: \"+myBool);\n"); //$NON-NLS-1$
      sb.append("    }\n"); //$NON-NLS-1$
    }
    if (createConstructors) {
      sb.append("    /** x10doc comment for myMethod */;\n"); //$NON-NLS-1$
      sb.append("    public def myMethod(): boolean = {\n"); //$NON-NLS-1$
      sb.append("       return true;\n"); //$NON-NLS-1$
      sb.append("    }\n"); //$NON-NLS-1$
    }
    sb.append("}"); //$NON-NLS-1$

    return new ByteArrayInputStream(sb.toString().getBytes());
  }

}
