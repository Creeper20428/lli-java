package libc.stdlib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import libc.CEnvironment;
import libc.CFunction;
import llvm.AbstractFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class QSort extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer base = (IPointer) args[0];		
		long nmemb = ((ValueInteger) args[1]).longValue();
		long size  = ((ValueInteger) args[2]).longValue();
		final AbstractFunction comparator = (AbstractFunction) args[3];
		

		ArrayList<IPointer> listOfPointers = new ArrayList<IPointer>();
		IPointer ptr = base;
		
		for(int i = 0; i < nmemb; ++i) {
			listOfPointers.add(ptr);
			ptr = ptr.add((int) size);
		}
		
		IPointer[] pointers = (IPointer[]) listOfPointers.toArray();
		Arrays.sort(pointers, new Comparator<IPointer>() {

			@Override
			public int compare(IPointer o1, IPointer o2) {
				try {
					ValueInteger result = (ValueInteger) comparator.call(o1, o2);
					return result.intValue();
				} catch (LlvmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return 0;
			}
		});
		
		byte buf[][] = new byte[(int) nmemb][(int) size];
		for(int i = 0; i < nmemb; ++i) {
			buf[i] = pointers[i].load(0, (int) size);
		}
		
		ptr = base;
		for(int i = 0; i < nmemb; ++i) {
			ptr.store(0, buf[i]);
			ptr = ptr.add((int) size);
		}
		
		return null;
	}

}
