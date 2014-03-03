package io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Testing CSVParser class.
 * 
 * @author Julius
 * 
 */
public class CSVParserTest {
	/**
	 * Testing method parseFile of CSV class.
	 * 
	 * @throws IOException
	 */
	@Test
	public void parseFileTest() throws IOException {
		//@formatter:off
		List<List<String>> expected = Arrays.asList(
				Arrays.asList("Geschlecht", "Raucherstatus"),
				Arrays.asList("m", "n"),
				Arrays.asList("w", "y"));
		//@formatter:on

		String separator = ";";
		String lineSeparator = System.getProperty("line.separator");
		File temp = File.createTempFile("tempfile", ".tmp");
		BufferedWriter bw = new BufferedWriter(new FileWriter(temp));

		int expectedColCount = expected.get(0).size();
		int expectedRowCount = expected.size();

		for (List<String> row : expected) {
			for (int i = 0; i < row.size(); i++) {
				if (i != 0) {
					bw.write(separator);
				}
				bw.write(row.get(i));
			}
			bw.write(lineSeparator);
		}

		bw.close();

		List<List<String>> actual = CSVParser.parseFile(temp, separator);
		assertNotNull(actual);

		int actualColCount = actual.get(0).size();
		int actialRowCount = actual.size();

		assertEquals(expectedColCount, actualColCount);
		assertEquals(expectedRowCount, actialRowCount);

		for (int i = 0; i < actual.size(); i++) {
			assertEquals(expected.get(i), actual.get(i));
		}
	}
}
