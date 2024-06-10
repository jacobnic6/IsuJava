package hw4;

import java.awt.Rectangle;

import api.AbstractElement;

/**
 * Special documentation requirement: (see page 11 of documentation)
 * SimpleElement will extend to VanishingElement, MovingElement.
 * MovingElement will extend to FlyingElement, BaseElement and Associated
 * Element.
 * AssociatedElement is an abstract class that will extend to AttachedElement
 * and FollowerElement.
 * BaseElement is an abstract class that will extend to LiftElement and
 * PlatformElement.
 */
/**
 * Minimal concrete extension of AbstractElement. The <code>update</code> method
 * in this implementation just increments the frame count.
 * 
 * @author Nicholas Jacobs
 */
// TODO: This class must directly or indirectly extend AbstractElement
public class SimpleElement extends AbstractElement
	{
		/**
		 * Instance variable for the x coordinate of SimpleElement.
		 */
		private double xVal;
		/**
		 * Instance variable for the y coordinate of SimpleElement.
		 */
		private double yVal;
		/**
		 * Instance variable for the width of SimpleElement.
		 */
		private int width;
		/**
		 * Instance variable for the height of SimpleElement.
		 */
		private int height;
		/**
		 * Instance variable for the frameCount of SimpleElement.
		 */
		private int frameCount;
		/**
		 * Instance variable tracking if this SimpleElement is marked for deletion.
		 */
		private boolean isMarked;

		/**
		 * Constructs a new SimpleElement.
		 * 
		 * @param x      x-coordinate of upper left corner
		 * @param y      y-coordinate of upper left corner
		 * @param width  element's width
		 * @param height element's height
		 */
		public SimpleElement(double x, double y, int width, int height)
			{
				// TODO: everything
				xVal = x;
				yVal = y;
				this.width = width;
				this.height = height;
				frameCount = 0;
				isMarked = false;
			}

		public double getXReal()
			{
				// TODO Auto-generated method stub
				return xVal;
			}

		public int getXInt()
			{
				// TODO Auto-generated method stub
				return (int) Math.round(xVal);
			}

		@Override
		public int getYInt()
			{
				// TODO Auto-generated method stub
				return (int) Math.round(yVal);
			}

		@Override
		public int getWidth()
			{
				// TODO Auto-generated method stub
				return width;
			}

		@Override
		public int getHeight()
			{
				// TODO Auto-generated method stub
				return height;
			}

		@Override
		public Rectangle getRect()
			{
				// TODO Auto-generated method stub
				return new Rectangle(getXInt(), getYInt(), width, height);
			}

		@Override
		public void setPosition(double newX, double newY)
			{
				// TODO Auto-generated method stub
				xVal = newX;
				yVal = newY;
			}

		@Override
		public double getYReal()
			{
				// TODO Auto-generated method stub
				return yVal;
			}

		@Override
		public void update()
			{
				// TODO Auto-generated method stub
				frameCount += 1;
			}

		@Override
		public int getFrameCount()
			{
				// TODO Auto-generated method stub
				return frameCount;
			}

		@Override
		public boolean isMarked()
			{
				// TODO Auto-generated method stub
				return isMarked;
			}

		@Override
		public void markForDeletion()
			{
				// TODO Auto-generated method stub
				isMarked = true;
			}

		@Override
		public boolean collides(AbstractElement other)
			{
				if (other.getRect().intersects(this.getRect()))
					{
						return true;
					}
				return false;
			}
	}
