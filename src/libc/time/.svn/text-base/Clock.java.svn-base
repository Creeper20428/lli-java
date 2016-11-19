package libc.time;

import libc.CEnvironment;
import libc.CFunction;
import libc.CMacro;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class Clock extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		long time = System.currentTimeMillis();
		
		long result = (time - env.startTime)*CMacro.CLOCKS_PER_SEC/1000;
		return new ValueInteger(result);
	}

}
