package llvm;


/**
 * This interface can be used to share a memory between different modules.
 */
public interface IMemory {
	
	IPointer nullPointer();

	IPointer long2pointer(long longValue);

	IPointer allocate(long size) throws BadAlloc;
	
	/** @return pointer size in bytes */
	int pointerSize();
}
