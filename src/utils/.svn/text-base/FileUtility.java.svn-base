package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public final class FileUtility {

	public static char[] loadFile(String filename) {
    	final String fileNotFound = "File not found";
    	final String errorWhenClosingFile = "Error when closing the the file";
    	final String magicalShitHappened = "Magical shit happened";
    	
    	BufferedReader bufferedReader = null;
    	char[] buffer = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(filename));
			
			final File file = new File(filename);
	     	final int estimatedLength = (int) file.length();
			buffer = new char[estimatedLength];
			final char[] testBuffer = new char[1];
			
			final int read = bufferedReader.read(buffer, 0, estimatedLength);
			final int minusOne = bufferedReader.read(testBuffer, 0, 1);
			if (read != estimatedLength || minusOne != -1) {
				throw new RuntimeException(magicalShitHappened);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(fileNotFound);
		} catch (IOException e) {
			throw new RuntimeException();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(errorWhenClosingFile);
			}
		}
    	return buffer;
    }
	
}
