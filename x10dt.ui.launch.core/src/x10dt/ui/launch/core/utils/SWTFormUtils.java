/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.ui.launch.core.utils;

import java.util.Collection;

import org.eclipse.debug.internal.ui.SWTFactory;
import org.eclipse.imp.utils.Pair;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

/**
 * Utility methods for creating SWT controls in forms.
 * 
 * @author egeay
 */
public class SWTFormUtils {
  
  /**
   * Calls {@link #createLabelAndCombo(Composite, String, FormToolkit, Collection)} with no container to collect the widgets.
   * 
   * @param parent The parent composite to consider.
   * @param labelText The text for the label.
   * @param toolkit The toolkit to use for creating the label and combo widgets.
   * @return The non-null Combo widget created.
   */
  public static Combo createLabelAndCombo(final Composite parent, final String labelText, final FormToolkit toolkit) {
    return createLabelAndCombo(parent, labelText, toolkit, null);
  }
  
  /**
   * Creates a label and combo widgets on the same row in a SWT form.
   * 
   * @param parent The parent composite to consider.
   * @param labelText The text for the label.
   * @param toolkit The toolkit to use for creating the label and combo widgets.
   * @param controlContainer A container that, if it is not <b>null</b> will contain after the call the label and combo 
   * controls. Can be useful for enabling/disabling of groups of controls easily.
   * @return The non-null Combo widget created.
   */
  public static Combo createLabelAndCombo(final Composite parent, final String labelText, final FormToolkit toolkit, 
                                          final Collection<Control> controlContainer) {
    final Composite composite = toolkit.createComposite(parent);
    final boolean isTableWrapLayout = parent.getLayout() instanceof TableWrapLayout;
    composite.setFont(parent.getFont());
    if (isTableWrapLayout) {
      final TableWrapLayout tableWrapLayout = new TableWrapLayout();
      tableWrapLayout.numColumns = 2;
      composite.setLayout(tableWrapLayout);
      composite.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    } else {
      composite.setLayout(new GridLayout(2, false));
      composite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
    }
    if (controlContainer != null) {
      controlContainer.add(composite);
    }
    
    final Label label = toolkit.createLabel(composite, labelText, SWT.NONE);
    if (isTableWrapLayout) {
      label.setLayoutData(new TableWrapData(TableWrapData.FILL, TableWrapData.MIDDLE));
    } else {
      label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
    }
    if (controlContainer != null) {
      controlContainer.add(label);
    }
    
    final Combo combo = new Combo(composite, SWT.READ_ONLY);
    if (isTableWrapLayout) {
      final TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
      td.indent = 5;
      combo.setLayoutData(td);
    } else {
      final GridData gd = new GridData(SWT.FILL, SWT.NONE, true, false);
      gd.horizontalIndent = 5;
      combo.setLayoutData(gd);
    }
    toolkit.paintBordersFor(composite);
    
    if (controlContainer != null) {
      controlContainer.add(combo);
    }
    return combo;
  }
  
  /**
   * Calls {@link #createLabelAndSpinner(Composite, String, FormToolkit, Collection)} with no container to collect the
   * widgets.
   * 
   * @param parent The parent composite to consider.
   * @param labelText The text for the label.
   * @param toolkit The toolkit to use for creating the label and spinner widgets.
   * @return The non-null spinner widget created.
   */
  public static Spinner createLabelAndSpinner(final Composite parent, final String labelText, final FormToolkit toolkit) {
    return createLabelAndSpinner(parent, labelText, toolkit, null);
  }
  
