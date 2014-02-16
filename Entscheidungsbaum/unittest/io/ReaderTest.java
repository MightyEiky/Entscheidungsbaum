package io;

import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Testing Reader class.
 * 
 * @author Julius
 * 
 */
public class ReaderTest {
	/**
	 * Testing method readFile of Reader class.
	 * 
	 * @throws IOException
	 */
	@Test
	public void readFileTest() throws IOException {
		List<String> expected = Arrays.asList("This is", "some", "test content.");

		File temp = File.createTempFile("tempfile", ".tmp");

		BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
		for (String s : expected) {
			bw.write(s + System.getProperty("line.separator"));
		}

		bw.close();

		List<String> actual = Reader.readFile(temp);

		for (String t : expected) {
			assertTrue("Expected [" + t + "] in " + actual, actual.contains(t));
		}

		temp.delete();
	}
}
