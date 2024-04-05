
public class MysteryTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Mystery m = new Mystery(5);
		System.out.println(m.brew(20)); // line 1
		System.out.println(m.brew(-9)); // line 2
		System.out.println(m.brew(5)); // line 3
		System.out.println(m.brew(5)); // line 4
	}

}
