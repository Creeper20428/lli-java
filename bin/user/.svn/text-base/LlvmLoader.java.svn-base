package user;

import llvm.BadAlloc;
import llvm.LlvmException;
import llvm.MemoryAccessViolation;
import llvm.environment.AbstractLibrary;
import llvm.environment.internal.asmparser.AsmParser;
import llvm.environment.internal.ast.ASTModule;

public final class LlvmLoader {
	
	public LlvmModule loadModule(String filename, AbstractLibrary functionFactory) throws LlvmException {
		final ASTModule astModule = AsmParser.parse(filename);
		final LlvmBuilder translator = new LlvmBuilder(functionFactory);
		final LlvmModule llvmModule = translator.buildModule(astModule);
		
		return llvmModule; 
	}

}
