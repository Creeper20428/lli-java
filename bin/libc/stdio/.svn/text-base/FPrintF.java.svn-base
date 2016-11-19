package libc.stdio;

import java.io.IOException;

import libc.CEnvironment;
import libc.CFile;
import libc.CFormatter;
import libc.CFunction;
import libc.CMacro;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;
import static libc.CEnvironment.loadString;

public class FPrintF extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer stream = (IPointer) args[0];
		IPointer formatPtr = (IPointer) args[1];
		Value[] va_list = new Value[args.length - 2];
		
		for(int i = 2; i < args.length; ++i) {
			va_list[i] = args[i];
		}
		String format = loadString(formatPtr);
		String str = CFormatter.format(format, va_list);
		
		try {
			CFile file = env.getFile(stream);
			file.putString(str);
			return new ValueInteger(str.length());
		} catch (IOException e) {
			return new ValueInteger(CMacro.EOF);
		}
	}

}
