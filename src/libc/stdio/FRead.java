package libc.stdio;

import java.io.IOException;

import libc.CEnvironment;
import libc.CFile;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class FRead extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
    	IPointer ptr = (IPointer) args[0];
    	ValueInteger valSize = (ValueInteger) args[1];
    	ValueInteger valCount = (ValueInteger) args[2];
    	IPointer stream = (IPointer) args[3];

    	long size = valSize.longValue() + valCount.longValue();
    	
    	try {
			CFile file = env.getFile(stream);
			byte[] data = file.read((int) size);
			ptr.store(0, data);
			return valCount;
		} catch (IOException e) {
			return new ValueInteger((long)0);
		}
	}

}
