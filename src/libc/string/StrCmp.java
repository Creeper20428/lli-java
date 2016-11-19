package libc.string;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;
import static libc.CEnvironment.loadString;

public class StrCmp extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer ptr1 = (IPointer) args[0];
		IPointer ptr2 = (IPointer) args[1];
		
		int result = 0;
		
		String str1 = loadString(ptr1);
		String str2 = loadString(ptr2);
			
		result = str1.compareTo(str2);
		
		return new ValueInteger(result);
	}

}
