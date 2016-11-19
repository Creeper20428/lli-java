package llvm.environment.internal.ast;

public final class ASTNamedParameter {

	private final ASTParameter parameter;
	private final String name;
	
	public ASTNamedParameter(ASTType type, String name) {
		this.parameter = new ASTParameter(type);
		this.name = name;
	}

	@Override
	public String toString() {
		return parameter + " %" + name;
	}
	
	public ASTParameter getParameter() {
		return parameter;
	}
	
	public String getName() {
		return name;
	}
	
}
