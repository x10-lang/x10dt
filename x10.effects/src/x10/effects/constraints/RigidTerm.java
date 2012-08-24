package x10.effects.constraints;

import x10.constraint.XConstraint;
import x10.constraint.XTerm;
import x10.constraint.XType;

/**
 * A rigid term is an expression that references only final variables.
 * 
 * 
 * @author vj
 *
 */
public interface RigidTerm<T extends XType> {
	
	/**
	 * The underlying designtor (term).
	 * @return
	 */
	XTerm<T> designator();
	
	/**
	 * Returns true iff o is another RigidTerm and c entails
	 * this.designator() == o.designator().
	 * 
	 * <p> This call must be used when the caller wants to 
	 * determine if these two rigid terms are forced to have
	 * the same r-value at runtime, given the constraint c.
	 * @param o
	 * @param c
	 * @return
	 */
	boolean equals(RigidTerm<T> o, XConstraint<T> c);

}
