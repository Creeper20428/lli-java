package libc;

import java.util.HashMap;

import llvm.AbstractFunction;
import llvm.BadAlloc;
import llvm.Value;
import llvm.environment.AbstractLibrary;
import llvm.environment.LlvmEnvironment;
import libc.stdlib.*;
import libc.cassert.AssertFail;
import libc.errno.*;
import libc.math.*;
import libc.signal.*;
import libc.stdio.*;
import libc.string.*;
import libc.time.*;

/**
 * Instances of this class can't be created by a user.
 * 
 * @author leso
 * 
 */
public final class CLibrary extends AbstractLibrary {

	private CEnvironment env;
	
	private static HashMap<String, CFunction> libcFunctions;
	
	{
		libcFunctions = new HashMap<String, CFunction>();
		// include <errno.h>
		libcFunctions.put("__errno_location", new ErrnoLocation());
		
		//include <assert.h>
		libcFunctions.put("__assert_fail", new AssertFail());
		
		//include <math.h>
		libcFunctions.put("acos", new ACos());
		libcFunctions.put("asin", new ASin());
		libcFunctions.put("atan", new ATan());
		libcFunctions.put("atan2", new ATan2());
		libcFunctions.put("cos", new Cos());
		libcFunctions.put("sin", new Sin());
		libcFunctions.put("cosh", new CosH());
		libcFunctions.put("sinh", new SinH());
		libcFunctions.put("tanh", new TanH());
		libcFunctions.put("exp", new Exp());
		libcFunctions.put("frexp", new FrExp());
		libcFunctions.put("ldexp", new LdExp());
		libcFunctions.put("log", new Log());
		libcFunctions.put("log10", new Log10());
		libcFunctions.put("modf", new ModF());
		libcFunctions.put("pow", new Pow());
		libcFunctions.put("sqrt", new Sqrt());
		libcFunctions.put("ceil", new Ceil());
		libcFunctions.put("fabs", new FAbs());
		libcFunctions.put("floor", new Floor());
		libcFunctions.put("fmod", new FMod());
		
		// include <signal.h>
		libcFunctions.put("signal", new Signal());
		libcFunctions.put("raise", new Raise());
		
		// include <stdio.h>
		libcFunctions.put("remove", new Remove());
		libcFunctions.put("rename", new Rename());
		libcFunctions.put("tmpfile", new TmpFile());
		libcFunctions.put("tmpnam", new TmpNam());
		
		libcFunctions.put("fclose", new FClose());
		libcFunctions.put("fflush", new FFlush());
		libcFunctions.put("fopen", new FOpen());
		libcFunctions.put("freopen", new FReopen());
		
		libcFunctions.put("fgetc", new FGetC());
		libcFunctions.put("fgets", new FGetS());
		libcFunctions.put("fputc", new FPutC());
		libcFunctions.put("fputs", new FPutS());
		libcFunctions.put("getc", new FGetC());
		libcFunctions.put("getchar", new GetChar());
		libcFunctions.put("gets", new GetS());
		libcFunctions.put("putc", new FPutS());
		libcFunctions.put("putchar", new PutChar());
		libcFunctions.put("puts", new PutS());
		libcFunctions.put("ungetc", new UngetC());
		
		libcFunctions.put("fread", new FRead());
		libcFunctions.put("fwrite", new FWrite());
		
		libcFunctions.put("fgetpos", new FGetPos());
		libcFunctions.put("fseek", new FSeek());
		libcFunctions.put("fsetpos", new FSetPos());
		libcFunctions.put("ftell", new FTell());
		
		libcFunctions.put("rewind", new Rewind());
		libcFunctions.put("clearerr", new ClearErr());
		libcFunctions.put("feof", new FEof());
		libcFunctions.put("ferror", new FError());
		libcFunctions.put("perror", new PError());
		
		// include <stdlib.h>
		libcFunctions.put("atof", new AToF());
		libcFunctions.put("atoi", new AToI());
		libcFunctions.put("atol", new AToL());
		libcFunctions.put("strtod", new StrToD());
		libcFunctions.put("strtol", new StrToL());
		libcFunctions.put("strtoul", new StrToUL());
		
		libcFunctions.put("rand", new Rand());
		libcFunctions.put("srand", new SRand());
		
		libcFunctions.put("calloc", new CAlloc());
		libcFunctions.put("free", new Free());
		libcFunctions.put("malloc", new MAlloc());
		libcFunctions.put("realloc", new ReAlloc());
		
		libcFunctions.put("bsearch", new BSearch());
		libcFunctions.put("qsort", new QSort());
		
		libcFunctions.put("abs", new Abs());
		libcFunctions.put("div", new Div());
		libcFunctions.put("labs", new LAbs());
		libcFunctions.put("ldiv", new LDiv());
		
		// include <string.h>
		libcFunctions.put("memcpy", new MemCpy());
		libcFunctions.put("memmove", new MemMove());
		libcFunctions.put("strcpy", new StrCpy());
		libcFunctions.put("strncpy", new StrNCpy());
		
		libcFunctions.put("strcat", new StrCat());
		libcFunctions.put("strncat", new StrNCat());
		libcFunctions.put("memcmp", new MemCmp());
		libcFunctions.put("strcmp", new StrCmp());
		libcFunctions.put("strncmp", new StrNCmp());
		
		libcFunctions.put("memchr", new MemChr());
		libcFunctions.put("strchr", new StrChr());
		libcFunctions.put("strcspn", new StrCSpn());
		libcFunctions.put("strpbrk", new StrPBrk());
		libcFunctions.put("strrchr", new StrRChr());
		libcFunctions.put("strspn", new StrSpn());
		libcFunctions.put("strstr", new StrStr());
		libcFunctions.put("strtok", new StrTok());
		libcFunctions.put("memset", new MemSet());
		libcFunctions.put("strerror", new StrError());
		libcFunctions.put("strlen", new StrLen());
		
		// include <time.h>
		libcFunctions.put("difftime", new DiffTime());
		libcFunctions.put("mktime", new MkTime());
		libcFunctions.put("time", new Time());
		libcFunctions.put("asctime", new AscTime());
		libcFunctions.put("ctime", new CTime());
		libcFunctions.put("gmtime", new GMTime());
		libcFunctions.put("localtime", new LocalTime());
	}
	
	CLibrary(CEnvironment env) {
		this.env = env;
	}

	@Override
	public AbstractFunction getFunction(String name) throws BadAlloc {
		CFunction function = libcFunctions.get(name);
		
		if(function == null) {
			return null;
		}
		
		return new CFunctionCaller(env, function);
	}
	
	@Override
	public Value getGlobal(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LlvmEnvironment getEnvironment() {
		return env;
	}

}
