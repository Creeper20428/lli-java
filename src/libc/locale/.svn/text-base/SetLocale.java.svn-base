package libc.locale;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IMemory;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class SetLocale extends CFunction {

	@Override
	public Value call(CEnvironment env, Value... args) throws LlvmException {
		IMemory memory = env.getMemory();
		int category = ((ValueInteger) args[0]).intValue();
		IPointer locale = (IPointer) args[1];
		
		IPointer result = env.localeconv.setLocale(category, locale);
		if(result == null) {
			result = memory.nullPointer();
		}
		
		return result;
	}

}
