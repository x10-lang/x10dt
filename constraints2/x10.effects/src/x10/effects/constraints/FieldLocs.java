package x10.effects.constraints;

import x10.constraint.XTerm;
import x10.constraint.XType;

/**
 * A field represents the mutable location o.f where o is an object
 * and f is a mutable field of that object.
 * @author vj
 *
 */
public interface FieldLocs<T extends XType> extends Locs<T>, RigidTerm<T> {
	XTerm<T> obj();
	String field();
}
