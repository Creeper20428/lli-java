package libc.stdlib;

import libc.CEnvironment;
import libc.CFunction;
import llvm.LlvmException;
import llvm.Value;

public class Abort extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		env.abort();
		return null;
	}

}
