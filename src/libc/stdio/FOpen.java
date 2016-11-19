package libc.stdio;

import java.io.FileNotFoundException;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IMemory;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import static libc.CEnvironment.loadString;

public class FOpen extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IMemory memory = env.getMemory();
    	IPointer filePtr = (IPointer) args[0];
    	IPointer modePtr = (IPointer) args[1];
    	
    	try {
			String fileName = loadString(filePtr);
			String mode = loadString(modePtr);
			return env.fopen(fileName, mode);
		} catch (FileNotFoundException e) {
			return memory.nullPointer();
		}
	}

}
