package llvm.environment.internal.ast;

import java.util.Formatter;

public final class ASTDeclaration {

	private final String name;
	private final ASTType resType;
	private final ASTParameter[] params;
	
	public ASTDeclaration(String name, ASTType resType, ASTParameter[] params) {
		this.name = name;
		this.resType = resType;
		this.params = params;
	}
	
	@Override
	public String toString() {
		final Formatter fmt = new Formatter();
		
		fmt.format("declare %s @%s(", resType, name);
		for (int i = 0; i < params.length; ++i) {
			if (i > 0) {
				fmt.format(", ");
			}
			fmt.format("%s", params[i]);
		}
		fmt.format(")");
		return fmt.toString();
	}
	
	public String getName() {
		return name;
	}
	
}
