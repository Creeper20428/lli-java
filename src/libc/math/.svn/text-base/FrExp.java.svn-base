package libc.math;

import utils.ConversionUtility;
import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueDouble;

public class FrExp extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer ptr = (IPointer) args[1];
		double value = ((ValueDouble)args[0]).toDouble();
		
		int exp = Math.getExponent(value) + 1;
		double result = value/Math.pow(2,exp);
		ptr.store(0, ConversionUtility.intToBytes(exp));
		
		return new ValueDouble(result);
	}

}
