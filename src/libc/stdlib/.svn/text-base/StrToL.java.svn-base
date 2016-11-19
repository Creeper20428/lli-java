package libc.stdlib;

import utils.ConversionUtility;
import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;
import static libc.CEnvironment.loadString;

public class StrToL extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer ptrStr = (IPointer) args[0];
		IPointer endPtr = (IPointer) args[1];
		int base = ((ValueInteger) args[2]).intValue();

		try {
			String str = loadString(ptrStr);
			
			long f = Long.parseLong(str, base);
			
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
			
			return new ValueInteger(f);
		} catch (NumberFormatException e) {
			// TODO: log error
		}
		
		return new ValueInteger((long)0);
	}

}
