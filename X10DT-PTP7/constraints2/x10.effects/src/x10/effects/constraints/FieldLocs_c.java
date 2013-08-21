package x10.effects.constraints;

import x10.constraint.XConstraint;
import x10.constraint.XTerm;
import x10.constraint.XType;
import x10.constraint.XVar;


public class FieldLocs_c<T extends XType> extends RigidTerm_c<T> implements FieldLocs<T> {

	String fieldName;
	public FieldLocs_c(XTerm<T> o, String f) {
		super(o);
		this.fieldName=f;
	}
	
	public String field() { return fieldName;}
	
	public Locs<T> substitute(XTerm<T> t, XVar<T> s) {
		XTerm<T> old = designator();
		XTerm<T> result = old.copy().subst(t, s);
		return (result.equals(old)) ? this
				: Effects.makeFieldLocs(result, fieldName);
	}
	
	public XTerm<T> obj() { return designator();}
	
	public boolean disjointFrom(Locs<T> other, XConstraint<T> c){
		if (other instanceof ObjLocs) {
        	ObjLocs<T> o = (ObjLocs<T>) other;
        	return (c.entailsDisEquality(obj(), o.designator()));
        }
        if  (other instanceof FieldLocs) {
        	FieldLocs<T> o = (FieldLocs<T>) other;
        	if (c.entailsDisEquality(obj(), o.obj()))
        		return true;
        	return ! fieldName.equals(o.field());
        }
		return true;
	}

	@Override
	public String toString() {
	    return obj().toString() + "." + fieldName.toString();
	}
	@Override
	public int hashCode() {
		return designator().hashCode() + field().hashCode();
	}
	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (! (other instanceof FieldLocs_c)) return false;
		@SuppressWarnings("unchecked")
		FieldLocs_c<T> o = (FieldLocs_c<T>) other;
		return designator().equals(o.designator())
		&& field().equals(o.field());
	}
}
