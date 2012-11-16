package x10dt.ui.launch.cpp.launching;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.debug.ui.ILaunchConfigurationTabGroup;

public class X10RemoteCompilationConfigurationTabGroup extends AbstractLaunchConfigurationTabGroup 
implements ILaunchConfigurationTabGroup {

// --- Interface methods implementation

public final void createTabs(final ILaunchConfigurationDialog dialog, final String mode) {
  final List<ILaunchConfigurationTab> tabs = new ArrayList<ILaunchConfigurationTab>();;
  X10RemoteCompilationApplicationTab tab = new X10RemoteCompilationApplicationTab();
  tabs.add(tab);
  tabs.add(new ConnectionTab());
  tabs.add(new CompilationTab(tab));
  tabs.add(new CommonTab());
  setTabs(tabs.toArray(new ILaunchConfigurationTab[tabs.size()]));
}


}
