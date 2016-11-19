package libc.time;

import libc.CEnvironment;
import libc.CFunction;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueDouble;
import llvm.values.ValueInteger;

public class DiffTime extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		long time2 = ((ValueInteger)args[0]).longValue();
		long time1 = ((ValueInteger)args[1]).longValue();
		
		double dif = (time2 - time1)/1000;
        return new ValueDouble(dif);
	}

}
