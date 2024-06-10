package hw4;

/**
 * An element in which the <code>update</code> method updates the position each
 * frame according to a <em>velocity</em> vector (deltaX, deltaY). The units are
 * assumed to be "pixels per frame".
 * 
 * @author Nicholas Jacobs
 */
//TODO: This class must directly or indirectly extend AbstractElement
public class MovingElement extends SimpleElement
	{
		/**
		 * Instance variable for x velocity.The units are assumed to be "pixels per
		 * frame".
		 */
		private double deltaX;
		/**
		 * Instance variable for y velocity.The units are assumed to be "pixels per
		 * frame".
		 */
		private double deltaY;

		/**
		 * Constructs a MovingElement with a default velocity of zero in both
		 * directions.
		 * 
		 * @param x      x-coordinate of upper left corner
		 * @param y      y-coordinate of upper left corner
		 * @param width  object's width
		 * @param height object's height
		 */
		public MovingElement(double x, double y, int width, int height)
			{
				// TODO: everything
				super(x, y, width, height);
				deltaX = 0;
				deltaY = 0;
			}

		/**
		 * @return the deltaX
		 */
		public double getDeltaX()
			{
				return deltaX;
			}

		/**
		 * @return the deltaY
		 */
		public double getDeltaY()
			{
				return deltaY;
			}

		@Override
		public void update()
			{
				super.update();
				move();
			}

		/**
		 * @param deltaX
		 * @param deltaY
		 */
		public void setVelocity(double deltaX, double deltaY)
			{
				this.deltaX = deltaX;
				this.deltaY = deltaY;
			}

		/**
		 * Protected helper method for moving this object. To be called in update and
		 * can be overridden by subclasses.
		 */
		protected void move()
			{
				double tempX = getXReal() + deltaX;
				double tempY = getYReal() + deltaY;
				setPosition(tempX, tempY);
			}
	}
