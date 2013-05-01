package war.sp;

import java.util.ArrayList;

public class Parsey {
	
	public Pointy toPoint(String rawInput){
		int colonCount = 0;
		int leftAngleBracketCount = 0;
		int rightAngleBracketCount = 0;
		String idSequenceStr = new String();
		String[] idSequenceStrSplit;
		int id = 0;
		int sequence = 0;
		String xyStr = new String();
		String[] xyStrSplit;
		double x = 0;
		double y = 0;
		for(int i = 0;i < rawInput.length(); i++){
			//Get x/y coordinates
			if(leftAngleBracketCount==2 && rightAngleBracketCount==2 && rawInput.charAt(i)=='<'){
				xyStrSplit = xyStr.split(",");
				x = Double.parseDouble(xyStrSplit[0].trim());
				y = Double.parseDouble(xyStrSplit[1].trim());
				break;
			}else if(leftAngleBracketCount==2 && rightAngleBracketCount==2){
				xyStr+=rawInput.charAt(i);
			}
			//Delimiters for parsing
			if(rawInput.charAt(i)==':'){
				colonCount++;
			}else if(rawInput.charAt(i)=='<'){
				leftAngleBracketCount++;
			}else if(rawInput.charAt(i)=='>'){
				rightAngleBracketCount++;
			}
			//Get ID/Sequence
			if(colonCount<3){
				idSequenceStr+=rawInput.charAt(i);
			}else if(colonCount==3){
				idSequenceStrSplit = idSequenceStr.split(":");
				id = Integer.parseInt(idSequenceStrSplit[1]);
				sequence = Integer.parseInt(idSequenceStrSplit[2]);
				colonCount++;
			}
		}
		return new Pointy(id,sequence,x,y);
	}
	public Polygony toPolygon(String rawInput){
		int colonCount = 0;
		int leftAngleBracketCount = 0;
		int rightAngleBracketCount = 0;
		String idSequenceStr = new String();
		String[] idSequenceStrSplit;
		int id = 0;
		int sequence = 0;
		String xyExtStr = new String();
		String xyIntStr = new String();
		String[] xyExtStrSplit;
		String[] xyIntStrSplit;
		ArrayList<Pointy> pointyExtList = null;
		ArrayList<Pointy> pointyIntList = null;
		for(int i = 0;i < rawInput.length(); i++){
			//Get all x/y coordinates of external/internal path
			if(leftAngleBracketCount==4 && rightAngleBracketCount==4 && rawInput.charAt(i)=='<'){
				pointyExtList = new ArrayList<Pointy>();
				xyExtStrSplit = xyExtStr.split(",| ");
				for(int j = 0;j < xyExtStrSplit.length;){
					pointyExtList.add(new Pointy(Double.parseDouble(xyExtStrSplit[j++].trim()),Double.parseDouble(xyExtStrSplit[j++].trim())));
				}
			}else if(leftAngleBracketCount==4 && rightAngleBracketCount==4){
				xyExtStr+=rawInput.charAt(i);
			}else if(leftAngleBracketCount==10 && rightAngleBracketCount==10 && rawInput.charAt(i)=='<'){
				pointyIntList = new ArrayList<Pointy>();
				xyIntStrSplit = xyIntStr.split(",| ");
				for(int j = 0;j < xyIntStrSplit.length;){
					pointyIntList.add(new Pointy(Double.parseDouble(xyIntStrSplit[j++].trim()),Double.parseDouble(xyIntStrSplit[j++].trim())));
				}
			}else if(leftAngleBracketCount==10 && rightAngleBracketCount==10){
				xyIntStr+=rawInput.charAt(i);
			}
			//Delimiters for parsing
			if(rawInput.charAt(i)==':'){
				colonCount++;
			}else if(rawInput.charAt(i)=='<'){
				leftAngleBracketCount++;
			}else if(rawInput.charAt(i)=='>'){
				rightAngleBracketCount++;
			}
			//Get ID/Sequence
			if(colonCount<3){
				idSequenceStr+=rawInput.charAt(i);
			}else if(colonCount==3){
				idSequenceStrSplit = idSequenceStr.split(":");
				id = Integer.parseInt(idSequenceStrSplit[1]);
				sequence = Integer.parseInt(idSequenceStrSplit[2]);
				colonCount++;
			}
		}
		return new Polygony(id,sequence,pointyExtList,pointyIntList);
	}
}
