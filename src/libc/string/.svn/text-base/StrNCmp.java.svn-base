package libc.string;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;
import static libc.CEnvironment.loadString;

public class StrNCmp extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer ptr1 = (IPointer) args[0];
		IPointer ptr2 = (IPointer) args[1];
		int num = (int) ((ValueInteger)args[2]).longValue();
		
		int result = 0;
		
		String str1 = loadString(ptr1);
		String str2 = loadString(ptr2);
			
		if(str1.length() > num) {
			str1 = str1.substring(0, num);
		}
			
		if(str2.length() > num) {
			str2 = str2.substring(0, num);
		}
			
		result = str1.compareTo(str2);
		
		return new ValueInteger(result);
	}

}
