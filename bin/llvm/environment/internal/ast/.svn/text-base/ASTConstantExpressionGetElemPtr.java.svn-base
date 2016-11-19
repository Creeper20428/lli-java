package llvm.environment.internal.ast;

import java.util.Formatter;

public final class ASTConstantExpressionGetElemPtr extends ASTConstantExpression {

	private final ASTType[] types;
	private final ASTConstantExpression[] arguments;
	private final boolean isInbound;

	public ASTConstantExpressionGetElemPtr(ASTType[] types,
			ASTConstantExpression[] arguments, boolean isInbound) {
		this.types = types;
		this.arguments = arguments;
		this.isInbound = isInbound;
	}

	@Override
	public String toString() {
		final Formatter fmt = new Formatter();
		
		fmt.format("getelementptr ");
		if (isInbound) {
			fmt.format("inbound ");
		}
		fmt.format("(");
		for (int i = 0; i < types.length; ++i) {
			if (i > 0) {
				fmt.format(", ");
			}
			fmt.format("%s %s", types[i], arguments[i]);
		}
		fmt.format(")");
		return fmt.toString();
	}
	
	public ASTType getType() {
		return types[0];
	}

	public ASTConstantExpression[] getArguments() {
		return arguments;
	}

}
