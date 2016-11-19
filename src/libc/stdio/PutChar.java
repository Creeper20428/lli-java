package libc.stdio;

import java.io.IOException;

import libc.CEnvironment;
import libc.CFile;
import libc.CFunction;
import libc.CMacro;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class PutChar extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		int ch = ((ValueInteger) args[0]).intValue();
        
        try {
			CFile file = env.getFile(env.stdout);
			file.putChar(ch);
			return new ValueInteger(ch);
		} catch (IOException e) {
			return new ValueInteger(CMacro.EOF);
		}
	}

}
