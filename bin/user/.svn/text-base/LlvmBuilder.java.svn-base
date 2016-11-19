package user;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import utils.ConversionUtility;

import llvm.AbstractFunction;
import llvm.BadAlloc;
import llvm.IMemory;
import llvm.IPointer;
import llvm.Inlet;
import llvm.LlvmException;
import llvm.MemoryAccessViolation;
import llvm.Outlet;
import llvm.Value;
import llvm.environment.AbstractLibrary;
import llvm.environment.Instruction;
import llvm.environment.LlvmEnvironment;
import llvm.environment.LlvmFunction;
import llvm.environment.LlvmFunctionBody;
import llvm.environment.LlvmStack;
import llvm.environment.LlvmType;
import llvm.environment.LlvmTypeArray;
import llvm.environment.LlvmTypePointer;
import llvm.environment.LlvmTypePrimitive;
import llvm.environment.RegisterArgument;
import llvm.environment.RegisterFake;
import llvm.environment.RegisterGlobal;
import llvm.environment.RegisterLocal;
import llvm.environment.internal.ast.ASTArgument;
import llvm.environment.internal.ast.ASTArrayType;
import llvm.environment.internal.ast.ASTArrayValue;
import llvm.environment.internal.ast.ASTBlock;
import llvm.environment.internal.ast.ASTCallInstruction;
import llvm.environment.internal.ast.ASTConstantExpressionGetElemPtr;
import llvm.environment.internal.ast.ASTConstantExpression;
import llvm.environment.internal.ast.ASTDeclaration;
import llvm.environment.internal.ast.ASTDefinition;
import llvm.environment.internal.ast.ASTGlobal;
import llvm.environment.internal.ast.ASTGlobalDeclaration;
import llvm.environment.internal.ast.ASTInstruction;
import llvm.environment.internal.ast.ASTIntegerType;
import llvm.environment.internal.ast.ASTIntegerValue;
import llvm.environment.internal.ast.ASTModule;
import llvm.environment.internal.ast.ASTPointerType;
import llvm.environment.internal.ast.ASTRetInstruction;
import llvm.environment.internal.ast.ASTStringValue;
import llvm.environment.internal.ast.ASTType;
import llvm.environment.internal.ast.ASTUndefValue;
import llvm.environment.internal.ast.ASTValue;
import llvm.instructions.InstructionCall;
import llvm.instructions.InstructionGetElemPtr;
import llvm.instructions.InstructionLabel;
import llvm.instructions.InstructionRet;
import llvm.values.ValueByteArray;
import llvm.values.ValueInteger;
import llvm.values.ValueLabel;

final class Context
{
	final Map<String, RegisterGlobal> globals;
	final Map<String, RegisterLocal> locals;
	final Map<String, RegisterArgument> arguments;
	
	Context(Map<String, RegisterGlobal> globals,
			Map<String, RegisterLocal> locals,
			Map<String, RegisterArgument> arguments) {
		this.globals = globals;
		this.locals = locals;
		this.arguments = arguments;
	}
}

final class LlvmBuilder {
	
	private abstract class BuildInstruction {
		abstract Instruction apply(ASTInstruction instruction, Context context) throws LlvmException;
	}
	
	private final class BuildRet extends BuildInstruction {

		@Override
		Instruction apply(ASTInstruction instruction, Context context) throws LlvmException {
			final ASTRetInstruction ret = (ASTRetInstruction) instruction;
			final Inlet argument = buildArgument(ret.getArgument(), context);
			
			return new InstructionRet(argument);
		}
		
	}
	
	private final class BuildCall extends BuildInstruction {

		@Override
		Instruction apply(ASTInstruction instruction, Context context) throws LlvmException {
			final ASTCallInstruction call = (ASTCallInstruction) instruction;
			final ASTArgument[] astArguments = call.getArguments();
			final Inlet[] arguments = new Inlet[astArguments.length];
			final String result = call.getResult();
			final Outlet out = lookupLocal(context.locals, result);
			final Inlet func = context.globals.get(call.getFunctionName());
			
			for (int i = 0; i < astArguments.length; ++i) {
				arguments[i] = buildArgument(astArguments[i], context);
			}
			return new InstructionCall(func, arguments, out);
		}
		
	}

