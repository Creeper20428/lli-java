package libc.stdlib;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import static libc.CEnvironment.loadString;

public class GetEnv extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer ptrStr = (IPointer) args[0];
		
		String str = loadString(ptrStr);
		return env.getEnv(str);
	}

}
