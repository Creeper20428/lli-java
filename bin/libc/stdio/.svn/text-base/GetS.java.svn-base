package libc.stdio;

import java.io.IOException;

import utils.ConversionUtility;

import libc.CEnvironment;
import libc.CFile;
import libc.CFunction;
import llvm.IMemory;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;

public class GetS extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IMemory memory = env.getMemory();
    	IPointer ptrStr = (IPointer) args[0];
    	
    	try {
			CFile file = env.getFile(env.stdin);
			String str = file.gets();
			ptrStr.store(0, ConversionUtility.stringToBytes(str));
		} catch (IOException e) {
			return memory.nullPointer();
		} 
		
        return ptrStr;
	}

}
