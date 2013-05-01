/**
 * @cs348 project
 *
 */

package war.sp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
	
public class SpacePanda {	

	public static void main(String[] args){
		Scanner inputPointsS = null;
		Scanner inputPolyS = null;
		Parsey parsey = new Parsey();
		ArrayList<Pointy> pointyList = new ArrayList<Pointy>();
		ArrayList<Polygony> polygonyList = new ArrayList<Polygony>();
		try {
			inputPointsS = new Scanner(new FileReader(args[0]));
			inputPolyS = new Scanner(new FileReader(args[1]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(inputPointsS.hasNext()){
			pointyList.add(parsey.toPoint(inputPointsS.nextLine()));		
		}
		while(inputPolyS.hasNext()){
			polygonyList.add(parsey.toPolygon(inputPolyS.nextLine()));
		}
		testAndPrint(pointyList,polygonyList);
	}

	private static void testAndPrint(ArrayList<Pointy> pointyList, ArrayList<Polygony> polygonyList) {
		Collections.sort(pointyList,new SortPointy());
		Collections.sort(polygonyList,new SortPolygony());
		for(int i = 0;i < pointyList.size();i++){
			Pointy currentPointy = pointyList.get(i);
			for(int j = 0;j < polygonyList.size();j++){
				Polygony currentPolygony = polygonyList.get(j);
				if(currentPolygony.contains(currentPointy.getX(),currentPointy.getY())){
					System.out.println(currentPointy.getID()+":"+currentPointy.getSeq()+":"+currentPolygony.getID()+":"+currentPolygony.getSeq());
				}
			}
		}
	}
}
