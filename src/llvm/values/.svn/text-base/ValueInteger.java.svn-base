package llvm.values;

import java.math.BigInteger;

import llvm.Value;

import utils.ConversionUtility;

public final class ValueInteger implements Value {
	
	final BigInteger value;
	
	public ValueInteger(BigInteger value) {
		this.value = value;
	}
	
	public ValueInteger(byte value) {
		this.value = new BigInteger(ConversionUtility.byteToBytes(value));
	}
	
	public ValueInteger(short value) {
		this.value = new BigInteger(ConversionUtility.shortToBytes(value));
	}
	
	public ValueInteger(int value) {
		this.value = new BigInteger(ConversionUtility.intToBytes(value));
	}
	
	public ValueInteger(long value) {
		this.value = new BigInteger(ConversionUtility.longToBytes(value));
	}
	
	public ValueInteger(byte[] value) {
		this.value = new BigInteger(value);
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
	
	public byte byteValue() {
		return value.byteValue();
	}
	
	public short shortValue() {
		return value.shortValue();
	}
	
	public int intValue() {
		return value.intValue();
	}
	
	public long longValue() {
		return value.longValue();
	}

	@Override
	public byte[] toBytes() {
		return value.toByteArray();
	}

}
