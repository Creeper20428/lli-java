package libc.string;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IMemory;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import static libc.CEnvironment.loadString;

public class StrTok extends CFunction {

	private static IPointer strtokStartPtr = null;
	
	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IMemory memory = env.getMemory();
		IPointer ptr1 = (IPointer) args[0];
		IPointer ptr2 = (IPointer) args[1];
		
		if(ptr1.isNull()) {
			ptr1 = strtokStartPtr;
		}
		
		String str1 = loadString(ptr1);
		String str2 = loadString(ptr2);
		
		char[] ch = str1.toCharArray();
		
		int start = 0;
		int end = 0;
		
		// skip chars from str2
		while(str2.indexOf(ch[start]) >= 0) {
			++start;
			
			if(start > str1.length()) {
				return memory.nullPointer();
			}
		}
		
		// find next char from str2; insert terminate
		end = start;
		while(str2.indexOf(ch[start]) == -1) {
			++end;
			
			if(end > str1.length()) {
				return memory.nullPointer();
			}
		}
		
		strtokStartPtr = ptr1.add(end);
		strtokStartPtr.store(0, new byte[1]);
		
		strtokStartPtr = strtokStartPtr.add(1);
		return ptr1.add(start);	
	}

}