  /**
   * Creates a label and spinner widgets on the same row in a SWT form.
   * 
   * @param parent The parent composite to consider.
   * @param labelText The text for the label.
   * @param toolkit The toolkit to use for creating the label and spinner widgets.
   * @param controlContainer A container that, if it is not <b>null</b> will contain after the call the label and spinner 
   * controls. Can be useful for enabling/disabling of groups of controls easily.
   * @return The non-null spinner widget created.
   */
  public static Spinner createLabelAndSpinner(final Composite parent, final String labelText, final FormToolkit toolkit,
                                              final Collection<Control> controlContainer) {
    final Composite composite = toolkit.createComposite(parent);
    final boolean isTableWrapLayout = parent.getLayout() instanceof TableWrapLayout;
    composite.setFont(parent.getFont());
    if (isTableWrapLayout) {
      final TableWrapLayout tableWrapLayout = new TableWrapLayout();
      tableWrapLayout.numColumns = 2;
      composite.setLayout(tableWrapLayout);
      composite.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    } else {
      composite.setLayout(new GridLayout(2, false));
      composite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
    }
    if (controlContainer != null) {
      controlContainer.add(composite);
    }
    
    final Label label = toolkit.createLabel(composite, labelText, SWT.NONE);
    if (isTableWrapLayout) {
      label.setLayoutData(new TableWrapData(TableWrapData.FILL, TableWrapData.MIDDLE));
    } else {
      label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
    }
    if (controlContainer != null) {
      controlContainer.add(label);
    }
    final Spinner spinner = new Spinner(composite, SWT.NONE);
    if (isTableWrapLayout) {
      spinner.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    } else {
      spinner.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
    }
    if (controlContainer != null) {
      controlContainer.add(spinner);
    }
    return spinner;
  }
  
  /**
   * Calls {@link #createLabelAndText(Composite, String, FormToolkit, Collection, int)} with no container to collect the
   * widgets and with the default text widget height.
   * 
   * @param parent The parent composite to consider.
   * @param labelText The text for the label.
   * @param toolkit The toolkit to use for creating the label and text widgets.
   * @return The non-null Text widget created.
   */
  public static Text createLabelAndText(final Composite parent, final String labelText, final FormToolkit toolkit) {
    return createLabelAndText(parent, labelText, toolkit, null, 1, SWT.NONE);
  }
  
  /**
   * Calls {@link #createLabelAndText(Composite, String, FormToolkit, Collection, int)} with the default text widget height.
   * 
   * @param parent The parent composite to consider.
   * @param labelText The text for the label.
   * @param toolkit The toolkit to use for creating the label and text widgets.
   * @param controlContainer A container that, if it is not <b>null</b> will contain after the call the label and text 
   * controls. Can be useful for enabling/disabling of groups of controls easily.
   * @return The non-null Text widget created.
   */
  public static Text createLabelAndText(final Composite parent, final String labelText, final FormToolkit toolkit,
                                        final Collection<Control> controlContainer) {
    return createLabelAndText(parent, labelText, toolkit, controlContainer, 1, SWT.NONE);
  }
  
  /**
   * Calls {@link #createLabelAndText(Composite, String, FormToolkit, Collection, int)} with no container to collect the
   * widgets.
   * 
   * @param parent The parent composite to consider.
   * @param labelText The text for the label.
   * @param toolkit The toolkit to use for creating the label and text widgets.
   * @param textHeightFactor The height factor to consider for the text widget. A value of 1 will create the default height
   * value based on the font size. A value of 2 will multiply this default value by 2, and so on.
   * @return The non-null Text widget created.
   */
  public static Text createLabelAndText(final Composite parent, final String labelText, final FormToolkit toolkit,
                                        final int textHeightFactor) {
    return createLabelAndText(parent, labelText, toolkit, null, textHeightFactor, SWT.NONE);
  }
  
