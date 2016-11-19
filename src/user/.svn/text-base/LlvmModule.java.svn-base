package user;

import java.util.HashMap;

import llvm.AbstractFunction;
import llvm.IMemory;
import llvm.Inlet;

public final class LlvmModule {
	
	private final IMemory memory;
	private final HashMap<String, AbstractFunction> functions;

	public LlvmModule(IMemory memory, HashMap<String,AbstractFunction> functions) {
		this.memory = memory;
		this.functions = functions;
	}
	
	public AbstractFunction getFunction(String name) {
		return functions.get(name);
	}
	
	public Inlet getGlobal(String name) {
		return null;
	}

}
