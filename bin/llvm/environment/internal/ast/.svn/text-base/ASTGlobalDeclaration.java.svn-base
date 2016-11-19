package llvm.environment.internal.ast;

import java.util.Formatter;

public final class ASTGlobalDeclaration {

	private final String name;
	private final String linkageType;
	private final boolean constant;
	private final ASTType type;
	private final ASTConstantExpression value;
	
	public ASTGlobalDeclaration(String name, String linkageType,
			boolean constant, ASTType type, ASTConstantExpression value) {
		this.name = name;
		this.linkageType = linkageType;
		this.constant = constant;
		this.type = type;
		this.value = value;
	}
	
	@Override
	public String toString() {
		final Formatter fmt = new Formatter();
		
		fmt.format("@%s = ", name);
		if (linkageType == null) {
			if (!constant) {
				fmt.format("global ");
			} else {
				fmt.format("constant ");
			}
		} else {
			fmt.format("%s ", linkageType);
			if (constant) {
				fmt.format("constant ");
			}
		}
		fmt.format("%s %s", type, value);
		return fmt.toString();
	}
	
	public String getName() {
		return name;
	}
	
	public ASTType getType() {
		return type;
	}
	
	public ASTConstantExpression getValue() {
		return value;
	}
	
}
