/**
 * @cs348 project
 *
 */

package war.sp;

import java.awt.BasicStroke;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
	
public class SpacePanda {	

	public static void main(String[] args){
		//final long startTime = System.currentTimeMillis();
		String mode = new String();
		String nUnits = new String();
		String inputFilePoints = new String();
		String inputFilePolygons = new String();
		String outputFile = new String();
		if(args.length == 4 && args[0].equals("INSIDE")){
			mode = args[0];
			inputFilePoints = args[1];
			inputFilePolygons = args[2];
			outputFile = args[3];		
		}else if(args.length == 5 && args[0].equals("WITHIN")){
			mode = args[0];
			nUnits = args[1];
			inputFilePoints = args[2];
			inputFilePolygons = args[3];
			outputFile = args[4];
		}else{
			System.out.println("Please use the format: <INSIDE|WITHIN n> <PointInputFilePath> <PolygonInputFilePath> <OutputFilePath>");
			System.exit(0);
		}
		Scanner inputPointsS = null;
		Scanner inputPolyS = null;
		Parsey parsey = new Parsey();
		ArrayList<Pointy> pointyList = new ArrayList<Pointy>();
		ArrayList<Polygony> polygonyList = new ArrayList<Polygony>();
		int distinctPointyCount = 0;
		int distinctPolygonyCount = 0;
		ArrayList<String> Results = new ArrayList<String>();
		try {
			inputPointsS = new Scanner(new FileReader(inputFilePoints));
			inputPolyS = new Scanner(new FileReader(inputFilePolygons));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(inputPointsS.hasNext()){
			Pointy tempPoint = parsey.toPoint(inputPointsS.nextLine());
			if(tempPoint.getID()==distinctPointyCount)
				distinctPointyCount++;
			pointyList.add(tempPoint);	
		}
		while(inputPolyS.hasNext()){
			Polygony tempPolygon = parsey.toPolygon(inputPolyS.nextLine());
			if(tempPolygon.getID()>distinctPolygonyCount)
				distinctPolygonyCount++;
			polygonyList.add(tempPolygon);
		}
		if(mode.equals("INSIDE"))
			Results = testInside(pointyList,polygonyList,distinctPointyCount,distinctPolygonyCount);
		if(mode.equals("WITHIN"))
			Results = testWithin(pointyList,polygonyList,distinctPointyCount,distinctPolygonyCount,Integer.parseInt(nUnits));
		printResults(Results,outputFile);
		inputPointsS.close();
		inputPolyS.close();
		
		//final long endTime = System.currentTimeMillis();

		//System.out.println("Total execution time: " + (endTime - startTime) );
	}

	private static ArrayList<String> testWithin(ArrayList<Pointy> pointyList,ArrayList<Polygony> polygonyList, int distinctPointyCount,int distinctPolygonyCount,int nUnits) {
		ArrayList<String> Results = new ArrayList<String>();
		Pointy[] comparisonPointy = new Pointy[distinctPointyCount];
		Polygony[] comparisonPolygony = new Polygony[distinctPolygonyCount];
		BasicStroke scaleUp = new BasicStroke(nUnits*2);
		for(int i = 0;i < distinctPointyCount;i++){
			comparisonPointy[i] = pointyList.remove(0);
		}
		for(int i = 0;i < distinctPolygonyCount;i++){
			comparisonPolygony[i] = polygonyList.remove(0);
		}
		ArrayList<Object> fullList = new ArrayList<Object>();
		fullList.addAll(pointyList);
		fullList.addAll(polygonyList);
		Collections.sort(fullList,new SortFull());
		
		for(int k = 0;k <= fullList.size();k++){
			int pathSwitch = 0;
			int index = 0;
			if(k==0){
				pathSwitch = 1;
			}else{
				if(fullList.get(k-1) instanceof Pointy){
					Pointy tempObject = (Pointy)(fullList.get(k-1));
					index = tempObject.getID();
					comparisonPointy[index] = tempObject;
				}else{
					Polygony tempObject = (Polygony)(fullList.get(k-1));
					index = tempObject.getID() - 1;
					comparisonPolygony[index] = tempObject;
					pathSwitch = 1;
				}
			}
			if(pathSwitch==1){
				for(int i = 0;i < comparisonPointy.length;i++){
					Pointy currentPointy = comparisonPointy[i];
					for(int j = 0;j < comparisonPolygony.length;j++){
						Polygony currentPolygony = comparisonPolygony[j];
						Shape currentArea = scaleUp.createStrokedShape(currentPolygony);
						if(currentArea.contains(currentPointy)){
							Results.add(currentPointy.getID()+":"+currentPointy.getSeq()+":"+currentPolygony.getID()+":"+currentPolygony.getSeq());
						}
					}
				}
			}else{
				Pointy currentPointy = comparisonPointy[index];
				for(int j = 0;j < comparisonPolygony.length;j++){
					Polygony currentPolygony = comparisonPolygony[j];
					Shape currentArea = scaleUp.createStrokedShape(currentPolygony);
					if(currentArea.contains(currentPointy)){
						Results.add(currentPointy.getID()+":"+currentPointy.getSeq()+":"+currentPolygony.getID()+":"+currentPolygony.getSeq());
					}
				}
			}
			pathSwitch = 0;
		}
		return Results;
	}

	private static void printResults(ArrayList<String> results,String outputFile) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
			Collections.sort(results,new SortResults());
			String current = "";
			for(int i = 0;i < results.size();i++){
				if(current.isEmpty()){
					out.write(results.get(i));
				}else if(!current.equals(results.get(i)))
					out.write("\n"+results.get(i));
				current = results.get(i);
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static ArrayList<String> testInside(ArrayList<Pointy> pointyList, ArrayList<Polygony> polygonyList,int distinctPointyCount,int distinctPolygonyCount) {
		ArrayList<String> Results = new ArrayList<String>();
		Pointy[] comparisonPointy = new Pointy[distinctPointyCount];
		Polygony[] comparisonPolygony = new Polygony[distinctPolygonyCount];
		for(int i = 0;i < distinctPointyCount;i++){
			comparisonPointy[i] = pointyList.remove(0);
		}
		for(int i = 0;i < distinctPolygonyCount;i++){
			comparisonPolygony[i] = polygonyList.remove(0);
		}
		ArrayList<Object> fullList = new ArrayList<Object>();
		fullList.addAll(pointyList);
		fullList.addAll(polygonyList);
		Collections.sort(fullList,new SortFull());
		
		for(int k = 0;k <= fullList.size();k++){
			int pathSwitch = 0;
			int index = 0;
			if(k==0){
				pathSwitch = 1;
			}else{
				if(fullList.get(k-1) instanceof Pointy){
					Pointy tempObject = (Pointy)(fullList.get(k-1));
					index = tempObject.getID();
					comparisonPointy[index] = tempObject;
				}else{
					Polygony tempObject = (Polygony)(fullList.get(k-1));
					index = tempObject.getID() - 1;
					comparisonPolygony[index] = tempObject;
					pathSwitch = 1;
				}
			}
			if(pathSwitch==1){
				for(int i = 0;i < comparisonPointy.length;i++){
					Pointy currentPointy = comparisonPointy[i];
					for(int j = 0;j < comparisonPolygony.length;j++){
						Polygony currentPolygony = comparisonPolygony[j];
						if(currentPolygony.contains(currentPointy)){
							Results.add(currentPointy.getID()+":"+currentPointy.getSeq()+":"+currentPolygony.getID()+":"+currentPolygony.getSeq());
						}
					}
				}
			}else{
				Pointy currentPointy = comparisonPointy[index];
				for(int j = 0;j < comparisonPolygony.length;j++){
					Polygony currentPolygony = comparisonPolygony[j];
					if(currentPolygony.contains(currentPointy)){
						Results.add(currentPointy.getID()+":"+currentPointy.getSeq()+":"+currentPolygony.getID()+":"+currentPolygony.getSeq());
					}
				}
			}
			pathSwitch = 0;
		}
		return Results;
	}
}
