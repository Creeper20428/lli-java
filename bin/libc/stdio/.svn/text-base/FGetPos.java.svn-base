package libc.stdio;

import java.io.IOException;

import utils.ConversionUtility;

import libc.CEnvironment;
import libc.CFile;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class FGetPos extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
    	IPointer stream = (IPointer) args[0];
    	IPointer posPtr = (IPointer) args[1];
    	
    	final int success = 0;
    	final int fail = -1;
    	
    	try {
			CFile file = env.getFile(stream);
			long pos = file.getPosition();
			posPtr.store(0, ConversionUtility.longToBytes(pos));
			return new ValueInteger(success);
		} catch (IOException e) {
			return new ValueInteger(fail);
		}
	}

}
