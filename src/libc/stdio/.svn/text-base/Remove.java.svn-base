package libc.stdio;

import java.io.File;

import libc.CEnvironment;
import libc.CFunction;
import libc.CMacro;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;
import static libc.CEnvironment.loadString;

public class Remove extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {    	
    	IPointer ptr = (IPointer) args[0];
    	
    	final int fail = -1;
    	final int success = 0;
    	
    	String fileName = loadString(ptr);
		File f = new File(fileName);

	    if (!f.exists()) {
	    	env.setErrno(CMacro.ENOENT);
	    	return new ValueInteger(fail);
	    }

	    if (!f.canWrite() || f.isDirectory()) {
	    	env.setErrno(CMacro.EACCES);
	    	return new ValueInteger(fail);
	    }

	    if(!f.delete()) {
	    	// TODO: set error
	    	return new ValueInteger(fail);
	    }
    	
        return new ValueInteger(success);
	}

}
