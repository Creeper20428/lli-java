package llvm.environment.internal.asmparser;

import utils.FileUtility;
import llvm.environment.internal.ast.ASTModule;

public final class AsmParser {

	public static ASTModule parse(String filename) {
		final char[] code = FileUtility.loadFile(filename);
		final Lexer lexer = new Lexer(code);
		final Parser parser = new Parser(lexer);
		
		return parser.parse();
	}
	
}
