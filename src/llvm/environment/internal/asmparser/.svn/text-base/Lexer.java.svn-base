package llvm.environment.internal.asmparser;

import java.util.Iterator;
import java.util.NoSuchElementException;

import llvm.environment.internal.asmparser.TokenInfo.TokenType;

final class Lexer implements Iterator<TokenInfo>, Iterable<TokenInfo> {

	private final char[] code;
	private StringBuilder token;
	private int index;
	private int line;
	private int column;
	private char ch;
	private int tokenLine;
	private int tokenColumn;
	private TokenType tokenType;
	private boolean ended;

	Lexer(char[] code) {
		this.code = code;
		this.token = null;
		this.index = 0;
		this.column = 0;
		this.line = 0;
		this.ch = code[0];
		this.tokenColumn = 0;
		this.tokenLine = 0;
		this.ended = false;
	}

	@Override
	public boolean hasNext() {
		return !ended;
	}

	@Override
	public TokenInfo next() {
		if (ended) {
			throw new NoSuchElementException();
		}
		if (!readToken()) {
			ended = true;
		}
		return new TokenInfo(token.toString(), tokenType, tokenLine,
				tokenColumn);
	}

	@Override
	public void remove() {
	}

	@Override
	public Iterator<TokenInfo> iterator() {
		return this;
	}
	
	private boolean readToken() {
		token = new StringBuilder(256);

		if (skipWhitespaceAndComments()) {
			return false;
		}
		tokenLine = line + 1;
		tokenColumn = column + 1;
		
		return minusOrNegativeNumber()
				|| number()
				|| identifier()
				|| global()
				|| local()
				|| string()
				|| delimiter()
				|| undefined();
	}
	
	private boolean skipWhitespaceAndComments() {
		while (true) {
			while (!eos() && Character.isWhitespace(ch)) {
				more();
			}
			if (eos()) {
				tokenType = TokenType.EOS;
				return true;
			}
			if (ch != ';') {
				break;
			}
			skipToNewLine();
		}
		return false;
	}
	
	private boolean minusOrNegativeNumber() {
		if (ch != '-') {
			return false;
		}
		token.append('-');
		more();
		if (!eos() && number()) {
			return true;
		}
		tokenType = TokenType.Delimiter;
		return true;
	}
	
	private boolean number() {
		return integer() && (eos() || fractional());
	}
	
	private boolean integer() {
		if (!Character.isDigit(ch)) {
			return false;
		}
		while (!eos() && Character.isDigit(ch)) {
			token.append(ch);
			more();
		}
		tokenType = TokenType.Integer;
		return true;
	}
	
	private boolean fractional() {
		if (ch != '.') {
			return true;
		}
		if (!(index + 1 < code.length && Character.isDigit(code[index + 1]))) {
			return true;
		}
		token.append('.');
		more();
		integer();
		tokenType = TokenType.Float;
		return true;
	}
	
	private boolean undefined() {
		token.append(ch);
		more();
		tokenType = TokenType.Undefined;
		return true;
	}

	private boolean string() {
		if (ch != '"') {
			return false;
		}
		more();
		while (!eos() && ch != '"') {
			token.append(ch);
			more();
		}
		if (eos()) {
			tokenType = TokenType.Undefined;
			return true;
		}
		more();
		tokenType = TokenType.String;
		return true;
	}

	private boolean delimiter() {
		if ("[]{}()*=,:".indexOf(ch) == -1) {
			return false;
		}
		token.append(ch);
		more();
		tokenType = TokenType.Delimiter;
		return true;
	}

	private boolean local() {
		if (ch != '%') {
			return false;
		}
		more();
		return unnamedLocal() || namedLocal() || epsilon0();
	}
	
	private boolean unnamedLocal() {
		if (!integer()) {
			return false;
		}
		tokenType = TokenType.Local;
		return true;
	}
	
	private boolean namedLocal() {
		if (!identifier()) {
			return false;
		}
		tokenType = TokenType.Local;
		return true;
	}
	
	private boolean epsilon0() {
		token.append('%');
		tokenType = TokenType.Undefined;
		return true;
	}

	private boolean global() {
		if (ch != '@') {
			return false;
		}
		more();
		if (identifier()) {
			tokenType = TokenType.Global;
			return true;
		}
		token.append('@');
		tokenType = TokenType.Undefined;
		return true;
	}

	private boolean identifier() {
		return identifierHead() && identifierTail();
	}

	private boolean identifierHead() {
		if (Character.isLetter(ch) || "$._".indexOf(ch) != -1) {
			token.append(ch);
			more();
			tokenType = TokenType.Identifier;
			return true;
		}
		return false;
	}
	
	private boolean identifierTail() {
		while (!eos() && (Character.isLetterOrDigit(ch) || "$._".indexOf(ch) != -1)) {
			token.append(ch);
			more();
		}
		return true;
	}
	
	private boolean eos() {
		return index >= code.length;
	}

	private void more() {
		final char oldch = ch;
		++index;
		if (eos()) {
			return;
		}
		ch = code[index];
		if (oldch == '\n') {
			++line;
			column = 0;
		} else {
			++column;
		}
	}

	private void skipToNewLine() {
		while (!eos() && ch != '\n') {
			more();
		}
		if (!eos()) {
			more();
		}
	}

}
