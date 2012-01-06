package x10dt.ui.launch.java.launching.rms;

import org.eclipse.ptp.rmsystem.AbstractResourceManagerFactory;
import org.eclipse.ptp.rmsystem.IResourceManager;
import org.eclipse.ptp.rmsystem.IResourceManagerComponentConfiguration;
import org.eclipse.ptp.rmsystem.IResourceManagerConfiguration;
import org.eclipse.ptp.rmsystem.IResourceManagerControl;
import org.eclipse.ptp.rmsystem.IResourceManagerMonitor;
import org.eclipse.ptp.services.core.IServiceProvider;

public class MultiVMResourceManagerFactory extends AbstractResourceManagerFactory{
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ptp.rmsystem.AbstractResourceManagerFactory#create(org.eclipse
	 * .ptp.rmsystem.IResourceManagerConfiguration,
	 * org.eclipse.ptp.rmsystem.IResourceManagerControl,
	 * org.eclipse.ptp.rmsystem.IResourceManagerMonitor)
	 */
	@Override
	public IResourceManager create(IResourceManagerConfiguration configuration, IResourceManagerControl control,
			IResourceManagerMonitor monitor) {
		return new MultiVMResourceManager((MultiVMResourceManagerConfiguration) configuration, (MultiVMResourceManagerControl) control,
				(MultiVMResourceManagerMonitor) monitor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ptp.rmsystem.AbstractResourceManagerFactory#createConfiguration
	 * (org.eclipse.ptp.services.core.IServiceProvider)
	 */
	@Override
	public IResourceManagerConfiguration createConfiguration(IServiceProvider provider) {
		return new MultiVMResourceManagerConfiguration(MultiVMResourceManagerConfiguration.BASE, provider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ptp.rmsystem.AbstractResourceManagerFactory#createControl
	 * (org.eclipse.ptp.rmsystem.IResourceManagerComponentControl)
	 */
	@Override
	public IResourceManagerControl createControl(IResourceManagerComponentConfiguration configuration) {
		return new MultiVMResourceManagerControl((MultiVMResourceManagerConfiguration) configuration);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ptp.rmsystem.AbstractResourceManagerFactory#
	 * createControlConfiguration
	 * (org.eclipse.ptp.services.core.IServiceProvider)
	 */
	@Override
	public IResourceManagerComponentConfiguration createControlConfiguration(IServiceProvider provider) {
		return new MultiVMResourceManagerConfiguration(MultiVMResourceManagerConfiguration.BASE, provider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ptp.rmsystem.AbstractResourceManagerFactory#createMonitor
	 * (org.eclipse.ptp.rmsystem.IResourceManagerComponentControl)
	 */
	@Override
	public IResourceManagerMonitor createMonitor(IResourceManagerComponentConfiguration configuration) {
		return new MultiVMResourceManagerMonitor((MultiVMResourceManagerConfiguration) configuration);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ptp.rmsystem.AbstractResourceManagerFactory#
	 * createMonitorConfiguration
	 * (org.eclipse.ptp.services.core.IServiceProvider)
	 */
	@Override
	public IResourceManagerComponentConfiguration createMonitorConfiguration(IServiceProvider provider) {
		return new MultiVMResourceManagerConfiguration(MultiVMResourceManagerConfiguration.BASE, provider);
	}
}