  /**
   * Creates a label and text widgets on the same row in a SWT form.
   * 
   * @param parent The parent composite to consider.
   * @param labelText The text for the label.
   * @param toolkit The toolkit to use for creating the label and text widgets.
   * @param controlContainer A container that, if it is not <b>null</b> will contain after the call the label and text 
   * controls. Can be useful for enabling/disabling of groups of controls easily.
   * @param textHeightFactor The height factor to consider for the text widget. A value of 1 will create the default height
   * value based on the font size. A value of 2 will multiply this default value by 2, and so on.
   * @param textStyle End-user text style.
   * @return The non-null Text widget created.
   */
  public static Text createLabelAndText(final Composite parent, final String labelText, final FormToolkit toolkit,
                                        final Collection<Control> controlContainer, final int textHeightFactor,
                                        final int textStyle) {
    final Composite composite = toolkit.createComposite(parent);
    final boolean isTableWrapLayout = parent.getLayout() instanceof TableWrapLayout;
    composite.setFont(parent.getFont());
    if (isTableWrapLayout) {
      final TableWrapLayout tableWrapLayout = new TableWrapLayout();
      tableWrapLayout.numColumns = 2;
      composite.setLayout(tableWrapLayout);
      composite.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    } else {
      composite.setLayout(new GridLayout(2, false));
      composite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
    }
    if (controlContainer != null) {
      controlContainer.add(composite);
    }
    
    final Label label = toolkit.createLabel(composite, labelText, SWT.NONE);
    if (isTableWrapLayout) {
      label.setLayoutData(new TableWrapData(TableWrapData.FILL, TableWrapData.MIDDLE));
    } else {
      label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
    }
    if (controlContainer != null) {
      controlContainer.add(label);
    }
    final int style = (textHeightFactor == 1) ? SWT.NONE : SWT.WRAP;
    final Text text = toolkit.createText(composite, null /* value */, style | textStyle);
    if (isTableWrapLayout) {
      final TableWrapData gd = new TableWrapData(TableWrapData.FILL_GRAB);
      if (textHeightFactor > 1) {
        gd.heightHint = text.getLineHeight() * textHeightFactor;
      }
      gd.indent = 5;
      text.setLayoutData(gd);
    } else {
      final GridData gd = new GridData(SWT.FILL, SWT.NONE, true, false);
      if (textHeightFactor > 1) {
        gd.heightHint = text.getLineHeight() * textHeightFactor;
      }
      gd.horizontalIndent = 5;
      text.setLayoutData(gd);
    }
    toolkit.paintBordersFor(composite);
    if (controlContainer != null) {
      controlContainer.add(text);
    }
    return text;
  }
  
  public static Text createLabelAndText(final Composite parent, final String labelText,
      final int textHeightFactor, final int textStyle) {
    final Composite composite= new Composite(parent, SWT.NONE);
    final boolean isTableWrapLayout= parent.getLayout() instanceof TableWrapLayout;
    composite.setFont(parent.getFont());
    if (isTableWrapLayout) {
      final TableWrapLayout tableWrapLayout= new TableWrapLayout();
      tableWrapLayout.numColumns= 2;
      composite.setLayout(tableWrapLayout);
      composite.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    } else {
      composite.setLayout(new GridLayout(2, false));
      composite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
    }
   

    final Label label= new Label(composite, SWT.NONE);
    label.setText(labelText);
    if (isTableWrapLayout) {
      label.setLayoutData(new TableWrapData(TableWrapData.FILL, TableWrapData.MIDDLE));
    } else {
      label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
    }
   
    final int style= (textHeightFactor == 1) ? SWT.NONE : SWT.WRAP;
    final Text text= new Text(composite, style | textStyle);
    if (isTableWrapLayout) {
      final TableWrapData gd= new TableWrapData(TableWrapData.FILL_GRAB);
      if (textHeightFactor > 1) {
        gd.heightHint= text.getLineHeight() * textHeightFactor;
      }
      gd.indent= 5;
      text.setLayoutData(gd);
    } else {
      final GridData gd= new GridData(SWT.FILL, SWT.NONE, true, false);
      if (textHeightFactor > 1) {
        gd.heightHint= text.getLineHeight() * textHeightFactor;
      }
      gd.horizontalIndent= 5;
      text.setLayoutData(gd);
    }
    return text;
  }
  
  /**
   * Creates a label, text (with the default text widget height) and button widgets on the same row in a SWT form.
   * 
   * @param parent The parent composite to consider.
   * @param labelText The text for the label.
   * @param buttonText The text for the button.
   * @param toolkit The toolkit to use for creating the label and text widgets.
   * @param controlContainer A container that, if it is not <b>null</b> will contain after the call the label and text 
   * controls. Can be useful for enabling/disabling of groups of controls easily.
   * @return The non-null Text and Button widgets created.
   */
  public static Pair<Text,Button> createLabelTextButton(final Composite parent, final String labelText, 
                                                        final String buttonText, final FormToolkit toolkit, 
                                                        final Collection<Control> controlContainer) {
    return createLabelTextButton(parent, labelText, buttonText, toolkit, controlContainer, 1);
  }
  
