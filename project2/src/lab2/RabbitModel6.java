package lab2;

import java.util.Random;

/**
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class RabbitModel6
{
	private int population;
	private int lastYear;
	private int yearBefore;
	
  // TODO - add instance variables as needed
  
  /**
   * Constructs a new RabbitModel.
   */
  public RabbitModel6()
  {
	  
	  yearBefore = 0;
	  lastYear = 1;
	  population = lastYear + yearBefore;
	 
    // TODO
  }  
 
  /**
   * Returns the current number of rabbits.
   * @return
   *   current rabbit population
   */
  public int getPopulation()
  {
    // TODO - returns a dummy value so code will compile
    return population;
  }
  
  /**
   * Updates the population to simulate the
   * passing of one year.
   */
  public void simulateYear()
  {
	  yearBefore = lastYear;
	  lastYear = population;
	  population = lastYear + yearBefore;
    // TODO
  }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
	  yearBefore = 0;
	 lastYear = 1;
	  population = lastYear + yearBefore;
    // TODO
  }
}
