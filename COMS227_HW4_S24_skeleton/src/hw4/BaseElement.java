/**
 * 
 */
package hw4;

import java.util.ArrayList;

import api.AbstractElement;

/**
 * A base element is an abstract base class to classes like PlatformElement or
 * LiftElement, with two distinctive behaviors. First, it can be set to move
 * either horizontally or vertically within a fixed set of boundaries. On
 * reaching a boundary, the x or y velocity is reversed accordingly. Second, it
 * maintains a list of <em>associated</em> elements whose basic motion all
 * occurs relative to the BaseElement.
 * 
 * @author Nicholas Jacobs
 */
public abstract class BaseElement extends MovingElement
	{
		/**
		 * Instance variable tracking this Object's "base".
		 */
		private ArrayList<AbstractElement> associatedElementList;

		private double upperBound;

		private double lowerBound;

		/**
		 * Constructs a new BaseElement through reference to a child class. Initially
		 * the left and right boundaries are <code>Double.NEGATIVE_INFINITY</code> and
		 * <code>Double.POSITIVE_INFINITY</code>, respectively.
		 * 
		 * @param x      x-coordinate of initial position of upper left corner
		 * @param y      y-coordinate of initial position of upper left corner
		 * @param width  object's width
		 * @param height object's height
		 */
		public BaseElement(double x, double y, int width, int height)
			{
				super(x, y, width, height);
				associatedElementList = new ArrayList<AbstractElement>();
				upperBound = Double.NEGATIVE_INFINITY;
				lowerBound = Double.POSITIVE_INFINITY;
				// TODO Auto-generated constructor stub
			}

		/**
		 * Used as a helper method for switching this element's velocity. If deltaY != 0
		 * then it is moving vertically and deltaY is reversed. If deltaX != 0 then it
		 * is moving horizontally and deltaX is reversed.
		 * 
		 * @param deltaX This object's x velocity
		 * @param deltaY This object's y velocity
		 * @see #setVelocity(double, double)
		 */
		protected void switchVelocity(double deltaX, double deltaY)
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
		 * Helper method calculating the distance to the right or left bound if moving
		 * horizontally or the distance to upper or lower bound if moving vertically
		 * 
		 * @return distance distance to right or left bound if moving horizontally
		 *         distance to upper or lower bound if moving vertically
		 */
		protected double distanceTillBound()
			{
				double xRight = getXReal() + getWidth();	//x value of the right side of this element
				double distance = 0;

				if (getDeltaX() != 0)
					{

						//if moving right 
						if (getDeltaX() > 0)
							{

								distance = upperBound - xRight;
							}
						else
							{
								distance = getXReal() - lowerBound;
							}
					}
				else if (getDeltaY() != 0)
					{
						//if moving down
						if (getDeltaY() > 0)
							{
								distance = upperBound - (getYReal() + getHeight());
							}
						else
							{
								distance = getYReal() - lowerBound;
							}
					}
				return distance;
			}

		public void addAssociated(AttachedElement attached)
			{
				attached.setBase(this);
				associatedElementList.add(attached);
			}

		public void addAssociated(FollowerElement follower)
			{
				follower.setBase(this);
				associatedElementList.add(follower);
			}

		/**
		 * Removes all elements in associatedElementList that are marked for deletion
		 */
		public void deleteMarkedAssociated()
			{

				for (int i = associatedElementList.size() - 1; i >= 0; i--)
					{

						if (associatedElementList.get(i).isMarked())
							{
								associatedElementList.remove(i);
							}
					}
			}

		/**
		 * Overrides MovingElement's implementation of update by calling super.update so
		 * the base element moves and then iterates through its associatedElementList
		 * and update's each element's position.
		 * 
		 * @see AbstractElement#setPosition(double, double)
		 * @see AbstractElement#update()
		 */
		@Override
		public void update()
			{
				super.update();

				for (AbstractElement e : getAssociated())
					{
						e.setPosition(e.getXReal() + getDeltaX(), e.getYReal() + getDeltaY());
						e.update();
					}
			}

		public void setBounds(double min, double max)
			{
				this.lowerBound = min;
				this.upperBound = max;
			}

		/**
		 * @return the associatedElementList
		 */
		public ArrayList<AbstractElement> getAssociated()
			{
				return associatedElementList;
			}

		/**
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

	}
