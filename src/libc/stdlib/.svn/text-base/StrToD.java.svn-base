package libc.stdlib;

import utils.ConversionUtility;
import libc.CEnvironment;
import libc.CFunction;
import libc.CMacro;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueDouble;
import static libc.CEnvironment.loadString;

public class StrToD extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer ptrStr = (IPointer) args[0];
		IPointer endPtr = (IPointer) args[1];

		try {
			String str = loadString(ptrStr);
			
			double f = Double.parseDouble(str);
			
			if(Double.isInfinite(f)) {
				env.setErrno(CMacro.ERANGE);
			}
			
			if(!endPtr.isNull()) {
				char[] ch = str.toCharArray();
				int end = 0;
				//skip whitespaces
				while(Character.isWhitespace(ch[end])) {
					++end;
				}
				//skip double
				while(!Character.isWhitespace(ch[end])) {
					++end;
					if(end > ch.length) {
						break;
					}
				}
				
				endPtr.store(0, ConversionUtility.longToBytes(ptrStr.add(end).toLong()));
			}
			
			return new ValueDouble(f);
		} catch (NumberFormatException e) {
			// TODO: log error
		}
		
		return new ValueDouble(0);
	}

}
