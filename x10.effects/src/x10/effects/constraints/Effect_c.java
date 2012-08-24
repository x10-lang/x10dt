/**
 * 
 */
package x10.effects.constraints;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import x10.constraint.XConstraint;
import x10.constraint.XConstraintManager;
import x10.constraint.XEQV;
import x10.constraint.XFailure;
import x10.constraint.XTerm;
import x10.constraint.XType;
import x10.constraint.XVar;
import x10.types.constraints.XLocal;


/**
 * (Skeleton as of now)
 * @author vj 05/13/09
 *
 */
public class Effect_c<T extends XType> implements Effect<T> {

	protected Set<Locs<T>> readSet, writeSet, atomicIncSet;
	protected boolean isFun;

	public Effect_c(boolean b) {
		isFun = b;
		readSet = new HashSet<Locs<T>>();
		writeSet = new HashSet<Locs<T>>();
		atomicIncSet = new HashSet<Locs<T>>();
	}
	
	public Effect_c<T> clone() {
		if (this == Effects.BOTTOM_EFFECT)
			return this;
		try {
			@SuppressWarnings("unchecked")
			Effect_c<T> result = (Effect_c<T>) super.clone();
			result.isFun=isFun;
			result.readSet = new HashSet<Locs<T>>();
			result.readSet.addAll(readSet());

			result.writeSet = new HashSet<Locs<T>>();
			result.writeSet.addAll(writeSet());

			result.atomicIncSet = new HashSet<Locs<T>>();
			result.atomicIncSet.addAll(atomicIncSet());
			return result;

		} catch (CloneNotSupportedException z) {
			// not reachable
			return null;
		}

	}
	/* (non-Javadoc)
	 * @see x10.effects.constraints.Effect#atomicIncSet()
	 */
	public Set<Locs<T>> atomicIncSet() {
		return atomicIncSet;
	}

	/* (non-Javadoc)
	 * @see x10.effects.constraints.Effect#commutesWith(x10.effects.constraints.Effect)
	 */
	public boolean commutesWith(Effect<T> e) {
		return commutesWith(e, XConstraintManager.<T>getConstraintSystem().makeConstraint());
	}

	private boolean disjoint(Set<Locs<T>> a, Set<Locs<T>> b, XConstraint<T> c) {
		for (Locs<T> l : a)
			for (Locs<T> m: b) {
				if (! l.disjointFrom(m, c))
					return false;
			}
		return true;
	}
	/* (non-Javadoc)
	 * @see x10.effects.constraints.Effect#commutesWith(x10.effects.constraints.Effect, x10.constraint.XConstraint)
	 */
	public boolean commutesWith(Effect<T> e, XConstraint<T> c) {
		if (e == Effects.BOTTOM_EFFECT)
			return false;
		final Set<Locs<T>> r = readSet(), w=writeSet(), a=atomicIncSet();
		final Set<Locs<T>> er = e.readSet(), ew=e.writeSet(), ea=e.atomicIncSet();
		return 
		disjoint(r,ew,c) 
		&& disjoint(r,ea,c) 
		&& disjoint(w, er,c)
		&& disjoint(w, ew,c)
		&& disjoint(w, ea,c)
		&& disjoint(a, er,c)
		&& disjoint(a, ew,c)
		&& disjoint(a, ea,c);
	}
/*
public boolean commutesWith(Effect<T> e, XConstraint c) {
		if (e == Effects.BOTTOM_EFFECT)
			return false;
		for (Locs l : readSet()) {
			for (Locs m: e.writeSet()) {
				if (! l.disjointFrom(m, c))
					return false;
			}
			for (Locs m: e.atomicIncSet()) {
				if (! l.disjointFrom(m, c))
					return false;
			}
		}
		for (Locs l : writeSet()) {
			for (Locs m: e.writeSet()) {
				if (! l.disjointFrom(m, c))
					return false;
			}
			for (Locs m: e.atomicIncSet()) {
				if (! l.disjointFrom(m, c))
					return false;
			}
		}
		for (Locs l : atomicIncSet()) {
			for (Locs m: e.writeSet()) {
				if (! l.disjointFrom(m, c))
					return false;
			}
			for (Locs m: e.atomicIncSet()) {
				if (! l.disjointFrom(m, c))
					return false;
			}
		}
		
		for (Locs l : e.readSet()) {
			for (Locs m: writeSet()) {
				if (! l.disjointFrom(m, c))
					return false;
			}
			for (Locs m: atomicIncSet()) {
				if (! l.disjointFrom(m, c))
					return false;
			}
		}
		return true;
	}*/
	/* (non-Javadoc)
	 * @see x10.effects.constraints.Effect#commutesWithForall(x10.constraint.XVar)
	 */
	public boolean commutesWithForall(XVar<T> x) {
		return commutesWithForall(x, XConstraintManager.<T>getConstraintSystem().makeConstraint());
	}

