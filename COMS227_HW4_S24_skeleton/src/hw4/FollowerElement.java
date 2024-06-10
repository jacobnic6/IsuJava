package hw4;

import api.AbstractElement;

/**
 * A follower element is one that is associated with another "base" element such
 * as a PlatformElement or LiftElement. Specifically, the follower element's
 * movement is determined by the movement of the base element, when the base
 * moves up 10 pixels, the follower moves up 10 pixels. However, the follower
 * may not always be at a fixed location relative to the base. When the
 * horizontal velocity of the follower is set to a non-zero value, the follower
 * will oscillate between the left and right edges of the PlatformElement or
 * LiftElement it is associated with. This class extends the abstract class
 * AssociatedElement.
 * 
 * @author Nicholas Jacobs
 */
//TODO: This class must directly or indirectly extend AbstractElement
public class FollowerElement extends AssociatedElement
	{
		/**
		 * Instance variable tracking this Object's upper bound.
		 */
		private double upperBound;

		/**
		 * Instance variable tracking this Object's lower bound.
		 */
		private double lowerBound;

		/**
		 * Constructs a new FollowerElement. Before being added to a "base" element such
		 * as a PlatformElement or LiftElement, the x and y coordinates are zero. When a
		 * base element is set, the initial x-coordinate becomes the base's
		 * x-coordinate, plus the given offset, and the y-coordinate becomes the base's
		 * y-coordinate, minus this element's height.
		 * 
		 * @param width         element's width
		 * @param height        element's height
		 * @param initialOffset when added to a base, this amount will be added to the
		 *                      bases's x-coordinate to calculate this element's initial
		 *                      x-coordinate
		 */
		public FollowerElement(int width, int height, int initialOffset)
			{
				super(width, height, initialOffset);
				upperBound = Double.NEGATIVE_INFINITY;
				lowerBound = Double.POSITIVE_INFINITY;
				// TODO: everything
			}

		/**
		 * Overrides MovingElement's move() to be called in update(). Checks whether the
		 * bounds are correct and updates this element's position accordingly to the
		 * direction that it is moving.
		 * 
		 * Movement is determined by the movement of the base element, when the base
		 * move up 10 pixels, the follower moves up 10 pixels. However, the follower may
		 * not always be at a fixed location relative to the base. When the horizontal
		 * velocity of the follower is set to a non-zero value, the follower will
		 * oscillate between the left and right edges of the PlatformElement or
		 * LiftElement it is associated with.
		 * 
		 * @see #checkBounds()
		 * @see #setPosition(double, double)
		 * @see #switchVelocity(double, double)
		 * @see #distanceTillBound()
		 * @see MovingElement#move()
		 */
		@Override
		protected void move()
			{
				checkBounds();
				double futureXVal;
				double deltaXAbsolute = Math.abs(getDeltaX());

				if (getDeltaX() == 0)
					{
						// if not moving horizontally, x = base x position + offset

						futureXVal = getBase().getXReal() + getOffset();
					}
				else
					{

						if (distanceTillBound() >= deltaXAbsolute)
							{
								// if not going to run into a bound this frame, x = currentX + deltaX
								futureXVal = getXReal() + getDeltaX();
							}
						else
							{

								if (getDeltaX() > 0)
									{
										// if approaching upperBound, need to subtract width
										futureXVal = upperBound - getWidth();
									}
								else
									{
										// moving left and going to hit lowerBound this frame so x = lowerBound
										futureXVal = lowerBound;
									}
							}
					}
				// update position
				setPosition(futureXVal, getBaseMinusHeight());

				if (distanceTillBound() <= 0)
					{
						// if we are currently at a bound, negate delta x to go other way
						switchVelocity(getDeltaX(), getDeltaY());
					}
			}

		/**
		 * Helper method calculating this element's y value. Found by taking base's y
		 * value - this element's height
		 * 
		 * @return this element's y value
		 */
		private double getBaseMinusHeight()
			{
				return getBase().getYReal() - this.getHeight();
			}

		/**
		 * Helper method comparing the base element's left and right x values to this
		 * element's lower and upperBound. If neither are equal to lowerBound, then
		 * resets this element's bounds.
		 * 
		 * @see #setBounds(double, double)
		 */
		private void checkBounds()
			{

				if (getBase().getXReal() != lowerBound || getBase().getXReal() + getBase().getWidth() != upperBound)
					{
						setBounds(getBase().getXReal(), getBase().getXReal() + getBase().getWidth());
					}
			}

		/**
		 * Helper method checking whether the element is moving left or right and
		 * returns the distance to the bound it is approaching.
		 * 
		 * @return distance if moving right, = upperBound - (getXReal() + getWidth())
		 *         distance if moving left, = getXReal() - lowerBound
		 * @see FollowerElement#upperBound
		 * @see FollowerElement#lowerBound
		 * @see #isMovingRight()
		 */
		private double distanceTillBound()
			{
				double xRight = getXReal() + getWidth();
				double distance = 0;

				if (getDeltaX() > 0)
					{
						distance = upperBound - xRight;
					}
				else
					{
						distance = getXReal() - lowerBound;
					}
				return distance;
			}

		/**
		 * Helper method taking deltaX and deltaY as input and checking which way the
		 * object is moving then sets this objects velocity to the negative of that
		 * delta value.
		 * 
		 * @param deltaX element's current deltaX
		 * @param deltaY element's current deltaY
		 * @see #setVelocity(double, double)
		 */
		private void switchVelocity(double deltaX, double deltaY)
			{

				if (deltaY != 0)
					{
						deltaY = -deltaY;
					}

				if (deltaX != 0)
					{
						deltaX = -deltaX;
					}
				setVelocity(deltaX, deltaY);
			}

		/**
		 * Overrides AssociatedElement's setBase() so this element can set its initial
		 * bounds before passing it to its super
		 * 
		 * @param b
		 * @see FollowerElement#setBounds(double, double)
		 */
		@Override
		public void setBase(AbstractElement b)
			{
				setBounds(b.getXReal(), b.getXReal() + b.getWidth());
				super.setBase(b);
			}

		/**
		 * @param min
		 * @param max
		 */
		public void setBounds(double min, double max)
			{
				this.lowerBound = min;
				this.upperBound = max;
			}

		/**
		 * Returns this object
		 * 
		 * @return lowerBound
		 */
		public double getMin()
			{
				return lowerBound;
			}

		/**
		 * @return upperBound
		 */
		public double getMax()
			{
				return upperBound;
			}
		// TODO: everything
	}
