package llvm.environment.internal.ast;

public abstract class ASTInstruction {

	private final String name;
	private final ASTType resultType;
	
	public ASTInstruction(String name, ASTType resultType) {
		this.name = name;
		this.resultType = resultType;
	}

	@Override
	public abstract String toString();
	
	public final String getName() {
		return name;
	}
	
	public final ASTType getResultType() {
		return resultType;
	}
	
	public boolean isTerminator() {
		return false;
	}
	
}
