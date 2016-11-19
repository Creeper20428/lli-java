package libc.math;

import libc.CEnvironment;
import libc.CFunction;
import libc.CMacro;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueDouble;

public class ACos extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		double x = ((ValueDouble)args[0]).toDouble();
		
		double result = Math.acos(x);
		if(Double.isNaN(result)) {
			env.setErrno(CMacro.EDOM);
		}
		
		return new ValueDouble(result);
	}

}
