package x10.effects.constraints;

import x10.constraint.XConstraint;
import x10.constraint.XTerm;
import x10.constraint.XType;
import x10.constraint.XVar;
import x10.types.constraints.XLocal;

/**
 * Represents a mutable local variable.
 * 
 * @author vj
 *
 */
public class LocalLocs_c<T extends XType> extends Locs_c<T> implements LocalLocs<T> {

	final XVar<T> local;
	LocalLocs_c(XVar<T> x) {
		assert x != null : "Cannot construct LocalLocs_c from null";
		this.local = x;
	}
	public XVar<T> local() { return local;}
	
	public boolean disjointFrom(Locs<T> other, XConstraint<T> c) {
		return ! equals(other);
	}

	public XTerm<T> term() {
		return local;
	}
	
	public boolean hasSubterm(XTerm<T> t) {
		return local.equals(t);
	}
	
	/**
	 * It should never be the case that 
	 */
	public Locs<T> substitute(XTerm<T> t, XVar<T> s) {
		assert false : "Should never have to replace " + s + " by " + t + " in " + this;
		return this;
	}
	@Override
	public boolean equals(Object other) {
		if (other == this) return true;
		if (! (other instanceof LocalLocs_c)) 
			return false;
		@SuppressWarnings("unchecked")
		LocalLocs_c<T> o = (LocalLocs_c<T>) other;
		return local.equals(o.local());
	}

	@Override 
	public int hashCode() {
		return local.hashCode();
	}
	@Override
	public String toString() {
	    return local.toString();
	}
	
	
}
