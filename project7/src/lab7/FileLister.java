package lab7;

import java.io.File;

public class FileLister {
	public static void main(String[] args) {
		// Choose the directory you want to list.
		// If running in Eclipse, "." will just be the current project directory.
		// Use ".." to list the whole workspace directory, or enter a path to
		// some other directory.
		File rootDirectory = new File(".");

		listAllFiles(rootDirectory);

		System.out.println(countFiles(rootDirectory));
		
		
		System.out.println(countPatterns(5));
		System.out.println(countPatterns(9));
	}

	/**
	 * Print the names of all items in the hierarchy located under a given
	 * directory. If the given File object is not a directory, just prints the
	 * file's name.
	 */
	public static void listAllFiles(File f) {
		if (!f.isDirectory()) {
			// Base case: f is a file, so just print its name
			// System.out.println(f.getName());
		} else {
			// Recursive case: f is a directory, so go through the
			// files and directories it contains, and recursively call
			// this method on each one
			// System.out.println("+ " + f.getName());
			File[] files = f.listFiles();
			for (int i = 0; i < files.length; ++i) {
				listAllFiles(files[i]);
			}
		}
	}

	/**
	 * Checkpoint 2 Using listAllFiles as a guide, write a method that has one File
	 * argument: if the argument is a directory, the method returns the total number
	 * of files below it. If the argument represents a file, the method just returns
	 * 1.
	 * 
	 * @param file
	 * @return
	 */
	public static int countFiles(File file) {
		int numFiles = 0;

		if (!file.isDirectory()) {

			System.out.println(file.getName() + " *****");
			numFiles += 1;
			return numFiles;

		} else {
			File[] f = file.listFiles();

			for (int i = 0; i < f.length; ++i) {

				numFiles += countFiles(f[i]);

			}
		}
		return numFiles;
	}

	/**
	 * Checkpoint 2 pt 2 A mason is using rectangular bricks to make a walkway. He
	 * has bricks that are 3 feet long and bricks that are 1 foot long. If the
	 * walkway is a total of n feet long, how many different ways are there to lay
	 * out bricks for the walkway? For example, if n is 5, there are four
	 * possibilities:
	 * 
	 * @return
	 */
	public static int countPatterns(int walkwayLength) {
	
		//since blocks are 1 and 3 ft, if <=2 then we can only use 1 ft blocks
		if(walkwayLength <=2) {
			return 1;
		}else {
			//returns the recursive calls of the walkway -1 and -3
			return countPatterns(walkwayLength-3)+ countPatterns(walkwayLength-1);
		}
	
		
		

	

	}

}
