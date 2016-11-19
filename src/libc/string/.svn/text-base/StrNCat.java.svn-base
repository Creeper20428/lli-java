package libc.string;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;
import static libc.CEnvironment.loadString;

public class StrNCat extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer dst = (IPointer) args[0];
		IPointer src = (IPointer) args[1];
		int num = ((ValueInteger)args[2]).intValue();

		String dstStr = loadString(dst);
		String srcStr = loadString(src);
		int length = Math.min(num, srcStr.length());

		IPointer ptr = dst.add(dstStr.length());
		byte[] buf = src.load(0, length);	
		ptr.store(0, buf);
		
		// terminating symbol
		IPointer end = ptr.add(length);
		end.store(0, new byte[1]);
		
		return dst;
	}

}
