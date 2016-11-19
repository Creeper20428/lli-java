package libc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import llvm.IPointer;
import llvm.LlvmException;
import llvm.Value;
import llvm.values.ValueDouble;
import llvm.values.ValueFloat;
import llvm.values.ValueInteger;
import static libc.CEnvironment.loadString;

public class CFormatter {
	public static String format(String format, Value[] args) throws LlvmException {
		Formatter f = new Formatter();
		Pattern p = Pattern.compile("%([-+#0 ])?(\\*|[0-9]+)?(\\.[0-9]+|\\.\\*)?([hl](?=[idouxX])|l(?=[cs])|L(?=[eEfgG]))?([cdefginopsuxEGX])");
		Matcher m = p.matcher(format);
		
		// convert args
		Object[] convArgs = new Object[args.length];
		int index = 0;
		while(m.find()) {
			String found = m.group();
			char[] chs = found.toCharArray();
			convArgs[index] = convertValue(args[index], chs[chs.length - 2], chs[chs.length - 1]);
			++index;
		}
		
		f.format(format, convArgs);
		return f.toString();
	}
	
	public static String timeFormat(String format, StructTM tm) {
		Formatter f = new Formatter();
		Pattern p = Pattern.compile("%[aAbBcdHIjmMpSUwWxXyYZ]");
		Matcher m = p.matcher(format);
		Calendar cal = tm.toCalendar();
		StringBuffer javaFormat = new StringBuffer(format);
		
		// convert C format string to Java format string
		int i = 0;
		while(m.find()) {
			if(i == 0) {
				javaFormat.insert(m.start() + 1, 't');
				++i;
			} else {
				javaFormat.insert(m.start() + i + 1, "<t");
				i += 2;
			}
		}
		
		f.format(javaFormat.toString(), cal);
		return f.toString();
	}
	
	private static Object convertValue(Value val, final char length, final char specifier) throws LlvmException {
		switch(length) {
			case 'h':
				return ((ValueInteger) val).shortValue();
			case 'l':
				return ((ValueInteger) val).longValue();
			case 'L':
				// TODO: implement long double
			default:		
				switch(specifier) {
				case 'i':
				case 'd':
				case 'u':
				case 'o':
				case 'x':
				case 'X':
					return ((ValueInteger) val).intValue();
				case 'c':
					return ((ValueInteger) val).byteValue();
				case 's':
					return loadString((IPointer) val);
				case 'f':
				case 'e':
				case 'E':
				case 'g':
				case 'G':
					if (val instanceof ValueFloat) {
						return ((ValueFloat) val).toFloat();
					}
					
					if (val instanceof ValueDouble) {
						return ((ValueDouble) val).toDouble();
						
					}
				default:
				}
		}
		
		return null;
	}

}
