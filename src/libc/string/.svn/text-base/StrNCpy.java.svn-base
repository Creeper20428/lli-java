package libc.string;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;
import static libc.CEnvironment.loadString;

public class StrNCpy extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer dst = (IPointer) args[0];
		IPointer src = (IPointer) args[1];
		
		int num = (int) ((ValueInteger)args[2]).longValue();

		String str = loadString(src);
		int strlen = Math.min(str.length(), num);
		char[] chars = str.toCharArray();
			
		byte[] buf = new byte[num];
			
		for(int i = 0; i < strlen; ++i) {
			buf[i] = (byte) chars[i];
		}
			
		dst.store(0, buf);
		
		return dst;
	}

}
