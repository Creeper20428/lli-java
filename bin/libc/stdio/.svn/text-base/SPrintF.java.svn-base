package libc.stdio;

import utils.ConversionUtility;

import libc.CEnvironment;
import libc.CFormatter;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;
import static libc.CEnvironment.loadString;

public class SPrintF extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer ptr = (IPointer) args[0];
		IPointer formatPtr = (IPointer) args[1];
		Value[] va_list = new Value[args.length - 2];
		
		for(int i = 2; i < args.length; ++i) {
			va_list[i] = args[i];
		}
		String format = loadString(formatPtr);
		String str = CFormatter.format(format, va_list);
		
		ptr.store(0, ConversionUtility.stringToBytes(str));
		return new ValueInteger(str.length());
	}

}