	/* (non-Javadoc)
	 * @see x10.effects.constraints.Effect#commutesWithForall(x10.constraint.XVar, x10.constraint.XConstraint)
	 */
	public boolean commutesWithForall(XVar<T> x, XConstraint<T> c) {
		if (this == Effects.BOTTOM_EFFECT)
			return false;
//		XLocal x1 = XTerms.makeLocal(XTerms.makeFreshName());
//		XLocal x2 = XTerms.makeLocal(XTerms.makeFreshName());
        XEQV<T> x1 = XConstraintManager.<T>getConstraintSystem().makeEQV(x.type());
        XEQV<T> x2 = XConstraintManager.<T>getConstraintSystem().makeEQV(x.type());
	
		Effect<T> e1 = substitute( x1, x), e2 = substitute(x2, x);
		XConstraint<T> c2 = c.copy();
		c2.addDisEquality(x1, x2);

		boolean result = e1.commutesWith(e2,  c2);
		return result;

	}

	private static class Pair<T1,T2> {
	    public T1 fst;
	    public T2 snd;
	    public Pair(T1 t1, T2 t2) {
	        fst= t1;
	        snd= t2;
	    }
	}

	public boolean commutesWithForall(List<XVar<T>> xs) {
        return commutesWithForall(xs, XConstraintManager.<T>getConstraintSystem().makeConstraint());
	}

	public boolean commutesWithForall(List<XVar<T>> xs, XConstraint<T> c) {
        if (this == Effects.BOTTOM_EFFECT)
            return false;
        Effect<T> e1 = this;
        Effect<T> e2 = this;
//      List<Pair<XLocal, XLocal>> freshVars= new ArrayList<Pair<XLocal,XLocal>>(xs.size());
        List<Pair<XEQV<T>, XEQV<T>>> freshVars= new ArrayList<Pair<XEQV<T>,XEQV<T>>>(xs.size());
        for(XVar<T> x: xs) {
//          XLocal x1= XTerms.makeLocal(XTerms.makeFreshName(x.toString()));
//          XLocal x2= XTerms.makeLocal(XTerms.makeFreshName(x.toString()));
            XEQV<T> x1 = XConstraintManager.<T>getConstraintSystem().makeEQV(x.type());
            XEQV<T> x2 = XConstraintManager.<T>getConstraintSystem().makeEQV(x.type());

            freshVars.add(new Pair<XEQV<T>, XEQV<T>>(x1, x2));
            e1 = e1.substitute(x1, x);
            e2 = e2.substitute(x2, x);
        }
        XConstraint<T> c2 = c.copy();
        for(Pair<XEQV<T>,XEQV<T>> freshVar: freshVars) {
            XEQV<T> x1= freshVar.fst;
            XEQV<T> x2= freshVar.snd;
            c2.addDisEquality(x1, x2);
        }

        boolean result = e1.commutesWith(e2,  c2);
        return result;

    }
	
