/**
 * 
 */
package x10.effects.tests;

import junit.framework.TestCase;
import x10.constraint.XConstraintManager;
import x10.constraint.XConstraintSystem;
import x10.constraint.XVar;
import x10.effects.constraints.ArrayElementLocs;
import x10.effects.constraints.ArrayLocs;
import x10.effects.constraints.Effect;
import x10.effects.constraints.Effect_c;
import x10.effects.constraints.Effects;
import x10.effects.constraints.FieldLocs;
import x10.effects.constraints.LocalLocs;

/**
 * @author vj
 */
public class BaseTests extends TestCase {
	
	XConstraintSystem<TestType> cs = XConstraintManager.<TestType>getConstraintSystem();
	TestType type = new TestType("intType");
    LocalLocs<TestType> l1 = Effects.makeLocalLocs(cs.makeVar(type, "l1"));
    LocalLocs<TestType> l2 = Effects.makeLocalLocs(cs.makeVar(type, "l2"));
    LocalLocs<TestType> l3 = Effects.makeLocalLocs(cs.makeVar(type, "l2"));
    LocalLocs<TestType> l4 = Effects.makeLocalLocs(cs.makeVar(type, "l2"));

	public BaseTests() {
		super("BaseTests");
	}
	
	/**
	 * Does {read(l1), write(l2), atomic(l3)} commute with {read(l4)}?
	 * @throws Throwable
	 */
	public void test1() throws Throwable {
		Effect<TestType> e1 = new Effect_c<TestType>(Effects.FUN);
		e1.addRead(l1);
		e1.addWrite(l2);
		e1.addAtomicInc(l3);
		
		Effect<TestType> e2 = new Effect_c<TestType>(Effects.FUN);
		e2.addRead(l4);
		
		boolean result = e1.commutesWith(e2);
		assertTrue(result);
		
	}
	
	/**
	 * Does {read(l1)} commute with {read(l1)}?
	 * @throws Throwable
	 */
	public void test2() throws Throwable {
		Effect<TestType> e1 = new Effect_c<TestType>(Effects.FUN);	
		e1.addRead(l1);

		Effect<TestType> e2 = new Effect_c<TestType>(Effects.FUN);
		e2.addRead(l1);
		
		boolean result = e1.commutesWith(e2);
		assertTrue(result);
		
	}
	
	/**
	 * Does {read(l1)} commute with {write(l1)}?
	 * @throws Throwable
	 */
	public void test3() throws Throwable {
		Effect<TestType> e1 = new Effect_c<TestType>(Effects.FUN);	
		e1.addRead(l1);

		Effect<TestType> e2 = new Effect_c<TestType>(Effects.FUN);
		e2.addWrite(l1);
		
		boolean result = e1.commutesWith(e2);
		assertFalse(result);
		
	}

	/**
	 * Does {read(l1)} commute with {atomic(l1)}?
	 * @throws Throwable
	 */
	public void test4() throws Throwable {
		Effect<TestType> e1 = new Effect_c<TestType>(Effects.FUN);	
		e1.addRead(l1);

		Effect<TestType> e2 = new Effect_c<TestType>(Effects.FUN);
		e2.addAtomicInc(l1);
		
		boolean result = e1.commutesWith(e2);
		assertFalse(result);
		
	}
	
	/**
	 * Does {write(l1)} commute with {read(l1)}?
	 * @throws Throwable
	 */
	public void test5() throws Throwable {
		Effect<TestType> e1 = new Effect_c<TestType>(Effects.FUN);	
		e1.addWrite(l1);

		Effect<TestType> e2 = new Effect_c<TestType>(Effects.FUN);
		e2.addRead(l1);
		
		boolean result = e1.commutesWith(e2);
		assertFalse(result);
		
	}
	
	/**
	 * Does {write(l1)} commute with {write(l1)}?
	 * @throws Throwable
	 */
	public void test6() throws Throwable {
		Effect<TestType> e1 = new Effect_c<TestType>(Effects.FUN);	
		e1.addWrite(l1);

		Effect<TestType> e2 = new Effect_c<TestType>(Effects.FUN);
		e2.addWrite(l1);
		
		boolean result = e1.commutesWith(e2);
		assertFalse(result);
		
	}
	
	/**
	 * Does {write(l1)} commute with {atomicInc(l1)}?
	 * @throws Throwable
	 */
	public void test7() throws Throwable {
		Effect<TestType> e1 = new Effect_c<TestType>(Effects.FUN);	
		e1.addWrite(l1);

		Effect<TestType> e2 = new Effect_c<TestType>(Effects.FUN);
		e2.addAtomicInc(l1);
		
		boolean result = e1.commutesWith(e2);
		assertFalse(result);
		
	}

	/**
	 * Does {atomicInc(l1)} commute with {read(l1)}?
	 * @throws Throwable
	 */
	public void test8() throws Throwable {
		Effect<TestType> e1 = new Effect_c<TestType>(Effects.FUN);	
		e1.addAtomicInc(l1);

		Effect<TestType> e2 = new Effect_c<TestType>(Effects.FUN);
		e2.addRead(l1);
		
		boolean result = e1.commutesWith(e2);
		assertFalse(result);
		
	}
	
	/**
	 * Does {atomicInc(l1)} commute with {write(l1)}?
	 * @throws Throwable
	 */
	public void test9() throws Throwable {
		Effect<TestType> e1 = new Effect_c<TestType>(Effects.FUN);	
		e1.addAtomicInc(l1);

		Effect<TestType> e2 = new Effect_c<TestType>(Effects.FUN);
		e2.addWrite(l1);
		
		boolean result = e1.commutesWith(e2);
		assertFalse(result);
		
	}
	
