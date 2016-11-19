package defaultmemory;

import llvm.MemoryAccessViolation;

final class Location {

	private final int size;
	private final byte[] storage;

	Location(int size) {
		this.size = size;
		this.storage = new byte[size];
	}

	int getSize() {
		return size;
	}

	byte[] getBytes(int start, int size) throws MemoryAccessViolation {
		int end = start + size;
        
        if (end > this.size) {
            throw new MemoryAccessViolation();
        }
        
        byte[] result = new byte[size];
        System.arraycopy(storage, start, result, 0, size);
        
        return result;
	}

	void setBytes(int start, byte[] bytes) throws MemoryAccessViolation {
		int end = start + bytes.length;
        if (end > this.size) {
            throw new MemoryAccessViolation();
        }
        
        System.arraycopy(bytes, 0, storage, start, bytes.length);
	}

}
