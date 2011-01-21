package x10dt.ui.navigator.internal;

import org.eclipse.imp.model.ISourceFolder;
import org.eclipse.imp.model.ISourceRoot;
import org.eclipse.imp.model.ModelFactory.ModelException;

public class Utils {
	public static boolean hasSubpackages(ISourceFolder folder) throws ModelException {
		ISourceFolder[] packages= ((ISourceRoot)folder.getParent()).getChildren();
		int namesLength = folder.getPath().segments().length;
		nextPackage: for (int i= 0, length = packages.length; i < length; i++) {
			String[] otherNames = ((ISourceFolder) packages[i]).getPath().segments();
			if (otherNames.length <= namesLength) continue nextPackage;
			for (int j = 0; j < namesLength; j++)
				if (!folder.getPath().segments()[j].equals(otherNames[j]))
					continue nextPackage;
			return true;
		}
		return false;
	}
}
