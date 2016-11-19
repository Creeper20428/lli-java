package libc;

import java.io.IOException;
import java.io.OutputStream;

public class CFileOutputStream extends CFile {
	OutputStream stream;
	
	public CFileOutputStream(OutputStream os) {
		stream = os;
	}

	private void notRandomAccess() throws IOException {
        throw new IOException("Stream is not random access");
    }

    private void writeonly() throws IOException {
        throw new IOException("Stream is write only");
    }

	@Override
	public void putChar(int ch) throws IOException {
		stream.write(ch);
	}

	@Override
	public int getChar() throws IOException {
		writeonly();
		return 0;
	}

	@Override
	public void putString(String str) throws IOException {
		stream.write(str.getBytes());
	}

	@Override
	public boolean match(String str) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void ungetc(int c) throws IOException {
		writeonly();
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
		stream.write(data);
	}

	@Override
	public byte[] read(int size) throws IOException {
		writeonly();
		return null;
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
