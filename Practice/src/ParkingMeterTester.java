
public class ParkingMeterTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParkingMeter m = new ParkingMeter(15, 60);
		m.insertCoin(3);
		System.out.println(m.getTotal());
		System.out.println(m.getTimeRemaining());
		m.passTime(20);
		System.out.println(m.getTotal());
		System.out.println(m.getTimeRemaining());
		m.insertCoin(4);
		System.out.println(m.getTotal());
		System.out.println(m.getTimeRemaining());
		m.passTime(90);
		
		System.out.println(m.getTotal());
		System.out.println(m.getTimeRemaining());
	}

}
