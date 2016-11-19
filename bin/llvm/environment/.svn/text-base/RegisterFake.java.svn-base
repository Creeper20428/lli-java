package llvm.environment;

import llvm.Inlet;
import llvm.Outlet;
import llvm.Value;

public class RegisterFake implements Inlet, Outlet {
	Value value;
	
	public RegisterFake() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void letOut(Value out) {
		value = out;
	}

	@Override
	public Value letIn() {
		return value;
	}

}
