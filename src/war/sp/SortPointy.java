package war.sp;

import java.util.Comparator;

public class SortPointy implements Comparator<Pointy> {
	@Override
	public int compare(Pointy x, Pointy y) {
		int xID = x.getID();
		int xSeq = x.getSeq();
		int yID = y.getID();
		int ySeq = y.getSeq();
		if(xID<yID){
			return -1;
		}else if(xID>yID){
			return 1;
		}else if(xSeq<ySeq){
			return -1;
		}else if(xSeq>ySeq){
			return 1;
		}
		return 0;
	}
}
