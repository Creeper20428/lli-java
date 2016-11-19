package libc;

import llvm.AbstractFunction;
import llvm.BadAlloc;
import llvm.Inlet;
import llvm.LlvmException;
import llvm.Outlet;
import llvm.Value;

class CFunctionCaller extends AbstractFunction {
	
	private CEnvironment env;
	private CFunction func;

	CFunctionCaller(CEnvironment env, CFunction function) throws BadAlloc {
		super(env.getMemory());
		this.env = env;
		this.func = function;
	}
	
	@Override
	public String toString() {
		return "c_function(" + super.toString() + ")";
	}
	
	@Override
	public Value call(Value... args) throws LlvmException {
		return func.call(env, args);
	}

}
