package libc.cassert;

import java.io.IOException;

import libc.CEnvironment;
import libc.CFile;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;
import static libc.CEnvironment.loadString;

public class AssertFail extends CFunction {

	private static final String appendix = "/n/nThis application has requested the Runtime to terminate it in an unusual way. Please contact the application's support team for more information.";

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
    	IPointer exprPtr = (IPointer) args[0];
    	IPointer pathPtr = (IPointer) args[1];
    	IPointer funcPtr = (IPointer) args[3];
    	int lineNum = ((ValueInteger)args[2]).intValue();
    	
    	try {
			String expression = loadString(exprPtr);
			String path = loadString(pathPtr);
			String funcName = loadString(funcPtr);
			
			String message = "Assertion failed: " + expression + 
							 ", file " + path + ", line " + 
							 Integer.toString(lineNum) + appendix;
			
			CFile err = env.getFile(env.stderr);
			err.putString(message); 
		} catch (IOException e) {
		}
    	
		env.abort();
        return null;
	}

}
