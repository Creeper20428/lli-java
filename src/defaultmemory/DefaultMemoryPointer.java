package defaultmemory;

import utils.ConversionUtility;
import llvm.IPointer;
import llvm.MemoryAccessViolation;

final class DefaultMemoryPointer implements IPointer {

	private DefaultMemory memory;
	private int location;
	private int index;
	
	DefaultMemoryPointer(DefaultMemory memory, int location, int index) {
		this.memory = memory;
		this.location = location;
		this.index = index;
	}
	
	@Override
	public String toString() {
		final String hexLocation = Integer.toHexString(location);
		final String hexIndex = Integer.toHexString(index);
		
		return "pointer(" + hexLocation + ":" + hexIndex + ")";
	}
	
	@Override
	public IPointer add(int shift) {
		return new DefaultMemoryPointer(memory, location, index + shift);
	}

	@Override
	public boolean isNull() {
		if((location == 0)&&(index == 0)) {
			return true;
		}
		
		return false;
	}

	@Override
	public long toLong() {
		long result = location;
    	result <<= 32;
    	result += index;
    	
    	return result;
	}

	@Override
	public int getSize() {
		return memory.size(location) - index;
	}

	@Override
	public void free() {
		memory.deallocate(location);
	}

	@Override
	public byte[] load(int offset, int size) throws MemoryAccessViolation {
		return memory.load(location, index + offset, size);
	}

	@Override
	public void store(int offset, byte[] data) throws MemoryAccessViolation {
		memory.store(location, index + offset , data);
	}

	@Override
	public byte[] toBytes() {
		return ConversionUtility.longToBytes(toLong());
	}

}
