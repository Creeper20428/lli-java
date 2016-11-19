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
import static libc.CEnvironment.loadString;;

public class FPutS extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
    	IPointer ptrStr = (IPointer) args[0];
    	IPointer stream = (IPointer) args[1];
    	
    	try {
			CFile file = env.getFile(stream);
			String str = loadString(ptrStr);
			file.putString(str);
			return new ValueInteger(0);
		} catch (IOException e) {
			return new ValueInteger(CMacro.EOF);
		}
	}

}
