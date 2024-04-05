import java.util.Scanner;

public class MeterConverterTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double meters;
		String response;
		
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("enter a number of meters");
		meters = scnr.nextDouble();
		
		System.out.println("to km or cm?");
		response = scnr.next();
		
		if(response.equals("km")) {
			System.out.println(meters/ 1000);
		}else if(response.equals("cm")) {
			System.out.println(meters * 100);
		}else {
			System.out.println("error");
		}
		
	}

}
