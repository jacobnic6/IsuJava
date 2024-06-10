package hw4;

/**
 * Moving element in which the vertical velocity is adjusted each frame by a
 * gravitational constant to simulate gravity. The element can be set to
 * "grounded", meaning gravity will no longer influence its velocity.
 * 
 * @author Nicholas Jacobs
 */
//TODO: This class must directly or indirectly extend AbstractElement
public class FlyingElement extends MovingElement
	{
		/**
		 * Instance variable tracking whether this object is "grounded".
		 */
		private boolean isGrounded;
		/**
		 * Instance variable tracking the force of gravity influencing this object's velocity.
		 */
		private double gravity;

		/**
		 * Constructs a new FlyingElement. By default it should be grounded, meaning
		 * gravity does not influence its velocity.
		 * 
		 * @param x      x-coordinate of upper left corner
		 * @param y      y-coordinate of upper left corner
		 * @param width  element's width
		 * @param height element's height
		 */
		public FlyingElement(double x, double y, int width, int height)
			{
				// TODO: everything
				super(x, y, width, height);
				isGrounded = true;
				gravity = 0;
			}


		@Override
		protected void move()
			{
			
				double velocity;

				setPosition(getXReal() + getDeltaX(), getYReal() + getDeltaY());
						
						
						if (!isGrounded || gravity == 0)
							{
								//velocity = y velocity + gravity if not grounded or gravity is 0
								velocity = getDeltaY() + gravity;
								setVelocity(getDeltaX(), velocity);
							}
					
					
			}

		/**
		 * @return isGrounded
		 */
		public boolean isGrounded()
			{
				// TODO Auto-generated method stub
				return isGrounded;
			}

		/**
		 * @param grounded
		 */
		public void setGrounded(boolean grounded)
			{
				// TODO Auto-generated method stub
				this.isGrounded = grounded;
			}

		/**
		 * @param gravity
		 */
		public void setGravity(double gravity)
			{
				// TODO Auto-generated method stub
				this.gravity = gravity;
			}

	}
