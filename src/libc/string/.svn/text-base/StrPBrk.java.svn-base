package libc.string;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IMemory;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import static libc.CEnvironment.loadString;

public class StrPBrk extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IMemory memory = env.getMemory();
		IPointer ptr1 = (IPointer) args[0];
		IPointer ptr2 = (IPointer) args[1];

		String str1 = loadString(ptr1);
		String str2 = loadString(ptr2);
			
		char[] ch = str1.toCharArray();
			
		int i = 0;
		while(str2.indexOf(ch[i]) < 0) {
			++i;
			if(i > ch.length) {
				return memory.nullPointer();
			}
		}
			
		return ptr1.add(i);
	}

}
