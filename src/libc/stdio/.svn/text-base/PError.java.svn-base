package libc.stdio;

import java.io.IOException;

import libc.CEnvironment;
import libc.CFile;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import static libc.CEnvironment.loadString;

public class PError extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer ptr = (IPointer) args[0];
    	
    	try {
    		String customMessage;
    		if(ptr.isNull()) {
    			customMessage = "";
    		} else {
    			customMessage = loadString(ptr) + ": ";
    		}
    		
			String errorMessage = loadString(env.errorMessage(env.getErrno()));
			CFile err = env.getFile(env.stderr);
			err.putString(customMessage + errorMessage + "\n"); 
		} catch (IOException e) {
		}
    	
        return null;
	}

}
