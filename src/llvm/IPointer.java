package llvm;


public interface IPointer extends Value {

	IPointer add(int shift);

	boolean isNull();

	long toLong();

	byte[] load(int offset, int size) throws MemoryAccessViolation;
	
	void store(int offset, byte[] data) throws MemoryAccessViolation;

	int getSize();

	void free();

}
