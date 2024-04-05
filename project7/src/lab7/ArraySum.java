package lab7;

public class ArraySum {
	/**
	 * Try it out.
	 */
	public static void main(String[] args) {
		int[] test = { 3, 4, 5, 1, 2, 3, 2 }; // sum should be 20
		int result = arraySum(test);
		System.out.println(result);

		// should be 5
		int[] test2 = { 37, 4, 5, 1, 2, 359, 2 };
		int maxResult = arrayMax(test2);
		System.out.println(maxResult);

		System.out.println(getPyramidCount(2));// 5
		System.out.println(getPyramidCount(7));// 5
	}

	/**
	 * Returns the sum of all array elements.
	 */
	public static int arraySum(int[] arr) {
		return arraySumRec(arr, 0, arr.length - 1);
	}

	/**
	 * Returns the sum of array elements from start to end, inclusive.
	 */
	private static int arraySumRec(int[] arr, int start, int end) {
		if (start == end) {
			return arr[start];
		} else {
			int mid = (start + end) / 2;
			int leftSum = arraySumRec(arr, start, mid);
			int rightSum = arraySumRec(arr, mid + 1, end);
			return leftSum + rightSum;
		}
	}

	private static int arrayMax(int[] arr) {

		return findMaxValue(arr, 0, arr.length - 1);
	}

	/**
	 * Checkpoint 1 part 1 Write a method that uses a divide-and-conquer strategy to
	 * find the maximum value in an integer array. The maximum value of a
	 * one-element array is that element. The maximum of any other array is the
	 * maximum of the left half, or the maximum of the right half, whichever is
	 * larger. This is similar to ArraySum.
	 */
	private static int findMaxValue(int[] arr, int start, int end) {
		
		if (start == end) {
			return arr[start];
		} else {
			int mid = (start + end) / 2;
			int leftMax = findMaxValue(arr, start, mid);
			int rightMax = findMaxValue(arr, mid + 1, end);
			if (leftMax > rightMax) {
				return leftMax;

			} else {
				return rightMax;
			}
		}

	}

	/**
	 * Checkpoint 1 part 2
	 * Each level in the pyramid is a square, so if there are n levels, the bottom
	 * level has n * n balls, and the total number of balls is just (n * n) +
	 * (number of balls in a pyramid of height n - 1).
	 * 
	 * There is just one ball in a pyramid of height 1. Write a static recursive
	 * method getPyramidCount that takes a single int argument representing the
	 * number of levels in a pyramid, and returns the total number of balls.
	 * 
	 * @param numLevels
	 * @return
	 */

	private static int getPyramidCount(int numLevels) {

		int total = 0;
		if (numLevels == 1) {
			return 1;
		} else {
			total = (numLevels * numLevels) + (getPyramidCount(numLevels - 1));

		}
		return total;
	}

}