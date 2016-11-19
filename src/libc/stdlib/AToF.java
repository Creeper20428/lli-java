package libc.stdlib;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueDouble;
import static libc.CEnvironment.loadString;

public class AToF extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer ptr = (IPointer) args[0];

		try {
			String str = loadString(ptr);
			
			double f = Double.parseDouble(str);
			return new ValueDouble(f);
		} catch (NumberFormatException e) {
			// TODO: log error
		}
		
		return new ValueDouble(0);
	}

}
