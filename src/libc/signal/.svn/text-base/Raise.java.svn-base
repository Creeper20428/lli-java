package libc.signal;

import libc.CEnvironment;
import libc.CFunction;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class Raise extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		int signal = ((ValueInteger)args[0]).intValue();
    	int result = env.raise(signal);
    	
        return new ValueInteger(result);
	}

}
