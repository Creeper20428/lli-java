package libc.string;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IMemory;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class MemChr extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IMemory memory = env.getMemory();
		IPointer ptr = (IPointer) args[0];
		byte val = (byte) ((ValueInteger)args[1]).intValue();
		long num = ((ValueInteger)args[2]).longValue();
		
		byte[] buf = ptr.load(0, (int) num);
			
		for(int i = 0; i < num; ++i) {
			if(buf[i] == val) {
				IPointer result = ptr.add(i);
				return result;
			}
		}
		
		return memory.nullPointer();
	}

}
