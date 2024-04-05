
package hw2;

/**
 * Models a simplified baseball-like game called Fuzzball.
 * 
 * @author Nicholas Jacobs
 * @version 1.0
 */
public class FuzzballGame {
	/**
	 * Number of strikes causing a player to be out.
	 */
	public static final int MAX_STRIKES = 2;

	/**
	 * Number of balls causing a player to walk.
	 */
	public static final int MAX_BALLS = 5;

	/**
	 * Number of outs before the teams switch.
	 */
	public static final int MAX_OUTS = 3;

	/**
	 * Current number of balls in the current batter's count. Starts at 0 for each
	 * batter.
	 */
	private int ballCount;
	/**
	 * Current number of strikes in the current batter's count. Starts at 0 for each
	 * batter.
	 */
	private int strikeCount;
	/**
	 * Current number of outs the the team at bat has. Starts at 0 each time a new
	 * team goes to bat.
	 */
	private int outCount;
	/**
	 * Current inning of the game
	 */
	private int currentInning;
	/**
	 * Current score for team 0. (First to bat)
	 */
	private int team0Score;
	/**
	 * Current score for team 1. (Last to bat)
	 */
	private int team1Score;

	/**
	 * Given amount of innings in the game. Each inning has a top and bottom of the
	 * inning.
	 */
	private int gameLength;
	/**
	 * Variable to track whether it is the top of the inning or not. Set to true
	 * upon construction.
	 */
	private boolean isTopOfInning;

	/**
	 * String tracking the current position of baserunners. "ooo" - no runners on
	 * base "XXX" - runners on 1st, 2nd, and 3rd
	 */
	private String baseRunnerPosition;

	/**
	 * Constructs a game that has the given number of innings and starts at the top
	 * of inning 1.
	 * 
	 * @param givenNumInnings
	 */
	public FuzzballGame(int givenNumInnings) {
		this.gameLength = givenNumInnings;
		this.isTopOfInning = true;
		this.currentInning = 1;
		this.baseRunnerPosition = "ooo";

	}

	/**
	 * Method called to indicate a strike for the current batter. If the swung
	 * parameter is true, the batter is immediately out. Otherwise, 1 is added to
	 * the batters current count of called strikes (possibly resulting in the batter
	 * being out).
	 * 
	 * @param swung
	 */
	public void strike(boolean swung) {
		if (!gameEnded()) {
			if (swung == true) {
				updateOuts();

			} else {
				strikeCount += 1;
				if (strikeCount % MAX_STRIKES == 0) {
					updateOuts();

				}
			}
		}
	}

	/**
	 * Helper method to update the outs of the game. Calls for a new pitch count and
	 * adds one to the current out count. If the current number of outs is now equal
	 * to the maximum amount of outs, update inning and set out count to 0.
	 * 
	 * @see #newCount()
	 * @see #updateInning()
	 */
	private void updateOuts() {
		newCount();
		outCount += 1;
		if (outCount % MAX_OUTS == 0) {
			updateInning();
			outCount = 0;
		}
	}

	/**
	 * Helper method to update the inning of the game. If isTopOfInning() == true
	 * ,set Top of inning to false. Otherwise it is currently the bottom of the
	 * inning so we change it to true and add 1 to the current inning count.
	 * 
	 * @see #isTopOfInning()
	 */
	private void updateInning() {
		baseRunnerPosition = "ooo";

		if (isTopOfInning()) {
			isTopOfInning = false;
		} else {
			isTopOfInning = true;
			currentInning += 1;

		}
	}

	/**
	 * Helper method to reset the strike and ball count of the batter to zero.
	 * Called any time a new batter is at bat.
	 */
	private void newCount() {
		strikeCount = 0;
		ballCount = 0;
	}

