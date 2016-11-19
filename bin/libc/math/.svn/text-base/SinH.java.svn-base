package libc.math;

import libc.CEnvironment;
import libc.CFunction;
import libc.CMacro;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueDouble;

public class SinH extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		double x = ((ValueDouble)args[0]).toDouble();
		double result = Math.sinh(x);
		
		if(Double.isInfinite(result)) {
			env.setErrno(CMacro.ERANGE);
			x = Double.POSITIVE_INFINITY;
		}
		
		return new ValueDouble(result);
	}

}
