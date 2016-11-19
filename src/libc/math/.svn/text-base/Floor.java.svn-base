package libc.math;

import libc.CEnvironment;
import libc.CFunction;
import llvm.Inlet;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueDouble;

public class Floor extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		double x = ((ValueDouble)args[0]).toDouble();
		double result = Math.floor(x);
		
		return new ValueDouble(result);
	}

}
