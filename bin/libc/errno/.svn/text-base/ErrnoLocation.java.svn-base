package libc.errno;

import libc.CEnvironment;
import libc.CFunction;
import llvm.LlvmException;
import llvm.Value;

public class ErrnoLocation extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		return env.errnoLocation;
	}

}
