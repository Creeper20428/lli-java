package llvm.environment;

import llvm.Inlet;
import llvm.Value;

public final class RegisterGlobal implements Inlet {
	
	private final Value value;
	
	public RegisterGlobal(Value value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "global(" + value + ")";
	}

	@Override
	public Value letIn() {
		return value;
	}

}
