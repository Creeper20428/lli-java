package libc;

public class CMacro {
	//errno constants
	public static final int EDOM = 33;
	public static final int ERANGE = 34;
	
	public static final int EACCES = 13;
	public static final int ENOENT = 2;
	public static final int EXDEV = 18;
	public static final int EIO = 5;
	
	/** max number of functions to be called at exit */
	public static final int maxAtExitFunctions = 32;
	public static final int L_tmpnam = 20;
	
	// include <locale.h>
	public static final int ALL = 6;
	public static final int COLLATE= 3;
	public static final int CTYPE = 0;
	public static final int MONETARY = 4;
	public static final int NUMERIC = 1;
	public static final int TIME = 2;
	
	// include <signal.h>
	public static final int SIGABRT = 6;
	public static final int SIGFPE = 8;
	public static final int SIGILL = 4;
	public static final int SIGINT = 2;
	public static final int SIGSEGV = 2;
	public static final int SIGTERM = 15;
	
	public static final int SIG_DFL = 0;
	public static final int SIG_IGN = 1;
	
	// include <stdio.h>
	public static final int SEEK_SET = 0;
	public static final int SEEK_CUR = 1;
	public static final int SEEK_END = 2;
	
	public static final int EOF = -1;
	
	// include <time.h>
	public static final int CLOCKS_PER_SEC = 1000000;
	
	// include <limits.h>
	public static final int CHAR_MAX = 127;
}
