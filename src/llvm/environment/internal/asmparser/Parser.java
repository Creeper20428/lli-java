package llvm.environment.internal.asmparser;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import llvm.environment.internal.asmparser.TokenInfo.TokenType;
import llvm.environment.internal.ast.ASTArgument;
import llvm.environment.internal.ast.ASTArrayType;
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
import llvm.environment.internal.ast.ASTLocal;
import llvm.environment.internal.ast.ASTModule;
import llvm.environment.internal.ast.ASTNamedParameter;
import llvm.environment.internal.ast.ASTParamAttr;
import llvm.environment.internal.ast.ASTParameter;
import llvm.environment.internal.ast.ASTPointerType;
import llvm.environment.internal.ast.ASTRetInstruction;
import llvm.environment.internal.ast.ASTStringValue;
import llvm.environment.internal.ast.ASTType;
import llvm.environment.internal.ast.ASTUndefValue;
import llvm.environment.internal.ast.ASTValue;

final class Parser {
	
	private final class InstructionParser {
		
		private final Map<String, InstructionWithoutResult> withoutResult;
		private final Map<String, InstructionWithResult> withResult;
		
		private abstract class InstructionWithoutResult {
			abstract ASTInstruction parse();
		}
		
		private abstract class InstructionWithResult {
			abstract ASTInstruction parse(String result);
		}
				
		InstructionParser() {
			withoutResult = new HashMap<String, InstructionWithoutResult>();
			withoutResult.put("ret", new InstructionWithoutResult() {
				@Override
				ASTInstruction parse() {
					final ASTType retType = readType();
					final ASTArgument arg = readArgument();
					return new ASTRetInstruction(retType, arg);
				}
			});
			
			withResult = new HashMap<String, InstructionWithResult>();
			withResult.put("tail", new InstructionWithResult() {
				@Override
				ASTInstruction parse(String result) {
					readToken("call");
					return parseCallInstruction(result, true);
				}
			});
			withResult.put("call", new InstructionWithResult() {
				@Override
				ASTInstruction parse(String result) {
					return parseCallInstruction(result, false);
				}
			});
		}
		
		ASTInstruction parseInstruction(String name) {
			final InstructionWithoutResult parser = withoutResult.get(name);
			return parser.parse();
		}

		ASTInstruction parseInstruction(String name, String result) {
			final InstructionWithResult parser = withResult.get(name);
			return parser.parse(result);
		}
		
		private ASTInstruction parseCallInstruction(String res, boolean tail) {
			final ASTType retType = readType();
			final String func = readTokenType(TokenType.Global);
			readToken("(");
			final List<ASTType> types = new LinkedList<ASTType>();
			final List<ASTArgument> args = new LinkedList<ASTArgument>();
			if (!token.value().equals(")")) {
				while (true) {
					types.add(readType());
					args.add(readArgument());
					if (!token.value().equals(",")) {
						break;
					}
					token = lexer.next();
				}
			}
			readToken(")");
			skipFunctionAttributes();
			return new ASTCallInstruction(tail, res, retType, func,
					types.toArray(new ASTType[0]),
					args.toArray(new ASTArgument[0]));
		}
		
	}

	static private final Set<String> parameterAttributes;
	static private final Set<String> functionAttributes;
	static private final Set<String> linkageType;
	
	private final Iterator<TokenInfo> lexer;
	private final InstructionParser instructionParser;
	private TokenInfo token;
	
	static {
		parameterAttributes = new HashSet<String>();
		Collections.addAll(parameterAttributes, "zeroext", "signext", "inreg",
				"byval", "sret", "noalias", "nocapture", "nest");
		
		functionAttributes = new HashSet<String>();
		Collections.addAll(functionAttributes, "alignstack", "alwaysinline",
				"hotpatch", "inlinehint", "naked", "noimplicitfloat",
				"noinline", "noredzone", "noreturn", "nounwind", "optsize",
				"readnone", "readonly", "ssp", "sspreq");
		
		linkageType = new HashSet<String>();
		Collections.addAll(linkageType, "private", "linker_private",
				"linker_private_weak", "linker_private_weak_def_auto",
				"internal", "available_externally", "linkonce", "weak",
				"common", "appending", "extern_weak", "linkonce_odr",
				"weak_odr", "dllimport", "dllexport");
	}
	
	Parser(Iterator<TokenInfo> lexer) {
		this.lexer = lexer;
		this.instructionParser = new InstructionParser();
		this.token = null;
	}
	
