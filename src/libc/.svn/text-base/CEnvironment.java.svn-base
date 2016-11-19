package libc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import defaultmemory.DefaultMemory;

import utils.ConversionUtility;

import llvm.AbstractFunction;
import llvm.BadAlloc;
import llvm.IMemory;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.MemoryAccessViolation;
import llvm.Value;
import llvm.environment.LlvmEnvironment;
import llvm.values.ValueInteger;

/**
 * 
 * @author
 * 
 */
public final class CEnvironment extends LlvmEnvironment {
	
	private final IPointer evironmentString;
	private Random randomGenerator;
	private Stack<AbstractFunction> atexitFunctions;
	private Map<Long, CFile> openedStreams;
	private Map<Integer, AbstractFunction> signalHandlers;
	private Map<Long, JmpBuf>jmpBuffers;
	
	private static final int maxAtexitFunctions = 32;
	
	public final IPointer errnoLocation;
	public final IPointer internalTmpnam;
	public final IPointer timeStringPtr;
	public final IPointer structTMPointer;
	
	public final LConv localeconv;
	
	public final IPointer stdin;
	public final IPointer stdout;
	public final IPointer stderr;

	public final AbstractFunction defaultHandler;
	public final AbstractFunction ignoreHandler;
	
	public final long startTime;
	
	public CEnvironment() throws BadAlloc {
		this(new DefaultMemory());
	}
	
	public CEnvironment(IMemory memory) throws BadAlloc {
		super(memory);
		
		evironmentString = memory.allocate(42);
		randomGenerator = new Random();
		errnoLocation = memory.allocate(4);
		
		stdin = memory.allocate(1);
		stdout = memory.allocate(1);
    	stderr = memory.allocate(1);
    	
    	localeconv = new LConv(memory);
    	
    	defaultHandler = new AbstractFunction(memory) {
			
			@Override
			public Value call(Value... args) throws LlvmException {
				return null;
			}
		};
		
		ignoreHandler = new AbstractFunction(memory) {
			
			@Override
			public Value call(Value... args) throws LlvmException {
				return null;
			}
		};
		
    	jmpBuffers = new HashMap<Long, JmpBuf>();
    	internalTmpnam = memory.allocate(CMacro.L_tmpnam);
    	timeStringPtr  = getMemory().allocate(42);
    	
    	structTMPointer = memory.allocate(StructTM.size);
		startTime = System.currentTimeMillis();
		
		initSignal();
		initIO();
	}
	
	public CLibrary getFunctionFactory() {
		return new CLibrary(this);
	}
	
	public static String loadString(IPointer ptr) throws MemoryAccessViolation {
		int size = ptr.getSize();
		byte[] data = ptr.load(0, size);
		
		int nullIndex = 0;
		while(data[nullIndex] != 0) {
			++nullIndex;
			if(nullIndex > size) {
				throw new MemoryAccessViolation();
			}
		}
		
		byte[] str = new byte[nullIndex];
		System.arraycopy(data, 0, str, 0, nullIndex);
		return ConversionUtility.bytesToString(str);
	}
	
	@Deprecated
	public void abort() {
    	// TODO: flush output streams
		// TODO: send SIGABRT signal
    }
	
	@Deprecated
	public void exit(int status) throws LlvmException {
    	while(!atexitFunctions.empty()) {
    		AbstractFunction function = atexitFunctions.pop();
			function.call();
			
			// TODO: flush output streams
			
			if(status == 0) {
				// TODO: add exit without errors
			}
			
			// TODO: add exit with errors
    	}
    }
	
	public int atexit(AbstractFunction function) {
    	if(atexitFunctions.capacity() > maxAtexitFunctions) {
    		return 1;
    	}
    	
    	atexitFunctions.push(function);
    	return 0;
    }
	
