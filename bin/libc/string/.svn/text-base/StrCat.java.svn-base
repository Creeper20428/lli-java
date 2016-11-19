package libc.string;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import static libc.CEnvironment.loadString;

public class StrCat extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer dst = (IPointer) args[0];
		IPointer src = (IPointer) args[1];

		String dstStr = loadString(dst);
		String srcStr = loadString(src);
			
		IPointer ptr = dst.add(dstStr.length());
		byte[] buf = src.load(0, srcStr.length());
		ptr.store(0, buf);
			
		// terminating symbol
		IPointer end = ptr.add(srcStr.length());
		end.store(0, new byte[1]);
		
		return dst;
	}

}
