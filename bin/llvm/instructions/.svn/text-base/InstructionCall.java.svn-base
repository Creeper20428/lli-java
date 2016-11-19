package llvm.instructions;

import java.util.Arrays;

import llvm.AbstractFunction;
import llvm.Inlet;
import llvm.LlvmException;
import llvm.Outlet;
import llvm.Value;
import llvm.environment.Instruction;
import llvm.environment.LlvmFunction;

public final class InstructionCall extends Instruction {
	
	private final Inlet func;
	private final Inlet[] args;
	private final Outlet out;

	public InstructionCall(Inlet func, Inlet[] args, Outlet out) {
		this.func = func;
		this.args = args;
		this.out = out;
	}
	
	@Override
	public String toString() {
		return "call(" + func + ", " + Arrays.asList(args) + ", " + out + ")";
	}

	@Override
	public void call() throws LlvmException {
		final Value value = func.letIn();
		
		int length = args.length;
		Value[] arguments = new Value[length];
		for(int i = 0; i < length; ++i) {
			arguments[i] = args[i].letIn();
		}
		
		if (value instanceof LlvmFunction) {
			final LlvmFunction function = (LlvmFunction) value;
			function.push(out, arguments);
		} else {
			final AbstractFunction function = (AbstractFunction) value;
			Value result = function.call(arguments);
			out.letOut(result);
		}
	}

}
