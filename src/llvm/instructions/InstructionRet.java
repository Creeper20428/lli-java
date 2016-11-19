package llvm.instructions;

import llvm.Inlet;
import llvm.Value;
import llvm.environment.Instruction;
import llvm.environment.LlvmStackFrame;

public final class InstructionRet extends Instruction {
	
	private final Inlet in;
	
	public InstructionRet(Inlet in) {
		this.in = in;
	}
	
	@Override
	public String toString() {
		return "ret(" + in + ")";
	}

	@Override
	public void call() {
		final Value value = in.letIn();
		final LlvmStackFrame deadFrame = getEnvironment().getStack().pop(); 
		
		deadFrame.setResult(value);
	}

}