	public Effect<T> substitute(XTerm<T> t, XVar<T> r) {
		if (this==Effects.BOTTOM_EFFECT)
			return this;
		Effect_c<T> result = new Effect_c<T>(isFun());
		for (Locs<T> l : readSet()) {
			result.readSet().add(l.substitute(t,r));
		}
		for (Locs<T> l : writeSet()) {
			result.writeSet().add(l.substitute(t,r));
		}
		for (Locs<T> l : atomicIncSet()) {
			result.atomicIncSet().add(l.substitute(t,r));
		}
		return result;
	}
	public Effect<T> exists(LocalLocs<T> x) {
		if (this == Effects.BOTTOM_EFFECT)
			return this;
		Effect_c<T> result = clone();
		result.readSet().remove(x);
		result.writeSet().remove(x);
		result.atomicIncSet().remove(x);
		return result;
	}
	/* (non-Javadoc)
	 * @see x10.effects.constraints.Effect#exists(x10.constraint.XVar)
	 */
	public Effect<T> exists(XVar<T> x, XTerm<T> t) {
		if (this == Effects.BOTTOM_EFFECT)
			return this;
		Effect_c<T> result = new Effect_c<T>(isFun());
		for (Locs<T> l : readSet()) {
			if (l.equals(x)) 
				continue;
			result.readSet().add(l.substitute(t, x));
		}
		for (Locs<T> l : writeSet()) {
			if (l.equals(x)) 
				continue;
			result.writeSet().add(l.substitute(t, x));
		}
		for (Locs<T> l : atomicIncSet()) {
			if (l.equals(x)) 
				continue;
			result.atomicIncSet().add(l.substitute(t, x));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see x10.effects.constraints.Effect#followedBy(x10.effects.constraints.Effect, x10.constraint.XConstraint)
	 */
	public Effect<T> followedBy(Effect<T> e, XConstraint<T> c)  {
		if (! isFun()) {
			if (! commutesWith(e, c))
				return null;
		}
		return union(e);
	}

	static private <T extends XType> void generalize(Set<Locs<T>> s, XVar<T> x) {
		for (Locs<T> l : s) {
			if (l instanceof ArrayElementLocs) {
				ArrayElementLocs al = (ArrayElementLocs) l;
				ArrayLocs array = al.generalize(x);
				if (array != null) {
					s.remove(l);
					s.add(array);
				}
					
			}
		}
	}
	/* (non-Javadoc)
	 * @see x10.effects.constraints.Effect#forall(x10.constraint.XVar)
	 */
	public Effect<T> forall(XVar<T> x) {
		if (this == Effects.BOTTOM_EFFECT)
			return this;
		Effect_c<T> result = clone();
		generalize(result.readSet(), x);
		generalize(result.writeSet(), x);
		generalize(result.atomicIncSet(), x);
		return result;
	}

	/* (non-Javadoc)
	 * @see x10.effects.constraints.Effect#isFun()
	 */
	public boolean isFun() {
		return isFun;
	}

	/* (non-Javadoc)
	 * @see x10.effects.constraints.Effect#makeFun()
	 */
	public Effect<T> makeFun() {
		if (this == Effects.BOTTOM_EFFECT)
			return this;
		Effect_c<T> result = clone();
		result.isFun = Effects.FUN;
		return result;
	}

	/* (non-Javadoc)
	 * @see x10.effects.constraints.Effect#makeParFun()
	 */
	public Effect<T> makeParFun() {
		if (this == Effects.BOTTOM_EFFECT)
			return this;
		Effect_c<T> result = clone();
		result.isFun = Effects.PAR_FUN;
		return result;
	}

	/* (non-Javadoc)
	 * @see x10.effects.constraints.Effect#readSet()
	 */
	 public Set<Locs<T>> readSet() {
		return readSet;
	}

	/* (non-Javadoc)
	 * @see x10.effects.constraints.Effect#writeSet()
	 */
	public Set<Locs<T>> writeSet() {
		return writeSet;
	}
	 public void addRead(Locs<T> t) {
		 if (this == Effects.BOTTOM_EFFECT) return;
			 readSet.add(t);
	}
	public void addWrite(Locs<T> t) {
		if (this == Effects.BOTTOM_EFFECT) return;
		writeSet.add(t);
	}
	public void addAtomicInc(Locs<T> t) {
		if (this == Effects.BOTTOM_EFFECT) return;
		atomicIncSet.add(t);
	}

	public Effect<T> union(Effect<T> e) {
		if (this == Effects.BOTTOM_EFFECT)
			return this;
		Effect_c<T> result = clone();
		result.readSet.addAll(e.readSet());
		result.writeSet.addAll(e.writeSet());
		result.atomicIncSet.addAll(e.atomicIncSet());
		return result;
	}

    @Override
    public String toString() {
    	if (this == Effects.BOTTOM_EFFECT)
    		return "BOTTOM_EFFECT";
        StringBuilder sb= new StringBuilder();
        sb.append("{ r: ");
        sb.append(readSet.toString());
        sb.append(", w: ");
        sb.append(writeSet.toString());
        sb.append(", a: ");
        sb.append(atomicIncSet.toString());
        sb.append(" }");
        return sb.toString();
    }
    @Override
    public int hashCode() {
    	return (isFun()? 0: 1) + readSet().hashCode() + writeSet().hashCode()
    	+ atomicIncSet().hashCode();
    }
    @Override
    public boolean equals(Object other ) {
    	if (this == other) return true;
    	if (! (other instanceof Effect_c)) return false;
    	@SuppressWarnings("unchecked")
		Effect_c<T> o = (Effect_c<T>) other;
    	return isFun() == o.isFun()
    	&& readSet().equals(o.readSet())
    	&& writeSet().equals(o.writeSet())
    	&& atomicIncSet().equals(o.atomicIncSet());
    	
    }
}
