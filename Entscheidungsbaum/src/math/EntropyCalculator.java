package math;

import java.util.Set;

/**
 * Calculates entropy.
 * 
 * @author Julius
 * 
 */
public class EntropyCalculator {

	/**
	 * Calculates the entropy for a given Set of probabilities.
	 * 
	 * @param pProbabilities
	 *            Set of probabilities
	 * @return double containing the entropy, or -1 if the Set is empty
	 */
	public static double calculateEntropy(Set<Double> pProbabilities) {
		double result = 0;

		if (pProbabilities.size() == 0) {
			return -1;
		}

		for (Double d : pProbabilities) {
			result += (-1 * d) * Math.log(d);
		}

		return result;
	}
}
