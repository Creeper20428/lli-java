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

public class FFlush extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer stream = (IPointer) args[0];
    	try {
			CFile file = env.getFile(stream);
			file.flush();
		} catch (IOException e) {
			return new ValueInteger(CMacro.EOF);
		}
    	return new ValueInteger(0);
	}

}
