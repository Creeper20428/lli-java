package libc.time;

import java.util.Calendar;

import libc.CEnvironment;
import libc.CFunction;
import libc.StructTM;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueInteger;

public class LocalTime extends CFunction {

	@Override
	public Value call(CEnvironment env, Value ... args) throws LlvmException {
		long time = ((ValueInteger)args[0]).longValue();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		
		StructTM tm = new StructTM(cal);
		byte[] data = tm.getData();
		
		env.structTMPointer.store(0, data);
		
		return env.structTMPointer;
	}

}
