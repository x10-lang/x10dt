package x10dt.ui.launch.cpp.rms.provider;

import org.eclipse.ptp.rmsystem.IResourceManager;
import org.eclipse.ptp.rtsystem.IRuntimeSystem;
import org.eclipse.ptp.rtsystem.IRuntimeSystemFactory;

public class SocketsRuntimeSystemFactory implements IRuntimeSystemFactory {
	

	public IRuntimeSystem create(IResourceManager rm) {
		return new SocketsX10RuntimeSystem((SocketsResourceManager) rm);
	}

}
