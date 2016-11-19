package libc.stdio;

import java.io.IOException;

import libc.CEnvironment;
import libc.CFile;
import libc.CFunction;
import libc.CMacro;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class GetChar extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		int character = CMacro.EOF;
    	
    	try {
			CFile file = env.getFile(env.stdin);
			character = file.getChar();
			return new ValueInteger(character);
		} catch (IOException e) {
			return new ValueInteger(CMacro.EOF);
		}
	}

}
