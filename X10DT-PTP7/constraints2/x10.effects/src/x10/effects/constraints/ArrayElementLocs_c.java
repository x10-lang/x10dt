package x10.effects.constraints;

import x10.constraint.XConstraint;
import x10.constraint.XTerm;
import x10.constraint.XType;
import x10.constraint.XVar;

/**
 * Implements the mutable array element a(t), where a is an Array
 * and t an Index. 
 * @author vj
 *
 */
public class ArrayElementLocs_c<T extends XType> extends RigidTerm_c<T> implements ArrayElementLocs<T> {
	
	XTerm<T> index;
	public ArrayElementLocs_c(XTerm<T> array, XTerm<T> index) {
		super(array);
		this.index=index;
	}
	
	public XTerm<T> array() { return designator();}
	public XTerm<T> index() { return index;}
	
	public Locs<T> substitute(XTerm<T> t, XVar<T> s) {
		XTerm<T> old = designator();
		XTerm<T> result = old.subst(t, s);
		XTerm<T> newIndex = index.subst(t, s);
		if (result.equals(old) && newIndex.equals(index))
			return this;
		else 
			return Effects.makeArrayElementLocs(result, newIndex);
		
	}
	public ArrayLocs<T> generalize(XVar<T> x) {
		return (index.hasVar(x)) ? Effects.makeArrayLocs(array()) : null;
	}
	public boolean disjointFrom(Locs<T> other, XConstraint<T> c){
		if (other instanceof ArrayLocs) {
        	ArrayLocs<T> o = (ArrayLocs<T>) other;
        	return (c.entailsDisEquality(array(), o.designator()));
        }
        if (other instanceof ArrayElementLocs) {
        	ArrayElementLocs<T> o = (ArrayElementLocs<T>) other;
        	if (c.entailsDisEquality(array(), o.array()))
        		return true;
        	return c.entailsDisEquality(index(), o.index());
        }

		return true;
	}

	@Override
	public String toString() {
	    return array().toString() + "(" + index() + ")";
	}
	
	@Override
	public int hashCode() {
		return array().hashCode() + index().hashCode();
	}
	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (! (other instanceof ArrayElementLocs_c)) return false;
		@SuppressWarnings("unchecked")
		ArrayElementLocs_c<T> o = (ArrayElementLocs_c<T>) other;
		return array().equals(o.array()) 
		&& index().equals(o.index());
	}
}
