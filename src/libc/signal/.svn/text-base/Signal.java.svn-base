package libc.signal;

import utils.ConversionUtility;
import libc.CEnvironment;
import libc.CFunction;
import libc.CMacro;
import llvm.AbstractFunction;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class Signal extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		int signal = ((ValueInteger)args[0]).intValue();
		AbstractFunction customFunction = (AbstractFunction) args[1];
		AbstractFunction function;
		
		int num = (int) ConversionUtility.bytesToLong(customFunction.toBytes());
		
		switch(num) {
		case CMacro.SIG_DFL:
			function = env.defaultHandler;
			break;
		case CMacro.SIG_IGN:
			function = env.ignoreHandler;
			break;
		default:
			function = customFunction;
		}
		
		AbstractFunction result = env.signal(signal, function);
		
        return result;
	}

}
