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
		List<List<String>> expected = Arrays.asList(Arrays.asList("Geschlecht", "m", "w", "w", "m", "w"),
				Arrays.asList("Raucherstatus", "y", "n", "y", "y", "n"));

		String separator = ";";
		String lineSeparator = System.getProperty("line.separator");
		File temp = File.createTempFile("tempfile", ".tmp");
		BufferedWriter bw = new BufferedWriter(new FileWriter(temp));

		int expectedColCount = expected.size();
		int expectedRowCount = expected.get(0).size();

		for (int i = 0; i < expectedRowCount; i++) {
			for (int j = 0; j < expectedColCount; j++) {
				bw.write(expected.get(j).get(i) + separator);
			}
			bw.write(lineSeparator);
		}

		bw.close();

		List<List<String>> actual = CSVParser.parseFile(temp);
		assertNotNull(actual);

		int actualColCount = actual.size();
		int actialRowCount = actual.get(0).size();

		assertEquals(expectedColCount, actualColCount);
		assertEquals(expectedRowCount, actialRowCount);

		for (int col = 0; col < expectedColCount; col++) {
			for (int row = 0; row < expectedRowCount; row++) {
				assertEquals(expected.get(col).get(row), actual.get(col).get(row));
			}
		}
	}
}
