package libc;

import llvm.environment.LlvmInstructionPointer;

public class JmpBuf {
	/** if long jump just happened */
	private boolean flag;
	/** Value to which the setjmp expression evaluates. */
	private int val;
	
	public final LlvmInstructionPointer instruction;
	
	
	public JmpBuf(LlvmInstructionPointer instruction) {
		this.instruction = instruction;
		flag = false;
	}
	
	public void setFlag(boolean val) {
		flag = val;
	}
	
	public void setVal(int val) {
		this.val = val == 0 ? 1 : val;
	}
	
	public boolean getFlag() {
		return flag;
	}
	
	public int getVal() {
		return val;
	}
}
