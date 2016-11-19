package libc.time;

import utils.ConversionUtility;
import libc.CEnvironment;
import libc.CFormatter;
import libc.CFunction;
import libc.StructTM;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;
import static libc.CEnvironment.loadString;

public class StrFTime extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer ptr = (IPointer) args[0];
		long size = ((ValueInteger) args[1]).longValue();
		IPointer ptrFormat = (IPointer) args[2];
		IPointer timePtr = (IPointer) args[3];

		byte[] data = timePtr.load(0, StructTM.size);
		StructTM tm = new StructTM(data);
		
		String format = loadString(ptrFormat);
		String str = CFormatter.timeFormat(format, tm);
		
		if(str.length() > size + 1) {
			str = str.substring(0, (int) (size - 1));
		}
		
		ptr.store(0, ConversionUtility.stringToBytes(str));
		
		return new ValueInteger((long)(str.length() + 1));
	}

}
