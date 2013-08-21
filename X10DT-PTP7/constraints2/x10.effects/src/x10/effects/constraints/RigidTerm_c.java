package x10.effects.constraints;

import x10.constraint.XConstraint;
import x10.constraint.XTerm;
import x10.constraint.XType;

/**
 * The base class for rigid terms. A rigid term designates an
 * immutable value.
 * 
 * @author vj
 *
 */
public class RigidTerm_c<T extends XType> implements RigidTerm<T> {

	XTerm<T> designator;
	
	public RigidTerm_c(XTerm<T> d) {
		this.designator = d;
	}
	
	public XTerm<T> designator() {
		return designator;
	}

	public boolean equals(RigidTerm<T> other, XConstraint<T> c) {
		if (! (other instanceof RigidTerm_c)) return false;
		RigidTerm_c<T> o = (RigidTerm_c<T>) other;
		return c.entailsEquality(designator(), o.designator());
	}

}
