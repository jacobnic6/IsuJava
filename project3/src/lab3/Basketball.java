package lab3;

public class Basketball {
	private double diameter;
	private boolean isInflated;
	
	public Basketball(double givenDiameter)
	  {
		diameter = givenDiameter;
		isInflated = false;
	  }

	  public boolean isDribbleable()
	  {
		  if (isInflated == true) {
			  return true;
		  }
		  else {
			  return false;
		  }
	    
	  }

	  public double getDiameter()
	  {
	    return this.diameter;
	  }

	  public double getCircumference()
	  {
	    return diameter * Math.PI;
	  }

	  public void inflate()
	  {
		  isInflated = true;
	  }
}
