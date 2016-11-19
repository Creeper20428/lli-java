package libc.stdio;

import java.io.IOException;

import libc.CEnvironment;
import libc.CFile;
import libc.CFunction;
import libc.CMacro;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class FPutC extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		int ch = ((ValueInteger) args[0]).intValue();
    	IPointer stream = (IPointer) args[1];
        
        try {
			CFile file = env.getFile(stream);
			file.putChar(ch);
			return new ValueInteger(ch);
		} catch (IOException e) {
			return new ValueInteger(CMacro.EOF);
		}
	}

}
