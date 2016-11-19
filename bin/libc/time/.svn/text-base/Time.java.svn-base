package libc.time;

import utils.ConversionUtility;
import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class Time extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer timerPtr = (IPointer) args[0];
		
		long time = System.currentTimeMillis();
		
		if(!timerPtr.isNull()) {
			timerPtr.store(0, ConversionUtility.longToBytes(time));
		}
		
        return new ValueInteger(time);
	}

}
