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
import llvm.values.ValueInteger;

public class FGetS extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IMemory memory = env.getMemory();
		IPointer ptrStr = (IPointer) args[0];
    	int num = ((ValueInteger) args[1]).intValue();
    	IPointer stream = (IPointer) args[2];
    	
    	try {
			CFile file = env.getFile(stream);
			String str = file.fgets(num);
			ptrStr.store(0, ConversionUtility.stringToBytes(str));
		} catch (IOException e) {
			return memory.nullPointer();
		}
		
        return ptrStr;
	}

}
