package lab2;

import java.util.Random;

public class StringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String message = "Hello, world!";
		System.out.println(message);
		int theLength = message.length();
		
		System.out.println(theLength);
		
		char theChar = message.charAt(0);
		System.out.println(theChar);
		
		theChar = message.charAt(1);
		System.out.println(theChar);
		
		System.out.println(message.toUpperCase());
		
		System.out.println(message.substring(0, 5));
		

	}

}
