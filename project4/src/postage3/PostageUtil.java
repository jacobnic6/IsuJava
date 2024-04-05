package postage3;

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

		// flowchart 3 runs as expected

		costToShip = .47;
		if (weight > 1) {
			costToShip = costToShip + Math.ceil(weight - 1) * COST_PER_EXTRA_OZ;

		}
		if (weight > 3.5) {
			costToShip = costToShip + .47;
		}

		return costToShip;

	}
}