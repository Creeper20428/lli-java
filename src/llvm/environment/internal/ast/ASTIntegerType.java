package llvm.environment.internal.ast;

public final class ASTIntegerType extends ASTType {

	private final int size;
	
	public ASTIntegerType(int size) {
		this.size = size;
	}
	
	@Override
	public String toString() {
		return "i" + size;
	}
	
	public int getSize() {
		return size;
	}
	
}