	/**
	 * Does {atomicInc(l1)} commute with {atomicInc(l1)}?
	 * @throws Throwable
	 */
	public void test10() throws Throwable {
		Effect<TestType> e1 = new Effect_c<TestType>(Effects.FUN);	
		e1.addAtomicInc(l1);

		Effect<TestType> e2 = new Effect_c<TestType>(Effects.FUN);
		e2.addAtomicInc(l1);
		
		boolean result = e1.commutesWith(e2);
		assertFalse(result);
		
	}
	
	/**
	 * Is {read(l1)}.exists(l1).equals({})
	 * @throws Throwable
	 */
	public void test11() throws Throwable {
		Effect<TestType> e1 = new Effect_c<TestType>(Effects.FUN);	
		e1.addRead(l1);

		Effect<TestType> e = e1.exists(l1);
		Effect<TestType> e2 = new Effect_c<TestType>(Effects.FUN);

		boolean result = e.equals(e2);
		assertTrue(result);
		
	}

	/**
	 * Is {read(l1,l2)}.exists(l1).equals({read(l2)})
	 * @throws Throwable
	 */
	public void test12() throws Throwable {
		Effect<TestType> e1 = new Effect_c<TestType>(Effects.FUN);	
		e1.addRead(l1);
		e1.addRead(l2);

		Effect<TestType> e = e1.exists(l1);
		Effect<TestType> e2 = new Effect_c<TestType>(Effects.FUN);
		e2.addRead(l2);
		boolean result = e.equals(e2);
		assertTrue(result);
		
	}

	/**
	 * Is {read(L.f)}.exists(L,M).equals({read(M.f)})
	 * @throws Throwable
	 */
	public void test13() throws Throwable {
		XVar<TestType> L = cs.makeVar(type, "L");
		FieldLocs<TestType> Lf = Effects.makeFieldLocs(L, "f");
		XVar<TestType> M = cs.makeVar(type, "M");
		FieldLocs<TestType> Mf = Effects.makeFieldLocs(M, "f");
		Effect<TestType> e1 = new Effect_c<TestType>(Effects.FUN);	
		e1.addRead(Lf);

		
		Effect<TestType> e = e1.exists(L, M);
		Effect<TestType> e2 = new Effect_c<TestType>(Effects.FUN);
		e2.addRead(Mf);

		boolean result = e.equals(e2);
		assertTrue(result);
		
	}
	
	/**
	 * Is {read(L(1))}.exists(L,M).equals({read(M(1))})?
	 * @throws Throwable
	 */
	public void test14() throws Throwable {
		XVar<TestType> L = cs.makeVar(type, "L");
		ArrayElementLocs<TestType> L1 = Effects.makeArrayElementLocs(L, cs.makeLit(1, type));
		XVar<TestType> M = cs.makeVar(type, "M");
		ArrayElementLocs<TestType> M1 = Effects.makeArrayElementLocs(M, cs.makeLit(1, type));
		Effect<TestType> e1 = new Effect_c<TestType>(Effects.FUN);	
		e1.addRead(L1);

		
		Effect<TestType> e = e1.exists(L, M);
		Effect<TestType> e2 = new Effect_c<TestType>(Effects.FUN);
		e2.addRead(M1);

		boolean result = e.equals(e2);
		assertTrue(result);
		
	}

	/**
	 * Is {read(L(1))}.exists(L,M).equals({read(M(1))})?
	 * @throws Throwable
	 */
	public void test15() throws Throwable {
		XVar<TestType> L = cs.makeVar(type, "L");
		XVar<TestType> One = cs.makeVar(type, "1");
		XVar<TestType> Two = cs.makeVar(type, "2");
		ArrayElementLocs<TestType> L1 = Effects.makeArrayElementLocs(L, One);
		ArrayElementLocs<TestType> L2 = Effects.makeArrayElementLocs(L, Two);
		Effect<TestType> e1 = new Effect_c<TestType>(Effects.FUN);	
		e1.addRead(L1);

		
		Effect<TestType> e = e1.exists(One, Two);
		Effect<TestType> e2 = new Effect_c<TestType>(Effects.FUN);
		e2.addRead(L2);

		boolean result = e.equals(e2);
		assertTrue(result);
		
	}

	public void test16() throws Throwable {
		XVar<TestType> L = cs.makeVar(type, "L");
		XVar<TestType> A = cs.makeVar(type, "A");
		ArrayLocs<TestType> L1 = Effects.makeArrayLocs(L);
		LocalLocs<TestType> Al = Effects.makeLocalLocs(A);
		ArrayElementLocs<TestType> LA = Effects.makeArrayElementLocs(L, A);
		Effect<TestType> e1 = new Effect_c<TestType>(Effects.FUN);	
		e1.addRead(L1);
		e1.addRead(Al);
        e1.addWrite(LA);
		
		Effect<TestType> e = e1.forall(A);
		
		Effect<TestType> e2 = new Effect_c<TestType>(Effects.FUN);
		e2.addRead(L1);
		e2.addRead(Al);
		e2.addWrite(L1);
		 
		boolean result = e.equals(e2);
		assertTrue(result);
		
	}

	public void test17() throws Throwable {
		XVar<TestType> L = cs.makeVar(type, "L");
		XVar<TestType> A = cs.makeVar(type, "A");
		//ArrayLocs<TestType> L1 = Effects.makeArrayLocs(L);
		LocalLocs<TestType> Al = Effects.makeLocalLocs(A);
		ArrayElementLocs<TestType> LA = Effects.makeArrayElementLocs(L, A);
		Effect<TestType> e1 = new Effect_c<TestType>(Effects.FUN);	
		//e1.addRead(L1);
		e1.addRead(Al);
        e1.addWrite(LA);
		boolean result = e1.commutesWithForall(A);
		
		assertTrue(result);
		
	}
}
