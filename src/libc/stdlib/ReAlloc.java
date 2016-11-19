package libc.stdlib;

import libc.CEnvironment;
import libc.CFunction;
import llvm.BadAlloc;
import llvm.IMemory;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class ReAlloc extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IMemory memory = env.getMemory();
		
		long size = ((ValueInteger) args[1]).longValue();
		IPointer ptr = (IPointer) args[0];
		
		if(size == 0) {
			if(!ptr.isNull()) {
				ptr.free();
			}
			return memory.nullPointer();
		}
		
		if(ptr.isNull()) {
			try {
				return memory.allocate(size);
			} catch (BadAlloc e) {
			}
			
			return memory.nullPointer();
		}
		
		long oldSize = ptr.getSize();
		long sizeOfCopy = Math.min(oldSize, size);
		byte[] data = ptr.load(0, (int) sizeOfCopy);
		
		IPointer newPtr;
		try {
			newPtr = memory.allocate((int) size);
			newPtr.store(0, data);
			return newPtr;
		} catch (BadAlloc e) {
		}

		return memory.nullPointer();
	}

}
