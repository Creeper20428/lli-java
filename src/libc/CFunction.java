package libc;

import llvm.LlvmException;
import llvm.Value;

public abstract class CFunction {

	public abstract Value call(CEnvironment env, Value ... args) throws LlvmException;
	
}
