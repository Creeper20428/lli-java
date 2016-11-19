package llvm.environment.internal.ast;

import java.util.Formatter;

public final class ASTCallInstruction extends ASTInstruction {

	private final boolean tail;
	private final String result;
	private final String functionName;
	private final ASTType[] argumentTypes;
	private final ASTArgument[] arguments;

	public ASTCallInstruction(boolean tail, String result, ASTType resultType,
			String functionName, ASTType[] argumentTypes,
			ASTArgument[] arguments) {
		super("call", resultType);
		this.tail = tail;
		this.result = result;
		this.functionName = functionName;
		this.argumentTypes = argumentTypes;
		this.arguments = arguments;
	}

	@Override
	public String toString() {
		final Formatter fmt = new Formatter();

		fmt.format("%%%s = %scall %s @%s(", result, tail ? "tail " : "",
				getResultType(), functionName);
		for (int i = 0; i < argumentTypes.length; ++i) {
			if (i > 0) {
				fmt.format(", ");
			}
			fmt.format("%s %s", argumentTypes[i], arguments[i]);
		}
		fmt.format(")");
		return fmt.toString();
	}

	public String getResult() {
		return result;
	}

	public String getFunctionName() {
		return functionName;
	}

	public ASTType[] getArgumentTypes() {
		return argumentTypes;
	}

	public ASTArgument[] getArguments() {
		return arguments;
	}

}
