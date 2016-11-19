package llvm.instructions;

import llvm.IPointer;
import llvm.Inlet;
import llvm.LlvmException;
import llvm.Outlet;
import llvm.Value;
import llvm.environment.Instruction;
import llvm.environment.LlvmType;
import llvm.environment.LlvmTypePointer;
import llvm.values.ValueInteger;

public final class InstructionGetElemPtr extends Instruction {
	
	private final LlvmTypePointer baseType;
	private final Inlet base;
	private final Inlet[] args;
	private final Outlet out;

	public InstructionGetElemPtr(LlvmTypePointer type, Inlet base, Inlet[] args, Outlet out) {
		this.base = base;
		this.baseType = type;
		this.args = args;
		this.out = out;
	}

	@Override
	public void call() throws LlvmException {		
		final Value baseValue = base.letIn();
		
		assert baseValue instanceof IPointer : baseValue;
		IPointer pointer = (IPointer) baseValue;
		
		LlvmType type = baseType;
		
		for (Inlet arg : args) {
			type = type.inner();
			
			final Value value = arg.letIn();
			
			assert value instanceof ValueInteger : value;
			final ValueInteger shift = (ValueInteger) value;
			
			if (type == null) {
				throw new LlvmException("Too much arguments");
			}
			pointer = pointer.add(type.size() * shift.intValue());
		}
		out.letOut(pointer);
	}

}
