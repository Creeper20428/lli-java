package libc.setjmp;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class LongJmp extends CFunction {

	@Override
	public Value call(CEnvironment env, Value... args) throws LlvmException {
		IPointer jmpBuf = (IPointer) args[0];
		int val = ((ValueInteger)args[1]).intValue();
		env.longJump(jmpBuf, val);
		
		return null;
	}

}
