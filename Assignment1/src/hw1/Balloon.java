package hw1;

/**
 * A class a Hot air balloon for a hot air balloon simulation.
 * This is COM S 227 homework assignment 1.
 * @author Nicholas Jacobs
 * @version 1.0
 */
public class Balloon {
	/**
	 * constant with class level scope because it is used in several methods
	 * Acceleration constant. A_g on formula sheet
	 */
	private final double ACCELERATION = 9.81;
	/**
	 * constant with class level scope because it is used in several methods
	 * a default double zero value
	 */
	private final double DEFAULT_ZERO = 0.0; // a default double zero value
	/**
	 * saves starting value of airTemp parameter from the constructor
	 */
	private double startingAirTemp;
	/**
	 * saves starting value of windDirection parameter from the constructor
	 */
	private double startingWindDirection;
	/**
	 * Given input to the simulation for air temp outside of the balloon. 
	 * T_outside on formula sheet
	 */
	private double airTempOutside; 
	/**
	 * Given input to the simulation for the fuel burn rate of the balloon. 
	 * B on formula sheet
	 */
	private double fuelBurnRate;
	/**
	 * Given input to the simulation for the mass of the balloon. 
	 * m on formula sheet
	 */
	private double balloonMass;
	/**
	 * Calculated instance variable for the amount of fuel left to use.
	 */
	private double fuelRemaning;
	/**
	 * Calculated instance variable for the current temperature inside of the balloon
	 */
	private double balloonTemp;
	/**
	 * Calculated instance variable for the current velocity of the balloon
	 */
	private double balloonVelocity;
	/**
	 * Calculated instance variable for the current altitude of the balloon
	 */
	private double balloonAltitude;
	/**
	 * Calculated instance variable for the tether length of the balloon
	 */
	private double tetherLength;
	/**
	 * Calculated instance variable for the current direction of the wind
	 */
	private double windDirection;
	/**
	 * Calculated instance variable for the amount of time in seconds since the start of the simulation.
	 */
	private long time;

	/**
	 * Non-Default Constructor initializing startingAirTemp, startingWindDirection, airTempOutside, 
	 * balloonTemp, and windDirection to their respective parameters
	 * initializes all other variables to a default value of 0
	 * @see #setOutsideAirTemp(double)
	 * @see #setBalloonTemp(double)
	 * @see #changeWindDirection(double)
	 * @see #setFuelRemaning(double)
	 * @see #setFuelBurnRate(double)
	 * @see #setBalloonMass(double)
	 * @see #setTetherLength(double)
	 * @param airTemp temperature of the air outside at start of the simulation
	 * @param windDirection direction of the wind (in degrees) at start of the simulation
	 */
	public Balloon(double airTemp, double windDirection) {
		this.startingAirTemp = airTemp;
		this.startingWindDirection = windDirection;
		setOutsideAirTemp(airTemp);
		setBalloonTemp(airTemp);
		changeWindDirection(windDirection);
		this.time = 0;
		this.balloonAltitude = DEFAULT_ZERO;
		setFuelRemaning(DEFAULT_ZERO);
		setFuelBurnRate(DEFAULT_ZERO);
		setBalloonMass(DEFAULT_ZERO);
		this.balloonVelocity = DEFAULT_ZERO;
		setTetherLength(DEFAULT_ZERO);

	}

	/**
	 * Rate of change in the balloon's air temp per second 
	 * Formula: ΔT = B + (T_outside - T_balloon) * HEAT_LOSS
	 * @see #getOutsideAirTemp()
	 * @see #getBalloonTemp()
	 * @return a double value representing the rate of change in the balloon's temperature
	 */
	private double calcBalloonTempRate() {
		final double HEAT_LOSS = 0.1; //Constant H on formula sheet. 

		double tempChange = fuelBurnRate + (getOutsideAirTemp() - getBalloonTemp()) * HEAT_LOSS;
		return tempChange;

	}

	/**
	 * temp of the air inside the balloon after one second 
	 * Formula: T_balloonAtCurrentTime = T_balloonOneSecondAgo + ΔT
	 * @see #getBalloonTemp()
	 * @see #calcBalloonTempRate()
	 * @return a double value representing the temperature of the balloon currently
	 */
	private double calcBalloonTemp() {
		return getBalloonTemp() + calcBalloonTempRate();
	}