	/**
	 * Method called to indicate a bad pitch at which the batter did not swing. This
	 * method adds 1 the the batter's count of balls, possibly resulting in a walk.
	 * This method does nothing if the game has ended.
	 * 
	 * @see #gameEnded()
	 * @see #newCount()
	 * @see #runnerOnBase(int)
	 * @see #advanceBases(int)
	 */
	public void ball() {
		// if game is not over
		if (!gameEnded()) {
			String temp = "X"; // Temporary String variable to serve as the current batter if he gets walked.
			ballCount += 1;
			// if remainder of ballCount / MAX_BALLS == 0 then the batter is walked.
			if (ballCount % MAX_BALLS == 0) {

				newCount();
				// Checks if there is currently a runner on 1st base
				if (baseRunnerPosition.indexOf('X') != 0) {
					// if runner is not on first, update baseRunnerPosition to have a runner on
					// first without advancing other runners.
					baseRunnerPosition = temp + baseRunnerPosition.substring(1);
				} else {
					// if there is a runner on 1st and 3rd but not on second, advance runner from
					// 1st -> 2nd and 3rd stays put.
					if (!runnerOnBase(2) && runnerOnBase(3)) {
						baseRunnerPosition = "XXX";

					} else {
						// call to advance bases, as all runners will be forced to advance with a walk.
						advanceBases(1);
					}
				}

			}
		}
	}

	/**
	 * Method called to indicate that the batter hit the ball. The interpretation of
	 * the distance parameter is as follows: - Less than 15: the hit is a foul and
	 * the batter is immediately out. at least 15, but less than 150: the hit is a
	 * single. An imaginary runner advances to first base, and all other runners on
	 * base advance by 1. If there was a runner on third base, the score increases
	 * by 1. - At least 150, but less than 200: the hit is a double. An imaginary
	 * runner advances to second base, and all other runners on base advance by 2.
	 * If there were runners on second or third, the score increases by 1 or 2. - At
	 * least 200, but less than 250: the hit is a triple. An imaginary runner
	 * advances to third base, and all other runners on base advance by 3. If there
	 * were runners on first, second, or third, the score is increased by 1, 2, or
	 * 3. -at least 250: the hit is a home run. All imaginary runners currently on
	 * base advance to home. The score is increased by 1 plus the number of runners
	 * on base. -This method does nothing if the game has ended.
	 * 
	 * @param distance - distance the ball travels (possibly negative)
	 */
	public void hit(int distance) {
		if (!gameEnded()) {

			if (distance < 15) {
				updateOuts();
			} else if (distance < 150) {
				advanceBases(1);

			} else if (distance < 200) {
				advanceBases(2);

			} else if (distance < 250) {
				advanceBases(3);
			} else {
				advanceBases(4);
			}
		}

	}

	/**
	 * Helper method to advance base runners and batter. Calls newCount() as a new
	 * batter will be up to bat.
	 * 
	 * @see #updateScore(int)
	 * @see #newCount()
	 * @param basesToAdvance - number of bases for the batter and base runners to
	 *                       advance.
	 */
	private void advanceBases(int basesToAdvance) {
		newCount();
		String temp = ""; // Temporary String variable to hold the position of the batter after advancing.
		if (basesToAdvance == 1) {
			temp += "X";
		} else if (basesToAdvance == 2) {
			temp += "oX";
		} else if (basesToAdvance == 3) {
			temp += "ooX";
		} else if (basesToAdvance == 4) {
			temp += "oooX";
		}
		// appends current base runner position to our batter position.
		temp += baseRunnerPosition;
		// assigns baseRunnerPosition with the value of temp.substring(0, 3). This gets
		// runners on 1st through 3rd.
		baseRunnerPosition = temp.substring(0, 3);
		// loops through each character from temp.substring(3). These are all of the
		// characters who made it to home base.
		for (char c : temp.substring(3).toCharArray()) {
			// if the character == 'X' call updateScore(int)
			if (c == 'X') {
				updateScore(1);
			}
		}

	}

	/**
	 * Helper method to update score of the current team at bat. If it is the top of
	 * the inning, Team0's score is updated. If not then team1 is at bat and their
	 * score is updated.
	 * 
	 * @param scoreToAdd
	 */
	private void updateScore(int scoreToAdd) {
		if (isTopOfInning) {
			team0Score += scoreToAdd;
		} else {
			team1Score += scoreToAdd;
		}

	}