	private final AbstractLibrary functionFactory;
	private final HashMap<String, BuildInstruction> transformations;

	LlvmBuilder(AbstractLibrary functionFactory) {
		this.functionFactory = functionFactory;
		this.transformations = new HashMap<String, BuildInstruction>();
		this.transformations.put("ret", new BuildRet());
		this.transformations.put("call", new BuildCall());
	}

	public Outlet lookupLocal(Map<String, RegisterLocal> locals, String key) {
		if (!locals.containsKey(key)) {
			final LlvmStack stack = functionFactory.getEnvironment().getStack();
			final RegisterLocal value = new RegisterLocal(stack, locals.size());
			locals.put(key, value);
			return value;
		}
		return locals.get(key);
	}

	LlvmModule buildModule(ASTModule astModule) throws LlvmException {
		final IMemory allocator = functionFactory.getEnvironment().getMemory();
		final HashMap<String, RegisterGlobal> globals = new HashMap<String, RegisterGlobal>();
		final HashMap<String, AbstractFunction> functions = new HashMap<String, AbstractFunction>();
		final HashMap<String, LlvmFunction> definitions = new HashMap<String, LlvmFunction>();
		
		addGlobals(astModule, globals);
		addDeclaredFunctions(astModule, globals, functions);
		addEmptyDefinitions(astModule, globals, definitions, functions);
		initializeDefinitions(astModule, globals, definitions);
		
		return new LlvmModule(allocator, functions);
	}

	private void addEmptyDefinitions(ASTModule astModule,
			HashMap<String, RegisterGlobal> globals,
			HashMap<String, LlvmFunction> definitions, HashMap<String,AbstractFunction> functions) throws BadAlloc {
		final LlvmEnvironment environment = functionFactory.getEnvironment();
		final IMemory allocator = environment.getMemory();
		final LlvmStack stack = environment.getStack();
		final Map<String, ASTDefinition> map = astModule.getDefinitions();

		for (String functionName : map.keySet()) {
			final LlvmFunction function = new LlvmFunction(allocator, stack);
			final RegisterGlobal registerGlobal = new RegisterGlobal(function);

			functions.put(functionName, function);
			definitions.put(functionName, function);
			globals.put(functionName, registerGlobal);
		}
	}

	private void addDeclaredFunctions(ASTModule astModule,
			HashMap<String, RegisterGlobal> globals, HashMap<String,AbstractFunction> functions) throws BadAlloc {
		final Map<String, ASTDeclaration> map = astModule.getDeclarations();

		for (String functionName : map.keySet()) {
			final AbstractFunction function = functionFactory.getFunction(functionName);
			final RegisterGlobal registerGlobal = new RegisterGlobal(function);

			functions.put(functionName, function);
			globals.put(functionName, registerGlobal);
		}
	}

	private void addGlobals(ASTModule astModule,
			HashMap<String, RegisterGlobal> globals) throws LlvmException {
		final IMemory allocator = functionFactory.getEnvironment().getMemory();
		final Map<String, ASTGlobalDeclaration> map = astModule
				.getGlobalDeclarations();

		for (String globalName : map.keySet()) {
			final ASTGlobalDeclaration declaration = map.get(globalName);
			final ASTConstantExpression astCE = declaration.getValue();
			final Inlet ce = buildCE(astCE, globals);
			final Value innerValue = ce.letIn();
			final byte[] bytes = innerValue.toBytes();
			final IPointer value = allocator.allocate(bytes.length);

			try {
				value.store(0, bytes);
			} catch (MemoryAccessViolation e) {
				throw new RuntimeException("Internal error");
			}
			globals.put(globalName, new RegisterGlobal(value));
		}
	}

