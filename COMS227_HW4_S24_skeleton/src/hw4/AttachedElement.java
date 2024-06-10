package hw4;

/**
 * An attached element is one that is associated with another "base" element
 * such as a PlatformElement or a LiftElement. Specifically, the attached
 * element's movement is determined by the movement of the base element, the
 * element always remains a fixed distance away. This class extends the abstract
 * class AssociatedElement.
 * 
 * @author Nicholas Jacobs
 */
//TODO: This class must directly or indirectly extend AbstractElement
public class AttachedElement extends AssociatedElement
	{
		/**
		 * Instance variable tracking this Object's vertical offset horizontally from
		 * its "base".
		 */
		private int hover;
		/**
		 * Instance variable tracking the last x coordinate of base.
		 */
		private double lastBaseX;
		/**
		 * Instance variable tracking the last y coordinate of base.
		 */
		private double lastBaseY;

		/**
		 * Constructs a new AttachedElement. Before being added to an associated "base"
		 * element such as a PlatformElement or LiftElement, the x and y coordinates are
		 * initialized to zero. When the base object is set (not in this constructor),
		 * the x-coordinate will be calculated as the base object's x-coordinate, plus
		 * the given offset, and the y-coordinate will become the base object's
		 * y-coordinate, minus this element's height, minus the hover amount.
		 * 
		 * @param width  element's width
		 * @param height element's height
		 * @param offset when added to a base object, this amount will be added to the
		 *               other object's x-coordinate to calculate this element's
		 *               x-coordinate
		 * @param hover  when added to a base object, this element's y-coordinate is the
		 *               other object's y-coordinate, minus this element's height, minus
		 *               the hover amount
		 */
		public AttachedElement(int width, int height, int offset, int hover)
			{
				// TODO: everything
				super(width, height, offset);
				this.hover = hover;
			}

		/**
		 * Gets the current position of the base object and updates this objects
		 * position relative to it.
		 * 
		 * x = base x + offset
		 * y = base y - this.height - hover
		 * 
		 * @see #setPosition(double, double)
		 */
		@Override
		protected void calculatePosition()
			{
				lastBaseX = getBase().getXReal();
				lastBaseY = getBase().getYReal();
				double xCoordinate = getBase().getXReal() + getOffset();
				double yCoordinate = getBase().getYReal() - this.getHeight() - hover;
				this.setPosition(xCoordinate, yCoordinate);
			}

		@Override
		protected void move()
			{
				//if base's current x or y != last x or y then the base has moved
				if (getBase().getXReal() != lastBaseX || getBase().getYReal() != lastBaseY)
					{
						//update this element's position
						calculatePosition();
					}
			}
	}
