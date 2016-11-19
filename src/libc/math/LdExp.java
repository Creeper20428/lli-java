package libc.math;

import libc.CEnvironment;
import libc.CFunction;
import libc.CMacro;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueDouble;
import llvm.values.ValueInteger;

public class LdExp extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		double x = ((ValueDouble)args[0]).toDouble();
		int exp  = ((ValueInteger)args[1]).intValue();
		double result = x*Math.exp(exp);
		
		if(x == Double.POSITIVE_INFINITY) {
			env.setErrno(CMacro.ERANGE);
		}
		
		return new ValueDouble(result);
	}

}
