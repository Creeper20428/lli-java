package libc.stdio;

import java.io.IOException;

import libc.CEnvironment;
import libc.CFile;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class FTell extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer ptr = (IPointer) args[0];
    	
    	final long fail = -1;
    	try {
    		CFile file = env.getFile(ptr);
			long result = file.getPosition();
			return new ValueInteger(result);
		} catch (IOException e) {
			return new ValueInteger(fail);
		}
		
		
	}

}
