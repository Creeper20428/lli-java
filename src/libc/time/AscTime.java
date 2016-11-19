package libc.time;

import utils.ConversionUtility;
import libc.CEnvironment;
import libc.CFunction;
import libc.StructTM;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;

public class AscTime extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer timePtr = (IPointer) args[0];

		byte[] data = timePtr.load(0, StructTM.size);
		StructTM tm = new StructTM(data);
		String str = tm.toString();
		
	    env.timeStringPtr.store(0, ConversionUtility.stringToBytes(str));	
		
        return env.timeStringPtr;
	}

}
