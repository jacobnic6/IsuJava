package lab6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class LineNumberer2 {

	 public static void main(String[] args) throws FileNotFoundException
	  {
		 
			/*
			 * Checkpoint 1 part 1
			 * Modify the code for LineNumberer2 so that the input file comes from a
			 * different project, using a relative path to identify it. For example, run
			 * LineNumberer on the source file SimpleLoops.java that was part of lab 5.
			 * (Remember, a .java file is just a text file!)
			 */
		 String path = "..//project5//src//lab5//SimpleLoops.java";
		 
		   
		  File file = new File(path);
		    
		   
		    
		    System.out.println(file.exists());          // true if the file exists
		    System.out.println(file.getName());         // name of the file 
		    System.out.println(file.getAbsolutePath()); // absolute path to the file
		    System.out.println(file.length());          // size of the file
		  
		    FileReader inFile = new FileReader(file);
	    Scanner scanner = new Scanner(file);
	    int lineCount = 1;

	    while (scanner.hasNextLine())
	    {
	      String line = scanner.nextLine();
	      System.out.print(lineCount + " ");
	      System.out.println(line);
	      lineCount += 1;
	    }
	    scanner.close();
	     System.out.println("**************************");
	     System.out.println();
	    
	     wordsPerLine("story.txt");
	  }
	 
	 
		/*
		 * Checkpoint 1 part 2
		 * Write a method that reads a text file and, for each line, prints out the
		 * number of words in the line. Try it on the file story.txt Download story.txt.
		 */
	 private static void wordsPerLine(String fileName)throws FileNotFoundException {
		 File tempFile = new File(fileName);
		 Scanner tempScanner = new Scanner(tempFile);
		
		 while(tempScanner.hasNextLine()) {
			 int count = 0;
			 String line = tempScanner.nextLine();
			 for(String s : line.split(" ")) {
				 if(!s.isBlank()) {
					 count += 1; 
				 }
				
			 }
			 System.out.println("Words: " + count);
		 }
		 
		 
		 
		 
	 }
	 

}
