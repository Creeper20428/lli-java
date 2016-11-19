package llvm.instructions;

import llvm.LlvmException;
import llvm.environment.Instruction;
import llvm.environment.LlvmStack;
import llvm.values.ValueLabel;

public final class InstructionLabel extends Instruction {

	private final Instruction instruction;
	private final LlvmStack stack;
	private final ValueLabel label;
	
	public InstructionLabel(Instruction instruction, LlvmStack stack, ValueLabel label) {
		this.instruction = instruction;
		this.stack = stack;
		this.label = label;
	}
	
	@Override
	public String toString() {
		return "setlabel(" + label + ", " + instruction + ")";
	}

	@Override
	public void call() throws LlvmException {
		instruction.call();
		stack.peek().setLabel(label);
	}

}
