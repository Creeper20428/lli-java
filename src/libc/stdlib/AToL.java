package libc.stdlib;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;
import static libc.CEnvironment.loadString;

public class AToL extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer ptr = (IPointer) args[0];
		
		try {
			String str = loadString(ptr);
			
			long i = Long.parseLong(str);
			return new ValueInteger(i);
		} catch (NumberFormatException e) {
			// TODO: log error
		}
		
		return new ValueInteger((long)0);
	}

}