	public int system(String command) {
		try {
			Process p = Runtime.getRuntime().exec(command);
			p.waitFor(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} 

		return 0;
	}
	
	public IPointer getEnv(String name) throws MemoryAccessViolation {
		String property = System.getProperty(name);
		if(property == null) {
			return getMemory().nullPointer();
		}
		
		evironmentString.store(0, ConversionUtility.stringToBytes(property));
		return evironmentString;
	}

	public int rand() {
    	return randomGenerator.nextInt();
    }
    
    public void setSeed(long seed) {
    	randomGenerator.setSeed(seed);
    }
    
    public void setErrno(int val) {
    	byte[] bytes = ConversionUtility.intToBytes(val);
    	try {
			errnoLocation.store(0, bytes);
		} catch (MemoryAccessViolation e) {
			// shouldn't happen
			e.printStackTrace();
		}
    }
    
    public int getErrno() {
    	byte[] bytes;
		try {
			bytes = errnoLocation.load(0, 4);
			return ConversionUtility.bytesToInt(bytes);
		} catch (MemoryAccessViolation e) {
			// shouldn't happen
			e.printStackTrace();
			return 0;
		}
    }
    
    @Deprecated
    public IPointer errorMessage(int error) {
    	return null;
    }
    
    public int raise(int signal) throws LlvmException {
    	AbstractFunction function = signalHandlers.get(signal);
    	if(function != null) {
			function.call(new ValueInteger(signal));
    	}
    	
    	return 0;
    }
    
    public AbstractFunction signal(int signal, AbstractFunction function) {
    	AbstractFunction result = signalHandlers.get(signal);
    	signalHandlers.put(signal, function);
    	return result;
    }
    
    public int setJump(IPointer env) {
    	JmpBuf buf = jmpBuffers.get(env.toLong());
    	if(buf == null || !buf.getFlag()) {
    		JmpBuf newBuf = new JmpBuf(getStack().getInstructionPointer());
    		jmpBuffers.put(env.toLong(), newBuf);
    		return 0;
    	}
    	
    	buf.setFlag(false);
		return buf.getVal();
    }
    
    public void longJump(IPointer env, int val) {
    	JmpBuf buf = jmpBuffers.get(env.toLong());
    	if(buf == null) {
    		// TODO: log warning
    		return;
    	}
    	
    	buf.setVal(val);
    	buf.setFlag(true);
    	getStack().goTo(buf.instruction);
    }
    
    public IPointer fopen(String path, String mode) throws FileNotFoundException {
    	CFile file = new CFileRandomAccess(new RandomAccessFile(path, mode));
    	IPointer filePointer = null;
    	try {
			filePointer = getMemory().allocate(0);
			openedStreams.put(filePointer.toLong(), file);
		} catch (BadAlloc e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	return filePointer;
    }
    
    public void freopen(String path, String mode, IPointer filePointer) throws IOException {
    	fclose(filePointer);
    	CFile file = new CFileRandomAccess(new RandomAccessFile(path, mode));
    	openedStreams.put(filePointer.toLong(), file);
    }
    
    public CFile getFile(IPointer filePointer) throws IOException {
    	CFile file = openedStreams.get(filePointer.toLong());
    	if(file == null) {
    		throw new IOException("File is not opened");
    	}
    	return file;
    }
    
    public void fclose(IPointer filePointer) throws IOException {
    	CFile file = openedStreams.get(filePointer.toLong());
    	file.close();
    	openedStreams.remove(filePointer.toLong());
    }
    
    private void initSignal() {
    	signalHandlers = new HashMap<Integer, AbstractFunction>();
    	
		signalHandlers.put(CMacro.SIGABRT, defaultHandler);
		signalHandlers.put(CMacro.SIGFPE, defaultHandler);
		signalHandlers.put(CMacro.SIGILL, defaultHandler);
		signalHandlers.put(CMacro.SIGINT, defaultHandler);
		signalHandlers.put(CMacro.SIGTERM, defaultHandler);
    }
    
    private void initIO() {
    	openedStreams = new HashMap<Long, CFile>();
    	
	    openedStreams.put(stdin.toLong(), new CFileInputStream(System.in));
	    openedStreams.put(stdout.toLong(), new CFileOutputStream(System.out));
	    openedStreams.put(stderr.toLong(), new CFileOutputStream(System.err));
    	
    	//TODO:  store pointers in extern registers;
    }
    
}
