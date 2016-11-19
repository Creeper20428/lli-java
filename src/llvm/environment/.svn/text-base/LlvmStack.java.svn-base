package llvm.environment;

import java.util.Stack;

/**
 * Instances of this class can't be created by a user.
 * 
 * @author leso
 * 
 */
public final class LlvmStack {
	
	private final Stack<LlvmStackFrame> stackFrames;

	LlvmStack() {
		stackFrames = new Stack<LlvmStackFrame>();
	}
	
	@Override
	public String toString() {
		return "stack(" + stackFrames + ")";
	}

	public LlvmStackFrame peek() {
		return stackFrames.peek();
	}

	public LlvmStackFrame pop() {
		return stackFrames.pop();
	}
	
	void push(LlvmStackFrame stackFrame) {
		stackFrames.push(stackFrame);
	}
	
	int size() {
		return stackFrames.size();
	}
	
	/**
	 * Can be used in user-defined environment.
	 * 
	 * @return Returns an instruction pointer that can be used only in
	 *         {@link LlvmStack#goTo(LlvmInstructionPointer)}.
	 */
	public LlvmInstructionPointer getInstructionPointer() {
		final int instructionIndex = peek().getInstructionIndex();
		final int frameIndex = stackFrames.size(); // TODO Fix this line
		return new LlvmInstructionPointer(frameIndex, instructionIndex);
	}

	/**
	 * Can be used in user-defined environment. Uses instruction pointer created
	 * by {@link LlvmStack#getInstructionPointer()} to jump to given
	 * instruction.
	 * 
	 * @param instructionPointer
	 */
	public void goTo(LlvmInstructionPointer instructionPointer) {
		// TODO Implement
	}

}
