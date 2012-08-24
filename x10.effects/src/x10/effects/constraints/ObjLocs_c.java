package x10.effects.constraints;

import x10.constraint.XConstraint;
import x10.constraint.XTerm;
import x10.constraint.XType;
import x10.constraint.XVar;

/**
 * 
 * @author vj
 *
 */

public class ObjLocs_c<T extends XType> extends RigidTerm_c<T> implements ObjLocs<T> {

	public ObjLocs_c(XTerm<T> d) {
		super(d);
	}

	public Locs<T> substitute(XTerm<T> t, XVar<T> s) {
		XTerm<T> old = designator();
		XTerm<T> result = old.subst(t, s);
		return (result.equals(old)) ? this : Effects.makeObjLocs(result);
	}
	
	public boolean disjointFrom(Locs<T> other, XConstraint<T> c) {
		if (other instanceof ObjLocs) {
        	return c.entailsDisEquality(designator(), ((ObjLocs<T>) other).designator());
        }
		return true;
	}

	@Override
	public String toString() {
	    return designator.toString();
	}
	@Override
	public int hashCode() {
		return designator().hashCode();
	}
	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (! (other instanceof ObjLocs_c)) return false;
		@SuppressWarnings("unchecked")
		ObjLocs_c<T> o = (ObjLocs_c<T>) other;
		return designator().equals(o.designator());
	}
}
