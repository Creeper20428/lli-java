package llvm.environment.internal.ast;

public final class ASTPointerType extends ASTType {
	
	private final ASTType inner;

	public ASTPointerType(ASTType inner) {
		this.inner = inner;
	}
	
	@Override
	public String toString() {
		return inner + "*";
	}
	
	public ASTType getInner() {
		return inner;
	}

}
