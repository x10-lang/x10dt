package x10.effects.tests;

import x10.constraint.XTypeSystem;
/**
 * Simple TypeSystem to test the effects package without having a 
 * package dependency on the compiler package. 
 * @author lshadare
 *
 */
public class TestTypeSystem implements XTypeSystem<TestType> {
	@Override
	public TestType Boolean() {
		return new TestType("booleanType");
	}

	@Override
	public TestType Null() {
		return new TestType("nullType");
	}

}