	private void initializeDefinitions(ASTModule astModule,
			Map<String, RegisterGlobal> globals,
			Map<String, LlvmFunction> definitions) throws LlvmException {
		final Map<String, ASTDefinition> astDefinitions = astModule.getDefinitions();
		
		for (String functionName : definitions.keySet()) {
			final ASTDefinition astDefinition = astDefinitions.get(functionName);
			final LlvmFunctionBody functionBody = buildFunctionBody(astDefinition, globals);
			
			definitions.get(functionName).initialize(functionBody);
		}
	}

	private LlvmFunctionBody buildFunctionBody(ASTDefinition definition,
			Map<String, RegisterGlobal> globals) throws LlvmException {
		final LlvmStack stack = functionFactory.getEnvironment().getStack();
		final Map<String, RegisterLocal> locals = new HashMap<String, RegisterLocal>();
		final Map<String, RegisterArgument> arguments = new HashMap<String, RegisterArgument>();
		final Context context = new Context(globals, locals, arguments);
		final int nInstructions = calculateInstructionsNumber(definition.getEntry());
		final Instruction[] instructions = new Instruction[nInstructions];
		
		ASTBlock block = definition.getEntry();
		int i = 0;
		int iLabel = 0;
		do {
			final List<ASTInstruction> instrs = block.getInstructions();
			
			boolean isFirst = true;
			for (ASTInstruction instr : instrs) {
				final Instruction instruction = buildInstruction(instr, context); 
				
				if (isFirst) {
					isFirst = false;
					instructions[i++] = new InstructionLabel(instruction, stack, new ValueLabel(iLabel++));
				} else {
					instructions[i++] = instruction; 
				}
			}
			block = block.getNextBlock();
		} while (block != null);
		
		return new LlvmFunctionBody(locals.size(), instructions);
	}

	private Instruction buildInstruction(ASTInstruction instr, Context context) throws LlvmException {
		final String instructionName = instr.getName();
		final BuildInstruction buildInstruction = transformations.get(instructionName);
		final Instruction instruction = buildInstruction.apply(instr, context);
		
		instruction.setEnvironment(functionFactory.getEnvironment());
		return instruction;
	}

	private int calculateInstructionsNumber(ASTBlock entry) {
		int nInstructions = 0;

		ASTBlock block = entry;
		do {
			nInstructions += block.getInstructions().size();
			block = block.getNextBlock();
		} while (block != null);
		return nInstructions;
	}
	
	private Inlet buildArgument(ASTArgument argument, Context context) throws LlvmException {
		if (argument instanceof ASTConstantExpression) {
			final ASTConstantExpression astCE = (ASTConstantExpression) argument;
			final Inlet constExpr = buildCE(astCE, context.globals);
			return constExpr;
		}
		// Local
		assert false : argument;
		return null;
	}

	private Inlet buildCE(ASTConstantExpression astCE, Map<String,RegisterGlobal> globals) throws LlvmException {
		if (astCE instanceof ASTGlobal) {
			final ASTGlobal astGlobal = (ASTGlobal) astCE;
			return buildGlobal(astGlobal, globals);
		}
		if (astCE instanceof ASTValue) {
			final ASTValue astValue = (ASTValue) astCE;
			return new RegisterGlobal(buildValue(astValue));
		}
		if (astCE instanceof ASTConstantExpressionGetElemPtr) {
			final ASTConstantExpressionGetElemPtr astCCE = (ASTConstantExpressionGetElemPtr) astCE;
			return buildCEGetElemPtr(astCCE, globals);
		}
		// XXX add other constant expressions here
		assert false : astCE;
		return null;
	}

	private Value buildValue(ASTValue astValue) throws BadAlloc, MemoryAccessViolation {
		if (astValue instanceof ASTArrayValue) {
			return buildArrayValue((ASTArrayValue) astValue);
		}
		if (astValue instanceof ASTIntegerValue) {
			return buildIntegerValue((ASTIntegerValue) astValue);
		}
		if (astValue instanceof ASTStringValue) {
			return buildStringValue((ASTStringValue) astValue);
		}
		if (astValue instanceof ASTUndefValue) {
			return null;
		}
		assert false : astValue;
		return null;
	}

