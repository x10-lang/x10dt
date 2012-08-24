package x10.effects.constraints;

import x10.constraint.XTerm;
import x10.constraint.XType;
import x10.constraint.XVar;

public interface ArrayElementLocs<T extends XType> extends Locs<T> {
	
	XTerm<T> array();
	XTerm<T> index();
	ArrayLocs<T> generalize(XVar<T> x);

}
