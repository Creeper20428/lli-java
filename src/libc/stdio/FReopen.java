package libc.stdio;

import java.io.FileNotFoundException;
import java.io.IOException;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import static libc.CEnvironment.loadString;

public class FReopen extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
    	IPointer fileNamePtr = (IPointer) args[0];
    	IPointer modePtr = (IPointer) args[1];
    	IPointer stream = (IPointer) args[2];
    	
    	try {
			String fileName = loadString(fileNamePtr);
			String mode = loadString(modePtr);
			env.freopen(fileName, mode, stream);
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
    	
        return stream;
	}

}
