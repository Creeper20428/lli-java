package libc.stdio;

import java.io.IOException;

import utils.ConversionUtility;

import libc.CEnvironment;
import libc.CFile;
import libc.CFunction;
import libc.CMacro;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class FSetPos extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
    	IPointer stream = (IPointer) args[0];
    	IPointer posPtr = (IPointer) args[1];
    	
    	final int success = 0;
    	final int fail = -1;
    	
    	try {
			byte[] data = posPtr.load(0, 8);
			long pos = ConversionUtility.bytesToLong(data);
			CFile file = env.getFile(stream);
			file.setPosition(pos);
			return new ValueInteger(success);
		} catch (IOException e) {
			env.setErrno(CMacro.EIO);
			return new ValueInteger(fail);
		}
    	
    	
	}

}