	ASTModule parse() {
		token = lexer.next();
		readToken("target");
		readToken("datalayout");
		readToken("=");
		final String datalayout = readTokenType(TokenType.String);
		readToken("target");
		readToken("triple");
		readToken("=");
		final String triple = readTokenType(TokenType.String);
		
		final ASTModule module = new ASTModule(datalayout, triple);
		while (token.type() != TokenType.EOS) {
			if (token.type() == TokenType.Global) {
				readGlobalDeclaration(module);
				continue;
			}
			if (token.value().equals("define")) {
				token = lexer.next();
				readDefinition(module);
				continue;
			}
			if (token.value().equals("declare")) {
				token = lexer.next();
				readDeclaration(module);
				continue;
			}
			throw new RuntimeException("EOS expected but '" + token.toString() + "' found");
		}
		return module;
	}
	
	private void readGlobalDeclaration(ASTModule module) {
		final String name = readTokenType(TokenType.Global);
		readToken("=");
		
		/* Without linkage */
		if (token.value().equals("global") || token.value().equals("constant")) {
			final boolean constant = token.value().equals("constant");
			token = lexer.next();
			final ASTType type = readType();
			final ASTConstantExpression value = readConstantExpression();
			skipGlobalSpecifiers();
			module.addGlobal(new ASTGlobalDeclaration(name, null, constant, type, value));
			return;
		}
		
		/* With linkage */
		if (linkageType.contains(token.value())) { 
			final String linkageType = readTokenType(TokenType.Identifier);
			boolean constant = false;
			if (token.value().equals("constant")) {
				token = lexer.next();
				constant = true;
			}
			final ASTType type = readType();
			final ASTConstantExpression value = readConstantExpression();
			skipGlobalSpecifiers();
			module.addGlobal(new ASTGlobalDeclaration(name, linkageType, constant, type, value));
			return;
		}
		throw new RuntimeException("Unexpected token '" + token.value() + "'");
	}
	
	private ASTType readType() {
		if (token.value().charAt(0) == 'i') {
			final Integer size = Integer.decode(token.value().substring(1));
			if (size == null) {
				throw new RuntimeException("Integer type name epected but '" + token.value() + "' found");
			}
			token = lexer.next();
			return readType1(new ASTIntegerType(size));
		}
		if (token.value().equals("[")) {
			token = lexer.next();
			final String sizeAsString = readTokenType(TokenType.Integer);
			final Integer size = Integer.decode(sizeAsString);
			if (size == null) {
				throw new RuntimeException("Integer expected but '" + sizeAsString + "' found");
			}
			readToken("x");
			final ASTType inner = readType();
			readToken("]");
			return readType1(new ASTArrayType(size, inner));
		}
		throw new RuntimeException("Undefined type '" + token.value() + "'");
	}
	
	private ASTType readType1(ASTType inner) {
		ASTType result = inner;
		while (token.value().equals("*")) {
			token = lexer.next();
			result = new ASTPointerType(result);
		}
		return result;
	}
	
	private ASTValue readConstant() {
		if (token.value().equals("undef")) {
			readToken("undef");
			return new ASTUndefValue();
		}
		if (token.type() == TokenType.Integer) {
			final BigInteger value = new BigInteger(token.value());
			token = lexer.next();
			return new ASTIntegerValue(value);
		}
		if (token.value().equals("c")) {
			token = lexer.next();
			final String string = readTokenType(TokenType.String);
			return new ASTStringValue(string);
		}
		throw new RuntimeException("Undefined value representation");
	}
	
	private void readDefinition(ASTModule module) {
		final ASTType resType = readType();
		final String name = readTokenType(TokenType.Global);
		readToken("(");
		final List<ASTNamedParameter> params = new LinkedList<ASTNamedParameter>();
		if (!token.value().equals(")")) {
			while (true) {
				final ASTType type = readType();
				skipParamAttrs();
				final String paramName = readTokenType(TokenType.Local);
				params.add(new ASTNamedParameter(type, paramName));
				if (!token.value().equals(",")) {
					break;
				}
				token = lexer.next();
			}
		}
		readToken(")");
		skipFunctionAttributes();
		readToken("{");
		final Map<String, ASTBlock> blocks = new HashMap<String, ASTBlock>();
		final Map<String, ASTType> locals = new HashMap<String, ASTType>();
		final ASTBlock entry = readBlocks(blocks, locals);
		readToken("}");
		module.addDefinition(new ASTDefinition(name, resType, params
				.toArray(new ASTNamedParameter[0]), blocks, entry, locals));
	}
	
	private ASTBlock readBlocks(Map<String, ASTBlock> out, Map<String, ASTType> locals) {
		if (token.value().equals("}")) {
			return null;
		}
		final String name = readTokenType(TokenType.Identifier);
		readToken(":");
		final List<ASTInstruction> instructions = readInstructions(locals);
		final ASTBlock block = new ASTBlock(name, instructions, readBlocks(out, locals));
		out.put(name, block);
		return block;
	}
	
	private ASTArgument readArgument() {
		if (token.type() == TokenType.Local) {
			return new ASTLocal(readTokenType(TokenType.Local));
		}
		return readConstantExpression();
	}
	
