package llvm.values;

import llvm.Value;

public final class ValueLabel implements Value {

	private final int iInstruction;
	
	public ValueLabel(int iInstruction) {
		this.iInstruction = iInstruction;
	}
	
	@Override
	public String toString() {
		return "label(" + iInstruction + ")";
	}
	
	int getInstructionIndex() {
		return iInstruction;
	}

	@Override
	public byte[] toBytes() {
		return null;
	}

}
