package libc.string;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class MemSet extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer ptr = (IPointer) args[0];
		byte val = (byte) ((ValueInteger)args[1]).intValue();
		int num = (int) ((ValueInteger)args[2]).longValue();
		
		byte[] buf = new byte[num];
		
		for(int i = 0; i < num; ++i) {
			buf[i] = val;
		}
		ptr.store(0, buf);	
		
		return ptr;
	}

}
