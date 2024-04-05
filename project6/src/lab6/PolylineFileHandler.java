package lab6;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import plotter.Plotter;
import plotter.Polyline;

/**
 * Checkpoint 2
 */
public class PolylineFileHandler {

	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Polyline> list = readFile("hello.txt");
		Plotter plotter = new Plotter();

		for (Polyline p : list) {
			plotter.plot(p);
		}
	}

	/**
	 * Then, write a helper method that opens and reads the file, and returns an
	 * ArrayList of Polyline objects, one for each line (other than comments or
	 * blank lines).
	 * 
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	public static ArrayList<Polyline> readFile(String fileName) throws FileNotFoundException {

		File file = new File(fileName);
		Scanner scnr = new Scanner(file);

		ArrayList<Polyline> polylineList = new ArrayList<Polyline>();

		while (scnr.hasNextLine()) {
			String line = scnr.nextLine();
			line = line.trim();

			if (!line.isBlank() && line.charAt(0) != '#') {
				polylineList.add(parseOneLine(line));
			}
		}
		scnr.close();

		return polylineList;

	}

	/**
	 * Write a program that will read files of the above format and plot the
	 * polylines using the plotter. Start with a helper method that parses just one
	 * line and returns the corresponding Polyline object
	 * 
	 * @param line
	 * @return
	 */

	public static Polyline parseOneLine(String line) {
		Scanner scnr = new Scanner(line);
		Polyline polyline;

		int firstNum = 1;
		int numCounter = 0;
		String color = "";
		String firstVal = scnr.next();

		if (Character.isDigit(firstVal.charAt(0))) {
			firstNum = Integer.parseInt(firstVal);
			color = scnr.next();
		}
		polyline = new Polyline(color, firstNum);

		int numOne = 0;
		int numTwo = 0;
		while (scnr.hasNextInt()) {
			numCounter += 1;
			if (numCounter % 2 == 0) {
				numTwo = scnr.nextInt();
				Point temp = new Point(numOne, numTwo);
				polyline.addPoint(temp);

			} else {
				numOne = scnr.nextInt();
			}

		}

		scnr.close();
		return polyline;

	}

}
