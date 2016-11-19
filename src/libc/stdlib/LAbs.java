package libc.stdlib;

import libc.CEnvironment;
import libc.CFunction;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class LAbs extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		long j = ((ValueInteger) args[0]).longValue();
		long result = Math.abs(j);
		
		return new ValueInteger(result);
	}

}
