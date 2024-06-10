package hw4;

/**
 * An element with two distinctive behaviors. First, it can be set up to move
 * vertically within a fixed set of boundaries. On reaching a boundary, the
 * y-component of its velocity is reversed. Second, it maintains a list of
 * <em>associated</em> elements whose basic motion all occurs relative to the
 * LiftElement.
 * 
 * @author Nicholas Jacobs
 */
//TODO: This class must directly or indirectly extend AbstractElement
public class LiftElement extends BaseElement
	{
		/**
		 * Constructs a new Elevator. Initially the upper and lower boundaries are
		 * <code>Double.NEGATIVE_INFINITY</code> and
		 * <code>Double.POSITIVE_INFINITY</code>, respectively.
		 * 
		 * @param x      x-coordinate of initial position of upper left corner
		 * @param y      y-coordinate of initial position of upper left corner
		 * @param width  element's width
		 * @param height element's height
		 */
		public LiftElement(double x, double y, int width, int height)
			{
				// TODO: everything
				super(x, y, width, height);
			}

		@Override
		protected void move()
			{

				double futureYVal;
				double deltaYAbsolute = Math.abs(getDeltaY());	

				if (distanceTillBound() >= deltaYAbsolute)
					{
						//if not going to run into a bound, then y = y position + deltaY
						futureYVal = getYReal() + getDeltaY();
					}
				else if (getDeltaY() > 0)
					{
						//if we are within range of the upper bound y = upper - height
						futureYVal = getMax() - getHeight();
					}
				else
					{
						//has to be in range of the lower bound, y = lowerBound
						futureYVal = getMin();
					}
				setPosition(getXReal() + getDeltaX(), futureYVal);

				if (distanceTillBound() <= 0)
					{
						//if we are at a bound, reverse velocity accordingly
						switchVelocity(getDeltaX(), getDeltaY());
					}
			}

		// TODO: everything
	}