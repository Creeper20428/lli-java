package libc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import utils.ConversionUtility;

public class StructTM {
	/**
	 *  the size of data in bytes
	 */
	public static final int size = 9*4;
	
	private byte[] data;
	private Calendar calendar;
	
	public StructTM(byte[] data) {
		this.data = data;
		byte[] tm_sec_data = new byte[4];
		byte[] tm_min_data = new byte[4];
		byte[] tm_hour_data = new byte[4];
		byte[] tm_mday_data = new byte[4];
		byte[] tm_mon_data = new byte[4];
		byte[] tm_year_data = new byte[4];
		byte[] tm_isdst_data = new byte[4];
		
		System.arraycopy(data, 0, tm_sec_data, 0, 4);
		System.arraycopy(data, 4*1, tm_min_data, 0, 4);
		System.arraycopy(data, 4*2, tm_hour_data, 0, 4);
		System.arraycopy(data, 4*3, tm_mday_data, 0, 4);
		System.arraycopy(data, 4*4, tm_mon_data, 0, 4);
		System.arraycopy(data, 4*5, tm_year_data, 0, 4);
		System.arraycopy(data, 4*8, tm_isdst_data, 0, 4);
		
		// init calendar
		calendar = Calendar.getInstance();
		int tm_sec = ConversionUtility.bytesToInt(tm_sec_data);
		int tm_min = ConversionUtility.bytesToInt(tm_min_data);
		int tm_hour = ConversionUtility.bytesToInt(tm_hour_data);
		int tm_mday = ConversionUtility.bytesToInt(tm_mday_data);
		int tm_mon = ConversionUtility.bytesToInt(tm_mon_data);
		int tm_year = ConversionUtility.bytesToInt(tm_year_data);
		
		calendar.set(tm_year, tm_mon, tm_mday, tm_hour, tm_min, tm_sec);
		int tm_wday  = calendar.get(Calendar.DAY_OF_WEEK);
		int tm_yday  = calendar.get(Calendar.DAY_OF_YEAR);
		int tm_isdst = Math.abs(calendar.get(Calendar.DST_OFFSET));
		System.arraycopy(ConversionUtility.intToBytes(tm_wday), 0, data, 4*6, 4);
		System.arraycopy(ConversionUtility.intToBytes(tm_yday), 0, data, 4*7, 4);
		System.arraycopy(ConversionUtility.intToBytes(tm_isdst), 0, data, 4*9, 4);
	}
	
	public StructTM(Calendar calendar) {
		this.calendar = calendar;
		int tm_sec 	 = calendar.get(Calendar.SECOND);
		int tm_min 	 = calendar.get(Calendar.MINUTE);
		int tm_hour  = calendar.get(Calendar.HOUR_OF_DAY);
		int tm_mday  = calendar.get(Calendar.DAY_OF_MONTH);
		int tm_mon 	 = calendar.get(Calendar.MONTH);
		int tm_year  = calendar.get(Calendar.YEAR) - 1900;
		int tm_wday  = calendar.get(Calendar.DAY_OF_WEEK);
		int tm_yday  = calendar.get(Calendar.DAY_OF_YEAR);
		int tm_isdst = Math.abs(calendar.get(Calendar.DST_OFFSET));
		
		// init data
		data = new byte[size];
		System.arraycopy(ConversionUtility.intToBytes(tm_sec), 0, data, 0, 4);
		System.arraycopy(ConversionUtility.intToBytes(tm_min), 0, data, 4*1, 4);
		System.arraycopy(ConversionUtility.intToBytes(tm_hour), 0, data, 4*2, 4);
		System.arraycopy(ConversionUtility.intToBytes(tm_mday), 0, data, 4*3, 4);
		System.arraycopy(ConversionUtility.intToBytes(tm_mon), 0, data, 4*4, 4);
		System.arraycopy(ConversionUtility.intToBytes(tm_year), 0, data, 4*5, 4);
		System.arraycopy(ConversionUtility.intToBytes(tm_wday), 0, data, 4*6, 4);
		System.arraycopy(ConversionUtility.intToBytes(tm_yday), 0, data, 4*7, 4);
		System.arraycopy(ConversionUtility.intToBytes(tm_isdst), 0, data, 4*9, 4);
	}
	
	public long toTime_t() {
		return calendar.getTimeInMillis();
	}
	
	public Calendar toCalendar() {
		return calendar;
	}
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy", Locale.US);
	    return sdf.format(calendar.getTime());
	}
	
	public byte[] getData() {
		return data;
	}
}
