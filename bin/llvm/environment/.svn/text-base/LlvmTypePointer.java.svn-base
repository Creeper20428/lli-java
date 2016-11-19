package llvm.environment;

public final class LlvmTypePointer extends LlvmType {
	private LlvmType inner;

	public LlvmTypePointer(LlvmType inner) {
		this.inner = inner;
	}

	@Override
	public int size() {
		throw new AssertionError(this);
	}

	@Override
	public LlvmType inner() {
		return inner;
	}
}
