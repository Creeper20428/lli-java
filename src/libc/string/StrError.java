package libc.string;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class StrError extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		int error = ((ValueInteger)args[0]).intValue();
		
		IPointer message = env.errorMessage(error);
		return message;
	}

}
