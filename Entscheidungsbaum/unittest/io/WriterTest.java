package io;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author Julius
 * 
 */
public class WriterTest {
	/**
	 * Testing method writeFile of Writer class.
	 * 
	 * @throws IOException
	 */
	@Test
	public void writeFileTest() throws IOException {
		List<String> expected = Arrays.asList("Hello World!", "This is ", "a file.");
		File temp = File.createTempFile("tempfile", ".tmp");
		temp.deleteOnExit();
		Writer.writeFile(temp, expected);

		try (BufferedReader br = new BufferedReader(new FileReader(temp))) {
			String line;
			List<String> actual = new ArrayList<String>();
			while ((line = br.readLine()) != null) {
				actual.add(line);
			}

			assertEquals(expected.size(), actual.size());

			for (int i = 0; i < expected.size(); i++) {
				assertEquals(expected.get(i), actual.get(i));
			}
		}
	}
}
