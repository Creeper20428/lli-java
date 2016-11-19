package tests;

import llvm.environment.internal.asmparser.AsmParser;
import llvm.environment.internal.ast.ASTModule;

public final class TestAsmParser {

	public static void main(String[] args) {
		final ASTModule module = AsmParser.parse("llvm_code_examples/hello2.ll");
		
		System.out.println(module.toString());
	}

}
