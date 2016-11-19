package llvm.environment.internal.ast;

public final class ASTStringValue extends ASTValue {

	private final String string;
	
	public ASTStringValue(String string) {
		this.string = string;
	}
	
	@Override
	public String toString() {
		return "c\"" + string + "\"";
	}
	
	public String getString() {
		return string;
	}

}
