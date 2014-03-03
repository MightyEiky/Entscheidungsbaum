package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Allows to parse CSVFiles.
 * 
 * @author Julius
 * 
 */
public final class CSVParser {

	/**
	 * Allows to parse a given CSV-File by given separator.
	 * 
	 * @param pFile
	 *            given CSV-File
	 * @param pSeparator
	 *            given separator
	 * @return List of rows(List<String>) of the CSV-File
	 * @throws FileNotFoundException
	 *             if unable to find File
	 * @throws IOException
	 *             if unable to read from File
	 */
	public static List<List<String>> parseFile(File pFile, String pSeparator) throws FileNotFoundException, IOException {
		List<String> lines = Reader.readFile(pFile);
		List<List<String>> result = new ArrayList<List<String>>();

		for (String s : lines) {
			result.add(Arrays.asList(s.split(pSeparator)));
		}

		return result;
	}
}
