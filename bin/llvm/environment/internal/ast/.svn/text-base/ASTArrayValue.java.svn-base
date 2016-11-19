package llvm.environment.internal.ast;

import java.util.Formatter;

public final class ASTArrayValue extends ASTValue {

	private final ASTType type;
	private final ASTValue[] elements;
	
	public ASTArrayValue(ASTType type, ASTValue[] elements) {
		this.type = type;
		this.elements = elements;
	}
	
	@Override
	public String toString() {
		final Formatter fmt = new Formatter();
		final String typeAsString = type.toString();
		
		fmt.format("[ ");
		for (int i = 0; i < elements.length; ++i) {
			if (i > 0) {
				fmt.format(", ");
			}
			fmt.format("%s %s", typeAsString, elements[i]);
		}
		fmt.format(" ]");
		return fmt.toString();
	}

}
