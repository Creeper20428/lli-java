package llvm.values;

import utils.ConversionUtility;
import llvm.Value;

public class ValueFloat implements Value {

	private float value;
	
	public ValueFloat(float val) {
		value = val;
	}
	
	public float toFloat() {
		return value;
	}
	
	@Override
	public byte[] toBytes() {
		return ConversionUtility.floatToBytes(value);
	}

}
