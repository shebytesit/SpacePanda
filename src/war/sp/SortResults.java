package war.sp;

import java.util.Comparator;

public class SortResults implements Comparator<String> {
	@Override
	public int compare(String x, String y) {
		String[] xSplit = x.split(":");
		String[] ySplit = y.split(":");

		if(Integer.parseInt(xSplit[0])<Integer.parseInt(ySplit[0])){
			return -1;
		}else if((Integer.parseInt(xSplit[0])>Integer.parseInt(ySplit[0]))){
			return 1;
		}else if(Integer.parseInt(xSplit[1])<Integer.parseInt(ySplit[1])){
			return -1;
		}else if((Integer.parseInt(xSplit[1])>Integer.parseInt(ySplit[1]))){
			return 1;
		}else if(Integer.parseInt(xSplit[2])<Integer.parseInt(ySplit[2])){
			return -1;
		}else if((Integer.parseInt(xSplit[2])>Integer.parseInt(ySplit[2]))){
			return 1;
		}else if(Integer.parseInt(xSplit[3])<Integer.parseInt(ySplit[3])){
			return -1;
		}else if((Integer.parseInt(xSplit[3])>Integer.parseInt(ySplit[03]))){
			return 1;
		}
		return 0;
	}
}