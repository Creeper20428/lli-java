package libc.stdio;

import java.io.IOException;

import libc.CEnvironment;
import libc.CFile;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class FEof extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer stream = (IPointer) args[0];
    	
    	boolean eof = false;
		try {
			CFile file = env.getFile(stream);
			eof = file.eof();
		} catch (IOException e) {
		}
    	
    	int result;
    	if(eof) {
    		result = 1;
    	} else {
    		result = 0;
    	}
    	
    	return new ValueInteger(result);
	}

}