	private Value buildStringValue(ASTStringValue astStringValue) throws BadAlloc, MemoryAccessViolation {
		final String asString = astStringValue.getString();
		final StringBuilder stringBulider = new StringBuilder();
		final int length = asString.length();
		
		for (int i = 0; i < length; ++i) {
			final char ch = asString.charAt(i); 
			if (ch == '\\') {
				if (i + 2 < length) {
					final int hi = Character.digit(asString.charAt(i + 1), 16);
					final int lo = Character.digit(asString.charAt(i + 2), 16);
					final int asInt = hi * 0x10 + lo;
					final char asChar = (char) asInt;
					stringBulider.append(asChar);
					i += 2;
					continue;
				}
			}
			stringBulider.append(ch);
		}
		final byte[] asBytes = ConversionUtility.stringToBytes(stringBulider.toString());
		return new ValueByteArray(asBytes);
	}

	private Value buildIntegerValue(ASTIntegerValue astIntegerValue) {
		return new ValueInteger(astIntegerValue.getInteger());
	}

	private Value buildArrayValue(ASTArrayValue astValue) {
		// TODO Auto-generated method stub
		return null;
	}

	private Inlet buildCEGetElemPtr(ASTConstantExpressionGetElemPtr astCCE, Map<String,RegisterGlobal> globals) throws LlvmException {
		final ASTConstantExpression[] arguments = astCCE.getArguments();
		final Inlet value = buildCE(arguments[0], globals);
		
		if (!(value.letIn() instanceof IPointer)) {
			throw new LlvmException("");
		}
		final LlvmType type = buildType(astCCE.getType(), true);
		
		if (!(type instanceof LlvmTypePointer)) {
			throw new LlvmException("");
		}
		final LlvmTypePointer baseType = (LlvmTypePointer) type;
		final Inlet[] args = new Inlet[arguments.length - 1];
		
		for (int i = 1; i < arguments.length; ++i) {
			final BigInteger integer = buildInteger(arguments[i]);
			final ValueInteger valueInteger = new ValueInteger(integer);
			args[i - 1] = new RegisterGlobal(valueInteger);
		}
		
		final RegisterFake out = new RegisterFake();
		final InstructionGetElemPtr intruction = new InstructionGetElemPtr(baseType, value, args, out);
		
		intruction.call();
		return out;
	}

	private BigInteger buildInteger(ASTConstantExpression astConstantExpression) throws LlvmException {
		if (astConstantExpression instanceof ASTIntegerValue) {
			final ASTIntegerValue integer = (ASTIntegerValue) astConstantExpression;
			return integer.getInteger();
		}
		throw new LlvmException("");
	}

	private LlvmType buildType(ASTType type, boolean isBase) {
		if (type instanceof ASTArrayType) {
			ASTArrayType arrayType = (ASTArrayType) type;
			return new LlvmTypeArray(arrayType.getLength(), buildType(arrayType.getInner(), isBase));
		}
		if (type instanceof ASTIntegerType) {
			ASTIntegerType integerType = (ASTIntegerType) type;
			return new LlvmTypePrimitive(1 + (integerType.getSize() - 1)/8);
		}
		if (type instanceof ASTPointerType) {
			ASTPointerType pointerType = (ASTPointerType) type;
			if (isBase) {
				return new LlvmTypePointer(buildType(pointerType.getInner(), false));
			} else {
				return new LlvmTypePrimitive(64);
			}
		}
		throw new AssertionError(type); 
	}

	private Inlet buildGlobal(ASTGlobal astGlobal, Map<String,RegisterGlobal> globals) {
		final String name = astGlobal.getName();
		
		if (!globals.containsKey(name)) {
			throw new NoSuchElementException(name);
		}
		return globals.get(name);
	}

}
