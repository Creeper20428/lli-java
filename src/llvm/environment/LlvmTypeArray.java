package llvm.environment;

public final class LlvmTypeArray extends LlvmType {
	private LlvmType inner;
	private int size;

	public LlvmTypeArray(int length, LlvmType inner) {
		this.inner = inner;
		this.size = length * inner.size();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public LlvmType inner() {
		return inner;
	}
}
