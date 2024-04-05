package lab3;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import balloon4.Balloon;

public class BalloonTests {
	private Balloon balloon;
	private Balloon balloon2;
	private Balloon balloon3;
	
	private int maxRadius1;
	private int maxRadius2;
	private int negMax;
	@Before
	public void setup()//runs before test cases
	{
		maxRadius1 = 10;
		maxRadius2 = 15;
		negMax = -1;
		balloon = new Balloon(maxRadius1);
		balloon2 = new Balloon(maxRadius2);
		balloon3 = new Balloon(negMax);
		
		
	}
	
	@Test
	public void testRadius()//ensures radius is 0 at construction
	{
		assertEquals(0, balloon.getRadius());
		assertEquals(0, balloon2.getRadius());
		assertEquals(0, balloon3.getRadius());
	}
	
	
	@Test
	public void testNotPopped()//ensures newly constructed balloon is not popped
	{
		assertEquals(false, balloon.isPopped());
		assertEquals(false, balloon2.isPopped());
		assertEquals(false, balloon3.isPopped());
	}
	
	@Test
	public void testBlow()//ensures radius goes back to  0 after over inflation
	{
	
		balloon.blow(5);
		assertEquals( 5, balloon.getRadius());
		balloon.blow(3);
		assertEquals(8, balloon.getRadius());
		
		balloon2.blow(20);
		
		assertEquals(0, balloon2.getRadius());
		
		
		balloon3.blow(5);
		assertEquals(0, balloon3.getRadius());
	}
	
	@Test
	public void testIsPopped()//ensures that balloon doesn't inflate after being popped
	{
	
		balloon2.pop();
		
		assertEquals(false, balloon.isPopped());
		assertEquals(true, balloon2.isPopped());
		assertEquals(false, balloon3.isPopped());
		
		balloon2.blow(10);
		assertEquals(0, balloon2.getRadius());
		
		
	}
	
	@Test
	public void testDeflate() {
		balloon.deflate();
		assertEquals(0, balloon.getRadius());
		balloon.blow(5);
		assertEquals(5, balloon.getRadius());
		assertEquals(false, balloon.isPopped());
	}
	
	@Test
	public void testNotBlow()
	{
		Balloon b = new Balloon(10);
		b.blow(9);
		assertEquals(9, b.getRadius());
		assertEquals(false, b.isPopped());
		b.pop();
		
		assertEquals(0, b.getRadius());
		assertEquals(true, b.isPopped());
		
		b.blow(8);
		assertEquals(0, b.getRadius());
	}
}

//blow(int) pops if radius> max radius

//deflate() deflates balloon to radius = 0

//getRadius() returns radius

//isPopped() returns whether balloon is popped 

//pop() pops the balloon, radius = 0 and cannot be inflated again

//1 allows for over inflation