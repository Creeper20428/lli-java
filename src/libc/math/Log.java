package libc.math;

import libc.CEnvironment;
import libc.CFunction;
import libc.CMacro;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueDouble;

public class Log extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		double x = ((ValueDouble)args[0]).toDouble();
		double result = Math.log(x);
		
		if(result == Double.NEGATIVE_INFINITY) {
			env.setErrno(CMacro.ERANGE);
		}
		
		return new ValueDouble(result);
	}

}
