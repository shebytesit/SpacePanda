/**
 * @cs348 project
 *
 */

package war.sp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class SpacePanda {
	
	public static void main(String[] args){
		Scanner inputPoints = null;
		try {
			inputPoints = new Scanner(new FileReader(args[1]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(inputPoints.hasNext()){
			String rawInput = inputPoints.nextLine();		
		}
	}
}
