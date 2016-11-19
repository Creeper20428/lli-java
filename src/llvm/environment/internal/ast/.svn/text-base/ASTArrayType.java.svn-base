package llvm.environment.internal.ast;

public final class ASTArrayType extends ASTType {
	
	private final int length;
	private final ASTType inner;
	
	public ASTArrayType(int length, ASTType inner) {
		this.length = length;
		this.inner = inner;
	}
	
	@Override
	public String toString() {
		return "[" + length + " x " + inner + "]";
	}
	
	public int getLength() {
		return length;
	}
	
	public ASTType getInner() {
		return inner;
	}

}
