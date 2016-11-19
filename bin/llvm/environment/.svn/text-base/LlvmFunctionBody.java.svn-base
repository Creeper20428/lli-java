package llvm.environment;

import java.util.Arrays;


public final class LlvmFunctionBody {
	
	private final int nLocals;
	private final Instruction[] instructions;
	
	public LlvmFunctionBody(int nLocals, Instruction[] instructions) {
		this.nLocals = nLocals;
		this.instructions = instructions;
	}
	
	@Override
	public String toString() {
		return "function_body(" + nLocals + ", " + Arrays.toString(instructions) + ")";
	}

	int nLocals() {
		return nLocals;
	}

	Instruction getInstruction(int index) {
		return instructions[index];
	}

}
