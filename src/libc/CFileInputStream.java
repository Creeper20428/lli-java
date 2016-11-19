package libc;

import java.io.IOException;
import java.io.InputStream;

public class CFileInputStream extends CFile {
	InputStream stream;
	
	public CFileInputStream(InputStream is) {
		stream = is;
	}

	private void readonly() throws IOException {
		throw new IOException("Stream is read-only.");
	}	
	
	private void notRandomAccess() throws IOException {
		throw new IOException("Stream is not random access");
	}
	
	@Override
	public void putChar(int ch) throws IOException {
		readonly();
	}

	@Override
	public int getChar() throws IOException  {
		try {
			int c = stream.read();
			if(c == -1) {
				eof = true;
			}
			
			return c;
		} catch (IOException e) {
			error = true;
			throw e;
		}
	}

	@Override
	public void putString(String str) throws IOException {
		readonly();
	}

	@Override
	public boolean match(String str) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void ungetc(int c) throws IOException {
		readonly();
	}
	
	@Override
	public void discardUnget() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getPosition() throws IOException {
		notRandomAccess();
		return 0;
	}

	@Override
	public void setPosition(long position) throws IOException {
		notRandomAccess();
	}

	@Override
	public void write(byte[] data) throws IOException {
		readonly();
	}

	@Override
	public byte[] read(int size) throws IOException {
		byte[] data = new byte[size];
		int num = stream.read(data);
		if(num == -1) {
			eof = true;
		}
		
		return data;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws IOException {
		stream.close();
	}
	
	@Override
	public long length() throws IOException {
		notRandomAccess();
		return 0;
	}
}
