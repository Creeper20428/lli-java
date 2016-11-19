package libc.stdlib;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;

public class Free extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer ptr = (IPointer) args[0];
		
		if(!ptr.isNull()) {
			ptr.free();
		}
		
		return null;
	}

}
