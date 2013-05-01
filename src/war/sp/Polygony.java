package war.sp;

import java.awt.geom.Path2D;
import java.util.ArrayList;

public class Polygony extends Path2D.Double{
	int id = 0;
	int sequence = 0;
	public Polygony(int id, int sequence, ArrayList<Pointy> externalPath, ArrayList<Pointy> internalPath){
		super(WIND_NON_ZERO);
		this.id = id;
		this.sequence = sequence;
		if(externalPath!=null)
			this.buildIt(externalPath);
		if(internalPath!=null)
			this.buildIt(internalPath);
	}
	private void buildIt(ArrayList<Pointy> currentPath) {
		Pointy currentPoint = currentPath.get(0);
		double firstX = currentPoint.getX();
		double firstY = currentPoint.getY();
		this.moveTo(firstX,firstY);
		for(int i = 1;i < currentPath.size();i++){
			currentPoint = currentPath.get(i);
			this.lineTo(currentPoint.getX(),currentPoint.getY());
		}
		this.lineTo(firstX,firstY);
	}
	public int getID(){
		return id;
	}
	public int getSeq(){
		return sequence;
	}
}
