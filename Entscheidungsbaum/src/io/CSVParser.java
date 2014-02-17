package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
	 * @return List of columns(List<String>) of the CSV-File
	 * @throws FileNotFoundException
	 *             if unable to find File
	 * @throws IOException
	 *             if unable to read from File
	 */
	public static List<List<String>> parseFile(File pFile, String pSeparator) throws FileNotFoundException, IOException {
		List<String> lines = Reader.readFile(pFile);

		int listCount = lines.get(0).split(pSeparator).length;
		List<List<String>> result = new ArrayList<List<String>>();

		for (int i = 0; i < listCount; i++) {
			result.add(new ArrayList<String>());
		}

		for (String s : lines) {
			String[] elements = s.split(pSeparator);
			if (elements.length != listCount) {
				throw new IllegalArgumentException();
			}

			for (int i = 0; i < elements.length; i++) {
				result.get(i).add(elements[i]);
			}
		}

		return result;
	}
}
