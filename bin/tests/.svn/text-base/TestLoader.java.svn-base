package tests;

import user.LlvmLoader;
import user.LlvmModule;
import user.SimpleOutlet;
import defaultmemory.DefaultMemory;
import libc.CEnvironment;
import libc.CLibrary;
import llvm.AbstractFunction;
import llvm.BadAlloc;
import llvm.IMemory;
import llvm.Inlet;
import llvm.LlvmException;
import llvm.Value;

public final class TestLoader {

	public static void main(String[] args) throws LlvmException {
		final String filename = "llvm_code_examples/hello2.ll";
		final IMemory memory = new DefaultMemory();
		
		try {
			CEnvironment environment = new CEnvironment(memory);
			final CLibrary functionFactory = environment.getFunctionFactory();
			final LlvmLoader loader = new LlvmLoader();
			final LlvmModule module = loader.loadModule(filename, functionFactory);
			
			final AbstractFunction mainFunction = module.getFunction("main");
			try {
				Value result = mainFunction.call();
				System.out.println(result);
			} catch (LlvmException e) {
				e.printStackTrace();
			}
		} catch (BadAlloc e) {
			e.printStackTrace();
		}
		
	}

}
