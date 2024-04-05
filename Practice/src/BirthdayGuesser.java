import java.util.Random;
public class BirthdayGuesser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(guessBirthday());
	}
	
	public static String guessBirthday() {
		//int x = 
		Random random = new Random();
		int x = random.nextInt(1, 31);
		int y  = random.nextInt(1900, 2000);
		String birthday = "September " + x + ", " + y;
		
		
		return birthday;
		
	}

}
