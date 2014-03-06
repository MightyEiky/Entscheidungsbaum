package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * 
 * @author Julius
 * 
 */
public class Writer {

	/**
	 * Writes a given List of lines into a given File. Overrides the File if
	 * already exists.
	 * 
	 * @param pFile
	 *            given File
	 * @param pLines
	 *            given List of lines
	 * @throws IOException
	 *             if unable to open file
	 */
	public static void writeFile(File pFile, List<String> pLines) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(pFile, false))) {
			for (String line : pLines) {
				bw.write(line + System.getProperty("line.separator"));
			}
		}
	}
}
