package libc.stdio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IMemory;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;

public class TmpFile extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IMemory memory = env.getMemory();
		IPointer filePointer;
		try {
			File tmpFile = File.createTempFile("", "");
			String tmpName = tmpFile.getCanonicalPath();
			filePointer = env.fopen(tmpName, "wb+");
			return filePointer;
		} catch (FileNotFoundException e) {
			return memory.nullPointer();
		} catch (IOException e) {
			return memory.nullPointer();
		}
	}

}
