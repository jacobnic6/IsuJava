package postage2;

import java.text.DecimalFormat;

public class PostageUtil {
	/**
	 * Returns the cost of postage for a letter of the given weight.
	 * 
	 * @param weight given weight in ounces
	 * @return cost of postage for the weight
	 */
	public static double computePostage(double weight) {

		final double COST_PER_EXTRA_OZ = 0.21;
		final double SMALL_LETTER_COST = 0.47;
		final double LARGE_LETTER_COST = 0.94;
		double costToShip;

		// Flowchart 2 if weight is > 3.5 then it has to be > 1. last block is
		// unreachable any value > 3.5 oz returns incorrect

		if (weight <= 1) {
			costToShip = .47;
		} else {
			if (weight > 1) {
				costToShip = SMALL_LETTER_COST + Math.ceil(weight - 1.0) * COST_PER_EXTRA_OZ;
			} else {
				if (weight > 3.5) {
					costToShip = LARGE_LETTER_COST + Math.ceil(weight - 1.0) * COST_PER_EXTRA_OZ;
				} else {
					costToShip = SMALL_LETTER_COST + Math.ceil(weight - 1.0) * COST_PER_EXTRA_OZ;
				}
			}

		}

		return costToShip;

	}
}