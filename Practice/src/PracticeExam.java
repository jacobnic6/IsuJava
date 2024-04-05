
public class PracticeExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int dollars = 5;
		int cents = 37;
		double total = dollars + ((double)cents/100 );
		//System.out.println(total);
		
		/**
		 * An integer containing the whole number part of a double d
		 */
		double d = 7.999;
		int wholeNum = (int)d ;
		//System.out.println(wholeNum);
		
		boolean result;
		int x, y, z;
		
		x = 1; y = 2; z = 4;
		boolean is = true;
		
		boolean resultBool;
		System.out.println(foo(3,6));
		System.out.println(foo2(3,6));
	}
	
	public static boolean foo(int x, int y) {
		
		boolean result = false;
		if (x > 0) 
		{
			if (y / x == 2) 
			{
				result = true;
			} else if (y / x == 3)
			{
				result = true;
			}
		}
		return result;
		
		
	}
public static boolean foo2(int x, int y) {
		
		boolean result = x > 0 && (y/x == 2 || y/x == 3);
	
		return result;
		
		
	}

}
