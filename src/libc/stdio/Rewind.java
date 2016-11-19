package libc.stdio;

import java.io.IOException;

import libc.CEnvironment;
import libc.CFile;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;

public class Rewind extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer stream = (IPointer) args[0];
    	
    	try {
    		CFile file = env.getFile(stream);
			file.setPosition(0);
		} catch (IOException e) {
		}
		
		return null;
	}

}
