package x10.effects.tests;
import x10.constraint.XType;
import x10.constraint.XTypeSystem;
/**
 * Simple TestType class to test the effects without having a package 
 * dependency on the compiler package. 
 * @author lshadare
 *
 */
public class TestType implements XType {
	final String name;
	public static XTypeSystem<TestType> ts = null;

	TestType(String name) {
		this.name = name; 
		if (ts == null )
			ts = new TestTypeSystem();
	}

	@Override
	public boolean isBoolean() {
		return false;
	}
	@Override
	public XTypeSystem<TestType> xTypeSystem() {
		return ts; 
	}

	@Override
	public boolean isChar() {
		return false;
	}

	@Override
	public boolean isByte() {
		return false;
	}

	@Override
	public boolean isShort() {
		return false;
	}

	@Override
	public boolean isInt() {
		return false;
	}

	@Override
	public boolean isLong() {
		return false;
	}

	@Override
	public boolean isFloat() {
		return false;
	}

	@Override
	public boolean isDouble() {
		return false;
	}

	@Override
	public boolean isUByte() {
		return false;
	}

	@Override
	public boolean isUShort() {
		return false;
	}

	@Override
	public boolean isUInt() {
		return false;
	}

	@Override
	public boolean isULong() {
		return false;
	}

	@Override
	public boolean isPrimitive() {
		return false;
	}


}
