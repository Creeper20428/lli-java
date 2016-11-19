package libc.string;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IMemory;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import static libc.CEnvironment.loadString;

public class StrStr extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IMemory memory = env.getMemory();
		IPointer ptr1 = (IPointer) args[0];
		IPointer ptr2 = (IPointer) args[1];
		
		String str1 = loadString(ptr1);
		String str2 = loadString(ptr2);
		
		int index = str1.indexOf(str2);
		if(index >= 0) {
			IPointer result = ptr1.add(index);
			return result;
		}	
		
		return memory.nullPointer();
	}

}
