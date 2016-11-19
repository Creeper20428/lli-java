package llvm.environment;

import llvm.AbstractFunction;
import llvm.BadAlloc;
import llvm.IMemory;
import llvm.Inlet;
import llvm.LlvmException;
import llvm.Outlet;
import llvm.Value;

/**
 * Objects of this class represent specific defined functions
 * 
 * @author leso
 * 
 */
public final class LlvmFunction extends AbstractFunction {

	private LlvmFunctionBody functionBody;
	private final LlvmStack stack;
	
	public LlvmFunction(IMemory memory, LlvmStack stack) throws BadAlloc {
		super(memory);
		this.functionBody = null;
		this.stack = stack;
	}
	
	@Override
	public String toString() {
		return "defined_function(" + super.toString() + ")";
	}
	
	public void initialize(LlvmFunctionBody functionBody) {
		this.functionBody = functionBody;
	}

	/**
	 * This method puts arguments to the new stack frame
	 */
	public void push(Outlet out, Value ... args) {
		final LlvmStackFrame stackFrame = new LlvmStackFrame(functionBody, args, null, out);
		stack.push(stackFrame);
	}
	
	@Override
	public Value call(Value ...  args) throws LlvmException {
		final int oldStackSize = stack.size();
		RegisterFake out = new RegisterFake();
		push(out, args);
		
		while (stack.size() > oldStackSize) {
			final LlvmStackFrame stackFrame = stack.peek();
			stackFrame.callNextInstruction();
		}
		
		return out.letIn();
	}

}
