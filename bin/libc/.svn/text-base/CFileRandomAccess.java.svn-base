package libc;

import java.io.IOException;
import java.io.RandomAccessFile;

public class CFileRandomAccess extends CFile {
	private RandomAccessFile file;
	
	public CFileRandomAccess(RandomAccessFile file) {
		// TODO Auto-generated constructor stub
		this.file = file;
	}

	@Override
	public void putChar(int ch) throws IOException {
		try {
			file.write(ch);
		} catch (IOException e) {
			error = true;
			throw e;
		}
	}

	@Override
	public int getChar() throws IOException {
		try {
			int c =  file.read();
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
		file.writeBytes(str);
	}

	@Override
	public boolean match(String str) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void ungetc(int c) throws IOException {
		file.seek(file.getFilePointer() - 1);
		file.write(c);
		file.seek(file.getFilePointer() - 1);
		
		eof = false;
	}
	
	@Override
	public void discardUnget() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getPosition() throws IOException {
		return file.getFilePointer();
	}

	@Override
	public void setPosition(long position) throws IOException {
		file.seek(position);
	}

	@Override
	public void write(byte[] data) throws IOException {
		file.write(data);
	}

	@Override
	public byte[] read(int size) throws IOException {
		byte[] data = new byte[size];
		int num = file.read(data);
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
		file.close();
	}
	
	@Override
	public long length() throws IOException {
		return file.length();
	}
}
