package libc.stdlib;

import libc.CEnvironment;
import libc.CFunction;
import llvm.BadAlloc;
import llvm.IMemory;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class CAlloc extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IMemory memory = env.getMemory();
		long nmemb = ((ValueInteger) args[0]).longValue();
		long size = ((ValueInteger) args[1]).longValue();
		
		long allSize = size*nmemb;
		try {
			return memory.allocate(allSize);
		} catch (BadAlloc e) {
		}
		
		return memory.nullPointer();
	}

}
