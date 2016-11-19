.assembly extern mscorlib {}
.assembly MSIL {}

// External
.method static hidebysig pinvokeimpl("MSVCRT.DLL")
	unsigned int32 modopt([mscorlib]System.Runtime.CompilerServices.CallConvCdecl) 'puts'(void* ) preservesig {}

.method public hidebysig static pinvokeimpl("KERNEL32.DLL" ansi winapi)  native int LoadLibrary(string) preservesig {}
.method public hidebysig static pinvokeimpl("KERNEL32.DLL" ansi winapi)  native int GetProcAddress(native int, string) preservesig {}
.method private static void* $MSIL_Import(string lib,string sym)
 managed cil
{
	ldarg	lib
	call	native int LoadLibrary(string)
	ldarg	sym
	call	native int GetProcAddress(native int,string)
	dup
	brtrue	L_01
	ldstr	"Can no import variable"
	newobj	instance void [mscorlib]System.Exception::.ctor(string)
	throw
L_01:
	ret
}

.method static private void $MSIL_Init() managed cil
{
	ret
}

// Declarations
.class value explicit ansi sealed 'unsigned int8 [12]' { .pack 1 .size 12 }

// Definitions
.field static private valuetype 'unsigned int8 [12]' '.str' at '.str$data'
.data '.str$data' = {
int8 (72),
int8 (101),
int8 (108),
int8 (108),
int8 (111),
int8 (32),
int8 (76),
int8 (76),
int8 (86),
int8 (77),
int8 (33),
int8 (0) [1]
}

// Startup code
.method static public int32 $MSIL_Startup() {
	.entrypoint
	.locals (native int i)
	.locals (native int argc)
	.locals (native int ptr)
	.locals (void* argv)
	.locals (string[] args)
	call	string[] [mscorlib]System.Environment::GetCommandLineArgs()
	dup
	stloc	args
	ldlen
	conv.i4
	dup
	stloc	argc
	ldc.i4	4
	mul
	localloc
	stloc	argv
	ldc.i4.0
	stloc	i
L_01:
	ldloc	i
	ldloc	argc
	ceq
	brtrue	L_02
	ldloc	args
	ldloc	i
	ldelem.ref
	call	native int [mscorlib]System.Runtime.InteropServices.Marshal::StringToHGlobalAnsi(string)
	stloc	ptr
	ldloc	argv
	ldloc	i
	ldc.i4	4
	mul
	add
	ldloc	ptr
	stind.i
	ldloc	i
	ldc.i4.1
	add
	stloc	i
	br	L_01
L_02:
	call void $MSIL_Init()
	call	unsigned int32 modopt([mscorlib]System.Runtime.CompilerServices.CallConvCdecl) main()
	conv.i4
	ret
}

.method static public unsigned int32 modopt([mscorlib]System.Runtime.CompilerServices.CallConvCdecl) 'main'
	() cil managed
{
	.locals (unsigned int32 'tmp1')
	.maxstack	16
tmp2:
	ldsflda	valuetype 'unsigned int8 [12]' '.str'
	conv.u4
	call	unsigned int32 modopt([mscorlib]System.Runtime.CompilerServices.CallConvCdecl) 'puts'(void* )
	stloc	'tmp1'
	ldc.i4	0
	ret
}
