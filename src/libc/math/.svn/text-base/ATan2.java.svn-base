package libc.math;

import libc.CEnvironment;
import libc.CFunction;
import libc.CMacro;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueDouble;

public class ATan2 extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		double y = ((ValueDouble)args[0]).toDouble();
		double x = ((ValueDouble)args[1]).toDouble();
		
		double result = Math.atan2(y, x);
		if(x == 0 && y == 0) {
			env.setErrno(CMacro.EDOM);
		}
		
		return new ValueDouble(result);
	}

}
