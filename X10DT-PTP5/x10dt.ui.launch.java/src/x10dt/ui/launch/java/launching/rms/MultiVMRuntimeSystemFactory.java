package x10dt.ui.launch.java.launching.rms;

import org.eclipse.ptp.rmsystem.IResourceManager;
import org.eclipse.ptp.rtsystem.IRuntimeSystem;
import org.eclipse.ptp.rtsystem.IRuntimeSystemFactory;

public class MultiVMRuntimeSystemFactory implements IRuntimeSystemFactory {
	

	public IRuntimeSystem create(IResourceManager rm) {
		return new MultiVMX10RuntimeSystem((MultiVMResourceManager) rm);
	}

}