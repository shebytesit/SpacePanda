package war.sp;

import java.util.Comparator;

public class SortFull implements Comparator<Object> {
	@Override
	public int compare(Object x, Object y) {
		int xSeq;
		int ySeq;
		if(x instanceof Pointy){
			xSeq = ((Pointy)x).getSeq();
		}else{
			xSeq = ((Polygony)x).getSeq();
		}
		if(y instanceof Pointy){
			ySeq = ((Pointy)y).getSeq();
		}else{
			ySeq = ((Polygony)y).getSeq();
		}
		if(xSeq<ySeq){
			return -1;
		}else if(xSeq>ySeq){
			return 1;
		}
		return 0;
	}
}