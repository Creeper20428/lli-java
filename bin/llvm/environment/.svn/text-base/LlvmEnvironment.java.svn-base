package llvm.environment;

import llvm.IMemory;

public abstract class LlvmEnvironment {
	
	private final IMemory memory;
	private final LlvmStack stack;

	protected LlvmEnvironment(IMemory memory) {
		this.memory = memory;
		this.stack = new LlvmStack();
	}
	
	public final IMemory getMemory() {
		return memory;
	}
	
	public final LlvmStack getStack() {
		return stack;
	}
	
}
