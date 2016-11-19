package libc.string;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class MemCmp extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer ptr1 = (IPointer) args[0];
		IPointer ptr2 = (IPointer) args[1];
		long num = ((ValueInteger)args[2]).longValue();

		int result = 0;
		
		byte[] bytes1 = ptr1.load(0, (int) num);
		byte[] bytes2 = ptr2.load(0, (int) num);
			
		for(int i = 0; i < num; ++i) {
			if(bytes1[i] > bytes2[i]) {
				result = 1;
				break;
			} 
				
			if(bytes1[i] < bytes2[i]) {
				result = -1;
				break;
			}
		}
		
		return new ValueInteger(result);
	}

}
