package sample_tests;

import hw4.FlyingElement;

public class FlyingTest {
	public static void main(String[] args) {
		FlyingElement p = new FlyingElement(0, 0, 0, 0);
		p.setGrounded(false);
		p.setVelocity(2, 3);
		p.update();
		System.out.println("REAL  Y EXPECTED:   3   ACTUAL:  " + p.getYReal()); // expected 3
		System.out.println("DELTA Y EXPECTED:  3   ACTUAL:  " +p.getDeltaY());// expected 3
		System.out.println();
		p.update();
		System.out.println("REAL  Y EXPECTED:   6   ACTUAL:  " +p.getYReal()); // expected 6
		System.out.println("DELTA Y EXPECTED:  3   ACTUAL:  " + p.getDeltaY());// expected 3
		System.out.println();
		p.setGravity(5);
		p.update();
		System.out.println("REAL  Y EXPECTED:   9   ACTUAL:  " +p.getYReal()); // 6 + 3 = 9
		System.out.println("DELTA Y EXPECTED:  8   ACTUAL:  " +p.getDeltaY()); // 3 + 5 = 8
		System.out.println();
		p.setGrounded(true);
		p.update();
		
		System.out.println("REAL  Y EXPECTED:  17  ACTUAL:  " +p.getYReal()); // 9 + 8 = 17
		System.out.println("DELTA Y EXPECTED:  8   ACTUAL:  " +p.getDeltaY()); // 8 (grounded)
		System.out.println();
		p.update();
		System.out.println("REAL  Y EXPECTED:  25   ACTUAL:  " +p.getYReal()); // 17 + 8 = 25
		System.out.println("DELTA Y EXPECTED:  8   ACTUAL:  " +p.getDeltaY()); // 8 (grounded)
	}
}
