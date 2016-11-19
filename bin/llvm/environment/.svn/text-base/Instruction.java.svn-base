package llvm.environment;

import llvm.LlvmException;


public abstract class Instruction {

	private LlvmEnvironment environment;
	
	protected Instruction() {
		this.environment = null;
	}
	
	public void setEnvironment(LlvmEnvironment environment) {
		this.environment = environment;
	}
	
	protected LlvmEnvironment getEnvironment() {
		return environment;
	}
	
	public abstract void call() throws LlvmException;
	
}
