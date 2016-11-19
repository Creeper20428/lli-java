package llvm.environment.internal.ast;

public final class ASTRetInstruction extends ASTInstruction {

	private final ASTType type;
	private final ASTArgument argument;
	
	public ASTRetInstruction(ASTType type, ASTArgument argument) {
		super("ret", null);
		this.type = type;
		this.argument = argument;
	}

	@Override
	public String toString() {
		return "ret " + type + " " + argument;
	}
	
	public ASTType getType() {
		return type;
	}
	
	public ASTArgument getArgument() {
		return argument;
	}

	public final boolean isTerminator() {
		return true;
	}

}
