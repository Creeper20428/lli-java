package libc;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import utils.ConversionUtility;

import llvm.BadAlloc;
import llvm.IMemory;
import llvm.IPointer;
import llvm.MemoryAccessViolation;

public class LConv {
	public final int size;
	
	public final IPointer cLocale;
	public final IPointer defaultLocale;
	
	// string constants
	public final IPointer emptyString;
	public final IPointer dotString;
	
	// offsets 
	public final int decimal_point;
	public final int thousands_sep;
	public final int grouping;
	public final int int_curr_symbol;
	public final int currency_symbol;
	public final int mon_decimal_point;
	public final int mon_thousands_sep;
	public final int mon_grouping;
	public final int positive_sign;
	public final int negative_sign;
	public final int int_frac_digits;
	public final int frac_digits;
	public final int p_cs_precedes;
	public final int n_cs_precedes;
	public final int p_sep_by_space;
	public final int n_sep_by_space;
	public final int p_sign_posn;
	public final int n_sign_posn;
	
	private IPointer address;
	private Map<Integer, IPointer> categories;
	
	private static final byte[] CHAR_MAX_BYTE_ARRAY = new byte[] { CMacro.CHAR_MAX };
	
	public LConv(IMemory memory) throws BadAlloc {
		final int ptrSize = memory.pointerSize();
		final int byteSize = 8;
		
		// init offsets
		decimal_point = 0;
		thousands_sep = decimal_point + ptrSize;
		grouping = thousands_sep + ptrSize;
		int_curr_symbol = grouping + ptrSize;
		currency_symbol = int_curr_symbol + ptrSize;
		mon_decimal_point = currency_symbol + ptrSize;
		mon_thousands_sep = mon_decimal_point + ptrSize;
		mon_grouping = mon_thousands_sep + ptrSize;
		positive_sign = mon_grouping + ptrSize;
		negative_sign = positive_sign + ptrSize;
		
		int_frac_digits = negative_sign + byteSize;
		frac_digits = int_frac_digits + byteSize;
		p_cs_precedes = frac_digits + byteSize;
		n_cs_precedes = p_cs_precedes + byteSize;
		p_sep_by_space = n_cs_precedes + byteSize;
		n_sep_by_space = p_sep_by_space + byteSize;
		p_sign_posn = n_sep_by_space + byteSize;
		n_sign_posn = p_sign_posn + byteSize;
		
		size = n_sign_posn + 8;
		
		categories = new HashMap<Integer, IPointer>();
		
		address = memory.allocate(size);
		cLocale = memory.allocate(2);
		defaultLocale = memory.allocate(1);
		
		emptyString = memory.allocate(1);
		dotString = memory.allocate(2);
		
		try {
			cLocale.store(0, ConversionUtility.stringToBytes("C"));
			defaultLocale.store(0, ConversionUtility.stringToBytes(""));
			
			emptyString.store(0, ConversionUtility.stringToBytes(""));
			dotString.store(0, ConversionUtility.stringToBytes("."));
			
			setLocale(CMacro.ALL, cLocale);
		} catch (MemoryAccessViolation e) {
			// should't happen
			e.printStackTrace();
		}
	}
	
	public IPointer getAddress() {
		return address;
	}
	
	public IPointer setLocale(int category, IPointer locale) throws MemoryAccessViolation {
		if(locale.isNull()) {
			return categories.get(category);
		}
		
		final String loc = CEnvironment.loadString(locale);
		switch(category) {
		case CMacro.ALL:
			setAll(loc);
			break;
		case CMacro.MONETARY:
			setMonetary(loc);
			break;
		case CMacro.NUMERIC:
			setNumeric(loc);
			break;
		}
		
		return categories.put(category, locale);
	}
	
	@Deprecated
	public Locale getLocale() {
		// TODO: implement
		return null;
	}
	
	private void setAll(String locale) throws MemoryAccessViolation {
		if(locale.equals("C")) {
			address.store(decimal_point, dotString.toBytes());
			address.store(thousands_sep, emptyString.toBytes());
			address.store(grouping, emptyString.toBytes());
			address.store(int_curr_symbol, emptyString.toBytes());
			address.store(currency_symbol, emptyString.toBytes());
			address.store(mon_decimal_point, emptyString.toBytes());
			address.store(mon_thousands_sep, emptyString.toBytes());
			address.store(mon_grouping, emptyString.toBytes());
			address.store(positive_sign, emptyString.toBytes());
			address.store(negative_sign, emptyString.toBytes());
			
			address.store(int_frac_digits, CHAR_MAX_BYTE_ARRAY );
			address.store(frac_digits, CHAR_MAX_BYTE_ARRAY );
			address.store(p_cs_precedes, CHAR_MAX_BYTE_ARRAY );
			address.store(n_cs_precedes, CHAR_MAX_BYTE_ARRAY );
			address.store(p_sep_by_space, CHAR_MAX_BYTE_ARRAY );
			address.store(n_sep_by_space, CHAR_MAX_BYTE_ARRAY );
			address.store(p_sign_posn, CHAR_MAX_BYTE_ARRAY );
			address.store(n_sign_posn, CHAR_MAX_BYTE_ARRAY );
		} else if(locale.equals("")) {
			// TODO: implement
		}
	}
	
	private void setMonetary(String locale) throws MemoryAccessViolation {
		if(locale.equals("C")) {
			address.store(int_curr_symbol, emptyString.toBytes());
			address.store(currency_symbol, emptyString.toBytes());
			address.store(mon_decimal_point, emptyString.toBytes());
			address.store(mon_thousands_sep, emptyString.toBytes());
			address.store(mon_grouping, emptyString.toBytes());
			address.store(positive_sign, emptyString.toBytes());
			address.store(negative_sign, emptyString.toBytes());
			
			address.store(int_frac_digits, CHAR_MAX_BYTE_ARRAY );
			address.store(frac_digits, CHAR_MAX_BYTE_ARRAY );
			address.store(p_cs_precedes, CHAR_MAX_BYTE_ARRAY );
			address.store(n_cs_precedes, CHAR_MAX_BYTE_ARRAY );
			address.store(p_sep_by_space, CHAR_MAX_BYTE_ARRAY );
			address.store(n_sep_by_space, CHAR_MAX_BYTE_ARRAY );
			address.store(p_sign_posn, CHAR_MAX_BYTE_ARRAY );
			address.store(n_sign_posn, CHAR_MAX_BYTE_ARRAY );
		} else if(locale.equals("")) {
			// TODO: implement
		}
	}
	
	private void setNumeric(String locale) throws MemoryAccessViolation {
		if(locale.equals("C")) {
			address.store(decimal_point, dotString.toBytes());
			address.store(thousands_sep, emptyString.toBytes());
			address.store(grouping, emptyString.toBytes());
		} else if(locale.equals("")) {
			// TODO: implement
		}
	}
}
