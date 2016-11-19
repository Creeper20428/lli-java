package libc.stdlib;

import libc.CEnvironment;
import libc.CFunction;
import llvm.BadAlloc;
import llvm.IMemory;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class MAlloc extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IMemory memory = env.getMemory();
		long size = ((ValueInteger) args[0]).longValue();

		try {
			return memory.allocate(size);
		} catch (BadAlloc e) {
		}
		
		return memory.nullPointer();
	}
	

}
