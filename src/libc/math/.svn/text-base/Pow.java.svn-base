package libc.math;

import libc.CEnvironment;
import libc.CFunction;
import libc.CMacro;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueDouble;

public class Pow extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		double x = ((ValueDouble)args[0]).toDouble();
		double y = ((ValueDouble)args[1]).toDouble();
		
		double result = Math.pow(x, y);
		
		if(Double.isInfinite(result)) {
			env.setErrno(CMacro.ERANGE);
		}
		
		if((x == 0 && y < 0) || (x < 0 && y == Math.ceil(y))) {
			env.setErrno(CMacro.EDOM);
		}
		
		return new ValueDouble(result);
	}

}
