package llvm.environment;

import java.util.Arrays;

import llvm.LlvmException;
import llvm.Outlet;
import llvm.Value;
import llvm.values.ValueLabel;

public final class LlvmStackFrame {
	
	private final LlvmFunctionBody functionBody;
	private final Value[] locals;
	private final Value[] arguments;
	private final Outlet out;
	private int instructionIndex;
	private ValueLabel label;

	LlvmStackFrame(LlvmFunctionBody functionBody, Value[] arguments,
			Value[] vaList, Outlet out) {
		this.functionBody = functionBody;
		this.locals = new Value[functionBody.nLocals()];
		this.arguments = arguments;
		this.out = out;
		this.instructionIndex = 0;
		this.label = null;
	}
	
	@Override
	public String toString()
	{
		return "frame(" + Arrays.toString(locals) + ", " + Arrays.toString(arguments) + ", " + out + ")";
	}
	
	ValueLabel getLabel() {
		return label;
	}
	
	public void setLabel(ValueLabel label) {
		this.label = label;
	}

	Value getLocal(int index) {
		return locals[index];
	}
	
	void setLocal(int index, Value value) {
		locals[index] = value;
	}
	
	Value getArgument(int index) {
		return arguments[index];
	}
	
	void setArgument(int index, Value value) {
		arguments[index] = value;
	}
	
	public void setResult(Value value) {
		out.letOut(value);
	}
	
	void callNextInstruction() throws LlvmException {
		final Instruction instruction = functionBody.getInstruction(instructionIndex++);
		instruction.call();
	}
	
	/**
	 * This method must be used to implement
	 * {@link LlvmStack#getInstructionPointer()}
	 * 
	 * @return
	 */
	int getInstructionIndex() {
		return instructionIndex;
	}
	
}
