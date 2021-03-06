package libc.string;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;
import static libc.CEnvironment.loadString;

public class StrSpn extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer ptr1 = (IPointer) args[0];
		IPointer ptr2 = (IPointer) args[1];
		
		int result = 0;
		String str1 = loadString(ptr1);
		String str2 = loadString(ptr2);
			
		char[] ch = str1.toCharArray();
		
		while(result < str1.length()) {
			if(str2.indexOf(ch[result]) == -1) {
				break;
			}
			
			++result;
		}	
		
		return new ValueInteger((long)result);
	}

}
