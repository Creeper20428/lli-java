package llvm.environment;

/**
 * Instances of this class represent pointers to particular instructions. They
 * can't be created by a user. Also, users can't invoke methods of this class.
 * 
 * @author leso
 * 
 */
public final class LlvmInstructionPointer {

	private final int frameIndex;
	private final int instructionIndex;

	LlvmInstructionPointer(int frameIndex, int instructionIndex) {
		this.frameIndex = frameIndex;
		this.instructionIndex = instructionIndex;
	}

	int getFrameIndex() {
		return frameIndex;
	}

	int getInstructionIndex() {
		return instructionIndex;
	}

}
