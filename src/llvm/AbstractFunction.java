package llvm;


/**
 * This is a base interface for functions.
 * 
 * @author leso
 * 
 */
public abstract class AbstractFunction implements Value {
	
	private final IPointer address;
	
	protected AbstractFunction(IMemory memory) throws BadAlloc {
		this.address = memory.allocate(8);
	}
	
	protected AbstractFunction(IPointer	ptr) {
		address = ptr;
	}
	
	public IPointer getAddress() {
		return address;
	}
	
	@Override
	public String toString() {
		return address.toString();
	}
	
	@Override
	public final byte[] toBytes() {
		return address.toBytes();
	}
	
	public abstract Value call(Value ...  args) throws LlvmException;
	
}
