package libc.math;

import libc.CEnvironment;
import libc.CFunction;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueDouble;

public class TanH extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		double x = ((ValueDouble)args[0]).toDouble();
		double result = Math.tanh(x);
		
		return new ValueDouble(result);
	}

}
