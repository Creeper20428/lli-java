package llvm.environment.internal.ast;

import java.util.Formatter;
import java.util.List;

public final class ASTBlock {
	
	private final String name;
	private final List<ASTInstruction> instructions;
	private final ASTBlock nextBlock;

	public ASTBlock(String name, List<ASTInstruction> instructions, ASTBlock nextBlock) {
		this.name = name;
		this.instructions = instructions;
		this.nextBlock = nextBlock;
	}
	
	@Override
	public String toString() {
		final Formatter fmt = new Formatter();
		
		fmt.format("%s:\n", name);
		for (ASTInstruction instr : instructions) {
			fmt.format("  %s\n", instr);
		}
		return fmt.toString();
	}
	
	public String getName() {
		return name;
	}
	
	public List<ASTInstruction> getInstructions() {
		return instructions;
	}
	
	public ASTBlock getNextBlock() {
		return nextBlock;
	}
	
}
