package libc.stdlib;

import utils.ConversionUtility;
import libc.CEnvironment;
import libc.CFunction;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class Div extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		int number = ((ValueInteger) args[0]).intValue();
		int denom = ((ValueInteger) args[1]).intValue();
		
		int qout = number/denom;
		int rem  = number%denom;
		
		byte[] qb = ConversionUtility.intToBytes(qout);
		byte[] rb  = ConversionUtility.intToBytes(rem);
		
		// rem bytes + qout bytes 
		byte[] result = new byte[] { rb[0], rb[1], rb[2], rb[3],
									qb[0], qb[1], qb[2], qb[3]};
		
		return new ValueInteger(result);
	}

}
