package x10dt.ui.launch.cpp.editors;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;

import x10dt.ui.launch.cpp.LaunchMessages;

/**
 * Defines Platform Configuration editor action bar contributor. See <i>org.eclipse.ui.editors</i> extension point.
 * 
 * @author egeay
 */
public final class FormEditorActionBarContributor extends MultiPageEditorActionBarContributor {
  
  // --- Abstract methods implementation
  
  public void setActivePage(final IEditorPart activeEditor) {
    if (this.fEditor != null) {
      updateActions();
      getActionBars().updateActionBars();
    }
  }

  // --- Overridden methods
  
  public void init(final IActionBars bars) {
    super.init(bars);
    
    this.fRevertAction = new RevertAction();
    this.fRevertAction.setEnabled(false);
    getActionBars().setGlobalActionHandler(ActionFactory.REVERT.getId(), this.fRevertAction);
  }
  
  public void setActiveEditor(final IEditorPart targetEditor) {
    if (targetEditor instanceof X10PlatformConfFormEditor) {
      this.fEditor = (X10PlatformConfFormEditor) targetEditor;
    }
  }
  
  // --- Internal services
  
  void updateActions() {
    this.fRevertAction.update();
  }
  
  // --- Private classes
  
  private final class RevertAction extends Action {
    
    RevertAction() {
      super(LaunchMessages.FEABC_RevertAction);
    }
    
    // --- Overridden methods
    
    public void run() {
      FormEditorActionBarContributor.this.fEditor.performRevert();
    }
    
    // --- Internal classes
    
    void update() {
      final X10PlatformConfFormEditor editor = FormEditorActionBarContributor.this.fEditor;
      setEnabled(editor == null ? false : editor.isDirty());
    }
    
  }
  
  // --- Fields
  
  private X10PlatformConfFormEditor fEditor;
  
  private RevertAction fRevertAction;
  
}