  /**
   * Creates a label, text and button widgets on the same row in a SWT form.
   * 
   * @param parent The parent composite to consider.
   * @param labelText The text for the label.
   * @param buttonText The text for the button.
   * @param toolkit The toolkit to use for creating the label and text widgets.
   * @param controlContainer A container that, if it is not <b>null</b> will contain after the call the label and text 
   * controls. Can be useful for enabling/disabling of groups of controls easily.
   * @param textHeightFactor The height factor to consider for the text widget. A value of 1 will create the default height
   * value based on the font size. A value of 2 will multiply this default value by 2, and so on.
   * @return The non-null Text and Button widgets created.
   */
  public static Pair<Text,Button> createLabelTextButton(final Composite parent, final String labelText, 
                                                        final String buttonText, final FormToolkit toolkit, 
                                                        final Collection<Control> controlContainer,
                                                        final int textHeightFactor) {
    final Composite composite = toolkit.createComposite(parent);
    composite.setFont(parent.getFont());
    final boolean isTableWrapLayout = parent.getLayout() instanceof TableWrapLayout;
    if (isTableWrapLayout) {
      final TableWrapLayout tableWrapLayout = new TableWrapLayout();
      tableWrapLayout.numColumns = 3;
      composite.setLayout(tableWrapLayout);
      composite.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    } else {
      composite.setLayout(new GridLayout(3, false));
      composite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
    }
    if (controlContainer != null) {
      controlContainer.add(composite);
    }
    
    final Label label = toolkit.createLabel(composite, labelText, SWT.NONE);
    if (isTableWrapLayout) {
      label.setLayoutData(new TableWrapData(TableWrapData.FILL, TableWrapData.MIDDLE));
    } else {
      label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
    }
    if (controlContainer != null) {
      controlContainer.add(label);
    }
    final int style = (textHeightFactor == 1) ? SWT.NONE : SWT.WRAP;
    final Text text = toolkit.createText(composite, null /* value */, style);
    if (isTableWrapLayout) {
      final TableWrapData twData = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.MIDDLE);
      if (textHeightFactor > 1) {
        twData.heightHint = text.getLineHeight() * textHeightFactor;
      }
      twData.indent = 5;
      text.setLayoutData(twData);
    } else {
      final GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
      if (textHeightFactor > 1) {
        gd.heightHint = text.getLineHeight() * textHeightFactor;
      }
      gd.horizontalIndent = 5;
      text.setLayoutData(gd);
    }
    if (controlContainer != null) {
      controlContainer.add(text);
    }
    final Button button = toolkit.createButton(composite, buttonText, SWT.PUSH);
    if (isTableWrapLayout) {
      final TableWrapData twData = new TableWrapData(TableWrapData.FILL, TableWrapData.MIDDLE);
      twData.heightHint = text.getLineHeight() + 4;
      button.setLayoutData(twData);
    } else {
      GridData gd = new GridData(SWT.FILL, SWT.CENTER, false, false);
      gd.heightHint = text.getLineHeight() + 4;
      button.setLayoutData(gd);
    }
    button.setEnabled(false);
    toolkit.paintBordersFor(composite);
    if (controlContainer != null) {
      controlContainer.add(button);
    }
    return new Pair<Text,Button>(text, button);
  }
  
  
  public static Pair<Text, Button> createLabelTextButton(final Composite parent, final String labelText, final String buttonText,
      final Collection<Control> controlContainer, final int textHeightFactor) {
    final Composite composite= new Composite(parent, SWT.NONE);
    composite.setFont(parent.getFont());
    final boolean isTableWrapLayout= parent.getLayout() instanceof TableWrapLayout;
    if (isTableWrapLayout) {
      final TableWrapLayout tableWrapLayout= new TableWrapLayout();
      tableWrapLayout.numColumns= 3;
      composite.setLayout(tableWrapLayout);
      composite.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    } else {
      composite.setLayout(new GridLayout(3, false));
      composite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
    }
    if (controlContainer != null) {
      controlContainer.add(composite);
    }

    final Label label= new Label(composite, SWT.NONE);
    label.setText(labelText);
    if (isTableWrapLayout) {
      label.setLayoutData(new TableWrapData(TableWrapData.FILL, TableWrapData.MIDDLE));
    } else {
      label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
    }
    if (controlContainer != null) {
      controlContainer.add(label);
    }
    final int style= (textHeightFactor == 1) ? SWT.NONE : SWT.WRAP;
    final Text text= new Text(composite, style);
    if (isTableWrapLayout) {
      final TableWrapData twData= new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.MIDDLE);
      if (textHeightFactor > 1) {
        twData.heightHint= text.getLineHeight() * textHeightFactor;
      }
      twData.indent= 5;
      text.setLayoutData(twData);
    } else {
      final GridData gd= new GridData(SWT.FILL, SWT.CENTER, true, false);
      if (textHeightFactor > 1) {
        gd.heightHint= text.getLineHeight() * textHeightFactor;
      }
      gd.horizontalIndent= 5;
      text.setLayoutData(gd);
    }
    if (controlContainer != null) {
      controlContainer.add(text);
    }
    final Button button= new Button(composite,  SWT.PUSH);
    button.setText(buttonText);
    if (isTableWrapLayout) {
      final TableWrapData twData= new TableWrapData(TableWrapData.FILL, TableWrapData.MIDDLE);
      twData.heightHint= text.getLineHeight() + 4;
      button.setLayoutData(twData);
    } else {
      GridData gd= new GridData(SWT.FILL, SWT.CENTER, false, false);
      gd.heightHint= text.getLineHeight() + 4;
      button.setLayoutData(gd);
    }
    button.setEnabled(false);
    if (controlContainer != null) {
      controlContainer.add(button);
    }
    return new Pair<Text, Button>(text, button);
  }
  
  public static Text createLabelAndText(final Composite parent, final String labelText, final Collection<Control> controlContainer) {
    return createLabelAndText(parent, labelText, new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1), SWT.BORDER,
                              controlContainer);
  }
  
  public static Text createLabelAndText(final Composite parent, final String labelText, final GridData gridData,
                                  final int style, final Collection<Control> controlContainer) {
    final Composite composite = new Composite(parent, SWT.NONE);
    composite.setFont(parent.getFont());
    composite.setLayout(new GridLayout(2, false));
    composite.setLayoutData(gridData);
    if (controlContainer != null) {
      controlContainer.add(composite);
    }
    final Label label = new Label(composite, SWT.NONE);
    label.setText(labelText);
    label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
    if (controlContainer != null) {
      controlContainer.add(label);
    }
    final Text text = new Text(composite, style);
    text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
    if (controlContainer != null) {
      controlContainer.add(text);
    }
    return text;
  }
  
  public static Pair<Text, Button> createLabelTextAndPushButton(final Composite parent, final String labelText, final String btText,
      final Collection<Control> controlContainer) {
    return createLabelTextAndPushButton(parent, labelText, btText, new GridData(SWT.FILL, SWT.NONE, true, false), controlContainer);
  }

  public static Pair<Text, Button> createLabelTextAndPushButton(final Composite parent, final String labelText, final String btText, final GridData gridData,
      final Collection<Control> controlContainer) {
    final Composite composite= new Composite(parent, SWT.NONE);
    composite.setFont(parent.getFont());
    composite.setLayout(new GridLayout(3, false));
    composite.setLayoutData(gridData);
    if (controlContainer != null) {
      controlContainer.add(composite);
    }
    final Label label= new Label(composite, SWT.NONE);
    label.setText(labelText);
    label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
    if (controlContainer != null) {
      controlContainer.add(label);
    }
    final Text text= new Text(composite, SWT.BORDER);
    text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    if (controlContainer != null) {
      controlContainer.add(text);
    }
    final Button button= SWTFactory.createPushButton(composite, btText, null /* image */);
    button.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
    if (controlContainer != null) {
      controlContainer.add(button);
    }
    return new Pair<Text, Button>(text, button);
  }
  
  // --- Private code
  
  private SWTFormUtils() {}

}
