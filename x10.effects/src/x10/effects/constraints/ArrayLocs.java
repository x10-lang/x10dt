package x10.effects.constraints;

import x10.constraint.XType;

/**
 * An interface representing an array with mutable elements. 
 * In the source language, should be an 
 * expresison of type Array.
 * @author vj
 *
 */
public interface ArrayLocs<T extends XType> extends RigidTerm<T>, Locs<T>{

}
