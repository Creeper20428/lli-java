package llvm.environment.internal.ast;

import java.util.Formatter;
import java.util.Map;

public final class ASTDefinition {

	private final String name;
	private final ASTType resType;
	private final ASTNamedParameter[] params;
	private final Map<String, ASTBlock> blocks;
	private final ASTBlock entry;
	private final Map<String, ASTType> locals;

	public ASTDefinition(String name, ASTType resType,
			ASTNamedParameter[] params, Map<String, ASTBlock> blocks,
			ASTBlock first, Map<String, ASTType> locals) {
		this.name = name;
		this.resType = resType;
		this.params = params;
		this.blocks = blocks;
		this.entry = first;
		this.locals = locals;
	}

	@Override
	public String toString() {
		final Formatter fmt = new Formatter();

		fmt.format("define %s @%s(", resType, name);
		for (int i = 0; i < params.length; ++i) {
			if (i > 0) {
				fmt.format(", ");
			}
			fmt.format("%s", params[i]);
		}
		fmt.format(") ");

		/* TODO Add attributes. */
		fmt.format("{\n");
		for (ASTBlock block = entry; block != null; block = block
				.getNextBlock()) {
			fmt.format("%s", block);
			if (block.getNextBlock() != null) {
				fmt.format("\n");
			}
		}
		fmt.format("}\n");
		return fmt.toString();
	}

	public String getName() {
		return name;
	}

	public ASTType getResultType() {
		return resType;
	}

	public ASTNamedParameter[] getParameters() {
		return params;
	}

	public Map<String, ASTBlock> getBlocks() {
		return blocks;
	}

	public ASTBlock getEntry() {
		return entry;
	}

	public Map<String, ASTType> getLocals() {
		return locals;
	}

}
