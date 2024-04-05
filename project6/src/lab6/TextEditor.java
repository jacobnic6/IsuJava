package lab6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextEditor {

	public static void main(String[] args) throws FileNotFoundException
	  {
		  Scanner in = new Scanner(System.in);
		    File outFile = new File("mydocument.txt");
		    if (outFile.exists())
		    {
		      System.out.println("File already exists, ok to overwrite (y/n)? ");
		      if (!in.next().startsWith("y"))
		      {
		        return;
		      }else {

			    	 PrintWriter out = new PrintWriter(outFile);

			 	    // Echo keyboard input out to the file.
			 	    while (in.hasNextLine() && !in.nextLine().contains("done"))
			 	    {
			 	      String line = in.nextLine();
			 	      out.println(line);
			 	    }
			 	    
			 	    System.out.println("Done");
			 	    out.close(); // Important: don't forget to close!
		      }
		    }else {
		    	
		    	 PrintWriter out = new PrintWriter(outFile);

		 	    // Echo keyboard input out to the file.
		 	    while (in.hasNextLine() )
		 	    {
		 	      String line = in.nextLine();
		 	      out.println(line);
		 	    }
		 	    
		 	    System.out.println("Done");
		 	    out.close(); // Important: don't forget to close!
		    }
	    
	    
	   
	  }

}
