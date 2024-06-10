package hw4;

/**
 * A PlatformElement is an element with two distinctive behaviors. First, it can
 * be set up to move horizontally within a fixed set of boundaries. On reaching
 * a boundary, the x-component of its velocity is reversed. Second, it maintains
 * a list of <em>associated</em> elements whose basic motion all occurs relative
 * to the PlatformElement.
 * 
 * @author Nicholas Jacobs
 */
//TODO: This class must directly or indirectly extend AbstractElement
public class PlatformElement extends BaseElement
	{
		/**
		 * Constructs a new PlatformElement. Initially the left and right boundaries are
		 * <code>Double.NEGATIVE_INFINITY</code> and
		 * <code>Double.POSITIVE_INFINITY</code>, respectively.
		 * 
		 * @param x      x-coordinate of initial position of upper left corner
		 * @param y      y-coordinate of initial position of upper left corner
		 * @param width  object's width
		 * @param height object's height
		 */
		public PlatformElement(double x, double y, int width, int height)
			{
				// TODO: everything
				super(x, y, width, height);
			}
		
		@Override
		protected void move()
			{
				
				double futureXVal;
				double deltaXAbsolute = Math.abs(getDeltaX());
				
				if (distanceTillBound() >= deltaXAbsolute)
					{
						//if not going to run into a bound, then x = x position + deltaX
						futureXVal = getXReal() + getDeltaX();
					}
				else if (getDeltaX() > 0)
					{
						//if we are within range of the upper bound x = upper - width
						futureXVal = getMax() - getWidth();
					}
				else
					{
						//has to be in range of the lower bound, x = lowerBound
						futureXVal = getMin();
					}
				setPosition(futureXVal, getYReal() + getDeltaY());
					
				if (distanceTillBound() <= 0)
					{
						//if we are at a bound, reverse velocity accordingly
						switchVelocity(getDeltaX(), getDeltaY());
					}
			}


		// TODO: everything
	}
