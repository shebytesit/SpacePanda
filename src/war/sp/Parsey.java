package war.sp;

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
}
