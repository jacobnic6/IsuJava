package lab5;

public class ScannerTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x = 1;
		String temp = ""+x;
		System.out.println(temp);
		System.out.println(findFirstVowel("slls sll"));
		

		System.out.println(findFirstVowel("saells sll"));
		System.out.println(findFirstVowel("sls sllI"));
		
		System.out.println(findInitials("Nicholas David Jacobs"));
		System.out.println(findInitials("bobby"));
		System.out.println(findInitials(" bobby"));
		System.out.println(findInitials("     N   bas JDS"));
	}
	
	public static int findFirstVowel(String s) {
		int vowelIndex = -1;
		for(int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			if("aeiouAEIOU".indexOf(c) >= 0) {
				vowelIndex = i;
				break;
			}
		}
		
		return vowelIndex;
	}
	
	
	public static String findInitials(String s) {
		String initials = "";
	
		for(int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			
			if(s.charAt(i)==' ') {
				continue;
			}else {
				if(i == 0 || s.charAt(i-1) == ' ') {
					initials += c;
				}
			}
		}
		
		
		return initials;
	}

}
