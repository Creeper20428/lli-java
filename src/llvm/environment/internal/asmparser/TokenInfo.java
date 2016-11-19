package llvm.environment.internal.asmparser;

import java.util.Formatter;

final class TokenInfo {

	enum TokenType {
		Undefined, Identifier, Global, Local, Delimiter, String, Integer, Float, EOS
	}

	private final String value;
	private final TokenType type;
	private final int line;
	private final int column;

	TokenInfo(String value, TokenType type, int line, int column) {
		this.value = value;
		this.type = type;
		this.line = line;
		this.column = column;
	}

	String value() {
		return value;
	}

	TokenType type() {
		return type;
	}
	
	@Override
	public String toString() {
		final Formatter fmt = new Formatter();
		
		fmt.format("%s '%s' at %d : %d", type, value, line, column);
		return fmt.toString();
	}

}
