package libc.stdlib;

import utils.ConversionUtility;
import libc.CEnvironment;
import libc.CFunction;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueByteArray;
import llvm.values.ValueInteger;

public class LDiv extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		long number = ((ValueInteger) args[0]).longValue();
		long denom = ((ValueInteger) args[1]).longValue();
		
		long qout = number/denom;
		long rem  = number%denom;
		
		byte[] qoutBytes = ConversionUtility.longToBytes(qout);
		byte[] remBytes  = ConversionUtility.longToBytes(rem);
		
		byte[] result = new byte[qoutBytes.length + remBytes.length];
		System.arraycopy(qoutBytes, 0, result, 0, qoutBytes.length);
		System.arraycopy(remBytes, 0, result, qoutBytes.length, remBytes.length);
		
		return new ValueByteArray(result);
	}

}