	/**
	 * calculates lift of balloon in Newtons 
	 * Formula: F_L = V * (P_c - P_u) * A_g
	 * @see #getOutsideAirTemp()
	 * @see #getBalloonTemp()
	 * @return a double value representing the force of lift on a balloon
	 */
	private double calcForceOfLift() {
		final int BALLOON_VOLUME = 61234; //Constant V on formula sheet
		final double GAS = 287.05; //Constant R on formula sheet
		final double KELVINS_AT_ZERO = 273.15; //Constant K_c on formula sheet
		final double STANDARD_PRESSURE = 1013.25; //Constant P on formula sheet

		double surroundingAir = STANDARD_PRESSURE / (GAS * (getOutsideAirTemp() + KELVINS_AT_ZERO)); // Formula for surroundingAir pressure: P_c = P / (R * (T_outside + K_c))
		double balloonAir = STANDARD_PRESSURE / (GAS * (getBalloonTemp() + KELVINS_AT_ZERO)); // Formula for balloonAir pressure: P_u = P / (R * (T_balloon + K_c))

		double lift = BALLOON_VOLUME * (surroundingAir - balloonAir) * ACCELERATION;
		return lift;
	}

	/**
	 * calculates net force in upward direction in Newtons 
	 * Formula: F_n = F_L - F_g
	 * @see #calcForceOfLift()
	 * @return a double value representing the net force of lift - force of gravity.
	 */
	private double calcNetForceUp() {
		double forceOfGravity = balloonMass * ACCELERATION; //Formula for force of Gravity:  F_g = m A_g
		double netForce = calcForceOfLift() - forceOfGravity;
		return netForce;
	}

	/**
	 * calculates net acceleration in upward direction 
	 * Formula: A_n = F_n / m
	 * @see #calcNetForceUp()
	 * @return a double value representing the net acceleration
	 */
	private double calcNetAccelerationUp() {
		double netAcceleration = calcNetForceUp() / balloonMass;
		return netAcceleration;
	}

	/**
	 * calculates Velocity in upward direction (Assuming 1 second of time) in m/s
	 * Formula: V_atTime n = V_atTime n-1 + A_n
	 * @see #getVelocity()
	 * @see #calcNetAccelerationUp()
	 * @return a double value representing the velocity of the balloon
	 */
	private double calcVelocityUp() {
		double velocityUp = getVelocity() + calcNetAccelerationUp();

		return velocityUp;
	}

	/**
	 * calculates altitute of baloon after 1 second 
	 * Formula A_balloonAtTime n = A_balloonAtTime n-1 + V_atTime n
	 * @see #getTetherRemaining()
	 * @see #calcVelocityUp()
	 * @return a double value representing the current altitude of the balloon
	 */
	private double calcBalloonAltitude() {
		double altToAdd = Math.min(getTetherRemaining(), calcVelocityUp());
		double currentAlt = Math.max(0, altToAdd + balloonAltitude);
		return currentAlt;
	}

	/**
	 * update method for the simulation.
	 * Increments time by 1, calls burnFuel(), and updates balloonTemp, balloonAltitude, and balloonVelocity 
	 * with current values returned by their respective methods.
	 * @see #burnFuel()
	 * @see #calcBalloonTemp()
	 * @see #calcBalloonAltitude()
	 * @see #calcVelocityUp()
	 */
	public void update() {
		time += 1;
		burnFuel();
		balloonTemp = calcBalloonTemp();
		balloonAltitude = calcBalloonAltitude();
		balloonVelocity = calcVelocityUp();

	}

	/**
	 * Method to simulate burning fuel by calculating the amount to burn and then updating our fuel remaining with the difference between its current value
	 * and (the max of the amount to burn and 0).
	 * assigns fuelBurnRate with newly calculated amountToBurn
	 * The thought is that while there is fuel to burn, burn it. Otherwise fuelRemaining is 0
	 */
	private void burnFuel() {
		double amountToBurn = Math.min(fuelBurnRate, fuelRemaning);
		fuelRemaning -= Math.max(amountToBurn, 0);
		fuelBurnRate = amountToBurn;
	}

	/**
	 * Reset method for the simulation. Updates all instance variables to their starting values.
	 * @see #setOutsideAirTemp(double)
	 * @see #setBalloonTemp(double)
	 * @see #setFuelRemaning(double)
	 * @see #setFuelBurnRate(double)
	 * @see #setBalloonMass(double)
	 * @see #setTetherLength(double)
	 */
	public void reset() {
		setOutsideAirTemp(startingAirTemp);
		setBalloonTemp(startingAirTemp);
		this.windDirection = startingWindDirection;
		this.time = 0;
		this.balloonAltitude = DEFAULT_ZERO;
		setFuelRemaning(DEFAULT_ZERO);
		setFuelBurnRate(DEFAULT_ZERO);
		setBalloonMass(DEFAULT_ZERO);
		this.balloonVelocity = DEFAULT_ZERO;
		setTetherLength(DEFAULT_ZERO);

	}
	
