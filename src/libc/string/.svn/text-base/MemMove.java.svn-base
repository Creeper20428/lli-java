package libc.string;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class MemMove extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer dst = (IPointer) args[0];
		IPointer src = (IPointer) args[1];
		long num = ((ValueInteger)args[2]).longValue();
		
		byte[] buf = src.load(0, (int) num);
		dst.store(0, buf);
		
		return dst;
	}

}
