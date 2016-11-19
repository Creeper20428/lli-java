package libc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public abstract class CFile {
	protected boolean eof = false;
	protected boolean error = false;
	
	public boolean eof() {
		return eof;
	}
	
	public boolean hasError() {
		return error;
	}
	
	public void clearError() {
		error = false;
	}
	
	public void clearEOF() {
		eof = false;
	}
	
	public abstract void putChar(int ch) throws IOException;
	public abstract int getChar() throws IOException;
	public abstract void putString(String str) throws IOException;
	public abstract boolean match(String str);
	public abstract void ungetc(int c) throws IOException;
	public abstract void discardUnget();
	
	public abstract long getPosition() throws IOException;
	public abstract void setPosition(long position) throws IOException;
	
	public abstract void write(byte[] data) throws IOException;
	public abstract byte[] read(int size) throws IOException;
	
	public abstract void flush();
	public abstract void close() throws IOException;
	
	public abstract long length() throws IOException;
	
	public String gets() throws IOException {
		String result = "";
		while(true) {
			int c = getChar();
			if(c == -1 || c == '\n') {
				break;
			}
			
			result += c;
		}
		
		return result;
	}
	
	public String fgets(int num) throws IOException {
		StringBuffer result = new StringBuffer(num);
        for (int length = 0; length < num; length++) {
            int c = getChar();
            if (c == -1) {
                break;
            }
            result.append((char) c);
            if (c == '\n' || c == '\r') {
                break;
            }
        }
        
        return result.toString();
	}
}
