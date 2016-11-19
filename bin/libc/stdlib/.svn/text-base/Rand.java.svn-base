package libc.stdlib;

import libc.CEnvironment;
import libc.CFunction;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class Rand extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		int result = env.rand();
		return new ValueInteger(result);
	}

}
