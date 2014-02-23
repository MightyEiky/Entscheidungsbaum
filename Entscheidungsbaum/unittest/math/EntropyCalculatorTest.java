package math;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Testing EntropyCalculator class.
 * 
 * @author Julius
 * 
 */
public class EntropyCalculatorTest {

	/**
	 * Testing calculateEntropy method of EntropyCalculator class.
	 */
	@Test
	public void calculateEntropyTest() {
		// testing method using empty Set
		double minusOne = EntropyCalculator.calculateEntropy(new HashSet<Double>());
		assertTrue("Expected -1, got " + minusOne + ".", minusOne == -1);

		// testing method using regular parameters
		double testValue1 = 6d / 11d;
		double testValue2 = 5d / 11d;

		double expected = (-1 * testValue1) * Math.log(testValue1) + (-1 * testValue2) * Math.log(testValue2);

		Set<Double> params = new HashSet<Double>();
		params.add(testValue1);
		params.add(testValue2);

		double actual = EntropyCalculator.calculateEntropy(params);

		assertTrue("Expected " + expected + ", got " + actual + ".", expected == actual);
	}
}
