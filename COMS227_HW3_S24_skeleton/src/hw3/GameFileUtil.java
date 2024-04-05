package hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import api.BodySegment;
import api.Cell;
import api.Exit;
import api.Wall;

/**
 * Utility class with static methods for loading game files.
 */
public class GameFileUtil {
	/**
	 * Loads the file at the given file path into the given game object. When the
	 * method returns the game object has been modified to represent the loaded
	 * game.
	 * 
	 * @param filePath the path of the file to load
	 * @param game     the game to modify
	 * @throws FileNotFoundException 
	 */
	public static void load(String filePath, LizardGame game){
		// TODO: method stub
		int width;
		int height;
		File gameFile = new File(filePath);
		try {
			Scanner scanner = new  Scanner(gameFile);
			String gridDimensions = scanner.next();
			width = gridDimensions.codePointAt(0);
			height = gridDimensions.codePointAt(gridDimensions.length()-1);
			game.resetGrid(width, height);
			int count = 0;
			while(scanner.hasNextLine()) {
				count += 1;
				String line = scanner.nextLine();
				
				if(count<= height) {
					int columnInd = 0;
					for (char c : line.toCharArray()) {
						if(c == 'W') {
							Wall wall = new Wall(game.getCell(columnInd, count));
							game.addWall(wall);
						}else if(c == 'E') {
							Exit exit = new Exit(game.getCell(columnInd, count));
							game.addExit(exit);
						}
						columnInd++;
						
					}
				}
				
				
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
