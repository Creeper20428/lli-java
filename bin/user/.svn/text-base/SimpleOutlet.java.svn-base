package user;

import llvm.Outlet;
import llvm.Value;

public final class SimpleOutlet implements Outlet {

	private Value value = null;
	
	public Value getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return "out(" + value + ")";
	}
	
	@Override
	public void letOut(Value value) {
		this.value = value;
	}

}
