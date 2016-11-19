package llvm.environment;

import llvm.AbstractFunction;
import llvm.BadAlloc;
import llvm.Value;

public abstract class AbstractLibrary {
	
	/**
	 * The method to be implemented in derived classes to give the necessary set
	 * of functions for a particular environment.
	 * 
	 * @param functionName
	 * @return
	 * @throws BadAlloc 
	 */
	public abstract AbstractFunction getFunction(String name) throws BadAlloc;
	
	public abstract Value getGlobal(String name);
	
	public abstract LlvmEnvironment getEnvironment();
	
}
