package x10dt.ui.launch.core.actions;

/**
 * This action is used to convert the platform conf appropriately during a project conversion.
 * To convert to Java backend, the platform conf is renamed to start with a dot to make it invisible.
 * To convert to C++ backend, the plarform conf is renamed back to make it visible.
 * @author mvaziri
 *
 */
public interface IPlatformConfHider {
	
	public void hide();
}
