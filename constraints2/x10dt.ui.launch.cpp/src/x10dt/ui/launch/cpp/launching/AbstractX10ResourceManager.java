package x10dt.ui.launch.cpp.launching;

import org.eclipse.ptp.rm.core.rmsystem.AbstractToolResourceManager;
import org.eclipse.ptp.rmsystem.AbstractResourceManagerConfiguration;
import org.eclipse.ptp.rtsystem.AbstractRuntimeResourceManagerControl;
import org.eclipse.ptp.rtsystem.AbstractRuntimeResourceManagerMonitor;

import x10dt.ui.launch.rms.core.provider.AbstractX10RuntimeSystem;

public abstract class AbstractX10ResourceManager extends AbstractToolResourceManager {

	public AbstractX10ResourceManager(AbstractResourceManagerConfiguration config, AbstractRuntimeResourceManagerControl control,
			AbstractRuntimeResourceManagerMonitor monitor) {
		super(config, control, monitor);
	}
	
	 public AbstractX10RuntimeSystem getRuntimeSystem(){
		  return (AbstractX10RuntimeSystem) super.getRuntimeSystem();
	  }
}