	/**
	 * Method called to indicate that the batter is out due to a caught fly. Does
	 * nothing if the game is over.
	 * 
	 * @see #gameEnded()
	 * @see #updateOuts()
	 */
	public void caughtFly() {
		if (gameEnded() == false) {
			updateOuts();
		}
	}

	/**
	 * Returns true if the game is over, false otherwise.
	 * 
	 * @return boolean value of whether the game is over
	 */
	public boolean gameEnded() {
		// if current inning is less than the game length, game cannot be over.
		if (currentInning < gameLength) {
			return false;
		} else if (currentInning == gameLength) {
			// if current inning is equal to game length but it is the top of the inning,
			// game cannot be over.
			if (isTopOfInning() == true) {
				return false;
			} else {
				// if it is the bottom of the last inning, but current outs are less than max
				// outs, game is not over.
				if (getCurrentOuts() < MAX_OUTS) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Returns true if it's the first half of the inning (team 0 is at bat).
	 * 
	 * @return Returns boolean value of whether it is the top of the inning
	 */
	public boolean isTopOfInning() {

		return isTopOfInning;

	}

	/**
	 * Returns true if there is a runner on the indicated base, false otherwise.
	 * 
	 * @param which - base number to check
	 * @return Returns false if the given argument is anything other than 1, 2, or3
	 */
	public boolean runnerOnBase(int which) {
		// checks for baserunner on which - 1 since our baserunner String starts at
		// index 0
		if (baseRunnerPosition.charAt(which - 1) == 'X') {
			return true;
		}
		return false;

	}

	/**
	 * Returns the current inning. Innings are numbered starting at 1. When the game
	 * is over, this method returns the game's total number of innings, plus one.
	 * 
	 * @return currentInning, or the number of innings plus one in case the game is
	 *         over
	 */
	public int whichInning() {

		if (gameEnded()) {
			return gameLength + 1;
		}

		return currentInning;

	}

	/**
	 * Returns the count of balls for the current batter.
	 * 
	 * @return
	 */
	public int getBallCount() {

		return ballCount;
	}

	/**
	 * Returns the number of called strikes for the current batter.
	 * 
	 * @return strikeCount
	 */
	public int getCalledStrikes() {
		return strikeCount;

	}

	/**
	 * Returns the number of outs for the team currently at bat.
	 * 
	 * @return outCount
	 */
	public int getCurrentOuts() {
		return outCount;

	}

	/**
	 * Returns the score for team 0.
	 * 
	 * @return team0Score
	 */
	public int getTeam0Score() {
		return team0Score;

	}

	/**
	 * Returns the score for team 1.
	 * 
	 * @return team1Score
	 */
	public int getTeam1Score() {
		return team1Score;

	}

	// The methods below are provided for you and you should not modify them.
	// The compile errors will go away after you have written stubs for the
	// rest of the API methods.
	/**
	 * Returns a three-character string representing the players on base, in the
	 * order first, second, and third, where 'X' indicates a player is present and
	 * 'o' indicates no player. For example, the string "oXX" means that there are
	 * players on second and third but not on first.
	 * 
	 * @return three-character string showing players on base
	 */
	public String getBases() {
		return (runnerOnBase(1) ? "X" : "o") + (runnerOnBase(2) ? "X" : "o") + (runnerOnBase(3) ? "X" : "o");
	}

	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * 
	 * <pre>
	 *      ooo Inning:1 [T] Score:0-0 Balls:0 Strikes:0 Outs:0
	 * </pre>
	 * 
	 * The first three characters represent the players on base as returned by the
	 * <code>getBases()</code> method. The 'T' after the inning number indicates
	 * it's the top of the inning, and a 'B' would indicate the bottom. The score
	 * always shows team 0 first.
	 * 
	 * @return a single line string representation of the state of the game
	 */
	public String toString() {
		String bases = getBases();
		String topOrBottom = (isTopOfInning() ? "T" : "B");
		String fmt = "%s Inning:%d [%s] Score:%d-%d Balls:%d Strikes:%d Outs:%d";
		return String.format(fmt, bases, whichInning(), topOrBottom, getTeam0Score(), getTeam1Score(), getBallCount(),
				getCalledStrikes(), getCurrentOuts());
	}

}
