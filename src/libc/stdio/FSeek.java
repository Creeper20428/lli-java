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

public class FSeek extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer stream = (IPointer) args[0];
		long offset = ((ValueInteger) args[1]).longValue();
		int origin = ((ValueInteger) args[2]).intValue();
		
		final int success = 0;
    	final int fail = -1;
    	int result = success;
    	try {
    		CFile file = env.getFile(stream);
	    	switch(origin) {
	    	case CMacro.SEEK_SET:
				file.setPosition(offset);
	    		break;
	    	case CMacro.SEEK_CUR:
	    		file.setPosition(file.getPosition() + offset);
	    		break;
	    	case CMacro.SEEK_END:
	    		file.setPosition(file.length() + offset);
	    		break;
	    	default:
	    		result = fail;
	    	}
    	} catch (IOException e) {
			result = fail;
		}
    	
    	return new ValueInteger(result);
	}

}
