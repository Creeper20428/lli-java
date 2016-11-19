package libc.string;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import static libc.CEnvironment.loadString;

public class StrCpy extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer dst = (IPointer) args[0];
		IPointer src = (IPointer) args[1];

		String str = loadString(src);
		char[] chars = str.toCharArray();
			
		byte[] buf = new byte[str.length() + 1];
		int i = 0;
		
		for(char ch : chars) {
			buf[i++] = (byte) ch;
		}
		buf[i] = 0;
			
		dst.store(0, buf);
		
		return dst;
	}

}