	private ASTConstantExpression readConstantExpression() {
		if (token.type() == TokenType.Global) {
			return new ASTGlobal(readTokenType(TokenType.Global));
		}
		
		/* Parse expression */
		if (token.value().equals("getelementptr")) {
			readTokenType(TokenType.Identifier);
			boolean inbounds = false;
			if (token.value().equals("inbounds")) {
				token = lexer.next();
				inbounds = true;
			}
			readToken("(");
			final List<ASTType> types = new LinkedList<ASTType>();
			final List<ASTConstantExpression> args = new LinkedList<ASTConstantExpression>();
			if (!token.value().equals(")")) {
				while (true) {
					final ASTType type = readType();
					final ASTConstantExpression arg = readConstantExpression();
					types.add(type);
					args.add(arg);
					if (!token.value().equals(",")) {
						break;
					}
					token = lexer.next();
				}
			}
			readToken(")");
			return new ASTConstantExpressionGetElemPtr(
					types.toArray(new ASTType[0]),
					args.toArray(new ASTConstantExpression[0]), inbounds);
		}
		return readConstant();
	}
	
	private List<ASTInstruction> readInstructions(Map<String, ASTType> locals) {
		final List<ASTInstruction> instructions = new LinkedList<ASTInstruction>();
		ASTInstruction instruction = null;
		do {
			switch (token.type()) {
			case Identifier: {
				final String name = readTokenType(TokenType.Identifier);
				instruction = instructionParser.parseInstruction(name);
				break;
			}
			case Local: {
				final String result = readTokenType(TokenType.Local);
				readToken("=");
				final String name = readTokenType(TokenType.Identifier);
				instruction = instructionParser.parseInstruction(name, result);
				locals.put(result, instruction.getResultType());
				break;
			}
			default:
				throw new RuntimeException("Label or instruction name expected but '" + token.value() + "' found");
			}
			instructions.add(instruction);
		} while (!instruction.isTerminator());
		return instructions;
	}
	
	private void readDeclaration(ASTModule module) {
		final ASTType resType = readType();
		final String name = readTokenType(TokenType.Global);
		readToken("(");
		final List<ASTParameter> params = new LinkedList<ASTParameter>();
		if (!token.value().equals(")")) {
			while (true) {
				final ASTType type = readType();
				skipParamAttrs();
				params.add(new ASTParameter(type));
				if (!token.value().equals(",")) {
					break;
				}
				token = lexer.next();
			}
		}
		readToken(")");
		skipFunctionAttributes();		
		module.addDeclaration(new ASTDeclaration(name, resType, params.toArray(new ASTParameter[0])));
	}
	
	/* TODO Implement reading attributes. */
	@Deprecated
	private void skipFunctionAttributes() {
		while (functionAttributes.contains(token.value())) {
			final boolean wasAlignstack = token.value().equals("alignstack");
			readTokenType(TokenType.Identifier);
			if (wasAlignstack) {
				readToken("(");
				readTokenType(TokenType.Integer);
				readToken(")");
			}
		}
	}
	
	@Deprecated
	private void skipGlobalSpecifiers() {
		if (token.value().equals(",")) {
			token = lexer.next();
			if (token.value().equals("section")) {
				token = lexer.next();
				readTokenType(TokenType.String);
				if (token.value().equals(",")) {
					readTokenType(TokenType.Delimiter);
					readToken("align");
					readTokenType(TokenType.Integer);
				}
				return;
			}
			if (token.value().equals("align")) {
				token = lexer.next();
				readTokenType(TokenType.Integer);
			}
		}
	}
	
	@Deprecated
	private ASTParamAttr[] skipParamAttrs() {
		/* TODO Implement reading parameter attributes. */
		while (parameterAttributes.contains(token.value())) {
			readTokenType(TokenType.Identifier);
		}
		return new ASTParamAttr[0];
	}
	
	private void readToken(String expectedToken) {
		tokenIs(expectedToken);
		token = lexer.next();
	}
	
	private String readTokenType(TokenType tokenType) {
		tokenTypeIs(tokenType);
		final String result = token.value();
		token = lexer.next();
		return result;
	}

	private void tokenIs(String expectedToken) {
		if (token.value().equals(expectedToken)) {
			return;
		}
		final Formatter fmt = new Formatter();
		fmt.format("Token '%s' expected but '%s' found", expectedToken, token.value());
		throw new RuntimeException(fmt.toString());
	}
	
	private void tokenTypeIs(TokenType tokenType) {
		if (token.type() == tokenType) {
			return;
		}
		final Formatter fmt = new Formatter();
		fmt.format("Token type '%s' expected but '%s' found", tokenType,
				token.value());
		throw new RuntimeException(fmt.toString());
	}
	
}
