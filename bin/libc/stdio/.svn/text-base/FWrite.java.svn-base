package libc.stdio;

import java.io.IOException;

import libc.CEnvironment;
import libc.CFile;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class FWrite extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
    	IPointer ptr = (IPointer) args[0];
    	ValueInteger valSize = (ValueInteger) args[1];
    	ValueInteger valCount = (ValueInteger) args[2];
    	IPointer stream = (IPointer) args[3];
    	
    	long size = valSize.longValue() + valCount.longValue();
    	
    	try {
			byte[] data = ptr.load(0, (int) size);
			CFile file = env.getFile(stream);
			file.write(data);
			return valCount;
		} catch (IOException e) {
			return new ValueInteger((long)0);
		}
	}

}
