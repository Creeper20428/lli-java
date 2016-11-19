package libc.stdlib;

import libc.CEnvironment;
import libc.CFunction;
import llvm.AbstractFunction;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class AtExit extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		AbstractFunction function = (AbstractFunction) args[0];
		int result = env.atexit(function);
		
		return new ValueInteger(result);
	}

}
