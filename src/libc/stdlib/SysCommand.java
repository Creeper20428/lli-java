package libc.stdlib;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;
import static libc.CEnvironment.loadString;

public class SysCommand extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer ptrStr = (IPointer) args[0];
		if(ptrStr.isNull()) {
			return new ValueInteger(0);
		}
		
		String command = loadString(ptrStr);
		int result = env.system(command);
		
		return new ValueInteger(result);
	}

}
