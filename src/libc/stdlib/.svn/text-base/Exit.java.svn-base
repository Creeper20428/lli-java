package libc.stdlib;

import libc.CEnvironment;
import libc.CFunction;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class Exit extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		int status = ((ValueInteger) args[0]).intValue();
		env.exit(status);
		
		return null;
	}

}
