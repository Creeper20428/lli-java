package libc.math;

import utils.ConversionUtility;
import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueDouble;

public class ModF extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer ptr = (IPointer) args[1];
		double value = ((ValueDouble)args[0]).toDouble();
		
		double floor = Math.floor(value);
		double result = value - floor;
		
		ptr.store(0, ConversionUtility.doubleToBytes(floor));
		
		return new ValueDouble(result);
	}

}
