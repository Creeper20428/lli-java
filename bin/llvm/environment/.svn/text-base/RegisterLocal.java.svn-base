package llvm.environment;

import llvm.Inlet;
import llvm.Outlet;
import llvm.Value;

public final class RegisterLocal implements Inlet, Outlet {
	
	private final LlvmStack stack;
	private final int index;

	public RegisterLocal(LlvmStack stack, int index) {
		this.stack = stack;
		this.index = index;
	}
	
	@Override
	public String toString() {
		return "local(" + index + ")";
	}

	@Override
	public Value letIn() {
		return stack.peek().getLocal(index);
	}

	@Override
	public void letOut(Value value) {
		stack.peek().setLocal(index, value);
	}

}
