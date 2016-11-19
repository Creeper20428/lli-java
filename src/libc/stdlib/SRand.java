package libc.stdlib;

import libc.CEnvironment;
import libc.CFunction;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class SRand extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		long seed = ((ValueInteger) args[0]).longValue();
		env.setSeed(seed);
		
		return null;
	}

}
