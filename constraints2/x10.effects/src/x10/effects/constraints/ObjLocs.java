package x10.effects.constraints;

import x10.constraint.XType;

/**
 * Interface representing an object with mutable fields.
 * Represents an object -- and hence the set of locations specified 
 * by its mutable fields. An object is designated by an XTerm. 
 * The XTerm must be rigid -- that is all variables occurring in it 
 * must be final.
 * 
 * @author vj
 *
 */
public interface ObjLocs<T extends XType> extends RigidTerm<T>, Locs<T> {

}
