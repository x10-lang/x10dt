package x10.effects.constraints;

import x10.constraint.XConstraint;
import x10.constraint.XTerm;
import x10.constraint.XType;
import x10.constraint.XVar;

/**
 * Represents an array -- and hence the set of locations specified 
 * by its mutable fields. An object is designated by an XTerm. 
 * The XTerm must be rigid -- that is all variables occurring in it 
 * must be final.
 * @author vj
 *
 */

public class ArrayLocs_c<T extends XType> extends RigidTerm_c<T> implements ArrayLocs<T> {

	public ArrayLocs_c(XTerm<T> d) {
		super(d);
	}
	public Locs<T> substitute(XTerm<T> t, XVar<T> s) {
		XTerm<T> old = designator();
		XTerm<T> result = old.subst(t, s);
		return (result.equals(old)) ? this : Effects.makeArrayLocs(result);
	}
	
	public boolean disjointFrom(Locs<T> other, XConstraint<T> c) {
		if (other instanceof ArrayLocs_c) {
        	ArrayLocs_c<T> o = (ArrayLocs_c<T>) other;
        	return c.entailsDisEquality(designator(), o.designator());
        }
		return true;
	}

	@Override
	public String toString() {
		return designator().toString();
	}
	@Override
	public int hashCode() {
		return designator().hashCode();
	}
	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (! (other instanceof ArrayLocs_c)) return false;
		ArrayLocs_c<T> o = (ArrayLocs_c<T>) other;
		return designator().equals(o.designator());
		
	}
}
