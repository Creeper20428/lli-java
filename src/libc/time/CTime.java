package libc.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import utils.ConversionUtility;

import libc.CEnvironment;
import libc.CFunction;
import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;

public class CTime extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		IPointer timerPtr = (IPointer) args[0];

		long time = ConversionUtility.bytesToLong(timerPtr.load(0, 8));
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
	    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy", Locale.US);
	    String str = sdf.format(cal.getTime());
	    env.timeStringPtr.store(0, ConversionUtility.stringToBytes(str));
		
        return env.timeStringPtr;
	}

}
