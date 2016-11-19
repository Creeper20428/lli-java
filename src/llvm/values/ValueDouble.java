package llvm.values;

import llvm.Value;

public class ValueDouble implements Value {

	private double value;
	
	public ValueDouble(double val) {
		value = val;
	}
	
	public double toDouble() {
		return value;
	}
	
	@Override
	public byte[] toBytes() {
		// TODO Auto-generated method stub
		return null;
	}

}
