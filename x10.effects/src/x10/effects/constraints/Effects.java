package x10.effects.constraints;

import x10.constraint.XTerm;
import x10.constraint.XType;
import x10.constraint.XVar;
import x10.types.constraints.XLocal;

/**
 * A Factory class for creating effects.
 * 
 * @author vj 05/13/09
 *
 */
public class Effects {
	public static boolean PAR_FUN = false;
	public static boolean FUN = true;
	public static Effect BOTTOM_EFFECT = new Effect_c(PAR_FUN);
	/**
	 * The code walker will create effects for sequential leaf statements by calling 
	 * Factory.makeEffect(Factory.FUN),and will then add the XTerms picked up from the statement
	 * to the readSet, writeSet and atomicIncSet of the effect.
	 * 
	 * 
	 * @param isFun
	 * @return
	 */
	public static <T extends XType> Effect<T> makeEffect(boolean isFun) {
		return new Effect_c<T>(isFun);
	}
	
	/**
	 * Return an Obj designated by x. The caller must ensure
	 * that x is a rigid term and its type is an object type.
	 * 
	 * @param x
	 * @return
	 */
	public static <T extends XType> ObjLocs<T> makeObj(XTerm<T> x) {
		return new ObjLocs_c<T>(x);
	}
	/**
	 * Return a Locs corresponding to the object designated by
	 * the term x. 
	 * <p> Caller must ensure x is a rigid designator, that is, all
	 * variables occurring in it must be final. Caller must
	 * ensure that the type of x is an Object (with mutable fields); in
	 * particular it must not be an array.
	 * @param x
	 * @return
	 */
	public static <T extends XType> ObjLocs<T> makeObjLocs(XTerm<T> x) {
		return new ObjLocs_c<T>(x);
	}
	
	/**
	 * Return a Locs corresponding to the array designated by
	 * the term x. 
	 * <p> Caller must ensure x is a rigid designator, that is, all
	 * variables occurring in it must be final. Caller must
	 * ensure that the type of x is an Array whose members are
	 * mutable; in particular it must not be an object.
	 * @param x
	 * @return
	 */
	public static <T extends XType> ArrayLocs<T> makeArrayLocs(XTerm<T> x) {
		return new ArrayLocs_c<T>(x);
	}
	/**
	 * Returns a Locs corresponding to the local variable
	 * designated by x. 
	 * 
	 * <p> Caller must ensure that x is mutable.
	 * @param x
	 * @return
	 */
	public static <T extends XType> LocalLocs<T> makeLocalLocs(XVar<T> x) {
		return new LocalLocs_c<T>(x);
	}
	

	
	/**
	 * Returns a Locs corresponding to the local variable
	 * designated by x. 
	 * 
	 * <p> Caller must ensure that x is mutable.
	 * @param x
	 * @return
	 */
	public static <T extends XType> FieldLocs<T> makeFieldLocs(XTerm<T> o, String field) {
		return new FieldLocs_c<T>(o, field);
	}
	
	public static <T extends XType> ArrayElementLocs<T> makeArrayElementLocs(XTerm<T> a, XTerm<T> t) {
		return new ArrayElementLocs_c<T>(a, t);
	}

	public static <T extends XType> Effect<T> makeBottomEffect() {
		return BOTTOM_EFFECT;
	}
}
