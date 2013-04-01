package x10dt.core.utils;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.osgi.util.NLS;

import x10dt.core.Messages;
import x10dt.core.X10DTCorePlugin;

public class URIUtils {
	/**
	 * If the authority of uri is jazz, this method returns the query part of uri.
	 * If the authority is null, it returns uri (identity).
	 * If the authority is non-null and not jazz, then an error is logged and uri is returned.
	 * @param uri
	 * @return
	 */
	public static URI getExpectedURI(URI uri){
		try {
			if (uri == null)
				return null;
			if (uri.getAuthority() == null) {
				return uri;
			}
			
			if (uri.getAuthority().equals("jazz")) {
				if (uri.getQuery() != null) {
					return new URI(uri.getQuery());
				} else if (uri.getPath() != null){
					String root = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
					String path = uri.getPath();
					if (path.startsWith("/default/")) {
						return new URI("file", null, root + path.substring(8) , null, null);
					} else {
						return new URI("file", null, root + path, null, null);
					}
				} else {
					return uri;
				}
				
			} else { // --- Non-null authority that is not jazz
				X10DTCorePlugin.getInstance().logException(NLS.bind(Messages.UUUnknownAuthority, uri.getAuthority()), null);
				return uri;
			}
		} catch (URISyntaxException e){
			X10DTCorePlugin.getInstance().logException(Messages.UUException, e);
			return uri;
		} catch (NullPointerException e){
			X10DTCorePlugin.getInstance().logException(NLS.bind(Messages.UUException, uri.toString()), e);
			return uri;
		}
		
	}
}
