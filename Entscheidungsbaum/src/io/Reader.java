package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Allows to read files.
 * 
 * @author Julius
 * 
 */
public final class Reader {

	/**
	 * Reads the given File line by line and returns the result as List of
	 * Strings.
	 * 
	 * @param pFile
	 *            given File
	 * @return result as List of Strings
	 * @throws FileNotFoundException
	 *             if unable to find file
	 * @throws IOException
	 *             if unable to read from file
	 */
	public static List<String> readFile(File pFile) throws FileNotFoundException, IOException {
		List<String> result = new ArrayList<String>();

		try (BufferedReader br = new BufferedReader(new FileReader(pFile))) {
			String line = null;

			while ((line = br.readLine()) != null) {
				result.add(line);
			}
			br.close();
		}

		return result;
	}
}
