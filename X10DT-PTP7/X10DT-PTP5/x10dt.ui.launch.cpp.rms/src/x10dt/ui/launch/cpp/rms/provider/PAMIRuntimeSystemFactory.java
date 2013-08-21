package x10dt.ui.launch.cpp.rms.provider;


import org.eclipse.ptp.rmsystem.IResourceManager;
import org.eclipse.ptp.rtsystem.IRuntimeSystem;
import org.eclipse.ptp.rtsystem.IRuntimeSystemFactory;

public class PAMIRuntimeSystemFactory implements IRuntimeSystemFactory{
	
		
		public IRuntimeSystem create(IResourceManager rm) {
			return new PAMIX10RuntimeSystem((PAMIResourceManager) rm);
		}
}



