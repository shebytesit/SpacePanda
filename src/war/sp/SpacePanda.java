/**
 * @cs348 project
 *
 */

package war.sp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
	
public class SpacePanda {	

	public static void main(String[] args){
		Scanner inputPointsSC = null;
		Parsey parsey = new Parsey();
		ArrayList<Pointy> pointyList = new ArrayList<Pointy>();
		try {
			inputPointsSC = new Scanner(new FileReader(args[0]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(inputPointsSC.hasNext()){
			pointyList.add(parsey.toPoint(inputPointsSC.nextLine()));		
		}
		printThatShit(pointyList);
	}

	private static void printThatShit(ArrayList<Pointy> pointyList) {
		for(int i = 0;i < pointyList.size();i++){
			Pointy temp = pointyList.get(i);
			System.out.println("ID="+temp.getID()+", Sequence="+temp.getSeq()+", X="+temp.getX()+", Y="+temp.getY());
		}
	}
}
