package x10.effects.constraints;

import x10.constraint.XType;
import x10.constraint.XVar;

/**
 * Represents a local variable.
 * 
 * @author vj
 *
 */
public interface LocalLocs<T extends XType>  extends Locs<T> {
	XVar<T> local();

}