	/**
	 * Getter method for fuelRemaining
	 * @return a double representing the current value of fuelRemaining
	 */
	public double getFuelRemaining() {
		return fuelRemaning;
	}
	
	/**
	 * Setter method for this.fuelRemaining
	 * @param fuelRemaning value that we are assigning this.fuelRemaining to
	 */
	public void setFuelRemaning(double fuelRemaning) {
		this.fuelRemaning = fuelRemaning;
	}

	/**
	 * Getter method for balloonMass
	 * @return a double representing the current value of balloonMass
	 */
	public double getBalloonMass() {
		return balloonMass;
	}

	/**
	 * Setter method for this.balloonMass
	 * @param balloonMass value that we are assigning this.balloonMass to
	 */
	public void setBalloonMass(double balloonMass) {
		this.balloonMass = balloonMass;
	}

	/**
	 * Getter method for airTempOutside
	 * @return a double representing the current value of airTempOutside
	 */
	public double getOutsideAirTemp() {
		return airTempOutside;
	}

	/**
	 * Setter method for this.airTempOutside
	 * @param airTempOutside value that we are assigning this.airTempOutside to
	 */
	public void setOutsideAirTemp(double airTempOutside) {
		this.airTempOutside = airTempOutside;
	}

	/**
	 * Getter method for fuelBurnRate
	 * @return a double representing the current value of fuelBurnRate
	 */
	public double getFuelBurnRate() {
		return fuelBurnRate;
	}

	
	/**
	 * Setter method for this.fuelBurnRate
	 * @param fuelBurnRate value that we are assigning this.fuelBurnRate to
	 */
	public void setFuelBurnRate(double fuelBurnRate) {
		this.fuelBurnRate = fuelBurnRate;
	}

	/**
	 * Getter method for balloonTemp
	 * @return a double representing the current value of balloonTemp
	 */
	public double getBalloonTemp() {
		return balloonTemp;
	}

	/**
	 * Setter method for this.balloonTemp
	 * @param balloonTemp value that we are assigning this.balloonTemp to
	 */
	public void setBalloonTemp(double balloonTemp) {
		this.balloonTemp = balloonTemp;
	}

	/**
	 * Getter method for balloonVelocity
	 * @return a double representing the current value of balloonVelocity
	 */
	public double getVelocity() {

		return balloonVelocity;
	}

	/**
	 * Getter method for balloonAltitude
	 * @return a double representing the current value of balloonAltitude
	 */
	public double getAltitude() {
		return balloonAltitude;
	}

	/**
	 * Getter method for tetherLength
	 * @return a double representing the current value of tetherLength
	 */
	public double getTetherLength() {
		return tetherLength;
	}

	/**
	 * Method to return tetherLength - current altitude to get remaining tetherLength
	 * @return a double value representing the remaining tether length
	 */
	public double getTetherRemaining() {
		return this.tetherLength - balloonAltitude;
	}

	/**
	 * Setter method for tetherLength
	 * @param length value that we are assigning tetherLength to
	 */
	public void setTetherLength(double length) {
		this.tetherLength = length;
	}

	/**
	 * Getter method for windDirection
	 * @return a double representing the current value of windDirection
	 */
	public double getWindDirection() {
		return windDirection;
	}

	/**
	 * Method to change wind direction by adding a degree amount to the current wind
	 * direction. Add 360 to account for negative values and then take % 360 to
	 * ensure windDirection is 0 to 359
	 * @param deg amount to add to our current wind direction
	 */
	public void changeWindDirection(double deg) {
		double degreesToAdd = 360;
		windDirection = (windDirection + deg + degreesToAdd) % 360;
	}

	/**
	 * Method to return how many minutes have passed since the start of the simulation
	 * @return a long value representing total time / 60
	 */
	public long getMinutes() {
		return this.time / 60;
	}

	/**
	 * Method to get the number of seconds passed the number of full minutes.
	 * @return a long value representing total time % 60
	 */
	public long getSeconds() {
		return this.time % 60;
	}

}
