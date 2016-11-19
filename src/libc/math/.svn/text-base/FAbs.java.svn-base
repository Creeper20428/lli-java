package libc.math;

import libc.CEnvironment;
import libc.CFunction;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueDouble;

public class FAbs extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		double x = ((ValueDouble)args[0]).toDouble();
		double result = Math.abs(x);
		
		return new ValueDouble(result);
	}

}
