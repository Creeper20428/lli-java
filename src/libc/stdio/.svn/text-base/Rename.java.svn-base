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

public class Rename extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {    	
    	IPointer oldPtr = (IPointer) args[0];
    	IPointer newPtr = (IPointer) args[1];
    	
    	final int fail = -1;
    	final int success = 0;
    	
    	String oldName = loadString(oldPtr);
		String newName = loadString(newPtr);
		
		File oldFile = new File(oldName);

	    if (!oldFile.exists()) {
	    	env.setErrno(CMacro.ENOENT);
	    	return new ValueInteger(fail);
	    }
	    
	    File newFile = new File(newName);

	    if (newFile.exists()) {
	    	env.setErrno(CMacro.EACCES);
	    	return new ValueInteger(fail);
	    }

	    if(!oldFile.renameTo(newFile)) {
	    	// TODO: set error
	    	return new ValueInteger(fail);
	    }
    	
	    return new ValueInteger(success);
	}

}
