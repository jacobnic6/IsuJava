
public class ParkingMeter {

	
	private int minutesPerQuarter;
	private int maximumTime;
	private int timeRemaining;
	private int quarters;
	
	ParkingMeter(int minutesPerQuarter, int maximumTime){
		this.minutesPerQuarter = minutesPerQuarter;
		this.maximumTime = maximumTime;
		this.timeRemaining = 0;
		this.quarters = 0;
	}
	
	public void insertCoin(int howMany) {
		quarters += howMany;
		int timeToAdd = howMany * minutesPerQuarter;
		
		timeRemaining = Math.min(maximumTime, (timeRemaining + timeToAdd));
	}
	public int getTimeRemaining() {
		return this.timeRemaining;
	}
	public void passTime(int minutes) {
		timeRemaining = Math.max(0, (timeRemaining - minutes));
	}
	
	public double getTotal() {
		int dollars = quarters / 4;
		int cents = (quarters % 4) * 25;
		
		double total = dollars + ((double)cents/100);
		
		return total;
	}
}
