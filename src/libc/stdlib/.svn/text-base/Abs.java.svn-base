package libc.stdlib;

import libc.CEnvironment;
import libc.CFunction;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class Abs extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		int j = ((ValueInteger) args[0]).intValue();
		int result = Math.abs(j);
		
		return new ValueInteger(result);
	}

}
