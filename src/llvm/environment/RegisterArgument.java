package llvm.environment;

import llvm.Inlet;
import llvm.Value;

public final class RegisterArgument implements Inlet {
	
	private final LlvmStack stack;
	private final int index;
	
	public RegisterArgument(LlvmStack stack, int index) {
		this.stack = stack;
		this.index = index;
	}

	@Override
	public Value letIn() {
		return stack.peek().getArgument(index);
	}

}
