package x10dt.ui.launch.cpp.rms.provider;

import org.eclipse.ptp.rmsystem.AbstractResourceManagerFactory;
import org.eclipse.ptp.rmsystem.IResourceManager;
import org.eclipse.ptp.rmsystem.IResourceManagerComponentConfiguration;
import org.eclipse.ptp.rmsystem.IResourceManagerConfiguration;
import org.eclipse.ptp.rmsystem.IResourceManagerControl;
import org.eclipse.ptp.rmsystem.IResourceManagerMonitor;
import org.eclipse.ptp.services.core.IServiceProvider;

public class SocketsResourceManagerFactory extends AbstractResourceManagerFactory{
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
		return new SocketsResourceManager((SocketsResourceManagerConfiguration) configuration, (SocketsResourceManagerControl) control,
				(SocketsResourceManagerMonitor) monitor);
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
		return new SocketsResourceManagerConfiguration(SocketsResourceManagerConfiguration.BASE, provider);
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
		return new SocketsResourceManagerControl((SocketsResourceManagerConfiguration) configuration);
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
		return new SocketsResourceManagerConfiguration(SocketsResourceManagerConfiguration.BASE, provider);
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
		return new SocketsResourceManagerMonitor((SocketsResourceManagerConfiguration) configuration);
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
		return new SocketsResourceManagerConfiguration(SocketsResourceManagerConfiguration.BASE, provider);
	}
}
