package libc.stdlib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import libc.CEnvironment;
import libc.CFunction;
import llvm.AbstractFunction;
import llvm.IMemory;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class BSearch extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IMemory memory = env.getMemory();
		IPointer key =  (IPointer) args[0];
		IPointer base = (IPointer) args[1];
		
		long nmemb = ((ValueInteger) args[2]).longValue();
		long size  = ((ValueInteger) args[3]).longValue();
		final AbstractFunction comparator = (AbstractFunction) args[4];
		

		ArrayList<IPointer> listOfPointers = new ArrayList<IPointer>();
		IPointer ptr = base;
		
		for(int i = 0; i < nmemb; ++i) {
			listOfPointers.add(ptr);
			ptr = ptr.add((int) size);
		}
		
		IPointer[] pointers = (IPointer[]) listOfPointers.toArray();
		int index = Arrays.binarySearch(pointers, key, new Comparator<IPointer>() {

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
		
		if(index < 0) {
			return memory.nullPointer();
		}
		
		return pointers[index];
	}

}
