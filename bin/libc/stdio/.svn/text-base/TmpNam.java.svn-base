package libc.stdio;

import java.io.File;
import java.io.IOException;

import utils.ConversionUtility;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IMemory;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;

public class TmpNam extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IMemory memory = env.getMemory();
    	IPointer ptr = (IPointer) args[0];
    	
    	String tmpName = null;
        try {
        	File tmpFile = File.createTempFile("", "");
			tmpName = tmpFile.getCanonicalPath();
		} catch (IOException e) {
			return memory.nullPointer();
		}

    	if(ptr.isNull()) {
    		ptr = env.internalTmpnam;
    	}
    	
		ptr.store(0, ConversionUtility.stringToBytes(tmpName));
    	
        return ptr;
	}

}
