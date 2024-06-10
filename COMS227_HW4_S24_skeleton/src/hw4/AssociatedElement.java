/**
 * 
 */
package hw4;

import api.AbstractElement;

/**
 * An associated element is an abstract base class to classes like
 * FollowerElement or AttachedElement, that are associated with another "base"
 * element such as a PlatformElement or a LiftElement. Specifically, the
 * attached
 * element's movement is determined by the movement of the base element.
 * 
 * @author Nicholas Jacobs
 */
public abstract class AssociatedElement extends MovingElement
	{
		/**
		 * Instance variable tracking this Object's "base".
		 */
		private AbstractElement base;
		/**
		 * Instance variable tracking this Object's horizontal offset from its "base".
		 */
		private int offset;

		/**
		 * Constructs a new AssociatedElement object through reference to a child class.
		 * Before being added to a "base" element such as a PlatformElement or
		 * LiftElement, the x and y coordinates are zero. When a base element is set,
		 * the initial x-coordinate becomes the base's
		 * x-coordinate, plus the given offset, and the y-coordinate becomes the base's
		 * y-coordinate, minus this element's height.
		 * 
		 * @param width  element's width
		 * @param height element's height
		 * @param offset when added to a base object, this amount will be added to the
		 *               other object's x-coordinate to calculate this element's initial
		 *               x-coordinate
		 */
		public AssociatedElement(int width, int height, int offset)
			{
				super(0, 0, width, height);
				// TODO Auto-generated constructor stub
				this.offset = offset;
			}

		/**
		 * Gets the current position of the base object and updates this objects
		 * position relative to it.
		 * 
		 * @see #setPosition(double, double)
		 */
		protected void calculatePosition()
			{
				// TODO Auto-generated method stub
				double xCoordinate = base.getXReal() + offset;
				double yCoordinate = base.getYReal() - this.getHeight();
				this.setPosition(xCoordinate, yCoordinate);
			}

		/**
		 * Sets the base for this element and calls a method to update its position
		 * relative to its base.
		 * 
		 * @param b AbstractElement to be set as this object's base
		 * @see #calculatePosition()
		 */
		public void setBase(AbstractElement b)
			{
				this.base = b;
				calculatePosition();
			}

		/**
		 * @return the offset
		 */
		protected int getOffset()
			{
				return offset;
			}

		/**
		 * @param offset the offset to set
		 */
		protected void setOffset(int offset)
			{
				this.offset = offset;
			}

		/**
		 * @return the base
		 */
		protected AbstractElement getBase()
			{
				return base;
			}
	}
