package libc.time;

import libc.CEnvironment;
import libc.CFunction;
import libc.StructTM;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class MkTime extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer timePtr = (IPointer) args[0];

		byte[] data = timePtr.load(0, StructTM.size);
		StructTM tm = new StructTM(data);
		long result = tm.toTime_t();
		data = tm.getData();
		timePtr.store(0, data);
		
        return new ValueInteger(result);
	}

}
