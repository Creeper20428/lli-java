package llvm.environment.internal.ast;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

public final class ASTModule {
	
	private final String datalayout;
	private final String triple;
	private final Map<String, ASTGlobalDeclaration> globals;
	private final Map<String, ASTDefinition> definitions;	
	private final Map<String, ASTDeclaration> declarations;

	public ASTModule(String datalayout, String triple) {
		this.datalayout = datalayout;
		this.triple = triple;
		this.globals = new HashMap<String, ASTGlobalDeclaration>();
		this.definitions = new HashMap<String, ASTDefinition>();
		this.declarations = new HashMap<String, ASTDeclaration>();
	}
	
	@Override
	public String toString() {
		final Formatter fmt = new Formatter();
		
		fmt.format("target datalayout = \"%s\"\n", datalayout);
		fmt.format("target triple = \"%s\"\n\n", triple);
		for (ASTGlobalDeclaration global : globals.values()) {
			fmt.format("%s\n", global);
		}
		fmt.format("\n");
		for (ASTDefinition definition : definitions.values()) {
			fmt.format("%s\n", definition);
		}
		for (ASTDeclaration declaration : declarations.values()) {
			fmt.format("%s\n", declaration);
		}
		fmt.format("\n");
		return fmt.toString();
	}
	
	public ASTGlobalDeclaration getGlobal(String name) {
		return globals.get(name);
	}
	
	public String getDatalayout() {
		return datalayout;
	}
	
	public String getTriple() {
		return triple;
	}
	
	public Map<String, ASTGlobalDeclaration> getGlobalDeclarations() {
		return globals;
	}
	
	public Map<String, ASTDefinition> getDefinitions() {
		return definitions;
	}
	
	public Map<String, ASTDeclaration> getDeclarations() {
		return declarations;
	}
	
	public void addGlobal(ASTGlobalDeclaration global) {
		globals.put(global.getName(), global);
	}
	
	public void addDefinition(ASTDefinition definition) {
		definitions.put(definition.getName(), definition);
	}
	
	public void addDeclaration(ASTDeclaration declaration) {
		declarations.put(declaration.getName(), declaration);
	}
	
}
