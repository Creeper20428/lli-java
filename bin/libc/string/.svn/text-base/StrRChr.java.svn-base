package libc.string;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;
import static libc.CEnvironment.loadString;

public class StrRChr extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer ptr = (IPointer) args[0];
		byte val = (byte) ((ValueInteger)args[1]).intValue();

		String str = loadString(ptr);
		str.concat(new String(new byte[1]));
			
		int index = str.lastIndexOf(val);
		IPointer result = ptr.add(index);
			
		return result;
	}

}
