
public class PatternTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		
		for(int i = 0; i < 20; i++) {
			String temp = "";
			
			if(i < 10) 
			{
				
				
				for(int j = 10 - i; j >0 ; j--) 
				{
					
					temp += " ";
				}
				for( int z = 0; z < i; z++) {
					temp += " *";
				}
				
			}
			else if (i >= 10) 
			{
				for( int y = 20 - i  ; y < 10; y++) {
					temp += " ";
				}
				for(int j = 20 - i; j >0 ; j--) 
				{
					
					temp += " *";
				}
				
			}
			
			System.out.println(temp);
		}
		
	}

}
